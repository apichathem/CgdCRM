
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="locus" uri="/WEB-INF/tld/locus.tld"%>
<spring:message code="message.please.enter" var="msgPleaseActEnter"/>
<spring:message code="sr.actAttendTo" var="msgActAttendTo"/>
<spring:message code="sr.actTitle" var="msgActTitle" />
<spring:message code="sr.actType" var="msgActType"/>
<spring:message code="sr.actDetail" var="msgActDetail"/>
<spring:message code="sr.actStatus" var="msgActStatus"/>
<spring:message code="sr.actAssignTo" var="msgActAssignTo"/>


<!-- Begin Put Activity Detail -->
<div class="row">
	<div class="col-md-12">
		
			<!-- Begin Grid Activity List -->
			<div class="row">
				<div class="col-md-12">
						<div role="grid" class="dataTables_wrapper" id="grid_act_wrapper">
							<div class="table-scrollunable">
								<table  id="grid_act_result_list" class="table table-bordered">
								</table>
							</div>
						</div>		
				</div>
				
			</div>
			<!-- End Grid Activity List -->
	
	<form:form action="updateSrActivity.htm" name="actForm" id="actForm" modelAttribute="srActModelBean" class="form-horizontal">	
			<form:hidden path="refDocNo"/>
			<form:hidden id="modeAct" path="mode"/>
			<!-- Row1 Activity-->	
			<div class="row">
				<div class="col-md-4">
					<div class="form-group">
						<label class="control-label col-md-5">
							<spring:message code="sr.actNumber"/>
						</label>
						<div class="col-md-7">
							<form:input type="text" id="txtActNumber" name="actNumber" path="actNumber" class="form-control" readonly="true" /> 
						</div>
					</div>
				</div>
				
				<div class="col-md-4">
					<div class="form-group">
						<label class="control-label col-md-5">
							<spring:message code="sr.actPhoneNo"/>
						</label>
						<div class="col-md-7">
							 <div class="input-group">															
								<form:input type="tel" id="txtPhoneNo" name="phoneNo" path ="phoneNo" class="form-control maskmobile"  />
								<span class="input-group-btn">
									<button type="button" id="btn_phone_no" class="btn">
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
							<spring:message code="sr.actStatus"/><span class="required">*</span>							
						</label>
						<div class="col-md-7">
							<form:select id="cmbStatusCd" name="statusCd" path="statusCd" class="form-control select2me"  data-rule-required="true" data-msg-required='${msgPleaseActEnter}&nbsp;${msgActStatus}.'>
							<option></option>
								<form:options items='${CODEBOOK_LIST.ACT_STATUS}' itemLabel="codeName" itemValue="codeId" />
							</form:select>
						</div>
					</div>
				</div>
			</div> <!-- End Row1 -->
			 								
			<!-- Row2 Activity-->	
			<div class="row">
				<div class="col-md-4">
					<div class="form-group">
						<label class="control-label col-md-5">
							<spring:message code="sr.actAttendTo"/><span class="required">*</span>
						</label>
						<div class="col-md-7">
							<form:input type="text" id="txtActAttendTo" path="attendTo" class="form-control"  maxlength="50" data-rule-required="true" data-msg-required='${msgPleaseActEnter}&nbsp;${msgActAttendTo}.'/> 
						</div>
					</div>
				</div>
				
				<div class="col-md-4">
					<div class="form-group">
						<label class="control-label col-md-5">
							<spring:message code="sr.actSmsNo"/>
						</label>
						<div class="col-md-7">
							 <div class="input-group">															
								<form:input type="tel" id="txtActSmsNo" name="smsNo" path="smsNo" class="form-control maskmobile"/>
								<span class="input-group-btn">												
									<button type="button" id="btn_sms_no" class="btn">
										<i class="fa fa-phone-square" id="select_sms_no"></i>
									</button>
								 </span> 
								
							</div>
						</div>
					</div>
				</div>
				
				<div class="col-md-4">
					<div class="form-group">
						<label class="control-label col-md-5">
							<spring:message code="sr.actAssignTo"/><span class="required">*</span>
						</label>
						<div class="col-md-7">
							<div class="input-group">															
								<form:input type="text" id="txtActAssignToName" name="ownerName" path="ownerName" class="form-control" readonly="true" data-rule-required="true" data-msg-required='${msgPleaseActEnter}&nbsp;${msgActAssignTo}.' />
								<form:input type='hidden' id="txtActAssignToId" name="ownerId" path="ownerId" class="form-control" />
								<span class="input-group-btn">												
									<button type="button" id="btnAssignTo" class="btn" data-target="#actAssignToDialog" data-toggle="modal">
										<i class="fa fa-user" id="select_assignTo"></i>
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
			</div> <!-- End Row2 -->
			<!--<span>&nbsp;</span>-->
			
			<!-- Row3 Activity-->	
			<div class="row">
				<div class="col-md-4">
					<div class="form-group">
						<label class="control-label col-md-5">
							<spring:message code="sr.actTitle"/><span class="required">*</span>
						</label>
						<div class="col-md-7">
							 <form:input type="text" id="txtActTitle" name="title" path="title" maxlength="100" class="form-control"  data-rule-required="true" data-msg-required='${msgPleaseActEnter}&nbsp;${msgActTitle}.'/>
						</div>
					</div>
				</div>
				
				<div class="col-md-4">
					<div class="form-group">
						<label class="control-label col-md-5">
							<spring:message code="sr.actFaxNumber"/>
						</label>
						<div class="col-md-7">
							<div class="input-group">															
								<form:input type="tel" id="txtActFaxNo" name="faxNo" path="faxNo" class="form-control maskmobile" />
								<span class="input-group-btn">
									<button type="button" id="btn_fax_no" class="btn">
										<i class="fa fa-phone-square" id="select_fax_no"></i>
									</button>
								</span>
								
							</div>
						</div>
					</div>
				</div>
				
				<div class="col-md-4">
					<div class="form-group">
						<label class="control-label col-md-5">
							<spring:message code="sr.actDueDate"/>
						</label>
						<div class="col-md-7">
							<div class="input-group">
								<form:input type="text" id="txtActDueDate" name="dueDate" path="dueDate" class="form-control date-picker maskdate" data-date-format="dd-mm-yyyy" data-date-start-date="+0d"/>
								<span class="input-group-btn">
									<button class="btn default" type="button"><i class="fa fa-calendar"></i></button>
								</span>
								<form:input type="text" id="txtActDueDatetime" name="dueDateTime" path="dueDateTime"  class="form-control masktime"/>
							</div>
						</div>
					</div>
					
				</div>
			</div><!-- End Row3 -->											
		<!--<span>&nbsp;</span>-->
		
			<!-- Row4 Activity-->	
			<div class="row">
				<div class="col-md-4">
					<div class="form-group">
						<label class="control-label col-md-5">
							<spring:message code="sr.actType"/><span class="required">*</span>
						</label>
						<div class="col-md-7">
							<form:select id="cmbActType" name="typeCd" path="typeCd" class="form-control select2me"  data-rule-required="true" data-msg-required='${msgPleaseActEnter}&nbsp;${msgActType}.'>
								<option></option>
								<form:options items='${CODEBOOK_LIST.ACT_TYPE}' itemLabel="codeName" itemValue="codeId" />
							</form:select>
						</div>
					</div>
				</div>
				
				<div class="col-md-4">
					<div class="form-group">
						<label class="control-label col-md-5">
							<spring:message code="sr.actEmail"/>
						</label>
						<div class="col-md-7">
							<div class="input-group">															
								<form:input type="text" id="txtActEmail" name="email" path="email" class="form-control" data-rule-email="true" />
								<span class="input-group-btn">	
									<button type="button" id="btn_email" class="btn">
										<i class="fa fa-envelope" id="select_fax_no"></i>
									</button>
								</span>
							</div>
							
						</div>
					</div>
				</div>
				
				<div class="col-md-4">
					<div class="form-group">
							<label class="control-label col-md-5">
								<spring:message code="sr.actOperDate"/>
							</label>
							<div class="col-md-7">
								<div class="input-group">
									<form:input type="text" id="txtActOperDate" name="operDate" path="operDate" class="form-control date-picker maskdate" data-date-format="dd-mm-yyyy" data-date-start-date="+0d"/>
									<span class="input-group-btn">
										<button class="btn default" type="button"><i class="fa fa-calendar"></i></button>
									</span>
										<form:input type="text" id="txtActOperDateTime" name="operDateTime" path="operDateTime"  class="form-control masktime"  />
								</div>
							</div>
					</div>
				</div>
			</div><!-- End Row4 -->
			<!--<span>&nbsp;</span>-->
				
			<!-- Row5 Activity-->	
			<div class="row">
				<div class="col-md-4">
					<div class="form-group">
						<label class="control-label col-md-5">
							<spring:message code="sr.actDetail"/><span class="required">*</span>
						</label>
						<div class="col-md-7">
							<form:textarea id='txtActDetail' name='description' path="description" class="form-control" rows="3" maxlength="500"  data-rule-required="true" data-msg-required='${msgPleaseActEnter}&nbsp;${msgActDetail}.'></form:textarea> 
						</div>
					</div>
				</div>
				
				<div class="col-md-4">
						<div class="form-group">
							<label class="control-label col-md-5">
								<spring:message code="sr.actAddress"/>
							</label>
							<div class="col-md-7">
								<form:textarea id='txtActAddress' name='address' path="address" class="form-control" rows="3" maxlength="500" ></form:textarea>
							</div>
						</div>
					 
				</div>
				
				<div class="col-md-4">
					<%-- <div class="form-group">
							<label class="control-label col-md-5">
								<spring:message code="sr.actOperDate"/>
							</label>
							<div class="col-md-7">
								<div class="input-group">
									<form:input type="text" id="txtActOperDate" name="operDate" path="operDate" class="form-control date-picker maskdate" data-date-format="dd-mm-yyyy" data-date-start-date="+0d"/>
									<span class="input-group-btn">
										<button class="btn default" type="button"><i class="fa fa-calendar"></i></button>
									</span>
										<form:input type="text" id="txtActOperDateTime" name="operDateTime" path="operDateTime"  class="form-control masktime"  />
								</div>
							</div>
					</div> --%>
				</div>
				
				<div class="col-md-4">
					<div class="form-group">
							<label class="control-label col-md-5">
								<spring:message code="sr.actClosedDate"/>
							</label>
							<div class="col-md-7">
								<div class="input-group">
									<form:input type="text" id="txtActClosedDate" name="closedDate" path="closedDate" class="form-control date-picker maskdate" data-date-format="dd-mm-yyyy" data-date-start-date="+0d" disabled="true"/>
									<span class="input-group-btn">
										<button class="btn default" type="button"><i class="fa fa-calendar"></i></button>
									</span>
										<form:input type="text" id="txtActClosedDate" name="closedDateTime" path="closedDateTime"  class="form-control masktime" disabled="true" />
								</div>
							</div>
					</div>
				</div>
			</div><!-- End Row6 -->
		</form:form>

		
		
	</div>
