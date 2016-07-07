<%@page import="com.locus.jlo.web.constant.JLOWebConstant"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%
	int ranNum = (int)(Math.random()*1000000);
%>

<div class="modalCustId" id="modalCustId_<%=ranNum%>">

<form id="customerCriteriaDialog" class="form-horizontal">
<%-- 	<input type="text" id="customerCallBackFn" value="${callbackfn }" /> --%>
	
	<div class="row">
		<div class="col-md-2">
			<label class="control-label col-md-10"><spring:message code="cust.custSearch.type" /></label>
		</div>
		<div class="col-md-8">
			<div class="form-group text-left">
				<div class="radio-list">
					<label class="radio-inline">
<!-- 						<div class="radio"> -->
								<span> <!--  class="checked"> -->
									<input type="radio" id="custType1" name="custType" value="A" />
								</span>
<!-- 							</div>  -->
							<spring:message code="cust.type.all" />
						</label>
					<label class="radio-inline">
<!-- 						<div class="radio"> -->
							<span>
								<input type="radio" id="custType2" name="custType" value="I" />
							</span>
<!-- 						</div>  -->
							<spring:message code="cust.type.individual" /> 
					</label>
					<label class="radio-inline">
<!-- 						<div class="radio"> -->
							<span><input type="radio" id="custType3" name="custType" value="C" /></span>
<!-- 						</div> -->
						 <spring:message code="cust.type.corporate" />
					</label>
				</div>
			</div>
		</div>
		<div class="col-md-2">
			<div class="form-group">
			</div>
		</div>	
	</div>
	<div class="row">
		<div class="col-md-4">
			<div class="form-group">
				<label class="control-label col-md-5"> <spring:message code="cust.fullName" /> </label>
				<div class="col-md-7">
					<input type="text" id="custFullName" name="custFullName" class="form-control" maxlength="200"/>
				</div>
			</div>
		</div>
		<div class="col-md-4">
			<div class="form-group">
				<label class="control-label col-md-5"> <spring:message code="cust.industry" /> </label>
				<div class="col-md-7">
						<select id="custIndustry" name="custIndustry" class="select2me form-control">
						</select>
				</div>
			</div>
		</div>
		<div class="col-md-4">
			<div class="form-group">
				<label class="control-label col-md-5"> <spring:message code="cust.owner" />
				</label>
				<div class="col-md-7">
					<div class="input-group">
						<input type="hidden" id="custOwnerId" name="custOwnerId" /> 
						<input type="text" id="custOwner" name="custOwner" class="form-control" readonly="readonly" /> 
						<span class="input-group-addon"> 
							<a id="openUserDialog" href="#" data-target="#userIncustomerDialog" data-toggle="modal"> 
								<i class="fa fa-user"></i>
							</a>
						</span>
						<span class="input-group-addon"> 
							<a id="clearOwner" style="cursor: pointer;">
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
				<label class="control-label col-md-5"> <spring:message code="cust.firstname" /> </label>
				<div class="col-md-7">
					<input type="text" id="custFirstName" name="custFirstName" class="form-control" maxlength="50"/>
				</div>
			</div>
		</div>

		<div class="col-md-4">
			<div class="form-group">
				<label class="control-label col-md-5"> <spring:message code="cust.registId" /> </label>
				<div class="col-md-7">
					<input type="text" id="custRegistId" name="custRegistId" class="form-control" maxlength="20"/>
				</div>
			</div>
		</div>

		<div class="col-md-4">
			<div class="form-group">
				<label class="control-label col-md-5"> <spring:message code="cust.status" /> </label>
				<div class="col-md-7">
					<select id="custStatus" name="custStatus" class="select2me form-control">
					</select>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-4">
			<div class="form-group">
				<label class="control-label col-md-5"> <spring:message code="cust.lastname" /> </label>
				<div class="col-md-7">
					<input type="text" id="custLastName" name="custLastName" class="form-control" maxlength="50"/>
				</div>
			</div>
		</div>

		<div class="col-md-4">
			<div class="form-group">
				<label class="control-label col-md-5"> <spring:message code="cust.taxId" /> </label>
				<div class="col-md-7">
					<input type="text" id="custTaxId" name="custTaxId" class="form-control" maxlength="20"/>
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
				<label class="control-label col-md-5"> <spring:message code="cust.citizenId" /> </label>
				<div class="col-md-7">
					<input type="text" id="custCitizenId" name="custCitizenId" class="form-control" maxlength="20"/>
				</div>
			</div>
		</div>
		
		<div class="col-md-4">
			<div class="form-group"></div>
		</div>
		
		<div class="col-md-4">
			<div class="form-group"></div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-4">
			<div class="form-group">
				<label class="control-label col-md-5"> <spring:message code="cust.jobTitle" /> </label>
				<div class="col-md-7">
					<select id="custJobTitle" name="custJobTitle" class="select2me form-control">
					</select>
				</div>
			</div>
		</div>
		
		<div class="col-md-4">
			<div class="form-group"></div>
		</div>
		
		<div class="col-md-4">
			<div class="form-group"></div>
		</div>

	</div>
	<div class="form-actions right">
		<button id="btnSearch" class="btn blue" type="button" onclick="searchCustomer();">
			<i class="fa fa-search"></i>
			<spring:message code="button.search.label" />
		</button>
	</div>
	<div class="row">
		<div class="col-md-12">
				<table id="customerDialogDataTable" class="table table-bordered dataTable"></table>	
		</div>
	</div>
