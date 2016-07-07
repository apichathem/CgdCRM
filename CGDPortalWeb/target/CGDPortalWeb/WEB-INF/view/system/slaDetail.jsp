<%@page import="com.locus.jlo.web.constant.JLOWebConstant"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<div class="row">
	<div class="col-md-12">
		<h3 class="caption">
			<spring:message code="slamanagement.title" />
			<!-- <small>blank page</small> -->
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="home.htm"> <spring:message code="menu.home" />
				</a> <i class="fa fa-angle-right"></i></li>
			<li><a href="#"> <spring:message code="menu.system" />
			</a> <i class="fa fa-angle-right"></i></li>
			<li><a href="#"> <spring:message code="menu.system.sla" />
				</a></li>
		</ul>
	</div>
<form:form id="slaDetailForm" action="saveSla.htm" modelAttribute="SLAModelBean" onsubmit="return false;" method="post" autocomplete="off" class="form form-horizontal">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-reorder"></i>
					<spring:message code="slamanagement.detail.topic" />
				</div>
				<div class="tools">
					<a class="collapse" href="javascript:;"> </a>
				</div>
			</div>
			<div class="portlet-body">
				<form:hidden path="mode"/>
				<form:hidden path="slaId"/>
				<div class="form-body">
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-5">
									<spring:message code="slamanagement.detail.slaname" /> <span class="required"> * </span>
								</label>
								<div class="col-md-7">
									<form:input path="slaName" class="form-control"  data-rule-required="true" readonly="true"/>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-5">
									<spring:message code="slamanagement.detail.status" /> <span class="required"> * </span>
								</label>
								<div class="col-md-7">
									<form:select path="statusCd" class="form-control select2me" >
										<option></option>
										<form:options items="${CODEBOOK_LIST.SLA_STATUS }" itemValue="codeId" itemLabel="codeName" />
									</form:select>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-5">
									<spring:message code="slamanagement.detail.slavalue" /> <span class="required"> * </span>
								</label>
								<div class="col-md-4">
									<form:input path="slaUnit" class="form-control"  data-rule-required="true" data-rule-digits="true"/>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-5">
									<spring:message code="slamanagement.detail.slauom" />
								</label>
								<div class="col-md-7">
									<form:select path="SlaUomCd" class="form-control select2me" >
										<option></option>
										<form:options items="${CODEBOOK_LIST.SLA_UOM }" itemValue="codeId" itemLabel="codeName" />
									</form:select>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-5">
									<spring:message code="slamanagement.detail.desc" />
								</label>
								<div class="col-md-7">
									<form:textarea path="descp" class="form-control"/>
								</div>
							</div>
						</div>
						<div class="col-md-6">
						</div>
					</div>
					<hr>
					<div class="row">
						<div class="col-md-4">
							<div class="form-group">
								<label class="control-label col-md-5"> <spring:message code="lbl.createBy" />
								</label>
								<div class="col-md-7">
									<p class="form-control-static">${SLAModelBean.createBy}</p>
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="form-group">
								<label class="control-label col-md-5"> <spring:message code="lbl.createDate" />
								</label>
								<div class="col-md-7">
									<p class="form-control-static">${SLAModelBean.createDate}</p>
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
									<p class="form-control-static">${SLAModelBean.updateBy}</p>
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="form-group">
								<label class="control-label col-md-5"> <spring:message code="lbl.updateDate" />
								</label>
								<div class="col-md-7">
									<p class="form-control-static">${SLAModelBean.updateDate}</p>
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="form-group"></div>
						</div>
					</div> 
				</div>
			</div>
			<div class="form-actions right">
				<button id="saveSlaBtn" class="btn blue" type="button">
					<i class="fa fa-save"></i>
					<spring:message code="button.save.label" />
				</button>
				<button class="btn default" type="button" onclick="back()">
					<i class="fa fa-mail-reply"></i>
							<spring:message code="button.back.label" />
				</button>
			</div>
		</div>
	</div>
</form:form>
</div>
<script type="text/javascript">

$(document).ready(function() {
	$('#saveSlaBtn').click(function () {
		saveSla();
	});
});

function saveSla() {
	var $valid = $("#slaDetailForm").valid();
    if (!$valid) {
    	return false;
    }
    ajaxSubmitFormAndRedirect($("#slaDetailForm"));
}

function back() {
	redirectUrlAction("slaManagement.htm");
}
</script>