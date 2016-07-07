<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="locus" uri="/WEB-INF/tld/locus.tld"%>
<div class="form  form-horizontal ">
	<div class="form-body">
		<h5 class="form-section"><spring:message code="sr.srAttachMentCaption"/></h5>
		<div class="row">
			<div class="col-md-12">						
				<div role="grid" class="dataTables_wrapper" id="sample_1_wrapper">
					<table class="table table-bordered" id="grid_att_result_list">
										
					</table>
				</div>		
			</div>
			
		</div>
	</div>
</div>
<!-- End Grid Activity List -->


<!-- Begin Create panel Attachment Detail -->
<form id="form_contentatt" class="form form-horizontal" action='insertKbAttch.htm' method="post" enctype="multipart/form-data">
<input type="hidden" name="contentId" id="attContentId" value="" />
<input type="hidden" name="contentAttId" id="contentAttId" value="" />
<input type="hidden" name="attId" id="attId" />
<input type="hidden" name="mode" id="att_mode" />
<div class="form-body">
	<div class="row">
		<div class="col-md-12">
			<h5 class="form-section"><spring:message code="sr.srAttDetailCaption"/></h5>
			<div class="row">
				<div class="col-md-12">
					<div class="form-group">
						<label class="control-label col-md-2">
							<spring:message code="sr.srAttFileName"/>
							<input type="file" id="fileNameID" name="files[]"  style="display: none;">
							<input type="hidden" id="fileurl" />
						</label>
						<div class="col-md-6">
							<div class="input-group">
								
								<input id="txtAttFileName" name="attFileName" data-msg-required="กรุณากรอก&amp;nbsp;ชื่อไฟล์เอกสาร ." class="form-control disabled" data-rule-required="true" type="text" readonly="readonly" value="" maxlength="255">
								<span class="input-group-btn">												
									<button type="button" id="btnAttachFile" class="btn disabled" >
										<i class="fa fa-paperclip"></i>
									</button>
								</span>
								<span class="input-group-btn">												
									<button type="button" id="btnDownloadAttachFile" class="btn disabled">
										<i class="fa fa-download"></i>
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
							<spring:message code="sr.srAttDocumentName"/>
						</label>
						<div class="col-md-10">
							<input type="text" id='att_title' name='title' class="form-control"  data-rule-required="true" data-msg-required="Document Name is required." maxlength="150" /> 
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<div class="form-group">
						<label class="control-label col-md-2">
							<spring:message code="knowledge.attDescription"/>
						</label>
						<div class="col-md-10">
							<textarea id='att_descp' name='descp' class="form-control" rows="3" maxlength="1000"></textarea> 
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
							<input type="checkbox" name="mainFlag" id="mainFlag" value="1"> <spring:message code="knowledge.mainDocument"/>
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
							<p id="att_regBy" class="form-control-static">&nbsp;</p>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">
							<spring:message code="lbl.createDate"/>
						</label>
						<div class="col-md-8">
							<p id="att_regDt" class="form-control-static">&nbsp;</p>
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
							<p id="att_chgBy" class="form-control-static">&nbsp;</p>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">
							<spring:message code="lbl.updateDate"/>
						</label>
						<div class="col-md-8">
							<p id="att_chgDt" class="form-control-static">&nbsp;</p>
						</div>
					</div>
				</div>
			</div>
		
		
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">	
			<div class="row form-actions fluid">
				<div class="col-md-12">
					<div class="col-md-offset-3 col-md-9 text-right">
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
</form>
<script type="text/javascript">
var columns = [
				{ "sTitle":'<spring:message code="cp.actAction"/>',
						"mData": null,
				    "fnRender": function(obj) {
				    	return "<a href='javascript:void(0);' onclick='deleteFileConfirm("+obj.aData.contentAttId+","+obj.aData.attId+")'><i class='fa fa-trash-o'></i></a>";
				    },
				    "sClass": "text-center",
				    "sWidth": "50"
				},
               { "sTitle": '<spring:message code="cp.actNo"/>', "mData": "contentAttId", "sClass": "text-center","sWidth": "40" }, 
               { "sTitle": '<spring:message code="cp.actDocName"/>', 
					"mData": null,
				    "fnRender": function(obj) {
// 				    	console.log(obj.aData);
				   		if(obj.aData.mainFlag==1){
				   			return "<i class='fa   fa-bookmark-o red'></i> "+obj.aData.title;
				   		}else{
				   			return obj.aData.title;
				   		}
				    }  
			   },
			   { "sTitle": '<spring:message code="file.downloadFile"/>', "mData": null,"sClass": "text-center",  
				   "fnRender": function(obj) {
					   return "<a href='<%=request.getContextPath()%>/downloadFile.htm?attId="+obj.aData.attId+"' target='_blank'><spring:message code="file.downloadFile"/></a>";
				   }
				},
               { "sTitle": '<spring:message code="lbl.createBy"/>', "mData": "regBy", },
               { "sTitle": '<spring:message code="lbl.createDate"/>', "mData": "regDtText","sClass": "text-center", "sWidth": "140" }
//                { "sTitle": '<spring:message code="lbl.updateBy"/>', "mData": "chgBy" },
//                { "sTitle": '<spring:message code="lbl.updateDate"/>', "mData": "chgDtText", "sClass": "text-center"},
               ];
