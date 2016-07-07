<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 
<div class="row">
	<div class="col-md-12">
		<div class="form-body">
		
			<form:form action="saveAttachmentDetailDialog.htm" name="dialogAttachmentForm" id="dialogAttachmentForm" modelAttribute="attModelBean" class="form-horizontal">
				<form:hidden path="relAttId" id="relAttId" />
				<form:hidden path="attId" id="attId"/>
		
				<!-- Row1 -->
				<div class="row">
					<label class="control-label col-md-2">
						<spring:message code="sr.srAttFileName"/>&nbsp;&nbsp;&nbsp;
					</label>
					<div class="col-md-8">
						<div class="form-group">
							<form:input type="text" id="txtActAttFileName" name="attFileName" path="attFileName" class="form-control" readonly="true" maxlength="255"/>
						</div>
					</div>
				</div>
				
				<!-- Row2 -->
				<div class="row">
					<label class="control-label col-md-2">
						<spring:message code="sr.srAttDocumentName"/> &nbsp;&nbsp;&nbsp;
					</label>
					<div class="col-md-8">
						<div class="form-group">
							<form:input type="text" id="txtActAttDocumentName" path="attDocumentName" class="form-control" maxlength="500" />	
						</div>
					</div>
				</div>
				
				<!-- Row3 -->
				<div class="row">
					<label class="control-label col-md-2">
							<spring:message code="sr.srAttDetail"/>&nbsp;&nbsp;&nbsp;
					</label>
					<div class="col-md-8">
						<div class="form-group">
							<form:textarea id="txtActAttDescription" path="attDescription" class="form-control" rows="5" data-rule-required="true" data-msg-required='<spring:message code="message.please.enter"/>&nbsp;<spring:message code="sr.srAttDetail"/>.' maxlength="1000"></form:textarea>	
						</div>
					</div>
				</div>
			
<%-- 			</form:form>   --%>
<!-- 		</div> -->
		
		<hr/>
		
		 <div class="row">
				<div class="col-md-1">
					<div class="form-group"></div>
				</div>
				<div class="col-md-5">
					<div class="form-group">
						<label class="control-label col-md-5"> <spring:message code="lbl.createBy" />
						</label>
						<div class="col-md-7">
							<p id="docAttCreateBy" class="form-control-static"><c:out value="${attModelBean.regName}"/></p>
						</div>
					</div>
				</div>
				<div class="col-md-5">
					<div class="form-group">
						<label class="control-label col-md-5"> <spring:message code="lbl.createDate" />
						</label>
						<div class="col-md-7">
							<p id="docAttCreateDate" class="form-control-static"><c:out value="${attModelBean.createDateTime}"/></p>
						</div>
					</div>
				</div>
				<div class="col-md-1">
					<div class="form-group"></div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-1">
					<div class="form-group"></div>
				</div>
				<div class="col-md-5">
					<div class="form-group">
						<label class="control-label col-md-5"> <spring:message code="lbl.updateBy" />
						</label>
						<div class="col-md-7">
							<p id="docAttUpdateBy" class="form-control-static"><c:out value="${attModelBean.chgName}"/></p>
						</div>
					</div>
				</div>
				<div class="col-md-5">
					<div class="form-group">
						<label class="control-label col-md-5"> <spring:message code="lbl.updateDate" />
						</label>
						<div class="col-md-7">
							<p id="docAttUpdateDate" class="form-control-static"><c:out value="${attModelBean.updateDateTime}"/></p>
						</div>
					</div>
				</div>
				<div class="col-md-1">
					<div class="form-group"></div>
				</div>
				 
			</div>  
			<br/>
			
			<!-- End Detail  -->
				<div class="form-actions right">
					<div class="row">
							<div class="col-md-12">
								<div class="col-md-offset-3 col-md-9">
									<div class="btn-group">	
										<button type="button" class="btn blue" type="button"  id="btnSaveAttDetail" name="btnSaveAttDetail">
											<i class="fa fa-floppy-o"></i>
											<spring:message code="button.save.label" />
										</button>
									</div>
									
									<div class="btn-group">	
										<button type="button" class="btn default" id="btnSrAttCancel" name="btnSrAttCancel">
											<spring:message code="button.reset.label"/>
										</button>
									</div>
								
								</div>
							</div>
					</div>			
					
				</div>
			</form:form> 
		</div>
	</div>
</div>

<script type="text/javascript">
var attJsonData=[];
var msgTitle = '<b><spring:message code="menu.activity" /> : <spring:message code="sr.srAttDocInfoCaption" /></b>';
var evalFuncDocInfo ="${callbackfn}";
console.log("evalFuncDocInfo :"+evalFuncDocInfo);
var attDialogInfoName="${dialogName}";
console.log("attDialogInfoName :"+attDialogInfoName);
var resultAfterSave = false;

$(document).ready(function() {   
	
	
	
	$("#btnSaveAttDetail").click(function() {

		saveDocumentInfo();
		
	});
	
	$("#btnSrAttCancel").click(function() {
		
			resetDataDocInfoBefore();
	});
	
	$("#"+attDialogInfoName).on("hidden", function(){
	    console.log('actAttDetailDialog HIDDEN');
	    setDataIntoComponent(evalFuncDocInfo,resultAfterSave);
	}).on("shown", function(){
	    console.log('actAttDetailDialog SHOWN');
	});
	
	/* $("#actAttDetailDialog").on("hidden", function(){
	    console.log('actAttDetailDialog HIDDEN');
	    setDataIntoComponent(evalFuncDocInfo,resultAfterSave);
	}).on("shown", function(){
	    console.log('actAttDetailDialog SHOWN');
	}); */
	
});


function saveDocumentInfo(){
	 
	ajaxSubmitForm($("#dialogAttachmentForm"), function(data){		
		 resultAfterSave = true;
		 alertMessage("<h5>"+msgTitle+"</h5>","<h6>"+data.resultMessage+"</h6>");	

		 attJsonData = data.model;
		 
		if(data.resultCode == "0"){
			
			setButtomUserPanelDetail(attJsonData);
	
			// set data in to component
			setFormScreenByModelBean("dialogAttachmentForm", data);
			
		}else{
			
			return false;
		}
		 
	});
	 
}

function setButtomUserPanelDetail(jsData){
		 
	 	var regName = jsData.regName;
	    var chgName = jsData.chgName;
	    var createDateTime = jsData.createDateTime;
	    var updateDateTime = jsData.updateDateTime;
	    
	    $("#docAttCreateBy").text(regName);
		$("#docAttCreateDate").text(createDateTime);	
		
		$("#docAttUpdateBy").text(chgName);
		$("#docAttUpdateDate").text(updateDateTime);
	
}

//Reset old value  
function resetDataDocInfoBefore(){
	
	console.log("empty : "+empty(attJsonData) +" : "+attJsonData);
	
	 if(!empty(attJsonData)){
		 
		setFormScreenOnSelectDatable("dialogAttachmentForm",attJsonData);
		setButtomUserPanelDetail(attJsonData);
		
	 }else{
		 
			$("#dialogAttachmentForm").trigger("reset");
		 
	 }
	
}

 


</script>