</div>	

	<hr/>
	<div class="row">
		<div class="col-md-4">
			<div class="form-group">
				<label class="control-label col-md-5"> <spring:message code="lbl.createBy" />
				</label>
				<div class="col-md-7">
					<p id="actCreateBy" class="form-control-static"></p>
				</div>
			</div>
		</div>
		<div class="col-md-4">
			<div class="form-group">
				<label class="control-label col-md-5"> <spring:message code="lbl.createDate" />
				</label>
				<div class="col-md-7">
					<p id="actCreateDate" class="form-control-static"></p>
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
					<p id="actUpdateBy" class="form-control-static"></p>
				</div>
			</div>
		</div>
		<div class="col-md-4">
			<div class="form-group">
				<label class="control-label col-md-5"> <spring:message code="lbl.updateDate" />
				</label>
				<div class="col-md-7">
					<p id="actUpdateDate" class="form-control-static"></p>
				</div>
			</div>
		</div>
		<div class="col-md-4">
			<div class="form-group"></div>
		</div>
	</div>
	<!-- End Detail  -->


<!-- Begin Panel Button Activity -->
<div class="form-actions right">
	<div class="row">
		<div class="col-md-12">
			<div class="col-md-offset-3 col-md-9">
			<locus:privilege oper="ADD">
				<div class="btn-group">
					<button type="button" class="btn green" id="btn_actNew" name="btn_actNew">
						<i class="fa fa-plus"></i>
						<spring:message code="button.create.label"/>
					</button>
				</div>
			</locus:privilege>	
			<locus:privilege oper="EDIT">	
				<div class="btn-group"> 
					<button type="button" class="btn blue" id="btn_actSave" name="btn_actSave">
						<i class="fa fa-floppy-o"></i>											
						<spring:message code="button.save.label"/>
					</button>
				</div>
			</locus:privilege>	
				<div class="btn-group">	
					<button type="button" class="btn default" id="btn_actCancel" name="btn_actCancel">
						<spring:message code="button.cancel.label"/>
					</button>
				</div>
				
			</div>
		</div>
		
	</div>
