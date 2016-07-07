package com.locus.jlo.web.bean.criteria;

import java.io.Serializable;
import java.util.List;

public class BaseCriteria implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9078427797431500867L;
	private List<String> ownerGroup;
	private String langCd;
	
	public List<String> getOwnerGroup() {
		return ownerGroup;
	}
	public void setOwnerGroup(List<String> ownerGroup) {
		if (ownerGroup != null && ownerGroup.size() == 0) {
			this.ownerGroup = null;
		} else {
			this.ownerGroup = ownerGroup;
		}
		
	}
	public String getLangCd() {
		return langCd;
	}
	public void setLangCd(String langCd) {
		this.langCd = langCd;
	}
}
