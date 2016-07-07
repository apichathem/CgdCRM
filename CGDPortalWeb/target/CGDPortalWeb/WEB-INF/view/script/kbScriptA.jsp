<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<style type="text/css">
.jstree-container-ul li.jstree-last {
	display:none;
}
.jstree-container-ul li.jstree-last:FIRST-CHILD, .jstree-container-ul li.jstree-last ul li.jstree-last {
	display:block;
}
</style>
<script type="text/javascript">
	var tblknowledge;
	
	var columnsTable = [ {
		"sTitle" : '<spring:message code="knowledge.id"/>',
		"mData" : "contentNumber"
	}, {
		"sTitle" : '<spring:message code="knowledge.name"/>',
		"mData" : "title"
	} ];
	
	// 	init
	window.onload = function() {
		jQuery(document).ready(function() {
			SearchTable.init();
			TreeTable.init();
			ComponentsPickers.init();
			
			if (typeof InfoPage != "undefined") {
				InfoPage.init();
			}
			if (typeof initMap != "undefined") {
				initMap();
			}
			if (typeof initAddress != "undefined"){
				initAddress();
			}
			if(typeof initKeyword != "undefined"){
				initKeyword();
			}
			
			knowledgedformreadonly(true);
			$("#btn_Save").addClass("disabled");
		});
	};
	
	var treeobj;
	//     Tree function 
	var TreeTable = function() {
		return {
			init : function() {
				treeobj = loadJsonTree($('#tree_knowledge'),
						'inquiryKBTreeSelect.htm',
						'contentType='+searchContentType, "POST");

				$('#tree_knowledge').on(
						'changed.jstree',
						function(e, data) {
							contentNumber = data.instance
									.get_node(data.selected[0]).id;
							
							if($("#"+contentNumber).attr("type")!="file")return;
							
							if($(".tab-pane.active #tbl_knowledge").length==0){
								$("#tbl_knowledge").find("td:contains('"+contentNumber+"')").click();
							}
						});
			},
			refresh : function(){
				$("#tree_knowledge").jstree("destroy").html("");
				TreeTable.init();
			}
		};
	}();
	
	
	
	
	var SearchTable = function() {
		
		return {
			init : function() {
				tblknowledge = $('#tbl_knowledge').dataTable({
	    				"aoColumns": columnsTable,
	    		        "bLengthChange": false,
	    		        "bDestroy": true,
	    		        "bAutoWidth": false,
	    		        "bPaginate": false,
	    		        "fnDrawCallback": function (oSettings){
	    		            var that = this;
	    		        }
	    		    }); 
				
				$.ajax({
					timeout: AJAX_TIMEOUT,
					url : "inquiryKBTableList.htm",
					dataType: 'json',
					data: "contentType="+searchContentType,
					type: "post",
					success : function(data, textStatus, jqXHR){
// 						 console.log(data.aaData);
						 tblknowledge.dataTable().fnClearTable(0);
						 tblknowledge.dataTable().fnAddData(data.aaData);
						 
					},error: function (xhr, textStatus, errorThrown) {
						
						checkShowMsgAjaxError(xhr, textStatus, errorThrown);
						
					}
				});
				
				
				
				$("#tbl_knowledge_searchFilter").unbind('keypress keyup').bind(
						'keypress keyup', function(e) {
							if ($(this).val().length > 0 && $(this).val().length < 2 && e.keyCode != 13)
								return;
							
							tblknowledge.fnFilter($(this).val());

						});
				$(".dataTables_filter").hide();

				$("#tbl_knowledge tr").live('click',function() {
					var selectid = $(this).find("td:first-child").text();
					if(selectid == '') return;
					try{
						loadJsonData("inquiryKBDetail.htm", "contentNumber="
								+ selectid, "POST", callbackTableselect);
					}catch(e){
						console.log(e);
					}
				});
			},
			refresh : function(){
				tblknowledge = ajaxDataTableSetCallback($('#tbl_knowledge'), columnsTable, 'inquiryKBTableList.htm', "contentType="+searchContentType, 10000, false, false,[],false);
			}
		};
	}();
	
	
	function callbackTableselect(data) {
		genDetailForm(data);
		
		var vIndex = data.title.indexOf("VDO");
		var pIndex = data.title.indexOf("ภาพ");
		
		if(vIndex >= 0){
			$("#attPic").closest(".row").hide();
			$("#attVideo").closest(".row").show();
		}else if(pIndex >= 0){
			$("#attPic").closest(".row").show();
			$("#attVideo").closest(".row").hide();
		}else{
			$("#attPic").closest(".row").show();
			$("#attVideo").closest(".row").show();
		}
		
		
		

	}

</script>