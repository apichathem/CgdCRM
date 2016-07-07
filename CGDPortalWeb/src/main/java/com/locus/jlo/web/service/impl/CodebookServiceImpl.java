package com.locus.jlo.web.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.locus.common.domain.ServiceResult;
import com.locus.common.domain.SimpleKeyValue;
import com.locus.common.mapper.PrimitiveSafeBeanPropertyRowMapper;
import com.locus.common.utils.DateTimeUtils;
import com.locus.jlo.web.bean.modelbean.CodebookModelBean;
import com.locus.jlo.web.service.CodebookService;

@Service
public class CodebookServiceImpl extends BaseService implements CodebookService {
	private Logger logger = Logger.getLogger(getClass());
	
	final static String SQL_FIND_CODEBOOK_ALL_BY_LANG = "CODEBOOK.SQL_FIND_CODEBOOK_ALL_BY_LANG";
	final static String SQL_FIND_CODEBOOK_BY_CODE_TYPE_AND_CODE_NAME = "CODEBOOK.SQL_FIND_CODEBOOK_BY_CODE_TYPE_AND_CODE_NAME";
	final static String SQL_FIND_CODEBOOK_LANG_BY_CODE_TYPE_AND_PARENT = "CODEBOOK.SQL_FIND_CODEBOOK_LANG_BY_CODE_TYPE_AND_PARENT";
	final static String SQL_FIND_CODEBOOK_BY_CODE_ID = "CODEBOOK.SQL_FIND_CODEBOOK_BY_CODE_ID";
	final static String SQL_INSERT_CODEBOOK = "CODEBOOK.SQL_INSERT_CODEBOOK";
	final static String SQL_INSERT_CODEBOOK_LANG = "CODEBOOK.SQL_INSERT_CODEBOOK_LANG";
	final static String SQL_UPDATE_CODEBOOK = "CODEBOOK.SQL_UPDATE_CODEBOOK";
	final static String SQL_UPDATE_CODEBOOK_LANG = "CODEBOOK.SQL_UPDATE_CODEBOOK_LANG";
	final static String SQL_COUNT_CODEBOOK_BY_CODE_ID = "CODEBOOK.SQL_COUNT_CODEBOOK_BY_CODE_ID";
	final static String SQL_COUNT_CODEBOOK_BY_CODE_ID_AND_CODE_TYPE = "CODEBOOK.SQL_COUNT_CODEBOOK_BY_CODE_ID_AND_CODE_TYPE";
	final static String SQL_GET_CODEBOOK_WITH_DISABLED = "CODEBOOK.SQL_GET_CODEBOOK_WITH_DISABLED";
	
	final static String SQL_FIND_LIST_CODEBOOK_BY_SYNC_DATE = "CODEBOOK.SQL_FIND_LIST_CODEBOOK_BY_SYNC_DATE";
	final static String SQL_FIND_TOTALROW_CODEBOOK_BY_SYNC_DATE = "CODEBOOK.SQL_FIND_TOTALROW_CODEBOOK_BY_SYNC_DATE";
	
	@Override
	public ServiceResult<Page<CodebookModelBean>> searchByCriteria(String codeType, String codeName, String langCd, String parentCodeType, Pageable pageable) {
		ServiceResult<Page<CodebookModelBean>> result = new ServiceResult<Page<CodebookModelBean>>();
		
		try {
			Page<CodebookModelBean> resultList = dynamicJdbcDao.findForPage(SQL_FIND_CODEBOOK_BY_CODE_TYPE_AND_CODE_NAME, PrimitiveSafeBeanPropertyRowMapper.newInstance(CodebookModelBean.class), pageable, 
				new SimpleKeyValue("codeName",codeName),
				new SimpleKeyValue("codeType",codeType),
				new SimpleKeyValue("parentType",parentCodeType),
				new SimpleKeyValue("langCd",langCd));
			result.setSuccess(Boolean.TRUE);
			result.setResult(resultList);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			setErrorResult(result, e);
		}

		return result;
	}
	
	@Override
	public ServiceResult<CodebookModelBean> searchById(String codeType, String codeId) {
		ServiceResult<CodebookModelBean> result = new ServiceResult<CodebookModelBean>();
		try {
			CodebookModelBean resultList = dynamicJdbcDao.findForObject(SQL_FIND_CODEBOOK_BY_CODE_ID, PrimitiveSafeBeanPropertyRowMapper.newInstance(CodebookModelBean.class),
					new SimpleKeyValue("codeType",codeType),
					new SimpleKeyValue("codeId",codeId));
			result.setSuccess(Boolean.TRUE);
			result.setResult(resultList);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			setErrorResult(result, e);
		}

		return result;
	}
	
