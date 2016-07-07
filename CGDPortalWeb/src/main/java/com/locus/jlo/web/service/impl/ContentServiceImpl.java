package com.locus.jlo.web.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;


//import com.locus.common.domain.CodebookInfo;
import com.locus.common.domain.ServiceResult;
import com.locus.common.domain.SimpleKeyValue;
import com.locus.jlo.web.bean.dto.AttDTO;
import com.locus.jlo.web.bean.dto.ContentAddrDTO;
import com.locus.jlo.web.bean.dto.ContentAttDTO;
import com.locus.jlo.web.bean.dto.ContentCatDTO;
import com.locus.jlo.web.bean.dto.ContentDTO;
import com.locus.jlo.web.bean.dto.ContentKeywordDTO;
import com.locus.jlo.web.service.ContentService;

@Service
public class ContentServiceImpl extends BaseService implements ContentService{
	
	private Logger logger = Logger.getLogger(getClass());
	
	public static final String SQL_FIND_LIST_CONTENT_BY_CAT = "CONTENT.SQL_FIND_LIST_CONTENT_BY_CAT";
	public static final String SQL_FIND_CONTENT_BY_ID = "CONTENT.SQL_FIND_CONTENT_BY_ID";
	public static final String SQL_FIND_CONTENT_ADDR_BY_CONTENT_ID = "CONTENT.SQL_FIND_CONTENT_ADDR_BY_CONTENT_ID";
	public static final String SQL_FIND_CONTENT_CAT_BY_ID = "CONTENT.SQL_FIND_CONTENT_CAT_BY_ID";
	public static final String SQL_FIND_CONTENT_CAT_BY_TYPE = "CONTENT.SQL_FIND_CONTENT_CAT_BY_TYPE";
	
	public static final String SQL_UPDATE_CONTENT_ADDR_LATLNG = "CONTENT.SQL_UPDATE_CONTENT_ADDR_LATLNG";
	public static final String SQL_FIND_CONTENT_ATT_BY_CONTENT_ID = "CONTENT.SQL_FIND_CONTENT_ATT_BY_CONTENT_ID";
	public static final String SQL_FIND_CONTENT_CAT_BY_PARENT = "CONTENT.SQL_FIND_CONTENT_CAT_BY_PARENT";
	public static final String SQL_FIND_CONTENT_CAT_BY_CONTENT_TYPE = "CONTENT.SQL_FIND_CONTENT_CAT_BY_CONTENT_TYPE";
	
	public static final String SQL_INSERT_CONTENT_CAT = "CONTENT.SQL_INSERT_CONTENT_CAT";
	public static final String SQL_UPDATE_CONTENT_CAT = "CONTENT.SQL_UPDATE_CONTENT_CAT";
	public static final String SQL_DELETE_CONTENT_CAT = "CONTENT.SQL_DELETE_CONTENT_CAT";
	
	public static final String SQL_INSERT_CONTENT = "CONTENT.SQL_INSERT_CONTENT";
	public static final String SQL_UPDATE_CONTENT = "CONTENT.SQL_UPDATE_CONTENT";
	public static final String SQL_DELETE_CONTENT = "CONTENT.SQL_DELETE_CONTENT";
	public static final String SQL_UPDATE_CONTENT_SUMMARY = "CONTENT.SQL_UPDATE_CONTENT_SUMMARY";
	
	public static final String SQL_UPDATE_CONTENT_ADDR = "CONTENT.SQL_UPDATE_CONTENT_ADDR";
	
	public static final String SQL_INSERT_CONTENT_ATT = "CONTENT.SQL_INSERT_CONTENT_ATT";
	public static final String SQL_DELETE_CONTENT_ATT = "CONTENT.SQL_DELETE_CONTENT_ATT";
	public static final String SQL_UPDATE_CONTENT_ATT = "CONTENT.SQL_UPDATE_CONTENT_ATT";
	public static final String SQL_UPDATE_CONTENT_ATT_MAIN_FLG_NULL = "CONTENT.SQL_UPDATE_CONTENT_ATT_MAIN_FLG_NULL";
	public static final String SQL_INSERT_TP_ATT = "ATT.SQL_INSERT_TP_ATT";
	public static final String SQL_DELETE_TP_ATT = "ATT.SQL_DELETE_TP_ATT";
	
