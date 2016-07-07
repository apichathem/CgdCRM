<%@page import="com.locus.common.utils.CollectionUtil"%>
<%@page import="com.locus.common.constant.WebPortalConstant"%>
<%--@page import="com.locus.jlo.entity.TpCodebookLang"--%>
<%@page import="com.locus.jlo.web.bean.modelbean.CodebookModelBean" %>
<%@page import="com.locus.jlo.web.util.CodeBookHelper" %>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="com.locus.jlo.web.constant.JLOWebConstant"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	Map<String, List<CodebookModelBean>>  codebookList = CodeBookHelper.getCodeBookList();
%>
<form id="contactCriteriaDialog" class="form-horizontal" >

	<div class="row">
		<div class="col-md-12">
			<div class="row">
				<div class="col-md-4">
					<div class="form-group">
						<label class="control-label col-md-5"> <spring:message code="dialog.address.criteria.addr1" /> </label>
						<div class="col-md-7">
							<input type="text" id="txtAddrDlg" name="txtAddrDlg" class="form-control" maxlength="50"/>
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="form-group">
						<label class="control-label col-md-5"> <spring:message code="dialog.address.criteria.subDistrict" /> </label>
						<div class="col-md-7">
							<input type="text" id="txtSubDistrictDlg" name="txtSubDistrictDlg" class="form-control" maxlength="50"/>
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="form-group">
						<label class="control-label col-md-5"> <spring:message code="dialog.address.criteria.district" /> </label>
						<div class="col-md-7">
							<input type="text" id="txtDistrictDlg" name="txtDistrictDlg" class="form-control"/>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-4">
					<div class="form-group">
						<label class="control-label col-md-5"> <spring:message code="dialog.address.criteria.province" /> </label>
						<div class="col-md-7">
							<input type="text" id="txtProvinceDlg" name="txtProvinceDlg" class="form-control" maxlength="50"/>
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="form-group">
						<label class="control-label col-md-5"> <spring:message code="dialog.address.criteria.postalCode" /> </label>
						<div class="col-md-7">
							<input type="text" id="txtPostalCodeDlg" name="txtPostalCodeDlg" class="form-control" maxlength="50"/>
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="form-group">
						<label class="control-label col-md-5"> <spring:message code="dialog.address.criteria.country" /> </label>
						<div class="col-md-7">
							<select name="txtCountryDlg" id="txtCountryDlg" class="form-control select2me">
								<c:forEach items="${CODEBOOK_LIST.COUNTRY }" var="cb">
									<option value="${cb.codeId }">${cb.codeName }</option>
								</c:forEach>
							</select>
						</div>
					</div>
				</div>
			</div>
			
			<div class="form-actions right">
				<button id="btnSearchContact" class="btn blue" type="button" onclick="searchAddressList();">
					<i class="fa fa-search"></i>
					<spring:message code="button.search.label" />
				</button>
				<button id="btnCreateAddress" class="btn green" type="button" data-target="#dlgCreateAddress" data-toggle="modal">
					<i class="fa fa-plus"></i>
					<spring:message code="button.create.label" />
				</button>
			</div>
		</div>
		<div class="col-md-12">
			<table id="cusAddressDialogDataTable" class="table table-bordered dataTable"></table>	
		</div>	
	</div>
</form>


