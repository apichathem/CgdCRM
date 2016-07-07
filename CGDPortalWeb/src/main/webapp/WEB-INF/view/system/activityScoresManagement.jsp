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
			<spring:message code="activityScoresManagement.title" />
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="home.htm"> <spring:message code="menu.home" />
			</a> <i class="fa fa-angle-right"></i></li>
			<li><i class="fa fa-money"></i><a href="#"> <spring:message code="activityScoresManagement.title" />
			</a></li>
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<form:form id="activityScoresManagementDomain" action="/activityScoresList.htm" class="form-horizontal" autocomplete="off" method="POST">
	<input type="hidden" name="sEcho" value="1" />
	<div class="row">
		<div class="col-md-12">
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-reorder"></i>
						<spring:message code="activityScoresManagement.topic" />
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
										<label class="control-label col-md-5"><spring:message code="activityScoresManagement.name" /></label>
										<div class="col-md-7">
											<input type="text" name="actName" id="actName" class="form-control" />
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
							<button class="btn blue" type="button" onclick="searchActivityScore();">
								<i class="fa fa-search"></i>
								<spring:message code="button.search.label" />
							</button>
							<button class="btn default" type="button" onclick="clearForm()">
								<spring:message code="button.cancel.label" />
							</button>
							<button class="btn green" type="button" onclick="initChild()">
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
		<!-- BEGIN EXAMPLE TABLE PORTLET-->
		<c:url var="editUrl" value="/initActivityScores.htm" />
		<c:url var="deleteUrl" value="/deleteActivityScoresById.htm" />
			
		<table id="resultTableId" class="table table-bordered dataTable">
		</table>
		<!-- END EXAMPLE TABLE PORTLET-->
	</div>
</div>

<script>

	var oTable;
	var recordPerPage = <%=JLOWebConstant.recordsPerPage%>;
	var column1 = '<spring:message code="activityScoresManagement.id" />';
	var column2 = '<spring:message code="activityScoresManagement.name" />';
	var column3 = '<spring:message code="activityScoresManagement.action" />';
	var column4 = '<spring:message code="activityScoresManagement.score" />';
	var columns = [{ 
				        "sTitle": actionLabel,
				        "mData": null,
				        "sWidth": "1%",
				        "sClass": "text-center",
				        "fnRender": function(objData) {
			        	 	var actId = objData.aData.actId; 
			        		var url =  '/initActivityScores.htm?actId=' + actId;
				       		     
			      		  	var returnButton = "<a href='#' onclick='postAction(\"" + url + "\")'><i class='fa fa-pencil'></i></a>";
			           	  	return returnButton;
				        }
				   }
				  ,{ "sTitle": column1, "mData": "actId" , "sWidth": "1%", sClass: "text-center"}
				  ,{ "sTitle": column2, "mData": "actName" , "sWidth": "10%", sClass: "text-left"}
				  ,{ "sTitle": column3, "mData": "actActionCode" , "sWidth": "15%", sClass: "text-left"}
				  ,{ "sTitle": column4, "mData": "actScores" , "sWidth": "3%", sClass: "text-right"}];
	
	$(document).ready(function() {
		oTable = initajaxDataTable($('#resultTableId'), columns);
		
		$("#actName").onEnter( function() {
			if(!empty($("#actName").val())){
				searchActivityScore();
			}
		});
	} );
	
	function searchActivityScore() {
		var dataString = serialize($("#activityScoresManagementDomain")[0]);
		oTable = ajaxDataTable($('#resultTableId'), columns, 'getActivityScoresList.htm', dataString, recordPerPage, true, true);
	}
	
	function initChild() {
		
		postAction('/initActivityScores.htm?actId=');
	}
	
	$("#activityScoresManagementDomain").on("submit",function(){
		return false;
	});
	 
	function deleteActivityScores(roleID){
  		alertConfirm(confirmDeleteMsg,"deleteActivityScoresManagement", roleID);
	}
	
	function deleteActivityScoresManagement(roleID){
		window.location = "${deleteUrl}?actId=" + roleID;
	}
	
	function clearForm() {
		$('#actName').val('');
	}
	
</script>