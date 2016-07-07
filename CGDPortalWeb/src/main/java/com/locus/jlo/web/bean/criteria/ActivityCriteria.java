package com.locus.jlo.web.bean.criteria;

import java.io.Serializable;

public class ActivityCriteria extends BaseCriteria implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String actNumber;
	private String actGroup;
	private String typeCd;
	private String statusCd;
	private String ownerId;
	private String regDtForm;
	private String regDtTo;
	private String dueDtForm;
	private String dueDtTo;
	
	public String getActNumber() {
		return actNumber;
	}
	public void setActNumber(String actNumber) {
		this.actNumber = actNumber;
	}
	public String getTypeCd() {
		return typeCd;
	}
	public void setTypeCd(String typeCd) {
		this.typeCd = typeCd;
	}
	public String getStatusCd() {
		return statusCd;
	}
	public void setStatusCd(String statusCd) {
		this.statusCd = statusCd;
	}
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	public String getRegDtForm() {
		return regDtForm;
	}
	public void setRegDtForm(String regDtForm) {
		this.regDtForm = regDtForm;
	}
	public String getRegDtTo() {
		return regDtTo;
	}
	public void setRegDtTo(String regDtTo) {
		this.regDtTo = regDtTo;
	}
	public String getDueDtForm() {
		return dueDtForm;
	}
	public void setDueDtForm(String dueDtForm) {
		this.dueDtForm = dueDtForm;
	}
	public String getDueDtTo() {
		return dueDtTo;
	}
	public void setDueDtTo(String dueDtTo) {
		this.dueDtTo = dueDtTo;
	}
	public String getActGroup() {
		return actGroup;
	}
	public void setActGroup(String actGroup) {
		this.actGroup = actGroup;
	}

}
