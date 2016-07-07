/**
 * 
 */
package com.locus.jlo.web.bean.modelbean;

import java.io.Serializable;

/**
 * @author Mr.BoonOom
 * 
 */
public class ServiceRequestActivityModelBean extends BaseModelBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7875610063364631797L;

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
	private String openedDt;
	private String dueDt;
	private String operDt;
	private String closedDt;
	private String regId;
	private String regDt;
	private String chgId;
	private String chgDt;

	// Custom For set value in Component
	private String dueDate;
	private String dueDateTime;
	private String operDate;
	private String operDateTime;
	private String closedDate;
	private String closedDateTime;

	// Join From Other Table
	private String activityTypeName;
	private String activityStatusName;
	private String ownerName;
	private String deptName;
	private String regName;
	private String chgName;
	private String regDatetime;
	private String chgDatetime;

	private String refStatusCd;

	/**
	 * @return the dueDate
	 */
	public String getDueDate() {
		return dueDate;
	}

	/**
	 * @param dueDate
	 *            the dueDate to set
	 */
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	/**
	 * @return the dueDateTime
	 */
	public String getDueDateTime() {
		return dueDateTime;
	}

	/**
	 * @param dueDateTime
	 *            the dueDateTime to set
	 */
	public void setDueDateTime(String dueDateTime) {
		this.dueDateTime = dueDateTime;
	}

	/**
	 * @return the operDate
	 */
	public String getOperDate() {
		return operDate;
	}

	/**
	 * @param operDate
	 *            the operDate to set
	 */
	public void setOperDate(String operDate) {
		this.operDate = operDate;
	}

	/**
	 * @return the operDateTime
	 */
	public String getOperDateTime() {
		return operDateTime;
	}

	/**
	 * @param operDateTime
	 *            the operDateTime to set
	 */
	public void setOperDateTime(String operDateTime) {
		this.operDateTime = operDateTime;
	}

	/**
	 * @return the actNumber
	 */
	public String getActNumber() {
		return actNumber;
	}

	/**
	 * @param actNumber
	 *            the actNumber to set
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
	 * @param refDocNo
	 *            the refDocNo to set
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
	 * @param attendTo
	 *            the attendTo to set
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
	 * @param title
	 *            the title to set
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
	 * @param typeCd
	 *            the typeCd to set
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
	 * @param subTypeCd
	 *            the subTypeCd to set
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
	 * @param description
	 *            the description to set
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
	 * @param phoneNo
	 *            the phoneNo to set
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
	 * @param smsNo
	 *            the smsNo to set
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
	 * @param faxNo
	 *            the faxNo to set
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
	 * @param email
	 *            the email to set
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
	 * @param address
	 *            the address to set
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
	 * @param statusCd
	 *            the statusCd to set
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
	 * @param ownerId
	 *            the ownerId to set
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
	 * @param ownerDeptCode
	 *            the ownerDeptCode to set
	 */
	public void setOwnerDeptCode(String ownerDeptCode) {
		this.ownerDeptCode = ownerDeptCode;
	}

	/**
	 * @return the openedDt
	 */
	public String getOpenedDt() {
		return openedDt;
	}

	/**
	 * @param openedDt
	 *            the openedDt to set
	 */
	public void setOpenedDt(String openedDt) {
		this.openedDt = openedDt;
	}

	/**
	 * @return the dueDt
	 */
	public String getDueDt() {
		return dueDt;
	}

	/**
	 * @param dueDt
	 *            the dueDt to set
	 */
	public void setDueDt(String dueDt) {
		this.dueDt = dueDt;
	}

	/**
	 * @return the operDt
	 */
	public String getOperDt() {
		return operDt;
	}

	/**
	 * @param operDt
	 *            the operDt to set
	 */
	public void setOperDt(String operDt) {
		this.operDt = operDt;
	}

	/**
	 * @return the closedDt
	 */
	public String getClosedDt() {
		return closedDt;
	}

	/**
	 * @param closedDt
	 *            the closedDt to set
	 */
	public void setClosedDt(String closedDt) {
		this.closedDt = closedDt;
	}

	/**
	 * @return the regId
	 */
	public String getRegId() {
		return regId;
	}

	/**
	 * @param regId
	 *            the regId to set
	 */
	public void setRegId(String regId) {
		this.regId = regId;
	}

	/**
	 * @return the regDt
	 */
	public String getRegDt() {
		return regDt;
	}

	/**
	 * @param regDt
	 *            the regDt to set
	 */
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	/**
	 * @return the chgId
	 */
	public String getChgId() {
		return chgId;
	}

	/**
	 * @param chgId
	 *            the chgId to set
	 */
	public void setChgId(String chgId) {
		this.chgId = chgId;
	}

	/**
	 * @return the chgDt
	 */
	public String getChgDt() {
		return chgDt;
	}

	/**
	 * @param chgDt
	 *            the chgDt to set
	 */
	public void setChgDt(String chgDt) {
		this.chgDt = chgDt;
	}

	/**
	 * @return the activityTypeName
	 */
	public String getActivityTypeName() {
		return activityTypeName;
	}

	/**
	 * @param activityTypeName
	 *            the activityTypeName to set
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
	 * @param activityStatusName
	 *            the activityStatusName to set
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
	 * @param ownerName
	 *            the ownerName to set
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
	 * @param deptName
	 *            the deptName to set
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
	 * @param regName
	 *            the regName to set
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
	 * @param chgName
	 *            the chgName to set
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
	 * @param regDatetime
	 *            the regDatetime to set
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
	 * @param chgDatetime
	 *            the chgDatetime to set
	 */
	public void setChgDatetime(String chgDatetime) {
		this.chgDatetime = chgDatetime;
	}

	/**
	 * @return the closedDate
	 */
	public String getClosedDate() {
		return closedDate;
	}

	/**
	 * @param closedDate
	 *            the closedDate to set
	 */
	public void setClosedDate(String closedDate) {
		this.closedDate = closedDate;
	}

	/**
	 * @return the closedDateTime
	 */
	public String getClosedDateTime() {
		return closedDateTime;
	}

	/**
	 * @param closedDateTime
	 *            the closedDateTime to set
	 */
	public void setClosedDateTime(String closedDateTime) {
		this.closedDateTime = closedDateTime;
	}

	/**
	 * @return the refStatusCd
	 */
	public String getRefStatusCd() {
		return refStatusCd;
	}

	/**
	 * @param refStatusCd
	 *            the refStatusCd to set
	 */
	public void setRefStatusCd(String refStatusCd) {
		this.refStatusCd = refStatusCd;
	}

}
