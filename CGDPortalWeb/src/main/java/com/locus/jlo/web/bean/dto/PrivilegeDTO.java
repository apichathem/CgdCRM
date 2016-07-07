package com.locus.jlo.web.bean.dto;


public class PrivilegeDTO extends BaseDTO{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1308320350251180192L;
	private Integer menuId;
	private String caption;
	private String actionName;
	private String actionCd;
	private String roleActionCd;
	private String roleActionValue;
	
	public Integer getMenuId() {
		return menuId;
	}
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public String getActionCd() {
		return actionCd;
	}
	public void setActionCd(String actionCd) {
		this.actionCd = actionCd;
	}
	public String getRoleActionCd() {
		return roleActionCd;
	}
	public void setRoleActionCd(String roleActionCd) {
		this.roleActionCd = roleActionCd;
	}
	public String getRoleActionValue() {
		return roleActionValue;
	}
	public void setRoleActionValue(String roleActionValue) {
		this.roleActionValue = roleActionValue;
	}
	
	
}
