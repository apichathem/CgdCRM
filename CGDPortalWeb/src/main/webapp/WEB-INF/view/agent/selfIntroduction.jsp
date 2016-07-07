<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<style>
#portlet_tab2 .input-group {
	margin-bottom: 5px;
}

.tableknowledgetab1 {
	min-height: 397px;
	max-height: 397px;
	overflow-y: auto;
	overflow-x: hidden;
}

.tableknowledgetab2 {
	min-height: 370px;
	max-height: 370px;
	overflow-y: auto;
	overflow-x: hidden;
}

.tableknowledgeBase .dataTables_info {
	display: none;
}

.tableknowledgeBase .table-scrollable {
	margin: 0px 0 !important;
}
</style>
<!-- BEGIN PAGE CONTENT-->
<div class="row">
	<div class="col-md-12">
		<h3 class="caption">
			${pageTitle }
			<!-- <small>blank page</small> -->
		</h3>
		<ul class="page-breadcrumb breadcrumb">

			<li><i class="fa fa-home"></i> <a href="home.htm"> <spring:message
						code="menu.home" />
			</a> <i class="fa fa-angle-right"></i></li>
			<li><i class="fa ${pageIcon}"></i> <a href="javascript:void(0)"> ${pageTitle }
			</a>
		</ul>
	</div>

	<div class="col-md-4">
		<div class="portlet box grey tabbable">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-reorder"></i>
					${pageTitle }
				</div>
			</div>
			<div class="portlet-body">
				<div class=" portlet-tabs">
					<ul class="nav nav-tabs" style="display:none">
						<%-- <li><a href="#portlet_tab2" data-toggle="tab"> <spring:message
									code="button.search.label" />
						</a></li> --%>
						<li class="active"><a href="#portlet_tab1" data-toggle="tab">
								${pageTitle }
						</a></li>
					</ul>
					<div class="tab-content">
						<div class="tab-pane active" id="portlet_tab1">
							<div class="tableknowledgetab1">
								<div id="tree_knowledge" class="tree-demo"></div>
							</div>
						</div>
						<div class="tab-pane" id="portlet_tab2">
							<div class="input-group">
								<span class="input-group-btn">
									<button type="button" class="btn ">
										<i class="fa fa-search"></i>
									</button>
								</span> <input type="text" class="form-control"
									name="tbl_knowledge_searchFilter"
									id="tbl_knowledge_searchFilter" />
							</div>
							<div class="tableknowledgetab2 tableknowledgeBase">
								<table
									class="table table-bordered dataTable"
									id="tbl_knowledge">
									<thead>
										<tr>
											<th><spring:message code="knowledge.id" /></th>
											<th><spring:message code="knowledge.name" /></th>
										</tr>
									</thead>
								</table>
							</div>

						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="text-right btncreatetree hide">
			<div class="btn-group"> 
				<button type="button" class="btn green" id="btn_New" name="btn_actNew">
					<i class="fa fa-plus"></i>											
					<spring:message code="button.create.label"/>
				</button>
				
			</div>
		</div>
		

	</div>
	<div class="col-md-8">
		<tiles:insertTemplate template="/WEB-INF/view/kb/infoInvisible.jsp" />
		
		
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-reorder"></i><spring:message code="knowledge.detail" />
				</div>
				<div class="tools">
					<a href="javascript:;" class="collapse">
					</a>
				</div>
			</div>
			<div class="portlet-body">
				<ul class="nav nav-tabs detail_tab">
					<li class="active ">
						<a href="#tab_map" onclick="" data-toggle="tab">
							ภาพ/วีดีโอนำเสนอ
						</a>
					</li>
				</ul>
				<div class="tab-content">
					<div class="tab-pane fade active in"  id="tab_map">
						<tiles:insertTemplate template="/WEB-INF/view/tab/tab_myseft.jsp" />
					</div>
					
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
var searchContentType = "${contentType}";
jQuery(document).ready(function() {
	

});
</script>
<tiles:insertTemplate template="/WEB-INF/view/script/kbScriptA.jsp" />