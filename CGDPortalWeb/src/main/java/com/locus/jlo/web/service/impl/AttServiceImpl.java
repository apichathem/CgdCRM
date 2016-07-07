package com.locus.jlo.web.service.impl;

import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import com.locus.common.domain.ServiceResult;
import com.locus.common.domain.SimpleKeyValue;
import com.locus.jlo.web.bean.dto.AttDTO;
import com.locus.jlo.web.service.AttService;

@Service
public class AttServiceImpl extends BaseService implements AttService {

	private Logger logger = Logger.getLogger(getClass());
	
	private static final String SQL_FIND_TP_ATT_BY_ID = "ATT.SQL_FIND_TP_ATT_BY_ID";
	private static final String SQL_FIND_TP_ATT_WHERE_IN_BY_ID = "ATT.SQL_FIND_TP_ATT_WHERE_IN_BY_ID";
	private static final String SQL_FIND_ATTACHE_FILE_SEND_EMAIL = "ATT.SQL_FIND_ATTACHE_FILE_SEND_EMAIL";
	public static final String SQL_INSERT_TP_ATT = "ATT.SQL_INSERT_TP_ATT";
	
	@Override
	public ServiceResult<AttDTO> selectById(String attId) {
		logger.debug("AttServiceImpl : selectById");
		logger.debug("********************************************");	
		ServiceResult<AttDTO> serviceResult = null;
		AttDTO result = null;		
		try{
			result = dynamicJdbcDao.findForObject(SQL_FIND_TP_ATT_BY_ID, BeanPropertyRowMapper.newInstance(AttDTO.class), 
					new SimpleKeyValue("ATT_ID", attId));
			serviceResult = new ServiceResult<AttDTO>(result);
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			serviceResult = new ServiceResult<AttDTO>(e);
		}
		
		return serviceResult;
	}

	@Override
	public ServiceResult<List<AttDTO>> searchAttachmentById(Set<String> attId) throws Exception {
		
			ServiceResult<List<AttDTO>> serviceResult = new ServiceResult<List<AttDTO>>();
			List<AttDTO> result = null;		
			
			try{
				result = dynamicJdbcDao.findForList(SQL_FIND_TP_ATT_WHERE_IN_BY_ID
										,BeanPropertyRowMapper.newInstance(AttDTO.class)
										,new SimpleKeyValue("attId",attId));
				
				serviceResult = new ServiceResult<List<AttDTO>>(result);
				
				logger.info("Element result :: "+serviceResult.getResult().size());
				
			}catch(Exception e){
				logger.error(e.getMessage(), e);
				
				serviceResult = new ServiceResult<List<AttDTO>>(e);
			}
			return serviceResult;
	}
	
	
	@Override
	public ServiceResult<List<AttDTO>> searchAttachmentFileSendEmail(String actRefDocNo) throws Exception {
		
			ServiceResult<List<AttDTO>> serviceResult = new ServiceResult<List<AttDTO>>();
			List<AttDTO> result = null;		
			
			try{
				result = dynamicJdbcDao.findForList(SQL_FIND_ATTACHE_FILE_SEND_EMAIL
										,BeanPropertyRowMapper.newInstance(AttDTO.class)
										,new SimpleKeyValue("actRefDocNo",actRefDocNo));
				
				serviceResult = new ServiceResult<List<AttDTO>>(result);
				
				logger.info("Element result :: "+serviceResult.getResult().size());
				
			}catch(Exception e){
				logger.error(e.getMessage(), e);
				
				serviceResult = new ServiceResult<List<AttDTO>>(e);
			}
			return serviceResult;
	}

	@Override
	public ServiceResult<Long> insert(AttDTO att) {
		ServiceResult<Long> out = null;
		try{
			//TP_ATT
			Long insertId = dynamicJdbcDao.executeInsert(SQL_INSERT_TP_ATT,true,att);
			att.setAttId(insertId);
			out = new ServiceResult<Long>(insertId);
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			out = new ServiceResult<Long>(e);
		}
		return out;
	}
 

}
