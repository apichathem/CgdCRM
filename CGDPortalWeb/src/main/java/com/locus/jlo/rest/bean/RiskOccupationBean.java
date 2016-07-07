package com.locus.jlo.rest.bean;

public class RiskOccupationBean {

	private String riskId;
	private String occupationCd;
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

	public String getOccupationCd() {
		return occupationCd;
	}

	public void setOccupationCd(String occupationCd) {
		this.occupationCd = occupationCd;
	}

}
