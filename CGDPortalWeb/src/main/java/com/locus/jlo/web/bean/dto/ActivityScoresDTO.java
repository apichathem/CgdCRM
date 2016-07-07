package com.locus.jlo.web.bean.dto;


@SuppressWarnings("serial")
public class ActivityScoresDTO extends BaseDTO {

	/**
	 * 
	 *     ACT_ID       	int(11) AUTO_INCREMENT NOT NULL,
    ACT_SEQ      	int(11) NULL,
    ACT_NAME     	varchar(100) NULL,
    ACT_ACTION_CODE   	varchar(20) NULL,
    ACT_ENABLED  	varchar(1) NULL,
    ACT_SCORES    	int(11) NULL,
    ACT_DESC	varchar(2000) NULL,
    REG_ID        	varchar(20) NULL,
    REG_DT        	datetime NULL,
    CHG_ID        	varchar(20) NULL,
    CHG_DT        	datetime NULL,
    
	 */
	private Integer actId;
	private String actName;
	private String actActionCode;
	private Integer actSeq;
	private String actEnabled;
	public Integer actScores;
	public String actDesc;
	public Integer getActId() {
		return actId;
	}
	public void setActId(Integer actId) {
		this.actId = actId;
	}
	public String getActName() {
		return actName;
	}
	public void setActName(String actName) {
		this.actName = actName;
	}
	public String getActActionCode() {
		return actActionCode;
	}
	public void setActActionCode(String actActionCode) {
		this.actActionCode = actActionCode;
	}
	public Integer getActSeq() {
		return actSeq;
	}
	public void setActSeq(Integer actSeq) {
		this.actSeq = actSeq;
	}
	public String getActEnabled() {
		return actEnabled;
	}
	public void setActEnabled(String actEnabled) {
		this.actEnabled = actEnabled;
	}
	public Integer getActScores() {
		return actScores;
	}
	public void setActScores(Integer actScores) {
		this.actScores = actScores;
	}
	public String getActDesc() {
		return actDesc;
	}
	public void setActDesc(String actDesc) {
		this.actDesc = actDesc;
	}
	
	
}
