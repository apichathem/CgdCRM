<%@page import="java.util.HashMap"%>
<%@page import="com.locus.common.constant.WebPortalConstant"%>
<%@page import="com.locus.jlo.web.bean.dto.UserInfoDTO"%>
<%@page import="com.locus.jlo.web.constant.JLOWebConstant"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="locus" uri="/WEB-INF/tld/locus.tld"%>

<spring:message code="message.please.enter" var="msgPleaseActEnter"/>
<spring:message code="activity.detail.actAttendTo" var="msgActAttendTo"/>
<spring:message code="activity.detail.actTitle" var="msgActTitle" />
<spring:message code="activity.detail.actType" var="msgActType"/>
<spring:message code="activity.detail.actDetail" var="msgActDetail"/>
<spring:message code="activity.detail.actStatus" var="msgActStatus"/>
<spring:message code="activity.detail.actGroup" var="msgActGroup"/>

<!-- USER DIALOG -->
<div class="modal fade" id="userDialog" role="basic" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div id="modal_content_div"></div>
		</div>
	</div>
</div>

<!-- BEGIN HEAD AND TITLE -->
<div class="row">
	<div class="col-md-12">
		<h3 class="caption">
			<spring:message code="menu.activity.management"/>
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i><a href="home.htm"> <spring:message code="menu.home"/></a>
			<i class="fa fa-angle-right"></i></li>
			<li><a href="#"><spring:message code="menu.activity"/></a>
			<i class="fa fa-angle-right"></i></li>
			<li><a href="#"><spring:message code="menu.activity.management"/></a></li>
		</ul>
	</div>
</div>
<!-- END HEAD AND TITLE-->

