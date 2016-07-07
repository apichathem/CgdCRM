package com.locus.jlo.web.service;

import java.util.Map;

import com.locus.common.domain.ServiceResult;

public interface DropdownService {
	ServiceResult<Map<Integer, String>> getRoleSelect();
//	ServiceResult<Map<String, TpCodebook>> getCodeNameSelect();
	ServiceResult<Map<String, String>> getDepartmentSelect();
//	ServiceResult<Map<String, TpCodebook>> getActivityTypeSelect();
//	ServiceResult<Map<String, TpCodebook>> getPositionSelect();
	ServiceResult<Map<Integer, String>> getUserSelect();
	ServiceResult<Map<Integer, String>> getSlaSelect();
}
