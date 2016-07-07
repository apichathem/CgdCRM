<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="locus" uri="/WEB-INF/tld/locus.tld"%>

<!-- BEGIN CONTENT -->	
	<div class="row">
		<div class="col-md-12">
			<!-- BEGIN PAGE TITLE & BREADCRUMB-->
			<h3 class="caption">
				<spring:message code="menu.customer" />
			</h3>
			<ul class="page-breadcrumb breadcrumb">
				<li><i class="fa fa-home"></i> <a href="home.htm"> <spring:message code="menu.home" /> </a> <i
					class="fa fa-angle-right"></i></li>
				<li><a href="#"> <spring:message code="menu.customer" /> </a> <i
					class="fa fa-angle-right"></i></li>
				<li><a href="#"> <spring:message code="menu.customer.detail" /> </a></li>
			</ul>
			<!-- END PAGE TITLE & BREADCRUMB-->
		</div>
	</div>
	
	<!-- Begin Corporate Detail -->
	<div class="row">
		<div class="col-md-12">
			
			<!-- Begin Div Class Portlet box blue -->
			<div class="portlet box blue">
				
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-reorder"></i>
						<spring:message code="cust.head.corporate"/>
					</div>
					<div class="tools">
						<a href="javascript:;" class="collapse"></a>
					</div>
				</div>
				
				<div class="portlet-body form">
					<!-- Begin Form -->
						<form:form name="corporateForm" id="corporateForm" class="form-horizontal" modelAttribute="custModelBean">
							<form:hidden path="mode"/>
							<form:hidden path="type"/>
							<form:hidden path="custId"/>
							<form:hidden path="corpId"/>
							<!-- Begin form-body -->
							<div class="form-body">
								<br/>
								<div class="row">
									<div class="col-md-4">
										<div class="form-group">
											<label class="control-label col-md-5">
												<spring:message code="cust.category"/>
											</label>
											<div class="col-md-7">
												<form:select path="custCategory" class="form-control select2me" disabled="true">
													<option></option>
													<form:options items="${CODEBOOK_LIST.CUST_TYPE }" itemValue="codeId" itemLabel="codeName"/>
												</form:select>
											</div>
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label class="control-label col-md-5">
												<spring:message code="cust.status"/><span class="required">*</span>
											</label>
											<div class="col-md-7">
												<form:select path="custStatusDtl" class="form-control select2me" data-rule-required="true">
													<option></option>
													<form:options items="${CODEBOOK_LIST.CUST_STATUS }" itemValue="codeId" itemLabel="codeName"/>
												</form:select>
											</div>
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label class="control-label col-md-5">
												<spring:message code="cust.email"/>
											</label>
											<div class="col-md-7">
												<form:input id="custEmail" path="custEmail" type="email" class="form-control" maxlength="50"/>
											</div>
										</div>
									</div>
								</div>
								
								<div class="row">
									<div class="col-md-4">
										<div class="form-group">
											<label class="control-label col-md-5">
												<spring:message code="cust.company"/><span class="required">*</span>
											</label>
											<div class="col-md-7">
												<form:input id="custCompany" path="custCompany" class="form-control" data-rule-required="true" data-msg-required="This field is required." maxlength="100"/>
											</div>
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label class="control-label col-md-5">
												<spring:message code="cust.work"/>
											</label>
										<%-- 	<div class="col-md-7">
												<form:input id="custOffice" path="custOffice" class="form-control" />
											</div> --%>
											<div class="col-md-7">
											   	<div class="input-group">
									   				<form:input id="custOffice" path="custOffice" class="form-control" />
									   				<span class="input-group-btn">-</span>
										   			<form:input id="custOfficeExt" path="custOfficeExt" class="form-control maskExtTel" maxlength="4"/>
										   		</div>	
											</div>
											
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label class="control-label col-md-5">
												<spring:message code="cust.owner"/><span class="required">*</span>
											</label>
											<div class="col-md-7">
												<div class="input-group">
													<form:hidden id="custOwnerId" path="custOwnerId"/>
													<form:input id="custOwnerDtl" path="custOwnerDtl" class="form-control" readonly="true" data-rule-required="true"/>
													<span class="input-group-addon">												
														<a id="btnSearchOwner" href="#"  data-target="#userDialog" data-toggle="modal"> 
															<i class="fa fa-user"></i>
														</a>
													</span>
													<span class="input-group-addon"> 
														<a id="btnClearOwner" style="cursor: pointer;"> 
															<i class="fa fa-minus-circle red"></i>
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
												<spring:message code="cust.industry"/>
											</label>
											<div class="col-md-7">
												<form:select path="custIndustryDtl" class="form-control select2me">
													<option></option>
													<form:options items="${CODEBOOK_LIST.INDUSTRY_TYPE }" itemValue="codeId" itemLabel="codeName"/>
												</form:select>
											</div>
										</div> 
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label class="control-label col-md-5">
												<spring:message code="cust.fax"/>
											</label>
											<div class="col-md-7">
												<form:input id="custFax" path="custFax" class="form-control" />
											</div>
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label class="control-label col-md-5">
												<spring:message code="cust.url"/>
											</label>
											<div class="col-md-7">
												<form:input id="custUrl" path="custUrl" class="form-control" maxlength="50"/>
											</div>
										</div>
									</div>
								</div>
								
								<div class="row">
									<div class="col-md-4">
									</div>
									<div class="col-md-4">
									</div>
									<div class="col-md-4">
									</div>
								</div>
									
							
								<!-- row7 -->
								<div class="row">
									<div class="col-md-8">
										<hr/>
									</div>
									<div class="col-md-4">
									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
										<br>
									</div>
								</div>
								<!-- row8 -->
								<div class="row">
									<div class="col-md-4">
										<div class="row">
											<div class="col-md-12">
												<div class="form-group">
													<label class="control-label col-md-5">
														<spring:message code="cust.registId"/>
													</label>
													<div class="col-md-7">
														<form:input id="custRegistIdDtl" path="custRegistIdDtl" class="form-control" maxlength="20"/>
													</div>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-md-12">
												<div class="form-group">
													<label class="control-label col-md-5">
														<spring:message code="cust.taxId"/><span class="required">*</span>
													</label>
													<div class="col-md-7">
														<form:input id="custTaxIdDtl" path="custTaxIdDtl" class="form-control" maxlength="20" data-rule-required="true" data-msg-required="This field is required." />
													</div>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-md-12">
												<div class="form-group">
													<label class="control-label col-md-5">
														<spring:message code="cust.annual.revenue"/>
													</label>
													<div class="col-md-7">
														<form:select path="custAnnualRevenue" class="form-control select2me">
															<option></option>
															<form:options items="${CODEBOOK_LIST.CORPORATION_REVENUE }" itemValue="codeId" itemLabel="codeName"/>
														</form:select>
													</div> 
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-md-12">
												<div class="form-group">
													<label class="control-label col-md-5">
														<spring:message code="cust.noOfEmployee"/>
													</label>
													<div class="col-md-7">
														<form:select path="custNoOfEmp" class="form-control select2me">
															<option></option>
															<form:options items="${CODEBOOK_LIST.COMPANY_SIZE }" itemValue="codeId" itemLabel="codeName"/>
														</form:select>
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label class="control-label col-md-5">
												<spring:message code="cust.remark"/>
											</label>
											<div class="col-md-7">
												<form:textarea id="custRemark" path="custRemark" class="form-control" rows="5" maxlength="500"/>
											</div>
										</div>
									</div>
									<div class="col-md-4">
										<br/>
										<div class="form-group">
											<label class="control-label col-md-5">
												
											</label>
											<div class="col-md-7">
												<form:checkbox id="custNotCall" path="custNotCall"/><spring:message code="cust.donotcall"/><br/>
												<form:checkbox id="custNotSms" path="custNotSms"/><spring:message code="cust.donotsms"/><br/>
												<form:checkbox id="custNotEmail" path="custNotEmail"/><spring:message code="cust.donotemail"/><br/>
												<form:checkbox id="custNotMail" path="custNotMail"/><spring:message code="cust.donotmail"/>
											</div>
										</div>
									</div>
								</div>
							</div>
							<!-- End form-body -->
							
							<!-- Begin Panel Button -->	
							<div class="form-actions right">
								<button type="button" class="btn blue" id="btnSave" name="btnSave" onclick="saveCorporate();">
									<i class="fa fa-floppy-o"></i>											
									<spring:message code="button.save.label"/>
								</button>
							
								<button type="reset" class="btn default" id="btnCancel" name="btnCancel" onclick="clearCustomerForm();">
									<spring:message code="button.cancel.label"/>
								</button>
								
								<button type="button" class="btn default" id="btnBack" name="btnBack" onclick="back();">
									<i class="fa fa-mail-reply"></i>
									<spring:message code="button.back.label" />
								</button>
							</div>	
							<!-- End Panel Button -->	
							
						</form:form>
					<!-- End Form -->
				</div>
				
			</div>
			<!-- End Div Class Portlet box blue -->
		</div>
	</div>
	<!-- End Corporate Detail -->
	
	<!-- Begin Detail -->
	<div class="portlet box blue form-horizontal">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-reorder"></i>
						<spring:message code="cust.custCaptionDetailLbl" />
					</div>
					<div class="tools">
						<a href="javascript:;" class="collapse">
						</a>
					</div>
				</div>
	
				
				<div id="custDetailTab" class="portlet-body">
					<ul class="nav nav-tabs">
						
						<li id="liContactTab">
							<a href="#contactTab" data-toggle="tab">
								 <spring:message code="cust.contactTab"/>
							</a>
						</li>
						<li id="liAddressTab">									
							<a href="#addressTab" data-toggle="tab">
								<spring:message code="cust.addressTab"/>
							</a>
						 </li>
						<li id="liServiceRequestTab">									
							<a href="#serviceRequestTab" data-toggle="tab">
								<spring:message code="cust.serviceRequestTab"/>
							</a>
						</li>
					</ul>
					<div class="tab-content">
						
						<div class="tab-pane fade" id="contactTab">
						</div>
						
						<div class="tab-pane fade" id="addressTab">
						</div>
						
						<div class="tab-pane fade" id="serviceRequestTab">
						</div>
						
					</div> <!-- End tab-content -->
					
			</div>  <!-- End Portlet-body-->	
	</div>
	<!-- End Detail -->

