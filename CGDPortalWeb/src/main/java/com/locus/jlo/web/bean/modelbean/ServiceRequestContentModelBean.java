/**
 * 
 */
package com.locus.jlo.web.bean.modelbean;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Mr.BoonOom
 * 
 */
public class ServiceRequestContentModelBean extends BaseModelBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8346236886831671798L;
	
	private String srNumber;
	private String srContentId;
	private String srContentNumber;
	private String contentTitle;
	private String contentCat1Id;
	private String contentCat2Id;
	private String contentCat3Id;
	private String contentCat4Id;
	private String contentCat5Id;
	
	private String contentCat1Name;
	private String contentCat2Name;
	private String contentCat3Name;
	private String contentCat4Name;
	private String contentCat5Name;
	
	private Integer regId;
	private Date regDt;
	private Integer chgId;
	private Date chgDt;
	private String chgName;

	private String createByName;
	private String updateByName;
	private String createDateTime;
	private String updateDateTime;
	
	
	private String contentFaqKbType;
	
	public String getSrNumber() {
		return srNumber;
	}
	public void setSrNumber(String srNumber) {
		this.srNumber = srNumber;
	}
	public String getSrContentId() {
		return srContentId;
	}
	public void setSrContentId(String srContentId) {
		this.srContentId = srContentId;
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
	public String getSrContentNumber() {
		return srContentNumber;
	}
	public void setSrContentNumber(String srContentNumber) {
		this.srContentNumber = srContentNumber;
	}
	public String getContentTitle() {
		return contentTitle;
	}
	public void setContentTitle(String contentTitle) {
		this.contentTitle = contentTitle;
	}
	public String getContentCat1Id() {
		return contentCat1Id;
	}
	public void setContentCat1Id(String contentCat1Id) {
		this.contentCat1Id = contentCat1Id;
	}
	public String getContentCat2Id() {
		return contentCat2Id;
	}
	public void setContentCat2Id(String contentCat2Id) {
		this.contentCat2Id = contentCat2Id;
	}
	public String getContentCat3Id() {
		return contentCat3Id;
	}
	public void setContentCat3Id(String contentCat3Id) {
		this.contentCat3Id = contentCat3Id;
	}
	public String getContentCat4Id() {
		return contentCat4Id;
	}
	public void setContentCat4Id(String contentCat4Id) {
		this.contentCat4Id = contentCat4Id;
	}
	public String getContentCat5Id() {
		return contentCat5Id;
	}
	public void setContentCat5Id(String contentCat5Id) {
		this.contentCat5Id = contentCat5Id;
	}
	public String getContentCat1Name() {
		return contentCat1Name;
	}
	public void setContentCat1Name(String contentCat1Name) {
		this.contentCat1Name = contentCat1Name;
	}
	public String getContentCat2Name() {
		return contentCat2Name;
	}
	public void setContentCat2Name(String contentCat2Name) {
		this.contentCat2Name = contentCat2Name;
	}
	public String getContentCat3Name() {
		return contentCat3Name;
	}
	public void setContentCat3Name(String contentCat3Name) {
		this.contentCat3Name = contentCat3Name;
	}
	public String getContentCat4Name() {
		return contentCat4Name;
	}
	public void setContentCat4Name(String contentCat4Name) {
		this.contentCat4Name = contentCat4Name;
	}
	public String getContentCat5Name() {
		return contentCat5Name;
	}
	public void setContentCat5Name(String contentCat5Name) {
		this.contentCat5Name = contentCat5Name;
	}
	public String getContentFaqKbType() {
		return contentFaqKbType;
	}
	public void setContentFaqKbType(String contentFaqKbType) {
		this.contentFaqKbType = contentFaqKbType;
	}
	

}
