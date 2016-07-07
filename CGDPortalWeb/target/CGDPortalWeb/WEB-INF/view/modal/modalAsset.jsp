<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<ul class="nav nav-tabs">
	<li class="active"><a href="#tab1" data-toggle="tab">Tab 1</a></li>
	<li><a href="#tab2" data-toggle="tab">Tab 2</a></li>
</ul>
<div class="tab-content">
	<div class="tab-pane active" id="tab1"><p>This modal was loaded in via ajax</p></div>
	<div class="tab-pane" id="tab2"><p>This is some other tab content</p></div>
</div>
<button class="btn update" id="openDialog"  data-target="#toggleDialog" data-toggle="modal">OpenDialog</button>

<button class="btn update" id="returnData" > Return Data </button>



<div class="modal fade" id="toggleDialog" role="basic" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div id="modal_content_div_toggle"></div>
		</div>
	</div>
</div>
<script type="text/javascript">
var CONTEXT_PATH = "<%=request.getContextPath()%>";
var AJAX_TIMEOUT = '10000'; 	

$(document).ready(function() { 
	
	//  Dialog
	$("#openDialog").click(function() {
		loadContentIntoModal($("#modal_content_div_toggle"), "openModalDialog.htm", "userDialog", "modal.header.user");
	});
	$("#returnData").click(function() {
		selectedUser("A");
		$("#toggleDialog").hide();
	});
	
	
	
	
});

function selectedUser(param){
	
	alert("Param :"+param);
	
} 
</script>
