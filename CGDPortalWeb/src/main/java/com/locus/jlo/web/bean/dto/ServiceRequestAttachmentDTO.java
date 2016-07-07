/**
 * 
 */
package com.locus.jlo.web.bean.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Mr.BoonOom
 * 
 */
public class ServiceRequestAttachmentDTO  extends BaseDTO implements Serializable {

	private static final long serialVersionUID = 1731115004231796879L;
	/**
	 * 
	 */
	public ServiceRequestAttachmentDTO() {
		// TODO Auto-generated constructor stub
	}

	//TP_ATT Properties
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
    
    
    //TP_REL_ATT Properties
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
	
	private String attDocumentName;
	private String attDescription;
	private String attFileName;
	private String attRefDocNo;

	private String regName;
	private String chgName;
	private String createDateTime;
	private String updateDateTime;
	
	
	
	/**
	 * @return the attId
	 */
	public Integer getAttId() {
		return attId;
	}
	/**
	 * @param attId the attId to set
	 */
	public void setAttId(Integer attId) {
		this.attId = attId;
	}
	/**
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}
	/**
	 * @param filePath the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * @return the fileExtension
	 */
	public String getFileExtension() {
		return fileExtension;
	}
	/**
	 * @param fileExtension the fileExtension to set
	 */
	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}
	/**
	 * @return the fileType
	 */
	public String getFileType() {
		return fileType;
	}
	/**
	 * @param fileType the fileType to set
	 */
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	/**
	 * @return the activeFlg
	 */
	public String getActiveFlg() {
		return activeFlg;
	}
	/**
	 * @param activeFlg the activeFlg to set
	 */
	public void setActiveFlg(String activeFlg) {
		this.activeFlg = activeFlg;
	}
	/**
	 * @return the attSourceCode
	 */
	public String getAttSourceCode() {
		return attSourceCode;
	}
	/**
	 * @param attSourceCode the attSourceCode to set
	 */
	public void setAttSourceCode(String attSourceCode) {
		this.attSourceCode = attSourceCode;
	}
	/**
	 * @return the userPicture
	 */
	public String getUserPicture() {
		return userPicture;
	}
	/**
	 * @param userPicture the userPicture to set
	 */
	public void setUserPicture(String userPicture) {
		this.userPicture = userPicture;
	}
	/**
	 * @return the regDt
	 */
	public Date getRegDt() {
		return regDt;
	}
	/**
	 * @param regDt the regDt to set
	 */
	public void setRegDt(Date regDt) {
		this.regDt = regDt;
	}
	/**
	 * @return the chgDt
	 */
	public Date getChgDt() {
		return chgDt;
	}
	/**
	 * @param chgDt the chgDt to set
	 */
	public void setChgDt(Date chgDt) {
		this.chgDt = chgDt;
	}
	/**
	 * @return the attType
	 */
	public String getAttType() {
		return attType;
	}
	/**
	 * @param attType the attType to set
	 */
	public void setAttType(String attType) {
		this.attType = attType;
	}
	/**
	 * @return the fileSize
	 */
	public String getFileSize() {
		return fileSize;
	}
	/**
	 * @param fileSize the fileSize to set
	 */
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	/**
	 * @return the relAttId
	 */
	public Integer getRelAttId() {
		return relAttId;
	}
	/**
	 * @param relAttId the relAttId to set
	 */
	public void setRelAttId(Integer relAttId) {
		this.relAttId = relAttId;
	}
	/**
	 * @return the refDocType
	 */
	public String getRefDocType() {
		return refDocType;
	}
	/**
	 * @param refDocType the refDocType to set
	 */
	public void setRefDocType(String refDocType) {
		this.refDocType = refDocType;
	}
	/**
	 * @return the refDocNo
	 */
	public String getRefDocNo() {
		return refDocNo;
	}
	/**
	 * @param refDocNo the refDocNo to set
	 */
	public void setRefDocNo(String refDocNo) {
		this.refDocNo = refDocNo;
	}
	/**
	 * @return the attName
	 */
	public String getAttName() {
		return attName;
	}
	/**
	 * @param attName the attName to set
	 */
	public void setAttName(String attName) {
		this.attName = attName;
	}
	/**
	 * @return the descp
	 */
	public String getDescp() {
		return descp;
	}
	/**
	 * @param descp the descp to set
	 */
	public void setDescp(String descp) {
		this.descp = descp;
	}
	/**
	 * @return the regId
	 */
	public Integer getRegId() {
		return regId;
	}
	/**
	 * @param regId the regId to set
	 */
	public void setRegId(Integer regId) {
		this.regId = regId;
	}
	/**
	 * @return the regTime
	 */
	public Date getRegTime() {
		return regTime;
	}
	/**
	 * @param regTime the regTime to set
	 */
	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}
	/**
	 * @return the chgId
	 */
	public Integer getChgId() {
		return chgId;
	}
	/**
	 * @param chgId the chgId to set
	 */
	public void setChgId(Integer chgId) {
		this.chgId = chgId;
	}
	/**
	 * @return the chgTime
	 */
	public Date getChgTime() {
		return chgTime;
	}
	/**
	 * @param chgTime the chgTime to set
	 */
	public void setChgTime(Date chgTime) {
		this.chgTime = chgTime;
	}
	/**
	 * @return the sendDocFlg
	 */
	public String getSendDocFlg() {
		return sendDocFlg;
	}
	/**
	 * @param sendDocFlg the sendDocFlg to set
	 */
	public void setSendDocFlg(String sendDocFlg) {
		this.sendDocFlg = sendDocFlg;
	}
	/**
	 * @return the attDocumentName
	 */
	public String getAttDocumentName() {
		return attDocumentName;
	}
	/**
	 * @param attDocumentName the attDocumentName to set
	 */
	public void setAttDocumentName(String attDocumentName) {
		this.attDocumentName = attDocumentName;
	}
	/**
	 * @return the attDescription
	 */
	public String getAttDescription() {
		return attDescription;
	}
	/**
	 * @param attDescription the attDescription to set
	 */
	public void setAttDescription(String attDescription) {
		this.attDescription = attDescription;
	}
	/**
	 * @return the attFileName
	 */
	public String getAttFileName() {
		return attFileName;
	}
	/**
	 * @param attFileName the attFileName to set
	 */
	public void setAttFileName(String attFileName) {
		this.attFileName = attFileName;
	}
	/**
	 * @return the attRefDocNo
	 */
	public String getAttRefDocNo() {
		return attRefDocNo;
	}
	/**
	 * @param attRefDocNo the attRefDocNo to set
	 */
	public void setAttRefDocNo(String attRefDocNo) {
		this.attRefDocNo = attRefDocNo;
	}
	/**
	 * @return the regName
	 */
	public String getRegName() {
		return regName;
	}
	/**
	 * @param regName the regName to set
	 */
	public void setRegName(String regName) {
		this.regName = regName;
	}
	/**
	 * @return the chgName
	 */
	public String getChgName() {
		return chgName;
	}
	/**
	 * @param chgName the chgName to set
	 */
	public void setChgName(String chgName) {
		this.chgName = chgName;
	}
	/**
	 * @return the createDateTime
	 */
	public String getCreateDateTime() {
		return createDateTime;
	}
	/**
	 * @param createDateTime the createDateTime to set
	 */
	public void setCreateDateTime(String createDateTime) {
		this.createDateTime = createDateTime;
	}
	/**
	 * @return the updateDateTime
	 */
	public String getUpdateDateTime() {
		return updateDateTime;
	}
	/**
	 * @param updateDateTime the updateDateTime to set
	 */
	public void setUpdateDateTime(String updateDateTime) {
		this.updateDateTime = updateDateTime;
	}


	
	
	
}
