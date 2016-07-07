/**
 * 
 */
package com.locus.jlo.web.bean.modelbean;

import java.io.Serializable;

/**
 * @author Mr.BoonOom
 * 
 */
public class ServiceRequestModelBean extends BaseModelBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3973946269474934305L;

	/**
	 * For grid search service request list
	 */

	private String srId;
	private String srNumber;
	private String srSubject;
	private String srTypeProblem1;
	private String srTypeProblem2;
	private String srTypeProblem3;
	private String srTypeProblem4;
	private String srTypeProblem5;
	private String srPriorityDesc;
	private String srStatusDesc;
	private String srOpenedDate;
	private String srOpenedTime;
	private String srDueDate;
	private String srDueTime;
	private String srCloseDate;
	private String srCloseTime;
	private String srOwnerName;
	private String srConsultingNumber;
	// for display code
	private String srTypeProblem1Cd;
	private String srTypeProblem2Cd;
	private String srTypeProblem3Cd;
	private String srTypeProblem4Cd;
	private String srTypeProblem5Cd;
	private String srPriorityCd;
	private String srStatusCd;
	private String srAssetId;
	private String srAssetCode;
	private String srOwnerId;
	private String srOwnerDeptCd;
	private String srOwnerDeptName;
	private String srCustId;
	private String srCustType;
	private String srChannelCd;
	private String srChannelName;
	private String srCustomerName;
	private String srContactName;
	private String srContactId;
	private String srDescription;
	private String srNoteDescription;
	private String srRegId;
	private String srRegDate;
	private String srChgId;
	private String srChgDt;

	private String createByName;
	private String updateByName;
	private String createDateTime;
	private String updateDateTime;

	public String getSrId() {
		return srId;
	}

	public void setSrId(String srId) {
		this.srId = srId;
	}

	public String getSrNumber() {
		return srNumber;
	}

	public void setSrNumber(String srNumber) {
		this.srNumber = srNumber;
	}

	public String getSrSubject() {
		return srSubject;
	}

	public void setSrSubject(String srSubject) {
		this.srSubject = srSubject;
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

	public String getSrOpenedDate() {
		return srOpenedDate;
	}

	public void setSrOpenedDate(String srOpenedDate) {
		this.srOpenedDate = srOpenedDate;
	}

	public String getSrOpenedTime() {
		return srOpenedTime;
	}

	public void setSrOpenedTime(String srOpenedTime) {
		this.srOpenedTime = srOpenedTime;
	}

	public String getSrDueDate() {
		return srDueDate;
	}

	public void setSrDueDate(String srDueDate) {
		this.srDueDate = srDueDate;
	}

	public String getSrDueTime() {
		return srDueTime;
	}

	public void setSrDueTime(String srDueTime) {
		this.srDueTime = srDueTime;
	}

	public String getSrCloseDate() {
		return srCloseDate;
	}

	public void setSrCloseDate(String srCloseDate) {
		this.srCloseDate = srCloseDate;
	}

	public String getSrCloseTime() {
		return srCloseTime;
	}

	public void setSrCloseTime(String srCloseTime) {
		this.srCloseTime = srCloseTime;
	}

	public String getSrOwnerName() {
		return srOwnerName;
	}

	public void setSrOwnerName(String srOwnerName) {
		this.srOwnerName = srOwnerName;
	}


	public String getSrPriorityCd() {
		return srPriorityCd;
	}

	public void setSrPriorityCd(String srPriorityCd) {
		this.srPriorityCd = srPriorityCd;
	}

	public String getSrStatusCd() {
		return srStatusCd;
	}

	public void setSrStatusCd(String srStatusCd) {
		this.srStatusCd = srStatusCd;
	}

	public String getSrAssetId() {
		return srAssetId;
	}

	public void setSrAssetId(String srAssetId) {
		this.srAssetId = srAssetId;
	}

	public String getSrAssetCode() {
		return srAssetCode;
	}

	public void setSrAssetCode(String srAssetCode) {
		this.srAssetCode = srAssetCode;
	}

	public String getSrOwnerId() {
		return srOwnerId;
	}

	public void setSrOwnerId(String srOwnerId) {
		this.srOwnerId = srOwnerId;
	}

	public String getSrOwnerDeptCd() {
		return srOwnerDeptCd;
	}

	public void setSrOwnerDeptCd(String srOwnerDeptCd) {
		this.srOwnerDeptCd = srOwnerDeptCd;
	}

	public String getSrOwnerDeptName() {
		return srOwnerDeptName;
	}

	public void setSrOwnerDeptName(String srOwnerDeptName) {
		this.srOwnerDeptName = srOwnerDeptName;
	}

	public String getSrCustId() {
		return srCustId;
	}

	public void setSrCustId(String srCustId) {
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

	public String getSrCustomerName() {
		return srCustomerName;
	}

	public void setSrCustomerName(String srCustomerName) {
		this.srCustomerName = srCustomerName;
	}

	public String getSrContactName() {
		return srContactName;
	}

	public void setSrContactName(String srContactName) {
		this.srContactName = srContactName;
	}

	public String getSrContactId() {
		return srContactId;
	}

	public void setSrContactId(String srContactId) {
		this.srContactId = srContactId;
	}

	public String getSrDescription() {
		return srDescription;
	}

	public void setSrDescription(String srDescription) {
		this.srDescription = srDescription;
	}

	public String getSrRegId() {
		return srRegId;
	}

	public void setSrRegId(String srRegId) {
		this.srRegId = srRegId;
	}

	public String getSrRegDate() {
		return srRegDate;
	}

	public void setSrRegDate(String srRegDate) {
		this.srRegDate = srRegDate;
	}

	public String getSrChgId() {
		return srChgId;
	}

	public void setSrChgId(String srChgId) {
		this.srChgId = srChgId;
	}

	public String getSrChgDt() {
		return srChgDt;
	}

	public void setSrChgDt(String srChgDt) {
		this.srChgDt = srChgDt;
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

	public String getSrConsultingNumber() {
		return srConsultingNumber;
	}

	public void setSrConsultingNumber(String srConsultingNumber) {
		this.srConsultingNumber = srConsultingNumber;
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

	public String getSrNoteDescription() {
		return srNoteDescription;
	}

	public void setSrNoteDescription(String srNoteDescription) {
		this.srNoteDescription = srNoteDescription;
	}
	
}
