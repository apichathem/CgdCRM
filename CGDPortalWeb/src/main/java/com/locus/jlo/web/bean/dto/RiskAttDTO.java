package com.locus.jlo.web.bean.dto;

import java.util.Date;

public class RiskAttDTO extends BaseDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7092597090816337194L;
	private Integer riskId;
	private Integer attId;
	private Integer seq;
	private Integer enabled;
	private String regId;
	private Date regDt;
	private String chgId;
	private Date chgDt;
	
	private String fileName;
	private String filePath;
	private String fileExtension;
	private String fileType;
	private String fileSize;
	private String regDtText;
	private String chgDtText;
	
	public Integer getRiskId() {
		return riskId;
	}
	public void setRiskId(Integer riskId) {
		this.riskId = riskId;
	}
	public Integer getAttId() {
		return attId;
	}
	public void setAttId(Integer attId) {
		this.attId = attId;
	}
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	public Integer getEnabled() {
		return enabled;
	}
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	public Date getRegDt() {
		return regDt;
	}
	public void setRegDt(Date regDt) {
		this.regDt = regDt;
	}
	public String getChgId() {
		return chgId;
	}
	public void setChgId(String chgId) {
		this.chgId = chgId;
	}
	public Date getChgDt() {
		return chgDt;
	}
	public void setChgDt(Date chgDt) {
		this.chgDt = chgDt;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
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
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
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
}
