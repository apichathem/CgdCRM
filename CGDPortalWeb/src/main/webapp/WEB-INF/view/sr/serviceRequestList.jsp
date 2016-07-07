<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="locus" uri="/WEB-INF/tld/locus.tld"%>

<!-- DIALOG User Dialog -->
<div class="modal fade" id="userDialog" role="basic" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div id="modal_content_div_user"></div>
		</div>
	</div>
</div>
 
 

<div class="modal fade" id="srCustomerDialog" role="basic" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div id="modal_content_div_cust"></div>
		</div>
	</div>
</div>

<!-- BEGIN CONTENT -->
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="caption">
			<spring:message code="menu.serviceRequest.list" />
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i><a href="home.htm"> <spring:message code="menu.home" /> </a><i class="fa fa-angle-right"></i></li>
			<li><a href="#"> <spring:message code="menu.serviceRequest" /> </a><i class="fa fa-angle-right"></i></li>
			<li><a href="#"> <spring:message code="menu.serviceRequest.list" /> </a></li>
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>

<div class="row">
	<div class="col-md-12">
	
		<!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-reorder"></i>
					<spring:message code="menu.serviceRequest.list" /> 
				</div>			
				<div class="tools">
					<a class="collapse" href="javascript:;"> </a>
				</div>
			</div>
			
			
			<div class="portlet-body form">
				<!-- BEGIN FORM-->
				<form:form id="serviceRequestCriteriaMain" action="#" modelAttribute="srModelBean" class="form-horizontal" >
					<input type="hidden" id="srNumber" name="sr_number"/>
					<input type="hidden" id="mode" name="mode"/>
					<input type="hidden" id="module" name="module"/>
					
					<div class="form-body">
					
						<!-- Row1  -->
						<div class="row">
						
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-5">
										<spring:message code="sr.criSrNumber"/>
									</label>
									<div class="col-md-7">
										<input type="text" id="txt_sr_number" name="txt_sr_number" class="form-control" placeholder="SR Number" value=""> 
									</div>
								</div>
							</div>
							
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-4">
										<spring:message code="sr.criSrOwner"/>
									</label>
									<div class="col-md-8">
										<div class="input-group">
											<form:input path="srOwnerName" class="form-control" readonly="true" />
											<form:hidden path="srOwnerId" class="form-control" />
											<span class="input-group-addon">
												<a id="btnSearchOwner" href="#" data-target="#userDialog" data-toggle="modal">
													<i class="fa fa-user" id="select_owner"></i>
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
									<label class="control-label col-md-4">
										<spring:message code="sr.criSrCustomer"/>
									</label>
									<div class="col-md-8">
										<div class="input-group">
											<input type="text" id="txt_sr_customer_name" name="txt_sr_customer_name" value="" class="form-control" readonly="readonly">
											<input type="hidden" id="txt_sr_customer_id" name="txt_sr_customer_id"  class="form-control" />
											<span class="input-group-addon">
												<a id="btnSearchCustomer" href="#" data-target="#srCustomerDialog" data-toggle="modal">
													<i class="fa fa-user" id="select_customer"></i>
												</a>	
											</span> 
											<span class="input-group-addon">
												<a id="btnClearCustomer" style="cursor: pointer;">
													<i class="fa fa-minus-circle red"></i>
												</a>
											</span>
											
										</div> 
									 
									</div>
								</div>
							</div>
							
							</div> 
							
							
							<!-- Below Layout AdvanceSearch Panel       -->			
										
							<div style="display:none" id="advanceSearchDiv">	
							<!-- Row 2 -->
								<div class="row">		
								 	<div class="col-md-4">
										<div class="form-group">
											<label class="control-label col-md-5">
												<spring:message code="sr.srTypeProblem1"/>
											</label>
											<div class="col-md-7">
												<select name="cmbSrTypeProblem1" id="cmbSrTypeProblem1" class="form-control select2me">
												</select>
											</div>
										</div>
									</div>
							
							
									<div class="col-md-4">
										<div class="form-group">
											<label class="control-label col-md-4">
												<spring:message code="sr.criSrChannel"/>
											</label>
											<div class="col-md-8">
												<form:select id="cmb_sr_channel" name="srChannelCd" path="srChannelCd" class="form-control select2me">
													<option></option>
													<form:options items='${CODEBOOK_LIST.SRQ_CHANNEL}' itemLabel="codeName" itemValue="codeId" />
												</form:select>
											</div>
										</div>
									</div>	
								
								
									<div class="col-md-4">
										<div class="form-group">
											<label class="control-label col-md-4">
												<spring:message code="sr.criSrStatus"/>
											</label>
											<div class="col-md-8">
												<form:select id="cmb_sr_status" name="srStatusCd" path="srStatusCd" class="form-control select2me" >
													<option></option>
													<form:options items='${CODEBOOK_LIST.SRQ_STATUS}' itemLabel="codeName" itemValue="codeId" />
												</form:select>	
											</div>
										</div>
									</div>
								
								</div>
								<!--  Row 3 -->
								<div class="row">
								
									<div class="col-md-4">
										  <div class="form-group">
											<label class="control-label col-md-5">
												<spring:message code="sr.srTypeProblem2"/>
											</label>
											<div class="col-md-7">
												<select name="cmbSrTypeProblem2" id="cmbSrTypeProblem2" class="form-control select2me">
												</select>
											</div>
										</div>  
									</div>
								
									<div class="col-md-4">
										<div class="form-group">
											<label class="control-label col-md-4">
												<spring:message code="sr.criSrPriority"/>
											</label>
											<div class="col-md-8">
												 <form:select id="cmb_sr_priority" name="srPriorityCd" path="srPriorityCd" class="form-control select2me">
												 	<option></option>
													<form:options items='${CODEBOOK_LIST.SRQ_PRIORITY}' itemLabel="codeName" itemValue="codeId" />
												 </form:select>
												 
											</div>
										</div>
									</div>
								
									<div class="col-md-4">
										<div class="form-group">
											<label class="control-label col-md-4">
												<spring:message code="sr.criSrCreatedDate"/>
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
								<!--  Row 4 -->
								<div class="row">
								
									<div class="col-md-4">
										  <div class="form-group">
											<label class="control-label col-md-5">
												<spring:message code="sr.srTypeProblem3"/>
											</label>
											<div class="col-md-7">
												<select name="cmbSrTypeProblem3" id="cmbSrTypeProblem3" class="form-control select2me">
												</select>
											</div>
										</div>  
									</div>
								
									<div class="col-md-4">
										   &nbsp;
									</div>
								
									<div class="col-md-4">
										<div class="form-group">
											<label class="control-label col-md-4">
												<spring:message code="sr.criSrDueDate"/>
											</label>
											<div class="col-md-8">
												<div class="input-group date-picker input-daterange" data-date="30/04/2014" data-date-format="dd/mm/yyyy" >
													<input id="due_date_start" type="text" class="form-control" name="due_date_start" placeholder="dd/mm/yyyy" />
														<span class="input-group-addon" > - </span>
													<input id="due_date_end" type="text" class="form-control" name="due_date_end" placeholder="dd/mm/yyyy" />
												</div> 
											</div>
										</div>
									</div> 
								</div>
								<!--  Row 5 -->
								<div class="row">
								
									<div class="col-md-4">
										  <div class="form-group">
											<label class="control-label col-md-5">
												<spring:message code="sr.srTypeProblem4"/>
											</label>
											<div class="col-md-7">
												<select name="cmbSrTypeProblem4" id="cmbSrTypeProblem4" class="form-control select2me">
												</select>
											</div>
										</div>  
									</div>
								
									<div class="col-md-4">
										   &nbsp;
									</div>
								
									<div class="col-md-4">
										 &nbsp;
									</div> 
									
								</div>
								<!--  Row 5 -->
								<div class="row">
									<div class="col-md-4">
										  <div class="form-group">
											<label class="control-label col-md-5">
												<spring:message code="sr.srTypeProblem5"/>
											</label>
											<div class="col-md-7">
												<select name="cmbSrTypeProblem5" id="cmbSrTypeProblem5" class="form-control select2me">
												</select>
											</div>
										</div>  
									</div>
								
									<div class="col-md-4">
										   &nbsp;
									</div>
								
									<div class="col-md-4">
										 &nbsp;
									</div> 
								</div>	
								
								
						</div> 
						<!-- End Advance search -->
						
