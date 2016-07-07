<%@page import="com.locus.jlo.web.constant.JLOWebConstant"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%
	int randNum = (int)(Math.random()*1000000);
%>

<div class="modalContactConsulting" id="modalContactConsulting_<%=randNum%>">

<form id="contactCriteriaDialog_<%=randNum%>" class="form-horizontal" >

	<div class="row">
		<div class="col-md-12">
			<div class="row">
				<div class="col-md-4">
					<div class="form-group">
						<label class="control-label col-md-5"> <spring:message code="dialog.contact.firstname" /> </label>
						<div class="col-md-7">
							<input type="text" id="txtFirstNameDlg" name="txtFirstNameDlg" class="form-control" maxlength="50"/>
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="form-group">
						<label class="control-label col-md-5"> <spring:message code="dialog.contact.lastname" /> </label>
						<div class="col-md-7">
							<input type="text" id="txtLastNameDlg" name="txtLastNameDlg" class="form-control" maxlength="50"/>
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="form-group">
						<label class="control-label col-md-5"> <spring:message code="dialog.contact.mobile" /> </label>
						<div class="col-md-7">
							<input type="text" id="txtMobileDlg" name="txtMobileDlg" class="form-control"/>
						</div>
					</div>
				</div>
			</div>
			
			<div class="form-actions right">
				<button id="btnSearchContact" class="btn blue" type="button" onclick="searchContactList();">
					<i class="fa fa-search"></i>
					<spring:message code="button.search.label" />
				</button>
				<button id="btnCreateContact" class="btn green" type="button" data-target="#createContactSubPopupDialog" data-toggle="modal">
					<i class="fa fa-plus"></i>
					<spring:message code="button.create.label" />
				</button>
			</div>
		</div>
		<div class="col-md-12">
			<table id="contactDialogDataTable" class="table table-bordered dataTable"></table>	
		</div>	
	</div>
</form>


<script>
	var evalFuncCont ="${callbackfn}";
	
	var oTableCont;
	
	var recordPerPage = <%=JLOWebConstant.recordsPerPage%>;
	var columnContactDlg1 = '<spring:message code="dialog.contact.result.no" />';
	var columnContactDlg2 = '<spring:message code="dialog.contact.result.fullName" />';
	var columnContactDlg3 = '<spring:message code="dialog.contact.result.citizenId" />';
	var columnContactDlg4 = '<spring:message code="dialog.contact.result.mobile" />';
	var columnContactDlg5 = '<spring:message code="dialog.contact.result.email" />';

	var columnContactDlgs = [
			{
				"sTitle" : columnContactDlg1,
				"mData" : "indId"
			},
			{
				"sTitle" : columnContactDlg2,
				"mData": "firstName" ,
			},
			{
				"sTitle" : columnContactDlg3,
				"mData" : "citizenId"
			},
			{
				"sTitle" : columnContactDlg4,
				"mData" : "mobileNo"
			},
			{
				"sTitle" : columnContactDlg5,
				"mData" : "email"
			},
	];

	$(document).ready(function() {
		
		$(".modalContactConsulting").not("#modalContactConsulting_<%=randNum%>").remove();
		
		initajaxDataTable($('#contactDialogDataTable'), columnContactDlgs);
		
		searchContactList();
		
		$('#btnCreateContact').click(function (){
			console.log("btnCreateContact click");
			loadContentIntoModal($("#modal_content_div_createContect_popup"), "openModalDialog.htm","createContactConsultingDialog","dialog.contact.detail.topic", "callbackCreateContactConsulting");
		});
		
		$('#contactDialogDataTable tbody').on('dblclick touchstart', 'tr', function() {
			var aPos = oTableCont.fnGetPosition(this);
			var aData = oTableCont.fnGetData(aPos[0]);
			
			setDataIntoComponent(evalFuncCont,aData[aPos]);

		});
		
		
		 
		
	});
	
	function callbackCreateContactConsulting(data){
		console.log(data);
		searchContactList();
		$("#createContactSubPopupDialog").modal("hide");
	}
	
	function searchContactList() {
		var firstName = $('#txtFirstNameDlg').val();
		var lastName = $('#txtLastNameDlg').val();
		var mobile = $('#txtMobileDlg').val();
		
		var param = "custId="+$("#popcustId").val()+"&firstName=" + firstName + "&lastName=" + lastName + "&mobile=" + mobile;
		oTableCont = ajaxDataTable($('#contactDialogDataTable'), columnContactDlgs, 'getContactConsultingList.htm', param, recordPerPage, true, true);
		
		
	}
</script>

</div>