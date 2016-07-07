package com.locus.jlo.web.bean.dto;


public class MyTaskActivityDTO extends BaseDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1622967088277297784L;
	private String actNumber;
	private String title;
	private String actMenu;
	private String assignedToName;
	private String actTypeName;
	private String actStatusName;
	private String regName;
	private String regDatetime;
	private String dueDatetime;

	private Integer ownerId;

	public String getActNumber() {
		return actNumber;
	}

	public void setActNumber(String actNumber) {
		this.actNumber = actNumber;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getActMenu() {
		return actMenu;
	}

	public void setActMenu(String actMenu) {
		this.actMenu = actMenu;
	}

	public String getAssignedToName() {
		return assignedToName;
	}

	public void setAssignedToName(String assignedToName) {
		this.assignedToName = assignedToName;
	}

	public String getActTypeName() {
		return actTypeName;
	}

	public void setActTypeName(String actTypeName) {
		this.actTypeName = actTypeName;
	}

	public String getActStatusName() {
		return actStatusName;
	}

	public void setActStatusName(String actStatusName) {
		this.actStatusName = actStatusName;
	}

	public String getRegName() {
		return regName;
	}

	public void setRegName(String regName) {
		this.regName = regName;
	}

	public String getRegDatetime() {
		return regDatetime;
	}

	public void setRegDatetime(String regDatetime) {
		this.regDatetime = regDatetime;
	}

	public String getDueDatetime() {
		return dueDatetime;
	}

	public void setDueDatetime(String dueDatetime) {
		this.dueDatetime = dueDatetime;
	}

	public Integer getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}
}