<!-- 					</div>	 END ROW  -->
				</div>	
				
				
				
					<div class="form-actions right">
							<locus:privilege oper="READ"></locus:privilege>
						
							<button id="btn_searchSR" class="btn blue" type="button">
								<i class="fa fa-search"></i> 
								<spring:message code="button.search.label"/>
							</button>
							<button id="btn_cancelSearch" class="btn default" type="button" >
								<spring:message code="button.cancel.label"/>
							</button>
							<button id="btn_searchAdvanceSearch" class="btn dark" type="button" onclick="showAdvanceSearch();">
								<i class="fa fa-search"></i> 
								<spring:message code="button.advanceSearch.label"/>
							</button>
							<button id="btn_searchQuickSearch" class="btn dark" type="button" onclick="hideAdvanceSearch();">
								<i class="fa fa-search"></i> 
								<spring:message code="button.quickSearch.label"/>
							</button>
						
						<locus:privilege oper="ADD">
							<button id="btn_sr_create" class="btn green" type="button">
								<i class="fa fa-plus"></i>  
								<spring:message code="button.create.label"/>
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
		<!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="portlet-body">
			<table class="table table-bordered" id="tbl_SRList">
			</table>
		</div>
	</div>
		<!-- END EXAMPLE TABLE PORTLET-->
