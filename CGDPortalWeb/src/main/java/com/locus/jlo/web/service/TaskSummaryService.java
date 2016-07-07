package com.locus.jlo.web.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.locus.common.domain.ServiceResult;
import com.locus.jlo.web.bean.dto.ContentDetailDTO;
import com.locus.jlo.web.bean.dto.MyTaskActivityDTO;
import com.locus.jlo.web.bean.dto.MyTaskServiceRequestDTO;

public interface TaskSummaryService {
	ServiceResult<Page<MyTaskActivityDTO>> searchActivityList(Pageable pageable, Integer ownerId, String langCd, List<String> ownerGroup);
	ServiceResult<Page<MyTaskServiceRequestDTO>> searchServiceRequestList(Pageable pageable, Integer ownerId, String langCd, List<String> ownerGroup);
	ServiceResult<Page<ContentDetailDTO>> searchKbList(Pageable pageable, String langCd, List<String> ownerGroup);
	int countPendingActivity(Integer ownerId, List<String> ownerGroup);
	int countPendingServiceRequest(Integer ownerId, List<String> ownerGroup);
//	int countRejectedKb(Integer ownerId, List<String> ownerGroup);
}
