package com.locus.jlo.rest.request;

import java.util.List;

import com.locus.jlo.rest.bean.OpportunityAddressBean;

public class SyncOpportunityAddressRequest extends CommonRequest {

	private List<OpportunityAddressBean> createList;
	private List<OpportunityAddressBean> updateList;

	public List<OpportunityAddressBean> getCreateList() {
		return createList;
	}

	public void setCreateList(List<OpportunityAddressBean> createList) {
		this.createList = createList;
	}

	public List<OpportunityAddressBean> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(List<OpportunityAddressBean> updateList) {
		this.updateList = updateList;
	}

}