</form>


<div class="modal fade" id="userIncustomerDialog" role="basic" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div id="modal_content_div_cust_user"></div>
		</div>
	</div>
</div>


<script type="text/javascript">
	//var evalFunc = $('#customerCallBackFn').val();
	//var evalFunc ="${callbackfn}";
	//console.log("evalFuncCustomer :"+evalFuncCustomer);
	//var temEvalFunc = evalFunc;
	//console.log("evalFunc :"+evalFunc+"\n temEvalFunc : "+temEvalFunc);
	
	var messageTitle ='<spring:message code="menu.customer" />';
	var msgPleaseEnterWarning = '<spring:message code="message.please.enter.atLeastOne" />'; 
	
	var evalFuncCustomer ="${callbackfn}";
	var oTableCust;
	var columnCustDialogs ="[]";
	var recordPerPage = <%=JLOWebConstant.recordsPerPage%>;
	
	
	$(document).ready(function() {
		
		$(".modalCustId").not("#modalCustId_<%=ranNum%>").remove();
		
		ComponentsPickers.init();
		
		declareColumnDialogCust();
		
		initajaxDataTable($('#customerDialogDataTable'), columnCustDialogs);

		// custStatus dropdown
		getCodeBookComboByCodeType($("#custStatus"), "CUST_STATUS", '<spring:message code="lbl.select"/>');

		// custIndustry dropdown
		getCodeBookComboByCodeType($("#custIndustry"), "INDUSTRY_TYPE", '<spring:message code="lbl.select"/>');

		// custJobTitle dropdown
		getCodeBookComboByCodeType($("#custJobTitle"), "CONTACT_JOB_TITLE", '<spring:message code="lbl.select"/>');

		setRadioValue("custType", "A");
		
		//  Dialog
		$("#openUserDialog").click(function() {
			loadContentIntoModal($("#modal_content_div_cust_user"), "openModalDialog.htm", "userDialog", "modal.header.user","selectUserInCustomer");
		});
		$("#clearOwner").click(function(e) {
			e.preventDefault();
			$("#custOwnerId").val("");  
			$("#custOwner").val("");
		});
		
	/* 	$('#userIncustomerDialog').on('hidden', function(){
			
		    $(this).data('modal', null);
		    
		}); */
		
		/* $('#userIncustomerDialog').on('hide.bs.modal', function () {
		 		alert("hide.bs.modal");
			 //  $('#userIncustomerDialog').removeData();
			 //  $(".modal-body").empty();
			//   $("#userIncustomerDialog").data('modal', null);	
			  
			   $("#userIncustomerDialog").modal('destroy'); 
			   
			   
		}); 
		
		 $('body').on('hidden.bs.modal', '.modal', function () {
		        $(this).removeData('bs.modal');

	      });
		 */
		
		$('#customerDialogDataTable tbody').on('dblclick touchstart', 'tr', function() {
			var aPos = oTableCust.fnGetPosition(this);
			var aData = oTableCust.fnGetData(aPos[0]);
							
			//var funcCallMain = temEvalFunc;
			
			//console.log(" Customer Dialog EvalFunc : " + evalFunc+" \n funcCallMain :"+funcCallMain);
			console.log("Before Set evalFuncCustomer :"+evalFuncCustomer);
			//console.log(JSON.stringify(aData[aPos]));
			setDataIntoComponent(evalFuncCustomer,aData[aPos]);

			//selectedCustomer(aData[aPos]);
		});
		
		//Search First Time
		//searchCustomer();
		
		 
		$("#customerCriteriaDialog input[name='custType']").change(function (e) {
		 	e.preventDefault();
		 	
		 	if($("#custType1").is(":checked")) {
		           setRadioValue("custType", "A");
		       }else if ($("#custType2").is(":checked")) {
		       	setRadioValue("custType", "I");
		       }else{
		       	setRadioValue("custType", "C");
		       } 			 
		 	//searchCustomer();
		});
		
	
	});
	
