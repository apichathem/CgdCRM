package com.locus.jlo.web.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.locus.common.domain.ServiceResult;
import com.locus.common.utils.JsonUtil;
import com.locus.jlo.web.bean.dto.UserLoginHistoryDTO;
import com.locus.jlo.web.bean.modeljson.DatatableModelBean;
import com.locus.jlo.web.service.UserLoginHistoryService;

public class LoginHistoryController extends BaseController {
	private static final Logger logger = Logger.getLogger(LoginHistoryController.class);
	
	@Autowired
	private UserLoginHistoryService userLoginHistoryService;
	
	@RequestMapping(value = "/loginHistory")
	public ModelAndView roleManagement(Model model, HttpServletRequest request, Locale local){
		model.addAttribute("pageTitle", messageSource.getMessage("loginhistory.title", null, local));
		setMenuId(request);
		
		return new ModelAndView("loginHistory");
	}
	
	@RequestMapping(value = "/getLoginHistoryList", headers = { "Accept=application/json" })
	public @ResponseBody String menuList(
			@RequestParam(value = "loginId", required=true) String loginId
			,HttpServletRequest request, Pageable pageable){
		logger.info("getLoginHistoryList");
		DatatableModelBean result =  new DatatableModelBean();
		
		ServiceResult<Page<UserLoginHistoryDTO>> serviceResult = userLoginHistoryService.searchByUserId(loginId, pageable);
		if(serviceResult.isSuccess()){
			Page<UserLoginHistoryDTO> page = serviceResult.getResult();
			Long totalElement = page.getTotalElements();			
			result.setsEcho(getSecho(request));
			result.setiTotalDisplayRecords(totalElement.intValue());
			result.setiTotalRecords(totalElement.intValue());
			result.setAaData(page.getContent());
		}
		return JsonUtil.toJSON(result, true);
	}
}
