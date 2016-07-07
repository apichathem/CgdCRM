<%@page import="com.locus.jlo.web.constant.JLOWebConstant"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title><c:out value="${pageTitle}" /></title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="icon" href="assets/img/icon-jlo-light-blue.png" type="image/x-icon">
<link rel="shortcut" href="assets/img/icon-jlo-light-blue.png" type="image/x-icon">
		
<meta name="description" content="">
<meta name="author" content="">
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="max-age=0" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="cache-control" content="no-store" />

<!-- BEGIN GLOBAL MANDATORY STYLES -->
<link href="assets/css/font-opensans.css" rel="stylesheet" type="text/css"/>
<link href="assets/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
<link href="assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="assets/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" href="assets/plugins/select2/select2.css"/>
<link rel="stylesheet" type="text/css" href="assets/plugins/select2/select2-metronic.css"/>
<link href="assets/css/style-metronic.css" rel="stylesheet" type="text/css"/>
<link href="assets/css/style.css" rel="stylesheet" type="text/css"/>
<link href="assets/css/style-responsive.css" rel="stylesheet" type="text/css"/>
<link href="assets/css/plugins.css" rel="stylesheet" type="text/css"/>
<link href="assets/css/themes/default.css" rel="stylesheet" type="text/css" id="style_color"/>
<link href="assets/css/pages/login-soft.css" rel="stylesheet" type="text/css"/>
<link href="assets/css/custom.css" rel="stylesheet" type="text/css"/>
<link href="assets/plugins/gritter/css/jquery.gritter.css" rel="stylesheet" type="text/css"/>
<!-- END THEME STYLES -->


<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
	<!-- <script src="assets/plugins/jquery-1.10.2.min.js" type="text/javascript"></script>
	<script src="assets/plugins/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
	<script src="assets/plugins/jquery-ui/jquery-ui-1.10.3.custom.min.js" type="text/javascript"></script>
	<script src="assets/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
	<script src="assets/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
	<script src="assets/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
	<script src="assets/plugins/jquery.blockui.min.js" type="text/javascript"></script>
	<script src="assets/plugins/jquery.cokie.min.js" type="text/javascript"></script>
	<script src="assets/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
	<script src="assets/plugins/jquery-validation/dist/jquery.validate.js" type="text/javascript"></script>
	

	GRITTER MESSAGE
	
	MODAL
	
	CUSTOM
	 -->
	 <!-- CUSTOM -->
	<script type="text/javascript">
		var msgSendingEmail= '<spring:message code="email.msg.sending.waiting" />';
		var waitingMsg = '<spring:message code="message.please.waiting" />';
	</script>
	
	<script src="assets/plugins/jquery-1.10.2.min.js" type="text/javascript"></script>
	<script src="assets/plugins/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
	<script src="assets/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
	<script src="assets/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
	<script src="assets/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
	<script src="assets/plugins/jquery.blockui.min.js" type="text/javascript"></script>
	<script src="assets/plugins/jquery.cokie.min.js" type="text/javascript"></script>
	<script src="assets/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
	<!-- END CORE PLUGINS -->
	<!-- BEGIN PAGE LEVEL PLUGINS -->
	<script src="assets/plugins/jquery-validation/dist/jquery.validate.min.js" type="text/javascript"></script>
	<script src="assets/plugins/backstretch/jquery.backstretch.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="assets/plugins/select2/select2.min.js"></script>
	<!-- END PAGE LEVEL PLUGINS -->
	<!-- BEGIN PAGE LEVEL SCRIPTS -->
	<script src="assets/scripts/core/app.js" type="text/javascript"></script>
	<script src="assets/scripts/custom/login-soft.js" type="text/javascript"></script>
	<script src="assets/plugins/gritter/js/jquery.gritter.js" type="text/javascript"></script>
	<script src="assets/plugins/bootstrap-modal/js/bootstrap-modalmanager.js" type="text/javascript"></script>
	<script src="assets/plugins/bootstrap-modal/js/bootstrap-modal.js" type="text/javascript"></script>
	
	<script src="assets/scripts/custom/index.js" type="text/javascript"></script>
	<script src="assets/scripts/custom/ui-alert-dialog-api.js"></script>
	<script src="assets/plugins/bootbox/bootbox.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="assets/plugins/jquery-validation/lib/jquery.form.js"></script>
	<script type="text/javascript">
	var FILE_TYPE_VIDEO='<%=JLOWebConstant.FILE_TYPE.VIDEO%>';
	var FILE_SIZE_VIDEO='<%=JLOWebConstant.FILE_SIZE.VIDEO%>';
	var FILE_EXT_VIDEO='<%=JLOWebConstant.FILE_EXT.VIDEO%>';
	
	var FILE_TYPE_IMAGE='<%=JLOWebConstant.FILE_TYPE.IMAGE%>';
	var FILE_SIZE_IMAGE='<%=JLOWebConstant.FILE_SIZE.IMAGE%>';
	var FILE_EXT_IMAGE='<%=JLOWebConstant.FILE_EXT.IMAGE%>';
	
	var FILE_TYPE_DOC='<%=JLOWebConstant.FILE_TYPE.DOCUMENT%>';
	var FILE_SIZE_DOC='<%=JLOWebConstant.FILE_SIZE.DOCUMENT%>';
	var FILE_EXT_DOC='<%=JLOWebConstant.FILE_EXT.DOCUMENT%>';
	</script>
	<script src="assets/js/jlo-common.js" charset="utf-8"></script>
	<!-- END PAGE LEVEL SCRIPTS -->
<!-- END PAGE LEVEL SCRIPTS -->


</head>

<body class="login">

	<!-- Content layout -->
	<tiles:insertAttribute name="body" />



<script>
		jQuery(document).ready(function() {     
		  App.init();
		  Login.init();
		  // clear cookie menu select
		  $.cookie("menuactive","");
		  $.cookie("folderactive","");
		});
	</script>
<!-- END JAVASCRIPTS -->

<c:if test="${sessionScope.RESULT_CODE != null}">
	<script type="text/javascript">	

	var btnLblClose   = '<spring:message code="lbl.btn.close" />';
	$(document).ready(function(){
			var resultCode = "${sessionScope.RESULT_CODE}";
			var resultDesc = "${sessionScope.RESULT_DESC}";
			//var resultTitle = "${sessionScope.RESULT_TITLE}";
			//var titleMessage = (resultCode != null && resultCode == '0') ? 'Success' : 'Fail';
			//alert('Hello');
			alertMessage(resultCode ,resultDesc);
	});
			/*
			var className = '';
			if (resultCode == '0') {
				className = 'gritter-blue';
			} else {
				className = 'gritter-red';
			}
			
			var unique_id = $.gritter.add({
			    // (string | mandatory) the heading of the notification
			    title: "JLO:"+resultCode,
			    // (string | mandatory) the text inside the notification
			    text: "${sessionScope.RESULT_DESC}",
			    // (string | optional) the image to display on the left
			    //image: './assets/img/avatar1.jpg',
			    // (bool | optional) if you want it to fade out on its own or just sit there
			    sticky: false,
			    // (int | optional) the time you want it to be alive for before fading out
			    time: 18000,
			    // see custom.css
			    class_name: className
			});
			*/
			//$.growlCustomUI(resultCode, resultDesc); 
			
			<c:remove var="RESULT_CODE" scope="session" />
			<c:remove var="RESULT_DESC" scope="session" /> 
	</script>
	</c:if>
	
</body>
</html>
