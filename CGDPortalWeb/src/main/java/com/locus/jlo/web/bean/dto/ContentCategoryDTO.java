package com.locus.jlo.web.bean.dto;

import java.util.Date;

import com.locus.common.utils.DateTimeUtils;

public class ContentCategoryDTO extends BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8076991051215523566L;
	
	private String contentCatId;
	private String contentTypeCd;
	private String parentCatId;
	private String parentCatName;
	private String catName;
	private String statusCd;
	private String descp;
	private Boolean hasChild;
	private Boolean hasData;
	private Boolean usedBySr;
	private Integer slaId;
	private Integer regId;
	private Date regDt;
	private Integer chgId;
	private Date chgDt;
	private Integer slaUnit;
	private Integer level;
	
	private Boolean isUsed = Boolean.FALSE;
	
	// ---------------------------------------
	@SuppressWarnings("unused")
	private String createDateTxt;
	@SuppressWarnings("unused")
	private String updateDateTxt;
	
	public String getCreateDateTxt() {
		return DateTimeUtils.formatDateTime(regDt);
	}
	public void setCreateDateTxt(String createDateTxt) {
		this.createDateTxt = createDateTxt;
	}
	public String getUpdateDateTxt() {
		return DateTimeUtils.formatDateTime(chgDt);
	}
	public void setUpdateDateTxt(String updateDateTxt) {
		this.updateDateTxt = updateDateTxt;
	}
	public String getContentCatId() {
		return contentCatId;
	}
	public void setContentCatId(String contentCatId) {
		this.contentCatId = contentCatId;
	}
	public String getContentTypeCd() {
		return contentTypeCd;
	}
	public void setContentTypeCd(String contentTypeCd) {
		this.contentTypeCd = contentTypeCd;
	}
	public String getParentCatId() {
		return parentCatId;
	}
	public void setParentCatId(String parentCatId) {
		this.parentCatId = parentCatId;
	}
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
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
	public Integer getSlaId() {
		return slaId;
	}
	public void setSlaId(Integer slaId) {
		this.slaId = slaId;
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
	public Boolean getHasChild() {
		return hasChild;
	}
	public void setHasChild(Boolean hasChild) {
		this.hasChild = hasChild;
	}
	public Integer getSlaUnit() {
		return slaUnit;
	}
	public void setSlaUnit(Integer slaUnit) {
		this.slaUnit = slaUnit;
	}
	public String getParentCatName() {
		return parentCatName;
	}
	public void setParentCatName(String parentCatName) {
		this.parentCatName = parentCatName;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Boolean getIsUsed() {
		return isUsed;
	}
	public void setIsUsed(Boolean isUsed) {
		this.isUsed = isUsed;
	}
	public Boolean getHasData() {
		return hasData;
	}
	public void setHasData(Boolean hasData) {
		this.hasData = hasData;
	}
	public Boolean getUsedBySr() {
		return usedBySr;
	}
	public void setUsedBySr(Boolean usedBySr) {
		this.usedBySr = usedBySr;
	}
	
}
