<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<style type="text/css">
@import url(assets/fonts/thsarabun/thsarabunnew.css);
.simpletemplate{ font-family: 'THSarabunNew', sans-serif;  line-height: 1.5em; background: #e1e1e1; }
.bgContent{
	background-image: url('getImg.htm?src=${fn:replace(template.picUrl,"\\","/")}');
	width: 480px;
	height: 680px;
	position: absolute; 
	background-size: contain;
	background-repeat: no-repeat;
	border: 1px solid;
}
.topContent{
	width: 370px;
	height: 100px;
	position: absolute;
	margin-top: 25px;
	margin-left: 60px;
	color: white;
}
.rightContent{
	height: 260px;
	width: 180px;
	position: absolute;
	margin-left: 277px;
	margin-top: 130px;
	font-size: 12px;
	line-height: 1.4em;
}
.titleContent{
	height: 35px;
	width: 225px;
	position: absolute;
	margin-top: 390px;
	margin-left: 35px;
	color: white;
	text-align: center;
}
.bodyContent{
	height: 175px;
	width: 420px;
	position: absolute;
	margin-top: 422px;
	margin-left: 35px;
}
.footContent{
	height: 50px;
	width: 370px;
	position: absolute;
	color: white;
	margin-top: 638px;
	margin-left: 100px;
	text-align: right;
}
</style>
 
<div class="row simpletemplate" style="clear: both;">
	<div class="bgContent"></div>
	<div class="topContent">
		<div style="margin-left: 10px;  margin-bottom: 10px; font-size:30px;"><span style="${fn:replace(template.styleA,'dp','px')}">“${template.textA }”</span></div>
		<div><span style="${fn:replace(template.styleB,'dp','px')}">${template.textB} ${fullname}</span></div>
		<div><span style="${fn:replace(template.styleC,'dp','px')}">${template.textC }</span></div>
	</div>
	<div class="rightContent">
		<span style="${fn:replace(template.styleD,'dp','px')}">${template.textD }</span>
		<br />
		<span style="${fn:replace(template.styleE,'dp','px')}">${template.textE } ${agentLicense}</span>
		<br />
		<span style="${fn:replace(template.styleF,'dp','px')}">${template.textF }</span>
		<br />
		<span style="${fn:replace(template.styleG,'dp','px')}">${template.textG}</span>
	</div>
	<div class="titleContent"><div style=" font-size: 18px;"><span style="${fn:replace(template.styleH,'dp','px')}">${template.textH}</span></div></div>
	<div class="bodyContent ">
		<div style="font-size:12px"><span style="${fn:replace(template.styleI,'dp','px')}">${template.textI }</span></div>
		<div style="font-size:11px"><span>${ trainHistory}</span></div>
		<div style="font-size:12px"><span style="${fn:replace(template.styleJ,'dp','px')}">${template.textJ }</span></div>
		<div style="font-size:11px"><span>${refCustomer}</span></div>
		<div style="font-size:12px"><span style="${fn:replace(template.styleK,'dp','px')}">${template.textK }</span></div>
		<div style="font-size:12px"><span style="${fn:replace(template.styleL,'dp','px')}">${template.textL}</span></div>
		<div style="font-size:11px"><span>${address}</span></div>
	</div>
	<div class="footContent">
		<div style="font-size:22px"><span style="${fn:replace(template.styleM,'dp','px')}">“${template.textM}”</span></div>
		<div style="font-size: 10px;  margin-right: 20;"><span>CODE ${agentCode}</span></div>
	</div>
</div>
<div style="clear: both;"> &nbsp;
</div>
