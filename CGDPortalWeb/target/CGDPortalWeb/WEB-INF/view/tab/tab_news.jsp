<%@page import="com.locus.jlo.web.constant.JLOWebConstant"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tile"%>
<form id="formContentSummary" action="updatetabnews.htm" method="post" class="form-body form-horizontal " enctype="multipart/form-data">
 <input type="hidden" name="contentNumber" id="summary_contentId" />
 <input type="hidden" name="mode" value="update" />
	<div class="row">								
		<div class="col-md-12">
			<div class="form-group">
				<label class="control-label col-md-2"><spring:message code="knowledge.tab.news" /></label>
				<div class="col-md-10">
					<textarea class="form-control wysihtml5" id="content_summary" name="summary" rows="8" maxlength="1500" ></textarea>
				</div>
			</div>
		</div>
	</div>
	<div class="row form-actions fluid">
		<div class="col-md-12">
			<div class="col-md-offset-3 col-md-9 text-right">
				<div class="btn-group"> 
					<button type="button" class="btn green disabled" id="btn_newPreview" name="btn_newPreview" >
						<i class="fa fa-eye"></i>											
						<spring:message code="knowledge.tab.news.preview"/>
					</button>
				</div>
				<div class="btn-group">
					<button type="button" class="btn blue disabled" id="btn_summSave" name="btn_summSave" onclick="saveSummary()">
						<i class="fa fa-floppy-o"></i>											
						<spring:message code="button.save.label"/>
					</button>
				</div>
			</div>
		</div>
	</div>	
</form>
<div id="simpleDialog" class="modal fade" tabindex="-1">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title"><spring:message code="knowledge.tab.news.preview"/></h4>
			</div>
			<div class="modal-body">
				<div class="previewText" style="height: 500px;">
				</div>
			</div>
		</div>		
	</div>
</div>
<script type="text/javascript">
jQuery(document).ready(function() {
	$("#newPic").on("change",function(){
		if(this.value==""){
			return;
		}
		var ext = this.value.match(/\.([^\.]+)$/)[1];
		console.log("ext = "+ext);
	    switch(ext)
	    {
	        case 'jpg':
	        case 'gif':
	        case 'png':
	            break;
	        default:
	        	alertMessage('<spring:message code="knowledge.detail" />','<spring:message code="file.notalow" />');
	            this.value='';
	    }
	});
	$("#btn_newPreview").on("click",function(){
		$("#simpleDialog").modal("show");
		$(".previewText").load("previewNews.htm?contentId="+$("#contentNumber").val());

	});
});

function setSummary(summary){
	$("#content_summary").val(summary);
	$("#btn_summSave").removeClass("disabled");
	$("#btn_summSaveAtt").removeClass("disabled");
	
	$(".new-attachfile").removeClass("disabled");
	$("#btn_newPreview").removeClass("disabled");
	$('.fileinput-exists[data-dismiss="fileinput"]').click();
	reloadNewAttachFile();
}

function saveSummary(){
	
	if($("#contentNumber").val()==""){
		alertMessage('<spring:message code="knowledge.detail" />','<spring:message code="knowledge.chooseContent" />');
		return;
	}
	$("#summary_contentId").val($("#contentNumber").val());
	ajaxSubmitForm($("#formContentSummary"),function(data){
		console.log(data);
		if(data!=null){
			if(data.status!="error"){
				if (data.chgBy != null){
					$("#chgBy").text(data.chgBy);
				}else{
					$("#chgBy").text("");
				}
					
				if (data.chgDt != null){
					$("#chgDt").text(timestamp2datetime(data.chgDt));
				}else{
					$("#chgDt").text("");
				}
				reloadNewAttachFile();
			}else{
				alertMessage("<spring:message code="knowledge.detail" />","<spring:message code="lbl.save.fail" />");
			}
		}else{
			alertMessage("<spring:message code="knowledge.detail" />","<spring:message code="lbl.save.fail" />");
		}
		
	});
}
</script>