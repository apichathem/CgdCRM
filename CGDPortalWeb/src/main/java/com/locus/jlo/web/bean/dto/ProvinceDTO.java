package com.locus.jlo.web.bean.dto;

import java.io.Serializable;
import java.util.Date;

public class ProvinceDTO extends BaseDTO implements Serializable {
	
	private static final long serialVersionUID = 4197110578029606816L;
	
	private Integer provinceId;
	private String provinceName;
	private Integer regId;
	private Date regDt;
	private Integer chgId;
	private Date chgDt;
	/**
	 * @return the provinceId
	 */
	public Integer getProvinceId() {
		return provinceId;
	}
	/**
	 * @param provinceId the provinceId to set
	 */
	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}
	/**
	 * @return the provinceName
	 */
	public String getProvinceName() {
		return provinceName;
	}
	/**
	 * @param provinceName the provinceName to set
	 */
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	/**
	 * @return the regId
	 */
	public Integer getRegId() {
		return regId;
	}
	/**
	 * @param regId the regId to set
	 */
	public void setRegId(Integer regId) {
		this.regId = regId;
	}
	/**
	 * @return the regDt
	 */
	public Date getRegDt() {
		return regDt;
	}
	/**
	 * @param regDt the regDt to set
	 */
	public void setRegDt(Date regDt) {
		this.regDt = regDt;
	}
	/**
	 * @return the chgId
	 */
	public Integer getChgId() {
		return chgId;
	}
	/**
	 * @param chgId the chgId to set
	 */
	public void setChgId(Integer chgId) {
		this.chgId = chgId;
	}
	/**
	 * @return the chgDt
	 */
	public Date getChgDt() {
		return chgDt;
	}
	/**
	 * @param chgDt the chgDt to set
	 */
	public void setChgDt(Date chgDt) {
		this.chgDt = chgDt;
	}
	
	
	
}
