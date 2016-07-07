<%@page import="com.locus.jlo.web.bean.modelbean.ContentModelBean"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	request.setAttribute("contentModelBean", new ContentModelBean());
	int num = (int)(Math.random()*1000000);
%>
<div class="modalkbid" id="modalkbid_<%=num%>">
<div class="row">
	<div class="col-md-12">
		<form:form modelAttribute="contentModelBean" id="formSearchKB" class="form-horizontal" method="POST">
		<div class="row">
			<div class="col-md-4">
				<div class="form-group">
					<label class="control-label col-md-5">
						<spring:message code="knowledge.type"/>
					</label>
					<div class="col-md-7">
						<form:select path="type" id="typeDialog" class="form-control select2me">
							<form:option value=""></form:option>
							<form:options items="${CODEBOOK_LIST.CONTENT_TYPE}" itemValue="codeId" itemLabel="codeName"/>
						</form:select>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="form-group">
					<label class="control-label col-md-5">
						<spring:message code="knowledge.name"/>
					</label>
					<div class="col-md-7">
						<form:input path="title" id="nameDialog" class="form-control"/>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="form-group">
					<label class="control-label col-md-5">
						<spring:message code="knowledge.tab.att.documentname"/>
					</label>
					<div class="col-md-7">
						<form:input path="documentName" id="docnameDialog" class="form-control"/>
					</div>
				</div>
			</div>
		</div>
		<div class="form-actions right">
			<button id="btnSearch" class="btn blue" type="button" onclick="searchKB();">
				<i class="fa fa-search"></i>
				<spring:message code="button.search.label" />
			</button>
		</div>
		</form:form>
	</div>
</div>
<div class="row">
	<div class="col-md-4">
		<table id="kbDialogDataTable" class="table table-bordered"></table>	
	</div>
	<div class="col-md-8">
		<div class="form-horizontal">
			<div class="form-body">
				<h5 class="form-section"><spring:message code="knowledge.folderinfo" /></h5>
				<div class="col-md-4">
					<div class="form-group">
						<label class="control-label col-md-4">
							<spring:message code="knowledge.id"/>
						</label>
						<div class="col-md-8">
							<input type="text" name="id" id="infoId" class="form-control" readonly="readonly"/>
						</div>
					</div>
				</div>
				<div class="col-md-8">
					<div class="form-group">
						<label class="control-label col-md-3">
							<spring:message code="knowledge.name"/>
						</label>
						<div class="col-md-9">
							<input type="text" name="title" id="infotitle" class="form-control" readonly="readonly"/>
						</div>
					</div>
				</div>
				<h5 class="form-section"><spring:message code="knowledge.tab.attachfile" /></h5>
				<div class="col-md-12">
					<table id="kbAttDialogDataTable" class="table table-bordered"></table>	
				</div>
				<div class="col-md-12">
					<div class="form-group">
						<label class="control-label col-md-4">
							<spring:message code="sr.srAttFileName"/>
						</label>
						<div class="col-md-8">
							<input type="text" name="filename" id="attfilename" class="form-control" readonly="readonly"/>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-4">
							<spring:message code="knowledge.tab.att.documentname"/>
						</label>
						<div class="col-md-8">
							<input type="text" name="docname" id="attdocname" class="form-control" readonly="readonly"/>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-4">
							<spring:message code="knowledge.attDescription"/>
						</label>
						<div class="col-md-8">
							<textarea rows="3" name="descp" id="attDescp" class="form-control" readonly="readonly"></textarea>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-4">
							&nbsp;
						</label>
						<div class="col-md-8">
							<input type="button" id="btnAttChooseOne" class="btn" value="<spring:message code="lbl.chooseOne"/>">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
var jsonDtObj=[];

var evalFuncKb ="${callbackfn}";
var kbDialogDataTable;
var kbAttDialogDataTable;

var recordPerPageDialog = parseInt('<spring:message code="datatable.recordPerPage" />');
var columnD1 = '<spring:message code="knowledge.id" />';
var columnD2 = '<spring:message code="knowledge.name" />';

var attcolumn1 = '<spring:message code="knowledge.tab.att.no" />';
var attcolumn2 = '<spring:message code="knowledge.tab.att.documentname" />';
var attcolumn3 = '<spring:message code="lbl.createBy" />';
var attcolumn4 = '<spring:message code="lbl.createDate" />';


var columnsDialog = [{ "sTitle": columnD1, "mData": "contentNumber" }
				    ,{ "sTitle": columnD2, "mData": "title" }];
