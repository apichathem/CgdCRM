package com.locus.jlo.web.bean.modelbean;

import com.locus.jlo.web.constant.JLOWebConstant;

public class ContentModelBean extends BaseModelBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String contentNumber;
	private String contentCatId;
	private String contentCatName;
	private String refId;
	private String type;
	private String title;
	private String summary;
	private String connectId;
	private String connectStatus;
	private String useStatus;
	private String agtLevel;
	private String display;
	private String ord;
	private String commonFlag;
	private String startDt;
	private String startDtTime;
	private String endDt;
	private String endDtTime;
	private String regId;
	private String regBy;
	private String regDt;
	private String chgId;
	private String chgBy;
	private String chgDt;

	private String catName;
	private String catType;
	private String catCenterType;
	private String catSubType;

	private String documentName;

	public boolean isDeleteMode() {
		return (JLOWebConstant.MODE_DELETE.equals(this.getMode())) ? Boolean.TRUE : Boolean.FALSE;
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

	public String getCatCenterType() {
		return catCenterType;
	}

	public void setCatCenterType(String catCenterType) {
		this.catCenterType = catCenterType;
	}

	public String getCatSubType() {
		return catSubType;
	}

	public void setCatSubType(String catSubType) {
		this.catSubType = catSubType;
	}

	public String getContentNumber() {
		return contentNumber;
	}

	public void setContentNumber(String contentNumber) {
		this.contentNumber = contentNumber;
	}

	public String getContentCatId() {
		return contentCatId;
	}

	public void setContentCatId(String contentCatId) {
		this.contentCatId = contentCatId;
	}

	public String getRefId() {
		return refId;
	}

	public void setRefId(String refId) {
		this.refId = refId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getConnectId() {
		return connectId;
	}

	public void setConnectId(String connectId) {
		this.connectId = connectId;
	}

	public String getConnectStatus() {
		return connectStatus;
	}

	public void setConnectStatus(String connectStatus) {
		this.connectStatus = connectStatus;
	}

	public String getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}

	public String getAgtLevel() {
		return agtLevel;
	}

	public void setAgtLevel(String agtLevel) {
		this.agtLevel = agtLevel;
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public String getOrd() {
		return ord;
	}

	public void setOrd(String ord) {
		this.ord = ord;
	}

	public String getCommonFlag() {
		return commonFlag;
	}

	public void setCommonFlag(String commonFlag) {
		this.commonFlag = commonFlag;
	}

	public String getStartDt() {
		return startDt;
	}

	public void setStartDt(String startDt) {
		this.startDt = startDt;
	}

	public String getEndDt() {
		return endDt;
	}

	public void setEndDt(String endDt) {
		this.endDt = endDt;
	}

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	public String getChgId() {
		return chgId;
	}

	public void setChgId(String chgId) {
		this.chgId = chgId;
	}

	public String getChgDt() {
		return chgDt;
	}

	public void setChgDt(String chgDt) {
		this.chgDt = chgDt;
	}

	public String getStartDtTime() {
		return startDtTime;
	}

	public void setStartDtTime(String startDtTime) {
		this.startDtTime = startDtTime;
	}

	public String getEndDtTime() {
		return endDtTime;
	}

	public void setEndDtTime(String endDtTime) {
		this.endDtTime = endDtTime;
	}

	public String getContentCatName() {
		return contentCatName;
	}

	public void setContentCatName(String contentCatName) {
		this.contentCatName = contentCatName;
	}

	public String getRegBy() {
		return regBy;
	}

	public void setRegBy(String regBy) {
		this.regBy = regBy;
	}

	public String getChgBy() {
		return chgBy;
	}

	public void setChgBy(String chgBy) {
		this.chgBy = chgBy;
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

}
