package com.locus.jlo.web.bean.dto;

import java.io.Serializable;

import org.springframework.stereotype.Service;

@Service
public class KbContentCatDTO extends BaseDTO implements Serializable {
	
	private static final long serialVersionUID = 8607478298633170924L;
	
	private String contentCatId;
	private String contentTypeCd;
	private String catName;
	private String statusCd;
	private String parentCatId;
	private String descp;
	private String slaId;
	private String regId;
	private String regDt;
	private String chgId;
	private String chgDt;
	
	public String getContentCatId() {
		return contentCatId;
	}
	public void setContentCatId(String contentCatId) {
		this.contentCatId = contentCatId;
	}
	public String getContentTypeCd() {
		return contentTypeCd;
	}
	public void setContentTypeCd(String contentTypeCd) {
		this.contentTypeCd = contentTypeCd;
	}
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	public String getStatusCd() {
		return statusCd;
	}
	public void setStatusCd(String statusCd) {
		this.statusCd = statusCd;
	}
	public String getParentCatId() {
		return parentCatId;
	}
	public void setParentCatId(String parentCatId) {
		this.parentCatId = parentCatId;
	}
	public String getDescp() {
		return descp;
	}
	public void setDescp(String descp) {
		this.descp = descp;
	}
	public String getSlaId() {
		return slaId;
	}
	public void setSlaId(String slaId) {
		this.slaId = slaId;
	}
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	public String getRegDt() {
		return regDt;
	}
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	public String getChgId() {
		return chgId;
	}
	public void setChgId(String chgId) {
		this.chgId = chgId;
	}
	public String getChgDt() {
		return chgDt;
	}
	public void setChgDt(String chgDt) {
		this.chgDt = chgDt;
	}
	
	
	
}