<script type="text/javascript">

	var messageTitle ='<spring:message code="menu.customer" />';
	var msgPleaseEnterWarning = '<spring:message code="message.please.enter.atLeastOne" />'; 
	var evalFuncCont ="${callbackfn}";
	var	oTableAddr;
	var columnAddressDlgs = [];
	var recordPerPage = <%=JLOWebConstant.recordsPerPage%>;

	$(document).ready(function() {
		
		declareColumnAddressDialog();
		
		initajaxDataTable($('#cusAddressDialogDataTable'), columnAddressDlgs);
		
		//searchAddressList();
		
		$('#cusAddressDialogDataTable tbody').on('dblclick touchstart', 'tr', function() {
			var aPos = oTableAddr.fnGetPosition(this);
			var aData = oTableAddr.fnGetData(aPos[0]);
			
			// Check duplicate contact before choose relation
			var addrId = aData[aPos].addrId;
			var custId = $('#custId').val();
			
			var param = "custId=" + custId + "&addrId=" + addrId;
			var dup = getJsonData('checkDuplicateAddress.htm', param, 'POST');
			if (dup == true) {
				alertMessage('<spring:message code="dialog.address.topic"/>' , '<spring:message code="lbl.duplicate" />');
			} else {
				setDataIntoComponent(evalFuncCont, aData[aPos]);
			}
		});
		
		$('#btnCreateAddress').click(function (){
			var param = $('#custId').val();
			loadContentIntoModalWithParameter($("#modal_create_address_content_div"), "addressDialogMain.htm", "createAddressDialog", "dialog.address.detail.topic","", param, "");
			$("#dlgSearchAddress").modal("hide");
		});
		
	});
	
	
	function declareColumnAddressDialog(){
		
		var columnAddressDlg1 = '<spring:message code="dialog.address.result.no" />';
		var columnAddressDlg2 = '<spring:message code="dialog.address.result.type" />';
		var columnAddressDlg3 = '<spring:message code="dialog.address.result.addr1" />';
		var columnAddressDlg4 = '<spring:message code="dialog.address.result.addr2" />';
		var columnAddressDlg5 = '<spring:message code="dialog.address.result.subDistrict" />';
		var columnAddressDlg6 = '<spring:message code="dialog.address.result.district" />';
		var columnAddressDlg7 = '<spring:message code="dialog.address.result.province" />';
		var columnAddressDlg8 = '<spring:message code="dialog.address.result.postalCode" />';

		columnAddressDlgs = [
				{"sTitle" : columnAddressDlg1, "mData" : "addrId" },
				{"sTitle" : columnAddressDlg2, "mData" : "addrTypeName" },
				{"sTitle" : columnAddressDlg3, "mData" : "addr1" },
				{"sTitle" : columnAddressDlg4, "mData" : "addr2" },
				{"sTitle" : columnAddressDlg5, "mData" : "subArea" },
				{"sTitle" : columnAddressDlg6, "mData" : "area" },
				{"sTitle" : columnAddressDlg7, "mData" : "province" },
				{"sTitle" : columnAddressDlg8, "mData" : "postalCode" }];
	}
	
	function searchAddressList() {
		 
		if(validateEnterCriteriaAtLeastOne()){
			
			var addr = $('#txtAddrDlg').val();
			var subDistrict = $('#txtSubDistrictDlg').val();
			var district = $('#txtDistrictDlg').val();
			var province = $('#txtProvinceDlg').val();
			var postalCode = $('#txtPostalCodeDlg').val();
			var country = $('#txtCountryDlg').val();
			
			var param = "addr="+addr;
			param = param + "&subDistrict=" + subDistrict;
			param = param + "&district=" + district;
			param = param + "&province=" + province;
			param = param + "&postalCode=" + postalCode;
			param = param + "&country=" + country;
			
			oTableAddr = ajaxDataTable($('#cusAddressDialogDataTable'), columnAddressDlgs, 'getAddressList.htm', param, recordPerPage, true, true);
			
		}else{
			
			alertMessage("<h5>"+messageTitle+"</h5>",msgPleaseEnterWarning);
			return false;
		}
		
		
	}
	
	function validateEnterCriteriaAtLeastOne(){
		
		var addr = $('#txtAddrDlg').val();
		var subDistrict = $('#txtSubDistrictDlg').val();
		var district = $('#txtDistrictDlg').val();
		var province = $('#txtProvinceDlg').val();
		var postalCode = $('#txtPostalCodeDlg').val();
		var country = $('#txtCountryDlg').val(); 

		if (empty(addr) && empty(subDistrict) && empty(district) && empty(province) && empty(postalCode)){
			return false;
		}else{
			return true;
			
		}
	}
	
</script>