package com.locus.jlo.rest.bean;

public class RiskHraBean {

	private String riskId;
	private String hraCd;
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

	public String getHraCd() {
		return hraCd;
	}

	public void setHraCd(String hraCd) {
		this.hraCd = hraCd;
	}

}
