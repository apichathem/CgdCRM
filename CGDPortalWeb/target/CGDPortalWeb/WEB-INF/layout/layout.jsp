<%@page import="com.locus.jlo.web.constant.JLOWebConstant"%>
<%@page import="java.util.TreeMap"%>
<%@page import="java.util.List"%>
<%@page import="com.locus.common.utils.CollectionUtil"%>
<%@page import="com.locus.jlo.web.bean.modelbean.MenuDetailModelBean"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title><c:out value="${pageTitle}" /></title>
<link rel="icon" href="assets/img/icon-jlo-light-blue.png" type="image/x-icon">
<link rel="shortcut" href="assets/img/icon-jlo-light-blue.png" type="image/x-icon">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="max-age=0" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="cache-control" content="no-store" />

<!-- BEGIN GLOBAL MANDATORY STYLES -->
	<link href="assets/css/font-opensans.css" rel="stylesheet"
		type="text/css" />
	<link href="assets/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
	<link href="assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
	<link href="assets/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css" />
	<!--<link href="assets/plugins/jquery-ui/jquery-ui-1.10.3.custom.min.css" rel="stylesheet" type="text/css" />-->
	
	<!-- END GLOBAL MANDATORY STYLES -->
	<link href="assets/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css" rel="stylesheet" type="text/css" />
	<link href="assets/plugins/fullcalendar/fullcalendar/fullcalendar.css" rel="stylesheet" type="text/css" />
	<link href="assets/plugins/jstree/dist/themes/default/style.min.css" rel="stylesheet" type="text/css" />
	<!-- END PAGE LEVEL PLUGIN STYLES -->
	<!-- BEGIN THEME STYLES -->
	<link href="assets/css/style.css" rel="stylesheet" type="text/css" />
	<link href="assets/css/style-metronic.css" rel="stylesheet" type="text/css" />
	
	<link href="assets/css/style-responsive.css" rel="stylesheet" type="text/css" />
	<link href="assets/css/plugins.css" rel="stylesheet" type="text/css" />
	<link href="assets/css/pages/tasks.css" rel="stylesheet" type="text/css" />
	<!-- <link href="assets/css/themes/locus.css" rel="stylesheet" type="text/css" id="style_color" /> -->
	<link href="assets/css/themes/cgd.css" rel="stylesheet" type="text/css" id="style_color"/>
	<link href="assets/css/print.css" rel="stylesheet" type="text/css" media="print" />
	
	<!-- DATE PICKER -->
	<link href="assets/plugins/bootstrap-datepicker/css/datepicker.css" rel="stylesheet" type="text/css"/>
	<link href="assets/plugins/bootstrap-colorpicker/css/colorpicker.css" rel="stylesheet" type="text/css"/>
	<!-- FILE INPUT -->
	<link href="assets/plugins/bootstrap-fileinput/bootstrap-fileinput.css" rel="stylesheet" type="text/css"/>
	<!-- TAGS -->
	<link href="assets/plugins/jquery-tags-input/jquery.tagsinput.css" rel="stylesheet" type="text/css"/>
	<!-- GRITTER MESSAGE -->
	<link href="assets/plugins/gritter/css/jquery.gritter.css" rel="stylesheet" type="text/css"/>
	<!-- EXTENDED MODAL -->
	<!-- <link href="assets/plugins/bootstrap-modal/css/bootstrap-modal-bs3patch.css" rel="stylesheet" type="text/css"/> -->
	<!-- <link href="assets/plugins/bootstrap-modal/css/bootstrap-modal.css" rel="stylesheet" type="text/css"/>
	 -->
	 <!-- TASK -->
	 <link href="assets/css/pages/tasks.css" rel="stylesheet" type="text/css"/>
	 
	 <!-- TOASTR -->
	<!--  <link rel="stylesheet" type="text/css" href="assets/plugins/bootstrap-toastr/toastr.min.css"/> -->
	 <link href="assets/plugins/select2/select2.css" rel="stylesheet" type="text/css"/>
	 
	 <!-- INBOX -->
	 <link href="assets/css/pages/inbox.css" rel="stylesheet" type="text/css"/>
	 <!-- TEXTEDITOR -->
	 <link rel="stylesheet" type="text/css" href="assets/plugins/bootstrap-wysihtml5/bootstrap-wysihtml5.css"/>
	 <!-- BEGIN PAGE LEVEL STYLES -->
	<link href="assets/plugins/ion.rangeslider/css/ion.rangeSlider.css" rel="stylesheet" type="text/css"/>
	<link href="assets/plugins/ion.rangeslider/css/ion.rangeSlider.Metronic.css" rel="stylesheet" type="text/css"/>
		 
	 <link href="assets/css/custom.css" rel="stylesheet" type="text/css" />
	 <link href="assets/css/override-themes.css" rel="stylesheet" type="text/css" />
