package com.locus.jlo.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.locus.common.domain.ServiceResult;
import com.locus.common.utils.CollectionUtil;
import com.locus.common.utils.JsonUtil;
import com.locus.jlo.web.bean.dto.PrivilegeDTO;
import com.locus.jlo.web.bean.dto.SysActionDTO;
import com.locus.jlo.web.bean.modelbean.MenuDetailModelBean;
import com.locus.jlo.web.bean.modelbean.RoleModelBean;
import com.locus.jlo.web.bean.modeljson.DatatableModelBean;
import com.locus.jlo.web.constant.JLOWebConstant;
import com.locus.jlo.web.service.MenuService;
import com.locus.jlo.web.service.RoleService;

@Controller
public class RoleManagementController extends BaseController {
	public static final String ROLE_PRIV_ACT = "rolePrivAction";

	private Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private MenuService menuService;
	
	@Autowired
	private RoleService roleService;
	
	@RequestMapping(value = "/roleManagement")
	public ModelAndView roleManagement(Model model, HttpServletRequest request, Locale local){
		model.addAttribute("pageTitle", messageSource.getMessage("roleManagement.title", null, local));
		setMenuId(request);
		
		return new ModelAndView("roleManagement");
	}
	
	@RequestMapping(value = "/getRoleList", headers = { "Accept=application/json" })
	public @ResponseBody String menuList(
			@RequestParam(value = "roleName", required=true) String roleName
			,HttpServletRequest request, Pageable pageable){
		DatatableModelBean result =  new DatatableModelBean();
		ServiceResult<Page<RoleModelBean>> serviceResult = roleService.findListByName(roleName,pageable);
		if(serviceResult.isSuccess()){
			Page<RoleModelBean> page = serviceResult.getResult();
			Long totalElement = page.getTotalElements();			
			result.setsEcho(getSecho(request));
			result.setiTotalDisplayRecords(totalElement.intValue());
			result.setiTotalRecords(totalElement.intValue());
			result.setAaData(page.getContent());
		}
		return JsonUtil.toJSON(result, true);
	}
	
	@RequestMapping(value = "/initManageRole")
	public ModelAndView initManageRole(Model model, HttpServletRequest request, Locale local){
		model.addAttribute("pageTitle", messageSource.getMessage("roleManagement.title", null, local));
		List<String> listRole = null;
		if(request.getParameter("roleId")==null){
			model.addAttribute("roleModelDomain", new RoleModelBean());
		}else{
			ServiceResult<RoleModelBean> serviceResult = roleService.findById(request.getParameter("roleId"));
			if(serviceResult.isSuccess()){
				RoleModelBean modelDomain = serviceResult.getResult();
				modelDomain.setMode(JLOWebConstant.MODE_UPDATE);
				model.addAttribute("roleModelDomain", modelDomain);
				ServiceResult<List<String>> serviceResult2 = roleService.findRoleMenuByRoleId(modelDomain.getRoleId());
				listRole = serviceResult2.getResult();
				model.addAttribute("roleMenuList", listRole);
			}else{
				model.addAttribute("roleModelDomain", new RoleModelBean());
			}
		}		
		ServiceResult<List<MenuDetailModelBean>> menuResult = menuService.findListMenuEnabled();
		List<MenuDetailModelBean> detailModelDomains = menuResult.getResult();
		List<MenuDetailModelBean> listMenu = new ArrayList<MenuDetailModelBean>();
		if(listRole==null){
			listMenu.addAll(detailModelDomains);
		}else{
			for (MenuDetailModelBean menuDetailModelBean : detailModelDomains) {
				if(listRole.indexOf(menuDetailModelBean.getMenuId().toString())!=-1){
					menuDetailModelBean.setChecked("checked");
				}
				listMenu.add(menuDetailModelBean);
			}
		}
		model.addAttribute("menuList", listMenu);
		return new ModelAndView("roleDetail");
	}
	
