
package com.locus.jlo.web.bean.dto;

import java.io.Serializable;

public class PermissionDTO extends BaseDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String permissionCode;

	public String getPermissionCode() {
		return permissionCode;
	}

	public void setPermissionCode(String permissionCode) {
		this.permissionCode = permissionCode;
	}
}