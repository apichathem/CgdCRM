<%@page import="com.locus.jlo.web.constant.JLOWebConstant"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="caption">
			<spring:message code="menu.system.userAssignment" />
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="home.htm"> <spring:message code="menu.home" />
			</a><i class="fa fa-angle-right"></i></li>
			<li><a href="#"> <spring:message code="menu.system" />
			</a> <i class="fa fa-angle-right"></i></li>
			<li><a href="#"> <spring:message code="menu.system.userAssignment" />
			</a></li>
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<form:form id="userAssignForm" name="userAssignForm" method="POST" action="" modelAttribute="userModelBean" enctype="multipart/form-data" class="form-horizontal" autocomplete="off">
<div class="row">
	<div class="col-md-12">
			<div class="portlet blue box">
				<div class="portlet-title">
					<div class="caption"><spring:message code="menu.system.userAssignment" /></div>
				</div>
				<div class="portlet-body">
					<div class="form-body">
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label class="control-label required col-md-5"><spring:message code="userManage.assignment.userlist" /><span
										class="required"> * </span></label>
									<div class="col-md-7">
										<form:select path="userId" class="form-control select2me" data-rule-required="true">
											<form:option value="" />
											<form:options items="${userList}" />
										</form:select>
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label class="col-md-5 control-label"><spring:message code="userManage.assignment.userAssignList" /></label>
									<div class="col-md-7">
										<form:select path="myTeamUserId" multiple="multiple" class="form-control multi-selects" data-rule-required="true">
										</form:select>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="form-actions right">
					<button id="saveAssignmentUserBtn" class="btn blue" type="button">
						<i class="fa fa-floppy-o"></i>
						<spring:message code="button.save.label" />
					</button>
					<button id="cancelAssignmentUserBtn" class="btn default" type="button">
						<spring:message code="button.cancel.label" />
					</button>
				</div>
			</div>
	</div>
</div>
</form:form>

<script>
	var text1 = '<spring:message code="userManage.assignment.list1"/>';
	var text2 = '<spring:message code="userManage.assignment.list2"/>';
	$(document).ready(function() {
		
		// multi select
		initMultiSelect($('#myTeamUserId'));
		
		// Drop down event
		$('#userId').change(function() {
			reloadMultiSelectUser(this.value);
		});

		// Save button event
		$('#saveAssignmentUserBtn').click(function() {
			saveAssignmentUser();
		});

		// Inital multi select when userId is not null
		if($('#userId').val() != '') {
			reloadMultiSelectUser($('#userId').val());
		}

		$('#cancelAssignmentUserBtn').click(function() {
			if($('#userId').val() != '') {
				reloadMultiSelectUser($('#userId').val());
			}
		});
	});

	function reloadMultiSelectUser(userIdSelected) {
		jLoBlockUI();
		
		// Call controller
		$.ajax({
	         url:"getUnderUserList.htm?userId=" + userIdSelected,
	         dataType: "json"
	    }).done( function(data){
		    // Clear multiselect list
		    $("#myTeamUserId").empty();

		    // Add result to option tag
		    $.each(data, function(key,value){
		    	$("#myTeamUserId").append($('<option/>',{
		            value: value.value,
		            text: value.text,
		            selected: value.selected
	            }));
		    });

		    // Reload multiselect
		    $("#myTeamUserId").multiSelect("refresh");
		});
	}

	function saveAssignmentUser() {
		if ($('#userId').find(":selected").val() == '') {
			var txtmsg = '<spring:message code="userManage.assignment.required.user" />';
			alertMessage('Invalid',txtmsg);

			return false;
		}

		$('#userAssignForm').attr('action', 'saveUserAssignment.htm');
    	ajaxSubmitFormAndRedirect($("#userAssignForm"));
	}
	
	function initMultiSelect(obj) {
		obj.multiSelect({
		    selectableHeader: "<input type='text' class='form-control' autocomplete='off'>",
		    selectionHeader: "<input type='text' class='form-control' autocomplete='off'>",
		    selectableFooter: "<div class='custom-header'>" + text1+ "</div>",
		    selectionFooter: "<div class='custom-header'>" + text2+ "</div>",
		    afterInit: function(ms) {
		        var that = this,
		            $selectableSearch = that.$selectableUl.prev(),
		            $selectionSearch = that.$selectionUl.prev(),
		            selectableSearchString = '#' + that.$container.attr('id') +
		            ' .ms-elem-selectable:not(.ms-selected)',
		            selectionSearchString = '#' +
		            that.$container.attr('id') + ' .ms-elem-selection.ms-selected';
		
		        that.qs1 = $selectableSearch.quicksearch(selectableSearchString)
		            .on('keydown', function(e) {
		                if (e.which === 40) {
		                    that.$selectableUl
		                        .focus();
		                    return false;
		                }
		            });
		
		        that.qs2 = $selectionSearch
		            .quicksearch(
		                selectionSearchString)
		            .on(
		                'keydown',
		                function(e) {
		                    if (e.which == 40) {
		                        that.$selectionUl
		                            .focus();
		                        return false;
		                    }
		                });
		    },
		    afterSelect: function() {
		        this.qs1.cache();
		        this.qs2.cache();
		    },
		    afterDeselect: function() {
		        this.qs1.cache();
		        this.qs2.cache();
		    }
		});
		
	}
	
</script>