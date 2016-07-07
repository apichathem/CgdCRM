<%@page import="com.locus.jlo.web.bean.dto.ContactDTO"%>
<%@page import="com.locus.common.utils.CollectionUtil"%>
<%--@page import="com.locus.jlo.web.constant.WebPortalConstant"--%>
<%--@page import="com.locus.jlo.entity.TpCodebookLang"--%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="com.locus.jlo.web.constant.JLOWebConstant"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%
request.setAttribute("contactDto", new ContactDTO());
%>

<form:form id="contactCreateForm" class="form-horizontal" action="createNewContact.htm" method="post" modelAttribute="contactDto" >
	<form:hidden path="indId"/>
	<form:hidden path="custId" id="consultingCustId"/>
	<form:hidden path="contId"/>
	<div class="row">
		<div class="col-md-12">
			<div class="form-body">
				<div class="row">
					<div class="col-md-4">
						<div class="form-group">
							<label class="control-label col-md-5"> <spring:message code="dialog.contact.detail.firstname" /> <span
										class="required"> * </span></label>
							<div class="col-md-7">
								<form:input path="firstName" class="form-control" data-rule-required="true"/>
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group">
							<label class="control-label col-md-5"> <spring:message code="dialog.contact.detail.lastname" /> </label>
							<div class="col-md-7">
								<form:input path="lastName" class="form-control"/>
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group">
							<label class="control-label col-md-5"> <spring:message code="dialog.contact.detail.mobile" /> <span
										class="required"> * </span></label>
							<div class="col-md-7">
								<form:input path="mobileNo" class="form-control" data-rule-required="true"/>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-4">
						<div class="form-group">
							<label class="control-label col-md-5"> <spring:message code="dialog.contact.detail.homephone" /> </label>
							<div class="col-md-7">
								<form:input path="homePhone" class="form-control"/>
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group">
							<label class="control-label col-md-5"> <spring:message code="dialog.contact.detail.workphone" /> </label>
							<div class="col-md-7">
								<form:input path="workPhone" class="form-control"/>
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group">
							<label class="control-label col-md-5"> <spring:message code="dialog.contact.detail.faxno" /> </label>
							<div class="col-md-7">
								<form:input path="faxPhone" class="form-control"/>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-4">
						<div class="form-group">
							<label class="control-label col-md-5"> <spring:message code="dialog.contact.detail.email" /> </label>
							<div class="col-md-7">
								<form:input path="email" name="email" class="form-control"/>
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group">
							<label class="control-label col-md-5"> <spring:message code="dialog.contact.detail.relationship" /> </label>
							<div class="col-md-7">
								<form:select path="relationCd" class="form-control select2me">
									<option></option>
									<form:options items="${CODEBOOK_LIST.RELATION }" itemValue="codeId" itemLabel="codeName"/>
								</form:select>
							</div>
						</div>
					</div>
					
				</div>
				<div class="row">
					<div class="col-md-4">
						<div class="form-group">
							<label class="control-label col-md-5"> <spring:message code="dialog.contact.detail.address1" /> </label>
							<div class="col-md-7">
								<form:input path="addr1" class="form-control"/>
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group">
							<label class="control-label col-md-5"> <spring:message code="dialog.contact.detail.address2" /> </label>
							<div class="col-md-7">
								<form:input path="addr2" class="form-control"/>
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group">
							<label class="control-label col-md-5"> <spring:message code="dialog.contact.detail.subdistrict" /> </label>
							<div class="col-md-7">
								<form:input path="subArea" class="form-control"/>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-4">
						<div class="form-group">
							<label class="control-label col-md-5"> <spring:message code="dialog.contact.detail.district" /> </label>
							<div class="col-md-7">
								<form:input path="area" class="form-control"/>
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group">
							<label class="control-label col-md-5"> <spring:message code="dialog.contact.detail.province" /> </label>
							<div class="col-md-7">
								<form:input path="province" class="form-control"/>
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group">
							<label class="control-label col-md-5"> <spring:message code="dialog.contact.detail.postal" /> </label>
							<div class="col-md-7">
								<form:input path="postalCode" class="form-control"/>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-4">
						<div class="form-group">
							<label class="control-label col-md-5"> <spring:message code="dialog.contact.detail.country" /> </label>
							<div class="col-md-7">
								<form:select path="countryCd" class="form-control select2me">
									<option></option>
									<form:options items="${CODEBOOK_LIST.COUNTRY }" itemValue="codeId" itemLabel="codeName"/>
								</form:select>
							</div>
						</div>
					</div>
				</div>
				
				<div class="form-actions right">
					<button id="btnSearchContactCreate" class="btn blue" type="button">
						<i class="fa fa-floppy-o"></i>
						<spring:message code="button.save.label" />
					</button>
				</div>
			</div>
		</div>
			
		
	</div>
</form:form>


<script>
	var evalFuncCreate ="${callbackfn}";


	$(document).ready(function() {
		$('#mobileNo').inputmask("099-999-9999");
		$('#homePhone').inputmask("09-999-9999");
		$('#workPhone').inputmask("09-999-9999");
		$('#faxPhone').inputmask("09-999-9999");
		
		$('#relationCd').select2();
		$('#countryCd').select2();
	});

	$("#btnSearchContactCreate").on("click",function(){
		createCustomerContact();
	});
	function createCustomerContact() {
		
		var $valid = validateForm($("#contactCreateForm"));
		if (!$valid) {
			return false;
		} else {
			var param = serialize($("#contactCreateForm")[0]);
			$("#consultingCustId").val($("#popcustId").val());
			console.log(param);
			ajaxSubmitForm($("#contactCreateForm"), function(data) {
				
				if (data != null) {
					if (data.resultCode == '0') {
						setDataIntoComponent(evalFuncCreate,data);
					}else{
						alertMessage("<spring:message code="dialog.contact.detail.topic"/>","<spring:message code="lbl.save.fail"/>");
					}
					
				}
			});
		}
		
	}
</script>