package com.locus.jlo.rest.request;

public class GetVersionRequest extends CommonRequest {
	
	private String os;
	private String currentversion;
	
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getCurrentversion() {
		return currentversion;
	}
	public void setCurrentversion(String currentversion) {
		this.currentversion = currentversion;
	}
	
}
