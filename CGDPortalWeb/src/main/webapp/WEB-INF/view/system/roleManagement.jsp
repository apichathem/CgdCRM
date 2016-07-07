<%@page import="com.locus.jlo.web.constant.JLOWebConstant"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="locus" uri="/WEB-INF/tld/locus.tld"%>

<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="caption">
			<spring:message code="roleManagement.title" />
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="home.htm"> <spring:message code="menu.home" />
			</a><i class="fa fa-angle-right"></i></li>
			<li><a href="#"> <spring:message code="menu.system" />
			</a> <i class="fa fa-angle-right"></i></li>
			<li><a href="#"> <spring:message code="menu.system.roleManagement" />
			</a></li>
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<form:form id="roleManagementDomain" class="form-horizontal" autocomplete="off" method="POST">
	<div class="row">
		<div class="col-md-12">
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-reorder"></i>
						<spring:message code="roleManage.topic" />
					</div>
					<div class="tools">
						<a class="collapse" href="javascript:;"> </a>
					</div>
				</div>
				<div class="portlet-body form">
					<!-- BEGIN FORM-->
						<div class="form-body">
							<div class="row">
								<div class="col-md-4">
									<div class="form-group">
										<label class="control-label col-md-5"><spring:message code="roleManage.roleName" /></label>
										<div class="col-md-7">
											<input type="text" name="roleName" id="roleName" class="form-control" />
										</div>
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label class="control-label col-md-5"> </label>
										<div class="col-md-7"></div>
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label class="control-label col-md-5"> </label>
										<div class="col-md-7"></div>
									</div>
								</div>
							</div>
						</div>
						<div class="form-actions right">
							<locus:actionButton action="READ" path="roleSearchBtn" code="button.search.label" onclick="searchRole();"/>
							<locus:actionButton action="ADD" code="button.create.label" onclick="initChild();"/>
						</div>
					<!-- END FORM-->
				</div>
			</div>
		</div>
	</div>
</form:form>
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN EXAMPLE TABLE PORTLET-->
		<c:url var="editUrl" value="/initRole.htm" />
		<c:url var="deleteUrl" value="/deleteRoleById.htm" />
			
		<table id="resultTableId" class="table table-bordered dataTable">
		</table>
		<!-- END EXAMPLE TABLE PORTLET-->
	</div>
</div>

<script>

	var oTable;
	var recordPerPage = <%=JLOWebConstant.recordsPerPage%>;
	var column1 = '<spring:message code="roleManage.result.no" />';
	var column2 = '<spring:message code="roleManage.result.name" />';
	var column3 = '<spring:message code="roleManage.result.parentRole" />';
	var columns = [{ 
				        "sTitle": actionLabel,
				        "mData": null,
				        "sWidth": "5%",
				        "sClass": "text-center",
				        "fnRender": function(objData) {
				        	//<a href="${editUrl}?id=${role.roleId}">
			        	 	var roleId = objData.aData.roleId; 
			        		var url =  '/initManageRole.htm?roleId=' + roleId;
				       		     
			      		  	var returnButton = "<a href='#' onclick='postAction(\"" + url + "\")'><i class='fa fa-pencil'></i></a>";
			           	  	return returnButton;
				        }  
				   }
				  ,{ "sTitle": column1, "mData": "roleId" , "sWidth": "3%", sClass: "text-center"}
				  ,{ "sTitle": column2, "mData": "roleName" }
				  ,{ "sTitle": column3, "mData": "parentRoleName" , "sWidth": "15%"}];
	//var searchCriteria = '${roleCriteria}';
	
	$(document).ready(function() {
		oTable = initajaxDataTable($('#resultTableId'), columns);
		//searchRole();
		
		$("#roleName").onEnter( function() {
			if(!empty($("#roleName").val())){
				searchRole();
			}
		});

		$('#roleSearchBtn').click();
	} );
	
	function searchRole() {
		var dataString = serialize($("#roleManagementDomain")[0]);
		oTable = ajaxDataTable($('#resultTableId'), columns, 'getRoleList.htm', dataString, recordPerPage, true, true);
	}
	
	function initChild() {
		
		postAction('/initManageRole.htm?id=');
	}
	
	$("#roleManagementDomain").on("submit",function(){
		return false;
	});
	 
	function deleteRole(roleID){
  		alertConfirm(confirmDeleteMsg,"deleteRoleManagement", roleID);
	}
	
	function deleteRoleManagement(roleID){
		window.location = "${deleteUrl}?id=" + roleID;
	}
	
	function clearForm() {
		$('#roleName').val('');
	}
	
</script>