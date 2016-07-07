package com.locus.jlo.web.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.locus.common.domain.ServiceResult;
import com.locus.jlo.web.bean.dto.PrivilegeDTO;
import com.locus.jlo.web.bean.dto.SysActionDTO;
import com.locus.jlo.web.bean.modelbean.RoleModelBean;

public interface RoleService {

	ServiceResult<Page<RoleModelBean>> findListByName(String roleName, Pageable pageable);

	ServiceResult<Long> insert(RoleModelBean roleModelBean);

	ServiceResult<Long> update(RoleModelBean roleModelBean);

	ServiceResult<RoleModelBean> findById(String roleId);
	
	ServiceResult<List<RoleModelBean>> findListByEnabled();

	void resetRoleMenu(String roleId);

	ServiceResult<Long> updateRoleMenu(String roleId, String menuId, String actionCd, String actionValue);

	ServiceResult<List<String>> findRoleMenuByRoleId(Integer roleId);

	ServiceResult<List<PrivilegeDTO>> findRolePrivilegeList(Integer roleId);
	
	void inserOrUpdatePrivilage(Integer roleId, Integer menuId, String actionCd, String actionValue, Integer userId);
	
	ServiceResult<List<SysActionDTO>> findSysAction();
	
}
