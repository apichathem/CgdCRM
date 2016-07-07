package com.locus.jlo.web.bean.modelbean;

import java.io.Serializable;

public class RoleMenuModelBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -739545868516494926L;
	private Integer menuId;
	private Integer roleId;

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
}