<!-- BEGIN CRITERIA -->
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
		
			<!-- BEGIN TITLE CRITERIA -->
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-reorder"></i>
					<spring:message code="activity.header.actCaptionSearch" />
				</div>
				<div class="tools">
					<a class="collapse" href="javascript:;"></a>
				</div>
			</div>
			<!-- END TITLE CRITERIA-->

			<!-- BEGIN PORTLET BODY OF CRITERIA -->
			<div class="portlet-body form">
				<form:form id="ActivityCriteriaMain" modelAttribute="activityModelBean" class="form-horizontal">
					
					<div class="form-body">
					
						<!-- BEGIN QUICK SEARCH CRITERIA -->
						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-5"><spring:message code="activity.header.ownerName"/></label>
									<div class="col-md-7">
										<div class="input-group">
											<form:hidden id="ownerId" path="ownerId" class="form-control"/>
											<form:input id="ownerName" path="ownerName" class="form-control" readonly="true"/>
											<span class="input-group-addon"> 
												<a id="btnSearchOwner" href="#" data-target="#userDialog" data-toggle="modal">
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
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-5"> <spring:message code="activity.detail.actGroup"/>
									</label>
									<div class="col-md-7">
										<form:select id="activityGroup" path="activityGroup" class="form-control select2me" >
										<%-- data-rule-required="true"  data-msg-required='${msgPleaseActEnter}&nbsp;${msgActGroup}.' --%>
										<option></option>
											<form:options items='${CODEBOOK_LIST.ACT_GROUP}' itemLabel="codeName" itemValue="codeId" />
										</form:select>
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label class="col-md-5 control-label"> <spring:message code="activity.header.status"/>
									</label>
									<div class="col-md-7">
										<form:select id="activityStatusParam" path="activityStatusParam" class="select2me form-control">
										<option></option>
											<form:options items='${CODEBOOK_LIST.ACT_STATUS}' itemLabel="codeName" itemValue="codeId" />
										</form:select>
									</div>
								</div>
							</div>
							
						</div>
						<!-- END QUICK SEARCH CRITERIA -->

						<!-- BEGIN ADVANCE SEARCH CRITERIA -->
						<div id="divAdvSearch" style="display: none;">
							<div class="row">
								<div class="col-md-4">
									<div class="form-group">
										<label class="col-md-5 control-label"> <spring:message code="activity.header.type"/></label>
										<div class="col-md-7">
											<form:select id="activityTypeParam" path="activityTypeParam" class="select2me form-control">
												<option></option>
												<form:options items='${CODEBOOK_LIST.ACT_TYPE}' itemLabel="codeName" itemValue="codeId" />
											</form:select>
										</div>
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label class="control-label col-md-5"><spring:message code="activity.header.createdDate"/></label>
										<div class="col-md-7">
											<div class="input-group">
												<div class="input-group date-picker input-daterange" data-date-format="dd/mm/yyyy" >
													<input id="activityCreatedDateFrom" type="text" class="form-control" name="activityCreatedDateFrom" placeholder="dd/mm/yyyy" />
														<span class="input-group-addon" > - </span>
													<input id="activityCreatedDateTo" type="text" class="form-control" name="activityCreatedDateTo" placeholder="dd/mm/yyyy" />
												</div> 
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label class="control-label col-md-5"><spring:message code="activity.header.dueDate"/></label>
										<div class="col-md-7">
											<div class="input-group">
												<div class="input-group date-picker input-daterange" data-date-format="dd/mm/yyyy" >
													<input id="activityClosedDateFrom" type="text" class="form-control" name="activityClosedDateFrom" placeholder="dd/mm/yyyy"/>
														<span class="input-group-addon" > - </span>
													<input id="activityClosedDateTo" type="text" class="form-control" name="activityClosedDateTo" placeholder="dd/mm/yyyy" />
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-4">
									<div class="form-group">
										<label class="col-md-5 control-label"> <spring:message code="activity.header.activityNo"/>
										</label>
										<div class="col-md-7">
											<input class="form-control" id="activityNumber" name="activityNumber" type="text" placeholder="Activity No." maxlength="25">
										</div>
									</div>
								</div>
								<div class="col-md-4"></div>
								<div class="col-md-4"></div>
							</div>
						</div>
						<!-- END ADVANCE SEARCH CRITERIA -->
						
						<br>
					</div>

					<!-- BEGIN SERCH BUTTON GROUP -->
					<div class="form-actions right">
						<button id="btnSearchAct" class="btn blue" type="button">
							<i class="fa fa-search"></i>
							<spring:message code="button.search.label"/>
						</button>
						<button id="btnCancelSearch" class="btn default" type="button">
							<spring:message code="button.cancel.label"/>
						</button>
						<button id="btnAdvSearch" class="btn dark" type="button" onclick="javascript:showAdvSearch();">
							<i class="fa fa-search"></i>
							<spring:message code="button.advanceSearch.label"/>
						</button>
						<button id="btnQuickSearch" class="btn dark" type="button" onclick="javascript:hideAdvSearch();">
							<i class="fa fa-search"></i>
							<spring:message code="button.quickSearch.label"/>
						</button>
						<locus:privilege oper="ADD">
							<button id="btnCreateAct" class="btn green" type="button" onclick="javascript:initActivityforInsert();">
								<i class="fa fa-plus"></i>
								<spring:message code="button.create.label"/>
							</button>
						</locus:privilege>
					</div>
					<!-- END SERCH BUTTON GROUP -->
				</form:form>
			</div>
			<!-- END PORTLET BODY OF CRITERIA -->
		</div>
	</div>
</div>
<!-- END CRITERIA -->

<!-- BEGIN TABLE LIST -->
<div class="row" id="div_table_list">
	<div class="col-md-12">
		<div class="portlet-body">
			<div class="portlet-body">
				<table class="table table-bordered" id="tbl_actList">
				</table>
			</div>
		</div>
	</div>
</div>
<!-- END TABLE LIST -->