<!-- owner dialog -->	
<div class="modal fade" id="userDialog" role="basic" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div id="modal_content_div"></div>
		</div>
	</div>
</div>
<div class="modal fade" id="corpAddressDialog" role="basic" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div id="address_content_div"></div>
		</div>
	</div>
</div>		
	
<script type="text/javascript">

var txtValidatesCorpDetail = ["custCompany","custStatusDtl"];
var custModule ='<c:out value="${custModelBean.module}"/>';

var tabIndex = 0;
var liTabId;
var	tabId;
var customerTabMain;

var custNotCallFlg;
var custNotSmsFlg;
var custNotEmailFlg;
var custNotMailFlg;

$(document).ready(function() {     
	
	ComponentsPickers.init();
	maskInput();
	
	
	customerDetailTab(); 
	
	checkActiveTabByModule(custModule);
	
	/*
	initialTabOnLoad();
	customerDetailTab(); 
	*/
	
	custNotCallFlg = $('#custNotCall').prop('checked');
	custNotSmsFlg = $('#custNotSms').prop('checked');
	custNotEmailFlg = $('#custNotEmail').prop('checked');
	custNotMailFlg = $('#custNotMail').prop('checked');
	
	$("#btnSearchOwner").click(function() {		 
		loadContentIntoModal($("#modal_content_div"), "openModalDialog.htm","userDialog","modal.header.user", "selectedCorporateUser");
	});
	
	$("#btnClearOwner").click(function() {		 
		$("#custOwnerId").val("");
		$("#custOwnerDtl").val("");
	});
	
	$("#btnSearchAddr1").click(function() {		 
		loadContentIntoModal($("#address_content_div"), "openModalDialog.htm","addressDialog","dialog.address.topic", "selectedAddress");
	});
});

