<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
						<form action="#" method="post" class="form-body form-horizontal">
							<input type="hidden" name="contentId" id="tag_contentId" />
							<input type="hidden" name="oriTags" id="oriTags" value="" />
							<input id="keyword" name="keyword" type="text" class="form-control tags" value=""/>
							
							<div class="row form-actions fluid hide">
								<div class="col-md-12">
									<div class="col-md-offset-3 col-md-9 text-right">
										<div class="btn-group"> 
											<button type="button" class="btn blue" id="btn_tagSave" name="btn_actSave">
												<i class="fa fa-floppy-o"></i>											
												<spring:message code="button.save.label"/>
											</button>
										</div>
										
										<div class="btn-group">	
											<button type="button" class="btn default" id="btn_tagCancel" name="btn_actCancel">
												<spring:message code="button.cancel.label"/>
											</button>
										</div>
									</div>
								</div>
							</div>	
						</form>
<script type="text/javascript">
function initKeyword(){
	$('#keyword').tagsInput({
		width : 'auto',
		'removeWithBackspace' : false,
		'defaultText' : '<spring:message code="knowledge.addTags" />',
		'onAddTag' : function(tagname) {
			console.log("insert db tag : "+tagname);
			actionAddTag(tagname);
		},
		'onRemoveTag' : function(tagname) {
			console.log("delete db tag : "+tagname);
			actionDeleteTag(tagname);
		}
	});
}
function actionAddTag(tagname){
	if($("#contentNumber").val()==""){
		alertMessage('<spring:message code="knowledge.detail" />','<spring:message code="knowledge.chooseContent" />');
		$("#keyword").importTags("");
		return;
	}
	loadJsonData("insert${kbGroup }Tag.htm", "contentId="
			+ $("#contentNumber").val()+"&mode=insert&tagname="+tagname, "POST", function(data){
		if(data!=null && data.status=="success"){
			$("#keyword").importTags(data.keyword);
			$("#oriTags").val(data.keyword);
// 			alertMessage("<spring:message code="knowledge.detail" />","<spring:message code="lbl.save.success" />");
		}else{
			$("#keyword").importTags($("#oriTags").val());
			alertMessage("<spring:message code="knowledge.detail" />","<spring:message code="lbl.save.fail" />");
		}
	});
}
function actionDeleteTag(tagname){
	if($("#contentNumber").val()==""){
		alertMessage('<spring:message code="knowledge.detail" />','<spring:message code="knowledge.chooseContent" />');
		$("#keyword").importTags("");
		return;
	}
	loadJsonData("delete${kbGroup }Tag.htm", "contentId="
			+ $("#contentNumber").val()+"&mode=delete&tagname="+tagname, "POST", function(data){
		if(data!=null && data.status=="success"){
			$("#keyword").importTags(data.keyword);
			$("#oriTags").val(data.keyword);
// 			alertMessage("<spring:message code="knowledge.detail" />","<spring:message code="lbl.save.success" />");
		}else{
			$("#keyword").importTags($("#oriTags").val());
			alertMessage("<spring:message code="knowledge.detail" />","<spring:message code="lbl.save.fail" />");
		}
	});
}
function callbackDataKeyword(data) {
	if (data.keyword == null) {
		$("#keyword").importTags("");
	} else {
		$("#keyword").importTags(data.keyword);
		$("#oriTags").val(data.keyword);
	}
	$("#btn_tagSave").on("click",function(){
		if($("#contentNumber").val()==""){
			alertMessage('<spring:message code="knowledge.detail" />','<spring:message code="knowledge.chooseContent" />');
			$("#keyword").importTags("");
			return;
		}
		if($("#keyword").val()==null){
			alertMessage("<spring:message code="knowledge.detail" />","<spring:message code="lbl.save.fail" />");
		}else{
// 			alert("OK");
		}
	});
	$("#btn_tagCancel").on("click",function(){
		$("#keyword").importTags($("#oriTags").val());
	});
	
}
function loadKeywordTags(contentId){
	loadJsonData("inquiryKBTag.htm", "contentId="
			+ contentId, "POST", callbackDataKeyword);
}
</script>