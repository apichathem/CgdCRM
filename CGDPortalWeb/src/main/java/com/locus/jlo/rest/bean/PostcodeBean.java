package com.locus.jlo.rest.bean;

public class PostcodeBean {

	private String id;
	private String regionCode;
	private String provinceCode;
	private String amphurCode;
	private String tumbonCode;
	private String tumbonName;
	private String activeFlag;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getAmphurCode() {
		return amphurCode;
	}

	public void setAmphurCode(String amphurCode) {
		this.amphurCode = amphurCode;
	}

	public String getTumbonCode() {
		return tumbonCode;
	}

	public void setTumbonCode(String tumbonCode) {
		this.tumbonCode = tumbonCode;
	}

	public String getTumbonName() {
		return tumbonName;
	}

	public void setTumbonName(String tumbonName) {
		this.tumbonName = tumbonName;
	}

	public String getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

}
