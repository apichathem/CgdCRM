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
		<locus:privilege oper="ADD">
			<div align="right">
				<button type="button" class="btn green" id="btnSelectAddress" name="btnCreateContact" data-target="#dlgSearchAddress" data-toggle="modal">
					<spring:message code="button.select.label" />
				</button>
			</div>
		</locus:privilege>
		<c:url var="editUrl" value="/customerAddress.htm" />
		<c:url var="deleteUrl" value="/deleteCustomerAddress.htm" />
		<table id="addressDataTable" class="table table-bordered dataTable"></table>
	</div>
</div>

<!-- search address dialog -->	
<div class="modal fade" id="dlgSearchAddress" role="basic" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div id="modal_search_address_content_div"></div>
		</div>
	</div>
</div>

<!-- create address dialog -->
<div class="modal fade" id="dlgCreateAddress" role="basic" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div id="modal_create_address_content_div"></div>
		</div>
	</div>
</div>	

<script type="text/javascript">
	var custId = $('#custId').val();

	var recordPerPage = <%=JLOWebConstant.recordsPerPage%>;
	var column1 = '<spring:message code="cust.address.no" />';
	var column2 = '<spring:message code="cust.address.type" />';
	var column3 = '<spring:message code="cust.address.address1" />';
	var column4 = '<spring:message code="cust.address.address2" />';
	var column5 = '<spring:message code="cust.address.subDistrict" />';
	var column6 = '<spring:message code="cust.address.district" />';
	var column7 = '<spring:message code="cust.address.province" />';
	var column8 = '<spring:message code="cust.address.postcode" />';
	var column9 = '<spring:message code="cust.address.primary" />';
	
	var columns = [{
				  	  "sTitle": actionLabel,
					  "mData": null,
					  "sWidth": "5%",
					  "sClass": "text-center",
					  "fnRender": function(objData) {
						  
						var addrId = objData.aData.addrId;
						var custId = objData.aData.custId;
						var editUrl = objData.aData.editUrl;
						var deleteUrl = objData.aData.deleteUrl;	
						
						var editButton = "";
						var deleteButton = "";
						var returnButton = "";
						   
						if(!empty(editUrl)){
							editButton = "<a href='#' onclick='javascript:editAddressDlg("+custId + "," +addrId+")' data-target='#dlgCreateAddress' data-toggle='modal'><i class='fa fa-pencil'></i></a>";	
						}
					  	
						if(!empty(deleteUrl)){
							 deleteButton = "<a href='javascript:deleteAddressDlg("+custId + "," +addrId+");'<i class='fa fa-trash-o'></i></a>";
						}
												   
					  	returnButton = editButton + '&nbsp;&nbsp;&nbsp;'+ deleteButton;
						  
					 	return returnButton;
					  }
				   }
				  ,{ "sTitle": column1, "sWidth": "3%","sClass": "text-center","mData": "addrId" }
				  ,{ "sTitle": column2, "mData": "addrTypeName" }
				  ,{ "sTitle": column3, "mData": "addr1" }
	              ,{ "sTitle": column4, "mData": "addr2" }
	              ,{ "sTitle": column5, "mData": "subArea" }
	              ,{ "sTitle": column6, "mData": "area" }
	              ,{ "sTitle": column7, "mData": "province" }
	              ,{ "sTitle": column8, "mData": "postalCode","sClass": "text-center" }
	              ,{
	            	  "sTitle": column9,
	            	  "mData": null,
	            	  "sClass": 'text-center',
	            	  "fnRender": function(objData) {
	            		  var primaryYn = objData.aData.primaryYn;
	            		  var checked = '';
	            		  var addrId = objData.aData.addrId;
	            		  var custId = objData.aData.custId;
	            		  var editUrl = objData.aData.editUrl;
	            		  
	            		  if (primaryYn == 'Y') {
	            			  checked = 'checked';
	            		  }
	            		  
	            		  
	            		  if(!empty(editUrl)){
	            			  returnButton = '<input type="checkbox" name="primary" value="' + primaryYn + '"' + checked + ' onclick="javascript:choosePrimary('+custId+','+addrId+')"/>';  
	            		  }else{
	            			  returnButton = '<input type="checkbox" name="primary" disabled value="' + primaryYn + '"' + checked + ' onclick="javascript:choosePrimary('+custId+','+addrId+')"/>';
	            		  }
	            		  
	            		  
	            	  	  return returnButton;
	            	  }
	               }
	              ,
	               ];
	var oTableAddress;
	$(document).ready(function(){
		var mode = $('#mode').val();
		if (mode == "insert") {
			$("#btnSelectAddress").attr("disabled", "disabled");
		} else if (mode == "update"){
			$("#btnSelectAddress").removeAttr("disabled");
		}
		
		if (custId != '') {
			oTableAddress = ajaxDataTable($('#addressDataTable'), columns, 'getCustAddressList.htm', 'custId='+custId, recordPerPage, true, true);
		}
		
		$('#btnSelectAddress').click(function () {
			loadContentIntoModal($("#modal_search_address_content_div"), "openModalDialog.htm", "customerAddressDialog", "dialog.address.topic", "selectCustomerAddress"); 
		});
		
	});
	
	function selectCustomerAddress(jsonObj) {
		var addrId = jsonObj.addrId;
		var param = "custId=" + custId + "&addrId=" + addrId;
		
		// save TP_CUST_ADDRESS
		var jsonResult = getJsonData('saveCustomerAddress.htm', param, 'POST');
		if (jsonResult.resultCode == '0') {
			
			alertMessage('<spring:message code="cust.custCaptionDetailLbl" /> : <spring:message code="cust.addressTab"/>' , jsonResult.resultMessage);
			
			$("#dlgSearchAddress").modal("hide");
			
			ajaxDataTable($('#addressDataTable'), columns, 'getCustAddressList.htm', 'custId='+custId, recordPerPage, true, true);
		} else {
			alertMessage('<spring:message code="cust.custCaptionDetailLbl" /> : <spring:message code="cust.addressTab"/>' , jsonResult.resultMessage);
		}
	}
	
	function createCustomerAddress() {
		var $valid = validateForm($("#addressForm"));
		if (!$valid) {
			return false;
		} else {
			var param = serialize($("#addressForm")[0]);
			param = param + '&custId='+custId;
			console.log(param);
			ajaxSubmitForm($("#addressForm"), function(data) {
				
				if (data != null) {
					if (data.resultCode == '0') {
						//$("#dlgCreateAddress").modal("hide");
						//$("#dlgSearchAddress").modal("hide");
						var addrId = data.model.addrId;
						console.log('New Addr Id ' + addrId);
						$("#addressForm #addrId").val(addrId);
						$("#addressForm #custId").val(custId);
						
						ajaxDataTable($('#addressDataTable'), columns, 'getCustAddressList.htm', 'custId='+custId, recordPerPage, true, true);
						
						$("#dlgCreateAddress").modal("hide");
					}
					
					alertMessage('<spring:message code="cust.custCaptionDetailLbl" /> : <spring:message code="cust.addressTab"/>' , data.resultMessage);
				}
			});
		}
	}
	
	function deleteAddressDlg(custId, addrId){
		var param = "custId=" + custId + "&addrId=" + addrId;
  		alertConfirm(confirmDeleteMsg, "deleteAddress", param);
	}
	
	function deleteAddress(param){
		
		var result = getJsonData("${deleteUrl}", param, "POST");
		if (result != null) {
			alertMessage('<spring:message code="cust.custCaptionDetailLbl" /> : <spring:message code="cust.addressTab"/>' , result.resultMessage);
			if (result.resultCode == '0') {
				ajaxDataTable($('#addressDataTable'), columns, 'getCustAddressList.htm', 'custId='+custId, recordPerPage, true, true);
			}
		}
	}
	
	function editAddressDlg(custId, addrId) {
		var param = custId + "," + addrId;
		loadContentIntoModalWithParameter($("#modal_create_address_content_div"), "addressDialogMain.htm", "createAddressDialog", "dialog.address.detail.topic","", param, "");
	}
	
	function choosePrimary(custId, addrId) {
		var param = "custId=" + custId + "&addrId=" + addrId;
		console.log('param=' + param)
		var jsonResult = getJsonData('choosePrimary.htm', param, 'POST');
		if (jsonResult.resultCode == '0') {
			ajaxDataTable($('#addressDataTable'), columns, 'getCustAddressList.htm', 'custId='+custId, recordPerPage, true, true);
		}
		
		alertMessage('<spring:message code="cust.custCaptionDetailLbl" /> : <spring:message code="cust.addressTab"/>' , jsonResult.resultMessage);
	}
</script>