var attDatatable;
function loadContentAtt(contentId){
	if(contentId==null){
		$('#grid_att_result_list').html("");
		initajaxDataTable($('#grid_att_result_list'),columns);
		return;
	}
	$("#attContentId").val(contentId);
//  	if(attDatatable!=null){attDatatable.fnClearTable();}
// 	alert(contentId);
	
// 	initajaxDataTable($('#grid_att_result_list'),columns);
	return ajaxDataTable($('#grid_att_result_list'),columns,'inquiryKBAtt.htm?contentId='+contentId,'', '5',true,true);
	
	/* $("#grid_att_result_list tbody tr").live("click",function(){
		var aPos = attDatatable.fnGetPosition(this);	    	
    	if(aPos == null){
			return false;
    	}
    	console.log(aPos);
        var aData = attDatatable.fnGetData(aPos[0]);
        console.log(aData[aPos]);
        setFormAttFile(aData[aPos]);
	}); */
// 	setFormAttFile(null);
}
function clearAttValidate(){
	clearValidate(["txtAttFileName","att_title"]);
}
function setFormAttFile(data){
	clearAttValidate();
	if(data==null){
		$("#attfileinput").fileinput('clear');
		$("#contentAttId").val("");
		$("#textshowFilename").addClass("disabled");
		$("#btnAttachFile").addClass("disabled");
		$("#btnDownloadAttachFile").addClass("disabled");
		$("#fileurl").val("");
		$("#att_title").val("");
		$("#att_descp").val("");
		$("#att_regBy").text("");
		$("#att_regDt").text("");
		$("#att_chgBy").text("");
		$("#att_chgDt").text("");
		$("#btn_fileNew").removeClass("disabled");
		$("#btn_fileSave").addClass("disabled");
		$("#btn_fileDel").addClass("disabled");
		setCheckbox("mainFlag",false);
		$("#btn_fileSave").addClass("disabled");
		$("#btn_fileNew").removeClass("disabled");
		$("#btn_fileDel").addClass("disabled");
		$("#txtAttFileName").val("");
		$("#form_contentatt")[0].reset();
		formAttDisable(true);
	}else{
		$("#att_mode").val("update");
		$("#contentAttId").val(data.contentAttId);
		$("#attId").val(data.attId);
		$("#txtAttFileName").val(data.fileName);
		$("#fileurl").val('<%=request.getContextPath()%>/downloadFile.htm?attId='+data.attId);
		$("#btnAttachFile").addClass("disabled");		
		$("#att_title").val(data.title);
		$("#att_descp").val(data.descp);
		if(data.regBy!=null) $("#att_regBy").text(data.regBy);
		if(data.regDtText!=null) $("#att_regDt").text(data.regDtText);
		if(data.chgBy!=null) $("#att_chgBy").text(data.chgBy); else $("#att_chgBy").text("");
		if(data.chgDtText!=null) $("#att_chgDt").text(data.chgDtText); else $("#att_chgDt").text("");
		if (data.mainFlag == "1") {
			setCheckbox("mainFlag",true);
		} else {
			setCheckbox("mainFlag",false);
		}
		$("#att_mode").val("update");
// 		$("#btn_fileNew").addClass("disabled");
		$("#btn_fileSave").removeClass("disabled");
		$("#btn_fileDel").removeClass("disabled");
		formAttDisable(false);
		$("#btnDownloadAttachFile").removeClass("disabled");
	}
	
}
jQuery(document).ready(function() { 
	attDatatable = initajaxDataTable($('#grid_att_result_list'),columns);
	
	$("#grid_att_result_list tbody tr").live("click",function(){
		var aPos = attDatatable.fnGetPosition(this);	    	
    	if(aPos == null){
			return false;
    	}
//     	console.log(aPos);
        var aData = attDatatable.fnGetData(aPos[0]);
//         console.log(aData[aPos]);
        setFormAttFile(aData[aPos]);
	});
	
	$("#btn_fileNew").click(function(){
		setFormAttFile(null);
		formAttDisable(false);
		$("#btnAttachFile").removeClass("disabled");
		$("#att_mode").val("insert");
		$("#btn_fileSave").removeClass("disabled");
// 		$("#btn_fileNew").addClass("disabled");
		$("#btn_fileDel").addClass("disabled");
	});
	$("#btn_fileSave").click(function(){
		
		if($("#contentNumber").val()==""){
			alertMessage('<spring:message code="knowledge.detail" />','<spring:message code="knowledge.chooseContent" />');
			return;
		}
		$("#attContentId").val($("#contentNumber").val());
		validateForm($("#form_contentatt"));
		if (!$("#form_contentatt").valid()) {
			return false;
		}
		if($("#att_mode").val()=="insert"){	
			$("#form_contentatt").attr("action","insert${kbGroup }Attch.htm");
		}else if($("#att_mode").val()=="update"){	
			$("#form_contentatt").attr("action","update${kbGroup }Attch.htm");
		}
		ajaxSubmitForm($("#form_contentatt"),function(data){
			if(data.status=="success"){
// 				alertMessage("<spring:message code="knowledge.detail" />","<spring:message code="lbl.save.success" />");
			//	attDatatable = loadContentAtt($("#attContentId").val());
// 				setFormAttFile(null);

				//saveModeClickTemp
				if(data.mode=="insert"){
// 					alert(data.contentAttDTO.contentAttId);
					attDatatable = loadContentAtt($("#attContentId").val());

					$("#contentAttId").val(data.contentAttDTO.contentAttId);
					$("#attId").val(data.contentAttDTO.attId);
					$("#att_mode").val("update");
					
				} else{
					
					setPageChangeCurrent(attDatatable);
				}
				
				if (typeof loadMainDocument != "undefined"){
					loadMainDocument($("#contentNumber").val());
				}
				$("#btn_fileNew").removeClass("disabled");
			}else{
				alertMessage("<spring:message code="knowledge.detail" />","<spring:message code="lbl.save.fail" />");
			}
		});
	});
	
	$("#btn_fileCancel").click(function(){
		setFormAttFile(null);
	});
	$("#btnAttachFile").click(function(e) {
		 e.preventDefault();
		$("#fileNameID").click();
		
	});
	$("#btnDownloadAttachFile").click(function(e){
		window.location.href=$("#fileurl").val();
	});
	$("#fileNameID").on("change",function(){
		$("#txtAttFileName").val($("#fileNameID")[0].files[0].name);
	});
	setFormAttFile(null);
	$("#btn_fileNew").addClass("disabled");
});
var idfordeletecontentAttId,idfordeleteattId;
function deleteFileConfirm(id,id2){
	idfordeletecontentAttId = id;
	idfordeleteattId=id2;
	alertConfirm('<spring:message code="lbl.confirm.delete" />','deleteFile');
};
function deleteFile(){
	$("#att_mode").val("delete");
	$("#contentAttId").val(idfordeletecontentAttId);
	$("#attId").val(idfordeleteattId);
	$("#form_contentatt").attr("action","delete${kbGroup }Attch.htm");
	ajaxSubmitForm($("#form_contentatt"),function(data) {
		if(data.status=="success"){
// 			alertMessage("<spring:message code="knowledge.detail" />","<spring:message code="lbl.save.success" />");
			loadContentAtt($("#attContentId").val());
			setFormAttFile(null);
		}else{
			alertMessage("<spring:message code="knowledge.detail" />","<spring:message code="lbl.save.fail" />");
		}
		$("#contentAttId").val("");
		$("#attId").val("");
		
		if (typeof loadMainDocument != "undefined"){
			loadMainDocument($("#contentNumber").val());
		}
	
	});
}
function formAttDisable(flg){
	$("#form_contentatt").find("input[type='text']").attr("readonly",flg);
	$("#form_contentatt").find("input[type='file']").attr("readonly",flg);
	$("#form_contentatt").find("textarea").attr("readonly",flg);
	$("#btnAttachFile").addClass("disabled");
	$("#btnDownloadAttachFile").addClass("disabled");
	if(flg){
		$(".uneditable-input").addClass("disabled");
	}else{
		$(".uneditable-input").removeClass("disabled");
	}
	$("#txtAttFileName").attr("readonly",true);
	
}
</script>	