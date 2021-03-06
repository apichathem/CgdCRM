package com.locus.jlo.web.bean.modeljson;

import java.io.Serializable;

public class JsonResultBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8905061577893321731L;
	
	public JsonResultBean() {
		
	}
	
	public JsonResultBean(String resultCode, String resultMessage, Object data) {
		this.resultCode = resultCode;
		this.resultMessage = resultMessage;
		this.model = data;
	}
	
	private String resultCode;
	private String resultMessage;
	private Object model;
	
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getResultMessage() {
		return resultMessage;
	}
	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}
	public Object getModel() {
		return model;
	}
	public void setModel(Object model) {
		this.model = model;
	}
	
}
