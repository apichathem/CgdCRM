<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
					<form:form id="formContentAddr" action="update${kbGroup }Address.htm" class="form form-horizontal" method="post" modelAttribute="contentAddrModelBean">				
						<form:hidden path="contentId" id="addr_contentId"/>
						<form:hidden path="mode" id="addr_mode"/>
						<div class="form-body">
							<h5 class="form-section"><spring:message code="knowledge.tab.address.boxaddress" /></h5>
							<div class="row">
								
								<div class="col-md-6">
									<div class="form-group">
										<label class="control-label col-md-4"><spring:message code="knowledge.tab.address.addressno" /></label>
										<div class="col-md-8">
											<form:input path="houseNo" class="form-control" data-rule-required="true" data-msg-required="Address No is required." maxlength="100"/>
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label class="control-label col-md-4"><spring:message code="knowledge.tab.address.moo" /></label>
										<div class="col-md-8">
											<form:input path="moo" class="form-control" maxlength="100"/>
										</div>
									</div>
								</div>
							</div>
							
							<div class="row">							
								<div class="col-md-12">
									<div class="form-group">
										<label class="control-label col-md-2"><spring:message code="knowledge.tab.address.village" /></label>
										<div class="col-md-10">
											<form:input path="building" class="form-control" maxlength="100"/>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label class="control-label col-md-4"><spring:message code="knowledge.tab.address.alley" /></label>
										<div class="col-md-8">
											<form:input path="soi" class="form-control" maxlength="100"/>
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label class="control-label col-md-4"><spring:message code="knowledge.tab.address.street" /></label>
										<div class="col-md-8">
											<form:input path="road" class="form-control" maxlength="100"/>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label class="control-label col-md-4"><spring:message code="knowledge.tab.address.subdistrict" /></label>
										<div class="col-md-8">
											<form:select path="city" class="form-control select2me"></form:select>
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label class="control-label col-md-4"><spring:message code="knowledge.tab.address.district" /></label>
										<div class="col-md-8">
											<form:select path="district" class="form-control select2me"></form:select>
											
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label class="control-label col-md-4"><spring:message code="knowledge.tab.address.province" /></label>
										<div class="col-md-8">
											<form:select path="province" class="form-control select2me"></form:select>
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label class="control-label col-md-4"><spring:message code="knowledge.tab.address.zipcode" /></label>
										<div class="col-md-8">
											<form:input path="postalCd" class="form-control inputnumber" maxlength="5" />
