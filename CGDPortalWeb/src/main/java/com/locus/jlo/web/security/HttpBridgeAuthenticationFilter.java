package com.locus.jlo.web.security;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.util.Assert;

public class HttpBridgeAuthenticationFilter extends
		AbstractAuthenticationProcessingFilter {
	
	public static final String SPRING_SECURITY_FORM_USERNAME_KEY = "j_username";
    public static final String SPRING_SECURITY_FORM_PASSWORD_KEY = "j_password";
    public static final String SPRING_SECURITY_FORM_PASSWORD_HASH_KEY = "j_passwordhash";
    public static final String SPRING_SECURITY_FORM_LOGIN_METHOD_HASH_KEY = "j_loginMethod";
	
    private String usernameParameter = SPRING_SECURITY_FORM_USERNAME_KEY;
    private String passwordParameter = SPRING_SECURITY_FORM_PASSWORD_KEY;
    private String passwordHashParameter = SPRING_SECURITY_FORM_PASSWORD_HASH_KEY;
    private String loginMethodParameter = SPRING_SECURITY_FORM_LOGIN_METHOD_HASH_KEY;
    
    private boolean postOnly = true;
	private Logger logger = Logger.getLogger(getClass());
    
    public HttpBridgeAuthenticationFilter() {
        super("/j_spring_security_check");
    }
    
	protected HttpBridgeAuthenticationFilter(String defaultFilterProcessesUrl) {
		super(defaultFilterProcessesUrl);
	}
	
	@Override
	public Authentication attemptAuthentication(
			HttpServletRequest request,
			HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		
		logger.debug("[Step 1] : HttpBridgeAuthenticationFilter - attemptAuthentication : ..");
		logger.debug("**********************************");
		logger.debug("	request method : "+request.getMethod());
		
		if (postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        String username = URLEncoder.encode(obtainUsername(request));
        String password = obtainPassword(request);
        String passwordHash = obtainPasswordHash(request);
        String loginMethod = obtainLoginMethod(request);
        
        logger.debug("	username : "+username);
        logger.debug("	password : "+password);
        logger.debug("	passwordHash : "+passwordHash);
        logger.debug("	loginMethod : "+loginMethod);
        
        if (username == null) {
            username = "";
        }

        if (password == null) {        	
        	password = "";        	
        }
        
		if (passwordHash == null){
			passwordHash = "";
		}
		
		if (loginMethod == null){
			loginMethod = "";
		}
		
		//if(request.getParameter("activeDirectoryFlag")!=null && request.getParameter("activeDirectoryFlag").equals("on")){
			loginMethod = "ActiveDirectoryLogin";
		//}
		
		String sessionId = request.getSession().getId();
		logger.debug("	sessionId : "+sessionId);
		
        username = username.trim();
        PasswordHashCredentails credentails = new PasswordHashCredentails(username,password, passwordHash,loginMethod,sessionId,request);
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, credentails);
        setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
	}
	
	/**
     * Enables subclasses to override the composition of the password, such as by including additional values
     * and a separator.<p>This might be used for example if a postcode/zipcode was required in addition to the
     * password. A delimiter such as a pipe (|) should be used to separate the password and extended value(s). The
     * <code>AuthenticationDao</code> will need to generate the expected password in a corresponding manner.</p>
     *
     * @param request so that request attributes can be retrieved
     *
     * @return the password that will be presented in the <code>Authentication</code> request token to the
     *         <code>AuthenticationManager</code>
     */
    protected String obtainPassword(HttpServletRequest request) {
        return request.getParameter(passwordParameter);
    }
    
    protected String obtainPasswordHash(HttpServletRequest request) {
        return request.getParameter(passwordHashParameter);
    }

    /**
     * Enables subclasses to override the composition of the username, such as by including additional values
     * and a separator.
     *
     * @param request so that request attributes can be retrieved
     *
     * @return the username that will be presented in the <code>Authentication</code> request token to the
     *         <code>AuthenticationManager</code>
     */
    protected String obtainUsername(HttpServletRequest request) {
        return request.getParameter(usernameParameter);
    }
    
    protected String obtainLoginMethod(HttpServletRequest request) {
        return request.getParameter(loginMethodParameter);
    }
    
    /**
     * Provided so that subclasses may configure what is put into the authentication request's details
     * property.
     *
     * @param request that an authentication request is being created for
     * @param authRequest the authentication request object that should have its details set
     */
    protected void setDetails(HttpServletRequest request, UsernamePasswordAuthenticationToken authRequest) {
    	logger.debug("[Step 2] : HttpBridgeAuthenticationFilter - setDetails : ..");
    	logger.debug("*******************************************************************");
    	
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }

    /**
     * Sets the parameter name which will be used to obtain the username from the login request.
     *
     * @param usernameParameter the parameter name. Defaults to "j_username".
     */
    public void setUsernameParameter(String usernameParameter) {
        Assert.hasText(usernameParameter, "Username parameter must not be empty or null");
        this.usernameParameter = usernameParameter;
    }

    /**
     * Sets the parameter name which will be used to obtain the password from the login request..
     *
     * @param passwordParameter the parameter name. Defaults to "j_password".
     */
    public void setPasswordParameter(String passwordParameter) {
        Assert.hasText(passwordParameter, "Password parameter must not be empty or null");
        this.passwordParameter = passwordParameter;
    }

    /**
     * Defines whether only HTTP POST requests will be allowed by this filter.
     * If set to true, and an authentication request is received which is not a POST request, an exception will
     * be raised immediately and authentication will not be attempted. The <tt>unsuccessfulAuthentication()</tt> method
     * will be called as if handling a failed authentication.
     * <p>
     * Defaults to <tt>true</tt> but may be overridden by subclasses.
     */
    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

    public final String getUsernameParameter() {
        return usernameParameter;
    }

    public final String getPasswordParameter() {
        return passwordParameter;
    }

}
