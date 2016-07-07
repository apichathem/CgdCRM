<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="locus" uri="/WEB-INF/tld/locus.tld"%>

<div class="row">
	<div class="col-md-12">
		<!-- Begin Grid Activity List -->
		<div class="row">
			<div class="col-md-12">
				<div role="grid" class="dataTables_wrapper" id="grid_act_wrapper">
					<div class="table-scrollunable">
						<table id="gridSolution1List" class="table table-bordered">
						</table>
					</div>
				</div>
			</div>
		</div>
		<hr />

		<form:form action="inquirySearchDataFromTqKbByCriteria.htm" name="solutionSearchForm" id="solutionSearchForm" modelAttribute="srSolutionModelBean" class="form-horizontal">
			<div class="form-body">

				<!-- Row1  -->
				<div class="row">

					<div class="col-md-6">
						<div class="col-md-6"></div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="radio-inline"> <input type="radio" id="rdoSolSearchTQ" name="rdoSolSearchType" value="TQ" /> <spring:message code="sr.solutionSearchTQ" />
								</label>
							</div>
						</div>

					</div>
					<div class="col-md-6">
						<div class="col-md-6"></div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="radio-inline"> <input type="radio" id="rdoSolSearchKB" name="rdoSolSearchType" value="KB" checked /> <spring:message code="sr.solutionSearchKB" />
								</label>
							</div>
						</div>

					</div>
				</div>

				<!-- Row2  -->
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-6"> <spring:message code="sr.soltionKeyWord" />
							</label>
							<div class="col-md-6">
								<input type="text" id="txtSoltionKeyWord" name="soltionKeyWord" class="form-control" placeholder="<spring:message code="sr.soltionKeyWord" />" value="">
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-6"> <spring:message code="sr.solutionKbNo" />
							</label>
							<div class="col-md-6">
								<input type="text" id="txtSolutionkbNo" name="txtSolutionkbNo" class="form-control" placeholder="<spring:message code="sr.solutionKbNo"/>" value="">
							</div>
						</div>
					</div>
				</div>

				<!-- Row3  -->
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-6"> <spring:message code="sr.soltionTypeProblem1" />
							</label>
							<div class="col-md-6">
								<select id="cmbSoltionTypeProblem1" name="soltionTypeProblem1" class="form-control select2me">
								</select>
							</div>
						</div>
					</div>
					<div class="col-md-6">&nbsp;</div>
				</div>

				<!-- Row4  -->
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-6"> <spring:message code="sr.soltionTypeProblem2" />
							</label>
							<div class="col-md-6">
								<select id="cmbSoltionTypeProblem2" name="soltionTypeProblem2" class="form-control select2me">
								</select>
							</div>
						</div>
					</div>
					<div class="col-md-6">&nbsp;</div>
				</div>

				<!-- Row5  -->
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-6"> <spring:message code="sr.soltionTypeProblem3" />
							</label>
							<div class="col-md-6">
								<select id="cmbSoltionTypeProblem3" name="soltionTypeProblem3" class="form-control select2me">
								</select>
							</div>
						</div>
					</div>
					<div class="col-md-6">&nbsp;</div>
				</div>

				<!-- Row6  -->
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-6"> <spring:message code="sr.soltionTypeProblem4" />
							</label>
							<div class="col-md-6">
								<select id="cmbSoltionTypeProblem4" name="soltionTypeProblem4" class="form-control select2me">
								</select>
							</div>
						</div>
					</div>
					<div class="col-md-6">&nbsp;</div>
				</div>

				<!-- Row7  -->
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-6"> <spring:message code="sr.soltionTypeProblem5" />
							</label>
							<div class="col-md-6">
								<select id="cmbSoltionTypeProblem5" name="soltionTypeProblem5" class="form-control select2me">
								</select>
							</div>
						</div>
					</div>
					<div class="col-md-6"></div>
				</div>

			</div>

			<div class="form-actions right">
					<div class="btn-group">
						<button id="btn_searchTqKb" class="btn blue" type="button">
							<i class="fa fa-search"></i>
							<spring:message code="button.search.label" />
						</button>
					</div>
					
					<div class="btn-group">
						<button id="btn_cancelSearchCrit" class="btn default" type="button">
							<spring:message code="button.cancel.label" />
						</button>
					</div>
			</div>
		</form:form>
		<br />

		<div class="row">
			<div class="col-md-12">
				<div role="grid" class="dataTables_wrapper" id="grid_act_wrapper">
					<div class="table-scrollunable">
						<table id="gridSolution2List" class="table table-bordered">
						</table>
					</div>
				</div>
			</div>
		</div>
                  
		<form:form action="#" name="solutionCreateForm" id="solutionCreateForm" modelAttribute="srSolutionModelBean" class="form-horizontal">
			
			<div class="form-body">
			
			<div class="row">
				<div class="col-md-12">
						<div class="form-group">
							<div class="col-md-2">	
								<label class="control-label col-md-12"> <spring:message code="sr.solutionFaqKbType" />
								</label>
							</div>
							<div class="col-md-10">
								<form:input type="text" id="txtSolutionFaqKbType" path="solContentFaqKbType" class="form-control" readonly="true" value="" />
							</div>
						</div>
				</div>

			</div>
			
				<!-- Row1  -->
				<div class="row">
					<div class="col-md-12">
						<div class="form-group">
						<div class="col-md-2">	
							<label class="control-label col-md-12"> <spring:message code="sr.solutionKbNo" />
							</label>
						</div>	
							<div class="col-md-10">
								<form:input type="text" id="txtSolution2kbNo" path="solContentNumber" class="form-control" readonly="true" value="" />
							</div>
						</div>
					</div>

				</div>

				<!-- Row2  -->
				<div class="row">
					<div class="col-md-12">
						<div class="form-group">
							<div class="col-md-2">	
								<label class="control-label col-md-12"> <spring:message code="sr.solutionTitle" />
								</label>
							</div>
							<div class="col-md-10">
