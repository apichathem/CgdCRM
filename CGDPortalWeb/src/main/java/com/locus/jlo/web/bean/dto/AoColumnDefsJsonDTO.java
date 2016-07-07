package com.locus.jlo.web.bean.dto;

import java.io.Serializable;

public class AoColumnDefsJsonDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8310430158978790342L;
	private String sTitle;
	private int aTargets;
	private String sClass;
	private Boolean bVisible;
	
	public String getsTitle() {
		return sTitle;
	}
	public void setsTitle(String sTitle) {
		this.sTitle = sTitle;
	}
	public int getaTargets() {
		return aTargets;
	}
	public void setaTargets(int aTargets) {
		this.aTargets = aTargets;
	}
	public String getsClass() {
		return sClass;
	}
	public void setsClass(String sClass) {
		this.sClass = sClass;
	}
	public Boolean getbVisible() {
		return bVisible;
	}
	public void setbVisible(Boolean bVisible) {
		this.bVisible = bVisible;
	}
}
