<%@page import="com.locus.jlo.web.constant.JLOWebConstant"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="locus" uri="/WEB-INF/tld/locus.tld"%>

<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="caption">
			<spring:message code="menu.knowledgebase.knowledgebaseManagement" />
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="home.htm"> <spring:message code="menu.home" />
			</a><i class="fa fa-angle-right"></i></li>
			<li><a href="#"> <spring:message code="menu.knowledgebase" />
			</a> <i class="fa fa-angle-right"></i></li>
			<li><a href="#"> <spring:message code="menu.knowledgebase.knowledgebaseManagement" />
			</a></li>
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<div class="row">
	<div class="col-md-4">
		<div class="portlet grey box tabbable">
			<div class="portlet-title">
				<div class="caption"><spring:message code="knowledge.management.topiclist" /></div>
			</div>
			<div class="portlet-body">
				<div class=" portlet-tabs">
					<ul class="nav nav-tabs">
						<li>
							<a href="#portlet_tab2" data-toggle="tab">
								  <spring:message code="knowledge.tab.title2" />
							</a>
						</li>
						<li class="active">
							<a href="#portlet_tab1" data-toggle="tab">
								 <spring:message code="knowledge.tab.title1" />
							</a>
						</li>
					</ul>
					<div class="tab-content">
						<div class="tab-pane active" id="portlet_tab1">
							<div class="form-body">
								<div class="row">
									<div class="col-md-12">
										<div class="form-group">
											<label class="control-label col-md-5"> <spring:message code="knowledge.management.issue.category" />
											</label>
											<div class="col-md-7">
												<input type="hidden" id="kbContentTypeId" class="form-control select2me"/>
											</div>
										</div>
									</div>
								</div>
							</div>
							<hr>
							<div id="cateTreeId"></div>		
						</div>
						<div class="tab-pane" id="portlet_tab2">
							<div class="col-md-12">
								<div class="form-group">
									<label class="control-label col-md-8"> <spring:message code="knowledge.tab.list.criteria.level" />
									</label>
									<div class="col-md-4">
										<select id="criteriaLevelSel" class="form-control">
											<option value="1">1</option>
											<option value="2">2</option>
											<option value="3">3</option>
											<option value="4">4</option>
											<option value="5">5</option>
										</select>
									</div>
								</div>
							</div>
							<div class="col-md-12">
								<div class="form-group">
									<div class="col-md-12">
										<div class="input-group">
											<span class="input-group-btn">
												<button type="button" class="btn ">
													<i class="fa fa-search"></i>
												</button>
											</span>
											<input type="text" class="form-control" id="criteriaKeywordTxt" />
											<span class="input-group-btn">
												<a title="" data-original-title="" href="javascript:clearDetail();javascript:searchCategoryList();" class="btn blue" id="username1_checker">
													<i class="fa fa-search"></i>
													<spring:message code="button.search.label" />
												</a>
											</span>							
										</div>
									</div>
								</div>
							</div>
							
							<div>
								<table class="table table-bordered dataTable" id="resultTableId">
								</table>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="text-right">
			<locus:privilege oper="ADD">
				<button class="btn green" type="button" onclick="createNewCategory()">
					<i class="fa fa-plus"></i>
					<spring:message code="button.create.label" />
				</button>
			</locus:privilege>
		</div>
	</div>

	<div class="col-md-8">
		<div class="portlet blue box">
			<div class="portlet-title">
				<div class="caption"><spring:message code="knowledge.management.topicdetail" /></div>
			</div>
			<div class="portlet-body">
				<form:form id="categoryDetailFormId" action="saveCategoryDetail.htm" modelAttribute="categoryModel" class="form-horizontal" autocomplete="off">
					<div class="form-body">
						<div class="row">
							<div class="col-md-6">
								<form:hidden path="level" class="form-control" />
								<form:hidden path="hasChild" class="form-control" />
								<form:hidden path="statusCd" class="form-control" value="01"/>
								
								<div class="form-group">
									<label class="control-label col-md-5"> <spring:message code="knowledge.management.detail.parentfolder" />
									</label>
									<div class="col-md-7">
										<div class="input-group">
											<form:hidden path="parentCatId" class="form-control" />
											<form:input path="parentCatName" class="form-control" readonly="true"/>
											<span class="input-group-addon"> 
											<a id="categorybtnModal" href="#" data-target="#categoryDialogId" data-toggle="modal"> <i class="fa fa-search" id=""></i></a>
											</span>
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label class="control-label col-md-5"> <spring:message code="knowledge.management.detail.sla" />
									<span class="required"> * </span></label>
									<div class="col-md-7">
										<form:select path="slaId" class="form-control select2me" data-rule-required="true">
											<option></option>
											<form:options items="${slaSelect}" />
										</form:select>
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label class="control-label col-md-5"> <spring:message code="knowledge.management.detail.categoryname" />
									<span class="required"> * </span></label>
									<div class="col-md-7">
										<form:hidden path="contentCatId" class="form-control"/>
										<form:hidden path="contentTypeCd" class="form-control"/>
										<form:input path="catName" class="form-control" data-rule-required="true"/>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label class="control-label col-md-5"> <spring:message code="knowledge.management.detail.slatime" />
									<span class="required"> * </span>
									</label>
									<div class="col-md-3">
										<form:input path="slaUnit" class="form-control" data-rule-required="true" readonly="true"/>
									</div>
									<label class="control-label col-md-3"> <spring:message code="knowledge.management.detail.slatunit" />
									</label>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label class="control-label col-md-5"> <spring:message code="knowledge.management.detail.description" />
									</label>
									<div class="col-md-7">
										<form:textarea path="descp" class="form-control"/>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
								</div>
							</div>
						</div>
						<hr>
						<div class="row form-horizontal">
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-5"> <spring:message code="lbl.createBy"/>
									</label>
									<div class="col-md-7">
										<p id="createBy" class="form-control-static"></p>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label class="control-label col-md-5"> <spring:message code="lbl.createDate"/>
									</label>
									<div class="col-md-7">
										<p id="createDate" class="form-control-static"></p>
									</div>
								</div>
							</div>
							<div class="col-md-2">
								<div class="form-group"></div>
							</div>
						</div>
						<div class="row form-horizontal" id="show_update_field">
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-5"> <spring:message code="lbl.updateBy"/>
									</label>
									<div class="col-md-7">
										<p id="updateBy" class="form-control-static"></p>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label class="control-label col-md-5"> <spring:message code="lbl.updateDate"/>
									</label>
									<div class="col-md-7">
										<p id="updateDate" class="form-control-static"></p>
									</div>
								</div>
							</div>
							<div class="col-md-2">
								<div class="form-group">
								</div>
							</div>
						</div>
					</div>
				</form:form>
			</div>
			
			<div class="form-actions right">
				<button id="delCategoryBtn" class="btn red" type="button" disabled="disabled">
					<i class="fa fa-trash-o"></i>
					<spring:message code="button.delete.label" />
				</button>
				
				<span id="insertDiv" style="display: none;">
					<button id="insertCategoryBtn" class="btn blue" type="button" onclick="insertCategory()">
						<i class="fa fa-floppy-o"></i>
						<spring:message code="button.save.label" />
					</button>
				</span>
				
				<span id="updateDiv" style="display: none;">
					<button id="updateCategoryBtn" class="btn blue" type="button" onclick="updateCategory()">
						<i class="fa fa-floppy-o"></i>
						<spring:message code="button.save.label" />
					</button>
				</span>
				
				<button id="clearCategoryBtn" class="btn default" type="button" onclick="clearDetail()">
					<spring:message code="button.cancel.label" />
				</button>
			</div>
		</div>
	</div>
	<div class="col-md-8 text-left">
		<locus:privilege oper="ADD">
			<button class="btn green" type="button" onclick="createNewCategory()">
				<i class="fa fa-plus"></i>
				<spring:message code="button.create.label" />
			</button>
		</locus:privilege>
	</div>
