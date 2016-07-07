package com.locus.jlo.web.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.locus.common.domain.ServiceResult;
import com.locus.common.domain.SimpleKeyValue;
import com.locus.common.mapper.HashMapLowerCaseRowMapper;
import com.locus.common.mapper.PrimitiveSafeBeanPropertyRowMapper;
import com.locus.common.utils.BeanPropertyUtils;
import com.locus.jlo.rest.bean.ActivityBean;
import com.locus.jlo.rest.bean.AmphurBean;
import com.locus.jlo.rest.bean.BranchBean;
import com.locus.jlo.rest.bean.IntroduceBean;
import com.locus.jlo.rest.bean.NewsAttachBean;
import com.locus.jlo.rest.bean.NewsBean;
import com.locus.jlo.rest.bean.OpportunityAddressBean;
import com.locus.jlo.rest.bean.OpportunityBean;
import com.locus.jlo.rest.bean.OpportunityChildBean;
import com.locus.jlo.rest.bean.OpportunityInvestmentBean;
import com.locus.jlo.rest.bean.OpportunityWelfareBean;
import com.locus.jlo.rest.bean.PostcodeBean;
import com.locus.jlo.rest.bean.ProvinceBean;
import com.locus.jlo.rest.bean.RegionBean;
import com.locus.jlo.rest.bean.RiskAttachBean;
import com.locus.jlo.rest.bean.RiskBean;
import com.locus.jlo.rest.bean.RiskChildBean;
import com.locus.jlo.rest.bean.RiskHraBean;
import com.locus.jlo.rest.bean.RiskMaritalStatusBean;
import com.locus.jlo.rest.bean.RiskOccupationBean;
import com.locus.jlo.rest.bean.RiskRegionBean;
import com.locus.jlo.rest.bean.TumbonBean;
import com.locus.jlo.rest.bean.WorkingPeriodBean;
import com.locus.jlo.web.bean.dto.HospitalDTO;
import com.locus.jlo.web.bean.modelbean.CodebookModelBean;
import com.locus.jlo.web.service.SyncService;

@Service
public class SyncServiceImpl extends BaseService implements SyncService {

	final static String SQL_GET_LAST_SYNC_DATE = "SYNC.GET_LAST_SYNC_DATE";
	final static String SQL_INSERT_ACTIVITY_LOG = "SYNC.INSERT_ACTIVITY_LOG";

	final static String SQL_INSERT_OPPORTUNITY_AGENT = "SYNC.INSERT_OPPORTUNITY_AGENT";

	final static String SQL_INSERT_OPPORTUNITY = "SYNC.INSERT_OPPORTUNITY";
	final static String SQL_UPDATE_OPPORTUNITY = "SYNC.UPDATE_OPPORTUNITY";
	final static String SQL_GET_OPPORTUNITY_BY_SYNC_DATE = "SYNC.GET_OPPORTUNITY_BY_SYNC_DATE";

	final static String SQL_INSERT_OPPORTUNITY_ADDRESS = "SYNC.INSERT_OPPORTUNITY_ADDRESS";
	final static String SQL_UPDATE_OPPORTUNITY_ADDRESS = "SYNC.UPDATE_OPPORTUNITY_ADDRESS";
	final static String SQL_GET_OPPORTUNITY_ADDRESS_BY_SYNC_DATE = "SYNC.GET_OPPORTUNITY_ADDRESS_BY_SYNC_DATE";

	final static String SQL_INSERT_OPPORTUNITY_CHILD = "SYNC.INSERT_OPPORTUNITY_CHILD";
	final static String SQL_UPDATE_OPPORTUNITY_CHILD = "SYNC.UPDATE_OPPORTUNITY_CHILD";
	final static String SQL_GET_OPPORTUNITY_CHILD_BY_SYNC_DATE = "SYNC.GET_OPPORTUNITY_CHILD_BY_SYNC_DATE";

