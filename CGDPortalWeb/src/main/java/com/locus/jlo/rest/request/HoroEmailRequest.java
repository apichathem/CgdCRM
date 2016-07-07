package com.locus.jlo.rest.request;

import java.util.List;

public class HoroEmailRequest extends CommonRequest{

	private String horoWorkId;
	private String horoHealthId;
	private String horoLoveId;
	private String horoMoneyId;
	
	private List<String> toEmails;
	private List<String> ccEmails;
	private String from;
	private String subject;
	private String body;
	private String horoDesc;
	
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
	
	public String getHoroDesc() {
		return horoDesc;
	}
	public void setHoroDesc(String horoDesc) {
		this.horoDesc = horoDesc;
	}
	public String getHoroWorkId() {
		return horoWorkId;
	}
	public void setHoroWorkId(String horoWorkId) {
		this.horoWorkId = horoWorkId;
	}
	public String getHoroHealthId() {
		return horoHealthId;
	}
	public void setHoroHealthId(String horoHealthId) {
		this.horoHealthId = horoHealthId;
	}
	public String getHoroLoveId() {
		return horoLoveId;
	}
	public void setHoroLoveId(String horoLoveId) {
		this.horoLoveId = horoLoveId;
	}
	public String getHoroMoneyId() {
		return horoMoneyId;
	}
	public void setHoroMoneyId(String horoMoneyId) {
		this.horoMoneyId = horoMoneyId;
	}
	
	
}
