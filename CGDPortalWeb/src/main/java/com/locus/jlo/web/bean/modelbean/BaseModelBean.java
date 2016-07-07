package com.locus.jlo.web.bean.modelbean;

import java.io.Serializable;

import com.locus.jlo.web.constant.JLOWebConstant;

public class BaseModelBean implements Serializable {
	private static final long serialVersionUID = 5234600954779874191L;

	private transient String mode = JLOWebConstant.MODE_INSERT;

	private transient Integer idx;

	private transient String clickSearch;

	private transient String createBy;

	private transient String createDate;

	private transient String updateBy;

	private transient String updateDate;

	private transient String editUrl;

	private transient String addUrl;

	private transient String deleteUrl;

	private transient String module;

	public boolean isInsertMode() {
		return (JLOWebConstant.MODE_INSERT.equals(mode)) ? Boolean.TRUE : Boolean.FALSE;
	}

	public boolean isUpdateMode() {
		return (JLOWebConstant.MODE_UPDATE.equals(mode)) ? Boolean.TRUE : Boolean.FALSE;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public Integer getIdx() {
		return idx;
	}

	public void setIdx(Integer idx) {
		this.idx = idx;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getClickSearch() {
		return clickSearch;
	}

	public void setClickSearch(String clickSearch) {
		this.clickSearch = clickSearch;
	}

	public String getEditUrl() {
		return editUrl;
	}

	public void setEditUrl(String editUrl) {
		this.editUrl = editUrl;
	}

	public String getAddUrl() {
		return addUrl;
	}

	public void setAddUrl(String addUrl) {
		this.addUrl = addUrl;
	}

	public String getDeleteUrl() {
		return deleteUrl;
	}

	public void setDeleteUrl(String deleteUrl) {
		this.deleteUrl = deleteUrl;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

}