</div>

<!-- End Panel Button Activity -->
<div class="modal fade" id="actAssignToDialog" role="basic" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div id="modal_content_div_assign_to"></div>
		</div>
	</div>
</div>

<div class="modal fade" id="actDeptDialog" role="basic" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div id="modal_content_div_dept"></div>
		</div>
	</div>
</div>

<%-- ${SR_NUMBER_KEY} --%>
<script type="text/javascript">
var recordPerPageSR = '<spring:message code="datatable.recordPerPage" />';
//var recordPerPageSR = '5';
var serviceRequestTitle = '<spring:message code="serviceRequest.title" />';
var AJAX_TIMEOUT = '1000000';
var mainFunctionPage = "selectedActOwnerUser";

var msgTitleAct = '<b><spring:message code="sr.srDetailMoreInfoCaption" />  :  <spring:message code="menu.activity" /></b>';
var msgDuplicateAct = '<spring:message code="sr.attDuplicateSelect" />';

var msgPleaseEnterEmail='<spring:message code="message.please.enter.email" />';
var msgInValidEmail ='<spring:message code="message.please.provide.email"/>'; 
var msgInValidPhoneNo ='<spring:message code="message.please.provide.phone"/>'; 
var msgInValidSmsNo ='<spring:message code="message.please.provide.sms"/>';
var msgInValidFaxNo='<spring:message code="message.please.provide.fax"/>';

