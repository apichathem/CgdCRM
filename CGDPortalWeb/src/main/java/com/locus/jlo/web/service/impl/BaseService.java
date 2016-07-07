package com.locus.jlo.web.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.support.TransactionTemplate;

import com.locus.common.domain.ServiceResult;
import com.locus.common.jdbc.DynamicJdbcDao;

public class BaseService {
	protected final String RES_CODE_SUCCESS = "0";
	protected final String RES_CODE_ERROR = "-1";
	protected final String RES_CODE_ERROR_DUPLICATE_DATA = "-2";
	protected final String RES_DESC_SUCCESS = "SUCCESS";
	protected final String RES_DESC_FAIL = "FAIL";
	
	@Autowired
	@Qualifier("dynamicJdbcDao")
	protected DynamicJdbcDao dynamicJdbcDao;
	
	@Autowired
	@Qualifier("transactionTemplate")
	protected TransactionTemplate transactionTemplate;
	
	protected <T> void setErrorResult(ServiceResult<T> result, Exception e) {
		result.setResult(null);
		result.setResponseCode(RES_CODE_ERROR);
		result.setResponseDescription(e.getMessage());
		result.setSuccess(Boolean.FALSE);
		result.setThrowable(e);
	}
	
	protected static final class IntegerResultMapper  implements RowMapper<Integer> {

	    public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
	        return rs.getInt(1);
	    }        
	}
	
	protected static final class DoubleResultMapper  implements RowMapper<Double> {

	    public Double mapRow(ResultSet rs, int rowNum) throws SQLException {
	        return rs.getDouble(1);
	    }        
	}
	
	protected static final class StringResultMapper  implements RowMapper<String> {

	    public String mapRow(ResultSet rs, int rowNum) throws SQLException {
	        return rs.getString(1);
	    }        
	}
}