</div>
	
 
<!-- BEGIN Declare Url Path-->
<c:url var="editUrl" value="/serviceRequestDetail.htm" />
<!-- END Declare Url Path -->

<!-- BEGIN JAVA SCRIPTS -->
<script type="text/javascript">
var columnsSrDt ="[]";
var CONTEXT_PATH = "<%=request.getContextPath()%>";
var AJAX_TIMEOUT = '1000000'; 
var recordPerPage = '<spring:message code="datatable.recordPerPage" />';
var plaseSelectMsg = '<spring:message code="message.please.select" />';

$(document).ready(function() {  
	
	ComponentsPickers.init();
	
	getTypeProblem1List(plaseSelectMsg);
	$('#btn_searchQuickSearch').hide();
	
	declareColumn();
	
	var oTable = createSrDatatable();
	 
  //DisplayTooltips
 	dataTableOnMouseoverTooltips("tbl_SRList");
	
 // Begin Dialog   
    
 	//Owner dialog button
	$("#btnSearchOwner").click(function() {
		loadContentIntoModal($("#modal_content_div_user"), "openModalDialog.htm", "userDialog", "modal.header.user","selectedSROwnerUser");
	});
 
	// Owner dialog clear field criteria button
	$("#btnClearOwner").click(function() {
		$("#srOwnerId").val("");
		$("#srOwnerName").val("");
	});
	
	$("#btnSearchCustomer").click(function() {
		loadContentIntoModal($("#modal_content_div_cust"), "openModalDialog.htm", "customerDialog", "modal.header.cust","selectedSRCustomer");
	});
	
	$("#btnClearCustomer").click(function() {
		$('#txt_sr_customer_name').val("");
		$('#txt_sr_customer_id').val("");
	});
// End Dialog	
    
    
	$("#btn_searchSR").click(function() {
		serviceRequestSearchData();
	}); 
    
    $("#btn_cancelSearch").click(function() {
    	clearSRCriteriaSearch();
	});
    

	$("#cmbSrTypeProblem1").change(function(){		
		
		getTypeProblem2ByParentIdList($(this).val(), '', plaseSelectMsg);	
		
		$("#cmbSrTypeProblem3").html("");
		$("#cmbSrTypeProblem4").html("");
		$("#cmbSrTypeProblem5").html("");

		$("#cmbSrTypeProblem3").select2("val",null);
		$("#cmbSrTypeProblem4").select2("val",null);
		$("#cmbSrTypeProblem5").select2("val",null);
		
		
		 
	});
	
	$("#cmbSrTypeProblem2").change(function(){	
		
		getTypeProblem3ByParentIdList($(this).val(), '', plaseSelectMsg);
		
		$("#cmbSrTypeProblem4").html("");
		$("#cmbSrTypeProblem5").html("");
		$("#cmbSrTypeProblem4").select2("val",null);
		$("#cmbSrTypeProblem5").select2("val",null);
		
	});
	
	$("#cmbSrTypeProblem3").change(function(){	
		
		getTypeProblem4ByParentIdList($(this).val(), '', plaseSelectMsg);
		
		$("#cmbSrTypeProblem5").html("");
		$("#cmbSrTypeProblem5").select2("val",null);
		
	});
	
	$("#cmbSrTypeProblem4").change(function(){		
		getTypeProblem5ByParentIdList($(this).val(), '', plaseSelectMsg);
		
	});
	
	
	$("#btn_sr_create").click(function(){		
		createServiceRequestPostForm();
		
	});
	
	
	 $("#txt_sr_number").onEnter( function() {
		 if(!empty($("#txt_sr_number").val())){
		 	serviceRequestSearchData();
		 }
     });
	    
});

