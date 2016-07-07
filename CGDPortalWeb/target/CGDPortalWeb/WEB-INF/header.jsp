<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.Locale"%>
<%@page import="com.locus.jlo.web.constant.JLOWebConstant"%>
<%@page import="com.locus.common.utils.StringUtils"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page import="org.springframework.web.servlet.support.RequestContext"%>
<%@page import="com.locus.jlo.web.bean.dto.ConsultingDTO"%>
<%

	ConsultingDTO consultObj = (ConsultingDTO) request.getSession().getAttribute("CONSULTING_OBJECT");

	String consultNumber = null;
	String consultStatusName = null;
	String consultStatusCd = null;
	String consultCustName = null;
	String consultCustId = null;
	String consultCustType = null;	
	String consultContactName = null;
	String consultContactID = null;
	String consultChannelCd = null;
	String consultChannelName = null;
	
	Locale locale = (Locale)request.getSession().getAttribute("CURRENT_LANG");
	
	String lang = null;
	if (locale!=null) {
		lang = locale.getLanguage();
	} else {
		lang = "en";
	}
	
	if(consultObj != null){				
		 consultStatusName = consultObj.getStatusName();
		 consultStatusCd = consultObj.getStatusCd();	
		 consultNumber = consultObj.getConsultingNumber();			 
		 consultCustType = consultObj.getCustType();
		 consultCustName = consultObj.getCustName();
		 consultCustId = consultObj.getCustId();
		 consultContactName = consultObj.getContactName();
		 consultContactID = consultObj.getContactId();
		 consultChannelCd= consultObj.getChannelCd();
		 consultChannelName = consultObj.getChannelName();
	}
%>
<script type="text/javascript">

	var customerUrl = "/customerMain.htm"; 
	var toCustomerPage = true;	
	
	var consultingNumber = "<%=consultNumber%>";
	var consultingStatusCd = "<%=consultStatusCd%>";
	var consultingCustName = "<%=consultCustName%>";
	var consultingCustId = "<%=consultCustId%>";
	var consultingCustType = "<%=consultCustType%>";
	var consultingContactName = "<%=consultContactName%>";
	var consultingContactId = "<%=consultContactID%>";
	var consultingChannelCd = "<%=consultChannelCd%>";
	var consultingChannelName = "<%=consultChannelName%>";

	var customerIdHeader = "";
	var popupHeaderKb = '<spring:message code="knowledgeBase.popup.info.title" />';

	var my_panel = undefined;
	jQuery(document).ready(function() {

				//reloadTaskProgress();

				$("#consultingWrapUpBtn").on("click",function() {

						toCustomerPage = true;
						var wrapupParam = "CONSULTING_WRAPUP";
						loadContentIntoModalWithParameter($("#modal_content_div_consulting"), "openDialogConsulting.htm", "consultingDialog", "model.header.consulting",
								"callbackHeaderConsulting", wrapupParam, "consultingDialog");

						//loadContentIntoModal($("#modal_content_div_consulting"), "openModalDialog.htm", "consultingDialog", "model.header.consulting", "callbackHeaderConsulting");

				});

				$("#consultingStartBtn").on("click", function() {
					insertConsultingHeader();
				});

	});

	function openConsultingModalStartButton() {
		$("#consultingStartBtn").click();
	}

	function openConsultingModalWrapUpButton() {
		$("#consultingWrapUpBtn").click();
	}

	function goCustomerDetail(custCode) {

		var jsonObj = getJsonData('customerDetailByCustCode.htm', 'custCode=' + custCode, 'POST');
		if (jsonObj.resultCode == '0') {

			var custId = jsonObj.model.custId;
			var custType = jsonObj.model.custType;

			postAction('/customerDetail.htm?id=' + custId + '&type=' + custType);

			return custId;
		} else {
			return '';
		}
	}

	function insertConsultingHeader() {

		toCustomerPage = true;
		var param = "";
		console.log('customerIdHeader : ' + customerIdHeader);
		if (!empty(customerIdHeader)) {
			param = "custId=" + customerIdHeader;
		}

		loadJsonData("insertConsulting.htm", param, "POST", loadDialogConsulting);
	}

	/*
	 * for check consulting is availible
	 */
	function checkConsultingIsAvailible() {

		console.log("checkConsultingIsAvailible StatusCd : " + consultingStatusCd);
		console.log("toCustomerPage : " + toCustomerPage);
		
		//Check Status Consulting Inprogress
		if (consultingStatusCd === "01") {
			// Not Available
			return false;
		} else {
			// Available
			if (toCustomerPage) {
				return true;
			} else {
				return false;
			}

		}

	}

	function setCustomerToConsultingHeader(customerId) {
		customerIdHeader = customerId;

	}
	function loadDialogConsulting(data) {

		if (data) {

			if (data != null) {

				$("#consulting_status").removeClass("icon-green").addClass("icon-blue").text(data.statusName);
				$("#consultingStatusCd").text(data.statusName);

				if (typeof setConsultingData != 'undefined') {
					setConsultingData(data);
				}
			}

			$("#consultingWrapUpBtn").removeClass("disabled");
			$("#consultingStartBtn").addClass("disabled");

			var startParam = "CONSULTING_START";
			loadContentIntoModalWithParameter($("#modal_content_div_consulting"), "openDialogConsulting.htm",
					"consultingDialog", "model.header.consulting", "callbackHeaderConsulting",
					startParam,	"consultingDialog");

			//loadContentIntoModal($("#modal_content_div_consulting"), "openModalDialog.htm", "consultingDialog", "model.header.consulting", "callbackHeaderConsulting");

		} else {
			alertMessage("<spring:message code="model.header.consulting" />", "<spring:message code="lbl.save.fail" />");
		}

	}

	function callbackHeaderConsulting(data) {

		if (typeof setConsultingData != 'undefined') {
			setConsultingData(data);
		}

		if (data) {

			if (data.statusCd == "02") {
				$("#consulting_status").removeClass("icon-blue").addClass("icon-green").text("<spring:message code="consulting.header.status.available" />");
				$("#consultingStatusCd").text("<spring:message code="consulting.header.status.available" />");
				$("#consultingWrapUpBtn").addClass("disabled");
				$("#consultingStartBtn").removeClass("disabled");
				$("#consultingCallingNumber").text("");
				$("#consultingCustName").text("");
				$("#consultingChannelName").text("");

			} else {
				$("#consulting_status").removeClass("icon-green").addClass("icon-blue").text(data.statusName);
				$("#consultingStatusCd").text(data.statusName);
				$("#consultingCallingNumber").text(data.callingNumber == null ? "" : data.callingNumber);
				$("#consultingCustName").text(data.custName == null ? "" : data.custName);
				$("#consultingChannelName").text(data.channelName == null ? "" : data.channelName);

			}

			$("#consultingDialog").modal("hide");

		} else {
			alertMessage("<spring:message code="model.header.consulting" />", "<spring:message code="lbl.save.fail" />");
		}

	}

	function setConsultingData(objData) {

		console.log(" header setConsultingData");

		if (!empty(objData)) {
			
			console.log("CONSULTING_NUMBER 	: " + objData.consultingNumber);
			console.log("STATUSCD 	: " + objData.statusCd);
			console.log("CUSTID   	: " + objData.custId);
			console.log("CUSTNAME 	: " + objData.custName);
			console.log("CUSTTYPE 	: " + objData.custType);
			console.log("CONTACTID	: " + objData.contactId);
			console.log("CONTACTNAME: " + objData.contactName);
			console.log("CHANNELCD  : " + objData.channelCd);
			console.log("CHANNELNAME: " + objData.channelName);
			
			consultingNumber =  objData.consultingNumber;
			consultingStatusCd = objData.statusCd;
			consultingCustId = objData.custId;
			consultingCustName = objData.custName;
			consultingCustType = objData.custType;
			consultingContactId = objData.contactId;
			consultingContactName = objData.contactName;

			consultingChannelCd = objData.channelCd;
			consultingChannelName = objData.channelName;
		}

	}
