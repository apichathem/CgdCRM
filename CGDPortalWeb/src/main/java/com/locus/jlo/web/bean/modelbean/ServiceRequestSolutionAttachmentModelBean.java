package com.locus.jlo.web.bean.modelbean;

import java.io.Serializable;

public class ServiceRequestSolutionAttachmentModelBean extends BaseModelBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3494317832901984379L;
	
	
	private Integer no;
	private String contentAttId;
	private String contentId;
	private String contentTitle;
	private String title;
	private String filePath;
	private String fileName;
	private String mainFlag;
	private String attId;
	private String descp;
	private String fileType;
	private String regId;
	private String regDtText;
	private String regBy;				
	private String chgDtText;
	private String chgBy;
	public Integer getNo() {
		return no;
	}
	public void setNo(Integer no) {
		this.no = no;
	}
	public String getContentAttId() {
		return contentAttId;
	}
	public void setContentAttId(String contentAttId) {
		this.contentAttId = contentAttId;
	}
	public String getContentId() {
		return contentId;
	}
	public void setContentId(String contentId) {
		this.contentId = contentId;
	}
	public String getContentTitle() {
		return contentTitle;
	}
	public void setContentTitle(String contentTitle) {
		this.contentTitle = contentTitle;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public String getMainFlag() {
		return mainFlag;
	}
	public void setMainFlag(String mainFlag) {
		this.mainFlag = mainFlag;
	}
	public String getAttId() {
		return attId;
	}
	public void setAttId(String attId) {
		this.attId = attId;
	}
	public String getDescp() {
		return descp;
	}
	public void setDescp(String descp) {
		this.descp = descp;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	public String getRegDtText() {
		return regDtText;
	}
	public void setRegDtText(String regDtText) {
		this.regDtText = regDtText;
	}
	public String getRegBy() {
		return regBy;
	}
	public void setRegBy(String regBy) {
		this.regBy = regBy;
	}
	public String getChgDtText() {
		return chgDtText;
	}
	public void setChgDtText(String chgDtText) {
		this.chgDtText = chgDtText;
	}
	public String getChgBy() {
		return chgBy;
	}
	public void setChgBy(String chgBy) {
		this.chgBy = chgBy;
	}
	
}
