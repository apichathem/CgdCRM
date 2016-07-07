package com.locus.jlo.web.bean.criteria;

import java.io.Serializable;

public class AddressCriteria implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4598846759722129452L;
	
	private String addr;
	private String subArea;
	private String area;
	private String province;
	private String countryCd;
	private String postalCode;
	
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getSubArea() {
		return subArea;
	}
	public void setSubArea(String subArea) {
		this.subArea = subArea;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCountryCd() {
		return countryCd;
	}
	public void setCountryCd(String countryCd) {
		this.countryCd = countryCd;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
}
