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
			<spring:message code="loginhistory.title" />
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="home.htm"> <spring:message code="menu.home" />
			</a><i class="fa fa-angle-right"></i></li>
			<li><i class="fa fa-users"></i><a href="#"> <spring:message code="menu.system.loginhistory" />
			</a></li>
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<form:form id="loginHistoryDomain" class="form-horizontal" autocomplete="off" method="POST">
	<div class="row">
		<div class="col-md-12">
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-reorder"></i>
						<spring:message code="loginhistory.topic" />
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
										<label class="control-label col-md-5"> </label>
										<div class="col-md-7">
											<input type="hidden" name="loginId" id="loginId" class="form-control" />
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
							
						</div>
					<!-- END FORM-->
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
	var column1 = '<spring:message code="loginhistory.result.loginid" />';
	var column2 = '<spring:message code="loginhistory.result.logintime" />';
	var column3 = '<spring:message code="loginhistory.result.sourceip" />';
	var column4 = '<spring:message code="loginhistory.result.status" />';
	var column5 = '<spring:message code="loginhistory.result.browser" />';
	var column6 = '<spring:message code="loginhistory.result.platform" />';
	var columns = [{ "sTitle": column1, "mData": "loginId"}
				  ,{ "sTitle": column2, "mData": "loginTime" }
				  ,{ "sTitle": column3, "mData": "sourceIp" }
				  ,{ "sTitle": column4, "mData": "status" }
				  ,{ "sTitle": column5, "mData": "browser" }
				  ,{ "sTitle": column6, "mData": "platform" }];
	
	$(document).ready(function() {
		oTable = initajaxDataTable($('#resultTableId'), columns);
	} );
	
	function searchRole() {
		var dataString = serialize($("#loginHistoryDomain")[0]);
		oTable = ajaxDataTable($('#resultTableId'), columns, 'getLoginHistoryList.htm', dataString, recordPerPage, true, true);
	}
	 
</script>