<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%
	int randNum = (int)(Math.random()*1000000);
%>
<div class="modalUserId" id="modalUserId_<%=randNum%>">
<div class="row">
	<div class="col-md-12">
		<div class="form-body">
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-5">
							<spring:message code="userManage.agentName"/>
						</label>
						<div class="col-md-7">
							<input type="text" id="firstNameDialog" name="firstNameDialog" class="form-control" />
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-5">
							<spring:message code="userManage.agentSurname"/>
						</label>
						<div class="col-md-7">
							<input type="text" id="lastNameDialog" name="lastNameDialog" class="form-control" />
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="form-actions right">
			<button id="btnSearch" class="btn blue" type="button" onclick="javascript:searchUser();">
				<i class="fa fa-search"></i>
				<spring:message code="button.search.label" />
			</button>
		</div>
	</div>
	<div class="col-md-12">
		<table id="userDialogDataTable" class="table table-bordered dataTable"></table>	
	</div>	
</div>

<script type="text/javascript">
var recordPerPageDialog = parseInt('<spring:message code="datatable.recordPerPage" />');
var columnsUserDialog=[];

var evalFuncUser ="${callbackfn}";
var oTableUser;
console.log("evalFuncUser :"+evalFuncUser);

$(document).ready(function() {  
	$(".modalUserId").not("#modalUserId_<%=randNum%>").remove();
	
	userDeclareColumn();
	
	dataTableOnMouseoverTooltips("userDialogDataTable");
	
	initajaxDataTable($('#userDialogDataTable'), columnsUserDialog);
	
	searchUser();
	
	$('#userDialogDataTable tbody').on( 'dblclick touchstart', 'tr', function () {
		//selectedUser($(this).find('td:eq(0)').html(),$(this).find('td:eq(2)').html());
		var aPos = oTableUser.fnGetPosition(this);
	    var aData = oTableUser.fnGetData( aPos[0] );
	    setDataIntoComponent(evalFuncUser,aData[aPos]);
	    
	    //var obj = $('form:first', window.parent.document).attr('id');
	    //selectedUser(aData[aPos]);
	});

});

function userDeclareColumn(){
	
	var columnD1 = '<spring:message code="userManage.result.no" />';
	var columnD2 = '<spring:message code="userManage.result.agentName" />';
	var columnD3 = '<spring:message code="userManage.result.department" />';
	var columnD4 = '<spring:message code="userManage.result.role" />';
	var columnD5 = '<spring:message code="userManage.result.email" />';
	var columnD6 = '<spring:message code="userManage.result.mobileNo" />';


	columnsUserDialog = [{ "sTitle": columnD1, "mData": "userId" }
					    ,{ "sTitle": columnD2, 
							  "mData": null ,
							  "fnRender": function(objData) {
											  var firstName = objData.aData.firstName; 
											  var lastName = objData.aData.lastName; 
											  var agentName = firstName + " " + lastName;
									   	   	return agentName;
										  }
					     }
					    ,{ "sTitle": columnD3, "mData": "departmentName" }
					    ,{ "sTitle": columnD4, "mData": "roleName" }
					    ,{ "sTitle": columnD5, "mData": "email" }
					    ,{ "sTitle": columnD6, "mData": "mobileNo" }];
	
}

function searchUser(){
	var firstName = $('#firstNameDialog').val();
	var lastName = $('#lastNameDialog').val();
	var param = "firstName="+firstName+"&lastName="+lastName;
	oTableUser = ajaxDataTable($('#userDialogDataTable'), columnsUserDialog, 'getUserList.htm', param, recordPerPageDialog, true, true);
}

</script>

</div>