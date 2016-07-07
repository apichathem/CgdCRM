package com.locus.jlo.web.bean.modelbean;

import java.io.Serializable;
import java.util.List;

public class UserLoginInfoModelBean extends BaseModelBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4299176246314524661L;

	private String departmentCd;
	private String departmentName;
	private String roleId;
	private String roleName;
	private Integer userId;
	private String loginId;
	private String password;
	private String firstName;
	private String lastName;
	private String useYn;
	private String imageFile;
	private String positionCd;
	private String positionName;
	private String agentNo;
	private Integer agentId;
	private String email;
	private String mobileNo;
	private String userPic;
	private Integer reportTo;
	private String reportName;
	
	private List<String> departmentGroup;
	private List<String> myTeamUserId;

	public String getMyTeamUserIdText() {
		StringBuffer sb = new StringBuffer();
		if (myTeamUserId != null) {

			for (String userId : myTeamUserId) {
				sb.append("'" + userId + "',");
			}
			return sb.substring(0, sb.length() - 1);
		} else {
			return "";
		}

	}

	public String getDepartmentGroupText() {
		StringBuffer sb = new StringBuffer();
		if (departmentGroup != null) {

			for (String dept : departmentGroup) {
				sb.append("'" + dept + "',");
			}
			return sb.substring(0, sb.length() - 1);
		} else {
			return "";
		}

	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<String> getDepartmentGroup() {
		return departmentGroup;
	}

	public void setDepartmentGroup(List<String> departmentGroup) {
		this.departmentGroup = departmentGroup;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getImageFile() {
		return imageFile;
	}

	public void setImageFile(String imageFile) {
		this.imageFile = imageFile;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getPositionCd() {
		return positionCd;
	}

	public void setPositionCd(String positionCd) {
		this.positionCd = positionCd;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public List<String> getMyTeamUserId() {
		return myTeamUserId;
	}

	public void setMyTeamUserId(List<String> myTeamUserId) {
		this.myTeamUserId = myTeamUserId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public String getAgentNo() {
		return agentNo;
	}

	public void setAgentNo(String agentNo) {
		this.agentNo = agentNo;
	}

	public Integer getAgentId() {
		return agentId;
	}

	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getUserPic() {
		return userPic;
	}

	public void setUserPic(String userPic) {
		this.userPic = userPic;
	}

	public Integer getReportTo() {
		return reportTo;
	}

	public void setReportTo(Integer reportTo) {
		this.reportTo = reportTo;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public String getDepartmentCd() {
		return departmentCd;
	}

	public void setDepartmentCd(String departmentCd) {
		this.departmentCd = departmentCd;
	}
}
