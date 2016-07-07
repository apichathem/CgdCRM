package com.locus.jlo.web.bean.dto;

import java.io.Serializable;
import java.util.Date;

public class BaseDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4942324153724408969L;
	private String createBy;
	private Date createDate;
	private String updateBy;
	private Date updateDate;
	private String status;
	private String langCd;
	
	private String editUrl;
	private String addUrl;
	private String deleteUrl;
	
	protected java.util.List<String> ownerGroup;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getLangCd() {
		return langCd;
	}
	public void setLangCd(String langCd) {
		this.langCd = langCd;
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
	public java.util.List<String> getOwnerGroup() {
		return ownerGroup;
	}
	public void setOwnerGroup(java.util.List<String> ownerGroup) {
		this.ownerGroup = ownerGroup;
	}
	public String getOwnerGroupTxt() {
		StringBuffer sb = new StringBuffer();
		if (ownerGroup != null) {
			
			for (String userId : ownerGroup) {
				sb.append("'" + userId + "',");
			}
			return sb.substring(0, sb.length() - 1);
		} else {
			return "";
		}
	}
}
