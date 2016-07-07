package com.locus.jlo.web.service;

import java.util.List;
import java.util.Set;

import com.locus.common.domain.ServiceResult;
import com.locus.jlo.web.bean.dto.AttDTO;


public interface AttService {

	ServiceResult<AttDTO> selectById(String attId);
	ServiceResult<List<AttDTO>> searchAttachmentById(Set<String> attId) throws Exception;;
	ServiceResult<List<AttDTO>> searchAttachmentFileSendEmail(String actRefDocNo) throws Exception;
	ServiceResult<Long> insert(AttDTO att);

}
