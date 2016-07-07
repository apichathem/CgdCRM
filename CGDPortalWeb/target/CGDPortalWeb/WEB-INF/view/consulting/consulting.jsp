<%@page import="com.locus.jlo.web.bean.modelbean.ConsultingModelBean"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%
request.setAttribute("consultingModelBean", new ConsultingModelBean());
%>


<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="caption">
			<spring:message code="menu.consulting.list" />
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="home.htm"> <spring:message code="menu.home" />
			</a> <i class="fa fa-angle-right"></i></li>
			<li><a href="#"> <spring:message code="menu.consulting" />
			</a> <i class="fa fa-angle-right"></i></li>
			<li><a href="#"> <spring:message code="menu.consulting.list" />
			</a></li>
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<form:form id="consultingSearchFrom" action="inquiryConsiltingList.htm" modelAttribute="consultingModelBean" class="form-horizontal" autocomplete="off">
	<div class="row">
		<div class="col-md-12">
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-reorder"></i>
						<spring:message code="menu.consulting.list" />
					</div>
					<div class="tools">
						<a class="collapse" href="javascript:;"> </a>
					</div>
				</div>
				<div class="portlet-body form">
					<!-- BEGIN FORM-->

					<div class="form-body">
						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-4"><spring:message code="consulting.channel" /></label>
									<div class="col-md-8">
										<form:select path="channelCd" class="form-control select2me">
											<form:option value=""></form:option>
											<form:options items='${CODEBOOK_LIST.CONSULT_CHANNEL}' itemLabel="codeName" itemValue="codeId" />
										</form:select>
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-4"><spring:message code="consulting.owner" /></label>
									<div class="col-md-8">
										<div class="input-group">
											<form:hidden path="ownerId" id="ownerId"  />
											<form:input path="ownerName" class="form-control" readonly="true"/>
											<span class="input-group-addon"> 
												<a id="btnListOwner" href="#" data-target="#consultingPopupDialog" data-toggle="modal">
													<i class="fa fa-user"></i>
												</a>
											</span>
											<span class="input-group-addon">
												<a id="btnClearListOwner" style="cursor: pointer;"> 
													<i class="fa fa-minus-circle red"></i>
												</a>
											</span>
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-4"><spring:message code="consulting.customer" /></label>
									<div class="col-md-8">
										<div class="input-group">
											<form:hidden path="custId" id="custId"  />
											<input type="text" id="custName" class="form-control" readonly  />
											<span class="input-group-addon"> 
												<a id="btnListCust" href="#" data-target="#consultingPopupDialog" data-toggle="modal">
													<i class="fa fa-user"></i>
												</a>
											</span>
											<span class="input-group-addon">
												<a id="btnClearListCust" style="cursor: pointer;"> 
													<i class="fa fa-minus-circle red"></i>
												</a>
											</span>
										</div>
									</div>
								</div>
							</div>
						</div>
						
						<div style="display:none" id="advanceSearchDiv">					
							<div class="row">
								<div class="col-md-4">
									<div class="form-group">
										<label class="control-label col-md-4"><spring:message code="consulting.consultingNo" /></label>
										<div class="col-md-8">
											<form:input id="consultingNumber" path="consultingNumber" class="form-control" />
										</div>
									</div>
								</div>
								<div class="col-md-4">
 									<div class="form-group">
										<label class="control-label col-md-4"><spring:message code="consulting.title" /></label>
										<div class="col-md-8">
											<form:input id="title" path="title" class="form-control" />
										</div>
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label class="control-label col-md-4"><spring:message code="consulting.status" /></label>
										<div class="col-md-8">
											<form:select id="statusCd" path="statusCd" class="form-control select2me">
												<form:option value=""></form:option>
												<form:options items='${CODEBOOK_LIST.CONSULT_STATUS}' itemLabel="codeName" itemValue="codeId" />
											</form:select>
										</div>
									</div>
								</div>
								<div class="col-md-4">
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label class="control-label col-md-4"><spring:message code="consulting.startDate" /></label>
										<div class="col-md-8">
											<div class="input-group date-picker input-daterange" data-date-format="dd/mm/yyyy" >
												<form:input path="startDt" class="form-control" placeholder="dd/mm/yyyy"/>
												<span class="input-group-addon" > - </span>
												<form:input path="endDt" class="form-control" placeholder="dd/mm/yyyy"/>
											</div> 	
										</div>
									</div>
								</div>
								<div class="col-md-4">
								</div>
								<div class="col-md-4">
								</div>
							</div>
						</div>
					</div>
					<div class="form-actions right">
						<div class="col-md-12">
							<button class="btn blue" type="button" id="btnSearchConsulting">
								<i class="fa fa-search"></i>
								<spring:message code="button.search.label" />
							</button>
							<button class="btn default" type="button" onclick="clearForm()">
								<spring:message code="button.cancel.label" />
							</button>
							<button id="btn_searchAdvanceSearch" class="btn dark" type="button" onclick="showAdvanceSearch();">
								<i class="fa fa-search"></i> <spring:message code="button.advanceSearch.label"/>
							</button>
							<button id="btn_searchQuickSearch" class="btn dark" type="button" onclick="hideAdvanceSearch();">
								<i class="fa fa-search"></i> <spring:message code="button.quickSearch.label"/>
							</button>
						</div>
					</div>
					<!-- END FORM-->
				</div>

			</div>
		</div>
	</div>
