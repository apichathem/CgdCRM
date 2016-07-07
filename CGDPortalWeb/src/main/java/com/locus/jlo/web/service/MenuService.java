package com.locus.jlo.web.service;

import java.util.List;

import com.locus.common.domain.ServiceResult;
import com.locus.jlo.web.bean.modelbean.MenuDetailModelBean;

public interface MenuService {

	ServiceResult<Long> insert(MenuDetailModelBean menuDetailModelBean);

	void update(MenuDetailModelBean menuDetailModelBean);

	ServiceResult<MenuDetailModelBean> findById(String menuId);

	ServiceResult<List<MenuDetailModelBean>> findListByName(String menuName);
	
	ServiceResult<List<MenuDetailModelBean>> findListMenuEnabled();
	
	ServiceResult<List<MenuDetailModelBean>> findListMenuEnabledByRoleId(Integer roleId);

	ServiceResult<List<MenuDetailModelBean>> findListAllParentMenu();

}
