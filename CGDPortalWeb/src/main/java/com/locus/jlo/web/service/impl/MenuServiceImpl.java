package com.locus.jlo.web.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import com.locus.common.domain.ServiceResult;
import com.locus.common.domain.SimpleKeyValue;
import com.locus.jlo.web.bean.modelbean.MenuDetailModelBean;
import com.locus.jlo.web.service.MenuService;

@Service
public class MenuServiceImpl extends BaseService implements MenuService  {
	private static final Logger logger = Logger.getLogger(MenuServiceImpl.class);
	
	final static String SQL_INSERT = "MENU.INSERT_MENU";
	final static String SQL_UPDATE = "MENU.UPDATE_MENU";
	final static String SQL_FIND_BY_ID = "MENU.FIND_BY_ID";
	final static String SQL_FIND_BY_NAME = "MENU.FIND_BY_NAME";
	final static String SQL_FIND_BY_ENABLED = "MENU.FIND_BY_ENABLED";
	final static String SQL_FIND_BY_ENABLED_BY_ROLE_ID = "MENU.FIND_BY_ENABLED_BY_ROLE_ID";
	final static String SQL_FIND_ALL_PARENT_MENU = "MENU.SQL_FIND_ALL_PARENT_MENU";

	@Override
	public ServiceResult<Long> insert(MenuDetailModelBean menuDetailModelBean) {
		ServiceResult<Long> result = new ServiceResult<Long>();
		try {
			Long id = dynamicJdbcDao.executeInsert(SQL_INSERT, Boolean.TRUE, menuDetailModelBean);
			result.setResult(id);
			result.setSuccess(Boolean.TRUE);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			setErrorResult(result, e);
		}
		return result;
	}

	@Override
	public void update(MenuDetailModelBean menuDetailModelBean) {
		try {
			dynamicJdbcDao.executeUpdate(SQL_UPDATE, menuDetailModelBean);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	@Override
	public ServiceResult<MenuDetailModelBean> findById(String menuId) {
		ServiceResult<MenuDetailModelBean> result = new ServiceResult<MenuDetailModelBean>();
		try {
			MenuDetailModelBean obj = dynamicJdbcDao.findForObject(SQL_FIND_BY_ID, BeanPropertyRowMapper.newInstance(MenuDetailModelBean.class),
					new SimpleKeyValue("menuId",menuId));
			result.setResult(obj);
			result.setSuccess(Boolean.TRUE);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			setErrorResult(result, e);
		}
		return result;
	}

	@Override
	public ServiceResult<List<MenuDetailModelBean>> findListByName(
			String menuName) {
		ServiceResult<List<MenuDetailModelBean>> result = new ServiceResult<List<MenuDetailModelBean>>();
		try {
			List<MenuDetailModelBean> obj = dynamicJdbcDao.findForList(SQL_FIND_BY_NAME, BeanPropertyRowMapper.newInstance(MenuDetailModelBean.class),
					new SimpleKeyValue("menuName",menuName));
			result.setResult(obj);
			result.setSuccess(Boolean.TRUE);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			setErrorResult(result, e);
		}
		return result;
	}
	
	
	@Override
	public ServiceResult<List<MenuDetailModelBean>> findListMenuEnabled() {
		ServiceResult<List<MenuDetailModelBean>> result = new ServiceResult<List<MenuDetailModelBean>>();
		try {
			List<MenuDetailModelBean> obj = dynamicJdbcDao.findForList(SQL_FIND_BY_ENABLED, BeanPropertyRowMapper.newInstance(MenuDetailModelBean.class));
			result.setResult(obj);
			result.setSuccess(Boolean.TRUE);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			setErrorResult(result, e);
		}
		return result;
	}
	
	
	@Override
	public ServiceResult<List<MenuDetailModelBean>> findListMenuEnabledByRoleId(Integer roleId){
		ServiceResult<List<MenuDetailModelBean>> result = new ServiceResult<List<MenuDetailModelBean>>();
		try {
			List<MenuDetailModelBean> obj = dynamicJdbcDao.findForList(SQL_FIND_BY_ENABLED_BY_ROLE_ID, BeanPropertyRowMapper.newInstance(MenuDetailModelBean.class),
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
	public ServiceResult<List<MenuDetailModelBean>> findListAllParentMenu() {
		ServiceResult<List<MenuDetailModelBean>> result = new ServiceResult<List<MenuDetailModelBean>>();
		try {
			List<MenuDetailModelBean> obj = dynamicJdbcDao.findForList(SQL_FIND_ALL_PARENT_MENU, BeanPropertyRowMapper.newInstance(MenuDetailModelBean.class));
			result.setResult(obj);
			result.setSuccess(Boolean.TRUE);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			setErrorResult(result, e);
		}
		return result;
	}
	
}
