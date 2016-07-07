package com.locus.jlo.web.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.locus.common.domain.ServiceResult;
import com.locus.jlo.web.bean.dto.AttDTO;
import com.locus.jlo.web.bean.dto.ContentAddrDTO;
import com.locus.jlo.web.bean.dto.ContentAttDTO;
import com.locus.jlo.web.bean.dto.ContentCatDTO;
import com.locus.jlo.web.bean.dto.ContentDTO;
import com.locus.jlo.web.bean.dto.ContentKeywordDTO;

public interface ContentService {
	ServiceResult<Page<ContentDTO>> searchContent(ContentCatDTO contentCat, ContentDTO content,Pageable pageable);
	ServiceResult<List<ContentDTO>> searchContent(ContentCatDTO contentCat, ContentDTO content);

	ServiceResult<ContentDTO> searchById(String contentId);

	ServiceResult<ContentAddrDTO> searchContentAddrByContentId(String contentId);

	ServiceResult<ContentCatDTO> searchContentCatById(String catId);
	
	ServiceResult<List<ContentCatDTO>> searchContentCatByType(String type);
	
	ServiceResult<List<ContentKeywordDTO>> searchContentKeywordByContentId(String contentId);

	ServiceResult<Long> updateContentAddrLatLng(String contentId, String lat_lng);

	ServiceResult<Page<ContentAttDTO>> searchContentAttByContentId(String contentId,Pageable pageable);
	ServiceResult<List<ContentAttDTO>> searchContentAttByContentId(String contentId);

	ServiceResult<Long> insertContentAtt(ContentAttDTO att, AttDTO attDTO);

	ServiceResult<List<ContentCatDTO>> searchContentCatByParent(ContentCatDTO contentCat);
	
	ServiceResult<List<ContentCatDTO>> searchContentCatByContentType(ContentCatDTO contentCat);

//	ServiceResult<List<CodebookInfo>> searchCodeBookByTypeParent(String type,
//			String parent);

//	ServiceResult<List<CodebookInfo>> searchCodeBookByType(String type);

	ServiceResult<Long> insertContentCat(ContentCatDTO contentCat);

	ServiceResult<Long> updateContentCat(ContentCatDTO contentCat);

	ServiceResult<Long> deleteContentCat(ContentCatDTO contentCat);

	boolean isChildrenCat(ContentCatDTO contentCat);

	boolean isRootCat(ContentCatDTO contentCat);
	
//	ServiceResult<List<ContentDTO>> searchContent(ContentCatDTO cat);
	Long insertContent(ContentDTO obj);
	Long updateContent(ContentDTO obj);
	Long deleteContent(ContentDTO obj);
	Long updateContentAddr(ContentAddrDTO contentAddr);
	Long updateContentAtt(ContentAttDTO att);
	Long deleteContentAtt(ContentAttDTO att, AttDTO attDTO);
	ServiceResult<ContentAttDTO> searchMainPageByContentId(String contentId);
	Long deleteContentKeyword(String contentId, String tagname);
	Long insertContentKeyword(ContentKeywordDTO dto);
	ServiceResult<Page<ContentDTO>> findContentforDialogAtt(Pageable pageable,
			String type, String name, String docname);
	ServiceResult<Page<ContentAttDTO>> findContentAttforDialogAtt(
			Pageable pageable, String contentId, String docName);
	Long updateContentSummary(ContentDTO obj);
	Long removeMainFlag(ContentAttDTO att);
	
}
