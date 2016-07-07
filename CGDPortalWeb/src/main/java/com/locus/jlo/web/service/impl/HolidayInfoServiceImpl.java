package com.locus.jlo.web.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.locus.common.domain.ServiceResult;
import com.locus.common.domain.SimpleKeyValue;
import com.locus.common.mapper.PrimitiveSafeBeanPropertyRowMapper;
import com.locus.jlo.web.bean.dto.HolidayDTO;
import com.locus.jlo.web.service.HolidayInfoService;

@Service
public class HolidayInfoServiceImpl extends BaseService implements HolidayInfoService {
	private Logger logger = Logger.getLogger(getClass());
	
	private static final String SQL_FIND_HOLIDAY_LIST = "HOLIDAY.SQL_FIND_HOLIDAY_LIST";
	private static final String SQL_FIND_HOLIDAY = "HOLIDAY.SQL_FIND_HOLIDAY";
	private static final String SQL_INSERT_HOLIDAY = "HOLIDAY.SQL_INSERT_HOLIDAY";
	private static final String SQL_UPDATE_HOLIDAY = "HOLIDAY.SQL_UPDATE_HOLIDAY";
	private static final String SQL_DELETE_HOLIDAY = "HOLIDAY.SQL_DELETE_HOLIDAY";

	@Override
	public ServiceResult<List<HolidayDTO>> searchHolidayByYear(Integer year, String langCd) {
		logger.info("searchHolidayByYear");
		
		ServiceResult<List<HolidayDTO>> result = new ServiceResult<List<HolidayDTO>>();
		
		try {
			List<HolidayDTO> holidayResultList = dynamicJdbcDao.findForList(SQL_FIND_HOLIDAY_LIST
					, PrimitiveSafeBeanPropertyRowMapper.newInstance(HolidayDTO.class)
					, new SimpleKeyValue("year", year)
					, new SimpleKeyValue("langCd", langCd));
			result.setSuccess(Boolean.TRUE);
			result.setResult(holidayResultList);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			setErrorResult(result, e);
		}
		return result;
	}

	@Override
	public ServiceResult<HolidayDTO> searchHolidayById(Integer holidayId) {
		logger.info("searchHolidayById");
		ServiceResult<HolidayDTO> result = new ServiceResult<HolidayDTO>();
		
		try {
			HolidayDTO holidayResultList = dynamicJdbcDao.findForObject(SQL_FIND_HOLIDAY
					, PrimitiveSafeBeanPropertyRowMapper.newInstance(HolidayDTO.class)
					, new SimpleKeyValue("holidayId", holidayId));
			result.setSuccess(Boolean.TRUE);
			result.setResult(holidayResultList);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			setErrorResult(result, e);
		}
		return result;
	}

	@Override
	@Transactional
	public void deleteHoliday(Integer holidayId) {
		logger.info("deleteHoliday");
		try {
			dynamicJdbcDao.executeUpdate(SQL_DELETE_HOLIDAY, new SimpleKeyValue("holidayId", holidayId));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	@Override
	@Transactional
	public HolidayDTO insertHoliday(HolidayDTO tpHolidayInfo) {
		logger.info("insertHoliday");
		try {
			Long res = dynamicJdbcDao.executeInsert(SQL_INSERT_HOLIDAY, Boolean.TRUE, tpHolidayInfo);
			tpHolidayInfo.setHolidayId(res.intValue());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return tpHolidayInfo;
	}

	@Override
	@Transactional
	public HolidayDTO updateHoliday(HolidayDTO tpHolidayInfo) {
		logger.info("updateHoliday");
		try {
			dynamicJdbcDao.executeUpdate(SQL_UPDATE_HOLIDAY, tpHolidayInfo);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return tpHolidayInfo;
	}
}
