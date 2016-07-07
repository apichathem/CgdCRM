package com.locus.jlo.web.service;

import java.util.List;

import com.locus.common.domain.ServiceResult;
import com.locus.jlo.web.bean.dto.AgentProfileDTO;
import com.locus.jlo.web.bean.dto.AgentScoreDTO;

public interface AgentService {

	ServiceResult<Boolean> register(String agentCode, String idCardNo, String birthDate) throws Exception;

	ServiceResult<AgentProfileDTO> searchAgentByAgentCode(String agentCode) throws Exception;

	ServiceResult<List<AgentScoreDTO>> findAgentScoreTopTen(String agentCode, String startDate, String endDate) throws Exception;

	ServiceResult<List<AgentScoreDTO>> findAgentScoreNearBy(String agentCode, String startDate, String endDate, int diff) throws Exception;
}
