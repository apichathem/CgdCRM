package com.locus.jlo.web.bean.dto;

import java.io.Serializable;
import java.util.Date;

public class CustomerDTO extends BaseDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5914347186134704810L;

	private Long custId;
	private String custName;
	private String custTypeName;
	private String custTypeCd;
	private String industryName;
	private String email;
	private String ownerName;
	private String statusName;

	// Individual customer detail
	private Long indId;
	private String typeCd;
	private String custCode;
	private String titleCd;
	private String firstName;
	private String lastName;
	private String midName;
	private String statusCd;
	private String contactChannelCd;
	private String homePhone;
	private String mobileNo;
	private String workPhone;
	private String workPhoneFormat;
	
	private String homePhoneExt;
	private String mobileNoExt;
	private String workPhoneExt;
	
	private Integer ownerId;
	private String comment;
	private String addr1;
	private String addr2;
	private String subArea;
	private String area;
	private String province;
	private String postalCode;
	private String countryCd;
	private String notCallYn;
	private String notSmsYn;
	private String notEmailYn;
	private String notMailYn;
	private String genderCd;
	private String maritalStatusCd;
	private String raceCd;
	private String nationalityCd;
	private Date birthDt;
	private String citizenId;
	private String segmentCd;
	private String educationCd;
	private String incomeCd;
	private String occupationCd;
	private String jobTitleCd;
	private String nickName;
	private String passportNo;
	private String householdSize;
	private String interestCd;
	private String extNo;
	private String faxPhone;
	private String languageCd;
	private Double totSpentAmt;
	private Date lastPurchasedDt;
	private String ministry;
	private String department;
	private String divisionDept;
	private String position;
	private String provinceId;

	// Corporate Detail
	private Long corpId;
	private String companyName;
	private String altName;
	private String registrationId;
	private String taxId;
	private String industryCd;
	private String companySizeCd;
	private String revenueCd;
	private String url;

	private Integer regId;
	private Date regDt;
	private Integer chgId;
	private Date chgDt;

	public Long getCustId() {
		return custId;
	}

	public void setCustId(Long custId) {
		this.custId = custId;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCustTypeName() {
		return custTypeName;
	}

	public void setCustTypeName(String custTypeName) {
		this.custTypeName = custTypeName;
	}

	public String getIndustryName() {
		return industryName;
	}

	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getTypeCd() {
		return typeCd;
	}

	public void setTypeCd(String typeCd) {
		this.typeCd = typeCd;
	}

	public String getCustCode() {
		return custCode;
	}

	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}

	public String getTitleCd() {
		return titleCd;
	}

	public void setTitleCd(String titleCd) {
		this.titleCd = titleCd;
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

	public String getMidName() {
		return midName;
	}

	public void setMidName(String midName) {
		this.midName = midName;
	}

	public String getStatusCd() {
		return statusCd;
	}

	public void setStatusCd(String statusCd) {
		this.statusCd = statusCd;
	}

	public String getContactChannelCd() {
		return contactChannelCd;
	}

	public void setContactChannelCd(String contactChannelCd) {
		this.contactChannelCd = contactChannelCd;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getWorkPhone() {
		return workPhone;
	}

	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}

	public Integer getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getAddr1() {
		return addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public String getAddr2() {
		return addr2;
	}

	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}

	public String getSubArea() {
		return subArea;
	}

	public void setSubArea(String subArea) {
		this.subArea = subArea;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getNotCallYn() {
		return notCallYn;
	}

	public void setNotCallYn(String notCallYn) {
		this.notCallYn = notCallYn;
	}

	public String getNotSmsYn() {
		return notSmsYn;
	}

	public void setNotSmsYn(String notSmsYn) {
		this.notSmsYn = notSmsYn;
	}

	public String getNotEmailYn() {
		return notEmailYn;
	}

	public void setNotEmailYn(String notEmailYn) {
		this.notEmailYn = notEmailYn;
	}

	public String getNotMailYn() {
		return notMailYn;
	}

	public void setNotMailYn(String notMailYn) {
		this.notMailYn = notMailYn;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getIndustryCd() {
		return industryCd;
	}

	public void setIndustryCd(String industryCd) {
		this.industryCd = industryCd;
	}

	public String getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}

	public String getTaxId() {
		return taxId;
	}

	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}

	public String getRevenueCd() {
		return revenueCd;
	}

	public void setRevenueCd(String revenueCd) {
		this.revenueCd = revenueCd;
	}

	public String getGenderCd() {
		return genderCd;
	}

	public void setGenderCd(String genderCd) {
		this.genderCd = genderCd;
	}

	public String getMaritalStatusCd() {
		return maritalStatusCd;
	}

	public void setMaritalStatusCd(String maritalStatusCd) {
		this.maritalStatusCd = maritalStatusCd;
	}

	public Date getBirthDt() {
		return birthDt;
	}

	public void setBirthDt(Date birthDt) {
		this.birthDt = birthDt;
	}

	public String getCitizenId() {
		return citizenId;
	}

	public void setCitizenId(String citizenId) {
		this.citizenId = citizenId;
	}

	public String getSegmentCd() {
		return segmentCd;
	}

	public void setSegmentCd(String segmentCd) {
		this.segmentCd = segmentCd;
	}

	public String getEducationCd() {
		return educationCd;
	}

	public void setEducationCd(String educationCd) {
		this.educationCd = educationCd;
	}

	public String getIncomeCd() {
		return incomeCd;
	}

	public void setIncomeCd(String incomeCd) {
		this.incomeCd = incomeCd;
	}

	public String getOccupationCd() {
		return occupationCd;
	}

	public void setOccupationCd(String occupationCd) {
		this.occupationCd = occupationCd;
	}

	public String getJobTitleCd() {
		return jobTitleCd;
	}

	public void setJobTitleCd(String jobTitleCd) {
		this.jobTitleCd = jobTitleCd;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPassportNo() {
		return passportNo;
	}

	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}

	public String getHouseholdSize() {
		return householdSize;
	}

	public void setHouseholdSize(String householdSize) {
		this.householdSize = householdSize;
	}

	public String getInterestCd() {
		return interestCd;
	}

	public void setInterestCd(String interestCd) {
		this.interestCd = interestCd;
	}

	public String getExtNo() {
		return extNo;
	}

	public void setExtNo(String extNo) {
		this.extNo = extNo;
	}

	public String getFaxPhone() {
		return faxPhone;
	}

	public void setFaxPhone(String faxPhone) {
		this.faxPhone = faxPhone;
	}

	public Integer getRegId() {
		return regId;
	}

	public void setRegId(Integer regId) {
		this.regId = regId;
	}

	public Date getRegDt() {
		return regDt;
	}

	public void setRegDt(Date regDt) {
		this.regDt = regDt;
	}

	public Integer getChgId() {
		return chgId;
	}

	public void setChgId(Integer chgId) {
		this.chgId = chgId;
	}

	public Date getChgDt() {
		return chgDt;
	}

	public void setChgDt(Date chgDt) {
		this.chgDt = chgDt;
	}

	public String getLanguageCd() {
		return languageCd;
	}

	public void setLanguageCd(String languageCd) {
		this.languageCd = languageCd;
	}

	public Double getTotSpentAmt() {
		return totSpentAmt;
	}

	public void setTotSpentAmt(Double totSpentAmt) {
		this.totSpentAmt = totSpentAmt;
	}

	public Date getLastPurchasedDt() {
		return lastPurchasedDt;
	}

	public void setLastPurchasedDt(Date lastPurchasedDt) {
		this.lastPurchasedDt = lastPurchasedDt;
	}

	public String getCountryCd() {
		return countryCd;
	}

	public void setCountryCd(String countryCd) {
		this.countryCd = countryCd;
	}

	public String getRaceCd() {
		return raceCd;
	}

	public void setRaceCd(String raceCd) {
		this.raceCd = raceCd;
	}

	public String getNationalityCd() {
		return nationalityCd;
	}

	public void setNationalityCd(String nationalityCd) {
		this.nationalityCd = nationalityCd;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCompanySizeCd() {
		return companySizeCd;
	}

	public void setCompanySizeCd(String companySizeCd) {
		this.companySizeCd = companySizeCd;
	}

	public String getAltName() {
		return altName;
	}

	public void setAltName(String altName) {
		this.altName = altName;
	}

	public Long getIndId() {
		return indId;
	}

	public void setIndId(Long indId) {
		this.indId = indId;
	}

	public Long getCorpId() {
		return corpId;
	}

	public void setCorpId(Long corpId) {
		this.corpId = corpId;
	}

	public String getWorkPhoneFormat() {
		String workPhone = this.workPhone;
		if(workPhone != null){
			if (workPhone.length() > 0) {
				workPhoneFormat = workPhone.substring(0, 2) + "-" + workPhone.substring(2, 5) + "-" + workPhone.substring(5, 9);
			}
		}
		
		return workPhoneFormat;
	}

	public String getCustTypeCd() {
		return custTypeCd;
	}

	public void setCustTypeCd(String custTypeCd) {
		this.custTypeCd = custTypeCd;
	}

	public void setWorkPhoneFormat(String workPhoneFormat) {
		this.workPhoneFormat = workPhoneFormat;
	}

	public String getMinistry() {
		return ministry;
	}

	public void setMinistry(String ministry) {
		this.ministry = ministry;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDivisionDept() {
		return divisionDept;
	}

	public void setDivisionDept(String divisionDept) {
		this.divisionDept = divisionDept;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	public String getHomePhoneExt() {
		return homePhoneExt;
	}

	public void setHomePhoneExt(String homePhoneExt) {
		this.homePhoneExt = homePhoneExt;
	}

	public String getMobileNoExt() {
		return mobileNoExt;
	}

	public void setMobileNoExt(String mobileNoExt) {
		this.mobileNoExt = mobileNoExt;
	}

	public String getWorkPhoneExt() {
		return workPhoneExt;
	}

	public void setWorkPhoneExt(String workPhoneExt) {
		this.workPhoneExt = workPhoneExt;
	}
	
	
}
