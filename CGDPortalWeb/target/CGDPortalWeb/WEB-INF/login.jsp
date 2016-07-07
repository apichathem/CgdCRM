<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!-- BEGIN LOGO -->
<div class="logo">
	  <a href="${pageContext.request.contextPath}/login.htm">
<!-- 		<img src="assets/img/locus_1.png" alt=""/> -->
		<img src="assets/img/locus_crm_plus_1.png" alt=""/>
	</a>  
	<div id="status"></div>
</div>
<!-- END LOGO -->
<!-- BEGIN LOGIN -->
<div class="content">
	<!-- BEGIN LOGIN FORM -->
	<form id="login-form" class="login-form" name='f' action='${pageContext.request.contextPath}/j_spring_security_check' method='POST' accept-charset="UTF-8">
		
		<h3 class="form-title"><spring:message code="login.signinto"/> : กรมบัญชีกลาง</h3>
		<h4 class="form-title">The Comptroller General's Department (CGD)</h4>
		
		 <!--  <div id="login-fb-picture-box">		
				<img class="page-lock-img" src="assets/img/profile/profile.jpg" alt="">
			</div>   -->
			
		<div class="alert alert-danger display-hide">
			<button class="close" data-close="alert"></button>
			<span>
				 กรุณากรอกข้อมูลชื่อผู้ใช้งาน และรหัสผ่าน
			</span>
		</div>
		<div class="form-group">
			<!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
			
			
			<div id="login-fb-picture-box">		
				<!-- <img id="facebookPicture" class="page-lock-img" src="" alt=""> -->
			</div>
			
			<label class="control-label visible-ie8 visible-ie9"><spring:message code="login.username"/></label>
			<div class="input-icon">
				<i class="fa fa-user"></i>
				<input class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="<spring:message code="login.username"/>" name="j_username" id="j_username" data-rule-required="true" data-msg-required="กรุณากรอกชื่อผู้ใช้งาน" minlength="5" maxlength="25"/>
			</div>
		</div>
		<div id="login-password-box" class="form-group">
			<label class="control-label visible-ie8 visible-ie9"><spring:message code="login.password"/></label>
			<div class="input-icon">
				<i class="fa fa-lock"></i>
				<input class="form-control placeholder-no-fix" type="password" autocomplete="off" placeholder="<spring:message code="login.password"/>" name="j_password" id="j_password" data-rule-required="true" data-msg-required="กรุณากรอกรหัสผ่าน" minlength="6" maxlength="25"/>
			</div>
		</div>
		<div id="login-cti-ad-box" class="form-group">
		<%-- 	<div class="input-group">
				<span class="input-group-addon">
					<input type="checkbox" name="activeDirectoryFlag" id="activeDirectoryFlag"/>
					<spring:message code="login.ad"/>
					
					<input type="checkbox" name="j_cti_mode"/>
					<spring:message code="login.cti"/>
					
				</span>
				<!-- <span class="input-group-addon">
					
				</span> -->
				<input type="text" class="form-control" placeholder="<spring:message code="login.station"/>" name="j_station_id">
				<input type='hidden' id="j_loginMethod" name='j_loginMethod'  value=""> 
			</div>		--%>
		</div> 
		
		  <div id="login-normal-box" class="form-actions">
			<div class="form-group">
				<%-- <button type="button"  id="forget-password" class="btn grey pull-left">
					<spring:message code="login.forgetPassword" /> <i class="fa fa-question-circle m-icon-black"></i>
				</button> --%>
				<button type="submit" class="btn blue pull-right">
					<spring:message code="login.login"/> <i class="m-icon-swapright m-icon-white"></i>
				</button>
			</div>
		</div>  
		
		<div id="login-normal-box" class="form-group">
			<%-- <div class="">
				<button type="button" class="btn blue btn-facebook" onClick="fbAuth();" ><i class="fa fa-facebook"></i> <spring:message code="login.fb"/></button>
			</div>
		 	--%>
		 </div>
		
		<div id="login-fb-box">
			<!-- <input id="btnFbSubmit" type="submit" class="btn btn-info pull-left"> 
			<input id="btnFblogout" type="button" class="btn btn-warning pull-right" value="Cancel" onclick="fblogout();"> 
			 --><br/><br/>
		</div>
		
		<div id="fb-root"></div>
		
		
	</form>
	<!-- END LOGIN FORM -->
	<!-- BEGIN FORGOT PASSWORD FORM -->
	<form class="forget-form" action="service-rest/adminForgetPassword.htm" id="frmforgetPassword" method="post">
		<!-- <input type="hidden" name="deviceid" value="DESKTOP" /> -->
		<h3><spring:message code="login.forgetPassword" /> ?</h3>
		<p>
			<spring:message code="login.forgetPasswordMessageInput" />
		</p>
		<div class="form-group">
			<div class="input-icon">
				<i class="fa fa-user"></i>
				<input class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="<spring:message code="login.username"/>" name="loginid" data-rule-required="true" data-msg-required="กรุณากรอกชื่อผู้ใช้งาน" minlength="5" maxlength="25"/>
			</div>
		</div>
		 <div class="form-group">
			<div class="input-icon">
				<i class="fa fa-envelope"></i>
				<input class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="<spring:message code="userManege.detail.agent.email"/>" email=true name="email" data-rule-required="true" data-msg-required="กรุณากรอกที่อยู่อีเมล" />
			</div>
		</div>
		<div class="form-actions">
			<button type="button" id="back-btn" class="btn">
			<i class="m-icon-swapleft"></i> <spring:message code="login.back" /> </button>
			<button type="button"  onclick='sendForgetpassword()' class="btn blue pull-right">
			<spring:message code="login.send" /> <i class="m-icon-swapright m-icon-white"></i>
			</button>
		</div>
	</form>
	<!-- END FORGOT PASSWORD FORM -->
	
