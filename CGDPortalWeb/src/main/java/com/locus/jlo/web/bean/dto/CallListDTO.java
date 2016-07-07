package com.locus.jlo.web.bean.dto;

public class CallListDTO extends BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2451985267982396928L;
	
	private Long callListId;
	private String dialedNumber;
	private String callingNumber;
	private String callerName;
	private String callId;
	
	private String custId;
	private String custType;
	
	public Long getCallListId() {
		return callListId;
	}
	public void setCallListId(Long callListId) {
		this.callListId = callListId;
	}
	public String getDialedNumber() {
		return dialedNumber;
	}
	public void setDialedNumber(String dialedNumber) {
		this.dialedNumber = dialedNumber;
	}
	public String getCallingNumber() {
		return callingNumber;
	}
	public void setCallingNumber(String callingNumber) {
		this.callingNumber = callingNumber;
	}
	public String getCallerName() {
		return callerName;
	}
	public void setCallerName(String callerName) {
		this.callerName = callerName;
	}
	public String getCallId() {
		return callId;
	}
	public void setCallId(String callId) {
		this.callId = callId;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getCustType() {
		return custType;
	}
	public void setCustType(String custType) {
		this.custType = custType;
	}
	
}
