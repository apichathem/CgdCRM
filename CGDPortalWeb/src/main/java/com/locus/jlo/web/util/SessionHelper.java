package com.locus.jlo.web.util;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.locus.common.constant.WebPortalConstant;
import com.locus.common.domain.ServiceResult;
import com.locus.common.utils.CustomPropertyPlaceholderConfigurer;
import com.locus.jlo.web.bean.dto.LoginDTO;
import com.locus.jlo.web.bean.dto.PrivilegeDTO;
import com.locus.jlo.web.bean.modelbean.CodebookModelBean;
import com.locus.jlo.web.service.CodebookService;
import com.locus.jlo.web.service.UserManagementService;

@Component
public class SessionHelper {

	private static Logger logger = Logger.getLogger(SessionHelper.class);
	
	private static SessionHelper my;
	
	@PostConstruct
    public void registerInstance() {
        my = this;
    }
	
//	@Autowired
//	private CodebookLangRepository codebookLangRepository;
	
	@Autowired
	private CodebookService codebookService;
	
	@Autowired
	private UserManagementService userManagementService;
	

	public static LoginDTO getCurrentLoginInfo(){
		if(!isAuthenticated()) return null;
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		return (LoginDTO)auth.getPrincipal();
	}
	
	public static boolean isAuthenticated()
	{
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && !(auth instanceof AnonymousAuthenticationToken)) {
			return auth.isAuthenticated();
		}
		
		return false;
	}
	
	
	public static String getCurrentPermissionInfo(HttpServletRequest request) {
		return (String) request.getSession().getAttribute(WebPortalConstant.PERMISSION_PROFILE);
	}
	
