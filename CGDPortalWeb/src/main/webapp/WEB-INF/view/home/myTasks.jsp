<%@page import="com.locus.jlo.web.constant.JLOWebConstant"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!-- BEGIN CONTENT -->
<div class="row">
	<div class="col-md-12">
		<h3 class="caption">
			<spring:message code="myTask.title" />
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i><a href="home.htm"> <spring:message code="menu.home" /> </a><i class="fa fa-angle-right"></i> <spring:message code="menu.mytask" /></li>
		</ul>
		
		<div class="row">
				
				<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
					<div class="dashboard-stat green">
						<div class="visual">
							<i class="fa fa-check-square-o"></i>
						</div>
						<div class="details">
							<div class="number" id="totalServiceRequest">
								 0
							</div>
							<div class="desc">
								 <spring:message code="mytask.serviceRequest.new" />
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
					<div class="dashboard-stat blue">
						<div class="visual">
							<i class="fa fa-users"></i>
						</div>
						<div class="details">
							<div class="number" id="totalActivity">
								 0
							</div>
							<div class="desc">
								 <spring:message code="mytask.activity.new" />
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
					<div class="dashboard-stat yellow">
						<div class="visual">
							<i class="fa fa-book"></i>
						</div>
						<div class="details">
							<div class="number" id="totalKb">
								 0
							</div>
							<div class="desc">
								 <spring:message code="mytask.kb.new" />
							</div>
						</div>
					</div>
				</div>
			</div>
		<!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="box_link" id="box_sr"></div>
		<div class="portlet box green">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-barcode"></i><spring:message code="mytask.serviceRequest" />
				</div>
				<div class="tools">
					<a href="javascript:;" class="collapse">
					</a>
				</div>
			</div>
			<div class="portlet-body">
				<table id="srTaskList" class="table table-bordered dataTable">
				</table>
			</div>
		</div>
		<!-- END EXAMPLE TABLE PORTLET-->
		
		<!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="box_link" id="box_activity"></div>
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-bullhorn"></i><spring:message code="mytask.activity" />
				</div>
				<div class="tools">
					<a href="javascript:;" class="collapse">
					</a>
				</div>
			</div>
			<div class="portlet-body">
				<table id="activityTaskList" class="table table-bordered dataTable">
				</table>
			</div>
			
		</div>
		<!-- END EXAMPLE TABLE PORTLET-->
		
		<!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="box_link" id="box_kb"></div>
		<div class="portlet box yellow">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-book"></i><spring:message code="mytask.kb" />
				</div>
				<div class="tools">
					<a href="javascript:;" class="collapse">
					</a>
				</div>
			</div>
			<div class="portlet-body">
				<table id="kbTaskList" class="table table-bordered dataTable">
				</table>
			</div>
			
		</div>
		<!-- END EXAMPLE TABLE PORTLET-->
		
	</div>
</div>
<script >
var recordPerPage = <%=JLOWebConstant.recordsPerPage%>;
var columnAct1 = '<spring:message code="mytask.activity.activityNo" />';
var columnAct2 = '<spring:message code="mytask.activity.title" />';
var columnAct4 = '<spring:message code="mytask.activity.assignTo" />';
var columnAct5 = '<spring:message code="mytask.activity.type" />';
var columnAct6 = '<spring:message code="mytask.activity.status" />';
var columnAct7 = '<spring:message code="mytask.activity.dueDate" />';
var columnAct9 = '<spring:message code="mytask.activity.createdDate" />';

var columnActs = [{ 
				    "sTitle": actionLabel,
				    "mData": "editUrl",
				    "sClass": "text-center",
				    "sWidth": "3%",
				}
			  //,{ "sTitle": columnAct1, "mData": "actNumber" }
			  ,{ "sTitle": columnAct2, "mData": "title" }
			  //,{ "sTitle": columnAct4, "mData": "assignedToName" }
			  ,{ "sTitle": columnAct5, "mData": "actTypeName" }
			  ,{ "sTitle": columnAct6, "mData": "actStatusName" }
			  ,{ "sTitle": columnAct7, "mData": "dueDatetime" }
			  ,{ "sTitle": columnAct9, "mData": "regDatetime" }
               ];
               
var columnSr1 = '<spring:message code="mytask.sr.srGrdSrNo" />';
var columnSr2 = '<spring:message code="mytask.sr.srGrdSrNumber" />';
var columnSr3 = '<spring:message code="mytask.sr.srGrdTypeProblem1" />';
var columnSr4 = '<spring:message code="mytask.sr.srGrdSubject" />';
var columnSr5 = '<spring:message code="mytask.sr.srGrdPriority" />';
var columnSr6 = '<spring:message code="mytask.sr.srGrdSrStatus" />';
var columnSr7 = '<spring:message code="mytask.sr.srGrdSrOpenDate" />';
var columnSr8 = '<spring:message code="mytask.sr.srGrdSrDueDate" />';
var columnSr9 = '<spring:message code="mytask.sr.srGrdSrOwner" />';

