<%@page import="com.locus.jlo.web.constant.JLOWebConstant"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="locus" uri="/WEB-INF/tld/locus.tld"%>

<!-- Begin Content -->
<div class="row">
	<div class="col-md-12">
		<br/>
		<!-- Panel Button -->
		<div align="right">
			<locus:privilege oper="ADD">
				<button type="button" class="btn green" id="btnSelectContact" name="btnCreateContact" data-target="#dlgSearchContact" data-toggle="modal">
					<spring:message code="button.select.label" />
				</button>
			</locus:privilege>
			<button type="button" class="btn green" id="btnOpenContactDetail" data-target="#dlgCreateContact" data-toggle="modal">
			</button>
		</div>
		
		<!-- Datatable -->
		<input type="hidden" id="relationContactId"/>
		<input type="hidden" id="editContact"/>
		<c:url var="editUrl" value="/editCustomerContact.htm" />
		<c:url var="deleteUrl" value="/deleteCustomerContact.htm" />
		<table id="contactDataTable" class="table table-bordered dataTable"></table>	
	</div>	
</div>

<!-- search contact dialog -->	
<div class="modal fade" id="dlgSearchContact" role="basic" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div id="modal_search_content_div"></div>
		</div>
	</div>
</div>

<!-- relationship dialog -->
<div class="modal fade" id="dlgSaveRelation" role="basic" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div id="modal_relation_content_div"></div>
		</div>
	</div>
</div>		

<!-- create contact dialog -->
<div class="modal fade" id="dlgCreateContact" role="basic" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div id="modal_create_content_div"></div>
		</div>
	</div>
</div>		

