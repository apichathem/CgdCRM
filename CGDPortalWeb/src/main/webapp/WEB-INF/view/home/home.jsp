<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<script src="assets/scripts/custom/calendar.js"></script>
<script src="assets/scripts/custom/table-managed.js"></script>

<script>
window.onload = function(){
	jQuery(document).ready(function() {       
	   // initiate layout and plugins
	//    App.init();
	   TableManaged.init();
	   Calendar.init();
	});
};
</script>

<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="caption">
			<spring:message code="menu.home" /> <!-- <small>Service Request</small> -->
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="home.htm"> <spring:message code="menu.home" /> </a> <i
				class="fa fa-angle-right"></i></li>
		
		
		
			<li class="btn-group">
				<button type="button" class="btn blue dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-delay="1000" data-close-others="true">
				<span>
					<spring:message code="lbl.help" />
				</span>
				<i class="fa fa-angle-down"></i>
				</button>
				<ul class="dropdown-menu pull-right" role="menu">
					<li>
						<a href="pdf/home.docx">
							<spring:message code="lbl.downloadHelpDocument" />
						</a>
					</li>
					<%-- <li>
						<a href="#">
							<spring:message code="lbl.contactAdmin" />
						</a>
					</li> --%>
					<li>
						<a href="#">
							<spring:message code="lbl.reportIssue" />
						</a>
					</li>
					
				</ul>
			</li>
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->

	</div>
	
</div>



<div class="row ">
	<div class="col-md-10">
	<div class="page-body">
		<div class="tab-content">
			<div class="tab-pane active" id="tab_1_1">
				<div class="row">
					<div class="col-md-3">
						<ul class="list-unstyled profile-nav">
							<li>
								<img src="getImg.htm?src=${USER_PROFILE.userPic }" class="img-responsive" alt=""/>
								
							</li>
						</ul>
					</div>
					<div class="col-md-9">
						<div class="row">
							<div class="col-md-12 profile-info">
								<h2>${USER_PROFILE.firstName} ${USER_PROFILE.lastName}</h2>
								<div class="row">
									<div class="col-md-2"><h5><b><spring:message code="userManage.role"/></b></h5></div>
									<div class="col-md-10"><h5>${USER_PROFILE.roleName}</h5></div>
								</div>
								<div class="row">
									<div class="col-md-2"><h5><b><spring:message code="userManege.detail.agent.position"/></b></h5></div>
									<div class="col-md-10"><h5>${USER_PROFILE.positionName}</h5></div>
								</div>
								<div class="row">
									<div class="col-md-2"><h5><b><spring:message code="userManage.detail.department"/></b></h5></div>
									<div class="col-md-10"><h5>${USER_PROFILE.departmentName}</h5></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>
	<div class="col-md-6">
	</div>
	<div class="col-md-6">
	</div>
	
</div>

<div class="modal fade" id="basic" tabindex="-1" role="basic" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
				<h4 class="modal-title"><spring:message code="home.todolist"/></h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-md-12">
						<div class="form-group">
							<div class="col-md-9">
								<textarea name="markdown" data-provide="markdown" rows="10" data-error-container="#editor_error"></textarea>
								<div id="editor_error">
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn default" data-dismiss="modal"><spring:message code="button.cancel.label"/></button>
				<button type="button" class="btn blue" data-dismiss="modal" onclick="addMessage();"><spring:message code="button.save.label"/></button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