function createServiceRequestPostForm(){
	
	var urlSrDetail = '${editUrl}';
	$("#serviceRequestCriteriaMain").attr("action", urlSrDetail);
	$("#serviceRequestCriteriaMain").attr("method", "POST");
	$("#mode").val("update");   
	$("#serviceRequestCriteriaMain").submit();
	
}	
	
/**
 * Set Value return form modal popup and set into component
 * Owner 
 */
function selectedSROwnerUser(jsonData) {
	
	$('#srOwnerId').val(jsonData.userId);
	$('#srOwnerName').val(jsonData.firstName + ' ' + jsonData.lastName);
	
	$("#userDialog").modal("hide");
}


function selectedSRCustomer(jsonData){
	
	$('#txt_sr_customer_name').val(jsonData.custName);
	$('#txt_sr_customer_id').val(jsonData.custId);
	$("#srCustomerDialog").modal("hide");
}


function clearSRCriteriaSearch(){
	$("#serviceRequestCriteriaMain").clearForm();
	$("#serviceRequestCriteriaMain input").val("");
	
}

function declareColumn(){
	
	var col_no = '<center><spring:message code="sr.srGrdSrNo"/></center>';
	var col_srnumber = '<center><spring:message code="sr.srGrdSrNumber"/></center>';
	var col_srTypeProblem1 = '<center><spring:message code="sr.srGrdTypeProblem1"/></center>';
	var col_srSubject ='<center><spring:message code="sr.srGrdSubject"/></center>';
	/* var col_srTypeProblem2 = '<center><spring:message code="sr.srGrdTypeProblem2"/></center>';
	var col_srTypeProblem3 = '<center><spring:message code="sr.srGrdTypeProblem3"/></center>';
	var col_srTypeProblem4 = '<center><spring:message code="sr.srGrdTypeProblem4"/></center>';
	var col_srTypeProblem5 = '<center><spring:message code="sr.srGrdTypeProblem5"/></center>'; */
	
	var col_srGrdSrPriority = '<center><spring:message code="sr.srGrdPriority"/></center>';
	var col_srGrdSrStatus	= '<center><spring:message code="sr.srGrdSrStatus"/></center>';
	var col_srGrdSrOpenDate	= '<center><spring:message code="sr.srGrdSrOpenDate"/></center>';
	var col_srGrdSrDueDate = '<center><spring:message code="sr.srGrdSrDueDate"/></center>';
	var col_srGrdSrOwner = '<center><spring:message code="sr.srGrdSrOwner"/></center>';
	var col_srGrdSrAction = '<center><spring:message code="sr.srGrdSrAction"/></center>';
	
	 columnsSrDt = [
                { 
                    "sTitle": col_srGrdSrAction,
                    "mData": "editUrl" ,
                    "sWidth": "3%",
               }
              ,{ "sTitle": col_no, "mData": null ,"sClass": "tdCenter","sWidth": "3%" }
			  ,{ "sTitle": col_srnumber, "mData": "srNumber" ,"sClass": "tdLeft" ,"sWidth": "10%" }
			  ,{ "sTitle": col_srTypeProblem1, "mData": "srTypeProblem1" ,"sClass": "tdLeft" }
			  ,{ "sTitle": col_srSubject, "mData": "srSubject" ,"sClass": "tdLeft" }
			 
			  /* ,{ "sTitle": col_srTypeProblem2, "mData": "srTypeProblem2","sClass": "tdLeft"  }
			  ,{ "sTitle": col_srTypeProblem3, "mData": "srTypeProblem3" ,"sClass": "tdLeft" }
			  ,{ "sTitle": col_srTypeProblem4, "mData": "srTypeProblem4","sClass": "tdLeft"  }
			  ,{ "sTitle": col_srTypeProblem5, "mData": "srTypeProblem5" ,"sClass": "tdLeft" } */
			  
			  ,{ "sTitle": col_srGrdSrPriority, "mData": "srPriorityDesc" ,"sClass": "tdLeft" }
			  ,{ "sTitle": col_srGrdSrStatus, "mData": "srStatusDesc" ,"sClass": "tdLeft" }
			  ,{ "sTitle": col_srGrdSrOpenDate, "mData": "srOpenedDate" ,"sClass": "tdCenter", "sWidth": "10%"}
			  ,{ "sTitle": col_srGrdSrDueDate, "mData": "srDueDate" ,"sClass": "tdCenter" ,"sWidth": "10%" }
			  ,{ "sTitle": col_srGrdSrOwner, "mData": "srOwnerName" ,"sClass": "tdLeft" ,"sWidth": "13%" }
			 ];
	
	//sTitle >> Caption Display Header Grid
	//mData >> Mapping to Json Data 
	
}

