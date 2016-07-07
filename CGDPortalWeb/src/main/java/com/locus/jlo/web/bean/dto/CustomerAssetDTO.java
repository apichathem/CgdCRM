package com.locus.jlo.web.bean.dto;

public class CustomerAssetDTO extends BaseDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8871916211993901825L;
	private Integer assetId;
	private String assetNo;
	private String assetType;
	private String assetSubType;
	private String assetContactPerson;
	private String assetContactNo;
	private String assetStatus;

	public String getAssetNo() {
		return assetNo;
	}

	public void setAssetNo(String assetNo) {
		this.assetNo = assetNo;
	}

	public String getAssetType() {
		return assetType;
	}

	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}

	public String getAssetSubType() {
		return assetSubType;
	}

	public void setAssetSubType(String assetSubType) {
		this.assetSubType = assetSubType;
	}

	public String getAssetContactPerson() {
		return assetContactPerson;
	}

	public void setAssetContactPerson(String assetContactPerson) {
		this.assetContactPerson = assetContactPerson;
	}

	public String getAssetContactNo() {
		return assetContactNo;
	}

	public void setAssetContactNo(String assetContactNo) {
		this.assetContactNo = assetContactNo;
	}

	public String getAssetStatus() {
		return assetStatus;
	}

	public void setAssetStatus(String assetStatus) {
		this.assetStatus = assetStatus;
	}

	public Integer getAssetId() {
		return assetId;
	}

	public void setAssetId(Integer assetId) {
		this.assetId = assetId;
	}
}
