<%@page import="com.locus.jlo.web.constant.JLOWebConstant"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%
	int randNum = (int)(Math.random()*1000000);
%>

<div class="modalDepartment" id="modalDepartment_<%=randNum%>">

<div class="row">
	<div class="col-md-12">
		<div class="row">
			<div class="col-md-4">
				<div class="form-group">
					<label class="control-label col-md-5">
						<spring:message code="dialog.department.code"/>
					</label>
					<div class="col-md-7">
						<input type="text" id="deptCodeDialog" name="deptCodeDialog" class="form-control" />
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="form-group">
					<label class="control-label col-md-5">
						<spring:message code="dialog.department.name"/>
					</label>
					<div class="col-md-7">
						<input type="text" id="deptNameDialog" name="deptNameDialog" class="form-control" />
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="form-group">
				</div>
			</div>
		</div>
		<div class="form-actions right">
			<button id="btnSearch" class="btn blue" type="button" onclick="searchDepartment();">
				<i class="fa fa-search"></i>
				<spring:message code="button.search.label" />
			</button>
		</div>
	</div>
	<div class="col-md-12">
		<table id="deptDialogDataTable" class="table table-bordered dataTable"></table>	
	</div>	
</div>

<script type="text/javascript">
var evalFuncDept ="${callbackfn}";

var recordPerPage = <%=JLOWebConstant.recordsPerPage%>;
var columnD1 = '<spring:message code="dialog.department.result.no" />';
var columnD2 = '<spring:message code="dialog.department.result.departmentcode" />';
var columnD3 = '<spring:message code="dialog.department.result.departmentname" />';
var columnD4 = '<spring:message code="dialog.department.result.parentcode" />';


var columnsDialog = [{ "sTitle": columnD1, "mData": "deptCode" }
				    ,{ "sTitle": columnD2, "mData": "deptCode" }
				    ,{ "sTitle": columnD3, "mData": "deptName" }
				    ,{ "sTitle": columnD4, "mData": "parentDeptCode" }
				    ];

$(document).ready(function() {
	$(".modalDepartment").not("#modalDepartment_<%=randNum%>").remove();
	initajaxDataTable($('#deptDialogDataTable'), columnsDialog);
	searchDepartment();
	
});

function searchDepartment() {
	var deptName = $("#deptNameDialog").val();
	var deptCode = $("#deptCodeDialog").val();
	
	var param = "deptName=" + deptName + "&deptCode=" + deptCode;
	var oTable = ajaxDataTable($('#deptDialogDataTable'), columnsDialog, 'getDepartmentList.htm', param, recordPerPage, true, true);
	
	$('#deptDialogDataTable tbody').on( 'dblclick touchstart', 'tr', function () {
		var aPos = oTable.fnGetPosition(this);
	    var aData = oTable.fnGetData( aPos[0] );
	    setDataIntoComponent(evalFuncDept,aData[aPos]);
	    
	  //selectedDepartment(aData[aPos]);
	});
	
}
</script>

</div>