<!DOCTYPE html>
<html lang="en" class="no-js">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<meta charset="utf-8"/>
<title>Metronic | Form Stuff - Form Validation</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0" name="viewport"/>
<meta content="" name="description"/>
<meta content="" name="author"/>
<!-- BEGIN GLOBAL MANDATORY STYLES -->
<link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all" rel="stylesheet" type="text/css"/>
<link href="assets/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
<link href="assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="assets/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>
<!-- END GLOBAL MANDATORY STYLES -->
<!-- BEGIN PAGE LEVEL STYLES -->
<link rel="stylesheet" type="text/css" href="assets/plugins/select2/select2.css"/>
<link rel="stylesheet" type="text/css" href="assets/plugins/select2/select2-metronic.css"/>
<link rel="stylesheet" type="text/css" href="assets/plugins/bootstrap-wysihtml5/bootstrap-wysihtml5.css"/>
<link rel="stylesheet" type="text/css" href="assets/plugins/bootstrap-markdown/css/bootstrap-markdown.min.css">
<!-- END PAGE LEVEL SCRIPTS -->
<!-- BEGIN THEME STYLES -->
<link href="assets/css/style-metronic.css" rel="stylesheet" type="text/css"/>
<link href="assets/css/style.css" rel="stylesheet" type="text/css"/>
<link href="assets/css/style-responsive.css" rel="stylesheet" type="text/css"/>
<link href="assets/css/plugins.css" rel="stylesheet" type="text/css"/>
<link href="assets/css/themes/default.css" rel="stylesheet" type="text/css" id="style_color"/>
<link href="assets/css/custom.css" rel="stylesheet" type="text/css"/>
<!-- END THEME STYLES -->
<link rel="shortcut icon" href="favicon.ico"/>
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="page-header-fixed">
<!-- BEGIN HEADER -->
<div class="header navbar navbar-fixed-top">
	<!-- BEGIN TOP NAVIGATION BAR -->
	<div class="header-inner">
		<!-- BEGIN LOGO -->
		<a class="navbar-brand" href="index.html">
			<img src="assets/img/logo.png" alt="logo" class="img-responsive"/>
		</a>
		<!-- END LOGO -->
		<!-- BEGIN RESPONSIVE MENU TOGGLER -->
		<a href="javascript:;" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
			<img src="assets/img/menu-toggler.png" alt=""/>
		</a>
		<!-- END RESPONSIVE MENU TOGGLER -->
		<!-- BEGIN TOP NAVIGATION MENU -->
		<ul class="nav navbar-nav pull-right">
			<!-- BEGIN NOTIFICATION DROPDOWN -->
			<li class="dropdown" id="header_notification_bar">
				<a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
					<i class="fa fa-warning"></i>
					<span class="badge">
						 6
					</span>
				</a>
				<ul class="dropdown-menu extended notification">
					<li>
						<p>
							 You have 14 new notifications
						</p>
					</li>
					<li>
						<ul class="dropdown-menu-list scroller" style="height: 250px;">
							<li>
								<a href="#">
									<span class="label label-icon label-success">
										<i class="fa fa-plus"></i>
									</span>
									 New user registered.
									<span class="time">
										 Just now
									</span>
								</a>
							</li>
							<li>
								<a href="#">
									<span class="label label-icon label-danger">
										<i class="fa fa-bolt"></i>
									</span>
									 Server #12 overloaded.
									<span class="time">
										 15 mins
									</span>
								</a>
							</li>
							<li>
								<a href="#">
									<span class="label label-icon label-warning">
										<i class="fa fa-bell-o"></i>
									</span>
									 Server #2 not responding.
									<span class="time">
										 22 mins
									</span>
								</a>
							</li>
							<li>
								<a href="#">
									<span class="label label-icon label-info">
										<i class="fa fa-bullhorn"></i>
									</span>
									 Application error.
									<span class="time">
										 40 mins
									</span>
								</a>
							</li>
							<li>
								<a href="#">
									<span class="label label-icon label-danger">
										<i class="fa fa-bolt"></i>
									</span>
									 Database overloaded 68%.
									<span class="time">
										 2 hrs
									</span>
								</a>
							</li>
							<li>
								<a href="#">
									<span class="label label-icon label-danger">
										<i class="fa fa-bolt"></i>
									</span>
									 2 user IP blocked.
									<span class="time">
										 5 hrs
									</span>
								</a>
							</li>
							<li>
								<a href="#">
									<span class="label label-icon label-warning">
										<i class="fa fa-bell-o"></i>
									</span>
									 Storage Server #4 not responding.
									<span class="time">
										 45 mins
									</span>
								</a>
							</li>
							<li>
								<a href="#">
									<span class="label label-icon label-info">
										<i class="fa fa-bullhorn"></i>
									</span>
									 System Error.
									<span class="time">
										 55 mins
									</span>
								</a>
							</li>
							<li>
								<a href="#">
									<span class="label label-icon label-danger">
										<i class="fa fa-bolt"></i>
									</span>
									 Database overloaded 68%.
									<span class="time">
										 2 hrs
									</span>
								</a>
							</li>
						</ul>
					</li>
					<li class="external">
						<a href="#">
							 See all notifications <i class="m-icon-swapright"></i>
						</a>
					</li>
				</ul>
			</li>
			<!-- END NOTIFICATION DROPDOWN -->
			<!-- BEGIN INBOX DROPDOWN -->
			<li class="dropdown" id="header_inbox_bar">
				<a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
					<i class="fa fa-envelope"></i>
					<span class="badge">
						 5
					</span>
				</a>
				<ul class="dropdown-menu extended inbox">
					<li>
						<p>
							 You have 12 new messages
						</p>
					</li>
					<li>
						<ul class="dropdown-menu-list scroller" style="height: 250px;">
							<li>
								<a href="inbox.html?a=view">
									<span class="photo">
										<img src="./assets/img/avatar2.jpg" alt=""/>
									</span>
									<span class="subject">
										<span class="from">
											 Lisa Wong
										</span>
										<span class="time">
											 Just Now
										</span>
									</span>
									<span class="message">
										 Vivamus sed auctor nibh congue nibh. auctor nibh auctor nibh...
									</span>
								</a>
							</li>
							<li>
								<a href="inbox.html?a=view">
									<span class="photo">
										<img src="./assets/img/avatar3.jpg" alt=""/>
									</span>
									<span class="subject">
										<span class="from">
											 Richard Doe
										</span>
										<span class="time">
											 16 mins
										</span>
									</span>
									<span class="message">
										 Vivamus sed congue nibh auctor nibh congue nibh. auctor nibh auctor nibh...
									</span>
								</a>
							</li>
							<li>
								<a href="inbox.html?a=view">
									<span class="photo">
										<img src="./assets/img/avatar1.jpg" alt=""/>
									</span>
									<span class="subject">
										<span class="from">
											 Bob Nilson
										</span>
										<span class="time">
											 2 hrs
										</span>
									</span>
									<span class="message">
										 Vivamus sed nibh auctor nibh congue nibh. auctor nibh auctor nibh...
									</span>
								</a>
							</li>
							<li>
								<a href="inbox.html?a=view">
									<span class="photo">
										<img src="./assets/img/avatar2.jpg" alt=""/>
									</span>
									<span class="subject">
										<span class="from">
											 Lisa Wong
										</span>
										<span class="time">
											 40 mins
										</span>
									</span>
									<span class="message">
										 Vivamus sed auctor 40% nibh congue nibh...
									</span>
								</a>
							</li>
							<li>
								<a href="inbox.html?a=view">
									<span class="photo">
										<img src="./assets/img/avatar3.jpg" alt=""/>
									</span>
									<span class="subject">
										<span class="from">
											 Richard Doe
										</span>
										<span class="time">
											 46 mins
										</span>
									</span>
									<span class="message">
										 Vivamus sed congue nibh auctor nibh congue nibh. auctor nibh auctor nibh...
									</span>
								</a>
							</li>
						</ul>
					</li>
					<li class="external">
						<a href="inbox.html">
							 See all messages <i class="m-icon-swapright"></i>
						</a>
					</li>
				</ul>
			</li>
			<!-- END INBOX DROPDOWN -->
			<!-- BEGIN TODO DROPDOWN -->
			<li class="dropdown" id="header_task_bar">
				<a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
					<i class="fa fa-tasks"></i>
					<span class="badge">
						 5
					</span>
				</a>
				<ul class="dropdown-menu extended tasks">
					<li>
						<p>
							 You have 12 pending tasks
						</p>
					</li>
					<li>
						<ul class="dropdown-menu-list scroller" style="height: 250px;">
							<li>
								<a href="#">
									<span class="task">
										<span class="desc">
											 New release v1.2
										</span>
										<span class="percent">
											 30%
										</span>
									</span>
									<span class="progress">
										<span style="width: 40%;" class="progress-bar progress-bar-success" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100">
											<span class="sr-only">
												 40% Complete
											</span>
										</span>
									</span>
								</a>
							</li>
							<li>
								<a href="#">
									<span class="task">
										<span class="desc">
											 Application deployment
										</span>
										<span class="percent">
											 65%
										</span>
									</span>
									<span class="progress progress-striped">
										<span style="width: 65%;" class="progress-bar progress-bar-danger" aria-valuenow="65" aria-valuemin="0" aria-valuemax="100">
											<span class="sr-only">
												 65% Complete
											</span>
										</span>
									</span>
								</a>
							</li>
							<li>
								<a href="#">
									<span class="task">
										<span class="desc">
											 Mobile app release
										</span>
										<span class="percent">
											 98%
										</span>
									</span>
									<span class="progress">
										<span style="width: 98%;" class="progress-bar progress-bar-success" aria-valuenow="98" aria-valuemin="0" aria-valuemax="100">
											<span class="sr-only">
												 98% Complete
											</span>
										</span>
									</span>
								</a>
							</li>
							<li>
								<a href="#">
									<span class="task">
										<span class="desc">
											 Database migration
										</span>
										<span class="percent">
											 10%
										</span>
									</span>
									<span class="progress progress-striped">
										<span style="width: 10%;" class="progress-bar progress-bar-warning" aria-valuenow="10" aria-valuemin="0" aria-valuemax="100">
											<span class="sr-only">
												 10% Complete
											</span>
										</span>
									</span>
								</a>
							</li>
							<li>
								<a href="#">
									<span class="task">
										<span class="desc">
											 Web server upgrade
										</span>
										<span class="percent">
											 58%
										</span>
									</span>
									<span class="progress progress-striped">
										<span style="width: 58%;" class="progress-bar progress-bar-info" aria-valuenow="58" aria-valuemin="0" aria-valuemax="100">
											<span class="sr-only">
												 58% Complete
											</span>
										</span>
									</span>
								</a>
							</li>
							<li>
								<a href="#">
									<span class="task">
										<span class="desc">
											 Mobile development
										</span>
										<span class="percent">
											 85%
										</span>
									</span>
									<span class="progress progress-striped">
										<span style="width: 85%;" class="progress-bar progress-bar-success" aria-valuenow="85" aria-valuemin="0" aria-valuemax="100">
											<span class="sr-only">
												 85% Complete
											</span>
										</span>
									</span>
								</a>
							</li>
							<li>
								<a href="#">
									<span class="task">
										<span class="desc">
											 New UI release
										</span>
										<span class="percent">
											 18%
										</span>
									</span>
									<span class="progress progress-striped">
										<span style="width: 18%;" class="progress-bar progress-bar-important" aria-valuenow="18" aria-valuemin="0" aria-valuemax="100">
											<span class="sr-only">
												 18% Complete
											</span>
										</span>
									</span>
								</a>
							</li>
						</ul>
					</li>
					<li class="external">
						<a href="#">
							 See all tasks <i class="m-icon-swapright"></i>
						</a>
					</li>
				</ul>
			</li>
			<!-- END TODO DROPDOWN -->
			<!-- BEGIN USER LOGIN DROPDOWN -->
			<li class="dropdown user">
				<a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
					<img alt="" src="assets/img/avatar1_small.jpg"/>
					<span class="username">
						 Bob Nilson
					</span>
					<i class="fa fa-angle-down"></i>
				</a>
				<ul class="dropdown-menu">
					<li>
						<a href="extra_profile.html">
							<i class="fa fa-user"></i> My Profile
						</a>
					</li>
					<li>
						<a href="page_calendar.html">
							<i class="fa fa-calendar"></i> My Calendar
						</a>
					</li>
					<li>
						<a href="inbox.html">
							<i class="fa fa-envelope"></i> My Inbox
							<span class="badge badge-danger">
								 3
							</span>
						</a>
					</li>
					<li>
						<a href="#">
							<i class="fa fa-tasks"></i> My Tasks
							<span class="badge badge-success">
								 7
							</span>
						</a>
					</li>
					<li class="divider">
					</li>
					<li>
						<a href="javascript:;" id="trigger_fullscreen">
							<i class="fa fa-arrows"></i> Full Screen
						</a>
					</li>
					<li>
						<a href="extra_lock.html">
							<i class="fa fa-lock"></i> Lock Screen
						</a>
					</li>
					<li>
						<a href="login.html">
							<i class="fa fa-key"></i> Log Out
						</a>
					</li>
				</ul>
			</li>
			<!-- END USER LOGIN DROPDOWN -->
		</ul>
		<!-- END TOP NAVIGATION MENU -->
	</div>
	<!-- END TOP NAVIGATION BAR -->
