package com.locus.jlo.web.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.locus.common.domain.ServiceResult;
import com.locus.common.utils.JsonUtil;
import com.locus.common.utils.StringUtils;
import com.locus.jlo.web.bean.modelbean.MenuDetailModelBean;
import com.locus.jlo.web.bean.modeljson.DatatableModelBean;
import com.locus.jlo.web.constant.JLOWebConstant;
import com.locus.jlo.web.service.MenuService;

@Controller
public class MenuManagementController extends BaseController {

	private Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private MenuService menuService;
	
	@RequestMapping(value = "/menuManagement")
	public ModelAndView menuManagement(Model model, HttpServletRequest request, Locale local){
		model.addAttribute("pageTitle", messageSource.getMessage("menuManagement.title", null, local));
		setMenuId(request);
		
		return new ModelAndView("menuManagement");
	}
	
	@RequestMapping(value = "/getMenuList", headers = { "Accept=application/json" })
	public @ResponseBody String menuList(@RequestParam(value = "menuName", required=true) String menuName,
			HttpServletRequest request) {
		
		DatatableModelBean result =  new DatatableModelBean();
		ServiceResult<List<MenuDetailModelBean>> serviceResult = menuService.findListByName(menuName);
		if(serviceResult.isSuccess()){
			List<MenuDetailModelBean> list = serviceResult.getResult();
			result.setsEcho(getSecho(request));
			result.setiTotalDisplayRecords(list.size());
			result.setiTotalRecords(list.size());
			result.setAaData(list);
			
		} else {
			result.setsEcho(1);
			result.setiTotalDisplayRecords(0);
			result.setiTotalRecords(0);
			result.setAaData(null);
		}
		
		return JsonUtil.toJSON(result, true );	
	}
	
	@RequestMapping(value = "/initMenu")
	public ModelAndView initMenu(Model model, HttpServletRequest request, Locale local){
		model.addAttribute("pageTitle", messageSource.getMessage("menuManagement.title", null, local));
		
		MenuDetailModelBean modelDomain = null;
		if(StringUtils.isEmpty(request.getParameter("menuId"))){
			modelDomain = new MenuDetailModelBean();
		} else {
			ServiceResult<MenuDetailModelBean> serviceResult = menuService.findById(request.getParameter("menuId"));
			
			if(serviceResult.isSuccess()){
				modelDomain = serviceResult.getResult();
				modelDomain.setMode(JLOWebConstant.MODE_UPDATE);
			}else{
				modelDomain = new MenuDetailModelBean();
			}
		}
		
		ServiceResult<List<MenuDetailModelBean>> serviceParent = menuService.findListAllParentMenu();
		if(serviceParent.isSuccess()){
			logger.info("parentSelect size " + serviceParent.getResult().size());
			model.addAttribute("parentSelect", serviceParent.getResult());
		}
		return new ModelAndView("menuDetail","MenuDetailModelBean", modelDomain);
		
	}
	
	@RequestMapping(value = "/saveMenu")
	public @ResponseBody String saveMenu(@ModelAttribute("MenuDetailModelBean") MenuDetailModelBean menuDetailModelBean,HttpServletRequest request){
		if(menuDetailModelBean.isInsertMode()){
			menuDetailModelBean.setCreateBy(getUserId(request).toString());
			ServiceResult<Long> serviceResult = menuService.insert(menuDetailModelBean);
			if(serviceResult.isSuccess()){
				menuDetailModelBean.setMenuId(serviceResult.getResult().intValue());
			}
		}else if(menuDetailModelBean.isUpdateMode()){
			menuDetailModelBean.setUpdateBy(getUserId(request).toString());
			menuService.update(menuDetailModelBean);
		}
		String url = "/initMenu.htm?menuId="+menuDetailModelBean.getMenuId();
		return JsonUtil.toJSON(url, true );	
	}
}