function maskInput(){
	
	$("#custOffice").inputmask("99-999-9999");
	$("#custFax").inputmask("99-999-9999");
	$("#custTaxIdDtl").inputmask("99999999999999999999");
}



function checkActiveTabByModule(module){
	
	if(!empty(module)){
		
		if(custModule === "sr"){
			
			tabIndex = 2;
			liTabId = "liServiceRequestTab";
			tabId = "serviceRequestTab";
			
		}/* else if(custModule === "cp"){
			
			tabIndex = 3;
			liTabId = "liComplaintTab";
			tabId = "complaintTab";
			
		}else if(custModule === "inc"){
			
			tabIndex = 4;
			liTabId = "liIncidentTab";
			tabId = "incidentTab";
			
		}else if(custModule === "asset"){
			
			tabIndex = 5;
			liTabId = "liAssetTab";
			tabId = "assetTab";
			
		} */else{
			
			tabIndex = 0;
			liTabId = "liContactTab";
			tabId = "contactTab";
		}
		
		customerDetailTabActiveByModule(liTabId,tabId,tabIndex);
	
	}else{
		
		tabIndex = 0;
		liTabId = "liContactTab";
		tabId = "contactTab";
		
		if(empty(customerTabMain)){
			customerTabMain = $("#custDetailTab").tabs();
		}
		
		setClassActiveTab(customerTabMain,liTabId,tabId,tabIndex);
		initialTabOnLoad();
	}
	
}

