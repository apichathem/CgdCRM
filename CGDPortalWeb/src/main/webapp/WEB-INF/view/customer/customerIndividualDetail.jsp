<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="locus" uri="/WEB-INF/tld/locus.tld"%>

<spring:message code="message.please.enter" var="msgPleaseCustEnter" />
<spring:message code="cust.status" var="msgCustStatus" />

<!-- BEGIN CONTENT -->
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="caption">
			<spring:message code="menu.customer" />
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="home.htm"> <spring:message code="menu.home" />
			</a> <i class="fa fa-angle-right"></i></li>
			<li><a href="#"> <spring:message code="menu.customer" />
			</a> <i class="fa fa-angle-right"></i></li>
			<li><a href="#"> <spring:message code="menu.customer.detail" />
			</a></li>
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>

<!-- Begin Individual Detail  -->
<div class="row">
	<div class="col-md-12">

		<!-- Begin Div Class Portlet box blue -->

		<div class="portlet box blue">

			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-reorder"></i>
					<spring:message code="cust.head.individual" />
				</div>
				<div class="tools">
					<a href="javascript:;" class="collapse"> </a>
				</div>

			</div>

			<div class="portlet-body form">
				<!-- Begin Form -->
				<form:form name="individualForm" id="individualForm" class="form-horizontal" modelAttribute="custModelBean">
					<form:hidden path="mode" />
					<form:hidden path="type" />
					<form:hidden path="custId" />
					<form:hidden path="indId" />
					<div class="form-body">
						<br>
						<!-- Row1 -->
						<div class="row">
							<div class="col-md-4">
								<div class="row">
									<div class="col-md-12">
										<div class="form-group">
											<label class="control-label col-md-5"> <spring:message code="cust.category" />
											</label>
											<div class="col-md-7">
												<form:select id="custCategoryId" path="custCategory" class="form-control select2me" disabled="true">
													<option></option>
													<form:options items="${CODEBOOK_LIST.CUST_TYPE }" itemValue="codeId" itemLabel="codeName" />
												</form:select>
											</div>
										</div>
									</div>
									<div class="col-md-12">
									
										<div class="form-group">
											<label class="control-label col-md-5"> <spring:message code="cust.title" />
											</label>
											<div class="col-md-7">
												<form:select path="custTitle" class="form-control select2me">
													<option></option>
													<form:options items="${CODEBOOK_LIST.TITLE_NAME }" itemValue="codeId" itemLabel="codeName" />
												</form:select>
											</div>
										</div>
									</div>
									<div class="col-md-12">
										<div class="form-group">
											<label class="control-label col-md-5"> <spring:message code="cust.firstname" /><span class="required">*</span>
											</label>
											<div class="col-md-7">
												<form:input id="custFirstNameDtl" path="custFirstNameDtl" class="form-control" data-rule-maxlength="50" data-rule-required="true" data-msg-required="This field is required." />
											</div>
										</div>
									</div>
									<div class="col-md-12">
										<div class="form-group">
											<label class="control-label col-md-5"> <spring:message code="cust.lastname" /><span class="required">*</span>
											</label>
											<div class="col-md-7">
												<form:input id="custLastNameDtl" path="custLastNameDtl" class="form-control" data-rule-maxlength="50" data-rule-required="true" data-msg-required="This field is required." />
											</div>
										</div>
									</div>
								</div>

							</div>
							<!--/span-->
							<div class="col-md-4">
								<div class="row">
									<div class="col-md-12">
										<div class="form-group">
											<label class="control-label col-md-5"> <spring:message code="cust.homePhone" />
											</label>
										   <div class="col-md-7">
											   	<div class="input-group">
									   				<form:input id="custHomePhone" path="custHomePhone" class="form-control" />
									   				<span class="input-group-btn">-</span>
										   			<form:input id="custHomePhoneExt" path="custHomePhoneExt" class="form-control maskExtTel" maxlength="20"/>
										   		</div>	
											</div> 
										</div>
									</div>
									<div class="col-md-12">
										<div class="form-group">
											<label class="control-label col-md-5"> <spring:message code="cust.mobile" /><span class="required">*</span>
											</label>
											<div class="col-md-7">
												<div class="input-group">
									   				<form:input id="custMobile" path="custMobile" class="form-control" data-rule-required="true" data-msg-required="This field is required." />
									   				<span class="input-group-btn">-</span>
										   			<form:input id="custMobileExt" path="custMobileExt" class="form-control maskExtTel" maxlength="20"/>
										   		</div>	
												
											</div>
										</div>
									</div>
									<div class="col-md-12">
										<div class="form-group">
											<label class="control-label col-md-5"> <spring:message code="cust.work" />
											</label>
											<div class="col-md-7">
												 <div class="input-group">
									   				<form:input id="custOffice" path="custOffice" class="form-control"/>
									   				<span class="input-group-btn">-</span>
										   			<form:input id="custOfficeExt" path="custOfficeExt" class="form-control maskExtTel" maxlength="20"/>
										   		</div>	
											</div>
										</div>
									</div>
									<div class="col-md-12">
										<div class="form-group">
											<label class="control-label col-md-5"> <spring:message code="cust.fax" />
											</label>
											<div class="col-md-7">
												<form:input id="custFax" path="custFax" class="form-control" />
											</div>
										</div>
									</div>
								</div>

							</div>
							<!--/span-->
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-5"> <spring:message code="cust.remark" />
									</label>
									<div class="col-md-7">
										<form:textarea id="custRemark" path="custRemark" class="form-control" rows="5" data-rule-maxlength="500" />
									</div>
								</div>
							</div>

						</div>

						<!-- Row2 -->
						<!-- Row3 -->
						<!-- Row4 -->
						<!-- Row5 -->
						<div class="row">
							<div class="col-md-4">
								<div class="row">
									<div class="col-md-12">
										<div class="form-group">
											<label class="control-label col-md-5"> <spring:message code="cust.middlename" />
											</label>
											<div class="col-md-7">
												<form:input id="custMiddleName" path="custMiddleName" class="form-control" data-rule-maxlength="50" />
											</div>
										</div>
									</div>
									<div class="col-md-12">
										<div class="form-group">
											<label class="control-label col-md-5">
													<spring:message code="cust.moreinfo.citizenId" /><span class="required">*</span>
											</label>
										<div class="col-md-7">
											<form:input id="custCitizenId" path="custCitizenId" class="form-control" data-rule-maxlength="20" data-rule-required="true" data-msg-required="This field is required." />
										</div>
									</div>
									</div>
									<div class="col-md-12">
										<div class="form-group">
											<label class="control-label col-md-5"> <spring:message code="cust.status" /> <span class="required">*</span>
											</label>
											<div class="col-md-7">
												<form:select path="custStatusDtl" class="form-control select2me" data-rule-required="true" data-msg-required="This field is required.">
													<option></option>
													<form:options items="${CODEBOOK_LIST.CUST_STATUS }" itemValue="codeId" itemLabel="codeName" />
												</form:select>
											</div>
										</div>
									</div>
									
									<div class="col-md-12"></div>
								</div>
							</div>
							<!--/span-->
							<div class="col-md-4">
								<div class="row">
									<div class="col-md-12">
										<div class="form-group">
											<label class="control-label col-md-5"> <spring:message code="cust.email" />
											</label>
											<div class="col-md-7">
												<form:input id="custEmail" path="custEmail" class="form-control" data-rule-maxlength="50" data-rule-email="true" />
											</div>
										</div>
									</div>
									<div class="col-md-12">
										<div class="form-group">
											<label class="control-label col-md-5"> <spring:message code="cust.owner" /><span class="required">*</span>
											</label>
											<div class="col-md-7">
												<div class="input-group">
													<form:hidden id="custOwnerId" path="custOwnerId" />
													<form:input id="custOwnerDtl" path="custOwnerDtl" class="form-control" readonly="true" data-rule-required="true" />
													<span class="input-group-addon"> <a id="btnSearchOwner" href="#" data-target="#userDialog" data-toggle="modal"> <i class="fa fa-user"></i>
													</a>
													</span> <span class="input-group-addon"> <a id="btnClearOwner" style="cursor: pointer;"> <i class="fa fa-minus-circle red"></i>
													</a>
													</span>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-12">
										<div class="form-group">
											 
										</div>
									</div>
									<div class="col-md-12"></div>
								</div>

							</div>
							<!--/span-->
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-5"> </label>
									<div class="col-md-7">
										<form:checkbox id="custNotCall" path="custNotCall" />
										<spring:message code="cust.donotcall" />
										<br />
										<form:checkbox id="custNotSms" path="custNotSms" />
										<spring:message code="cust.donotsms" />
										<br />
										<form:checkbox id="custNotEmail" path="custNotEmail" />
										<spring:message code="cust.donotemail" />
										<br />
										<form:checkbox id="custNotMail" path="custNotMail" />
										<spring:message code="cust.donotmail" />
									</div>
								</div>
							</div>
						</div>

						<!-- Row 6 -->
						<h5 class="form-section"></h5>
								<!-- Row 6 -->
								<div class="row">
									<div class="col-md-4">
										<div class="row">
											<div class="col-md-12">
												<div class="form-group">
													<label class="control-label col-md-5">
														<spring:message code="cust.custMinistry"/>
													</label>
													<div class="col-md-7">
														<form:input id="custMinistry" path="custMinistry" class="form-control" data-rule-maxlength="100"/>
													</div>
												</div>
											</div>
											<div class="col-md-12">
												<div class="form-group">
													<label class="control-label col-md-5">
														<spring:message code="cust.custDepartment"/>
													</label>
													<div class="col-md-7">
														<form:input id="custDepartment" path="custDepartment" class="form-control" data-rule-maxlength="100"/>
													</div>
												</div>
											</div>
											<div class="col-md-12">
												<div class="form-group">
													<label class="control-label col-md-5">
														<spring:message code="cust.custDivisionDepartment"/>
													</label>
													<div class="col-md-7">
														<form:input id="custDivisionDept" path="custDivisionDept" class="form-control" data-rule-maxlength="100"/>
													</div>
												</div>
											</div>
											
											<div class="col-md-12">
												<div class="form-group">
													<label class="control-label col-md-5">
														<spring:message code="cust.custPosition"/>
													</label>
													<div class="col-md-7">
														<form:input id="custPosition" path="custPosition" class="form-control" data-rule-maxlength="100"/>
													</div>
												</div>
											</div>
											
											
											<div class="col-md-12">
												<div class="form-group">
													<label class="control-label col-md-5">
														<spring:message code="cust.custProvince"/>
													</label>
													<div class="col-md-7">
														<form:select path="custProvinceId" id="cmb_CustProvince" class="form-control select2me">
														</form:select>
													</div>
												</div>
											</div>
											
											<div class="col-md-12">
											</div>
											
										
										</div>
									</div>
									<!--/span-->
									<div class="col-md-4">
										<div class="row">
											<div class="col-md-12">
												<div class="form-group">
													<label class="control-label col-md-5">
