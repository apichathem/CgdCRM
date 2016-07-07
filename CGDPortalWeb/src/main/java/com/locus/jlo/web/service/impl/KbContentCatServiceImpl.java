/**
 * 
 */
package com.locus.jlo.web.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import com.locus.common.domain.ServiceResult;
import com.locus.common.domain.SimpleKeyValue;
import com.locus.jlo.web.bean.dto.KbContentCatDTO;
import com.locus.jlo.web.service.KbContentCatService;

/**
 * @author Mr.BoonOom
 * For get Content category
 * 
 */
@Service
public class KbContentCatServiceImpl extends BaseService implements KbContentCatService {

	private Logger logger = Logger.getLogger(getClass());

	public static final String SQL_FIND_KB_CONTENT_CAT1 = "KB.SQL_SEARCH_KB_CONTENT_CAT1";
	public static final String SQL_FIND_KB_CONTENT_CAT2_BY_PARENT_ID = "KB.SQL_SEARCH_KB_CONTENT_CAT2_BY_PARENT_ID";
	public static final String SQL_FIND_KB_CONTENT_CAT3_BY_PARENT_ID = "KB.SQL_SEARCH_KB_CONTENT_CAT3_BY_PARENT_ID";
	public static final String SQL_FIND_KB_CONTENT_CAT4_BY_PARENT_ID = "KB.SQL_SEARCH_KB_CONTENT_CAT4_BY_PARENT_ID";
	public static final String SQL_FIND_KB_CONTENT_CAT5_BY_PARENT_ID = "KB.SQL_SEARCH_KB_CONTENT_CAT5_BY_PARENT_ID";

	@Override
	public ServiceResult<List<KbContentCatDTO>> getKbContentCat1List() throws Exception {

		logger.info("+++++++++++++ In getKbContentCat1List ++++++++++++++++++++");
		ServiceResult<List<KbContentCatDTO>> serviceResult = new ServiceResult<List<KbContentCatDTO>>();
		List<KbContentCatDTO> result = null;

		try {
			logger.info("+++++++++++++ In Befor findForPage ++++++++++++++++++++");
			result = dynamicJdbcDao.findForList(SQL_FIND_KB_CONTENT_CAT1, BeanPropertyRowMapper.newInstance(KbContentCatDTO.class));

			logger.info("+++++++++++++ In Befor result :" + result.size());

			serviceResult = new ServiceResult<List<KbContentCatDTO>>(result);
			logger.info("Element result :: " + serviceResult.getResult().size());

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			serviceResult = new ServiceResult<List<KbContentCatDTO>>(e);
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<List<KbContentCatDTO>> getKbContentCat2ByParentCatIdList(String parentCatId) throws Exception {
		logger.info("+++++++++++++ In getKbContentCat2ByParentCatIdList ++++++++++++++++++++");

		ServiceResult<List<KbContentCatDTO>> serviceResult = new ServiceResult<List<KbContentCatDTO>>();
		List<KbContentCatDTO> result = null;

		try {
			logger.info("+++++++++++++ In Befor findForPage ++++++++++++++++++++");

			result = dynamicJdbcDao.findForList(SQL_FIND_KB_CONTENT_CAT2_BY_PARENT_ID, BeanPropertyRowMapper.newInstance(KbContentCatDTO.class), new SimpleKeyValue("parentCatId", parentCatId));

			logger.info("+++++++++++++ In Befor result :" + result.size());

			serviceResult = new ServiceResult<List<KbContentCatDTO>>(result);

			logger.info("Element result :: " + serviceResult.getResult().size());

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			serviceResult = new ServiceResult<List<KbContentCatDTO>>(e);
		}

		return serviceResult;
	}

	@Override
	public ServiceResult<List<KbContentCatDTO>> getKbContentCat3ByParentCatIdList(String parentCatId) throws Exception {
		
		ServiceResult<List<KbContentCatDTO>> serviceResult = new ServiceResult<List<KbContentCatDTO>>();
		List<KbContentCatDTO> result = null;

		try {
			logger.info("+++++++++++++ In Befor findForPage ++++++++++++++++++++");

			result = dynamicJdbcDao.findForList(SQL_FIND_KB_CONTENT_CAT3_BY_PARENT_ID, BeanPropertyRowMapper.newInstance(KbContentCatDTO.class), new SimpleKeyValue("parentCatId", parentCatId));

			logger.info("+++++++++++++ In Befor result :" + result.size());

			serviceResult = new ServiceResult<List<KbContentCatDTO>>(result);

			logger.info("Element result :: " + serviceResult.getResult().size());

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			serviceResult = new ServiceResult<List<KbContentCatDTO>>(e);
		}

		return serviceResult;
	}

	@Override
	public ServiceResult<List<KbContentCatDTO>> getKbContentCat4ByParentCatIdList(String parentCatId) throws Exception {
		ServiceResult<List<KbContentCatDTO>> serviceResult = new ServiceResult<List<KbContentCatDTO>>();
		List<KbContentCatDTO> result = null;

		try {
			logger.info("+++++++++++++ In Befor findForPage ++++++++++++++++++++");

			result = dynamicJdbcDao.findForList(SQL_FIND_KB_CONTENT_CAT4_BY_PARENT_ID, BeanPropertyRowMapper.newInstance(KbContentCatDTO.class), new SimpleKeyValue("parentCatId", parentCatId));

			logger.info("+++++++++++++ In Befor result :" + result.size());

			serviceResult = new ServiceResult<List<KbContentCatDTO>>(result);

			logger.info("Element result :: " + serviceResult.getResult().size());

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			serviceResult = new ServiceResult<List<KbContentCatDTO>>(e);
		}

		return serviceResult;
	}

	@Override
	public ServiceResult<List<KbContentCatDTO>> getKbContentCat5ByParentCatIdList(String parentCatId) throws Exception {
		ServiceResult<List<KbContentCatDTO>> serviceResult = new ServiceResult<List<KbContentCatDTO>>();
		List<KbContentCatDTO> result = null;

		try {
			logger.info("+++++++++++++ In Befor findForPage ++++++++++++++++++++");

			result = dynamicJdbcDao.findForList(SQL_FIND_KB_CONTENT_CAT5_BY_PARENT_ID, BeanPropertyRowMapper.newInstance(KbContentCatDTO.class), new SimpleKeyValue("parentCatId", parentCatId));

			logger.info("+++++++++++++ In Befor result :" + result.size());

			serviceResult = new ServiceResult<List<KbContentCatDTO>>(result);

			logger.info("Element result :: " + serviceResult.getResult().size());

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			serviceResult = new ServiceResult<List<KbContentCatDTO>>(e);
		}

		return serviceResult;
	}
}