<!-- BEGIN ACTIVITY DETAIL AND DOCUMENT LIST -->
<div class="row" id="div_edit">
	<div class="col-md-12">
		<div class="portlet box blue">		
			
			<!-- BEGIN ACTIVITY CRITERIA -->
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-reorder"></i>
					<spring:message code="activity.detail.actDetailCaption" />
				</div>
				<div class="tools">
					<a class="collapse" href="javascript:;"></a>
				</div>
			</div>
			<!-- END TITLE CRITERIA-->
			
			<!-- BEGIN CRITERIA -->
			
			<div class="portlet-body form">
				<form:form id="actForm" class="form-horizontal" modelAttribute="activityModelBean" method="POST">
					<form:hidden id="modeAct" path="mode"/>
					<form:hidden id="regId" path="regId"/>
				     
					<div class="form-body">
					
						<!-- BEGIN ACTIVITY DETAIL -->
						
						<!-- BEGIN ROW1 ACTIVITY -->
						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-5"> <spring:message code="activity.detail.actNumber"/>
									</label>
									<div class="col-md-7">
										<form:input id="actNumber" path="actNumber" class="form-control" readonly="true"/>
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-5"> <spring:message code="activity.detail.actPhoneNo"/>
									</label>
									<div class="col-md-7">
										<div class="input-group">															
											<form:input type="tel" id="phoneNo" name="phoneNo" path ="phoneNo" class="form-control maskmobile"  />
											<span class="input-group-btn">
												<button type="button" id="btn_phone_no" style="width:30px" class="btn">
													<i class="fa fa-phone-square" id="select_phone_no"></i>
												</button>
											</span>
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-5">
										<spring:message code="activity.detail.actStatus"/>
										<span class="required">*</span>
									</label>
									<div class="col-md-7">
										<form:select id="statusCd" path="statusCd" class="form-control select2me" data-rule-required="true" 
													 data-msg-required='${msgPleaseActEnter}&nbsp;${msgActStatus}.'>
										<option></option>
											<form:options items='${CODEBOOK_LIST.ACT_STATUS}' itemLabel="codeName" itemValue="codeId" />
										</form:select>
									</div>
								</div>
							</div>
						</div>
						<!-- END ROW1 ACTIVITY -->
	
						<!-- BEGIN ROW2 ACTIVITY -->
						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-5">
										<spring:message code="activity.detail.actAttendTo"/>
										<span class="required">*</span>
									</label>
									<div class="col-md-7">
										<form:input path="attendTo" class="form-control" maxlength="50"  data-rule-required="true"
											data-msg-required='${msgPleaseActEnter}&nbsp;${msgActAttendTo}.'/>
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-5"> <spring:message code="activity.detail.actSmsNo"/>
									</label>
									<div class="col-md-7">
										<div class="input-group">															
											<form:input type="tel" id="smsNo" name="smsNo" path ="smsNo" class="form-control maskmobile"  />
											<span class="input-group-btn">
												<button type="button" style="width:30px" id="btn_sms_no" class="btn">
													<i class="fa fa-phone-square" id="select_sms_no"></i>
												</button>
											</span>
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-5"><spring:message code="activity.detail.actAssignTo"/></label>
									<div class="col-md-7">
										<div class="input-group">
											<form:hidden id="assignToId" path="assignToId" class="form-control"/>
											<form:input id="assignToName" path="assignToName" class="form-control" readonly="true"/>
											<span class="input-group-btn">												
												<button type="button" id="btnAssignTo" class="btn" data-target="#userDialog" data-toggle="modal">
													<i class="fa fa-user"></i>
												</button>
											</span> 
											<span class="input-group-btn">												
												<button type="button" id="btnClearAssignTo" class="btn">
													<i class="fa fa-minus-circle red"></i>
												</button>
											</span> 
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- END ROW2 ACTIVITY -->
	
						<!-- BEGIN ROW3 ACTIVITY -->
						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-5"><spring:message code="activity.detail.actTitle" />
										<span class="required">*</span>
									</label>
									<div class="col-md-7">
										<form:input id="title" path="title" class="form-control" data-rule-required="true" maxlength="100" data-msg-required='${msgPleaseActEnter}&nbsp;${msgActTitle}.' />
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-5"> <spring:message code="activity.detail.actFaxNumber"/>
									</label>
									<div class="col-md-7">
										<div class="input-group">															
											<form:input type="tel" id="faxNo" name="faxNo" path="faxNo" class="form-control maskmobile" />
											<span class="input-group-btn">
												<button type="button" style="width:30px" id="btn_fax_no" class="btn">
													<i class="fa fa-phone-square" id="select_fax_no"></i>
												</button>
											</span>
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-5"><spring:message code="activity.detail.actDept" /></label>
									<div class="col-md-7">
										<div class="input-group">
											<form:hidden id="ownerDeptCode" path="ownerDeptCode" class="form-control" />
											<form:input id="deptName" path="deptName" class="form-control" readonly="true" />
											<span class="input-group-btn">												
												<button type="button" id="btnSearchDept" class="btn" data-target="#userDialog" data-toggle="modal">
													<i class="fa fa-user"></i>
												</button>
											</span> 
											<span class="input-group-btn">												
												<button type="button" id="btnClearDept" class="btn">
													<i class="fa fa-minus-circle red"></i>
												</button>
											</span> 
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- END ROW3 ACTIVITY -->
	
						<!-- BEGIN ROW4 ACTIVITY -->
						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-5"><spring:message code="activity.detail.actType"/>
										<span class="required">*</span>
									</label>
									<div class="col-md-7">
										 <form:select id="typeCd"  path="typeCd" class="form-control select2me"  data-rule-required="true" data-msg-required='${msgPleaseActEnter}&nbsp;${msgActType}.'>
											<option></option>
											<form:options items='${CODEBOOK_LIST.ACT_TYPE}' itemLabel="codeName" itemValue="codeId" />
										</form:select> 
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-5"><spring:message code="activity.detail.actEmail"/>
									</label>
									<div class="col-md-7">
										<div class="input-group">															
											<form:input type="text" id="email" name="email" path="email" class="form-control" maxlength="50"/>
											<span class="input-group-btn">
												<button type="button" class="btn">
													<i class="fa fa-envelope" id="select_email"></i>
												</button>
												 
											</span>
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-5"><spring:message code="activity.detail.actDueDate" /></label>
									<div class="col-md-7">
										<div class="input-group">
											<form:input type="text" id="dueDt" name="dueDt" path="dueDt" class="form-control date-picker maskdate" data-date-format="dd/mm/yyyy" data-date-start-date="+0d"/>
											<span class="input-group-btn">
												<button id="btn_calendarDueDt" class="btn default" type="button"><i class="fa fa-calendar"></i></button>
											</span>
											<form:input type="text" id="dueTime" name="dueTime" path="dueTime"  class="form-control masktime"/>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- END ROW4 ACTIVITY -->
	
						<!-- BEGIN ROW5 ACTIVITY -->
						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-5"><spring:message code="activity.detail.actDetail"/>
										<span class="required">*</span>
									</label>
									<div class="col-md-7">
										<form:textarea id='description' path='description' class="form-control" rows="3" data-rule-required="true"  maxlength="500"
													   data-msg-required='${msgPleaseActEnter}&nbsp;${msgActDetail}.'></form:textarea>
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-5"><spring:message code="activity.detail.actAddress"/>
									</label>
									<div class="col-md-7">
										<form:textarea id='address' path='address' class="form-control" rows="3"  maxlength="500"></form:textarea>
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<!-- ROW 1.1 -->
								<div class="row">
									<div class="col-md-12">
										<div class="form-group">
											<label class="control-label col-md-5"><spring:message code="activity.detail.actOperaDate" /></label>
											<div class="col-md-7">
												<div class="input-group">
													<form:input type="text" id="operDt" name="operDt" path="operDt" class="form-control date-picker maskdate" data-date-format="dd/mm/yyyy" data-date-start-date="+0d"/>
													<span class="input-group-btn">
														<button id="btn_calendarOperDt" class="btn default" type="button"><i class="fa fa-calendar"></i></button>
													</span>
													<form:input type="text" id="operTime" name="operTime" path="operTime"  class="form-control masktime"/>
												</div>
											</div>
										</div>
									</div>
								</div>	
								<!-- ROW 1.2 -->
								<div class="row">
									<div class="col-md-12">
										<div class="form-group">
											<label class="control-label col-md-5"><spring:message code="activity.detail.actClosedDT" /></label>
											<div class="col-md-7">
												<div class="input-group">
													<form:input type="text" id="closedDt" name="closedDt" path="closedDt" class="form-control date-picker maskdate" data-date-format="dd/mm/yyyy" data-date-start-date="+0d"  disabled="true"/>
													<span class="input-group-btn">
														<button id="btn_calendarClosedDt" class="btn default" type="button"><i class="fa fa-calendar"></i></button>
													</span>
													<form:input type="text" id="closedTime" name="closedTime" path="closedTime"  class="form-control masktime" disabled="true"/>
												</div>
											</div>
										</div>
									</div>
								</div>	
							</div>
						</div>
						<!-- END ROW5 ACTIVITY -->
						
						<!-- END ACTIVITY DETAIL -->
						<hr/>
						<div class="row form-horizontal" id="show_create_field">
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-5"> <spring:message code="lbl.createBy"/>
									</label>
									<div class="col-md-7">
										<p id="createBy" class="form-control-static"></p>
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-5"> <spring:message code="lbl.createDate"/>
									</label>
									<div class="col-md-7">
										<p id="createDate" class="form-control-static"></p>
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group"></div>
							</div>
						</div>
						<div class="row form-horizontal" id="show_update_field">
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-5"> <spring:message code="lbl.updateBy"/>
									</label>
									<div class="col-md-7">
										<p id="updateBy" class="form-control-static"></p>
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-5"> <spring:message code="lbl.updateDate"/>
									</label>
									<div class="col-md-7">
										<p id="updateDate" class="form-control-static"></p>
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group"></div>
							</div>
						</div>
					</div>
					<!-- BEGIN PANEL BUTTON ACTIVITY -->
					<div class="form-actions right">
						<div class="btn-group">
							<span id="insertDiv" style="display: none;">
									<button type="button" class="btn blue" id="btn_actInsert" name="btn_actInsert">
								<i class="fa fa-floppy-o"></i>
								<spring:message code="button.save.label" />
							</button>
							</span>
							
							<span id="updateDiv" style="display: none;">
									<button type="button" class="btn blue" id="btn_actUpdate" name="btn_actUpdate">
										<i class="fa fa-floppy-o"></i>
										<spring:message code="button.save.label" />
									</button>
							</span>
						</div>
						<div class="btn-group">
							<button type="button" class="btn default" id="btn_actCancel" name="btn_actCancel">
								<spring:message code="button.cancel.label"/>
							</button>
						</div>
					</div>
					<!-- END PANEL BUTTON ACTIVITY -->
				</form:form>
				
			</div>
		</div>
	</div>
