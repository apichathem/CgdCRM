<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>


<spring:message code="message.please.enter" var="msgPleaseCustEnter"/>
<spring:message code="cust.status" var="msgCustStatus"/>

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
							<a href="javascript:;" class="collapse">
							</a>
						</div>
						
					</div>
					
				<div class="portlet-body form">
					<!-- Begin Form -->
					<form:form name="individualForm" id="individualForm" class="form-horizontal" modelAttribute="custModelBean">
						<form:hidden path="mode"/>
						<form:hidden path="type"/>
						<form:hidden path="custId"/>
						<form:hidden path="indId"/>
						<div class="form-body">
							<br>
							<!-- Row1 -->	
								<div class="row">
									<div class="col-md-4">
										<div class="row">
											<div class="col-md-12">
												<div class="form-group">
													<label class="control-label col-md-5">
														<spring:message code="cust.custType"/>
													</label>
													<div class="col-md-7">
														<form:select path="custCategory" class="form-control select2me">
															<option></option>
															<form:options items="${CODEBOOK_LIST.CUST_TYPE }" itemValue="codeId" itemLabel="codeName"/>
														</form:select>		
													</div>
												</div>
											</div>
											<div class="col-md-12">
												<div class="form-group">
													<label class="control-label col-md-5">
														<spring:message code="cust.code"/>
													</label>
													<div class="col-md-7">
														<form:input id="custCode" path="custCode" class="form-control" data-rule-maxlength="20"/>
													</div>
												</div>
											</div>
											<div class="col-md-12">
												<div class="form-group">
													<label class="control-label col-md-5">
														<spring:message code="cust.title"/>
													</label>
													<div class="col-md-7">
														<form:select path="custTitle" class="form-control select2me">
															<option></option>
															<form:options items="${CODEBOOK_LIST.TITLE_NAME }" itemValue="codeId" itemLabel="codeName"/>
														</form:select>
													</div>
												</div>
											</div>
											<div class="col-md-12">
												<div class="form-group">
													<label class="control-label col-md-5">
														<spring:message code="cust.firstname"/><span class="required">*</span>
													</label>
													<div class="col-md-7">
														<form:input id="custFirstNameDtl" path="custFirstNameDtl" class="form-control" data-rule-maxlength="50" data-rule-required="true" data-msg-required="This field is required."/>
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
													<label class="control-label col-md-5">
														<spring:message code="cust.homePhone"/>
													</label>
													<div class="col-md-7">
														<form:input id="custHomePhone" path="custHomePhone" class="form-control" />
													</div>
												</div>
											</div>
											<div class="col-md-12">
												<div class="form-group">
													<label class="control-label col-md-5">
														<spring:message code="cust.mobile"/><span class="required">*</span>
													</label>
													<div class="col-md-7">
														<form:input id="custMobile" path="custMobile" class="form-control" data-rule-required="true" data-msg-required="This field is required."/>
													</div>
												</div>
											</div>
											<div class="col-md-12">
												<div class="form-group">
													<label class="control-label col-md-5">
														<spring:message code="cust.work"/>
													</label>
													<div class="col-md-7">
														<form:input id="custOffice" path="custOffice" class="form-control" />
													</div>
												</div>
											</div>
											<div class="col-md-12">
												<div class="form-group">
													<label class="control-label col-md-5">
														<spring:message code="cust.fax"/>
													</label>
													<div class="col-md-7">
														<form:input id="custFax" path="custFax" class="form-control" />
													</div>
												</div>
											</div>
										</div>
										
									</div>
									<!--/span Remark-->
									<div class="col-md-4">
										<div class="form-group">
											<label class="control-label col-md-5">
												<spring:message code="cust.remark"/>
											</label>
											<div class="col-md-7">
												<form:textarea id="custRemark" path="custRemark" class="form-control" rows="5" data-rule-maxlength="500"/>
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
													<label class="control-label col-md-5">
														<spring:message code="cust.lastname"/>
													</label>
													<div class="col-md-7">
														<form:input id="custLastNameDtl" path="custLastNameDtl" class="form-control" data-rule-maxlength="50"/>
													</div>
												</div>
											</div>
											<div class="col-md-12">
												<div class="form-group">
													<label class="control-label col-md-5">
														<spring:message code="cust.middlename"/>
													</label>
													<div class="col-md-7">
														<form:input id="custMiddleName" path="custMiddleName" class="form-control" data-rule-maxlength="50"/>
													</div>
												</div>
											</div>
											<div class="col-md-12">
												<div class="form-group">
													<label class="control-label col-md-5">
														<spring:message code="cust.status"/>
														<span class="required">*</span>
													</label>
													<div class="col-md-7">
														<form:select path="custStatusDtl" class="form-control select2me" data-rule-required="true" 
																	data-msg-required="This field is required.">
															<option></option>
															<form:options items="${CODEBOOK_LIST.CUST_STATUS }" itemValue="codeId" itemLabel="codeName"/>
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
														<spring:message code="cust.email"/>
													</label>
													<div class="col-md-7">
														<form:input id="custEmail" path="custEmail" type="email" class="form-control" data-rule-maxlength="50"/>
													</div>
												</div>
											</div>
											<div class="col-md-12">
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
											<div class="col-md-12">
												<div class="form-group">
													<label class="control-label col-md-5">
														<spring:message code="cust.contactMethod"/>
													</label>
													<div class="col-md-7">
														<form:select path="custContactMethod" class="form-control select2me">
															<option></option>
															<form:options items="${CODEBOOK_LIST.CONTACT_CHANNEL }" itemValue="codeId" itemLabel="codeName"/>
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
								
							    <!-- Begin Customize -->
								
								
								
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
														<form:input id="custMinistry" path="custLastNameDtl" class="form-control" data-rule-maxlength="50"/>
													</div>
												</div>
											</div>
											<div class="col-md-12">
												<div class="form-group">
													<label class="control-label col-md-5">
														<spring:message code="cust.custDepartment"/>
													</label>
													<div class="col-md-7">
														<form:input id="custMiddleName" path="custMiddleName" class="form-control" data-rule-maxlength="50"/>
													</div>
												</div>
											</div>
											<div class="col-md-12">
												<div class="form-group">
													<label class="control-label col-md-5">
														<spring:message code="cust.custDivisionDepartment"/>
													</label>
													<div class="col-md-7">
														<form:input id="custMiddleName" path="custMiddleName" class="form-control" data-rule-maxlength="50"/>
													</div>
												</div>
											</div>
											
											<div class="col-md-12">
												<div class="form-group">
													<label class="control-label col-md-5">
														<spring:message code="cust.custPosition"/>
													</label>
													<div class="col-md-7">
														<form:input id="custMiddleName" path="custMiddleName" class="form-control" data-rule-maxlength="50"/>
													</div>
												</div>
											</div>
											
											
											<div class="col-md-12">
												<div class="form-group">
													<label class="control-label col-md-5">
														<spring:message code="cust.custProvince"/>
													</label>
													<div class="col-md-7">
														<select path="custContactMethod" class="form-control select2me">
															<option> กรุงเทพมหานคร</option>
																	<option> กระบี่</option>
																	<option> กาญจนบุรี</option>
																	<option> กาฬสินธุ์</option>
																	<option> กำแพงเพชร</option>
																	<option> ขอนแก่น</option>
																	<option> จันทบุรี</option>
																	<option> ฉะเชิงเทรา</option>
																	<option> ชลบุรี</option>
																	<option> ชัยนาท</option>
																	<option> ชัยภูมิ</option>
																	<option> ชุมพร</option>
																	<option> เชียงราย</option>
																	<option> เชียงใหม่</option>
																	<option> ตรัง</option>
																	<option> ตราด</option>
																	<option> ตาก</option>
																	<option> นครนายก</option>
																	<option> นครปฐม</option>
																	<option> นครพนม</option>
																	<option> นครราชสีมา</option>
																	<option> นครศรีธรรมราช</option>
																	<option> นครสวรรค์</option>
																	<option> นนทบุรี</option>
																	<option> นราธิวาส</option>
																	<option> น่าน</option>
																	<option> บึงกาฬ</option>
																	<option> บุรีรัมย์</option>
																	<option> ปทุมธานี</option>
																	<option> ประจวบคีรีขันธ์ </option>
															<option></option>
														</select>
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
<%-- 														<form:input id="custEmail" path="custEmail" type="email" class="form-control" data-rule-maxlength="50"/> --%>
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
															<%-- <form:hidden id="custOwnerId" path="custOwnerId"/>
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
															</span> --%>
														</div>
													</div>
												</div>
											</div>
											<div class="col-md-12">
												<div class="form-group">
													<label class="control-label col-md-5">
