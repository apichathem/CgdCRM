package com.locus.jlo.web.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.locus.common.domain.ServiceResult;
import com.locus.jlo.web.bean.dto.AgentProfileDTO;
import com.locus.jlo.web.bean.dto.LoginDTO;
import com.locus.jlo.web.service.AgentService;
import com.locus.jlo.web.service.UserManagementService;

@Service("userDetailService")
public class DGSUserDetailService implements UserDetailsService {

	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private UserManagementService userInformationService;

	@Autowired
	private AgentService agentService;
	
	@Override
	public UserDetails loadUserByUsername(String loginId) throws AuthenticationException {
		String type = loginId.split(":")[0];
		String username = loginId.split(":")[1];
		if ("AGENT".equals(type)) {
			return loadAgent(username);
		} else {
			return loadUser(username);
		}
	}

	private UserDetails loadAgent(String loginId) {
		List<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
		auth.add((GrantedAuthority) new SimpleGrantedAuthority("LOGIN_READ"));
		LoginDTO userInfo = new LoginDTO(loginId, "", auth);
		try {
			ServiceResult<AgentProfileDTO> result = agentService.searchAgentByAgentCode(loginId);
			userInfo.setAgentProfile(result.getResult());
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new AuthenticationServiceException(messageSource.getMessage("LOGIN_0901", new Object[] { loginId }, Locale.getDefault()));
		}
		return userInfo;
	}

	private UserDetails loadUser(String loginId) {
		List<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
		auth.add((GrantedAuthority) new SimpleGrantedAuthority("LOGIN_READ"));
		LoginDTO userInfo = new LoginDTO(loginId, "", auth);
		
		ServiceResult<LoginDTO> userServiceResult = userInformationService.getLoginInfo(loginId, "TH");
		if (userServiceResult.isSuccess()) {
			logger.debug("	[Step 4.1] : Success");
			userInfo = userServiceResult.getResult();
		} else {
			
			throw new AuthenticationServiceException(messageSource.getMessage("LOGIN_0901", new Object[] { loginId }, Locale.getDefault()));
		}

		return userInfo;
	}

}
