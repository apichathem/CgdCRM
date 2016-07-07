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
			<spring:message code="menu.knowledgebase.knowledgebase.export" />
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="home.htm"> <spring:message code="menu.home" />
			</a><i class="fa fa-angle-right"></i></li>
			<li><a href="#"> <spring:message code="menu.knowledgebase" />
			</a> <i class="fa fa-angle-right"></i></li>
			<li><a href="#"> <spring:message code="menu.knowledgebase.knowledgebase.export" />
			</a></li>
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<div class="row"></div>
<form:form id="exportKnowledgeBaseForm" action="exportKb2Csv.htm" class="form-horizontal">
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<!-- BEGIN TITLE CRITERIA -->
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-reorder"></i>
					<spring:message code="knowledgeBase.export.title" />
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
					 	<div class="col-md-offset-3 col-md-6">
							<div class="form-group">
								<label class="control-label col-md-5">
									<spring:message code="knowledge.export.type"/><span class="required"> * </span>
								</label>
								<div class="col-md-7">
									<select id="eximTypeCmb" name="eximType" class="select2me form-control" data-rule-required="true">
									</select>
								</div>
							</div>
						</div>
					</div>
					<!--  Row 1 -->
					<div class="row">		
					 	<div class="col-md-offset-3 col-md-6">
							<div class="form-group">
								<label class="control-label col-md-5">
									<spring:message code="knowledge.detail.criteria.kbType1"/><span class="required"> * </span>
								</label>
								<div class="col-md-7">
									<select name="contentCat1Id" id="contentCat1Id" class="form-control select2me" data-rule-required="true">
									</select>
								</div>
							</div>
						</div>
					</div>
					<!--  Row 2 -->
					<div class="row">
						<div class="col-md-offset-3 col-md-6">
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
					<!--  Row 3 -->
					<div class="row">
						<div class="col-md-offset-3 col-md-6">
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
					<!--  Row 4 -->
					<div class="row">
						<div class="col-md-offset-3 col-md-6">
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
						<div class="col-md-offset-3 col-md-6">
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
					<button id="exportKbBtn" class="btn green" type="button">
						<i class="fa fa-download"></i>
						<spring:message code="buton.export"/>
					</button>
					<button id="cancelExportBtn" class="btn default" type="button">
						<spring:message code="button.cancel.label"/>
					</button>
				</div>
				<!-- END SERCH BUTTON GROUP -->
			</div>
		</div>
	</div>
</div>
</form:form>

<form:form id="exportAllKbForm" action="exportAllKb2Csv.htm" class="form-horizontal">
<div class="row text-right">
	<div class="col-md-12">
		<button id="exportAllKbBtn" class="btn dark" type="button">
			<i class="fa fa-download"></i>
			<spring:message code="buton.export.master" />
		</button>
	</div>
</div>
</form:form>

<script type="text/javascript">
	var plaseSelectMsg = '<spring:message code="message.please.select" />';
	var exportFailMsg = '<spring:message code="message.please.select" />';

	/* Initial on load page*/
	$(document).ready(function() {
		// Initial validator form
		validateForm($("#exportKnowledgeBaseForm"));
		
		// Initial Select 2 component
		getCodeBookComboByCodeType($("#eximTypeCmb"), "EXIM_TYPE", '<spring:message code="lbl.select"/>');
		getCategoryTypeCombo($('#contentCat1Id'), 1, '', '', plaseSelectMsg);

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

		$('#exportKbBtn').click(function () {
			// Call controller
			var $valid = $("#exportKnowledgeBaseForm").valid();
		    if (!$valid) {
		    	return false;
		    }
		    
		    $( "#exportKnowledgeBaseForm" ).submit();
		});

		$('#exportAllKbBtn').click(function () {
		    $( "#exportAllKbForm" ).submit();
		});

		$('#cancelExportBtn').click(function () {
			getCodeBookComboByCodeType($("#eximTypeCmb"), "EXIM_TYPE", '<spring:message code="lbl.select"/>');
			getCategoryTypeCombo($('#contentCat1Id'), 1, '', '', plaseSelectMsg);

			$("#contentCat2Id").html("");
			$("#contentCat3Id").html("");
			$("#contentCat4Id").html("");
			$("#contentCat5Id").html("");

			$("#contentCat2Id").select2("val",null);
			$("#contentCat3Id").select2("val",null);
			$("#contentCat4Id").select2("val",null);
			$("#contentCat5Id").select2("val",null);
		});
	});

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
	
</script>