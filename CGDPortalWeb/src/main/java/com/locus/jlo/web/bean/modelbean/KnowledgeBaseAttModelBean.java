package com.locus.jlo.web.bean.modelbean;


public class KnowledgeBaseAttModelBean extends BaseModelBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4794812051849100999L;
	private String contentId;
	private String contentAttId;
	private String attId;
	private String attMode;
	private String fileurl;
	private String attFileName;
	private String attTitle;
	private String attDescp;
	private String mainFlag;
	
	private String attRegBy;
	private String attRegDt;
	private String attChgBy;
	private String attChgDt;
	
	private Integer regId;
	
	public String getContentId() {
		return contentId;
	}
	public void setContentId(String contentId) {
		this.contentId = contentId;
	}
	public String getContentAttId() {
		return contentAttId;
	}
	public void setContentAttId(String contentAttId) {
		this.contentAttId = contentAttId;
	}
	public String getAttId() {
		return attId;
	}
	public void setAttId(String attId) {
		this.attId = attId;
	}
	public String getFileurl() {
		return fileurl;
	}
	public void setFileurl(String fileurl) {
		this.fileurl = fileurl;
	}
	public String getAttFileName() {
		return attFileName;
	}
	public void setAttFileName(String attFileName) {
		this.attFileName = attFileName;
	}
	public String getAttTitle() {
		return attTitle;
	}
	public void setAttTitle(String attTitle) {
		this.attTitle = attTitle;
	}
	public String getAttDescp() {
		return attDescp;
	}
	public void setAttDescp(String attDescp) {
		this.attDescp = attDescp;
	}
	public String getMainFlag() {
		return mainFlag;
	}
	public void setMainFlag(String mainFlag) {
		this.mainFlag = mainFlag;
	}
	public String getAttRegBy() {
		return attRegBy;
	}
	public void setAttRegBy(String attRegBy) {
		this.attRegBy = attRegBy;
	}
	public String getAttRegDt() {
		return attRegDt;
	}
	public void setAttRegDt(String attRegDt) {
		this.attRegDt = attRegDt;
	}
	public String getAttChgBy() {
		return attChgBy;
	}
	public void setAttChgBy(String attChgBy) {
		this.attChgBy = attChgBy;
	}
	public String getAttChgDt() {
		return attChgDt;
	}
	public void setAttChgDt(String attChgDt) {
		this.attChgDt = attChgDt;
	}
	public String getAttMode() {
		return attMode;
	}
	public void setAttMode(String attMode) {
		this.attMode = attMode;
	}
	public Integer getRegId() {
		return regId;
	}
	public void setRegId(Integer regId) {
		this.regId = regId;
	}
	
}
