package com.locus.jlo.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.locus.common.constant.WebPortalConstant;
import com.locus.common.utils.StringUtils;
import com.locus.jlo.web.bean.dto.UserInfoDTO;
import com.locus.jlo.web.constant.JLOWebConstant;
import com.locus.jlo.web.util.SelectorContainer;

@Component
public class BaseController {
	
	@Autowired
	protected SelectorContainer selectorContainer;
	
	@Autowired
	protected MessageSource messageSource;
	
//	private Logger logger = Logger.getLogger(getClass());
	
	protected static final String PAGEABLE_ATTRIBUTE = "pageable";
	protected static final String SESS_CRITERIA_ALREADY_SEARCH = "alreadySearch";
	
	protected void setMenuId(HttpServletRequest request) {
		String menuId = request.getParameter(JLOWebConstant.SESSION_MENU_ID);
		if (menuId != null && !"".equals(menuId)) {
			//logger.info("MENU ID [ " + menuId + " ]");
			removeSessionAttr(request, JLOWebConstant.SESSION_MENU_ID);
			setSessionAttr(request, JLOWebConstant.SESSION_MENU_ID, menuId);
		}
	}
	
	protected Object getSessionMenuId(HttpServletRequest request) {
		return getSessionAttr(request, JLOWebConstant.SESSION_MENU_ID);
	}

	protected void showWebMessage(HttpServletRequest request, String code, String desc) {
		if (code == null) {
			code = JLOWebConstant.FAIL_CODE;
		}
		if (desc == null) {
			desc = JLOWebConstant.FAIL_DESC;
		}
		setSessionAttr(request,WebPortalConstant.STRING_RESULT_TITLE, code);
		setSessionAttr(request,WebPortalConstant.STRING_RESULT_DESC, desc);
	}
	
	protected void showWebMessage(HttpServletRequest request, String code, String desc, String title) {
		if (code == null) {
			code = JLOWebConstant.FAIL_CODE;
		}
		if (desc == null) {
			desc = JLOWebConstant.FAIL_DESC;
		}
		if (title == null) {
			title = "";
		}
		setSessionAttr(request,WebPortalConstant.STRING_RESULT_CODE, code);
		setSessionAttr(request,WebPortalConstant.STRING_RESULT_DESC, desc);
		setSessionAttr(request,WebPortalConstant.STRING_RESULT_TITLE, title);
	}
	
	protected Object getSessionAttr(HttpServletRequest request, String code) {
		return request.getSession().getAttribute(code);
	}
	
	protected void setSessionAttr(HttpServletRequest request, String code, Object obj) {
		request.getSession().setAttribute(code, obj);
	}
	
	protected void removeSessionAttr(HttpServletRequest request, String code) {
		request.getSession().removeAttribute(code);
	}
	
	protected String getLanguageCode(Locale locale) {
		try {
			return locale.getLanguage().toUpperCase();
		} catch (Exception e) {
			return "";
		}
	}
//	public LoginInfo getLoginInfo(){
//		LoginInfo loginInfo = null;
//		try{
//			if(SecurityContextHolder.getContext().getAuthentication().getPrincipal()!=null){
//				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//				if(auth != null && auth.isAuthenticated()){
//					loginInfo = (LoginInfo)auth.getPrincipal();
//				}else{
//					return null;
//				}
//			}else{
//				return null;
//			}
//		
//			if(loginInfo!=null && loginInfo.getAuthorities()!=null && loginInfo.getAuthorities().size() > 0){
//				return loginInfo;
//			}else{
//				return null;
//			}
//		
//		}catch(Exception ex){
//			return null;
//		}
//	}
	