function editServiceRequest(obj){
      
    var sr_number = obj.getAttribute("href");
    var urlSrDetail = '${editUrl}';
   	$("#serviceRequestCriteriaMain").attr("action", urlSrDetail);
   	$("#serviceRequestCriteriaMain").attr("method", "POST");
   	$("#srNumber").val(sr_number);
   	$("#mode").val("update");   	
   	$("#serviceRequestCriteriaMain").submit();
       
}

function getTypeProblem1List(plaseSelectMsg){
	 
	getTypeProblem1ListDefaultVal($("#cmbSrTypeProblem1"),"",plaseSelectMsg);
}

function getTypeProblem2ByParentIdList(parentCatId,defaultValueJsp,plaseSelectMsg){
	 
	getTypeProblem2ByParentIdListDefaultVal($("#cmbSrTypeProblem2"),parentCatId,defaultValueJsp,plaseSelectMsg);
}

function getTypeProblem3ByParentIdList(parentCatId,defaultValueJsp,plaseSelectMsg){
	 
	getTypeProblem3ByParentIdListDefaultVal($("#cmbSrTypeProblem3"),parentCatId,defaultValueJsp,plaseSelectMsg);
}
 
function getTypeProblem4ByParentIdList(parentCatId,defaultValueJsp,plaseSelectMsg){
	 
	getTypeProblem4ByParentIdListDefaultVal($("#cmbSrTypeProblem4"),parentCatId,defaultValueJsp,plaseSelectMsg);
}
 
function getTypeProblem5ByParentIdList(parentCatId,defaultValueJsp,plaseSelectMsg){
	 
	getTypeProblem5ByParentIdListDefaultVal($("#cmbSrTypeProblem5"),parentCatId,defaultValueJsp,plaseSelectMsg);
}
 

function serviceRequestSearchData(){
	var dataString = $("#serviceRequestCriteriaMain").serialize();
	ajaxDataTable($('#tbl_SRList'), columnsSrDt, 'inquiryServiceRequestList.htm', dataString, recordPerPage, true, true);
}

function createSrDatatable(){
	return initajaxDataTable($('#tbl_SRList'), columnsSrDt);
			
}


function showAdvanceSearch(){
	$('#advanceSearchDiv').show();	
	$('#btn_searchAdvanceSearch').hide();
	$('#btn_searchQuickSearch').show();
}

function hideAdvanceSearch(){
	
	$('#advanceSearchDiv').hide();
	$('#btn_searchQuickSearch').hide();
	$('#btn_searchAdvanceSearch').show();	
	
}

/* function getSRChannelListCombo(plaseSelectMsg){
getCodeBookComboByCodeType($("#cmb_sr_channel"),"SRQ_CHANNEL",plaseSelectMsg);
}
function getSRStatusListCombo(plaseSelectMsg){
getCodeBookComboByCodeType($("#cmb_sr_status"),"SRQ_STATUS",plaseSelectMsg);
} 
function getSRPriorityListCombo(plaseSelectMsg){
getCodeBookComboByCodeType($("#cmb_sr_priority"),"SRQ_PRIORITY",plaseSelectMsg);
} */
</script>
<!-- END JAVA SCRIPTS -->