<%-- 														<spring:message code="cust.email"/> --%>
													</label>
													<div class="col-md-7">
													</div>
												</div>
											</div>
											<div class="col-md-12">
												<div class="form-group">
													<label class="control-label col-md-5">
<%-- 														<spring:message code="cust.owner"/><span class="required">*</span> --%>
													</label>
													<div class="col-md-7">
														<div class="input-group">
														</div>
													</div>
												</div>
											</div>
											<div class="col-md-12">
												<div class="form-group">
													<label class="control-label col-md-5">
													</label>
													<div class="col-md-7">
													</div>
												</div>
											</div>
											<div class="col-md-12">
											</div>
										</div>
										
									</div>
									<!--/span-->
									<div class="col-md-4">
										<div class="form-group">
											<label class="control-label col-md-5">
												
											</label>
											<div class="col-md-7">
												
											</div>
										</div>
									</div>	
								</div>
						<!-- Row 7 -->
					</div>
					<!-- End form-body -->

					<!-- Begin Panel Button -->
					<div class="form-actions right">
						
					<locus:privilege oper="EDIT">
						<button type="button" class="btn blue" id="btnSave" name="btnSave">
							<i class="fa fa-floppy-o"></i>
							<spring:message code="button.save.label" />
						</button>
					</locus:privilege>
					
						<button type="reset" class="btn default" id="btnCancel" name="btnCancel">
							<spring:message code="button.cancel.label" />
						</button>

						<button type="button" class="btn default" id="btnBack" name="btnBack" onclick="back();">
							<i class="fa fa-mail-reply"></i>
							<spring:message code="button.back.label" />
						</button>
					</div>

					<!-- End Panel Button -->


					<!-- End Form -->
				</form:form>
			</div>

		</div>
		<!-- End Div Class Portlet box blue -->

	</div>