<!-- 								<input type="text" id="txtSolution2Title" name="solContentTitle" class="form-control" readonly="true" value=""> -->
								<textarea id='txtSolution2Title' name='solContentTitle' class="form-control" rows="3" maxlength="500" readonly="true"></textarea>
							</div>
						</div>
					</div>

				</div>

				<!-- Row3  -->
				<div class="row">
					<div class="col-md-12">
						<div class="form-group">
						<div class="col-md-2">	
							<label class="control-label col-md-12"> <spring:message code="sr.solutionAnswer" />
							</label>
						</div>	
							<div class="col-md-10">
								<textarea id='txtsolutionAnswer' name='solContentQuestion' class="form-control" rows="3" maxlength="500" readonly="true"></textarea>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-8">
						<div class="form-group">
							<label class="control-label col-md-6"><b> <spring:message code="sr.solutionAttachMentCaption" /></b> </label>
						</div>
					</div>
				</div>

				<hr />

				<div class="row">
					<div class="col-md-2"></div>
					<div class="col-md-8">
						<div class="form-group">
							<div role="grid" class="dataTables_wrapper" id="grid_act_wrapper">
								<table class="table table-bordered" id="gridSolution3List">
								</table>
							</div>
						</div>
					</div>
					<div class="col-md-2"></div>
				</div>
			</div>

			<div class="form-actions right">
				<div class="btn-group">
				
					<locus:privilege oper="ADD">
						<button id="btn_sr_sol_create" name="btn_sr_sol_create" class="btn blue" type="button">
							<i class="fa fa-plus"></i>
							<spring:message code="button.create.add" />
						</button>
					</locus:privilege>
					
				</div>
			
			</div>
		</form:form>



	</div>
</div>

