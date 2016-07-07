<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<div class="row">
	<div class="col-md-12">
		<h3 class="caption">
			<spring:message code="activityScoresManagement.title" />
			<!-- <small>blank page</small> -->
		</h3>
		<ul class="page-breadcrumb breadcrumb">

			<li><i class="fa fa-home"></i> <a href="home.htm"> <spring:message
						code="menu.home" />
			</a> <i class="fa fa-angle-right"></i></li>
			<li> <i class="fa fa-money"></i> <a href="#"> <spring:message
						code="activityScoresManagement.title" />
			</a></li>
		</ul>
	</div>
<form:form id="activityScoresDetailForm" action="saveActivityScores.htm" modelAttribute="activityScoresDomain" onsubmit="return false;" method="post" autocomplete="off" class="form form-horizontal">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-reorder"></i>
					<spring:message code="activityScoresManagement.topic" />
				</div>
				<div class="tools">
					<a class="collapse" href="javascript:;"> </a>
				</div>
			</div>
			<div class="portlet-body">
				<form:hidden path="mode"/>
				<form:hidden path="actId"/>
				<div class="form-body">
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-5">
									<spring:message code="activityScoresManagement.name" />
								</label>
								<div class="col-md-7">
									<form:input path="actName" class="form-control"  data-rule-required="true" />
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-5">
									<spring:message code="activityScoresManagement.action" />
								</label>
								<div class="col-md-7">
									<form:input path="actActionCode" class="form-control" data-rule-required="true" />
								</div>
							</div>
						</div>
					</div>
					
					
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-5">
									<spring:message code="lbl.number" />
								</label>
								<div class="col-md-7">
									<form:input path="actSeq" class="form-control input-xsmall inputnumber" data-rule-required="true" />
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-5">
									<spring:message code="activityScoresManagement.score" />
								</label>
								<div class="col-md-7">
									<form:input path="actScores" class="form-control input-xsmall" data-rule-required="true" />
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-5">
									<spring:message code="activityScoresManagement.desc" />
								</label>
								<div class="col-md-7">
									<form:input path="actDesc" class="form-control" data-rule-required="true" />
								</div>
							</div>
						</div>
					</div>
							
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-5 required">
									<spring:message code="activityScoresManagement.visible" />
								</label>
								<div class="col-md-7">
									<label>
										<form:radiobutton path="actEnabled" value="Y"/>
										<spring:message code="lbl.show" />
									</label>
									<label>
										<form:radiobutton path="actEnabled" value="N"/>
										<spring:message code="lbl.notShow" />
									</label>
								</div>
							</div>
						</div>
					</div>					
				</div>
			</div>
			<div class="form-actions right">
				<button class="btn blue" type="button" onclick="saveActivityScores();">
					<i class="fa fa-save"></i>
					<spring:message code="button.save.label" />
				</button>
				<button class="btn default" type="button" onclick="back()">
					<spring:message code="button.back.label" />
				</button>
			</div>
		</div>
		
	</div>	
</form:form>
</div>

<script type="text/javascript">
$(document).ready(function() {
	validateForm($("#activityScoresDetailForm"));
});
function saveActivityScores(){	
	
	var $valid = $("#activityScoresDetailForm").valid();
    if (!$valid) {
    	return false;
    }
    ajaxSubmitFormAndRedirect($("#activityScoresDetailForm"));
    
}
function back() {
	redirectUrlAction("activityScoresManagement.htm");
}
</script>