<%@page import="java.util.Map"%>
<%@page import="com.locus.jlo.web.constant.JLOWebConstant"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="locus" uri="/WEB-INF/tld/locus.tld"%>

<%
	int randNum = (int)(Math.random()*1000000);
%>

<div class="modalIncidentDialog" id="modalIncidentDialog_<%=randNum%>"> 
	<form id="incidentalCriteriaDialog" action="#"  class="form-horizontal" >
		
	<!-- BEGIN CONTENT -->
		
		<div class="row">
			<div class="col-md-12">
					<div class="form-body">
						<div class="row">
						
								<div class="col-md-4">
									<div class="form-group">
										<label class="control-label col-md-4">
											<spring:message code="inc.criIncNumber"/>
										</label>
										<div class="col-md-8">
											<input type="text" id="txt_dia_inc_number" name="incNumber"  class="form-control" placeholder="INC Number" value=""/> 
										</div>
									</div>
								</div>
								
								<div class="col-md-4">
									<div class="form-group">
										<label class="control-label col-md-4">
											<spring:message code="inc.criIncOwner"/>
										</label>
										<div class="col-md-8">
											<div class="input-group">
												<input type="text" id="txt_dia_inc_owner_name" name="incOwnerName"   class="form-control" readonly="readonly"/>
												<input type="hidden" id="txt_dia_inc_owner_id" name="incOwnerId"   class="form-control" />
												<span class="input-group-addon">
													<a id="btnSearchOwnerInc" href="#" data-target="#incUserDialog" data-toggle="modal">
														<i class="fa fa-user" id="select_owner"></i>
													</a>
												</span> 
												<span class="input-group-addon">
													<a id="btnClearOwnerInc" style="cursor: pointer;"> 
														<i class="fa fa-minus-circle red"></i>
													</a>
												</span>
											</div>
										</div>
									</div>
								</div>	
									
								<div class="col-md-4">
									<div class="form-group">
										<label class="control-label col-md-4">
											<spring:message code="inc.criIncOwnerDept"/>
										</label>
										<div class="col-md-8">
																
											<div class="input-group">
												<input type="text" id="txt_dia_inc_owner_dept" name="incOwnerDeptName"  value="" class="form-control" readonly="readonly"/>
												<input type="hidden" id="current_to_dept_cd" name="incOwnerDeptCd"   class="form-control" />
												<span class="input-group-addon">
													<a id="btnSearchDeptInc" href="#" data-target="#incDeptDialog" data-toggle="modal">
														<i class="fa fa-user" id="select_owner"></i>
													</a>	
												</span> 
												<span class="input-group-addon">
													<a id="btnClearDeptInc" style="cursor: pointer;">
														<i class="fa fa-minus-circle red"></i>
													</a>
												</span>
												
											</div>
										</div>
									</div>
								</div>
									
									
		
								<!-- Below Layout AdvanceSearch Panel       -->						
								 	<div class="col-md-4">
										<div class="form-group">
											<label class="control-label col-md-4">
												<spring:message code="inc.criIncType"/>
											</label>
											<div class="col-md-8">
												<select name="incTypeCd" id="cmb_dia_inc_type" class="form-control select2me">
												</select>
											</div>
										</div>
									</div>
								
								
									<div class="col-md-4">
										<div class="form-group">
											<label class="control-label col-md-4">
												<spring:message code="inc.criIncSubType"/>
											</label>
											<div class="col-md-8">
												<select name="incSubTypeCd" id="cmb_dia_inc_subtype"  class="form-control select2me">
													<option></option>
												</select>
											</div>
										</div>
									</div>	
									
									
									<div class="col-md-4">
										<div class="form-group">
											<label class="control-label col-md-4">
												<spring:message code="inc.criIncStatus"/>
											</label>
											<div class="col-md-8">
											 	<select name="incStatusCd"  id="cmb_dia_inc_status"  class="form-control select2me">
													<option></option>
												</select> 
											</div>
										</div>
									</div>
									
									<div class="col-md-4">
										<div class="form-group">
											<label class="control-label col-md-4">
												<spring:message code="inc.criIncChannel"/>
											</label>
											<div class="col-md-8">
												 <select id="cmb_dia_inc_channel" name="incChannelCd"   class="form-control select2me"  data-rule-required="true" data-msg-required='<spring:message code="message.please.enter"/>&nbsp;<spring:message code="sr.srChannel"/>.'>
													<option></option>
												</select> 
											</div>
										</div>
									</div>
									
									<div class="col-md-4">
										<div class="form-group">
											<label class="control-label col-md-4">
												<spring:message code="inc.criIncPriority"/>
											</label>
											<div class="col-md-8">
												 <select id="cmb_dia_inc_priority" name="incPriorityCd"    class="form-control select2me">
													<option></option>
												</select>	 
												
											</div>
										</div>
									</div>
									
									<div class="col-md-4">
										<div class="form-group">
											<label class="control-label col-md-4">
												<spring:message code="inc.criIncCreatedDate"/>
											</label>
											<div class="col-md-8">
												<div class="input-group date-picker input-daterange" data-date-format="dd/mm/yyyy" >
													<input id="create_date_start" type="text" class="form-control" name="create_date_start" placeholder="dd/mm/yyyy" />
														<span class="input-group-addon" > - </span>
													<input id="create_date_end" type="text" class="form-control" name="create_date_end" placeholder="dd/mm/yyyy" />
												</div> 
											</div>
										</div>
									</div> 
									
								
								
					</div>	
				
				</div>	
				
					<div class="form-actions right">
						<button id="btn_searchIncDia" class="btn blue" type="button">
							<i class="fa fa-search"></i>
							<spring:message code="button.search.label"/>
						</button>
						<button id="btn_cancelSearchDia" class="btn default" type="button" >
							<spring:message code="button.cancel.label"/>
						</button>
					</div>
						
				
			</div>
		</div>
			
		<div class="row">
			<div class="col-md-12">	
				<!-- BEGIN EXAMPLE TABLE PORTLET-->
				<div class="portlet-body">
					<table class="table table-bordered dataTable" id="tblDialogIncList">
					</table>
				</div>
			</div>
				<!-- END EXAMPLE TABLE PORTLET-->
		</div>

	</form>
	<!-- DIALOG User Dialog -->
		<div class="modal fade" id="incUserDialog" role="basic" aria-hidden="true">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div id="inc_modal_content_div_user"></div>
				</div>
			</div>
		</div>
		
		<div class="modal fade" id="incDeptDialog" role="basic" aria-hidden="true">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div id="inc_modal_content_div_dept"></div>
				</div>
			</div>
		</div>
		
