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
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="max-age=0" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="cache-control" content="no-store" />

<!-- BEGIN GLOBAL MANDATORY STYLES -->
<link href="assets/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
<link href="assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="assets/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>
<!-- END GLOBAL MANDATORY STYLES -->
<!-- BEGIN THEME STYLES -->
<link href="assets/css/style-metronic.css" rel="stylesheet" type="text/css"/>
<link href="assets/css/style.css" rel="stylesheet" type="text/css"/>
<link href="assets/css/style-responsive.css" rel="stylesheet" type="text/css"/>
<link href="assets/css/plugins.css" rel="stylesheet" type="text/css"/>
<link href="assets/css/themes/default.css" rel="stylesheet" type="text/css" id="style_color"/>
<link href="assets/css/custom.css" rel="stylesheet" type="text/css" />
<link href="assets/css/override-themes.css" rel="stylesheet" type="text/css" />
<!-- END THEME STYLES -->

<script src="assets/plugins/jquery-1.10.2.min.js" type="text/javascript"></script>
<script src="assets/plugins/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
<script type="text/javascript">
	var msgSendingEmail= '<spring:message code="email.msg.sending.waiting" />';
</script>
<!-- IMPORTANT! Load jquery-ui-1.10.3.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
<script src="assets/plugins/jquery-ui/jquery-ui-1.10.3.custom.min.js" type="text/javascript"></script>
<script src="assets/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="assets/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
<script src="assets/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
<script src="assets/plugins/jquery.blockui.min.js" type="text/javascript"></script>
<script src="assets/plugins/jquery.cokie.min.js" type="text/javascript"></script>
<script src="assets/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
<!-- END CORE PLUGINS -->
<!-- BEGIN PAGE LEVEL PLUGINS -->
<script src="assets/scripts/core/app.js" type="text/javascript"></script>
<script src="assets/plugins/bootstrap-modal/js/bootstrap-modalmanager.js" type="text/javascript"></script>
<script src="assets/plugins/bootstrap-modal/js/bootstrap-modal.js" type="text/javascript"></script>
<!-- DATA TABEL -->
<script type="text/javascript" src="assets/plugins/data-tables/jquery.dataTables.js"></script>
<script type="text/javascript" src="assets/plugins/data-tables/DT_bootstrap.js"></script>
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
<script src="assets/js/jlo-common.js"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<script>
	jQuery(document).ready(function() {
		App.init();
	});
	
	$(document).ajaxStop($.unblockUI); //Until AjaxStop UnblockUI 
	$(document).ajaxSuccess($.unblockUI); //Until AjaxSuccess UnblockUI 
	$(document).ajaxError($.unblockUI); //Until AjaxError UnblockUI 
</script>
<!-- END JAVASCRIPTS -->
</head>

<body class="page-header-fixed">

	<!-- Content layout -->
	<tiles:insertAttribute name="body" />

</body>
</html>