var columnSrs = [{ 
				    "sTitle": actionLabel,
				    "mData": "editUrl",
				    "sClass": "text-center",
				    "sWidth": "3%",
				}
			  ,{ "sTitle": columnSr1, "mData": 'srNumber' }
			  ,{ "sTitle": columnSr2, "mData": 'srNumber' }
			  ,{ "sTitle": columnSr3, "mData": 'srTypeProblem1' }
			  ,{ "sTitle": columnSr4, "mData": 'srSubject' }
			  ,{ "sTitle": columnSr5, "mData": 'srPriorityDesc' }
			  ,{ "sTitle": columnSr6, "mData": 'srStatusDesc' }
			  ,{ "sTitle": columnSr7, "sWidth": "10%", "sClass": "text-center", "mData": null, 
			    	"fnRender": function(objData) {
		     		  	var srOpenedDate = new Date( objData.aData.srOpenedDate ); 
		     		 	return formatDateDDMMYYYYWithTime(srOpenedDate);
			  		} 
			    }
			  ,{ "sTitle": columnSr8, "sWidth": "10%", "sClass": "text-center", "mData": null, 
			    	"fnRender": function(objData) {
		     		  	var srDueDate = new Date( objData.aData.srDueDate ); 
		     		 	return formatDateDDMMYYYYWithTime(srDueDate);
			  		} 
			    }
			  ,{ "sTitle": columnSr9, "mData": 'srOwnerName' }
               ];

var columnKb1 = '<spring:message code="mytask.kb.kbNo" />';
var columnKb2 = '<spring:message code="mytask.kb.title" />';
var columnKb3 = '<spring:message code="mytask.kb.requestDate" />';
var columnKb4 = '<spring:message code="mytask.kb.rejectBy" />';
var columnKb5 = '<spring:message code="mytask.kb.rejectDate" />';

var columnKbs = [{ 
			    "sTitle": actionLabel,
			    "mData": "editUrl",
			    "sClass": "text-center",
			    "sWidth": "3%",
			}
			,{ "sTitle": columnKb1, "mData": "contentNumber" }
			,{ "sTitle": columnKb2, "mData": "title" }
			,{ "sTitle": columnKb3, "sWidth": "10%", "sClass": "text-center", "mData": null, 
		    	"fnRender": function(objData) {
	     		  	var chgDt = new Date( objData.aData.chgDt ); 
	     		 	return formatDateDDMMYYYYWithTime(chgDt);
		  		} 
		    }
			,{ "sTitle": columnKb4, "mData": "approveName" }
			,{ "sTitle": columnKb5, "sWidth": "10%", "sClass": "text-center", "mData": null, 
		    	"fnRender": function(objData) {
	     		  	var approveDt = new Date( objData.aData.approveDt ); 
	     		 	return formatDateDDMMYYYYWithTime(approveDt);
		  		} 
		    }
];

$(document).ready(function() {      
	
	// Activity Task
	var actTable = ajaxDataTable($('#activityTaskList'), columnActs, 'getActivityTask.htm', '', recordPerPage, true, false);
	
	var actSettings = actTable.fnSettings();
	
	setTimeout(function () {
		$("#totalActivity").html(actSettings.fnRecordsDisplay());
	}, 1000);
	
	/* $('#activityTaskList tbody').on( 'dblclick touchstart', 'tr', function () {
		var aPos = actTable.fnGetPosition(this);
	    var aData = actTable.fnGetData( aPos[0] );
	    // activity.htm?act_no=
        var actNumber = aData[aPos].actNumber;
        redirectUrlAction('activity.htm?module=mytask&mode=update&act_number=' + actNumber);
	}); */
	
	
	// Service Request Task
	var srTable = ajaxDataTable($('#srTaskList'), columnSrs, 'getServiceRequestTask.htm', '', recordPerPage, true, false);
	
	var srSettings = srTable.fnSettings();
	
	setTimeout(function () {
		$("#totalServiceRequest").html(srSettings.fnRecordsDisplay());
	}, 1000);
	
	// serviceRequestDetail.htm?mode=update&sr_number=SR-20140714-0000090
	/* $('#srTaskList tbody').on( 'dblclick touchstart', 'tr', function () {
		var aPos = srTable.fnGetPosition(this);
	    var aData = srTable.fnGetData( aPos[0] );
        var srNumber = aData[aPos].srNumber;
        redirectUrlAction('serviceRequestDetail.htm?module=mytask&mode=update&sr_number=' + srNumber);
        
	}); */

	// Knowledge base Task
	var kbTable = ajaxDataTable($('#kbTaskList'), columnKbs, 'getKbTask.htm', '', recordPerPage, true, false);

	var kbSettings = kbTable.fnSettings();
	setTimeout(function () {
		$("#totalKb").html(kbSettings.fnRecordsDisplay());
	}, 1000);
});


</script>
<!-- END JAVA SCRIPTS -->