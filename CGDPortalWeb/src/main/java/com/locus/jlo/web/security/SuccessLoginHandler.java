package com.locus.jlo.web.security;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.locus.common.constant.WebPortalConstant;
import com.locus.common.domain.ServiceResult;
import com.locus.jlo.web.bean.dto.LoginDTO;
import com.locus.jlo.web.bean.dto.UserInfoDTO;
import com.locus.jlo.web.bean.modelbean.MenuDetailModelBean;
import com.locus.jlo.web.service.MenuService;
import com.locus.jlo.web.service.UserManagementService;
import com.locus.jlo.web.util.SessionHelper;


public class SuccessLoginHandler implements AuthenticationSuccessHandler{
	private Logger logger = Logger.getLogger(getClass());

//	@Autowired
//	private CodebookService codebookService;
//	
	@Autowired
	private UserManagementService userManagementService;
	
	@Autowired
	private MenuService menuService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authen) throws IOException,
			ServletException {

		logger.info("[Step 7] : SuccessLoginHandler - getCredentials : ..");
    	logger.info("*******************************************************************");
		logger.info("	Credentials:" + authen.getCredentials());
		logger.info("	Details:" + authen.getDetails());
		logger.info("	Principal:" + authen.getPrincipal());
		logger.info("	Name:" + authen.getName());
		for (GrantedAuthority ga: authen.getAuthorities()){
			logger.info("	Authority:" + ga.getAuthority());
		}
		
		logger.info("[Step 8] : SuccessLoginHandler - getPrincipal : ..");
    	logger.info("*******************************************************************");
    	LoginDTO loginInfo = (LoginDTO) authen.getPrincipal();
		UserInfoDTO userInfo = loginInfo.getUserInfo();
		request.getSession().setAttribute(WebPortalConstant.USER_PROFILE, userInfo);
		
    	ServiceResult<List<MenuDetailModelBean>> serviceResult = menuService.findListMenuEnabledByRoleId(Integer.parseInt(userInfo.getRoleId()));
		if(serviceResult.isSuccess()){
			List<MenuDetailModelBean> list = serviceResult.getResult();
//			for (MenuDetailModelBean MenuDetailModelBean : list) {
//			}
			request.getSession().setAttribute(WebPortalConstant.MENU_PROFILE, list);
		}else{
			response.sendRedirect("login.htm?lang=th");
		}
		
		logger.info("[Step 9] : SuccessLoginHandler - getPrivilege");
    	logger.info("*******************************************************************");
    	long startDatePrivilege = Calendar.getInstance().getTimeInMillis();
    	SessionHelper.initPrivilege(request, Integer.parseInt(userInfo.getRoleId()));
    	logger.info("	***** Use Time to getPrivilege **** in " + (Calendar.getInstance().getTimeInMillis() - startDatePrivilege) + " ms.");
		
    	logger.info("[Step 10] : SuccessLoginHandler - getTeamMember");
    	logger.info("*******************************************************************");
    	ServiceResult<List<String>> res = userManagementService.serachUserInTeam(userInfo.getUserId());
    	if (res.isSuccess()) {
    		List<String> teamMember = res.getResult();
    		userInfo.setMyTeamUserId(teamMember);
    	}
    	
		logger.info("[Step 11] : SuccessLoginHandler - setDefault Locale : ..");
    	logger.info("*******************************************************************");
//		SessionHelper.initCodeBook(request,Locale.getDefault());
    	request.getSession().setAttribute("CURRENT_LANG", new Locale("th"));

		logger.info("[Step 12] : SuccessLoginHandler - setRedirect : to home");
    	logger.info("*******************************************************************");
		logger.info("	redirect to page ...");
		response.sendRedirect("home.htm?lang=th");
	}

}
