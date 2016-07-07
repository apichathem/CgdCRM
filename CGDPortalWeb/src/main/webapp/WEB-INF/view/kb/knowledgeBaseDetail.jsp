<%@page import="java.util.HashMap"%>
<%@page import="com.locus.common.constant.WebPortalConstant"%>
<%@page import="com.locus.jlo.web.bean.dto.UserInfoDTO"%>
<%@page import="com.locus.jlo.web.constant.JLOWebConstant"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="locus" uri="/WEB-INF/tld/locus.tld"%>
<% 
		UserInfoDTO userInfoDTO = (UserInfoDTO) request.getSession().getAttribute(WebPortalConstant.USER_PROFILE);
		String teamList = userInfoDTO.getMyTeamUserIdText();
		String userId = userInfoDTO.getUserId().toString();
		
		HashMap< String, HashMap<String, String> > privilegeMap = (HashMap< String, HashMap<String, String> >) request.getSession().getAttribute(WebPortalConstant.PRIVILEGE_PROFILE);
		String menuId = (String) request.getSession().getAttribute(JLOWebConstant.SESSION_MENU_ID);
		String actionValue = request.getParameter(JLOWebConstant.ACTION_VALUE);
		String approveType = "";
		if (privilegeMap != null) {
			HashMap<String, String> priv = privilegeMap.get(menuId);
			approveType = priv.get("APPROVE");
		}
%>
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="caption">
			<spring:message code="menu.knowledgebase.knowledgebaseDetailManagement" />
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="home.htm"> <spring:message code="menu.home" />
			</a><i class="fa fa-angle-right"></i></li>
			<li><a href="#"> <spring:message code="menu.knowledgebase" />
			</a> <i class="fa fa-angle-right"></i></li>
			<li><a href="#"> <spring:message code="menu.knowledgebase.knowledgebaseDetailManagement" />
			</a></li>
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>

<!-- BEGIN CRITERIA -->
<input id="teamIds" type="hidden" value="<%= teamList%>" />
<input id="approveType" type="hidden" value="<%= approveType%>" />
<input id="userId" type="hidden" value="<%= userId%>" /> 

<form:form id="knowledgeBaseForm" modelAttribute="" class="form-horizontal">
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<!-- BEGIN TITLE CRITERIA -->
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-reorder"></i>
					<spring:message code="knowledge.detail.criteria.title" />
				</div>
				<div class="tools">
					<a class="collapse" href="javascript:;"></a>
				</div>
			</div>
			<!-- END TITLE CRITERIA-->

			<!-- BEGIN PORTLET BODY OF CRITERIA -->
			<div class="portlet-body form">
					<div class="form-body">
						<!-- Row 2 -->
						<div class="row">		
							<div class="col-md-6">
								<div class="form-group">
									<label class="control-label col-md-5">
										<spring:message code="knowledge.detail.criteria.kbno"/>
									</label>
									<div class="col-md-7">
										<input id="contentNumber" name="contentNumber" type="text" class="form-control" value="${contentNumber}" />
									</div>
								</div>
							</div>
						 	<div class="col-md-6">
								<div class="form-group">
									<label class="control-label col-md-5">
										<spring:message code="knowledge.detail.criteria.kbType1"/>
									</label>
									<div class="col-md-7">
										<select name="contentCat1Id" id="contentCat1Id" class="form-control select2me">
										</select>
									</div>
								</div>
							</div>
						
						</div>
						<!--  Row 3 -->
						<div class="row">
							<div class="col-md-6">
								  <div class="form-group">
									<label class="control-label col-md-5">
										<spring:message code="knowledge.detail.criteria.keyword"/>
									</label>
									<div class="col-md-7">
										<input id="keyword" name="keyword" type="text" class="form-control" />
									</div>
								</div>  
							</div>
							<div class="col-md-6">
								  <div class="form-group">
									<label class="control-label col-md-5">
										<spring:message code="knowledge.detail.criteria.kbType2"/>
									</label>
									<div class="col-md-7">
										<select name="contentCat2Id" id="contentCat2Id" class="form-control select2me">
										</select>
									</div>
								</div>  
							</div>
						</div>
						<!--  Row 4 -->
						<div class="row">
							<div class="col-md-6">
								  <div class="form-group">
									<label class="control-label col-md-5">
									</label>
									<div class="col-md-7">
									</div>
								</div>  
							</div>
							<div class="col-md-6">
								  <div class="form-group">
									<label class="control-label col-md-5">
										<spring:message code="knowledge.detail.criteria.kbType3"/>
									</label>
									<div class="col-md-7">
										<select name="contentCat3Id" id="contentCat3Id" class="form-control select2me">
										</select>
									</div>
								</div>  
							</div>
						</div>
						<!--  Row 5 -->
						<div class="row">
							<div class="col-md-6">
								  <div class="form-group">
									<label class="control-label col-md-5">
									</label>
									<div class="col-md-7">
									</div>
								</div>  
							</div>
							<div class="col-md-6">
								  <div class="form-group">
									<label class="control-label col-md-5">
										<spring:message code="knowledge.detail.criteria.kbType4"/>
									</label>
									<div class="col-md-7">
										<select name="contentCat4Id" id="contentCat4Id" class="form-control select2me">
										</select>
									</div>
								</div>  
							</div>
						</div>
						<!--  Row 5 -->
						<div class="row">
							<div class="col-md-6">
								  <div class="form-group">
									<label class="control-label col-md-5">
									</label>
									<div class="col-md-7">
									</div>
								</div>  
							</div>
							<div class="col-md-6">
								  <div class="form-group">
									<label class="control-label col-md-5">
										<spring:message code="knowledge.detail.criteria.kbType5"/>
									</label>
									<div class="col-md-7">
										<select name="contentCat5Id" id="contentCat5Id" class="form-control select2me">
										</select>
									</div>
								</div>  
							</div>
						</div>
					</div>

					<!-- BEGIN SERCH BUTTON GROUP -->
					<div class="form-actions right">
						<button id="searchContentBtn" class="btn blue" type="button">
							<i class="fa fa-search"></i>
							<spring:message code="button.search.label"/>
						</button>
						<button class="btn default" type="button" onclick="clearKbDetailCriteria();">
							<spring:message code="button.cancel.label"/>
						</button>
						<locus:privilege oper="ADD">
							<button class="btn green" type="button" onclick="createKbDetail();">
								<i class="fa fa-plus"></i>
								<spring:message code="button.create.label"/>
							</button>
						</locus:privilege>
					</div>
					<!-- END SERCH BUTTON GROUP -->
			</div>
			<!-- END PORTLET BODY OF CRITERIA -->
		</div>
	</div>
