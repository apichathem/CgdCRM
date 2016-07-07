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
			<spring:message code="userManagement.title" />
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="home.htm"> <spring:message code="menu.home" />
			</a> <i class="fa fa-angle-right"></i></li>
			<li><a href="userManagement.htm"> <spring:message code="menu.system.userManagement" />
			</a></li>
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<form:form id="userManagementDomain" action="searchUserList.htm" modelAttribute="userModelBean" class="form-horizontal">
	
	<div class="row">
		<div class="col-md-12">
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-reorder"></i>
						<spring:message code="userManage.topic" />
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
									<label class="control-label col-md-5"><spring:message code="userManage.login" /></label>
									<div class="col-md-7">
										<form:input id="loginId" path="loginId" class="form-control" />
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-5"><spring:message code="userManage.agentName" /></label>
									<div class="col-md-7">
										<form:input id="firstName" path="firstName" class="form-control" />
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-5"><spring:message code="userManage.agentSurname" /></label>
									<div class="col-md-7">
										<form:input id="lastName" path="lastName" class="form-control" />
									</div>
								</div>
							</div>
						</div>
						<div style="display:none" id="advanceSearchDiv">					
						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-5"><spring:message code="userManage.agentNo" /></label>
									<div class="col-md-7">
										<form:input id="agentNo" path="agentNo" class="form-control" />
									</div>
								</div>
							</div>
							<div class="col-md-4"></div>
							<div class="col-md-4"></div>
						</div>
						</div>
					</div>
					<div class="form-actions right">
						<!-- <button class="btn blue" type="button" onclick="exportToCSV()">
							<i class="fa fa-search"></i>
							Export to CSV
						</button> -->
						<button id="userSearchBtn" class="btn blue" type="button" onclick="searchUser();">
							<i class="fa fa-search"></i>
							<spring:message code="button.search.label" />
						</button>
						<%-- <button class="btn default" type="button" onclick="clearForm()">
							<spring:message code="button.cancel.label" />
						</button> --%>
						<%-- <button id="btn_searchAdvanceSearch" class="btn dark" type="button" onclick="showAdvanceSearch();">
							<i class="fa fa-search"></i> <spring:message code="button.advanceSearch.label"/>
						</button> --%>
						<button id="btn_searchQuickSearch" class="btn dark" type="button" onclick="hideAdvanceSearch();">
							<i class="fa fa-search"></i> <spring:message code="button.quickSearch.label"/>
						</button>
						 <button class="btn green" type="button" id="btnInitChild">
							<i class="fa fa-plus"></i>
							<spring:message code="button.create.label" />
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
		<c:url var="editUrl" value="/initManageUser.htm" />
		<c:url var="deleteUrl" value="/deleteUser.htm" />
		<table id="resultTableId" class="table table-bordered dataTable">
		</table>
	</div>
</div>

<script type="text/javascript">
	var oTable;
	var recordPerPage = <%=JLOWebConstant.recordsPerPage%>;

	var column1 = '<spring:message code="userManage.result.no" />';
	var column2 = '<spring:message code="userManage.result.login" />';
	var column3 = '<spring:message code="userManage.result.agentNo" />';
	var column4 = '<spring:message code="userManage.result.agentName" />';
	var column8 = '<spring:message code="userManage.result.email" />';
	var column9 = '<spring:message code="userManage.result.mobileNo" />';
	var column10 = '<spring:message code="userManage.result.status" />';

	var columns = [{ 
			        "sTitle": actionLabel,
			        "mData": null,
			        "sClass": "text-center",
			        "sWidth": "3%",
			        "fnRender": function(objData) {
				        var editUrl = objData.aData.editUrl;     
				      	return editUrl;
				        }  
				   	}
				  ,{ "sTitle": column1, "mData": "userId" , sClass: "text-center"}
				  ,{ "sTitle": column2, "mData": "loginId" }
				  //,{ "sTitle": column3, "mData": "agentNo" }
				  ,{ "sTitle": column4, 
					  "mData": null ,
					  "fnRender": function(objData) {
		         		  var firstName = objData.aData.firstName; 
						  var lastName = objData.aData.lastName; 
		        		  var returnButton = firstName + " " + lastName;
		             	  return returnButton;
				  		}
				  }
				  ,{ "sTitle": column8, "mData": "email" }
				  ,{ "sTitle": column9, 
					  "mData": null ,
					  "fnRender": function(objData) {
									  var mobileNo = objData.aData.mobileNo;
									  if (mobileNo != null) {
										  return mobileNo.replace(/(\d{3})(\d{3})(\d{4})/, "$1-$2-$3");
									  } else {
										  return '';
									  }
							   	   	
								  }
				  }
				  ,{ "sTitle": column10, 
					  "mData": null , 
					  "sClass": "text-center",
					  "fnRender": function(objData) {
						  var useYn = objData.aData.useYn;
						  if (useYn == 'Y') {
							  return "Yes";
						  } else {
							  return "No";
						  }
				   	   	
					  }
				  }
			      
			     ];
				
	$(document).ready(function() {  
		$('#btn_searchQuickSearch').hide();
		oTable = initajaxDataTable($('#resultTableId'), columns);
		//searchUser();
		
		$("#loginId").onEnter( function() {
			 
			if(!empty($("#loginId").val())){
				searchUser();
			}
		});
		
		$("#firstName").onEnter( function() {
			 
			if(!empty($("#firstName").val())){
				searchUser();
			}
		});
		
		$("#lastName").onEnter( function() {
			 
			if(!empty($("#lastName").val())){
				searchUser();
			}
		});
		
		$("#btnInitChild").click(function () {
	 		
			initChild();
	 	});

		$('#userSearchBtn').click();
		
	});
	
	function exportToCSV(){
		console.log(oTable);
		exportCSV(oTable.fnGetData(0),'Users Management',true);
	}
	
	function searchUser() {
		
		var dataString = serialize($("#userManagementDomain")[0]);
		oTable = ajaxDataTable($('#resultTableId'), columns, 'getUserList.htm', dataString, recordPerPage, true, true);
	}

	function initChild() {
		$('#userManagementDomain').attr('action', 'initManageUser.htm?id=');
		$('#userManagementDomain').submit();
	}
	
	function showAdvanceSearch(){
		$('#advanceSearchDiv').show();	
		$('#btn_searchAdvanceSearch').hide();
		$('#btn_searchQuickSearch').show();
	}

	function hideAdvanceSearch(){
		$('#advanceSearchDiv').hide();
		$('#btn_searchQuickSearch').hide();
		$('#btn_searchAdvanceSearch').show();	
	}
	
	function clearForm() {
		$('#loginId').val('');
		$('#firstName').val('');
		$('#lastName').val('');
		$('#agentNo').val('');
	}
</script>
