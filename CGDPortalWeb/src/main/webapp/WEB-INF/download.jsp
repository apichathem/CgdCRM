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
	<c:forEach items="${downloadList }" var="item">
		<div class="row">
			<div class="col-md-6">
				${item.codeId }
			</div>
			<div class="col-md-6">
				<a href="${item.etc1 }">${item.codeNameTh }</a>
			</div>
		</div>
	</c:forEach>
</div>

<!-- BEGIN COPYRIGHT -->
<div class="copyright">
	<b>Copyright &copy;</b>2015 Locus Telecommunication Inc., Ltd.<br/>
</div>
<!-- END COPYRIGHT -->