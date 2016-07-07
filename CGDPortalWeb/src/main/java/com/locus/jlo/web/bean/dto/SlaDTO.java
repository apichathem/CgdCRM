package com.locus.jlo.web.bean.dto;

import java.util.Date;

public class SlaDTO extends BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7582059353331125989L;
	
	private Integer slaId;
	private String slaName;
	private Integer slaUnit;
	private String slaUomCd;
	private String slaUomName;
	private String statusCd;
	private String statusName;
	private String descp;
	private Integer regId;
	private Date regDt;
	private Integer chgId;
	private Date chgDt;
	
	public Integer getSlaId() {
		return slaId;
	}
	public void setSlaId(Integer slaId) {
		this.slaId = slaId;
	}
	public String getSlaName() {
		return slaName;
	}
	public void setSlaName(String slaName) {
		this.slaName = slaName;
	}
	public Integer getSlaUnit() {
		return slaUnit;
	}
	public void setSlaUnit(Integer slaUnit) {
		this.slaUnit = slaUnit;
	}
	public String getSlaUomCd() {
		return slaUomCd;
	}
	public void setSlaUomCd(String slaUomCd) {
		this.slaUomCd = slaUomCd;
	}
	public String getSlaUomName() {
		return slaUomName;
	}
	public void setSlaUomName(String slaUomName) {
		this.slaUomName = slaUomName;
	}
	public String getStatusCd() {
		return statusCd;
	}
	public void setStatusCd(String statusCd) {
		this.statusCd = statusCd;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public String getDescp() {
		return descp;
	}
	public void setDescp(String descp) {
		this.descp = descp;
	}
	public Integer getRegId() {
		return regId;
	}
	public void setRegId(Integer regId) {
		this.regId = regId;
	}
	public Date getRegDt() {
		return regDt;
	}
	public void setRegDt(Date regDt) {
		this.regDt = regDt;
	}
	public Integer getChgId() {
		return chgId;
	}
	public void setChgId(Integer chgId) {
		this.chgId = chgId;
	}
	public Date getChgDt() {
		return chgDt;
	}
	public void setChgDt(Date chgDt) {
		this.chgDt = chgDt;
	}
	
}
