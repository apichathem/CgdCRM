package com.locus.jlo.rest.bean;

public class RiskMaritalStatusBean {

	private String riskId;
	private String maritalStatusCd;
	private String deleted;

	public String getRiskId() {
		return riskId;
	}

	public void setRiskId(String riskId) {
		this.riskId = riskId;
	}

	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

	public String getMaritalStatusCd() {
		return maritalStatusCd;
	}

	public void setMaritalStatusCd(String maritalStatusCd) {
		this.maritalStatusCd = maritalStatusCd;
	}

}
