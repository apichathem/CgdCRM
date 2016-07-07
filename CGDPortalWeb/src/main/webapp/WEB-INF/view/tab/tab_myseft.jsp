<%@page import="com.locus.jlo.web.constant.JLOWebConstant"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tile"%>
<form id="frmMyseft" action="updatetabintroduction.htm" method="post" class="form-body form-horizontal " enctype="multipart/form-data">
	<input type="hidden" name="contentNumber" id="summary_contentId" />
	<input type="hidden" name="mode" value="update" />
	<input type="hidden" name="oattPic" id="oattPic" value=""/>
	<input type="hidden" name="oattVideo" id="oattVideo" value=""/>
	<input type="hidden" name="deleteVideo" id="deleteVideo" value="0" />
	<input type="hidden" name="deletePic" id="deletePic" value="0" />
	<div class="row">								
		<div class="col-md-12">
			<div class="form-group">
				<label class="control-label col-md-3">แนบรูปภาพ</label>
				<div class="col-md-9">
					<div class="fileinput fileinput-new" data-provides="fileinput">
						<div class="input-group input-large">
							<div class="form-control uneditable-input span3" data-trigger="fileinput">
								<i class="fa fa-file fileinput-exists"></i>&nbsp;
								<span class="fileinput-filename">
								</span>
							</div>
							<span class="input-group-addon btn default btn-file">
								<span class="fileinput-new">
									 <i class="fa fa-paperclip"></i>
								</span>
								<span class="fileinput-exists">
									 <i class="fa  fa-refresh"></i>
								</span>
								<input type="file" name="attPic" id="attPic" accept="<%=JLOWebConstant.FILE_TYPE.IMAGE%>" />
							</span>
							<a href="#" class="input-group-addon btn default fileinput-exists" data-dismiss="fileinput">
								<i class="fa fa-trash-o"></i>
							</a>
						</div>
					</div>	
					
				</div>
			</div>
		</div>
	</div>
	<div class="row" style="display:none">
		<div class="col-md-12">
			<div class="form-group">
				<label class="control-label col-md-3">ตัวอย่างรูปภาพ</label>
				<div class="col-md-9">
					<div class="btn-group"> 
						<button type="button" class="btn green hidden-sm hidden-xs" id="btn_Preview" name="btn_Preview" >
							<i class="fa fa-eye"></i> 				
							<spring:message code="knowledge.tab.news.preview"/>
						</button>
						<button type="button" class="btn green hidden-md hidden-lg" id="btn_Download" name="btn_Download" >
							<i class="fa fa-eye"></i> 				
							<spring:message code="file.downloadFile"/>
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row">								
		<div class="col-md-12">
			<div class="form-group">
				<label class="control-label col-md-3">แนบวีดีโอ</label>
				<div class="col-md-9">
					<div class="fileinput fileinput-new" data-provides="fileinput">
						<div class="input-group input-large">
							<div class="form-control uneditable-input span3" data-trigger="fileinput">
								<i class="fa fa-file fileinput-exists"></i>&nbsp;
								<span class="fileinput-filename">
								</span>
							</div>
							<span class="input-group-addon btn default btn-file">
								<span class="fileinput-new">
									 <i class="fa fa-paperclip"></i>
								</span>
								<span class="fileinput-exists">
									 <i class="fa  fa-refresh"></i>
								</span>
								<input type="file" name="attVideo" id="attVideo" accept="<%=JLOWebConstant.FILE_TYPE.VIDEO%>">
							</span>
							<a href="#" class="input-group-addon btn default fileinput-exists" data-dismiss="fileinput">
								<i class="fa fa-trash-o"></i>
							</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div class="row" style="display:none">
		<div class="col-md-12">
			<div class="form-group">
				<label class="control-label col-md-3">ตัวอย่างวีดีโอ</label>
				<div class="col-md-9">
					<div class="btn-group"> 
						 <div id="simpleVideo">
							
						</div> 
						<%-- <button type="button" class="btn green hidden-sm hidden-xs" id="btn_PreviewVdo" name="btn_PreviewVdo" >
							<i class="fa fa-eye"></i> 				
							<spring:message code="knowledge.tab.news.preview"/>
						</button>
						<button type="button" class="btn green hidden-md hidden-lg" id="btn_DownloadVdo" name="btn_DownloadVdo" >
							<i class="fa fa-eye"></i> 				
							<spring:message code="file.downloadFile"/>
						</button> --%>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div class="row form-actions fluid">
		<div class="col-md-12">
			<div class="col-md-offset-3 col-md-9 text-right">
				
				<div class="btn-group">
					<button type="button" class="btn blue" id="btn_summSave" name="btn_summSave" disabled onclick="saveMySeft()">
						<i class="fa fa-floppy-o"></i>											
						<spring:message code="button.save.label"/>
					</button>
				</div>
			</div>
		</div>
	</div>	
</form>
<form action="report.htm" id="frmReport" method="post" target="_blank">
	<input type="hidden" name="reportName" value="myseft" />
	<input type="hidden" name="bg" id="hiddenbg" value="myseft" />	
	<input type="hidden" name="agentLicense" value="XXXXXXXXXXXX" />
	<input type="hidden" name="custName" value="คุณพลอยชมพู ชีวิตสุขดี" />
	<input type="hidden" name="custRef" value="คุณไทยสมุทร ประกันชีวิต, คุณใจดี มีบุญ" />
	<input type="hidden" name="contact" value="เลขที่ 170/74-83 อาคารโอเชี่ยนทาวเวอร์ 1 ถนนรัชดาภิเษก เขตคลองเตย กรุงเทพฯ 10110" />
	<input type="hidden" name="tel" value="0-2258-0385" />
	<input type="hidden" name="isdownload" id="isdownload" value="0" />
	<input type="hidden" name="agentName" value="${USER_PROFILE.firstName} ${USER_PROFILE.lastName}" />