	@Override
	public ServiceResult<Boolean> insert(CodebookModelBean codebookModelBean) {
		ServiceResult<Boolean> result = null;
		try {
			
			// Check Duplicate before insert
			Integer count = dynamicJdbcDao.findForObject(SQL_COUNT_CODEBOOK_BY_CODE_ID, new IntegerResultMapper(), codebookModelBean);
			if (count > 0) {
				result = new ServiceResult<Boolean>();
				result.setResponseCode(RES_CODE_ERROR_DUPLICATE_DATA);
				result.setResponseDescription("Cannot insert Codebook with duplicate Data");
				result.setSuccess(Boolean.FALSE);
				return result;
			}
			int row = 0;
			row = dynamicJdbcDao.executeUpdate(SQL_INSERT_CODEBOOK, codebookModelBean);
			
			// Key = Language Code, Value = Code name by Language
			if (codebookModelBean.getCodenameByLang() != null) {
				String[] codenameByLang = codebookModelBean.getCodenameByLang() ;
				String[] languageCode = codebookModelBean.getLanguageCode();
				int size = codenameByLang.length;
				
				for (int i = 0; i < size; i++) {
					String langCd = languageCode[i];
					String codeName = codenameByLang[i];
					
					codebookModelBean.setLangCd(langCd);
					codebookModelBean.setCodeName(codeName);
					
					row += dynamicJdbcDao.executeUpdate(SQL_INSERT_CODEBOOK_LANG,codebookModelBean);
				}
			}
			
			result = new ServiceResult<Boolean>(row>0?Boolean.TRUE:Boolean.FALSE);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result = new ServiceResult<Boolean>(Boolean.FALSE);
			setErrorResult(result, e);
		}
		return result;
	}
	
