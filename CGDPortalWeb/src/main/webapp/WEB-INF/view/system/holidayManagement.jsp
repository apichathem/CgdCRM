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
			<spring:message code="holiday.title" />
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="home.htm"> <spring:message code="menu.home" />
			</a> <i class="fa fa-angle-right"></i></li>
			<li><a href="#"> <spring:message code="menu.system" />
			</a> <i class="fa fa-angle-right"></i></li>
			<li><a href="#"> <spring:message code="menu.system.holidayManagement" />
			</a></li>
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<c:url var="deleteUrl" value="/deleteHoliday.htm" />
<div class="row">
	<div class="col-md-12">
		<div class="row">
			<div class="col-md-7">
				<div id="hoildayCalendar"></div>
			</div>
			<div class="col-md-5">
				
				<div class="portlet blue box">
					<div class="portlet-title">
						<div class="caption"><spring:message code="holidayManagement.topic" /></div>
					</div>
					
					<div class="portlet-body">
						<table id="resultTableId" class="table table-bordered dataTable">
						</table>
						
						<form:form id="holidayFormId" action="insertHoliday" modelAttribute="holidayModelBean" method="POST" class="form-horizontal">
							<div class="form-body">
								<div class="row">
									<div class="col-md-12">
										<div class="form-group">
											<label class="control-label col-md-2"> <spring:message code="holidayManagement.holiday" /><span
											class="required"> * </span>
											</label>
											<div class="col-md-10">
												<form:hidden path="holidayId"/>
												<form:input path="holidayDate" class="form-control" readonly="true" data-rule-required="true" />
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
										<div class="form-group">
											<label class="control-label col-md-2"> <spring:message code="holidayManagement.holidayName"/><span
											class="required"> * </span>
											</label>
											<div class="col-md-10">
												<form:input path="holidayName" class="form-control" data-rule-maxlength="80" data-rule-required="true"/>
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
										<div class="form-group">
											<label class="control-label col-md-2"> <spring:message code="holidayManagement.holidayType" /><span
											class="required"> * </span>
											</label>
											<div class="col-md-10">
												<%-- form:hidden path="typeCd" class="form-control" /> --%>
												
												<select name="typeCd" id="typeCd" class="form-control select2me" data-rule-required="true">
												</select>
											</div>
										</div>
									</div>
								</div>
							</div>
						</form:form>
						<div class="text-right">
							<button class="btn blue" type="button" onclick="saveHoliday()">
								<i class="fa fa-floppy-o"></i>
								<spring:message code="button.save.label" />
							</button>
							<button id="deleteBtnId" class="btn red" type="button" onclick="deleteRow()" disabled="disabled">
								<i class="fa fa-trash-o"></i>
								<spring:message code="button.delete.label" />
							</button>
							<button class="btn default" type="button" onclick="cancel()">
								<spring:message code="button.cancel.label" />
							</button>
						</div>
					</div>
					
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	var recordPerPage = <%=JLOWebConstant.recordsPerPage%>;
	var column1 = '<spring:message code="holidayManagement.result.date" />';
	var column2 = '<spring:message code="holidayManagement.result.name" />';
	var column3 = '<spring:message code="holidayManagement.result.type" />';
	var columns = [{ "sTitle": column1, "mData": "holidayDate"}
				  ,{ "sTitle": column2, "mData": "holidayName" }
				  ,{ "sTitle": column3, "mData": "typeName" }
				  ,{ "sTitle": 'Holiday Id', "mData": "holidayId", bVisible: false }
	              ];
              
	$(document).ready(function() {
		validateForm($("#holidayFormId"));
		
		// Datatable
		initajaxDataTable($('#resultTableId'), columns);
		var oTable = ajaxDataTable($('#resultTableId'), columns, 'getHolidayList.htm', '', recordPerPage, false, false);
		
		/* DBClick event handler */
    	$("#resultTableId tbody tr").die( "click" ); 	
		$("#resultTableId tbody tr").live('click', function () {	
	    	var aPos = oTable.fnGetPosition(this);
	    	var aData = oTable.fnGetData( aPos[0] );
	        
	    	var holidayId = aData[aPos].holidayId;
	    	loadJsonData('getHolidayDetail.htm', 'holidayId=' + holidayId, 'GET', callbackFunction);
	    	
	    	$('#deleteBtnId').removeAttr('disabled');
		});
		
		// Select 2
		//getCodebookSel2DropdownByCodeType($('#typeCd'), 'HOLIDAY_TYPE');
		getCodeBookComboByCodeType($("#typeCd"), "HOLIDAY_TYPE", '<spring:message code="lbl.select"/>');
		
		// Full Calendar
		$('#hoildayCalendar').fullCalendar({
		    weekends: true, // will hide Saturdays and Sundays
		    selectable: true,
		    dayClick: function(date, jsEvent, view) {
				var cDate = new Date(date);
				/*
					If the date is already has in database, get data for edit
					else new holiday
				*/
				cancel();
				$('#holidayDate').val(formatDateDDMMYYYY(cDate));
            },
            eventClick: function(calEvent, jsEvent, view) {

                var holidayId = calEvent.id;
    	    	loadJsonData('getHolidayDetail.htm', 'holidayId=' + holidayId, 'GET', callbackFunction);
    	    	
    	    	$('#deleteBtnId').removeAttr('disabled');
    	    	
            },
            dayRender: function (date, cell) {
                
                var today = new Date();

                // Month background
                /* if (date.getMonth() === today.getMonth()) {
                	cell.css("background-color", "#FFFFCC");
                } */

             	// Today background
                if (date.toDateString() === today.toDateString()) {
                	cell.css("background-color", "#FFFC82");
                }
            },
		    eventSources: [
	            {
	                url: 'getHoliday.htm', // use the `url` property
	                color: '#3a87ad;',    // an option!
	                textColor: '#FFFFFF'  // an option!
	            }
		   	],
		});
	});
	
	function callbackFunction(data, textStatus, jqXHR) {
		$('#holidayId').val(data.holidayId);
		var cDate = new Date(data.holidayDate);
		$('#holidayDate').val(formatDateDDMMYYYY(cDate));
		$('#holidayName').val(data.holidayName);
		$('#typeCd').select2("val",data.typeCd);
	}
	
	function saveHoliday() {
	    var $valid = validateForm($("#holidayFormId"));
	    if (!$valid) {
	    	return false;
	    }
	    
	    if ($('#holidayId').val() == '') {
	    	$('#holidayFormId').attr('action', 'insertHoliday.htm');
	    } else {
	    	$('#holidayFormId').attr('action', 'updateHoliday.htm');
	    }
	   	ajaxSubmitFormAndRedirect($("#holidayFormId"));
	}
	
	function deleteRow(){
		var holidayId = $('#holidayId').val();
		if (holidayId != '') {
			alertConfirm(confirmDeleteMsg,"deleteHoliday", holidayId);
		}
  		
	}
	
	function deleteHoliday(holidayId) {
		window.location = "${deleteUrl}?holidayId=" + holidayId;
	}
	
	function cancel() {
		$('#deleteBtnId').attr('disabled', 'disabled');
		$('#holidayId').val('');
		$('#holidayDate').val('');
		$('#holidayName').val('');
		$('#typeCd').select2("val", '');
		
		clearValidateForm( $("#holidayFormId"));
	}
</script>