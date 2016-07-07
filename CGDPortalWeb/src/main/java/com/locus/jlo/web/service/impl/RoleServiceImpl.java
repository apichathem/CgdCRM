package com.locus.jlo.web.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.locus.common.domain.ServiceResult;
import com.locus.common.domain.SimpleKeyValue;
import com.locus.jlo.web.bean.dto.PrivilegeDTO;
import com.locus.jlo.web.bean.dto.SysActionDTO;
import com.locus.jlo.web.bean.modelbean.RoleModelBean;
import com.locus.jlo.web.service.RoleService;

@Service
public class RoleServiceImpl extends BaseService implements RoleService {
	private static final Logger logger = Logger.getLogger(RoleServiceImpl.class);
	
	final static String SQL_FIND_BY_NAME="ROLE.FIND_BY_NAME";
	final static String SQL_FIND_BY_ID="ROLE.FIND_BY_ID";
	final static String SQL_FIND_BY_ENABLED="ROLE.FIND_BY_ENABLED";
	final static String SQL_INSERT="ROLE.INSERT_ROLE";
	final static String SQL_UPDATE="ROLE.UPDATE_ROLE";
	
	final static String SQL_RESET_ROLEMENU = "ROLE.DELETE_ROLEMENU";
	final static String SQL_INSERT_ROLEMENU ="ROLE.INSERT_ROLEMENU";
	final static String SQL_FIND_ROLEMENU_BY_ID = "ROLE.FIND_ROLEMENU";
	final static String SQL_FIND_ROLE_PRIVILEGE = "ROLE.FIND_ROLE_PRIVILEGE";
	
	final static String COUNT_ROLE_PRIVILEGE = "ROLE.COUNT_ROLE_PRIVILEGE";
	final static String INSERT_ROLE_PRIVILEGE = "ROLE.INSERT_ROLE_PRIVILEGE";
	final static String UPDATE_ROLE_PRIVILEGE = "ROLE.UPDATE_ROLE_PRIVILEGE";
	final static String FIND_ROLE_ACTION_MASTER = "ROLE.FIND_ROLE_ACTION_MASTER";

	@Override
	public ServiceResult<Page<RoleModelBean>> findListByName(String roleName, Pageable pageable) {
		ServiceResult<Page<RoleModelBean>> result = null;
		try {
			Page<RoleModelBean> obj = dynamicJdbcDao.findForPage(SQL_FIND_BY_NAME, BeanPropertyRowMapper.newInstance(RoleModelBean.class),pageable,
					new SimpleKeyValue("roleName",roleName));
			result = new ServiceResult<Page<RoleModelBean>>(obj);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result = new ServiceResult<Page<RoleModelBean>>(e);
			result.setSuccess(false);
		}
		return result;
	}
	

	@Override
	public ServiceResult<Long> insert(RoleModelBean roleModelBean) {
		ServiceResult<Long> result = null;
		try {
			Long id = dynamicJdbcDao.executeInsert(SQL_INSERT, Boolean.TRUE, roleModelBean);
			result = new ServiceResult<Long>(id);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result = new ServiceResult<Long>(e);
			result.setSuccess(false);
		}
		return result;
	}

	@Override
	public ServiceResult<Long> update(RoleModelBean roleModelBean) {
		ServiceResult<Long> result = null;
		try {
			int co = dynamicJdbcDao.executeUpdate(SQL_UPDATE, roleModelBean);
			result = new ServiceResult<Long>(new Long(co));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result = new ServiceResult<Long>(e);
			result.setSuccess(false);
		}
		return result;
	}

	@Override
	public ServiceResult<RoleModelBean> findById(String roleId) {
		ServiceResult<RoleModelBean> result = null;
		try {
			RoleModelBean obj = dynamicJdbcDao.findForObject(SQL_FIND_BY_ID, BeanPropertyRowMapper.newInstance(RoleModelBean.class),
					new SimpleKeyValue("roleId",roleId));
			result = new ServiceResult<RoleModelBean>(obj);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result = new ServiceResult<RoleModelBean>(e);
			result.setSuccess(false);
		}
		return result;
	}

