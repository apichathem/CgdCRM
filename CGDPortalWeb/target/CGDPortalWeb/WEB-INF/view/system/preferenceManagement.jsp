<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="locus" uri="/WEB-INF/tld/locus.tld"%>

<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="caption">
			<spring:message code="preferenceManagement.title" />
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="home.htm"> <spring:message code="menu.home" />
			</a> <i class="fa fa-angle-right"></i></li>
			<li><a href="#"> <spring:message code="menu.system" />
			</a> <i class="fa fa-angle-right"></i></li>
			<li><a href="#">  <spring:message code="menu.system.preferenceManagement" /> </a></li>
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>

<form:form id="prefFormId" action="getPreferenceList.htm" modelAttribute="prefModelBean" class="form-horizontal" autocomplete="off">
	<form:hidden id="modePref" path="mode"/>
	<div class="row">
		<div class="col-md-12">
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-reorder"></i>
						<spring:message code="preferenceManagement.topic" />
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
								<label class="control-label col-md-5"><spring:message code="preferenceManagement.criteria.prefName" /></label>
								<div class="col-md-7">
									<input type="text" name="prefNameCriteria" id="prefNameCriteria" class="form-control" />
								</div>
							</div>
							<div class="col-md-4">
								<label class="control-label col-md-5"><spring:message code="preferenceManagement.criteria.prefValue" /></label>
								<div class="col-md-7">
									<input type="text" name="prefValueCriteria" id="prefValueCriteria" class="form-control" />
								</div>
							</div>
							<div class="col-md-4">
							</div>
						</div>
					</div>
					
					<div class="form-actions right">
						<button class="btn blue" type="button" id="btnSearchPref">
							<i class="fa fa-search"></i>
							<spring:message code="button.search.label" />
						</button>
						<button class="btn default" type="button" id="btnCancelPref" >
							<spring:message code="button.cancel.label" />
						</button>
						<button class="btn green" type="button" id="btnCreatePref" >
							<i class="fa fa-plus"></i>
							<spring:message code="button.create.label" />
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="row">
		<div class="col-md-12">
			<table id="resultTableId" class="table table-bordered dataTable">
			</table>
		</div>
	</div>
	<br>
	
	<div class="row">
		<div class="col-md-12">
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-reorder"></i>
						<spring:message code="preferenceManagement.detail.topic" />
					</div>
					<div class="tools">
						<a class="collapse" href="javascript:;"> </a>
					</div>
				</div>
				<div class="portlet-body form">
					<div class="form-body">
						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-5"> <spring:message code="preferenceManagement.detail.prefId" /></label>
									<div class="col-md-7">
										<form:input path="prefId" class="form-control" readonly="true"/>
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-5"> <spring:message code="preferenceManagement.detail.prefName" /> <span
										class="required"> * </span>
									</label>
									<div class="col-md-7">
										<form:input path="prefName" class="form-control" data-rule-maxlength="50" data-rule-required="true" data-msg-required="Please enter preference name."/>
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-5"> <spring:message code="preferenceManagement.detail.prefValue" /> <span
										class="required"> * </span>
									</label>
									<div class="col-md-7">
										<form:input path="prefValue" class="form-control" data-rule-maxlength="50" data-rule-required="true" data-msg-required="Please enter preference value."/>
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-5"> <spring:message code="preferenceManagement.detail.etc1" />
									</label>
									<div class="col-md-7">
										<input type="text" name="etc1" id="etc1" class="form-control" />
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-5"> <spring:message code="preferenceManagement.detail.etc2" />
									</label>
									<div class="col-md-7">
										<input type="text" name="etc2" id="etc2" class="form-control" />
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-5"> <spring:message code="preferenceManagement.detail.etc3" />
									</label>
									<div class="col-md-7">
										<input type="text" name="etc3" id="etc3" class="form-control" />
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-5"> <spring:message code="preferenceManagement.detail.description" />
									</label>
									<div class="col-md-7">
										<textarea name="descp" id="descp"  class="form-control"></textarea>
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
								<label class="control-label col-md-5"> <spring:message code="preferenceManagement.detail.enabled" /></label>
									<div class="col-md-7">
										<div class="radio-list">
											<label class="radio-inline">
												<div class="radio">
													<form:radiobutton id='rdoUseYn_y' name="enabledFlag" path="enabledFlag" value="Y" />
												</div><spring:message code="lbl.yes" />
											</label> 
											<label class="radio-inline">
												<div class="radio">
													<form:radiobutton  id='rdoUseYn_n' name="enabledFlag" path="enabledFlag" value="N" />
												</div><spring:message code="lbl.no" />
											</label>
										</div>	
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
								</div>
							</div>
						</div>
						<hr>
						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-5"> <spring:message code="lbl.createBy" />
									</label>
									<div class="col-md-7">
										<p id="createBy" class="form-control-static"></p>
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-5"> <spring:message code="lbl.createDate" />
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
						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-5"> <spring:message code="lbl.updateBy" />
									</label>
									<div class="col-md-7">
										<p id="updateBy" class="form-control-static"></p>
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-5"> <spring:message code="lbl.updateDate" />
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
					<div class="form-actions right">
						<button class="btn blue" type="button" id="btn_savePref">
							<i class="fa fa-floppy-o"></i>
							<spring:message code="button.save.label" />
						</button>
						<button class="btn default" type="button" id="btn_cancelPref">
							<spring:message code="button.cancel.label" />
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</form:form>

