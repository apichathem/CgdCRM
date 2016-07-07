<%@page import="com.locus.jlo.web.constant.JLOWebConstant"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="caption">
			<spring:message code="menuManagement.title" />
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="home.htm"> <spring:message code="menu.home" />
			</a> <i class="fa fa-angle-right"></i></li>
			<li><a href="#"> <spring:message code="menu.system" />
			</a> <i class="fa fa-angle-right"></i></li>
			<li><a href="#"> <spring:message code="menu.system.menuManagement" />
			</a></li>
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<form:form id="menuManagementDomain" action="/menuList.htm" class="form-horizontal" autocomplete="off" method="POST">
	<input type="hidden" name="sEcho" value="1" />
	<div class="row">
		<div class="col-md-12">
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-reorder"></i>
						<spring:message code="menuManage.dialog.menu.topic" />
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
										<label class="control-label col-md-5"><spring:message code="menuManage.caption" /></label>
										<div class="col-md-7">
											<input type="text" name="menuName" id="menuName" class="form-control" />
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
							<button id="menuSearchBtn" class="btn blue" type="button" onclick="searchRole();">
								<i class="fa fa-search"></i>
								<spring:message code="button.search.label" />
							</button>
							<%-- <button class="btn default" type="button" onclick="clearForm()">
								<spring:message code="button.cancel.label" />
							</button> --%>
							<button class="btn green" type="button" onclick="initChild()">
								<i class="fa fa-plus"></i>
								<spring:message code="menuManage.button.create" />
							</button>
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
		<c:url var="editUrl" value="/initMenu.htm" />
		<c:url var="deleteUrl" value="/deleteMenuById.htm" />
			
		<table id="resultTableId" class="table table-bordered dataTable">
		</table>
		<!-- END EXAMPLE TABLE PORTLET-->
	</div>
</div>

<script>

	var oTable;
	var recordPerPage = 100;
	var column1 = '<spring:message code="menuManage.orderNo" />';
	var column2 = '<spring:message code="menuManage.caption" />';
	var column3 = '<spring:message code="menuManage.action" />';
	var column4 = '<spring:message code="menuManage.visible" />';
	var columns = [{ 
				        "sTitle": actionLabel,
				        "mData": null,
				        "sWidth": "5%",
				        "sClass": "text-center",
				        "fnRender": function(objData) {
			        	 	var menuId = objData.aData.menuId; 
			        		var url =  '/initMenu.htm?menuId=' + menuId;
				       		     
			      		  	var returnButton = "<a href='#' onclick='postAction(\"" + url + "\")'><i class='fa fa-pencil'></i></a>";
			           	  	return returnButton;
				        }
				   }
				  ,{ "sTitle": column1, "mData": "menuId" , "sWidth": "3%", sClass: "text-center"}
				  ,{ "sTitle": column2, "mData": "menuName" }
				  ,{ "sTitle": column3, "mData": "menuAction" }
				  ,{ "sTitle": column4, "mData": "menuEnabled" , "sWidth": "3%", sClass: "text-center"}];
	
	$(document).ready(function() {
		oTable = initajaxDataTable($('#resultTableId'), columns);
		
		$("#menuName").onEnter( function() {
			if(!empty($("#menuName").val())){
				searchRole();
			}
		});

		$('#menuSearchBtn').click();
	} );
	
	function searchRole() {
		var dataString = serialize($("#menuManagementDomain")[0]);
		oTable = ajaxDataTable($('#resultTableId'), columns, 'getMenuList.htm', dataString, recordPerPage, true, true,false);
	}
	
	function initChild() {
		
		postAction('/initMenu.htm?menuId=');
	}
	
	$("#menuManagementDomain").on("submit",function(){
		return false;
	});
	 
	function deleteMenu(roleID){
  		alertConfirm(confirmDeleteMsg,"deleteMenuManagement", roleID);
	}
	
	function deleteMenuManagement(roleID){
		window.location = "${deleteUrl}?menuId=" + roleID;
	}
	
	function clearForm() {
		$('#menuName').val('');
	}
	
</script>