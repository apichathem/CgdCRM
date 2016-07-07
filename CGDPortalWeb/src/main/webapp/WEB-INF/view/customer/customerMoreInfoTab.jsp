<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="locus" uri="/WEB-INF/tld/locus.tld"%>

<!-- Begin Content -->
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN FORM-->
		<form:form name="moreInfoForm" id="moreInfoForm" class="form-horizontal" modelAttribute="custModelBean" action="saveMoreInfo.htm">
			<!-- Begin form-body -->
			<form:hidden path="mode"/>
			<form:hidden path="indId"/>
			<form:hidden path="custId"/>
			<div class="form-body">
				<br>
				<!-- row1 -->
				<div class="row">
					<div class="col-md-4">
						<div class="form-group">
							<label class="col-md-5">
								<spring:message code="cust.moreinfo.gender"/>
							</label>
							<div class="col-md-7">
								<form:select path="custGender" class="form-control select2me">
									<option></option>
									<form:options items="${CODEBOOK_LIST.GENDER }" itemValue="codeId" itemLabel="codeName"/>
								</form:select>
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group">
							<label class="col-md-5">
								<spring:message code="cust.moreinfo.birthdate" />
							</label>
							<div class="col-md-7">
								<div class="input-group">
									<div data-date-end-date="+0d" data-date-format="dd/mm/yyyy" class="input-group date date-picker">
										<form:input id="custBirthDate" path="custBirthDate" class="form-control maskdate"/>
										<span class="input-group-btn">
											<button class="btn default" type="button"><i class="fa fa-calendar"></i></button>
										</span>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group">
							<label class="col-md-5">
								<spring:message code="cust.moreinfo.segmentation" />
							</label>
							<div class="col-md-7">
								<form:select path="custSegmentation" class="form-control select2me">
									<option></option>
									<form:options items="${CODEBOOK_LIST.CUST_SEGMENT }" itemValue="codeId" itemLabel="codeName"/>
								</form:select>
							</div>
						</div>
					</div>
				</div>
				<!-- row2 -->
				<div class="row">
					<div class="col-md-4">
						<div class="form-group">
							<label class="col-md-5">
								<spring:message code="cust.moreinfo.marriageStatus" />
							</label>
							<div class="col-md-7">
								<form:select path="custMarriageStatus" class="form-control select2me">
									<option></option>
									<form:options items="${CODEBOOK_LIST.MARITAL_STATUS }" itemValue="codeId" itemLabel="codeName"/>
								</form:select>
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group">
							<label class="col-md-5">
								<spring:message code="cust.moreinfo.age" />
							</label>
							<div class="col-md-7">
								<input type="hidden" id="age"/>
								<input type="text" id="custAge" name="custAge" class="form-control" readonly="readonly" />
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group">
							<label class="col-md-5">
								<spring:message code="cust.moreinfo.education" />
							</label>
							<div class="col-md-7">
								<form:select path="custEducation" class="form-control select2me">
									<option></option>
									<form:options items="${CODEBOOK_LIST.EDUCATION }" itemValue="codeId" itemLabel="codeName"/>
								</form:select>
							</div>
						</div>
					</div>
				</div>
				<!-- row3 -->
				<div class="row">
					<div class="col-md-4">
						<div class="form-group">
							<label class="col-md-5">
								<spring:message code="cust.moreinfo.race" />
							</label>
							<div class="col-md-7">
								<form:select path="custRace" class="form-control select2me">
									<option></option>
									<form:options items="${CODEBOOK_LIST.RACE }" itemValue="codeId" itemLabel="codeName"/>
								</form:select>
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group">
							<%-- <label class="col-md-5">
								<spring:message code="cust.moreinfo.citizenId" />
							</label>
							<div class="col-md-7">
								<form:input id="custCitizenIdTab" path="custCitizenIdTab" class="form-control" data-rule-maxlength="20"/>
							</div> --%>
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group">
							<label class="col-md-5">
								<spring:message code="cust.moreinfo.salary" />
							</label>
							<div class="col-md-7">
								<form:select path="custSalary" class="form-control select2me">
									<option></option>
									<form:options items="${CODEBOOK_LIST.SALARY_RANGE }" itemValue="codeId" itemLabel="codeName"/>
								</form:select>
							</div>
						</div>
					</div>
				</div>
				<!-- row4 -->
				<div class="row">
					<div class="col-md-4">
						<div class="form-group">
							<label class="col-md-5">
								<spring:message code="cust.moreinfo.nationality" />
							</label>
							<div class="col-md-7">
								<form:select path="custNationality" class="form-control select2me">
									<option></option>
									<form:options items="${CODEBOOK_LIST.NATIONALITY }" itemValue="codeId" itemLabel="codeName"/>
								</form:select>
							</div>
						</div>
					</div>
					<div class="col-md-4"></div>
					<div class="col-md-4">
						<div class="form-group">
							<label class="col-md-5">
								<spring:message code="cust.moreinfo.occupation" />
							</label>
							<div class="col-md-7">
								<form:select path="custOccupation" class="form-control select2me">
									<option></option>
									<form:options items="${CODEBOOK_LIST.OCCUPATION }" itemValue="codeId" itemLabel="codeName"/>
								</form:select>
							</div>
						</div>
					</div>
				</div>
				<!-- row5 -->
				<div class="row">
					<div class="col-md-4"></div>
					<div class="col-md-4"></div>
					<div class="col-md-4">
						<div class="form-group">
							<label class="col-md-5">
								<spring:message code="cust.moreinfo.jobtitle" />
							</label>
							<div class="col-md-7">
								<form:select path="custJobTitleTab" class="form-control select2me">
									<option></option>
									<form:options items="${CODEBOOK_LIST.CONTACT_JOB_TITLE }" itemValue="codeId" itemLabel="codeName"/>
								</form:select>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- End form-body -->
			
			<!-- Begin Panel Button -->
			<div class="form-actions right">
				<locus:privilege oper="ADD">
					<div class="btn-group">
						<button type="button" class="btn blue" id="btnSaveInfo" name="btnSaveInfo" onclick="saveMoreInfo();">
							<i class="fa fa-floppy-o"></i>
							<spring:message code="button.save.label"/>
						</button>
					</div>
				</locus:privilege>
					<button type="button" class="btn default" id="btnCancelInfo" name="btnCancelInfo" onclick="clearForm();">
						<spring:message code="button.cancel.label"/>
					</button>
			</div>
			<!-- End Panel Button -->
		</form:form>
	</div>
