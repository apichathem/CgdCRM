package com.locus.jlo.web.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.locus.common.domain.ServiceResult;
import com.locus.common.domain.SimpleKeyValue;
import com.locus.common.mapper.PrimitiveSafeBeanPropertyRowMapper;
import com.locus.jlo.web.bean.dto.UserLoginHistoryDTO;
import com.locus.jlo.web.bean.dto.UserStaticDTO;
import com.locus.jlo.web.service.UserLoginHistoryService;

@Service
public class UserLoginHistoryServiceImpl extends BaseService implements UserLoginHistoryService {
	private Logger logger = Logger.getLogger(getClass());
	
	final static String SQL_INSERT_HISTORY_LIST = "LOG_HISTORY.SQL_INSERT_HISTORY_LIST";
	final static String SQL_SEARCH_HISTORY_LIST = "LOG_HISTORY.SQL_SEARCH_HISTORY_LIST";
	final static String SQL_LOGIN_STATIC = "LOG_HISTORY.SQL_LOGIN_STATIC";
	
	@Override
	@Transactional
	public void insert(UserLoginHistoryDTO userLoginHistoryDTO) {
		try {
			dynamicJdbcDao.executeInsert(SQL_INSERT_HISTORY_LIST, Boolean.FALSE, userLoginHistoryDTO);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

	@Override
	public ServiceResult<Page<UserLoginHistoryDTO>> searchByUserId(String loginId, Pageable pageable) {
		ServiceResult<Page<UserLoginHistoryDTO>> result = new ServiceResult<Page<UserLoginHistoryDTO>>();
		
		try {
			Page<UserLoginHistoryDTO> resultList = dynamicJdbcDao.findForPage(SQL_SEARCH_HISTORY_LIST, 
					PrimitiveSafeBeanPropertyRowMapper.newInstance(UserLoginHistoryDTO.class), 
					pageable, 
					new SimpleKeyValue("loginId",loginId));
			result.setSuccess(Boolean.TRUE);
			result.setResult(resultList);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			setErrorResult(result, e);
		}

		return result;
	}

	@Override
	public ServiceResult<List<UserStaticDTO>> getUserLoginStatByDate(Date dateInput) {
		ServiceResult<List<UserStaticDTO>> result = new ServiceResult<List<UserStaticDTO>>();
		
		try {
			List<UserStaticDTO> resultList = dynamicJdbcDao.findForList(SQL_LOGIN_STATIC, 
					PrimitiveSafeBeanPropertyRowMapper.newInstance(UserStaticDTO.class), 
					new SimpleKeyValue("viewDate",dateInput));
			result.setSuccess(Boolean.TRUE);
			result.setResult(resultList);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			setErrorResult(result, e);
		}

		return result;
	}

}
