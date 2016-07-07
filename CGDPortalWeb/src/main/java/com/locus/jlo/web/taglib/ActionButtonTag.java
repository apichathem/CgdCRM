package com.locus.jlo.web.taglib;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.apache.log4j.Logger;
import org.springframework.context.NoSuchMessageException;
import org.springframework.web.servlet.tags.MessageTag;

import com.locus.common.constant.WebPortalConstant;
import com.locus.common.utils.StringUtils;
import com.locus.jlo.web.bean.dto.UserInfoDTO;

public class ActionButtonTag extends MessageTag {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6013913215584734527L;

	private Logger logger = Logger.getLogger(getClass());
	
	/* Required */
	private String action;
	private String menuId;
	private String label;
	private String code;
	
	/* Optional */
	private String icon;
	private String btnClass = "default";
	private String onclick;
	private String path;
	private String disabled;
	
	private Boolean isPermission;
	private String userId;
	
	@Override
	protected String resolveMessage() throws JspException, NoSuchMessageException {
		return "";
	}
	public int doEndTag() {
		logger.info("ActionButtonTag-doEndTag");
		
		try {
			/* ========== Initial ========== */
			Locale locale = null;
			HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
			
			String actionValue = "";
			switch (action) {
			case "READ":
				actionValue = "00";
				this.icon = "fa-search";
				this.btnClass = "blue";
				break;
			case "ADD":
				actionValue = "01";
				this.icon = "fa-plus";
				this.btnClass = "green";
				break;
			case "EDIT":
				actionValue = "02";
				this.icon = "fa-floppy-o";
				this.btnClass = "blue";
				break;
			case "DELETE":
				actionValue = "03";
				this.icon = "fa-trash-o";
				this.btnClass = "red";
				break;
			case "APPROVE":
				actionValue = "04";
				this.icon = "fa-check";
				this.btnClass = "green";
				break;
			case "EXTRA1":
				actionValue = "05";
				break;
			case "EXTRA2":
				actionValue = "06";
				break;
			case "EXTRA3":
				actionValue = "07";
				break;
			default:
				break;
			}
			logger.info("actionValue : " + actionValue);
			
			/* ========== Get label  from message properties ========== */
			if (StringUtils.isNotEmpty(code)) {
				String language = request.getParameter("lang");
				if (StringUtils.isNotEmpty(language)) {
					locale = new Locale(request.getParameter("lang"));
				} else {
					locale = new Locale("TH");
				}
				
				this.label = getMessageSource().getMessage(code, null, locale);
			} else {
				this.label = "";
			}
			
			logger.info("isPermission : " + this.isPermission);
			
			/* ========== Print HTML tag to page ==========*/
			checkPermission(request, actionValue);
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
		return EVAL_PAGE;
	}
	
	private String generateButtonTag() {
		StringBuffer button = new StringBuffer();
		button.append("");
		button.append("<button " + ((StringUtils.isNotEmpty(this.path)) ? "id=\"" + path + "\" name=\"" + path + "\"" : ""));
		button.append(" type=\"button\"");
		button.append(" class=\"btn " + btnClass + "\"");
		button.append((StringUtils.isNotEmpty(this.onclick)) ? "onclick=\"" + onclick + "\"" : "");
		button.append(" />");
		if (StringUtils.isNotEmpty(this.icon)) {
			button.append(" <i class=\"fa " + icon + "\"></i>");
		}
		button.append(label);
		button.append("</button>");
		
		return button.toString();
	}
	
	@SuppressWarnings("unchecked")
	private void checkPermission(HttpServletRequest request, String operation) throws IOException {
		HttpSession session = request.getSession();
		JspWriter out = pageContext.getOut();
		String buttonTagString = generateButtonTag();
		
		if (this.isPermission != null && this.isPermission) {
			// Get user information from session
			UserInfoDTO userLoginInfoModelBean = (UserInfoDTO) request.getSession().getAttribute(WebPortalConstant.USER_PROFILE);
			
			// Get user all privilege from session 
			HashMap< String, HashMap<String, String> > privilegeMap = (HashMap< String, HashMap<String, String> >) session.getAttribute(WebPortalConstant.PRIVILEGE_PROFILE);
			
			// Get privilege from menu id
			HashMap<String, String> byMenu = privilegeMap.get(menuId);
			String actionValue = byMenu.get(operation);
			
			logger.info("actionValue is " + actionValue + " from operator " + operation);
			logger.info("team = " + userLoginInfoModelBean.getMyTeamUserIdText());
			logger.info("owner = " + userLoginInfoModelBean.getUserId());
			
			if ("ALL".equalsIgnoreCase(actionValue)) {
				out.print(buttonTagString);
			} else if ("TEAM".equalsIgnoreCase(actionValue)) {
				if (StringUtils.isNotEmpty(this.userId)) {
					if (this.userId.indexOf(userLoginInfoModelBean.getMyTeamUserIdText()) >= 0) {
						out.print(buttonTagString);
					}
				}
				
			} else if ("OWNER".equalsIgnoreCase(actionValue)) {
				if (StringUtils.isNotEmpty(this.userId)) {
					if (userLoginInfoModelBean.getUserId().equals(Integer.valueOf(this.userId))) {
						out.print(buttonTagString);
					} 
				}
				
			} else {
			}
		} else {
			out.print(buttonTagString);
		}
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getBtnClass() {
		return btnClass;
	}

	public void setBtnClass(String btnClass) {
		this.btnClass = btnClass;
	}

	public String getOnclick() {
		return onclick;
	}

	public void setOnclick(String onclick) {
		this.onclick = onclick;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) { 
		super.setCode(code);
		this.code = code;
	}
	
	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	public String getDisabled() {
		return disabled;
	}
	
	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}
	
	public Boolean getIsPermission() {
		return isPermission;
	}
	
	public void setIsPermission(Boolean isPermission) {
		this.isPermission = isPermission;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
