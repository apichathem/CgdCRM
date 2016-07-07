package com.locus.jlo.web.bean.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class UserInfoDTO extends BaseDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -372730934158495568L;

	private Integer userId;
	private String loginId;
	private String password;
	private String useYn;
	private String roleId;
	private String roleName;
	private Integer reportTo;
	private String reportName;
	private String departmentCd;
	private String departmentName;
	private Integer agentId;
	private String agentNo;
	private String firstName;
	private String lastName;
	private String socialId;
	private String email;
	private String mobileNo;
	private String workNo;
	private String extNo;
	private String userPic;
	private String positionCd;
	private String positionName;
	private String ctiId;
	private String ctiPassword;
	private String ctiStation;
	private Date regDt;
	private Date chgDt;
	private String imageFile;
	private String tokenCode;
	private String tokenExpiredDatetime;

	private String empNo;
	private String deptCode;
	private String deptName;
	private List<String> myTeamUserId;
	
	public Integer getUserId() {
		return userId;
	}

	public String getImageFile() {
		return imageFile;
	}

	public void setImageFile(String imageFile) {
		this.imageFile = imageFile;
	}

	public String getCtiId() {
		return ctiId;
	}

	public void setCtiId(String ctiId) {
		this.ctiId = ctiId;
	}

	public String getCtiPassword() {
		return ctiPassword;
	}

	public void setCtiPassword(String ctiPassword) {
		this.ctiPassword = ctiPassword;
	}

	public String getCtiStation() {
		return ctiStation;
	}

	public void setCtiStation(String ctiStation) {
		this.ctiStation = ctiStation;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
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

	public String getSocialId() {
		return socialId;
	}

	public void setSocialId(String socialId) {
		this.socialId = socialId;
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

	public String getWorkNo() {
		return workNo;
	}

	public void setWorkNo(String workNo) {
		this.workNo = workNo;
	}

	public String getExtNo() {
		return extNo;
	}

	public void setExtNo(String extNo) {
		this.extNo = extNo;
	}

	/*
	 * public MultipartFile getImgFile() { return imgFile; } public void
	 * setImgFile(MultipartFile imgFile) { this.imgFile = imgFile; }
	 */
	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
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

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getRegDt() {
		return regDt;
	}

	public void setRegDt(Date regDt) {
		this.regDt = regDt;
	}

	public Date getChgDt() {
		return chgDt;
	}

	public void setChgDt(Date chgDt) {
		this.chgDt = chgDt;
	}

	public String getTokenCode() {
		return tokenCode;
	}

	public void setTokenCode(String tokenCode) {
		this.tokenCode = tokenCode;
	}

	public String getTokenExpiredDatetime() {
		return tokenExpiredDatetime;
	}

	public void setTokenExpiredDatetime(String tokenExpiredDatetime) {
		this.tokenExpiredDatetime = tokenExpiredDatetime;
	}

	public Integer getAgentId() {
		return agentId;
	}

	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}

	public String getAgentNo() {
		return agentNo;
	}

	public void setAgentNo(String agentNo) {
		this.agentNo = agentNo;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getDepartmentCd() {
		return departmentCd;
	}

	public void setDepartmentCd(String departmentCd) {
		this.departmentCd = departmentCd;
	}

	public String getPositionCd() {
		return positionCd;
	}

	public void setPositionCd(String positionCd) {
		this.positionCd = positionCd;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public List<String> getMyTeamUserId() {
		return myTeamUserId;
	}

	public void setMyTeamUserId(List<String> myTeamUserId) {
		this.myTeamUserId = myTeamUserId;
	}

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
}
