package com.locus.jlo.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import com.locus.common.domain.ServiceResult;
import com.locus.common.domain.SimpleKeyValue;
import com.locus.common.mapper.PrimitiveSafeBeanPropertyRowMapper;
import com.locus.common.utils.CollectionUtil;
import com.locus.jlo.web.bean.criteria.ContentDetailCriteria;
import com.locus.jlo.web.bean.dto.ContentAttDTO;
import com.locus.jlo.web.bean.dto.ContentCategoryDTO;
import com.locus.jlo.web.bean.dto.ContentDetailDTO;
import com.locus.jlo.web.constant.JLOWebConstant;
import com.locus.jlo.web.constant.SequenceType;
import com.locus.jlo.web.service.KnowledgeBaseService;
import com.locus.jlo.web.service.SequenceGeneratorService;

@Service
public class KnowledgeBaseServiceImpl extends BaseService implements KnowledgeBaseService {
	private static final Logger logger = Logger.getLogger(KnowledgeBaseServiceImpl.class);

	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;

	private static final String SQL_FIND_CATE1_LIST = "KB.SQL_FIND_CATE1_LIST";
	private static final String SQL_FIND_CATE2_LIST = "KB.SQL_FIND_CATE2_LIST";
	private static final String SQL_FIND_CATE3_LIST = "KB.SQL_FIND_CATE3_LIST";
	private static final String SQL_FIND_CATE4_LIST = "KB.SQL_FIND_CATE4_LIST";
	private static final String SQL_FIND_CATE5_LIST = "KB.SQL_FIND_CATE5_LIST";
	private static final String SQL_FIND_CATE1 = "KB.SQL_FIND_CATE1";
	private static final String SQL_FIND_CATE2 = "KB.SQL_FIND_CATE2";
	private static final String SQL_FIND_CATE3 = "KB.SQL_FIND_CATE3";
	private static final String SQL_FIND_CATE4 = "KB.SQL_FIND_CATE4";
	private static final String SQL_FIND_CATE5 = "KB.SQL_FIND_CATE5";
	private static final String SQL_DELETE_CATEGORY_1 = "KB.SQL_DELETE_CATEGORY1";
	private static final String SQL_DELETE_CATEGORY_2 = "KB.SQL_DELETE_CATEGORY2";
	private static final String SQL_DELETE_CATEGORY_3 = "KB.SQL_DELETE_CATEGORY3";
	private static final String SQL_DELETE_CATEGORY_4 = "KB.SQL_DELETE_CATEGORY4";
	private static final String SQL_DELETE_CATEGORY_5 = "KB.SQL_DELETE_CATEGORY5";
	private static final String SQL_UPDATE_CATEGORY1 = "KB.SQL_UPDATE_CATEGORY1";
	private static final String SQL_UPDATE_CATEGORY2 = "KB.SQL_UPDATE_CATEGORY2";
	private static final String SQL_UPDATE_CATEGORY3 = "KB.SQL_UPDATE_CATEGORY3";
	private static final String SQL_UPDATE_CATEGORY4 = "KB.SQL_UPDATE_CATEGORY4";
	private static final String SQL_UPDATE_CATEGORY5 = "KB.SQL_UPDATE_CATEGORY5";
	private static final String SQL_INSERT_CATEGORY1 = "KB.SQL_INSERT_CATEGORY1";
	private static final String SQL_INSERT_CATEGORY2 = "KB.SQL_INSERT_CATEGORY2";
	private static final String SQL_INSERT_CATEGORY3 = "KB.SQL_INSERT_CATEGORY3";
	private static final String SQL_INSERT_CATEGORY4 = "KB.SQL_INSERT_CATEGORY4";
	private static final String SQL_INSERT_CATEGORY5 = "KB.SQL_INSERT_CATEGORY5";
	private static final String SQL_FIND_CATE1_LIST_BY_KEYWORD = "KB.SQL_FIND_CATE1_LIST_BY_KEYWORD";
	private static final String SQL_FIND_CATE2_LIST_BY_KEYWORD = "KB.SQL_FIND_CATE2_LIST_BY_KEYWORD";
	private static final String SQL_FIND_CATE3_LIST_BY_KEYWORD = "KB.SQL_FIND_CATE3_LIST_BY_KEYWORD";
	private static final String SQL_FIND_CATE4_LIST_BY_KEYWORD = "KB.SQL_FIND_CATE4_LIST_BY_KEYWORD";
	private static final String SQL_FIND_CATE5_LIST_BY_KEYWORD = "KB.SQL_FIND_CATE5_LIST_BY_KEYWORD";
	private static final String SQL_FIND_CONTENT_BY_CRITERIA = "KB.SQL_FIND_CONTENT_BY_CRITERIA";
	private static final String SQL_INSERT_CONTENT = "KB.SQL_INSERT_CONTENT";
	private static final String SQL_UPDATE_CONTENT = "KB.SQL_UPDATE_CONTENT";
	private static final String SQL_FIND_CONTENT_ATT_BY_CONTENT_ID = "KB.SQL_FIND_CONTENT_ATT_BY_CONTENT_ID";
	private static final String SQL_FIND_WAITING_APPRVOVE_LIST = "KB.SQL_FIND_WAITING_APPRVOVE_LIST";
	private static final String SQL_UPDATE_KB_STATUS_CODE = "KB.SQL_UPDATE_KB_STATUS_CODE";
	private static final String SQL_UPDATE_KB_STATUS_CODE_2 = "KB.SQL_UPDATE_KB_STATUS_CODE_2";
	private static final String SQL_FIND_CONTENT = "KB.SQL_FIND_CONTENT";
	private static final String SQL_EXPORT_CONTENT ="KB.SQL_EXPORT_CONTENT";
	private static final String SQL_EXPORT_CATEGORY1 = "KB.SQL_EXPORT_CATEGORY1";
	private static final String SQL_EXPORT_CATEGORY2 = "KB.SQL_EXPORT_CATEGORY2";
	private static final String SQL_EXPORT_CATEGORY3 = "KB.SQL_EXPORT_CATEGORY3";
	private static final String SQL_EXPORT_CATEGORY4 = "KB.SQL_EXPORT_CATEGORY4";
	private static final String SQL_EXPORT_CATEGORY5 = "KB.SQL_EXPORT_CATEGORY5";
	private static final String SQL_EXPORT_ALL_CATEGORY = "KB.SQL_EXPORT_ALL_CATEGORY";