	final static String SQL_INSERT_OPPORTUNITY_INVESTMENT = "SYNC.INSERT_OPPORTUNITY_INVESTMENT";
	final static String SQL_UPDATE_OPPORTUNITY_INVESTMENT = "SYNC.UPDATE_OPPORTUNITY_INVESTMENT";
	final static String SQL_GET_OPPORTUNITY_INVESTMENT_BY_SYNC_DATE = "SYNC.GET_OPPORTUNITY_INVESTMENT_BY_SYNC_DATE";

	final static String SQL_INSERT_OPPORTUNITY_WELFARE = "SYNC.INSERT_OPPORTUNITY_WELFARE";
	final static String SQL_UPDATE_OPPORTUNITY_WELFARE = "SYNC.UPDATE_OPPORTUNITY_WELFARE";
	final static String SQL_GET_OPPORTUNITY_WELFARE_BY_SYNC_DATE = "SYNC.GET_OPPORTUNITY_WELFARE_BY_SYNC_DATE";

	final static String SQL_GET_BRANCH_BY_SYNCDATE = "SYNC.SQL_GET_BRANCH_BY_SYNCDATE";
	final static String SQL_GET_HOSPITAL_BY_SYNCDATE = "SYNC.SQL_GET_HOSPITAL_BY_SYNCDATE";
	final static String SQL_GET_CODEBOOK_BY_SYNCDATE = "SYNC.SQL_GET_CODEBOOK_BY_SYNCDATE";
	final static String SQL_GET_NEWS_BY_SYNCDATE = "SYNC.SQL_GET_NEWS_BY_SYNCDATE";
	final static String SQL_GET_NEWS_ATTACH_BY_SYNCDATE = "SYNC.SQL_GET_NEWS_ATTACH_BY_SYNCDATE";
	final static String SQL_GET_WORKING_PERIOD_BY_SYNCDATE = "SYNC.SQL_GET_WORKING_PERIOD_BY_SYNCDATE";
	final static String SQL_GET_REGION_BY_SYNCDATE = "SYNC.SQL_GET_REGION_BY_SYNCDATE";
	final static String SQL_GET_PROVINCE_BY_SYNCDATE = "SYNC.SQL_GET_PROVINCE_BY_SYNCDATE";
	final static String SQL_GET_AMPHUR_BY_SYNCDATE = "SYNC.SQL_GET_AMPHUR_BY_SYNCDATE";
	final static String SQL_GET_TUMBON_BY_SYNCDATE = "SYNC.SQL_GET_TUMBON_BY_SYNCDATE";
	final static String SQL_GET_POSTCODE_BY_SYNCDATE = "SYNC.SQL_GET_POSTCODE_BY_SYNCDATE";
	final static String SQL_GET_INTRODUCE_BY_SYNCDATE = "SYNC.SQL_GET_INTRODUCE_BY_SYNCDATE";
	final static String SQL_GET_RISK_BY_SYNCDATE = "SYNC.SQL_GET_RISK_BY_SYNCDATE";
	final static String SQL_GET_RISK_ATTACH_BY_SYNCDATE = "SYNC.SQL_GET_RISK_ATTACH_BY_SYNCDATE";
	final static String SQL_GET_RISK_CHILD_BY_SYNCDATE = "SYNC.SQL_GET_RISK_CHILD_BY_SYNCDATE";
	final static String SQL_GET_RISK_HRA_BY_SYNCDATE = "SYNC.SQL_GET_RISK_HRA_BY_SYNCDATE";
	final static String SQL_GET_RISK_MARITAL_STATUS_BY_SYNCDATE = "SYNC.SQL_GET_RISK_MARITAL_STATUS_BY_SYNCDATE";
	final static String SQL_GET_RISK_OCCUPATION_BY_SYNCDATE = "SYNC.SQL_GET_RISK_OCCUPATION_BY_SYNCDATE";
	final static String SQL_GET_RISK_REGION_BY_SYNCDATE = "SYNC.SQL_GET_RISK_REGION_BY_SYNCDATE";
	final static String SQL_GET_STAGING_PRODUCT = "SYNC.SQL_GET_STAGING_PRODUCT";