<%-- 														<spring:message code="cust.contactMethod"/> --%>
													</label>
													<div class="col-md-7">
<%-- 														<form:select path="custContactMethod" class="form-control select2me"> --%>
<!-- 															<option></option> -->
<%-- 															<form:options items="${CODEBOOK_LIST.CONTACT_CHANNEL }" itemValue="codeId" itemLabel="codeName"/> --%>
<%-- 														</form:select> --%>
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
								<button type="button" class="btn blue" id="btnSave" name="btnSave" onclick="saveIndividual();">
									<i class="fa fa-floppy-o"></i>											
									<spring:message code="button.save.label"/>
								</button>
							
								<button type="reset" class="btn default" id="btnCancel" name="btnCancel">
									<spring:message code="button.cancel.label"/>
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
			
			</div> <!-- End Div Class Portlet box blue -->	
					
		</div>
	
	</div><!--  End Begin Individual Detail  -->
	
	 
	
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
<!-- 						<li class="active"> -->
						<li id="liMoreInfoTab">
							<a href="#moreInfoTab" data-toggle="tab">
								 <spring:message code="cust.moreInfoTab"/>
							</a>
						</li>
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
						
						<%-- <li id="liComplaintTab">									
							<a href="#complaintTab" data-toggle="tab">
								<spring:message code="cust.complaintTab"/>
							</a>
						</li>
						<li id="liIncidentTab">									
							<a href="#incidentTab" data-toggle="tab">
								<spring:message code="cust.incidentTab"/>
							</a>
						</li>
						<li id="liAssetTab">									
							<a href="#assetTab" data-toggle="tab">
								<spring:message code="cust.assetTab"/>
							</a>
						</li> --%>
							
					</ul>
					<div class="tab-content">
						
