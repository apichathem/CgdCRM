package com.locus.jlo.web.service.impl;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import com.locus.common.domain.ServiceResult;
import com.locus.common.domain.SimpleKeyValue;
import com.locus.common.mapper.PrimitiveSafeBeanPropertyRowMapper;
import com.locus.jlo.web.bean.criteria.ServiceRequestCriteria;
import com.locus.jlo.web.bean.dto.ServiceRequestActDTO;
import com.locus.jlo.web.bean.dto.ServiceRequestContentDTO;
import com.locus.jlo.web.bean.dto.ServiceRequestDTO;
import com.locus.jlo.web.bean.dto.ServiceRequestSolutionDTO;
import com.locus.jlo.web.service.ServiceRequestService;

/**
 * @author Mr.BoonOom
 * 
 */
@Service
public class ServiceRequestServiceImpl extends BaseService implements ServiceRequestService {

	private Logger logger = Logger.getLogger(getClass());

	// ServiceRequest Detail Top Panel
	public static final String SQL_FIND_LIST_SR_BY_CRITERIA = "SR.SQL_SEARCH_SR_BY_CRITERIA";
	public static final String SQL_FIND_DESC_SR_BY_SR_NUMBER = "SR.SQL_SEARCH_DETAIL_SR_BY_SR_NUMBER";
	public static final String SQL_UPDATE_DESC_SR_BY_SR_NUMBER = "SR.SQL_UPDATE_DETAIL_SR_BY_SR_NUMBER";
	public static final String SQL_UPDATE_NOTE_DESC_SR_BY_SR_NUMBER = "SR.SQL_UPDATE_NOTE_DESC_SR_BY_SR_NUMBER";
	public static final String SQL_INSERT_SERVICE_REQUEST = "SR.SQL_INSERT_SERVICE_REQUEST";

	// Solution Tab
	public static final String SQL_INSERT_SERVICE_REQUEST_CONTENT = "SR.SQL_INSERT_SERVICE_REQUEST_CONTENT";
	public static final String SQL_SEARCH_SOLUTION_KNOWLEDGE_BASE = "SR.SQL_SEARCH_SOLUTION_KNOWLEDGE_BASE";
	public static final String SQL_SEARCH_SOLUTION_KNOWLEDGE_BASE_DETAIL = "SR.SQL_SEARCH_SOLUTION_KNOWLEDGE_BASE_DETAIL";
	public static final String SQL_SEARCH_CONTENT_BY_SERVICE_REQUEST_NUMBER = "SR.SQL_SEARCH_CONTENT_BY_SERVICE_REQUEST_NUMBER";
	public static final String SQL_DELETE_SERVICE_REQ_CONTENT_BY_KEY = "SR.SQL_DELETE_SERVICE_REQ_CONTENT_BY_KEY";

	// Activity Tab Under ServiceRequest
	public static final String SQL_FIND_ACTIVITY_LIST_BY_SR_NUMBER = "SR.SQL_SEARCH_ACTIVITY_LIST_SR_BY_SR_NUMBER";
	public static final String SQL_INSERT_ACTIVITY_UNDER_SERVICE_REQUEST = "SR.SQL_INSERT_ACTIVITY_UNDER_SERVICE_REQUEST";
	public static final String SQL_UPDATE_ACTIVITY_UNDER_SERVICE_REQUEST = "SR.SQL_UPDATE_ACTIVITY_UNDER_SERVICE_REQUEST";
	public static final String SQL_DELETE_ACTIVITY_UNDER_SERVICE_REQUEST = "SR.SQL_DELETE_ACTIVITY_UNDER_SERVICE_REQUEST";
	public static final String SQL_SEARCH_ACTIVITY_DETAIL_BY_ACT_NUMBER = "SR.SQL_SEARCH_ACTIVITY_DETAIL_BY_ACT_NUMBER";

