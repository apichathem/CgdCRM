<%@page import="com.locus.jlo.web.bean.modelbean.CodebookModelBean"%>
<%@page import="java.util.Map"%>
<%@page import="com.locus.jlo.web.util.CodeBookHelper"%>
<%@page import="com.locus.common.utils.CollectionUtil"%>
<%@page import="com.locus.jlo.web.bean.dto.PrivilegeDTO"%>
<%@page import="java.util.List"%>
<%@page import="com.locus.jlo.web.controller.RoleManagementController"%>
<%@page import="com.locus.jlo.web.constant.JLOWebConstant"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<div class="row">
	<div class="col-md-12">
		<h3 class="caption">
			<spring:message code="roleManagement.title" />
			<!-- <small>blank page</small> -->
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="home.htm"> <spring:message code="menu.home" />
				</a> <i class="fa fa-angle-right"></i></li>
			<li><a href="#"> <spring:message code="menu.system" />
			</a> <i class="fa fa-angle-right"></i></li>
			<li><a href="#"> <spring:message code="menu.system.roleManagement" />
				</a></li>
		</ul>
	</div>

	<form:form id="roleActionForm" action="saveRoleAction.htm" onsubmit="return false;" method="post" autocomplete="off" class="form form-horizontal">
	<div class="col-md-12">
		<input type="hidden" id="roleId" name="roleId" value="${roleId }" />
		<% 	
		List<PrivilegeDTO> res = (List<PrivilegeDTO>) session.getAttribute(RoleManagementController.ROLE_PRIV_ACT);
		if (CollectionUtil.isNotEmpty(res)) {
			%>
			<table id="" class="table table-bordered dataTable">
				<tr>
					<th>Menu</th>
					<th>Action Name</th>
					<th>Action Value</th>
				</tr>
			<%
			Map<String, List<CodebookModelBean>> codebookList = CodeBookHelper.getCodeBookList();
			
			List<CodebookModelBean> roleActionList = codebookList.get("ROLE_ACTION");
			List<CodebookModelBean> roleModActionList = codebookList.get("ROLE_MOD_ACTION");
			StringBuffer htmlRender = new StringBuffer();
			String label = "";
			String caption = "";
			for (PrivilegeDTO privilegeDomain : res) {
				if (!label.equals(privilegeDomain.getCaption()) ) {
					label = privilegeDomain.getCaption();
					caption = label;
				} else {
					caption = "";
				}
				htmlRender.append("<tr>");
				if (!"".equals(caption)) {
					htmlRender.append("<td width='40%'>" + caption + "</td>");
				} else {
					htmlRender.append("<td width='40%' bgcolor='#b0b0b0'>" + caption + "</td>");
				}
				
				htmlRender.append("<td width='30%'>" + privilegeDomain.getActionName());
				htmlRender.append("<input name='menuId' type='hidden' value='" + privilegeDomain.getMenuId() + "' />");
				htmlRender.append("<input name='actionCode' type='hidden' value='" + privilegeDomain.getActionCd() + "' />");
				htmlRender.append("</td>");
				htmlRender.append("<td width='30%'>");
				htmlRender.append("<select class='form-control select2me' name='actionValue' >");
				
				if ("ADD".equals(privilegeDomain.getActionName())) {
					if (CollectionUtil.isNotEmpty(roleModActionList)) {
						for (CodebookModelBean codebookModelBean : roleModActionList) {
							if (codebookModelBean.getCodeId().equals(privilegeDomain.getRoleActionCd())) {
								htmlRender.append("<option value='" + codebookModelBean.getCodeId() + "' selected='selected'>" + codebookModelBean.getCodeName() + "</option>");
							} else {
								htmlRender.append("<option value='" + codebookModelBean.getCodeId() + "'>" + codebookModelBean.getCodeName() + "</option>");
							}
							
						}
					}
				} else {
					if (CollectionUtil.isNotEmpty(roleActionList)) {
						for (CodebookModelBean codebookModelBean : roleActionList) {
							if (codebookModelBean.getCodeId().equals(privilegeDomain.getRoleActionCd())) {
								htmlRender.append("<option value='" + codebookModelBean.getCodeId() + "' selected='selected'>" + codebookModelBean.getCodeName() + "</option>");
							} else {
								htmlRender.append("<option value='" + codebookModelBean.getCodeId() + "'>" + codebookModelBean.getCodeName() + "</option>");
							}
							
						}
					}
				}
				
				
				htmlRender.append("</select>");
				htmlRender.append("</td>");
				htmlRender.append("</tr>");
			}
			out.print(htmlRender.toString());
			%>
			</table>
			<%
		}
		%>
		
		<div class="form-actions right">
			<button class="btn blue" type="button" onclick="save();">
				<i class="fa fa-save"></i>
				<spring:message code="button.save.label" />
			</button>
			<button class="btn default" type="button" onclick="back()">
				<i class="fa fa-mail-reply"></i>
				<spring:message code="button.back.label" />
			</button>
		</div>
	</div>
	</form:form>
</div>

<script type="text/javascript">
$(document).ready(function() {
    
});

function save() {
	var $valid = $("#roleActionForm").valid();
    if (!$valid) {
    	return false;
    }
    ajaxSubmitFormAndRedirect($("#roleActionForm"));
}

function back() {
	
	redirectUrlAction('initManageRole.htm?roleId=' + $('#roleId').val());
}
</script>