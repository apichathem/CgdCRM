package com.locus.jlo.web.bean.dto;

import java.io.Serializable;

public class CityDTO implements Serializable {
	private static final long serialVersionUID = 8922848773907367289L;
	private Integer tumbonCode;
	private Integer amphurCode;
	private Integer provinceCode;
	private String name;
	private String activeFlag;
	private String modifyDate;

	public Integer getTumbonCode() {
		return tumbonCode;
	}

	public void setTumbonCode(Integer tumbonCode) {
		this.tumbonCode = tumbonCode;
	}

	public Integer getAmphurCode() {
		return amphurCode;
	}

	public void setAmphurCode(Integer amphurCode) {
		this.amphurCode = amphurCode;
	}

	public Integer getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(Integer provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

}
