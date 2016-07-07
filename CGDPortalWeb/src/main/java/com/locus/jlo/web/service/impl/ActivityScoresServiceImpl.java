package com.locus.jlo.web.service.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import com.locus.common.domain.ServiceResult;
import com.locus.common.domain.SimpleKeyValue;
import com.locus.jlo.web.bean.dto.ActivityScoresDTO;
import com.locus.jlo.web.service.ActivityScoresService;

@Service
public class ActivityScoresServiceImpl extends BaseService implements ActivityScoresService {

	final static String SQL_INSERT = "ACTSCORES.INSERT_ACT_SCORES";
	final static String SQL_UPDATE = "ACTSCORES.UPDATE_ACT_SCORES";
	final static String SQL_FIND_BY_ID = "ACTSCORES.FIND_BY_ID";
	final static String SQL_FIND_BY_NAME = "ACTSCORES.FIND_BY_NAME";
	final static String SQL_INSERT_ACTIVITY_LOG = "ACT.INSERT_ACTIVITY_LOG";

	@Override
	public ServiceResult<Long> insert(ActivityScoresDTO activityScoresDTO) {
		ServiceResult<Long> result = null;
		try {
			Long id = dynamicJdbcDao.executeInsert(SQL_INSERT, Boolean.TRUE, activityScoresDTO);
			result = new ServiceResult<Long>(id);
		} catch (Exception e) {
			result = new ServiceResult<Long>(e);
			result.setSuccess(false);
		}
		return result;
	}

	@Override
	public void update(ActivityScoresDTO activityScoresDTO) {
		try {
			dynamicJdbcDao.executeUpdate(SQL_UPDATE, activityScoresDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public ServiceResult<ActivityScoresDTO> findById(String actId) {
		ServiceResult<ActivityScoresDTO> result = null;
		try {
			ActivityScoresDTO obj = dynamicJdbcDao.findForObject(SQL_FIND_BY_ID, BeanPropertyRowMapper.newInstance(ActivityScoresDTO.class), new SimpleKeyValue("actId",
					actId));
			result = new ServiceResult<ActivityScoresDTO>(obj);
		} catch (Exception e) {
			e.printStackTrace();
			result = new ServiceResult<ActivityScoresDTO>(e);
			result.setSuccess(false);
		}
		return result;
	}

	@Override
	public ServiceResult<List<ActivityScoresDTO>> findListByName(String actName) {
		ServiceResult<List<ActivityScoresDTO>> result = null;
		try {
			List<ActivityScoresDTO> obj = dynamicJdbcDao.findForList(SQL_FIND_BY_NAME, BeanPropertyRowMapper.newInstance(ActivityScoresDTO.class), new SimpleKeyValue(
					"actName", actName));
			result = new ServiceResult<List<ActivityScoresDTO>>(obj);
		} catch (Exception e) {
			e.printStackTrace();
			result = new ServiceResult<List<ActivityScoresDTO>>(e);
			result.setSuccess(false);
		}
		return result;
	}

}