</form>
<script type="text/javascript">
jQuery(document).ready(function() {
	$("#newPic").on("change",function(){
		if(this.value==""){
			return;
		}
		var ext = this.value.match(/\.([^\.]+)$/)[1];
		console.log("ext = "+ext);
	    switch(ext)
	    {
	        case 'jpg':
	        case 'gif':
	        case 'png':
	            break;
	        default:
	        	alertMessage('<spring:message code="knowledge.detail" />','<spring:message code="file.notalow" />');
	            this.value='';
	    }
	});
	$("#btn_Preview").on("click",function(){
		$("#isdownload").val("0");
		$("#hiddenbg").val("<%=JLOWebConstant.ATTACHMENT_FOLDER%>/attachment/content/"+$("#contentNumber").val()+"/"+$("#attPic").closest(".fileinput").find(".fileinput-filename").text());
		$("#frmReport").submit();

	});
	$("#btn_Download").on("click",function(){
		$("#isdownload").val("1");
		$("#hiddenbg").val("<%=JLOWebConstant.ATTACHMENT_FOLDER%>/attachment/content/"+$("#contentNumber").val()+"/"+$("#attPic").closest(".fileinput").find(".fileinput-filename").text());
		$("#frmReport").submit();

	});
	
	
});

function reloadNewAttachFile(){
	loadJsonData("inquiryKBAtt.htm", "contentId="
			+ $("#contentNumber").val()+"&sEcho=1&iDisplayLength=10&iDisplayStart=0", "POST", function(data){
		console.log(data.aaData);
		$("#btn_summSave").prop("disabled",false);
		$("#attPic").closest(".fileinput").find(".fileinput-filename").text("");
		$("#attPic").closest(".fileinput").removeClass("fileinput-exists");
		$("#attPic").closest(".fileinput").addClass("fileinput-new");
		$("#oattPic").val("");
		$("#btn_Preview").closest(".row").hide();
		$("#simpleVideo").closest(".row").hide();
		
		$("#attVideo").closest(".fileinput").find(".fileinput-filename").text("");
		$("#attVideo").closest(".fileinput").removeClass("fileinput-exists");
		$("#attVideo").closest(".fileinput").addClass("fileinput-new");
		$("#oattVideo").val("");
		
		
		$("#oattPic").val("");
		$("#oattVideo").val("");
		for (var i=0; i<data.aaData.length;i++) {
			console.log(data.aaData[i]);
			if(data.aaData[i].descp=="attPic"){
				$("#attPic").closest(".fileinput").find(".fileinput-filename").text(data.aaData[i].fileName);
				$("#attPic").closest(".fileinput").addClass("fileinput-exists");
				$("#attPic").closest(".fileinput").removeClass("fileinput-new");
				$("#oattPic").val(data.aaData[i].attId+":::"+data.aaData[i].contentAttId+":::"+data.aaData[i].filePath+data.aaData[i].fileName);
				$("#btn_Preview").closest(".row").show();
			}else if(data.aaData[i].descp=="attVideo"){
				$("#attVideo").closest(".fileinput").find(".fileinput-filename").text(data.aaData[i].fileName);
				$("#attVideo").closest(".fileinput").addClass("fileinput-exists");
				$("#attVideo").closest(".fileinput").removeClass("fileinput-new");
				$("#oattVideo").val(data.aaData[i].attId+":::"+data.aaData[i].contentAttId+":::"+data.aaData[i].filePath+data.aaData[i].fileName);
				$("#simpleVideo").closest(".row").show();
				
				$("#simpleVideo").html('<video height="250" controls><source  src="readFile.htm?attId='+data.aaData[i].attId+'" type="video/mp4">Your browser does not support the video tag.</video>');
				
			}
			
		}
	});
}
function saveMySeft(){
	if($("#contentNumber").val()==""){
		alertMessage('<spring:message code="knowledge.detail" />','<spring:message code="knowledge.chooseContent" />');
		return;
	}
	$("#summary_contentId").val($("#contentNumber").val());
	if($("#attPic").closest(".fileinput").find(".fileinput-filename").text()==""){
		$("#deletePic").val("1");
	}
	if($("#attVideo").closest(".fileinput").find(".fileinput-filename").text()==""){
		$("#deleteVideo").val("1");
	}
	ajaxSubmitForm($("#frmMyseft"),function(data){
		console.log(data);
		if(data!=null){
			if(data.status!="error"){
				if (data.chgBy != null){
					$("#chgBy").text(data.chgBy);
				}else{
					$("#chgBy").text("");
				}
					
				if (data.chgDt != null){
					$("#chgDt").text(timestamp2datetime(data.chgDt));
				}else{
					$("#chgDt").text("");
				}
				reloadNewAttachFile();
			}else{
				alertMessage("<spring:message code="knowledge.detail" />","<spring:message code="lbl.save.fail" />");
			}
		}else{
			alertMessage("<spring:message code="knowledge.detail" />","<spring:message code="lbl.save.fail" />");
		}
		
	});
}
</script>