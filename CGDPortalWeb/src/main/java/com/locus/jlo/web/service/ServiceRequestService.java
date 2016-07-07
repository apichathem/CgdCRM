/**
 * 
 */
package com.locus.jlo.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.locus.common.domain.ServiceResult;
import com.locus.jlo.web.bean.criteria.ServiceRequestCriteria;
import com.locus.jlo.web.bean.dto.ServiceRequestActDTO;
import com.locus.jlo.web.bean.dto.ServiceRequestContentDTO;
import com.locus.jlo.web.bean.dto.ServiceRequestDTO;
import com.locus.jlo.web.bean.dto.ServiceRequestSolutionDTO;

/**
 * @author Mr.BoonOom
 *
 */
public interface ServiceRequestService{
	
	
	//Service Request Detail Top Panel
	ServiceResult<Page<ServiceRequestDTO>> searchServiceRequestByCriteria(Pageable pageable,  ServiceRequestCriteria srCriteria)throws Exception;
	ServiceResult<ServiceRequestDTO> searchServiceRequestDetailBySrID(String sr_number,String langCd) throws Exception;
	ServiceResult<Long> updateServiceRequestDetail(ServiceRequestDTO srDTO) throws Exception;
	ServiceResult<Long> insertServiceRequest(ServiceRequestDTO srDTO) throws Exception;
	ServiceResult<Map<String, Object>> insertServiceRequestProcedure(ServiceRequestDTO srDTO) throws Exception;
	ServiceResult<Map<String, Object>> updateServiceRequestDetailProcedure(ServiceRequestDTO srDTO) throws Exception;
	ServiceResult<Long> updateServiceRequestNoteDescription(ServiceRequestDTO srDTO) throws Exception;
	
	
	//Solution Tab
	ServiceResult<Page<ServiceRequestSolutionDTO>> searchKnowledgeBaseContentByKbNumber(Pageable pageable, String contentNumber,String langCd)throws Exception;
	ServiceResult<Long> insertServiceRequestContent(ServiceRequestContentDTO srContentDTO) throws Exception;
	ServiceResult<Page<ServiceRequestContentDTO>> searchContentServiceRequestByRefDocNo(Pageable pageable, String srNumber,String langCd)throws Exception;
	ServiceResult<Long> deleteSrContentByKeyRef(String srNumber,String srContentId) throws Exception;
	ServiceResult<ServiceRequestSolutionDTO> searchKnowledgeBaseContentDetailByKbNumber(String contentNumber,String langCd)throws Exception;
	
	//Activity Tab 
	ServiceResult<Page<ServiceRequestActDTO>> searchSRActivityListDetailByRefDocNo(Pageable pageable, String refDocNo,String langCd)throws Exception;
	ServiceResult<ServiceRequestActDTO> searchSRActivityListDetailByActNumber(String actNumber,String langCd) throws Exception;
	ServiceResult<Long> insertActivity(ServiceRequestActDTO actDTO) throws Exception;
	ServiceResult<Long> updateActivity(ServiceRequestActDTO actDTO) throws Exception;
	ServiceResult<Long> deleteActivity(String actNumber,String refDocNo) throws Exception;
	
	// Consulting Module 
	ServiceResult<Page<ServiceRequestDTO>> searchServiceRequestByConsultingNumber(Pageable pageable,  ServiceRequestCriteria srCriteria)throws Exception;
	 
	int countActivityUnderRefDocNo(String refDocNo) throws Exception; 
}