function customerDetailTabActiveByModule(liTabId,tabId,tabIndex){
	
	console.log(liTabId+","+tabId+","+tabIndex);
	if(empty(customerTabMain)){
		customerTabMain = $("#custDetailTab").tabs();
	}
	setClassActiveTab(customerTabMain,liTabId,tabId,tabIndex);
	
}

function initialTabOnLoad(){
	$("#contactTab").load("customerDetailTab.htm?tab=contact", function( response, status, xhr){
		if(xhr.status == 200){
			
		}
	});
	
}

function customerDetailTab(){
	
	customerTabMain = $("#custDetailTab").tabs({
		
		activate: function (event, ui) {
			
			var $activeTab = $("#custDetailTab").tabs("option", "active");
			var mode = '<c:out value="${custModelBean.mode}"/>';
			var custId = '<c:out value="${custModelBean.custId}"/>';
			
			if($activeTab == 0) { // tab contact
				jLoBlockUI();
				$("#contactTab").load("customerDetailTab.htm?tab=contact", function( response, status, xhr){
					if(xhr.status == 200){
						
					}
				});
			}else if($activeTab == 1){ // tab address
				jLoBlockUI();
				$("#addressTab").load("customerDetailTab.htm?tab=address", function( response, status, xhr){
					if(xhr.status == 200){
						
					}
				});
			} else if ($activeTab == 2) { // tab service request
				jLoBlockUI();
				$("#serviceRequestTab").load("customerDetailTab.htm?tab=serviceRequest&mode="+mode, function( response, status, xhr){
					if(xhr.status == 200){
						
					}
				});
			}
			/* else if ($activeTab == 3) { // tab complaint
				jLoBlockUI();
				$("#complaintTab").load("customerDetailTab.htm?tab=complaint&mode="+mode, function( response, status, xhr){
					if(xhr.status == 200){
						
					}
				});
			} else if ($activeTab == 4) { // tab incident
				jLoBlockUI();
				$("#incidentTab").load("customerDetailTab.htm?tab=incident&mode="+mode, function( response, status, xhr){
					if(xhr.status == 200){
						
					}
				});
			} else if ($activeTab == 5) { // tab asset
				jLoBlockUI();
				$("#assetTab").load("customerDetailTab.htm?tab=asset&mode="+mode, function( response, status, xhr){
					if(xhr.status == 200){
						
					}
				});
			} */ 
		}
	});
}

