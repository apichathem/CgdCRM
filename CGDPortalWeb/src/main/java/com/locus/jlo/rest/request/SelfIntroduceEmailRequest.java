package com.locus.jlo.rest.request;

import java.util.List;

public class SelfIntroduceEmailRequest extends CommonRequest{

	/*private List<String> attUrls;	
	private List<String> attNames;*/
	private String sex;
	private List<String> toEmails;
	private List<String> ccEmails;
	private String from;
	private String subject;
	private String body;
	
	private String agentName;
	private String agentLicenseNo;
	private String prospectName;
	private String refName;
	private String agentAddress1;
	private String agentAddress2;
	
	
	public List<String> getToEmails() {
		return toEmails;
	}
	public void setToEmails(List<String> toEmails) {
		this.toEmails = toEmails;
	}
	public List<String> getCcEmails() {
		return ccEmails;
	}
	public void setCcEmails(List<String> ccEmails) {
		this.ccEmails = ccEmails;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAgentName() {
		return agentName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	public String getAgentLicenseNo() {
		return agentLicenseNo;
	}
	public void setAgentLicenseNo(String agentLicenseNo) {
		this.agentLicenseNo = agentLicenseNo;
	}
	public String getProspectName() {
		return prospectName;
	}
	public void setProspectName(String prospectName) {
		this.prospectName = prospectName;
	}
	public String getRefName() {
		return refName;
	}
	public void setRefName(String refName) {
		this.refName = refName;
	}
	public String getAgentAddress1() {
		return agentAddress1;
	}
	public void setAgentAddress1(String agentAddress1) {
		this.agentAddress1 = agentAddress1;
	}
	public String getAgentAddress2() {
		return agentAddress2;
	}
	public void setAgentAddress2(String agentAddress2) {
		this.agentAddress2 = agentAddress2;
	}
	
	
}
