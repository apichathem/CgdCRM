package com.locus.jlo.web.bean.dto;


public class SysActionDTO extends BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3884247073109680332L;

	private String actionCd;
	private String actionName;
	private String description;
	private String enabled;
	
	public String getActionCd() {
		return actionCd;
	}
	public void setActionCd(String actionCd) {
		this.actionCd = actionCd;
	}
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	
}
