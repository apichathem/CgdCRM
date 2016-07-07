package com.locus.jlo.web.bean.modelbean;

import java.util.ArrayList;
import java.util.List;

public class UserMenuModelDomain extends BaseModelBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1892622242121817964L;
	private String menuId;
	private String menuName;
	private String action;
	private String iconName;
	private List<UserMenuModelDomain> child = new ArrayList<UserMenuModelDomain>();

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public List<UserMenuModelDomain> getChild() {
		return child;
	}

	public void setChild(List<UserMenuModelDomain> child) {
		this.child = child;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getIconName() {
		return iconName;
	}

	public void setIconName(String iconName) {
		this.iconName = iconName;
	}

}
