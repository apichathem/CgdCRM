package com.locus.jlo.rest.request;

public class SyncDownRequest extends CommonRequest {

	private String lastSyncDate;
	private String type;

	public String getLastSyncDate() {
		return lastSyncDate;
	}

	public void setLastSyncDate(String lastSyncDate) {
		this.lastSyncDate = lastSyncDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
