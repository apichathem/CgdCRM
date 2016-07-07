package com.locus.jlo.web.bean.dto;

import java.io.Serializable;
import java.util.Date;

public class ServiceRequestActDTO extends BaseDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4748466138819798232L;
	
	public ServiceRequestActDTO() {
		// TODO Auto-generated constructor stub
	}
	
	private String 	actNumber;
	private String 	refDocNo;
	private String 	attendTo;
	private String 	title;
	private String 	typeCd;
	private String 	subTypeCd;
	private String 	description;
	private String 	phoneNo;
	private String 	smsNo;
	private String 	faxNo;
	private String 	email;
	private String 	address;
	private String 	statusCd;
	private String 	ownerId;
	private String 	ownerDeptCode;
	private Date	openedDt;
	private Date	dueDt;
	private Date	operDt;
	private Date	closedDt;
	private String 	regId;
	private Date	regDt;
	private String 	chgId;
	private Date	chgDt;
	
	// Join From Other Table
	private String 	activityTypeName; 	
	private String 	activityStatusName;										
	private String 	ownerName;										
	private String 	deptName;										
	private String 	regName;										
	private String 	chgName;										
	private String 	regDatetime;										
	private String 	chgDatetime;	
	
	//for Document List AttachMent
	private String relAttId;
	private String 	attId;
	private String 	attDocumentName;			
	private String 	attKnowledgeBase;									
	private String 	attSendDocFlg;
	private String 	attDescription;
	private String 	attRefDocType;

		
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
	 * @return the chgId
	 */
	public String getChgId() {
		return chgId;
	}

	
	
	/**
	 * @param regId the regId to set
	 */
	public void setRegId(String regId) {
		this.regId = regId;
	}
	/**
	 * @param chgId the chgId to set
	 */
	public void setChgId(String chgId) {
		this.chgId = chgId;
	}
	/**
	 * @return the actNumber
	 */
	public String getActNumber() {
		return actNumber;
	}
	/**
	 * @param actNumber the actNumber to set
	 */
	public void setActNumber(String actNumber) {
		this.actNumber = actNumber;
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
	 * @return the attendTo
	 */
	public String getAttendTo() {
		return attendTo;
	}
	/**
	 * @param attendTo the attendTo to set
	 */
	public void setAttendTo(String attendTo) {
		this.attendTo = attendTo;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the typeCd
	 */
	public String getTypeCd() {
		return typeCd;
	}
	/**
	 * @param typeCd the typeCd to set
	 */
	public void setTypeCd(String typeCd) {
		this.typeCd = typeCd;
	}
	/**
	 * @return the subTypeCd
	 */
	public String getSubTypeCd() {
		return subTypeCd;
	}
	/**
	 * @param subTypeCd the subTypeCd to set
	 */
	public void setSubTypeCd(String subTypeCd) {
		this.subTypeCd = subTypeCd;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the phoneNo
	 */
	public String getPhoneNo() {
		return phoneNo;
	}
	/**
	 * @param phoneNo the phoneNo to set
	 */
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	/**
	 * @return the smsNo
	 */
	public String getSmsNo() {
		return smsNo;
	}
	/**
	 * @param smsNo the smsNo to set
	 */
	public void setSmsNo(String smsNo) {
		this.smsNo = smsNo;
	}
	/**
	 * @return the faxNo
	 */
	public String getFaxNo() {
		return faxNo;
	}
	/**
	 * @param faxNo the faxNo to set
	 */
	public void setFaxNo(String faxNo) {
		this.faxNo = faxNo;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the statusCd
	 */
	public String getStatusCd() {
		return statusCd;
	}
	/**
	 * @param statusCd the statusCd to set
	 */
	public void setStatusCd(String statusCd) {
		this.statusCd = statusCd;
	}
	/**
	 * @return the ownerId
	 */
	public String getOwnerId() {
		return ownerId;
	}
	/**
	 * @param ownerId the ownerId to set
	 */
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	/**
	 * @return the ownerDeptCode
	 */
	public String getOwnerDeptCode() {
		return ownerDeptCode;
	}
	/**
	 * @param ownerDeptCode the ownerDeptCode to set
	 */
	public void setOwnerDeptCode(String ownerDeptCode) {
		this.ownerDeptCode = ownerDeptCode;
	}
	/**
	 * @return the openedDt
	 */
	public Date getOpenedDt() {
		return openedDt;
	}
	/**
	 * @param openedDt the openedDt to set
	 */
	public void setOpenedDt(Date openedDt) {
		this.openedDt = openedDt;
	}
	/**
	 * @return the dueDt
	 */
	public Date getDueDt() {
		return dueDt;
	}
	/**
	 * @param dueDt the dueDt to set
	 */
	public void setDueDt(Date dueDt) {
		this.dueDt = dueDt;
	}
	/**
	 * @return the operDt
	 */
	public Date getOperDt() {
		return operDt;
	}
	/**
	 * @param operDt the operDt to set
	 */
	public void setOperDt(Date operDt) {
		this.operDt = operDt;
	}
	/**
	 * @return the closedDt
	 */
	public Date getClosedDt() {
		return closedDt;
	}
	/**
	 * @param closedDt the closedDt to set
	 */
	public void setClosedDt(Date closedDt) {
		this.closedDt = closedDt;
	}
	/**
	 * @return the regId
	 */
	public String getRegId() {
		return regId;
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
	 * @return the activityTypeName
	 */
	public String getActivityTypeName() {
		return activityTypeName;
	}
	/**
	 * @param activityTypeName the activityTypeName to set
	 */
	public void setActivityTypeName(String activityTypeName) {
		this.activityTypeName = activityTypeName;
	}
	/**
	 * @return the activityStatusName
	 */
	public String getActivityStatusName() {
		return activityStatusName;
	}
	/**
	 * @param activityStatusName the activityStatusName to set
	 */
	public void setActivityStatusName(String activityStatusName) {
		this.activityStatusName = activityStatusName;
	}
	/**
	 * @return the ownerName
	 */
	public String getOwnerName() {
		return ownerName;
	}
	/**
	 * @param ownerName the ownerName to set
	 */
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	/**
	 * @return the deptName
	 */
	public String getDeptName() {
		return deptName;
	}
	/**
	 * @param deptName the deptName to set
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
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
	 * @return the regDatetime
	 */
	public String getRegDatetime() {
		return regDatetime;
	}
	/**
	 * @param regDatetime the regDatetime to set
	 */
	public void setRegDatetime(String regDatetime) {
		this.regDatetime = regDatetime;
	}
	/**
	 * @return the chgDatetime
	 */
	public String getChgDatetime() {
		return chgDatetime;
	}
	/**
	 * @param chgDatetime the chgDatetime to set
	 */
	public void setChgDatetime(String chgDatetime) {
		this.chgDatetime = chgDatetime;
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
	 * @return the attKnowledgeBase
	 */
	public String getAttKnowledgeBase() {
		return attKnowledgeBase;
	}
	/**
	 * @param attKnowledgeBase the attKnowledgeBase to set
	 */
	public void setAttKnowledgeBase(String attKnowledgeBase) {
		this.attKnowledgeBase = attKnowledgeBase;
	}
	/**
	 * @return the attSendDocFlg
	 */
	public String getAttSendDocFlg() {
		return attSendDocFlg;
	}
	/**
	 * @param attSendDocFlg the attSendDocFlg to set
	 */
	public void setAttSendDocFlg(String attSendDocFlg) {
		this.attSendDocFlg = attSendDocFlg;
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
	 * @return the attRefDocType
	 */
	public String getAttRefDocType() {
		return attRefDocType;
	}
	/**
	 * @param attRefDocType the attRefDocType to set
	 */
	public void setAttRefDocType(String attRefDocType) {
		this.attRefDocType = attRefDocType;
	}
	/**
	 * @return the attId
	 */
	public String getAttId() {
		return attId;
	}
	/**
	 * @param attId the attId to set
	 */
	public void setAttId(String attId) {
		this.attId = attId;
	}
	/**
	 * @return the relAttId
	 */
	public String getRelAttId() {
		return relAttId;
	}
	/**
	 * @param relAttId the relAttId to set
	 */
	public void setRelAttId(String relAttId) {
		this.relAttId = relAttId;
	}
		
	

}
