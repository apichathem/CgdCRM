package com.locus.jlo.web.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.locus.common.constant.WebPortalConstant;

public class FailureLoginHandler implements AuthenticationFailureHandler {
	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	private MessageSource messageSource;

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

		logger.debug("onAuthenticationFailure : ......");
		logger.debug("*******************************");
		logger.debug("exception : " + exception.getMessage());

		boolean isAjax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
		logger.info("isAjax : " + isAjax);

		if (isAjax) {
			String jsonObject = "{\"status\": 500," + "\"message\":\"" + exception.getMessage() + "\"}";
			String contentType = "application/json;charset=UTF-8";
			response.setContentType(contentType);
			PrintWriter out = response.getWriter();
			out.print(jsonObject);
			out.flush();
			out.close();
			return;
		} else {
			request.getSession().setAttribute(WebPortalConstant.STRING_RESULT_CODE, messageSource.getMessage("LOGIN_0900", null, Locale.getDefault()));
			request.getSession().setAttribute(WebPortalConstant.STRING_RESULT_DESC, exception.getMessage());
			response.sendRedirect("login.htm?login_error=true");
		}
	}

}
