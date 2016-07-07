package com.locus.jlo.web.bean.modelbean;

public class CodebookModelBean extends BaseModelBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4840792078547098455L;

	private String codeId;
	private String codeType;
	private String codeNameEn;
	private String codeNameTh;
	private String parentId;
	private String parentType;
	private String etc1;
	private String etc2;
	private String etc3;
	private String etc4;
	private String etc5;

	private String id;
	private String useYn;
	private String sortKey;

	private transient String parentName;
	private transient String codeName;
	private transient String langCd;
	private transient String channel;
	private transient String centerCd;
	private transient String selectedCodeType;
	private transient String selectedCodeId;
	private transient String regId;
	private transient String regDtText;
	private transient String chgId;
	private transient String chgDtText;
	private transient String[] languageCode;
	private transient String[] codenameByLang;

	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public String getParentType() {
		return parentType;
	}

	public void setParentType(String parentType) {
		this.parentType = parentType;
	}

	/*
	 * public String getParentCodeType() { return parentCodeType; } public void
	 * setParentCodeType(String parentCodeType) { this.parentCodeType =
	 * parentCodeType; } public String getParentCodeName() { return
	 * parentCodeName; } public void setParentCodeName(String parentCodeName) {
	 * this.parentCodeName = parentCodeName; } public String getParentCodeId() {
	 * return parentCodeId; } public void setParentCodeId(String parentCodeId) {
	 * this.parentCodeId = parentCodeId; }
	 */
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

	public String getEtc4() {
		return etc4;
	}

	public void setEtc4(String etc4) {
		this.etc4 = etc4;
	}

	public String getEtc5() {
		return etc5;
	}

	public void setEtc5(String etc5) {
		this.etc5 = etc5;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public String getSelectedCodeType() {
		return selectedCodeType;
	}

	public void setSelectedCodeType(String selectedCodeType) {
		this.selectedCodeType = selectedCodeType;
	}

	public String getSelectedCodeId() {
		return selectedCodeId;
	}

	public void setSelectedCodeId(String selectedCodeId) {
		this.selectedCodeId = selectedCodeId;
	}

	/*
	 * public String getChildCodeType() { return childCodeType; } public void
	 * setChildCodeType(String childCodeType) { this.childCodeType =
	 * childCodeType; } public String getChildCodeName() { return childCodeName;
	 * } public void setChildCodeName(String childCodeName) { this.childCodeName
	 * = childCodeName; } public String getChildCodeId() { return childCodeId; }
	 * public void setChildCodeId(String childCodeId) { this.childCodeId =
	 * childCodeId; }
	 */

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getCodeId() {
		return codeId;
	}

	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}

	public String getCodeNameEn() {
		return codeNameEn;
	}

	public void setCodeNameEn(String codeNameEn) {
		this.codeNameEn = codeNameEn;
	}

	public String getCodeNameTh() {
		return codeNameTh;
	}

	public void setCodeNameTh(String codeNameTh) {
		this.codeNameTh = codeNameTh;
	}

	public String getCenterCd() {
		return centerCd;
	}

	public void setCenterCd(String centerCd) {
		this.centerCd = centerCd;
	}

	public String getSortKey() {
		return sortKey;
	}

	public void setSortKey(String sortKey) {
		this.sortKey = sortKey;
	}

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public String getChgId() {
		return chgId;
	}

	public void setChgId(String chgId) {
		this.chgId = chgId;
	}

	public String getRegDtText() {
		return regDtText;
	}

	public void setRegDtText(String regDtText) {
		this.regDtText = regDtText;
	}

	public String getChgDtText() {
		return chgDtText;
	}

	public void setChgDtText(String chgDtText) {
		this.chgDtText = chgDtText;
	}

	public String[] getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(String[] languageCode) {
		this.languageCode = languageCode;
	}

	public String[] getCodenameByLang() {
		return codenameByLang;
	}

	public void setCodenameByLang(String[] codenameByLang) {
		this.codenameByLang = codenameByLang;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLangCd() {
		return langCd;
	}

	public void setLangCd(String langCd) {
		this.langCd = langCd;
	}

}
