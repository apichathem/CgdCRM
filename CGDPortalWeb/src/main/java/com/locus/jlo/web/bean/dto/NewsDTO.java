package com.locus.jlo.web.bean.dto;


public class NewsDTO {

	private String id;
	private String topic;
	
	/* id : "123",
	  topic : "Test Online News",
	  content : "Online News Content",
	  start_dt : "20150727180000",
	  end_dt : "201501027180000",
	  image_id : "55",
	  attach_id : "115",
	  attach_file_name : "SS_Sirinapa B  Java 9Y.pdf",
	  attach_file_type : "application/pdf"
		  
	*/	  
	private String content;
	private String startDt;
	private String endDt;
	private String imageId;
	private String attachId;
	private String attachFileName;
	private String attachFileType;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getStartDt() {
		return startDt;
	}
	public void setStartDt(String startDt) {
		this.startDt = startDt;
	}
	public String getEndDt() {
		return endDt;
	}
	public void setEndDt(String endDt) {
		this.endDt = endDt;
	}
	public String getImageId() {
		return imageId;
	}
	public void setImageId(String imageId) {
		this.imageId = imageId;
	}
	public String getAttachId() {
		return attachId;
	}
	public void setAttachId(String attachId) {
		this.attachId = attachId;
	}
	public String getAttachFileName() {
		return attachFileName;
	}
	public void setAttachFileName(String attachFileName) {
		this.attachFileName = attachFileName;
	}
	public String getAttachFileType() {
		return attachFileType;
	}
	public void setAttachFileType(String attachFileType) {
		this.attachFileType = attachFileType;
	}
	
	
	
	
}
