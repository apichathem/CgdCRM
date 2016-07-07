package com.locus.jlo.web.bean.modeljson;

import java.io.Serializable;

public class MultiSelectBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4315036066215092200L;
	
	private String text;
	private String value;
	private Boolean selected;
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Boolean getSelected() {
		return selected;
	}
	public void setSelected(Boolean selected) {
		this.selected = selected;
	}
	
}
