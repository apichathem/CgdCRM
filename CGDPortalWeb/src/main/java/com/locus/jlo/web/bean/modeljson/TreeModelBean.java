package com.locus.jlo.web.bean.modeljson;

import java.io.Serializable;
import java.util.Map;

public class TreeModelBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1379712740048126501L;
	
	public static final String STATE_OPENED = "opened";
	public static final String STATE_DISABLED = "disabled";
	public static final String STATE_SELECTED = "selected";
	public static final String ATTR_HREF = "href";
	
	private String id;
	private String parent;
	private String text;
	private String icon;
	private Boolean children;
	private String type;
	private Map<String, Boolean> state;
	private Map<String, String> a_attr;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Map<String, Boolean> getState() {
		return state;
	}
	public void setState(Map<String, Boolean> state) {
		this.state = state;
	}
	public Map<String, String> getA_attr() {
		return a_attr;
	}
	public void setA_attr(Map<String, String> a_attr) {
		this.a_attr = a_attr;
	}
	public Boolean getChildren() {
		return children;
	}
	public void setChildren(Boolean children) {
		this.children = children;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
