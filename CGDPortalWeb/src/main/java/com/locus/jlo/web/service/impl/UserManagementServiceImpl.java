package com.locus.jlo.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.locus.common.domain.ServiceResult;
import com.locus.common.domain.SimpleKeyValue;
import com.locus.common.mapper.PrimitiveSafeBeanPropertyRowMapper;
import com.locus.jlo.web.bean.dto.LoginDTO;
import com.locus.jlo.web.bean.dto.PositionDTO;
import com.locus.jlo.web.bean.dto.PrivilegeDTO;
import com.locus.jlo.web.bean.dto.UserInfoDTO;
import com.locus.jlo.web.bean.modelbean.ChangePasswordModelBean;
import com.locus.jlo.web.service.UserManagementService;

@Service
public class UserManagementServiceImpl extends BaseService implements UserManagementService {

	private Logger logger = Logger.getLogger(getClass());
	public static final String SQL_LOGIN_BY_USER_NAME = "USER.SQL_LOGIN_BY_USER_NAME";
	public static final String SQL_LOGIN_BY_USER_NAME_AND_EMAIL = "USER.SQL_LOGIN_BY_USER_NAME_AND_EMAIL";
	public static final String SQL_FIND_BY_CRITERIA = "USER.SQL_FIND_BY_CRITERIA";
	public static final String SQL_FIND_BY_USER_ID = "USER.SQL_FIND_BY_USER_ID";
	public static final String SQL_AGENT_INSERT = "USER.SQL_AGENT_INSERT";
	public static final String SQL_USR_INSERT = "USER.SQL_USR_INSERT";
	public static final String SQL_USR_ROLE_INSERT = "USER.SQL_USR_ROLE_INSERT";
	public static final String SQL_AGENT_UPDATE = "USER.SQL_AGENT_UPDATE";
	public static final String SQL_USR_UPDATE = "USER.SQL_USR_UPDATE";
	public static final String SQL_USR_ROLE_UPDATE = "USER.SQL_USR_ROLE_UPDATE";
	public static final String SQL_SEARCH_USER_PRIVILEGE = "USER.SQL_SEARCH_USER_PRIVILEGE";
	public static final String SQL_SEARCH_USER_LIST = "USER.SQL_SEARCH_USER_LIST";
	public static final String SQL_SEARCH_UNDER_USER_LIST = "USER.SQL_SEARCH_UNDER_USER_LIST";
	public static final String SQL_SEARCH_UNDER_USER_FREE_LIST = "USER.SQL_SEARCH_UNDER_USER_FREE_LIST";
	public static final String SQL_SAVE_USER_ASSIGNMENT = "USER.SQL_SAVE_USER_ASSIGNMENT";
	public static final String SQL_SAVE_USER_UNASSIGNMENT = "USER.SQL_SAVE_USER_UNASSIGNMENT";
	public static final String SQL_FIND_USER_IN_TEAM = "USER.SQL_FIND_USER_IN_TEAM";
	public static final String SQL_USR_PASSWORD_UPDATE = "USER.SQL_USR_PASSWORD_UPDATE";