<%-- 											<form:select path="postalCd" class="form-control select2me"></form:select> --%>
										</div>
									</div>
								</div>
							</div>
							<div class="row hide">
								<div class="col-md-6">
									<div class="form-group">
										<label class="control-label col-md-4"><spring:message code="knowledge.tab.address.area" /></label>
										<div class="col-md-8">
											<form:select path="area" class="form-control select2me">
												<form:option value=""></form:option>
												<form:options items="${ CODEBOOK_LIST.COUNTRY_AREA }" itemLabel="codeName" itemValue="codeId"/>
											</form:select>
										</div>
									</div>
								</div>
							</div>
							<h5 class="form-section"><spring:message code="knowledge.tab.address.boxtel" /></h5>
							<div class="row">
								<div class="col-lg-6 col-md-12">
									<div class="form-group">
										<label class="control-label col-md-2 col-lg-4"><spring:message code="knowledge.tab.address.telno" /> 1</label>
										<div class="col-md-6 col-lg-8">
											<div class="input-group">
												<form:input path="telNo1" class="form-control inputtel" maxlength="30"/>
												<span class="input-group-addon">
													#
												</span>
												<input type="text" name="telNo1Ext" id="telNo1Ext" value="" class="form-control telext" maxlength="5" />																												
											</div>											
										</div>
									</div>
								</div>
								<div class="col-lg-6 col-md-12">
									<div class="form-group">
										<label class="control-label col-md-2 col-lg-4"><spring:message code="knowledge.tab.address.telno" /> 2</label>
										<div class="col-md-6 col-lg-8">
											<div class="input-group">
												<form:input path="telNo2" class="form-control inputtel" maxlength="30"/>
												<span class="input-group-addon">
													#
												</span>
												<input type="text" name="telNo2Ext" id="telNo2Ext" value="" class="form-control telext" maxlength="5" />
											</div>
										</div>
									</div>
								</div>
								<div class="col-lg-6 col-md-12">
									<div class="form-group">
										<label class="control-label col-md-2 col-lg-4"><spring:message code="knowledge.tab.address.faxno" /></label>
										<div class="col-md-6 col-lg-8">
											<div class="input-group">
												<form:input path="faxNo1" class="form-control inputtel" maxlength="30"/>
												<span class="input-group-addon">
													#
												</span>
												<input type="text" name="faxNo1Ext" id="faxNo1Ext" value="" class="form-control telext" maxlength="5"/>
											</div>
										</div>
									</div>									
								</div>
								
								<div class="col-lg-6 col-md-12 hide">
									<div class="form-group">
										<label class="control-label col-md-2 col-lg-4"><spring:message code="knowledge.tab.address.faxno" /> 2</label>
										<div class="col-md-6 col-lg-8">
											<div class="input-group">
												<span class="input-group-addon">
													<i class="fa fa-fax"></i>
												</span>
												<form:input path="faxNo2" class="form-control inputtel" maxlength="30"/>
											</div>
										</div>
									</div>
								</div>
								<div class="col-lg-6 col-md-12 hide">
									<div class="form-group">
										<label class="control-label col-md-2 col-lg-4"><spring:message code="knowledge.tab.address.telno" /> 3</label>
										<div class="col-md-6 col-lg-8">
											<div class="input-group">
												<span class="input-group-addon">
													<i class="fa fa-phone"></i>
												</span>
												<form:input path="telNo3" class="form-control inputtel" maxlength="30"/>
											</div>
										</div>
									</div>
								</div>
							</div>
							<hr />
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label class="control-label col-md-4"><spring:message code="lbl.updateBy" /></label>
										<div class="col-md-8">
											<p id="addr_chgBy" class="form-control-static" />
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label class="control-label col-md-4"><spring:message code="lbl.updateDate" /></label>
										<div class="col-md-8">
											<p id="addr_chgDt" class="form-control-static" />
										</div>
									</div>
								</div>
							</div>
							
							<div class="row form-actions fluid">
								<div class="col-md-12">
									<div class="col-md-offset-3 col-md-9 text-right">
										<div class="btn-group"> 
											<button type="button" class="btn yellow" style="display:none" id="btn_addrEdit" name="btn_addrEdit">
												<i class="fa fa-edit"></i>
												<spring:message code="button.edit.label"/>
											</button>
										</div>
										<div class="btn-group"> 
											<button type="button" class="btn blue disabled" id="btn_addrSave" name="btn_addrSave">
												<i class="fa fa-floppy-o"></i>											
												<spring:message code="button.save.label"/>
											</button>
										</div>
										
										<div class="btn-group">	
											<button type="button" class="btn default" style="display:none" id="btn_addrCancel" name="btn_addrCancel">
												<spring:message code="button.cancel.label"/>
											</button>
										</div>
									</div>
								</div>
							</div>
						</div>
					</form:form>
