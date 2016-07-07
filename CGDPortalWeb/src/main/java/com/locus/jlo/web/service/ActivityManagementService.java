package com.locus.jlo.web.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.locus.common.domain.ServiceResult;
import com.locus.jlo.web.bean.criteria.ActivityCriteria;
import com.locus.jlo.web.bean.dto.ActivityAttFileDTO;
import com.locus.jlo.web.bean.dto.ActivityDTO;

public interface ActivityManagementService {
	
	ServiceResult<Page<ActivityDTO>> searchByCriteria(Pageable pageable,ActivityCriteria activityCriteria);
	ServiceResult<ActivityDTO> searchActivityListDetailByActNumber(String actNumber, String langCd) throws Exception;
	ServiceResult<List<ActivityAttFileDTO>> searchActivityDocumentList(String actDocNo);
	ServiceResult<Long> insertActivity(ActivityDTO dto);
	ServiceResult<Long> updateActivity(ActivityDTO dto);
	ServiceResult<Long> insertTpAttachment(ActivityAttFileDTO tpAttDTO);
	ServiceResult<Long> insertTpRelAttAttachment(ActivityAttFileDTO tpRelAttDTO);
	ServiceResult<Long> deleteAttByNumber(String relAttId) throws Exception;
	ServiceResult<Long> updateActAttFlg(String relAttId,String sendDocFlg,Integer chgId, Date chgTime) throws Exception;
	ServiceResult<Page<ActivityDTO>> findById(Pageable pageable,String actNumber, String langCd);
}