var columsActList = [];
var actListDT;

var actJsonData=[];
var actNumberTemp="";
var totalAttRecord = 0;

var firstTime = true;



var actStatusPocessing = "02";	//Processing
var actStatusCompleted = "03";	//Completed
var actStatusCanceled = "04";	//Canceled

var actStatusTemp ="";
var refStatusCdTemp=""; 

var saveModeClickTemp = "insert"; 

var txtValidatesSrAct = ["txtActAttendTo","txtActTitle","cmbActType","txtActDetail"];

$(document).ready(function() { 
	
	validateForm($("#actForm"));
	
	declareActListColumn();
	
	var refDocNo  = '<c:out value="${srActModelBean.refDocNo}"/>';	
	var refStatusCd  = '<c:out value="${srActModelBean.refStatusCd}"/>';
	refStatusCdTemp = refStatusCd;
	actListDT = searchActivityListByReferenceNo(refDocNo,firstTime,refStatusCd);
	
	 
	
 	//DisplayTooltips
 	dataTableOnMouseoverTooltips("grid_act_result_list");
 	
 		
 	$("#grid_act_result_list tbody tr").die( "click" ); 	
 	$('#grid_act_result_list tbody tr').live('click touchstart', function () {
 		
 			clearValidate(txtValidatesSrAct);
 			
 			$("#modeAct").val("update");
 			firstTime = false;
 			//console.log(JSON.stringify(this)); 			
 	 		//alert(actListDT.fnSettings().fnRecordsTotal() );
 	 		//alert(actListDT.fnSettings().fnRecordsDisplay() );
 	 		//alert(actListDT.fnSettings().fnRecordsTotal() );
 	 		
 			var aPos = actListDT.fnGetPosition(this);
 	     	
 			if(empty(aPos)){
 				return false;    		
 	    	}
 			
 			
 	        var aData = actListDT.fnGetData(aPos[0]);
 	      	
 	       actNumberTemp= aData[aPos].actNumber; 
 	       	       
 	      	
 	      	setButtomPanelDetail(aData[aPos]);
			//console.log(JSON.stringify(aData[aPos]));
			
			actJsonData = aData[aPos]; // Setting temporary
			
 	      	setFormScreenOnSelectDatable("actForm",aData[aPos]);
			
 	  	 	// check disable component by activity statusCd
 	  	 	var statusCd = aData[aPos].statusCd;
 	  	 	actStatusTemp = statusCd;
 	  	 	
 	  	 	checkDisableActivityByStatusCd(statusCd);
 	  	  	
 	});
 	
 		
 	// Begin Dialog
 	
 		$("#btnAssignTo").click(function(e) {
 			e.preventDefault();
			loadContentIntoModal($("#modal_content_div_assign_to"), "openModalDialog.htm", "userDialog", "modal.header.user","selectedActOwnerUser");
		});
		
		$("#btnClearAssignTo").click(function(e) {
			e.preventDefault();
			$('#txtActAssignToId').val("");
			$('#txtActAssignToName').val("");
		});
		
		$("#btnActDept").click(function(e) {
			e.preventDefault();
			loadContentIntoModal($("#modal_content_div_dept"), "openModalDialog.htm", "departmentDialog", "modal.header.dept","selectedActDepartment");
			
		});
		
		$("#btnClearDept").click(function(e) {
			e.preventDefault();
			$("#txtActDept").val("");
			$("#txtActDeptCd").val("");
		});
	 
		
 	// End Dialog
 	
	 	$("#btn_actNew").click(function(e) {
	 		e.preventDefault();
	 		prepareScreenCreateAct();
	 		
	 	});
 	
 	
	  $("#btn_actSave").click(function(e) {		
		
		 e.preventDefault();
		 var $valid = $("#actForm").valid();
	 
		 if(!$valid){
			return false;
		 }
		 
		 saveActivityInfo();
			 
		});
	  
	 $("#btn_actCancel").click(function(e) {
		 
		 clearValidate(txtValidatesSrAct);
		 
 		 e.preventDefault();
 		 
 		 var totalRecord = actListDT.fnSettings().fnRecordsTotal(); 
 		 console.log("totalRecord : "+totalRecord);
 		 
 		 if(totalRecord > 0){ 			 
 			 // reset panel
 			 //actListDT.$('tr:first').click();
 			 
 			resetSrActData();
 			 
			/*
			if(totalRecord == 1){}else{}
			*/
 			
 		 }else{
 				
 			 $("#actForm").clearForm();

 			 var compArrEnable = ["txtActAttendTo","txtActTitle","cmbActType","txtActDetail"
 			                      ,"txtPhoneNo","txtActSmsNo","txtActFaxNo","txtActEmail","txtActAddress"
 			                      ,"cmbStatusCd","btnAssignTo","btnClearAssignTo","btnActDept","btnClearDept"
 			                      ,"txtActDueDate","txtActDueDatetime","txtActOperDate","txtActOperDateTime"
 			                      ,"btn_sms_no","btn_fax_no","btn_email","btn_actSave"];

 			 setComponentDisableById(compArrEnable,true); 
 			
 		 }
 		 
 		$("#modeAct").val("update");
 		
 		// reset button new 
 		 var compArrAct= ["btn_actNew"];	 
 		 setComponentDisableById(compArrAct,false);
 		
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
	 
	 
	 $("#btn_email").click(function(e) {
		e.preventDefault();
	 });
	
});


function resetSrActData(){
	
	setFormScreenOnSelectDatable("actForm",actJsonData);
	setButtomPanelDetail(actJsonData);
}



function checkDisableActivityByStatusCd(statusCd){
	var compArrEnable = ["txtActAttendTo","txtActTitle","cmbActType","txtActDetail"
	                      ,"txtPhoneNo","txtActSmsNo","txtActFaxNo","txtActEmail","txtActAddress"
	                      ,"cmbStatusCd","btnAssignTo","btnClearAssignTo","btnActDept","btnClearDept"
	                      ,"txtActDueDate","txtActDueDatetime","txtActOperDate","txtActOperDateTime","btn_phone_no"
	                      ,"btn_sms_no","btn_fax_no","btn_email","btn_actCancel","btn_actSave"];
	 var compArrAct= ["btn_actNew"];	
	
	if(!empty(refStatusCd)){
		//Completed  , Cancel
		if(refStatusCd == "03"){ //|| refStatusCd == "04"
			setComponentDisableById(compArrEnable,true);
			 setComponentDisableById(compArrAct,true);
			
		}else{
			
			if(!empty(statusCd)){
				
				// Activity Status				
				if(statusCd == actStatusCompleted || statusCd == actStatusCanceled){ 	// 03 Completed , 04 Cancel 
					
					setComponentDisableById(compArrEnable,true);
				
				}else{
					setComponentDisableById(compArrEnable,false); 
				}
				
				 var compArrAct= ["btn_actNew"];	 
				 setComponentDisableById(compArrAct,false);
			}
			
		}
	}
	
	
	
	
}
 
 

 
 
function setButtomPanelDetail(jsData){
	
	var regName = jsData.regName;
   	var chgName = jsData.chgName;
   	var regDatetime = jsData.regDatetime;
   	var chgDatetime = jsData.chgDatetime;
   	
	$("#actCreateBy").text(regName);
	$("#actCreateDate").text(regDatetime);
	$("#actUpdateBy").text(chgName);
	$("#actUpdateDate").text(chgDatetime);
	
	
}


function selectedActOwnerUser(jsonData) {
	
	$('#txtActAssignToId').val(jsonData.userId);
	$('#txtActAssignToName').val(jsonData.firstName + ' ' + jsonData.lastName);
	$('#txtActDeptCd').val(jsonData.deptCode);
	$('#txtActDept').val(jsonData.deptName);
	
	$("#actAssignToDialog").modal("hide");
	
}

function selectedActDepartment(jsonData) {
	
	$('#txtActDeptCd').val(jsonData.deptCode);
	$('#txtActDept').val(jsonData.deptName);
	
	$('#txtActAssignToId').val("");
	$('#txtActAssignToName').val("");
	
	$("#actDeptDialog").modal("hide");
	
}

 
function saveActivityInfo(){
	
	var MODE_ACT_IU = $("#modeAct").val();
	
	if(MODE_ACT_IU === "insert"){
		
		$("#actForm").attr("action", "insertSrActivity.htm");
		 
	}else if(MODE_ACT_IU === "update"){	
		
		$("#actForm").attr("action", "updateSrActivity.htm");
	}
	
	saveModeClickTemp = MODE_ACT_IU;
	
	ajaxSubmitForm($("#actForm"), function(data){
		
		// reset button new 
		 var compArrAct= ["btn_actNew"];	 
		 setComponentDisableById(compArrAct,false);
		 		
		 actJsonData = data;		
		 
		 alertMessage("<h5>"+msgTitleAct+"</h5>","<h6>"+data.resultMessage+"</h6>");		 
		 
		if(actJsonData.resultCode == "0"){
			
			var srRefDocNo  = actJsonData.model.refDocNo;
 	 		 
 	 		if(saveModeClickTemp === "insert"){ 	 			
 	 			actListDT = searchActivityListByReferenceNo(srRefDocNo,firstTime,refStatusCdTemp); 	 			
 	 		}else{
 	 			
 	 			setPageChangeCurrent(actListDT);
 	 			
 	 		}
 	 		
			// set data in to component
		 	setFormScreenByModelBean("actForm", actJsonData);
			 
			var regName = actJsonData.model.regName;
 	      	var chgName = actJsonData.model.chgName;
 	      	var regDatetime = actJsonData.model.regDatetime;
 	      	var chgDatetime = actJsonData.model.chgDatetime;
 	      	$("#actCreateBy").text(regName);
			$("#actCreateDate").text(regDatetime);
			$("#actUpdateBy").text(chgName);
			$("#actUpdateDate").text(chgDatetime);
			
			// check disable component by activity statusCd
 	  	 	var statusCd = actJsonData.model.statusCd;
 	  	 	actStatusTemp = statusCd;
 	  	 	checkDisableActivityByStatusCd(statusCd); 
 	  	 	
		}else{
			
			return false;
		}
				
				
	});
	
}


function prepareScreenCreateAct(){
	
	firstTime = true;
	$('#actForm').clearForm();
	setMaskComponent();
	$("#actCreateBy").text("");
	$("#actCreateDate").text("");
	$("#actUpdateBy").text("");
	$("#actUpdateDate").text("");
	
	
	var compArr = ["btn_actSave","btn_actCancel"];
	setComponentDisableById(compArr,false); 
	
	$("#modeAct").val("insert");   
	 
	 var compArrAtt = ["btn_actNew"];
	 
	 setComponentDisableById(compArrAtt,true); 
	 
	 var compArrEnable = ["txtActAttendTo","txtActTitle","cmbActType","txtActDetail"
	                      ,"txtPhoneNo","txtActSmsNo","txtActFaxNo","txtActEmail","txtActAddress"
	                      ,"cmbStatusCd","btnAssignTo","btnClearAssignTo","btnActDept","btnClearDept"
	                      ,"txtActDueDate","txtActDueDatetime","txtActOperDate","txtActOperDateTime","btn_phone_no"
	                      ,"btn_sms_no","btn_fax_no","btn_email"];
	setComponentDisableById(compArrEnable,false); 
	 
 	var assOwnerName ='<c:out value="${USER_PROFILE.firstName} ${USER_PROFILE.lastName}"/>';
	var assOwnerId ='<c:out value="${USER_PROFILE.userId}"/>';
	
	$("#txtActAssignToName").val(assOwnerName);
	$("#txtActAssignToId").val(assOwnerId);
	
	var departmentCode = '<c:out value="${USER_PROFILE.departmentCd}"/>';
	var departmentName = '<c:out value="${USER_PROFILE.departmentName}"/>';
	
	$("#txtActDept").val(departmentName);
	$("#txtActDeptCd").val(departmentCode);
	
	var cmbStatusCdVal = "01";
	$("#cmbStatusCd").select2("val",cmbStatusCdVal);
}

 

 

function searchActivityListByReferenceNo(referenceNo,firstTime,refStatusCd){
	
	var referenceNoParam = "referenceNo="+referenceNo;
	
	var compArr = ["btn_actSave","btn_actCancel"
               ,"txtActAttendTo","txtActTitle","cmbActType","txtActDetail"
               ,"txtPhoneNo","txtActSmsNo","txtActFaxNo","txtActEmail","txtActAddress"
               ,"cmbStatusCd","btnAssignTo","btnClearAssignTo","btnActDept"
               ,"btnClearDept","txtActDueDate","txtActDueDatetime","txtActOperDate","txtActOperDateTime","btn_phone_no"
               ,"btn_sms_no","btn_fax_no","btn_email"];
	
	if(!empty(referenceNo)){
		
		if(!empty(refStatusCd)){
			
			/**
					SRQ_STATUS	03	Completed ,
					SRQ_STATUS	04	Cancel
			**/
			if(refStatusCd === "03" || refStatusCd === "04"){
			 
				setComponentDisableById(compArr,true);  
				
				var compArrDisable =["btn_actNew","btn_actSave","btn_actCancel"];
				setComponentDisableById(compArrDisable,true); 
				
			}
			
		}
	
	}else{
	               
		var compArrDisable =["btn_actNew"];
		setComponentDisableById(compArrDisable,true);        
		
	}
	
	
	return ajaxDataTableSetCallback($('#grid_act_result_list'), columsActList, 'inquirySrActivityList.htm', referenceNoParam, recordPerPageSR, true, true,compArr,firstTime);	
	
}

 
function declareActListColumn(){
	
	var col_no ='<center><spring:message code="sr.actGrdNo"/></center>';
	var col_actGrdActNumber='<center><spring:message code="sr.actGrdActNumber"/></center>';
	var	col_actGrdTitle='<center><spring:message code="sr.actGrdTitle"/></center>';
	var	col_actGrdType='<center><spring:message code="sr.actGrdType"/></center>';
	var	col_actGrdStatus='<center><spring:message code="sr.actGrdStatus"/></center>';
	var	col_actGrdOpenedDate='<center><spring:message code="sr.actGrdOpenedDate"/></center>';
	var	col_actGrdOperDate='<center><spring:message code="sr.actGrdOperDate"/></center>';
	var	col_actGrdClosedDate='<center><spring:message code="sr.actGrdClosedDate"/></center>';
	var	col_actGrdOwner='<center><spring:message code="sr.actGrdOwner"/></center>';
	var	col_actGrdAction='<center><spring:message code="sr.actGrdAction"/></center>';
	
	columsActList = [
		               { "sTitle": col_no, "mData": "actNumber"  ,"sClass": "tdCenter","sWidth": "3%" }
		   			/*  ,{ "sTitle": col_actGrdActNumber, "mData": "actNumber","sClass": "tdLeft" ,"sWidth": "10%" }*/
		   			  ,{ "sTitle": col_actGrdTitle, "mData": "title","sClass": "tdLeft" ,"sWidth": "10%" }
		   			  ,{ "sTitle": col_actGrdType, "mData": "activityTypeName" ,"sClass": "tdLeft" }
	   				  ,{ "sTitle": col_actGrdStatus, "mData": "activityStatusName" ,"sClass": "tdLeft" }
		   			  ,{ "sTitle": col_actGrdOpenedDate, "mData": "openedDt","sClass": "tdCenter", "sWidth": "10%" }
		   			  ,{ "sTitle": col_actGrdOperDate, "mData": "operDt" ,"sClass": "tdCenter", "sWidth": "10%"}
		   			  ,{ "sTitle": col_actGrdClosedDate, "mData": "closedDt","sClass": "tdCenter", "sWidth": "10%" }
		   			  ,{ "sTitle": col_actGrdOwner, "mData": "ownerName" }
		   			 ];
	
}

</script>
