package com.locus.jlo.web.service.impl;

import java.util.Date;
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
import com.locus.jlo.web.service.ActivityManagementService;
import com.locus.jlo.web.service.SequenceGeneratorService;
import com.locus.jlo.web.bean.criteria.ActivityCriteria;
import com.locus.jlo.web.bean.dto.ActivityAttFileDTO;
import com.locus.jlo.web.bean.dto.ActivityDTO;


@Service
public class ActivityManagementServiceImpl extends BaseService implements ActivityManagementService {
	
	//Search criteria for Grid
	String SQL_SEARCH_ACTIVITY = "ACTIVITY.SQL_SEARCH_ACTIVITY";
	
	//Search document
	String SQL_SEARCH_DOCUMENT_ACTIVITY = "ACTIVITY.SQL_SEARCH_DOCUMENT_ACTIVITY";

	//Search Activity Detail
	String SQL_SEARCH_ACTIVITY_DETAIL = "ACTIVITY.SQL_SEARCH_ACTIVITY_DETAIL";
	String SQL_SEARCH_ACTIVITY_DETAIL_BY_ACT_NUMBER = "ACTIVITY.SQL_SEARCH_ACTIVITY_DETAIL_BY_ACT_NUMBER";
	
	//Insert Activity
	String SQL_INSERT_ACTIVITY = "ACTIVITY.SQL_FOR_INSERT_ACTIVITY";
	
	//Update Activity
	String SQL_UPDATE_ACTIVITY = "ACTIVITY.SQL_UPDATE_ACTIVITY";

	//insert attachFile
	String SQL_INSERT_TPATT = "ACTIVITY.SQL_INSERT_TPATT";
	String SQL_INSERT_TPRELATT = "ACTIVITY.SQL_INSERT_TPRELATT";
	
	//deleteNoAttechment
	String SQL_DELETENO_ATTECHMENT = "ACTIVITY.SQL_DELETENO_ATTECHMENT";
	
	//update flag of attachment
	String SQL_UPDATE__FLAG_TPRELATT = "ACTIVITY.SQL_UPDATE__FLAG_TPRELATT";
	
	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;
	
	private Logger logger = Logger.getLogger(getClass());

	@Override
	public ServiceResult<Page<ActivityDTO>> searchByCriteria(Pageable pageable, ActivityCriteria activityCriteria) {
		
		ServiceResult<Page<ActivityDTO>> serviceResult = null;
		Page<ActivityDTO> result = null;
				
		try{
			logger.info("+++++++++++++ In Befor findForPage ++++++++++++++++++++ : "+activityCriteria.getLangCd());
			result = dynamicJdbcDao.findForPage(SQL_SEARCH_ACTIVITY, PrimitiveSafeBeanPropertyRowMapper.newInstance(ActivityDTO.class), pageable, activityCriteria);
			logger.info("+++++++++++++ In Befor result :"+result.getSize());
			serviceResult = new ServiceResult<Page<ActivityDTO>>(result);
			
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			serviceResult = new ServiceResult<Page<ActivityDTO>>(e);
		}
		return serviceResult; 
	}
	
	@Override
	public ServiceResult<List<ActivityAttFileDTO>> searchActivityDocumentList(String actDocNo) {
		logger.info("******************** searchActivityDocumentList ********************");
		ServiceResult<List<ActivityAttFileDTO>> serviceResult = new ServiceResult<List<ActivityAttFileDTO>>();
		List<ActivityAttFileDTO> result = null;		
		logger.info("actDocNo = "+actDocNo);
		try{
			result = dynamicJdbcDao.findForList(SQL_SEARCH_DOCUMENT_ACTIVITY,BeanPropertyRowMapper.newInstance(ActivityAttFileDTO.class)
									,new SimpleKeyValue("actDocNo",actDocNo)
			);
			
			serviceResult = new ServiceResult<List<ActivityAttFileDTO>>(result);
			System.out.println("Element result :: "+serviceResult.getResult().size());
			
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			serviceResult = new ServiceResult<List<ActivityAttFileDTO>>(e);
		}
		return serviceResult;
	}
	
