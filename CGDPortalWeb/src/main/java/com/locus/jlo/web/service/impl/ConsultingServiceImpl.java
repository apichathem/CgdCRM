package com.locus.jlo.web.service.impl;

import org.apache.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import com.locus.common.domain.ServiceResult;
import com.locus.common.domain.SimpleKeyValue;
import com.locus.common.mapper.PrimitiveSafeBeanPropertyRowMapper;
import com.locus.jlo.web.service.ConsultingService;
import com.locus.jlo.web.bean.criteria.ContactCriteria;
import com.locus.jlo.web.bean.dto.ConsultingDTO;
import com.locus.jlo.web.bean.dto.ConsultingRelDTO;
import com.locus.jlo.web.bean.dto.ContactDTO;

@Service
public class ConsultingServiceImpl extends BaseService implements
		ConsultingService {
	private Logger logger = Logger.getLogger(getClass());
	
	final static String SQL_INSERT = "CONSULTING.SQL_INSERT";
	final static String SQL_UPDATE = "CONSULTING.SQL_UPDATE";
	final static String SQL_FIND = "CONSULTING.SQL_FIND";
	final static String SQL_INSERT_TP_REL_CONSULTING = "CONSULTING.SQL_INSERT_TP_REL_CONSULTING";
	final static String SQL_FIND_CUSTOMER_BY_ID = "CONSULTING.SQL_FIND_CUSTOMER_BY_ID";
	final static String SQL_FIND_CONTACT_LIST = "CONSULTING.SQL_FIND_CONTACT_LIST";
	
	@Override
	public ServiceResult<ConsultingDTO> insertInital(ConsultingDTO inital) {
		ServiceResult<ConsultingDTO> result = null;
		try {
			Long insertId = dynamicJdbcDao.executeInsert(SQL_INSERT, false, inital);
			if(insertId>0){
				result = new ServiceResult<ConsultingDTO>(inital);
			}else{
				result = new ServiceResult<ConsultingDTO>(new Exception("InsertError"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			result = new ServiceResult<ConsultingDTO>(e);
		}
		
		return result;
	}

	@Override
	public ServiceResult<Long> update(ConsultingDTO dto) {
		ServiceResult<Long> result = null;
		try {
			int countrow = dynamicJdbcDao.executeUpdate(SQL_UPDATE, dto);
			result = new ServiceResult<Long>(new Long(countrow));
			
		} catch (Exception e) {
			e.printStackTrace();
			result = new ServiceResult<Long>(e);
		}
		
		return result;
	}

	@Override
	public ServiceResult<Page<ConsultingDTO>> find(ConsultingDTO obj, Pageable pageable) {
		ServiceResult<Page<ConsultingDTO>> serviceResult = null;
		try{
			Page<ConsultingDTO> result = dynamicJdbcDao.findForPage(SQL_FIND
					, BeanPropertyRowMapper.newInstance(ConsultingDTO.class)
					, pageable
					, obj);
			serviceResult = new ServiceResult<Page<ConsultingDTO>>(result);
		}catch(Exception e){
			serviceResult = new ServiceResult<Page<ConsultingDTO>>(e);
			e.printStackTrace();
		}
		
		return serviceResult;
	}

	@Override
	public ServiceResult<Long> insertTpRelConsulting(ConsultingRelDTO inital) {
		
		Long result = null;
		ServiceResult<Long> serviceResult = null;
		
		try {
			  result = dynamicJdbcDao.executeInsert(SQL_INSERT_TP_REL_CONSULTING, false,
					  	new SimpleKeyValue("consultingId",inital.getConsultingId()),
						new SimpleKeyValue("refId",inital.getRefId()));
			  
			  serviceResult = new ServiceResult<Long>(result);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			serviceResult = new ServiceResult<Long>(e);
		}
		
		return serviceResult;
	}

	@Override
	public ServiceResult<ConsultingDTO> searchConsultingCustomerById(String custId) {
		
		
		ServiceResult<ConsultingDTO> serviceResult = new ServiceResult<ConsultingDTO>();
		ConsultingDTO detailDTO = new ConsultingDTO();
		try {
			detailDTO = dynamicJdbcDao.findForObject(SQL_FIND_CUSTOMER_BY_ID,
							 BeanPropertyRowMapper.newInstance(ConsultingDTO.class)
							,new SimpleKeyValue("custId", custId));
			
			serviceResult.setSuccess(Boolean.TRUE);
			serviceResult.setResult(detailDTO);
			
		} catch (Exception e){
			logger.error(e.getMessage(), e);
			setErrorResult(serviceResult, e);
		}
		return serviceResult;
		
	}

	@Override
	public ServiceResult<Page<ContactDTO>> searchContactList(Pageable pageable,
			ContactCriteria contactCriteria, String custId) {
		ServiceResult<Page<ContactDTO>> serviceResult = new ServiceResult<Page<ContactDTO>>();
		Page<ContactDTO> result = null;
		try{
			result = dynamicJdbcDao.findForPage(SQL_FIND_CONTACT_LIST, PrimitiveSafeBeanPropertyRowMapper.newInstance(ContactDTO.class), pageable, 
					contactCriteria, new SimpleKeyValue("custId",custId));
			serviceResult.setSuccess(Boolean.TRUE);
			serviceResult.setResult(result);
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			setErrorResult(serviceResult, e);
		}
		return serviceResult;
	}

	 
}