	@Override
	public ServiceResult<String> getLastSyncDate() throws Exception {
		String lastSync = dynamicJdbcDao.findForString(SQL_GET_LAST_SYNC_DATE);
		return new ServiceResult<String>(lastSync);
	}

	@SuppressWarnings("unchecked")
	@Override
	public ServiceResult<Boolean> syncActivityLog(List<ActivityBean> list, String agentCode) throws Exception {
		Map<String, Object>[] values = new HashMap[list.size()];
		for (int i = 0; i < list.size(); i++) {
			values[i] = BeanPropertyUtils.createMappedPropertyCamel(list.get(i));
			values[i].put("agent_code", agentCode);
		}
		dynamicJdbcDao.batchInsert(SQL_INSERT_ACTIVITY_LOG, values);
		return new ServiceResult<Boolean>(true);
	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public ServiceResult<Boolean> syncOpportunity(List<OpportunityBean> createList, List<OpportunityBean> updateList, String agentCode) throws Exception {
		Map<String, Object>[] values = new HashMap[createList.size()];
		for (int i = 0; i < createList.size(); i++) {
			values[i] = BeanPropertyUtils.createMappedPropertyCamel(createList.get(i));
			values[i].put("agent_code", agentCode);
		}
		dynamicJdbcDao.batchInsert(SQL_INSERT_OPPORTUNITY, values);
		dynamicJdbcDao.batchInsert(SQL_INSERT_OPPORTUNITY_AGENT, values);
		values = new HashMap[updateList.size()];
		for (int i = 0; i < updateList.size(); i++) {
			values[i] = BeanPropertyUtils.createMappedPropertyCamel(updateList.get(i));
		}
		dynamicJdbcDao.batchInsert(SQL_UPDATE_OPPORTUNITY, values);

		return new ServiceResult<Boolean>(true);
	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public ServiceResult<Boolean> syncOpportunityAddress(List<OpportunityAddressBean> createList, List<OpportunityAddressBean> updateList, String agentCode) throws Exception {
		Map<String, Object>[] values = new HashMap[createList.size()];
		for (int i = 0; i < createList.size(); i++) {
			values[i] = BeanPropertyUtils.createMappedPropertyCamel(createList.get(i));
		}
		dynamicJdbcDao.batchInsert(SQL_INSERT_OPPORTUNITY_ADDRESS, values);
		values = new HashMap[updateList.size()];
		for (int i = 0; i < updateList.size(); i++) {
			values[i] = BeanPropertyUtils.createMappedPropertyCamel(updateList.get(i));
		}
		dynamicJdbcDao.batchInsert(SQL_UPDATE_OPPORTUNITY_ADDRESS, values);

		return new ServiceResult<Boolean>(true);
	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public ServiceResult<Boolean> syncOpportunityChild(List<OpportunityChildBean> createList, List<OpportunityChildBean> updateList, String agentCode) throws Exception {
		Map<String, Object>[] values = new HashMap[createList.size()];
		for (int i = 0; i < createList.size(); i++) {
			values[i] = BeanPropertyUtils.createMappedPropertyCamel(createList.get(i));
		}
		dynamicJdbcDao.batchInsert(SQL_INSERT_OPPORTUNITY_CHILD, values);
		values = new HashMap[updateList.size()];
		for (int i = 0; i < updateList.size(); i++) {
			values[i] = BeanPropertyUtils.createMappedPropertyCamel(updateList.get(i));
		}
		dynamicJdbcDao.batchInsert(SQL_UPDATE_OPPORTUNITY_CHILD, values);

		return new ServiceResult<Boolean>(true);
	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public ServiceResult<Boolean> syncOpportunityInvestment(List<OpportunityInvestmentBean> createList, List<OpportunityInvestmentBean> updateList, String agentCode)
			throws Exception {
		Map<String, Object>[] values = new HashMap[createList.size()];
		for (int i = 0; i < createList.size(); i++) {
			values[i] = BeanPropertyUtils.createMappedPropertyCamel(createList.get(i));
		}
		dynamicJdbcDao.batchInsert(SQL_INSERT_OPPORTUNITY_INVESTMENT, values);
		values = new HashMap[updateList.size()];
		for (int i = 0; i < updateList.size(); i++) {
			values[i] = BeanPropertyUtils.createMappedPropertyCamel(updateList.get(i));
		}
		dynamicJdbcDao.batchInsert(SQL_UPDATE_OPPORTUNITY_INVESTMENT, values);

		return new ServiceResult<Boolean>(true);
	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public ServiceResult<Boolean> syncOpportunityWelfare(List<OpportunityWelfareBean> createList, List<OpportunityWelfareBean> updateList, String agentCode) throws Exception {
		Map<String, Object>[] values = new HashMap[createList.size()];
		for (int i = 0; i < createList.size(); i++) {
			values[i] = BeanPropertyUtils.createMappedPropertyCamel(createList.get(i));
		}
		dynamicJdbcDao.batchInsert(SQL_INSERT_OPPORTUNITY_WELFARE, values);
		values = new HashMap[updateList.size()];
		for (int i = 0; i < updateList.size(); i++) {
			values[i] = BeanPropertyUtils.createMappedPropertyCamel(updateList.get(i));
		}
		dynamicJdbcDao.batchInsert(SQL_UPDATE_OPPORTUNITY_WELFARE, values);

		return new ServiceResult<Boolean>(true);
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ServiceResult<List<Object>> syncDown(String type, String agentCode, String lastSyncDate) throws Exception {
		String sqlId = "";
		Class clazz = Object.class;
		switch (type) {
		case "OPPORTUNITY":
			sqlId = SQL_GET_OPPORTUNITY_BY_SYNC_DATE;
			clazz = OpportunityBean.class;
			break;
		case "OPPORTUNITY_ADDRESS":
			sqlId = SQL_GET_OPPORTUNITY_ADDRESS_BY_SYNC_DATE;
			clazz = OpportunityAddressBean.class;
			break;
		case "OPPORTUNITY_CHILD":
			sqlId = SQL_GET_OPPORTUNITY_CHILD_BY_SYNC_DATE;
			clazz = OpportunityChildBean.class;
			break;
		case "OPPORTUNITY_INVESTMENT":
			sqlId = SQL_GET_OPPORTUNITY_INVESTMENT_BY_SYNC_DATE;
			clazz = OpportunityInvestmentBean.class;
			break;
		case "OPPORTUNITY_WELFARE":
			sqlId = SQL_GET_OPPORTUNITY_WELFARE_BY_SYNC_DATE;
			clazz = OpportunityWelfareBean.class;
			break;
		}

		List<Object> param = new ArrayList<Object>();
		param.add(new SimpleKeyValue("AGENT_CODE", agentCode));
		if (StringUtils.isNotEmpty(lastSyncDate)) {
			param.add(new SimpleKeyValue("LAST_SYNC_DATE", lastSyncDate));
		} else {
			param.add(new SimpleKeyValue("EXCLUDE_DELETED"));
		}
		List<Object> result = dynamicJdbcDao.findForList(sqlId, PrimitiveSafeBeanPropertyRowMapper.newInstance(clazz), param.toArray(new Object[] {}));

		return new ServiceResult<List<Object>>(result);
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ServiceResult<List<Object>> syncDownOnly(String type, String lastSyncDate) throws Exception {
		String sqlId = "";
		Class clazz = Object.class;
		switch (type) {
		case "BRANCH":
			sqlId = SQL_GET_BRANCH_BY_SYNCDATE;
			clazz = BranchBean.class;
			break;
		case "HOSPITAL":
			sqlId = SQL_GET_HOSPITAL_BY_SYNCDATE;
			clazz = HospitalDTO.class;
			break;
		case "CODEBOOK":
			sqlId = SQL_GET_CODEBOOK_BY_SYNCDATE;
			clazz = CodebookModelBean.class;
			break;
		case "NEWS":
			sqlId = SQL_GET_NEWS_BY_SYNCDATE;
			clazz = NewsBean.class;
			break;
		case "NEWS_ATTACH":
			sqlId = SQL_GET_NEWS_ATTACH_BY_SYNCDATE;
			clazz = NewsAttachBean.class;
			break;
		case "WORKING_PERIOD":
			sqlId = SQL_GET_WORKING_PERIOD_BY_SYNCDATE;
			clazz = WorkingPeriodBean.class;
			break;
		case "INTRODUCE":
			sqlId = SQL_GET_INTRODUCE_BY_SYNCDATE;
			clazz = IntroduceBean.class;
			break;
		case "REGION":
			sqlId = SQL_GET_REGION_BY_SYNCDATE;
			clazz = RegionBean.class;
			break;
		case "PROVINCE":
			sqlId = SQL_GET_PROVINCE_BY_SYNCDATE;
			clazz = ProvinceBean.class;
			break;
		case "AMPHUR":
			sqlId = SQL_GET_AMPHUR_BY_SYNCDATE;
			clazz = AmphurBean.class;
			break;
		case "TUMBON":
			sqlId = SQL_GET_TUMBON_BY_SYNCDATE;
			clazz = TumbonBean.class;
			break;
		case "POSTCODE":
			sqlId = SQL_GET_POSTCODE_BY_SYNCDATE;
			clazz = PostcodeBean.class;
			break;
		case "RISK":
			sqlId = SQL_GET_RISK_BY_SYNCDATE;
			clazz = RiskBean.class;
			break;
		case "RISK_ATTACH":
			sqlId = SQL_GET_RISK_ATTACH_BY_SYNCDATE;
			clazz = RiskAttachBean.class;
			break;
		case "RISK_CHILD":
			sqlId = SQL_GET_RISK_CHILD_BY_SYNCDATE;
			clazz = RiskChildBean.class;
			break;
		case "RISK_HRA":
			sqlId = SQL_GET_RISK_HRA_BY_SYNCDATE;
			clazz = RiskHraBean.class;
			break;
		case "RISK_MARITAL_STATUS":
			sqlId = SQL_GET_RISK_MARITAL_STATUS_BY_SYNCDATE;
			clazz = RiskMaritalStatusBean.class;
			break;
		case "RISK_OCCUPATION":
			sqlId = SQL_GET_RISK_OCCUPATION_BY_SYNCDATE;
			clazz = RiskOccupationBean.class;
			break;
		case "RISK_REGION":
			sqlId = SQL_GET_RISK_REGION_BY_SYNCDATE;
			clazz = RiskRegionBean.class;
			break;
		}

		List<Object> param = new ArrayList<Object>();
		if (StringUtils.isNotEmpty(lastSyncDate)) {
			param.add(new SimpleKeyValue("LAST_SYNC_DATE", lastSyncDate));
		} else {
			param.add(new SimpleKeyValue("EXCLUDE_DELETED"));
		}
		List<Object> result = dynamicJdbcDao.findForList(sqlId, PrimitiveSafeBeanPropertyRowMapper.newInstance(clazz), param.toArray(new Object[] {}));

		return new ServiceResult<List<Object>>(result);
	}

	@Override
	public ServiceResult<List<Map<String, Object>>> syncDownStagingData(String type, String lastSyncDate) throws Exception {
		String sqlId = "";
		switch (type) {
		case "PRODUCT":
			sqlId = SQL_GET_STAGING_PRODUCT;
			break;
		}
		List<Object> param = new ArrayList<Object>();
		if (StringUtils.isNotEmpty(lastSyncDate)) {
			param.add(new SimpleKeyValue("LAST_SYNC_DATE", lastSyncDate));
		} else {
			param.add(new SimpleKeyValue("EXCLUDE_DELETED"));
		}
		List<Map<String, Object>> result = dynamicJdbcDao.findForList(sqlId, HashMapLowerCaseRowMapper.getInstance(), param.toArray(new Object[] {}));
		return new ServiceResult<List<Map<String, Object>>>(result);
	}

}
