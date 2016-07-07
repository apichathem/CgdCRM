<%@page import="com.locus.jlo.web.constant.JLOWebConstant"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="row">
	<div class="col-md-12">
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-6"> <spring:message code="codebookManage.criteria.codeType" />
					</label>
					<div class="col-md-6">
						<input type="text" id="codeTypeDialog" name="codeTypeDialog" class="form-control" />
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-6"> <spring:message code="codebookManage.criteria.codeName" />
					</label>
					<div class="col-md-6">
						<input type="text" id="codeNameDialog" name="codeNameDialog" class="form-control" />
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-6"> <spring:message code="codebookManage.criteria.parentCodeType" />
					</label>
					<div class="col-md-6">
						<input type="text" id="parentCodeTypeDialog" name="parentCodeTypeDialog" class="form-control" />
					</div>
				</div>
			</div>
			<div class="col-md-6 text-center">
				
			</div>
		</div>
		<div class="form-actions right">
			<button class="btn blue" type="button" onclick="searchCodebookDialog()">
				<i class="fa fa-search"></i>
				<spring:message code="button.search.label" />
			</button>
		</div>
	</div>
	<div class="col-md-12">
		<table id="codebookDialogTableId" class="table table-bordered dataTable">
		</table>
	</div>
</div>

<script type="text/javascript">
	var evalFunc ="${callbackfn}";

	var objForm = document.forms[0];
	var recordPerPage = <%=JLOWebConstant.recordsPerPage%>;
	var columnCbDlg1 = '<spring:message code="codebookManage.criteria.parentCodeType" />';
	var columnCbDlg2 = '<spring:message code="codebookManage.result1.codeType" />';
	var columnCbDlg3 = '<spring:message code="codebookManage.result1.codeName" />';
	var columnCbDlgs = [{ "sTitle": columnCbDlg1, "mData": "parentType" }
				  ,{ "sTitle": columnCbDlg2, "mData": "codeType" }
				  ,{ "sTitle": columnCbDlg3, "mData": "codeName" }
	               ];
	
	$(document).ready(function() {
		initajaxDataTable($('#codebookDialogTableId'), columnCbDlgs);
		searchCodebookDialog();
	});
	
	function searchCodebookDialog() {
		var codeTypeDialog = $('#codeTypeDialog').val();
		var codeNameDialog = $('#codeNameDialog').val();
		var parentCodeTypeDialog = $('#parentCodeTypeDialog').val();
		
		var param = 'codeTypeDialog=' + codeTypeDialog + '&codeNameDialog=' + codeNameDialog +'&parentCodeTypeDialog=' + parentCodeTypeDialog;
		var oTable = ajaxDataTable($('#codebookDialogTableId'), columnCbDlgs, 'getCodebookListDialog.htm', param, recordPerPage, true, false);
		
		$('#codebookDialogTableId tbody').on( 'dblclick touchstart', 'tr', function () {
			 //onSelectCodebook($(this).find('td:eq(1)').html(),$(this).find('td:eq(2)').html(), $(this).find('td:eq(0)').html());
			 var aPos = oTable.fnGetPosition(this);
			 var aData = oTable.fnGetData( aPos[0] );
			    
			 setDataIntoComponent(evalFunc,aData[aPos]);
		});
	}
	
</script>