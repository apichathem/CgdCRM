<%@page import="org.springframework.http.HttpRequest"%>

<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="locus" uri="/WEB-INF/tld/locus.tld"%>

<%@page import="com.locus.jlo.web.bean.dto.ConsultingDTO"%>
<spring:message code="message.please.enter" var="msgPleaseEnter" />
<spring:message code="sr.srSubject" var="msgSrSubject" />
<spring:message code="sr.srCustomer" var="msgSrCust" />
<spring:message code="sr.srChannel" var="msgSrChannel" />
<spring:message code="sr.srTypeProblem1" var="msgSrTypeProblem1" />
<spring:message code="sr.srTypeProblem2" var="msgSrTypeProblem2" />
<spring:message code="sr.srTypeProblem3" var="msgSrTypeProblem3" />
<spring:message code="sr.srTypeProblem4" var="msgSrTypeProblem4" />
<spring:message code="sr.srTypeProblem5" var="msgSrTypeProblem5" />
<spring:message code="sr.srStatus" var="msgSrStatus" />
<spring:message code="sr.srPriority" var="msgSrPriority" />
<spring:message code="sr.srOwner" var="msgSrOwner" />


<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="caption">
			<spring:message code="sr.srCaptionLbl" />
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li class="btn-group">
				<ul role="menu" class="dropdown-menu pull-right">
					<li><a href="#"> Action </a></li>
					<li><a href="#"> Another action </a></li>
					<li><a href="#"> Something else here </a></li>
					<li class="divider"></li>
					<li><a href="#"> Separated link </a></li>
				</ul>
			</li>
			<li><i class="fa fa-home"></i> <a href="home.htm"> <spring:message code="menu.home" />
			</a> <i class="fa fa-angle-right"></i></li>
			<li><a href="serviceRequestList.htm"> <spring:message code="menu.serviceRequest" />
			</a><i class="fa fa-angle-right"></i></li>
			<li><a href="#"> <spring:message code="menu.serviceRequest.detail" />
			</a></li>
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>

