package com.locus.jlo.web.bean.dto;

import java.util.Date;

public class PremiumRateDTO extends BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6497527174403281602L;
	
	private Long id;
	private Double incomeFrom;
	private Double incomeTo;
	private Float sizeS;
	private Float sizeM;
	private Float sizeL;
	private String defaultSize;
	private String regId;
	private String regBy;
	private Date regDt;
	private String chgId;
	private String chgBy;
	private Date chgDt;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Double getIncomeFrom() {
		return incomeFrom;
	}
	public void setIncomeFrom(Double incomeFrom) {
		this.incomeFrom = incomeFrom;
	}
	public Double getIncomeTo() {
		return incomeTo;
	}
	public void setIncomeTo(Double incomeTo) {
		this.incomeTo = incomeTo;
	}
	public Float getSizeS() {
		return sizeS;
	}
	public void setSizeS(Float sizeS) {
		this.sizeS = sizeS;
	}
	public Float getSizeM() {
		return sizeM;
	}
	public void setSizeM(Float sizeM) {
		this.sizeM = sizeM;
	}
	public Float getSizeL() {
		return sizeL;
	}
	public void setSizeL(Float sizeL) {
		this.sizeL = sizeL;
	}
	public String getDefaultSize() {
		return defaultSize;
	}
	public void setDefaultSize(String defaultSize) {
		this.defaultSize = defaultSize;
	}
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	public Date getRegDt() {
		return regDt;
	}
	public void setRegDt(Date regDt) {
		this.regDt = regDt;
	}
	public String getChgId() {
		return chgId;
	}
	public void setChgId(String chgId) {
		this.chgId = chgId;
	}
	public Date getChgDt() {
		return chgDt;
	}
	public void setChgDt(Date chgDt) {
		this.chgDt = chgDt;
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
	@Override
	public String toString() {
		return "PremiumRateDTO [id=" + id + ", incomeFrom=" + incomeFrom + ", incomeTo=" + incomeTo + ", sizeS="
				+ sizeS + ", sizeM=" + sizeM + ", sizeL=" + sizeL + ", defaultSize=" + defaultSize + ", regId=" + regId
				+ ", regBy=" + regBy + ", regDt=" + regDt + ", chgId=" + chgId + ", chgBy=" + chgBy + ", chgDt=" + chgDt
				+ "]";
	}
}
