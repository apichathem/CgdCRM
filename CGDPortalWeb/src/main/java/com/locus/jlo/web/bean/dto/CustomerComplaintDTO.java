package com.locus.jlo.web.bean.dto;

import java.util.Date;

public class CustomerComplaintDTO extends BaseDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8743320157730923747L;
	private String cpNumber;
	private String cpTypeDesc;
	private String cpSubTypeDesc;
	private String cpPriority;
	private String cpSeverity;
	private String cpStatusDesc;
	private String cpOwnerName;
	private String cpOwnerDepartment;
	private Date cpDueDate;
	private Date cpCreateDate;
	
	public String getCpNumber() {
		return cpNumber;
	}
	public void setCpNumber(String cpNumber) {
		this.cpNumber = cpNumber;
	}
	public String getCpTypeDesc() {
		return cpTypeDesc;
	}
	public void setCpTypeDesc(String cpTypeDesc) {
		this.cpTypeDesc = cpTypeDesc;
	}
	public String getCpSubTypeDesc() {
		return cpSubTypeDesc;
	}
	public void setCpSubTypeDesc(String cpSubTypeDesc) {
		this.cpSubTypeDesc = cpSubTypeDesc;
	}
	public String getCpPriority() {
		return cpPriority;
	}
	public void setCpPriority(String cpPriority) {
		this.cpPriority = cpPriority;
	}
	public String getCpSeverity() {
		return cpSeverity;
	}
	public void setCpSeverity(String cpSeverity) {
		this.cpSeverity = cpSeverity;
	}
	public String getCpStatusDesc() {
		return cpStatusDesc;
	}
	public void setCpStatusDesc(String cpStatusDesc) {
		this.cpStatusDesc = cpStatusDesc;
	}
	public String getCpOwnerName() {
		return cpOwnerName;
	}
	public void setCpOwnerName(String cpOwnerName) {
		this.cpOwnerName = cpOwnerName;
	}
	public String getCpOwnerDepartment() {
		return cpOwnerDepartment;
	}
	public void setCpOwnerDepartment(String cpOwnerDepartment) {
		this.cpOwnerDepartment = cpOwnerDepartment;
	}
	public Date getCpDueDate() {
		return cpDueDate;
	}
	public void setCpDueDate(Date cpDueDate) {
		this.cpDueDate = cpDueDate;
	}
	public Date getCpCreateDate() {
		return cpCreateDate;
	}
	public void setCpCreateDate(Date cpCreateDate) {
		this.cpCreateDate = cpCreateDate;
	}
	
}
