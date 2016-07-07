<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="caption">
			<spring:message code="userManagement.title" />
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="home.htm"> <spring:message code="menu.home" />
			</a><i class="fa fa-angle-right"></i></li>
			<li><a href="userManagement.htm"> <spring:message code="userManagement.title" /></a><i class="fa fa-angle-right"></i></li>
			<li><spring:message code="userManege.detail.topic" /></li>
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<form:form id="userDetailManagementDomain" name="userDetailManagementDomain" action="saveUser.htm"  modelAttribute="userModelBean" class="form-horizontal" enctype="multipart/form-data" autocomplete="off">
	<form:hidden path="agentId" id="agentId"/>
	<div class="row">
		<div class="col-md-12">
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-reorder"></i>
						<spring:message code="userManege.detail.topic" />
					</div>
					<div class="tools">
						<a class="collapse" href="javascript:;"> </a>
					</div>
				</div>
				<div class="portlet-body form">
					<!-- BEGIN FORM-->

					<form:hidden path="userId" />
					<form:hidden path="mode" />
					<form:hidden path="userPic"/>
					<div class="form-body">
						<h5 class="form-section">
							<spring:message code="userManege.detail.user.topic" />
						</h5>
						<div class="row">
							 <div class="col-md-4">
								<div class="form-group ">
									<label class="control-label col-md-5"><spring:message code="userManage.image" /></label>
									<div class="col-md-7">
										<div class="fileinput fileinput-new" data-provides="fileinput">
											<div id="viewer" class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 200px; height: 150px;">
												<c:if test="${userModelBean.mode == 'update'}">
													<img src="getImg.htm?src=${userModelBean.userPic }"/>
												</c:if>
											</div>
											<div>
												<span class="btn default btn-file">
													<span class="fileinput-new">
														 Select image
													</span>
													<span class="fileinput-exists">
														 Change
													</span>
													<input type="file" name="imgFile">
												</span>
												<a href="#" class="btn default fileinput-exists" data-dismiss="fileinput">
													 Remove
												</a>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-8">
							
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label required col-md-5"><spring:message code="userManage.login" /><span
												class="required"> * </span></label>
											<div class="col-md-7">
												<c:if test="${userModelBean.mode == 'insert'}">
													<form:input path="loginId" class="form-control" data-rule-required="true" data-rule-maxlength="20" autocomplete="off" data-rule-minlength="5" />
												</c:if>
												<c:if test="${userModelBean.mode == 'update'}">
													<form:input path="loginId" class="form-control" readonly="true" data-rule-required="true" data-rule-maxlength="20" autocomplete="off" />
												</c:if>
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label required col-md-5"><spring:message code="userManage.password" /><span
												class="required"> * </span></label>
											<div class="col-md-7">
												<form:password path="password" class="form-control" data-rule-maxlength="50" showPassword="true" data-rule-required="true"/>
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label col-md-5"><spring:message code="userManage.useYn" /></label>
											<div class="col-md-7">
												<form:select id="useYn" path="useYn" class="form-control select2me">
													<form:options items="${yn}" />
												</form:select>
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label required col-md-5"><spring:message
													code="userManage.result.role" /><span
												class="required"> * </span></label>
											<div class="col-md-7">
												<%-- <form:input id="roleId" path="roleId" class="form-control" data-rule-required="true" data-rule-maxlength="20"/>
												 --%><form:select id="roleId" path="roleId" class="form-control select2me" data-rule-required="true">
													<option></option>
													<form:options items="${roles}" />
												</form:select>
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label col-md-5"><spring:message code="userManage.reportto" /></label>
											<div class="col-md-7">
												<form:input id="reportName" path="reportName" class="form-control" readonly="true"/>
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
										</div>
									</div>
								</div>
							</div>
						</div>
						
						<h5 class="form-section">
							<spring:message code="userManege.detail.agent.topic" />
						</h5>
						<div class="row">
							<div class="col-md-4">
								<%-- <div class="form-group">
									<label class="control-label required col-md-5"><spring:message
											code="userManege.detail.agent.agentNo" /><span
										class="required"> * </span></label>
									<div class="col-md-7">
										<form:input id="agentNo" path="agentNo" class="form-control" data-rule-required="true" data-rule-maxlength="20"/>
									</div>
								</div> --%>
							</div>
							
							<div class="col-md-8">
							</div>
						</div>
						<div class="row">
							<div class="col-md-4">	
								<div class="form-group">
									<label class="control-label col-md-5"><spring:message code="userManege.detail.agent.agentName" /><span
										class="required"> * </span></label>
									<div class="col-md-7">
										<form:input id="firstName" path="firstName" class="form-control" data-rule-required="true" data-rule-maxlength="100"/>
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-5"><spring:message code="userManege.detail.agent.agentSurname" /><span
										class="required"> * </span></label>
									<div class="col-md-7">
										<form:input id="lastName" path="lastName" class="form-control" data-rule-required="true" data-rule-maxlength="100"/>
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-5"><spring:message code="userManage.detail.department" /><span
										class="required"> * </span></label>
									<div class="col-md-7">
										<form:hidden path="departmentCd" class="form-control"  data-rule-required="true" />
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-5"><spring:message code="userManege.detail.agent.email" /><span
										class="required"> * </span></label>
									<div class="col-md-7">
										<div class="input-group">
											<form:input id="email" type="email" path="email" class="form-control" data-rule-required="true" data-rule-maxlength="100"/>
											<span class="input-group-addon"> <i class="fa fa-envelope"></i>
											</span>
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-5"><spring:message code="userManege.detail.agent.mobileNo" /></label>
									<div class="col-md-7">
										<div class="input-group">
											<form:input id="mobileNo" path="mobileNo" class="form-control" data-rule-minlength="6" data-rule-maxlength="20"/>
											<span class="input-group-addon"> <i class="fa fa-phone"></i>
											</span>
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-5"><spring:message code="userManege.detail.agent.position" /></label>
									<div class="col-md-7">
										<form:hidden path="positionCd" class="form-control" />
									</div>
								</div>
							</div>
						</div>
						<%-- <div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label required col-md-5"><spring:message code="userManege.detail.agent.workNo" /></label>
									<div class="col-md-7">
										<div class="input-group">
											<form:input id="workNo" path="workNo" class="form-control" data-rule-minlength="9" data-rule-maxlength="20"/>
											<span class="input-group-addon"> <i class="fa fa-phone"></i>
											</span>
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-5"><spring:message code="userManege.detail.agent.extNo" /></label>
									<div class="col-md-7">
										<form:input id="extNo" path="extNo" class="form-control" data-rule-maxlength="20"/>
									</div>
								</div>
							</div>
							
						</div> --%>
						
						<%----%> <hr>
						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-5"> <spring:message code="lbl.createBy" />
									</label>
									<div class="col-md-7">
										<p class="form-control-static">${userModelBean.createBy}</p>
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-5"> <spring:message code="lbl.createDate" />
									</label>
									<div class="col-md-7">
										<p class="form-control-static">${userModelBean.createDate}</p>
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group"></div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-5"> <spring:message code="lbl.updateBy" />
									</label>
									<div class="col-md-7">
										<p class="form-control-static">${userModelBean.updateBy}</p>
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-5"> <spring:message code="lbl.updateDate" />
									</label>
									<div class="col-md-7">
										<p class="form-control-static">${userModelBean.updateDate}</p>
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group"></div>
							</div>
						</div>  
					</div>
					<div class="form-actions right">
						<button type="button" class="btn blue" onclick="saveUser()">
							<i class="fa fa-floppy-o"></i>
							<spring:message code="button.save.label" />
						</button>
						<button type="button" class="btn default" onclick="back()">
							<i class="fa fa-mail-reply"></i>
							<spring:message code="button.back.label" />
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</form:form>
<!-- report to dialog -->	
<div class="modal fade" id="userDialog" role="basic" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div id="modal_content_div"></div>
		</div>
	</div>
