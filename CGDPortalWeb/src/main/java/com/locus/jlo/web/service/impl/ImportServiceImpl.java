package com.locus.jlo.web.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.locus.common.domain.ServiceResult;
import com.locus.common.domain.SimpleKeyValue;
import com.locus.common.jdbc.DynamicJdbcDao;
import com.locus.jlo.web.service.ImportService;

@Service
public class ImportServiceImpl implements ImportService{

	@Autowired
	@Qualifier("dynamicJdbcDao")
	private DynamicJdbcDao dynamicJdbcDao;
	
	private Logger logger = Logger.getLogger(getClass());
	
	public static final String SQL_IMPORT_CSV = "ATT.SQL_IMPORT_CSV";
	
	@Override
	public ServiceResult<Integer> importCSV(String importId,String fileLocation, String demiter,
			String newLine, String tableName) {
		// TODO Auto-generated method stub
		
		ServiceResult<Integer> serviceResult = null;
		logger.debug("Importing to DB : ...");
		try{
			int updateResult = dynamicJdbcDao.executeUpdate(SQL_IMPORT_CSV,  
					new SimpleKeyValue("IMPORT_ID", importId),
					new SimpleKeyValue("FILE_LOCATION", fileLocation),
					new SimpleKeyValue("DEMITER_STRING", demiter),//|
					new SimpleKeyValue("NEWLINE_STRING", newLine),//\n
					new SimpleKeyValue("TABLE_NAME", tableName));//TB_IMPORT_TEMP
			serviceResult = new ServiceResult<Integer>(updateResult);
			
			logger.debug("Importing to DB : updateResult : "+updateResult);
			serviceResult.setSuccess(true);
			
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			serviceResult = new ServiceResult<Integer>(e);
		}
		
		
		return serviceResult;
	}

}