<!-- END THEME STYLES -->
	
	<!--  ADD NEW FOR SELECTED ROW CURRENT ON DATATABLE -->
	<link href="assets/css/selectedDT.css" rel="stylesheet" type="text/css" />
	
	<!-- jsPanel-bootstrap Css-->
	<link href="assets/plugins/jsPanel-bootstrap/jsPanel.css" rel="stylesheet" type="text/css" />
	
	<!--jquery-loadmask -->
	<link href="assets/plugins/jquery-loadmask/jquery.loadmask.css" rel="stylesheet" type="text/css" />
	
	<link rel="stylesheet" type="text/css" href="assets/plugins/jquery-multi-select/css/multi-select.css"/>
<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
	<!-- BEGIN CORE PLUGINS -->
	<!--[if lt IE 9]>
		<script src="assets/plugins/respond.min.js"></script>
		<script src="assets/plugins/excanvas.min.js"></script> 
		<![endif]-->
	<script src="assets/plugins/jquery-1.10.2.min.js" type="text/javascript"></script>
	<script src="assets/plugins/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
	<script src="assets/plugins/jquery-ui/jquery-ui-1.10.3.custom.min.js" type="text/javascript"></script>
	<script src="assets/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
	<script src="assets/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
	<script src="assets/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
	<script src="assets/plugins/jquery.blockui.min.js" type="text/javascript"></script>
	<script src="assets/plugins/jquery.cokie.min.js" type="text/javascript"></script>
	<script src="assets/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
	<script src="assets/plugins/jquery-validation/dist/jquery.validate.js" type="text/javascript"></script>
	<!-- END CORE PLUGINS -->
	<!-- BEGIN PAGE LEVEL SCRIPTS -->
	<!-- CUSTOM JS -->
	<script src="assets/scripts/core/app.js" type="text/javascript"></script>
	<script src="assets/scripts/custom/ui-tree.js"></script>	
	<script src="assets/scripts/custom/index.js" type="text/javascript"></script>
	<script src="assets/scripts/custom/tasks.js" type="text/javascript"></script>
	<script src="assets/scripts/custom/ui-alert-dialog-api.js"></script>
	<script src="assets/plugins/bootbox/bootbox.min.js" type="text/javascript"></script>
	<script src="assets/plugins/fullcalendar/fullcalendar/fullcalendar.min.js" type="text/javascript"></script>

	<!-- FORM INPUT -->
	<script src="assets/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
	<!-- DATE PICKER -->
	<script type="text/javascript" src="assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
	<script type="text/javascript" src="assets/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
	<!-- THAI DATE PICKER -->	
	<script type="text/javascript" src="assets/plugins/bootstrap-datepicker/js/locales/bootstrap-datepicker.th.js"></script>
	<!-- buddhist year -->
	<!-- <script type="text/javascript" src="assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker-thai.js"></script> -->
	
	<script src="assets/scripts/custom/components-pickers.js"></script>
	<!-- TREE -->
	<script src="assets/plugins/jstree/dist/jstree.js" type="text/javascript"></script>
	<!-- GMAP -->
	<!-- <script src="http://maps.google.com/maps/api/js?sensor=true" type="text/javascript"></script>
	<script src="assets/plugins/gmaps/gmaps.min.js" type="text/javascript"></script> -->
	<!-- DATA TABEL -->
	<script type="text/javascript" src="assets/plugins/data-tables/jquery.dataTables.js"></script>
	<script type="text/javascript" src="assets/plugins/data-tables/DT_bootstrap.js"></script>
	<script type="text/javascript" src="assets/plugins/select2/select2.min.js"></script>
	
	<!-- INPUT MASK -->
	<script type="text/javascript" src="assets/plugins/jquery-inputmask/jquery.inputmask.bundle.min.js"></script>
	<!-- TAGS -->
	<script src="assets/plugins/jquery-tags-input/jquery.tagsinput.min.js" type="text/javascript"></script>
	<!-- GRITTER MESSAGE -->
	<script src="assets/plugins/gritter/js/jquery.gritter.js" type="text/javascript"></script>
	<!-- MODAL -->
	<script src="assets/plugins/bootstrap-modal/js/bootstrap-modalmanager.js" type="text/javascript"></script>
	<script src="assets/plugins/bootstrap-modal/js/bootstrap-modal.js" type="text/javascript"></script>
	<!-- JLO CUSTOM JAVASCRIPT -->
	<!-- <script src="assets/js/jLoCRM.js" type="text/javascript"></script> -->
	<!-- BEGIN PLUGIN UI-BLOCKUI -->
	<script src="assets/scripts/custom/ui-blockui.js"></script>
	<!-- END PLUGIN UI-BLOCKUI  -->
	
	<!-- TASK -->
	<script src="assets/scripts/custom/tasks.js" type="text/javascript"></script>
	
	<!-- TOASTR -->
	<!-- <script src="assets/plugins/bootstrap-toastr/toastr.min.js"></script>
	<script src="assets/scripts/custom/ui-toastr.js"></script> -->
	<!-- TIMEOUT -->
	<script src="assets/plugins/jquery-idle-timeout/jquery.idletimeout.js" type="text/javascript"></script>
	<script src="assets/plugins/jquery-idle-timeout/jquery.idletimer.js" type="text/javascript"></script>
	
	<!-- File -->
	<script type="text/javascript" src="assets/plugins/bootstrap-fileinput/bootstrap-fileinput.js"></script>
	<script type="text/javascript" src="assets/plugins/jquery-validation/lib/jquery.form.js"></script>
	
	<!-- TextEditor -->
	<script type="text/javascript" src="assets/plugins/bootstrap-wysihtml5/wysihtml5-0.3.0.js"></script>
	<script type="text/javascript" src="assets/plugins/bootstrap-wysihtml5/bootstrap-wysihtml5.js"></script>
	
	<!-- BEGIN PAGE LEVEL PLUGINS -->
	<script src="assets/plugins/ion.rangeslider/js/ion-rangeSlider/ion.rangeSlider.min.js"></script>

	<!-- jquery jspanel -->	
	<script src="assets/plugins/jsPanel-bootstrap/jquery.jspanel.bs-1.4.0.js"></script>
	<!--jquery-loadmask -->
	<script src='assets/plugins/jquery-loadmask/jquery.loadmask.min.js' type='text/javascript'></script>
	
	<script type="text/javascript" src="assets/plugins/jquery-multi-select/js/jquery.multi-select.js"></script>
	<script type="text/javascript" src="assets/plugins/jquery-multi-select/js/jquery.quicksearch.js"></script>
	<!-- CUSTOM -->
	<script type="text/javascript">
	
		var msgSendingEmail= 'Sending Email...';
		var actionLabel= '<spring:message code="lbl.action" />';
		var numberLabel= '<spring:message code="lbl.number" />';
		var required ='<spring:message code="lbl.validate.required" />';
		var remote ='<spring:message code="lbl.validate.remote" />';
		var email ='<spring:message code="lbl.validate.email" />';
		var url ='<spring:message code="lbl.validate.url" />';
		var date ='<spring:message code="lbl.validate.date" />';
		var dateISO ='<spring:message code="lbl.validate.dateISO" />';
		var number ='<spring:message code="lbl.validate.number" />';
		var digits ='<spring:message code="lbl.validate.digits" />';
		var creditcard ='<spring:message code="lbl.validate.creditcard" />';
		var equalTo ='<spring:message code="lbl.validate.equalTo" />';
		var accept ='<spring:message code="lbl.validate.accept" />';
		var maxlength ='<spring:message code="lbl.validate.maxlength" />';
		var minlength ='<spring:message code="lbl.validate.minlength" />';
		var rangelength ='<spring:message code="lbl.validate.rangelength" />';
		var range ='<spring:message code="lbl.validate.range" />';
		var max ='<spring:message code="lbl.validate.max" />';
		var min ='<spring:message code="lbl.validate.min" />';
		var datatable_emptyTable = '<spring:message code="lbl.datatables.empty" />';
		var datatable_pagedetailTable = '<spring:message code="lbl.datatables.pagecount" />';
		var select2_pleaseSelect = '<spring:message code="lbl.select" />';
		var confirmation_title = '<spring:message code="lbl.confirmation" />';		
		var btnLblConfirmation   = '<spring:message code="lbl.btn.confirm" />';
		var btnLblClose   = '<spring:message code="lbl.btn.close" />';
		var btnLblYes   = '<spring:message code="lbl.btn.yes" />';
		var btnLblNo   = '<spring:message code="lbl.btn.no" />';
		var waitingMsg = '<spring:message code="message.please.waiting" />';
		var seeallMessage = '<spring:message code="header.allMessage" />';
		
		var fileNotSupportPlayer = '<spring:message code="file.fileNotSupportPlayer" />';
		var downloadFile = '<spring:message code="file.downloadFile" />';
		var youcanDownload = '<spring:message code="file.youcanDownload" />';
		var CONTEXT_PATH = "<%=request.getContextPath()%>";
		
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
	<script type="text/javascript">
		var confirmDeleteMsg = '<spring:message code="lbl.confirm.delete" />';
		function MenuJLo(){
			$(".page-sidebar-menu > li.folderMenu").click(function(){
				if($(this).find(".sub-menu").length ==0){
					$.cookie("folderactive","");
					$.cookie("menuactive",$(this).prop("id"));
				}				
			});
			$(".page-sidebar-menu > .folderMenu > .sub-menu > li").click(function(){
				id = $(this).parent().parent().prop("id");
				$.cookie("folderactive",id);
				$.cookie("menuactive",$(this).prop("id"));
			});
			var folderactive = $.cookie("folderactive");
			console.log("folderactive:"+folderactive);
			if(folderactive!=""){
				$("#"+folderactive).addClass("open active");
				$("#"+folderactive+" .arrow").addClass("open");
			}
			 var menuactive = $.cookie("menuactive");
			console.log("menuactive:"+menuactive);
			if(menuactive!=""){
				$("#"+menuactive).addClass("active");
			} 
			
		};
		
		function goToPage(action, menuId) {
			var url = action;
			$("#menuFormId").attr("action", url);
		   	$("#menuFormId").attr("method", "POST");
		   	$("#menuSessionId").val(menuId);
		   	$("#menuFormId").submit();
		}
		
		jQuery(document).ready(function() {
			
			// click menu wait next page
			$(".page-sidebar-menu li .sub-menu li a").on("click",function(){
				jLoBlockUI();
			});
			App.init(); // initlayout and core plugins
			//Index.init();
			//Index.initCalendar(); // init index page's custom scripts
			//Index.initIntro();
			//UIAlertDialogApi.init();
			//UITree.init();
			//UIBlockUI.init(); // initial BlockUI
			//messageBroadcastInterval(); // initial message broadcase timer
			//Tasks.initDashboardWidget();
			MenuJLo();
		});
		
		$(document).ajaxStop($.unblockUI); //Until AjaxStop UnblockUI 
		$(document).ajaxSuccess($.unblockUI); //Until AjaxSuccess UnblockUI 
		$(document).ajaxError($.unblockUI); //Until AjaxError UnblockUI 
		
		
	</script>
	
