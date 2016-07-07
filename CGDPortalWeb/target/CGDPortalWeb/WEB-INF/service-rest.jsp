<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!-- BEGIN LOGO -->
<div class="logo">
	<div id="status"></div>
</div>
<!-- END LOGO -->
<!-- BEGIN LOGIN -->


<div class="content">
<form id="login-form" class="login-form" name='f' action='${pageContext.request.contextPath}/j_spring_security_check' method='POST' accept-charset="UTF-8">
	
	<h3 class="form-title">Ocean Life Agent Web Service : Index</h3>
	
	<div id="login-normal-box" class="form-actions">
		
			<a href="service-rest/login.htm?username=xxxx&password=yyyy&deviceid=09340923804283094" id="login-button" class="btn blue pull-left">
			Mobile Login <i class="fa fa-users "></i>
			</a>
		
			<a href="service-rest/get-version.htm?currentversion=1.0.3&os=Android" id="version-button" class="btn green pull-left">
			Get Version <i class="fa fa-folder-open "></i>
			</a>
		
			<a href="service-rest/get-codebook-list.htm?syncdt=20150101111011&pageno=1&rowperpage=100&tokencode=dhUY9AE4ui0Hcg5ysDxz&deviceid=09340923804283094" id="codebook-button" class="btn yellow pull-left">
			Get Codebook <i class="fa fa-folder "></i>
			</a>
		
			<a href="service-rest/get-token.htm" id="get-token-button" class="btn dark pull-left">
			Get Token <i class="fa fa-users "></i>
			</a>
		
			<a href="service-rest/check-token.htm?tokencode=CUgAOMhUdqidcQaxMGoo" id="check-token-button" class="btn red pull-left">
			Check Token <i class="fa fa-exclamation-triangle "></i>
			</a>
		
			<a href="service-rest/destroy-token.htm?tokencode=CUgAOMhUdqidcQaxMGoo" id="check-token-button" class="btn purple pull-left">
			Destroy Token <i class="fa fa-map-marker "></i>
			</a>
		
			<a href="service-rest/changepassword.htm?username=xxxx&oldpassword=yyyy&newpassword=zzzz&deviceid=09340923804283094&tokencode=gXynqPky91wuLevQ0XyT" id="change-password-button" class="btn btn-info pull-left">
			Change Password <i class="fa fa-users "></i>
			</a>
		
			<a href="service-rest/forgetpassword.htm?username=xxxx&deviceid=09340923804283094&tokencode=gXynqPky91wuLevQ0XyT&email=yyyy@locus.co.th" id="forget-password-button" class="btn btn-primary pull-left">
			Forget Password <i class="fa fa-exclamation-triangle "></i>
			</a>
			
			<a href="service-rest/get-news-list.htm?syncdt=20150101111011&pageno=1&rowperpage=100&tokencode=dhUY9AE4ui0Hcg5ysDxz&deviceid=09340923804283094" id="get-news-button" class="btn purple pull-left">
			Get News <i class="fa fa-news "></i>
			</a>
			
			<a href="service-rest/get-branch-list.htm?syncdt=20140101111011&pageno=1&rowperpage=100&tokencode=dhUY9AE4ui0Hcg5ysDxz&deviceid=09340923804283094" id="get-news-button" class="btn red pull-left">
			Get Branch <i class="fa fa-map-marker "></i>
			</a>
			
			<a href="service-rest/get-hospital-list.htm?syncdt=20140101111011&pageno=1&rowperpage=100&tokencode=dhUY9AE4ui0Hcg5ysDxz&deviceid=09340923804283094" id="get-hospital-button" class="btn btn-info pull-left">
			Get Hospital <i class="fa fa-exclamation-triangle "></i>
			</a>
			
			<a href="service-rest/uploadfile.htm?filestream=iVBORw0KGgoAAAANSUhEUgAAABAAAAALCAIAAAD5gJpuAAAABGdBTUEAAK%2FINwWK6QAAABl0RVh0U29mdHdhcmUAQWRvYmUgSW1hZ2VSZWFkeXHJZTwAAAFWSURBVHjaYvzPgAD%2FUNlYEUAAisQgBwAQhGGi%2FpzP8EBvG%2BBImqbL7pzuUlda9SJ7DMs85NYEBgX5Ir4AYvz%2FH2QHhIQz%2FmMDjIyMnz59AgggRkfXjTmZOu%2Fe%2Ffz7D2jH%2F7%2F%2F%2Fv398%2B8PkPEHCEHsv3%2F%2F%2Ffn978%2B%2Ff8JCnGWlWwACiGX%2F7jOmhiKPHn3%2B8wck8fvPv9%2B%2F%2FwLRr1%2F%2FwORfOCkvz8fAsAUggIB%2B%2BAdxJ8iRQNf%2B%2Bf%2FrF8TZ%2F4B6fgEZQPIXRAEoLAACCKjhx9%2B%2Ff%2F78%2Bf0LaC%2FYbIjxyGaDSaCFvxgYvgAEEAs3r5qKqhAPLzs4GP4CnQR2G9CMf2A2iPEH7BNJSe5Tp8wAAojx58%2BfzMzM%2F%2F79wxU4EACUBYbS27dvAQKI5R87O1NJCQPEjX%2F%2FMvwGkn8Yf%2F8GRggCAY0DSgFt2bsXIIAYv6JGJJ44hgCAAAMA8pZimQIezaoAAAAASUVORK5CYII%3D&filename=testFile.jpg&group=testupload&username=poobase&tokencode=dhUY9AE4ui0Hcg5ysDxz&deviceid=09340923804283094" id="get-uploadfile-button" class="btn green pull-left">
			Upload File <i class="fa fa-exclamation-triangle "></i>
			</a>
		
	</div>
	
</form>	
	

</div>
<!-- END LOGIN -->



<!-- BEGIN COPYRIGHT -->
<div class="copyright">
	<b>Copyright &copy;</b>2015 Locus Telecommunication Inc., Ltd.<br/>
</div>
<!-- END COPYRIGHT -->
<script type="text/javascript">

$(document).ready(function(){
});

</script>
		