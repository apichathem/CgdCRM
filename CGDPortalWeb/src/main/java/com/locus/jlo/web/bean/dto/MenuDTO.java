package com.locus.jlo.web.bean.dto;

import java.util.Date;

public class MenuDTO extends BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7368652505770687176L;
	private Integer menuId;
	private String action;
	private String caption;
	private Date chgDt;
	private Integer chgId;
	private String descp;
	private String iconPath;
	private String leafYn;
	private Integer menuSeqno;
	private Integer parentId;
	private Date regDt;
	private Integer regId;
	private String visibleYn;
	private String systemYn;
	
	public Integer getMenuId() {
		return menuId;
	}
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public Date getChgDt() {
		return chgDt;
	}
	public void setChgDt(Date chgDt) {
		this.chgDt = chgDt;
	}
	public Integer getChgId() {
		return chgId;
	}
	public void setChgId(Integer chgId) {
		this.chgId = chgId;
	}
	public String getDescp() {
		return descp;
	}
	public void setDescp(String descp) {
		this.descp = descp;
	}
	public String getIconPath() {
		return iconPath;
	}
	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}
	public String getLeafYn() {
		return leafYn;
	}
	public void setLeafYn(String leafYn) {
		this.leafYn = leafYn;
	}
	public Integer getMenuSeqno() {
		return menuSeqno;
	}
	public void setMenuSeqno(Integer menuSeqno) {
		this.menuSeqno = menuSeqno;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public Date getRegDt() {
		return regDt;
	}
	public void setRegDt(Date regDt) {
		this.regDt = regDt;
	}
	public Integer getRegId() {
		return regId;
	}
	public void setRegId(Integer regId) {
		this.regId = regId;
	}
	public String getVisibleYn() {
		return visibleYn;
	}
	public void setVisibleYn(String visibleYn) {
		this.visibleYn = visibleYn;
	}
	public String getSystemYn() {
		return systemYn;
	}
	public void setSystemYn(String systemYn) {
		this.systemYn = systemYn;
	}
	
}