</form:form>

<form id="serviceRequestCriteriaMain" action="#" class="form-horizontal" >
	<input type="hidden" id="srNumber" name="sr_number"/>
	<input type="hidden" id="mode" name="mode"/>
	<input type="hidden" id="module" name="module"/>
</form>
					
<div class="row">
	<div class="col-md-12">	
		<table id="consultingtbl" class="table table-bordered dataTable">
		</table>
	</div>
</div>
<br/><br/>

<h5 class="form-section"><spring:message code="menu.serviceRequest" /></h5>
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

<script type="text/javascript">


var CONTEXT_PATH = "<%=request.getContextPath()%>";
var AJAX_TIMEOUT = '1000000'; 
var recordPerPage = '<spring:message code="datatable.recordPerPage" />';
var plaseSelectMsg = '<spring:message code="message.please.select" />';

var datatbl="";
var tempConsultingData="";
var columns = "[]";
var columnsSrDt ="[]";
var oTable ="";
 
$(document).ready(function() { 
	ComponentsPickers.init();
	$('#btn_searchQuickSearch').hide();
	
	decareConsultingColumn();
	
	datatbl = initajaxDataTable($("#consultingtbl"),columns);
	
	
	declareSRColumn();
	oTable = createSrDatatable();
	
	$("#btnListOwner").on("click",function() {
		fromBtn = 'assignTo';
		loadContentIntoModal($("#modal_content_div_Consulting_popup"), "openModalDialog.htm", "userDialog", "modal.header.user", "selectedListOwner");
	});
	$("#btnClearListOwner").on("click",function(){
		$("#ownerId").val("");
		$("#ownerName").val("");
	});
	
	$("#btnListCust").on("click",function() {
		fromBtn = 'assignTo';
		loadContentIntoModal($("#modal_content_div_Consulting_popup"), "openModalDialog.htm", "customerDialog", "modal.header.cust", "selectedListCust");
	});
	$("#btnClearListCust").on("click",function(){
		$("#custId").val("");
		$("#custName").val("");
	});
	
	$("#btnListContact").on("click",function() {
		fromBtn = 'assignTo';
		loadContentIntoModal($("#modal_content_div_Consulting_popup"), "openModalDialog.htm", "contactDialog", "modal.header.contact", "selectedListContact");
	});
	$("#btnClearListContact").on("click",function(){
		$("#contactId").val("");
		$("#contactName").val("");
	});
	$("#btnSearchConsulting").on("click",function(e){
		serviceRequestSearchDataByConsultingNumber("-1");
		searchConsultingList();		 
		 
	});	
	
	
	$("#consultingtbl tr").live("dblclick",function(){
		var aPos = datatbl.fnGetPosition($(this)[0]);
		if(aPos == null){
			return false;    		
		}
		var aData = datatbl.fnGetData(aPos);
// 		console.log(aData);
		tempConsultingData = aData;
		datatbl.$("tr:eq("+aPos+")" ).addClass("selected");	
		$("#consultingDialog").modal("show");
		toCustomerPage = false;
		loadContentIntoModal($("#modal_content_div_consulting"), "openModalDialog.htm", "consultingDialog", "model.header.consulting", "callbackDialogConsulting");
	});
	
	
	$("#consultingtbl tbody tr").live("click touchstart", function () {
		    var aPos = datatbl.fnGetPosition(this);
	    	if(aPos == null){
				return false;    		
			}
	        var aData = datatbl.fnGetData(aPos[0]);
	        var consultingNumber = aData[aPos].consultingNumber;
	        serviceRequestSearchDataByConsultingNumber(consultingNumber);
	    });
	
	
	$("#consultingSearchFrom").find("input").on("keypress",function(event){
		if(event.which==13){
			serviceRequestSearchDataByConsultingNumber("-1");
			searchConsultingList();
			
		}
	});
});

