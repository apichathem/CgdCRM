<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
					<div id="objPDF" class="isMainDocument" style="display:none">
						
					</div>
					<div class="notMainDocument">
						<div class="alert alert-block alert-warning fade in">
							<h4 class="alert-heading"><spring:message code="knowledge.titlenodocument" /></h4>
							<p>
								 <spring:message code="knowledge.nodocument" />
							</p>
						</div>
					</div>
						
<script type="text/javascript">
function loadMainDocument(contentId){
	loadJsonData("inquiryKBDocument.htm", "contentId="+ contentId,"POST", function(data){
		if(data.contentAtt!=null){
			$(".isMainDocument").show();
			$(".notMainDocument").hide();
			playAttFile($("#objPDF"),data.contentAtt.attId,data.contentAtt.fileType,400);
			
		}else{
			$("#objPDF").html('');
			$(".isMainDocument").hide();
			$(".notMainDocument").show();
		}
	});
}
</script>