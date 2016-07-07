package com.locus.jlo.web.bean.dto;

public class UserStaticDTO extends BaseDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6133370550329414649L;
	
	private Integer period;
	private Integer userAccess;
	
	public Integer getPeriod() {
		return period;
	}
	public void setPeriod(Integer period) {
		this.period = period;
	}
	public Integer getUserAccess() {
		return userAccess;
	}
	public void setUserAccess(Integer userAccess) {
		this.userAccess = userAccess;
	}
	
	
}