function decareConsultingColumn(){
	columns = [
		//{ "sTitle": actionLabel,"mData": null,"sClass": "text-center","sWidth": "3%",
		//	"fnRender": function(objData) {
		
		//	}  
		//	},
		{ "sTitle": '<spring:message code="consulting.no" />', "mData": null , sClass: "text-center", "fnRender":function(objData){ return objData.oSettings._iDisplayStart+objData.iDataRow+1;}}
		,{ "sTitle": '<spring:message code="consulting.consultingNo" />', "mData": "consultingNumber" }
		,{ "sTitle": '<spring:message code="consulting.channel" />', "mData": "channelName" }
		,{ "sTitle": '<spring:message code="consulting.customer" />', "mData": "custName" }
		,{ "sTitle": '<spring:message code="consulting.title" />', "mData": "title" }
		,{ "sTitle": '<spring:message code="consulting.status" />', "mData": "statusName" }
		,{ "sTitle": '<spring:message code="consulting.startDate" />', 
		"mData": null,
		 "sWidth": "130",
		 "sClass": "text-center",
		 "fnRender": function(objData) {
				var startDt = objData.aData.startDt;
				return timestamp2datetime(startDt);
		 }
		}
		,{ "sTitle": '<spring:message code="consulting.owner" />', "mData": "ownerName" }
		];
	
}

function declareSRColumn(){
	
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
			  ,{ "sTitle": col_srSubject, "mData": "srSubject" ,"sClass": "tdLeft","sWidth": "15%" }
			  
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

function searchConsultingList(){
	var param = serialize($("#consultingSearchFrom")[0]);
	datatbl = ajaxDataTable($("#consultingtbl"),columns, "inquiryConsultingList.htm", param, '<spring:message code="datatable.recordPerPage" />', true, false);
}

function callbackDialogConsulting(jsonData){
// 	console.log(jsonData);
	setPageChangeCurrent(datatbl);
	$("#consultingDialog").modal("hide");
}

function selectedListOwner(jsonData){
	console.log(jsonData);
	$("#ownerId").val(jsonData.userId);
	$("#ownerName").val((jsonData.firstName + ' ' + jsonData.lastName));
	$("#consultingPopupDialog").modal("hide");
}

function selectedListCust(jsonData){
	console.log(jsonData);
	$("#custId").val(jsonData.custId);
	$("#custName").val(jsonData.custName);
	$("#consultingPopupDialog").modal("hide");
}

function selectedListContact(jsonData){
	console.log(jsonData);
	$("#contactId").val(jsonData.userId);
	$("#contactName").val((jsonData.firstName + ' ' + jsonData.lastName));
	$("#consultingPopupDialog").modal("hide");
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

function clearForm() {
	$("#consultingSearchFrom").closest('form')[0].reset();
	$("#ownerId").val("");
	$("#custId").val("");
	$("#contactId").val("");
	$("#consultingSearchFrom").closest('form').find('.select2-offscreen').trigger('change');
}

function createSrDatatable(){
	return initajaxDataTable($('#tbl_SRList'), columnsSrDt);
			
}

function serviceRequestSearchDataByConsultingNumber(consultingNumber){
	var consultingNumberParam = "consultingNumber="+consultingNumber;
	ajaxDataTable($('#tbl_SRList'), columnsSrDt, 'inquiryServiceRequestListByConsultingNumber.htm', consultingNumberParam, recordPerPage, true, true);
}

</script>