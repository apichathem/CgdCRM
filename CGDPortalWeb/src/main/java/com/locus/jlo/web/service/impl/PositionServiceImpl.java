package com.locus.jlo.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.locus.common.domain.ServiceResult;
import com.locus.jlo.web.bean.dto.PositionDTO;
import com.locus.jlo.web.service.PositionService;

@Service
public class PositionServiceImpl extends BaseService implements PositionService{

	@Override
	public ServiceResult<List<PositionDTO>> findAllPosition() {
		List<PositionDTO> positionDTOs = new ArrayList<>();
		ServiceResult<List<PositionDTO>> result = new ServiceResult<>(positionDTOs);
		for (int i = 0; i < 5; i++) {
			PositionDTO positionDTO = new PositionDTO();
			positionDTO.setPositionId(String.valueOf(i));
			positionDTO.setPositionName("Position " + (i + 1));
			positionDTOs.add(positionDTO);
		}
		return result;
	}

}
