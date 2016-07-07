package com.locus.jlo.web.bean.dto;

import java.io.Serializable;
import java.util.Date;

public class ActivityDTO extends BaseDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 289175721182380445L;

	//Activity detail
	private String actGroup;
	private String actNumber;
	private String refDocNo;
	private String attendTo;
	private String title;
	private String typeCd;
	private String subTypeCd;
	private String description;
	private String phoneNo;
	private String smsNo;
	private String faxNo;
	private String email;
	private String address;
	private String statusCd;
	private String ownerId;
	private String ownerDeptCode;
	private String assignToId;
	private String assignToName;
	private Date openedDt;
	private Date dueDt;
	private Date operDt;
	private Date closedDt;
	private Integer regId;
	private Date regDt;
	private Integer chgId;
	private Date chgDt;
	
	private String activityNumber;
	private String activityGroup;
	private String activityTypeName;
	private String activityStatusName;
	private String ownerName;
	private String deptName;
	private String regName;
	private String chgName;
	private String regDatetime;
	private String chgDatetime;
	
	//for Document List AttachMent
	private String 	actId;
	private String 	actDocumentName;			
	private String 	actKnowledgeBase;									
	private String 	actSendDocFlg;

	public String getActNumber() {
		return actNumber;
	}

	public void setActNumber(String actNumber) {
		this.actNumber = actNumber;
	}

	public String getRefDocNo() {
		return refDocNo;
	}

	public void setRefDocNo(String refDocNo) {
		this.refDocNo = refDocNo;
	}

	public String getAttendTo() {
		return attendTo;
	}

	public void setAttendTo(String attendTo) {
		this.attendTo = attendTo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTypeCd() {
		return typeCd;
	}

	public void setTypeCd(String typeCd) {
		this.typeCd = typeCd;
	}

	public String getSubTypeCd() {
		return subTypeCd;
	}

	public void setSubTypeCd(String subTypeCd) {
		this.subTypeCd = subTypeCd;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getSmsNo() {
		return smsNo;
	}

	public void setSmsNo(String smsNo) {
		this.smsNo = smsNo;
	}

	public String getFaxNo() {
		return faxNo;
	}

	public void setFaxNo(String faxNo) {
		this.faxNo = faxNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStatusCd() {
		return statusCd;
	}

	public void setStatusCd(String statusCd) {
		this.statusCd = statusCd;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getOwnerDeptCode() {
		return ownerDeptCode;
	}

	public void setOwnerDeptCode(String ownerDeptCode) {
		this.ownerDeptCode = ownerDeptCode;
	}

	public Date getOpenedDt() {
		return openedDt;
	}

	public void setOpenedDt(Date openedDt) {
		this.openedDt = openedDt;
	}

	public Date getDueDt() {
		return dueDt;
	}

	public void setDueDt(Date dueDt) {
		this.dueDt = dueDt;
	}

	public Date getOperDt() {
		return operDt;
	}

	public void setOperDt(Date operDt) {
		this.operDt = operDt;
	}

	public Date getClosedDt() {
		return closedDt;
	}

	public void setClosedDt(Date closedDt) {
		this.closedDt = closedDt;
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

	public String getActivityTypeName() {
		return activityTypeName;
	}

	public void setActivityTypeName(String activityTypeName) {
		this.activityTypeName = activityTypeName;
	}

	public String getActivityStatusName() {
		return activityStatusName;
	}

	public void setActivityStatusName(String activityStatusName) {
		this.activityStatusName = activityStatusName;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
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

	public String getRegDatetime() {
		return regDatetime;
	}

	public void setRegDatetime(String regDatetime) {
		this.regDatetime = regDatetime;
	}

	public String getChgDatetime() {
		return chgDatetime;
	}

	public void setChgDatetime(String chgDatetime) {
		this.chgDatetime = chgDatetime;
	}

	public Integer getRegId() {
		return regId;
	}

	public void setRegId(Integer regId) {
		this.regId = regId;
	}

	public Integer getChgId() {
		return chgId;
	}

	public void setChgId(Integer chgId) {
		this.chgId = chgId;
	}

	public String getAssignToId() {
		return assignToId;
	}

	public void setAssignToId(String assignToId) {
		this.assignToId = assignToId;
	}

	public String getAssignToName() {
		return assignToName;
	}

	public void setAssignToName(String assignToName) {
		this.assignToName = assignToName;
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

	public String getActivityNumber() {
		return activityNumber;
	}

	public void setActivityNumber(String activityNumber) {
		this.activityNumber = activityNumber;
	}

	public String getActGroup() {
		return actGroup;
	}

	public void setActGroup(String actGroup) {
		this.actGroup = actGroup;
	}

	public String getActivityGroup() {
		return activityGroup;
	}

	public void setActivityGroup(String activityGroup) {
		this.activityGroup = activityGroup;
	}
}
