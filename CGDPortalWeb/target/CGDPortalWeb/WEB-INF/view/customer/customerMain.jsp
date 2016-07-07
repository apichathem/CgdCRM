<%@page import="com.locus.jlo.web.constant.ActionType"%>
<%@page import="com.locus.jlo.web.constant.JLOWebConstant"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="locus" uri="/WEB-INF/tld/locus.tld"%>

<!-- BEGIN CONTENT -->
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="caption">
			<spring:message code="menu.customer.management" />
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i><a href="home.htm"> <spring:message code="menu.home" />
			</a><i class="fa fa-angle-right"></i></li>
			<li><a href="#"> <spring:message code="menu.customer" />
			</a><i class="fa fa-angle-right"></i></li>
			<li><a href="#"> <spring:message code="menu.customer.management" />
			</a></li>
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<!-- END PAGE HEADER-->
<!-- BEGIN PAGE CONTENT-->
<div class="row">
	<div class="col-md-12">

		<!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-reorder"></i>
					<spring:message code="cust.custCaptionCriteriaLbl" />
				</div>
				<div class="tools">
					<a class="collapse" href="javascript:;"> </a>
				</div>
			</div>

			<div class="portlet-body form">
				<!-- BEGIN FORM-->
				<form:form id="customerCriteriaMain" class="form-horizontal" modelAttribute="custModelBean">
					<div class="form-body">

						<div class="row">
							<div style="display: none" id="advanceSearchDiv0">
								<div class="col-md-2">
									<div class="form-group">
										<label class="control-label col-md-10"><spring:message code="cust.custSearch.type" /></label>
									</div>
								</div>
								<div class="col-md-8">
									<div class="form-group text-left">
										<div class="radio-list">
											<label class="radio-inline">
												<div class="radio">
													<form:radiobutton path="custType" value="A" />
												</div> <spring:message code="cust.type.all" />
											</label> <label class="radio-inline">
												<div class="radio">
													<form:radiobutton path="custType" value="I" />
												</div> <spring:message code="cust.type.individual" />
											</label> <label class="radio-inline">
												<div class="radio">
													<form:radiobutton path="custType" value="C" />
												</div> <spring:message code="cust.type.corporate" />
											</label>
										</div>
									</div>
								</div>
								<div class="col-md-2">
									<div class="form-group"></div>
								</div>
							
							</div> 
							<!-- END advanceSearchDiv0-->
						</div>
						
						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-5"> 
										<spring:message code="cust.citizenId" />
									</label>
									<div class="col-md-7">
										<form:input id="custCitizenId" path="custCitizenId" class="form-control" maxlength="20" />
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-5"> 
										<spring:message code="cust.taxId" />
									</label>
									<div class="col-md-7">
										<form:input id="custTaxId" path="custTaxId" class="form-control" maxlength="20" />
									</div>
								</div>
							</div>

							<div style="display: none" id="advanceSearchDiv1">
								
								<div class="col-md-4">
									<div class="form-group">
										<label class="control-label col-md-5"> <spring:message code="cust.industry" />
										</label>
										<div class="col-md-7">
											<form:select path="custIndustry" class="form-control select2me">
												<option></option>
												<form:options items="${CODEBOOK_LIST.INDUSTRY_TYPE }" itemValue="codeId" itemLabel="codeName" />
											</form:select>
										</div>
									</div>
								</div>
								
							</div>
							<!-- END advanceSearchDiv1 -->
						</div>
						
						
						<div style="display: none" id="advanceSearchDiv2">
							<div class="row">
								<div class="col-md-4">
									<div class="form-group">

										<label class="control-label col-md-5"> 
											<spring:message code="cust.fullName" />
										
										</label>
										<div class="col-md-7">
											<form:input id="custFullName" path="custFullName" class="form-control" maxlength="200" />
										</div>
									</div>
								</div>

								<div class="col-md-4">
									<div class="form-group">
										<label class="control-label col-md-5"> <spring:message code="cust.registId" />
										</label>
										<div class="col-md-7">
											<form:input id="custRegistId" path="custRegistId" class="form-control" maxlength="20" />
										</div>
									</div>
								</div>
								
								
								<div class="col-md-4">
									<div class="form-group">
										<label class="control-label col-md-5"> <spring:message code="cust.status" />
										</label>
										<div class="col-md-7">
											<form:select path="custStatus" class="form-control select2me">
												<option></option>
												<form:options items="${CODEBOOK_LIST.CUST_STATUS }" itemValue="codeId" itemLabel="codeName" />
											</form:select>
										</div>
									</div>
								</div>
								
							</div>
							<div class="row">
								<div class="col-md-4">
									<div class="form-group">

										<label class="control-label col-md-5"> 
										<spring:message code="cust.firstname" />
										</label>
										<div class="col-md-7">
										<form:input id="custFirstName" path="custFirstName" class="form-control" maxlength="50" />
										</div>
									</div>
								</div>
								
								<div class="col-md-4">
									<div class="form-group">
										<label class="control-label col-md-5"> <spring:message code="cust.work" />
										</label>
										<div class="col-md-7">
											<form:input id="custOffice" path="custOffice" class="form-control" />
										</div>
									</div>
									
								</div>

								<div class="col-md-4">
									<div class="form-group">
										<label class="control-label col-md-5"> <spring:message code="cust.owner" />
										</label>
										<div class="col-md-7">
											<div class="input-group">
												<form:hidden id="custOwnerId" path="custOwnerId" />
												<form:input id="custOwner" path="custOwner" class="form-control" readonly="true" />
												<span class="input-group-addon"> <a id="btnSearchOwner" href="#" data-target="#userDialog" data-toggle="modal"> <i class="fa fa-user"></i>
												</a>
												</span> <span class="input-group-addon"> <a id="btnClearOwner" style="cursor: pointer;"> <i class="fa fa-minus-circle red"></i>
												</a>
												</span>
											</div>
										</div>
									</div>
								</div>
								
							</div>

							<div class="row">
								<div class="col-md-4">
									<div class="form-group">
										<label class="control-label col-md-5"> 
										<spring:message code="cust.lastname" />
										</label>
										<div class="col-md-7">
											<form:input id="custLastName" path="custLastName" class="form-control" maxlength="50" />
										</div>
									</div>
								</div>

								<div class="col-md-4">
									<div class="form-group">
										<label class="control-label col-md-5"> <spring:message code="cust.contact.phone" />
										</label>
										<div class="col-md-7">
											<form:input id="custMobile" path="custMobile" class="form-control" />
										</div>
									</div>
								</div>

								<div class="col-md-4">
									<div class="form-group"></div>
								</div>
							</div>
						</div>
					</div>


					<div class="form-actions right">
						<button id="btnSearch" class="btn blue" type="button" onclick="searchCustomer();">
							<i class="fa fa-search"></i>
							<spring:message code="button.search.label" />
						</button>
						<button id="btnCancel" class="btn default" type="reset" onclick="clearForm();">
							<spring:message code="button.cancel.label" />
						</button>
						<button id="btnAdvanceSearch" class="btn dark" type="button" onclick="showAdvanceSearch();">
							<i class="fa fa-search"></i>
							<spring:message code="button.advanceSearch.label" />
						</button>
						<button id="btnQuickSearch" class="btn dark" type="button" onclick="hideAdvanceSearch();">
							<i class="fa fa-search"></i>
							<spring:message code="button.quickSearch.label" />
						</button>
						
						<locus:privilege oper="ADD">
							<div class="btn-group">
								<button data-toggle="dropdown" class="btn green dropdown-toggle" type="button">
									<i class="fa fa-plus"></i>
									<spring:message code="button.create.label" />
									<i class="fa fa-angle-down"></i>
								</button>
								<ul class="dropdown-menu pull-right">
									<li class="text-left"><a href="initCustomer.htm?type=I"><spring:message code="cust.head.individual" /></a></li>
									<li class="text-left"><a href="initCustomer.htm?type=C"><spring:message code="cust.head.corporate" /></a></li>
								</ul>
							</div>
							<button id="btnCustomerNornal" class="btn green" type="button" onclick="createCustomerNormal();">
								<spring:message code="buton.customers.label" />
							</button>
						</locus:privilege>
						
					</div>
				</form:form>
				<!-- END FORM-->

			</div>
		</div>
	</div>