</div>
<!-- END ACTIVITY DETAIL AND DOCUMENT LIST -->

<!-- BEGIN Declare Url Path -->
	<c:url var="deleteUrl" value="/deleteActNumber.htm"/>
<!-- END Declare Url Path -->

<script type="text/javascript">

	var columns = "";
	var statusBtn = "";
	var actListDT = "";
	
	var recordPerPage = <%=JLOWebConstant.recordsPerPage%>;
	var firstTime = true;
	var plaseSelectMsg  = '<spring:message code="message.please.select" />';
	var activityTitle = '<spring:message code="activity.titleMain" />';
	var msgTitleAct = '<b><spring:message code="menu.activity" /></b>';
	var columnsModule ="";
	var msgDuplicateAct = '<spring:message code="activity.actAttDuplicateSelect" />';
	var actNumberTemp="";
	var actJsonData =[];
	var msgPleaseEnterEmail='<spring:message code="message.please.enter.email" />';
	var msgInValidEmail ='<spring:message code="message.please.provide.email"/>';
	var msgInValidPhoneNo ='<spring:message code="message.please.provide.phone"/>'; 
	
	var actStatusPocessing = "02";
	var actStatusCompleted = "03";
	var actStatusTemp = "";
	
	var saveModeClickTemp = "insert";
	
	var txtValidates = ["attendTo","title","typeCd","description"];
	
	$(document).ready(function(){
		
		$('#modeAct').val('insert');
		
		validateForm($("#actForm"));
		
		ComponentsPickers.init();
		
		//Activity List
		declareActivityColumn();
		
		actListDT = createActDatatable();
		
		//Onload page(check btn)
		statusBtn = "N";
		hideBtn(statusBtn);
		
		var defaultStatus = '01';
		$("#statusCd").select2("val",defaultStatus);
		
		var mode = '<c:out value="${activityModelBean.mode}"/>';
		var actNo = '<c:out value="${activityModelBean.activityNumber}"/>';
		var module = '<c:out value="${activityModelBean.module}"/>';
		
		//Selected Activity Grid
		$("#tbl_actList tbody tr").die( "click" ); 	
		$("#tbl_actList tbody tr").live('click', function () {
			
			clearValidate(txtValidates);
			$('#modeAct').val('update');
			var mode = $('#modeAct').val();
			toggleButton(mode);
			firstTime = false;
 	      	var aPos = actListDT.fnGetPosition(this);
 	      	
 	      	if(aPos == null){
 				return false;    		
 	    	}
 	      	
 	      	var aData = actListDT.fnGetData(aPos[0]);
 	      	
 	      	var actNoRefDocNo = aData[aPos].actNumber;
 	      	actNumberTemp = actNoRefDocNo;
 	      	
 	      	setButtomPanelDetail(aData[aPos]);
 	      	
			actJsonData = aData[aPos]; // Setting temporary
			
 	      	setFormScreenOnSelectDatable("actForm",aData[aPos]);
 	      	
			statusBtn = "Y";
			hideBtn(statusBtn);
			
			
			var currentwnerId = aData[aPos].ownerId;
			
 	      	var statusCd = aData[aPos].statusCd;
 	  	 	actStatusTemp = statusCd;
 	  	 	checkDisableActivityByStatusCd(statusCd,currentwnerId);
 	  	 	
	 	  	 jQuery.validator.setDefaults({
	 	  	  debug: true,
	 	  	  success: "valid"
	 	  	});
	 	  	$( "#attendTo" ).validate({
	 	  	  rules: {
	 	  	    details: {
	 	  	      required: "#other:checked"
	 	  	    }
	 	  	  }
	 	  	});
 	  	 	
		});
		
	 	 
	 	
	 	$("#btnCancelSearch").click(function () {
	 		
	 		resetForm();
	 	});
		
		$("#btnSearchOwner").click(function() {
			
			fromBtn = 'owner';
			loadContentIntoModal($("#modal_content_div"), "openModalDialog.htm", "userDialog", "activity.grdOwnerName", "selectedUser");
		});
		
		$("#btnClearOwner").click(function() {
			
			$("#ownerId").val('');
			$("#ownerName").val('');
		});
		
		//Search Activity
		$("#btnSearchAct").click(function() {
			actListDT = activitySearchData();
			toggleButton('update');
		});
		
		$("#btnAssignTo").click(function() {
			
			fromBtn = 'assignTo';
			loadContentIntoModal($("#modal_content_div"), "openModalDialog.htm", "userDialog", "activity.detail.actAssignTo", "selectedUser");
			
		});
		
		$("#btnClearAssignTo").click(function() {
			
			$("#assignToId").val('');
			$("#assignToName").val('');
		});
		
		$("#btnSearchDept").click(function() {
			
			loadContentIntoModal($("#modal_content_div"), "openModalDialog.htm", "departmentDialog", "modal.header.dept", "selectedDepartment");
			
		});
		
		$("#btnClearDept").click(function() {
			
			$("#ownerDeptCode").val('');
			$("#deptName").val('');
		});
		
		//Insert
		$("#btn_actInsert").click(function() {
			insertActivity();
		});
		
		//Update 
		$("#btn_actUpdate").click(function() {
			 updateActivity();
		});
		
		//Cancel activity detail
		$("#btn_actCancel").click(function(e) {
			
			e.preventDefault();
			clearValidate(txtValidates);
			
			 var totalRecord = actListDT.fnSettings().fnRecordsTotal(); 
			 console.log("totalRecord : "+totalRecord);
			 
			 var modeAct = $('#modeAct').val();
			 
			 if( modeAct == "insert"){
				 initActivityforInsert();
			 }else{
				 if(totalRecord > 0){ 
						resetActData();
				 }else{
					$("#actForm").clearForm();
					$("#actForm input").val("");
				 } 					 
			 }
			 
			
				
		  /* if(totalRecord > 0){ 
				resetActData();
			 }else{
				$("#actForm").clearForm();
				$("#actForm input").val("");
			 }*/
		});
		
		 
		
		$("#btn_phone_no").click(function (e) {
			 
			e.preventDefault();
		});
		
		$("#btn_sms_no").click(function (e) {
			e.preventDefault();
			 
		 });
		 
		 $("#btn_fax_no").click(function (e) {
			 e.preventDefault();
			  
		 });
		
		$("#btn_email_insert").click(function(e) {
			e.preventDefault();
		});
		
		$("#btn_email_update").click(function(e) {
			
			e.preventDefault();
			clickEmail();
		});
		
		
		$("#activityNumber").onEnter( function() {
			 
			if(!empty($("#activityNumber").val())){
				actListDT = activitySearchData();
			}
		});
		
		var mode = $('#modeAct').val();
		toggleButton(mode);
		
		checkModuleInvoke(module, mode, actNo);
});