</div>	

<script type="text/javascript">
	var plaseSelectMsg  = '<spring:message code="message.please.select" />';

	$(document).ready(function() {
		// Select 2
		getCodebookSel2DropdownByCodeType($('#positionCd'), 'POSITION');
		getCodebookSel2DropdownByCodeType($('#departmentCd'), 'DEPARTMENT');
		
		$('#mobileNo').inputmask({ "mask": "099-999-9999"});
		$('#workNo').inputmask("09-999-9999");
		$('#socialId').inputmask({ "mask": "9", "repeat": 13 },
	            { onKeyValidation: function (result) {
                    console.log("onKeyValidation : " + result);
                    } });
		
		
		// User dialog button
		$("#btnSearchOwner").click(function() {		 
			loadContentIntoModal($("#modal_content_div"), "openModalDialog.htm","userRoleDialog","modal.header.user", "selectedReportToUser");
		});
		// User dialog clear button
		$("#btnClearOwner").click(function() {
			$("#reportTo").val("");
			$("#reportName").val("");
		});
		
		// Select 2
		//getCodebookSel2DropdownByCodeType($('#positionCode'), 'POSITION', plaseSelectMsg);//
		
		//alert($('#roleId').val());
		//alert($('#roleId :selected').text());
		//$('#roleId').select2().select2('val','$("#roleId :selected").text()');
	});

	function saveUser() {
		var $valid = validateForm($("#userDetailManagementDomain"));
	    if (!$valid) {
	    	return false;
	    }
	    if ($('#loginId').val() == '') {
	    	$('#userDetailManagementDomain').attr('action', 'insertUser.htm');
	    } else {
	    	$('#userDetailManagementDomain').attr('action', 'updateUser.htm');
	    }
		
	    ajaxSubmitFormAndRedirect($("#userDetailManagementDomain"));
	}

	function back() {
		redirectUrlAction("userManagement.htm");
		/* $('#userDetailManagementDomain').attr('action', 'userManagement.htm');
		$('#userDetailManagementDomain').submit(); */
	}
	
	function selectedReportToUser(jsonData){
		$('#reportTo').val(jsonData.userId);
		$('#reportName').val(jsonData.firstName + ' ' + jsonData.lastName);
		$("#userDialog").modal("hide");
	}
</script>
