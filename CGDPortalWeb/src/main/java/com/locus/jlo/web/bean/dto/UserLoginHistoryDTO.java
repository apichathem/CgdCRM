package com.locus.jlo.web.bean.dto;

import java.util.Date;


public class UserLoginHistoryDTO extends BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2442246814366637134L;
	
	private Integer logHistId;
	private String loginId;
	private Date loginTime;
	private String sourceIp;
	private String status;
	private String browser;
	private String platform;
	
	public Integer getLogHistId() {
		return logHistId;
	}
	public void setLogHistId(Integer logHistId) {
		this.logHistId = logHistId;
	}
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	public String getSourceIp() {
		return sourceIp;
	}
	public void setSourceIp(String sourceIp) {
		this.sourceIp = sourceIp;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBrowser() {
		return browser;
	}
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	
}