	@Override
	public ServiceResult<ActivityDTO> searchActivityListDetailByActNumber(String actNumber, String langCd) throws Exception {
		 ServiceResult<ActivityDTO> srActList = null;
		 ActivityDTO srActDTO = new ActivityDTO();
		    logger.info("+++++++++++++ Begin searchSRActivityListDetailByActNumber ++++++++++++++++++++");
		    try {
		    	
		    	srActDTO = dynamicJdbcDao.findForObject(SQL_SEARCH_ACTIVITY_DETAIL_BY_ACT_NUMBER,
		    			BeanPropertyRowMapper.newInstance(ActivityDTO.class),
		    			new SimpleKeyValue("actNumber", actNumber), new SimpleKeyValue("langCd", langCd));
		    	srActList = new ServiceResult<ActivityDTO>(srActDTO);
		    	logger.info("+++++++++++++ End searchSRActivityListDetailByActNumber  ++++++++++++++++++++");
		    	
		    }catch(Exception e){
		    	logger.error(e.getMessage(), e);
				srActList = new ServiceResult<ActivityDTO>(e);
		    }
		    return srActList;
	}
	
	@Override
	public ServiceResult<Long> insertActivity(ActivityDTO dto) {
		
		Long result = null;
		ServiceResult<Long> serviceResult = null;
		logger.info("******************** insertActivity ********************");
		System.out.println("===============================================");
		
		try {
			// insert TP_ACTIVITY
			result = dynamicJdbcDao.executeInsert(SQL_INSERT_ACTIVITY, false,
					                                new SimpleKeyValue("actNumber", 	dto.getActNumber()),
					                                new SimpleKeyValue("activityGroup", dto.getActivityGroup()),
													new SimpleKeyValue("refDocNo",      dto.getRefDocNo()),	        
													new SimpleKeyValue("attendTo",      dto.getAttendTo()),	        
													new SimpleKeyValue("title",         dto.getTitle()),	                
													new SimpleKeyValue("typeCd",        dto.getTypeCd()),	                
													new SimpleKeyValue("subTypeCd",     dto.getSubTypeCd()),	        
													new SimpleKeyValue("description",   dto.getDescription()),	        
													new SimpleKeyValue("phoneNo",       dto.getPhoneNo()),	        
													new SimpleKeyValue("smsNo",         dto.getSmsNo()),	                
													new SimpleKeyValue("faxNo",         dto.getFaxNo()),	                
													new SimpleKeyValue("email",         dto.getEmail()),	                
													new SimpleKeyValue("address",       dto.getAddress()),	        
													new SimpleKeyValue("statusCd",      dto.getStatusCd()),	 
													new SimpleKeyValue("ownerId",  		dto.getOwnerId()),
													new SimpleKeyValue("ownerDeptCode", dto.getOwnerDeptCode()),	
													new SimpleKeyValue("openedDt",      new Date()),	        
													new SimpleKeyValue("dueDt",         dto.getDueDt()),	                
													new SimpleKeyValue("operDt",        dto.getOperDt()),	                
													new SimpleKeyValue("closedDt",      dto.getClosedDt()),
													new SimpleKeyValue("regId",         dto.getRegId()),	                
													new SimpleKeyValue("regDt",         new Date())               
													);
			serviceResult = new ServiceResult<Long>(result);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			setErrorResult(serviceResult, e);
		} 
		return serviceResult;
	}
	
	@Override
	public ServiceResult<Long> updateActivity(ActivityDTO dto) {
		
		long result = 0;
		ServiceResult<Long> serviceResult = null;
		try {
			result = dynamicJdbcDao.executeUpdate(SQL_UPDATE_ACTIVITY, dto);
			serviceResult = new ServiceResult<Long>(new Long(result));
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			serviceResult = new ServiceResult<Long>(e);
		}
		
		return serviceResult;
	}
	
