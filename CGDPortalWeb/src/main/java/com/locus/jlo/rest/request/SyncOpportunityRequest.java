package com.locus.jlo.rest.request;

import java.util.List;

import com.locus.jlo.rest.bean.OpportunityBean;

public class SyncOpportunityRequest extends CommonRequest {

	private List<OpportunityBean> createList;
	private List<OpportunityBean> updateList;

	public List<OpportunityBean> getCreateList() {
		return createList;
	}

	public void setCreateList(List<OpportunityBean> createList) {
		this.createList = createList;
	}

	public List<OpportunityBean> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(List<OpportunityBean> updateList) {
		this.updateList = updateList;
	}

}