//	/**
//	 * 
//	 * @param request HttpServletRequest
//	 * @return void
//	 */
//	public static void initCodeBook(HttpServletRequest request, Locale local) {
//		logger.info("init CodeBook");
//		Timestamp startTime = new Timestamp(new java.util.Date().getTime());
//		ServiceResult<List<CodebookModelBean>> lstcode = my.codebookService.searchAll(local.getLanguage().toUpperCase());
//		Timestamp dbTime = new Timestamp(new java.util.Date().getTime());
//		logger.info("Time Query DB Codebook All "+((dbTime.getTime()-startTime.getTime())/1000.0)+" sec");
//		Timestamp startmapTime = new Timestamp(new java.util.Date().getTime());
//		if(lstcode!=null){
//			java.util.Map<String, List<CodebookModelBean>> map = new java.util.HashMap<String, List<CodebookModelBean>>();  
//			
//			for (CodebookModelBean code : lstcode.getResult()) {
//				if(map.containsKey(code.getCodeType())){
//					List<CodebookModelBean> lcode = map.get(code.getCodeType());
//					lcode.add(code);
//					map.put(code.getCodeType(), lcode);
//				}else{
//					List<CodebookModelBean> lcode = new ArrayList<CodebookModelBean>();
//					lcode.add(code);
//					map.put(code.getCodeType(), lcode);
//				}
//				logger.debug(code.getCodeType()+"="+code.getCodeName());
//			}
//			request.getSession().setAttribute(WebPortalConstant.CODEBOOK_LIST,map);
//		}
//		Timestamp endTime = new Timestamp(new java.util.Date().getTime());
//		logger.info("Time loop Codebook All "+((endTime.getTime()-startmapTime.getTime())/1000.0)+" sec");
//		logger.info("Time set Codebook All "+((endTime.getTime()-startTime.getTime())/1000.0)+" sec");
//	}
//	/**
//	 * 
//	 * @param request HttpServletRequest
//	 * @return void
//	 */
//	public static void clearCodeBook(HttpServletRequest request, Locale local){
//		logger.info("remove CodeBook");
//		request.getSession().removeAttribute(WebPortalConstant.CODEBOOK_LIST);
//		initCodeBook(request, local);
//	}
	
	public static String getMenuByLangCd(String langCd, String captions) {
		// Get caption by language code
		String languageCode = CustomPropertyPlaceholderConfigurer.getProperty("language.code");
		String[] languageCodes = languageCode.split(",");
		
		if (captions != null && !"".equals(captions)) {
			
			HashMap<String, String> captionMap = new HashMap<String, String>();
			int pos = 0;
			
			for (int i = 0; i < languageCodes.length; i++) {
				String indexOf = "[" + languageCodes[i] + "]";
				String key = languageCodes[i];
				
				if (captions.indexOf(indexOf) != -1) {
					String value = captions.substring(pos, captions.indexOf(indexOf));
					captionMap.put(key, value);
					pos = captions.indexOf("[" + languageCodes[i] + "]") + 4;
				}
				
			}
			
			return captionMap.get(langCd);
		} else {
			return "";
		}
	}

	public static void resetCodeBook() {
		logger.debug("resetCodeBook");
		Map<String,Map<String, List<CodebookModelBean>>> codebookMap = new HashMap<String,Map<String, List<CodebookModelBean>>>();
		String[] langs = new String[]{"EN","TH"};
		for (String lang : langs) {
			ServiceResult<List<CodebookModelBean>> lstcode = my.codebookService.searchAll(lang);
			if(lstcode!=null){
				java.util.Map<String, List<CodebookModelBean>> map = new java.util.HashMap<String, List<CodebookModelBean>>();  
				
				for (CodebookModelBean code : lstcode.getResult()) {
					if(map.containsKey(code.getCodeType())){
						List<CodebookModelBean> lcode = map.get(code.getCodeType());
						lcode.add(code);
						map.put(code.getCodeType(), lcode);
					}else{
						List<CodebookModelBean> lcode = new ArrayList<CodebookModelBean>();
						lcode.add(code);
						map.put(code.getCodeType(), lcode);
					}
					logger.debug(code.getCodeType()+"="+code.getCodeName());
				}
				codebookMap.put(lang, map);
			}
		}
		CodeBookHelper.CODEBOOK_LIST = codebookMap;
	}
	
	/*public static void initMenu(HttpServletRequest request, Integer roleId, String langCd) {
		List<UserMenuModelBean> menuResultList = new ArrayList<UserMenuModelBean>();
		
		request.getSession().removeAttribute(WebPortalConstant.MENU_PROFILE);
				
		ServiceResult<List<TpMenu>> roleServiceResult = my.roleManagementService.searchUserMenuByRoleId(roleId, null);
		if (roleServiceResult.isSuccess()) {
			List<TpMenu> menuList = roleServiceResult.getResult();
			
			if (CollectionUtil.isNotEmpty(menuList)) {
				for (TpMenu menu : menuList) {
					UserMenuModelBean mainUserMenuModelBean = setMainMenuBean(menu, langCd);
					
					// Get sub menu list for each parent.
					Integer parentId = menu.getMenuId();
					ServiceResult<List<TpMenu>> subMenuResult =  my.roleManagementService.searchUserMenuByRoleId(roleId, parentId);
					if(subMenuResult.isSuccess()) {
						List<TpMenu> subMenuList = subMenuResult.getResult();
						
						if (CollectionUtil.isNotEmpty(subMenuList)) {
							for (TpMenu subMenu : subMenuList) {
								setSubMenuBean(subMenu, mainUserMenuModelBean, menuResultList, langCd);
							}
						}
					}
					
					menuResultList.add(mainUserMenuModelBean);
				}
			}
		} else {
			logger.error("Cannot get menu detail : " + roleServiceResult.getResponseDescription());
		}
		
		request.getSession().setAttribute(WebPortalConstant.MENU_PROFILE, menuResultList);
	}*/
	
	/*private static UserMenuModelBean setMainMenuBean(TpMenu tpMenu, String langCd) {
			Integer key = tpMenu.getMenuId();
			
			// Set model
			UserMenuModelBean userMenuModelBean = new UserMenuModelBean();
			userMenuModelBean.setMenuId(key.toString());
			userMenuModelBean.setAction(tpMenu.getAction());
			userMenuModelBean.setIconName(tpMenu.getIconPath());
			
			// Get caption by language code
			userMenuModelBean.setMenuName(getMenuByLangCd(langCd, tpMenu.getCaption()));
			
			return userMenuModelBean;
	}
	
	private static void setSubMenuBean(TpMenu tpMenu, UserMenuModelBean main, List<UserMenuModelBean> menuList, String langCd) {
		Integer key = tpMenu.getMenuId();
		
		// Set model
		UserMenuModelBean userMenuModelBean = new UserMenuModelBean();
		userMenuModelBean.setMenuId(key.toString());
		userMenuModelBean.setAction(tpMenu.getAction());
		userMenuModelBean.setIconName(tpMenu.getIconPath());
		
		// Get caption by language code
		userMenuModelBean.setMenuName(getMenuByLangCd(langCd, tpMenu.getCaption()));
		
		main.getChild().add(userMenuModelBean);
	}*/
	
	public static void initPrivilege(HttpServletRequest request, Integer roleId) {
		ServiceResult<List<PrivilegeDTO>> serviceResult = my.userManagementService.getMenuPrivilege(roleId.toString());
		HashMap< String, HashMap<String, String> > privilegeMap = new HashMap<String, HashMap<String, String>>();
		
		if (serviceResult.isSuccess()) {
			
			List<PrivilegeDTO> res = serviceResult.getResult();
			Integer menuId = null;
			HashMap<String, String> actionMap = new HashMap<String, String>();
			int loopsize = res.size();
			if (res != null) {
				int i = 1;
				for (PrivilegeDTO privilegeDTO : res) {
					if (menuId == null) {
						menuId = privilegeDTO.getMenuId();
					}
					
					if (privilegeDTO.getMenuId().equals(menuId)) {
						actionMap.put(privilegeDTO.getActionName(), privilegeDTO.getRoleActionValue());
						
					} else {
						privilegeMap.put(String.valueOf(menuId), actionMap);
						actionMap = new HashMap<String, String>();
						actionMap.put(privilegeDTO.getActionName(), privilegeDTO.getRoleActionValue());
						menuId = privilegeDTO.getMenuId();
					}
					
					if (i == loopsize) {
						privilegeMap.put(String.valueOf(menuId), actionMap);
					}
					
					i++;
				}
			}
		} else {
			logger.error("Cannot get privilege : " + serviceResult.getResponseDescription());
		}
		logger.info("privilegeMap : " + privilegeMap);
		request.getSession().setAttribute(WebPortalConstant.PRIVILEGE_PROFILE, privilegeMap);
	}
	
/*	public static void initCustomerTabId(HttpServletRequest request) {
		String customerTabList = CustomPropertyPlaceholderConfigurer.getProperty("customer.tabList");
		String[] customerTabs = customerTabList.split(",");
		List<String> listOfCustTab = new ArrayList<String>(Arrays.asList(customerTabs));

		ServiceResult<List<TpMenu>> customerMenuTab = my.menuManagementService.searchMenuListByName(listOfCustTab);
		if (customerMenuTab.isSuccess()) {
			request.getSession().setAttribute(WebPortalConstant.CUSTOMER_TAB_LIST, customerMenuTab.getResult());
		}
		
	}*/
}
