package com.locus.jlo.web.bean.dto;

import java.io.Serializable;

/**
 * 
 * @author Mr.BoonOom
 *
 */
public class ComboDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3278512406062209882L;
 
	private String label;
	private String value;
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