</div>
<!-- End Content -->

<script type="text/javascript">
var title = '<spring:message code="cust.custCaptionDetailLbl" /> : <spring:message code="cust.moreInfoTab"/>';
var worningCitizen = '<spring:message code="message.please.enter.citizen"/>';

var customerJsonData = [];
	$(document).ready(function(){
		
		var mode = '<c:out value="${custModelBean.mode}"/>';
		if(mode == "insert"){
			$("#btnSaveInfo").attr("disabled", "disabled");
		}else if(mode == "update"){
			$("#btnSaveInfo").removeAttr("disabled");
		}
		
		var custBirthDate = '<c:out value="${custModelBean.custBirthDate}"/>';
		setAge(custBirthDate);
		
		
		//$("#custCitizenIdTab").inputmask("9-9999-99999-99-9");
		
		$("#custBirthDate").change(function(){
			var custBirthDate = $("#custBirthDate").val();
			setAge(custBirthDate);
		});
	});
	
	function resetDropdown(){
		
		// custGender dropdown
		var custGender = '<c:out value="${custModelBean.custGender}"/>';
		$("#custGender").select2("val", custGender);
		
		// custMarriageStatus dropdown
		var custMarriageStatus = '<c:out value="${custModelBean.custMarriageStatus}"/>';
		$("#custMarriageStatus").select2("val", custMarriageStatus);
		
		// custRace dropdown
		var custRace = '<c:out value="${custModelBean.custRace}"/>';
		$("#custRace").select2("val", custRace);
		
		// custNationality dropdown
		var custNationality = '<c:out value="${custModelBean.custNationality}"/>';
		$("#custNationality").select2("val", custNationality);

		// custSegmentation dropdown
		var custSegmentation = '<c:out value="${custModelBean.custSegmentation}"/>';
		$("#custSegmentation").select2("val", custSegmentation);
		
		// custEducation dropdown
		var custEducation = '<c:out value="${custModelBean.custEducation}"/>';
		$("#custEducation").select2("val", custEducation);
		
		// custSalary dropdown
		var custSalary = '<c:out value="${custModelBean.custSalary}"/>';
		$("#custSalary").select2("val", custSalary);
		
		// custOccupation dropdown
		var custOccupation = '<c:out value="${custModelBean.custOccupation}"/>';
		$("#custOccupation").select2("val", custOccupation);
		
		// custJobTitleTab dropdown
		var custJobTitleTab = '<c:out value="${custModelBean.custJobTitleTab}"/>';
		$("#custJobTitleTab").select2("val", custJobTitleTab);
	}
	
	function setAge(custBirthDate){
		var age = calculateAge(custBirthDate);
		$("#custAge").val(age);
		$("#age").val(age);
	}
	
	function calculateAge(custBirthDate)
	{
		if(custBirthDate.length > 0){
			
			var birthYear = parseInt(custBirthDate.substr(6, 4));
			var birthMonth = parseInt(custBirthDate.substr(3,2));
			var birthDate = parseInt(custBirthDate.substr(0,2));
			
			var today = new Date();
		    var todayYear = today.getFullYear();
		    var todayMonth = today.getMonth()+1;
			var todayDate = today.getDate();
		    
		    var age = 0;
		    if(todayYear > birthYear){
		   		age = todayYear - birthYear;
		    }

		    if(age >= 1){
		    	var m = todayMonth - birthMonth;
		    	if (m < 0 || (m === 0 && todayDate < birthDate)) {
		        	age--;
		    	}
		    }
		    
		    return age;
		}else{
			return "";
		}
	}
	
	function saveMoreInfo(){
		/*  $('#moreInfoForm').attr('action', 'saveMoreInfo.htm');
			$('#moreInfoForm').submit(); */
		
		ajaxSubmitForm($("#moreInfoForm"), function(data){
			 //alert("Mode : "+data.model.mode);
			 customerJsonData = data;			
			 alertMessage( title, data.resultMessage);
		}); 
			
		/* var custCitizenIdTab = $("#custCitizenIdTab").val();	
		if(!empty(custCitizenIdTab)){
			
			if(checkCitizenId(custCitizenIdTab)){
				ajaxSubmitForm($("#moreInfoForm"), function(data){
					 //alert("Mode : "+data.model.mode);
					 customerJsonData = data;			
					 
					 alertMessage( title, data.resultMessage);
				}); 
				
			}else{
				alertMessage( title,worningCitizen );
				$("#custCitizenIdTab").focus();
				return false			
			 
			}	
		}else{
			
			ajaxSubmitForm($("#moreInfoForm"), function(data){
				 
				 customerJsonData = data;			
				 
				 alertMessage( title, data.resultMessage);
			}); 
			
		} */	
		
}	
	function clearForm(){
		
		if(customerJsonData != "" && customerJsonData != null){
			if(customerJsonData.resultCode == '0'){
				setFormScreenByModelBean('moreInfoForm', customerJsonData);
			}else{
				
				// set dropdown >> old value
				
				resetDropdown();
				var age = $("#age").val();
				$("#custAge").val(age);
				
				
			}
		}else{
			
			 $('#moreInfoForm')[0].reset();	
			
			 /* $("#custBirthDate").val("");
			 $("#custCitizenIdTab").val("");	
			 $("#custAge").val("");
			 $("#age").val(""); */
			 
			// set dropdown >> old value
			resetDropdown();
			 
		}
	}
	
</script>
