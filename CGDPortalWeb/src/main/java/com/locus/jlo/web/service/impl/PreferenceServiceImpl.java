package com.locus.jlo.web.service.impl;

import org.apache.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import com.locus.common.domain.ServiceResult;
import com.locus.common.domain.SimpleKeyValue;
import com.locus.jlo.web.bean.dto.PreferenceDTO;
import com.locus.jlo.web.service.PreferenceService;


@Service
public class PreferenceServiceImpl extends BaseService implements PreferenceService {
	private static final Logger logger = Logger.getLogger(PreferenceServiceImpl.class);
	
	private static final String FIND_BY_CRITERIA = "PREF.FIND_BY_CRITERIA";
	private static final String FIND_BY_ID = "PREF.FIND_BY_ID";
	private static final String FIND_BY_NAME = "PREF.FIND_BY_NAME";
	private static final String INSERT = "PREF.INSERT";
	private static final String UPDATE = "PREF.UPDATE";
	
	@Override
	public ServiceResult<Page<PreferenceDTO>> searchByCriteria(String name, String value, Pageable pageable) {
		ServiceResult<Page<PreferenceDTO>> result = new ServiceResult<Page<PreferenceDTO>>();
		
		try {
			Page<PreferenceDTO> res = dynamicJdbcDao.findForPage(FIND_BY_CRITERIA, 
											BeanPropertyRowMapper.newInstance(PreferenceDTO.class),
											pageable,
											new SimpleKeyValue("prefName", name), 
											new SimpleKeyValue("prefValue", value));
			result.setSuccess(Boolean.TRUE);
			result.setResult(res);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			setErrorResult(result, e);
		}
		return result;
	}

	@Override
	public ServiceResult<PreferenceDTO> searchById(Integer prefId) {
		ServiceResult<PreferenceDTO> result = new ServiceResult<PreferenceDTO>();
		
		try {
			PreferenceDTO preferenceDTO = dynamicJdbcDao.findForObject(FIND_BY_ID, 
													BeanPropertyRowMapper.newInstance(PreferenceDTO.class), 
													new SimpleKeyValue("prefId",prefId));
			result.setSuccess(Boolean.TRUE);
			result.setResult(preferenceDTO);
		} catch (Exception e) {
			logger.error(e.getMessage());
			setErrorResult(result, e);
		}
		return result;
	}

	@Override
	public ServiceResult<PreferenceDTO> save(PreferenceDTO preferenceDTO) {
		ServiceResult<PreferenceDTO> result = new ServiceResult<PreferenceDTO>();
		
		try {
			if (preferenceDTO.getPrefId() != null) {
				// Update
				dynamicJdbcDao.executeUpdate(UPDATE, preferenceDTO);
				result.setResult(preferenceDTO);
			} else {
				// Insert
				Long res = dynamicJdbcDao.executeInsert(INSERT, Boolean.TRUE, preferenceDTO);
				preferenceDTO.setPrefId(res.intValue());
				result.setResult(preferenceDTO);
			}
			result.setSuccess(Boolean.TRUE);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			setErrorResult(result, e);
		}
		return result;
	}

	@Override
	public ServiceResult<PreferenceDTO> searchByName(String prefName) {
		ServiceResult<PreferenceDTO> serviceResult = new ServiceResult<PreferenceDTO>();
		
		try {
			PreferenceDTO preferenceDTO = dynamicJdbcDao.findForObject(FIND_BY_NAME, 
					BeanPropertyRowMapper.newInstance(PreferenceDTO.class), 
					new SimpleKeyValue("prefName",prefName));
			serviceResult.setSuccess(Boolean.TRUE);
			serviceResult.setResult(preferenceDTO);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			setErrorResult(serviceResult, e);
		}
		
		return serviceResult;
	}

}
