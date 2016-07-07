package com.locus.jlo.web.service;

import java.util.List;
import java.util.Map;

import com.locus.common.domain.ServiceResult;
import com.locus.jlo.web.bean.dto.BranchDTO;

public interface BranchService {
	ServiceResult<List<BranchDTO>> findAllBranch() throws Exception;

	ServiceResult<BranchDTO> findByBranchCode(String branchCode) throws Exception;

	ServiceResult<Long> updateBranchAddrLatLng(String contentId, String latitude, String longtitude, Integer userId)
			throws Exception;
	
	ServiceResult<Map<String, List<BranchDTO>>> findAllBranchWithUnitName();
	
}