<script type="text/javascript">
	var messageTitle = '<spring:message code="sr.kbSolutionsTab" />';
	var messageSearchType = '<spring:message code="sr.message.searchType" />';
	var messageKbNo = '<spring:message code="sr.message.please.kbno" />';
	var recordPerPage = '<spring:message code="datatable.recordPerPage" />';
	
	var serviceRequestTitle = '<spring:message code="serviceRequest.title" />';
	var msgDuplicateSrContent = '<spring:message code="sr.solutionDuplicateInsert" />';
	
	var AJAX_TIMEOUT = '1000000';
	var plaseSelectMsg = "";
	var firstTime = true;
	var solution2JsonData = [];
	var columsSolution1List = [];
	var columsSolution2List = [];
	var columsSolution3List = [];

	var solutions1ListDT;
	var solutions2ListDT;
	var solutions3ListDT;
	
	var srNumberRefDocNo  = '<c:out value="${srSolutionModelBean.srRefDocNo}"/>';	
	var refStatusCd  = '<c:out value="${srSolutionModelBean.refStatusCd}"/>';
	
	var refStatusCdTemp=""; 
	var srContentIdCurrent = "";
	var contentNumberCurrent = "";
	var contentTitleCurrent = "";
	var contentStatusCdCurrent = "";
	
	var compArrDisableTQ =["txtSoltionKeyWord","cmbSoltionTypeProblem1","cmbSoltionTypeProblem2"
  	                     ,"cmbSoltionTypeProblem3","cmbSoltionTypeProblem4","cmbSoltionTypeProblem5"];
	var compArrDisableKB =["txtSolutionkbNo"]; 
	
	var comArrButton = ["rdoSolSearchTQ","rdoSolSearchKB","btn_searchTqKb","btn_cancelSearchCrit"];
	
	
	
	$(document).ready(function() {
		
		refStatusCdTemp = refStatusCd;
		
		setDefaultDisableScreen();
		
		var compArrDisable =["btn_sr_sol_create"];
		setComponentDisableById(compArrDisable,true); // Disable 
		
		declareSolution1ListColumn();
		declareSolution2ListColumn();
		declareSolution3ListColumn();

		//DisplayTooltips
		dataTableOnMouseoverTooltips("gridSolution1List");
		dataTableOnMouseoverTooltips("gridSolution2List");
		dataTableOnMouseoverTooltips("gridSolution3List");

		solutions1ListDT = createSolution1Datatable();
		solutions2ListDT = createSolution2Datatable();
		solutions3ListDT = createSolution3Datatable();
		
		solutions1ListDT = searchSRContentList(srNumberRefDocNo,firstTime);
		
		
		getTypeOfProblem1List(plaseSelectMsg);

		$("#cmbSoltionTypeProblem1").change(function() {

			getTypeOfProblem2ByParentIdList($(this).val(), '', plaseSelectMsg);

			$("#cmbSoltionTypeProblem3").html("");
			$("#cmbSoltionTypeProblem4").html("");
			$("#cmbSoltionTypeProblem5").html("");

			$("#cmbSoltionTypeProblem3").select2("val", null);
			$("#cmbSoltionTypeProblem4").select2("val", null);
			$("#cmbSoltionTypeProblem5").select2("val", null);

		});

		$("#cmbSoltionTypeProblem2").change(function() {

			getTypeOfProblem3ByParentIdList($(this).val(), '', plaseSelectMsg);

			$("#cmbSoltionTypeProblem4").html("");
			$("#cmbSoltionTypeProblem5").html("");
			$("#cmbSoltionTypeProblem4").select2("val", null);
			$("#cmbSoltionTypeProblem5").select2("val", null);

		});

		$("#cmbSoltionTypeProblem3").change(function() {

			getTypeOfProblem4ByParentIdList($(this).val(), '', plaseSelectMsg);

			$("#cmbSoltionTypeProblem5").html("");
			$("#cmbSoltionTypeProblem5").select2("val", null);

		});

		$("#cmbSoltionTypeProblem4").change(function() {
			getTypeOfProblem5ByParentIdList($(this).val(), '', plaseSelectMsg);

		});
		
		
		$("input[type=radio][name=rdoSolSearchType]").change(function(e) {
			e.preventDefault();

			if (this.value == 'TQ') {
	        	
	        	$("#txtSolutionkbNo").val("");
	        	
	        	setComponentDisableById(compArrDisableTQ,false); //Enable	        	
	        	setComponentDisableById(compArrDisableKB,true); //Disable
	        	
	        }else if (this.value == 'KB') { 	
	        	
	        	$("#txtSoltionKeyWord").val("");
	        	$("#cmbSoltionTypeProblem1").select2("val","");
	        	$("#cmbSoltionTypeProblem2").select2("val","");
	        	$("#cmbSoltionTypeProblem3").select2("val","");
	        	$("#cmbSoltionTypeProblem4").select2("val","");
	        	$("#cmbSoltionTypeProblem5").select2("val","");
	        	
	        	setComponentDisableById(compArrDisableKB,false); //Enable
				setComponentDisableById(compArrDisableTQ,true);  //Disable
	        }
	    });
		
		
		$("#txtSoltionKeyWord").onEnter(function() {
			searchDataFromTqKb();
		});
		
		$("#txtSolutionkbNo").onEnter(function() {
			searchDataFromTqKb();
		});
		

		$("#btn_searchTqKb").click(function(e) {
			e.preventDefault();
			searchDataFromTqKb();
		});

		$("#btn_cancelSearchCrit").click(function(e) {
			e.preventDefault();
			clearCriteriaSearchTqKb();
		});
		
		$("#btn_sr_sol_create").click(function(e){
			e.preventDefault();
			var currentIdChk = srContentIdCurrent;
			var curContentId = checkDuplicateServiceReqContentInDataTable(currentIdChk);
			
			if(!curContentId){
				insertServiceRequestContent();	
			}else{
				
				alertMessage("<h5>"+messageTitle+"</h5>","<h6> "+msgDuplicateSrContent+"  <br/>  "	+contentNumberCurrent+"</h6>");
				
				return false;
			}
			
		});
		

		$("#gridSolution2List tbody tr").die("click");
		$('#gridSolution2List tbody tr').live('click touchstart', function(e) {
			e.preventDefault();
			var aPos = solutions2ListDT.fnGetPosition(this);

			if (empty(aPos)) {
				return false;
			}

			var aData = solutions2ListDT.fnGetData(aPos[0]);
			solution2JsonData = aData[aPos]; // Setting temporary
			
			contentNumberCurrent = aData[aPos].solContentNumber;
			contentTitleCurrent = aData[aPos].solContentTitle;
			contentStatusCdCurrent = aData[aPos].solContentStatusCd;
			
			var compArrDisable =["btn_sr_sol_create"];
			var searchFlag = aData[aPos].searchFlag;
			
			if(searchFlag == "TQ"){
				if(!empty(contentNumberCurrent)){
					searchThaiQuestDetail(contentNumberCurrent);	
				}else{
					setComponentDisableById(compArrDisable,true); // Disable
				}
				
			}else if(searchFlag == "KB"){
				srContentIdCurrent= aData[aPos].solContentId;
				 
				setFormScreenOnSelectDatable("solutionCreateForm", aData[aPos]);
				if(!empty(contentStatusCdCurrent)){
					if(contentStatusCdCurrent == "03"){
						setComponentDisableById(compArrDisable,false); // Enable 
					}else{
						setComponentDisableById(compArrDisable,true); // Disable
					}
				}else{
					setComponentDisableById(compArrDisable,true); 	//Disable
				}
			}
			
			

		});

		$("#gridSolution3List tbody tr").die("click");
		$('#gridSolution3List tbody tr').live('click touchstart', function (e)  {
			e.preventDefault();
			
			var aPos = solutions3ListDT.fnGetPosition(this);
			if (empty(aPos)) {
				return false;
			}

			onRowSelected($(this), solutions3ListDT);

		});
	
		
		setScreenSolByRefStatusCode();
	});
	
	function searchThaiQuestDetail(contentNumber){
		
		var compArrDisable =["btn_sr_sol_create"];
		
		$.ajax({
			timeout : AJAX_TIMEOUT,
			url : "searchThaiQuestDetail.htm",
			dataType : "json",
			data : "contentNumber="+contentNumber,
			type : "post",
			success : function(data, textStatus, jqXHR) {
				
				srContentIdCurrent = data.model.solContentId; 
				contentStatusCdCurrent = data.model.solContentStatusCd;
				setFormScreenByModelBean("solutionCreateForm", data);
				
				if(!empty(contentStatusCdCurrent)){
					if(contentStatusCdCurrent == "03"){
						setComponentDisableById(compArrDisable,false); // Enable 
					}else{
						setComponentDisableById(compArrDisable,true); // Disable
					}
				}else{
					setComponentDisableById(compArrDisable,true); 	//Disable
				}
			 
				searchContentKbAttachmentByContentId(srContentIdCurrent);

			},
			error : function(xhr, textStatus, errorThrown) {

				checkShowMsgAjaxError(xhr, textStatus, errorThrown);

			}
		});
		
	}
	
	
	function setScreenSolByRefStatusCode(){
		
		/**
			Service Request Status is Close = 03 
		**/
		
		if(!empty(srNumberRefDocNo)){
			
			if(!empty(refStatusCd)){
				
				if(refStatusCd == "03"){
					
					setComponentDisableById(compArrDisableKB,true); //Disable
					setComponentDisableById(compArrDisableTQ,true);  //Disable
					setComponentDisableById(comArrButton,true);  //Disable
							
				}else{				
					
					//Not Equals  Service Request Close
					setComponentDisableById(compArrDisableKB,false); //Enable
					setComponentDisableById(compArrDisableTQ,true);  //Disable	
					setComponentDisableById(comArrButton,false);  //Disable
				}
				
				
			} 
			
		}else{
			// When Click New Service Request  
			setComponentDisableById(compArrDisableKB,true); //Disable
			setComponentDisableById(compArrDisableTQ,true);  //Disable
			setComponentDisableById(comArrButton,true);  //Disable
			
			
		}
		
		
	
		
		
	}
	
	function setDefaultDisableScreen(){
		
		$("#txtSoltionKeyWord").val("");
    	$("#cmbSoltionTypeProblem1").select2("val","");
    	$("#cmbSoltionTypeProblem2").select2("val","");
    	$("#cmbSoltionTypeProblem3").select2("val","");
    	$("#cmbSoltionTypeProblem4").select2("val","");
    	$("#cmbSoltionTypeProblem5").select2("val","");
			 
		setComponentDisableById(compArrDisableKB,false); //Enable
		setComponentDisableById(compArrDisableTQ,true);  //Disable
			
			
	}
	
	function checkDuplicateServiceReqContentInDataTable(currentContentIdChk){
		

			 var oTable = $('#gridSolution1List').dataTable();
			 var count = Object.keys(oTable.fnGetData()).length;
			 
			// console.log("myData count ::: "+count);
			 var myData = oTable.fnGetData();
			 var flgDup = false;
			 
			 for (var i = 0; i < count ; i++){			 
				 	
				 	//console.log(i+") : Content_ID = "+myData[i].srContentId);
					var srContentIdGrd = myData[i].srContentId;

					if(currentContentIdChk == srContentIdGrd){
				 		flgDup = true;
				 		//console.log("+++ BREAK :: Duplication +++");
				 		break;
				 		return flgDup;
				 	}
			 }
			 
			 return flgDup;
	}
	
	
	function insertServiceRequestContent(){
			
			//alert("srNumberRefDocNo : "+srNumberRefDocNo +"\n srContentId :"+srContentIdCurrent);
		
			jLoBlockUI();
			$.ajax({
				timeout: AJAX_TIMEOUT,
				url : "insertSeriviceRequestContent.htm",
				type: "POST",
		        data: "srNumber="+srNumberRefDocNo+"&srContentId="+srContentIdCurrent,
		    	dataType: "json",
				success: function(data, textStatus, jqXHR){
					
					alertMessage("<h5>"+messageTitle+"</h5>","<h6>"+data.resultMessage+"</h6>");	
					
					if(data.resultCode == "0"){
						searchSRContentList(srNumberRefDocNo,firstTime);
					}else{
						checkShowMsgAjaxError(jqXHR, textStatus, data.resultMessage);
						return false;
					}
					
				},error: function (xhr, textStatus, errorThrown) { 			
					checkShowMsgAjaxError(xhr, textStatus, errorThrown);
			 	}			
			
			});
	}
	
	function searchSRContentList(referenceNo,firstTime){
		
		var referenceNoParam = "srNumber="+referenceNo;
		return ajaxDataTableSetCallback($('#gridSolution1List'), columsSolution1List, 'inquirySRContentList.htm', referenceNoParam, recordPerPage, true, true,[],firstTime);
	}
	
	function deleteSrContent(contentId){
		
		alertConfirm(confirmDeleteMsg, "deleteSrContentByKeyRef", contentId);
		
	}
	
	function deleteSrContentByKeyRef(contentId){
			
			$.ajax({
				timeout: AJAX_TIMEOUT,
				url : "deleteSrContentByKeyRef.htm",
				type: "POST",
		        data:  "srNumber="+srNumberRefDocNo+"&srContentId="+contentId,
	      		dataType: 'json',
				success: function(data, textStatus, jqXHR){	 
				 
					if(data.resultCode == "0"){
						 
						alertMessage("<h5>"+messageTitle+"</h5>","<h6>"+data.resultMessage+"</h6>");
						
						searchSRContentList(srNumberRefDocNo,firstTime);
					 
						
					}else{
						
						checkShowMsgAjaxError(jqXHR, textStatus, data.resultMessage);
						return false;
					}
				
				},error: function (xhr, textStatus, errorThrown) { 
					
					checkShowMsgAjaxError(xhr, textStatus, errorThrown);
			 	}			
		
		});
		
	}
	
	
	function searchContentKbAttachmentByContentId(contentId) {
		var contentIdParam = "contentId=" + (!empty(contentId) ? contentId : -1) ;
		return getJsonAttKbDataTable($('#gridSolution3List'), "inquirySrSolContentKBAtt.htm", contentIdParam, "post");

	}
	function getJsonAttKbDataTable(objTable, postUrl, param, method) {

		//jLoBlockUI();

		$.ajax({
			timeout : AJAX_TIMEOUT,
			url : postUrl,
			dataType : 'json',
			data : param,
			type : method,
			success : function(data, textStatus, jqXHR) {
				/*
				 if dataType : text 
				 must be convert >> JSON.parse(data);
				 */
				objTable.dataTable().fnClearTable();
				objTable.dataTable().fnAddData(data);

				solutions3ListDT = objTable.dataTable();

				//totalAttRecord = data.length;

			},
			error : function(xhr, textStatus, errorThrown) {

				checkShowMsgAjaxError(xhr, textStatus, errorThrown);

			}
		});
	}

	function searchDataFromTqKb() {
		
		clearFormSolutionCreat();
		
		var criteriaData = $("#solutionSearchForm").serialize();
		var isChecked = $("input[name=rdoSolSearchType]:checked").val();
		
		var compArrDisable = ["btn_sr_sol_create"];
		
		if (!isChecked) {
			alertMessage(messageTitle, messageSearchType);
			return false;
		} else {
			
			if (isChecked == "TQ") {
				
				var solutionKbKeyWord =  $("#txtSoltionKeyWord").val();
				var cmbSoltionTypeProblem1=	$("#cmbSoltionTypeProblem1").val();
				
				//alert(solutionKbKeyWord+"\n "+cmbSoltionTypeProblem1);
				
				if (!empty(solutionKbKeyWord) || !empty(cmbSoltionTypeProblem1)) {
					solutions2ListD = searchDataFromThaiQuestKnowledgeBase(criteriaData,compArrDisable); 
					
				}else{
					
					alertMessage(messageTitle, "กรุณากรอก  คำค้น หรือ ประเภทของปัญหาระดับที่ 1");
					return false;
					
				}
				
				
				 
				
			} else if (isChecked == "KB") {

				var contentNumber = $("#txtSolutionkbNo").val();
				if (!empty(contentNumber)) {
					 
					solutions2ListD = searchDataFromThaiQuestKnowledgeBase(criteriaData,compArrDisable); 
					
				} else {
					alertMessage(messageTitle, messageKbNo);
					$("#txtSolutionkbNo").focus();
					return false;
				}

			}
		}
	}

	function searchDataFromThaiQuestKnowledgeBase(criteriaData,compArrDisable){
		jLoBlockUI();
		return  ajaxDataTableSetCallback($('#gridSolution2List'), columsSolution2List, 'inquirySearchDataFromTqKbByCriteria.htm', criteriaData, recordPerPage, true, true, compArrDisable, firstTime);
	}
	
	function clearCriteriaSearchTqKb() {
		$("#solutionSearchForm").clearForm();
	//	$("#solutionSearchForm input").val("");
	 	setRadioValue("rdoSolSearchType", "KB");
	 	$("#txtSoltionKeyWord").val("");
    	$("#cmbSoltionTypeProblem1").select2("val","");
    	$("#cmbSoltionTypeProblem2").select2("val","");
    	$("#cmbSoltionTypeProblem3").select2("val","");
    	$("#cmbSoltionTypeProblem4").select2("val","");
    	$("#cmbSoltionTypeProblem5").select2("val","");
    	
    	setComponentDisableById(compArrDisableKB,false); //Enable
		setComponentDisableById(compArrDisableTQ,true);  //Disable
	}
	
	function clearFormSolutionCreat(){
		
		$("#solutionCreateForm").clearForm();
	}
	
	function getTypeOfProblem1List(plaseSelectMsg) {
		getTypeProblem1ListDefaultVal($("#cmbSoltionTypeProblem1"), "", plaseSelectMsg);
	}

	function getDataSearchThaiQuest(objTable, postUrl, param, method) {

		//jLoBlockUI();

		$.ajax({
			timeout : AJAX_TIMEOUT,
			url : postUrl,
			dataType : 'json',
			data : param,
			type : method,
			success : function(data, textStatus, jqXHR) {
				/*
				 if dataType : text 
				 must be convert >> JSON.parse(data);
				 */

				objTable.dataTable().fnClearTable();
				objTable.dataTable().fnAddData(data);

				solutions2ListD = objTable.dataTable();

				var totalAttRecord = data.length;

			},
			error : function(xhr, textStatus, errorThrown) {

				checkShowMsgAjaxError(xhr, textStatus, errorThrown);

			}
		});
	}
	
	

	function getTypeOfProblem2ByParentIdList(parentCatId, defaultValueJsp, plaseSelectMsg) {

		getTypeProblem2ByParentIdListDefaultVal($("#cmbSoltionTypeProblem2"), parentCatId, defaultValueJsp, plaseSelectMsg);
	}

	function getTypeOfProblem3ByParentIdList(parentCatId, defaultValueJsp, plaseSelectMsg) {

		getTypeProblem3ByParentIdListDefaultVal($("#cmbSoltionTypeProblem3"), parentCatId, defaultValueJsp, plaseSelectMsg);
	}

	function getTypeOfProblem4ByParentIdList(parentCatId, defaultValueJsp, plaseSelectMsg) {

		getTypeProblem4ByParentIdListDefaultVal($("#cmbSoltionTypeProblem4"), parentCatId, defaultValueJsp, plaseSelectMsg);
	}

	function getTypeOfProblem5ByParentIdList(parentCatId, defaultValueJsp, plaseSelectMsg) {

		getTypeProblem5ByParentIdListDefaultVal($("#cmbSoltionTypeProblem5"), parentCatId, defaultValueJsp, plaseSelectMsg);
	}

	function createSolution1Datatable() {

		return initajaxDataTable($('#gridSolution1List'), columsSolution1List);
	}

	function createSolution2Datatable() {

		return initajaxDataTable($('#gridSolution2List'), columsSolution2List);
	}

	function createSolution3Datatable() {

		return initajaxDataTable($('#gridSolution3List'), columsSolution3List);
	}

	function declareSolution1ListColumn() {

		var col_no = '<center><spring:message code="sr.solutionNo"/></center>';
		var col_solutionKbNo = '<center><spring:message code="sr.solutionKbNo"/></center>';
		var col_solutionFaqKbType = '<center><spring:message code="sr.solutionFaqKbType"/></center>';
		var col_soltionTypeProblem1 = '<center><spring:message code="sr.soltionTypeProblem"/></center>';
		var col_solutionKbTitle = '<center><spring:message code="sr.solutionKbTitle"/></center>';
		var col_solutionAction = '<center><spring:message code="sr.solutionAction"/></center>';
		
		
		
		columsSolution1List = [ {
			"sTitle" : col_no,
			"mData" : "srContentNumber",
			"sClass" : "tdCenter",
			"sWidth" : "3%"
		}, {
			"sTitle" : col_solutionKbNo,
			"mData" : "srContentNumber",
			"sClass" : "tdLeft",
			"sWidth" : "10%"
		}, {
			"sTitle" : col_solutionFaqKbType,
			"mData" : "contentFaqKbType",
			"sClass" : "tdCenter",
			"sWidth" : "10%"
		}, {
			"sTitle" : col_soltionTypeProblem1,
			"mData" : "contentCat1Name",
			"sClass" : "tdLeft",
			"sWidth" : "30%"
		}, {
			"sTitle" : col_solutionKbTitle,
			"mData" : "contentTitle",
			"sClass" : "tdLeft",
			"sWidth" : "30%",
		}, {
			"sTitle" : col_solutionAction,
			"mData" : null,
			"sClass" : "tdCenter",
			"sWidth" : "5%",
			"fnRender" : function(objData) {
				var solContentId = objData.aData.srContentId; 
				var deleteFlag = objData.aData.deleteUrl;
				
				if(refStatusCd == "03"){ // ServiceRequest Reference  Status
					return "<i class='fa fa-trash-o'></i>";
					
				}else{
					
					if(!empty(deleteFlag)){
						if(deleteFlag == "DELETE"){							
							return "<a href='#' onclick='javascript:deleteSrContent("+solContentId+");' title='Delete'><i class='fa fa-trash-o'></i></a>";
						}else{
							return "<i class='fa fa-trash-o'></i>";
						}
					}else{
						return "<i class='fa fa-trash-o'></i>";
					}					
					
				}
				
			}

		} ];

	}

	function declareSolution2ListColumn() {

		var col_solutionNo = '<center><spring:message code="sr.solutionNo"/></center>';
		var col_solutionKbNo = '<center><spring:message code="sr.solutionKbNo"/></center>';
		var col_solutionFaqKbType = '<center><spring:message code="sr.solutionFaqKbType"/></center>';
		var col_soltionSearchResult = '<center><spring:message code="sr.soltionSearchResult"/></center>';
		var col_solutionUseNo = '<center><spring:message code="sr.solutionNumberOfUse"/></center>';

		columsSolution2List = [ {
			"sTitle" : col_solutionNo,
			"mData" : "solContentFaqKbType",
			"sClass" : "tdCenter",
			"sWidth" : "3%"
		}, {
			"sTitle" : col_solutionKbNo,
			"mData" : "solContentNumber",
			"sClass" : "tdLeft",
			"sWidth" : "10%"
		}, {
			"sTitle" : col_solutionFaqKbType,
			"mData" : "solContentFaqKbType",
			"sClass" : "tdCenter",
			"sWidth" : "10%"
		}, {
			"sTitle" : col_soltionSearchResult,
			"mData" : "solutionResult",
			"sClass" : "tdLeft",
			"sWidth" : "40%"
		} ];

	}

	function declareSolution3ListColumn() {

		var col_no = '<center><spring:message code="sr.solutionNo"/></center>';
		var col_solutionDocumentName = '<center><spring:message code="sr.solutionDocumentName"/></center>';
		var col_solutionCreateBy = '<center><spring:message code="sr.solutionCreateBy"/></center>';
		var col_solutionCreateDate = '<center><spring:message code="sr.solutionCreateDate"/></center>';
		var col_solutionAction = '<center><spring:message code="sr.solutionAction"/></center>';

		columsSolution3List = [ {

			"sTitle" : col_no,
			"mData" : "no",
			"sClass" : "tdCenter",
			"sWidth" : "3%"
		}, {
			"sTitle" : col_solutionDocumentName,
			"mData" : "title",
			"sClass" : "tdLeft",
			"sWidth" : "40%"
		}, {
			"sTitle" : col_solutionCreateBy,
			"mData" : "regBy",
			"sClass" : "tdLeft",
			"sWidth" : "10%"
		}, {
			"sTitle" : col_solutionCreateDate,
			"mData" : "regDtText",
			"sClass" : "tdCenter",
			"sWidth" : "10%",

		}, {
			"sTitle" : col_solutionAction,
			"mData" : null,
			"sClass" : "tdCenter",
			"sWidth" : "5%",
			"fnRender" : function(objData) {
				var attId = objData.aData.attId;  
				return "<a href='#' target='_blank' onclick='javascript:downloadKbAttDoc("+attId+");' title='Download Document'><i class='fa fa-download'></i></a>";
			}

		} ];

	}
	
	function downloadKbAttDoc(attId){
	 	var urlDowloadFile  = CONTEXT_PATH+"/downloadFile.htm?attId="+attId;
		window.location.href= urlDowloadFile;
		//window.open(urlDowloadFile, '_blank');
	}
 
	
</script>