	@Override
	public ServiceResult<List<ContentCategoryDTO>> getContentCategoryList(String cateTypeCd, String cateStatusCd, List<String> ownerFilter) {
		return getContentCategoryList(null, 0, cateTypeCd, cateStatusCd, ownerFilter);
	}

	@Override
	public ServiceResult<List<ContentCategoryDTO>> getContentCategoryList(String contentCateId, Integer level, String cateTypeCd, String cateStatusCd, List<String> ownerFilter) {
		ServiceResult<List<ContentCategoryDTO>> result = new ServiceResult<List<ContentCategoryDTO>>();

		try {
			
			List<ContentCategoryDTO> res = getCategoryList(contentCateId, level, cateTypeCd, cateStatusCd, ownerFilter);
			result.setResult(res);
			result.setSuccess(Boolean.TRUE);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			setErrorResult(result, e);
		}

		return result;
	}

	@Override
	public ServiceResult<ContentCategoryDTO> getContentCategory(String contentCateId, Integer level) {
		ServiceResult<ContentCategoryDTO> result = new ServiceResult<ContentCategoryDTO>();

		try {
			String sqlQuery = "";
			switch (level) {
			case 1:
				sqlQuery = SQL_FIND_CATE1;
				break;
			case 2:
				sqlQuery = SQL_FIND_CATE2;
				break;

			case 3:
				sqlQuery = SQL_FIND_CATE3;
				break;

			case 4:
				sqlQuery = SQL_FIND_CATE4;
				break;

			case 5:
				sqlQuery = SQL_FIND_CATE5;
				break;

			default:
				throw new Exception("Invalid level value");
			}

			ContentCategoryDTO contentCategoryDTO = dynamicJdbcDao.findForObject(sqlQuery, PrimitiveSafeBeanPropertyRowMapper.newInstance(ContentCategoryDTO.class), new SimpleKeyValue("contentCatId",
					contentCateId), new SimpleKeyValue("level", level));

			result.setSuccess(Boolean.TRUE);
			result.setResult(contentCategoryDTO);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			setErrorResult(result, e);
		}

		return result;
	}

