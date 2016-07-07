package com.locus.jlo.rest.response;

public class SyncServiceResponse<T> extends ServiceResponse<T> {

	private String pageno;
	private String rowperpage;
	private String totalrow;
	private String lastSyncDate;

	public SyncServiceResponse(int status, String code, String message, T data, String pageno, String rowperpage, String totalrow) {
		super(status, code, message, data);
		this.pageno = pageno;
		this.rowperpage = rowperpage;
		this.totalrow = totalrow;
	}

	public SyncServiceResponse(int status, String code, String message, T data, String pageno, String rowperpage, String totalrow, String lastSyncDate) {
		super(status, code, message, data);
		this.pageno = pageno;
		this.rowperpage = rowperpage;
		this.totalrow = totalrow;
		this.lastSyncDate = lastSyncDate;
	}

	public SyncServiceResponse(int status, String code, String message, T data, String lastSyncDate) {
		super(status, code, message, data);
		this.lastSyncDate = lastSyncDate;
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

	public String getLastSyncDate() {
		return lastSyncDate;
	}

	public void setLastSyncDate(String lastSyncDate) {
		this.lastSyncDate = lastSyncDate;
	}

}