<script type="text/javascript">

	var prefJsonData = "";
	var prefListDT = "";
	var firstTime = true;
	var saveModeClickTemp = "insert";
	var prefTitle = '<spring:message code="preference.title" />';

	var recordPerPage = parseInt('<spring:message code="datatable.recordPerPage" />');
	var column1 = '<spring:message code="preferenceManagement.result.prefId" />';
	var column2 = '<spring:message code="preferenceManagement.result.prefName" />';
	var column3 = '<spring:message code="preferenceManagement.result.prefValue" />';
	var column4 = '<spring:message code="preferenceManagement.result.description" />';
	var column5 = '<spring:message code="preferenceManagement.result.createBy" />';
	
	var columns = [{ "sTitle": column1, "mData": "prefId", sClass: "text-center", "sWidth": "3%" }
				  ,{ "sTitle": column2, "mData": "prefName" }
				  ,{ "sTitle": column3, "mData": "prefValue" }
				  ,{ "sTitle": column4, "mData": "descp" }
				  ,{ "sTitle": column5, "mData": "createBy", "sWidth": "13%"}
			  		];
	
	var txtValidates = ["prefName","prefValue"];
	
	$(document).ready(function() {
		
		createGrdPrefData();
				
		$('#btnSearchPref').on( 'click', function (e) {
			prefListDT = searchPreference();
		});
		
		$('#btnCancelPref').on( 'click', function (e) {
			cancelPreference();
		});
		
		$('#btnCreatePref').on( 'click', function (e) {
			initPref();
		});
		
		$('#btn_savePref').on( 'click', function (e) {
			savePreference();
		});
		
		$('#btn_cancelPref').on( 'click', function (e) {
			cancelPreferenceDetail();
		});
		
		$("#resultTableId tbody tr").live('click', function () {
			
			clearValidate(txtValidates);
			$('#modePref').val('update');
			firstTime = false;
			
			var aPos = prefListDT.fnGetPosition(this);
 	      	
 	      	if(aPos == null){
 				return false;    		
 	    	}
 	      	
 	      	var aData = prefListDT.fnGetData(aPos[0]);
 	      	prefJsonData = aData[aPos];
 	      	
			setFormScreenOnSelectDatable("prefFormId",aData[aPos]);
			setDetailPreference(aData[aPos]);
		});
		
		$("#prefNameCriteria").onEnter( function() {
			if(!empty($("#prefNameCriteria").val())){
				prefListDT = searchPreference();
			}
			
		});
		
		$("#prefValueCriteria").onEnter( function() {
			if(!empty($("#prefValueCriteria").val())){
				prefListDT = searchPreference();
			}
		});
	});
	
	function createGrdPrefData(){
		
		prefListDT = initajaxDataTable($('#resultTableId'), columns, true, true);
	}
	
	function searchPreference() {
		
		var dataString = $("#prefFormId").serialize();
		var compArr = [];
		
		return ajaxDataTableSetCallback($('#resultTableId'), columns, 'getPreferenceList.htm', dataString, recordPerPage, true, true, compArr, firstTime);
	}
	
 	function setDetailPreference(data){
		
		$('#createBy').html(data.createBy);
		$('#createDate').html(data.createDate);
		$('#updateBy').html(data.updateBy);
		$('#updateDate').html(data.updateDate);
		
		var enabledFlag = $.trim(data.enabledFlag);
		console.log(enabledFlag);
		setRadioValue("enabledFlag",enabledFlag);		 
		$('#modePref').val('update');
	}
	
	function initPref() {
		
		clearValidate(txtValidates);

		$('#prefFormId').clearForm();
		
		$('#modePref').val('insert');
		
		setRadioValue("enabledFlag",'Y');
		
		$('#createBy').html('');
		$('#createDate').html('');
		$('#updateBy').html('');
		$('#updateDate').html('');
		
	}
	
	function cancelPreference() {
		
		$('#prefNameCriteria').val('');
		$('#prefValueCriteria').val('');
		setRadioValue("enabledFlag",'Y');
	}
	
	function savePreference() {
		
		var modePrefTemp = $("#modePref").val();
		
		if(modePrefTemp  === "insert"){
			//insert
			$("#prefFormId").attr("action", "insertPreference.htm");
			
		}else if(modePrefTemp  === "update"){
			//update
			$("#prefFormId").attr("action", "updatePreference.htm");
		}
		
		saveModeClickTemp = modePrefTemp;
		
		var $valid = validateForm($("#prefFormId"));
		if (!$valid) {
			return false;
		}
		
		ajaxSubmitForm($("#prefFormId"), function(data){
			
			prefJsonData = data;
			
			alertMessage("<h6>"+prefTitle+"</h6>","<h5>"+data.resultMessage+"</h5>");
			
			if(data.resultCode == "0"){
				
				if(saveModeClickTemp === "insert"){ 	 			
					prefListDT = searchPreference();  			
	 	 		}else{
	 	 			setPageChangeCurrent(prefListDT);
	 	 		}
				
				setFormScreenByModelBean("prefFormId", prefJsonData);
				setDetailPreference(prefJsonData.model);
				
			}else{
				return false;
			}
		});
	}
 
	function cancelPreferenceDetail() {
		
		initPref();
		clearValidate(txtValidates);
	}
</script>