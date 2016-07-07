<%@page import="com.locus.common.utils.CollectionUtil"%>
<%@page import="com.locus.common.constant.WebPortalConstant"%>
<%@page import="com.locus.jlo.web.bean.modelbean.CodebookModelBean" %>
<%@page import="com.locus.jlo.web.util.CodeBookHelper" %>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="com.locus.jlo.web.constant.JLOWebConstant"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	int relationNum = (int)(Math.random()*1000000);
	Map<String, List<CodebookModelBean>>  codebookList = CodeBookHelper.getCodeBookList();
%>
<div class="modalRelationId" id="modalRelationId_<%=relationNum%>">
	<form id="relationForm" class="form-horizontal">
		<div class="row">
			<div class="col-md-12">
				<div class="form-body">
					<div class="row">
						<div class="col-md-4">
							<div class="form-group">
								<label class="control-label col-md-5"> <spring:message code="dialog.relation.relation" /> <span
											class="required"> * </span></label>
								<div class="col-md-7">
									<select name="statusCd" id="relationCd" class="form-control select2me" data-rule-required="true">
										<c:forEach items="${CODEBOOK_LIST.RELATION}" var="cb">
											<option value="${cb.codeId }">${cb.codeName }</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group">
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group">
						</div>
					</div>
				</div>
				
				<div class="form-actions right">
					<button id="btnSearchContact" class="btn blue" type="button" onclick="saveRelation();">
						<i class="fa fa-floppy-o"></i>
						<spring:message code="button.save.label" />
					</button>
				</div>
			</div>
		</div>
	</form>
</div>

<script>
	var evalFunc ="${callbackfn}";
	
	$(document).ready(function() {
		$(".modalRelationId").not("#modalRelationId_<%=relationNum%>").remove();
	});
</script>