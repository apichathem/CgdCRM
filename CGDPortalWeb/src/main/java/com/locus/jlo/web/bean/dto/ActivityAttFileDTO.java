package com.locus.jlo.web.bean.dto;

import java.io.Serializable;
import java.util.Date;

public class ActivityAttFileDTO extends BaseDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 343029849092834532L;
	
	//TP_ATT
	private Integer attId;
	private String filePath;
	private String fileName;
    private String fileExtension;
    private String fileType;
    private String activeFlg;
    private String attSourceCode;
    private String userPicture;
    private Date regDt;
    private Date chgDt;
    private String attType;
    private String fileSize;

    //TP_REL_ATT
    private Integer relAttId;
    private String refDocType;
    private String refDocNo;
    private String attName;
    private String descp;
    private Integer regId;
    private Date regTime;
    private Integer chgId;
    private Date chgTime;
    private String sendDocFlg;
    
	//SQL Select Mapping
    private Integer no;
	private String 	actId;
	private String 	actDocumentName;			
	private String 	actKnowledgeBase;									
	private String 	actSendDocFlg;
	private String 	actDescription;
	private String 	actRefDocType;

	private String regName;
	private String chgName;
	private String createDateTime;
	private String updateDateTime;
	
	public Integer getAttId() {
		return attId;
	}
	public void setAttId(Integer attId) {
		this.attId = attId;
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
	public String getActiveFlg() {
		return activeFlg;
	}
	public void setActiveFlg(String activeFlg) {
		this.activeFlg = activeFlg;
	}
	public String getAttSourceCode() {
		return attSourceCode;
	}
	public void setAttSourceCode(String attSourceCode) {
		this.attSourceCode = attSourceCode;
	}
	public String getUserPicture() {
		return userPicture;
	}
	public void setUserPicture(String userPicture) {
		this.userPicture = userPicture;
	}
	public Date getRegDt() {
		return regDt;
	}
	public void setRegDt(Date regDt) {
		this.regDt = regDt;
	}
	public Date getChgDt() {
		return chgDt;
	}
	public void setChgDt(Date chgDt) {
		this.chgDt = chgDt;
	}
	public String getAttType() {
		return attType;
	}
	public void setAttType(String attType) {
		this.attType = attType;
	}
	public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	public Integer getRelAttId() {
		return relAttId;
	}
	public void setRelAttId(Integer relAttId) {
		this.relAttId = relAttId;
	}
	public String getRefDocType() {
		return refDocType;
	}
	public void setRefDocType(String refDocType) {
		this.refDocType = refDocType;
	}
	public String getRefDocNo() {
		return refDocNo;
	}
	public void setRefDocNo(String refDocNo) {
		this.refDocNo = refDocNo;
	}
	public String getAttName() {
		return attName;
	}
	public void setAttName(String attName) {
		this.attName = attName;
	}
	public String getDescp() {
		return descp;
	}
	public void setDescp(String descp) {
		this.descp = descp;
	}
	public Integer getRegId() {
		return regId;
	}
	public void setRegId(Integer regId) {
		this.regId = regId;
	}
	public Date getRegTime() {
		return regTime;
	}
	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}
	public Integer getChgId() {
		return chgId;
	}
	public void setChgId(Integer chgId) {
		this.chgId = chgId;
	}
	public Date getChgTime() {
		return chgTime;
	}
	public void setChgTime(Date chgTime) {
		this.chgTime = chgTime;
	}
	public String getSendDocFlg() {
		return sendDocFlg;
	}
	public void setSendDocFlg(String sendDocFlg) {
		this.sendDocFlg = sendDocFlg;
	}
	public Integer getNo() {
		return no;
	}
	public void setNo(Integer no) {
		this.no = no;
	}
	public String getActId() {
		return actId;
	}
	public void setActId(String actId) {
		this.actId = actId;
	}
	public String getActDocumentName() {
		return actDocumentName;
	}
	public void setActDocumentName(String actDocumentName) {
		this.actDocumentName = actDocumentName;
	}
	public String getActKnowledgeBase() {
		return actKnowledgeBase;
	}
	public void setActKnowledgeBase(String actKnowledgeBase) {
		this.actKnowledgeBase = actKnowledgeBase;
	}
	public String getActSendDocFlg() {
		return actSendDocFlg;
	}
	public void setActSendDocFlg(String actSendDocFlg) {
		this.actSendDocFlg = actSendDocFlg;
	}
	public String getActRefDocType() {
		return actRefDocType;
	}
	public void setActRefDocType(String attRefDocType) {
		this.actRefDocType = attRefDocType;
	}
	public String getActDescription() {
		return actDescription;
	}
	public void setActDescription(String attDescription) {
		this.actDescription = attDescription;
	}
	public String getRegName() {
		return regName;
	}
	public void setRegName(String regName) {
		this.regName = regName;
	}
	public String getChgName() {
		return chgName;
	}
	public void setChgName(String chgName) {
		this.chgName = chgName;
	}
	public String getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(String createDateTime) {
		this.createDateTime = createDateTime;
	}
	public String getUpdateDateTime() {
		return updateDateTime;
	}
	public void setUpdateDateTime(String updateDateTime) {
		this.updateDateTime = updateDateTime;
	}
}
