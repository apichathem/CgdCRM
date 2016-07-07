package com.locus.jlo.web.interceptor;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.locus.common.constant.WebPortalConstant;
import com.locus.jlo.web.constant.JLOWebConstant;
import com.locus.jlo.web.constant.SessionUserConstant;

public class PermissionInterceptor implements HandlerInterceptor  {
	Logger logger = Logger.getLogger(getClass());
	
	@Override
	public void afterCompletion(HttpServletRequest request,	HttpServletResponse response, Object handler, Exception ex) throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		HashMap< String, HashMap<String, String> > privilegeMap = (HashMap< String, HashMap<String, String> >) request.getSession().getAttribute(WebPortalConstant.PRIVILEGE_PROFILE);
		String menuId = (String) request.getSession().getAttribute(JLOWebConstant.SESSION_MENU_ID);
		String actionValue = request.getParameter(JLOWebConstant.ACTION_VALUE);
//		logger.info("request " + request.getRequestURL());
//		logger.info("actionValue " + actionValue);
		if (privilegeMap != null) {
			HashMap<String, String> priv = privilegeMap.get(menuId);
			String action = request.getParameter("action");
			
			logger.info("preHandle - " + menuId + " " + priv);
			logger.info("preHandle - action : " + action);
		}
		
		// If no permission return false
		return true;
	}

}