	public static final String SQL_FIND_CONTENT_KEYWORD_BY_CONTENT_ID = "CONTENT.SQL_FIND_CONTENT_KEYWORD_BY_CONTENT_ID";
	public static final String SQL_INSERT_CONTENT_KEYWORD = "CONTENT.SQL_INSERT_CONTENT_KEYWORD";
	public static final String SQL_DELETE_CONTENT_KEYWORD = "CONTENT.SQL_DELETE_CONTENT_KEYWORD";
	
	public static final String SQL_COUNT_CONTENT_BY_CONTENT_CAT_ID = "CONTENT.SQL_COUNT_CONTENT_BY_CONTENT_CAT_ID";
	public static final String SQL_COUNT_CONTENT_CAT_BY_PAR_CAT_ID = "CONTENT.SQL_COUNT_CONTENT_CAT_BY_PAR_CAT_ID";
	
	public static final String SQL_FIND_CONTENT_FOR_ATT_DIALOG = "CONTENT.SQL_FIND_CONTENT_FOR_ATT_DIALOG";

//	@Override
//	public ServiceResult<List<ContentDTO>> searchContent(ContentCatDTO cat){
//		ServiceResult<List<ContentDTO>> serviceResult = null;
//		List<ContentDTO> list = null;
//		try {
//			list = dynamicJdbcDao.findForList(SQL_FIND_LIST_CONTENT_BY_CAT, BeanPropertyRowMapper.newInstance(ContentDTO.class),
//					new SimpleKeyValue("CONTENT_CAT_ID", cat.getContentCatId()),new SimpleKeyValue("TITLE", "%"));
//			serviceResult = new ServiceResult<List<ContentDTO>>(list);
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//			serviceResult = new ServiceResult<List<ContentDTO>>(e);
//		}
//		return serviceResult;
//		
//	}
	@Override
	public ServiceResult<Page<ContentDTO>> searchContent(
			ContentCatDTO contentCatDTO,  ContentDTO content,Pageable pageable) {
		ServiceResult<Page<ContentDTO>> serviceResult = null;
		Page<ContentDTO> page = null;
		try{
			if(contentCatDTO!=null){
				page = dynamicJdbcDao.findForPage(SQL_FIND_LIST_CONTENT_BY_CAT, BeanPropertyRowMapper.newInstance(ContentDTO.class), pageable,
						new SimpleKeyValue("CONTENT_CAT_ID", contentCatDTO.getContentCatId()),
						new SimpleKeyValue("TITLE", content.getTitle()),
						new SimpleKeyValue("ownerGroup", content.getOwnerGroup()));
			}
			serviceResult = new ServiceResult<Page<ContentDTO>>(page);
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			serviceResult = new ServiceResult<Page<ContentDTO>>(e);
		}
		return serviceResult;
	}
	
	@Override
	public ServiceResult<List<ContentDTO>> searchContent(
			ContentCatDTO contentCatDTO,  ContentDTO content) {
		ServiceResult<List<ContentDTO>> serviceResult = null;
		List<ContentDTO> result = null;
		try{
			result = dynamicJdbcDao.findForList(SQL_FIND_LIST_CONTENT_BY_CAT, BeanPropertyRowMapper.newInstance(ContentDTO.class),
					new SimpleKeyValue("CONTENT_CAT_ID", contentCatDTO.getContentCatId()),
					new SimpleKeyValue("TITLE", content.getTitle()),
					new SimpleKeyValue("ownerGroup", content.getOwnerGroup()));
			serviceResult = new ServiceResult<List<ContentDTO>>(result);
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			serviceResult = new ServiceResult<List<ContentDTO>>(e);
		}
		return serviceResult;
	}


