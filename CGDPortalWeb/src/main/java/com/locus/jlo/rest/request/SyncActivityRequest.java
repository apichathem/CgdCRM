package com.locus.jlo.rest.request;

import java.util.List;

import com.locus.jlo.rest.bean.ActivityBean;

public class SyncActivityRequest extends CommonRequest {
		
	private List<ActivityBean> activities;

	public List<ActivityBean> getActivities() {
		return activities;
	}

	public void setActivities(List<ActivityBean> activities) {
		this.activities = activities;
	}

}