	@RequestMapping(value = "/saveRole", headers = { "Accept=application/json" })
	public @ResponseBody String saveRole(
			@ModelAttribute("roleModelDomain") RoleModelBean roleModelBean,HttpServletRequest request){
		if(roleModelBean.isInsertMode()){
			roleModelBean.setCreateBy(getUserId(request).toString());
			ServiceResult<Long> serviceResult = roleService.insert(roleModelBean);
			roleModelBean.setRoleId(serviceResult.getResult().intValue());
		}else{
			roleModelBean.setUpdateBy(getUserId(request).toString());
			roleService.update(roleModelBean);
		}
		String url="/initManageRole.htm?roleId="+roleModelBean.getRoleId();
		return JsonUtil.toJSON(url, true);
	}
	
	@RequestMapping(value = "/saveRoleMenu", headers = { "Accept=application/json" })
	public @ResponseBody String saveRoleMenu(HttpServletRequest request){
		String roleId = request.getParameter("roleId");
		Integer userId = getUserId(request);
		String[] menuAuths = request.getParameterValues("menuAuth");
		
		roleService.resetRoleMenu(roleId);
		
		for (String menuId : menuAuths) {
			ServiceResult<List<SysActionDTO>> roleActionListRes = roleService.findSysAction();
			if (roleActionListRes.isSuccess()) {
				List<SysActionDTO> roleActionList = roleActionListRes.getResult();
				
				if (CollectionUtil.isNotEmpty(roleActionList)) {
					for (SysActionDTO sysActionDTO : roleActionList) {
						roleService.inserOrUpdatePrivilage(Integer.valueOf(roleId), Integer.valueOf(menuId), sysActionDTO.getActionCd(), "01", userId);
					}
				}
			}
		}		
		
		String url="/initManageRole.htm?roleId="+roleId;
		return JsonUtil.toJSON(url, true);
	}
	
	@RequestMapping(value = "/rolePrivilegeManagement")
	public ModelAndView rolePrivilegeManagement(Model model, HttpServletRequest request, Locale local,
			@RequestParam(value = "roleId", required=true) String roleId){
		logger.info("rolePrivilegeManagement : " + roleId);
		
		model.addAttribute("pageTitle", messageSource.getMessage("roleManagement.title", null, local));
		setMenuId(request);
		
		ServiceResult<List<PrivilegeDTO>> res = roleService.findRolePrivilegeList(Integer.parseInt(roleId));
		if (res.isSuccess()) {
			List<PrivilegeDTO> privilegeDomainList = res.getResult();
			
			HttpSession session = request.getSession();
			session.removeAttribute(ROLE_PRIV_ACT);
			session.setAttribute(ROLE_PRIV_ACT, privilegeDomainList);
		}
		
		model.addAttribute("roleId", roleId);
		return new ModelAndView("rolePrivilege");
	}
	
	@RequestMapping(value = "/saveRoleAction", headers = { "Accept=application/json" })
	public @ResponseBody String saveRoleAction(Model model, HttpServletRequest request, Locale local) {
		String roleId = request.getParameter("roleId");
		logger.info("saveRoleAction : " + roleId);
		
		String [] menuIds = request.getParameterValues("menuId");
		String [] actionCodes = request.getParameterValues("actionCode");
		String [] actionValues = request.getParameterValues("actionValue");
		
		Integer userId = getUserId(request);
		
		for (int i = 0; i < menuIds.length; i++) {
			logger.info(menuIds[i] + " " + actionCodes[i] + " " + actionValues[i]);
			roleService.inserOrUpdatePrivilage(new Integer(roleId), 
					new Integer(menuIds[i]), 
					actionCodes[i], 
					actionValues[i], 
					userId);
		}
		
		String url="/rolePrivilegeManagement.htm?roleId=" + roleId;
		return JsonUtil.toJSON(url, true);
	}
	
}
