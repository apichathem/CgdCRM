<%@page import="com.locus.jlo.web.bean.modelbean.ConsultingModelBean"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	int num = (int)(Math.random()*1000000);
	if(request.getAttribute("CONSULTING_OBJECT")==null){
		request.setAttribute("CONSULTING_OBJECT", request.getSession().getAttribute("CONSULTING_OBJECT"));
	}
	if(request.getAttribute("CONSULTING_OBJECT")==null){
		request.setAttribute("CONSULTING_OBJECT", new ConsultingModelBean());
	}
	
	/* 
	String consultWrapUpFinished = "01";
	if(request.getAttribute("conWrapUpStatusCd") != null){
		consultWrapUpFinished = (String) request.getAttribute("conWrapUpStatusCd");
	} 
	*/
	
%>

<div class="modalconsultingid" id="modalconsultingid_<%=num%>">

<div class="row">
	<div class="col-md-12">
		<form:form modelAttribute="CONSULTING_OBJECT" id="formConsulting" class="form-horizontal" method="POST" action="updateConsulting.htm">
		<!-- Row1 -->
		<div class="row">
			<div class="col-md-4">
				<div class="form-group">
					<label class="control-label col-md-4"><spring:message code="consulting.consultingNo" /></label>
					<div class="col-md-8">
						<form:input id="consultingNumber" path="consultingNumber" readonly="true" class="form-control" />
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="form-group">
					<label class="control-label col-md-4"><spring:message code="consulting.customer" /></label>
					<div class="col-md-8">
						<div class="input-group">
							<form:hidden path="custId" id="popcustId"  />
							<form:hidden path="custType" id="popcustType"  />
							<input type="text" id="popcustName" name="custName" value="${CONSULTING_OBJECT.custName }" class="form-control" readonly  />
							<span class="input-group-btn"> 
								<button class="btn" id="btnCust" data-target="#consultingPopupDialog" data-toggle="modal">
									<i class="fa fa-user"></i>
								</button>
								<button class="btn" id="btnClearCust" style="cursor: pointer;" type="button"> 
									<i class="fa fa-minus-circle red"></i>
								</button>
							</span>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="form-group">
					<label class="control-label col-md-4"><spring:message code="consulting.owner" /></label>
					<div class="col-md-8">
						<div class="input-group">
							<form:hidden path="ownerId" id="popownerId"  />
							<input type="text" id="popownerName" name="ownerName" value="${CONSULTING_OBJECT.ownerName }" class="form-control" readonly  />
							<span class="input-group-btn"> 
								<button class="btn" id="btnOwner" data-target="#consultingPopupDialog" data-toggle="modal">
									<i class="fa fa-user"></i>
								</button>
								<button class="btn" id="btnClearOwner" style="cursor: pointer;" type="button"> 
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
					<label class="control-label col-md-4"><spring:message code="consulting.channel" /></label>
					<div class="col-md-8">
						<form:select id="channelCd" path="channelCd" class="form-control select2me">
							<form:option value=""></form:option>
							<form:options items='${CODEBOOK_LIST.CONSULT_CHANNEL}' itemLabel="codeName" itemValue="codeId" />
						</form:select>
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
							<form:options items='${CODEBOOK_LIST.CONSULT_STATUS}' itemLabel="codeName" itemValue="codeId" />
						</form:select>
					</div>
				</div>
			</div>
		</div>
		
		<!-- Row3 -->
		<div class="row">
			<div class="col-md-4">
				<div class="form-group">
					<label class="control-label col-md-4"><spring:message code="consulting.startDate" /></label>
					<div class="col-md-8">
						<div class="input-group">
							<fmt:formatDate value="${CONSULTING_OBJECT.startDt}" var="startDtDate" pattern="dd/MM/yyyy" />
							<form:input id="startDt" path="startDt" value="${startDtDate}" disabled="true" readonly="true" class="form-control date-picker maskdate" data-date-format="dd/mm/yyyy" data-date-start-date="+0d" data-rule-required="true" data-msg-required="To Date is required." />
							<span class="input-group-btn">
								<button class="btn default" type="button"><i class="fa fa-calendar"></i></button>
							</span>
							<fmt:formatDate value="${CONSULTING_OBJECT.startDt}" var="startDtTime" pattern="HH:mm" />
							<form:input id="startDtTime" path="startDtTime" value="${startDtTime}"  disabled="true" readonly="true" class="form-control masktime" data-rule-required="true" data-msg-required="To Time is required." />
						</div>
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-md-4"><spring:message code="consulting.endDate" /></label>
					<div class="col-md-8">
						<div class="input-group">
							<fmt:formatDate value="${CONSULTING_OBJECT.endDt}" var="endDtDate" pattern="dd/MM/yyyy" />
							<form:input id="endDt" path="endDt" value="${endDtDate}" readonly="true" disabled="true" class="form-control date-picker maskdate" data-date-format="dd/mm/yyyy" data-date-start-date="+0d" data-rule-required="true" data-msg-required="To Date is required." />
							<span class="input-group-btn">
								<button class="btn default" type="button"><i class="fa fa-calendar"></i></button>
							</span>
							<fmt:formatDate value="${CONSULTING_OBJECT.endDt}" var="endDtTime" pattern="HH:mm" />
							<form:input id="endDtTime" path="endDtTime" value="${endDtTime}" readonly="true" disabled="true" class="form-control masktime" data-rule-required="true" data-msg-required="To Time is required." />
						 </div>
					</div>
				</div>
			</div>
			<div class="col-md-8">
				<div class="form-group">
					<label class="control-label col-md-2"><spring:message code="consulting.note" /></label>
					<div class="col-md-10">
						<form:textarea id="note" path="note" class="form-control" style="resize: none; height:80px"></form:textarea>
					</div>
				</div>
			</div>
		</div>
		
		
		<div class="form-actions right">
			<button id="btnSaveConsulting" class="btn blue" type="button">
				<i class="fa fa-floppy-o"></i>
				<spring:message code="button.save.label" />
			</button>
		</div>
		</form:form>
	</div>
