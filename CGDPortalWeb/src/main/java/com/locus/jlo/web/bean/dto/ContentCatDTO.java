package com.locus.jlo.web.bean.dto;

import java.io.Serializable;
import java.util.Date;

public class ContentCatDTO extends BaseDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer contentCatId;
	private String catCenterType;
	private String catName;
	private String catSubtype;
	private String catType;
	private Date chgDt;
	private String chgBy;
	private String chgId;
	private String contentType;
	private String display;
	private Integer ord;
	private Integer parCatId;
	private Date regDt;
	private String regBy;
	private String regId;
	private String useStatus;
	
	public Integer getContentCatId() {
		return contentCatId;
	}
	public void setContentCatId(Integer contentCatId) {
		this.contentCatId = contentCatId;
	}
	public String getCatCenterType() {
		return catCenterType;
	}
	public void setCatCenterType(String catCenterType) {
		this.catCenterType = catCenterType;
	}
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	public String getCatSubtype() {
		return catSubtype;
	}
	public void setCatSubtype(String catSubtype) {
		this.catSubtype = catSubtype;
	}
	public String getCatType() {
		return catType;
	}
	public void setCatType(String catType) {
		this.catType = catType;
	}
	public Date getChgDt() {
		return chgDt;
	}
	public void setChgDt(Date chgDt) {
		this.chgDt = chgDt;
	}
	public String getChgId() {
		return chgId;
	}
	public void setChgId(String chgId) {
		this.chgId = chgId;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getDisplay() {
		return display;
	}
	public void setDisplay(String display) {
		this.display = display;
	}
	public Integer getOrd() {
		return ord;
	}
	public void setOrd(Integer ord) {
		this.ord = ord;
	}
	public Integer getParCatId() {
		return parCatId;
	}
	public void setParCatId(Integer parCatId) {
		this.parCatId = parCatId;
	}
	public Date getRegDt() {
		return regDt;
	}
	public void setRegDt(Date regDt) {
		this.regDt = regDt;
	}
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	public String getUseStatus() {
		return useStatus;
	}
	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}
	public String getChgBy() {
		return chgBy;
	}
	public void setChgBy(String chgBy) {
		this.chgBy = chgBy;
	}
	public String getRegBy() {
		return regBy;
	}
	public void setRegBy(String regBy) {
		this.regBy = regBy;
	}

}
