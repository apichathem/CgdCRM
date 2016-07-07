package com.locus.jlo.web.interceptor;

import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.locus.common.constant.WebPortalConstant;
import com.locus.common.utils.CustomPropertyPlaceholderConfigurer;
import com.locus.jlo.web.bean.dto.LoginDTO;
import com.locus.jlo.web.util.CodeBookHelper;
import com.locus.jlo.web.util.SessionHelper;


public class SecurityDetectionInterceptor implements HandlerInterceptor  {

	
	private Logger logger = Logger.getLogger(getClass());
	
	/**
	 * @author Love
	 * @date 09/11/2013
	 * @version 1.0
	 * @description Security Detection Interceptor 
	 */
	
	@Autowired
	private MessageSource messageSource;
	
    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
        
        logger.debug("SecurityDetectionInterceptor - preHandle");
        logger.debug("******************************************************************");
        LoginDTO loginInfo = null;
        loginInfo = SessionHelper.getCurrentLoginInfo();
        
        logger.debug("	Check Switch Lang : to reload menu ...");
        if(isChangeLang(request)){
//			SessionHelper.clearCodeBook(request, new Locale(request.getParameter("lang")));
			request.getSession().setAttribute("CURRENT_LANG", new Locale(request.getParameter("lang")));
        }
        /*
        logger.debug("	Check Database ...");
        ServiceResult<Integer> result = sqlService.isDatabaseAlive();
        logger.debug("SQLService isSuccess:"+result.isSuccess());
        if(!result.isSuccess()){
        	logger.debug("	Check Database : Database is now unavailable");
			request.getSession().setAttribute(WebPortalConstant.STRING_RESULT_CODE, "DATABASE");
			request.getSession().setAttribute(WebPortalConstant.STRING_RESULT_DESC, "Database is now unavailable");
			return true;
        }
        logger.debug("	Check Database : Success");
        */
        logger.debug("handler class:"+handler.getClass());
        org.springframework.web.method.HandlerMethod handlerObj = (HandlerMethod) handler;
        logger.debug("handler method:"+handlerObj.getMethod().getName());
        logger.debug("SecurityDetectionInterceptor - spring security-cookies & session validating : ...! : "+request.getRequestURI());
        if(
        		request.getRequestURI().indexOf("login")!=-1 || 
        		request.getRequestURI().equals(request.getContextPath()+"/") ||
        		request.getRequestURI().indexOf("logout")!=-1 ||
        		request.getRequestURI().indexOf("pagenotfound")!=-1||
        		request.getRequestURI().indexOf("notsupport")!=-1||
        		request.getRequestURI().indexOf("service-rest")!=-1||
        		request.getRequestURI().indexOf("readFile")!=-1||
        		request.getRequestURI().indexOf("downloadFile")!=-1||
        		request.getRequestURI().indexOf("download")!=-1||
        		request.getRequestURI().indexOf("getImg")!=-1){
        	logger.debug("SecurityDetectionInterceptor - login , no action , skipped");
        }else if(handlerObj.getMethod().getName().indexOf("exception")!=-1 || handlerObj.getMethod().getName().indexOf("invalidParam")!=-1){
        	logger.debug("SecurityDetectionInterceptor - Exception , skipped");
        }else{	
        	if(!SessionHelper.isAuthenticated())
 			{
        		logger.debug("SecurityDetectionInterceptor - spring security-cookies validate failed , You're not login !");
        		
 				request.getSession().setAttribute(WebPortalConstant.STRING_RESULT_CODE, messageSource.getMessage("AUTHOR90901",null,request.getLocale()));
 				request.getSession().setAttribute(WebPortalConstant.STRING_RESULT_DESC, messageSource.getMessage("AUTHOR90902",null,request.getLocale()));
 				RequestDispatcher rd = request.getRequestDispatcher("" + "/login.htm");
                rd.forward(request, response);                  
                return false;
 			}else{
 				logger.debug("SecurityDetectionInterceptor - spring security-cookies validate pass!");
 			}
        	 
        }
        
        String yourPermissionsCode = "";
		
		boolean allowedFlag = true;
		String targetPage = "login.htm";
        
        String actionMappingCode = getActionMappingCode(request.getRequestURI(),request.getContextPath());
		if(actionMappingCode == null){
        	actionMappingCode = getActionMappingCode(request.getRequestURI(),request.getContextPath());
        }
		
        logger.debug("actionMappingCode : "+actionMappingCode);
		String requiredPermissionCode = getRequiredPermissionCode(actionMappingCode);
		logger.debug("requiredPermissionCode : "+requiredPermissionCode);
		if(requiredPermissionCode==null || requiredPermissionCode.equals("null") || requiredPermissionCode.equals("")) {
			return true;
		}		
		
		try{
			if(loginInfo!=null){
				yourPermissionsCode  = SessionHelper.getCurrentPermissionInfo(request);
				if(yourPermissionsCode.indexOf(requiredPermissionCode)!=-1){
					logger.debug("	You're authorized for this action : "+actionMappingCode+" with by permission code : "+yourPermissionsCode);
					return true;
				}
				logger.debug("You're not authorized to do this action ("+actionMappingCode+")");
				allowedFlag = false;
				targetPage = "notauthorized.htm";
			}
			else{
				logger.debug("	You have no any authorities");
				request.getSession().setAttribute(WebPortalConstant.STRING_RESULT_CODE, "Login ::");
				request.getSession().setAttribute(WebPortalConstant.STRING_RESULT_DESC, "You are not logged-in");
				allowedFlag = false;
			}
		}catch(Exception ex){
			logger.error("Error on SecurityDetectionInterceptor",ex);
			request.getSession().setAttribute(WebPortalConstant.STRING_RESULT_CODE, "Login Exception ::");
			request.getSession().setAttribute(WebPortalConstant.STRING_RESULT_DESC, messageSource.getMessage("AUTHOR90902",null,request.getLocale()));
			allowedFlag = false;
		}
		
		if(!allowedFlag){
			RequestDispatcher rd = request.getRequestDispatcher("" + "/"+targetPage);
			rd.forward(request, response);                  
            return false;
		}else{
			return true;
		}
    }
    private boolean isChangeLang(HttpServletRequest request) {
		return request.getParameter("lang")!=null;
	}
    
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		synchronized (CodeBookHelper.class) {
			if(CodeBookHelper.CODEBOOK_LIST==null){
				CodeBookHelper.resetCodeBook();
			}
			
			arg0.setAttribute(WebPortalConstant.CODEBOOK_LIST, CodeBookHelper.getCodeBookList());
		}
	}

	public String getActionMappingCode(String URI,String contextPath){
		URI = URI.replaceAll(""+contextPath+"/", "");
		URI = URI.replaceAll("/", "_");
		URI = URI.replaceAll(".htm", "");
		return URI.toUpperCase();
	}
	
	public String getRequiredPermissionCode(String actionMappingCode){
		String permissionCode = "";
		permissionCode = CustomPropertyPlaceholderConfigurer.getProperty(actionMappingCode);
		return permissionCode;
		
	}
	
}