</div>
<!-- END HEADER -->
<div class="clearfix">
</div>
<!-- BEGIN CONTAINER -->
<div class="page-container">
	<!-- BEGIN SIDEBAR -->
	<div class="page-sidebar-wrapper">
		<div class="page-sidebar navbar-collapse collapse">
			<!-- BEGIN SIDEBAR MENU -->
			<ul class="page-sidebar-menu" data-auto-scroll="true" data-slide-speed="200">
				<li class="sidebar-toggler-wrapper">
					<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
					<div class="sidebar-toggler hidden-phone">
					</div>
					<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
				</li>
				<li class="sidebar-search-wrapper">
					<!-- BEGIN RESPONSIVE QUICK SEARCH FORM -->
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
					<!-- END RESPONSIVE QUICK SEARCH FORM -->
				</li>
				<li class="start ">
					<a href="index.html">
						<i class="fa fa-home"></i>
						<span class="title">
							Dashboard
						</span>
					</a>
				</li>
				<li>
					<a href="javascript:;">
						<i class="fa fa-shopping-cart"></i>
						<span class="title">
							E-Commerce
						</span>
						<span class="arrow ">
						</span>
					</a>
					<ul class="sub-menu">
						<li>
							<a href="ecommerce_index.html">
								<i class="fa fa-bullhorn"></i>
								Dashboard
							</a>
						</li>
						<li>
							<a href="ecommerce_orders.html">
								<i class="fa fa-shopping-cart"></i>
								Orders
							</a>
						</li>
						<li>
							<a href="ecommerce_orders_view.html">
								<i class="fa fa-tags"></i>
								Order View
							</a>
						</li>
						<li>
							<a href="ecommerce_products.html">
								<i class="fa fa-sitemap"></i>
								Products
							</a>
						</li>
						<li>
							<a href="ecommerce_products_edit.html">
								<i class="fa fa-file-o"></i>
								Product Edit
							</a>
						</li>
					</ul>
				</li>
				<li>
					<a href="javascript:;">
						<i class="fa fa-gift"></i>
						<span class="title">
							Frontend Themes
						</span>
						<span class="arrow">
						</span>
					</a>
					<ul class="sub-menu">
						<li class="tooltips" data-container="body" data-placement="right" data-html="true" data-original-title="Complete E-Commerce Frontend Theme For Metronic Admin">
							<a href="http://keenthemes.com/preview/index.php?theme=metronic_ecommerce" target="_blank">
								<span class="title">
									E-Commerce Frontend
								</span>
							</a>
						</li>
						<li class="tooltips" data-container="body" data-placement="right" data-html="true" data-original-title="Complete Multipurpose Corporate Frontend Theme For Metronic Admin">
							<a href="http://keenthemes.com/preview/index.php?theme=metronic_frontend" target="_blank">
								<span class="title">
									Corporate Frontend
								</span>
							</a>
						</li>
					</ul>
				</li>
				<li>
					<a href="javascript:;">
						<i class="fa fa-cogs"></i>
						<span class="title">
							Page Layouts
						</span>
						<span class="arrow ">
						</span>
					</a>
					<ul class="sub-menu">
						<li>
							<a href="index_horizontal_menu.html">
								 Dashboard & Mega Menu
							</a>
						</li>
						<li>
							<a href="layout_horizontal_sidebar_menu.html">
								 Horizontal & Sidebar Menu
							</a>
						</li>
						<li>
							<a href="layout_horizontal_menu1.html">
								 Horizontal Mega Menu 1
							</a>
						</li>
						<li>
							<a href="layout_horizontal_menu2.html">
								 Horizontal Mega Menu 2
							</a>
						</li>
						<li>
							<a href="layout_search_on_header.html">
								 Searchbar On Header
							</a>
						</li>
						<li>
							<a href="layout_sidebar_toggler_on_header.html">
								 Sidebar Toggler On Header
							</a>
						</li>
						<li>
							<a href="layout_sidebar_reversed.html">
								<span class="badge badge-roundless badge-success">
									new
								</span>
								Right Sidebar Page
							</a>
						</li>
						<li>
							<a href="layout_sidebar_fixed.html">
								 Sidebar Fixed Page
							</a>
						</li>
						<li>
							<a href="layout_sidebar_closed.html">
								 Sidebar Closed Page
							</a>
						</li>
						<li>
							<a href="layout_ajax.html">
								 Content Loading via Ajax
							</a>
						</li>
						<li>
							<a href="layout_disabled_menu.html">
								 Disabled Menu Links
							</a>
						</li>
						<li>
							<a href="layout_blank_page.html">
								 Blank Page
							</a>
						</li>
						<li>
							<a href="layout_boxed_page.html">
								 Boxed Page
							</a>
						</li>
						<li>
							<a href="layout_language_bar.html">
								 Language Switch Bar
							</a>
						</li>
						<li>
							<a href="layout_promo.html">
								 Promo Page
							</a>
						</li>
					</ul>
				</li>
				<li>
					<a href="javascript:;">
						<i class="fa fa-bookmark-o"></i>
						<span class="title">
							UI Features
						</span>
						<span class="arrow ">
						</span>
					</a>
					<ul class="sub-menu">
						<li>
							<a href="ui_general.html">
								 General
							</a>
						</li>
						<li>
							<a href="ui_buttons.html">
								 Buttons & Icons
							</a>
						</li>
						<li>
							<a href="ui_typography.html">
								 Typography
							</a>
						</li>
						<li>
							<a href="ui_tabs_accordions_navs.html">
								 Tabs, Accordions & Navs
							</a>
						</li>
						<li>
							<a href="ui_tree.html">
								<span class="badge badge-roundless badge-important">
									new
								</span>
								Tree View
							</a>
						</li>
						<li>
							<a href="ui_page_progress_style_1.html">
								<span class="badge badge-roundless badge-warning">
									new
								</span>
								Page Progress Bar
							</a>
						</li>
						<li>
							<a href="ui_blockui.html">
								 Block UI
							</a>
						</li>
						<li>
							<a href="ui_notific8.html">
								 Notific8 Notifications
							</a>
						</li>
						<li>
							<a href="ui_toastr.html">
								 Toastr Notifications
							</a>
						</li>
						<li>
							<a href="ui_alert_dialog_api.html">
								<span class="badge badge-roundless badge-important">
									new
								</span>
								Alerts & Dialogs API
							</a>
						</li>
						<li>
							<a href="ui_session_timeout.html">
								 Session Timeout
							</a>
						</li>
						<li>
							<a href="ui_idle_timeout.html">
								 User Idle Timeout
							</a>
						</li>
						<li>
							<a href="ui_modals.html">
								 Modals
							</a>
						</li>
						<li>
							<a href="ui_extended_modals.html">
								 Extended Modals
							</a>
						</li>
						<li>
							<a href="ui_tiles.html">
								 Tiles
							</a>
						</li>
						<li>
							<a href="ui_datepaginator.html">
								<span class="badge badge-roundless badge-success">
									new
								</span>
								Date Paginator
							</a>
						</li>
						<li>
							<a href="ui_nestable.html">
								 Nestable List
							</a>
						</li>
					</ul>
				</li>
				<li>
					<a href="javascript:;">
						<i class="fa fa-puzzle-piece"></i>
						<span class="title">
							UI Components
						</span>
						<span class="arrow ">
						</span>
					</a>
					<ul class="sub-menu">
						<li>
							<a href="components_pickers.html">
								 Pickers
							</a>
						</li>
						<li>
							<a href="components_dropdowns.html">
								 Custom Dropdowns
							</a>
						</li>
						<li>
							<a href="components_form_tools.html">
								 Form Tools
							</a>
						</li>
						<li>
							<a href="components_editors.html">
								 Markdown & WYSIWYG Editors
							</a>
						</li>
						<li>
							<a href="components_ion_sliders.html">
								 Ion Range Sliders
							</a>
						</li>
						<li>
							<a href="components_noui_sliders.html">
								 NoUI Range Sliders
							</a>
						</li>
						<li>
							<a href="components_jqueryui_sliders.html">
								 jQuery UI Sliders
							</a>
						</li>
						<li>
							<a href="components_knob_dials.html">
								 Knob Circle Dials
							</a>
						</li>
					</ul>
				</li>
				<li class="active ">
					<a href="javascript:;">
						<i class="fa fa-table"></i>
						<span class="title">
							Form Stuff
						</span>
						<span class="selected">
						</span>
						<span class="arrow open">
						</span>
					</a>
					<ul class="sub-menu">
						<li>
							<a href="form_controls.html">
								 Form Controls
							</a>
						</li>
						<li>
							<a href="form_layouts.html">
								 Form Layouts
							</a>
						</li>
						<li>
							<a href="form_editable.html">
								<span class="badge badge-roundless badge-warning">
									new
								</span>
								Form X-editable
							</a>
						</li>
						<li>
							<a href="form_wizard.html">
								 Form Wizard
							</a>
						</li>
						<li class="active">
							<a href="form_validation.html">
								 Form Validation
							</a>
						</li>
						<li>
							<a href="form_image_crop.html">
								<span class="badge badge-roundless badge-important">
									new
								</span>
								Image Cropping
							</a>
						</li>
						<li>
							<a href="form_fileupload.html">
								 Multiple File Upload
							</a>
						</li>
						<li>
							<a href="form_dropzone.html">
								 Dropzone File Upload
							</a>
						</li>
					</ul>
				</li>
				<li>
					<a href="javascript:;">
						<i class="fa fa-envelope-o"></i>
						<span class="title">
							Email Templates
						</span>
						<span class="arrow ">
						</span>
					</a>
					<ul class="sub-menu">
						<li>
							<a href="email_newsletter.html">
								 Responsive Newsletter<br>
								Email Template
							</a>
						</li>
						<li>
							<a href="email_system.html">
								 Responsive System<br>
								Email Template
							</a>
						</li>
					</ul>
				</li>
				<li>
					<a href="javascript:;">
						<i class="fa fa-sitemap"></i>
						<span class="title">
							Pages
						</span>
						<span class="arrow ">
						</span>
					</a>
					<ul class="sub-menu">
						<li>
							<a href="page_portfolio.html">
								<i class="fa fa-briefcase"></i>
								<span class="badge badge-warning badge-roundless">
									new
								</span>
								Portfolio
							</a>
						</li>
						<li>
							<a href="page_timeline.html">
								<i class="fa fa-clock-o"></i>
								<span class="badge badge-info">
									4
								</span>
								Timeline
							</a>
						</li>
						<li>
							<a href="page_coming_soon.html">
								<i class="fa fa-cogs"></i>
								Coming Soon
							</a>
						</li>
						<li>
							<a href="page_blog.html">
								<i class="fa fa-comments"></i>
								Blog
							</a>
						</li>
						<li>
							<a href="page_blog_item.html">
								<i class="fa fa-font"></i>
								Blog Post
							</a>
						</li>
						<li>
							<a href="page_news.html">
								<i class="fa fa-coffee"></i>
								<span class="badge badge-success">
									9
								</span>
								News
							</a>
						</li>
						<li>
							<a href="page_news_item.html">
								<i class="fa fa-bell"></i>
								News View
							</a>
						</li>
						<li>
							<a href="page_about.html">
								<i class="fa fa-group"></i>
								About Us
							</a>
						</li>
						<li>
							<a href="page_contact.html">
								<i class="fa fa-envelope-o"></i>
								Contact Us
							</a>
						</li>
						<li>
							<a href="page_calendar.html">
								<i class="fa fa-calendar"></i>
								<span class="badge badge-important">
									14
								</span>
								Calendar
							</a>
						</li>
					</ul>
				</li>
				<li>
					<a href="javascript:;">
						<i class="fa fa-gift"></i>
						<span class="title">
							Extra
						</span>
						<span class="arrow ">
						</span>
					</a>
					<ul class="sub-menu">
						<li>
							<a href="extra_profile.html">
								 User Profile
							</a>
						</li>
						<li>
							<a href="extra_lock.html">
								 Lock Screen
							</a>
						</li>
						<li>
							<a href="extra_faq.html">
								 FAQ
							</a>
						</li>
						<li>
							<a href="inbox.html">
								<span class="badge badge-important">
									4
								</span>
								Inbox
							</a>
						</li>
						<li>
							<a href="extra_search.html">
								 Search Results
							</a>
						</li>
						<li>
							<a href="extra_invoice.html">
								 Invoice
							</a>
						</li>
						<li>
							<a href="extra_pricing_table.html">
								 Pricing Tables
							</a>
						</li>
						<li>
							<a href="extra_404_option1.html">
								 404 Page Option 1
							</a>
						</li>
						<li>
							<a href="extra_404_option2.html">
								 404 Page Option 2
							</a>
						</li>
						<li>
							<a href="extra_404_option3.html">
								 404 Page Option 3
							</a>
						</li>
						<li>
							<a href="extra_500_option1.html">
								 500 Page Option 1
							</a>
						</li>
						<li>
							<a href="extra_500_option2.html">
								 500 Page Option 2
							</a>
						</li>
					</ul>
				</li>
				<li>
					<a href="javascript:;">
						<i class="fa fa-folder-open"></i>
						<span class="title">
							Multi Level Menu
						</span>
						<span class="arrow ">
						</span>
					</a>
					<ul class="sub-menu">
						<li>
							<a href="javascript:;">
								<i class="fa fa-cogs"></i> Item 1
								<span class="arrow">
								</span>
							</a>
							<ul class="sub-menu">
								<li>
									<a href="javascript:;">
										<i class="fa fa-user"></i>
										Sample Link 1
										<span class="arrow">
										</span>
									</a>
									<ul class="sub-menu">
										<li>
											<a href="#">
												<i class="fa fa-remove"></i> Sample Link 1
											</a>
										</li>
										<li>
											<a href="#">
												<i class="fa fa-pencil"></i> Sample Link 1
											</a>
										</li>
										<li>
											<a href="#">
												<i class="fa fa-edit"></i> Sample Link 1
											</a>
										</li>
									</ul>
								</li>
								<li>
									<a href="#">
										<i class="fa fa-user"></i> Sample Link 1
									</a>
								</li>
								<li>
									<a href="#">
										<i class="fa fa-external-link"></i> Sample Link 2
									</a>
								</li>
								<li>
									<a href="#">
										<i class="fa fa-bell"></i> Sample Link 3
									</a>
								</li>
							</ul>
						</li>
						<li>
							<a href="javascript:;">
								<i class="fa fa-globe"></i> Item 2
								<span class="arrow">
								</span>
							</a>
							<ul class="sub-menu">
								<li>
									<a href="#">
										<i class="fa fa-user"></i> Sample Link 1
									</a>
								</li>
								<li>
									<a href="#">
										<i class="fa fa-external-link"></i> Sample Link 1
									</a>
								</li>
								<li>
									<a href="#">
										<i class="fa fa-bell"></i> Sample Link 1
									</a>
								</li>
							</ul>
						</li>
						<li>
							<a href="#">
								<i class="fa fa-folder-open"></i>
								Item 3
							</a>
						</li>
					</ul>
				</li>
				<li>
					<a href="javascript:;">
						<i class="fa fa-user"></i>
						<span class="title">
							Login Options
						</span>
						<span class="arrow ">
						</span>
					</a>
					<ul class="sub-menu">
						<li>
							<a href="login.html">
								 Login Form 1
							</a>
						</li>
						<li>
							<a href="login_soft.html">
								 Login Form 2
							</a>
						</li>
					</ul>
				</li>
				<li>
					<a href="javascript:;">
						<i class="fa fa-th"></i>
						<span class="title">
							Data Tables
						</span>
						<span class="arrow ">
						</span>
					</a>
					<ul class="sub-menu">
						<li>
							<a href="table_basic.html">
								 Basic Datatables
							</a>
						</li>
						<li>
							<a href="table_responsive.html">
								 Responsive Datatables
							</a>
						</li>
						<li>
							<a href="table_managed.html">
								 Managed Datatables
							</a>
						</li>
						<li>
							<a href="table_editable.html">
								 Editable Datatables
							</a>
						</li>
						<li>
							<a href="table_advanced.html">
								 Advanced Datatables
							</a>
						</li>
						<li>
							<a href="table_ajax.html">
								 Ajax Datatables
							</a>
						</li>
					</ul>
				</li>
				<li>
					<a href="javascript:;">
						<i class="fa fa-file-text"></i>
						<span class="title">
							Portlets
						</span>
						<span class="arrow ">
						</span>
					</a>
					<ul class="sub-menu">
						<li>
							<a href="portlet_general.html">
								 General Portlets
							</a>
						</li>
						<li>
							<a href="portlet_ajax.html">
								 Ajax Portlets
							</a>
						</li>
						<li>
							<a href="portlet_draggable.html">
								 Draggable Portlets
							</a>
						</li>
					</ul>
				</li>
				<li>
					<a href="javascript:;">
						<i class="fa fa-map-marker"></i>
						<span class="title">
							Maps
						</span>
						<span class="arrow ">
						</span>
					</a>
					<ul class="sub-menu">
						<li>
							<a href="maps_google.html">
								 Google Maps
							</a>
						</li>
						<li>
							<a href="maps_vector.html">
								 Vector Maps
							</a>
						</li>
					</ul>
				</li>
				<li class="last ">
					<a href="charts.html">
						<i class="fa fa-bar-chart-o"></i>
						<span class="title">
							Visual Charts
						</span>
					</a>
				</li>
			</ul>
			<!-- END SIDEBAR MENU -->
		</div>
	</div>
	<!-- END SIDEBAR -->
	<!-- BEGIN CONTENT -->
	<div class="page-content-wrapper">
		<div class="page-content">
			<!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
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
				<!-- /.modal-dialog -->
			</div>
			<!-- /.modal -->
			<!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->
			<!-- BEGIN STYLE CUSTOMIZER -->
			<div class="theme-panel hidden-xs hidden-sm">
				<div class="toggler">
				</div>
				<div class="toggler-close">
				</div>
				<div class="theme-options">
					<div class="theme-option theme-colors clearfix">
						<span>
							 THEME COLOR
						</span>
						<ul>
							<li class="color-black current color-default" data-style="default">
							</li>
							<li class="color-blue" data-style="blue">
							</li>
							<li class="color-brown" data-style="brown">
							</li>
							<li class="color-purple" data-style="purple">
							</li>
							<li class="color-grey" data-style="grey">
							</li>
							<li class="color-white color-light" data-style="light">
							</li>
						</ul>
					</div>
					<div class="theme-option">
						<span>
							 Layout
						</span>
						<select class="layout-option form-control input-small">
							<option value="fluid" selected="selected">Fluid</option>
							<option value="boxed">Boxed</option>
						</select>
					</div>
					<div class="theme-option">
						<span>
							 Header
						</span>
						<select class="header-option form-control input-small">
							<option value="fixed" selected="selected">Fixed</option>
							<option value="default">Default</option>
						</select>
					</div>
					<div class="theme-option">
						<span>
							 Sidebar
						</span>
						<select class="sidebar-option form-control input-small">
							<option value="fixed">Fixed</option>
							<option value="default" selected="selected">Default</option>
						</select>
					</div>
					<div class="theme-option">
						<span>
							 Sidebar Position
						</span>
						<select class="sidebar-pos-option form-control input-small">
							<option value="left" selected="selected">Left</option>
							<option value="right">Right</option>
						</select>
					</div>
					<div class="theme-option">
						<span>
							 Footer
						</span>
						<select class="footer-option form-control input-small">
							<option value="fixed">Fixed</option>
							<option value="default" selected="selected">Default</option>
						</select>
					</div>
				</div>
			</div>
			<!-- END STYLE CUSTOMIZER -->
			<!-- BEGIN PAGE HEADER-->
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
					Form Validation <small>form validation</small>
					</h3>
					<ul class="page-breadcrumb breadcrumb">
						<li class="btn-group">
							<button type="button" class="btn blue dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-delay="1000" data-close-others="true">
							<span>
								Actions
							</span>
							<i class="fa fa-angle-down"></i>
							</button>
							<ul class="dropdown-menu pull-right" role="menu">
								<li>
									<a href="#">
										Action
									</a>
								</li>
								<li>
									<a href="#">
										Another action
									</a>
								</li>
								<li>
									<a href="#">
										Something else here
									</a>
								</li>
								<li class="divider">
								</li>
								<li>
									<a href="#">
										Separated link
									</a>
								</li>
							</ul>
						</li>
						<li>
							<i class="fa fa-home"></i>
							<a href="index.html">
								Home
							</a>
							<i class="fa fa-angle-right"></i>
						</li>
						<li>
							<a href="#">
								Form Stuff
							</a>
							<i class="fa fa-angle-right"></i>
						</li>
						<li>
							<a href="#">
								Form Validation
							</a>
						</li>
					</ul>
					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
			</div>
			<!-- END PAGE HEADER-->
			<!-- BEGIN PAGE CONTENT-->
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN VALIDATION STATES-->
					<div class="portlet box blue">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-reorder"></i>Validation States
							</div>
							<div class="tools">
								<a href="javascript:;" class="collapse">
								</a>
								<a href="#portlet-config" data-toggle="modal" class="config">
								</a>
								<a href="javascript:;" class="reload">
								</a>
								<a href="javascript:;" class="remove">
								</a>
							</div>
						</div>
						<div class="portlet-body form">
							<!-- BEGIN FORM-->
							<form action="#" class="form-horizontal">
								<div class="form-body">
									<h3 class="form-section">Basic validation States</h3>
									<div class="form-group has-warning">
										<label class="control-label col-md-3" for="inputWarning">Input with warning</label>
										<div class="col-md-4">
											<input type="text" class="form-control" id="inputWarning"/>
											<span class="help-block">
												 Something may have gone wrong
											</span>
										</div>
									</div>
									<div class="form-group has-error">
										<label class="control-label col-md-3" for="inputError">Input with error</label>
										<div class="col-md-4">
											<input type="text" class="form-control" id="inputError"/>
											<span class="help-block">
												 Please correct the error
											</span>
										</div>
									</div>
									<div class="form-group has-success">
										<label class="control-label col-md-3" for="inputSuccess">Input with success</label>
										<div class="col-md-4">
											<input type="text" class="form-control" id="inputSuccess"/>
										</div>
									</div>
									<h3 class="form-section">Validation States With Icons</h3>
									<div class="form-group has-warning">
										<label class="control-label col-md-3">Input with warning</label>
										<div class="col-md-4">
											<div class="input-icon right">
												<i class="fa fa-exclamation" data-original-title="please write a valid email"></i>
												<input type="text" class="form-control"/>
											</div>
										</div>
									</div>
									<div class="form-group has-error">
										<label class="control-label col-md-3">Input with error</label>
										<div class="col-md-4">
											<div class="input-icon right">
												<i class="fa fa-warning" data-original-title="please write a valid email"></i>
												<input type="text" class="form-control"/>
											</div>
										</div>
									</div>
									<div class="form-group has-success">
										<label class="control-label col-md-3">Input with success</label>
										<div class="col-md-4">
											<div class="input-icon right">
												<i class="fa fa-check" data-original-title="success input!"></i>
												<input type="text" class="form-control"/>
											</div>
										</div>
									</div>
								</div>
								<div class="form-actions fluid">
									<div class="col-md-offset-3 col-md-9">
										<button type="submit" class="btn green">Submit</button>
										<button type="button" class="btn default">Cancel</button>
									</div>
								</div>
							</form>
							<!-- END FORM-->
						</div>
					</div>
					<!-- END VALIDATION STATES-->
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN VALIDATION STATES-->
					<div class="portlet box purple">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-reorder"></i>Basic Validation
							</div>
							<div class="tools">
								<a href="javascript:;" class="collapse">
								</a>
								<a href="#portlet-config" data-toggle="modal" class="config">
								</a>
								<a href="javascript:;" class="reload">
								</a>
								<a href="javascript:;" class="remove">
								</a>
							</div>
						</div>
						<div class="portlet-body form">
							<!-- BEGIN FORM-->
							<form action="#" id="form_sample_1" class="form-horizontal">
								<div class="form-body">
									<div class="alert alert-danger display-hide">
										<button class="close" data-close="alert"></button>
										You have some form errors. Please check below.
									</div>
									<div class="alert alert-success display-hide">
										<button class="close" data-close="alert"></button>
										Your form validation is successful!
									</div>
									<div class="form-group">
										<label class="control-label col-md-3">Name
										<span class="required">
											 *
										</span>
										</label>
										<div class="col-md-4">
											<input type="text" name="name" data-required="1" class="form-control"/>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3">Email
										<span class="required">
											 *
										</span>
										</label>
										<div class="col-md-4">
											<input name="email" type="text" class="form-control"/>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3">URL
										<span class="required">
											 *
										</span>
										</label>
										<div class="col-md-4">
											<input name="url" type="text" class="form-control"/>
											<span class="help-block">
												 e.g: http://www.demo.com or http://demo.com
											</span>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3">Number
										<span class="required">
											 *
										</span>
										</label>
										<div class="col-md-4">
											<input name="number" type="text" class="form-control"/>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3">Digits
										<span class="required">
											 *
										</span>
										</label>
										<div class="col-md-4">
											<input name="digits" type="text" class="form-control"/>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3">Credit Card
										<span class="required">
											 *
										</span>
										</label>
										<div class="col-md-4">
											<input name="creditcard" type="text" class="form-control"/>
											<span class="help-block">
												 e.g: 5500 0000 0000 0004
											</span>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3">Occupation&nbsp;&nbsp;</label>
										<div class="col-md-4">
											<input name="occupation" type="text" class="form-control"/>
											<span class="help-block">
												 optional field
											</span>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3">Category
										<span class="required">
											 *
										</span>
										</label>
										<div class="col-md-4">
											<select class="form-control" name="category" class="select2me">
												<option value="">Select...</option>
												<option value="Category 1">Category 1</option>
												<option value="Category 2">Category 2</option>
												<option value="Category 3">Category 5</option>
												<option value="Category 4">Category 4</option>
											</select>
										</div>
									</div>
								</div>
								<div class="form-actions fluid">
									<div class="col-md-offset-3 col-md-9">
										<button type="submit" class="btn green">Submit</button>
										<button type="button" class="btn default">Cancel</button>
									</div>
								</div>
							</form>
							<!-- END FORM-->
						</div>
					</div>
					<!-- END VALIDATION STATES-->
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN VALIDATION STATES-->
					<div class="portlet box yellow">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-reorder"></i>Validation Using Icons
							</div>
							<div class="tools">
								<a href="javascript:;" class="collapse">
								</a>
								<a href="#portlet-config" data-toggle="modal" class="config">
								</a>
								<a href="javascript:;" class="reload">
								</a>
								<a href="javascript:;" class="remove">
								</a>
							</div>
						</div>
						<div class="portlet-body form">
							<!-- BEGIN FORM-->
							<form action="#" id="form_sample_2" class="form-horizontal">
								<div class="form-body">
									<div class="alert alert-danger display-hide">
										<button class="close" data-close="alert"></button>
										You have some form errors. Please check below.
									</div>
									<div class="alert alert-success display-hide">
										<button class="close" data-close="alert"></button>
										Your form validation is successful!
									</div>
									<div class="form-group">
										<label class="control-label col-md-3">Name
										<span class="required">
											 *
										</span>
										</label>
										<div class="col-md-4">
											<div class="input-icon right">
												<i class="fa"></i>
												<input type="text" class="form-control" name="name"/>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3">Email
										<span class="required">
											 *
										</span>
										</label>
										<div class="col-md-4">
											<div class="input-icon right">
												<i class="fa"></i>
												<input type="text" class="form-control" name="email"/>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3">URL
										<span class="required">
											 *
										</span>
										</label>
										<div class="col-md-4">
											<div class="input-icon right">
												<i class="fa"></i>
												<input type="text" class="form-control" name="url"/>
											</div>
											<span class="help-block">
												 e.g: http://www.demo.com or http://demo.com
											</span>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3">Number
										<span class="required">
											 *
										</span>
										</label>
										<div class="col-md-4">
											<div class="input-icon right">
												<i class="fa"></i>
												<input type="text" class="form-control" name="number"/>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3">Digits
										<span class="required">
											 *
										</span>
										</label>
										<div class="col-md-4">
											<div class="input-icon right">
												<i class="fa"></i>
												<input type="text" class="form-control" name="digits"/>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3">Credit Card
										<span class="required">
											 *
										</span>
										</label>
										<div class="col-md-4">
											<div class="input-icon right">
												<i class="fa"></i>
												<input type="text" class="form-control" name="creditcard"/>
											</div>
											<span class="help-block">
												 e.g: 5500 0000 0000 0004
											</span>
										</div>
									</div>
								</div>
								<div class="form-actions fluid">
									<div class="col-md-offset-3 col-md-9">
										<button type="submit" class="btn green">Submit</button>
										<button type="button" class="btn default">Cancel</button>
									</div>
								</div>
							</form>
							<!-- END FORM-->
						</div>
					</div>
					<!-- END VALIDATION STATES-->
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN VALIDATION STATES-->
					<div class="portlet box green">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-reorder"></i>Advance Validation
							</div>
							<div class="tools">
								<a href="javascript:;" class="collapse">
								</a>
								<a href="#portlet-config" data-toggle="modal" class="config">
								</a>
								<a href="javascript:;" class="reload">
								</a>
								<a href="javascript:;" class="remove">
								</a>
							</div>
						</div>
						<div class="portlet-body form">
							<!-- BEGIN FORM-->
							<form action="#" id="form_sample_3" class="form-horizontal">
								<div class="form-body">
									<h3 class="form-section">Advance validation. <small>Custom radio buttons, checkboxes and Select2 dropdowns</small></h3>
									<div class="alert alert-danger display-hide">
										<button class="close" data-close="alert"></button>
										You have some form errors. Please check below.
									</div>
									<div class="alert alert-success display-hide">
										<button class="close" data-close="alert"></button>
										Your form validation is successful!
									</div>
									<div class="form-group">
										<label class="control-label col-md-3">Name
										<span class="required">
											 *
										</span>
										</label>
										<div class="col-md-4">
											<input type="text" name="name" data-required="1" class="form-control"/>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Email Address
										<span class="required">
											 *
										</span>
										</label>
										<div class="col-md-4">
											<div class="input-group">
												<span class="input-group-addon">
													<i class="fa fa-envelope"></i>
												</span>
												<input type="email" name="email" class="form-control" placeholder="Email Address">
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3">Occupation&nbsp;&nbsp;</label>
										<div class="col-md-4">
											<input name="occupation" type="text" class="form-control"/>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3">Category
										<span class="required">
											 *
										</span>
										</label>
										<div class="col-md-4">
											<select class="form-control" name="category">
												<option value="">Select...</option>
												<option value="Category 1">Category 1</option>
												<option value="Category 2">Category 2</option>
												<option value="Category 3">Category 5</option>
												<option value="Category 4">Category 4</option>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3">Select2 Dropdown
										<span class="required">
											 *
										</span>
										</label>
										<div class="col-md-4">
											<select id="form_2_select2" class="form-control select2me" name="options2">
												<option value="">Select...</option>
												<option value="Option 1">Option 1</option>
												<option value="Option 2">Option 2</option>
												<option value="Option 3">Option 3</option>
												<option value="Option 4">Option 4</option>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3">Membership
										<span class="required">
											 *
										</span>
										</label>
										<div class="col-md-4">
											<div class="radio-list" data-error-container="#form_2_membership_error">
												<label>
												<input type="radio" name="membership" value="1"/>
												Fee </label>
												<label>
												<input type="radio" name="membership" value="2"/>
												Professional </label>
											</div>
											<div id="form_2_membership_error">
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3">Services
										<span class="required">
											 *
										</span>
										</label>
										<div class="col-md-4">
											<div class="checkbox-list" data-error-container="#form_2_services_error">
												<label>
												<input type="checkbox" value="1" name="service"/> Service 1 </label>
												<label>
												<input type="checkbox" value="2" name="service"/> Service 2 </label>
												<label>
												<input type="checkbox" value="3" name="service"/> Service 3 </label>
											</div>
											<span class="help-block">
												 (select at least two)
											</span>
											<div id="form_2_services_error">
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3">Markdown</label>
										<div class="col-md-9">
											<textarea name="markdown" data-provide="markdown" rows="10" data-error-container="#editor_error"></textarea>
											<div id="editor_error">
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3">WYSIHTML5 Editor
										<span class="required">
											 *
										</span>
										</label>
										<div class="col-md-9">
											<textarea class="wysihtml5 form-control" rows="6" name="editor1" data-error-container="#editor1_error"></textarea>
											<div id="editor1_error">
											</div>
										</div>
									</div>
									<div class="form-group last">
										<label class="control-label col-md-3">CKEditor
										<span class="required">
											 *
										</span>
										</label>
										<div class="col-md-9">
											<textarea class="ckeditor form-control" name="editor2" rows="6" data-error-container="#editor2_error"></textarea>
											<div id="editor2_error">
											</div>
										</div>
									</div>
								</div>
								<div class="form-actions fluid">
									<div class="col-md-offset-3 col-md-9">
										<button type="submit" class="btn green">Submit</button>
										<button type="button" class="btn default">Cancel</button>
									</div>
								</div>
							</form>
							<!-- END FORM-->
						</div>
						<!-- END VALIDATION STATES-->
					</div>
				</div>
			</div>
			<!-- END PAGE CONTENT-->
		</div>
	</div>
	<!-- END CONTENT -->
