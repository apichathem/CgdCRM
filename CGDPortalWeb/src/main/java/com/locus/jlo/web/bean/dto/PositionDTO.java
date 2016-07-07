package com.locus.jlo.web.bean.dto;

import java.io.Serializable;

public class PositionDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4495031247531135891L;
	private String positionId;
	private String positionName;
	private String orgCd;
	private String proPositionFlag;
	private String ctiEmailFlag;
	private String ctiChatFlag;
	private String canEmailFlag;
	
	public String getPositionId() {
		return positionId;
	}
	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	public String getOrgCd() {
		return orgCd;
	}
	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}
	public String getProPositionFlag() {
		return proPositionFlag;
	}
	public void setProPositionFlag(String proPositionFlag) {
		this.proPositionFlag = proPositionFlag;
	}
	public String getCtiEmailFlag() {
		return ctiEmailFlag;
	}
	public void setCtiEmailFlag(String ctiEmailFlag) {
		this.ctiEmailFlag = ctiEmailFlag;
	}
	public String getCtiChatFlag() {
		return ctiChatFlag;
	}
	public void setCtiChatFlag(String ctiChatFlag) {
		this.ctiChatFlag = ctiChatFlag;
	}
	public String getCanEmailFlag() {
		return canEmailFlag;
	}
	public void setCanEmailFlag(String canEmailFlag) {
		this.canEmailFlag = canEmailFlag;
	}
	
}
