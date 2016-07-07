<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<style type="text/css">
@import url(assets/fonts/thsarabun/thsarabunnew.css);
.previewText{ font-family: 'THSarabunNew', sans-serif;  line-height: 1.5em; border: 1px solid #EEE; padding-bottom: 20px;}
.previewText .navbar-default{
	background-color: darkturquoise;
}
.previewText .navbar-toggle{
	display:block;
}
.previewText .navbar{
	margin-bottom: 0px;
	
}
.previewText .navbar-brand, 
.previewText .navbar-brand:HOVER,
.previewText .navbar-brand:ACTIVE,
.previewText .navbar-brand:FOCUS{
	line-height: 2.5em;
    font-weight: 600;
    color: white;
}
.previewText .navbar-default .navbar-toggle{
	border-color: none; 
}
.previewText .navbar-default .navbar-toggle:HOVER,
.previewText .navbar-default .navbar-toggle:ACTIVE,
.previewText .navbar-default .navbar-toggle:FOCUS{
	background-color: transparent;
}
.previewText .navbar-default .navbar-toggle .icon-bar {
    background-color: white;
}
.previewText .text-center img{
	max-width: 90%;
}
.previewText .content-body{
	overflow-y: scroll;
	height: 420px;
}
.previewText .news-body{
	font-size: 14px;
	margin-top: 20px;
	margin-left: 30px;
	margin-right: 30px;
}
.previewText .news-header{
	background-color: lightseagreen;
    color: white;
    font-size: 18px;
    font-weight: 600;
    padding-top: 10px;
    line-height: 1.5em;
}
</style>
<nav class="navbar navbar-default navbar-static-top">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="javascript:void(0)">
			<i class="fa fa-arrow-left"></i>
			${contentDomain.title }
			</a>
		</div>
	</div>
</nav>
<div class="content-body col-md-12">
	<div class="row">
		<div class="col-md-12 news-header">
			<i class="fa fa-newspaper-o"></i> News Detail<br/>
			<fmt:setLocale value="th_TH"/>
			<font size="2"><fmt:formatDate  dateStyle="long"  value="${ contentDomain.startDt}" /></font>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12 news-title text-center">
			<h3>${contentDomain.title }</h3>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12 text-center">
			<c:forEach items="${contentAttDomain }" var="att">
				<c:if test='${att.descp=="attPic" }'>
					<img alt="" src='readFile.htm?attId=${att.attId}'>
				</c:if>				
			</c:forEach>			
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<div class="news-body">
				<c:set var="newLine" value="
"/>
				<c:set var="contentbody" value="${fn:replace( contentDomain.summary ,newLine,'<br />') }" />
				<c:out value="${contentbody }" escapeXml="false" />
			</div>
			
		</div>
	</div>
</div>