function createActDatatable(){
	return initajaxDataTable($('#tbl_actList'), columns);
			
}
	
function resetActData(){
	
	setFormScreenOnSelectDatable("actForm",actJsonData);
	setButtomPanelDetail(actJsonData);
	
 
}


function toggleButton(mode) {
	if (mode == 'insert') {
		$("#insertDiv").show();
		$("#updateDiv").hide();
		$("#insertEmailDiv").show();
		$("#updateEmailDiv").hide();
		
		 
		
	} else {
		$("#insertDiv").hide();
		$("#updateDiv").show();
		$("#insertEmailDiv").hide();
		$("#updateEmailDiv").show();
	 
	}
}
	
//Activity detail from other mudule
function checkModuleInvoke(module, mode, actNo){
	
	if(!empty(module)){
		if(module == "mytask"){
			$('#activityNumber').val(actNo);
			activitySearchData();
		}
	
	}else{
		if(mode == "update" && !empty(actNo)){
			actListDT = activitySearchData();
		}
	}
}

function declareActivityColumn(){
	
	columns = [/* {
				"sTitle" : '<spring:message code="activity.grdAction"/>',
				"mData" : null,
				"sWidth" : "5%",
					"fnRender" : function(objData) {
						var actNumber = objData.aData.actNumber;
						var returnButton = "<center><i class='fa fa-pencil'></i></center>";
						//var returnButton = "<center><a href='javascript:editActivity(\""+actNumber+"\");'><i class='fa fa-pencil'></i></a></center>";
						return returnButton;
					}
				}, */{
				"sTitle" : '<spring:message code="activity.grdNo"/>',"mData" : "actNumber", "sClass": 'text-center', "sWidth": "3%"}, {
				/* "sTitle" : '<spring:message code="activity.grdActivityNo"/>',"mData" : "actNumber", "sClass": 'text-left', "sWidth": "10%" }, { */
				"sTitle" : '<spring:message code="activity.grdActGroup"/>',"mData" : "activityGroup", "sClass": 'text-left', "sWidth": "5%" }, {
				"sTitle" : '<spring:message code="activity.grdTitle"/>',"mData" : "title"}, {
				"sTitle" : '<spring:message code="activity.grdType"/>',"mData" : "activityTypeName"}, {
				"sTitle" : '<spring:message code="activity.grdStatus"/>',"mData" : "activityStatusName"}, {
				"sTitle" : '<spring:message code="activity.grdOpenedDate"/>',"mData" : "openedDt", "sClass": 'text-center', "sWidth": "10%"}, {
				"sTitle" : '<spring:message code="activity.grdOperDate"/>',"mData" : "operDt", "sClass": 'text-center', "sWidth": "10%"}, {
				"sTitle" : '<spring:message code="activity.grdOwnerName"/>',"mData" : "ownerName", "sWidth": "13%"}];
}

 
 

