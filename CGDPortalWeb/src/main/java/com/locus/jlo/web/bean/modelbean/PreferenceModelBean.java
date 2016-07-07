package com.locus.jlo.web.bean.modelbean;

public class PreferenceModelBean extends BaseModelBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6589818736536819432L;
	private String prefNameCriteria;
	private String prefValueCriteria;

	// Detail
	private Integer prefId;
	private String prefName;
	private String prefValue;
	private String etc1;
	private String etc2;
	private String etc3;
	private String descp;
	private String enabledFlag;
	private String createBy;
	private String createDate;
	private String updateBy;
	private String updateDate;

	public String getPrefNameCriteria() {
		return prefNameCriteria;
	}

	public void setPrefNameCriteria(String prefNameCriteria) {
		this.prefNameCriteria = prefNameCriteria;
	}

	public String getPrefValueCriteria() {
		return prefValueCriteria;
	}

	public void setPrefValueCriteria(String prefValueCriteria) {
		this.prefValueCriteria = prefValueCriteria;
	}

	public Integer getPrefId() {
		return prefId;
	}

	public void setPrefId(Integer prefId) {
		this.prefId = prefId;
	}

	public String getPrefName() {
		return prefName;
	}

	public void setPrefName(String prefName) {
		this.prefName = prefName;
	}

	public String getPrefValue() {
		return prefValue;
	}

	public void setPrefValue(String prefValue) {
		this.prefValue = prefValue;
	}

	public String getEtc1() {
		return etc1;
	}

	public void setEtc1(String etc1) {
		this.etc1 = etc1;
	}

	public String getEtc2() {
		return etc2;
	}

	public void setEtc2(String etc2) {
		this.etc2 = etc2;
	}

	public String getEtc3() {
		return etc3;
	}

	public void setEtc3(String etc3) {
		this.etc3 = etc3;
	}

	public String getDescp() {
		return descp;
	}

	public void setDescp(String descp) {
		this.descp = descp;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getEnabledFlag() {
		return enabledFlag;
	}

	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}

}