<form:form action="updateServiceRequest.htm" name="srForm" id="srForm" class="form-horizontal" modelAttribute="srModelBean">
	<form:hidden path="mode" id="srMode" />
	<!--
	<input type="hidden" id="custId" name="id"/>
	<input type="hidden" id="custType" name="type"/>
	-->
	<!-- Begin Service Request Detail  -->
	<div class="row">
		<div class="col-md-12">

			<!-- Begin  -->
			<div class="portlet box blue">

				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-reorder"></i>
						<spring:message code="sr.srDetailCaption" />
					</div>
					<div class="tools">
						<a href="javascript:;" class="collapse"> </a>
					</div>
				</div>

				<div class="portlet-body form">
					<div class="form-body">

						<!-- Row1 -->
						<div class="row">

							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-5"> <spring:message code="sr.srNumber" />
									</label>
									<div class="col-md-7">
										<form:input type="text" id="txt_sr_number" name="srNumber" path="srNumber" class="form-control" readonly="true" />
										<form:input type='hidden' id="txt_sr_id" name="srNumber" path="srId" class="form-control" />
									</div>
								</div>
							</div>
							<!--/span-->
							<div class="col-md-4">

								<div class="form-group">
									<label class="control-label col-md-5"> <spring:message code="sr.srConsultingNum" />
									</label>
									<div class="col-md-7">
										<form:input type='text' id="txtSRConsultingNumber" path="srConsultingNumber" class="form-control" readonly="true" />
									</div>
								</div>
							</div>
							<!--/span-->
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-4"> <spring:message code="sr.srOwner" /><span class="required">*</span>
									</label>
									<div class="col-md-8">
										<div class="input-group">
											<form:input type="text" id="txt_sr_owner_name" path="srOwnerName" class="form-control" readonly="true" data-rule-required="true" data-msg-required='${msgPleaseActEnter}&nbsp;${msgSrOwner}.' />
											<form:input type='hidden' id="txt_sr_owner_id" path="srOwnerId" class="form-control" />
											<span class="input-group-btn">
												<button type="button" id="btnSearchOwner" class="btn" data-target="#userOwnerDialog" data-toggle="modal">
													<i class="fa fa-user" id="select_owner"></i>
												</button>
											</span> <span class="input-group-btn">
												<button type="button" id="btnClearOwner" class="btn">
													<i class="fa fa-minus-circle red"></i>
												</button>
											</span>

										</div>

									</div>
								</div>
							</div>

						</div>

						<!-- Row2 -->
						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-5"> <spring:message code="sr.srSubject" /> <span class="required"> * </span>
									</label>
									<div class="col-md-7">
										<%-- 												 <form:input type="text" id="txt_sr_subject" path="srSubject" maxlength="50" class="form-control" data-rule-required="true" data-msg-required='${msgPleaseEnter}&nbsp;${msgSrSubject}.'/> --%>
										<form:textarea id='txt_sr_subject' path="srSubject" class="form-control" rows="1" maxlength="1000" style="height: 22px;" data-rule-required="true"
											data-msg-required='${msgPleaseEnter}&nbsp;${msgSrSubject}.' />
									</div>
								</div>
							</div>
							<!--/span-->
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-5"> <spring:message code="sr.srCustomer" /><span class="required"> * </span>
									</label>
									<div class="col-md-7">
										<div class="input-group">
											<form:input type="text" id="txt_sr_customer_name" path="srCustomerName" class="form-control" readonly="true" data-rule-required="true"
												data-msg-required='${msgPleaseEnter}&nbsp;${msgSrCust}.' />
											<form:input type='hidden' id="txt_sr_customer_id" path="srCustId" class="form-control" />
											<form:input type='hidden' id="txt_sr_customer_type" path="srCustType" class="form-control" />

											<span class="input-group-btn">
												<button type="button" id="btnSearchCustomer" class="btn" data-target="#srCustomerDialog" data-toggle="modal">
													<i class="fa fa-user" id="select_customer"></i>
												</button>
											</span> <span class="input-group-btn">
												<button type="button" id="btnClearCustomer" class="btn">
													<i class="fa fa-minus-circle red"></i>
												</button>
											</span>
										</div>
									</div>
								</div>
							</div>
							<!--/span-->
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-4"> <spring:message code="sr.srOpenedDate" />
									</label>
									<div class="col-md-8">
										<div class="input-group">
											<form:input type="text" id="txt_sr_open_date" path="srOpenedDate" class="form-control date-picker maskdate" data-date-format="dd-mm-yyyy" disabled="true" />
											<span class="input-group-btn">
												<button class="btn default" type="button" disabled="disabled">
													<i class="fa fa-calendar"></i>
												</button>
											</span>
											<form:input type="text" id="txt_sr_open_time" path="srOpenedTime" class="form-control masktime" disabled="true" />
										</div>
									</div>
								</div>
							</div>
						</div>
						
						
						<!-- Row 3 new  -->
						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-5"> <spring:message code="sr.srTypeProblem1" /><span class="required"> * </span>
									</label>
									<div class="col-md-7">
										<form:select id="cmbSrTypeProblem1" path="srTypeProblem1Cd" class="form-control select2me" data-rule-required="true" data-msg-required="${msgPleaseEnter}&nbsp;${msgSrTypeProblem1}." />
									</div>
								</div>
							</div>
							<!--/span-->
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-5"> <spring:message code="sr.srContact" />
									</label>
									<div class="col-md-7">
										<form:select id="cmb_sr_cont_list" path="srContactId" class="form-control select2me">
										</form:select>
									</div>
								</div>
							</div>
							<!--/span-->
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-4"> <spring:message code="sr.srClosedDate" />
									</label>
									<div class="col-md-8">
										<div class="input-group">
											<form:input type="text" id="txt_sr_closed_date" path="srCloseDate" class="form-control date-picker maskdate" data-date-format="dd-mm-yyyy" disabled="true" />
											<span class="input-group-btn">
												<button class="btn default" type="button" disabled="disabled">
													<i class="fa fa-calendar"></i>
												</button>
											</span>
											<form:input type="text" id="txt_sr_closed_time" path="srCloseTime" class="form-control masktime" disabled="true" />
										</div>
									</div>
								</div>
							</div>
						</div>
						
						
						
						<!-- Row3 -->
						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-5"> <spring:message code="sr.srTypeProblem2" /><span class="required"> * </span>
									</label>
									<div class="col-md-7">
										<form:select id="cmbSrTypeProblem2" path="srTypeProblem2Cd" class="form-control select2me" data-rule-required="true" data-msg-required="${msgPleaseEnter}&nbsp;${msgSrTypeProblem2}." />
									</div>
								</div>
							</div>
							<!--/span-->
							<div class="col-md-4">

								<div class="form-group">
									<label class="control-label col-md-5"> <spring:message code="sr.srChannel" /> <span class="required"> * </span>
									</label>
									<div class="col-md-7">
										<form:select id="cmb_sr_channel" path="srChannelCd" class="form-control select2me" data-rule-required="true" data-msg-required="${msgPleaseEnter}&nbsp;${msgSrChannel}.">
											<option></option>
											<form:options items='${CODEBOOK_LIST.CONSULT_CHANNEL}' itemLabel="codeName" itemValue="codeId" />
										</form:select>
									</div>
								</div>
							</div>
							<!--/span-->
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-4"> <spring:message code="sr.srDueDate" />
									</label>
									<div class="col-md-8">
										<div class="input-group">
											<form:input type="text" id="txt_sr_due_date" path="srDueDate" class="form-control date-picker maskdate" data-date-format="dd-mm-yyyy" data-date-start-date="+0d" disabled="true" />
											<span class="input-group-btn">
												<button class="btn default" type="button" disabled="disabled">
													<i class="fa fa-calendar"></i>
												</button>
											</span>
											<form:input type="text" name="txt_sr_duedate_time" path="srDueTime" class="form-control masktime" disabled="true" />
										</div>
									</div>
								</div>
							</div>
						</div>

						<!-- Row4 -->
						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-5"> <spring:message code="sr.srTypeProblem3" /><span class="required"> * </span>
									</label>
									<div class="col-md-7">
										<form:select id="cmbSrTypeProblem3" path="srTypeProblem3Cd" class="form-control select2me" data-rule-required="true" data-msg-required='${msgPleaseEnter}&nbsp;${msgSrTypeProblem3}.' />
									</div>
								</div>
							</div>
							<!--/span-->
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-5"> <spring:message code="sr.srPriority" /><span class="required"> * </span>
									</label>
									<div class="col-md-7">
										<!--  TODO  SRQ_PRIORITY -->
										<form:select id="cmb_sr_priority" path="srPriorityCd" class="form-control select2me" data-rule-required="true" data-msg-required='${msgPleaseEnter}&nbsp;${msgSrPriority}.'>
										</form:select>

									</div>
								</div>

							</div>
							<!--/span-->
							<div class="col-md-4">
								 &nbsp;
							</div>
						</div>

						<!-- Row5 To Row 7 -->
						<div class="row">
							<div class="col-md-4">
								<!-- Row5. Col 1 -->
								<div class="row">
									<div class="col-md-12">
										<div class="form-group">
											<label class="control-label col-md-5"> <spring:message code="sr.srTypeProblem4" /><span class="required"> * </span>
											</label>
											<div class="col-md-7">
												<form:select id="cmbSrTypeProblem4" path="srTypeProblem4Cd" class="form-control select2me" data-rule-required="true" data-msg-required='${msgPleaseEnter}&nbsp;${msgSrTypeProblem4}.' />
											</div>
										</div>
									</div>
								</div>
								<!--Row6. Col 1 -->
								<div class="row">
									<div class="col-md-12">
										<div class="form-group">
											<label class="control-label col-md-5"> <spring:message code="sr.srTypeProblem5" /><span class="required"> * </span>
											</label>
											<div class="col-md-7">
												<form:select id="cmbSrTypeProblem5" path="srTypeProblem5Cd" class="form-control select2me" data-rule-required="true" data-msg-required='${msgPleaseEnter}&nbsp;${msgSrTypeProblem5}.' />
											</div>
										</div>
									</div>
								</div>
								<!-- Row7 Col 1 -->
								<%-- <div class="row">
									<div class="col-md-12">
										<div class="form-group">
											<label class="control-label col-md-5"> <spring:message code="sr.srTypeProblem5" /><span class="required"> * </span>
											</label>
											<div class="col-md-7">
												<form:select id="cmbSrTypeProblem5" path="srTypeProblem5Cd" class="form-control select2me" data-rule-required="true" data-msg-required='${msgPleaseEnter}&nbsp;${msgSrTypeProblem5}.' />
											</div>
										</div>
									</div>

								</div> --%>

							</div>

							<!-- Row5 Col 2 -->

							<div class="col-md-4">

								<div class="form-group">
									<label class="control-label col-md-5"> <spring:message code="sr.srStatus" /><span class="required"> * </span>
									</label>
									<div class="col-md-7">
										<form:select id="cmb_sr_status" path="srStatusCd" class="form-control select2me" data-rule-required="true" data-msg-required='${msgPleaseEnter}&nbsp;${msgSrStatus}.'>
											<option></option>
											<form:options items='${CODEBOOK_LIST.SRQ_STATUS}' itemLabel="codeName" itemValue="codeId" />
										</form:select>

									</div>
								</div>
							</div>
							<div class="col-md-4">&nbsp;</div>
							<div class="col-md-4">&nbsp;</div>
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-5"> <spring:message code="sr.srDesc" />
									</label>
									<div class="col-md-7">
										<form:textarea id='txt_sr_desc' path="srDescription" class="form-control" rows="2" maxlength="1000" />
									</div>
								</div>
							</div>

							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-4"> <spring:message code="sr.srNoteDesc" />
									</label>
									<div class="col-md-8">
										<form:textarea id='txt_sr_note_desc' path="srNoteDescription" class="form-control" rows="2" maxlength="1000" />
									</div>
								</div>
							</div>
							<!--  Row 6 Colum 2 -->
							<!--  Row 5 Colum 3 -->
							<!--  New Component -->
							<div class="col-md-4">
								&nbsp;
							</div>

						</div>
						<!--  End Row4 -->

						<div class="row">
							<div class="col-md-4"></div>
						</div>
						<hr>
						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-5"> <spring:message code="lbl.createBy" />
									</label>
									<div class="col-md-7">
										<p id="srCreateBy" class="form-control-static">
											<c:out value="${srModelBean.createByName}" />
										</p>
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-5"> <spring:message code="lbl.createDate" />
									</label>
									<div class="col-md-7">
										<p id="srCreateDate" class="form-control-static">
											<c:out value="${srModelBean.createDateTime}" />
										</p>
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group"></div>
							</div>
						</div>

						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-5"> <spring:message code="lbl.updateBy" />
									</label>
									<div class="col-md-7">
										<p id="srUpdateBy" class="form-control-static">
											<c:out value="${srModelBean.updateByName}" />
										</p>
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-5"> <spring:message code="lbl.updateDate" />
									</label>
									<div class="col-md-7">
										<p id="srUpdateDate" class="form-control-static">
											<c:out value="${srModelBean.updateDateTime}" />
										</p>
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group"></div>
							</div>
						</div>
						<!-- End Detail  -->
					</div>

					<!-- Begin Panel Button -->
					<div class="form-actions right">
						<locus:privilege oper="ADD">
							<div class="btn-group">
								<button type="button" class="btn green" id="btn_srNew" name="btn_srNew">
									<i class="fa fa-plus"></i>
									<spring:message code="button.create.label" />
								</button>
							</div>
						</locus:privilege>


						<!-- Change here for save button privilege between new or edit -->
						<div class="btn-group">
							<span id="insertDiv" style="display: none;"> <locus:privilege oper="ADD">
									<button type="button" class="btn blue" id="btn_srInsert" name="btn_srInsert">
										<i class="fa fa-floppy-o"></i>
										<spring:message code="button.save.label" />
									</button>
								</locus:privilege>
							</span> <span id="updateDiv" style="display: none;"> <locus:privilege oper="EDIT" ownerId="${srModelBean.srOwnerId}">
									<button type="button" class="btn blue" id="btn_srUpdate" name="btn_srUpdate">
										<i class="fa fa-floppy-o"></i>
										<spring:message code="button.save.label" />
									</button>
								</locus:privilege> <locus:privilege oper="EDIT" ownerId="${srModelBean.srOwnerId}">
									<button type="button" class="btn blue" id="btn_srUpdateNoteDesc" name="btn_srUpdateNoteDesc">
										<i class="fa fa-floppy-o"></i>
										<spring:message code="button.saveNote.label" />
									</button>
								</locus:privilege>
							</span>
						</div>


						<div class="btn-group">
							<button type="button" class="btn default" id="btn_srCancel" name="btn_srCancel">
								<spring:message code="button.cancel.label" />
							</button>
						</div>
						<div class="btn-group">
							<button id="backBtn" type="button" class="btn default" onclick="back()">
								<i class="fa fa-mail-reply"></i>
								<spring:message code="button.back.label" />
							</button>
						</div>
						<div class="btn-group">
							<button id="btnBackToCustomer" type="button" class="btn green">
								<i class="fa  fa-repeat"></i>
								<spring:message code="button.back.to.customer.label" />
							</button>
						</div>
					</div>
					<!-- End Panel Button -->

					<%-- 						</form> --%>
					<!-- End Form Service Request -->
				</div>
				<!-- End form-body -->

			</div>
			<!-- End Div Class Portlet box blue -->

		</div>

	</div>
	<!--  End Begin Service Request Detail  -->

