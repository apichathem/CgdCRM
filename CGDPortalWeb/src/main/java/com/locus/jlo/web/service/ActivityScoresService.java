package com.locus.jlo.web.service;

import java.util.List;

import com.locus.common.domain.ServiceResult;
import com.locus.jlo.web.bean.dto.ActivityScoresDTO;

public interface ActivityScoresService {

	ServiceResult<Long> insert(ActivityScoresDTO activityScoresDTO);

	void update(ActivityScoresDTO activityScoresDTO);

	ServiceResult<ActivityScoresDTO> findById(String actId);

	ServiceResult<List<ActivityScoresDTO>> findListByName(String actName);

}
