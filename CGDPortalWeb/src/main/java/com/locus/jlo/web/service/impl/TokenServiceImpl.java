package com.locus.jlo.web.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import com.locus.common.domain.ServiceResult;
import com.locus.common.domain.SimpleKeyValue;
import com.locus.common.jdbc.DynamicJdbcDao;
import com.locus.common.utils.DateTimeUtils;
import com.locus.jlo.web.bean.dto.TokenDTO;
import com.locus.jlo.web.service.TokenService;

@Service
public class TokenServiceImpl implements TokenService {
	
	@Autowired
	@Qualifier("dynamicJdbcDao")
	private DynamicJdbcDao dynamicJdbcDao;
	
	@Autowired
	private MessageSource messageSource;
	
	private Logger logger = Logger.getLogger(getClass());
	
	public static final String SQL_GENERATE_TOKEN = "TOKEN.SQL_GENERATE_TOKEN";
	public static final String SQL_UPDATE_TOKEN = "TOKEN.SQL_UPDATE_TOKEN";
	public static final String SQL_CHECK_TOKEN_EXIST_BY_DEVICE_ID_AND_USER_NAME = "TOKEN.SQL_CHECK_TOKEN_EXIST_BY_DEVICE_ID_AND_USER_NAME";
	public static final String SQL_DESTROY_TOKEN = "TOKEN.SQL_DESTROY_TOKEN";
	public static final String SQL_VALIDATE_TOKEN = "TOKEN.SQL_VALIDATE_TOKEN";
	public static final String SQL_UPDATE_PASSCODE = "TOKEN.SQL_UPDATE_PASSCODE";
	public static final String SQL_GET_PASSCODE = "TOKEN.SQL_GET_PASSCODE";

	@Override
	public ServiceResult<TokenDTO> generateToken(
			TokenDTO tokenDTO) {
		
		ServiceResult<TokenDTO> serviceResult = new ServiceResult<TokenDTO>();
		String SQL_STMT = "";
		try{
			Long result = dynamicJdbcDao.findForLong(SQL_CHECK_TOKEN_EXIST_BY_DEVICE_ID_AND_USER_NAME,
					new SimpleKeyValue("USER_NAME", tokenDTO.getUserName()),
					new SimpleKeyValue("DEVICE_ID", tokenDTO.getDeviceId()));//from controller
			if(result >= 1){
				SQL_STMT = SQL_UPDATE_TOKEN;
			}else{
				SQL_STMT = SQL_GENERATE_TOKEN;
			}
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			serviceResult = new ServiceResult<TokenDTO>(e);
		}
		
		
		try{
			int result = dynamicJdbcDao.executeUpdate(SQL_STMT,
					new SimpleKeyValue("TOKEN_CODE", tokenDTO.getTokenCode()),
					new SimpleKeyValue("CREATED_BY", tokenDTO.getCreatedBy()),//from controller
					new SimpleKeyValue("CREATED_DATE", DateTimeUtils.getDate()),
					new SimpleKeyValue("CREATED_TIME", DateTimeUtils.getTime()),
					new SimpleKeyValue("EXPIRED_DATE", tokenDTO.getExpiredDate()),//from controller
					new SimpleKeyValue("EXPIRED_TIME", tokenDTO.getExpiredTime()),//from controller
					new SimpleKeyValue("USER_NAME", tokenDTO.getUserName()),
					new SimpleKeyValue("SESSION_ID", tokenDTO.getSessionId()),
					new SimpleKeyValue("DEVICE_ID", tokenDTO.getDeviceId()));//from controller
			if(result == 1){
				serviceResult.setSuccess(true);
				serviceResult = new ServiceResult<TokenDTO>(tokenDTO);
			}else{
				serviceResult.setSuccess(false);
				serviceResult.setResponseCode("TOK00001");
				serviceResult.setResponseDescription("Token generate failed.");
			}
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			serviceResult = new ServiceResult<TokenDTO>(e);
		}			
		return serviceResult;
	}

