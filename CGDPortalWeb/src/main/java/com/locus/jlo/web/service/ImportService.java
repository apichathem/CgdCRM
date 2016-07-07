package com.locus.jlo.web.service;

import com.locus.common.domain.ServiceResult;

public interface ImportService {
	
	ServiceResult<Integer> importCSV(String importId,String fileLocation,String demiter,String newLine,String tableName);
	
}
