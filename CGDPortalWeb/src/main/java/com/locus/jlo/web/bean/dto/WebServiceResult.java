package com.locus.jlo.web.bean.dto;

public class WebServiceResult<T> {
	
	private int status;
	private String code;
	private String message;
	private T data;
	private String pageno;	//CurrentPage
	private String rowperpage;//RowPerPage
	private String totalrow;//TotalRow
	
	public WebServiceResult(){}
	
	public WebServiceResult(int status,String code,String message,T data){
		this.status=status;
		this.message=message;
		this.data=data;
		this.code=code;
	}
	
	public WebServiceResult(int status,String code,String message,T data,String pageno,String rowperpage,String totalrow){
		this.status=status;
		this.message=message;
		this.data=data;
		this.code=code;
		this.pageno=pageno;
		this.rowperpage=rowperpage;
		this.totalrow=totalrow;
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public T getData() {
		return data;
	}
	public void setData(T result) {
		this.data = result;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPageno() {
		return pageno;
	}

	public void setPageno(String pageno) {
		this.pageno = pageno;
	}

	public String getRowperpage() {
		return rowperpage;
	}

	public void setRowperpage(String rowperpage) {
		this.rowperpage = rowperpage;
	}

	public String getTotalrow() {
		return totalrow;
	}

	public void setTotalrow(String totalrow) {
		this.totalrow = totalrow;
	}

	
	
	
	
}