	// Consulting Module
	public static final String SQL_SEARCH_SR_LIST_BY_CONSULTING_NUMBER = "SR.SQL_SEARCH_SR_LIST_BY_CONSULTING_NUMBER";

	// for count check activity under service request or parent
	public static final String SQL_COUNT_TP_ACTIVITY_UNDER_REFDOCNO = "SR.SQL_COUNT_TP_ACTIVITY_UNDER_REFDOCNO";

	/**
	 * 
	 */
	public ServiceRequestServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ServiceResult<Page<ServiceRequestDTO>> searchServiceRequestByCriteria(Pageable pageable, ServiceRequestCriteria srCriteria) throws Exception {
		logger.info("+++++++++++++ In ServiceRequestServiceImpl ++++++++++++++++++++");
		ServiceResult<Page<ServiceRequestDTO>> serviceResult = null;
		Page<ServiceRequestDTO> result = null;

		try {
			logger.info("+++++++++++++ In Befor findForPage ++++++++++++++++++++");
			result = dynamicJdbcDao.findForPage(SQL_FIND_LIST_SR_BY_CRITERIA, PrimitiveSafeBeanPropertyRowMapper.newInstance(ServiceRequestDTO.class), pageable, srCriteria);
			logger.info("+++++++++++++ In Befor result :" + result.getSize());
			serviceResult = new ServiceResult<Page<ServiceRequestDTO>>(result);
			logger.info("Element result :: " + serviceResult.getResult().getTotalElements());

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			serviceResult = new ServiceResult<Page<ServiceRequestDTO>>(e);
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<ServiceRequestDTO> searchServiceRequestDetailBySrID(String sr_number, String langCd) {
		ServiceResult<ServiceRequestDTO> serviceResult = null;
		ServiceRequestDTO srDetailDTO = new ServiceRequestDTO();
		logger.info("+++++++++++++ In searchServiceRequestDetailBySrID ++++++++++++++++++++");
		try {

			srDetailDTO = dynamicJdbcDao.findForObject(SQL_FIND_DESC_SR_BY_SR_NUMBER, BeanPropertyRowMapper.newInstance(ServiceRequestDTO.class), new SimpleKeyValue("sr_number", sr_number),
					new SimpleKeyValue("langCd", langCd));

			serviceResult = new ServiceResult<ServiceRequestDTO>(srDetailDTO);
			logger.info("+++++++++++++ End serviceResult  ++++++++++++++++++++");

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			serviceResult = new ServiceResult<ServiceRequestDTO>(e);
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<Long> updateServiceRequestDetail(ServiceRequestDTO srDTO) throws Exception {

		long result = 0;
		ServiceResult<Long> serviceResult = null;
		try {
			result = dynamicJdbcDao.executeUpdate(SQL_UPDATE_DESC_SR_BY_SR_NUMBER, srDTO);
			serviceResult = new ServiceResult<Long>(new Long(result));

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			serviceResult = new ServiceResult<Long>(e);
		}

		return serviceResult;
	}

	@Override
	public ServiceResult<Long> insertServiceRequest(ServiceRequestDTO srDTO) throws Exception {

		Long result = null;
		ServiceResult<Long> serviceResult = null;

		try {

			result = dynamicJdbcDao.executeInsert(SQL_INSERT_SERVICE_REQUEST, false, new SimpleKeyValue("srNumber", srDTO.getSrNumber()), new SimpleKeyValue("srCustId", srDTO.getSrCustId()),
					new SimpleKeyValue("srContactId", srDTO.getSrContactId()), new SimpleKeyValue("srCustType", srDTO.getSrCustType()), new SimpleKeyValue("srStatusCd", srDTO.getSrStatusCd()),
					new SimpleKeyValue("srpriorityCd", srDTO.getSrpriorityCd()), new SimpleKeyValue("srChannelCd", srDTO.getSrChannelCd()), new SimpleKeyValue("srOwnerId", srDTO.getSrOwnerId()),
					new SimpleKeyValue("srCloseDate", srDTO.getSrCloseDate()), new SimpleKeyValue("srOpenedDate", srDTO.getSrOpenedDate()), new SimpleKeyValue("srDueDate", srDTO.getSrDueDate()),
					new SimpleKeyValue("srSubject", srDTO.getSrSubject()), new SimpleKeyValue("srDescription", srDTO.getSrDescription()), new SimpleKeyValue("regId", srDTO.getRegId()),
					new SimpleKeyValue("regDt", srDTO.getRegDt()), new SimpleKeyValue("srConsultingNumber", srDTO.getSrConsultingNumber()),
					new SimpleKeyValue("srTypeProblem1Cd", srDTO.getSrTypeProblem1Cd()), new SimpleKeyValue("srTypeProblem2Cd", srDTO.getSrTypeProblem2Cd()), new SimpleKeyValue("srTypeProblem3Cd",
							srDTO.getSrTypeProblem3Cd()), new SimpleKeyValue("srTypeProblem4Cd", srDTO.getSrTypeProblem4Cd()), new SimpleKeyValue("srTypeProblem5Cd", srDTO.getSrTypeProblem5Cd()));

			serviceResult = new ServiceResult<Long>(result);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			serviceResult = new ServiceResult<Long>(e);
		}

		logger.info("ID:" + result);
		return serviceResult;

	}

	@Override
	public ServiceResult<Page<ServiceRequestActDTO>> searchSRActivityListDetailByRefDocNo(Pageable pageable, String refDocNo, String langCd) throws Exception {

		logger.info("+++++++++++++ In searchSRActivityListDetailByRefDocNo ++++++++++++++++++++");
		ServiceResult<Page<ServiceRequestActDTO>> serviceResult = null;
		Page<ServiceRequestActDTO> result = null;

		try {
			logger.info("+++++++++++++ In Befor findForPage ++++++++++++++++++++");
			result = dynamicJdbcDao.findForPage(SQL_FIND_ACTIVITY_LIST_BY_SR_NUMBER, PrimitiveSafeBeanPropertyRowMapper.newInstance(ServiceRequestActDTO.class), pageable, new SimpleKeyValue(
					"refDocNo", refDocNo), new SimpleKeyValue("langCd", langCd));
			logger.info("+++++++++++++ In Befor result :" + result.getSize());

			serviceResult = new ServiceResult<Page<ServiceRequestActDTO>>(result);

			logger.info("Element result :: " + serviceResult.getResult().getTotalElements());

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			serviceResult = new ServiceResult<Page<ServiceRequestActDTO>>(e);
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<ServiceRequestActDTO> searchSRActivityListDetailByActNumber(String actNumber, String langCd) throws Exception {
		ServiceResult<ServiceRequestActDTO> srActList = null;
		ServiceRequestActDTO srActDTO = new ServiceRequestActDTO();
		logger.info("+++++++++++++ Begin searchSRActivityListDetailByActNumber ++++++++++++++++++++");
		try {

			srActDTO = dynamicJdbcDao.findForObject(SQL_SEARCH_ACTIVITY_DETAIL_BY_ACT_NUMBER, BeanPropertyRowMapper.newInstance(ServiceRequestActDTO.class),
					new SimpleKeyValue("actNumber", actNumber), new SimpleKeyValue("langCd", langCd));
			srActList = new ServiceResult<ServiceRequestActDTO>(srActDTO);
			logger.info("+++++++++++++ End searchSRActivityListDetailByActNumber  ++++++++++++++++++++");

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			srActList = new ServiceResult<ServiceRequestActDTO>(e);
		}
		return srActList;
	}

	@Override
	public ServiceResult<Long> insertActivity(ServiceRequestActDTO actDTO) throws Exception {

		Long result = null;
		ServiceResult<Long> serviceResult = null;

		try {
			logger.info("+++++++++++++ In Befor executeInsert ++++++++++++++++++++");
			result = dynamicJdbcDao.executeInsert(SQL_INSERT_ACTIVITY_UNDER_SERVICE_REQUEST, false, new SimpleKeyValue("actNumber", actDTO.getActNumber()),
					new SimpleKeyValue("refDocNo", actDTO.getRefDocNo()), new SimpleKeyValue("attendTo", actDTO.getAttendTo()), new SimpleKeyValue("title", actDTO.getTitle()), new SimpleKeyValue(
							"typeCd", actDTO.getTypeCd()), new SimpleKeyValue("description", actDTO.getDescription()), new SimpleKeyValue("phoneNo", actDTO.getPhoneNo()), new SimpleKeyValue("smsNo",
							actDTO.getSmsNo()), new SimpleKeyValue("faxNo", actDTO.getFaxNo()), new SimpleKeyValue("email", actDTO.getEmail()), new SimpleKeyValue("address", actDTO.getAddress()),
					new SimpleKeyValue("statusCd", actDTO.getStatusCd()), new SimpleKeyValue("ownerId", actDTO.getOwnerId()), new SimpleKeyValue("ownerDeptCode", actDTO.getOwnerDeptCode()),
					new SimpleKeyValue("openedDt", actDTO.getOpenedDt()), new SimpleKeyValue("dueDt", actDTO.getDueDt()), new SimpleKeyValue("operDt", actDTO.getOperDt()), new SimpleKeyValue(
							"closedDt", actDTO.getClosedDt()), new SimpleKeyValue("regId", actDTO.getRegId()), new SimpleKeyValue("regDt", actDTO.getRegDt()));

			logger.info("+++++++++++++ In End executeInsert ++++++++++++++++++++");
			serviceResult = new ServiceResult<Long>(result);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			serviceResult = new ServiceResult<Long>(e);
		}

		logger.info("Insert Activity Result Code ID:" + result);

		return serviceResult;
	}

	@Override
	public ServiceResult<Long> updateActivity(ServiceRequestActDTO actDTO) throws Exception {

		logger.info("+++++++++++++ In Begin updateActivity  ++++++++++++++++++++");
		long result = 0;
		ServiceResult<Long> serviceResult = null;
		try {
			result = dynamicJdbcDao.executeUpdate(SQL_UPDATE_ACTIVITY_UNDER_SERVICE_REQUEST, actDTO);
			serviceResult = new ServiceResult<Long>(new Long(result));

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			serviceResult = new ServiceResult<Long>(e);
		}

		logger.info("+++++++++++++ In End updateActivity  ++++++++++++++++++++");

		return serviceResult;

	}

	@Override
	public ServiceResult<Long> deleteActivity(String actNumber, String refDocNo) throws Exception {
		long result = 0;
		ServiceResult<Long> serviceResult = new ServiceResult<Long>();
		try {
			result = dynamicJdbcDao.executeUpdate(SQL_UPDATE_DESC_SR_BY_SR_NUMBER, new SimpleKeyValue("actNumber", actNumber), new SimpleKeyValue("refDocNo", refDocNo));

			serviceResult = new ServiceResult<Long>(new Long(result));

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			serviceResult = new ServiceResult<Long>(e);
		}

		return serviceResult;
	}

	@Override
	public ServiceResult<Page<ServiceRequestDTO>> searchServiceRequestByConsultingNumber(Pageable pageable, ServiceRequestCriteria srCriteria) throws Exception {

		ServiceResult<Page<ServiceRequestDTO>> serviceResult = null;
		Page<ServiceRequestDTO> result = null;

		try {
			logger.info("+++++++++++++ In Befor findForPage ++++++++++++++++++++");
			result = dynamicJdbcDao.findForPage(SQL_SEARCH_SR_LIST_BY_CONSULTING_NUMBER, PrimitiveSafeBeanPropertyRowMapper.newInstance(ServiceRequestDTO.class), pageable, srCriteria);
			logger.info("+++++++++++++ In Befor result :" + result.getSize());
			serviceResult = new ServiceResult<Page<ServiceRequestDTO>>(result);
			logger.info("Element result :: " + serviceResult.getResult().getTotalElements());

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			serviceResult = new ServiceResult<Page<ServiceRequestDTO>>(e);
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<Page<ServiceRequestSolutionDTO>> searchKnowledgeBaseContentByKbNumber(Pageable pageable, String contentNumber, String langCd) throws Exception {

		logger.info("+++++++++++++ In searchKnowledgeBaseContentByKbNumber ++++++++++++++++++++");
		ServiceResult<Page<ServiceRequestSolutionDTO>> serviceResult = null;
		Page<ServiceRequestSolutionDTO> result = null;

		try {
			logger.info("+++++++++++++ In Befor findForPage ++++++++++++++++++++");
			result = dynamicJdbcDao.findForPage(SQL_SEARCH_SOLUTION_KNOWLEDGE_BASE, PrimitiveSafeBeanPropertyRowMapper.newInstance(ServiceRequestSolutionDTO.class), pageable, new SimpleKeyValue(
					"contentNumber", contentNumber), new SimpleKeyValue("langCd", langCd));
			logger.info("+++++++++++++ In Befor result :" + result.getSize());

			serviceResult = new ServiceResult<Page<ServiceRequestSolutionDTO>>(result);

			logger.info("Element result :: " + serviceResult.getResult().getTotalElements());

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			serviceResult = new ServiceResult<Page<ServiceRequestSolutionDTO>>(e);
		}

		return serviceResult;

	}

	@Override
	public ServiceResult<Long> insertServiceRequestContent(ServiceRequestContentDTO srContentDTO) throws Exception {
		Long result = null;
		ServiceResult<Long> serviceResult = null;

		try {

			result = dynamicJdbcDao.executeInsert(SQL_INSERT_SERVICE_REQUEST_CONTENT, false, new SimpleKeyValue("srNumber", srContentDTO.getSrNumber()),
					new SimpleKeyValue("srContentId", srContentDTO.getSrContentId()), new SimpleKeyValue("regId", srContentDTO.getRegId()), new SimpleKeyValue("regDt", srContentDTO.getRegDt()));

			serviceResult = new ServiceResult<Long>(result);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			serviceResult = new ServiceResult<Long>(e);
		}

		return serviceResult;
	}

	@Override
	public ServiceResult<Page<ServiceRequestContentDTO>> searchContentServiceRequestByRefDocNo(Pageable pageable, String srNumber, String langCd) throws Exception {
		logger.info("+++++++++++++ In searchSRActivityListDetailByRefDocNo ++++++++++++++++++++");
		ServiceResult<Page<ServiceRequestContentDTO>> serviceResult = null;
		Page<ServiceRequestContentDTO> result = null;

		try {
			logger.info("+++++++++++++ In Befor findForPage ++++++++++++++++++++");
			result = dynamicJdbcDao.findForPage(SQL_SEARCH_CONTENT_BY_SERVICE_REQUEST_NUMBER, PrimitiveSafeBeanPropertyRowMapper.newInstance(ServiceRequestContentDTO.class), pageable,
					new SimpleKeyValue("srNumber", srNumber), new SimpleKeyValue("langCd", langCd));
			logger.info("+++++++++++++ In Befor result :" + result.getSize());

			serviceResult = new ServiceResult<Page<ServiceRequestContentDTO>>(result);

			logger.info("Element result :: " + serviceResult.getResult().getTotalElements());

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			serviceResult = new ServiceResult<Page<ServiceRequestContentDTO>>(e);
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<Long> deleteSrContentByKeyRef(String srNumber, String srContentId) throws Exception {
		logger.info("+++++++++++++ In Begin deleteSrContentByKeyRef  ++++++++++++++++++++");

		long result = 0;
		ServiceResult<Long> serviceResult = new ServiceResult<Long>();

		try {
			result = dynamicJdbcDao.executeUpdate(SQL_DELETE_SERVICE_REQ_CONTENT_BY_KEY, new SimpleKeyValue("srNumber", srNumber), new SimpleKeyValue("srContentId", srContentId));

			serviceResult = new ServiceResult<Long>(new Long(result));

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			serviceResult = new ServiceResult<Long>(e);
		}

		logger.info("+++++++++++++ In End deleteAttachment  ++++++++++++++++++++");

		return serviceResult;
	}

	@Override
	public ServiceResult<ServiceRequestSolutionDTO> searchKnowledgeBaseContentDetailByKbNumber(String contentNumber, String langCd) throws Exception {

		ServiceResult<ServiceRequestSolutionDTO> serviceResult = new ServiceResult<ServiceRequestSolutionDTO>();
		ServiceRequestSolutionDTO solutionDTO = new ServiceRequestSolutionDTO();

		try {
			logger.info("+++++++++++++ In Befor findForPage ++++++++++++++++++++");
			solutionDTO = dynamicJdbcDao.findForObject(SQL_SEARCH_SOLUTION_KNOWLEDGE_BASE_DETAIL, PrimitiveSafeBeanPropertyRowMapper.newInstance(ServiceRequestSolutionDTO.class), new SimpleKeyValue(
					"contentNumber", contentNumber), new SimpleKeyValue("langCd", langCd));

			// serviceResult = new
			// ServiceResult<ServiceRequestSolutionDTO>(solutionDTO);

			serviceResult.setSuccess(Boolean.TRUE);
			serviceResult.setResult(solutionDTO);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			serviceResult = new ServiceResult<ServiceRequestSolutionDTO>(e);
		}

		return serviceResult;
	}

	@Override
	public int countActivityUnderRefDocNo(String refDocNo) throws Exception {
		try {

			return dynamicJdbcDao.findForLong(SQL_COUNT_TP_ACTIVITY_UNDER_REFDOCNO, new SimpleKeyValue("refDocNo", refDocNo)).intValue();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return 0;
		}

	}

	@Override
	public ServiceResult<Map<String, Object>> insertServiceRequestProcedure(ServiceRequestDTO srDTO) throws Exception {

		ServiceResult<Map<String, Object>> serviceResult = null;
		try {
			Map<String, Object> mapResult = dynamicJdbcDao.executeCall("INSERT_SR_PROC", new SimpleKeyValue("SR_NUMBER", srDTO.getSrNumber()), new SimpleKeyValue("SR_CUST_ID", srDTO.getSrCustId()),
					new SimpleKeyValue("SR_CONT_ID", srDTO.getSrContactId()), new SimpleKeyValue("SR_CUST_TYPE", srDTO.getSrCustType()), new SimpleKeyValue("SR_STATUS_CD", srDTO.getSrStatusCd()),
					new SimpleKeyValue("SR_PRIORITY_CD", srDTO.getSrpriorityCd()), new SimpleKeyValue("SR_CHANNEL_CD", srDTO.getSrChannelCd()),
					new SimpleKeyValue("SR_OWNER_ID", srDTO.getSrOwnerId()), new SimpleKeyValue("SR_GROUP", "SR"), new SimpleKeyValue("SR_SUBJECT", srDTO.getSrSubject()), new SimpleKeyValue(
							"SR_DESCRIPTION", srDTO.getSrDescription()), new SimpleKeyValue("SR_NOTE_DESCRIPTION", srDTO.getSrNoteDescription()), new SimpleKeyValue("REG_ID", srDTO.getRegId()),
					new SimpleKeyValue("SR_CONSULTING_NUMBER", srDTO.getSrConsultingNumber()), new SimpleKeyValue("SR_TYPEPROBLEM1_CD", srDTO.getSrTypeProblem1Cd()), new SimpleKeyValue(
							"SR_TYPEPROBLEM2_CD", srDTO.getSrTypeProblem2Cd()), new SimpleKeyValue("SR_TYPEPROBLEM3_CD", srDTO.getSrTypeProblem3Cd()),
					new SimpleKeyValue("SR_TYPEPROBLEM4_CD", srDTO.getSrTypeProblem4Cd()), new SimpleKeyValue("SR_TYPEPROBLEM5_CD", srDTO.getSrTypeProblem5Cd()),
					new SimpleKeyValue("SR_SLA_ID", srDTO.getSrpriorityCd()));

			serviceResult = new ServiceResult<Map<String, Object>>(mapResult);

		} catch (Exception e) {

			logger.error(e.getMessage(), e);
			serviceResult = new ServiceResult<Map<String, Object>>(e);

		}

		return serviceResult;
	}

	@Override
	public ServiceResult<Map<String, Object>> updateServiceRequestDetailProcedure(ServiceRequestDTO srDTO) throws Exception {
		ServiceResult<Map<String, Object>> serviceResult = null;
		try {
			Map<String, Object> mapResult = dynamicJdbcDao.executeCall("UPDATE_SR_PROC", new SimpleKeyValue("SR_NUMBER", srDTO.getSrNumber()), new SimpleKeyValue("SR_CUST_ID", srDTO.getSrCustId()),
					new SimpleKeyValue("SR_CONT_ID", srDTO.getSrContactId()), new SimpleKeyValue("SR_CUST_TYPE", srDTO.getSrCustType()), new SimpleKeyValue("SR_STATUS_CD", srDTO.getSrStatusCd()),
					new SimpleKeyValue("SR_PRIORITY_CD", srDTO.getSrpriorityCd()), new SimpleKeyValue("SR_CHANNEL_CD", srDTO.getSrChannelCd()),
					new SimpleKeyValue("SR_OWNER_ID", srDTO.getSrOwnerId()), new SimpleKeyValue("SR_GROUP", "SR"), new SimpleKeyValue("SR_SUBJECT", srDTO.getSrSubject()), new SimpleKeyValue(
							"SR_DESCRIPTION", srDTO.getSrDescription()), new SimpleKeyValue("SR_NOTE_DESCRIPTION", srDTO.getSrNoteDescription()), new SimpleKeyValue("CHG_ID", srDTO.getChgId()),
					new SimpleKeyValue("SR_CONSULTING_NUMBER", srDTO.getSrConsultingNumber()), new SimpleKeyValue("SR_TYPEPROBLEM1_CD", srDTO.getSrTypeProblem1Cd()), new SimpleKeyValue(
							"SR_TYPEPROBLEM2_CD", srDTO.getSrTypeProblem2Cd()), new SimpleKeyValue("SR_TYPEPROBLEM3_CD", srDTO.getSrTypeProblem3Cd()),
					new SimpleKeyValue("SR_TYPEPROBLEM4_CD", srDTO.getSrTypeProblem4Cd()), new SimpleKeyValue("SR_TYPEPROBLEM5_CD", srDTO.getSrTypeProblem5Cd()));

			serviceResult = new ServiceResult<Map<String, Object>>(mapResult);

		} catch (Exception e) {

			logger.error(e.getMessage(), e);
			serviceResult = new ServiceResult<Map<String, Object>>(e);

		}

		return serviceResult;
	}

	@Override
	public ServiceResult<Long> updateServiceRequestNoteDescription(ServiceRequestDTO srDTO) throws Exception {

		long result = 0;
		ServiceResult<Long> serviceResult = null;
		try {
			result = dynamicJdbcDao.executeUpdate(SQL_UPDATE_NOTE_DESC_SR_BY_SR_NUMBER, srDTO);
			serviceResult = new ServiceResult<Long>(new Long(result));

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			serviceResult = new ServiceResult<Long>(e);
		}

		return serviceResult;
	}

}
