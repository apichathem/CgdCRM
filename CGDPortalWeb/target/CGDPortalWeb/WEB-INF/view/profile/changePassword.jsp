<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<div class="row">
	<div class="col-md-12">
		<h3 class="caption">
			<spring:message code="changePassword.title" />
			<!-- <small>blank page</small> -->
		</h3>
		<ul class="page-breadcrumb breadcrumb">

			<li><i class="fa fa-home"></i> <a href="home.htm"> <spring:message
						code="menu.home" />
			</a> <i class="fa fa-angle-right"></i></li>
			<li><i class="fa fa-key"></i> <a href="#"> <spring:message
						code="changePassword.title" />
			</a></li>
		</ul>
	</div>
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-reorder"></i>
					<spring:message code="changePassword.title" />
				</div>
				<div class="tools">
					<a class="collapse" href="javascript:;"> </a>
				</div>
			</div>
			<div class="portlet-body">
			<form action="updateNewPassword.htm" onsubmit="return false;" method="post" autocomplete="off" class="form form-horizontal" id="formchangepassword">
				<input type="hidden" name="username" value="${USER_PROFILE.loginId}" />
				<input type="hidden" name="deviceid" value="DESKTOP" />
				<div class="form-body">
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-5">
									<spring:message code="changePassword.oldpassword" />
								</label>
								<div class="col-md-7">
									<input type="password" class="form-control" name="oldpassword" id="oldpassword"  data-rule-required="true" data-msg-required="Please enter Current Password." />
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-5 required">
									<spring:message code="changePassword.newpassword" />
								</label>
								<div class="col-md-7">
									<input type="password" class="form-control" name="newpassword" id="newpassword"  data-rule-required="true" data-msg-required="Please enter New Password." />
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-5 required">
									<spring:message code="changePassword.retypenewpassword" />
								</label>
								<div class="col-md-7">
									<input type="password" class="form-control" name="retypenewpassword" id="retypenewpassword" data-rule-required="true" data-msg-required="Please enter Re-type New Password." />
									<label id="labelnewpasswordnotmatch" style="display:none" class="help-block">Password does not match the confirm password</label>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-5 required">
									
								</label>
								<div class="col-md-7">
									<input type="button" id="btnChangepassword" class="btn blue" value="<spring:message code="changePassword.title" />" />
								</div>
							</div>
						</div>
					</div>
				</div>
			</form>
			</div>
		</div>
	</div>	
</div>
<script type="text/javascript">
$(document).ready(function() {
	validateForm($("#formchangepassword"));
	$("#retypenewpassword").on("keypress",function(){
		$("#labelnewpasswordnotmatch").hide();
	});
	$("#btnChangepassword").on("click",function(){
		changepasswordcommit();
	});
});
function changepasswordcommit(){
	if($("#newpassword").val() != $("#retypenewpassword").val()){
		setTimeout(function(){$("#retypenewpassword").closest('.form-group').addClass("has-error");
		$("#labelnewpasswordnotmatch").show();},500);
		return;
	}
	
	var $valid = $("#formchangepassword").valid();
    if (!$valid) {
    	return false;
    }
    
	
	
	
	
	ajaxSubmitForm($("#formchangepassword"),function(data){
		if(data!=null){
			if(data.status=="200"){
				alertMessage("<spring:message code="changePassword.title" />",data.message);
			}else{
				alertMessage("<spring:message code="changePassword.title" />",data.message);
			}
		}
		
		return false;
	});
}
</script>