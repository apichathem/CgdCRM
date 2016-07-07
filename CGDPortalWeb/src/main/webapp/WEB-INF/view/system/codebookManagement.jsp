<%@page import="com.locus.common.utils.CollectionUtil"%>
<%@page import="com.locus.jlo.web.controller.CodebookManagementController"%>
<%@page import="com.locus.jlo.web.bean.modelbean.CodebookModelBean"%>
<%@page import="java.util.List"%>
<%@page import="com.locus.jlo.web.constant.JLOWebConstant"%>
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
			<spring:message code="codebookManagement.title" />
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="home.htm"> <spring:message code="menu.home" /></a> <i class="fa fa-angle-right"></i></li>
			<li><a href="#"> <spring:message code="menu.system" />
			</a> <i class="fa fa-angle-right"></i></li>
			<li> <a href="#"> <spring:message code="menu.system.codebookManagement" /></a></li>
		</ul>

	</div>
</div>
<form:form id="codebookManagementDomainSearch" action="searchCodebookManagement.htm" modelAttribute="codebookModelBean" class="form-horizontal" autocomplete="off">

	<form:hidden path="clickSearch" />
	<div class="row">
		<div class="col-md-12">
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-reorder"></i>
						<spring:message code="codebookManage.topic" />
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
									<label class="control-label col-md-5"> <spring:message code="codebookManage.criteria.codeType" />
									</label>
									<div class="col-md-7">
										<form:input path="codeType" id="searchCodeType" class="form-control" />
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-5"> <spring:message code="codebookManage.criteria.codeName" />
									</label>
									<div class="col-md-7">
										<form:input path="codeName" id="searchCodeName" class="form-control" />
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-5"> <spring:message code="codebookManage.criteria.parentCodeType" />
									</label>
									<div class="col-md-7">
										<form:input path="parentType" id="searchParentType" class="form-control" />
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="form-actions right">
						<button class="btn blue" type="button" onclick="getCodebookList()">
							<i class="fa fa-search"></i>
							<spring:message code="button.search.label" />
						</button>
						<%-- <button class="btn default" type="button"
							onclick="clearCriteria()">
							<spring:message code="button.cancel.label" />
						</button> --%>
						<button class="btn green" type="button" onclick="clearDetail()">
							<i class="fa fa-plus"></i>
							<spring:message code="button.create.label" />
						</button>
						<button class="btn red" type="button" onclick="cleanCache()">
							<i class="fa fa-database"></i> เคลียร์แคชข้อมูลค่าคงที่
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</form:form>
<form:form id="codebookManagementDomain" action="searchCodebookManagement.htm" modelAttribute="codebookModelBean" class="form-horizontal" autocomplete="off">
	<form:hidden path="mode" />
	<div class="row">
		<div class="col-md-12">
			<c:url var="searchUri" value="searchCodebookManagement.htm?page.page=##" />
			<form:hidden path="selectedCodeType" id="selectedCodeType" />
			<form:hidden path="selectedCodeId" id="selectedCodeId" />
			<div class="portlet-body">
				<table id="resultId" class="table table-bordered dataTable no-select">
				</table>
			</div>
		</div>
	</div>
	<br>
	<div class="row">
		<div class="col-md-12">
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-reorder"></i>
						<spring:message code="codebookManage.detail.topic" />
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
									<label class="control-label col-md-5"> <spring:message code="codebookManage.parentCodeType" /> <span class="required"> * </span>
									</label>
									<div class="col-md-7">
										<div class="input-group">
											<form:input path="parentType" class="form-control" readonly="true" data-rule-required="true" data-rule-maxlength="30" />
											<span class="input-group-addon"> <a id="btn_codebook_modal" href="#" data-target="#codebookDialog" data-toggle="modal"> <i class="fa fa-book" id="select_kb"></i>
											</a>
											</span>
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-5"> <spring:message code="codebookManage.parentCodeName" /> <span class="required"> * </span>
									</label>
									<div class="col-md-7">

										<form:input path="parentName" name="parentName" class="form-control" readonly="true" data-rule-required="true" data-rule-maxlength="100" />
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-5"> <spring:message code="codebookManage.parentCodeId" /> <span class="required"> * </span>
									</label>
									<div class="col-md-7">
										<form:input path="parentId" class="form-control" readonly="true" data-rule-required="true" data-rule-maxlength="10" />
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-5"> <spring:message code="codebookManage.codeType" /> <span class="required"> * </span>
									</label>
									<div class="col-md-7">
										<form:input path="codeType" class="form-control" data-rule-required="true" data-rule-maxlength="30" />
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-5"> <spring:message code="codebookManage.codeId" /> <span class="required"> * </span>
									</label>
									<div class="col-md-7">
										<form:input path="codeId" class="form-control" data-rule-required="true" data-rule-maxlength="10" />
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-5"> <spring:message code="codebookManage.channel.mobile" /> <span class="required"> * </span>
									</label>
									<div class="col-md-7">
										<div class="radio-list">
											<label class="radio-inline">
												<div class="radio">
													<form:radiobutton path="channel" value="M" />
												</div> <spring:message code="lbl.yes" />
											</label> <label class="radio-inline">
												<div class="radio">
													<form:radiobutton path="channel" value="" />
												</div> <spring:message code="lbl.no" />
											</label>
										</div>
									</div>
								</div>
							</div>
						</div>


						<%
							List<String> languageNameList = (List<String>) session.getAttribute(CodebookManagementController.SESS_LANG_NM);
																			List<String> languageCodeList = (List<String>) session.getAttribute(CodebookManagementController.SESS_LANG_CD);
																																				
																			if (CollectionUtil.isNotEmpty(languageNameList)) {
																																			
																				for (int i = 0; i < languageNameList.size(); ) {
						%>
						<div class="row">

							<%
								for (int j = 0; j < 3; j++) { 
																								if (i >= languageNameList.size()) {
							%>
							<div class="col-md-4">
								<div class="form-group"></div>
							</div>
							<%
								} else {
							%>
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-5"><spring:message code="codebookManage.codeName" /> <%=languageNameList.get(i)%> <span class="required"> * </span></label>
									<div class="col-md-7">
										<div class="">
											<input type="hidden" name="languageCode" value="<%=languageCodeList.get(i)%>" /> <input id="<%=languageCodeList.get(i)%>" name="codenameByLang" class="form-control"
												data-rule-required="true" data-rule-maxlength="100" />
										</div>
									</div>
								</div>
							</div>
							<%
								}
																																													
																								i++;
																							}
							%>
						</div>
						<%
							}
																			}
						%>
						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-5"> <spring:message code="codebookManage.etc1" />
									</label>
									<div class="col-md-7">
										<form:input path="etc1" class="form-control" data-rule-maxlength="100" />
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-5"> <spring:message code="codebookManage.etc2" />
									</label>
									<div class="col-md-7">
										<form:input path="etc2" class="form-control" data-rule-maxlength="100" />
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-5"> <spring:message code="codebookManage.etc3" />
									</label>
									<div class="col-md-7">
										<form:input path="etc3" class="form-control" data-rule-maxlength="100" />
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-5"> <spring:message code="codebookManage.etc4" />
									</label>
									<div class="col-md-7">
										<form:input path="etc4" class="form-control" data-rule-maxlength="100" />
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-5"> <spring:message code="codebookManage.etc5" />
									</label>
									<div class="col-md-7">
										<form:input path="etc5" class="form-control" data-rule-maxlength="100" />
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-5"> <spring:message code="codebookManage.enabled" />
									</label>
									<div class="col-md-7">
										<div class="radio-list">
											<label class="radio-inline">
												<div class="radio">
													<form:radiobutton path="useYn" value="Y" />
												</div> <spring:message code="lbl.yes" />
											</label> <label class="radio-inline">
												<div class="radio">
													<form:radiobutton path="useYn" value="N" />
												</div> <spring:message code="lbl.no" />
											</label>
										</div>
									</div>
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
										<p id="createBy" class="form-control-static">${codebookModelBean.createBy}</p>
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-5"> <spring:message code="lbl.createDate" />
									</label>
									<div class="col-md-7">
										<p id="createDate" class="form-control-static">${codebookModelBean.createDate}</p>
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
										<p id="updateBy" class="form-control-static">${codebookModelBean.updateBy}</p>
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-5"> <spring:message code="lbl.updateDate" />
									</label>
									<div class="col-md-7">
										<p id="updateDate" class="form-control-static">${codebookModelBean.updateDate}</p>
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group"></div>
							</div>
						</div>
					</div>
					<div class="form-actions right">

						<span id="insertDiv" style="display: none;">
							<button class="btn blue" type="button" onclick="insertCodebook()">
								<i class="fa fa-floppy-o"></i>
								<spring:message code="button.save.label" />
							</button>
						</span> <span id="updateDiv" style="display: none;">
							<button class="btn blue" type="button" onclick="updateCodebook()">
								<i class="fa fa-floppy-o"></i>
								<spring:message code="button.save.label" />
							</button>
						</span>


						<%-- <button class="btn default" type="button" onclick="clearDetail()">
							<spring:message code="button.cancel.label" />
						</button> --%>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- responsive -->
	<div class="modal fade" id="codebookDialog" role="basic" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div id="modal_content_div"></div>
			</div>
		</div>
	</div>
