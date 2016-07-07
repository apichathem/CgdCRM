package com.locus.jlo.web.bean.modelbean;

public class RoleModelBean extends BaseModelBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3074336829166872380L;
	private Integer roleId;
	private String roleName;
	private String descp;
	private Integer parentRoleId;
	private String parentRoleName;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDescp() {
		return descp;
	}

	public void setDescp(String descp) {
		this.descp = descp;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getParentRoleId() {
		return parentRoleId;
	}

	public void setParentRoleId(Integer parentRoleId) {
		this.parentRoleId = parentRoleId;
	}

	public String getParentRoleName() {
		return parentRoleName;
	}

	public void setParentRoleName(String parentRoleName) {
		this.parentRoleName = parentRoleName;
	}

}
