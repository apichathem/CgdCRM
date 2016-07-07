package com.locus.jlo.web.bean.modelbean;

public class SLAModelBean extends BaseModelBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8392083106856662369L;
	private Integer slaId;
	private String slaName;
	private Integer slaUnit;
	private String slaUomCd;
	private String statusCd;
	private String descp;
	
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
	public String getStatusCd() {
		return statusCd;
	}
	public void setStatusCd(String statusCd) {
		this.statusCd = statusCd;
	}
	public String getDescp() {
		return descp;
	}
	public void setDescp(String descp) {
		this.descp = descp;
	}
	
}