</div>


<script type="text/javascript">
var jsonDtObj=[];

var evalFuncKb ="${callbackfn}";
<%-- var conWrapUpStatusCd = "<%=consultWrapUpFinished%>"; --%>
var conWrapUpStatusCd = "${conWrapUpStatusCd}";
<%-- alert(" JS : "+conWrapUpStatusCd+"\n JSP :"+"<%=consultWrapUpFinished%>"); --%>


$(document).ready(function() {
	
	$(".modalconsultingid").not("#modalconsultingid_<%=num%>").remove();
	 
	ComponentsPickers.init();
	setMaskComponent();
	
	try{
		$('#formConsulting .select2me').select2({
	        placeholder: select2_pleaseSelect,
	        allowClear: true
	    });  
	}catch(e){
		
	}
	
	if(typeof tempConsultingData != 'undefined'){
		if(tempConsultingData!=null){
			setDataViewMode(tempConsultingData);
			tempConsultingData = null;
		}		
	}
	$("#btnSaveConsulting").on("click",function(){
		consultingSaveSubmit();
		return false;
	});
	$("#btnOwner").on("click",function() {
		fromBtn = 'assignTo';
		loadContentIntoModal($("#modal_content_div_Consulting_popup"), "openModalDialog.htm", "userDialog", "modal.header.user", "selectedOwner");
	});
	$("#btnClearOwner").on("click",function(){
		$("#popownerId").val("");
		$("#popownerName").val("");
	});
	$("#btnCust").on("click",function() {
		fromBtn = 'assignTo';
		loadContentIntoModal($("#modal_content_div_Consulting_popup"), "openModalDialog.htm", "customerDialog", "modal.header.cust", "selectedCust");
	});
	$("#btnClearCust").on("click",function(){
		$("#popcustId").val("");
		$("#popcustName").val("");
		$("#popcontactId").val("");
		$("#popcontactName").val("");
		
		setEnabledCustomerContactDialog();
	});
	
	$("#btnContact").on("click",function() {
		fromBtn = 'assignTo';
		loadContentIntoModal($("#modal_content_div_Consulting_popup"), "openModalDialog.htm", "contactConsultingDialog", "modal.header.contact", "selectedContact");
	});
	
	$("#btnClearContactField").on("click",function(){
		$("#popcontactId").val("");
		$("#popcontactName").val("");
	});
	
	$("#consultingDialog").on("hidden", function(){
	    console.log('consultingDialog HIDDEN');
	    if(typeof setCustContactConsulting != 'undefined'){
	    	 setCustContactConsulting(); 
		} 
	    console.log("hidden");
	    if(checkConsultingIsAvailible()){
	    	console.log("redirect=customerMain.htm");
			redirectUrlAction("customerMain.htm");
		}
	    
	   	
	    /* if(conWrapUpStatusCd === "01"){
	    	redirectUrlAction("customerMain.htm");
	    } */
	    
	}).on("shown", function(){
		 console.log('consultingDialog SHOWN');
	}); 
	
	 setWrapUpStatusCd();
	 
	setEnabledCustomerContactDialog();
});
function setEnabledCustomerContactDialog(){
	var custId = $("#popcustId").val();
	if(custId==""){
		$("#btnContact").addClass("disabled");
	}else{
		$("#btnContact").removeClass("disabled");
	}
	
}

