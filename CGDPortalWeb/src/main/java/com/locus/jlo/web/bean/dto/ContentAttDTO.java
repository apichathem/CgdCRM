package com.locus.jlo.web.bean.dto;

import java.io.Serializable;
import java.util.Date;


public class ContentAttDTO extends BaseDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	// TP_CONTENT_ATT
	private Long contentAttId;
	private Long attId;
	private String contentId;
	private String contentTitle;
	private String title;
	private String descp;
	private String mainFlag;
	private String sendDocTypeFlag;
	private String attTypeFlag;
	private Date regDt;
	private String regBy;
	private String regId;
	private String regDtText; 
	private Date chgDt;
	private String chgBy;
	private String chgId;
	private String chgDtText;
	
	//TP_ATT
	private String filePath;	
	private String fileName;
	private String fileExtension;
	private String fileType;
	private String fileSize;
	private String activeFlg;
	
	
	
	public String getContentId() {
		return contentId;
	}
	public void setContentId(String contentId) {
		this.contentId = contentId;
	}
	public String getDescp() {
		return descp;
	}
	public void setDescp(String descp) {
		this.descp = descp;
	}
	public String getMainFlag() {
		return mainFlag;
	}
	public void setMainFlag(String mainFlag) {
		this.mainFlag = mainFlag;
	}
	public String getSendDocTypeFlag() {
		return sendDocTypeFlag;
	}
	public void setSendDocTypeFlag(String sendDocTypeFlag) {
		this.sendDocTypeFlag = sendDocTypeFlag;
	}
	public String getAttTypeFlag() {
		return attTypeFlag;
	}
	public void setAttTypeFlag(String attTypeFlag) {
		this.attTypeFlag = attTypeFlag;
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
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileExtension() {
		return fileExtension;
	}
	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	public String getActiveFlg() {
		return activeFlg;
	}
	public void setActiveFlg(String activeFlg) {
		this.activeFlg = activeFlg;
	}
	public Long getContentAttId() {
		return contentAttId;
	}
	public void setContentAttId(Long contentAttId) {
		this.contentAttId = contentAttId;
	}
	public Long getAttId() {
		return attId;
	}
	public void setAttId(Long attId) {
		this.attId = attId;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getRegDtText() {
		return regDtText;
	}
	public void setRegDtText(String regDtText) {
		this.regDtText = regDtText;
	}
	public String getChgDtText() {
		return chgDtText;
	}
	public void setChgDtText(String chgDtText) {
		this.chgDtText = chgDtText;
	}
	public String getContentTitle() {
		return contentTitle;
	}
	public void setContentTitle(String contentTitle) {
		this.contentTitle = contentTitle;
	}
	
}
