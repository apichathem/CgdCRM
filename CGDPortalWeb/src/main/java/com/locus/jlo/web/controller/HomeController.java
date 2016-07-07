package com.locus.jlo.web.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.locus.common.constant.WebPortalConstant;
import com.locus.jlo.web.bean.dto.UserInfoDTO;
import com.locus.jlo.web.bean.modelbean.CodebookModelBean;
import com.locus.jlo.web.util.CodeBookHelper;

@Controller
public class HomeController extends BaseController {

	private Logger logger = Logger.getLogger(getClass());

	@RequestMapping(value = "/home")
	public ModelAndView home(Model model, HttpServletRequest request, Locale locale, HttpSession session) {
		model.addAttribute("pageTitle", messageSource.getMessage("home.title", null, locale));
		logger.info("HomeController - home");
		logger.info("*******************************************");
		UserInfoDTO userInfoDTO = (UserInfoDTO) getSessionAttr(request, WebPortalConstant.USER_PROFILE);
		userInfoDTO.getDepartmentCd();
		logger.info("Department : " + userInfoDTO.getDepartmentCd() + " " + userInfoDTO.getDepartmentName());
		logger.info("Name : " + userInfoDTO.getFirstName() + " " + userInfoDTO.getLastName());

		return new ModelAndView("home");
	}

	@RequestMapping(value = "/download")
	public ModelAndView download(Model model, HttpServletRequest request, Locale locale, HttpSession session) {
		List<CodebookModelBean> downloadList = CodeBookHelper.getByCodeTypeAndParentId(request, "LATEST_VERSION", "1");
		model.addAttribute("downloadList", downloadList);
		return new ModelAndView("download");
	}

}