var columnsAtt=[{"sTitle":attcolumn1, "mData": "attId"},
                {"sTitle":attcolumn2, "mData": "title"},
                {"sTitle":attcolumn3, "mData": "regBy"},
                {"sTitle":attcolumn4, "mData": "regDtText"}];

$(document).ready(function() { 
	
	$(".modalkbid").not("#modalkbid_<%=num%>").remove();
	
	$("#typeDialog").select2({
	    allowClear: true,
	    placeholder: "Select"
	});
	kbDialogDataTable = initajaxDataTable($('#kbDialogDataTable'), columnsDialog);	
	
	kbAttDialogDataTable = initajaxDataTable($('#kbAttDialogDataTable'), columnsAtt);
	$("#kbDialogDataTable tbody tr").die( "click" ); 
	$('#kbDialogDataTable tbody').on( 'click touchstart', 'tr', function () {
		var aPos = kbDialogDataTable.fnGetPosition(this);
		if(aPos==null)return;
	    var aData = kbDialogDataTable.fnGetData( aPos[0] );
// 	    console.log(aData[aPos]);
	    //setDataIntoComponent(evalFuncKb,aData[aPos]);
	    //var obj = $('form:first', window.parent.document).attr('id');
	    //selectedUser(aData[aPos]);
	    $("#infoId").val(aData[aPos].contentNumber);
	    $("#infotitle").val(aData[aPos].title);
	    $("#attfilename").val("");
	    $("#attdocname").val("");
	    $("#attDescp").val("");
	    $("#btnAttChooseOne").die( "click touchstart" );
	    $("#btnAttChooseOne").removeClass("green");
	    
	    var docName = $('#docnameDialog').val()
	    kbAttDialogDataTable = ajaxDataTableSetCallback($('#kbAttDialogDataTable'), columnsAtt, 'inquiryDialogKBDetail.htm',  "contentId="+aData[aPos].contentNumber+"&docName="+docName, 5, true, false,[],true);
	    
	    /* $('#kbAttDialogDataTable tbody').on( 'click touchstart', 'tr', function () {
			var aPos = kbAttDialogDataTable.fnGetPosition(this);
		    var aData = kbAttDialogDataTable.fnGetData( aPos[0] );
// 		    console.log(aData[aPos]);
		    $("#attfilename").val(aData[aPos].fileName);
		    $("#attdocname").val(aData[aPos].title);
		    $("#attDescp").val(aData[aPos].descp);
		    $("#btnAttChooseOne").addClass("green");
		    
		    jsonDtObj = aData[aPos];
		     
		}); */
	});
	
	$("#kbAttDialogDataTable tbody tr").die( "click" ); 
	$('#kbAttDialogDataTable tbody tr').live('click touchstart', function () {	
	 
		var aPos = kbAttDialogDataTable.fnGetPosition(this);
	    var aData = kbAttDialogDataTable.fnGetData( aPos[0] );
//		    console.log(aData[aPos]);
	    $("#attfilename").val(aData[aPos].fileName);
	    $("#attdocname").val(aData[aPos].title);
	    $("#attDescp").val(aData[aPos].descp);
	    $("#btnAttChooseOne").addClass("green");
	    
	    jsonDtObj = aData[aPos];
	    
	});
	
	$("#btnAttChooseOne").on("click touchstart",function(){	    	
    	console.log("setDataIntoComponent");
    	console.log(jsonDtObj);    	
    	setDataIntoComponent(evalFuncKb,jsonDtObj);    	
    	//setDataIntoComponent(evalFuncKb,aData[aPos]);    	
    });
	

});

function searchKB(){
	$("#infoId").val("");
    $("#infotitle").val("");
    $("#attfilename").val("");
    $("#attdocname").val("");
    $("#attDescp").val("");
    $("#btnAttChooseOne").die( "click touchstart" );
    $("#btnAttChooseOne").removeClass("green");
    kbAttDialogDataTable.fnDestroy();
    $('#kbAttDialogDataTable').html("");
    initajaxDataTable($('#kbAttDialogDataTable'), columnsAtt);
	//kbDialogDataTable = ajaxDataTable($('#kbDialogDataTable'), columnsDialog, 'getKbAttDialogList.htm', serialize($('#formSearchKB')[0]), <spring:message code="datatable.recordPerPage" />, true, false);
	kbDialogDataTable = ajaxDataTableSetCallback($('#kbDialogDataTable'), columnsDialog, 'inquiryDialogKBList.htm', serialize($('#formSearchKB')[0]), <spring:message code="datatable.recordPerPage" />, true, true,[],true);
}

</script>
</div>