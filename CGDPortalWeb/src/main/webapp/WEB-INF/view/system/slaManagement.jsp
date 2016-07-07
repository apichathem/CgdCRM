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
			<spring:message code="sla.title" />
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="home.htm"> <spring:message code="menu.home" />
			</a><i class="fa fa-angle-right"></i></li>
			<li><a href="#"> <spring:message code="menu.system" />
			</a> <i class="fa fa-angle-right"></i></li>
			<li><a href="#"> <spring:message code="menu.system.sla" />
			</a></li>
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<form:form id="slaManagementForm" name="slaManagementForm" class="form-horizontal" autocomplete="off" method="POST">
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-reorder"></i>
					<spring:message code="slamanagement.title" />
				</div>
				<div class="tools">
					<a class="collapse" href="javascript:;"> </a>
				</div>
			</div>
			<div class="portlet-body">
				<div class="form-body">
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-5">
									<spring:message code="slamanagement.criteria.salname" />
								</label>
								<div class="col-md-7">
									<input id="slaname" name="slaname" type="text" class="form-control" />
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="form-actions right">
				<button id="searchSlaBtn" class="btn blue" type="button">
					<i class="fa fa-search"></i>
					<spring:message code="button.search.label" />
				</button>
				<%-- <button id="createSlaBtn" class="btn green" type="button">
					<i class="fa fa-plus"></i>
					<spring:message code="button.create.label" />
				</button> --%>
			</div>
		</div>
	</div>
</div>
</form:form>
<div class="row">
	<div class="col-md-12">
		<table id="resultTableId" class="table table-bordered dataTable">
		</table>
	</div>
</div>

<script>
	var oTable;
	var recordPerPage = <%=JLOWebConstant.recordsPerPage%>;
	var column1 = '<spring:message code="slamanagement.result.no" />';
	var column2 = '<spring:message code="slamanagement.result.slaname" />';
	var column3 = '<spring:message code="slamanagement.result.slavalue" />';
	var column4 = '<spring:message code="slamanagement.result.slaunit" />';
	var columns = [{ 
				        "sTitle": actionLabel,
				        "mData": null,
				        "sWidth": "5%",
				        "sClass": "text-center",
				        "fnRender": function(objData) {
				    	 	var slaId = objData.aData.slaId; 
				    		var url =  '/initSla.htm?slaId=' + slaId;
				       		     
				  		  	var returnButton = "<a href='#' onclick='postAction(\"" + url + "\")'><i class='fa fa-pencil'></i></a>";
				       	  	return returnButton;
				        }  
				   }
	   			,{ "sTitle": column1, "mData": "slaId" , "sWidth": "3%", sClass: "text-center"}
				,{ "sTitle": column2, "mData": "slaName" }
				,{ "sTitle": column3, "mData": "slaUnit" }
				,{ "sTitle": column4, "mData": "slaUomName" }];
	  
	$(document).ready(function() {
		oTable = initajaxDataTable($('#resultTableId'), columns);

		$('#searchSlaBtn').click(function () {
			searchSlaList();
		});

		$('#createSlaBtn').click(function () {

		});

		$('#searchSlaBtn').click();
	});

	function searchSlaList() {
		var dataString = serialize($("#slaManagementForm")[0]);
		oTable = ajaxDataTable($('#resultTableId'), columns, 'getSlaList.htm', dataString, recordPerPage, true, true);
	}
	
</script>