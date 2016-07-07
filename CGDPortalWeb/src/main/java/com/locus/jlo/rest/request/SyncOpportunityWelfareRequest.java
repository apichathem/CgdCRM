package com.locus.jlo.rest.request;

import java.util.List;

import com.locus.jlo.rest.bean.OpportunityWelfareBean;

public class SyncOpportunityWelfareRequest extends CommonRequest {

	private List<OpportunityWelfareBean> createList;
	private List<OpportunityWelfareBean> updateList;

	public List<OpportunityWelfareBean> getCreateList() {
		return createList;
	}

	public void setCreateList(List<OpportunityWelfareBean> createList) {
		this.createList = createList;
	}

	public List<OpportunityWelfareBean> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(List<OpportunityWelfareBean> updateList) {
		this.updateList = updateList;
	}

}
