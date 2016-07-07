package com.locus.jlo.web.bean.modeljson;

import java.io.Serializable;

public class Select2DataModelBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5157976633673874575L;
	private String id;
	private String text;
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
