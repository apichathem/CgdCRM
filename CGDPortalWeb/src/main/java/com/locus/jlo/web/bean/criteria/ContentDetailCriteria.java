package com.locus.jlo.web.bean.criteria;

import com.locus.common.utils.StringUtils;

public class ContentDetailCriteria extends BaseCriteria {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5363964335253646829L;
	private String contentNumber;
	private String contentCat1Id;
	private String contentCat2Id;
	private String contentCat3Id;
	private String contentCat4Id;
	private String contentCat5Id;
	private String keyword;
	
	public String getContentNumber() {
		return (StringUtils.isEmpty(contentNumber) ? null : contentNumber);
	}
	public void setContentNumber(String contentNumber) {
		this.contentNumber = contentNumber;
	}
	public String getContentCat1Id() {
		return (StringUtils.isEmpty(contentCat1Id) ? null : contentCat1Id);
	}
	public void setContentCat1Id(String contentCat1Id) {
		this.contentCat1Id = contentCat1Id;
	}
	public String getContentCat2Id() {
		return (StringUtils.isEmpty(contentCat2Id) ? null : contentCat2Id);
	}
	
	public void setContentCat2Id(String contentCat2Id) {
		this.contentCat2Id = contentCat2Id;
	}
	public String getContentCat3Id() {
		return (StringUtils.isEmpty(contentCat3Id) ? null : contentCat3Id);
	}
	public void setContentCat3Id(String contentCat3Id) {
		this.contentCat3Id = contentCat3Id;
	}
	public String getContentCat4Id() {
		return (StringUtils.isEmpty(contentCat4Id) ? null : contentCat4Id);
	}
	public void setContentCat4Id(String contentCat4Id) {
		this.contentCat4Id = contentCat4Id;
	}
	public String getContentCat5Id() {
		return (StringUtils.isEmpty(contentCat5Id) ? null : contentCat5Id);
	}
	public void setContentCat5Id(String contentCat5Id) {
		this.contentCat5Id = contentCat5Id;
	}
	public String getKeyword() {
		return (StringUtils.isEmpty(keyword) ? null : keyword);
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
}
