<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tile"%>
<form id="formContentSummary" action="update${kbGroup }Conclude.htm" method="post" class="form-body form-horizontal ">
	<input type="hidden" name="contentNumber" id="summary_contentId" />
	<input type="hidden" name="mode" value="update" />
	<div class="row">								
		<div class="col-md-12">
			<div class="form-group">
				<label class="control-label col-md-2"><spring:message code="knowledge.tab.conclude" /></label>
				<div class="col-md-10">
					<textarea class="form-control wysihtml5" id="content_summary" name="summary" rows="5" maxlength="1500"></textarea>
				</div>
			</div>
		</div>
	</div>
	<div class="row form-actions fluid">
		<div class="col-md-12">
			<div class="col-md-offset-3 col-md-9 text-right">
				<div class="btn-group"> 
					<button type="button" class="btn blue disabled" id="btn_summSave" name="btn_summSave" onclick="saveSummary()">
						<i class="fa fa-floppy-o"></i>											
						<spring:message code="button.save.label"/>
					</button>
				</div>
				
<!-- 				<div class="btn-group">	 -->
<!-- 					<button type="button" class="btn default" id="btn_summCancel" name="btn_summCancel"> -->
<%-- 						<spring:message code="button.cancel.label"/> --%>
<!-- 					</button> -->
<!-- 				</div> -->
			</div>
		</div>
	</div>	
</form>
<script type="text/javascript">
jQuery(document).ready(function() {
	if(wysihtml5enabled){
		$('.wysihtml5').wysihtml5({
		    "stylesheets": ["assets/plugins/bootstrap-wysihtml5/wysiwyg-color.css"]
		});
	}
	
});

function setSummary(summary){
	$("#content_summary").val(summary);
	if(wysihtml5enabled){		
		$("#content_summary").data("wysihtml5").editor.setValue(summary);
	}
	$("#btn_summSave").removeClass("disabled");
}
function saveSummary(){
	if($("#contentNumber").val()==""){
		alertMessage('<spring:message code="knowledge.detail" />','<spring:message code="knowledge.chooseContent" />');
		return;
	}
	$("#summary_contentId").val($("#contentNumber").val());
	ajaxSubmitForm($("#formContentSummary"),function(data){
		console.log(data);
		if(data!=false){
// 			alertMessage("<spring:message code="knowledge.detail" />","<spring:message code="lbl.save.success" />");
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
		}else{
			alertMessage("<spring:message code="knowledge.detail" />","<spring:message code="lbl.save.fail" />");
		}
	});
}
</script>