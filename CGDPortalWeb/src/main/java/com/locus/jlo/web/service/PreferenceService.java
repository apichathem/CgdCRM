package com.locus.jlo.web.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.locus.common.domain.ServiceResult;
import com.locus.jlo.web.bean.dto.PreferenceDTO;

public interface PreferenceService {
	ServiceResult<Page<PreferenceDTO>> searchByCriteria(String name, String value, Pageable pageable);
	ServiceResult<PreferenceDTO> searchById(Integer prefId);
	ServiceResult<PreferenceDTO> save(PreferenceDTO preferenceDTO);
	ServiceResult<PreferenceDTO> searchByName(String prefName);
}