	@Override
	public ServiceResult<Long> insertTpAttachment(ActivityAttFileDTO tpAttDTO) {
		
		ServiceResult<Long> serviceResult = new ServiceResult<Long>();
		
		try {
			// insert TP_ATT
			logger.info("************ insertTpAttachment ************");
			
			Long recordNum = dynamicJdbcDao.executeInsert(SQL_INSERT_TPATT, true,
													new SimpleKeyValue("filePath",      tpAttDTO.getFilePath()),        
													new SimpleKeyValue("fileName",      tpAttDTO.getFileName()),	       
													new SimpleKeyValue("fileExtension", tpAttDTO.getFileExtension()),	                
													new SimpleKeyValue("fileType",      tpAttDTO.getFileType()),	                
													new SimpleKeyValue("activeFlg",     tpAttDTO.getActiveFlg()),
													new SimpleKeyValue("attType",       tpAttDTO.getAttType()),	                
													new SimpleKeyValue("fileSize",      tpAttDTO.getFileSize()),
													//new SimpleKeyValue("attSourceCode", tpAttDTO.getAttSourceCode()),	        
													//new SimpleKeyValue("userPicture",   tpAttDTO.getUserPicture()),	
													new SimpleKeyValue("regId",         tpAttDTO.getRegId()),
													new SimpleKeyValue("regDt",         tpAttDTO.getRegDt())
													);
			serviceResult.setSuccess(true);
			serviceResult.setResult(recordNum);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			setErrorResult(serviceResult, e);
		}
		return serviceResult;
	}
	
	@Override
	public ServiceResult<Long> insertTpRelAttAttachment(ActivityAttFileDTO tpRelAttDTO) {
		
		ServiceResult<Long> serviceResult = new ServiceResult<Long>();
		
		try {
			// insert TP_REL_ATT
			logger.info("********* insertTpRelAttAttachment *********");
			
			Long recordNum = dynamicJdbcDao.executeInsert(SQL_INSERT_TPRELATT, true,
													new SimpleKeyValue("refDocType",   tpRelAttDTO.getRefDocType()),        
													new SimpleKeyValue("refDocNo",     tpRelAttDTO.getRefDocNo()),	       
													new SimpleKeyValue("attName",	   tpRelAttDTO.getAttName()),	                
													new SimpleKeyValue("descp",        tpRelAttDTO.getDescp()),	                
													new SimpleKeyValue("regId",        tpRelAttDTO.getRegId()),	        
													new SimpleKeyValue("regTime", 	   tpRelAttDTO.getRegTime()),	        
													new SimpleKeyValue("sendDocFlg",   tpRelAttDTO.getSendDocFlg()),
													new SimpleKeyValue("attId",tpRelAttDTO.getAttId())
													);
			serviceResult.setSuccess(true);
			serviceResult.setResult(recordNum);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			setErrorResult(serviceResult, e);
		}
		return serviceResult;
	}
	
	@Override
	public ServiceResult<Long> deleteAttByNumber(String relAttId)throws Exception {
		
		int result;
		ServiceResult<Long> serviceResult = null;
		try {
			logger.info("************ deleteAttByNumber ************");
			result = dynamicJdbcDao.executeUpdate(SQL_DELETENO_ATTECHMENT, new SimpleKeyValue("relAttId",relAttId));
			serviceResult = new ServiceResult<Long>(new Long(result));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			serviceResult = new ServiceResult<Long>(e);
		}
		return serviceResult;	
	}

	@Override
	public ServiceResult<Long> updateActAttFlg(String relAttId, String chkSenDocFlgParame, Integer chgId, Date chgTime) {
		
		logger.info("************ updateActAttFlg ************");
		
		ServiceResult<Long> serviceResult = new ServiceResult<Long>();
		
		try {
			
			long result = dynamicJdbcDao.executeUpdate(SQL_UPDATE__FLAG_TPRELATT, 
													 new SimpleKeyValue("relAttId",relAttId)
													,new SimpleKeyValue("sendDocFlg",chkSenDocFlgParame)
													,new SimpleKeyValue("chgId",chgId)
													,new SimpleKeyValue("chgTime",chgTime)
													);
			serviceResult = new ServiceResult<Long>(new Long(result));
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			serviceResult = new ServiceResult<Long>(e);
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<Page<ActivityDTO>> findById(Pageable pageable,String actNumber, String langCd) {
		
		ServiceResult<Page<ActivityDTO>> serviceResult = null;
		Page<ActivityDTO> result = null;
		try {
			result = dynamicJdbcDao.findForPage(SQL_SEARCH_ACTIVITY_DETAIL, PrimitiveSafeBeanPropertyRowMapper.newInstance(ActivityDTO.class), pageable, new SimpleKeyValue("actNumber", actNumber),
					 new SimpleKeyValue("langCd", langCd));
			serviceResult = new ServiceResult<Page<ActivityDTO>>(result);
			
		} catch (Exception e) {
			serviceResult = new ServiceResult<Page<ActivityDTO>>(e);
		}
		return serviceResult;
	}
}
