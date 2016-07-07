package com.locus.jlo.web.util;

import java.util.Map;

public class SelectorContainer {
	public static final String VISIBILITY = "visibility";
	public static final String MONTH = "month";
	public static final String CATEGORY = "categoryCode";
	public static final String USE_YN = "useYn";
	public static final String YN = "yn";
	
	private Map<String, String> visibilitySelect;
	private Map<String, String> monthSelect;
	private Map<String, String> categoryCodeSelect;
	private Map<String, String> useYnSelect;
	private Map<String, String> ynSelect;
	
	public Map<String, String> getVisibilitySelect() {
		return visibilitySelect;
	}
	public void setVisibilitySelect(Map<String, String> visibilitySelect) {
		this.visibilitySelect = visibilitySelect;
	}
	public Map<String, String> getMonthSelect() {
		return monthSelect;
	}
	public void setMonthSelect(Map<String, String> monthSelect) {
		this.monthSelect = monthSelect;
	}
	public Map<String, String> getCategoryCodeSelect() {
		return categoryCodeSelect;
	}
	public void setCategoryCodeSelect(Map<String, String> categoryCodeSelect) {
		this.categoryCodeSelect = categoryCodeSelect;
	}
	public Map<String, String> getUseYnSelect() {
		return useYnSelect;
	}
	public void setUseYnSelect(Map<String, String> useYnSelect) {
		this.useYnSelect = useYnSelect;
	}
	public Map<String, String> getYnSelect() {
		return ynSelect;
	}
	public void setYnSelect(Map<String, String> ynSelect) {
		this.ynSelect = ynSelect;
	}

}
