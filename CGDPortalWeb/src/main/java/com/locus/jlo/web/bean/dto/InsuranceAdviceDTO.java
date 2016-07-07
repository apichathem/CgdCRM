package com.locus.jlo.web.bean.dto;

import java.util.Date;

public class InsuranceAdviceDTO extends BaseDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3237167907438725857L;
	private Long id;
	private String sizeType;
	private Integer ageFrom;
	private Integer ageTo;
	private String insMain;
	private String insCpa;
	private Double insAmt;
	private String insDab;
	private String insBenefit;
	private String insHc;
	private String insPlan;
	private Double insCb;
	private String insCi;
	private String insPerson;
	private String insPersonPlan;
	private String regId;
	private Date regDt;
	private String chgId;
	private Date chgDt;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSizeType() {
		return sizeType;
	}
	public void setSizeType(String sizeType) {
		this.sizeType = sizeType;
	}
	public Integer getAgeFrom() {
		return ageFrom;
	}
	public void setAgeFrom(Integer ageFrom) {
		this.ageFrom = ageFrom;
	}
	public Integer getAgeTo() {
		return ageTo;
	}
	public void setAgeTo(Integer ageTo) {
		this.ageTo = ageTo;
	}
	public String getInsMain() {
		return insMain;
	}
	public void setInsMain(String insMain) {
		this.insMain = insMain;
	}
	public String getInsCpa() {
		return insCpa;
	}
	public void setInsCpa(String insCpa) {
		this.insCpa = insCpa;
	}
	public Double getInsAmt() {
		return insAmt;
	}
	public void setInsAmt(Double insAmt) {
		this.insAmt = insAmt;
	}
	public String getInsDab() {
		return insDab;
	}
	public void setInsDab(String insDab) {
		this.insDab = insDab;
	}
	public String getInsBenefit() {
		return insBenefit;
	}
	public void setInsBenefit(String insBenefit) {
		this.insBenefit = insBenefit;
	}
	public String getInsHc() {
		return insHc;
	}
	public void setInsHc(String insHc) {
		this.insHc = insHc;
	}
	public String getInsPlan() {
		return insPlan;
	}
	public void setInsPlan(String insPlan) {
		this.insPlan = insPlan;
	}
	public Double getInsCb() {
		return insCb;
	}
	public void setInsCb(Double insCb) {
		this.insCb = insCb;
	}
	public String getInsCi() {
		return insCi;
	}
	public void setInsCi(String insCi) {
		this.insCi = insCi;
	}
	public String getInsPerson() {
		return insPerson;
	}
	public void setInsPerson(String insPerson) {
		this.insPerson = insPerson;
	}
	public String getInsPersonPlan() {
		return insPersonPlan;
	}
	public void setInsPersonPlan(String insPersonPlan) {
		this.insPersonPlan = insPersonPlan;
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
	
}
