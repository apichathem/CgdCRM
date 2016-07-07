package com.locus.jlo.web.bean.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class ContentDTO extends BaseDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private String contentNumber;
	private String agtLevel;
	private Date chgDt;
	private String chgId;
	private String chgBy;
	private String connectId;
	private String connectStatus;
	private String display;
	private Date endDt;
	private Integer ord;
	private String refId;
	private Date regDt;
	private String regId;
	private String regBy;
	private String commonFlag;
	private Date startDt;
	private String summary;
	private String title;
	private String type;
	private String useStatus;
	private Integer contentCatId;
	private String area;
	private String parent;
	
	private ContentCatDTO contentCat;
	private List<ContentAddrDTO> contentAddr;
	private List<ContentAttDTO> contentAtt;
	private List<ContentKeywordDTO> contentKeyWord;
	
	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	protected void onCreate() {
		regDt = new Date();
		chgDt = new Date();
	}
	
	protected void onUpdate() {
		chgDt = new Date();
	}

	public ContentDTO() {
	}

	public String getContentNumber() {
		return contentNumber;
	}

	public void setContentNumber(String contentNumber) {
		this.contentNumber = contentNumber;
	}

	public String getAgtLevel() {
		return agtLevel;
	}

	public void setAgtLevel(String agtLevel) {
		this.agtLevel = agtLevel;
	}

	public Date getChgDt() {
		return chgDt;
	}

	public void setChgDt(Date chgDt) {
		this.chgDt = chgDt;
	}

	public String getChgId() {
		return chgId;
	}

	public void setChgId(String chgId) {
		this.chgId = chgId;
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

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public Date getEndDt() {
		return endDt;
	}

	public void setEndDt(Date endDt) {
		this.endDt = endDt;
	}

	public Integer getOrd() {
		return ord;
	}

	public void setOrd(Integer ord) {
		this.ord = ord;
	}

	public String getRefId() {
		return refId;
	}

	public void setRefId(String refId) {
		this.refId = refId;
	}

	public Date getRegDt() {
		return regDt;
	}

	public void setRegDt(Date regDt) {
		this.regDt = regDt;
	}

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	

	public String getCommonFlag() {
		return commonFlag;
	}

	public void setCommonFlag(String commonFlag) {
		this.commonFlag = commonFlag;
	}

	public Date getStartDt() {
		return startDt;
	}

	public void setStartDt(Date startDt) {
		this.startDt = startDt;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getArea() {
		return area;
	}

	public ContentCatDTO getContentCat() {
		return contentCat;
	}

	public void setContentCat(ContentCatDTO contentCat) {
		this.contentCat = contentCat;
	}

	public List<ContentAddrDTO> getContentAddr() {
		return contentAddr;
	}

	public void setContentAddr(List<ContentAddrDTO> contentAddr) {
		this.contentAddr = contentAddr;
	}

	public List<ContentAttDTO> getContentAtt() {
		return contentAtt;
	}

	public void setContentAtt(List<ContentAttDTO> contentAtt) {
		this.contentAtt = contentAtt;
	}

	public List<ContentKeywordDTO> getContentKeyWord() {
		return contentKeyWord;
	}

	public void setContentKeyWord(List<ContentKeywordDTO> contentKeyWord) {
		this.contentKeyWord = contentKeyWord;
	}

	public Integer getContentCatId() {
		return contentCatId;
	}

	public void setContentCatId(Integer contentCatId) {
		this.contentCatId = contentCatId;
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

	

}