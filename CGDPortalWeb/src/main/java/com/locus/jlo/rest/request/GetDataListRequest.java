package com.locus.jlo.rest.request;

public class GetDataListRequest extends CommonRequest {
	
	private String syncdt;
	private int pageno;
	private int rowperpage;
	
	public String getSyncdt() {
		return syncdt; 
	}
	public void setSyncdt(String syncdt) {
		this.syncdt = syncdt;
	}
	public int getPageno() {
		return pageno;
	}
	public void setPageno(int pageno) {
		this.pageno = pageno;
	}
	public int getRowperpage() {
		return rowperpage;
	}
	public void setRowperpage(int rowperpage) {
		this.rowperpage = rowperpage;
	}

}
