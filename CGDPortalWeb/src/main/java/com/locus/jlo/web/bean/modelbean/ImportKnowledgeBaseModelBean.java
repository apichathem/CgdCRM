package com.locus.jlo.web.bean.modelbean;

import java.util.ArrayList;
import java.util.List;

public class ImportKnowledgeBaseModelBean extends BaseModelBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2191635045460770443L;
	private String fileName;
	private String processType;
	private String importDateTime;
	private List<ValidateResultBean> validateResultBeanList = new ArrayList<ValidateResultBean>();
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getProcessType() {
		return processType;
	}
	public void setProcessType(String processType) {
		this.processType = processType;
	}
	public String getImportDateTime() {
		return importDateTime;
	}
	public void setImportDateTime(String importDateTime) {
		this.importDateTime = importDateTime;
	}
	public List<ValidateResultBean> getValidateResultBeanList() {
		return validateResultBeanList;
	}
	public void setValidateResultBeanList(
			List<ValidateResultBean> validateResultBeanList) {
		this.validateResultBeanList = validateResultBeanList;
	}
	
}