</div>
<!-- END CONTAINER -->
<!-- BEGIN FOOTER -->
<div class="footer">
	<div class="footer-inner">
		 2014 &copy; Metronic by keenthemes.
	</div>
	<div class="footer-tools">
		<span class="go-top">
			<i class="fa fa-angle-up"></i>
		</span>
	</div>
</div>
<!-- END FOOTER -->
<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<!-- BEGIN CORE PLUGINS -->
<!--[if lt IE 9]>
<script src="assets/plugins/respond.min.js"></script>
<script src="assets/plugins/excanvas.min.js"></script> 
<![endif]-->
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
<script type="text/javascript" src="assets/plugins/jquery-validation/dist/jquery.validate.min.js"></script>
<script type="text/javascript" src="assets/plugins/jquery-validation/dist/additional-methods.min.js"></script>
<script type="text/javascript" src="assets/plugins/select2/select2.min.js"></script>
<script type="text/javascript" src="assets/plugins/bootstrap-wysihtml5/wysihtml5-0.3.0.js"></script>
<script type="text/javascript" src="assets/plugins/bootstrap-wysihtml5/bootstrap-wysihtml5.js"></script>
<script type="text/javascript" src="assets/plugins/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="assets/plugins/bootstrap-markdown/js/bootstrap-markdown.js"></script>
<script type="text/javascript" src="assets/plugins/bootstrap-markdown/lib/markdown.js"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL STYLES -->
<script src="assets/scripts/core/app.js"></script>
<script src="assets/scripts/custom/form-validation.js"></script>
<!-- END PAGE LEVEL STYLES -->
<script>
jQuery(document).ready(function() {   
   // initiate layout and plugins
   App.init();
   FormValidation.init();
});
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>