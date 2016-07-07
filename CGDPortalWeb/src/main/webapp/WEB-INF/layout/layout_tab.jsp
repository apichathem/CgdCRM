<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
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
	
	<tiles:insertAttribute name="body" />
</body>
</html>