	@Override
	public ServiceResult<UserInfoDTO> getUserInfo(String userName, String langCd) {
		ServiceResult<UserInfoDTO> serviceResult = new ServiceResult<UserInfoDTO>();
		try {
			UserInfoDTO result = dynamicJdbcDao.findForObject(SQL_LOGIN_BY_USER_NAME,
					PrimitiveSafeBeanPropertyRowMapper.newInstance(UserInfoDTO.class), new SimpleKeyValue("USER_NAME", userName)
					, new SimpleKeyValue("langCd", langCd));

			if (result == null) {
				serviceResult.setSuccess(false);
				serviceResult.setResponseCode("USR00001");
				serviceResult.setResponseDescription("Record is not found");
			} else {
				serviceResult.setResult(result);
				serviceResult.setSuccess(true);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			setErrorResult(serviceResult, e);
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<LoginDTO> getLoginInfo(String userName, String langCd) {
		ServiceResult<LoginDTO> serviceResult = new ServiceResult<LoginDTO>();
		LoginDTO loginInfo = null;
		try {
			ServiceResult<UserInfoDTO> userResult = getUserInfo(userName, langCd);
			if (userResult.isSuccess() && userResult.getResult() != null) {
				List<GrantedAuthority> result = new ArrayList<GrantedAuthority>();
				result.add((GrantedAuthority) new SimpleGrantedAuthority("LOGIN_READ"));
				loginInfo = new LoginDTO(userResult.getResult().getLoginId(), userResult.getResult().getPassword(), result);
				
				loginInfo.setUserInfo(userResult.getResult());
				serviceResult.setResult(loginInfo);
				serviceResult.setSuccess(true);
			} else {
				serviceResult.setSuccess(false);
				serviceResult.setResponseCode("USR00002");
				serviceResult.setResponseDescription("Username is not found or Invalid.");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			setErrorResult(serviceResult, e);
		}
		return serviceResult;
	}
	
	@Override
	public ServiceResult<Page<UserInfoDTO>> searchByCriteria(UserInfoDTO criteria, Pageable pageable,String langCd) {
		ServiceResult<Page<UserInfoDTO>> result = new ServiceResult<Page<UserInfoDTO>>();

		try {
			Page<UserInfoDTO> tpUsrMasterRes = dynamicJdbcDao.findForPage(SQL_FIND_BY_CRITERIA,
					PrimitiveSafeBeanPropertyRowMapper.newInstance(UserInfoDTO.class), pageable, 
					new SimpleKeyValue("agentNo", criteria.getAgentNo()),
					new SimpleKeyValue("firstName", criteria.getFirstName()),
					new SimpleKeyValue("lastName", criteria.getLastName()),
					new SimpleKeyValue("loginId", criteria.getLoginId()),
					new SimpleKeyValue("langCd", langCd));
			
			
			result.setSuccess(Boolean.TRUE);
			result.setResult(tpUsrMasterRes);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			setErrorResult(result, e);
		}

		return result;
	}

	@Override
	public ServiceResult<UserInfoDTO> searchByLoginId(String loginId) {
		ServiceResult<UserInfoDTO> result = new ServiceResult<UserInfoDTO>();

		try {
			UserInfoDTO tpUsrMasterRes = dynamicJdbcDao.findForObject(SQL_LOGIN_BY_USER_NAME, //with AGENT
					PrimitiveSafeBeanPropertyRowMapper.newInstance(UserInfoDTO.class), new SimpleKeyValue("USER_NAME", loginId));
			result.setSuccess(Boolean.TRUE);
			result.setResult(tpUsrMasterRes);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			setErrorResult(result, e);
		}

		return result;
	}
	
	@Override
	public ServiceResult<UserInfoDTO> searchByLoginIdAndEmail(String loginId,String email){
		
		ServiceResult<UserInfoDTO> result = new ServiceResult<UserInfoDTO>();

		try {
			UserInfoDTO tpUsrMasterRes = dynamicJdbcDao.findForObject(SQL_LOGIN_BY_USER_NAME_AND_EMAIL, //with AGENT
					PrimitiveSafeBeanPropertyRowMapper.newInstance(UserInfoDTO.class), 
					new SimpleKeyValue("USER_NAME", loginId),
					new SimpleKeyValue("EMAIL", email));
			result.setSuccess(Boolean.TRUE);
			result.setResult(tpUsrMasterRes);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			setErrorResult(result, e);
		}

		return result;
		
	}
	
	@Override
	public ServiceResult<UserInfoDTO> searchById(Integer id, String langCd) {
		ServiceResult<UserInfoDTO> result = new ServiceResult<UserInfoDTO>();

		try {
			UserInfoDTO tpUsrMasterRes = dynamicJdbcDao.findForObject(SQL_FIND_BY_USER_ID, //with AGENT
					PrimitiveSafeBeanPropertyRowMapper.newInstance(UserInfoDTO.class)
					, new SimpleKeyValue("USER_ID", id)
					, new SimpleKeyValue("langCd", langCd));
			
			result.setSuccess(Boolean.TRUE);
			result.setResult(tpUsrMasterRes);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			setErrorResult(result, e);
		}

		return result;
	}

	
	@Override
	public ServiceResult<List<PositionDTO>> searchPositionList(String userId) {
		List<PositionDTO> result = null;
		ServiceResult<List<PositionDTO>> serviceResult = null;

		try {
			result = dynamicJdbcDao.findForList("common.login.myPostionList_select",
					PrimitiveSafeBeanPropertyRowMapper.newInstance(PositionDTO.class), new SimpleKeyValue("loginId", userId));
			serviceResult = new ServiceResult<List<PositionDTO>>(result);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			serviceResult = new ServiceResult<List<PositionDTO>>(e);
		}

		return serviceResult;
	}

	@Override
	@Transactional
	public UserInfoDTO saveUsrMaster(UserInfoDTO tpUsrMaster) throws Exception {
		if(tpUsrMaster.getUserId() == null || "".equals(tpUsrMaster.getUserId())){
			//dynamicJdbcDao.executeUpdate(SQL_AGENT_INSERT, tpUsrMaster);
			Long id = dynamicJdbcDao.executeInsert(SQL_USR_INSERT, Boolean.TRUE, tpUsrMaster);
			logger.info("New USER_ID : " + id);
			tpUsrMaster.setUserId(new Integer(id.intValue()));
			dynamicJdbcDao.executeInsert(SQL_USR_ROLE_INSERT, Boolean.FALSE, tpUsrMaster);
		} else {
			dynamicJdbcDao.executeUpdate(SQL_AGENT_UPDATE, tpUsrMaster);
			dynamicJdbcDao.executeUpdate(SQL_USR_UPDATE, tpUsrMaster);
			dynamicJdbcDao.executeUpdate(SQL_USR_ROLE_UPDATE, tpUsrMaster);
			
		}
		
		return tpUsrMaster;
	}

	@Override
	public void updateDaleteFlag(Integer id) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<String> getUserIdInTeam(List<String> deptCodes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResult<List<PrivilegeDTO>> getMenuPrivilege(String roleId) {
		ServiceResult<List<PrivilegeDTO>> serviceResult = new ServiceResult<List<PrivilegeDTO>>();
		try {
			List<PrivilegeDTO> result = dynamicJdbcDao.findForList(SQL_SEARCH_USER_PRIVILEGE,
					PrimitiveSafeBeanPropertyRowMapper.newInstance(PrivilegeDTO.class), new SimpleKeyValue("roleId", roleId));
			serviceResult.setSuccess(Boolean.TRUE);
			serviceResult.setResult(result);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			setErrorResult(serviceResult, e);
		}
		
		return serviceResult;
	}

	@Override
	public ServiceResult<List<UserInfoDTO>> getUserList() {
		ServiceResult<List<UserInfoDTO>> result = new ServiceResult<List<UserInfoDTO>>();
		try {
			List<UserInfoDTO> res = dynamicJdbcDao.findForList(SQL_SEARCH_USER_LIST,
					PrimitiveSafeBeanPropertyRowMapper.newInstance(UserInfoDTO.class));
			result.setSuccess(Boolean.TRUE);
			result.setResult(res);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			setErrorResult(result, e);
		}
		
		return result;
	}

	@Override
	public ServiceResult<List<UserInfoDTO>> getUnderUserList(Integer userId) {
		ServiceResult<List<UserInfoDTO>> result = new ServiceResult<List<UserInfoDTO>>();
		try {
			List<UserInfoDTO> res = dynamicJdbcDao.findForList(SQL_SEARCH_UNDER_USER_LIST,
					PrimitiveSafeBeanPropertyRowMapper.newInstance(UserInfoDTO.class),
					new SimpleKeyValue("userId", userId));
			
			result.setSuccess(Boolean.TRUE);
			result.setResult(res);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			setErrorResult(result, e);
		}
		
		return result;
	}

	@Override
	public ServiceResult<List<UserInfoDTO>> getUnderUserFreeList(Integer roleId) {
		ServiceResult<List<UserInfoDTO>> result = new ServiceResult<List<UserInfoDTO>>();
		try {
			List<UserInfoDTO> res = dynamicJdbcDao.findForList(SQL_SEARCH_UNDER_USER_FREE_LIST,
					PrimitiveSafeBeanPropertyRowMapper.newInstance(UserInfoDTO.class),
					new SimpleKeyValue("roleId", roleId));
			
			result.setSuccess(Boolean.TRUE);
			result.setResult(res);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			setErrorResult(result, e);
		}
		
		return result;
	}

	@Override
	public void saveUserAssignment(Integer userId, List<String> assignUserId) {
		try {
			dynamicJdbcDao.executeUpdate(SQL_SAVE_USER_ASSIGNMENT, 
					new SimpleKeyValue("userId", userId),
					new SimpleKeyValue("assignUserId", assignUserId));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	@Override
	public void saveUserUnAssignment(Integer userId, List<String> assignUserId) {
		try {
			dynamicJdbcDao.executeUpdate(SQL_SAVE_USER_UNASSIGNMENT, 
					new SimpleKeyValue("userId", userId),
					new SimpleKeyValue("assignUserId", assignUserId));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	@Override
	public ServiceResult<List<String>> serachUserInTeam(Integer userId) {
		ServiceResult<List<String>> result = new ServiceResult<List<String>>();
		try {
			
			List<String> res = dynamicJdbcDao.findForList(SQL_FIND_USER_IN_TEAM, 
					new StringResultMapper(), 
					new SimpleKeyValue("userId", userId));
			result.setSuccess(Boolean.TRUE);
			result.setResult(res);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			setErrorResult(result, e);
		}
		return result;
	}

	@Override
	public ServiceResult<Boolean> updateNewPassword(ChangePasswordModelBean changePasswordBean) {
		ServiceResult<Boolean> result = new ServiceResult<Boolean>();
		try{
			int cnt = dynamicJdbcDao.executeUpdate(SQL_USR_PASSWORD_UPDATE, changePasswordBean);
			result.setSuccess(Boolean.TRUE);
			result.setResult(cnt==1);
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			setErrorResult(result, e);
		}
		return result;
	}

}