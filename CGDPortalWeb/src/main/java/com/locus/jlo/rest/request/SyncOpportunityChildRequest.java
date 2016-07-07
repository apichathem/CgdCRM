package com.locus.jlo.rest.request;

import java.util.List;

import com.locus.jlo.rest.bean.OpportunityChildBean;

public class SyncOpportunityChildRequest extends CommonRequest {

	private List<OpportunityChildBean> createList;
	private List<OpportunityChildBean> updateList;

	public List<OpportunityChildBean> getCreateList() {
		return createList;
	}

	public void setCreateList(List<OpportunityChildBean> createList) {
		this.createList = createList;
	}

	public List<OpportunityChildBean> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(List<OpportunityChildBean> updateList) {
		this.updateList = updateList;
	}

}
