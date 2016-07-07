<%@page import="com.locus.jlo.web.constant.JLOWebConstant"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="caption">
			<spring:message code="menu.knowledgebase.knowledgebase.import" />
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="home.htm"> <spring:message code="menu.home" />
			</a><i class="fa fa-angle-right"></i></li>
			<li><a href="#"> <spring:message code="menu.knowledgebase" />
			</a> <i class="fa fa-angle-right"></i></li>
			<li><a href="#"> <spring:message code="menu.knowledgebase.knowledgebase.import" />
			</a></li>
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>

<form:form id="importKbForm" class="form form-horizontal" action="" method="post" enctype="multipart/form-data">
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<!-- BEGIN TITLE CRITERIA -->
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-reorder"></i>
					<spring:message code="knowledgeBase.import.title" />
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
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-5"><spring:message code="knowledge.import.type" /> <span class="required"> * </span>
								</label>
								<div class="col-md-7">
									<select id="eximTypeCmb" name="eximType" class="select2me form-control" data-rule-required="true">
									</select>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-5">
									<spring:message code="knowledge.import.filename"/> <span class="required"> * </span>
									<input type="file" id="fileNameID" name="kbImportFile" onchange="selectedFile();"  style="display: none;">
								</label>
								<div class="col-md-7">
									<div class="input-group">
										<input id="attFileName" name="attFileName" data-msg-required="กรุณากรอก&amp;nbsp;ชื่อไฟล์เอกสาร ." class="form-control disabled" data-rule-required="true" type="text" readonly="readonly" value="" maxlength="255">
										<span class="input-group-btn">												
											<button type="button" id="btnAttachFile" class="btn" >
												<i class="fa fa-paperclip"></i>
											</button>
										</span>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
						</div>
						<div class="col-md-6">
							<div class="col-md-5">
							</div>
							<div class="col-md-7">
								<spring:message code="lbl.filelimit" arguments="10" />
							</div>
						</div>
					</div>
				</div>
				<!-- BEGIN SERCH BUTTON GROUP -->
				<div class="form-actions right">
					<button id="validateBtn" class="btn dark" type="button">
						<i class="fa fa-check"></i>
						<spring:message code="buton.validate"/>
					</button>
					<button id="uploadBtn" class="btn green" type="button" disabled="disabled">
						<i class="fa fa-upload"></i>
						<spring:message code="buton.import"/>
					</button>
				</div>
				<!-- END SERCH BUTTON GROUP -->
			</div>
		</div>
	</div>
</div>
</form:form>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<!-- BEGIN TITLE CRITERIA -->
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-reorder"></i>
					<spring:message code="knowledgeBase.import.title" />
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
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-5"><spring:message code="knowledge.import.filename" /></label>
								<div class="col-md-7">
									<input id="resultFileNameTxt" type="text" class="form-control" readonly="readonly" />
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-5"><spring:message code="knowledge.import.processtype" /></label>
								<div class="col-md-7">
									<input id="resultProcessTypeTxt" type="text" class="form-control" readonly="readonly" />
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-5"><spring:message code="knowledge.import.datetime" /></label>
								<div class="col-md-7">
									<input id="resultProcessDatetimeTxt" type="text" class="form-control" readonly="readonly" />
								</div>
							</div>
						</div>
						<div class="col-md-6">
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<table id="importKbResultTbl" class="table table-bordered dataTable">
								<tr>
									<th><spring:message code="knowledge.import.result.no" /></th>
									<th><spring:message code="knowledge.import.result.row" /></th>
									<th><spring:message code="knowledge.import.result.detail" /></th>
								</tr>
							</table>
						</div>
					</div>
				</div><br>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	var column1 = '<spring:message code="knowledge.import.result.no" />';
	var column2 = '<spring:message code="knowledge.import.result.row" />';
	var column3 = '<spring:message code="knowledge.import.result.detail" />';
	
	
	/* Initial on load page*/
	$(document).ready(function() {
		// Initial validator form
		validateForm($("#importKbForm"));
		
		// Initial Select 2 component
		getCodeBookComboByCodeType($("#eximTypeCmb"), "EXIM_TYPE", '<spring:message code="lbl.select"/>');
		
		$("#btnAttachFile").click(function(e) {
			e.preventDefault();
			$("#fileNameID").click();
			$('#uploadBtn').prop('disabled', true);
		});

		$('#validateBtn').click(function (e) {
			e.preventDefault();

			var $valid = $("#importKbForm").valid();
		    if (!$valid) {
		    	return false;
		    }

		    $("#importKbForm").prop('action', 'validateImportFile.htm');
		    
		    ajaxSubmitForm($("#importKbForm"), callBackAfterValidate);
		    
		});

		$('#uploadBtn').click(function (e) {
			e.preventDefault();

			var $valid = $("#importKbForm").valid();
		    if (!$valid) {
		    	return false;
		    }

		    $("#importKbForm").prop('action', 'importKbFromCsv.htm');
		    
		    ajaxSubmitForm($("#importKbForm"), callBackAfterImport);
		});

	});


	function selectedFile() {
		$("#attFileName").val($("#fileNameID")[0].files[0].name);
	}

	function callBackAfterValidate(jsonObj) {

		if (jsonObj.resultCode != '0') {
			alertMessage('<spring:message code="knowledge.detail" />', jsonObj.resultMessage);
		} else {
			var model = jsonObj.model;
			$('#resultFileNameTxt').val(model.fileName);
			$('#resultProcessTypeTxt').val($('#eximTypeCmb').select2('data').text);
			$('#resultProcessDatetimeTxt').val(model.importDateTime);
			//alert($('#eximTypeCmb').select2('data').text);
			var resultList = model.validateResultBeanList;

			// To empty table
			clearTable();
			
			$.each(resultList, function( i, val ) {
				$('#importKbResultTbl tr:last').after('<tr><td>' + (i+1) + '</td><td>ROW ' + val.row + '</td><td>' + val.message + '</td></tr>');
			}); 
			
			if (jsonObj.resultCode == '0') {
		    	$('#uploadBtn').prop('disabled', false);
			} else {
				alertMessage('<spring:message code="knowledge.detail" />', jsonObj.resultMessage);
			} 
		}
		
	}

	function callBackAfterImport(jsonObj) {
		var model = jsonObj.model;
		$('#resultFileNameTxt').val(model.fileName);
		$('#resultProcessTypeTxt').val($('#eximTypeCmb').select2('data').text);
		$('#resultProcessDatetimeTxt').val(model.importDateTime);
		//alert($('#eximTypeCmb').select2('data').text);
		var resultList = model.validateResultBeanList;

		// To empty table
		clearTable();
		
		$.each(resultList, function( i, val ) {
			$('#importKbResultTbl tr:last').after('<tr><td>' + (i+1) + '</td><td>ROW ' + val.row + '</td><td>' + val.message + '</td></tr>');
		}); 
		
		alertMessage('<spring:message code="knowledge.detail" />', jsonObj.resultMessage);
	}

	function clearTable() {
		$('#importKbResultTbl').empty();
		$('#importKbResultTbl').append('<tr><th>' + column1 + '</th><th>' + column2 + '</th><th>' + column3 + '</th></tr>');
	}
	
</script>