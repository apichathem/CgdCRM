package com.locus.jlo.web.security;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Locale;

import javax.naming.Context;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.Assert;

import com.locus.common.constant.WebPortalConstant;
import com.locus.common.utils.Base64Util;
import com.locus.jlo.web.bean.dto.UserLoginHistoryDTO;
import com.locus.jlo.web.service.UserLoginHistoryService;
import com.locus.jlo.web.service.UserManagementService;
import com.locus.jlo.web.util.UserAgentUtil;

public class HttpBridgeAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	private MessageSource messageSource;

	private SaltSource saltSource;

	private UserDetailsService userDetailsService;

	@Autowired
	private UserManagementService userInformationService;
	
	@Autowired
	private UserLoginHistoryService userLoginHistoryService;

	@SuppressWarnings({ })
	protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		Object salt = null;
		logger.debug("[Step 5] : HttpBridgeAuthenticationProvider - additionalAuthenticationChecks");
		logger.debug("*****************************************************************************************");
		if (this.saltSource != null) {
			salt = this.saltSource.getSalt(userDetails);
		}
		if (authentication.getCredentials() == null) {
			logger.debug("Authentication failed: no credentials provided");
			throw new BadCredentialsException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
		}

		Object credetials = authentication.getCredentials();
		String username = null;
		String presentedPassword = null;
		username = ((PasswordHashCredentails) credetials).getUsername();
		presentedPassword = ((PasswordHashCredentails) credetials).getPassword();
		String sessionId = ((PasswordHashCredentails) credetials).getSessionId();
		HttpServletRequest request = ((PasswordHashCredentails) credetials).getRequest();

		Hashtable<String, String> env = new Hashtable<String, String>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, WebPortalConstant.INITCTX);
		env.put(Context.SECURITY_AUTHENTICATION, WebPortalConstant.SECURITY_AUTHEN);
		env.put(Context.SECURITY_PRINCIPAL, username + WebPortalConstant.DOMAIN_MAIL);
		env.put(Context.SECURITY_CREDENTIALS, presentedPassword);
		env.put(Context.PROVIDER_URL, WebPortalConstant.PROVIDER_URL);
		
		validateBase64(userDetails, username, presentedPassword, sessionId, salt, request);
		/*
		 * DirContext ctx;
		 * try {
		 * ctx = new InitialDirContext(env);
		 * String searchBase = "dc=locus,dc=co,dc=th";
		 * String searchFilter =
		 * "(&(objectCategory=person)(sAMAccountName=%USERNAME%))";
		 * SearchControls sCtrl = new SearchControls();
		 * sCtrl.setSearchScope(SearchControls.ONELEVEL_SCOPE);
		 * 
		 * @SuppressWarnings("rawtypes")
		 * NamingEnumeration answer = ctx.search(searchBase,
		 * searchFilter,sCtrl);
		 * 
		 * @SuppressWarnings("unused")
		 * boolean answerPass = false;
		 * if (answer.hasMoreElements()) {
		 * answerPass = true;
		 * }
		 * } catch (NamingException ex) {
		 * ex.printStackTrace();
		 * throw new
		 * AuthenticationServiceException(getAuthenticatedExceptionDetail
		 * (""+ex.getExplanation()));
		 * } catch (Exception e) {
		 * e.printStackTrace();
		 * throw new AuthenticationServiceException(
		 * "Can't login using Active Directory, Exception logic process");
		 * }
		 */
	}

	protected void validateBase64(UserDetails userDetails, String username, String rawPass, String sessionId, Object salt, HttpServletRequest request)
			throws AuthenticationException {

		logger.debug("[Step 6] : HttpBridgeAuthenticationProvider - validateBase64");
		logger.debug("*****************************************************************************************");
		int count = 0;
		if (request.getSession().getAttribute("ATTEMP_" + username) != null) {
			count = Integer.parseInt("" + request.getSession().getAttribute("ATTEMP_" + username));
		}
		String base64Pass = "";
		try {
			base64Pass = Base64Util.encode(rawPass);
			//base64Pass = rawPass;
			logger.debug("base64Pass = " + base64Pass);
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage(), e);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		if (!userDetails.getPassword().equals(base64Pass)) {
			if (count == 5) {
				// Insert login log
				UserLoginHistoryDTO userLoginHistoryDTO = new UserLoginHistoryDTO();
				userLoginHistoryDTO.setLoginId(userDetails.getUsername());
				userLoginHistoryDTO.setStatus("Your account has been disabled. Please contact your system administrator to unlock.");
				userLoginHistoryDTO.setLoginTime(Calendar.getInstance().getTime());
				userLoginHistoryDTO.setSourceIp(UserAgentUtil.getClientIp(request));
				userLoginHistoryDTO.setBrowser(UserAgentUtil.getBrowserVersion(request));
				userLoginHistoryDTO.setPlatform(UserAgentUtil.getOsVersion(request));
				userLoginHistoryService.insert(userLoginHistoryDTO);
				
				throw new AuthenticationServiceException(messageSource.getMessage("LOGIN_0904", new Object[] { userDetails.getUsername() }, Locale.getDefault()));
			}

			logger.debug("Authentication failed: password does not match stored value");
			request.getSession().setAttribute("ATTEMP_" + username, (count + 1));

			// Insert login log
			UserLoginHistoryDTO userLoginHistoryDTO = new UserLoginHistoryDTO();
			userLoginHistoryDTO.setLoginId(userDetails.getUsername());
			userLoginHistoryDTO.setStatus("The username or password is incorrect");
			userLoginHistoryDTO.setLoginTime(Calendar.getInstance().getTime());
			userLoginHistoryDTO.setSourceIp(UserAgentUtil.getClientIp(request));
			userLoginHistoryDTO.setBrowser(UserAgentUtil.getBrowserVersion(request));
			userLoginHistoryDTO.setPlatform(UserAgentUtil.getOsVersion(request));
			userLoginHistoryService.insert(userLoginHistoryDTO);
			
			throw new AuthenticationServiceException(messageSource.getMessage("LOGIN_0905", new Object[] { (3 - (count + 1)) }, Locale.getDefault()));
		} else {
			request.getSession().removeAttribute("ATTEMP_" + username);
			
			// Insert login log
			UserLoginHistoryDTO userLoginHistoryDTO = new UserLoginHistoryDTO();
			userLoginHistoryDTO.setLoginId(userDetails.getUsername());
			userLoginHistoryDTO.setStatus("Success");
			userLoginHistoryDTO.setLoginTime(Calendar.getInstance().getTime());
			userLoginHistoryDTO.setSourceIp(UserAgentUtil.getClientIp(request));
			userLoginHistoryDTO.setBrowser(UserAgentUtil.getBrowserVersion(request));
			userLoginHistoryDTO.setPlatform(UserAgentUtil.getOsVersion(request));
			userLoginHistoryService.insert(userLoginHistoryDTO);
		}
	}

	protected void doAfterPropertiesSet() throws Exception {
		Assert.notNull(this.userDetailsService, "A UserDetailsService must be set");
	}

	protected final UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		UserDetails loadedUser;

		logger.debug("[Step 3] : HttpBridgeAuthenticationProvider - retrieveUser : ..");
		logger.debug("*******************************************************************");

		try {
			String prefix = "USER:";
			Object object = authentication.getDetails();
			if (object instanceof User) {
				User details = (User) object;
				Collection<GrantedAuthority> auths = details.getAuthorities();
				if (auths != null && auths.size() > 0) {
					if ("AGENT".equals(auths.iterator().next().getAuthority())) {
						prefix = "AGENT:";
					} else {
						prefix = "USER:";
					}
				} else {
					prefix = "USER:";
				}
			}
			loadedUser = this.getUserDetailsService().loadUserByUsername(prefix + username);
			
			
		} catch (UsernameNotFoundException notFound) {
			throw notFound;
		} catch (Exception repositoryProblem) {
			throw new AuthenticationServiceException(repositoryProblem.getMessage(), repositoryProblem);
		}

		if (loadedUser == null) {
			throw new AuthenticationServiceException("UserDetailsService returned null, which is an interface contract violation");
		}
		return loadedUser;
	}

	/**
	 * Sets the PasswordEncoder instance to be used to encode and validate
	 * passwords.
	 * If not set, the password will be compared as plain text.
	 * <p>
	 * For systems which are already using salted password which are encoded
	 * with a previous release, the encoder should be of type
	 * {@code org.springframework.security.authentication.encoding.PasswordEncoder}
	 * . Otherwise, the recommended approach is to use
	 * {@code org.springframework.security.crypto.password.PasswordEncoder}.
	 * 
	 * @param passwordEncoder
	 *            must be an instance of one of the {@code PasswordEncoder}
	 *            types.
	 */

	/**
	 * The source of salts to use when decoding passwords. <code>null</code> is
	 * a valid value, meaning the <code>DaoAuthenticationProvider</code> will
	 * present <code>null</code> to the relevant <code>PasswordEncoder</code>.
	 * <p>
	 * Instead, it is recommended that you use an encoder which uses a random
	 * salt and combines it with the password field. This is the default
	 * approach taken in the
	 * {@code org.springframework.security.crypto.password} package.
	 * 
	 * @param saltSource
	 *            to use when attempting to decode passwords via the
	 *            <code>PasswordEncoder</code>
	 */
	public void setSaltSource(SaltSource saltSource) {
		this.saltSource = saltSource;
	}

	protected SaltSource getSaltSource() {
		return saltSource;
	}

	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	protected UserDetailsService getUserDetailsService() {
		return userDetailsService;
	}

	public String getAuthenticatedExceptionDetail(String errorCode) {

		if (errorCode.indexOf("data 525") != -1) {
			return "The specified account does not exist.";
		} else if (errorCode.indexOf("data 52e") != -1) {
			return "unknown user name or bad password.";
		} else if (errorCode.indexOf("data 530") != -1) {
			return "account logon time restriction violation.";
		} else if (errorCode.indexOf("data 531") != -1) {
			return "user not allowed to log on to this system.";
		} else if (errorCode.indexOf("data 532") != -1) {
			return "password expired.";
		} else if (errorCode.indexOf("data 533") != -1) {
			return "account currently disabled.";
		} else if (errorCode.indexOf("data 701") != -1) {
			return "account expired.";
		} else if (errorCode.indexOf("data 773") != -1) {
			return "user must reset password.";
		} else if (errorCode.indexOf("data 775") != -1) {
			return "account locked out.";
		} else if (errorCode.indexOf("data 775") != -1) {
			return "account locked out.";
		} else {
			return "unexpected error from active directory : " + errorCode;
		}
	}
}
