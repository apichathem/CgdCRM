<%@page import="com.locus.jlo.web.constant.JLOWebConstant"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="locus" uri="/WEB-INF/tld/locus.tld"%>

<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="caption">
			<spring:message code="menu.knowledgebase.knowledgebase.approval" />
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="home.htm"> <spring:message code="menu.home" />
			</a><i class="fa fa-angle-right"></i></li>
			<li><a href="#"> <spring:message code="menu.knowledgebase" />
			</a> <i class="fa fa-angle-right"></i></li>
			<li><a href="#"> <spring:message code="menu.knowledgebase.knowledgebase.approval" />
			</a></li>
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<!-- BEGIN TITLE CRITERIA -->
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-reorder"></i>
					<spring:message code="knowledgeBase.approval.title" />
				</div>
				<div class="tools">
					<a class="collapse" href="javascript:;"></a>
				</div>
			</div>
			<!-- END TITLE CRITERIA-->

			<!-- BEGIN PORTLET BODY OF CRITERIA -->
			<div class="portlet-body form">
				<div class="form-body">
					<div class="row">
						<div class="col-md-12">
							<button id="approvalBtn" class="btn green" type="button">
								<i class="fa fa-check"></i>
								<spring:message code="button.approve.label" />
							</button>
							<button id="rejectBtn" class="btn red" type="button">
								<i class="fa fa-times"></i>
								<spring:message code="button.reject.label" />
							</button>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<table class="table table-bordered" id="tableResultTbl"></table>
						</div>
					</div>
				</div>
				<br>
			</div>
			
		</div>
	</div>