function hideBtn(statusBtn){
	
	if(statusBtn == "N"){
		$('#btnQuickSearch').hide();
	} 
}

function maskInput(){
	
	$("#phoneNo").inputmask("99-999-9999");
	$("#smsNo").inputmask("99-999-9999");
	$("#faxNo").inputmask("99-999-9999");
}

function activitySearchData(){
	var dataString = $("#ActivityCriteriaMain").serialize();
	var compArr = [];
	$('#div_table_list').show();
	firstTime = true;
	return ajaxDataTableSetCallback($('#tbl_actList'), columns, 'getActivityList.htm', dataString, recordPerPage, true, true, compArr, firstTime);
}
 
//for set buttom panel detail
function setButtomPanelDetail(data){
	
	$('#createBy').text(data.regName);
	$('#createDate').text(data.regDatetime);
	$('#updateBy').text(data.chgName);
	$('#updateDate').text(data.chgDatetime);
}

//Clear criteria data
function resetForm() {
	
	$("#ActivityCriteriaMain").clearForm();
	$("#ActivityCriteriaMain input").val("");
}

//Btn Advance search
function showAdvSearch(){
	
	$('#divAdvSearch').show();
	$('#btnAdvSearch').hide();
	$('#btnQuickSearch').show();
}

//Btn Quick search
function hideAdvSearch() {
	
	$('#divAdvSearch').hide();
	$('#btnAdvSearch').show();
	$('#btnQuickSearch').hide();
}

