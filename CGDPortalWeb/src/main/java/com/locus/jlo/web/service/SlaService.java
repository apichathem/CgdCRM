package com.locus.jlo.web.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.locus.common.domain.ServiceResult;
import com.locus.jlo.web.bean.dto.SlaDTO;

public interface SlaService {
	ServiceResult<Page<SlaDTO>> searchByCriteria(String slaName, Pageable pageable, String langCd);
	ServiceResult<SlaDTO> seachById(Integer slaId);
	void update(SlaDTO slaDTO);
	ServiceResult<Long> insert(SlaDTO slaDTO);
	ServiceResult<List<SlaDTO>> getSlaList();
	ServiceResult<SlaDTO> seachByContentCatId(String contentCatId);
}
