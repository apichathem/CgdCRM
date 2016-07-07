<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%
	int randNum = (int)(Math.random()*1000000);
%>
<div class="modalRoleId" id="modalRoleId_<%=randNum%>">
<div class="row">
	<div class="col-md-12">
		<div class="form-body">
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-5">
							<spring:message code="roleManage.result.name"/>
						</label>
						<div class="col-md-7">
							<input type="text" id="roleNameDialog" name="roleNameDialog" class="form-control" />
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="form-actions right">
			<button id="btnSearch" class="btn blue" type="button" onclick="javascript:searchRole();">
				<i class="fa fa-search"></i>
				<spring:message code="button.search.label" />
			</button>
		</div>
	</div>
	<div class="col-md-12">
		<table id="roleDialogDataTable" class="table table-bordered dataTable"></table>	
	</div>	
</div>

<script type="text/javascript">
var recordPerPageDialog = parseInt('<spring:message code="datatable.recordPerPage" />');
var columnsRoleDialog=[];

var evalFuncRole ="${callbackfn}";
var oTableRole;
console.log("evalFuncRole :"+evalFuncRole);

var roleId = "${USER_PROFILE.roleId}";

$(document).ready(function() {  
	$(".modalRoleId").not("#modalRoleId_<%=randNum%>").remove();
	
	roleDeclareColumn();
	
	dataTableOnMouseoverTooltips("roleDialogDataTable");
	
	initajaxDataTable($('#roleDialogDataTable'), columnsRoleDialog);
	
	searchRole();
	
	$('#roleDialogDataTable tbody').on( 'dblclick touchstart', 'tr', function () {
		var aPos = oTableRole.fnGetPosition(this);
	    var aData = oTableRole.fnGetData( aPos[0] );
	    setDataIntoComponent(evalFuncRole,aData[aPos]);
	    
	});

});

function roleDeclareColumn(){
	
	var columnD1 = '<spring:message code="roleManage.result.no" />';
	var columnD2 = '<spring:message code="roleManage.result.name" />';

	columnsRoleDialog = [{ "sTitle": columnD1, "mData": "roleId" }
					    ,{ "sTitle": columnD2, "mData": "roleName" }];
	
}

function searchRole(){
	var roleName = $('#roleNameDialog').val();
	var param = "roleName="+roleName;
	oTableRole = ajaxDataTable($('#roleDialogDataTable'), columnsRoleDialog, 'getRoleList.htm', param, recordPerPageDialog, true, true);
}

</script>

</div>