</div>

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
			<div class="portlet-body">
				<div class="form">
					<div class="form-body">
						<div class="row">
							<div class="col-md-5">
								<div class="form-group">
									<label class="control-label col-md-5">
										<spring:message code="knowledge.detail.criteria.kbType1"/> <span class="required"> * </span>
									</label>
									<div class="col-md-7">
										<input type="text" id="contentCat1Name" name="contentCat1Name" class="form-control" readonly="readonly"/>
									</div>
								</div>  
							</div>
							<div class="col-md-7">
								<div class="form-group">
									<label class="control-label col-md-3">
										<spring:message code="knowledge.tab.info.kbno"/>
									</label>
									<div class="col-md-9">
										<input type="text" id="contentNumber" name="contentNumber" class="form-control" readonly="readonly"/>
									</div>
								</div>  
							</div>
						</div>
						<div class="row">
							<div class="col-md-5">
								<div class="row">
									<div class="col-md-12">
										<div class="form-group">
											<label class="control-label col-md-5">
												<spring:message code="knowledge.detail.criteria.kbType2"/> <span class="required"> * </span>
											</label>
											<div class="col-md-7">
												<input type="text" id="contentCat2Name" name="contentCat2Name" class="form-control" readonly="readonly"/>
											</div>
										</div>  
									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
										<div class="form-group">
											<label class="control-label col-md-5">
												<spring:message code="knowledge.detail.criteria.kbType3"/> <span class="required"> * </span>
											</label>
											<div class="col-md-7">
												<input type="text" id="contentCat3Name" name="contentCat3Name" class="form-control" readonly="readonly"/>
											</div>
										</div> 
									</div>
								</div> 
								<div class="row">
									<div class="col-md-12">
										<div class="form-group">
											<label class="control-label col-md-5">
												<spring:message code="knowledge.detail.criteria.kbType4"/> <span class="required"> * </span>
											</label>
											<div class="col-md-7">
												<input type="text" id="contentCat4Name" name="contentCat4Name" class="form-control" readonly="readonly"/>
											</div>
										</div> 
									</div>
									<div class="col-md-12">
										<div class="form-group">
											<label class="control-label col-md-5">
												<spring:message code="knowledge.detail.criteria.kbType5"/> <span class="required"> * </span>
											</label>
											<div class="col-md-7">
												<input type="text" id="contentCat5Name" name="contentCat5Name" class="form-control" readonly="readonly"/>
											</div>
										</div> 
									</div>
									<div class="col-md-12">
										<div class="form-group">
											<label class="control-label col-md-5">
												<spring:message code="knowledge.tab.info.type"/> <span class="required"> * </span>
											</label>
											<div class="col-md-7">
												<input type="text" id="type" name="type" class="form-control" readonly="readonly"/>
											</div>
										</div> 
									</div>
								</div> 
							</div>
							<div class="col-md-7">
								<div class="row">
									<div class="col-md-12">
										<div class="form-group">
											<label class="control-label col-md-3">
												<spring:message code="knowledge.tab.info.question"/> <span class="required"> * </span>
											</label>
											<div class="col-md-9">
												<textarea id="title" name="title" class="form-control" data-rule-required="true" data-rule-maxlength="2000" rows="4" readonly="readonly"></textarea>
											</div>
										</div>  
									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
										<div class="form-group">
											<label class="control-label col-md-3">
												<spring:message code="knowledge.tab.info.answer"/> <span class="required"> * </span>
											</label>
											<div class="col-md-9">
												<textarea id="question" name="question" class="form-control" data-rule-required="true" data-rule-maxlength="2000" rows="4" readonly="readonly"></textarea>
											</div>
										</div>  
									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
										<div class="form-group">
											<label class="control-label col-md-3">
												<spring:message code="knowledge.tab.info.status"/>
											</label>
											<div class="col-md-9">
												<input type="text" id="statusName" name="statusName" class="form-control" disabled="disabled"/>
											</div>
										</div>  
									</div>
								</div>
							</div>
						</div>
						
						<div>
							<div class="row">
								<div class="col-md-12">
									<div class="form  form-horizontal ">
										<div class="form-body">
											<h5 class="form-section"><spring:message code="knowledge.tab.attach.attachmentCaption"/></h5>
											<div class="row">
												<div class="col-md-12">						
													<table class="table table-bordered" id="attachmentDataTable">
													</table>	
												</div>
												
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">

	/* Initial Variable*/
	var oTable;
	var recordPerPage = <%=JLOWebConstant.recordsPerPage%>;
	var objodata = [];
	var dataArr = [];
	var approveMsg = '<spring:message code="lbl.confirm.approve" />';
	var rejectMsg ='<spring:message code="lbl.confirm.reject" />';
	var pleaseSelectMsg ='<spring:message code="message.please.select" />';
	var messageTitle = '<spring:message code="knowledgeBase.approval.title" />';
	var column1 = '<spring:message code="knowledge.approval.result.no" />';
	var column2 = '<spring:message code="knowledge.approval.result.approve" />';
	var column4 = '<spring:message code="knowledge.approval.result.kbno" />';
	var column5 = '<spring:message code="knowledge.approval.result.title" />';
	var column6 = '<spring:message code="knowledge.approval.result.type" />';
	var column7 = '<spring:message code="knowledge.approval.result.requester" />';
	var columns = [ { "sTitle": column1, "mData": 'contentNumber' , "sClass": "text-center"}
					,{
						"sTitle" : column2,
						"mData" : null,
						"sWidth" : "3%",
						"fnRender" : function(oObj) {
							var contentId = oObj.aData.contentId;

							var data = '{contentId:\'' + contentId + '\'}';
							objodata[contentId] = data;
							
							var returnButton = "<center><input type='checkbox' name='kbContentId' value='"+contentId+"'/></center></a>";
							return returnButton;
				
						}
					}
				   ,{ "sTitle": column4, "mData": 'contentNumber' ,"sClass": "text-center"}
				   ,{ "sTitle": column5, "mData": 'title' }
				   ,{ "sTitle": column6, "mData": 'contentCat1Name' }
				   ,{ "sTitle": column7, "mData": 'requesterName'}
			];
		
	var attDatatable;
	var attColumns = [	{"sTitle": '<spring:message code="knowledge.tab.attach.no"/>', "mData": "contentAttId", "sClass": "text-center","sWidth": "40" }, 
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
		              	{"sTitle": '<spring:message code="lbl.createDate"/>', "mData": "regDtText","sClass": "text-center", "sWidth": "15%" },
		              	{"sTitle":'<spring:message code="knowledge.tab.attach.action"/>', "mData": "deleteUrl","sClass": "text-center","sWidth": "50"}
	              ];
    
	/* Initial on load page*/
	$(document).ready(function() {
		// Initial datatable
		//oTable = initajaxDataTable($('#tableResultTbl'), columns);
		oTable = searchApproveList();

		attDatatable = initajaxDataTable($('#attachmentDataTable'), attColumns);
		
		$("#approvalBtn").click(function(event) {
			event.preventDefault();
			disableButton();
			dataArr = [];
			$('input:checked').each(function() {
				dataArr.push($(this).val());
			});

			confirmApprove(dataArr);
		});

		$("#rejectBtn").click(function(event) {
			event.preventDefault();
			disableButton();
			dataArr = [];
			$('input:checked').each(function() {
				dataArr.push($(this).val());
			});

			confirmReject(dataArr);

		});

		$('#tableResultTbl').on( 'click touchstart', 'tr', function () {
			var aPos = oTable.fnGetPosition(this);
			if (empty(aPos)) {
				return false;
			}
			
			contentDetailSelectedRow = aPos;
			var aData = oTable.fnGetData( aPos[0] );
			var model = aData[aPos];

			callbackSetDetail2Form(model);
			callbackSetAttDataTable(model);
		});
	});

	function callbackSetDetail2Form(model) {
		$('#contentNumber').val(model.contentNumber);
		$('#contentCat1Name').val(model.contentCat1Name);
		$('#contentCat2Name').val(model.contentCat2Name);
		$('#contentCat3Name').val(model.contentCat3Name);
		$('#contentCat4Name').val(model.contentCat4Name);
		$('#contentCat5Name').val(model.contentCat5Name);
		$('#title').val(model.title);
		$('#question').val(model.question);
		$('#type').val(model.type);
		$('#statusName').val(model.statusName);
	}

	function callbackSetAttDataTable(model) {
		var params = 'contentId=' + model.contentId;
		attDatatable = ajaxDataTable($('#attachmentDataTable'), attColumns, 'searchContentAttListForApprove.htm', params, recordPerPage, true, true);
	}

	function searchApproveList() {
		return ajaxDataTable($('#tableResultTbl'), columns, 'searchKBWaitingList.htm', '', recordPerPage, true, true);
	}
	
	function confirmApprove(dataArr) {
		if (dataArr.length > 0) {
			alertConfirm(approveMsg, "kbApprove", dataArr);
			enableButton();
		} else {

			alertMessage(messageTitle, pleaseSelectMsg);
			enableButton();
			return false;
		}
	}

	function confirmReject(dataArr) {
		if (dataArr.length > 0) {
			alertConfirm(rejectMsg, "kbReject", dataArr);
			enableButton();
		} else {

			alertMessage(messageTitle, pleaseSelectMsg);
			enableButton();
			return false;
		}

	}

	function kbApprove(requestData) {
		kbApproveOrReject(requestData, 'approve');
	}

	function kbReject(requestData) {
		kbApproveOrReject(requestData, 'reject');
	}

	function kbApproveOrReject(requestData, mode) {
		disableButton();
		var postdata = "";
		
		for ( var obj in requestData) {
			postdata += "&dataReqObj=" + requestData[obj];
		}
		
		var modeAction = '';
		if (mode == 'approve') {
			modeAction = 'APPROVE';
		} else {
			modeAction = 'REJECT';
		}
		jLoBlockUI();
		$.ajax({
			crossOrigin: true,
			timeout : 900000,
			url : "kbApproveOrReject.htm",
			type : "POST",
			data : "modeAction=" + modeAction + postdata,
			dataType : 'json',
			cache: false,
			//async: true,
			success : function(data, textStatus, jqXHR) {
					
				if (data.resultCode == "0") { 
					enableButton();
					searchApproveList();
				} else {
					enableButton();
					searchApproveList();
					alertMessage('Error', data.resultMessage);
					return false;
				}
				
			},
			error : function(xhr, textStatus, errorThrown) {
				checkShowMsgAjaxError(xhr, textStatus, errorThrown);
			}

		});
		
	}

	function disableButton() {
		$('#approvalBtn').attr("disabled","disabled");
		$('#rejectBtn').attr("disabled","disabled");
	}

	function enableButton() {
		$('#approvalBtn').removeAttr("disabled");
		$('#rejectBtn').removeAttr("disabled");
	}
	
</script>