</div>
<!--  End Begin Individual Detail  -->



<div class="portlet box blue form-horizontal">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-reorder"></i>
			<spring:message code="cust.custCaptionDetailLbl" />
		</div>
		<div class="tools">
			<a href="javascript:;" class="collapse"> </a>
		</div>
	</div>


	<div id="custDetailTab" class="portlet-body">
		<ul class="nav nav-tabs">
			<!-- <li class="active"> -->
			<li id="liMoreInfoTab"><a href="#moreInfoTab" data-toggle="tab"> <spring:message code="cust.moreInfoTab" />
			</a></li>
			<li id="liContactTab"><a href="#contactTab" data-toggle="tab"> <spring:message code="cust.contactTab" />
			</a></li>
			<li id="liAddressTab"><a href="#addressTab" data-toggle="tab"> <spring:message code="cust.addressTab" />
			</a></li>
			<li id="liServiceRequestTab"><a href="#serviceRequestTab" data-toggle="tab"> <spring:message code="cust.serviceRequestTab" />
			</a></li>
		</ul>
		<div class="tab-content">
			<div class="tab-pane fade" id="moreInfoTab"></div>
			<div class="tab-pane fade" id="contactTab"></div>
			<div class="tab-pane fade" id="addressTab"></div>
			<div class="tab-pane fade" id="serviceRequestTab"></div>
		</div>
		<!-- End tab-content -->

	</div>
	<!-- End Portlet-body-->
