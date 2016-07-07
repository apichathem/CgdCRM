package com.locus.jlo.web.bean.modelbean;

import com.locus.jlo.web.constant.JLOWebConstant;

public class ContentCatModelBean extends BaseModelBean {

	private static final long serialVersionUID = 1L;

	private String contentType;
	private String parentId;
	private String parentName;
	private String contentCatId;
	private String catName;
	private String ord;
	private String catType;
	private String catTypeName;
	private String catSubType;
	private String catSubTypeName;
	private String display;
	private String useStatus;
	private String catCenterType;

	public boolean isDeleteMode() {
		return (JLOWebConstant.MODE_DELETE.equals(this.getMode())) ? Boolean.TRUE : Boolean.FALSE;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getContentCatId() {
		return contentCatId;
	}

	public void setContentCatId(String contentCatId) {
		this.contentCatId = contentCatId;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public String getCatType() {
		return catType;
	}

	public void setCatType(String catType) {
		this.catType = catType;
	}

	public String getCatTypeName() {
		return catTypeName;
	}

	public void setCatTypeName(String catTypeName) {
		this.catTypeName = catTypeName;
	}

	public String getCatSubType() {
		return catSubType;
	}

	public void setCatSubType(String catSubType) {
		this.catSubType = catSubType;
	}

	public String getCatSubTypeName() {
		return catSubTypeName;
	}

	public void setCatSubTypeName(String catSubTypeName) {
		this.catSubTypeName = catSubTypeName;
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public String getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}

	public String getCatCenterType() {
		return catCenterType;
	}

	public void setCatCenterType(String catCenterType) {
		this.catCenterType = catCenterType;
	}

	public void setOrd(String ord) {
		this.ord = ord;
	}

	public String getOrd() {
		return ord;
	}

}
