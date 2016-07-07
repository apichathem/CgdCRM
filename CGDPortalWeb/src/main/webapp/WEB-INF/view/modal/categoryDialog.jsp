<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="row">
	<div class="col-md-12">
		<div class="portlet grey box">
			<div class="portlet-title">
				<div class="caption"><spring:message code="knowledge.management.topiclist" /></div>
			</div>
			<div class="portlet-body">
				<div class="form-body">
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<label class="control-label col-md-5"> <spring:message code="knowledge.management.issue.category" />
								</label>
								<div class="col-md-7">
									<input type="hidden" id="modalContentTypeId" class="form-control select2me"/>
								</div>
							</div>
						</div>
					</div>
				</div>
				<hr>
				<div id="parentCateTreeId"></div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	var evalFunc ="${callbackfn}";

	$(document).ready(function() {
		// Initial Select 2
		getCodebookSel2DropdownByCodeType($('#modalContentTypeId'), 'KB_CONTENT_TYPE');

		// Initial value for dropdown
		$('#modalContentTypeId').val('01');
		
		searchParentCategory($('#modalContentTypeId').val());		

		// Event selected node of tree
		$('#parentCateTreeId').on('changed.jstree', function (e, data) {  
            //var contentCatId = data.instance.get_node(data.selected[0]).id;
            //var level = data.instance.get_node(data.selected[0]).parents.length;
            setDataIntoComponent(evalFunc,data.instance.get_node(data.selected[0]));
        });
	});

	function searchParentCategory(cateTypeCd) {
		console.log('===== parentMenuTree =====');
	    $("#parentCateTreeId").jstree({
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
	                        'parent': node.id,
	                        "level": node.parents.length,
	                        "cateTypeCd": cateTypeCd
	                    };
	                }
	            }
	        }
	    });
	}
</script>