	@Override
	public ServiceResult<ContentCategoryDTO> updateCategory(ContentCategoryDTO contentCategoryDTO) {
		ServiceResult<ContentCategoryDTO> result = new ServiceResult<ContentCategoryDTO>();

		try {
			String sqlQuery = "";
			switch (contentCategoryDTO.getLevel()) {
			case 1:
				sqlQuery = SQL_UPDATE_CATEGORY1;
				break;

			case 2:
				sqlQuery = SQL_UPDATE_CATEGORY2;
				break;

			case 3:
				sqlQuery = SQL_UPDATE_CATEGORY3;
				break;

			case 4:
				sqlQuery = SQL_UPDATE_CATEGORY4;
				break;

			case 5:
				sqlQuery = SQL_UPDATE_CATEGORY5;
				break;

			default:
				throw new Exception("Invalid level value");
			}

			int res = dynamicJdbcDao.executeUpdate(sqlQuery, contentCategoryDTO);
			result.setSuccess((res > 0) ? Boolean.TRUE : Boolean.FALSE);
			result.setResult(contentCategoryDTO);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			setErrorResult(result, e);
		}

		return result;
	}

	@Override
	public ServiceResult<ContentCategoryDTO> insertCategory(ContentCategoryDTO contentCategoryDTO) {
		ServiceResult<ContentCategoryDTO> result = new ServiceResult<ContentCategoryDTO>();

		try {
			String sqlQuery = "";
			String contentCatId = "";

			switch (contentCategoryDTO.getLevel()) {
			case 1:
				sqlQuery = SQL_INSERT_CATEGORY1;
				contentCatId = sequenceGeneratorService.generateByType(SequenceType.CATEGORY1);
				break;

			case 2:
				sqlQuery = SQL_INSERT_CATEGORY2;
				contentCatId = sequenceGeneratorService.generateByType(SequenceType.CATEGORY2);
				break;

			case 3:
				sqlQuery = SQL_INSERT_CATEGORY3;
				contentCatId = sequenceGeneratorService.generateByType(SequenceType.CATEGORY3);
				break;

			case 4:
				sqlQuery = SQL_INSERT_CATEGORY4;
				contentCatId = sequenceGeneratorService.generateByType(SequenceType.CATEGORY4);
				break;

			case 5:
				sqlQuery = SQL_INSERT_CATEGORY5;
				contentCatId = sequenceGeneratorService.generateByType(SequenceType.CATEGORY5);
				break;

			default:
				throw new Exception("Invalid level value");
			}

			logger.info("ContentCatId : " + contentCatId);
			contentCategoryDTO.setContentCatId(contentCatId);
			int res = dynamicJdbcDao.executeUpdate(sqlQuery, contentCategoryDTO);
			result.setSuccess((res > 0) ? Boolean.TRUE : Boolean.FALSE);
			result.setResult(contentCategoryDTO);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			setErrorResult(result, e);
		}

		return result;
	}

	@Override
	public ServiceResult<Boolean> deleteCategory(String contentCateId, Integer level) {

		ServiceResult<Boolean> result = new ServiceResult<Boolean>();

		try {
			String sqlQuery = "";
			switch (level) {
			case 1:
				sqlQuery = SQL_DELETE_CATEGORY_1;
				break;
			case 2:
				sqlQuery = SQL_DELETE_CATEGORY_2;
				break;

			case 3:
				sqlQuery = SQL_DELETE_CATEGORY_3;
				break;

			case 4:
				sqlQuery = SQL_DELETE_CATEGORY_4;
				break;

			case 5:
				sqlQuery = SQL_DELETE_CATEGORY_5;
				break;

			default:
				throw new Exception("Invalid level value");
			}

			dynamicJdbcDao.executeUpdate(sqlQuery, new SimpleKeyValue("contentCatId", contentCateId), new SimpleKeyValue("level", level));

			result.setSuccess(Boolean.TRUE);
			result.setResult(Boolean.TRUE);
		} catch (Exception e) {
			logger.error(e.getMessage());
			setErrorResult(result, e);
		}

		return result;
	}

