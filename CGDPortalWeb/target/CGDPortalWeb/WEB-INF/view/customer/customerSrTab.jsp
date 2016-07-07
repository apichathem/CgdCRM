
<%-- <%@page import="com.locus.jlo.webportal.common.util.MenuHelper"%> --%>
<%@page import="com.locus.jlo.web.constant.JLOWebConstant"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="locus" uri="/WEB-INF/tld/locus.tld"%>

<!-- Begin Content -->
<div class="row">
	<div class="col-md-12">
		<br/>
		<!-- Panel Button -->
		<div align="right">
			<locus:privilege oper="ADD">
				<button type="button" class="btn green" id="btnCreateSr">
					<i class="fa fa-plus"></i>
					<spring:message code="button.create.label" />
				</button>
			</locus:privilege>
			 
		</div>
		<table id="custSrDataTable" class="table table-bordered dataTable"></table>
	</div>
</div>

<script type="text/javascript">
	var tabMenuId = "";
	var custId = $('#custId').val();
	var custType = $('#custCategoryId').val();
	var custName = $('#custFirstNameDtl').val() + ' ' + $('#custLastNameDtl').val();
	
	var recordPerPage = <%=JLOWebConstant.recordsPerPage%>;
	var oTable;
	var columnsCustSrDt = "[]";
	
	$(document).ready(function(){
		
		declareColumn();
		getCustomerSrList();
		
		$('#btnCreateSr').click(function () {
			var url = '/serviceRequestDetail.htm?module=customer&mode=insert&custId=' + custId + '&custType=' + custType + "&custName=" + custName ;
			
			postAction(url);
		});
		
	});
	
	function getCustomerSrList() {
		if(custId != "1") {
			var param = "custId=" + custId + "&tabMenuId=" + tabMenuId;
		   	oTable = ajaxDataTable($('#custSrDataTable'), columnsCustSrDt, 'getCustSrList.htm', param, recordPerPage, true, true);
		}
	}
	
	function declareColumn(){
		
		var col_no = '<center><spring:message code="sr.srGrdSrNo"/></center>';
		var col_srnumber = '<center><spring:message code="sr.srGrdSrNumber"/></center>';
		var col_srTypeProblem1 = '<center><spring:message code="cust.srGrdTypeProblem"/></center>';
		var col_srSubject ='<center><spring:message code="sr.srGrdSubject"/></center>';
		var col_srGrdSrPriority = '<center><spring:message code="sr.srGrdPriority"/></center>';
		var col_srGrdSrStatus	= '<center><spring:message code="sr.srGrdSrStatus"/></center>';
		var col_srGrdSrOpenDate	= '<center><spring:message code="sr.srGrdSrOpenDate"/></center>';
		var col_srGrdSrDueDate = '<center><spring:message code="sr.srGrdSrDueDate"/></center>';
		var col_srGrdSrOwner = '<center><spring:message code="sr.srGrdSrOwner"/></center>';
		var col_srGrdSrAction = '<center><spring:message code="sr.srGrdSrAction"/></center>';
		
		 columnsCustSrDt = [
	                { 
	                    "sTitle": col_srGrdSrAction,
	                    "mData": "editUrl" ,
	                    "sWidth": "3%",
	                    "sClass": "tdCenter"
	               }
	              ,{ "sTitle": col_no, "mData": null ,"sClass": "tdCenter","sWidth": "3%" }
				  ,{ "sTitle": col_srnumber, "mData": "srNumber" ,"sClass": "tdLeft" ,"sWidth": "10%" }
				  ,{ "sTitle": col_srTypeProblem1, "mData": "srTypeProblem1" ,"sClass": "tdLeft" }
				  ,{ "sTitle": col_srSubject, "mData": "srSubject" ,"sClass": "tdLeft" }
				  ,{ "sTitle": col_srGrdSrPriority, "mData": "srPriorityDesc" ,"sClass": "tdLeft" }
				  ,{ "sTitle": col_srGrdSrStatus, "mData": "srStatusDesc" ,"sClass": "tdLeft" }
				  ,{ "sTitle": col_srGrdSrOpenDate, "mData": "srOpenedDate" ,"sClass": "tdCenter", "sWidth": "10%",
					  "fnRender": function(objData) {
		     		  		var srOpenedDate = new Date(objData.aData.srOpenedDate); 
		     		 		return formatDateDDMMYYYYWithTime(srOpenedDate);
			  			}   
				  }
				  ,{ "sTitle": col_srGrdSrDueDate, "mData": "srDueDate" ,"sClass": "tdCenter" ,"sWidth": "10%" ,
					  "fnRender": function(objData) {
		     		  		var srDueDate = new Date(objData.aData.srDueDate); 
		     		 		return formatDateDDMMYYYYWithTime(srDueDate);
			  			}     
				  }
				  ,{ "sTitle": col_srGrdSrOwner, "mData": "srOwnerName" ,"sClass": "tdLeft" ,"sWidth": "13%" }
				 ];
		
		//sTitle >> Caption Display Header Grid
		//mData >> Mapping to Json Data 
		
	}
	
	
	
	
	
</script>