<!-- BEGIN JAVA SCRIPTS -->
<script type="text/javascript">

var evalFuncCont ="${callbackfn}";
var strParamIncNum ="${strParamIncNum}";

var columnsIncDt=[];
var CONTEXT_PATH = "<%=request.getContextPath()%>";
var AJAX_TIMEOUT = '1000000'; 
var recordPerPage = '<spring:message code="datatable.recordPerPage" />';
var plaseSelectMsg = '<spring:message code="message.please.select" />';

var oTableInc ;
$(document).ready(function() {  
	$(".modalIncidentDialog").not("#modalIncidentDialog_<%=randNum%>").remove();
	
	getINCTypeListCombo(plaseSelectMsg);
	getINCSubTypeListCombo(plaseSelectMsg);
	
	
	getINCChannelListCombo(plaseSelectMsg);
	getINCPriorityListCombo(plaseSelectMsg);
	getINCStatusListCombo(plaseSelectMsg);
	
	 //DisplayTooltips
	dataTableOnMouseoverTooltips("tblDialogIncList");
	
	declareColumn();
	
	oTableInc = createIncDatatable();
	
 	//Owner dialog button
	$("#btnSearchOwnerInc").click(function() {
		loadContentIntoModal($("#inc_modal_content_div_user"), "openModalDialog.htm", "userDialog", "modal.header.user","selectedIncOwnerUserDialog");
	});
 
	$("#btnClearOwnerInc").click(function() {
		$("#txt_dia_inc_owner_id").val("");
		$("#txt_dia_inc_owner_name").val("");
	});
	
	// Department
	$("#btnSearchDeptInc").click(function() {
		loadContentIntoModal($("#inc_modal_content_div_dept"), "openModalDialog.htm", "departmentDialog", "modal.header.dept","selectedIncDepartmentDialog");
	});
	
	$("#btnClearDeptInc").click(function() {
		$("#txt_dia_inc_owner_dept").val("");
		$("#current_to_dept_cd").val("");
	});
	
	
	/* End Dialog */	
    
    
	$("#btn_searchIncDia").click(function() {
		oTableInc = incidentalSearchData();
	}); 
    
    $("#btn_cancelSearchDia").click(function() {
    	clearIncCriteriaSearch();
	});
    

	$("#cmb_dia_inc_type").change(function(){		
		getCodeBookByParentTypeAndParentId($("#cmb_dia_inc_subtype"),"INC_TYPE","INC_SUB_TYPE",$(this).val(),"");
	});
	
	$("#txt_dia_inc_number").onEnter( function() {
		if(!empty($("#txt_dia_inc_number").val())){
			incidentalSearchData();	
		}
		
    });
	
	oTableInc = incidentalSearchData();	

	$("#tblDialogIncList tbody").on("dblclick touchstart", "tr", function() {
		var aPos = oTableInc.fnGetPosition(this);
		var aData = oTableInc.fnGetData(aPos[0]);
		
		setDataIntoComponent(evalFuncCont,aData[aPos]);

	});
	    
});




/**
 * Set Value return form modal popup and set into component
 * Owner 
 */
