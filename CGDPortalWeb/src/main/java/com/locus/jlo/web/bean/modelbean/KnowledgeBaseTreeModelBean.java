package com.locus.jlo.web.bean.modelbean;

import java.io.Serializable;
import java.util.Map;

public class KnowledgeBaseTreeModelBean implements Serializable {

	// // Alternative format of the node (id & parent are required)
	// {
	// id : "string" // required
	// parent : "string" // required
	// text : "string" // node text
	// icon : "string" // string for custom
	// children : boolean // is children
	// state : {
	// opened : boolean // is the node open
	// disabled : boolean // is the node disabled
	// selected : boolean // is the node selected
	// },
	// li_attr : {} // attributes for the generated LI node
	// a_attr : {} // attributes for the generated A node
	// }

	private static final long serialVersionUID = 8880505593907957985L;
	private String id;
	private String parent;
	private String text;
	private String icon;
	private Boolean children;
	private Map<String, Boolean> state;
	private Map<String, Object> a_attr;
	private Map<String, Object> li_attr;

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

	public Map<String, Object> getA_attr() {
		return a_attr;
	}

	public void setA_attr(Map<String, Object> a_attr) {
		this.a_attr = a_attr;
	}

	public void setState(Map<String, Boolean> state) {
		this.state = state;
	}

	public Map<String, Boolean> getState() {
		return state;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getIcon() {
		return icon;
	}

	public void setChildren(Boolean children) {
		this.children = children;
	}

	public Boolean getChildren() {
		return children;
	}

	public void setLi_attr(Map<String, Object> li_attr) {
		this.li_attr = li_attr;
	}

	public Map<String, Object> getLi_attr() {
		return li_attr;
	}

}