<script type="text/javascript">
	var recordPerPage = <%=JLOWebConstant.recordsPerPage%>;
	var oTableCont;
	var custId = $('#custId').val();
	var column1 = '<spring:message code="cust.contact.no" />';
	var column2 = '<spring:message code="cust.contact.fullname" />';
	var column4 = '<spring:message code="cust.contact.relationship" />';
	var column5 = '<spring:message code="cust.contact.mobile" />';
	var column6 = '<spring:message code="cust.contact.phone" />';
	var column7 = '<spring:message code="cust.contact.email" />';
	
	var columns = [{
					  "sTitle":actionLabel,
					  "mData": null,
					  "sWidth": "5%",
					  "sClass": "text-center",
					  "fnRender": function(objData) {
						  
							var contId = objData.aData.contId;
							var custId = objData.aData.custId;
							var editUrl = objData.aData.editUrl;
							var deleteUrl = objData.aData.deleteUrl;
							
							var editButton = "";
							var returnButton = "";
							var deleteButton = "";
							
							if(!empty(editUrl)){
								editButton = "<a href='#' onclick='javascript:editContactDlg("+custId + "," +contId+")' data-target='#dlgCreateContact' data-toggle='modal'><i class='fa fa-pencil'></i></a>";	
							}
						  	
							if(!empty(deleteUrl)){
								deleteButton = "<a href='javascript:deleteContactDlg("+custId + "," +contId+");'<i class='fa fa-trash-o'></i></a>";
							}
							
							returnButton = editButton + '&nbsp;&nbsp;&nbsp;'+ deleteButton;
							
						    return returnButton;
					  }
				   } 
				  ,{ "sTitle": column1, "sClass": "text-center", "sWidth": "3%", "mData": "contId" }
				  ,{ 	"sTitle": column2, 
					  	"mData": null ,
					  	"fnRender": function(objData) {
				   		  	var firstName = objData.aData.firstName; 
							var lastName = objData.aData.lastName; 
				  		  	var returnButton = firstName + " " + lastName;
				       	  	return returnButton;
				  		}
				   }
				  ,{ "sTitle": column4, "mData": "relationName" }
				  ,{ "sTitle": column5, "mData": "mobileNo" }
				  ,{ "sTitle": column6, "mData": "homePhone" }
				  ,{ "sTitle": column7, "mData": "email" }
				  ,
	               ];
	
	$(document).ready(function(){
		$('#btnOpenContactDetail').hide();
		var mode = $('#mode').val();
		if(mode == "insert"){
			$("#btnSelectContact").attr("disabled", "disabled");
		}else if(mode == "update"){
			$("#btnSelectContact").removeAttr("disabled");
		}
		
		if (custId != '') {
			oTableCont = ajaxDataTable($('#contactDataTable'), columns, 'getCustContactList.htm', 'custId='+custId, recordPerPage, true, true);
		}
		
		$('#btnSelectContact').click(function () {
			loadContentIntoModal($("#modal_search_content_div"), "openModalDialog.htm", "customerContactDialog", "dialog.contact.topic", "selectCustomerContact"); 
		});
		
		$('#btnOpenContactDetail').click(function () {
			loadContentIntoModal($("#modal_create_content_div"), "openModalDialog.htm", "createContactDialog", "dialog.contact.topic", ""); 
		});
		
		$('#dlgSaveRelation').on('hidden.bs.modal', function () {
			console.log("Close relation dialog");
			$(".modalRelationId").remove();
		});
	});
	
	function editContactDlg(custId, contId) {
		//$('#btnOpenContactDetail').click();
		var param = custId + "," + contId;
		loadContentIntoModalWithParameter($("#modal_create_content_div"), "contactDialogMain.htm", "createContactDialog", "dialog.contact.detail.topic","", param, "");
	}
	
	function deleteContactDlg(custId, contId){
		var param = "custId=" + custId + "&contId=" + contId;
  		alertConfirm(confirmDeleteMsg,"deleteContact", param);
	}
	
	function deleteContact(param){
		
		var result = getJsonData("${deleteUrl}", param, "POST");
		if (result != null) {
			alertMessage('<spring:message code="cust.custCaptionDetailLbl" /> :: <spring:message code="cust.contactTab"/>' , result.resultMessage);
			if (result.resultCode == '0') {
				oTableCont = ajaxDataTable($('#contactDataTable'), columns, 'getCustContactList.htm', 'custId='+custId, recordPerPage, true, true);
			}
		}
	}
	
	function saveRelation() {
		var contId = $("#relationContactId").val();
		var relationCd = $("#relationCd").val();
		
		var param = 'contId=' + contId + '&custId=' + custId + '&relationCd=' + relationCd;
		var result = getJsonData('saveCustomerContact.htm', param, 'POST');
		
		if (result != null) {
			if (result.resultCode == '0') {
				$("#dlgSaveRelation").modal("hide");
				$("#dlgSearchContact").modal("hide");
				oTableCont = ajaxDataTable($('#contactDataTable'), columns, 'getCustContactList.htm', 'custId='+custId, recordPerPage, true, true);
			}
			
			alertMessage('<spring:message code="cust.custCaptionDetailLbl" /> :: <spring:message code="cust.contactTab"/>' , result.resultMessage);
		}
	}
	
	function createCustomerContact() {
		
		var $valid = validateForm($("#contactForm"));
		if (!$valid) {
			return false;
		} else {
			var param = serialize($("#contactForm")[0]);
			param = param + '&custId='+custId;
			console.log(param);
			ajaxSubmitForm($("#contactForm"), function(data) {
				
				if (data != null) {
					if (data.resultCode == '0') {
						//$("#dlgCreateContact").modal("hide");
						//$("#dlgSearchContact").modal("hide");
						var addrId = data.model.addrId;
						var indId = data.model.indId;
						console.log('New Cont Id ' + addrId + ',' + indId);
						$("#contactForm #indId").val(indId);
						$("#contactForm #contId").val(addrId);
						$("#contactForm #custId").val(custId);
						
						oTableCont = ajaxDataTable($('#contactDataTable'), columns, 'getCustContactList.htm', 'custId='+custId, recordPerPage, true, true);
						
						$("#dlgCreateContact").modal("hide");
					}
					
					alertMessage('<spring:message code="cust.custCaptionDetailLbl" /> : <spring:message code="cust.addressTab"/>' , data.resultMessage);
				}
			});
		}
		
	}
	
</script>