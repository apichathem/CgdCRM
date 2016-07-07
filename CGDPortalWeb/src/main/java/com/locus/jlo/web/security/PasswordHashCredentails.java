package com.locus.jlo.web.security;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

public class PasswordHashCredentails implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2567510943005242315L;
	private String password;
	private String passwordHash;
	private String loginMethod;
	private String sessionId;
	private String username;
	private HttpServletRequest request;
	public PasswordHashCredentails(){
		
	}
	
	public PasswordHashCredentails(String username,String password, String passwordHash,String loginMethod,String sessionId,HttpServletRequest request){
		this.username = username;
		this.password = password;
		this.passwordHash = passwordHash;
		this.loginMethod = loginMethod;
		this.sessionId = sessionId;
		this.request = request;
	}
	
	
	/**
	 * @return the loginMethod
	 */
	public String getLoginMethod() {
		return loginMethod;
	}

	/**
	 * @param loginMethod the loginMethod to set
	 */
	public void setLoginMethod(String loginMethod) {
		this.loginMethod = loginMethod;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	@Override
	public String toString() {
		return password;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	
}
