package com.locus.jlo.web.service;

import java.util.List;

import com.locus.common.domain.ServiceResult;
import com.locus.jlo.web.bean.dto.PositionDTO;

public interface PositionService {
	ServiceResult<List<PositionDTO>> findAllPosition();
}