<!-- 						<div class="tab-pane fade active in" id="moreInfoTab"> -->
<!-- 						</div> -->
						<div class="tab-pane fade" id="moreInfoTab">
						</div>	
						
						<div class="tab-pane fade" id="contactTab">
						</div>
						
						<div class="tab-pane fade" id="addressTab">
						</div>
						
						<div class="tab-pane fade" id="serviceRequestTab">
						</div>
						
						<!-- <div class="tab-pane fade" id="complaintTab">
						</div>
						
						<div class="tab-pane fade" id="incidentTab">
						</div>
						
						<div class="tab-pane fade" id="assetTab">
						</div> -->
						
					</div> <!-- End tab-content -->
					
			</div>  <!-- End Portlet-body-->		
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

var txtValidatesCustDetail = ["custFirstNameDtl","custMobile","custStatusDtl"];
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
	
	custNotCallFlg = $('#custNotCall').prop('checked');
	custNotSmsFlg = $('#custNotSms').prop('checked');
	custNotEmailFlg = $('#custNotEmail').prop('checked');
	custNotMailFlg = $('#custNotMail').prop('checked');
	
/* 
initialTabOnLoad();
customerDetailTab(); 
*/
	
	$("#btnSearchOwner").click(function() {		 
		loadContentIntoModal($("#modal_content_div"), "openModalDialog.htm","userDialog","modal.header.user", 'selectedIndividualUser');
	});
	
	$("#btnClearOwner").click(function() {		 
		$("#custOwnerId").val("");
		$("#custOwnerDtl").val("");
	});
	
	$("#btnSearchAddr1").click(function() {		 
		loadContentIntoModal($("#address_content_div"), "openModalDialog.htm","addressDialog","dialog.address.topic", "selectedAddress");
	});
	
	$("#btnCancel").click(function() {
		clearValidate(txtValidatesCustDetail);
		clearCustomerForm();
	});
	
});


function maskInput(){
	$("#custHomePhone").inputmask("99-999-9999");
	$("#custMobile").inputmask("99-9999-9999");
	$("#custOffice").inputmask("99-999-9999");
	$("#custFax").inputmask("99-999-9999");
}

