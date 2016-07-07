package com.locus.jlo.web.bean.modelbean;

public class ChangePasswordModelBean {
	private Integer userId;
	private String username;
	private String deviceid;
	private String oldpassword;
	private String newpassword;
	private String retypenewpassword;
	private String bytePassword;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getDeviceid() {
		return deviceid;
	}
	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}
	public String getOldpassword() {
		return oldpassword;
	}
	public void setOldpassword(String oldpassword) {
		this.oldpassword = oldpassword;
	}
	public String getNewpassword() {
		return newpassword;
	}
	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}
	public String getRetypenewpassword() {
		return retypenewpassword;
	}
	public void setRetypenewpassword(String retypenewpassword) {
		this.retypenewpassword = retypenewpassword;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getBytePassword() {
		return bytePassword;
	}
	public void setBytePassword(String bytePassword) {
		this.bytePassword = bytePassword;
	}
}
