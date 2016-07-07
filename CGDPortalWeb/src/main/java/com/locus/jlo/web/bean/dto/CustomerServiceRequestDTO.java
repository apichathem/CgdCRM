package com.locus.jlo.web.bean.dto;

import java.util.Date;

public class CustomerServiceRequestDTO extends BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5475971800941745526L;
	
	private String srNumber;
	private String srTypeProblem1;
	private String srTypeProblem2;
	private String srTypeProblem3;
	private String srTypeProblem4;
	private String srTypeProblem5;
	private String srPriorityDesc;
	private String srStatusDesc;
	private Date srOpenedDate;
	private Date srDueDate;
	private Date srCloseDate;
	private String srOwnerName;
	private String srSubject;
	private String srDescription;

	// for display code
	private String srConsultingNumber;
	private String srTypeProblem1Cd;
	private String srTypeProblem2Cd;
	private String srTypeProblem3Cd;
	private String srTypeProblem4Cd;
	private String srTypeProblem5Cd;

	private String srpriorityCd;
	private String srStatusCd;
	private Integer srOwnerId;
	private Integer srCustId;
	private String srCustType;
	private String srChannelCd;
	private String srChannelName;
	private String customerName;

	private Integer regId;
	private Date regDt;

	private Integer chgId;
	private Date chgDt;
	private String chgName;

	private String createByName;
	private String updateByName;
	private String createDateTime;
	private String updateDateTime;
	
	
	/*private String srNumber;
	private String srTypeDesc;
	private String srSubTypeDesc;
	private String srStatusDesc;
	private String srChannelName;
	private Integer srOwnerId;
	private String srOwnerName;
	private String srOwnerDepartment;
	private Date srDueDate;
	private Date srCreateDate;*/
	
	private String custType;
	private String custName;
	public String getSrNumber() {
		return srNumber;
	}
	public void setSrNumber(String srNumber) {
		this.srNumber = srNumber;
	}
	public String getSrTypeProblem1() {
		return srTypeProblem1;
	}
	public void setSrTypeProblem1(String srTypeProblem1) {
		this.srTypeProblem1 = srTypeProblem1;
	}
	public String getSrTypeProblem2() {
		return srTypeProblem2;
	}
	public void setSrTypeProblem2(String srTypeProblem2) {
		this.srTypeProblem2 = srTypeProblem2;
	}
	public String getSrTypeProblem3() {
		return srTypeProblem3;
	}
	public void setSrTypeProblem3(String srTypeProblem3) {
		this.srTypeProblem3 = srTypeProblem3;
	}
	public String getSrTypeProblem4() {
		return srTypeProblem4;
	}
	public void setSrTypeProblem4(String srTypeProblem4) {
		this.srTypeProblem4 = srTypeProblem4;
	}
	public String getSrTypeProblem5() {
		return srTypeProblem5;
	}
	public void setSrTypeProblem5(String srTypeProblem5) {
		this.srTypeProblem5 = srTypeProblem5;
	}
	public String getSrPriorityDesc() {
		return srPriorityDesc;
	}
	public void setSrPriorityDesc(String srPriorityDesc) {
		this.srPriorityDesc = srPriorityDesc;
	}
	public String getSrStatusDesc() {
		return srStatusDesc;
	}
	public void setSrStatusDesc(String srStatusDesc) {
		this.srStatusDesc = srStatusDesc;
	}
	public Date getSrOpenedDate() {
		return srOpenedDate;
	}
	public void setSrOpenedDate(Date srOpenedDate) {
		this.srOpenedDate = srOpenedDate;
	}
	public Date getSrDueDate() {
		return srDueDate;
	}
	public void setSrDueDate(Date srDueDate) {
		this.srDueDate = srDueDate;
	}
	public Date getSrCloseDate() {
		return srCloseDate;
	}
	public void setSrCloseDate(Date srCloseDate) {
		this.srCloseDate = srCloseDate;
	}
	public String getSrOwnerName() {
		return srOwnerName;
	}
	public void setSrOwnerName(String srOwnerName) {
		this.srOwnerName = srOwnerName;
	}
	public String getSrSubject() {
		return srSubject;
	}
	public void setSrSubject(String srSubject) {
		this.srSubject = srSubject;
	}
	public String getSrDescription() {
		return srDescription;
	}
	public void setSrDescription(String srDescription) {
		this.srDescription = srDescription;
	}
	public String getSrConsultingNumber() {
		return srConsultingNumber;
	}
	public void setSrConsultingNumber(String srConsultingNumber) {
		this.srConsultingNumber = srConsultingNumber;
	}
	public String getSrTypeProblem1Cd() {
		return srTypeProblem1Cd;
	}
	public void setSrTypeProblem1Cd(String srTypeProblem1Cd) {
		this.srTypeProblem1Cd = srTypeProblem1Cd;
	}
	public String getSrTypeProblem2Cd() {
		return srTypeProblem2Cd;
	}
	public void setSrTypeProblem2Cd(String srTypeProblem2Cd) {
		this.srTypeProblem2Cd = srTypeProblem2Cd;
	}
	public String getSrTypeProblem3Cd() {
		return srTypeProblem3Cd;
	}
	public void setSrTypeProblem3Cd(String srTypeProblem3Cd) {
		this.srTypeProblem3Cd = srTypeProblem3Cd;
	}
	public String getSrTypeProblem4Cd() {
		return srTypeProblem4Cd;
	}
	public void setSrTypeProblem4Cd(String srTypeProblem4Cd) {
		this.srTypeProblem4Cd = srTypeProblem4Cd;
	}
	public String getSrTypeProblem5Cd() {
		return srTypeProblem5Cd;
	}
	public void setSrTypeProblem5Cd(String srTypeProblem5Cd) {
		this.srTypeProblem5Cd = srTypeProblem5Cd;
	}
	public String getSrpriorityCd() {
		return srpriorityCd;
	}
	public void setSrpriorityCd(String srpriorityCd) {
		this.srpriorityCd = srpriorityCd;
	}
	public String getSrStatusCd() {
		return srStatusCd;
	}
	public void setSrStatusCd(String srStatusCd) {
		this.srStatusCd = srStatusCd;
	}
	public Integer getSrOwnerId() {
		return srOwnerId;
	}
	public void setSrOwnerId(Integer srOwnerId) {
		this.srOwnerId = srOwnerId;
	}
	public Integer getSrCustId() {
		return srCustId;
	}
	public void setSrCustId(Integer srCustId) {
		this.srCustId = srCustId;
	}
	public String getSrCustType() {
		return srCustType;
	}
	public void setSrCustType(String srCustType) {
		this.srCustType = srCustType;
	}
	public String getSrChannelCd() {
		return srChannelCd;
	}
	public void setSrChannelCd(String srChannelCd) {
		this.srChannelCd = srChannelCd;
	}
	public String getSrChannelName() {
		return srChannelName;
	}
	public void setSrChannelName(String srChannelName) {
		this.srChannelName = srChannelName;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Integer getRegId() {
		return regId;
	}
	public void setRegId(Integer regId) {
		this.regId = regId;
	}
	public Date getRegDt() {
		return regDt;
	}
	public void setRegDt(Date regDt) {
		this.regDt = regDt;
	}
	public Integer getChgId() {
		return chgId;
	}
	public void setChgId(Integer chgId) {
		this.chgId = chgId;
	}
	public Date getChgDt() {
		return chgDt;
	}
	public void setChgDt(Date chgDt) {
		this.chgDt = chgDt;
	}
	public String getChgName() {
		return chgName;
	}
	public void setChgName(String chgName) {
		this.chgName = chgName;
	}
	public String getCreateByName() {
		return createByName;
	}
	public void setCreateByName(String createByName) {
		this.createByName = createByName;
	}
	public String getUpdateByName() {
		return updateByName;
	}
	public void setUpdateByName(String updateByName) {
		this.updateByName = updateByName;
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
	public String getCustType() {
		return custType;
	}
	public void setCustType(String custType) {
		this.custType = custType;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
}
