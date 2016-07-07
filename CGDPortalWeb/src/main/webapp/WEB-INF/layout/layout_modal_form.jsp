<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
<script  type="text/javascript">
			
		$("select").select2({
	     	allowClear: true
	    }); 
		
		ComponentsPickers.init();
		
		// Set format mask for component this function at jlo-common.js
		setMaskComponent();
	</script>
</head>
<body>

	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"aria-hidden="true">&times;</button>
			<h4 class="modal-title"><c:out value="${headerTitle}" /></h4>
	</div>
	<div class="modal-body">
	
		<tiles:insertAttribute name="body" />
		
	</div>
<!-- 	<div class="modal-footer"> -->
<%-- 		<button type="button" data-dismiss="modal" class="btn btn-primary"><spring:message code="button.close.label"/></button> --%>
<!-- 	</div> -->


</body>
</html>



