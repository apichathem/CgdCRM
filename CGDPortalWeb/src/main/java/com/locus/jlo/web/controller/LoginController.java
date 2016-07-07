package com.locus.jlo.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.locus.common.constant.WebPortalConstant;
import com.locus.jlo.web.constant.SessionUserConstant;
import com.locus.jlo.web.service.UserManagementService;

@Controller
public class LoginController extends BaseController {
	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	private UserManagementService userInformationService;


	@Value(value = "${email.from}")
	private String emailFrom;

	@Value(value = "${email.fromName}")
	private String emailFromName;

	@RequestMapping(value = "/login")
	public ModelAndView login(Model model, HttpServletRequest request, Locale local) {
		model.addAttribute("pageTitle", messageSource.getMessage("login.title", null, local));
		return new ModelAndView("login");
	}

	@RequestMapping(value = "/logout")
	public ModelAndView logout(Model model, HttpServletRequest request, HttpServletResponse response, Locale local) throws IOException {
		logger.debug("LoginController - logout");
		logger.debug("*****************************************************");
		model.addAttribute("pageTitle", messageSource.getMessage("login.title", null, local));

		removeSessionAttr(request, WebPortalConstant.USER_PROFILE);
		removeSessionAttr(request, WebPortalConstant.AUTHORITY_PROFILE);
		removeSessionAttr(request, WebPortalConstant.MENU_PROFILE);
		removeSessionAttr(request, WebPortalConstant.PERMISSION_PROFILE);
		removeSessionAttr(request, SessionUserConstant.SESSION_USER_CONSULTING_OBJ);
		removeSessionAttr(request, SessionUserConstant.SESSION_USER_CALL_LIST_OBJ);

		SecurityContextHolder.clearContext();

		boolean isAjax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
		logger.info("isAjax : " + isAjax);
		if (isAjax) {
			String jsonObject = "{\"status\": 401," + "\"message\":\"Session Timeout.\"}";
			String contentType = "application/json;charset=UTF-8";
			response.setContentType(contentType);
			PrintWriter out = response.getWriter();
			out.print(jsonObject);
			out.flush();
			out.close();
			return null;
		} else {
				return new ModelAndView("login");
			
		}
	}

	@RequestMapping(value = "/idletimeoutkeepalive")
	public @ResponseBody
	String idletimeoutkeepalive(HttpServletRequest request) {
		logger.debug("idletimeoutkeepalive...");
		return "1";
	}

}
