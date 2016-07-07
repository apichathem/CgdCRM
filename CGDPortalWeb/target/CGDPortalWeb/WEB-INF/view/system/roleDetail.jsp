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
<form:form id="roleDetailForm" action="saveRole.htm" modelAttribute="roleModelDomain" onsubmit="return false;" method="post" autocomplete="off" class="form form-horizontal">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-reorder"></i>
					<spring:message code="roleManage.detail.topic" />
				</div>
				<div class="tools">
					<a class="collapse" href="javascript:;"> </a>
				</div>
			</div>
			<div class="portlet-body">
				<form:hidden path="mode"/>
				<form:hidden path="roleId"/>
				<div class="form-body">
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-5">
									<spring:message code="roleManage.result.name" /><span class="required"> * </span>
								</label>
								<div class="col-md-7">
									<form:input path="roleName" class="form-control"  data-rule-required="true" />
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-5">
									<spring:message code="roleManage.detail.parentRole" /><span class="required"> * </span>
								</label>
								<div class="col-md-7">
									<div class="input-group">
										<form:hidden path="parentRoleId"/>
										<form:input path="parentRoleName" class="form-control" readonly="true" data-rule-required="true"/>
										<span class="input-group-addon">												
											<a id="btnSearchRole" href="#" data-target="#roleDialog" data-toggle="modal"> 
												<i class="fa fa-user"></i>
											</a>
										</span>
										<span class="input-group-addon"> 
											<a id="btnClearRoleParent" style="cursor: pointer;"> 
												<i class="fa fa-minus-circle red"></i>
											</a>
										</span>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="form-actions right">
				<button class="btn blue" type="button" onclick="saveRole();">
					<i class="fa fa-save"></i>
					<spring:message code="button.save.label" />
				</button>
				<button class="btn default" type="button" onclick="back()">
					<i class="fa fa-mail-reply"></i>
							<spring:message code="button.back.label" />
				</button>
			</div>
		</div>
	</div>
</form:form>
<c:if test="${roleModelDomain.roleId!=null }">
<form id="roleMenulForm" action="saveRoleMenu.htm"  onsubmit="return false;" method="post" autocomplete="off" class="form form-horizontal">
	<input type="hidden" name="roleId" value="${roleModelDomain.roleId }" />
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-reorder"></i>
					<spring:message code="roleManage.detail.action.topic" />
				</div>
				<div class="tools">
					<a class="collapse" href="javascript:;"> </a>
				</div>
			</div>
			<div class="portlet-body">
				<table class="table table-bordered dataTable">
					<thead>
						<tr>
							<th style="width:30px;"><input type="checkbox" name="menuAuthAll" value="Y" /></th>
							<th><spring:message code="menuManage.dialog.menu.topic" /></th>							
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${menuList}" var="menu">
						<tr>
							<td class="text-center">
								<c:if test="${menu.isParent }">
									<input id="${menu.parentId }" type="checkbox" name="menuAuth" value="${menu.menuId }" ${menu.checked} class="parentCheckBox" />
								</c:if>
								<c:if test="${!menu.isParent }">
									<input type="checkbox" name="menuAuth" value="${menu.menuId }" ${menu.checked} parentId="${menu.parentId }" class="childCheckBox"/>
								</c:if>
								
							</td>
							<td>${ menu.menuName}</td>							
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="form-actions right">
				<button class="btn dark" type="button" onclick="goPrivilege();">
					<i class="fa fa-gear"></i>
					<spring:message code="button.role.action.label" />
				</button>
				<button class="btn blue" type="button" onclick="saveRoleMenu();">
					<i class="fa fa-save"></i>
					<spring:message code="button.save.label" />
				</button>
				<button class="btn default" type="button" onclick="back()">
					<i class="fa fa-mail-reply"></i>
							<spring:message code="button.back.label" />
				</button>
			</div>
		</div>
	</div>
</form>
</c:if>
</div>
<!-- parent role dialog -->	
<div class="modal fade" id="roleDialog" role="basic" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div id="modal_content_div"></div>
		</div>
	</div>
</div>	
<script type="text/javascript">
$(document).ready(function() {
	validateForm($("#roleDetailForm"));
	$("input[name='menuAuthAll']").on("change",function(e){
		if(this.checked){
			$('input:checkbox').not(this).attr('checked', true);
			$('input:checkbox').not(this).prop('checked', true);
			$('input:checkbox').not(this).closest('span').addClass('checked');
		}else{
			$('input:checkbox').not(this).attr('checked', false);
			$('input:checkbox').not(this).prop('checked', false);
			$('input:checkbox').not(this).closest('span').removeClass('checked');
		}
	});

	$(".parentCheckBox").click(function (e) {
		var isChecked = $(this).prop('checked');
        var parentId = $(this).attr('id');
        
        $('input[type=checkbox][parentId=' + parentId + ']').each(function() {
        	this.checked = isChecked;
        	if (isChecked) {
        		$(this).closest('span').addClass('checked');
        	} else {
        		$(this).closest('span').removeClass('checked');
            }

        }); 
    });

	$(".childCheckBox").click(function (e) {
		var parentId = $(this).attr('parentId');
		//var total = $('input[type=checkbox][parentId=' + parentId + ']').size();
		var checked = $('input[type=checkbox][parentId=' + parentId + ']:checked').size();

		if (checked > 0) {
			$('#' + parentId).prop('checked', true);
			$('#' + parentId).closest('span').addClass('checked');
		}

		if (checked == 0) {
			$('#' + parentId).prop('checked', false);
			$('#' + parentId).closest('span').removeClass('checked');
		}
	});
	
	// Role dialog button
	$("#btnSearchRole").click(function() {		 
		loadContentIntoModal($("#modal_content_div"), "openModalDialog.htm","roleDialog","modal.header.role", "selectedRoleParentId");
	});
	// Role dialog clear button
	$("#btnClearRoleParent").click(function() {
		$("#parentRoleId").val("");
		$("#parentRoleName").val("");
	});
});
function saveRole(){	
	
	var $valid = $("#roleDetailForm").valid();
    if (!$valid) {
    	return false;
    }
    ajaxSubmitFormAndRedirect($("#roleDetailForm"));
}

function saveRoleMenu(){
    ajaxSubmitFormAndRedirect($("#roleMenulForm"));
}
function back() {
	redirectUrlAction("roleManagement.htm");
}
function goPrivilege() {
	var roleId = $('#roleId').val();
	postAction('/rolePrivilegeManagement.htm?roleId=' + roleId);
}
function selectedRoleParentId(jsonData){
	$('#parentRoleId').val(jsonData.roleId);
	$('#parentRoleName').val(jsonData.roleName);
	$("#roleDialog").modal("hide");
}
</script>