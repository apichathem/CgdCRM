<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<div class="row">
	<div class="col-md-12">
		<h3 class="caption">
			<spring:message code="menuManagement.title" />
			<!-- <small>blank page</small> -->
		</h3>
		<ul class="page-breadcrumb breadcrumb">

			<li><i class="fa fa-home"></i> <a href="home.htm"> <spring:message
						code="menu.home" />
			</a> <i class="fa fa-angle-right"></i></li>
			<li><a href="#"> <spring:message code="menu.system" />
			</a> <i class="fa fa-angle-right"></i></li>
			<li> <a href="#"> <spring:message
						code="menuManagement.title" />
			</a></li>
		</ul>
	</div>
<form:form id="menuDetailForm" action="saveMenu.htm" modelAttribute="MenuDetailModelBean" onsubmit="return false;" method="post" autocomplete="off" class="form form-horizontal">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-reorder"></i>
					<spring:message code="menuManage.topic.menuInformation" />
				</div>
				<div class="tools">
					<a class="collapse" href="javascript:;"> </a>
				</div>
			</div>
			<div class="portlet-body">
				<form:hidden path="mode"/>
				<form:hidden path="menuId"/>
				<form:hidden path="menuNumber"/>
				<div class="form-body">
					<div class="row">
						<div class="col-md-7">
							<div class="form-group">
								<label class="control-label col-md-5">
									<spring:message code="menuManage.parentId" />
								</label>
								<div class="col-md-7">
									<form:select path="menuParentId" class="form-control select2me">
										<form:option value="0">เมนูหลัก|Parent Menu</form:option>
										<form:options items="${parentSelect}" itemLabel="menuName" itemValue="menuId" />
									</form:select>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-7">
							<div class="form-group">
								<label class="control-label col-md-5">
									<spring:message code="menuManage.caption" />
								</label>
								<div class="col-md-7">
									<form:input path="menuName" class="form-control"  data-rule-required="true" />
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-7">
							<div class="form-group">
								<label class="control-label col-md-5">
									<spring:message code="menuManage.action" />
								</label>
								<div class="col-md-7">
									<form:input path="menuAction" class="form-control" data-rule-required="true" />
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-7">
							<div class="form-group">
								<label class="control-label col-md-5">
									<spring:message code="menuManage.icon" />
								</label>
								<div class="col-md-7">
									<div class="input-group">
										<form:input path="menuIcon" class="form-control" data-rule-required="true"  readonly="true" data-rule-maxlength="30"/>
										<span class="input-group-addon"> 
											<a id="btn_icon_modal" href="#"  data-target="#dialogId" data-toggle="modal"> 
												<i class="fa fa-book"></i>
											</a>
										</span>
										<span class="input-group-addon"> 
											<a id="btn_clear_icon" style="cursor: pointer;"> 
												<i class="fa fa-minus-circle red"></i>
											</a>
										</span>
									</div>
								</div>
							</div>
						</div>
					</div>
					
					<form:input type="hidden" path="menuLevel" class="form-control input-xsmall "  data-rule-required="true" value="1"/>
								
					
					<div class="row">
						<div class="col-md-7">
							<div class="form-group">
								<label class="control-label col-md-5">
									<spring:message code="menuManage.orderNo" />
								</label>
								<div class="col-md-7">
									<form:input path="menuSeq" class="form-control input-xsmall inputnumber" data-rule-required="true" />
								</div>
							</div>
						</div>
					</div>
					
					
								
					<div class="row">
						<div class="col-md-7">
							<div class="form-group">
								<label class="control-label col-md-5 required">
									<spring:message code="menuManage.visible" />
								</label>
								<div class="col-md-7">
									<label>
										<form:radiobutton path="menuEnabled" value="Y"/>
										<spring:message code="lbl.show" />
									</label>
									<label>
										<form:radiobutton path="menuEnabled" value="N"/>
										<spring:message code="lbl.notShow" />
									</label>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="form-actions right">
				<button class="btn blue" type="button" onclick="saveMenu();">
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
<div class="modal fade" id="dialogId" role="basic" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div id="modal_content_div"></div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	validateForm($("#menuDetailForm"));

	$("#btn_icon_modal").click(function() {
		loadContentIntoModal($("#modal_content_div"), "openModalDialog.htm","iconDialog","menuManage.dialog.topic", ""); 	
	});

	$('#btn_clear_icon').click(function () {
		$('#menuIcon').val("");
	});
});
function saveMenu(){	
	
	var $valid = $("#menuDetailForm").valid();
    if (!$valid) {
    	return false;
    }
    ajaxSubmitFormAndRedirect($("#menuDetailForm"));
    
}
function back() {
	redirectUrlAction("menuManagement.htm");
}
</script>