</div>
<!-- END LOGIN -->
<!-- BEGIN COPYRIGHT -->
<div class="copyright">
	<b>Copyright &copy;</b>2016 Locus Telecommunication Inc., Ltd.<br/>
	Last Deploy :05/07/2016 18:00 
</div>

		
<!-- END COPYRIGHT -->

<script type="text/javascript">

$(document).ready(function(){
	$("#j_loginMethod").val("");
	$("#login-normal-box").show();
	$("#login-password-box").show();
	$("#login-fb-box").hide();
	$("#login-fb-picture-box").hide();
	$("#login-cti-ad-box").show();
});

function sendForgetpassword(){
	ajaxSubmitForm($("#frmforgetPassword"),function(data){
		if(data!=null){
			if(data.status=="200"){
				alertMessage("<spring:message code="login.forgetPassword" />",data.message);
			}else{
				alertMessage("<spring:message code="login.forgetPassword" />",data.message);
			}
		}
		
		return false;
	});
}


  // This is called with the results from from FB.getLoginStatus().
  function statusChangeCallback(response) {
    console.log('statusChangeCallback');
    console.log(response);
    // The response object is returned with a status field that lets the
    // app know the current login status of the person.
    // Full docs on the response object can be found in the documentation
    // for FB.getLoginStatus().
    if (response.status === 'connected') {
      // Logged into your app and Facebook.
      fbLogin();
    } /* else if (response.status === 'not_authorized') {
      // The person is logged into Facebook, but not your app.
      document.getElementById('status').innerHTML = 'Please log ' +
        'into this app.';
    }  */else {
      // The person is not logged into Facebook, so we're not sure if
      // they are logged into this app or not.
      document.getElementById('status').innerHTML = '';
      
      $("#login-normal-box").show();
      $("#login-password-box").show();
  	  $("#login-fb-box").hide();
	  $("#login-fb-picture-box").hide();
	  $("#login-cti-ad-box").show();
	  $("#j_loginMethod").val("");
    }
  }

  // This function is called when someone finishes with the Login
  // Button.  See the onlogin handler attached to it in the sample
  // code below.
  function checkLoginState() {
    FB.getLoginStatus(function(response) {
      statusChangeCallback(response);
    });
  }

  window.fbAsyncInit = function() {
  FB.init({
    appId      : '181570185197526',
    cookie     : true,  // enable cookies to allow the server to access 
                        // the session
    xfbml      : true,  // parse social plugins on this page
    version    : 'v2.0' // use version 2.0
  });

  // Now that we've initialized the JavaScript SDK, we call 
  // FB.getLoginStatus().  This function gets the state of the
  // person visiting this page and can return one of three states to
  // the callback you provide.  They can be:
  //
  // 1. Logged into your app ('connected')
  // 2. Logged into Facebook, but not your app ('not_authorized')
  // 3. Not logged into Facebook and can't tell if they are logged into
  //    your app or not.
  //
  // These three cases are handled in the callback function.

  FB.getLoginStatus(function(response) {
    statusChangeCallback(response);
  });

  };

  // Load the SDK asynchronously
  (function(d, s, id) {
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) return;
    js = d.createElement(s); js.id = id;
    js.src = "//connect.facebook.net/en_US/sdk.js";
    fjs.parentNode.insertBefore(js, fjs);
  }(document, 'script', 'facebook-jssdk'));
  
  function fbAuth(){
	    FB.login(function(response) {
	
	        if (response.authResponse) {
	            console.log('Welcome!  Fetching your information.... ');
	            //console.log(response); // dump complete info
	            access_token = response.authResponse.accessToken; //get access token
	            user_id = response.authResponse.userID; //get FB UID
	
	            FB.api('/me', function(response) {
	                user_email = response.email; //get user email
	          // you can store this data into your database             
	            });
	
	        } else {
	            //user hit cancel button
	            console.log('User cancelled login or did not fully authorize.');
	
	        }
	    }, {
	        scope: 'publish_stream,email'
	    });
	}

  // Here we run a very simple test of the Graph API after login is
  // successful.  See statusChangeCallback() for when this call is made.
  function fbLogin() {
    console.log('Welcome!  Fetching your information.... ');
    FB.api('/me', function(response) {
      console.log('Successful login for: ' + response.name);
      document.getElementById('status').innerHTML = '';
      // alert(" json : "+JSON.stringify(response));
      $("#btnFbSubmit").val("Login as "+response.name);
      $("#facebookName").val(response.name);
      $("#facebookMail").val(response.email);
      $("#j_username").val(response.email);
      $("#j_username").attr("readonly",true);
      $("#j_loginMethod").val("FacebookLogin");
      $("#login-normal-box").hide();
      $("#login-password-box").hide();
      $("#login-cti-ad-box").hide();
      $(".btn-facebook").hide();
      $("#login-fb-box").show();
      $("#login-fb-picture-box").show();
    });
  }
  
  function fblogout(){
	  
	  FB.logout(function(response) {
	        // Person is now logged out
		  location.reload();
	  });
	  
  }
</script>

<script>
	(function(d, s, id) {
		  var js, fjs = d.getElementsByTagName(s)[0];
		  if (d.getElementById(id)) return;
		  js = d.createElement(s); js.id = id;
		  js.src = "//connect.facebook.net/en_US/sdk.js#xfbml=1&appId=181570185197526&version=v2.0";
		  fjs.parentNode.insertBefore(js, fjs);
		}(document, 'script', 'facebook-jssdk'));
</script>
		