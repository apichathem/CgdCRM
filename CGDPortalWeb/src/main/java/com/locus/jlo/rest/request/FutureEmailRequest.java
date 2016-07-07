package com.locus.jlo.rest.request;

import java.util.List;

public class FutureEmailRequest extends CommonRequest{

	private List<String> toEmails;
	private List<String> ccEmails;
	private String from;
	private String subject;
	private String body;
	private String futureDesc;
	
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
	public String getFutureDesc() {
		return futureDesc;
	}
	public void setFutureDesc(String futureDesc) {
		this.futureDesc = futureDesc;
	}
	
	
	
}
