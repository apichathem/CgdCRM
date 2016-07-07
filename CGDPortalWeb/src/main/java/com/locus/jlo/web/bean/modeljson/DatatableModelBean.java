package com.locus.jlo.web.bean.modeljson;

import java.io.Serializable;
import java.util.List;

public class DatatableModelBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3024186625929008290L;
	// Reply from the server
	private Integer sEcho;
	private Integer iTotalRecords;
	private Integer iTotalDisplayRecords;
	@SuppressWarnings("rawtypes")
	private List aaData;

	// initial
	private Integer iDisplayLength;
	private Boolean bFilter;
	private Boolean bLengthChange;
	private Boolean bDestroy;
	private Boolean bAutoWidth;
	private Boolean bPaginate;
	private Boolean bInfo;
	private Boolean bRetrieve;
	private Boolean bProcessing;
	private Boolean bServerSide;
	private List<AoColumn> aoColumns;

	public class AoColumn implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 270090242123626386L;
		private String sTitle;
		private String mData;
		private String sWidth;
		private String fnRender;

		public String getsTitle() {
			return sTitle;
		}

		public void setsTitle(String sTitle) {
			this.sTitle = sTitle;
		}

		public String getmData() {
			return mData;
		}

		public void setmData(String mData) {
			this.mData = mData;
		}

		public String getsWidth() {
			return sWidth;
		}

		public void setsWidth(String sWidth) {
			this.sWidth = sWidth;
		}

		public String getFnRender() {
			return fnRender;
		}

		public void setFnRender(String fnRender) {
			this.fnRender = fnRender;
		}

	}

	public Integer getsEcho() {
		return sEcho;
	}

	public void setsEcho(Integer sEcho) {
		this.sEcho = sEcho;
	}

	public Integer getiTotalRecords() {
		return iTotalRecords;
	}

	public void setiTotalRecords(Integer iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}

	public Integer getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}

	public void setiTotalDisplayRecords(Integer iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}

	@SuppressWarnings("rawtypes")
	public List getAaData() {
		return aaData;
	}

	@SuppressWarnings("rawtypes")
	public void setAaData(List aaData) {
		this.aaData = aaData;
	}

	public Integer getiDisplayLength() {
		return iDisplayLength;
	}

	public void setiDisplayLength(Integer iDisplayLength) {
		this.iDisplayLength = iDisplayLength;
	}

	public Boolean getbFilter() {
		return bFilter;
	}

	public void setbFilter(Boolean bFilter) {
		this.bFilter = bFilter;
	}

	public Boolean getbLengthChange() {
		return bLengthChange;
	}

	public void setbLengthChange(Boolean bLengthChange) {
		this.bLengthChange = bLengthChange;
	}

	public Boolean getbDestroy() {
		return bDestroy;
	}

	public void setbDestroy(Boolean bDestroy) {
		this.bDestroy = bDestroy;
	}

	public Boolean getbAutoWidth() {
		return bAutoWidth;
	}

	public void setbAutoWidth(Boolean bAutoWidth) {
		this.bAutoWidth = bAutoWidth;
	}

	public Boolean getbRetrieve() {
		return bRetrieve;
	}

	public void setbRetrieve(Boolean bRetrieve) {
		this.bRetrieve = bRetrieve;
	}

	public Boolean getbProcessing() {
		return bProcessing;
	}

	public void setbProcessing(Boolean bProcessing) {
		this.bProcessing = bProcessing;
	}

	public Boolean getbServerSide() {
		return bServerSide;
	}

	public void setbServerSide(Boolean bServerSide) {
		this.bServerSide = bServerSide;
	}

	public List<AoColumn> getAoColumns() {
		return aoColumns;
	}

	public void setAoColumns(List<AoColumn> aoColumns) {
		this.aoColumns = aoColumns;
	}

	public Boolean getbInfo() {
		return bInfo;
	}

	public void setbInfo(Boolean bInfo) {
		this.bInfo = bInfo;
	}

	public Boolean getbPaginate() {
		return bPaginate;
	}

	public void setbPaginate(Boolean bPaginate) {
		this.bPaginate = bPaginate;
	}
}
