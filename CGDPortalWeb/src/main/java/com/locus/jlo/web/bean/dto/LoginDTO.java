package com.locus.jlo.web.bean.dto;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import com.locus.jlo.web.bean.modelbean.MenuDetailModelBean;

public class LoginDTO extends org.springframework.security.core.userdetails.User {

	private static final long serialVersionUID = -7155518853403064085L;

	private Integer userId;
	private String loginname;
	private String password;
	private List<GrantedAuthority> authorityInfo;
	private AgentProfileDTO agentProfile;
	private UserInfoDTO userInfo;
	private List<MenuDetailModelBean> menuInfo;

	public LoginDTO(String userName, String password, List<GrantedAuthority> authorities) {
		super(userName, password, authorities);

		this.loginname = userName;
		this.password = password;
		this.authorityInfo = authorities;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<GrantedAuthority> getAuthorityInfo() {
		return authorityInfo;
	}

	public void setAuthorityInfo(List<GrantedAuthority> authorityInfo) {
		this.authorityInfo = authorityInfo;
	}
	
	public UserInfoDTO getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfoDTO userInfo) {
		this.userInfo = userInfo;
	}

	public List<MenuDetailModelBean> getMenuInfo() {
		return menuInfo;
	}

	public void setMenuInfo(List<MenuDetailModelBean> menuInfo) {
		this.menuInfo = menuInfo;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public AgentProfileDTO getAgentProfile() {
		return agentProfile;
	}

	public void setAgentProfile(AgentProfileDTO agentProfile) {
		this.agentProfile = agentProfile;
	}

}
