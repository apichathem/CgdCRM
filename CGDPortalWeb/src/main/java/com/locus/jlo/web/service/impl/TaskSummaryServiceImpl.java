package com.locus.jlo.web.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.locus.common.domain.ServiceResult;
import com.locus.common.domain.SimpleKeyValue;
import com.locus.common.mapper.PrimitiveSafeBeanPropertyRowMapper;
import com.locus.jlo.web.bean.dto.ContentDetailDTO;
import com.locus.jlo.web.bean.dto.MyTaskActivityDTO;
import com.locus.jlo.web.bean.dto.MyTaskServiceRequestDTO;
import com.locus.jlo.web.service.TaskSummaryService;

@Service("taskSummaryService")
public class TaskSummaryServiceImpl extends BaseService implements TaskSummaryService {
	private Logger logger = Logger.getLogger(getClass());

	public static final String SQL_FIND_ACTIVITY_LIST = "MYTASK.SQL_FIND_ACTIVITY";
	public static final String SQL_FIND_SERVICE_REQUEST_LIST = "MYTASK.SQL_FIND_SERVICE_REQUEST";
	public static final String SQL_COUNT_ACTIVITY_LIST = "MYTASK.SQL_COUNT_PENDING_ACTIVITY";
	public static final String SQL_COUNT_SERVICE_REQUEST_LIST = "MYTASK.SQL_COUNT_PENDING_SR";
	public static final String SQL_FIND_KB = "MYTASK.SQL_FIND_KB";
	public static final String SQL_COUNT_REJECTED_KB = "MYTASK.SQL_COUNT_REJECTED_KB";

	@Override
	public ServiceResult<Page<MyTaskActivityDTO>> searchActivityList(Pageable pageable, Integer ownerId, String langCd, List<String> ownerGroup) {
		ServiceResult<Page<MyTaskActivityDTO>> serviceResult = new ServiceResult<Page<MyTaskActivityDTO>>();
		Page<MyTaskActivityDTO> result = null;
		try {
			result = dynamicJdbcDao.findForPage(SQL_FIND_ACTIVITY_LIST, PrimitiveSafeBeanPropertyRowMapper.newInstance(MyTaskActivityDTO.class),
					pageable, new SimpleKeyValue("userId", ownerId), new SimpleKeyValue("langCd", langCd), new SimpleKeyValue("ownerGroup", ownerGroup));
			serviceResult.setSuccess(Boolean.TRUE);
			serviceResult.setResult(result);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			setErrorResult(serviceResult, e);
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<Page<MyTaskServiceRequestDTO>> searchServiceRequestList(Pageable pageable, Integer ownerId, String langCd, List<String> ownerGroup) {
		ServiceResult<Page<MyTaskServiceRequestDTO>> serviceResult = new ServiceResult<Page<MyTaskServiceRequestDTO>>();
		Page<MyTaskServiceRequestDTO> result = null;
		try {
			result = dynamicJdbcDao.findForPage(SQL_FIND_SERVICE_REQUEST_LIST, PrimitiveSafeBeanPropertyRowMapper.newInstance(MyTaskServiceRequestDTO.class),
					pageable, new SimpleKeyValue("userId", ownerId), new SimpleKeyValue("langCd", langCd), new SimpleKeyValue("ownerGroup", ownerGroup));
			serviceResult.setSuccess(Boolean.TRUE);
			serviceResult.setResult(result);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			setErrorResult(serviceResult, e);
		}
		return serviceResult;
	}

	@Override
	public int countPendingActivity(Integer ownerId, List<String> ownerGroup) {
		try {
			return dynamicJdbcDao.findForLong(SQL_COUNT_ACTIVITY_LIST, new SimpleKeyValue("userId", ownerId), new SimpleKeyValue("ownerGroup", ownerGroup)).intValue();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return 0;
		}
	}

	@Override
	public int countPendingServiceRequest(Integer ownerId, List<String> ownerGroup) {
		try {
			return dynamicJdbcDao.findForLong(SQL_COUNT_SERVICE_REQUEST_LIST, new SimpleKeyValue("userId", ownerId), new SimpleKeyValue("ownerGroup", ownerGroup)).intValue();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return 0;
		}
	}

	@Override
	public ServiceResult<Page<ContentDetailDTO>> searchKbList(Pageable pageable, String langCd, List<String> ownerGroup) {
		
		ServiceResult<Page<ContentDetailDTO>> serviceResult = new ServiceResult<Page<ContentDetailDTO>>();
		Page<ContentDetailDTO> result = null;
		
		try {
			result = dynamicJdbcDao.findForPage(SQL_FIND_KB, PrimitiveSafeBeanPropertyRowMapper.newInstance(ContentDetailDTO.class),
					pageable, new SimpleKeyValue("ownerGroup", ownerGroup));
			serviceResult.setSuccess(Boolean.TRUE);
			serviceResult.setResult(result);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			setErrorResult(serviceResult, e);
		}
		return serviceResult;
	}

/*	@Override
	public int countRejectedKb(Integer ownerId, List<String> ownerGroup) {
		try {
			return dynamicJdbcDao.findForLong(SQL_COUNT_REJECTED_KB, new SimpleKeyValue("userId", ownerId)).intValue();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return 0;
		}
	}*/
}