</div>
</form:form>
<!-- END CRITERIA -->

<!-- BEGIN TABLE LIST -->
<div class="row" id="div_table_list">
	<div class="col-md-12">
		<div class="portlet-body">
			<div class="portlet-body">
				<table class="table table-bordered" id="kbListResultTbl"></table>
			</div>
		</div>
	</div>
</div>
<!-- END TABLE LIST -->

<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue form-horizontal">
			<!-- BEGIN TITLE CRITERIA -->
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-reorder"></i>
					<spring:message code="knowledge.detail" />
				</div>
				<div class="tools">
					<a class="collapse" href="javascript:;"></a>
				</div>
			</div>
			<!-- END TITLE CRITERIA-->

			<!-- BEGIN PORTLET BODY OF CRITERIA -->
			<div class="portlet-body">
				<ul class="nav nav-tabs">
					<li id="tabInfo" class="active">
						<a href="#tab_main_detail" data-toggle="tab">
							 <spring:message code="knowledge.tab.info" />
						</a>
					</li>
					<li id="tabAtt" class="">
						<a href="#tab_attachfile" data-toggle="tab">
							 <spring:message code="knowledge.tab.attachfile" />
						</a>
					</li>
				</ul>
				<div class="tab-content">
					<div class="tab-pane fade active in" id="tab_main_detail">
						<div class="form">
							<div class="form-body">
								<form:form id="kbDetailForm" modelAttribute="categoryInfoModel">
									<div class="row">
										<div class="col-md-4">
											<div class="form-group">
												<label class="control-label col-md-6">
													<spring:message code="knowledge.detail.criteria.kbType1"/> <span class="required"> * </span>
												</label>
												<div class="col-md-6">
													<select name="contentCat1Cmb" id="contentCat1Cmb" class="form-control select2me" data-rule-required="true">
													</select>
												</div>
											</div>  
										</div>
										<div class="col-md-8">
											<div class="form-group">
												<label class="control-label col-md-2">
													<spring:message code="knowledge.tab.info.kbno"/>
												</label>
												<div class="col-md-10">
													<input type="hidden" id="contentIdTxt" name="contentIdTxt" class="form-control" />
													<input type="text" id="kbNoTxt" name="kbNoTxt" class="form-control" readonly="readonly"/>
												</div>
											</div>  
										</div>
									</div>
									<div class="row">
										<div class="col-md-4">
											<div class="row">
												<div class="col-md-12">
													<div class="form-group">
														<label class="control-label col-md-6">
															<spring:message code="knowledge.detail.criteria.kbType2"/> <span class="required"> * </span>
														</label>
														<div class="col-md-6">
															<select name="contentCat2Cmb" id="contentCat2Cmb" class="form-control select2me" data-rule-required="true">
															</select>
														</div>
													</div>  
												</div>
											</div>
											<div class="row">
												<div class="col-md-12">
													<div class="form-group">
														<label class="control-label col-md-6">
															<spring:message code="knowledge.detail.criteria.kbType3"/> <span class="required"> * </span>
														</label>
														<div class="col-md-6">
															<select name="contentCat3Cmb" id="contentCat3Cmb" class="form-control select2me" data-rule-required="true">
													</select>
														</div>
													</div> 
												</div>
											</div> 
											<div class="row">
												<div class="col-md-12">
													<div class="form-group">
														<label class="control-label col-md-6">
															<spring:message code="knowledge.detail.criteria.kbType4"/> <span class="required"> * </span>
														</label>
														<div class="col-md-6">
															<select name="contentCat4Cmb" id="contentCat4Cmb" class="form-control select2me" data-rule-required="true">
															</select>
														</div>
													</div> 
												</div>
												<div class="col-md-12">
													<div class="form-group">
														<label class="control-label col-md-6">
															<spring:message code="knowledge.detail.criteria.kbType5"/> <span class="required"> * </span>
														</label>
														<div class="col-md-6">
															<select name="contentCat5Cmb" id="contentCat5Cmb" class="form-control select2me" data-rule-required="true">
															</select>
														</div>
													</div> 
												</div>
												<div class="col-md-12">
													<div class="form-group">
														<label class="control-label col-md-6">
															<spring:message code="knowledge.tab.info.type"/> <span class="required"> * </span>
														</label>
														<div class="col-md-6">
															<select name="type" id="type" class="form-control select2me" data-rule-required="true">
															</select>
														</div>
													</div> 
												</div>
											</div> 
										</div>
										<div class="col-md-8">
											<div class="row">
												<div class="col-md-12">
													<div class="form-group">
														<label class="control-label col-md-2">
															<spring:message code="knowledge.tab.info.question"/> <span class="required"> * </span>
														</label>
														<div class="col-md-10">
															<textarea id="titleTxt" name="titleTxt" class="form-control" data-rule-required="true" data-rule-maxlength="2000" rows="8"></textarea>
														</div>
													</div>  
												</div>
											</div>
											<div class="row">
												<div class="col-md-12">
													<div class="form-group">
														<label class="control-label col-md-2">
															<spring:message code="knowledge.tab.info.answer"/> <span class="required"> * </span>
														</label>
														<div class="col-md-10">
															<textarea id="questionTxt" name="questionTxt" class="form-control" data-rule-required="true" data-rule-maxlength="2000" rows="8"></textarea>
														</div>
													</div>  
												</div>
											</div>
											<div class="row">
												<%-- <div class="col-md-12">
													<div class="form-group">
														<label class="control-label col-md-5">
															<spring:message code="knowledge.tab.info.description"/>
														</label>
														<div class="col-md-7">
															<textarea id="descriptionTxt" name="descriptionTxt" class="form-control" rows="3"></textarea>
														</div>
													</div>    
												</div> --%>
											</div>
											<div class="row">
												<div class="col-md-12">
													<div class="form-group">
														<label class="control-label col-md-2">
															<spring:message code="knowledge.tab.info.status"/>
														</label>
														<div class="col-md-10">
															<input type="hidden" id="externalRefId" name="externalRefId" class="form-control" /> 
															<input type="hidden" id="contentStatusCd" name="contentStatusCd"  /> 
															<input type="text" id="contentStatusTxt" name="contentStatusTxt" class="form-control" disabled="disabled"/>
														</div>
													</div>  
												</div>
											</div>
										</div>
									</div>
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
								</form:form>
							</div>
							<!-- BEGIN SERCH BUTTON GROUP -->
						
							<div class="form-actions right">
								<div class="btn-group">
								<span id="insertDiv" style="display: none;">
									<button id="insertInfoDetailBtn" class="btn blue" type="button" onclick="insertKbDetail()">
										<i class="fa fa-floppy-o"></i>
										<spring:message code="button.save.label"/>
									</button>
								</span>
								
								<span id="updateDiv" style="display: none;">
									
									<button id="updateInfoDetailBtn" class="btn blue" type="button" onclick="updateKbDetail()">
										<i class="fa fa-floppy-o"></i>
										<spring:message code="button.save.label"/>
									</button>
								</span>
								</div>
								
								<div class="btn-group">
									<button id="requestApprvBtn" class="btn yellow" type="button" onclick="saveKbDetail('02')">
										<i class="fa fa-legal"></i>
										<spring:message code="button.waitforapprove.label"/>
									</button>
								</div>
								
								
								<div class="btn-group">
									<button id="inactiveBtn" class="btn red" type="button" onclick="inactive()" disabled="disabled">
										<i class="fa fa-lock"></i>
										<spring:message code="buton.inactive"/>
									</button>
								</div>
								
								<div class="btn-group">
									<button id="cancelInfoDetailBtn" class="btn default" type="button" onclick="cancelKbDetail(true)">
										<spring:message code="button.cancel.label"/>
									</button>
								</div>
							</div>
							<!-- END SERCH BUTTON GROUP -->
						</div>
						
					</div>
					<div class="tab-pane fade" id="tab_attachfile">
						<tiles:insertTemplate template="/WEB-INF/view/kb/tab_attachfile.jsp" />
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">

	/* Initial Variable*/
	var oTable;
	var contentDetailSelectedRow;
	var recordPerPage = <%=JLOWebConstant.recordsPerPage%>;
	var plaseSelectMsg = '<spring:message code="message.please.select" />';
	var saveFailMsg = '<spring:message code="lbl.action.save.fail" />';
	var tempDataForDetail;
	
	var column1 = '<spring:message code="knowledge.tab.info.result.no" />';
	var column2 = '<spring:message code="knowledge.tab.info.result.kbno" />';
	var column3 = '<spring:message code="knowledge.tab.info.result.type" />';
	var column4 = '<spring:message code="knowledge.tab.info.result.question" />';
	var column5 = '<spring:message code="knowledge.tab.info.result.answer" />';
	var column6 = '<spring:message code="knowledge.tab.info.result.status" />';
	var columns = [	{ "sTitle": column1, "mData": "contentId" , "sClass": "text-center", "sWidth": "5%"}
				   ,{ "sTitle": column2, "mData": "contentNumber", "sClass": "text-center", "sWidth": "15%" }
				   ,{ "sTitle": column3, "mData": "type", "sClass": "text-center", "sWidth": "10%" }
				  // ,{ "sTitle": column4, "mData": "title", "sWidth": "30%"}
				   ,{ "sTitle": column4, 
						"mData": null,
				    	"fnRender": function(obj) {
					   		if(obj.aData.title.length <= 40) {
					   			return obj.aData.title;
					   		} else {
					   			return obj.aData.title.substring(0, 40);
					   		}
				    	}  
			   		}
				   //,{ "sTitle": column5, "mData": "question", "sWidth": "30%"}
				   ,{ "sTitle": column5, 
						"mData": null,
				    	"fnRender": function(obj) {
					   		if(obj.aData.question.length <= 40) {
					   			return obj.aData.question;
					   		} else {
					   			return obj.aData.question.substring(0, 40);
					   		}
				    	}  
			   		}
				   ,{ "sTitle": column6, "mData": "statusName", "sWidth": "10%", "sClass": "text-center"}
			];

	var attDatatable;
	var attColumns = [	{"sTitle":'<spring:message code="knowledge.tab.attach.action"/>', "mData": "deleteUrl","sClass": "text-center","sWidth": "50"},
	              		{"sTitle": '<spring:message code="knowledge.tab.attach.no"/>', "mData": "contentAttId", "sClass": "text-center","sWidth": "40" }, 
	              		{"sTitle": '<spring:message code="knowledge.tab.attach.docName"/>', 
							"mData": null,
					    	"fnRender": function(obj) {
						   		if(obj.aData.mainFlag==1){
						   			return "<i class='fa   fa-bookmark-o red'></i> "+obj.aData.title;
						   		}else{
						   			return obj.aData.title;
						   		}
					    	}  
				   		},
		              	{"sTitle": '<spring:message code="lbl.createBy"/>', "mData": "regBy", "sWidth": "15%" },
		              	{"sTitle": '<spring:message code="lbl.createDate"/>', "mData": "regDtText","sClass": "text-center", "sWidth": "15%" }
	              ];



	var compArrEnable = ["kbNoTxt","titleTxt", "questionTxt", "descriptionTxt"
	                 	, "contentCat1Cmb", "contentCat2Cmb", "contentCat3Cmb", "contentCat4Cmb", "contentCat5Cmb", "type"
	                 	, "insertInfoDetailBtn", "updateInfoDetailBtn" , "cancelInfoDetailBtn", "requestApprvBtn"];

	var tabAttBtnArrEnable = ["btn_fileSave", "btn_fileCancel"];
	var tabAttFormArrEnable = ["attTitle", "attDescp", "mainFlag", "btnAttachFile"];
	
	$(document).ready(function() {
		setComponentDisableById(compArrEnable, true);
		setComponentDisableById(tabAttBtnArrEnable, true);

		// Initial validator form
		validateForm($("#kbDetailForm"));
		
		// Initial Select 2 component
		getCategoryTypeCombo($('#contentCat1Id'), 1, '', '', plaseSelectMsg);
		getCategoryTypeCombo($('#contentCat1Cmb'), 1, '', '', plaseSelectMsg);
		getCodeBookComboByCodeType($("#type"), "KB_TYPE", '<spring:message code="lbl.select"/>');

		/* Start binding event */
		
		$("#contentCat1Id").change(function(){		
			getCategoryTypeCombo($("#contentCat2Id"), 2, $(this).val(), '', plaseSelectMsg);
			
			$("#contentCat3Id").html("");
			$("#contentCat4Id").html("");
			$("#contentCat5Id").html("");

			$("#contentCat3Id").select2("val",null);
			$("#contentCat4Id").select2("val",null);
			$("#contentCat5Id").select2("val",null);
			
		});
		
		$("#contentCat2Id").change(function(){	
			getCategoryTypeCombo($("#contentCat3Id"), 3, $(this).val(), '', plaseSelectMsg);
			
			$("#contentCat4Id").html("");
			$("#contentCat5Id").html("");
			$("#contentCat4Id").select2("val",null);
			$("#contentCat5Id").select2("val",null);
			
		});
		
		$("#contentCat3Id").change(function(){	
			getCategoryTypeCombo($("#contentCat4Id"), 4, $(this).val(), '', plaseSelectMsg);
			
			$("#contentCat5Id").html("");
			$("#contentCat5Id").select2("val",null);
			
		});
		
		$("#contentCat4Id").change(function(){		
			getCategoryTypeCombo($("#contentCat5Id"), 5, $(this).val(), '', plaseSelectMsg);
			
		});

		$("#contentCat1Cmb").change(function(){		
			getCategoryTypeCombo($("#contentCat2Cmb"), 2, $(this).val(), '', plaseSelectMsg);
			
			$("#contentCat3Cmb").html("");
			$("#contentCat4Cmb").html("");
			$("#contentCat5Cmb").html("");

			$("#contentCat3Cmb").select2("val",null);
			$("#contentCat4Cmb").select2("val",null);
			$("#contentCat5Cmb").select2("val",null);
			
		});
		
		$("#contentCat2Cmb").change(function(){	
			getCategoryTypeCombo($("#contentCat3Cmb"), 3, $(this).val(), '', plaseSelectMsg);
			
			$("#contentCat4Cmb").html("");
			$("#contentCat5Cmb").html("");
			$("#contentCat4Cmb").select2("val",null);
			$("#contentCat5Cmb").select2("val",null);
			
		});
		
		$("#contentCat3Cmb").change(function(){	
			getCategoryTypeCombo($("#contentCat4Cmb"), 4, $(this).val(), '', plaseSelectMsg);
			
			$("#contentCat5Cmb").html("");
			$("#contentCat5Cmb").select2("val",null);
			
		});
		
		$("#contentCat4Cmb").change(function(){		
			getCategoryTypeCombo($("#contentCat5Cmb"), 5, $(this).val(), '', plaseSelectMsg);
			
		});

		$('#searchContentBtn').click(function () {
			oTable = searchContentList(true);
		});

		$('#kbListResultTbl').on( 'click touchstart', 'tr', function () {
			
			var aPos = oTable.fnGetPosition(this);
			if (empty(aPos)) {
				return false;
			}
			jLoBlockUI();
			
			contentDetailSelectedRow = aPos;
			var aData = oTable.fnGetData( aPos[0] );
			var model = aData[aPos];
			
			clearAttForm();
			callbackLoadAttTab(model);

			tempDataForDetail = model;
			callbackSetList2Detail(model);

			toggleMode('edit');
		});

		/* End binding event */
		$('#searchContentBtn').click();
		//oTable = searchContentList(true);		
		toggleMode('');
		
	});

	function insertKbDetail() {
		saveKbDetail('01');
	}

	function updateKbDetail() {
		saveKbDetail('01');
	}

	function inactive() {
		var callbackYes = "callbackConfirmYes";
		var callbackNo = "";
		var param ="";
		var confirmInactiveMsg = '<spring:message code="lbl.confirm.inactive"/>';
		
		alertConfirmYesNo(confirmInactiveMsg,callbackYes,callbackNo,param);
	}

	function callbackConfirmYes() {
		saveKbDetail('05');
	}

	function toggleMode(mode) {
		if (mode == 'new') {
			$("#insertDiv").show();
			$("#updateDiv").hide();
		} else if (mode == 'edit') {
			$("#insertDiv").hide();
			$("#updateDiv").show();
		} else {
			$("#insertDiv").hide();
			$("#updateDiv").hide();
		}
	}

	function createKbDetail() {
		clearKbDetailForm();
		clearAttForm();
		attDatatable.dataTable().fnClearTable();
		setComponentDisableById(compArrEnable, false);
		setComponentDisableById(tabAttBtnArrEnable, true);
		setComponentDisableById(tabAttFormArrEnable, true);
		toggleTab();
		$('#btn_fileNew').prop('disabled', true);
		$('#contentCat1Cmb').focus();
		toggleMode('new');

		// Focus
		$('#titleTxt').focus();
	}

	function saveKbDetail(statusCd) {
		
		var $valid = $("#kbDetailForm").valid();
	    if (!$valid) {
	    	return false;
	    }
	    jLoBlockUI();
	    $('#contentStatusCd').val(statusCd);
	    
		var dataString = serialize($("#kbDetailForm")[0]);
		
		/* var jsonObj = getJsonData('saveContentInfo.htm', dataString, 'POST');
	    if (jsonObj.resultCode == '0') {
	    	callbackAfterSave(jsonObj.model);
		} else {
			alertMessage('<spring:message code="knowledge.detail" />', saveFailMsg);
		} */

		jLoBlockUI();
		$.ajax({
			crossOrigin: true,
			timeout : 900000,
			url : "saveContentInfo.htm",
			type : "POST",
			data : dataString,
			dataType : 'json',
			cache: false,
			success : function(data, textStatus, jqXHR) {
					
				if (data.resultCode == "0") { 
					callbackAfterSave(data);
					 
				} else {

					alertMessage('<spring:message code="knowledge.detail" />', data.resultMessage);
					return false;
				}

			},
			error : function(xhr, textStatus, errorThrown) {
				checkShowMsgAjaxError(xhr, textStatus, errorThrown);
			}

		});
	}

	function cancelKbDetail() {
		
		if ($('#contentIdTxt').val() == '') {
			setComponentDisableById(compArrEnable, true);
			clearKbDetailForm();
		} else {
			clearAttForm();
			callbackLoadAttTab(tempDataForDetail);

			callbackSetList2Detail(tempDataForDetail);
		}
		
	}

	function clearKbDetailCriteria() {
		$('#contentNumber').val('');
		$('#keyword').val('');
		getCategoryTypeCombo($('#contentCat1Id'), 1, '', '', plaseSelectMsg);
		
		$("#contentCat2Id").html("");
		$("#contentCat3Id").html("");
		$("#contentCat4Id").html("");
		$("#contentCat5Id").html("");

		$("#contentCat2Id").select2("val",null);
		$("#contentCat3Id").select2("val",null);
		$("#contentCat4Id").select2("val",null);
		$("#contentCat5Id").select2("val",null);
	}
	
	function clearKbDetailForm() {
		$('#kbDetailForm').clearForm();
		$('#contentIdTxt').val('');
		$('#createBy').text('');
		$('#createDate').text('');
		$('#updateBy').text('');
		$('#updateDate').text('');
	}
	
	function searchContentList(firstTime) {
		
		jLoBlockUI();
		var dataString = serialize($("#knowledgeBaseForm")[0]);
		return ajaxDataTableSetCallback($('#kbListResultTbl'), columns, 'searchContentList.htm', dataString, recordPerPage, true, true, [], firstTime);
	}
	
	function callbackAfterSave(data) {
		
		setPageChangeCurrent(oTable);

		//alert(contentDetailSelectedRow + 1);
		//var selectedRow = (contentDetailSelectedRow + 1);
		//alert(selectedRow);
		
		//alert($('#kbListResultTbl tr:eq(7)').html());
		//$('#kbListResultTbl tr:eq(' +selectedRow +')').click();
		//oTable.$("tr:eq("+contentDetailSelectedRow+")" ).click();
		
		/* oTable.$("tr:eq("+contentDetailSelectedRow+")" ).click();
        oTable.$("tr:eq("+contentDetailSelectedRow+")" ).addClass("selected");	 */
	}

	function callbackSetList2Detail(model) {
		//alert(JSON.stringify(model));
		clearValidateForm($('#kbDetailForm'));
		setComponentDisableById(compArrEnable, false);
		$('#contentIdTxt').val(model.contentId);
		$('#kbNoTxt').val(model.contentNumber);
		$('#titleTxt').val(model.title);
		$('#questionTxt').val(model.question);
		$('#descriptionTxt').val(model.summary);
		$("#contentStatusTxt").val(model.statusName);
		$("#contentStatusCd").val(model.statusCd);
		$("#externalRefId").val(model.externalRefId);
		//$("#contentCat1Cmb").select2("val", model.contentCat1Id);
		$("#type").select2("val", model.type);

		getCategoryTypeCombo($('#contentCat1Cmb'), 1, model.contentCat1Id, '', plaseSelectMsg);
		getCategoryTypeCombo($("#contentCat2Cmb"), 2, model.contentCat1Id, model.contentCat2Id, plaseSelectMsg);
		getCategoryTypeCombo($("#contentCat3Cmb"), 3, model.contentCat2Id, model.contentCat3Id, plaseSelectMsg);
		getCategoryTypeCombo($("#contentCat4Cmb"), 4, model.contentCat3Id, model.contentCat4Id, plaseSelectMsg);
		getCategoryTypeCombo($("#contentCat5Cmb"), 5, model.contentCat4Id, model.contentCat5Id, plaseSelectMsg); 
		
		setButtomPanelDetail(model);

		// Waiti for approve, approved, inactive status cannot be edit
		if (model.statusCd == '02' || model.statusCd == '03' || model.statusCd == '05') {
			setComponentDisableById(compArrEnable, true);
		}

		if (model.statusCd == '03') {
			//alert($('#teamIds').val());
			var teamIds = $('#teamIds').val();
			var recoedOwnerId = "'" + model.regId + "'";
			var approveType = $('#approveType').val();
			var userId = $('#userId').val();

			//alert(model.regId + ' in [' + teamIds + '] ' + " =" + (teamIds.indexOf(recoedOwnerId) > 0));
			// ALL, Owner, Team, None
			if (approveType == 'ALL') {
				$('#inactiveBtn').prop('disabled', false);
			} else if (approveType == 'Team') {
				if (teamIds.indexOf(recoedOwnerId) > 0) {
					$('#inactiveBtn').prop('disabled', false);
				} else {
					$('#inactiveBtn').prop('disabled', true);
				}
			} else if (approveType == 'Owner') {
				if (userId == model.regId) {
					$('#inactiveBtn').prop('disabled', false);
				} else {
					$('#inactiveBtn').prop('disabled', true);
				}
			} else {
				$('#inactiveBtn').prop('disabled', true);
			}
			
		} else {
			$('#inactiveBtn').prop('disabled', true);
		}

		// Request for approval must be disable save button
		if (model.statusCd == '02') {
			$('#insertInfoDetailBtn').prop('disabled', true);
			$('#updateInfoDetailBtn').prop('disabled', true);
			$('#requestApprvBtn').prop('disabled', true);
		}

	}

	function callbackLoadAttTab(model) {
		$('#contentId').val(model.contentId);
		toggleTab();
		$('#btn_fileSave').prop('disabled', true);
		$('#btn_fileCancel').prop('disabled', true);
		$('#btn_fileNew').prop('disabled', false);
		
		searchAttachFileList(true);

	}

	function callbackSetKbAttDetail(modelAtt) {
		$('#contentId').val(modelAtt.contentId);
		$('#contentAttId').val(modelAtt.contentAttId);
		$('#attId').val(modelAtt.attId);
		$('#attMode').val('edit');
		$('#fileurl').val('');
		$('#attFileName').val(modelAtt.fileName);
		$('#attTitle').val(modelAtt.title);
		$('#attDescp').val(modelAtt.descp);
		if (modelAtt.mainFlag == '1') {
			setCheckbox("mainFlag",true);
		} else {
			setCheckbox("mainFlag",false);
		}
		$('#regId').val(modelAtt.regId);
		$('#attRegBy').text(modelAtt.regBy);
		$('#attRegDt').text(modelAtt.regDtText);
		$('#attChgBy').text(modelAtt.chgBy);
		$('#attChgDt').text(modelAtt.chgDtText);
		setComponentDisableById(tabAttFormArrEnable, false);
		setComponentDisableById(tabAttBtnArrEnable, false);
		$('#btn_fileNew').prop('disabled', false);

		var statusCd = $('#contentStatusCd').val();
		// Request for approval and Approved status cannot be edit
		if (statusCd == '03' || statusCd == '02' || model.statusCd == '05') {
			setComponentDisableById(tabAttFormArrEnable, true);
			setComponentDisableById(tabAttBtnArrEnable, true);
			$('#btn_fileNew').prop('disabled', true);
		}
	}

	/* Function for Category Combo */
	function getCategoryTypeCombo(nextLevelcomboObj, nextLevel, value, defaultValue, plaseSelectMsg) {
		if (nextLevel == 1) {
			getTypeProblem1ListDefaultVal(nextLevelcomboObj, value, plaseSelectMsg);
		} else if (nextLevel == 2) {
			getTypeProblem2ByParentIdListDefaultVal(nextLevelcomboObj, value, defaultValue, plaseSelectMsg);
		} else if (nextLevel == 3) {
			getTypeProblem3ByParentIdListDefaultVal(nextLevelcomboObj, value, defaultValue, plaseSelectMsg);
		} else if (nextLevel == 4) {
			getTypeProblem4ByParentIdListDefaultVal(nextLevelcomboObj, value, defaultValue, plaseSelectMsg);
		} else if (nextLevel == 5) {
			getTypeProblem5ByParentIdListDefaultVal(nextLevelcomboObj, value, defaultValue, plaseSelectMsg);
		} else {
			alert('Invalid level');
		}
	}

	function setButtomPanelDetail(data){
		var createDate = formatDateDDMMYYYYWithTime(new Date(data.regDt));
		var updateDate = formatDateDDMMYYYYWithTime(new Date(data.chgDt));
		$('#createBy').text(data.createBy);
		$('#createDate').text(createDate);
		$('#updateBy').text(data.updateBy);
		$('#updateDate').text(updateDate);
	}

	function toggleTab() {
		$('#tab_main_detail').attr('class','tab-pane fade active in');
		$('#tab_attachfile').attr('class','tab-pane fade');
		$('#tabInfo').attr('class','active');
		$('#tabAtt').attr('class','');
	}

	function clearAttForm() {
		// Reset validation
		clearValidate(["attFileName","attTitle"]);

		// Disable form
		setComponentDisableById(tabAttFormArrEnable, true);
		
		// Reset button state
		setComponentDisableById(tabAttBtnArrEnable, false);
		$('#btn_fileSave').prop('disabled', true);
		$('#btn_fileCancel').prop('disabled', true);

		// Clear form
		$('#contentId').val('');
		$('#contentAttId').val('');
		$('#attId').val('');
		$('#attMode').val('');
		$('#fileurl').val('');
		$('#attFileName').val('');
		$('#attTitle').val('');
		$('#attDescp').val('');
		setCheckbox("mainFlag",false);
		$('#attRegBy').text('');
		$('#attRegDt').text('');
		$('#attChgBy').text('');
		$('#attChgDt').text('');

	}

	function searchAttachFileList(firstTime) {
		var params = 'contentId=' + $('#contentId').val();
		attDatatable = ajaxDataTableSetCallback($('#grid_att_result_list'), attColumns, 'searchContentAttList.htm', params, recordPerPage, true, true, [], firstTime);
		$('#grid_att_result_list tbody').on( 'click touchstart', 'tr', function () {
			var aPosAtt = attDatatable.fnGetPosition(this);

			if (empty(aPosAtt)) {
				return false;
			}
			
			var aDataAtt = attDatatable.fnGetData( aPosAtt[0] );
			
			var modelAtt = aDataAtt[aPosAtt];
			callbackSetKbAttDetail(modelAtt);
		});
		//setPageChangeCurrent(attDatatable);
	}

	// Delete attachment from list
	var idfordeletecontentAttId, idfordeleteattId
	var urlForDeleteContent = 'deleteContentAtt.htm';
	
	function deleteFileConfirm(contentAttId, attId){
		idfordeletecontentAttId = contentAttId;
		idfordeleteattId = attId;
		alertConfirm('<spring:message code="lbl.confirm.delete" />','deleteFile');
	};
	
	function deleteFile(){
		var dataString = 'contentAttId=' + idfordeletecontentAttId +'&attId=' + idfordeleteattId;
		var jsonObj = getJsonData(urlForDeleteContent, dataString, 'POST');
	    if (jsonObj.resultCode == '0') {
	    	var contentIdTemp = $('#contentId').val();
	    	clearAttForm();
	    	$('#contentId').val(contentIdTemp);
	    	searchAttachFileList(true);
		} else {
			alertMessage("<spring:message code="knowledge.detail" />",jsonObj.resultMessage);
		}

	    idfordeletecontentAttId = '';
		idfordeleteattId = '';
	}

</script>