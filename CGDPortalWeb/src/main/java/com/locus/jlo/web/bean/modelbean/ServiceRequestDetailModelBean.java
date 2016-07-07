package com.locus.jlo.web.bean.modelbean;

import java.io.Serializable;

/**
 * 
 * @author Mr.BoonOom
 * 
 */
public class ServiceRequestDetailModelBean extends BaseModelBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7986216699756394033L;

	public ServiceRequestDetailModelBean() {
		// TODO Auto-generated constructor stub
	}

	private String srDetailId;
	private String srNumber;
	private String answerDt;
	private String statusCd;
	private String ownerId;
	private String ownerDeptCd;
	private String content;
	private String regId;
	private String regDt;
	private String chgId;
	private String chgDt;

	// other join
	private String regName;
	private String chgName;
	private String createByName;
	private String createDateTime;
	private String updateByName;
	private String updateDateTime;

	private String refStatusCd;

	/**
	 * @return the srDetailId
	 */
	public String getSrDetailId() {
		return srDetailId;
	}

	/**
	 * @param srDetailId
	 *            the srDetailId to set
	 */
	public void setSrDetailId(String srDetailId) {
		this.srDetailId = srDetailId;
	}

	/**
	 * @return the srNumber
	 */
	public String getSrNumber() {
		return srNumber;
	}

	/**
	 * @param srNumber
	 *            the srNumber to set
	 */
	public void setSrNumber(String srNumber) {
		this.srNumber = srNumber;
	}

	/**
	 * @return the answerDt
	 */
	public String getAnswerDt() {
		return answerDt;
	}

	/**
	 * @param answerDt
	 *            the answerDt to set
	 */
	public void setAnswerDt(String answerDt) {
		this.answerDt = answerDt;
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
	 * @return the ownerDeptCd
	 */
	public String getOwnerDeptCd() {
		return ownerDeptCd;
	}

	/**
	 * @param ownerDeptCd
	 *            the ownerDeptCd to set
	 */
	public void setOwnerDeptCd(String ownerDeptCd) {
		this.ownerDeptCd = ownerDeptCd;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
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
	 * @return the createByName
	 */
	public String getCreateByName() {
		return createByName;
	}

	/**
	 * @param createByName
	 *            the createByName to set
	 */
	public void setCreateByName(String createByName) {
		this.createByName = createByName;
	}

	/**
	 * @return the createDateTime
	 */
	public String getCreateDateTime() {
		return createDateTime;
	}

	/**
	 * @param createDateTime
	 *            the createDateTime to set
	 */
	public void setCreateDateTime(String createDateTime) {
		this.createDateTime = createDateTime;
	}

	/**
	 * @return the updateByName
	 */
	public String getUpdateByName() {
		return updateByName;
	}

	/**
	 * @param updateByName
	 *            the updateByName to set
	 */
	public void setUpdateByName(String updateByName) {
		this.updateByName = updateByName;
	}

	/**
	 * @return the updateDateTime
	 */
	public String getUpdateDateTime() {
		return updateDateTime;
	}

	/**
	 * @param updateDateTime
	 *            the updateDateTime to set
	 */
	public void setUpdateDateTime(String updateDateTime) {
		this.updateDateTime = updateDateTime;
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