	protected Pageable getPagableFromRequest(HttpServletRequest request) {
		String iDisplayStartTxt = request.getParameter("iDisplayStart");
		String iDisplayLengthTxt = request.getParameter("iDisplayLength");
		
		Integer iDisplayStart = (!StringUtils.isNullOrEmpty(iDisplayStartTxt)) ? Integer.valueOf(iDisplayStartTxt) : 0;
		Integer iDisplayLength = (!StringUtils.isNullOrEmpty(iDisplayLengthTxt)) ? Integer.valueOf(iDisplayLengthTxt) : 0;
		Integer cPage = 0;
		
		if (iDisplayStart != 0) {
			cPage = (iDisplayStart / iDisplayLength) ;
		}

		return new PageRequest(cPage, iDisplayLength);
	}
	
	protected Integer getSecho(HttpServletRequest request) {
		String sEchoTxt = request.getParameter("sEcho");
		Integer sEcho = (!StringUtils.isNullOrEmpty(sEchoTxt)) ? Integer.valueOf(sEchoTxt) : 0;
		
		return sEcho;
	}
	
	protected UserInfoDTO getUserInfo(HttpServletRequest request) {
		return (UserInfoDTO) request.getSession().getAttribute(WebPortalConstant.USER_PROFILE);
	}
	
	protected Integer getUserId(HttpServletRequest request) {
		try {
			return getUserInfo(request).getUserId();
		} catch (Exception e) {
			return 0;
		}
		
	}
	
	protected String getUserLoginId(HttpServletRequest request) {
		try {
			return getUserInfo(request).getLoginId();
		} catch (Exception e) {
			return "";
		}
	}
	
	
	protected List<String> getOwnerGroup(HttpServletRequest request, String action) {
		return getOwnerGroup(request, action, null);
	}
	
	@SuppressWarnings("unchecked")
	protected List<String> getOwnerGroup(HttpServletRequest request, String action, String inputMenuId) {
		
		String menuId = "";
		if (!StringUtils.isNullOrEmpty(inputMenuId)) {
			menuId = inputMenuId;
		} else {
			menuId = (String) request.getSession().getAttribute(JLOWebConstant.SESSION_MENU_ID);
		}
		
		HashMap< String, HashMap<String, String> > privilegeMap = (HashMap< String, HashMap<String, String> >) request.getSession().getAttribute(WebPortalConstant.PRIVILEGE_PROFILE);
		HashMap<String, String> byMenu = privilegeMap.get(menuId);
		String actionValue = byMenu==null?"ALL":byMenu.get(action);
//		logger.info("actionValue : " + actionValue);
		if ("ALL".equalsIgnoreCase(actionValue)) {
			return new ArrayList<String>();
		} else if ("TEAM".equalsIgnoreCase(actionValue)) {
			return getMyTeamList(request);
		} else if ("OWNER".equalsIgnoreCase(actionValue)) {
			List<String> res = new ArrayList<String>();
			res.add(String.valueOf(getUserId(request)));
			return res;
		} else {
			return null;
		}
	}
	
	protected Boolean checkVisibility(HttpServletRequest request, String action, Integer ownerId) {
		if (ownerId == null || "".equals(ownerId)) {
			return false;
		} else {
			List<String> ownerGroup = getOwnerGroup(request, action);
			
			if (ownerGroup != null) {
				if (ownerGroup.size() > 0) {
					return ownerGroup.contains(String.valueOf(ownerId));
				} else {
					return true;
				}
				
			} else {
				return false;
			}
		}
	}
	
	protected Boolean checkVisibility(HttpServletRequest request, String action, Integer ownerId, String inputMenuId) {
		if (ownerId == null || "".equals(ownerId)) {
			return false;
		} else {
			List<String> ownerGroup = getOwnerGroup(request, action, inputMenuId);
			
			if (ownerGroup != null) {
				if (ownerGroup.size() > 0) {
					return ownerGroup.contains(String.valueOf(ownerId));
				} else {
					return true;
				}
				
			} else {
				return false;
			}
		}
	}
	
	protected List<String> getMyTeamList(HttpServletRequest request) {
		try {
			return getUserInfo(request).getMyTeamUserId();
		} catch (Exception e) {
			return null;
		}
	}
}