function selectedIncOwnerUserDialog(jsonData) {
	
	$('#txt_dia_inc_owner_id').val(jsonData.userId);
	$('#txt_dia_inc_owner_name').val(jsonData.firstName + ' ' + jsonData.lastName);
	
	$("#current_to_dept_cd").val(jsonData.deptCode);
	$("#txt_dia_inc_owner_dept").val(jsonData.deptName);
	
	$("#incUserDialog").modal("hide");
}

/**
 * Set Value return form modal popup and set into component
 * Department
 */
function selectedIncDepartmentDialog(jsonData) {	
	$('#current_to_dept_cd').val(jsonData.deptCode);
	$('#txt_dia_inc_owner_dept').val(jsonData.deptName);
	
	$('#txt_dia_inc_owner_id').val("");
	$('#txt_dia_inc_owner_name').val("");
	
	$("#incDeptDialog").modal("hide");
}

function clearIncCriteriaSearch(){
	$("#incidentalCriteriaDialog").clearForm();
	$("#incidentalCriteriaDialog input").val("");
}


function declareColumn(){
	
	var col_no = '<center><spring:message code="inc.incGrdIncNo"/></center>';
	var col_incNumber = '<center><spring:message code="inc.incGrdIncNumber"/></center>';
	var col_incGrdIncType = '<center><spring:message code="inc.incGrdIncType"/></center>';
	var col_incGrdIncSubType = '<center><spring:message code="inc.incGrdIncSubType"/></center>';
	var col_incGrdIncPriority = '<center><spring:message code="inc.incGrdIncPriority"/></center>';
	var col_incGrdIncStatus	= '<center><spring:message code="inc.incGrdIncStatus"/></center>';
	var col_incGrdIncOpenDate	= '<center><spring:message code="inc.incGrdIncOpenDate"/></center>';
	var col_incGrdIncDueDate = '<center><spring:message code="inc.incGrdIncDueDate"/></center>';
	var col_incGrdIncClosesDate = '<center><spring:message code="inc.incGrdIncClosedDate"/></center>';
	var col_incGrdIncOwner = '<center><spring:message code="inc.incGrdIncOwner"/></center>';
	 columnsIncDt = [
	           { "sTitle": col_no, "mData": "incNumber", "sClass": "tdCenter" ,"sWidth": "3%" }
			  ,{ "sTitle": col_incNumber, "mData": "incNumber","sClass": "tdLeft" ,"sWidth": "10%" }			  
			  ,{ "sTitle": col_incGrdIncType, "mData": "incTypeDesc","sClass": "tdLeft" }
			  ,{ "sTitle": col_incGrdIncSubType, "mData": "incSubTypeDesc","sClass": "tdLeft" }
			  ,{ "sTitle": col_incGrdIncPriority, "mData": "incPriorityDesc","sClass": "tdLeft" }
			  ,{ "sTitle": col_incGrdIncStatus, "mData": "incStatusDesc","sClass": "tdLeft" }
			  ,{ "sTitle": col_incGrdIncOpenDate, "mData": "incOpenedDate","sClass": "tdCenter","sWidth": "10%"  }
			  ,{ "sTitle": col_incGrdIncDueDate, "mData": "incDueDate" ,"sClass": "tdCenter","sWidth": "10%" }
			  ,{ "sTitle": col_incGrdIncClosesDate, "mData": "incCloseDate" }
			  ,{ "sTitle": col_incGrdIncOwner, "mData": "incOwnerName","sClass": "tdLeft" ,"sWidth": "13%" }
			  ,];
	
}

function getINCTypeListCombo(plaseSelectMsg){
	// Query Parent 
	getCodeBookComboByCodeType($("#cmb_dia_inc_type"),"INC_TYPE",plaseSelectMsg);
}

function getINCSubTypeListCombo(plaseSelectMsg){
	
	//Query Child Code Type
	getCodeBookByParentTypeAndParentId($("#cmb_dia_inc_subtype"),"INC_TYPE","INC_SUB_TYPE","",plaseSelectMsg);
	
} 


function getINCChannelListCombo(plaseSelectMsg){
	
	getCodeBookComboByCodeType($("#cmb_dia_inc_channel"),"SRQ_CHANNEL",plaseSelectMsg);
}

function getINCStatusListCombo(plaseSelectMsg){
	
	getCodeBookComboByCodeType($("#cmb_dia_inc_status"),"SRQ_STATUS",plaseSelectMsg);
} 

function getINCPriorityListCombo(plaseSelectMsg){
	
	getCodeBookComboByCodeType($("#cmb_dia_inc_priority"),"SRQ_PRIORITY",plaseSelectMsg);
}

function incidentalSearchData(){
	var dataString = $("#incidentalCriteriaDialog").serialize();
	dataString += "&strParam="+strParamIncNum;
	return ajaxDataTable($('#tblDialogIncList'), columnsIncDt, 'getIncidentalModalList.htm', dataString, recordPerPage, true, true);
}

function createIncDatatable(){
	
	return initajaxDataTable($('#tblDialogIncList'), columnsIncDt, true, true);
			
}

 

</script>
<!-- END JAVA SCRIPTS -->
</div>