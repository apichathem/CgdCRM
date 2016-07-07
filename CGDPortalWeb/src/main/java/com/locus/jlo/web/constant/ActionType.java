package com.locus.jlo.web.constant;

public enum ActionType {
	READ("READ"), ADD("ADD"), EDIT("EDIT"), DELETE("DELETE"), APPROVE("APPROVE");
	
	private String actionCode;
	 
	private ActionType(String s) {
		actionCode = s;
	}
 
	public String getActionCode() {
		return actionCode;
	}
}
