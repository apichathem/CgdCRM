package com.locus.jlo.web.bean.modelbean;

public class MenuDetailModelBean extends BaseModelBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5827049702006919222L;
	private Integer menuId;
	private String menuName;
	private String menuAction;
	private Integer menuSeq;
	private String menuEnabled;
	public String menuIcon;
	public String menuLevel;
	public String menuParentId;
	private String systemYn;
	private String menuNumber;
	private String parentId;
	
	private Boolean isParent;
	public String checked;

	public MenuDetailModelBean() {
		this.menuEnabled = "Y";
	}

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuAction() {
		return menuAction;
	}

	public void setMenuAction(String menuAction) {
		this.menuAction = menuAction;
	}

	public Integer getMenuSeq() {
		return menuSeq;
	}

	public void setMenuSeq(Integer menuSeq) {
		this.menuSeq = menuSeq;
	}

	public String getMenuEnabled() {
		return menuEnabled;
	}

	public void setMenuEnabled(String menuEnabled) {
		this.menuEnabled = menuEnabled;
	}

	public String getMenuIcon() {
		return menuIcon;
	}

	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}

	public String getMenuLevel() {
		return menuLevel;
	}

	public void setMenuLevel(String menuLevel) {
		this.menuLevel = menuLevel;
	}

	public String getMenuParentId() {
		return menuParentId;
	}

	public void setMenuParentId(String menuParentId) {
		this.menuParentId = menuParentId;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	public String getSystemYn() {
		return systemYn;
	}

	public void setSystemYn(String systemYn) {
		this.systemYn = systemYn;
	}

	public String getMenuNumber() {
		return menuNumber;
	}

	public void setMenuNumber(String menuNumber) {
		this.menuNumber = menuNumber;
	}

	public Boolean getIsParent() {
		return isParent;
	}

	public void setIsParent(Boolean isParent) {
		this.isParent = isParent;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

}