</form:form>


<div class="portlet box blue form-horizontal">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-reorder"></i>
			<spring:message code="sr.srDetailMoreInfoCaption" />
		</div>
		<div class="tools">
			<a href="javascript:;" class="collapse"> </a>

		</div>
	</div>


	<div class="portlet-body">

		<div id="tabs_sr_info">
			<ul class="nav nav-tabs">

				<li class="active"><a href="#kb_solutions_tab" data-toggle="tab"><spring:message code="sr.kbSolutionsTab" /></a></li>
				<li><a href="#act_tab" data-toggle="tab"><spring:message code="sr.actCaptionTab" /></a></li>

			</ul>
			<div class="tab-content">
				<div class="tab-pane fade active in" id="kb_solutions_tab">
					<br>
				</div>
				<div class="tab-pane fade" id="act_tab">
					<br>
				</div>


			</div>
			<!-- End tab-content -->
		</div>
	</div>
	<!-- End Portlet-body ${srModelBean.mode}-->
</div>

<!-- DIALOG User Dialog -->
<div class="modal fade" id="userOwnerDialog" role="basic" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div id="modal_content_div_user"></div>
		</div>
	</div>
</div>

<div class="modal fade" id="srCustomerDialog" role="basic" aria-hidden="true">
	<div class="page-loading page-loading-boxed">
		<img src="assets/img/loading-spinner-grey.gif" alt="" class="loading"> <span> &nbsp;&nbsp;Loading... </span>
	</div>
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div id="modal_content_div_cust"></div>
		</div>
	</div>