</form:form>
<script type="text/javascript">
	var title = '<spring:message code="codebookManage.detail.topic" />';
	var oTableCodebook;
	var saveModeClickTemp = "insert";
	var currentRow = 0;
	var jsonDataDetail;
	<%java.util.Locale locale = (java.util.Locale)request.getSession().getAttribute("CURRENT_LANG");
	String colCodeName = locale.getLanguage().toUpperCase().equalsIgnoreCase("TH")?"codeNameTh":"codeNameEn";%>
	var recordPerPage = <%=JLOWebConstant.recordsPerPage%>;
	var column1 = '<spring:message code="codebookManage.result1.no" />';
	var column2 = '<spring:message code="codebookManage.result1.codeId" />';
	var column3 = '<spring:message code="codebookManage.result1.codeType" />';
	var column4 = '<spring:message code="codebookManage.result1.codeName" />';
	var column5 = '<spring:message code="codebookManage.result1.parentCodeType" />';
	var column6 = '<spring:message code="codebookManage.result1.parentCodeId" />';
	var columns = [{ "sTitle": column1, "mData": "codeId", sClass:"text-center", "sWidth": "3%",}
				  ,{ "sTitle": column2, "mData": "codeId" }
				  ,{ "sTitle": column3, "mData": "codeType" }
				  ,{ "sTitle": column4, "mData": "<%=colCodeName%>" }
				  ,{ "sTitle": column5, "mData": "parentType" }
				  ,{ "sTitle": column6, "mData": "parentId" }
	               ];
	
	$(document).ready(function() {
		validateForm($("#codebookManagementDomain"));
		
		$("#searchParentType").val("");
		setRadioValue("useYn", 'Y');
		setRadioValue("channel", 'M');
		
		initajaxDataTable($('#resultId'), columns);
		
		var mode = $('#mode').val();
		toggleButton(mode);
		
		$("#btn_codebook_modal").click(function() {		 
			loadContentIntoModal($("#modal_content_div"), "openModalDialog.htm","codebookDialog","codebookManage.dialog.topic", "selectedCodebook");
		});

		$("#resultId tbody tr").die( "click" ); 	
		$("#resultId tbody tr").live('click', function () {	
			var aPos = oTableCodebook.fnGetPosition(this);
		    var aData = oTableCodebook.fnGetData( aPos[0] );
		   
		    console.log(JSON.stringify(aData[aPos]));
		    console.log(aData[aPos].codeId + ' ' + aData[aPos].codeType);
		    
			onClickCodebook(aData[aPos].codeId, aData[aPos].codeType);
			
		});
		
		$("#codeType").onEnter( function() {
			if(!empty($("#codeType").val())){
				getCodebookList();
			}
		});
		
		$("#codeName").onEnter( function() {
			if(!empty($("#codeName").val())){
				getCodebookList();
			}
		});
		
		$("#parentType").onEnter( function() {
			if(!empty($("#parentType").val())){
				getCodebookList();
			}
		});

	});
	
	function toggleButton(mode) {
		if (mode == 'insert') {
			$("#insertDiv").show();
			$("#updateDiv").hide();
		} else {
			$("#updateDiv").show();
			$("#insertDiv").hide();
		}
	}
	
	function getCodebookList() {
		var dataString = serialize($("#codebookManagementDomainSearch")[0]);
		oTableCodebook= ajaxDataTable($('#resultId'), columns, 'getCodebookList.htm', dataString, recordPerPage, true, true);
		
		return oTableCodebook;
		
	}
	
	function getCodebookDetail(codeId, codeType) {
		$('#selectedCodeId').val(codeId);
		$('#selectedCodeType').val(codeType);
		$('#mode').val('update');
		$('#codebookManagementDomainSearch').attr('action', 'searchCodebookDetail.htm');
		$('#codebookManagementDomainSearch').submit();
	}
	
	function onClickCodebook(codeId, codeType) {
		var param = 'codeType=' + codeType + '&codeId=' + codeId;
		loadJsonData('getCodebookDetail.htm', param, 'POST', callbackCodebookDetail);
	}
	
	function callbackCodebookDetail(data, textStatus, jqXHR) {
		//clearDetail();
		console.log(JSON.stringify(data));
		
		// Set JSON result object to temp
		jsonDataDetail = data;
		
		// Set JSON value to form
		setDetailForm(data);
		
		// Set mode to update
		$('#mode').val('update');
		toggleButton('update');
	}
	
	function setDetailForm(data) {
		$('#parentType').val(data.parentType);
		$('#parentName').val(data.parentName);
		$('#parentId').val(data.parentId);
		$('#codeType').val(data.codeType);
		$('#codeType').prop('readonly', true);
		$('#codeId').prop('readonly', true);
		$('#codeId').val(data.codeId);
		
		$('#etc1').val(data.etc1);
		$('#etc2').val(data.etc2);
		$('#etc3').val(data.etc3);
		$('#etc4').val(data.etc4);
		$('#etc5').val(data.etc5);
		
		var useYn = data.useYn;
		var channel = data.channel==null?'':data.channel;
		setRadioValue("useYn",useYn);
		setRadioValue("channel",channel);
		
		$('#createBy').html(data.createBy);
		$('#createDate').html(data.createDate);
		$('#updateBy').html(data.updateBy);
		$('#updateDate').html(data.updateDate);
		$('#EN').val(data.codeNameEn);
		$('#TH').val(data.codeNameTh);
		
		if ((typeof(data.languageCode) !== 'undefined') && (data.languageCode !== null)) {
			$.each(data.languageCode, function (idx, value) {
				console.log('ข้อมูล ' + data.codenameByLang[idx]);
				$('#' + value).val(data.codenameByLang[idx]);
			});
		}
	}
	
	function insertCodebook() {
		var $valid = validateForm($("#codebookManagementDomain"));
		if (!$valid) {
			return false;
		} else {
		 
			var MODE_CODEBOOK_IU = $('#mode').val();
			
			$("#codebookManagementDomain").attr("action", "insertCodebookDetail.htm");
			 
			saveModeClickTemp = MODE_CODEBOOK_IU;
			
			ajaxSubmitForm($("#codebookManagementDomain"), function(jsonObj){	
				
				if(jsonObj.resultCode == "0"){
					// Set new JSON Object result data temp
					jsonDataDetail = jsonObj.model;
					
					oTableCodebook = getCodebookList(); 			
						 
					$('#createBy').html(jsonObj.model.createBy);
					$('#createDate').html(jsonObj.model.createDate);
					$('#updateBy').html(jsonObj.model.updateBy);
					$('#updateDate').html(jsonObj.model.updateDate);
				} else {
					alertMessage(title, jsonObj.resultMessage);
				}
				 
			});
		}
	}
	
	function updateCodebook() {
		var $valid = validateForm($("#codebookManagementDomain"));
		if (!$valid) {
			return false;
		} else {
		 
			var MODE_CODEBOOK_IU = $('#mode').val();
			
			$("#codebookManagementDomain").attr("action", "updateCodebookDetail.htm");
			 
			saveModeClickTemp = MODE_CODEBOOK_IU;
			
			ajaxSubmitForm($("#codebookManagementDomain"), function(jsonObj){	
				
				if(jsonObj.resultCode == "0"){
			 		setPageChangeCurrent(oTableCodebook);
			 		// Set new JSON Object result data temp
					jsonDataDetail = jsonObj.model;
			 	
					$('#createBy').html(jsonObj.model.createBy);
					$('#createDate').html(jsonObj.model.createDate);
					$('#updateBy').html(jsonObj.model.updateBy);
					$('#updateDate').html(jsonObj.model.updateDate);
				} else {
					 
					alertMessage(title, jsonObj.resultMessage);
				}
				 
			});
			
		}
	}
	
	function clearCriteria() {
		$('#codeType').val('');
		$('#codeName').val('');
		$('#parentType').val('');
		$('#clickSearch').val('');
	}
	
	function clearDetail() {
		$('#parentType').val('<%=JLOWebConstant.CODEBOOK_ROOT_TYPE%>');
		$('#parentName').val('<%=JLOWebConstant.CODEBOOK_ROOT_NAME%>');
		$('#parentId').val('<%=JLOWebConstant.CODEBOOK_ROOT_ID%>');

		$('#codeType').prop("readonly", false);
		$('#codeId').attr('readonly', '');
		$('#codeId').removeAttr('readonly');

		$('#codeType').focus();
		$('#codeType').val('');
		$('#codeId').val('');
		$('#etc1').val('');
		$('#etc2').val('');
		$('#etc3').val('');
		$('#etc4').val('');
		$('#etc5').val('');
		setRadioValue("useYn", 'Y');
		setRadioValue("channel", 'M');
		$('#createBy').html('');
		$('#createDate').html('');
		$('#updateBy').html('');
		$('#updateDate').html('');

		$('input [name^=codenameByLang]').val('');

		$('#mode').val('insert');
		toggleButton('insert');
		clearValidateForm($("#codebookManagementDomain"));

		var rowPerPage = curRowSelectedDt;
		console.log('current row for cancel ' + rowPerPage);
		if (oTableCodebook.$("tr:eq(" + (rowPerPage) + ")").hasClass("selected")) {
			oTableCodebook.$("tr:eq(" + (rowPerPage) + ")").removeClass("selected");
		}

		var totalRecord = oTableCodebook.fnSettings().fnRecordsTotal();
		if (totalRecord > 0) {
			//setDetailForm(jsonDataDetail);
			//var aData = oTableCodebook.fnGetData(aPos[0]);
			//setFormScreenOnSelectDatable("codebookManagementDomain",aData[aPos]);
		}

	}

	function resetData() {
		// Reset old value  
		setFormScreenOnSelectDatable("codebookManagementDomain", jsonDataDetail);
	}

	function selectedCodebook(jsonData) {
		$('#parentName').val(jsonData.codeName);
		$('#parentType').val(jsonData.codeType);
		$('#parentId').val(jsonData.codeId);

		$('#codebookDialog').modal('hide');
	}

	function cleanCache() {
		loadJsonData('cleanCacheCodebook.htm', "", 'GET', function() {
			alertMessage('<spring:message code="codebookManagement.title" />', 'ระบบได้ทำการเคลียร์แคช และโหลดข้อมูลชุดใหม่ลงแคชแล้ว');
		});
	}
</script>