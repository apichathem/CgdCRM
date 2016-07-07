package com.locus.jlo.web.constant;

public enum PrivilegeAccess {
	ALL("01"), OWNER("02"), NONE("03");
	
	private String accessCode;

	private PrivilegeAccess(String accessCode) {
		this.accessCode = accessCode;
	}
	
	public String getAccessCode() {
		return accessCode;
	}

	
}