function back(){
	redirectUrlAction("customerMain.htm");
	/* $('#corporateForm').attr('action', 'customerMain.htm');
	$('#corporateForm').submit(); */
}

function clearCustomerForm(){
	
	clearValidate(txtValidatesCorpDetail);
	
	var mode = '<c:out value="${custModelBean.mode}"/>';
	if (mode == "insert"){
		
		$("#custOwnerId").val("");
		
		// set dropdown >> default value
		$("#custIndustryDtl").select2("val","");
		$("#custStatusDtl").select2("val","");
		$("#custContactMethod").select2("val","");
		$("#custCountry").select2("val","");
		$("#custAnnualRevenue").select2("val", "");
		$("#custNoOfEmp").select2("val", "");
		
		// set checkbox >> unchecked
		setCheckbox("custNotCall", false);
		setCheckbox("custNotSms", false);
		setCheckbox("custNotEmail", false);
		setCheckbox("custNotMail", false);
		
	} else if (mode == "update"){
		
		var custOwnerId = '<c:out value="${custModelBean.custOwnerId}"/>';
		$("#custOwnerId").val(custOwnerId);
		
		// set dropdown >> old value
		var custIndustryDtl = '<c:out value="${custModelBean.custIndustryDtl}"/>';
		$("#custIndustryDtl").select2("val", custIndustryDtl);
		
		var custStatusDtl = '<c:out value="${custModelBean.custStatusDtl}"/>';
		$("#custStatusDtl").select2("val", custStatusDtl);
		
		var custContactMethod = '<c:out value="${custModelBean.custContactMethod}"/>';
		$("#custContactMethod").select2("val", custContactMethod);
		
		var custCountry = '<c:out value="${custModelBean.custCountry}"/>';
		$("#custCountry").select2("val", custCountry);
		
		var custAnnualRevenue = '<c:out value="${custModelBean.custAnnualRevenue}"/>';
		$("#custAnnualRevenue").select2("val", custAnnualRevenue);
		
		var custNoOfEmp = '<c:out value="${custModelBean.custNoOfEmp}"/>';
		$("#custNoOfEmp").select2("val", custNoOfEmp);
		
		// set checkbox >> old value
		setCheckbox("custNotCall", custNotCallFlg);
		setCheckbox("custNotSms", custNotSmsFlg);
		setCheckbox("custNotEmail", custNotEmailFlg);
		setCheckbox("custNotMail", custNotMailFlg);
	}
}

function saveCorporate() {
	var $valid = validateForm($("#corporateForm"));
	if(!$valid){
		return false;
	}
	$("#corporateForm").attr('action', 'saveCorporation.htm');
	$("#corporateForm").submit();
}

function selectedCorporateUser(jsonData){
	$('#custOwnerId').val(jsonData.userId);
	$('#custOwnerDtl').val(jsonData.firstName + ' ' + jsonData.lastName);
	$("#userDialog").modal("hide");
}

function selectedAddress(jsonData) {
	console.log(JSON.stringify(jsonData));
	$('#custAddressLine1').val(jsonData.addr1);
	$('#custAddressLine2').val(jsonData.addr2);
	$('#custProvince').val(jsonData.province);
	$('#custArea').val(jsonData.area);
	$('#custSubArea').val(jsonData.subArea);
	$('#custPostal').val(jsonData.postalCode);
	
	$("#corpAddressDialog").modal("hide");
}
</script>
	