</div>

<!-- owner dialog -->
<div class="modal fade" id="userDialog" role="basic" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div id="modal_content_div"></div>
		</div>
	</div>
</div>
<!-- Address dialog -->
<div class="modal fade" id="indAddressDialog" role="basic" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div id="address_content_div"></div>
		</div>
	</div>
</div>
<script type="text/javascript">

	var title = '<spring:message code="cust.custCaptionDetailLbl" />';
	var worningCitizen = '<spring:message code="message.please.enter.citizen"/>';
	var txtValidatesCustDetail = [ "custFirstNameDtl", "custMobile", "custStatusDtl" ];
	var custModule = '<c:out value="${custModelBean.module}"/>';
	var custProvinceCdVal = '<c:out value="${custModelBean.custProvinceId}"/>';
	
	var tabIndex = 0;
	var liTabId;
	var tabId;
	var customerTabMain;

	var custNotCallFlg;
	var custNotSmsFlg;
	var custNotEmailFlg;
	var custNotMailFlg;

	$(document).ready(function() {

		ComponentsPickers.init();
		maskInput();
		$("#custCitizenId").inputmask("9-9999-99999-99-9");
		
		customerDetailTab();

		checkActiveTabByModule(custModule);

		custNotCallFlg = $('#custNotCall').prop('checked');
		custNotSmsFlg = $('#custNotSms').prop('checked');
		custNotEmailFlg = $('#custNotEmail').prop('checked');
		custNotMailFlg = $('#custNotMail').prop('checked');
		
		getProvinceListCombo(custProvinceCdVal, "");
		
		validateForm($("#individualForm"));
		
		$("#btnSave").click(function(e) {
			
			e.preventDefault();
			saveIndividual();
		});

		$("#btnSearchOwner").click(function(e) {
			e.preventDefault();
			loadContentIntoModal($("#modal_content_div"), "openModalDialog.htm", "userDialog", "modal.header.user", 'selectedIndividualUser');
		});

		$("#btnClearOwner").click(function(e) {
			e.preventDefault();
			$("#custOwnerId").val("");
			$("#custOwnerDtl").val("");
		});

		$("#btnSearchAddr1").click(function(e) {
			e.preventDefault();
			loadContentIntoModal($("#address_content_div"), "openModalDialog.htm", "addressDialog", "dialog.address.topic", "selectedAddress");
		});

		$("#btnCancel").click(function(e) {
			e.preventDefault();
			clearValidate(txtValidatesCustDetail);
			clearCustomerForm();
		});

	});
	
	function getProvinceListCombo(provinceCdVal,caption){
		getProvinceListDefaultVal($("#cmb_CustProvince"),provinceCdVal,caption);
		 
	}
	
	
	function maskInput() {
		$("#custHomePhone").inputmask("99-999-9999");
		$("#custMobile").inputmask("99-9999-9999");
		$("#custOffice").inputmask("99-999-9999");
		$("#custFax").inputmask("99-999-9999");
	}

	function checkActiveTabByModule(module) {
		
		if (!empty(module)) {

			if (custModule === "sr") {

				tabIndex = 3;
				liTabId = "liServiceRequestTab";
				tabId = "serviceRequestTab";

			} else {

				tabIndex = 0;
				liTabId = "liMoreInfoTab";
				tabId = "moreInfoTab";

			}

			customerDetailTabActiveByModule(liTabId, tabId, tabIndex);

		} else {

			tabIndex = 0;
			liTabId = "liMoreInfoTab";
			tabId = "moreInfoTab";

			if (empty(customerTabMain)) {
				customerTabMain = $("#custDetailTab").tabs();
			}

			setClassActiveTab(customerTabMain, liTabId, tabId, tabIndex);
			initialTabOnLoad();
		}

	}

	function customerDetailTabActiveByModule(liTabId, tabId, tabIndex) {

		console.log(liTabId + "," + tabId + "," + tabIndex);
		if (empty(customerTabMain)) {
			customerTabMain = $("#custDetailTab").tabs();
		}
		setClassActiveTab(customerTabMain, liTabId, tabId, tabIndex);

	}

	function initialTabOnLoad() {
		var mode = '<c:out value="${custModelBean.mode}"/>';
		var custId = '<c:out value="${custModelBean.custId}"/>';

		$("#moreInfoTab").load("customerDetailTab.htm?tab=moreInfo&mode=" + mode + "&id=" + custId, function(response, status, xhr) {
			if (xhr.status == 200) {

			}
		});

	}

	function customerDetailTab() {

		customerTabMain = $("#custDetailTab").tabs({

			activate : function(event, ui) {

				var $activeTab = $("#custDetailTab").tabs("option", "active");
				var mode = '<c:out value="${custModelBean.mode}"/>';
				var custId = '<c:out value="${custModelBean.custId}"/>';

				if ($activeTab == 0) { // tab moreInfo
					jLoBlockUI();
					$("#moreInfoTab").load("customerDetailTab.htm?tab=moreInfo&mode=" + mode + "&id=" + custId, function(response, status, xhr) {
						if (xhr.status == 200) {

						}
					});
				} else if ($activeTab == 1) { // tab contact
					jLoBlockUI();
					$("#contactTab").load("customerDetailTab.htm?tab=contact&mode=" + mode, function(response, status, xhr) {
						if (xhr.status == 200) {

						}
					});
				} else if ($activeTab == 2) { // tab address
					jLoBlockUI();
					$("#addressTab").load("customerDetailTab.htm?tab=address&mode=" + mode, function(response, status, xhr) {
						if (xhr.status == 200) {

						}
					});
				} else if ($activeTab == 3) { // tab service request
					jLoBlockUI();
					$("#serviceRequestTab").load("customerDetailTab.htm?tab=serviceRequest&mode=" + mode, function(response, status, xhr) {
						if (xhr.status == 200) {

						}
					});
				} 
			}
		});
	}

	function back() {
		redirectUrlAction("customerMain.htm");
		/* 	$('#individualForm').attr('action', 'customerMain.htm');
		 $('#individualForm').submit(); */
	}

	function saveIndividual() {
		
		var $valid = validateForm($("#individualForm"));
		if (!$valid) {
			return false;
		}
		
		var custCitizenId = $("#custCitizenId").val();	
		if(!empty(custCitizenId)){
			
			if(checkCitizenId(custCitizenId)){
				$("#individualForm").attr('action', 'saveIndividual.htm');
				$("#individualForm").submit(); 
				
			}else{
				alertMessage( title,worningCitizen );
				$("#custCitizenId").focus();
				return false			
			 
			}	
		}else{
			
			$("#individualForm").attr('action', 'saveIndividual.htm');
			$("#individualForm").submit();
			
		}	
		
		
		
		
	}

	function clearCustomerForm() {
		var mode = '<c:out value="${custModelBean.mode}"/>';
		var custCategoryId = '<c:out value="${custModelBean.custCategory}"/>';
		var custStatusDtl = '<c:out value="${custModelBean.custStatusDtl}"/>';
		
		if (mode == "insert") {

			$("#custOwnerId").val("");

			// set dropdown >> default value
			$("#custTitle").select2("val", "");
			$("#custStatusDtl").select2("val", "");
			$("#custContactMethod").select2("val", "");
			$("#custCountry").select2("val", "");

			// set checkbox >> unchecked
			$('#custNotCall').removeAttr('checked');
			setCheckbox("custNotCall", false);
			setCheckbox("custNotSms", false);
			setCheckbox("custNotEmail", false);
			setCheckbox("custNotMail", false);
			
			$('#individualForm').clearForm();			
			//$('#individualForm')[0].reset();	
			
			$("#custStatusDtl").select2("val", custStatusDtl);
			$("#custCategoryId").select2("val",custCategoryId);
			
			
		} else if (mode == "update") {

			var custOwnerId = '<c:out value="${custModelBean.custOwnerId}"/>';
			$("#custOwnerId").val(custOwnerId);

			// set dropdown >> old value
			var custTitle = '<c:out value="${custModelBean.custTitle}"/>';
			$("#custTitle").select2("val", custTitle);

			$("#custStatusDtl").select2("val", custStatusDtl);

			var custContactMethod = '<c:out value="${custModelBean.custContactMethod}"/>';
			$("#custContactMethod").select2("val", custContactMethod);

			var custCountry = '<c:out value="${custModelBean.custCountry}"/>';
			$("#custCountry").select2("val", custCountry);
			
			var custProvinceId = '<c:out value="${custModelBean.custProvinceId}"/>';
			$("#cmb_CustProvince").select2("val", custProvinceId);

			// set checkbox >> old value
			setCheckbox("custNotCall", custNotCallFlg);
			setCheckbox("custNotSms", custNotSmsFlg);
			setCheckbox("custNotEmail", custNotEmailFlg);
			setCheckbox("custNotMail", custNotMailFlg);
		
			 $('#individualForm')[0].reset();	
		}
	}

	function selectedIndividualUser(jsonData) {
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

		$("#indAddressDialog").modal("hide");
	}
</script>