function checkActiveTabByModule(module){
	
	if(!empty(module)){
		
		if(custModule === "sr"){
			
			tabIndex = 3;
			liTabId = "liServiceRequestTab";
			tabId = "serviceRequestTab";
			
		}
		/* else if(custModule === "cp"){
			
			tabIndex = 4;
			liTabId = "liComplaintTab";
			tabId = "complaintTab";
			
		}else if(custModule === "inc"){
			
			tabIndex = 5;
			liTabId = "liIncidentTab";
			tabId = "incidentTab";
			
		}else if(custModule === "asset"){
			
			tabIndex = 6;
			liTabId = "liAssetTab";
			tabId = "assetTab";
			
		} */else{
			
			tabIndex = 0;
			liTabId = "liMoreInfoTab";
			tabId = "moreInfoTab";
			
		}
		
		customerDetailTabActiveByModule(liTabId,tabId,tabIndex);
		
	}else{
		
		tabIndex = 0;
		liTabId = "liMoreInfoTab";
		tabId = "moreInfoTab";
	
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
	var mode = '<c:out value="${custModelBean.mode}"/>';
	var custId = '<c:out value="${custModelBean.custId}"/>';

	$("#moreInfoTab").load("customerDetailTab.htm?tab=moreInfo&mode="+mode+"&id="+custId, function( response, status, xhr ){
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
			
			if($activeTab == 0) { // tab moreInfo
				jLoBlockUI();
				$("#moreInfoTab").load("customerDetailTab.htm?tab=moreInfo&mode="+mode+"&id="+custId, function( response, status, xhr ){
					if(xhr.status == 200){
						
					}
				});
			}else if($activeTab == 1){ // tab contact
				jLoBlockUI();
				$("#contactTab").load("customerDetailTab.htm?tab=contact&mode="+mode, function( response, status, xhr){
					if(xhr.status == 200){
						
					}
				});
			}else if($activeTab == 2){ // tab address
				jLoBlockUI();
				$("#addressTab").load("customerDetailTab.htm?tab=address&mode="+mode, function( response, status, xhr){
					if(xhr.status == 200){
						
					}
				});
			} else if ($activeTab == 3) { // tab service request
				//jLoBlockUI();
				$("#serviceRequestTab").load("customerDetailTab.htm?tab=serviceRequest&mode="+mode, function( response, status, xhr){
					if(xhr.status == 200){
						
					}
				});
			}/*  else if ($activeTab == 4) { // tab complaint
				jLoBlockUI();
				$("#complaintTab").load("customerDetailTab.htm?tab=complaint&mode="+mode, function( response, status, xhr){
					if(xhr.status == 200){
						
					}
				});
			} else if ($activeTab == 5) { // tab incident
				jLoBlockUI();
				$("#incidentTab").load("customerDetailTab.htm?tab=incident&mode="+mode, function( response, status, xhr){
					if(xhr.status == 200){
						
					}
				});
			} else if ($activeTab == 6) { // tab asset
				jLoBlockUI();
				$("#assetTab").load("customerDetailTab.htm?tab=asset&mode="+mode, function( response, status, xhr){
					if(xhr.status == 200){
						
					}
				});
			}  */
		}
	});
}

function back() {
	redirectUrlAction("customerMain.htm");
/* 	$('#individualForm').attr('action', 'customerMain.htm');
	$('#individualForm').submit(); */
}

function saveIndividual(){
	var $valid = validateForm($("#individualForm"));
	if(!$valid){
		return false;
	}

	$("#individualForm").attr('action', 'saveIndividual.htm');
	$("#individualForm").submit();
}

function clearCustomerForm(){
	var mode = '<c:out value="${custModelBean.mode}"/>';
	if(mode == "insert"){
		
		$("#custOwnerId").val("");
		
		// set dropdown >> default value
		$("#custTitle").select2("val","");
		$("#custStatusDtl").select2("val","");
		$("#custContactMethod").select2("val","");
		$("#custCountry").select2("val","");
		
		// set checkbox >> unchecked
		$('#custNotCall').removeAttr('checked');
		setCheckbox("custNotCall", false);
		setCheckbox("custNotSms", false);
		setCheckbox("custNotEmail", false);
		setCheckbox("custNotMail", false);
		
	}else if(mode == "update"){
		
		var custOwnerId = '<c:out value="${custModelBean.custOwnerId}"/>';
		$("#custOwnerId").val(custOwnerId);
		
		// set dropdown >> old value
		var custTitle = '<c:out value="${custModelBean.custTitle}"/>';
		$("#custTitle").select2("val", custTitle);
		
		var custStatusDtl = '<c:out value="${custModelBean.custStatusDtl}"/>';
		$("#custStatusDtl").select2("val", custStatusDtl);

		var custContactMethod = '<c:out value="${custModelBean.custContactMethod}"/>';
		$("#custContactMethod").select2("val", custContactMethod);
		
		var custCountry = '<c:out value="${custModelBean.custCountry}"/>';
		$("#custCountry").select2("val", custCountry);
		
		// set checkbox >> old value
		setCheckbox("custNotCall", custNotCallFlg);
		setCheckbox("custNotSms", custNotSmsFlg);
		setCheckbox("custNotEmail", custNotEmailFlg);
		setCheckbox("custNotMail", custNotMailFlg);
		
	}
}

function selectedIndividualUser(jsonData){
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