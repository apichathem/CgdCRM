package com.locus.jlo.web.bean.modelbean;

import java.io.Serializable;

public class FullCalendarEventSourcesModelBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1908830093275822118L;
	private String id;
	private String title;
	private String start;
	private String end; 
	private Boolean allDay;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public Boolean getAllDay() {
		return allDay;
	}
	public void setAllDay(Boolean allDay) {
		this.allDay = allDay;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
