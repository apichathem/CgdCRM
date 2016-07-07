package com.locus.jlo.web.service;

import java.util.List;
import java.util.Map;

import com.locus.common.domain.ServiceResult;
import com.locus.jlo.rest.bean.ActivityBean;
import com.locus.jlo.rest.bean.OpportunityAddressBean;
import com.locus.jlo.rest.bean.OpportunityBean;
import com.locus.jlo.rest.bean.OpportunityChildBean;
import com.locus.jlo.rest.bean.OpportunityInvestmentBean;
import com.locus.jlo.rest.bean.OpportunityWelfareBean;

public interface SyncService {

	ServiceResult<String> getLastSyncDate() throws Exception;

	ServiceResult<Boolean> syncActivityLog(List<ActivityBean> list, String agentCode) throws Exception;

	ServiceResult<Boolean> syncOpportunity(List<OpportunityBean> createList, List<OpportunityBean> updateList, String agentCode) throws Exception;

	ServiceResult<Boolean> syncOpportunityAddress(List<OpportunityAddressBean> createList, List<OpportunityAddressBean> updateList, String agentCode) throws Exception;

	ServiceResult<Boolean> syncOpportunityInvestment(List<OpportunityInvestmentBean> createList, List<OpportunityInvestmentBean> updateList, String agentCode) throws Exception;

	ServiceResult<Boolean> syncOpportunityChild(List<OpportunityChildBean> createList, List<OpportunityChildBean> updateList, String agentCode) throws Exception;

	ServiceResult<Boolean> syncOpportunityWelfare(List<OpportunityWelfareBean> createList, List<OpportunityWelfareBean> updateList, String agentCode) throws Exception;

	ServiceResult<List<Object>> syncDown(String type, String agentCode, String lastSyncDate) throws Exception;

	ServiceResult<List<Object>> syncDownOnly(String type, String lastSyncDate) throws Exception;

	ServiceResult<List<Map<String, Object>>> syncDownStagingData(String type, String lastSyncDate) throws Exception;

}
