package com.locus.jlo.web.service;


import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.locus.common.domain.ServiceResult;
import com.locus.jlo.web.bean.modelbean.CodebookModelBean;

public interface CodebookService {
	ServiceResult<Boolean> insert(CodebookModelBean codebookModelBean);
	ServiceResult<Boolean> update(CodebookModelBean codebookModelBean);
	ServiceResult<List<CodebookModelBean>> searchAll(String langCd);
	ServiceResult<Page<CodebookModelBean>> searchByCriteria(String codeType, String codeName, String langCd, String parentCodeType, Pageable pageable);
	ServiceResult<CodebookModelBean> searchById(String codeType, String codeId, String langCd);
	ServiceResult<List<CodebookModelBean>> searchLangById(String codeType, String codeId);
	ServiceResult<CodebookModelBean> searchById(String codeType, String codeId);
	CodebookModelBean saveCodebook(CodebookModelBean tpCodebook) throws Exception;
	Boolean checkDuplicateKey(String codeType, String codeId);
	ServiceResult<List<CodebookModelBean>> searchBySyncDate(Date lastUpdatedDate,Integer pageNo,Integer rowPerPage);
	
	ServiceResult<List<CodebookModelBean>> searchByCodeType(String codeType, String langCd);
	 ServiceResult<List<CodebookModelBean>> searchByCodeTypeWithDisable(String codeType, String langCd);
	ServiceResult<List<CodebookModelBean>> searchByParentTypeAndParentId(String parentType,String parentId);
	ServiceResult<List<CodebookModelBean>> searchAllByChannel(String channel);
	
	
}