function declareColumnDialogCust(){
		
		var columnCustDialog1 = '<spring:message code="cust.custGrdNo" />';
		var columnCustDialog2 = '<spring:message code="cust.custGrdFullName" />';
		var columnCustDialog3 = '<spring:message code="cust.custGrdCustType" />';
		var columnCustDialog4 = '<spring:message code="cust.custGrdIndustry" />';
		var columnCustDialog5 = '<spring:message code="cust.custGrdOffice" />';
		var columnCustDialog6 = '<spring:message code="cust.custGrdEmail" />';
		var columnCustDialog7 = '<spring:message code="cust.custGrdOwner" />';
		var columnCustDialog8 = '<spring:message code="cust.custGrdStatus" />';
		
		  columnCustDialogs = [
				{
					"sTitle" : columnCustDialog1,
					"mData" : "custId"
				},
				{
					"sTitle" : columnCustDialog2,
					"mData" : "custName"
				},
				{
					"sTitle" : columnCustDialog3,
					"mData" : "custTypeName"
				},
				{
					"sTitle" : columnCustDialog4,
					"mData" : "industryName"
				},
				{
					"sTitle" : columnCustDialog5,
					"mData" : "workPhoneFormat"
				},
				{
					"sTitle" : columnCustDialog6,
					"mData" : "email"
				},
				{
					"sTitle" : columnCustDialog7,
					"mData" : "ownerName"
				},
				{
					"sTitle" : columnCustDialog8,
					"mData" : "statusName"
				},
			];
		
	}
	
	function searchCustomer() {
		
		if(validateEnterCriteriaAtLeastOne()){
			var dataString = serialize($("#customerCriteriaDialog")[0]);
			oTableCust = ajaxDataTable($('#customerDialogDataTable'), columnCustDialogs, 'getCustList.htm', dataString, recordPerPage, true, true);
		
		}else{
			
			alertMessage("<h5>"+messageTitle+"</h5>",msgPleaseEnterWarning);
			return false;
		}
		
		
	}

	function selectUserInCustomer(jsonData) {
		
		$('#custOwnerId').val(jsonData.userId);
		$('#custOwner').val(jsonData.firstName + ' ' + jsonData.lastName);

		$("#userIncustomerDialog").modal("hide");
		 
	}
	
	
	function validateEnterCriteriaAtLeastOne(){
		 
		var custFullName = $("#custFullName").val();
		var custFirstName = $("#custFirstName").val();
		var custLastName =  $("#custLastName").val();
		var custCitizenId = $("#custCitizenId").val();
		var custJobTitle =  $("#custJobTitle").val();
		
		var custIndustry = $("#custIndustry").val();
		var custRegistId = $("#custRegistId").val();
		var custTaxId  = $("#custTaxId").val();
		var custOwner = $("#custOwner").val();
		var custOwnerId = $("#custOwnerId").val();
		var custStatus = $("#custStatus").val();
		
		
		if (empty(custFullName) && empty(custFirstName) && empty(custLastName) 
				&& empty(custCitizenId) && empty(custJobTitle) && empty(custIndustry) 
				&& empty(custRegistId) && empty(custTaxId) && empty(custOwner)
				&& empty(custOwnerId) && empty(custStatus)){
			
			return false;
		}else{
			return true;
			
		}
	}
</script>

</div>