	@Override
	public ServiceResult<Page<ContentCategoryDTO>> getContentCategoryList(Integer level, String keyword, Pageable pageable, List<String> ownerFilter) {
		ServiceResult<Page<ContentCategoryDTO>> result = new ServiceResult<Page<ContentCategoryDTO>>();

		try {
			String sqlQuery = "";
			switch (level) {
			case 1:
				sqlQuery = SQL_FIND_CATE1_LIST_BY_KEYWORD;
				break;

			case 2:
				sqlQuery = SQL_FIND_CATE2_LIST_BY_KEYWORD;
				break;

			case 3:
				sqlQuery = SQL_FIND_CATE3_LIST_BY_KEYWORD;
				break;

			case 4:
				sqlQuery = SQL_FIND_CATE4_LIST_BY_KEYWORD;
				break;

			case 5:
				sqlQuery = SQL_FIND_CATE5_LIST_BY_KEYWORD;
				break;

			default:
				throw new Exception("Invalid level value");
			}

			Page<ContentCategoryDTO> res = dynamicJdbcDao.findForPage(sqlQuery, PrimitiveSafeBeanPropertyRowMapper.newInstance(ContentCategoryDTO.class), 
					pageable, 
					new SimpleKeyValue("keyword",keyword),
					new SimpleKeyValue("ownerGroup", (CollectionUtil.isEmpty(ownerFilter)) ? null : ownerFilter));

			result.setResult(res);
			result.setSuccess(Boolean.TRUE);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			setErrorResult(result, e);
		}

		return result;
	}

	@Override
	public ServiceResult<Page<ContentDetailDTO>> getContentDetailList(ContentDetailCriteria criteria, Pageable pageable) {
		ServiceResult<Page<ContentDetailDTO>> result = new ServiceResult<Page<ContentDetailDTO>>();

		try {
			Page<ContentDetailDTO> res = dynamicJdbcDao.findForPage(SQL_FIND_CONTENT_BY_CRITERIA, 
					PrimitiveSafeBeanPropertyRowMapper.newInstance(ContentDetailDTO.class), 
					pageable,
					criteria);

			result.setResult(res);
			result.setSuccess(Boolean.TRUE);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			setErrorResult(result, e);
		}
		return result;
	}

