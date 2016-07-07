<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="locus" uri="/WEB-INF/tld/locus.tld"%>
<!-- End Grid Activity List -->
<div class="form  form-horizontal ">
	<div class="form-body">
		<h5 class="form-section"><spring:message code="knowledge.tab.attach.attachmentCaption"/></h5>
		<div class="row">
			<div class="col-md-12">						
				<div role="grid" class="dataTables_wrapper">
					<table class="table table-bordered" id="grid_att_result_list">
					</table>
				</div>		
			</div>
			
		</div>
	</div>
</div>

<!-- Begin Create panel Attachment Detail -->
<div class="form">
	<div class="form-body">
		<form:form id="form_contentatt" class="form form-horizontal" action='saveContentAtt.htm' method="post" enctype="multipart/form-data" modelAttribute="knowledgeBaseAttModelBean">
		<input type="hidden" name="contentId" id="contentId"/>
		<input type="hidden" name="contentAttId" id="contentAttId" />
		<input type="hidden" name="attId" id="attId" />
		<input type="hidden" name="attMode" id="attMode" />
		<input type="hidden" name="regId" id="regId" />
		<div class="row">
			<div class="col-md-12">
				<h5 class="form-section"><spring:message code="knowledge.tab.attach.attDetailCaption"/></h5>
				<div class="row">
					<div class="col-md-12">
						<div class="form-group">
							<label class="control-label col-md-2">
								<spring:message code="knowledge.tab.attach.attFileName"/> <span class="required"> * </span>
								<input type="file" id="fileNameID" name="kbAttFile" onchange="selectedFile();"  style="display: none;">
								<input type="hidden" id="fileurl" />
							</label>
							<div class="col-md-6">
								<div class="input-group">
									<input id="attFileName" name="attFileName" data-msg-required="กรุณากรอก&amp;nbsp;ชื่อไฟล์เอกสาร ." class="form-control disabled" data-rule-required="true" type="text" readonly="readonly" value="" maxlength="255">
									<span class="input-group-btn">												
										<button type="button" id="btnAttachFile" class="btn" >
											<i class="fa fa-paperclip"></i>
										</button>
									</span>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<div class="form-group">
							<label class="control-label col-md-2">
							</label>
							<div class="col-md-10">
								<spring:message code="lbl.filelimit" arguments="20" />
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<div class="form-group">
							<label class="control-label col-md-2">
								<spring:message code="knowledge.tab.attach.attDocumentName"/> <span class="required"> * </span>
							</label>
							<div class="col-md-10">
								<input type="text" id='attTitle' name='attTitle' class="form-control"  data-rule-required="true" data-msg-required="Document Name is required." /> 
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<div class="form-group">
							<label class="control-label col-md-2">
								<spring:message code="knowledge.tab.attach.attDescription"/>
							</label>
							<div class="col-md-10">
								<textarea id='attDescp' name='attDescp' class="form-control" rows="3"></textarea> 
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<div class="form-group">
							<label class="control-label col-md-2">
								
							</label>
							<div class="col-md-10">
								<input type="checkbox" name="mainFlag" id="mainFlag" value="1"> <spring:message code="knowledge.tab.attach.mainDocument"/>
							</div>
						</div>
					</div>
				</div>
				<hr />
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4">
								<spring:message code="lbl.createBy"/>
							</label>
							<div class="col-md-8">
								<p id="attRegBy" class="form-control-static">&nbsp;</p>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4">
								<spring:message code="lbl.createDate"/>
							</label>
							<div class="col-md-8">
								<p id="attRegDt" class="form-control-static">&nbsp;</p>
							</div>
						</div>
					</div>
				</div>
				
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4">
								<spring:message code="lbl.updateBy"/>
							</label>
							<div class="col-md-8">
								<p id="attChgBy" class="form-control-static">&nbsp;</p>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4">
								<spring:message code="lbl.updateDate"/>
							</label>
							<div class="col-md-8">
								<p id="attChgDt" class="form-control-static">&nbsp;</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		</form:form>
		<div class="row">
			<div class="col-md-12">	
				<div class="row form-actions fluid">
					<div class="col-md-12">
						<div class="col-md-offset-3 col-md-9 text-right">
							<locus:privilege oper="ADD">
							
								<div class="btn-group">
									<button type="button" class="btn green" id="btn_fileNew" name="btn_attNew">
										<i class="fa fa-plus"></i>
										<spring:message code="button.create.label"/>
									</button>
								</div>
							
								<div class="btn-group"> 
									<button type="button" class="btn blue" id="btn_fileSave" name="btn_attSave">
										<i class="fa fa-floppy-o"></i>											
										<spring:message code="button.save.label"/>
									</button>
								</div>
							</locus:privilege>
							
							<div class="btn-group"> 
								<button type="button" class="btn red" style="display:none" id="btn_fileDel" name="btn_attDel">
									<i class="fa fa-times"></i>											
									<spring:message code="button.delete.label"/>
								</button>
							</div>
							
							<div class="btn-group">	
								<button type="button" class="btn default" id="btn_fileCancel" name="btn_actCancel">
									<spring:message code="button.cancel.label"/>
								</button>
							</div>
						</div>
					</div>	
				</div>
			</div> 
		</div>
	</div>
</div>

<script type="text/javascript">
              
	$(document).ready(function() {
		// Initial validator form
		validateForm($("#form_contentatt"));

		// Initial datatable
		//attDatatable = initajaxDataTable($('#grid_att_result_list'), attColumns);
		//searchAttachFileList(true);
		
		// Disable form
		setComponentDisableById(tabAttFormArrEnable, true);

		/* Start binding event */
		$('#btn_fileNew').click(function () {
			var contentId = $('#contentId').val();
			clearAttForm();
			setComponentDisableById(tabAttFormArrEnable, false);
			$('#btn_fileSave').prop('disabled', false);
			$('#btn_fileCancel').prop('disabled', false);
			$('#attMode').val('add');
			$('#contentId').val(contentId);
		});

		$("#btnAttachFile").click(function(e) {
			 e.preventDefault();
			$("#fileNameID").click();
			
		});

		$("#btn_fileSave").click(function() {

			validateForm($("#form_contentatt"));
			if (!$("#form_contentatt").valid()) {
				return false;
			}
			ajaxSubmitForm($("#form_contentatt"), callBackAfterSaveAtt);
		});

		$("#btn_fileCancel").click(function(){
			clearAttForm();
		});
		/* End binding event */
	});

	function callBackAfterSaveAtt(data) {
		if(data.resultCode == "0"){
			// Query table result again
			$('#attMode').val('');
			searchAttachFileList(false);
			
		} else{
			alertMessage("<spring:message code="knowledge.detail" />",data.resultMessage);
		}
	}

	function selectedFile() {
		$("#attFileName").val($("#fileNameID")[0].files[0].name);
	}
	
</script>	