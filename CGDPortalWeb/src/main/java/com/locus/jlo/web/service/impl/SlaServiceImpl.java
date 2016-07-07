package com.locus.jlo.web.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.locus.common.domain.ServiceResult;
import com.locus.common.domain.SimpleKeyValue;
import com.locus.common.mapper.PrimitiveSafeBeanPropertyRowMapper;
import com.locus.jlo.web.bean.dto.SlaDTO;
import com.locus.jlo.web.service.SlaService;

@Service
public class SlaServiceImpl extends BaseService implements SlaService {
	private static final Logger logger = Logger.getLogger(SlaServiceImpl.class);
	
	private static String GET_SLA_BY_CRITERIA = "SLA.GET_SLA_BY_CRITERIA";
	private static String GET_SLA_BY_ID = "SLA.GET_SLA_BY_ID";
	private static String GET_SLA_BY_CONTENT_CAT_ID = "SLA.GET_SLA_ID_BY_CONTENT_CAT_ID";
	private static String UPDATE = "SLA.UPDATE";
	private static String INSERT = "SLA.INSERT";
	private static String GET_SLA_LIST = "SLA.GET_SLA_LIST";
	
	@Override
	public ServiceResult<Page<SlaDTO>> searchByCriteria(String slaName, Pageable pageable, String langCd) {
		ServiceResult<Page<SlaDTO>> result = new ServiceResult<Page<SlaDTO>>();
		
		try {
			Page<SlaDTO> res = dynamicJdbcDao.findForPage(GET_SLA_BY_CRITERIA, 
					PrimitiveSafeBeanPropertyRowMapper.newInstance(SlaDTO.class), pageable, 
					new SimpleKeyValue("slaName", slaName),
					new SimpleKeyValue("langCd", langCd));
			
			result.setSuccess(Boolean.TRUE);
			result.setResult(res);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			setErrorResult(result, e);
		}
		
		return result;
	}

	@Override
	public ServiceResult<SlaDTO> seachById(Integer slaId) {
		ServiceResult<SlaDTO> result = new ServiceResult<SlaDTO>();
		
		try {
			SlaDTO res = dynamicJdbcDao.findForObject(GET_SLA_BY_ID, 
					PrimitiveSafeBeanPropertyRowMapper.newInstance(SlaDTO.class), 
					new SimpleKeyValue("slaId", slaId));
			
			result.setSuccess(Boolean.TRUE);
			result.setResult(res);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			setErrorResult(result, e);
		}
		
		return result;
	}

	@Override
	public void update(SlaDTO slaDTO) {
		try {
			dynamicJdbcDao.executeUpdate(UPDATE, slaDTO);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	@Override
	public ServiceResult<Long> insert(SlaDTO slaDTO) {
		ServiceResult<Long> result = new ServiceResult<Long>();
		try {
			Long slaId = dynamicJdbcDao.executeInsert(INSERT, Boolean.TRUE, slaDTO);
			result.setResult(slaId);
			result.setSuccess(Boolean.TRUE);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			setErrorResult(result, e);
		}
		return result;
	}

	@Override
	public ServiceResult<List<SlaDTO>> getSlaList() {
		ServiceResult<List<SlaDTO>> result = new ServiceResult<List<SlaDTO>>();
		
		try {
			List<SlaDTO> res = dynamicJdbcDao.findForList(GET_SLA_LIST, 
					PrimitiveSafeBeanPropertyRowMapper.newInstance(SlaDTO.class));
			
			result.setSuccess(Boolean.TRUE);
			result.setResult(res);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			setErrorResult(result, e);
		}
		
		return result;
	}

	@Override
	public ServiceResult<SlaDTO> seachByContentCatId(String contentCatId) {
		ServiceResult<SlaDTO> result = new ServiceResult<SlaDTO>();
		
		try {
			SlaDTO res = dynamicJdbcDao.findForObject(GET_SLA_BY_CONTENT_CAT_ID, 
					PrimitiveSafeBeanPropertyRowMapper.newInstance(SlaDTO.class), 
					new SimpleKeyValue("contentCatId", contentCatId));
			
			result.setSuccess(Boolean.TRUE);
			result.setResult(res);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			setErrorResult(result, e);
		}
		
		return result;
	}
	
}