</div>

<!-- Modal Dialog -->
<div class="modal fade" id="categoryDialogId" role="basic" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div id="modal_content_div"></div>
		</div>
	</div>
</div>


<script type="text/javascript">

	/* Initial Variable*/
	var txtValidates = ["catName","slaId","slaUnit"];
	var screenMode;
	var tempDataForEdit;
	
	var oTable;
	var recordPerPage = <%=JLOWebConstant.recordsPerPage%>;
	var confirmDeleteMsg = '<spring:message code="lbl.confirm.delete"/>';
	var deleteSuccessMsg = '<spring:message code="lbl.delete.success"/>';
	var deleteFailMsg = '<spring:message code="lbl.delete.fail"/>';
	
	var column1 = '<spring:message code="knowledge.tab.list.title.level" />';
	var column2 = '<spring:message code="knowledge.tab.list.title.name" />';
	var columns = [	{ "sTitle": column1, "mData": 'level' , sClass: "text-center"}
				   ,{ "sTitle": column2, "mData": 'catName' }
			];

	/* Initial on load page*/
	$(document).ready(function() {
		// Initial datatable
		oTable = initajaxDataTable($('#resultTableId'), columns);

		// Initial validator form
		validateForm($("#categoryDetailFormId"));
		
		// Initial Select 2 component
		getCodebookSel2DropdownByCodeType($('#kbContentTypeId'), 'KB_CONTENT_TYPE');
		$('#slaId').select2();
		$('#criteriaLevelSel').select2();

		// Initial value for dropdown
		$('#kbContentTypeId').val('01');
		/* $('#kbStatusId').val('01'); */
		$('#criteriaLevelSel').val('1');

		// Search KB Level 1
		searchMainCategory($('#kbContentTypeId').val());

		// Event change SLA dropdown
		$('#slaId').change(function() {
			var param = 'slaId=' + this.value;
		    var jsonObj = getJsonData('getSlaById.htm', param, 'POST');
		    if (jsonObj.resultCode == '0') {
				var model = jsonObj.model;
				$('#slaUnit').val(model.slaUnit);
			}
		});

		// Event modal dialog
		$("#categorybtnModal").click(function() {
			loadContentIntoModal($("#modal_content_div"), "openModalDialog.htm","categoryDialog","model.header.kb", "callbackSelectedCategory");
		});

		// Event enter to search
		$("#criteriaKeywordTxt").onEnter( function() {
			searchCategoryList();
		});
		
		toggleMode('none');
	});

	function toggleSearchButton(flag) {
		if (flag == true) {
			$('#categorybtnModal').prop('disabled', true);
	        $('#categorybtnModal').css('cursor', 'not-allowed');
		} else {
			$('#categorybtnModal').prop('disabled', false);
	        $('#categorybtnModal').css('cursor', 'pointer');
		}
        
	}

	function toggleMode(mode) {
		screenMode = mode;
		$('#modeId').text(screenMode);
		if (mode == 'new') {
			$('#slaId').prop('disabled', false);
			$('#catName').prop('disabled', false);
			$('#descp').prop('disabled', false);
			$('#slaUnit').prop('disabled', false);
			//$('#saveCategoryBtn').prop('disabled', false);
			//$('#insertCategoryBtn').prop('disabled', false);
			//$('#updateCategoryBtn').prop('disabled', false);
			
			$("#insertDiv").show();
			$("#updateDiv").hide();
		
			$('#delCategoryBtn').prop('disabled', true);
			$('#clearCategoryBtn').prop('disabled', false);

			toggleSearchButton(false);
		} else if (mode == 'edit') {
			$('#slaId').prop('disabled', false);
			$('#catName').prop('disabled', false);
			$('#descp').prop('disabled', false);
			$('#slaUnit').prop('disabled', false);
			//$('#saveCategoryBtn').prop('disabled', false);
			$('#clearCategoryBtn').prop('disabled', false);

			$("#insertDiv").hide();
			$("#updateDiv").show();
			
			toggleSearchButton(true);
		} else {
			$('#slaId').prop('disabled', true);
			$('#catName').prop('disabled', true);
			$('#descp').prop('disabled', true);
			$('#slaUnit').prop('disabled', true);
			//$('#saveCategoryBtn').prop('disabled', true);
			
			$("#insertDiv").hide();
			$("#updateDiv").hide();
			
			$('#delCategoryBtn').prop('disabled', true);
			$('#clearCategoryBtn').prop('disabled', true);
			
			toggleSearchButton(true);
		}
	}

	var delHandler = function() {
		var callbackYes = "callbackConfirmYes";
		var callbackNo = "";
		var param ="";
		
		alertConfirmYesNo(confirmDeleteMsg,callbackYes,callbackNo,param);
	};

	function callbackSelectedCategory(jsonData){
		//alert(JSON.stringify(jsonData));
		$('#parentCatName').val(jsonData.text);
		$('#parentCatId').val(jsonData.id);
		$('#level').val(parseInt(jsonData.parents.length) + 1);
		$("#categoryDialogId").modal("hide");
		
	}
	
	function callbackAfterDelete() {
		postAction('/knowledgeBase.htm');
	}

	function callbackConfirmYes() {
		var param = 'contentCatId='+ $('#contentCatId').val() + '&level=' + $('#level').val();
		var jsonObj = getJsonData('deleteCategory.htm', param, 'POST');
	    if (jsonObj.resultCode == '0') {
	    	// Reload Tree
	    	callbackAfterDelete();
		} else {
			alertMessage('<spring:message code="knowledgeBase.title" />', jsonObj.resultMessage);
		}
	}

	function callbackAfterSave(mode) {
		//alert($('#contentCatId').val() + ' ' +  $('#level').val());
		searchCategoryDetail($('#contentCatId').val(), $('#level').val());

		$('#cateTreeId').jstree("destroy");
		//alert($('#kbContentTypeId').val());
		searchMainCategory($('#kbContentTypeId').val());

	}
		
	function callbackCategoryDetail(level) {
		return function (data, textStatus, jqXHR) {
			
			
			if (data.resultCode == '0') {
				var model = data.model;
				tempDataForEdit = model;
				setDataToForm(model);
				$('#level').val(level);
			} else {
				alertMessage('<spring:message code="knowledgeBase.title" />', data.resultMessage);
			}
		};
	}

	function setDataToForm(model) {
		//alertMessage('',JSON.stringify(model));
		$('#contentCatId').val(model.contentCatId);
		$('#catName').val(model.catName);
		$('#descp').val(model.descp);
		$('#slaUnit').val(model.slaUnit);
		$('#level').val(model.level);
		$('#hasChild').val(model.hasChild);
		$('#parentCatId').val(model.parentCatId);
		
		if (model.parentCatName == '' || model.parentCatName == null) {
			$('#parentCatName').val('#');
		} else {
			$('#parentCatName').val(model.parentCatName);
		}

		$('#contentTypeCd').val(model.contentTypeCd);
		$('#catName').val(model.catName);
		
		$('#createBy').text(model.createBy);
		$('#updateBy').text(model.updateBy);
		$('#createDate').text(model.createDateTxt);
		$('#updateDate').text(model.updateDateTxt);
		
		$('#slaId').select2("val", model.slaId);

		// For manage delete button
		if (model.hasChild || model.isUsed || model.hasData || model.usedBySr) {
			$('#delCategoryBtn').prop('disabled', true);
			$('#delCategoryBtn').unbind('click', delHandler);
		} else {
			$('#delCategoryBtn').prop('disabled', false);
			$('#delCategoryBtn').bind('click', delHandler);
		}
		
	}

	function searchCategoryList() {
		var keyword = $('#criteriaKeywordTxt').val();
		var level = $('#criteriaLevelSel').val();

		var dataString = 'keyword=' + keyword + '&level=' + level;
		oTable = ajaxDataTable($('#resultTableId'), columns, 'searchCategoryList.htm', dataString, recordPerPage, true, false);

		$('#resultTableId tbody').on( 'click touchstart', 'tr', function () {
			 var aPos = oTable.fnGetPosition(this);
			 var aData = oTable.fnGetData( aPos[0] );
			    
			 var model = aData[aPos];

			 tempDataForEdit = model;
			 setDataToForm(model);

			 toggleMode('edit');
		});
	}

	function searchMainCategory(cateTypeCd) {
		console.log('===== searchMainCategory =====');
		
	    $("#cateTreeId").jstree({
	        "core": {
	            "themes": {
	                "responsive": false
	            },
	            "check_callback": true,
	            "data": {
	                "url": function(node) {
	                    return 'searchCategoryTree.htm';
	                },
	                "data": function(node) {
	                    return {
	                        "parent": node.id,
	                        "level": node.parents.length,
	                        "cateTypeCd": cateTypeCd
	                    };
	                }
	            }
	        }
	    });

	 	// Event selected node of tree
		$('#cateTreeId').on('changed.jstree', function (e, data) {  
            var contentCatId = data.instance.get_node(data.selected[0]).id;
            var level = data.instance.get_node(data.selected[0]).parents.length;
            searchCategoryDetail(contentCatId, level);

         	// Disable parent category button
            toggleMode('edit');
        });
	}

	function searchCategoryDetail(contentCatId, level) {
		var param = 'contentCatId=' + contentCatId + '&level=' + level;
		loadJsonData('searchCategoryDetail.htm', param, 'POST', callbackCategoryDetail(level));
	}

	function createNewCategory() {
		clear();
		$('#level').val(1);
		$('#contentTypeCd').val($('#kbContentTypeId').val());
		
		// Disable parent category button
        toggleMode('new');
        $('#catName').focus();
	}

	function insertCategory() {
		saveCategory('new');
	}

	function updateCategory() {
		saveCategory('edit');
	}

	function saveCategory(mode) {
		var $valid = $("#categoryDetailFormId").valid();
	    if (!$valid) {
	    	return false;
	    }
	    var dataString = serialize($("#categoryDetailFormId")[0]);

	    var jsonObj = getJsonData('saveCategoryDetail.htm', dataString, 'POST');
	    if (jsonObj.resultCode == '0') {
			$('#contentCatId').val(jsonObj.model.contentCatId);
	    	callbackAfterSave(mode);
		} else {
			alertMessage('<spring:message code="knowledgeBase.title" />', jsonObj.resultMessage);
		}
	}

	function clear() {
		$('#contentCatId').val('');
		$('#catName').val('');
		$('#descp').val('');
		$('#slaUnit').val('');
		$('#level').val('');
		$('#hasChild').val('');
		$('#parentCatName').val('');
		$('#contentTypeCd').val('');
		$('#catName').val('');
		
		$('#createBy').text('');
		$('#updateBy').text('');
		$('#createDate').text('');
		$('#updateDate').text('');
		
		$('#slaId').select2("val", '');

		$('#delCategoryBtn').prop('disabled', true);
		$('#delCategoryBtn').unbind('click', delHandler);

		toggleMode('none');
		clearValidate(txtValidates);
	}

	function clearDetail () {

		if ($('#contentCatId').val() != '') {
			setDataToForm(tempDataForEdit);
		} else {
			$('#contentCatId').val('');
			$('#catName').val('');
			$('#descp').val('');
			$('#slaUnit').val('');
			$('#level').val('');
			$('#hasChild').val('');
			$('#parentCatName').val('');
			$('#contentTypeCd').val('');
			$('#catName').val('');
			
			$('#createBy').text('');
			$('#updateBy').text('');
			$('#createDate').text('');
			$('#updateDate').text('');
			
			$('#slaId').select2("val", '');

			$('#delCategoryBtn').prop('disabled', true);
			$('#delCategoryBtn').unbind('click', delHandler);

			toggleMode('none');
			clearValidate(txtValidates);
		}
		
		
	}

</script>