package com.locus.jlo.web.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.locus.common.domain.ServiceResult;
import com.locus.jlo.web.bean.criteria.ContentDetailCriteria;
import com.locus.jlo.web.bean.dto.ContentAttDTO;
import com.locus.jlo.web.bean.dto.ContentCategoryDTO;
import com.locus.jlo.web.bean.dto.ContentDetailDTO;

public interface KnowledgeBaseService {
	/* Category*/
	ServiceResult<List<ContentCategoryDTO>> getContentCategoryList(String cateTypeCd, String cateStatusCd, List<String> ownerFilter);
	ServiceResult<List<ContentCategoryDTO>> getContentCategoryList(String contentCateId, Integer level, String cateTypeCd, String cateStatusCd, List<String> ownerFilter);
	ServiceResult<Page<ContentCategoryDTO>> getContentCategoryList(Integer level, String keyword, Pageable pageable, List<String> ownerFilter);
	ServiceResult<ContentCategoryDTO> getContentCategory(String contentCateId, Integer level);
	ServiceResult<ContentCategoryDTO> updateCategory(ContentCategoryDTO contentCategoryDTO);
	ServiceResult<ContentCategoryDTO> insertCategory(ContentCategoryDTO contentCategoryDTO);
	ServiceResult<Boolean> deleteCategory(String contentCateId, Integer level);
	
	/* Detail info*/
	ServiceResult<Page<ContentDetailDTO>> getContentDetailList(ContentDetailCriteria criteria, Pageable pageable);
	ServiceResult<List<ContentDetailDTO>> getContentDetailList(ContentDetailCriteria criteria);
	ServiceResult<ContentDetailDTO> getContentDetail(Integer contentId);
	ServiceResult<Boolean> updateContent(ContentDetailDTO contentDetailDTO);
	ServiceResult<Integer> insertContent(ContentDetailDTO contentDetailDTO);
	ServiceResult<Page<ContentDetailDTO>> getKBWaitingList(Pageable pageable, List<String> ownerFilter, String langCd);
	ServiceResult<Boolean> updateContentStatus(List<String> contentIds, Integer userId, String statusCd);
	ServiceResult<Boolean> updateContentStatus(Integer contentId, Integer userId, String statusCd);
	ServiceResult<List<ContentAttDTO>> getContentAttByContentId(String contentId);
	ServiceResult<List<ContentDetailDTO>> getContentDetailListForExport(ContentDetailCriteria criteria);
	ServiceResult<List<ContentCategoryDTO>> getContentCategoryListForExport(String contentCateId, Integer level, String cateTypeCd, String cateStatusCd, List<String> ownerFilter);
	ServiceResult<List<ContentDetailDTO>> getAllCategoryForExport();
}
