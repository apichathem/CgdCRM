package com.locus.jlo.web.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.locus.common.domain.ServiceResult;
import com.locus.common.domain.SimpleKeyValue;
import com.locus.common.mapper.PrimitiveSafeBeanPropertyRowMapper;
import com.locus.jlo.web.bean.dto.AgentProfileDTO;
import com.locus.jlo.web.bean.dto.AgentScoreDTO;
import com.locus.jlo.web.service.AgentService;

@Service
public class AgentServiceImpl extends BaseService implements AgentService {

	final static String SQL_AGENT_BY_AGENT_CODE = "AGENT.SQL_AGENT_BY_AGENT_CODE";
	final static String SQL_AGENT_SCORE_TOP_10 = "AGENT.SQL_AGENT_SCORE_TOP_10";
	final static String SQL_AGENT_SCORE_NEAR_BY = "AGENT.SQL_AGENT_SCORE_NEAR_BY";
	final static String SQL_CHECK_AGENT_REGISTER_LOG = "AGENT.SQL_CHECK_AGENT_REGISTER_LOG";
	final static String SQL_INSERT_AGENT_REGISTER_LOG = "AGENT.SQL_INSERT_AGENT_REGISTER_LOG";

	@Override
	public ServiceResult<AgentProfileDTO> searchAgentByAgentCode(String agentCode) throws Exception {
		AgentProfileDTO result = dynamicJdbcDao.findForObject(SQL_AGENT_BY_AGENT_CODE, PrimitiveSafeBeanPropertyRowMapper.newInstance(AgentProfileDTO.class),
				new SimpleKeyValue("AGENT_CODE", agentCode));
		return new ServiceResult<AgentProfileDTO>(result);
	}

	@Override
	public ServiceResult<List<AgentScoreDTO>> findAgentScoreTopTen(String agentCode, String startDate, String endDate) throws Exception {
		List<AgentScoreDTO> result = dynamicJdbcDao.findForList(SQL_AGENT_SCORE_TOP_10, PrimitiveSafeBeanPropertyRowMapper.newInstance(AgentScoreDTO.class),
				new SimpleKeyValue("AGENT_CODE", agentCode), new SimpleKeyValue("START_DATE", startDate), new SimpleKeyValue("END_DATE", endDate));
		return new ServiceResult<List<AgentScoreDTO>>(result);
	}

	@Override
	public ServiceResult<List<AgentScoreDTO>> findAgentScoreNearBy(String agentCode, String startDate, String endDate, int diff) throws Exception {
		List<AgentScoreDTO> result = dynamicJdbcDao
				.findForList(SQL_AGENT_SCORE_NEAR_BY, PrimitiveSafeBeanPropertyRowMapper.newInstance(AgentScoreDTO.class), new SimpleKeyValue("AGENT_CODE", agentCode),
						new SimpleKeyValue("START_DATE", startDate), new SimpleKeyValue("END_DATE", endDate), new SimpleKeyValue("DIFF", diff));
		return new ServiceResult<List<AgentScoreDTO>>(result);
	}

	@Override
	@Transactional
	public ServiceResult<Boolean> register(String agentCode, String idCardNo, String birthDate) throws Exception {
		ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
		Long cnt = dynamicJdbcDao.findForLong(SQL_CHECK_AGENT_REGISTER_LOG, new SimpleKeyValue("AGENT_CODE", agentCode));
		if (cnt > 0) {
			serviceResult.setSuccess(false);
			serviceResult.setResult(false);
			serviceResult.setResponseDescription("ไม่สามารถลงทะเบียนได้ เนื่องจากรหัสตัวแทนของท่านเคยลงทะเบียนไว้แล้ว");
			return serviceResult;
		}
		AgentProfileDTO result = dynamicJdbcDao.findForObject(SQL_AGENT_BY_AGENT_CODE, PrimitiveSafeBeanPropertyRowMapper.newInstance(AgentProfileDTO.class),
				new SimpleKeyValue("AGENT_CODE", agentCode));
		if (result != null) {
			if (!result.getIdCardNo().equals(idCardNo) || !result.getBirthDate().equals(birthDate)) {
				serviceResult.setSuccess(false);
				serviceResult.setResult(false);
				serviceResult.setResponseDescription("ไม่สามารถลงทะเบียนได้ เนื่องจากข้อมูลไม่ถูกต้อง กรุณาตรวจสอบข้อมูลของท่านอีกครั้ง");
			} else {
				dynamicJdbcDao.executeUpdate(SQL_INSERT_AGENT_REGISTER_LOG, new SimpleKeyValue("AGENT_CODE", agentCode));
				serviceResult.setSuccess(true);
				serviceResult.setResult(true);
			}
		} else {
			serviceResult.setSuccess(false);
			serviceResult.setResult(false);
		}
		return serviceResult;
	}

}
