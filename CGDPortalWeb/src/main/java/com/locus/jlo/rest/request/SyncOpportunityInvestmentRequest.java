package com.locus.jlo.rest.request;

import java.util.List;

import com.locus.jlo.rest.bean.OpportunityInvestmentBean;

public class SyncOpportunityInvestmentRequest extends CommonRequest {

	private List<OpportunityInvestmentBean> createList;
	private List<OpportunityInvestmentBean> updateList;

	public List<OpportunityInvestmentBean> getCreateList() {
		return createList;
	}

	public void setCreateList(List<OpportunityInvestmentBean> createList) {
		this.createList = createList;
	}

	public List<OpportunityInvestmentBean> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(List<OpportunityInvestmentBean> updateList) {
		this.updateList = updateList;
	}

}