<script type="text/javascript">
function initAddress(){
	getProvinceListDefaultVal($("#province"), "1","");
	getAmpurByProvinceIdListDefaultVal($("#district"),"1","", "");
	getCityByDistrictIdListDefaultVal($("#city"), "1", "", "");
	getCityByDistrictIdListDefaultVal($("#city"), "1", "", "");
	getZipcodeByCityIdListDefaultVal($("#postalCd"),"1","","");
	$("#province").on("change",function(){
		getAmpurByProvinceIdListDefaultVal($("#district"),$(this).find(":selected").val(),"", "");
		$("#city").select2("val","");
// 		$("#postalCd").select2("val","");
	});
	$("#district").on("change",function(){
		getCityByDistrictIdListDefaultVal($("#city"),$(this).find(":selected").val(),"", "");
// 		$("#postalCd").select2("val","");
	});
// 	$("#city").on("change",function(){
// 		getZipcodeByCityIdListDefaultVal($("#postalCd"),$(this).find(":selected").val(),"","");
// 	});	
// 	console.log($("#province :selected").val());
	$("#btn_addrEdit").on("click",function(){
		if($("#contentNumber").val()==""){
			alertMessage('<spring:message code="knowledge.detail" />','<spring:message code="knowledge.chooseContent" />');
			return;
		}
		$("#addr_mode").val("update");
		$("#addr_contentId").val($("#contentNumber").val());
		$("#btn_addrEdit").addClass("disabled");
		$("#btn_addrSave").removeClass("disabled");
		$("#btn_addrCancel").removeClass("disabled");
		formAddrReadOnly(false);
		validateForm($("#formContentAddr"));
	});
	$("#btn_addrSave").on("click",function(){
		if (!$("#formContentAddr").valid()) {
			return false;
		}
		$("#addr_contentId").val($("#contentNumber").val());
		$("#addr_mode").val("update");
		ajaxSubmitForm($("#formContentAddr"),function(data) {
			if(data.status=="success"){
// 				alertMessage("<spring:message code="knowledge.detail" />","<spring:message code="lbl.save.success" />");
				$("#addr_chgBy").text(data.ContentAddrDTO.chgBy);
				$("#addr_chgDt").text(timestamp2datetime(data.ContentAddrDTO.chgDt));
			}else{
				alertMessage("<spring:message code="knowledge.detail" />","<spring:message code="lbl.save.fail" />");
			}
			
		});
// 		$("#btn_addrEdit").removeClass("disabled");
// 		$("#btn_addrSave").addClass("disabled");
// 		$("#btn_addrCancel").addClass("disabled");
// 		formAddrReadOnly(true);
	});
	$("#btn_addrCancel").on("click",function(){
		$("#btn_addrEdit").removeClass("disabled");
		$("#btn_addrSave").addClass("disabled");
		$("#btn_addrCancel").addClass("disabled");
		formAddrReadOnly(true);
	});
	formAddrReadOnly(true);
	$("#btn_addrEdit").removeClass("disabled");
	$("#btn_addrSave").addClass("disabled");
	$("#btn_addrCancel").addClass("disabled");
}
function formAddrReadOnly(flag) {
	$("#formContentAddr input[type='text']").prop("readonly",flag);
	$("#formContentAddr .select2me").select2("readonly", flag);
	$("#formContentAddr .input-group-btn button").prop("disabled",flag);
	$("#formContentAddr input[type='checkbox']").prop("disabled",flag);
	
// 	$("#contentNumber").prop("readonly", true);
// 	$("#contentCatName").prop("readonly", true);
// 	$("#catType").prop("disabled", true);
// 	$("#catSubType").prop("disabled", true);
// 	$("#catCenterType").prop("disabled", true);
// 	$("#type").prop("readonly", true);
}
// tab address
function callbackDataAddress(addr) {
	$("#btn_addrEdit").removeClass("disabled");
	$("#btn_addrSave").addClass("disabled");
	$("#btn_addrCancel").addClass("disabled");
	/* address */
	$("#houseNo").val(addr.houseNo);
	$("#moo").val(addr.moo);
	$("#building").val(addr.building);
	$("#soi").val(addr.soi);
	$("#road").val(addr.road);
// 	$("#city").val(addr.city);
// 	$("#district").select2().select2('val',addr.district);
	$("#province").select2().select2('val',addr.province);
	getAmpurByProvinceIdListDefaultVal($("#district"),$("#province :selected").val(),addr.district, "");
	getCityByDistrictIdListDefaultVal($("#city"),addr.district ,addr.city, "");
// 	getZipcodeByCityIdListDefaultVal($("#postalCd"),addr.city,addr.postalCd,"");
	$("#postalCd").val(addr.postalCd);
	$("#area").select2().select2('val',addr.area);
	$("#telNo1").val(addr.telNo1);
	$("#telNo2").val(addr.telNo2);
	$("#telNo1Ext").val(addr.telNo1Ext);
	$("#telNo2Ext").val(addr.telNo2Ext);
	$("#telNo3").val(addr.telNo3);
	$("#faxNo1").val(addr.faxNo1);
	$("#faxNo1Ext").val(addr.faxNo1Ext);
	$("#faxNo2").val(addr.faxNo2);
	if (addr.chgBy != null){
		$("#addr_chgBy").text(addr.chgBy);
	}else{
		$("#addr_chgBy").text("");
	}
	if (addr.chgDt != null){
		$("#addr_chgDt").text(timestamp2datetime(addr.chgDt));
	}else{
		$("#addr_chgDt").text("");
	}
	if(typeof changeMap != "undefined"){
		if (addr.latLng != null && addr.latLng != "") {
			changeMap(addr.latLng);
		} else {
			lat = dlat;
			lng = dlng;
			resetMap();
		}
		
	}
	formAddrReadOnly(false);
	$("#btn_addrSave").removeClass("disabled");
}

function resetAddrForm(){
	$("#formContentAddr").closest('form')[0].reset();
	$("#formContentAddr").closest('form').find('.select2-offscreen').trigger('change');
	$("#addr_chgBy").text("");
	$("#addr_chgDt").text("");
}
</script>