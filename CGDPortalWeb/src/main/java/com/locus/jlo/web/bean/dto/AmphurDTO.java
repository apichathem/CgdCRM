package com.locus.jlo.web.bean.dto;

import java.io.Serializable;
import java.util.Date;

public class AmphurDTO extends BaseDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4258204082531892402L;
	private Integer ampurId;
	private String ampurName;
	private Integer provinceId;
	private Integer regId;
	private Date regDt;
	private Integer chgId;
	private Date chgDt;
	/**
	 * @return the ampurId
	 */
	public Integer getAmpurId() {
		return ampurId;
	}
	/**
	 * @param ampurId the ampurId to set
	 */
	public void setAmpurId(Integer ampurId) {
		this.ampurId = ampurId;
	}
	/**
	 * @return the ampurName
	 */
	public String getAmpurName() {
		return ampurName;
	}
	/**
	 * @param ampurName the ampurName to set
	 */
	public void setAmpurName(String ampurName) {
		this.ampurName = ampurName;
	}
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
