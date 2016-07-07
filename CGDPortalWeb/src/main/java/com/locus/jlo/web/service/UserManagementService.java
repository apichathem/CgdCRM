package com.locus.jlo.web.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.locus.common.domain.ServiceResult;
import com.locus.jlo.web.bean.dto.LoginDTO;
import com.locus.jlo.web.bean.dto.PositionDTO;
import com.locus.jlo.web.bean.dto.PrivilegeDTO;
import com.locus.jlo.web.bean.dto.UserInfoDTO;
import com.locus.jlo.web.bean.modelbean.ChangePasswordModelBean;


public interface UserManagementService {
	
	ServiceResult<Page<UserInfoDTO>> searchByCriteria(UserInfoDTO userManagementCrtieria, Pageable pageable,String langCd);
	ServiceResult<UserInfoDTO> searchByLoginId(String loginId);
	ServiceResult<UserInfoDTO> searchById(Integer id, String langCd);
	UserInfoDTO saveUsrMaster(UserInfoDTO tpUsrMaster) throws Exception;
	void updateDaleteFlag(Integer id) throws Exception;
	void deleteById(Integer id) throws Exception;
	List<String> getUserIdInTeam(List<String> deptCodes);
	ServiceResult<LoginDTO> getLoginInfo(String userName, String langCd);
	ServiceResult<UserInfoDTO> getUserInfo(String userName, String langCd);
	ServiceResult<List<PositionDTO>> searchPositionList(String userId);
	ServiceResult<UserInfoDTO> searchByLoginIdAndEmail(String loginId,String email);
	ServiceResult<List<PrivilegeDTO>> getMenuPrivilege(String roleId);
	ServiceResult<List<UserInfoDTO>> getUserList();
	ServiceResult<List<UserInfoDTO>> getUnderUserList(Integer userId);
	ServiceResult<List<UserInfoDTO>> getUnderUserFreeList(Integer roleId);
	void saveUserAssignment(Integer userId, List<String> assignUserId);
	void saveUserUnAssignment(Integer userId, List<String> assignUserId);
	ServiceResult<List<String>> serachUserInTeam(Integer userId);
	ServiceResult<Boolean> updateNewPassword(ChangePasswordModelBean changePasswordBean);
}
