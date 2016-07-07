package com.locus.jlo.web.taglib;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.log4j.Logger;

import com.locus.common.constant.WebPortalConstant;
import com.locus.common.utils.StringUtils;
import com.locus.jlo.web.bean.dto.UserInfoDTO;
import com.locus.jlo.web.constant.JLOWebConstant;

public class PrivilegeTag extends BodyTagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6815652871312832685L;
	private Logger logger = Logger.getLogger(getClass());
	
	private String oper;
	private String ownerId;
	
	private String menuId;
	private String menuName;
	
	private String mode = "hidden";

	public String getOper() {
		return oper;
	}

	public void setOper(String oper) {
		this.oper = oper;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	
	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	@SuppressWarnings("unchecked")
	public int doAfterBody() {

		try {
			BodyContent bodyContent = super.getBodyContent();
			String bodyString = bodyContent.getString();
			JspWriter out = bodyContent.getEnclosingWriter();
			bodyString = bodyString.trim();
			
			HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
			HttpSession session = request.getSession();
			
			if ( !StringUtils.isNullOrEmpty(this.menuName) ) {
//				this.menuId = MenuHelper.getMenuIdFromName(request, this.menuName);
				
			} else {
				this.menuId = (String) session.getAttribute(JLOWebConstant.SESSION_MENU_ID);
			}
			
//			logger.info("From menu " + this.menuId);
			
			if (StringUtils.isNullOrEmpty(this.menuId)) {
				out.print(hideOrDisable(bodyString, mode));
				
			} else {
				// Get user information from session
				UserInfoDTO userLoginInfoModelBean = (UserInfoDTO) request.getSession().getAttribute(WebPortalConstant.USER_PROFILE);
				
				// Get user all privilege from session 
				HashMap< String, HashMap<String, String> > privilegeMap = (HashMap< String, HashMap<String, String> >) session.getAttribute(WebPortalConstant.PRIVILEGE_PROFILE);
				
				// Get privilege from menu id
				HashMap<String, String> byMenu = privilegeMap.get(menuId);
				String actionValue = byMenu.get(oper);
				
				logger.info("actionValue is " + actionValue + " from operator " + oper);
				logger.info("ownerId = " + ownerId);
				logger.info("team = " + userLoginInfoModelBean.getMyTeamUserIdText());
				logger.info("owner = " + userLoginInfoModelBean.getUserId());
				
				if ("ALL".equalsIgnoreCase(actionValue)) {
					out.print(bodyString);
				} else if ("TEAM".equalsIgnoreCase(actionValue)) {
					if (this.ownerId == null) {
						String userId = String.valueOf(userLoginInfoModelBean.getUserId());
						if (userLoginInfoModelBean.getMyTeamUserIdText().indexOf(userId) >= 0) {
							out.print(bodyString);
						} else {
							out.print(hideOrDisable(bodyString, mode));
						}
					} else {
						if (this.ownerId.indexOf(userLoginInfoModelBean.getMyTeamUserIdText()) >= 0) {
							out.print(bodyString);
						} else {
							out.print(hideOrDisable(bodyString, mode));
						}	
					}
					
				} else if ("OWNER".equalsIgnoreCase(actionValue)) {
					logger.info(userLoginInfoModelBean.getUserId()+ " == " + this.ownerId);
					if (userLoginInfoModelBean.getUserId() != null && StringUtils.isNotEmpty(this.ownerId)) {
						if (userLoginInfoModelBean.getUserId().equals(Integer.valueOf(this.ownerId))) {
							out.print(bodyString);
						} else {
							out.print(hideOrDisable(bodyString, mode));
						}
					} else {
						out.print(hideOrDisable(bodyString, mode));
					}
					
				} else {
					out.print(hideOrDisable(bodyString, mode));
				}
				
			}
			
		} catch (Exception e) {
			logger.error("PrivilegeTag-doAfterBody " + e.getMessage(), e);
		}

		return SKIP_BODY;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	
	private String hideOrDisable(String bodyString, String action) {
		//logger.info("action : " + action + " bodyString : " + bodyString);
		
		//Check null and empty string
		if (StringUtils.isNullOrEmpty(action)) {
			return "";
			
		} else if ("disable".equals(action)) {
			// Insert disable attribute
			int buttonPos = bodyString.indexOf("<button");
			if (buttonPos >= 0) {
				String disable = " disabled=\"disabled\"";
				bodyString = bodyString.substring(buttonPos, buttonPos + 7) + disable + bodyString.substring(buttonPos + 7);
//				logger.info("bodyString after disable : " + bodyString);
			}
			
			return bodyString;
			
		} else if ("hidden".equals(action)) {
			// Hide everything in tag
			return "";
			
		} else if ("icon".equals(action)) {
			// Create new button with icon
			int startIconPos = bodyString.indexOf("<i");
			int endIconPos = bodyString.indexOf("</i>");
			
			String iconString = "<button type=\"button\" class=\"btn\">" + bodyString.substring(startIconPos, endIconPos+4) + "</button>";
//			logger.info("bodyString after remove button : " + iconString);
			return iconString;
			
		} else if ("iconLable".equals(action)) {
			// Create new button with icon and label
			int startIconPos = bodyString.indexOf("<i");
			int endIconPos = bodyString.indexOf("</button>");
			
			String iconString = "<button type=\"button\" class=\"btn\" >" + bodyString.substring(startIconPos, endIconPos) + "</button>";
//			logger.info("bodyString after remove button : " + iconString);
			return iconString;
			
		}  else {
			
			return "";
		}
	}

}