</div>

<div class="row">
	<div class="col-md-12">
		<c:url var="editUrl" value="/customerDetail.htm" />
		<table id="customerDataTable" class="table table-bordered dataTable"></table>
	</div>
</div>

<!-- owner dialog -->
<div class="modal fade" id="userDialog" role="basic" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div id="modal_content_div"></div>
		</div>
	</div>
</div>
<!-- END PAGE CONTENT-->

<script type="text/javascript">
	var messageTitle ='<spring:message code="menu.customer" />';
	var msgPleaseEnterWarning = '<spring:message code="message.please.enter.atLeastOne" />'; 
	var oTableCust;
	var columnsCustomer = "[]";
	var recordPerPage = parseInt('<spring:message code="datatable.recordPerPage" />');
	
	var moduleFrom = '<c:out value="${custModelBean.module}"/>';
	var phoneNoCic = '<c:out value="${custModelBean.custPhoneCic}"/>';
	
	$(document).ready(function() {
		
		ComponentsPickers.init();
		
		declareColumnCustomerMain();
		
		maskInput();
		$('#btnQuickSearch').hide();
		initajaxDataTable($('#customerDataTable'), columnsCustomer, true, true);
		setRadioValue("custType", "A");

		$("#btnSearchOwner").click(function() {
			loadContentIntoModal($("#modal_content_div"), "openModalDialog.htm", "userDialog", "modal.header.user", "selectedUser");
		});

		$("#btnClearOwner").click(function() {
			$('#custOwnerId').val("");
			$('#custOwner').val("");
		});
		
		/*
		$("#custFullName").onEnter(function() {
			if (!empty($("#custFullName").val())) {
				searchCustomer();
			}
		});
		
		$("#custCitizenId").onEnter(function() {
			if (!empty($("#custCitizenId").val())) {
				searchCustomer();
			}
		});
		
		$("#custTaxId").onEnter(function() {
			if (!empty($("#custTaxId").val())) {
				searchCustomer();
			}
		}); 
		*/
		
		
	 	$("#customerCriteriaMain").keydown(function(event){
		    if(event.keyCode == 13) {
		    	event.preventDefault();
		    	searchCustomer();
		    }
		 });
		
		
		$("#custCitizenId").inputmask("9-9999-99999-99-9");
		
		
		checkInitSearchFromCIC();
		
		
	});
	
	
	function checkInitSearchFromCIC(){
		
		//alert("moduleFrom : "+moduleFrom+"\n phoneNoCic : "+phoneNoCic);
		
		if(!empty(moduleFrom)){
			moduleFrom = moduleFrom.toUpperCase()
			if(moduleFrom == "CIC"){
			
				$("#custMobile").val(phoneNoCic);
				showAdvanceSearch();
				searchCustomer();
				
			}
			
		}
		
	}
	
	function declareColumnCustomerMain(){
		
		var column1 = '<spring:message code="cust.custGrdNo" />';
		var column2 = '<spring:message code="cust.custGrdFullName" />';
		var column3 = '<spring:message code="cust.custGrdCustType" />';
		var column4 = '<spring:message code="cust.custGrdIndustry" />';
		var column5 = '<spring:message code="cust.custGrdOffice" />';
		var column6 = '<spring:message code="cust.custGrdEmail" />';
		var column7 = '<spring:message code="cust.custGrdOwner" />';
		var column8 = '<spring:message code="cust.custGrdStatus" />';

		 columnsCustomer = [ {
			"sTitle" : actionLabel,
			"mData" : null,
			"sWidth" : "3%",
			"sClass" : "text-center",
			"fnRender" : function(objData) {
				var editUrl = objData.aData.editUrl;
				return editUrl;
			}
		}, {
			"sTitle" : column1,
			"mData" : "custId",
			"sClass" : "text-center",
			"sWidth" : "3%"
		}, {
			"sTitle" : column2,
			"mData" : "custName"
		}, {
			"sTitle" : column3,
			"mData" : "custTypeName"
		}, {
			"sTitle" : column4,
			"mData" : "industryName"
		}, {
			"sTitle" : column5,
			"mData" : "workPhoneFormat"
		}, {
			"sTitle" : column6,
			"mData" : "email"
		}, {
			"sTitle" : column7,
			"mData" : "ownerName"
		}, {
			"sTitle" : column8,
			"mData" : "statusName"
		}];
		
	}
	
	function maskInput() {
		$("#custMobile").inputmask("99-9999-9999");
		$("#custOffice").inputmask("99-999-9999");
	}
	
	function createCustomerNormal() {

		var customerId = "1";
		
		var urlCustDetail = "customerDetail.htm?id="+customerId+"&type=I";
		$("#customerCriteriaMain").attr("action", urlCustDetail);
		$("#customerCriteriaMain").attr("method", "post");
		$("#customerCriteriaMain").submit();

	}

	function showAdvanceSearch() {
		$('#advanceSearchDiv0').show();
		$('#advanceSearchDiv1').show();
		$('#advanceSearchDiv2').show();
		$('#btnAdvanceSearch').hide();
		$('#btnQuickSearch').show();
	}

	function hideAdvanceSearch() {
		$('#advanceSearchDiv0').hide();
		$('#advanceSearchDiv1').hide();
		$('#advanceSearchDiv2').hide();
		$('#btnQuickSearch').hide();
		$('#btnAdvanceSearch').show();
	}

	function searchCustomer() {
		
		if(validateEnterCriteriaAtLeastOne()){
			
			var dataString = serialize($("#customerCriteriaMain")[0]);
			oTableCust = ajaxDataTable($('#customerDataTable'), columnsCustomer, 'getCustList.htm', dataString, recordPerPage, true, true);
		}else{
			
			alertMessage("<h5>"+messageTitle+"</h5>",msgPleaseEnterWarning);
			return false;
		}
		
	}
	
	function validateEnterCriteriaAtLeastOne(){
		 
		var custFullName = $("#custFullName").val();
		var custFirstName = $("#custFirstName").val();
		var custLastName =  $("#custLastName").val();
		var custCitizenId = $("#custCitizenId").val();
		var custJobTitle =  $("#custJobTitle").val();
		
		var custIndustry = $("#custIndustry").val();
		var custRegistId = $("#custRegistId").val();
		var custTaxId  = $("#custTaxId").val();
		var custOwner = $("#custOwner").val();
		var custOwnerId = $("#custOwnerId").val();
		var custStatus = $("#custStatus").val();
		
		var custMobile = $("#custMobile").val();
		var custOffice = $("#custOffice").val();
		
		if (empty(custFullName) && empty(custFirstName) && empty(custLastName) 
				&& empty(custCitizenId) && empty(custJobTitle) && empty(custIndustry) 
				&& empty(custRegistId) && empty(custTaxId) && empty(custOwner)
				&& empty(custOwnerId) && empty(custStatus)&& empty(custMobile)&& empty(custOffice)){
			return false;
		}else{
			return true;
			
		}
	}
	
	
	function clearForm() {
		setRadioValue("custType", "A");
		$("#custIndustry").select2("val", "");
		$("#custStatus").select2("val", "");
		$("#custJobTitle").select2("val", "");
	}

	function selectedUser(jsonData) {
		$('#custOwnerId').val(jsonData.userId);
		$('#custOwner').val(jsonData.firstName + ' ' + jsonData.lastName);
		$("#userDialog").modal("hide");
	}
</script>
