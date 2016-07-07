package com.locus.jlo.web.bean.modelbean;

import java.io.Serializable;

public class ActivityModelBean extends BaseModelBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1775841252041186451L;
	// Criteria
	private String activityNumber;
	private String activityGroup;
	private String activityTypeParam;
	private String activityStatusParam;
	private String ownerIdParam;
	private String activityCreatedDateFrom;
	private String activityCreatedDateTo;
	private String activityClosedDateFrom;
	private String activityClosedDateTo;
	private String dueTime;
	private String operTime;
	private String operDate;
	private String operDateTime;

	private String closedDate;
	private String closedTime;

	private String no;
	private String actNumber;
	private String actGroup;
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
	private String openedDt;
	private String dueDt;
	private String operDt;
	private String closedDt;
	private Integer regId;
	private String regDt;
	private Integer chgId;
	private String chgDt;

	private String activityTypeName;
	private String activityStatusName;
	private String ownerName;
	private String deptName;
	private String regName;
	private String chgName;
	private String regDatetime;
	private String chgDatetime;

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

	public String getOpenedDt() {
		return openedDt;
	}

	public void setOpenedDt(String openedDt) {
		this.openedDt = openedDt;
	}

	public String getDueDt() {
		return dueDt;
	}

	public void setDueDt(String dueDt) {
		this.dueDt = dueDt;
	}

	public String getOperDt() {
		return operDt;
	}

	public void setOperDt(String operDt) {
		this.operDt = operDt;
	}

	public String getClosedDt() {
		return closedDt;
	}

	public void setClosedDt(String closedDt) {
		this.closedDt = closedDt;
	}

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	public String getChgDt() {
		return chgDt;
	}

	public void setChgDt(String chgDt) {
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

	public String getActivityTypeParam() {
		return activityTypeParam;
	}

	public void setActivityTypeParam(String activityTypeParam) {
		this.activityTypeParam = activityTypeParam;
	}

	public String getActivityStatusParam() {
		return activityStatusParam;
	}

	public void setActivityStatusParam(String activityStatusParam) {
		this.activityStatusParam = activityStatusParam;
	}

	public String getOwnerIdParam() {
		return ownerIdParam;
	}

	public void setOwnerIdParam(String ownerIdParam) {
		this.ownerIdParam = ownerIdParam;
	}

	public String getActivityCreatedDateFrom() {
		return activityCreatedDateFrom;
	}

	public void setActivityCreatedDateFrom(String activityCreatedDateFrom) {
		this.activityCreatedDateFrom = activityCreatedDateFrom;
	}

	public String getActivityCreatedDateTo() {
		return activityCreatedDateTo;
	}

	public void setActivityCreatedDateTo(String activityCreatedDateTo) {
		this.activityCreatedDateTo = activityCreatedDateTo;
	}

	public String getActivityClosedDateFrom() {
		return activityClosedDateFrom;
	}

	public void setActivityClosedDateFrom(String activityClosedDateFrom) {
		this.activityClosedDateFrom = activityClosedDateFrom;
	}

	public String getActivityClosedDateTo() {
		return activityClosedDateTo;
	}

	public void setActivityClosedDateTo(String activityClosedDateTo) {
		this.activityClosedDateTo = activityClosedDateTo;
	}

	public String getDueTime() {
		return dueTime;
	}

	public void setDueTime(String dueTime) {
		this.dueTime = dueTime;
	}

	public String getOperTime() {
		return operTime;
	}

	public void setOperTime(String operTime) {
		this.operTime = operTime;
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

	public String getOperDate() {
		return operDate;
	}

	public void setOperDate(String operDate) {
		this.operDate = operDate;
	}

	public String getOperDateTime() {
		return operDateTime;
	}

	public void setOperDateTime(String operDateTime) {
		this.operDateTime = operDateTime;
	}

	public String getActivityNumber() {
		return activityNumber;
	}

	public void setActivityNumber(String activityNumber) {
		this.activityNumber = activityNumber;
	}

	public String getClosedDate() {
		return closedDate;
	}

	public void setClosedDate(String closedDate) {
		this.closedDate = closedDate;
	}

	public String getClosedTime() {
		return closedTime;
	}

	public void setClosedTime(String closedTime) {
		this.closedTime = closedTime;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
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