</script>

<!-- BEGIN HEADER -->
<div class="header navbar navbar-fixed-top">
	<!-- BEGIN TOP NAVIGATION BAR -->
	<div class="page-header-inner">
		<!-- BEGIN LOGO -->

		<div class="page-logo">
<!-- 			<a class="navbar-brand" href="home.htm"> <img src="assets/img/logo22x2.png" alt="logo" class="img-responsive" /> -->
			<a class="navbar-brand" href="home.htm"> <img src="assets/img/logo_crm_plus.png" alt="logo" class="img-responsive" />
			</a>
		</div>
		<!-- END LOGO -->

		<!-- BEGIN RESPONSIVE MENU TOGGLER -->
		<a href="javascript:;" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse"> <img src="assets/img/menu-toggler.png" alt="" />
		</a>
		<!-- END RESPONSIVE MENU TOGGLER -->

		<!-- BEGIN TOP NAVIGATION MENU -->
		<div class="top-menu">
			<ul class="nav navbar-nav pull-right">
				<%
					String currentUrl = null;
					if (request.getQueryString() != null && request.getQueryString().length() > 0) {
						String queryString = request.getQueryString();
						if (queryString.indexOf("lang=") != -1) {
							currentUrl = "./home.htm?" + queryString.substring(0, queryString.indexOf("lang=") + 5);
						} else {
							currentUrl = "./home.htm?" + queryString + "&lang=";
						}
					} else {
						currentUrl = "./home.htm?lang=";
					}
				%>
				<!-- BEGIN LANGUAGE DROPDOWN -->
				<li class="dropdown language"><a data-close-others="true" data-hover="dropdown" data-toggle="dropdown" class="dropdown-toggle" href="#"> <img src="assets/img/flags/<%=lang%>.png" alt="">
						<span class="username"> <%=lang.toUpperCase()%>
					</span> <i class="fa fa-angle-down"></i>
				</a>
					<ul class="dropdown-menu">
						<li><a href="<%=currentUrl%>en"> <img src="assets/img/flags/us.png" alt=""> EN
						</a></li>
						<li><a href="<%=currentUrl%>th"> <img src="assets/img/flags/th.png" alt=""> TH
						</a></li>
					</ul></li>
				<!-- END LANGUAGE DROPDOWN -->
				<!-- BEGIN USER LOGIN DROPDOWN -->
				<li class="dropdown user"><a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true"> <span class="username"> <font
							style="font-style: bold"><spring:message code="header.loginas" /></font> ${USER_PROFILE.firstName} ${USER_PROFILE.lastName}
					</span> <img alt="" src="getImg.htm?src=${USER_PROFILE.userPic }" width="29" height="29" /> <i class="fa fa-angle-down"></i>
				</a>
					<ul class="dropdown-menu">
						<!-- <li>
							<a href="home.htm">
								<i class="fa fa-user"></i> Home
							</a>
						</li>
						-->
						<%-- <%if(lang.equals("th")){ %>
						<li>
							<a href="<%=currentUrl %>en">
								<img src="assets/img/flags/us.png" alt=""> English
							</a>
						</li>
						<%}else{ %>
						<li>
							<a href="<%=currentUrl %>th">
								<img src="assets/img/flags/th.png" alt=""> ภาษาไทย
							</a>
						</li>
						<%} %> --%>

						<li><a href="changePassword.htm"> <i class="fa fa-key"></i> <spring:message code="changePassword.title" />
						</a></li>
						<li class="divider"></li>
						<li><a href="logout.htm"> <i class="fa fa-sign-out"></i> <spring:message code="login.logout" />
						</a></li>
					</ul></li>


			</ul>
		</div>
		<div class="hor-menu" style="float: right;">
			<ul class="nav navbar-nav hidden-xs">
				<li id="consulting-li"><a data-toggle="dropdown" data-hover="dropdown" data-close-others="true" href=""> <i class="fa  fa-comments-o"></i> <span class="hidden-xs hidden-sm"><spring:message
								code="consulting.header.text" /> : </span> <c:if test="${CONSULTING_OBJECT==null }">
							<span id="consulting_status" class="icon-green"><spring:message code="consulting.header.status.available" /></span>
						</c:if> <c:if test="${CONSULTING_OBJECT!=null }">
							<span id="consulting_status" class="icon-blue">${CONSULTING_OBJECT.statusName}</span>
						</c:if> <i class="fa fa-angle-down"></i>
				</a>
					<ul class="dropdown-menu">
						<li><a href="#"> <span class="title-li"><spring:message code="consulting.status" /></span>: <c:if test="${CONSULTING_OBJECT==null }">
									<span id="consultingStatusCd" class="value-li"><spring:message code="consulting.header.status.available" /></span>
								</c:if> <c:if test="${CONSULTING_OBJECT!=null }">
									<span id="consultingStatusCd" class="value-li">${CONSULTING_OBJECT.statusName}</span>
								</c:if>

						</a></li>
						<li><a href="#"> <span class="title-li"><spring:message code="consulting.serviceTo" /></span>: <span id="consultingCustName" class="value-li"> <c:if
										test="${CONSULTING_OBJECT!=null }">${CONSULTING_OBJECT.custName}</c:if>
							</span>
						</a></li>
						<li><a href="#"> <span class="title-li"><spring:message code="consulting.channel" /></span>: <span id="consultingChannelName" class="value-li"> <c:if
										test="${CONSULTING_OBJECT!=null }">${CONSULTING_OBJECT.channelName}</c:if>
							</span>
						</a></li>
						<li><a href="#"> <span class="title-li"><spring:message code="consulting.contactInfo" /></span>: <span id="consultingCallingNumber" class="value-li"> <c:if
										test="${CONSULTING_OBJECT!=null }">${CONSULTING_OBJECT.callingNumber}</c:if>
							</span>
						</a></li>
						<li class="btn-group"><a href="#" style="text-align: right;">
								<div id="consultingStartBtn" class="btn blue <c:if test="${CONSULTING_OBJECT!=null }">disabled</c:if>" data-target="#consultingDialog" data-toggle="modal">
									<spring:message code="consulting.header.start" />
								</div>
								<div id="consultingWrapUpBtn" class="btn red <c:if test="${CONSULTING_OBJECT==null }">disabled</c:if>" data-target="#consultingDialog" data-toggle="modal">
									<spring:message code="consulting.header.wrapUp" />
								</div>
						</a></li>
					</ul></li>
			</ul>
		</div>

		<!-- END TOP NAVIGATION MENU -->

	</div>
	<!-- END TOP NAVIGATION BAR -->
	<!-- Mask Loading Customize  -->
	<!-- <div id="maskLoadingGlobal" style="position: absolute; width: 100%; display:none"></div> -->

</div>
<!-- END HEADER -->

<!-- Consulting DIALOG -->
<div class="modal fade" id="consultingDialog" role="basic" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div id="modal_content_div_consulting"></div>
		</div>
	</div>
</div>
<div class="modal fade" id="consultingPopupDialog" role="basic" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div id="modal_content_div_Consulting_popup"></div>
		</div>
	</div>
</div>
<div class="modal fade" id="createContactSubPopupDialog" role="basic" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div id="modal_content_div_createContect_popup"></div>
		</div>
	</div>
</div>
<!-- END Consulting DIALOG -->