</div>

<script type="text/javascript">

var CONTEXT_PATH = "<%=request.getContextPath()%>";
	var AJAX_TIMEOUT = "10000";
	var customerUrl = "/customerDetail.htm";

	var srMode = '<c:out value="${srModelBean.mode}"/>';
	var srModule = '<c:out value="${srModelBean.module}"/>';
	var statusCd = '<c:out value="${srModelBean.srStatusCd}"/>';

	var msgTitleSr = '<spring:message code="menu.serviceRequest.detail"/>';
	var msgAlertSr = '<spring:message code="message.activity.open"/>';
	var confirmCreateConsultingMsg = '<spring:message code="message.confirm.creat.consulting"/>';
	var plaseSelectMsg = '<spring:message code="message.please.select" />';
	var plaseCreateConsulting = '<spring:message code="message.warning.creat.consulting" />';
	var srNumberTemp = "";

	var compValidateSrMain = [ "txt_sr_subject", "cmbSrTypeProblem1", "cmbSrTypeProblem2", "cmbSrTypeProblem3", "cmbSrTypeProblem4", "cmbSrTypeProblem5", "cmb_sr_priority", "cmb_sr_status" ];

	$(document).ready(function() {
		//$("#btnBackToCustomer").hide();

		srNumberTemp = '<c:out value="${srModelBean.srNumber}"/>';

		ComponentsPickers.init();
		validateForm($("#srForm"));

		loadDataCombobox();

		// Begin Dialog

		//Owner dialog
		$("#btnSearchOwner").click(function() {
			loadContentIntoModal($("#modal_content_div_user"), "openModalDialog.htm", "userDialog", "sr.srOwner", "selectedSROwnerUser");
		});

		$("#btnClearOwner").click(function() {
			$("#txt_sr_owner_id").val("");
			$("#txt_sr_owner_name").val("");
		});

		// Customer Dialog
		$("#btnSearchCustomer").click(function() {
			loadContentIntoModal($("#modal_content_div_cust"), "openModalDialog.htm", "customerDialog", "modal.header.cust", "selectedSRCustomer");
		});

		$("#btnClearCustomer").click(function() {
			$("#txt_sr_customer_name").val("");
			$("#txt_sr_customer_id").val("");
		});

		// End Dialog	

		$("#btn_srNew").click(function(e) {
			e.preventDefault();

			clearValidate(compValidateSrMain);

			if (checkConsultingIsAvailible()) {
				confirmCreateConsulting();
			} else {
				prepareScreenCreateSRFromConsulting();
			}
		});

		// Add new 2 function for save in insert mode and update mode
		$("#btn_srInsert").click(function(e) {
			e.preventDefault();

			if (checkConsultingIsAvailible()) {
				alertMessage("<h5>" + msgTitleSr + "</h5>", "<div class='bootbox-body'>" + plaseCreateConsulting + "</div>");
				return false;

			} else {
				var $valid = $("#srForm").valid();
				if (!$valid) {
					return false;
				}

				var srStatus = $("#cmb_sr_status").val();
				var srModeChk = $("#srMode").val();

				if (srStatus === "03" && srModeChk != "insert") {
					checkStatusActivity();
				} else {
					insertOrUpdateServiceRequest();
				}

			}

		});

		$("#btn_srUpdate").click(function(e) {
			e.preventDefault();

			var $valid = $("#srForm").valid();
			if (!$valid) {
				return false;
			}

			var srStatus = $("#cmb_sr_status").val();
			var srModeChk = $("#srMode").val();

			if (srStatus === "03" && srModeChk != "insert") {
				checkStatusActivity();
			} else {
				insertOrUpdateServiceRequest();
			}
		});

		$("#btn_srUpdateNoteDesc").click(function(e) {
			e.preventDefault();

			$("#srForm").attr("action", "updateServiceRequestNoteDescription.htm");
			ajaxSubmitFormAndRedirect($("#srForm"));

		});

		/* -------------------------------------------------------------- */

		$("#btn_srCancel").click(function(e) {
			e.preventDefault();

			loadDataComboboxForClear();

			cancelSRScreen();
		});

		$("#btnBackToCustomer").click(function(e) {
			e.preventDefault();
			goToCustomerDetailPage();
		});

		$("#cmbSrTypeProblem1").change(function() {

			getTypeProblem2ByParentIdList($(this).val(), '', plaseSelectMsg);

			$("#cmbSrTypeProblem3").html("");
			$("#cmbSrTypeProblem4").html("");
			$("#cmbSrTypeProblem5").html("");

			$("#cmbSrTypeProblem3").select2("val", null);
			$("#cmbSrTypeProblem4").select2("val", null);
			$("#cmbSrTypeProblem5").select2("val", null);
		});

		$("#cmbSrTypeProblem2").change(function() {

			getTypeProblem3ByParentIdList($(this).val(), '', plaseSelectMsg);

			$("#cmbSrTypeProblem4").html("");
			$("#cmbSrTypeProblem5").html("");
			$("#cmbSrTypeProblem4").select2("val", null);
			$("#cmbSrTypeProblem5").select2("val", null);

		});

		$("#cmbSrTypeProblem3").change(function() {

			getTypeProblem4ByParentIdList($(this).val(), '', plaseSelectMsg);

			$("#cmbSrTypeProblem5").html("");
			$("#cmbSrTypeProblem5").select2("val", null);

		});

		$("#cmbSrTypeProblem4").change(function() {
			getTypeProblem5ByParentIdList($(this).val(), '', plaseSelectMsg);

		});

		$("#cmbSrTypeProblem5").change(function() {
			getSlaIdByCatId5($(this).val());
		});

		checkModeInsert();
		toggleButton(srMode);

		disableScreenBySrStatusCode();
	});

	// Disable Screen Status = 03 Close
	function disableScreenBySrStatusCode() {

		var compArrSrEnable = [ "txt_sr_subject", "btnSearchOwner", "btnClearOwner", "btnSearchCustomer", "btnClearCustomer", "cmb_sr_channel", "cmb_sr_priority","cmb_sr_cont_list", "cmbSrTypeProblem1",
				"cmbSrTypeProblem2", "cmbSrTypeProblem3", "cmbSrTypeProblem4", "cmbSrTypeProblem5", "cmb_sr_status", "txt_sr_desc", "btn_srUpdate", "btn_srCancel" ];
		var compNoteBtnEnable = [ "btn_srUpdateNoteDesc" ];

		if (statusCd == "03") {
			setComponentDisableById(compArrSrEnable, true);

			setComponentDisableById(compNoteBtnEnable, false); // enable btn save note
		} else {
			setComponentDisableById(compNoteBtnEnable, true); // disable btn save note
		}
	}

	function loadDataComboboxForClear() {

		var srTypeProblem1Cd = '<c:out value="${srModelBean.srTypeProblem1Cd}"/>';
		var srTypeProblem2Cd = '<c:out value="${srModelBean.srTypeProblem2Cd}"/>';
		var srTypeProblem3Cd = '<c:out value="${srModelBean.srTypeProblem3Cd}"/>';
		var srTypeProblem4Cd = '<c:out value="${srModelBean.srTypeProblem4Cd}"/>';
		var srTypeProblem5Cd = '<c:out value="${srModelBean.srTypeProblem5Cd}"/>';

		getTypeProblem2ByParentIdList(srTypeProblem1Cd, srTypeProblem2Cd, plaseSelectMsg);
		getTypeProblem3ByParentIdList(srTypeProblem2Cd, srTypeProblem3Cd, plaseSelectMsg);
		getTypeProblem4ByParentIdList(srTypeProblem3Cd, srTypeProblem4Cd, plaseSelectMsg);
		getTypeProblem5ByParentIdList(srTypeProblem4Cd, srTypeProblem5Cd, plaseSelectMsg);

	}

	function toggleButton(mode) {
		if (mode == 'insert') {
			$("#insertDiv").show();
			$("#updateDiv").hide();

		} else {
			$("#insertDiv").hide();
			$("#updateDiv").show();
		}
	}

	function goToCustomerDetailPage() {

		var custId = '<c:out value="${srModelBean.srCustId}"/>';
		var custType = '<c:out value="${srModelBean.srCustType}"/>';
		var custUrl = customerUrl + "?module=sr&id=" + custId + "&type=" + custType;
		postAction(custUrl);

	}

	function confirmCreateConsulting() {

		var callbackYes = "createConsultingHistorySrYes";
		var callbackNo = "callbackCheckModuleConfirmNo";
		var param = "";

		alertConfirmYesNo(confirmCreateConsultingMsg, callbackYes, callbackNo, param);
	}

	function createConsultingHistorySrYes() {
	
		if (!empty(srModule)) {
			if (srModule === "customer") {
				var custId = '<c:out value="${srModelBean.srCustId}"/>';
				if (typeof setCustomerToConsultingHeader != 'undefined') {
					setCustomerToConsultingHeader(custId);
				}
			}
		}
		// Open modal Consulting 
		openConsultingModalStartButton();
		prepareScreenCreateSR(); // Regular
	}

	function callbackCheckModuleConfirmNo() {

		console.log("Module Request CallbackCheckModuleConfirmNo : " + srModule);

		if (srModule === "customer") {
			prepareScreenCreateSRFromCust();
		} else {
			//Regular Case
			prepareScreenCreateSR();
		}
	}

	function checkModeInsert() {

		if (!empty(srMode)) {

			if (srMode === "insert") {
				console.log("Service Request Insert Mode ");

				if (checkConsultingIsAvailible()) {
					var callbackYes = "createConsultingHistorySrYes";
					var callbackNo = "callbackCheckModuleConfirmNo";
					var param = "";
					alertConfirmYesNo(confirmCreateConsultingMsg, callbackYes, callbackNo, param);

				} else {
					prepareScreenCreateSRFromConsulting();
				}

			} else {

				console.log("Service Request Update Mode ");

				checkModuleForDisplay();

				var srNumber = '<c:out value="${srModelBean.srNumber}"/>';
				initialTabOnloadSolutions(srNumber);
				serviceReqActiveTab(srNumber);
			}

			//checkModuleForDisplay();

		} else {

			console.log("Service Request Other Mode ");
			var srNumber = '<c:out value="${srModelBean.srNumber}"/>';
			initialTabOnloadSolutions(srNumber);
			serviceReqActiveTab(srNumber);

			//checkModuleForDisplay();
		}

		checkModuleForDisplay();
	}

	function checkModuleForDisplay() {

		if (srModule === "customer") {

			var comArrId1 = [ "btnBackToCustomer" ];
			setComponentVisibleById(comArrId1, true); //Display

			var comArrId2 = [ "btnSearchCustomer", "btnClearCustomer" ];
			setComponentDisableById(comArrId2, true); // Disable

		} else {

			var comArrId1 = [ "btnBackToCustomer" ];
			setComponentVisibleById(comArrId1, false); //Hidden

			var comArrId2 = [ "btnSearchCustomer", "btnClearCustomer" ];
			setComponentDisableById(comArrId2, false); //Enable
		}
	}

	/**
	 * Set Value return form modal popup and set into component
	 * Owner 
	 */
	function selectedSROwnerUser(jsonData) {

		$('#txt_sr_owner_id').val(jsonData.userId);
		$('#txt_sr_owner_name').val(jsonData.firstName + ' ' + jsonData.lastName);
		$("#userOwnerDialog").modal("hide");

	}

	/**
	 * Set Value return form modal popup and set into component
	 * Department
	 */
	function selectedSRDepartment(jsonData) {

		$('#current_to_dept_cd').val(jsonData.deptCode);
		$('#txt_sr_owner_dept').val(jsonData.deptName);
		$("#deptDialog").modal("hide");
	}

	function selectedSRCustomer(jsonData) {
		
		var custIdVal = jsonData.custId;
		
		$('#txt_sr_customer_name').val(jsonData.custName);
		$('#txt_sr_customer_id').val(jsonData.custId);
		$('#txt_sr_customer_type').val(jsonData.custTypeCd);
		$("#srCustomerDialog").modal("hide");
		
		getContactByCustIdList("",custIdVal, plaseSelectMsg);
		
	}

	/***
	 * 
	 */
	function loadDataCombobox() {

		var srTypeProblem1Cd = '<c:out value="${srModelBean.srTypeProblem1Cd}"/>';
		var srTypeProblem2Cd = '<c:out value="${srModelBean.srTypeProblem2Cd}"/>';
		var srTypeProblem3Cd = '<c:out value="${srModelBean.srTypeProblem3Cd}"/>';
		var srTypeProblem4Cd = '<c:out value="${srModelBean.srTypeProblem4Cd}"/>';
		var srTypeProblem5Cd = '<c:out value="${srModelBean.srTypeProblem5Cd}"/>';

		var srPriorityCd = '<c:out value="${srModelBean.srPriorityCd}"/>';

		var srContactId = '<c:out value="${srModelBean.srContactId}"/>';
		var srCustId = '<c:out value="${srModelBean.srCustId}"/>';
		
		getTypeProblem1List(srTypeProblem1Cd, plaseSelectMsg);
		getTypeProblem2ByParentIdList(srTypeProblem1Cd, srTypeProblem2Cd, plaseSelectMsg);
		getTypeProblem3ByParentIdList(srTypeProblem2Cd, srTypeProblem3Cd, plaseSelectMsg);
		getTypeProblem4ByParentIdList(srTypeProblem3Cd, srTypeProblem4Cd, plaseSelectMsg);
		getTypeProblem5ByParentIdList(srTypeProblem4Cd, srTypeProblem5Cd, plaseSelectMsg);

		getPriorityByList(srPriorityCd, plaseSelectMsg);
		
		
		getContactByCustIdList(srContactId,srCustId, plaseSelectMsg);
	}

	function getSlaIdByCatId5(contentCatId) {

		var param = "contentCatId=" + contentCatId;
		var jsonObj = getJsonData("getSlaByContentCatId.htm", param, "POST");

		if (jsonObj.resultCode == '0') {
			var model = jsonObj.model;

			$("#cmb_sr_priority").select2("val", model.slaId);
		}
	}

	function getTypeProblem1List(defaultValueJsp, plaseSelectMsg) {

		getTypeProblem1ListDefaultVal($("#cmbSrTypeProblem1"), defaultValueJsp, plaseSelectMsg);
	}

	function getTypeProblem2ByParentIdList(parentCatId, defaultValueJsp, plaseSelectMsg) {

		getTypeProblem2ByParentIdListDefaultVal($("#cmbSrTypeProblem2"), parentCatId, defaultValueJsp, plaseSelectMsg);
	}

	function getTypeProblem3ByParentIdList(parentCatId, defaultValueJsp, plaseSelectMsg) {

		getTypeProblem3ByParentIdListDefaultVal($("#cmbSrTypeProblem3"), parentCatId, defaultValueJsp, plaseSelectMsg);
	}

	function getTypeProblem4ByParentIdList(parentCatId, defaultValueJsp, plaseSelectMsg) {

		getTypeProblem4ByParentIdListDefaultVal($("#cmbSrTypeProblem4"), parentCatId, defaultValueJsp, plaseSelectMsg);
	}

	function getTypeProblem5ByParentIdList(parentCatId, defaultValueJsp, plaseSelectMsg) {

		getTypeProblem5ByParentIdListDefaultVal($("#cmbSrTypeProblem5"), parentCatId, defaultValueJsp, plaseSelectMsg);
	}

	function getPriorityByList(defaultValueJsp, plaseSelectMsg) {

		getSLAListDefaultVal($("#cmb_sr_priority"), defaultValueJsp, plaseSelectMsg);
	}

	function getContactByCustIdList(defaultValueJsp,custIdParam, plaseSelectMsg) {
	//	alert("custIdParam "+custIdParam);

		getContactByCustIdListDefaultVal($("#cmb_sr_cont_list"),custIdParam ,defaultValueJsp, plaseSelectMsg);
	}

	function back() {
		redirectUrlAction("serviceRequestList.htm");
	}

	function initialTabOnloadSolutions(srNumber) {

		$('#kb_solutions_tab').html("");
		$("#kb_solutions_tab").load("serviceRequestMoreInfo.htm", {
			tabAction : "solutions",
			sr_number : srNumber,
			refStatusCd : statusCd
		}, function(response, status, xhr) {
			// alert( "Load was performed." ); //KnowledgeBase
			if (xhr.status == 200) {

			}

		});
	}

	function serviceReqActiveTab(srNumberParam) {

		$("#tabs_sr_info").tabs({

			activate : function(event, ui) {

				var $activeTab = $("#tabs_sr_info").tabs("option", "active");
				//var srNumberTab = '<c:out value="${srModelBean.srNumber}"/>';
				var srNumberTab = srNumberParam;

				if ($activeTab == 0) {

					$('#kb_solutions_tab').html("");
					jLoBlockUI();
					$("#kb_solutions_tab").load("serviceRequestMoreInfo.htm", {
						tabAction : "solutions",
						sr_number : srNumberTab,
						refStatusCd : statusCd
					}, function(response, status, xhr) {
						// alert( "Load was performed." ); //KnowledgeBase
						if (xhr.status == 200) {

						}

					});

				} else if ($activeTab == 1) {

					$('#act_tab').html("");
					jLoBlockUI();
					$("#act_tab").load("serviceRequestMoreInfo.htm", {
						tabAction : "activity",
						sr_number : srNumberTab,
						refStatusCd : statusCd
					}, function(response, status, xhr) {
						//alert( "Load was performed." );
						if (xhr.status == 200) {

						}

					});
				}
			}

		});

	}

	function prepareScreenCreateSRFromCust() {

		prepareScreenCreateSR();

		var srCustName = '<c:out value="${srModelBean.srCustomerName}"/>';
		var srCustId = '<c:out value="${srModelBean.srCustId}"/>';
		var srCustType = '<c:out value="${srModelBean.srCustType}"/>';

		$("#txt_sr_customer_name").val(srCustName);
		$("#txt_sr_customer_id").val(srCustId);
		$("#txt_sr_customer_type").val(srCustType);
		
		getContactByCustIdList("", srCustId, plaseSelectMsg);
	}

	function setCustContactConsulting() {

		var srCustName = consultingCustName;
		var srCustId = consultingCustId;
		var srCustType = consultingCustType;
		var srContactId = consultingContactId;
		var srContactName = consultingContactName;
		var srconsultingNumber = consultingNumber;
		var srChannelCd = "03"; // Call-In

		$("#txt_sr_customer_name").val(srCustName);
		$("#txt_sr_customer_id").val(srCustId);
		$("#txt_sr_customer_type").val(srCustType);
		$("#txtSRConsultingNumber").val(srconsultingNumber);

		if (!empty(consultingChannelCd)) {

			$("#cmb_sr_channel").select2("val", consultingChannelCd);
		} else {
			$("#cmb_sr_channel").select2("val", srChannelCd);
		}

	}

	function prepareScreenCreateSRFromConsulting() {

		//Normal Prepare
		prepareScreenCreateSR();

		// for set consulting
		setCustContactConsulting();
		
		

	}

	function prepareScreenCreateSR() {

		$("#srForm").clearForm();

		$("#srCreateBy").text("");
		$("#srCreateDate").text("");
		$("#srUpdateBy").text("");
		$("#srUpdateDate").text("");

		$("#srMode").val("insert");
		toggleButton($("#srMode").val());

		$("#txt_sr_customer_id").val("");
		$("#txt_sr_customer_type").val("");

		$("#txt_sr_contact_id").val("");

		var srOwnerName = '<c:out value="${USER_PROFILE.firstName} ${USER_PROFILE.lastName}"/>';
		var srOwnerId = '<c:out value="${USER_PROFILE.userId}"/>';

		$("#txt_sr_owner_name").val(srOwnerName);
		$("#txt_sr_owner_id").val(srOwnerId);

		var srStatuscdVal = "01";
		$("#cmb_sr_status").select2("val", srStatuscdVal);
				
		if(srModule != "customer"){
			getContactByCustIdList("", !empty(consultingCustId) ? consultingCustId : "-1" , plaseSelectMsg);	
		}
		
		var compArr = [ "btn_srNew" ];
		setComponentDisableById(compArr, true);

		var compArrSrEnable = [ "txt_sr_subject", "btnSearchOwner", "btnClearOwner", "btnSearchCustomer", "btnClearCustomer", "cmb_sr_channel", "cmb_sr_priority","cmb_sr_cont_list", "cmbSrTypeProblem1",
				"cmbSrTypeProblem2", "cmbSrTypeProblem3", "cmbSrTypeProblem4", "cmbSrTypeProblem5", "cmb_sr_status", "txt_sr_desc", "btn_srCancel" ];

		setComponentDisableById(compArrSrEnable, false);

		// below for check service request number 
		srNumberTemp = "";
		initialTabOnloadSolutions(srNumberTemp);

		serviceReqActiveTab(srNumberTemp);
	}

	function cancelSRScreen() {

		clearValidate(compValidateSrMain);

		// below for check service request number 
		srNumberTemp = '<c:out value="${srModelBean.srNumber}"/>';

		if (!empty(srNumberTemp)) {

			$("#srForm").trigger("reset");

			var srTypeProblem1Cd = '<c:out value="${srModelBean.srTypeProblem1Cd}"/>';
			var srTypeProblem2Cd = '<c:out value="${srModelBean.srTypeProblem2Cd}"/>';
			var srTypeProblem3Cd = '<c:out value="${srModelBean.srTypeProblem3Cd}"/>';
			var srTypeProblem4Cd = '<c:out value="${srModelBean.srTypeProblem4Cd}"/>';
			var srTypeProblem5Cd = '<c:out value="${srModelBean.srTypeProblem5Cd}"/>';

			var srChannelCdVal = '<c:out value="${srModelBean.srChannelCd}"/>';
			var srStatuscdVal = '<c:out value="${srModelBean.srStatusCd}"/>';
			var srPriorityCdVal = '<c:out value="${srModelBean.srPriorityCd}"/>';
			var srContactIdCdVal = '<c:out value="${srModelBean.srContactId}"/>';

			$("#cmbSrTypeProblem1").select2("val", srTypeProblem1Cd);
			$("#cmbSrTypeProblem2").select2("val", srTypeProblem2Cd);
			$("#cmbSrTypeProblem3").select2("val", srTypeProblem3Cd);
			$("#cmbSrTypeProblem4").select2("val", srTypeProblem4Cd);
			$("#cmbSrTypeProblem5").select2("val", srTypeProblem5Cd);

			$("#cmb_sr_channel").select2("val", srChannelCdVal);
			$("#cmb_sr_status").select2("val", srStatuscdVal);
			$("#cmb_sr_priority").select2("val", srPriorityCdVal);
			$("#cmb_sr_cont_list").select2("val", srContactIdCdVal);

			// Reset Panel User Info Crete and Update
			var srCreateBy = '<c:out value="${srModelBean.createByName}"/>';
			var srCreateDate = '<c:out value="${srModelBean.createDateTime}"/>';
			var srUpdateByName = '<c:out value="${srModelBean.updateByName}"/>';
			var srUpdateDateTime = '<c:out value="${srModelBean.updateDateTime}"/>';
			$("#srCreateBy").text(srCreateBy);
			$("#srCreateDate").text(srCreateDate);
			$("#srUpdateBy").text(srUpdateByName);
			$("#srUpdateDate").text(srUpdateDateTime);

			$("#srMode").val("update");
			toggleButton($("#srMode").val());

			var compArr = [ "btn_srNew" ];
			setComponentDisableById(compArr, false);
			initialTabOnloadSolutions(srNumberTemp);
			serviceReqActiveTab(srNumberTemp);

		} else {

			$("#srForm").clearForm();

			$("#srCreateBy").text("");
			$("#srCreateDate").text("");
			$("#srUpdateBy").text("");
			$("#srUpdateDate").text("");

			$("#srMode").val("insert");

			$("#txt_sr_customer_id").val("");
			$("#txt_sr_customer_type").val("");
			$("#txt_sr_contact_id").val("");

			var srOwnerName = '<c:out value="${USER_PROFILE.firstName} ${USER_PROFILE.lastName}"/>';
			var srOwnerId = '<c:out value="${USER_PROFILE.userId}"/>';

			$("#txt_sr_owner_name").val(srOwnerName);
			$("#txt_sr_owner_id").val(srOwnerId);

			var srStatuscdVal = "01";
			$("#cmb_sr_status").select2("val", srStatuscdVal);

			var compArr = [ "btn_srNew" ];
			setComponentDisableById(compArr, true);

		}

	}

	function disableSRScreen() {

		var compArr = [ "txt_sr_subject", "cmbSrTypeProblem1", "txt_sr_subject" ];
		setComponentDisableById(compArr, false);

		disableEnableForm($("#srForm"), true);
	}

	function insertOrUpdateServiceRequest() {

		var SR_MODE_IU = $("#srMode").val();

		var srConsultingNumber = consultingNumber;
		$("#srConsultingNumber").val(srConsultingNumber);

		if (SR_MODE_IU === "insert") {

			$("#srForm").attr("action", "insertServiceRequest.htm");
			ajaxSubmitFormAndRedirect($("#srForm"));
			//$("#srForm").submit();

		} else if (SR_MODE_IU === "update") {

			$("#srForm").attr("action", "updateServiceRequest.htm");
			ajaxSubmitFormAndRedirect($("#srForm"));
			//$("#srForm").submit();
		}

		///SrStatusCd

		//	$("#srForm").attr('action', 'serviceRequestInsertOrUpdate.htm');
		//	$("#srForm").submit();

	}

	function checkStatusActivity() {

		var refSrNumber = '<c:out value="${srModelBean.srNumber}"/>';

		jLoBlockUI();
		$.ajax({
			timeout : AJAX_TIMEOUT,
			url : "checkStatusActivityByRefNo.htm",
			type : "POST",
			data : "referenceNo=" + refSrNumber,
			dataType : "json",
			success : function(data, textStatus, jqXHR) {

				var dataCount = data.count;

				if (dataCount == "0") {

					insertOrUpdateServiceRequest();

				} else {
					alertMessage("<h5>" + msgTitleSr + "</h5>", "<h6>" + msgAlertSr + " " + refSrNumber + "</h6>");

					$("#cmb_sr_status").select2("val", statusCd);

					return false;
				}

			},
			error : function(xhr, textStatus, errorThrown) {
				checkShowMsgAjaxError(xhr, textStatus, errorThrown);
			}
		});
	}
</script>