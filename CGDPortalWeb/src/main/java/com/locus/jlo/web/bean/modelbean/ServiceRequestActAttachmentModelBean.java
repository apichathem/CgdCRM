package com.locus.jlo.web.bean.modelbean;

import java.io.Serializable;

public class ServiceRequestActAttachmentModelBean extends BaseModelBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6140303413218919290L;

	public ServiceRequestActAttachmentModelBean() {
		// TODO Auto-generated constructor stub
	}

	private Integer no;
	private String relAttId;
	private String attId;
	private String attDocumentName;
	private String attKnowledgeBase;
	private String attSendDocFlg;
	private String attDescription;
	private String attRefDocType;

	/**
	 * @return the attDocumentName
	 */
	public String getAttDocumentName() {
		return attDocumentName;
	}

	/**
	 * @param attDocumentName
	 *            the attDocumentName to set
	 */
	public void setAttDocumentName(String attDocumentName) {
		this.attDocumentName = attDocumentName;
	}

	/**
	 * @return the attKnowledgeBase
	 */
	public String getAttKnowledgeBase() {
		return attKnowledgeBase;
	}

	/**
	 * @param attKnowledgeBase
	 *            the attKnowledgeBase to set
	 */
	public void setAttKnowledgeBase(String attKnowledgeBase) {
		this.attKnowledgeBase = attKnowledgeBase;
	}

	/**
	 * @return the attSendDocFlg
	 */
	public String getAttSendDocFlg() {
		return attSendDocFlg;
	}

	/**
	 * @param attSendDocFlg
	 *            the attSendDocFlg to set
	 */
	public void setAttSendDocFlg(String attSendDocFlg) {
		this.attSendDocFlg = attSendDocFlg;
	}

	/**
	 * @return the attDescription
	 */
	public String getAttDescription() {
		return attDescription;
	}

	/**
	 * @param attDescription
	 *            the attDescription to set
	 */
	public void setAttDescription(String attDescription) {
		this.attDescription = attDescription;
	}

	/**
	 * @return the attRefDocType
	 */
	public String getAttRefDocType() {
		return attRefDocType;
	}

	/**
	 * @param attRefDocType
	 *            the attRefDocType to set
	 */
	public void setAttRefDocType(String attRefDocType) {
		this.attRefDocType = attRefDocType;
	}

	/**
	 * @return the attId
	 */
	public String getAttId() {
		return attId;
	}

	/**
	 * @param attId
	 *            the attId to set
	 */
	public void setAttId(String attId) {
		this.attId = attId;
	}

	/**
	 * @return the no
	 */
	public Integer getNo() {
		return no;
	}

	/**
	 * @param no
	 *            the no to set
	 */
	public void setNo(Integer no) {
		this.no = no;
	}

	/**
	 * @return the relAttId
	 */
	public String getRelAttId() {
		return relAttId;
	}

	/**
	 * @param relAttId
	 *            the relAttId to set
	 */
	public void setRelAttId(String relAttId) {
		this.relAttId = relAttId;
	}

}