</head>

<body class="page-header-fixed">
	<div id="maskLoadingGlobal">
	
	<tiles:insertAttribute name="header" />

	<div class="clearfix clearfixheader" >
	</div>
	

	<!-- Content layout -->
	<!-- BEGIN CONTAINER -->
	<form id="menuFormId" action="">
		<input type="hidden" id="menuSessionId" name="menuSessionId">
	</form>
	<div class="page-container">
		<!-- BEGIN SIDEBAR -->
		<div class="page-sidebar-wrapper">
			<div class="page-sidebar navbar-collapse collapse">
				<!-- add "navbar-no-scroll" class to disable the scrolling of the sidebar menu -->
				<!-- BEGIN SIDEBAR MENU -->
				<ul class="page-sidebar-menu" data-auto-scroll="true" data-slide-speed="200">
					<li class="sidebar-toggler-wrapper">
						<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
						<div class="sidebar-toggler hidden-phone"></div> <!-- BEGIN SIDEBAR TOGGLER BUTTON -->
					</li>
					<li class="sidebar-search-wrapper"><br> <!-- BEGIN RESPONSIVE QUICK SEARCH FORM --></li>
					
					<%
					
					Locale locale = (Locale)request.getSession().getAttribute("CURRENT_LANG");
					String lang = null;
					if (locale!=null) {
						lang = locale.getLanguage();
					} else {
						lang = "en";
					}
					List<MenuDetailModelBean> menuList = (List<MenuDetailModelBean>) request.getSession().getAttribute("MENU_PROFILE");
					String menuName = "";
					String menuNames[];
					boolean isSubMenu = false;
			    	for (int i = 0; i < menuList.size() ; i++) {
			    		MenuDetailModelBean menuDomain = menuList.get(i);
			    		
			    		
			    			menuNames = menuDomain.getMenuName().split("\\|");
			    			if(lang.equals("th")){
			    				menuName = menuNames[0];
				    		}else{
				    			if(menuNames.length > 1)
				    				menuName = menuNames[1];
				    		}
			    	%>
			    	
			    	<%
			    		
			    		if(menuDomain.getMenuParentId().equalsIgnoreCase("0")){
			    			if(isSubMenu){
			    				out.write("</ul>");
			    				isSubMenu=false;
			    			}
			    			if(i>0){ out.write("</li>");}
			    	%>
					<li id="menu<%=menuDomain.getMenuId()%>" class="folderMenu">
						<a href="<%=menuDomain.getMenuAction()%>"> 
							<i class="fa <%=menuDomain.getMenuIcon()%>"></i> 
							<span class="title"> <%=menuName%> </span> 
							<% if(menuDomain.getMenuLevel().equalsIgnoreCase("1")){ %>
							<span class="arrow"></span>
							<% } %>
							
						</a>
					
						
					<%
			    		}else{
			    			if(!isSubMenu){
			    				out.write("<ul class='sub-menu'>");
			    				isSubMenu=true;
			    			}
			    			out.write("<li id=\"submenu"+menuDomain.getMenuId()+"\">");
			    	%>
			    			
			    			<a href="#" onclick="goToPage('<%=menuDomain.getMenuAction() %>', '<%=menuDomain.getMenuId() %>')">
			    			<i class="fa <%=menuDomain.getMenuIcon()%>"></i>  <%=menuName%> </a>
			    			</li>
			    	<%
			    		}
				    	if(i==menuList.size()-1 && isSubMenu){
		    				out.write("</ul>");
		    			}
				    
					} 
					%>
	        		
					 <!--
					<form class="sidebar-search" action="extra_search.html" method="POST">
						<div class="form-container">
							<div class="input-box">
								<a href="javascript:;" class="remove">
								</a>
								<input type="text" placeholder="Search..."/>
								<input type="button" class="submit" value=" "/>
							</div>
						</div>
					</form>
					--> <!-- END RESPONSIVE QUICK SEARCH FORM --></li>
					<%-- <li>
						<a href="home.htm"> 
							<i class="fa fa-home"></i> 
							<span class="title"> <spring:message code="menu.home" /> </span> 
						</a>
					</li>
					<li>
						<a href="codebookManagement.htm"> 
							<i class="fa fa-book"></i> 
							<span class="title"> <spring:message code="menu.system.codebookManagement"/> </span> 
						</a>
					</li>
					<li>
						<a href="userManagement.htm"> 
							<i class="fa fa-user"></i> 
							<span class="title"> <spring:message code="menu.system.userManagement"/> </span> 
						</a>
					</li>
					<li>
						<a href="roleManagement.htm"> 
							<i class="fa fa-users"></i> 
							<span class="title"> <spring:message code="menu.system.roleManagement"/> </span> 
						</a>
					</li> 
					<li>
						<a href="menuManagement.htm"> 
							<i class="fa fa-bars"></i> 
							<span class="title"> <spring:message code="menu.system.menuManagement"/> </span> 
						</a>
					</li> 
					 <li>
						<a href="folderManagement.htm"> 
							<i class="fa fa-folder-open"></i> 
							<span class="title"> <spring:message code="menu.knowledgebase.folderManagemant"/> </span> 
						</a>
					</li> 
					<li>
						<a href="newsManagement.htm"> 
							<i class="fa fa-globe"></i> 
							<span class="title"> <spring:message code="menu.knowledgebase.news"/> </span> 
						</a>
					</li>
					<li>
						<a href="contentManagement.htm"> 
							<i class="fa fa-download"></i> 
							<span class="title"> <spring:message code="menu.knowledgebase.content"/> </span> 
						</a>
					</li>
					<li>
						<a href="locationmanagent.htm"> 
							<i class="fa fa-map-marker"></i> 
							<span class="title"> <spring:message code="menu.knowledgebase.location"/> </span> 
						</a>
					</li> --%>
					
					
				</ul>
				<!-- END SIDEBAR MENU -->
			</div>
		</div>
		<!-- END SIDEBAR -->
		<!-- BEGIN CONTENT -->
		<div class="page-content-wrapper">
			<div class="page-content">
				<!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
				<div class="modal fade" id="portlet-config" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
					aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
								<h4 class="modal-title">Modal title</h4>
							</div>
							<div class="modal-body">Widget settings form goes here</div>
							<div class="modal-footer">
								<button type="button" class="btn blue">Save changes</button>
								<button type="button" class="btn default" data-dismiss="modal">Close</button>
							</div>
						</div>
						<!-- /.modal-content -->
					</div>
					<!-- /.modal-dialog -->
				</div>
				<!-- /.modal -->
				<!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->
				<!-- BEGIN STYLE CUSTOMIZER -->
				<!-- <div class="theme-panel hidden-xs hidden-sm">
					<div class="toggler"></div>
					<div class="toggler-close"></div>
					<div class="theme-options">
						<div class="theme-option theme-colors clearfix">
							<span> THEME COLOR </span>
							<ul>
								<li class="color-black current color-default" data-style="default"></li>
								<li class="color-blue" data-style="blue"></li>
								<li class="color-brown" data-style="brown"></li>
								<li class="color-purple" data-style="purple"></li>
								<li class="color-grey" data-style="grey"></li>
								<li class="color-white color-light" data-style="light"></li>
							</ul>
						</div>
						<div class="theme-option">
							<span> Layout </span> <select class="layout-option form-control input-small">
								<option value="fluid" selected="selected">Fluid</option>
								<option value="boxed">Boxed</option>
							</select>
						</div>
						<div class="theme-option">
							<span> Header </span> <select class="header-option form-control input-small">
								<option value="fixed" selected="selected">Fixed</option>
								<option value="default">Default</option>
							</select>
						</div>
						<div class="theme-option">
							<span> Sidebar </span> <select class="sidebar-option form-control input-small">
								<option value="fixed">Fixed</option>
								<option value="default" selected="selected">Default</option>
							</select>
						</div>
						<div class="theme-option">
							<span> Sidebar Position </span> <select class="sidebar-pos-option form-control input-small">
								<option value="left" selected="selected">Left</option>
								<option value="right">Right</option>
							</select>
						</div>
						<div class="theme-option">
							<span> Footer </span> <select class="footer-option form-control input-small">
								<option value="fixed">Fixed</option>
								<option value="default" selected="selected">Default</option>
							</select>
						</div>
					</div>
				</div> -->
				<!-- END STYLE CUSTOMIZER -->
				
				<!-- BEGIN PAGE HEADER-->
				<tiles:insertAttribute name="body" />
				<!-- END PAGE HEADER-->

				<div class="clearfix"></div>
			</div>
		</div>
		<!-- END CONTENT -->
	</div>
	
	<!-- BEGIN MODAL POPUP -->
	<div class="modal fade" id="portlet-config" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
					<h4 class="modal-title">Modal title</h4>
				</div>
				<div class="modal-body">
					 Widget settings form goes here
				</div>
				<div class="modal-footer">
					<button type="button" class="btn blue">Save changes</button>
					<button type="button" class="btn default" data-dismiss="modal">Close</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
	</div>
	<!-- END MODAL POPUP -->
	
	<!-- BEGIN GRITTER MESSAGE -->
	<c:if test="${sessionScope.RESULT_DESC != null}">
	<script type="text/javascript">
			var resultCode = "${sessionScope.RESULT_CODE}";
			var resultDesc = "${sessionScope.RESULT_DESC}";
			var resultTitle = "${sessionScope.RESULT_TITLE}";
			//var titleMessage = (resultCode != null && resultCode == '0') ? 'Success' : 'Fail';
			
			alertMessage(resultTitle ,resultDesc);
			
			<c:remove var="RESULT_CODE" scope="session" />
			<c:remove var="RESULT_DESC" scope="session" /> 
			
	</script>
	</c:if>
	<!-- END GRITTER MESSAGE -->
	<!-- END CONTAINER -->
	</div>
</body>
</html>