	@Override
	public ServiceResult<Integer> destroyToken(
			TokenDTO token) {
		ServiceResult<Integer> serviceResult = new ServiceResult<Integer>();
		try{
			int result = dynamicJdbcDao.executeUpdate(SQL_DESTROY_TOKEN,
					new SimpleKeyValue("TOKEN_CODE", token.getTokenCode()));
			
			if(result == 1){
				serviceResult.setSuccess(true);
				serviceResult = new ServiceResult<Integer>(1);
			}else{
				serviceResult.setSuccess(false);
				serviceResult.setResponseCode("TOK00002");
				serviceResult.setResponseDescription("Token destroy failed.");
			}
		}catch(Exception e){
			logger.debug(e.getMessage(), e);
			serviceResult = new ServiceResult<Integer>(e);
		}			
		return serviceResult;
	}

	@Override
	public ServiceResult<Integer> validateToken(TokenDTO tokenRequest) {
		ServiceResult<Integer> serviceResult = new ServiceResult<Integer>();
		try{
			List<TokenDTO> resultList = dynamicJdbcDao.findForList(SQL_VALIDATE_TOKEN,BeanPropertyRowMapper.newInstance(TokenDTO.class),
					new SimpleKeyValue("TOKEN_CODE", tokenRequest.getTokenCode()),
					new SimpleKeyValue("CURRENT_DATETIME", DateTimeUtils.getDate()+DateTimeUtils.getTime()));
	
			if(resultList!=null && resultList.size() > 0){
				serviceResult.setSuccess(true);
				serviceResult = new ServiceResult<Integer>(1);
			}else{
				serviceResult.setSuccess(false);
				serviceResult.setResponseCode("TOK00003");
				serviceResult.setResponseDescription("Token is invalid or expired");
			}
		}catch(Exception e){
			logger.debug(e.getMessage(), e);
			serviceResult = new ServiceResult<Integer>(e);
		}			
		return serviceResult;
	}
	
	
	@Override
	public ServiceResult<Integer> updatePasscode(TokenDTO token) {
		ServiceResult<Integer> serviceResult = new ServiceResult<Integer>();
		try{
			int result = dynamicJdbcDao.executeUpdate(SQL_UPDATE_PASSCODE,
					new SimpleKeyValue("PASSCODE", token.getPasscode()),
					new SimpleKeyValue("USER_NAME", token.getUserName()),
					new SimpleKeyValue("DEVICE_ID", token.getDeviceId()));//from controller
			if(result == 1){
				serviceResult.setSuccess(true);
				serviceResult = new ServiceResult<Integer>(1);
			}else{
				serviceResult.setSuccess(false);
				serviceResult.setResponseCode("TOK00002");
				serviceResult.setResponseDescription("Passcode update failed.");
			}
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			serviceResult = new ServiceResult<Integer>(e);
		}			
		return serviceResult;
	}
	
	
	@Override
	public ServiceResult<TokenDTO> getPasscode(TokenDTO token) {
		ServiceResult<TokenDTO> serviceResult = new ServiceResult<TokenDTO>();
		try{
			TokenDTO result = dynamicJdbcDao.findForObject(SQL_GET_PASSCODE,BeanPropertyRowMapper.newInstance(TokenDTO.class),
					new SimpleKeyValue("PASSCODE", token.getPasscode()),
					new SimpleKeyValue("USER_NAME", token.getUserName()),
					new SimpleKeyValue("DEVICE_ID", token.getDeviceId()));//from controller
			if(result!=null){
				serviceResult.setSuccess(true);
				serviceResult = new ServiceResult<TokenDTO>(result);
			}else{
				serviceResult.setSuccess(false);
				serviceResult.setResponseCode("TOK00002");
				serviceResult.setResponseDescription("Passcode update failed.");
			}
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			serviceResult = new ServiceResult<TokenDTO>(e);
		}			
		return serviceResult;
	}
	
}
