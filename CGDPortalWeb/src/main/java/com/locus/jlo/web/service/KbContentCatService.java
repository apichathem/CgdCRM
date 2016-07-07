/**
 * 
 */
package com.locus.jlo.web.service;

import java.util.List;

import com.locus.common.domain.ServiceResult;
import com.locus.jlo.web.bean.dto.KbContentCatDTO;

/**
 * @author Mr.BoonOom
 * 
 */
public interface KbContentCatService {

	ServiceResult<List<KbContentCatDTO>> getKbContentCat1List() throws Exception;

	ServiceResult<List<KbContentCatDTO>> getKbContentCat2ByParentCatIdList(String parentCatId) throws Exception;

	ServiceResult<List<KbContentCatDTO>> getKbContentCat3ByParentCatIdList(String parentCatId) throws Exception;

	ServiceResult<List<KbContentCatDTO>> getKbContentCat4ByParentCatIdList(String parentCatId) throws Exception;

	ServiceResult<List<KbContentCatDTO>> getKbContentCat5ByParentCatIdList(String parentCatId) throws Exception;

}
