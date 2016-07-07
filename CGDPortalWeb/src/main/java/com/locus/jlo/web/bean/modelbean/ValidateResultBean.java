package com.locus.jlo.web.bean.modelbean;

public class ValidateResultBean extends BaseModelBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4845610447379466690L;
	private Integer row;
	private String message;
	
	public Integer getRow() {
		return row;
	}
	public void setRow(Integer row) {
		this.row = row;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
