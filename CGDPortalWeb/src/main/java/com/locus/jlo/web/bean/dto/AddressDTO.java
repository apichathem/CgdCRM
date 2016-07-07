package com.locus.jlo.web.bean.dto;

public class AddressDTO extends BaseDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4482563477971436816L;
	private Integer custId;
	private Integer addrId;
	private Integer custAddrId;
	
	private String addr1;
	private String addr2;
	private String subArea;
	private String area;
	private String province;
	private String countryCd;
	private String countryName;
	private String postalCode;
	private String primaryYn;
	private String addrTypeCd;
	private String addrTypeName;
	
	private Integer regId;
	private Integer chgId;
	
	public Integer getCustId() {
		return custId;
	}
	public void setCustId(Integer custId) {
		this.custId = custId;
	}
	public Integer getAddrId() {
		return addrId;
	}
	public void setAddrId(Integer addrId) {
		this.addrId = addrId;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
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
	public String getPrimaryYn() {
		return primaryYn;
	}
	public void setPrimaryYn(String primaryYn) {
		this.primaryYn = primaryYn;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getAddrTypeCd() {
		return addrTypeCd;
	}
	public void setAddrTypeCd(String addrTypeCd) {
		this.addrTypeCd = addrTypeCd;
	}
	public Integer getRegId() {
		return regId;
	}
	public void setRegId(Integer regId) {
		this.regId = regId;
	}
	public Integer getChgId() {
		return chgId;
	}
	public void setChgId(Integer chgId) {
		this.chgId = chgId;
	}
	public String getAddrTypeName() {
		return addrTypeName;
	}
	public void setAddrTypeName(String addrTypeName) {
		this.addrTypeName = addrTypeName;
	}
	public Integer getCustAddrId() {
		return custAddrId;
	}
	public void setCustAddrId(Integer custAddrId) {
		this.custAddrId = custAddrId;
	}
	
}