	@Override
	public ServiceResult<Boolean> update(CodebookModelBean codebookModelBean) {
		ServiceResult<Boolean> result = new ServiceResult<Boolean>();
		try {
			int row = 0;
			row = dynamicJdbcDao.executeUpdate(SQL_UPDATE_CODEBOOK,codebookModelBean);
			result.setResult(row>0?Boolean.TRUE:Boolean.FALSE);
			
			// Key = Language Code, Value = Code name by Language
			if (codebookModelBean.getCodenameByLang() != null) {
				String[] codenameByLang = codebookModelBean.getCodenameByLang() ;
				String[] languageCode = codebookModelBean.getLanguageCode();
				int size = codenameByLang.length;
				
				for (int i = 0; i < size; i++) {
					String langCd = languageCode[i];
					String codeName = codenameByLang[i];
					
					codebookModelBean.setLangCd(langCd);
					codebookModelBean.setCodeName(codeName);
					
					row += dynamicJdbcDao.executeUpdate(SQL_UPDATE_CODEBOOK_LANG,codebookModelBean);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			setErrorResult(result, e);
		}
		return result;
	}
	
	@Override
	public ServiceResult<List<CodebookModelBean>> searchAll(String langCd) {
		ServiceResult<List<CodebookModelBean>> result = null;
		try {
			List<CodebookModelBean> list = dynamicJdbcDao.findForList(SQL_FIND_CODEBOOK_ALL_BY_LANG, PrimitiveSafeBeanPropertyRowMapper.newInstance(CodebookModelBean.class),
					new SimpleKeyValue("langCd",langCd.toUpperCase()));
			result = new ServiceResult<List<CodebookModelBean>>(list);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			setErrorResult(result, e);
		};
		return result;
	}


	@Override
	@Transactional
	public CodebookModelBean saveCodebook(CodebookModelBean tpCodebook) throws Exception {

		return tpCodebook;
	}

	@Override
	public Boolean checkDuplicateKey(String codeType, String codeId) {
			return Boolean.FALSE;
	}

	

	@Override
	@Transactional(readOnly = true)
	public ServiceResult<CodebookModelBean> searchById(String codeType, String codeId, String langCd) {
		ServiceResult<CodebookModelBean> result = new ServiceResult<CodebookModelBean>();
		try {
			result.setSuccess(Boolean.TRUE);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			setErrorResult(result, e);
		}

		return result;
	}

	@Override
	@Transactional(readOnly = true)
	public ServiceResult<List<CodebookModelBean>> searchByCodeType(String codeType, String langCd) {
		ServiceResult<List<CodebookModelBean>> result = new ServiceResult<List<CodebookModelBean>>();
		try {
			List<CodebookModelBean> list = dynamicJdbcDao.findForList(SQL_COUNT_CODEBOOK_BY_CODE_ID_AND_CODE_TYPE, PrimitiveSafeBeanPropertyRowMapper.newInstance(CodebookModelBean.class)
					, new SimpleKeyValue("codeType",codeType)
					, new SimpleKeyValue("langCd",langCd.toUpperCase()));
			result.setResult(list);
			result.setSuccess(Boolean.TRUE);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			setErrorResult(result, e);
		}

		return result;
	}
	
	@Override
	@Transactional(readOnly = true)
	public ServiceResult<List<CodebookModelBean>> searchByCodeTypeWithDisable(String codeType, String langCd) {
		ServiceResult<List<CodebookModelBean>> result = new ServiceResult<List<CodebookModelBean>>();
		try {
			List<CodebookModelBean> list = dynamicJdbcDao.findForList(SQL_GET_CODEBOOK_WITH_DISABLED, PrimitiveSafeBeanPropertyRowMapper.newInstance(CodebookModelBean.class)
					, new SimpleKeyValue("codeType",codeType)
					, new SimpleKeyValue("langCd",langCd.toUpperCase()));
			result.setResult(list);
			result.setSuccess(Boolean.TRUE);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			setErrorResult(result, e);
		}

		return result;
	}

	@Override
	public ServiceResult<List<CodebookModelBean>> searchByParentTypeAndParentId(String parentType,String parentId) {
		
		ServiceResult<List<CodebookModelBean>> result = new ServiceResult<List<CodebookModelBean>>();
		try {
			result.setSuccess(Boolean.TRUE);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			setErrorResult(result, e);
		}

		return result;
	}

	

	@Override
	public ServiceResult<List<CodebookModelBean>> searchAllByChannel(String channel) {
		ServiceResult<List<CodebookModelBean>> result = new ServiceResult<List<CodebookModelBean>>();
		try {
			result.setSuccess(Boolean.TRUE);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			setErrorResult(result, e);
		}

		return result;
	}

	
	@Override
	public ServiceResult<List<CodebookModelBean>> searchBySyncDate(Date syncDate,Integer pageNo,Integer rowPerPage) {
		Long resultTotalRow = null;
		int offSet = 0;
		offSet = (pageNo - 1) * rowPerPage ;
		ServiceResult<List<CodebookModelBean>> serviceResult = null;
		List<CodebookModelBean> result = null;
		try {
			result = dynamicJdbcDao.findForList(SQL_FIND_LIST_CODEBOOK_BY_SYNC_DATE, PrimitiveSafeBeanPropertyRowMapper.newInstance(CodebookModelBean.class),
					new SimpleKeyValue("SYNC_DATE", DateTimeUtils.convertDateToDBStr(syncDate)),
					new SimpleKeyValue("OFFSET", offSet),
					new SimpleKeyValue("ROW_PER_PAGE", rowPerPage));
			
			if(result!=null && result.size() > 0){
				resultTotalRow = dynamicJdbcDao.findForLong(SQL_FIND_TOTALROW_CODEBOOK_BY_SYNC_DATE, 
						new SimpleKeyValue("SYNC_DATE", DateTimeUtils.convertDateToDBStr(syncDate)));
			}else{
				resultTotalRow = 0l;
			}
			
			serviceResult = new ServiceResult<List<CodebookModelBean>>(result);
			serviceResult.setEtc1(""+resultTotalRow);
			
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			serviceResult = new ServiceResult<List<CodebookModelBean>>(e);
		}
		return serviceResult;
	}
	
	@Override
	@Transactional(readOnly = true)
	public ServiceResult<List<CodebookModelBean>> searchLangById(String codeType, String codeId) {
		ServiceResult<List<CodebookModelBean>> result = new ServiceResult<List<CodebookModelBean>>();
		try {
			List<CodebookModelBean> resultList = dynamicJdbcDao.findForList(SQL_FIND_CODEBOOK_LANG_BY_CODE_TYPE_AND_PARENT, PrimitiveSafeBeanPropertyRowMapper.newInstance(CodebookModelBean.class),
					new SimpleKeyValue("codeType",codeType),
					new SimpleKeyValue("codeId",codeId));
			result.setSuccess(Boolean.TRUE);
			result.setResult(resultList);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			setErrorResult(result, e);
		}

		return result;
	}
}
