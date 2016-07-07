package com.locus.jlo.web.bean.criteria;

import java.io.Serializable;

public class ContactCriteria extends BaseCriteria implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8454937574720809270L;
	private String firstName;
	private String lastName;
	private String mobileNo;
	
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
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
}