//Create Activity
function initActivityforInsert() {
	
	clearValidate(txtValidates);
	$('#actForm').clearForm();
	$('#modeAct').val('insert');
	
	$('#createBy').text("");
	$('#createDate').text("");
	$('#updateBy').text("");
	$('#updateDate').text("");
	
	var defaultStatus = '01';
	$("#statusCd").select2("val",defaultStatus);
	
	var status = "0";
	checkDisableActivityByStatusCd(status,"");
	
	statusBtn = "N";
	hideBtn(statusBtn);
	
	var mode = $('#modeAct').val();
	toggleButton(mode);
	
	$('#attendTo').focus();
}
 

function selectedUser(jsonData) {
	if (fromBtn == 'assignTo') {
		$('#assignToId').val(jsonData.userId);
		$('#assignToName').val(jsonData.firstName + ' ' + jsonData.lastName);
		$('#ownerDeptCode').val(jsonData.deptCode);
		$('#deptName').val(jsonData.deptName);
		
	} else if (fromBtn == 'owner') {
		$('#ownerId').val(jsonData.userId);
		$('#ownerName').val(jsonData.firstName + ' ' + jsonData.lastName);
	}
	
	$("#userDialog").modal("hide");
}

function selectedDepartment(jsonData) {
	
	$('#ownerDeptCode').val(jsonData.deptCode);
	$('#deptName').val(jsonData.deptName);
	
	$('#assignToId').val('');
	$('#assignToName').val('');
	
	$("#userDialog").modal("hide");
}