	@Override
	public ServiceResult<List<ContentAttDTO>> getContentAttByContentId(String contentId) {

		ServiceResult<List<ContentAttDTO>> serviceResult = null;
		List<ContentAttDTO> result = null;
		try {
			logger.info("+++++++++++++ In Befor findForPage ++++++++++++++++++++");

			result = dynamicJdbcDao.findForList(SQL_FIND_CONTENT_ATT_BY_CONTENT_ID, BeanPropertyRowMapper.newInstance(ContentAttDTO.class), new SimpleKeyValue("CONTENT_ID", contentId));
			logger.info("+++++++++++++ In Befor result :" + result.size());

			serviceResult = new ServiceResult<List<ContentAttDTO>>(result);

			logger.info("Element result :: " + serviceResult.getResult().size());

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			serviceResult = new ServiceResult<List<ContentAttDTO>>(e);
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<Boolean> updateContent(ContentDetailDTO contentDetailDTO) {
		ServiceResult<Boolean> result = new ServiceResult<Boolean>();
		
		try {
			int res = dynamicJdbcDao.executeUpdate(SQL_UPDATE_CONTENT, contentDetailDTO);
			
			result.setSuccess(Boolean.TRUE);
			result.setResponseCode(JLOWebConstant.SUCCESS_CODE);
			result.setResponseDescription(JLOWebConstant.SUCCESS_DESC);
			result.setResult((res > 0) ? Boolean.TRUE : Boolean.FALSE);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			setErrorResult(result, e);
		}
		
		return result;
	}

	@Override
	public ServiceResult<Integer> insertContent(ContentDetailDTO contentDetailDTO) {
		ServiceResult<Integer> result = new ServiceResult<Integer>();
		
		try {
			
			int res = dynamicJdbcDao.executeUpdate(SQL_INSERT_CONTENT, Boolean.TRUE, contentDetailDTO);
			
			result.setSuccess(Boolean.TRUE);
			result.setResponseCode(JLOWebConstant.SUCCESS_CODE);
			result.setResponseDescription(JLOWebConstant.SUCCESS_DESC);
			result.setResult(res);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			setErrorResult(result, e);
		}
		
		return result;
	}

	@Override
	public ServiceResult<Page<ContentDetailDTO>> getKBWaitingList(Pageable pageable, List<String> ownerFilter, String langCd) {
		ServiceResult<Page<ContentDetailDTO>> result = new ServiceResult<Page<ContentDetailDTO>>();
		
		try {
			Page<ContentDetailDTO> res = dynamicJdbcDao.findForPage(SQL_FIND_WAITING_APPRVOVE_LIST, 
						PrimitiveSafeBeanPropertyRowMapper.newInstance(ContentDetailDTO.class), 
						pageable,
						new SimpleKeyValue("langCd", langCd),
						new SimpleKeyValue("ownerGroup", (CollectionUtil.isEmpty(ownerFilter)) ? null : ownerFilter));
			
			result.setSuccess(Boolean.TRUE);
			result.setResult(res);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			setErrorResult(result, e);
		}
		return result;
	}

	@Override
	public ServiceResult<Boolean> updateContentStatus(List<String> contentIds, Integer userId, String statusCd) {
		ServiceResult<Boolean> result = new ServiceResult<Boolean>();
		
		try{
			int res = dynamicJdbcDao.executeUpdate(SQL_UPDATE_KB_STATUS_CODE, 
									new SimpleKeyValue("contentIdList", contentIds),
									new SimpleKeyValue("approveId", userId),
									new SimpleKeyValue("statusCd", statusCd));
			result.setSuccess(Boolean.TRUE);
			result.setResult((res > 0) ? Boolean.TRUE : Boolean.FALSE);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			setErrorResult(result, e);
		}
	
		return result;
	}

	@Override
	public ServiceResult<ContentDetailDTO> getContentDetail(Integer contentId) {
		ServiceResult<ContentDetailDTO> result = new ServiceResult<ContentDetailDTO>();
		
		try {
			ContentDetailDTO contentDetailDTO = dynamicJdbcDao.findForObject(SQL_FIND_CONTENT, 
												PrimitiveSafeBeanPropertyRowMapper.newInstance(ContentDetailDTO.class), 
												new SimpleKeyValue("contentId", contentId));
			
			result.setSuccess(Boolean.TRUE);
			result.setResult(contentDetailDTO);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			setErrorResult(result, e);
		}
		
		return result;
	}

	@Override
	public ServiceResult<Boolean> updateContentStatus(Integer contentId, Integer userId, String statusCd) {
		ServiceResult<Boolean> result = new ServiceResult<Boolean>();
		
		try{
			int res = dynamicJdbcDao.executeUpdate(SQL_UPDATE_KB_STATUS_CODE_2, 
									new SimpleKeyValue("contentId", contentId),
									new SimpleKeyValue("approveId", userId),
									new SimpleKeyValue("statusCd", statusCd));
			result.setSuccess(Boolean.TRUE);
			result.setResult((res > 0) ? Boolean.TRUE : Boolean.FALSE);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			setErrorResult(result, e);
		}
	
		return result;
	}

	@Override
	public ServiceResult<List<ContentDetailDTO>> getContentDetailList(ContentDetailCriteria criteria) {
		
		ServiceResult<List<ContentDetailDTO>> result = new ServiceResult<List<ContentDetailDTO>>();

		try {
			List<ContentDetailDTO> res = dynamicJdbcDao.findForList(SQL_FIND_CONTENT_BY_CRITERIA, PrimitiveSafeBeanPropertyRowMapper.newInstance(ContentDetailDTO.class), criteria);

			result.setResult(res);
			result.setSuccess(Boolean.TRUE);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			setErrorResult(result, e);
		}
		return result;
	}

	@Override
	public ServiceResult<List<ContentDetailDTO>> getContentDetailListForExport(ContentDetailCriteria criteria) {
		ServiceResult<List<ContentDetailDTO>> result = new ServiceResult<List<ContentDetailDTO>>();

		try {
			List<ContentDetailDTO> res = dynamicJdbcDao.findForList(SQL_EXPORT_CONTENT, PrimitiveSafeBeanPropertyRowMapper.newInstance(ContentDetailDTO.class), criteria);

			result.setResult(res);
			result.setSuccess(Boolean.TRUE);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			setErrorResult(result, e);
		}
		return result;
	}

	@Override
	public ServiceResult<List<ContentCategoryDTO>> getContentCategoryListForExport(
			String contentCateId, Integer level, String cateTypeCd,
			String cateStatusCd, List<String> ownerFilter) {
		
		ServiceResult<List<ContentCategoryDTO>> result = new ServiceResult<List<ContentCategoryDTO>>();

		try {
			String sqlQuery = "";
			switch (level) {
			case 0:
				sqlQuery = SQL_EXPORT_CATEGORY1;
				break;
				
			case 1:
				sqlQuery = SQL_EXPORT_CATEGORY2;
				break;

			case 2:
				sqlQuery = SQL_EXPORT_CATEGORY3;
				break;

			case 3:
				sqlQuery = SQL_EXPORT_CATEGORY4;
				break;

			case 4:
				sqlQuery = SQL_EXPORT_CATEGORY5;
				break;

			default:
				throw new Exception("Invalid level value");
			}
			
			// Query for base level
			List<ContentCategoryDTO> res = dynamicJdbcDao.findForList(sqlQuery, PrimitiveSafeBeanPropertyRowMapper.newInstance(ContentCategoryDTO.class), new SimpleKeyValue("contentCatId",
					contentCateId), new SimpleKeyValue("contentTypeCd", cateTypeCd), new SimpleKeyValue("statusCd", cateStatusCd));

			// Query for next level
			List<ContentCategoryDTO> allChild = findChildCategoryList(res, contentCateId, level, cateTypeCd, cateStatusCd, ownerFilter);
			if (CollectionUtil.isNotEmpty(allChild)) {
				res.addAll(allChild);
			}
			
			result.setResult(res);
			result.setSuccess(Boolean.TRUE);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			setErrorResult(result, e);
		}
		
		return result;
	}

	private List<ContentCategoryDTO> getCategoryList(String contentCateId, Integer level, String cateTypeCd, String cateStatusCd, List<String> ownerFilter) throws Exception {
		
		String sqlQuery = "";
		switch (level) {
		case 0:
			sqlQuery = SQL_FIND_CATE1_LIST;
			break;
			
		case 1:
			sqlQuery = SQL_FIND_CATE2_LIST;
			break;

		case 2:
			sqlQuery = SQL_FIND_CATE3_LIST;
			break;

		case 3:
			sqlQuery = SQL_FIND_CATE4_LIST;
			break;

		case 4:
			sqlQuery = SQL_FIND_CATE5_LIST;
			break;

		default:
			throw new Exception("Invalid level value");
		}
		logger.info("ownerFilter : " + ownerFilter);
		List<ContentCategoryDTO> res = dynamicJdbcDao.findForList(sqlQuery, PrimitiveSafeBeanPropertyRowMapper.newInstance(ContentCategoryDTO.class), 
				new SimpleKeyValue("contentCatId", contentCateId), new SimpleKeyValue("contentTypeCd", cateTypeCd), 
				new SimpleKeyValue("statusCd", cateStatusCd),
				new SimpleKeyValue("ownerGroup", (CollectionUtil.isEmpty(ownerFilter)) ? null : ownerFilter));

		return res;
	}
	
	private List<ContentCategoryDTO> findChildCategoryList(List<ContentCategoryDTO> parentList, String contentCateId, final Integer level, String cateTypeCd, String cateStatusCd, List<String> ownerFilter) throws Exception {
		List<ContentCategoryDTO> res = new ArrayList<ContentCategoryDTO>();
		List<ContentCategoryDTO> temp = null;
		int tempLevel = level + 1;
		
		logger.info(tempLevel + "----------" + contentCateId);
		if (tempLevel >= 5) {
			return res;
		}
		
		if (CollectionUtil.isNotEmpty(parentList)) {
			for (ContentCategoryDTO contentCategoryDTO : parentList) {
				String cateId = contentCategoryDTO.getContentCatId();
				
				// Current
				temp = getCategoryList(cateId, tempLevel, cateTypeCd, cateStatusCd, ownerFilter);
				if (CollectionUtil.isNotEmpty(temp)) {
					res.addAll(temp);
				}
				
				// Next
				//tempLevel++;
				if (CollectionUtil.isNotEmpty(temp) && tempLevel < 5) {
					List<ContentCategoryDTO> nextChild = findChildCategoryList(temp, cateId, tempLevel, cateTypeCd, cateStatusCd, ownerFilter);
					if (CollectionUtil.isNotEmpty(nextChild)) {
						res.addAll(nextChild);
					}
				}
				
			}
		}
		return res;
	}

	@Override
	public ServiceResult<List<ContentDetailDTO>> getAllCategoryForExport() {
		ServiceResult<List<ContentDetailDTO>> result = new ServiceResult<List<ContentDetailDTO>>();

		try {
			List<ContentDetailDTO> res = dynamicJdbcDao.findForList(SQL_EXPORT_ALL_CATEGORY, 
					PrimitiveSafeBeanPropertyRowMapper.newInstance(ContentDetailDTO.class));

			result.setResult(res);
			result.setSuccess(Boolean.TRUE);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			setErrorResult(result, e);
		}
		
		return result;
	}
}
