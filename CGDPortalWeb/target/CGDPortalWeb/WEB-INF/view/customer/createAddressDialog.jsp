<%@page import="com.locus.common.utils.CollectionUtil"%>
<%@page import="com.locus.common.constant.WebPortalConstant"%>
<%--@page import="com.locus.jlo.entity.TpCodebookLang"--%>
<%@page import="com.locus.jlo.web.bean.modelbean.CodebookModelBean" %>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="com.locus.jlo.web.constant.JLOWebConstant"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<form:form id="addressForm" class="form-horizontal" action="createNewAddress.htm" method="post" modelAttribute="addressDto" >
	<form:hidden path="custId"/>
	<form:hidden path="addrId"/>
	<div class="row">
		<div class="col-md-12">
			<div class="form-body">
				<div class="row">
					<div class="col-md-4">
						<div class="form-group">
							<label class="control-label col-md-5"> <spring:message code="dialog.address.detail.addr1" /><span class="required">*</span> </label>
							<div class="col-md-7">
								<form:input path="addr1" class="form-control" data-rule-required="true" />
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group">
							<label class="control-label col-md-5"> <spring:message code="dialog.address.detail.addr2" /> </label>
							<div class="col-md-7">
								<form:input path="addr2" class="form-control" />
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group">
							<label class="control-label col-md-5"> <spring:message code="dialog.address.detail.subDistrict" /> <span class="required">*</span></label>
							<div class="col-md-7">
								<form:input path="subArea" class="form-control"  data-rule-required="true"/>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-4">
						<div class="form-group">
							<label class="control-label col-md-5"> <spring:message code="dialog.address.detail.district" /> <span class="required">*</span></label>
							<div class="col-md-7">
								<form:input path="area" class="form-control" data-rule-required="true"/>
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group">
							<label class="control-label col-md-5"> <spring:message code="dialog.address.detail.province" /><span class="required">*</span> </label>
							<div class="col-md-7">
								<form:input path="province" class="form-control" data-rule-required="true" />
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group">
							<label class="control-label col-md-5"> <spring:message code="dialog.address.detail.postalCode" /> </label>
							<div class="col-md-7">
								<form:input path="postalCode" class="form-control" />
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-4">
						<div class="form-group">
							<label class="control-label col-md-5"> <spring:message code="dialog.address.detail.country" /> </label>
							<div class="col-md-7">
								<form:select path="countryCd" class="form-control select2me">
									<option></option>
									<form:options items="${CODEBOOK_LIST.COUNTRY }" itemValue="codeId" itemLabel="codeName"/>
								</form:select>
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group">
							<label class="control-label col-md-5"> <spring:message code="dialog.address.detail.type" /> <span
										class="required"> * </span></label>
							<div class="col-md-7">
								<form:select path="addrTypeCd" class="form-control select2me" data-rule-required="true">
									<option></option>
									<form:options items="${CODEBOOK_LIST.ADDRESS_TYPE }" itemValue="codeId" itemLabel="codeName"/>
								</form:select>
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group">
						</div>
					</div>
				</div>
				
				<div class="form-actions right">
					<button id="btnSearchContact" class="btn blue" type="button" onclick="createCustomerAddress();">
						<i class="fa fa-floppy-o"></i>
						<spring:message code="button.save.label" />
					</button>
				</div>
			</div>
		</div>
			
		
	</div>
</form:form>


<script type="text/javascript">
	var evalFunc ="${callbackfn}";

	$(document).ready(function() {
		$('#mobileNo').inputmask("099-999-9999");
		$('#homePhone').inputmask("09-999-9999");
		$('#workPhone').inputmask("09-999-9999");
		$('#faxPhone').inputmask("09-999-9999");
		
		$('#addrTypeCd').select2();
		$('#countryCd').select2();
		 
		
	});

</script>