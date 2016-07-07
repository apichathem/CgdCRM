package com.locus.jlo.web.bean.modelbean;

import java.io.Serializable;

public class ActivitytAttachmentModelBean extends BaseModelBean implements Serializable {

	private static final long serialVersionUID = 4648943628467588L;

	private Integer no;
	private Integer relAttId;
	private Integer attId;
	private String actDocumentName;
	private String actKnowledgeBase;
	private String actSendDocFlg;
	private String actDescription;
	private String actRefDocType;

	public ActivitytAttachmentModelBean() {
		// TODO Auto-generated constructor stub
	}

	public Integer getNo() {
		return no;
	}

	public void setNo(Integer no) {
		this.no = no;
	}

	public Integer getAttId() {
		return attId;
	}

	public void setAttId(Integer attId) {
		this.attId = attId;
	}

	public String getActDocumentName() {
		return actDocumentName;
	}

	public void setActDocumentName(String actDocumentName) {
		this.actDocumentName = actDocumentName;
	}

	public String getActKnowledgeBase() {
		return actKnowledgeBase;
	}

	public void setActKnowledgeBase(String actKnowledgeBase) {
		this.actKnowledgeBase = actKnowledgeBase;
	}

	public String getActSendDocFlg() {
		return actSendDocFlg;
	}

	public void setActSendDocFlg(String actSendDocFlg) {
		this.actSendDocFlg = actSendDocFlg;
	}

	public Integer getRelAttId() {
		return relAttId;
	}

	public void setRelAttId(Integer relAttId) {
		this.relAttId = relAttId;
	}

	public String getActDescription() {
		return actDescription;
	}

	public void setActDescription(String actDescription) {
		this.actDescription = actDescription;
	}

	public String getActRefDocType() {
		return actRefDocType;
	}

	public void setActRefDocType(String actRefDocType) {
		this.actRefDocType = actRefDocType;
	}
}