	@Override
	public void resetRoleMenu(String roleId) {
		try {
			dynamicJdbcDao.executeUpdate(SQL_RESET_ROLEMENU, new SimpleKeyValue("roleId",roleId));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	@Override
	public ServiceResult<Long> updateRoleMenu(String roleId, String menuId, String actionCd, String actionValue) {
		ServiceResult<Long> result = null;
		try {
			int co = dynamicJdbcDao.executeUpdate(SQL_INSERT_ROLEMENU, 
					new SimpleKeyValue("roleId",roleId),
					new SimpleKeyValue("menuId",menuId),
					new SimpleKeyValue("actionCd",menuId),
					new SimpleKeyValue("actionValue",menuId));
			result = new ServiceResult<Long>(new Long(co));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result = new ServiceResult<Long>(e);
			result.setSuccess(false);
		}
		return result;
	}

	@Override
	public ServiceResult<List<String>> findRoleMenuByRoleId(Integer roleId) {
		ServiceResult<List<String>> result = null;
		try {
			List<String> obj = dynamicJdbcDao.findForList(SQL_FIND_ROLEMENU_BY_ID, String.class,
					new SimpleKeyValue("roleId",roleId));
			result = new ServiceResult<List<String>>(obj);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result = new ServiceResult<List<String>>(e);
			result.setSuccess(false);
		}
		return result;
	}
	
	
	@Override
	public ServiceResult<List<RoleModelBean>> findListByEnabled() {
		ServiceResult<List<RoleModelBean>> result = null;
		try {
			List<RoleModelBean> obj = dynamicJdbcDao.findForList(SQL_FIND_BY_ENABLED, BeanPropertyRowMapper.newInstance(RoleModelBean.class));
			result = new ServiceResult<List<RoleModelBean>>(obj);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result = new ServiceResult<List<RoleModelBean>>(e);
			result.setSuccess(false);
		}
		return result;
	}


	@Override
	public ServiceResult<List<PrivilegeDTO>> findRolePrivilegeList(Integer roleId) {
		
		ServiceResult<List<PrivilegeDTO>> result = new ServiceResult<List<PrivilegeDTO>>();
		
		try {
			List<PrivilegeDTO> obj = dynamicJdbcDao.findForList(SQL_FIND_ROLE_PRIVILEGE, 
					BeanPropertyRowMapper.newInstance(PrivilegeDTO.class),
					new SimpleKeyValue("roleId",roleId));
			result.setResult(obj);
			result.setSuccess(Boolean.TRUE);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			setErrorResult(result, e);
		}
		
		return result;
	}


	@Override
	@Transactional
	public void inserOrUpdatePrivilage(Integer roleId,
			Integer menuId, String actionCd, String actionValue, Integer userId) {
		
		try {
			Integer countRoleAction = dynamicJdbcDao.findForObject(COUNT_ROLE_PRIVILEGE, new IntegerResultMapper(), 
							new SimpleKeyValue("roleId", roleId),
							new SimpleKeyValue("menuId", menuId),
							new SimpleKeyValue("actionCd", actionCd));
			
			if (countRoleAction > 0) {
				// Update
				dynamicJdbcDao.executeUpdate(UPDATE_ROLE_PRIVILEGE, 
						new SimpleKeyValue("roleId", roleId),
						new SimpleKeyValue("menuId", menuId),
						new SimpleKeyValue("actionCd", actionCd),
						new SimpleKeyValue("actionValue", actionValue),
						new SimpleKeyValue("updateBy", userId));
			} else {
				// Insert
				dynamicJdbcDao.executeUpdate(INSERT_ROLE_PRIVILEGE, 
						new SimpleKeyValue("roleId", roleId),
						new SimpleKeyValue("menuId", menuId),
						new SimpleKeyValue("actionCd", actionCd),
						new SimpleKeyValue("actionValue", actionValue),
						new SimpleKeyValue("createBy", userId));
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}


	@Override
	public ServiceResult<List<SysActionDTO>> findSysAction() {
		
		ServiceResult<List<SysActionDTO>> result = new ServiceResult<List<SysActionDTO>>();
		try {
			List<SysActionDTO> res = dynamicJdbcDao.findForList(FIND_ROLE_ACTION_MASTER, 
					BeanPropertyRowMapper.newInstance(SysActionDTO.class));
			
			result.setSuccess(Boolean.TRUE);
			result.setResult(res);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			setErrorResult(result, e);
		}
		
		return result;
	}

}