function setWrapUpStatusCd(){
	 if(!empty(conWrapUpStatusCd)){
	   if(conWrapUpStatusCd === "02"){
			 $("#formConsulting #statusCd").select2("val",conWrapUpStatusCd);
			 $("#formConsulting #statusCd").attr("readonly",true);
		 }   
	 }
}

function setDataViewMode(jsonData){
// 	console.log(jsonData);
// 	setFormScreenByModelBean("formConsulting",data);
	formId = "formConsulting";
	for(var obj in jsonData){
// 		console.log("obj:"+obj);
	    if(jsonData.hasOwnProperty(obj)){
// 	    	console.log("jsonData[obj]:"+jsonData[obj]);
	    	var val = jsonData[obj];
	    	if(jsonData[obj]==null){
	        	val="";
	    	}
	    	console.log(obj+"="+val);
	    	$("#"+formId+" input[name='"+obj+"']").val(val);
        	$("#"+formId+" select[name='"+obj+"']").select2("val",val);
	    	$("#"+formId+" textarea[name='"+obj+"']").val(val);
	    }
	}
	$("#formConsulting #startDt").val(timestamp2date(jsonData.startDt));
	$("#formConsulting #startDtTime").val(timestamp2time(jsonData.startDt));
	$("#formConsulting #endDt").val(timestamp2date(jsonData.endDt));
	$("#formConsulting #endDtTime").val(timestamp2time(jsonData.endDt));
	
	if($("#formConsulting #statusCd").val()=='02'){
		$("#"+formId+" input").attr("readonly",true);
		$("#"+formId+" select").attr("readonly",true);
		$("#"+formId+" textarea").attr("readonly",true);
		$("#btnSaveConsulting").hide();
		$("#btnOwner").attr("disabled",true);
		$("#btnCust").attr("disabled",true);
		$("#btnContact").attr("disabled",true);
		$("#btnClearOwner").attr("disabled",true);
		$("#btnClearCust").attr("disabled",true);
		$("#btnClearContactField").attr("disabled",true);
		$(".maskdate").attr("disabled",true);
	}
	
	
}
function consultingSaveSubmit(){
	
	ajaxSubmitForm($("#formConsulting"),function(data){
		setDataIntoComponent(evalFuncKb,data);
	});
	return false;
}
function selectedOwner(jsonData){
	console.log(jsonData);
	$("#popownerId").val(jsonData.userId);
	$("#popownerName").val((jsonData.firstName + ' ' + jsonData.lastName));
	$("#consultingPopupDialog").modal("hide");
}

function selectedCust(jsonData){
	console.log(jsonData);
	$("#popcustId").val(jsonData.custId);
	$("#popcustName").val(jsonData.custName);
	$("#popcustType").val(jsonData.custTypeCd);
	$("#consultingPopupDialog").modal("hide");
	$("#popcontactId").val("");
	$("#popcontactName").val("");
	setEnabledCustomerContactDialog();
}

function selectedContact(jsonData){
	console.log(jsonData);
	$("#popcontactId").val(jsonData.indId);
	$("#popcontactName").val((jsonData.firstName));
	$("#consultingPopupDialog").modal("hide");
}

</script>
</div>