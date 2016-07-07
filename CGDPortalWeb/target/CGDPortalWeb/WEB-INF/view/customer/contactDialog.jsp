<%@page import="com.locus.jlo.web.constant.JLOWebConstant"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%
	int contactNum = (int)(Math.random()*1000000);
%>

<div class="modalCustContId" id="modalCustContId_<%=contactNum%>">
	<form id="contactCriteriaDialog" class="form-horizontal" >
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
					<button id="btnCreateContact" class="btn green" type="button" data-target="#dlgCreateContact" data-toggle="modal">
						<i class="fa fa-plus"></i>
						<spring:message code="button.create.label" />
					</button>
					<button id="btnRelation" class="btn green" type="button" data-target="#dlgSaveRelation" data-toggle="modal">
					</button>
				</div>
			</div>
			<div class="col-md-12">
				<table id="contactDialogDataTable" class="table table-bordered dataTable"></table>	
			</div>	
		</div>
	</form>
</div>


<script type="text/javascript">

	var evalFuncCont ="${callbackfn}";
	var oTableContact;
	var recordPerPage = <%=JLOWebConstant.recordsPerPage%>;
	var messageTitle ='<spring:message code="menu.customer" />';
	var msgPleaseEnterWarning = '<spring:message code="message.please.enter.atLeastOne" />'; 
	var columnContactDlgs = "[]";	
	
	$(document).ready(function() {
		console.log("contact ready <%=contactNum%>");
		$(".modalCustContId").not("#modalCustContId_<%=contactNum%>").remove();
		$('#btnRelation').hide();
		
		declareColumnContactDialog();
		
		initajaxDataTable($('#contactDialogDataTable'), columnContactDlgs);
		
		//searchContactList();
		
		$('#btnCreateContact').click(function (){
			var param = $('#custId').val();
			loadContentIntoModalWithParameter($("#modal_create_content_div"), "contactDialogMain.htm", "createContactDialog", "dialog.contact.detail.topic","", param, "");
			$("#dlgSearchContact").modal("hide");
			//loadContentIntoModalWithParameter($("#modal_create_content_div"), "contactMain.htm", "createContactDialog", "dialog.contact.detail.topic","callbackFunctionAfterSave", "");
		});
		
		$('#btnRelation').click(function (){
			//alert($('#relationContactId').val());
			loadContentIntoModal($("#modal_relation_content_div"), "openModalDialog.htm","relationDialog","dialog.relation.topic", "");
		});
		
		$('#contactDialogDataTable tbody').on('dblclick touchstart', 'tr', function() {
			var aPos = oTableContact.fnGetPosition(this);
			var aData = oTableContact.fnGetData(aPos[0]);
			
			// Check duplicate contact before choose relation
			var contactId = aData[aPos].contId;
			var custId = $('#custId').val();
			
			var param = "custId=" + custId + "&contId=" + contactId;
			var dup = getJsonData('checkDuplicateContact.htm', param, 'POST');
			if (dup == true) {
				alertMessage('<spring:message code="cust.custCaptionDetailLbl" /> :: <spring:message code="cust.contactTab"/>' , '<spring:message code="lbl.duplicate" />');
			} else {
				$('#relationContactId').val(contactId);
				$('#btnRelation').click();
			}
		});
		
	});
	
	function declareColumnContactDialog(){
			
			var columnContactDlg1 = '<spring:message code="dialog.contact.result.no" />';
			var columnContactDlg2 = '<spring:message code="dialog.contact.result.fullName" />';
			var columnContactDlg3 = '<spring:message code="dialog.contact.result.citizenId" />';
			var columnContactDlg4 = '<spring:message code="dialog.contact.result.mobile" />';
			var columnContactDlg5 = '<spring:message code="dialog.contact.result.email" />';
	
			columnContactDlgs = [
					{
						"sTitle" : columnContactDlg1,
						"mData" : "contId"
					},
					{
						"sTitle" : columnContactDlg2,
						"mData": null ,
						  "fnRender": function(objData) {
			         		  var firstName = objData.aData.firstName; 
							  var lastName = objData.aData.lastName; 
			        		  var returnButton = firstName + " " + lastName;
			             	  return returnButton;
					  		}
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
					}];
			
		}
	
	function searchContactList() {
		
		if(validateEnterCriteriaAtLeastOne()){
			var firstName = $('#txtFirstNameDlg').val();
			var lastName = $('#txtLastNameDlg').val();
			var mobile = $('#txtMobileDlg').val();
			
			var param = "firstName=" + firstName + "&lastName=" + lastName + "&mobile=" + mobile;
			oTableContact = ajaxDataTable($('#contactDialogDataTable'), columnContactDlgs, 'getContactList.htm', param, recordPerPage, true, true);
			
		}else{
					
			alertMessage("<h5>"+messageTitle+"</h5>",msgPleaseEnterWarning);
			return false;
		}
				
		
		
	}
	
	function validateEnterCriteriaAtLeastOne(){
		 
		var contFirstName = $('#txtFirstNameDlg').val();
		var contLastName = $('#txtLastNameDlg').val();
		var conMobile = $('#txtMobileDlg').val();	 
		
		
		if (empty(contFirstName) && empty(contLastName) && empty(conMobile)){
			return false;
		}else{
			return true;
			
		}
	}
	
</script>