//insertAndUpdateActivity
function insertAndUpdateActivity(){
	
	var modeActTemp = $("#modeAct").val();
	
	if(modeActTemp  === "insert"){
		insertActivity();
		
	}else if(modeActTemp  === "update"){
		updateActivity();
	}
}

function insertActivity() {
	console.log('Insert activity');
	var modeActTemp = $("#modeAct").val();
	
	//insert
	$("#actForm").attr("action", "insertAct.htm");
		
	saveModeClickTemp = modeActTemp;
	
	var $valid = validateForm($("#actForm"));
	if (!$valid) {
		return false;
	}
	
	ajaxSubmitForm($("#actForm"), function(data){
		actJsonData = data;
		alertMessage("<h6>"+activityTitle+"</h6>","<h5>"+data.resultMessage+"</h5>");
		
		if(data.resultCode == "0"){
			
 	 		actListDT = activitySearchData(); 	 			
			
			setFormScreenByModelBean("actForm", actJsonData);
			
			var currentwnerId = actJsonData.model.ownerId;
			
			var statusCd = actJsonData.model.statusCd;
			actStatusTemp = statusCd;
			
			checkDisableActivityByStatusCd(statusCd,currentwnerId);
			
			// don't delete below here
			setButtomPanelDetail(actJsonData.model);
		
		}else{
			return false;
		}
	});
}

function updateActivity() {
	console.log('Update activity');
	var modeActTemp = $("#modeAct").val();
	
	//update
	$("#actForm").attr("action", "updateAct.htm");
	
	saveModeClickTemp = modeActTemp;
	
	var $valid = validateForm($("#actForm"));
	if (!$valid) {
		return false;
	}
	
	ajaxSubmitForm($("#actForm"), function(data){
		actJsonData = data;
		alertMessage("<h6>"+activityTitle+"</h6>","<h5>"+data.resultMessage+"</h5>");
		
		if(data.resultCode == "0"){
			
 	 		setPageChangeCurrent(actListDT);
			
			setFormScreenByModelBean("actForm", actJsonData);
			
			var currentwnerId = actJsonData.model.ownerId;
			var statusCd = actJsonData.model.statusCd;
			actStatusTemp = statusCd;
			checkDisableActivityByStatusCd(statusCd,currentwnerId);
			
			//don't delete below here
			setButtomPanelDetail(actJsonData.model);
		
		}else{
			return false;
		}
	});
}
	
//Edit grid of activity
function editActivity(actNumber){
	
	$('#modeAct').val('update');
     	var aPos = actListDT.fnGetPosition(this);
     	
     	if(aPos == null){
		return false;    		
   	}
     	
     	var aData = actListDT.fnGetData(aPos[0]);
     	
     	var actNoRefDocNo = aData[aPos].actNumber; 
     	actNumberTemp = actNoRefDocNo;
	
	statusBtn = "Y";
	hideBtn(statusBtn);
	
	
	setFormScreenOnSelectDatable("actForm",aData[aPos]);
    setButtomPanelDetail(aData[aPos]);
}

function checkDisableActivityByStatusCd(statusCd,currentOwnerId){
	if(!empty(statusCd)){
		 var compArrEnable = ["actNumber","phoneNo","statusCd","attendTo","btn_phone_no","btn_sms_no"
		                      ,"smsNo","assignToId","assignToName","btnAssignTo","btnClearAssignTo"
		                      ,"title","faxNo","ownerDeptCode","deptName","btnSearchDept","btn_calendarOperDt"
		                      ,"btnClearDept","typeCd","email","btn_email_insert","btn_email_update","btn_fax_no","btn_calendarDueDt"
		                      ,"dueDt","dueTime","description","address","operDt","operTime","btn_calendarClosedDt"
		                      ,"btn_actInsert", "btn_actUpdate","btn_actCancel"];
		
			//alert($('#teamIds').val());
		 
		if(statusCd == actStatusCompleted){   //03   Disabled
			setComponentDisableById(compArrEnable,true);		//Disabled	
		}else if(statusCd == "0"){
			setComponentDisableById(compArrEnable,false);
		
		}else{
			setComponentDisableById(compArrEnable,false);
		}
	}
}
</script>