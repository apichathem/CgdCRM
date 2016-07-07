package com.locus.jlo.rest.response;


public class ServiceResponse<T> {

	private int status;
	private String code;
	private String message;
	private T data;

	public ServiceResponse(int status, String code, String message, T data) {
		this.status = status;
		this.message = message;
		this.data = data;
		this.code = code;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