	@Override
	public ServiceResult<ContentDTO> searchById(String contentNumber) {
		ServiceResult<ContentDTO> serviceResult = null;
		try {
			ContentDTO model = dynamicJdbcDao.findForObject(SQL_FIND_CONTENT_BY_ID, BeanPropertyRowMapper.newInstance(ContentDTO.class),
					new SimpleKeyValue("CONTENT_NUMBER", contentNumber));
			serviceResult = new ServiceResult<ContentDTO>(model);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			serviceResult = new ServiceResult<ContentDTO>(e);
		}
		return serviceResult;
	}


	@Override
	public ServiceResult<ContentAddrDTO> searchContentAddrByContentId(String contentId) {
		ContentAddrDTO result = null;
		ServiceResult<ContentAddrDTO> serviceResult = null;
		try {
			result= dynamicJdbcDao.findForObject(SQL_FIND_CONTENT_ADDR_BY_CONTENT_ID, BeanPropertyRowMapper.newInstance(ContentAddrDTO.class),
					new SimpleKeyValue("CONTENT_ID", contentId));
			serviceResult = new ServiceResult<ContentAddrDTO>(result);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			serviceResult = new ServiceResult<ContentAddrDTO>(e);
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<ContentCatDTO> searchContentCatById(String catId) {
		ContentCatDTO result = null;
		ServiceResult<ContentCatDTO> serviceResult = null;
		try {
			result= dynamicJdbcDao.findForObject(SQL_FIND_CONTENT_CAT_BY_ID, BeanPropertyRowMapper.newInstance(ContentCatDTO.class),
					new SimpleKeyValue("CONTENT_CAT_ID", catId));
			serviceResult = new ServiceResult<ContentCatDTO>(result);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			serviceResult = new ServiceResult<ContentCatDTO>(e);
		}
		return serviceResult;
	}


	@Override
	public ServiceResult<List<ContentCatDTO>> searchContentCatByType(String type) {
		List<ContentCatDTO> result = null;
		ServiceResult<List<ContentCatDTO>> serviceResult = null;
		try {
			result= dynamicJdbcDao.findForList(SQL_FIND_CONTENT_CAT_BY_TYPE, BeanPropertyRowMapper.newInstance(ContentCatDTO.class),
					new SimpleKeyValue("CONTENT_TYPE", type));
			serviceResult = new ServiceResult<List<ContentCatDTO>>(result);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			serviceResult = new ServiceResult<List<ContentCatDTO>>(e);
		}
		return serviceResult;
	}


	@Override
	public ServiceResult<List<ContentKeywordDTO>> searchContentKeywordByContentId(String contentId) {
		List<ContentKeywordDTO> result = null;
		ServiceResult<List<ContentKeywordDTO>> serviceResult = null;
		try {
			result= dynamicJdbcDao.findForList(SQL_FIND_CONTENT_KEYWORD_BY_CONTENT_ID, BeanPropertyRowMapper.newInstance(ContentKeywordDTO.class),
					new SimpleKeyValue("CONTENT_ID", contentId));
			serviceResult = new ServiceResult<List<ContentKeywordDTO>>(result);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			serviceResult = new ServiceResult<List<ContentKeywordDTO>>(e);
		}
		return serviceResult;
	}


	@Override
	public ServiceResult<Long> updateContentAddrLatLng(String contentId,
			String lat_lng) {
		ServiceResult<Long> result = null;
		try{
			long out = dynamicJdbcDao.executeUpdate(SQL_UPDATE_CONTENT_ADDR_LATLNG, 
					new SimpleKeyValue("CONTENT_ID", contentId),
					new SimpleKeyValue("LAT_LNG", lat_lng));
			result =  new ServiceResult<Long>(new Long(out));
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			result =  new ServiceResult<Long>(e);
		}
		return result;
	}


	@Override
	public ServiceResult<Page<ContentAttDTO>> searchContentAttByContentId(String contentId, Pageable pageable) {
		ServiceResult<Page<ContentAttDTO>> serviceResult = null;
		Page<ContentAttDTO> page = null;
		try {
			page = dynamicJdbcDao.findForPage(SQL_FIND_CONTENT_ATT_BY_CONTENT_ID, BeanPropertyRowMapper.newInstance(ContentAttDTO.class),pageable,
					new SimpleKeyValue("CONTENT_ID", contentId), new SimpleKeyValue("DOC_NAME",""));
			serviceResult = new ServiceResult<Page<ContentAttDTO>>(page);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			serviceResult = new ServiceResult<Page<ContentAttDTO>>(e);
		}
		return serviceResult;
	}


	@Override
	public ServiceResult<Long> insertContentAtt(ContentAttDTO contentAtt, AttDTO attDTO) {
		ServiceResult<Long> out = null;
		try{
			//TP_ATT
			Long insertId = dynamicJdbcDao.executeInsert(SQL_INSERT_TP_ATT,true,attDTO);
			//TP_CONTENT_ATT
			contentAtt.setAttId(insertId);
			Long id = dynamicJdbcDao.executeInsert(SQL_INSERT_CONTENT_ATT,Boolean.TRUE,contentAtt);
			out = new ServiceResult<Long>(id);
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			out = new ServiceResult<Long>(e);
		}
		return out;
	}



	@Override
	public ServiceResult<List<ContentCatDTO>> searchContentCatByParent(ContentCatDTO parent) {
		List<ContentCatDTO> result = null;
		 ServiceResult<List<ContentCatDTO>> serviceResult = null;
		try {
			result = dynamicJdbcDao.findForList(SQL_FIND_CONTENT_CAT_BY_PARENT, BeanPropertyRowMapper.newInstance(ContentCatDTO.class),
					new SimpleKeyValue("PAR_CAT_ID", parent.getContentCatId()));
			serviceResult = new ServiceResult<List<ContentCatDTO>>(result);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			serviceResult = new ServiceResult<List<ContentCatDTO>>(e);
		}
		return serviceResult;
	}


	@Override
	public ServiceResult<List<ContentCatDTO>> searchContentCatByContentType(ContentCatDTO parent) {
		List<ContentCatDTO> result = null;
		ServiceResult<List<ContentCatDTO>> serviceResult = null;
		try {
			result = dynamicJdbcDao.findForList(SQL_FIND_CONTENT_CAT_BY_CONTENT_TYPE, BeanPropertyRowMapper.newInstance(ContentCatDTO.class),
					new SimpleKeyValue("CONTENT_TYPE", parent.getContentType()),
					new SimpleKeyValue("ownerGroup", parent.getOwnerGroup()));
			serviceResult = new ServiceResult<List<ContentCatDTO>>(result);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			serviceResult = new ServiceResult<List<ContentCatDTO>>(e);
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<Long> insertContentCat(ContentCatDTO contentCatDTO) {
		Long result = null;
		ServiceResult<Long> serviceResult = null;
		try {
			result =  dynamicJdbcDao.executeInsert(SQL_INSERT_CONTENT_CAT, true, 
					new SimpleKeyValue("contentType", contentCatDTO.getContentType()),
					new SimpleKeyValue("catName", contentCatDTO.getCatName()),
					new SimpleKeyValue("display", contentCatDTO.getDisplay()),
					new SimpleKeyValue("ord", contentCatDTO.getOrd()),
					new SimpleKeyValue("parCatId", contentCatDTO.getParCatId()),
					new SimpleKeyValue("catType", contentCatDTO.getCatType()),
					new SimpleKeyValue("catSubtype", contentCatDTO.getCatSubtype()),
					new SimpleKeyValue("catCenterType", contentCatDTO.getCatCenterType()),
					new SimpleKeyValue("useStatus", contentCatDTO.getUseStatus()),
					new SimpleKeyValue("regId", contentCatDTO.getRegId()),
					new SimpleKeyValue("regDt", contentCatDTO.getRegDt()));
			serviceResult = new ServiceResult<Long>(result);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			serviceResult = new ServiceResult<Long>(e);
		}
		logger.info("ID:"+result);
		return serviceResult;
	}


	@Override
	public ServiceResult<Long> updateContentCat(ContentCatDTO contentCatDTO) {
		long result = 0;
		ServiceResult<Long> serviceResult = null;
		try {
			result = dynamicJdbcDao.executeUpdate(SQL_UPDATE_CONTENT_CAT, contentCatDTO);
			serviceResult = new ServiceResult<Long>(new Long(result));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			serviceResult = new ServiceResult<Long>(e);
		}
		return serviceResult;		
	}


	@Override
	public ServiceResult<Long> deleteContentCat(ContentCatDTO contentCatDTO) {
		long result = 0;
		ServiceResult<Long> serviceResult = null;
		try {
			result = dynamicJdbcDao.executeUpdate(SQL_DELETE_CONTENT_CAT, contentCatDTO);
			serviceResult = new ServiceResult<Long>(new Long(result));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			serviceResult = new ServiceResult<Long>(e);
		}
		return serviceResult;	
	}


	@Override
	public boolean isChildrenCat(ContentCatDTO contentCat) {
		try {
			Long countcontent = dynamicJdbcDao.findForLong(SQL_COUNT_CONTENT_BY_CONTENT_CAT_ID, contentCat);
			if(countcontent > 0){
				return true;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return false;
	}


	@Override
	public boolean isRootCat(ContentCatDTO contentCat) {
		try {
			Long countcontent = dynamicJdbcDao.findForLong(SQL_COUNT_CONTENT_CAT_BY_PAR_CAT_ID, contentCat);
			if(countcontent > 0){
				return true;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return false;
	}
	@Override
	public Long insertContent(ContentDTO obj) {
		Long result=null;
		try {
			result = dynamicJdbcDao.executeInsert(SQL_INSERT_CONTENT, false, obj);
		} catch (Exception e) {
			logger.error(e);
		}
		return result;
	}
	@Override
	public Long updateContent(ContentDTO obj) {
		long result=0;
		try {
			result = dynamicJdbcDao.executeUpdate(SQL_UPDATE_CONTENT, obj);
		} catch (Exception e) {
			logger.error(e);
		}
		return result;
	}
	@Override
	public Long deleteContent(ContentDTO obj) {
		long result=0;
		try {
			result = dynamicJdbcDao.executeUpdate(SQL_DELETE_CONTENT, obj);
		} catch (Exception e) {
			logger.error(e);
		}
		return result;
	}
	@Override
	public Long updateContentAddr(ContentAddrDTO contentAddr) {
		long result=0;
		try {
			result = dynamicJdbcDao.executeUpdate(SQL_UPDATE_CONTENT_ADDR, contentAddr);
		} catch (Exception e) {
			logger.error(e);
		}
		return result;
	}
	@Override
	public Long updateContentAtt(ContentAttDTO att) {
		long result=0;
		try {
			result = dynamicJdbcDao.executeUpdate(SQL_UPDATE_CONTENT_ATT, att);
		} catch (Exception e) {
			logger.error(e);
		}
		return result;
	}
	@Override
	public Long deleteContentAtt(ContentAttDTO att, AttDTO attDTO) {
		long result=0;
		try {
			result = dynamicJdbcDao.executeUpdate(SQL_DELETE_CONTENT_ATT, att);
		} catch (Exception e) {
			logger.error(e);
		}
		try {
			result = dynamicJdbcDao.executeUpdate(SQL_DELETE_TP_ATT, attDTO);
		} catch (Exception e) {
			logger.error(e);
		}
		return result;
	}
	@Override
	public ServiceResult<ContentAttDTO> searchMainPageByContentId(
			String contentId) {
		ServiceResult<ContentAttDTO> serviceResult = null;
		List<ContentAttDTO> lst = null;
		try {
			lst = dynamicJdbcDao.findForList(SQL_FIND_CONTENT_ATT_BY_CONTENT_ID, BeanPropertyRowMapper.newInstance(ContentAttDTO.class),
					new SimpleKeyValue("CONTENT_ID", contentId), new SimpleKeyValue("DOC_NAME",""));
			for (ContentAttDTO contentAttDTO : lst) {
				if("1".equalsIgnoreCase(contentAttDTO.getMainFlag())){
					serviceResult = new ServiceResult<ContentAttDTO>(contentAttDTO);
				}
			}
			if(serviceResult==null){
				serviceResult = new ServiceResult<ContentAttDTO>(new NullPointerException());
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			serviceResult = new ServiceResult<ContentAttDTO>(e);
		}
		return serviceResult;
	}
	@Override
	public Long insertContentKeyword(ContentKeywordDTO dto){
		long result=0;
		try {
			result = dynamicJdbcDao.executeUpdate(SQL_INSERT_CONTENT_KEYWORD, 
					new SimpleKeyValue("CONTENT_ID",dto.getContentId()), 
					new SimpleKeyValue("KEYWORD", dto.getKeyword()),
					new SimpleKeyValue("REG_ID",dto.getRegId()),
					new SimpleKeyValue("REG_DT", dto.getRegDt()));
		} catch (Exception e) {
			logger.error(e);
		}
		return result;
	}
	@Override
	public Long deleteContentKeyword(String contentId, String tagname) {
		long result=0;
		try {
			result = dynamicJdbcDao.executeUpdate(SQL_DELETE_CONTENT_KEYWORD, new SimpleKeyValue("CONTENT_ID",contentId), new SimpleKeyValue("KEYWORD", tagname));
		} catch (Exception e) {
			logger.error(e);
		}
		return result;
	}
	@Override
	public ServiceResult<Page<ContentDTO>> findContentforDialogAtt(
			Pageable pageable, String type, String name, String docname) {
		ServiceResult<Page<ContentDTO>> serviceResult = null;
		try{
			Page<ContentDTO> page = dynamicJdbcDao.findForPage(SQL_FIND_CONTENT_FOR_ATT_DIALOG,BeanPropertyRowMapper.newInstance(ContentDTO.class), pageable, 
					new SimpleKeyValue("type",type.length()==0?null:type),
					new SimpleKeyValue("name",name),
					new SimpleKeyValue("docname",docname)
					);
			serviceResult = new ServiceResult<Page<ContentDTO>>(page);
		}catch(Exception e){
			logger.error(e);
			serviceResult = new ServiceResult<Page<ContentDTO>>(e);
		}
		return serviceResult;
	}
	@Override
	public ServiceResult<Page<ContentAttDTO>> findContentAttforDialogAtt(
			Pageable pageable, String contentId, String docName) {
		ServiceResult<Page<ContentAttDTO>> serviceResult = null;
		try{
			Page<ContentAttDTO> page = dynamicJdbcDao.findForPage(SQL_FIND_CONTENT_ATT_BY_CONTENT_ID,BeanPropertyRowMapper.newInstance(ContentAttDTO.class), pageable, 
					new SimpleKeyValue("CONTENT_ID",contentId), new SimpleKeyValue("DOC_NAME",docName)
					);
			serviceResult = new ServiceResult<Page<ContentAttDTO>>(page);
		}catch(Exception e){
			logger.error(e);
			serviceResult = new ServiceResult<Page<ContentAttDTO>>(e);
		}
		return serviceResult;
	}
	@Override
	public Long updateContentSummary(ContentDTO obj) {
		long result=0;
		try {
			result = dynamicJdbcDao.executeUpdate(SQL_UPDATE_CONTENT_SUMMARY, obj);
		} catch (Exception e) {
			logger.error(e);
		}
		return result;
	}
	@Override
	public Long removeMainFlag(ContentAttDTO att) {
		long result=0;
		try {
			result = dynamicJdbcDao.executeUpdate(SQL_UPDATE_CONTENT_ATT_MAIN_FLG_NULL, att);
		} catch (Exception e) {
			logger.error(e);
		}
		return result;
	}

	@Override
	public ServiceResult<List<ContentAttDTO>> searchContentAttByContentId(String contentId) {
		
		ServiceResult<List<ContentAttDTO>> serviceResult = null;
		List<ContentAttDTO> page = null;
		try {
			page = dynamicJdbcDao.findForList(SQL_FIND_CONTENT_ATT_BY_CONTENT_ID, BeanPropertyRowMapper.newInstance(ContentAttDTO.class),
					new SimpleKeyValue("CONTENT_ID", contentId), new SimpleKeyValue("DOC_NAME",""));
			serviceResult = new ServiceResult<List<ContentAttDTO>>(page);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			serviceResult = new ServiceResult<List<ContentAttDTO>>(e);
		}
		return serviceResult;
	}
	
	
}
