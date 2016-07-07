package com.locus.jlo.web.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.locus.common.domain.ServiceResult;
import com.locus.common.utils.DateTimeUtils;
import com.locus.common.utils.JsonUtil;
import com.locus.common.utils.StringUtils;
import com.locus.jlo.web.service.ActivityManagementService;
import com.locus.jlo.web.service.SequenceGeneratorService;
import com.locus.jlo.web.constant.ActionType;
import com.locus.jlo.web.constant.SequenceType;
import com.locus.jlo.web.bean.criteria.ActivityCriteria;
import com.locus.jlo.web.bean.dto.ActivityAttFileDTO;
import com.locus.jlo.web.bean.dto.ActivityDTO;
import com.locus.jlo.web.bean.dto.ContentDetailDTO;
import com.locus.jlo.web.bean.dto.UserInfoDTO;
import com.locus.jlo.web.bean.modelbean.ActivityModelBean;
import com.locus.jlo.web.bean.modelbean.ActivitytAttachmentModelBean;
import com.locus.jlo.web.constant.JLOWebConstant;
import com.locus.jlo.web.controller.BaseController;
import com.locus.jlo.web.bean.modelbean.UserLoginInfoModelBean;
import com.locus.jlo.web.bean.modeljson.DatatableModelBean;
import com.locus.jlo.web.bean.modeljson.JsonResultBean;

@Controller
public class ActivityController extends BaseController {
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private ActivityManagementService activityManagementService;
	
	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;
	
	@Autowired
	ServletContext context;
	
	private Logger logger = Logger.getLogger(getClass());
	
	private static final String ACTIVITY_MODEL_BEAN           = "activityModelBean";
	private static final String PATTERN_DATE_ACT_INSERT_UPDATE = "dd/MM/yyyy HH:mm";
	
	private static final String ACT_ATT_REL_TYPE = "ACT";
	
	private String SUCCESS = "";
	private String MESSAGE = "";
	private String MESSAGE_CODE = "";
	
	@RequestMapping(value = "/activity" , method = RequestMethod.POST)
	public ModelAndView activityManagement(Model model, HttpServletRequest request, Locale local,
		@RequestParam(value = "act_number", required = false) String activityNumber,
		@RequestParam(value = "module", required = false) String module) throws Exception {
		
		ActivityModelBean actModelBean = new ActivityModelBean();
		model.addAttribute("pageTitle", messageSource.getMessage("activity.titleMain", null, local));
		
		// Set menuId
		setMenuId(request);
		
		logger.info("******* activityManagement *******");
		logger.info(this.getClass().toString());
		actModelBean.setActivityNumber(activityNumber);
		actModelBean.setMode(JLOWebConstant.MODE_UPDATE);
		
		if(!StringUtils.isEmpty(module)){
			actModelBean.setModule(module);
		}else{
			actModelBean.setModule("other");
		}
		
		logger.info("activity number = "+activityNumber);
		logger.info("actModelBean mode = "+actModelBean.getMode());
		
		return new ModelAndView("activity",ACTIVITY_MODEL_BEAN,actModelBean);
		
	}
	
	@RequestMapping(value = "/getActivityList", method = RequestMethod.POST, headers = { "Accept=application/json" })
	public @ResponseBody String getActivityList(HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception {
		
		logger.info("******* getActivityList *******");
		logger.info("activityNumber == "+request.getParameter("activityNumber"));
		String activityNumber = request.getParameter("activityNumber");
		String activityTypeParam = request.getParameter("activityTypeParam");
		String activityStatusParam = request.getParameter("activityStatusParam");
		String ownerId = request.getParameter("ownerId");
		
		String activityCreatedDateFrom = request.getParameter("activityCreatedDateFrom");
		String activityCreatedDateTo = request.getParameter("activityCreatedDateTo");
		String activityDueDateFrom = request.getParameter("activityClosedDateFrom");
		String activityDueDateTo = request.getParameter("activityClosedDateTo");
		String activityGroup = request.getParameter("activityGroup");
		logger.info("ownerId : " + ownerId + "activityTypeParam : " + activityTypeParam);
		
		String langCd = getLanguageCode(locale);
		
		ActivityCriteria activityCriteria = new ActivityCriteria();
		
		if(StringUtils.isNotEmpty(langCd)){
			activityCriteria.setLangCd(langCd);
		}
		
		if(StringUtils.isNotEmpty(activityNumber)){
			activityCriteria.setActNumber(activityNumber);
		}
		
		if(StringUtils.isNotEmpty(activityGroup)){
			activityCriteria.setActGroup(activityGroup);
		}
		
		if(StringUtils.isNotEmpty(activityTypeParam)){
			activityCriteria.setTypeCd(activityTypeParam);
		}
		
		if(StringUtils.isNotEmpty(activityStatusParam)){
			activityCriteria.setStatusCd(activityStatusParam);
		}
		
		if(StringUtils.isNotEmpty(ownerId)){
			activityCriteria.setOwnerId(ownerId);
		}
		
		if(StringUtils.isNotEmpty(activityCreatedDateFrom)){
			String act_createDateFrom =  DateTimeUtils.convertDateStringToDateStringFormat(activityCreatedDateFrom, JLOWebConstant.DATE_PATTERN_SEARCH);
			activityCriteria.setRegDtForm(act_createDateFrom);
		}
		
		if(StringUtils.isNotEmpty(activityCreatedDateTo)){
			String act_createDateTo =  DateTimeUtils.convertDateStringToDateStringFormat(activityCreatedDateTo, JLOWebConstant.DATE_PATTERN_SEARCH);
			activityCriteria.setRegDtTo(act_createDateTo);
		}
		
		if(StringUtils.isNotEmpty(activityDueDateFrom)){
			String act_dueDateFrom =  DateTimeUtils.convertDateStringToDateStringFormat(activityDueDateFrom, JLOWebConstant.DATE_PATTERN_SEARCH);
			activityCriteria.setDueDtForm(act_dueDateFrom);
		}
		
		if(StringUtils.isNotEmpty(activityDueDateTo)){
			String act_dueDateTo =  DateTimeUtils.convertDateStringToDateStringFormat(activityDueDateTo, JLOWebConstant.DATE_PATTERN_SEARCH);
			activityCriteria.setDueDtTo(act_dueDateTo);
		}
		
		String sEchoTxt = request.getParameter("sEcho");
		Integer sEcho = (!StringUtils.isNullOrEmpty(sEchoTxt)) ? Integer.valueOf(sEchoTxt) : 0;
		
		
		Pageable pageable = getPagableFromRequest(request);
		DatatableModelBean datatableModelBean = new DatatableModelBean();
		List<ActivityModelBean> activityModelBeanList = new ArrayList<ActivityModelBean>();
		
		// Get owner filter
		List<String> ownerFilter = getOwnerGroup(request, ActionType.READ.getActionCode());
		logger.info("ownerFilter : " + ownerFilter);
		if (ownerFilter != null) {
			activityCriteria.setOwnerGroup(ownerFilter);
			ServiceResult<Page<ActivityDTO>> serviceResult = activityManagementService.searchByCriteria(pageable,activityCriteria);
			
			if (serviceResult.isSuccess()) {
				
				Page<ActivityDTO> actResult = serviceResult.getResult();
				Integer totalRecords = (int) (long)actResult.getTotalElements();
				
				ActivityModelBean activityModelBean = new ActivityModelBean();
				
				for (ActivityDTO activityDTO : actResult) {
					activityModelBean = new ActivityModelBean();
					
					activityModelBean.setOpenedDt(DateTimeUtils.formatDateTime(new Date()));
					
					activityModelBean.setActivityStatusName(activityDTO.getActivityStatusName());
					activityModelBean.setActivityTypeName(activityDTO.getActivityTypeName());
					
					activityModelBean.setActNumber(activityDTO.getActNumber());
					activityModelBean.setAttendTo(activityDTO.getAttendTo());
					activityModelBean.setTitle(activityDTO.getTitle());
					activityModelBean.setTypeCd(activityDTO.getTypeCd());
					activityModelBean.setDescription(activityDTO.getDescription());
					activityModelBean.setPhoneNo(activityDTO.getPhoneNo());
					activityModelBean.setSmsNo(activityDTO.getSmsNo());
					activityModelBean.setFaxNo(activityDTO.getFaxNo());
					activityModelBean.setEmail(activityDTO.getEmail());
					activityModelBean.setAddress(activityDTO.getAddress());
					activityModelBean.setStatusCd(activityDTO.getStatusCd());
					activityModelBean.setAssignToId(activityDTO.getAssignToId());
					activityModelBean.setAssignToName(activityDTO.getOwnerName());
					activityModelBean.setOwnerDeptCode(activityDTO.getOwnerDeptCode());
					activityModelBean.setActivityGroup(activityDTO.getActivityGroup());
					
					activityModelBean.setOwnerId(activityDTO.getOwnerId());
					activityModelBean.setOwnerName(activityDTO.getOwnerName());
					activityModelBean.setDeptName(activityDTO.getDeptName());

					activityModelBean.setDueDt(DateTimeUtils.formatDate(activityDTO.getDueDt()));
		   			activityModelBean.setDueTime(DateTimeUtils.parseFormatDateTime(activityDTO.getDueDt()));
		   			
		   			activityModelBean.setOperDt(DateTimeUtils.formatDateTime(activityDTO.getOperDt()));
		   			activityModelBean.setOperTime(DateTimeUtils.parseFormatDateTime(activityDTO.getOperDt()));
		   			
		   			activityModelBean.setClosedDt(DateTimeUtils.formatDateTime(activityDTO.getClosedDt()));
		   			activityModelBean.setClosedTime(DateTimeUtils.parseFormatDateTime(activityDTO.getClosedDt()));
		   			
		   			activityModelBean.setMode(JLOWebConstant.MODE_UPDATE);
		   			
		   			// For Panel activity Bottom
		   			activityModelBean.setRegId(activityDTO.getRegId());
		   			activityModelBean.setChgId(activityDTO.getChgId());
		   			activityModelBean.setRegName(!StringUtils.isNullOrEmpty(activityDTO.getRegName()) ? activityDTO.getRegName() : "");
		   			activityModelBean.setRegDatetime(!StringUtils.isNullOrEmpty(activityDTO.getRegDatetime()) ? activityDTO.getRegDatetime() : "");
		   			
		   			activityModelBean.setChgName(!StringUtils.isNullOrEmpty(activityDTO.getChgName()) ? activityDTO.getChgName() : "");
		   			activityModelBean.setChgDatetime(!StringUtils.isNullOrEmpty(activityDTO.getChgDatetime()) ? activityDTO.getChgDatetime() : "");
		   			
					activityModelBeanList.add(activityModelBean);
					
					if (checkVisibility(request, ActionType.EDIT.getActionCode(), activityDTO.getOwnerId())) {
						String contextPath = request.getContextPath();
						StringBuffer url = new StringBuffer();
						url.append("<a href='#' onclick='postAction(\"" + contextPath + "/activity.htm?id=" + activityDTO.getActId() + "&type=" + activityDTO.getTypeCd() + "\")'>");
						url.append("<i class='fa fa-pencil'></i>");
						url.append("</a>");
						logger.info("edit url : " + url);
						activityModelBean.setEditUrl(url.toString());
					} else {
						
						activityModelBean.setEditUrl("");
					}
				}
				
				// Set server prop return result
				datatableModelBean.setsEcho(sEcho);
				datatableModelBean.setiTotalDisplayRecords(totalRecords);
				datatableModelBean.setiTotalRecords(totalRecords);
				datatableModelBean.setAaData(activityModelBeanList);
			
			} else {
				showWebMessage(request, serviceResult.getResponseCode(), serviceResult.getResponseDescription());
			}
		}
		
		return JsonUtil.toJSON( datatableModelBean, Boolean.TRUE );
	}
	
	@RequestMapping(value = "/getActivityDocumentList", headers = { "Accept=application/json" })
	public @ResponseBody String getActivityDocumentList(HttpServletRequest request, HttpServletResponse response, Locale local,
		   @RequestParam(value = "actDocNo", required = false) String actDocNo) throws Exception {
	
		logger.info("******* getActivityDocumentList *******");
	
		String actRefNoParam = "";
		if(StringUtils.isEmpty(actDocNo)){
			actRefNoParam = "-1";
		}else{
			actRefNoParam = actDocNo;
		}
		
		List<ActivitytAttachmentModelBean> actAttListModelBean = new ArrayList<ActivitytAttachmentModelBean>();
		ActivitytAttachmentModelBean actAttModelBean = new ActivitytAttachmentModelBean();
		ServiceResult<List<ActivityAttFileDTO>> serviceResult = activityManagementService.searchActivityDocumentList(actRefNoParam);
		
		logger.info("isSuccess : "+serviceResult.isSuccess());
		Integer no = 1;
		
		if (serviceResult.isSuccess()) {
			
				List<ActivityAttFileDTO> result = serviceResult.getResult();
				
				for(ActivityAttFileDTO  actAttContent : result){
					
					actAttModelBean = new ActivitytAttachmentModelBean();
					
					actAttModelBean.setNo(no);
					actAttModelBean.setAttId(actAttContent.getAttId());
					actAttModelBean.setActDocumentName(actAttContent.getActDocumentName());
					actAttModelBean.setActKnowledgeBase(actAttContent.getActKnowledgeBase());
					actAttModelBean.setActSendDocFlg(actAttContent.getActSendDocFlg());
					actAttModelBean.setActDescription(actAttContent.getActDescription());
					actAttModelBean.setActRefDocType(actAttContent.getActRefDocType());
					actAttModelBean.setRelAttId(actAttContent.getRelAttId());
					actAttModelBean.setMode(JLOWebConstant.MODE_UPDATE);
					
					no = no+1;
					actAttListModelBean.add(actAttModelBean);
				}
				
		} else {
			showWebMessage(request, serviceResult.getResponseCode(), "error");
		}	
		
		String jsonResultAttKb = JsonUtil.jsonSerialize(actAttListModelBean);
		logger.info("Result Json Format ::  "+jsonResultAttKb);
		
		return jsonResultAttKb;
	}
	
	@RequestMapping(value = {"/insertAct", "/updateAct"},method = RequestMethod.POST, headers = { "Accept=application/json; charset=UTF-8" })
	public @ResponseBody String insertOrUpdateActivity(HttpServletRequest request, HttpServletResponse response,Locale local,
			@ModelAttribute("actModelBean") ActivityModelBean actModelBean)throws Exception {
		
		JsonResultBean result = new JsonResultBean();
		String actNumber = "";
		String langCd = getLanguageCode(local);
		logger.info("actModelBean mode "+actModelBean.getMode());
		if(actModelBean.isInsertMode()){
			logger.info("*************insertActivity*************");
			
			ActivityDTO dtoInsert = new ActivityDTO();
			
			actModelBean.setRegId(getUserId(request));
			actModelBean.setRegName(getUserLoginId(request));
			
			if(actModelBean != null){
				dtoInsert = setActivityModelToDtoForInsert(actModelBean);
				actNumber = dtoInsert.getActNumber();
			}
			logger.info("actNumber :"+actNumber);
			
			ServiceResult<Long> resultSave = activityManagementService.insertActivity(dtoInsert);
			Long insertACT = resultSave.getResult();
			
			if(resultSave.isSuccess() && insertACT > 0){
				actModelBean = getActivityDetailByActNumber(actNumber, langCd);
	 			setSUCCESS("True");
	 			MESSAGE = messageSource.getMessage("lbl.save.success", null, local);	
	 			MESSAGE_CODE = JLOWebConstant.INSERT_SUCCESS_CODE;
	 			actModelBean.setMode(JLOWebConstant.MODE_UPDATE);
	 			result.setResultCode(MESSAGE_CODE);
	 			result.setResultMessage(MESSAGE);
	 			result.setModel(actModelBean);
	 			 
	 		 }else{
	 			 setSUCCESS("False");
	 			 MESSAGE = messageSource.getMessage("lbl.save.fail", null, local);	
	 			 MESSAGE_CODE = JLOWebConstant.INSERT_FAIL_CODE;
	 			 actModelBean.setMode(JLOWebConstant.MODE_UPDATE);
	 			 result.setModel(actModelBean);
	 			 result.setResultCode(MESSAGE_CODE);
	 			 result.setResultMessage(MESSAGE);
	 		 }
		
		}else{
			logger.info("*************updateActivity*************");
		 	ActivityDTO dtoUpdate = new ActivityDTO();
//		 	UserLoginInfoModelBean userInfo = getUserLoginInfo(request);
		 	
		 	UserInfoDTO userInfo = getUserInfo(request);
		 	
		 	dtoUpdate = setActivityModelToDtoForUpdate(actModelBean,userInfo);
		 	actNumber = dtoUpdate.getActNumber();
		 	
		 	List<String> ownerFilter = getOwnerGroup(request, ActionType.EDIT.getActionCode());
			logger.info("ownerFilter : " + ownerFilter);
			if (ownerFilter != null) {
					// Check permission for save  
					if (!checkVisibility(request, ActionType.EDIT.getActionCode(), dtoUpdate.getRegId())) {
						result.setResultCode(JLOWebConstant.FAIL_CODE);
						result.setResultMessage(messageSource.getMessage("lbl.nopermission.edit", null, local));
						return JsonUtil.toJSON( result, true );
					}
			} else {
				result.setResultCode(JLOWebConstant.FAIL_CODE);
				result.setResultMessage(messageSource.getMessage("lbl.nopermission.edit", null, local));
				return JsonUtil.toJSON(result, true );
			}
		 	
		 	ServiceResult<Long> serviceResult = activityManagementService.updateActivity(dtoUpdate);
		 	Long updateACT = serviceResult.getResult();
		 	logger.info("ResultCode Delete :"+updateACT);
		 	 
		 	 if(serviceResult.isSuccess() && updateACT > 0){
		 		actModelBean = getActivityDetailByActNumber(actNumber, langCd);
		 			setSUCCESS("True");
		 			MESSAGE = messageSource.getMessage("lbl.save.success", null, local);	
		 			MESSAGE_CODE = JLOWebConstant.INSERT_SUCCESS_CODE;
		 			actModelBean.setMode(JLOWebConstant.MODE_UPDATE);
		 			result.setResultCode(MESSAGE_CODE);
		 			result.setResultMessage(MESSAGE);
		 			result.setModel(actModelBean);
	 			 
	 		 }else{
	 			 setSUCCESS("False");
	 			 MESSAGE = messageSource.getMessage("lbl.save.fail", null, local);	
	 			 MESSAGE_CODE = JLOWebConstant.INSERT_FAIL_CODE;
	 			 actModelBean.setMode(JLOWebConstant.MODE_UPDATE);
	 			 result.setModel(actModelBean);
	 			 result.setResultCode(MESSAGE_CODE);
	 			 result.setResultMessage(MESSAGE);
	 		 }
		}
	 	 return JsonUtil.toJSON(result, Boolean.TRUE);
	 }
	
	private ActivityModelBean getActivityDetailByActNumber(String actNumber, String langCd) throws Exception{
		
		ActivityModelBean actModelBean = new ActivityModelBean();
		ActivityDTO activityDTO = new ActivityDTO();
		ServiceResult<ActivityDTO> serviceResult = activityManagementService.searchActivityListDetailByActNumber(actNumber, langCd);
		
		if(serviceResult.isSuccess()){
			activityDTO = serviceResult.getResult();
			actModelBean.setActNumber(activityDTO.getActNumber());
			actModelBean.setAttendTo(activityDTO.getAttendTo());
			actModelBean.setTitle(activityDTO.getTitle());
			actModelBean.setTypeCd(activityDTO.getTypeCd());
			actModelBean.setDescription(activityDTO.getDescription());
			actModelBean.setPhoneNo(activityDTO.getPhoneNo());
			actModelBean.setFaxNo(activityDTO.getFaxNo());
			actModelBean.setEmail(activityDTO.getEmail());
			actModelBean.setAddress(activityDTO.getAddress());
			actModelBean.setStatusCd(activityDTO.getStatusCd());
			actModelBean.setAssignToId(activityDTO.getAssignToId());
			actModelBean.setAssignToName(activityDTO.getAssignToName());
			actModelBean.setDeptName(activityDTO.getDeptName());
			actModelBean.setOwnerName(activityDTO.getOwnerName());

			actModelBean.setDueDt(DateTimeUtils.formatDate(activityDTO.getDueDt()));
   			actModelBean.setDueTime(DateTimeUtils.parseFormatDateTime(activityDTO.getDueDt()));
   			
   			actModelBean.setOperDt(DateTimeUtils.formatDate(activityDTO.getOperDt()));
   			actModelBean.setOperTime(DateTimeUtils.parseFormatDateTime(activityDTO.getOperDt()));
   			
   			actModelBean.setClosedDt(DateTimeUtils.formatDate(activityDTO.getClosedDt()));
   			actModelBean.setClosedTime(DateTimeUtils.parseFormatDateTime(activityDTO.getClosedDt()));
   			
   			actModelBean.setMode(JLOWebConstant.MODE_UPDATE);
   			
   			// For Panel activity Bottom
   			actModelBean.setRegName(!StringUtils.isNullOrEmpty(activityDTO.getRegName()) ? activityDTO.getRegName() : "");
   			actModelBean.setChgName(!StringUtils.isNullOrEmpty(activityDTO.getChgName()) ? activityDTO.getChgName() : "");
   			
   			actModelBean.setRegDatetime(!StringUtils.isNullOrEmpty(activityDTO.getRegDatetime()) ? activityDTO.getRegDatetime() : "");
   			actModelBean.setChgDatetime(!StringUtils.isNullOrEmpty(activityDTO.getChgDatetime()) ? activityDTO.getChgDatetime() : "");
		} 
		return actModelBean;
	}
	
	private ActivityDTO setActivityModelToDtoForInsert(ActivityModelBean modelBean){
		
		String actNumberSeqKey = "";
		String dueTime  = "";
		String operTime = "";
		String actNumberGen = sequenceGeneratorService.generateByType(SequenceType.ACTIVITY);
		actNumberSeqKey = actNumberGen;
		
		ActivityDTO dto = new ActivityDTO();
		 
		 if(!StringUtils.isEmpty(actNumberSeqKey)){
				
				dto.setActNumber    (actNumberSeqKey);
				dto.setActGroup		(modelBean.getActGroup());
				dto.setRefDocNo     (modelBean.getRefDocNo());	
				dto.setAttendTo     (modelBean.getAttendTo());	
				dto.setTitle        (modelBean.getTitle());	
				dto.setTypeCd       (modelBean.getTypeCd());
				dto.setSubTypeCd    (modelBean.getSubTypeCd());	
				dto.setDescription  (modelBean.getDescription());	
				dto.setPhoneNo      (modelBean.getPhoneNo());	
				dto.setSmsNo        (modelBean.getSmsNo());	
				dto.setFaxNo        (modelBean.getFaxNo());	
				dto.setEmail        (modelBean.getEmail());	
				dto.setAddress      (modelBean.getAddress());	
				dto.setStatusCd     (modelBean.getStatusCd());	
				dto.setOwnerId      (modelBean.getAssignToId());	
				dto.setOwnerDeptCode(modelBean.getOwnerDeptCode());
				dto.setRegId        (modelBean.getRegId());
				
				dto.setActivityTypeName  (modelBean.getActivityTypeName());
				dto.setActivityStatusName(modelBean.getActivityStatusName());
				dto.setActivityGroup	 (modelBean.getActivityGroup());
				dto.setOwnerName         (modelBean.getAssignToName());
				dto.setDeptName          (modelBean.getDeptName());
				dto.setRegName           (modelBean.getRegName());
				dto.setRegDatetime       (modelBean.getRegDatetime());
				
				if(StringUtils.isNotEmpty(modelBean.getDueTime())){
					dueTime = modelBean.getDueTime();
				}else{
					dueTime = "00:00";
				}
				
				if(StringUtils.isNotEmpty(modelBean.getOperTime())){
					operTime = modelBean.getOperTime();
				}else{
					operTime = "00:00";
				}
				
				if(StringUtils.isNotEmpty(modelBean.getOpenedDt())){
					dto.setOpenedDt(DateTimeUtils.parseDate(modelBean.getOpenedDt(), PATTERN_DATE_ACT_INSERT_UPDATE));	
				}
				
				if(StringUtils.isNotEmpty(modelBean.getDueDt())){
					dto.setDueDt(DateTimeUtils.parseDate(modelBean.getDueDt() + " " + dueTime, PATTERN_DATE_ACT_INSERT_UPDATE));	
				}
				
				if(StringUtils.isNotEmpty(modelBean.getOperDt())){
					dto.setOperDt(DateTimeUtils.parseDate(modelBean.getOperDt() + " " + operTime, PATTERN_DATE_ACT_INSERT_UPDATE));
				}
				
				if(StringUtils.isNotEmpty(modelBean.getStatusCd())){
		        	String cpActStatusCd = modelBean.getStatusCd();
		        	
		        	if(cpActStatusCd.equals("03")){
		        		dto.setClosedDt(new Date());
		        	}else{
		        		dto.setClosedDt(null);
		        	}
				}
				
				if(StringUtils.isNotEmpty(modelBean.getRegDt())){
					dto.setRegDt(DateTimeUtils.parseDate(modelBean.getRegDt(), PATTERN_DATE_ACT_INSERT_UPDATE));	
				}
		 }
		return dto;
	}
	
	private ActivityDTO setActivityModelToDtoForUpdate(ActivityModelBean modelBean,UserInfoDTO userInfo){
		
		String dueTime  = "";
		String operTime = "";
		
		ActivityDTO dto = new ActivityDTO();

		dto.setActNumber(modelBean.getActNumber());
		dto.setAttendTo(modelBean.getAttendTo());
		dto.setTitle(modelBean.getTitle());
		dto.setTypeCd(modelBean.getTypeCd());
		dto.setSubTypeCd(modelBean.getSubTypeCd());
		dto.setDescription(modelBean.getDescription());
		dto.setPhoneNo(modelBean.getPhoneNo());
		dto.setSmsNo(modelBean.getSmsNo());
		dto.setFaxNo(modelBean.getFaxNo());
		dto.setEmail(modelBean.getEmail());
		dto.setAddress(modelBean.getAddress());
		dto.setStatusCd(modelBean.getStatusCd());
		dto.setOwnerId(modelBean.getAssignToId());
		dto.setOwnerDeptCode(modelBean.getOwnerDeptCode());
		dto.setChgId(userInfo.getUserId());
		dto.setRegId(modelBean.getRegId());
		dto.setActivityTypeName(modelBean.getActivityTypeName());
		dto.setActivityStatusName(modelBean.getActivityStatusName());
		dto.setOwnerName(modelBean.getAssignToName());
		dto.setDeptName(modelBean.getDeptName());
		dto.setChgDt(new Date());
		
		if(StringUtils.isNotEmpty(modelBean.getDueTime())){
			dueTime = modelBean.getDueTime();
		}else{
			dueTime = "00:00";
		}
		
		if(StringUtils.isNotEmpty(modelBean.getOperTime())){
			operTime = modelBean.getOperTime();
		}else{
			operTime = "00:00";
		}
		
		if(StringUtils.isNotEmpty(modelBean.getOpenedDt())){
			dto.setOpenedDt(DateTimeUtils.parseDate(modelBean.getOpenedDt(), PATTERN_DATE_ACT_INSERT_UPDATE));	
		}
		
		if(StringUtils.isNotEmpty(modelBean.getDueDt())){
			dto.setDueDt(DateTimeUtils.parseDate(modelBean.getDueDt() + " " + dueTime, PATTERN_DATE_ACT_INSERT_UPDATE));	
		}
		
		if(StringUtils.isNotEmpty(modelBean.getOperDt())){
			dto.setOperDt(DateTimeUtils.parseDate(modelBean.getOperDt() + " " + operTime, PATTERN_DATE_ACT_INSERT_UPDATE));
		}
		
		if(StringUtils.isNotEmpty(modelBean.getStatusCd())){
        	String cpActStatusCd = modelBean.getStatusCd();
        	
        	if(cpActStatusCd.equals("03")){
        		dto.setClosedDt(new Date());
        	}else{
        		dto.setClosedDt(null);
        	}
		}
		
		if(StringUtils.isNotEmpty(modelBean.getRegDt())){
			dto.setRegDt(DateTimeUtils.parseDate(modelBean.getRegDt(), PATTERN_DATE_ACT_INSERT_UPDATE));	
		}
			
		return dto;
	}
	
	@RequestMapping(value = "/uploadAttachFile", headers = { "Accept=application/json; charset=UTF-8" })
	 public @ResponseBody String uploadAttachFile(MultipartHttpServletRequest request, HttpServletResponse response,Locale local,
		 @ModelAttribute("activityAttachmentModelBean") ActivitytAttachmentModelBean activityAttachmentModelBean,
		 @RequestParam(value = "actNoRefNo", required = true) String actNoRefNo) throws Exception {
		
		logger.info("*************uploadAttachFile*************");
		
		//UserLoginInfoModelBean userInfo = getUserLoginInfo(request);
		
		UserInfoDTO userInfo =getUserInfo(request);
		
		ActivityAttFileDTO tpAttDTO = new ActivityAttFileDTO();
		ActivityAttFileDTO tpRelAttDTO = new ActivityAttFileDTO();
		JsonResultBean result = new JsonResultBean();
		Iterator<String> itr = request.getFileNames();
		MultipartFile mpf = null;
		
		if(itr.hasNext()){
			mpf = request.getFile(itr.next()); 
			logger.info("OriginalFilename "+mpf.getOriginalFilename() +", size "+mpf.getSize()+", type "+mpf.getContentType()+", Name "+mpf.getName());
			
			String fileSize =  String.valueOf(mpf.getSize());
			String extFile = "";
			String fileName = mpf.getOriginalFilename();
			String contentType	= mpf.getContentType();
			
			String[] ext = mpf.getOriginalFilename().split("\\.");
			logger.info(" ext :>>> "+ext);
			
			if(ext.length > 1){
				logger.info("ext[ext.length]"+ext[ext.length-1]);
				extFile = ext[ext.length-1];
				
			}else{
				extFile = "unknow";
			}
			
			String path = context.getRealPath(File.separator) + JLOWebConstant.ATTACHMENT;
			String pathFile =  path + JLOWebConstant.ACTIVITY_ATT_PATH;
			logger.info("Absolute Path:"+pathFile+mpf.getOriginalFilename());
			
			File myFile = new File(pathFile+mpf.getOriginalFilename());
			File parentDir = myFile.getParentFile();
			
			if(!parentDir.exists()){
				parentDir.mkdirs(); 
			}
			mpf.transferTo(myFile);
			logger.info("++++++++++++++ Pass Transfer Activity ++++++++++++ ");
			
			tpAttDTO.setFilePath(JLOWebConstant.ACTIVITY_ATT_PATH);
			tpAttDTO.setFileName(fileName);
			tpAttDTO.setActiveFlg("1");
			tpAttDTO.setAttType("");
			tpAttDTO.setFileExtension(extFile);
			tpAttDTO.setFileSize(fileSize);
			tpAttDTO.setFileType(contentType);
			tpAttDTO.setRegId(userInfo.getUserId());
			tpAttDTO.setRegDt(new Date());
			
			ServiceResult<Long> serviceResultTPAtt = activityManagementService.insertTpAttachment(tpAttDTO);
			Long keyAttId = serviceResultTPAtt.getResult();
			logger.info("keyAttId :"+keyAttId);
			logger.info("actNoRefNo :"+actNoRefNo);
			
			if(serviceResultTPAtt.isSuccess() && keyAttId > 0){
				 tpRelAttDTO.setAttId(Integer.parseInt(String.valueOf(keyAttId)));
				 tpRelAttDTO.setRefDocType("ACT");
				 tpRelAttDTO.setRefDocNo(actNoRefNo);
				 tpRelAttDTO.setAttName(fileName);
				 tpRelAttDTO.setDescp("file activity");
				 tpRelAttDTO.setRegId(userInfo.getUserId());
				 tpRelAttDTO.setSendDocFlg("N");
				 tpRelAttDTO.setRegTime(new Date());
				 
				 ServiceResult<Long> serviceResultTPRelAtt = activityManagementService.insertTpRelAttAttachment(tpRelAttDTO);
				 Long insertTpRelAttId =  serviceResultTPRelAtt.getResult();
				 
				 if(serviceResultTPRelAtt.isSuccess() && insertTpRelAttId > 0){
					setSUCCESS("True");
					MESSAGE = messageSource.getMessage("lbl.action.save.success", null, local);	
					MESSAGE_CODE = JLOWebConstant.INSERT_SUCCESS_CODE;
					logger.info("+++++ Pass get Detail Attachment ++++++"); 
					result.setResultCode(MESSAGE_CODE);
					result.setResultMessage(MESSAGE);
				 }else{
					setSUCCESS("False");
					MESSAGE = messageSource.getMessage("lbl.action.save.fail", null, local);	
					MESSAGE_CODE = JLOWebConstant.INSERT_FAIL_CODE;
					result.setResultCode(MESSAGE_CODE);
					result.setResultMessage(MESSAGE+" Can't insert table TP_REL_ATT");
				 }
			}else{
				setSUCCESS("False");
				MESSAGE = messageSource.getMessage("lbl.action.save.fail", null, local);	
				MESSAGE_CODE = JLOWebConstant.INSERT_FAIL_CODE;
				result.setResultCode(MESSAGE_CODE);
				result.setResultMessage(MESSAGE+" Can't insert table TP_ATT");
			}
		}
		
		return JsonUtil.toJSON(result, Boolean.TRUE);
	}
	
	@RequestMapping(value = "/deleteActAttNo",method = RequestMethod.POST, headers = { "Accept=application/json; charset=UTF-8" })
	public @ResponseBody String deleteActAttNo(HttpServletRequest request, HttpServletResponse response,Locale local,
		   @RequestParam(value = "relAttId", required = true) String relAttId) throws Exception {
		
		ActivitytAttachmentModelBean resultActAttachModel = new ActivitytAttachmentModelBean();
		JsonResultBean result = new JsonResultBean();
		
		ServiceResult<Long> serviceResult = activityManagementService.deleteAttByNumber(relAttId);
		 Long deleteAct = serviceResult.getResult();
		 logger.info("ResultCode Delete :"+deleteAct);
		 
		 if(serviceResult.isSuccess() && deleteAct > 0){
				setSUCCESS("True");
				MESSAGE = messageSource.getMessage("lbl.delete.success", null, local);	
				MESSAGE_CODE = JLOWebConstant.INSERT_SUCCESS_CODE;
				resultActAttachModel.setMode(JLOWebConstant.MODE_UPDATE);
				result.setResultCode(MESSAGE_CODE);
				result.setResultMessage(MESSAGE);
				result.setModel(resultActAttachModel);
				 
			 }else{
				 // Can't insert TP_REL_ATT
				 setSUCCESS("False");
				 MESSAGE = messageSource.getMessage("lbl.delete.fail", null, local);	
				 MESSAGE_CODE = JLOWebConstant.INSERT_FAIL_CODE;
				 resultActAttachModel.setMode(JLOWebConstant.MODE_UPDATE);
				 result.setModel(resultActAttachModel);
				 result.setResultCode(MESSAGE_CODE);
				 result.setResultMessage(MESSAGE);
			 }
		
		 return JsonUtil.toJSON(result, Boolean.TRUE);
	}
	
	@RequestMapping(value = "/updateActAttFlg",method = RequestMethod.POST, headers = { "Accept=application/json; charset=UTF-8" })
	public @ResponseBody String updateActAttFlg(HttpServletRequest request, HttpServletResponse response,Locale local,
	 	   @RequestParam(value = "relAttId", required = true) String relAttId,
	 	   @RequestParam(value = "chkSenDocFlg", required = true) Boolean chkSenDocFlg ) throws Exception {
		
		logger.info("*************updateActAttFlg*************");
//	 	UserLoginInfoModelBean userInfo = getUserLoginInfo(request);
	 	UserInfoDTO userInfo = getUserInfo(request);
	 	
	 	String chkSenDocFlgParame = "N";
	 	if(chkSenDocFlg != null){
	 		if(chkSenDocFlg){
	 			chkSenDocFlgParame = "Y";
	 		}else{
	 			chkSenDocFlgParame = "N";
	 		}
	 	}else{
	 		chkSenDocFlgParame = "N";
	 	}
	 	 
	 	logger.info("relAttId : "+relAttId);
	 	logger.info("chkSenDocFlgParame : "+chkSenDocFlgParame);
	 	
	 	ActivitytAttachmentModelBean actAttModelBean = new ActivitytAttachmentModelBean();
	 	JsonResultBean result = new JsonResultBean();
	 	
	 	ServiceResult<Long> serviceResult = activityManagementService.updateActAttFlg(relAttId,chkSenDocFlgParame,userInfo.getUserId(),new Date());
	 	Long updateACT = serviceResult.getResult();
	 	logger.info("ResultCode Delete :"+updateACT);
	 	 
	 	 if(serviceResult.isSuccess() && updateACT > 0){
	 		 
	 			setSUCCESS("True");
	 			MESSAGE = messageSource.getMessage("lbl.save.success", null, local);	
	 			MESSAGE_CODE = JLOWebConstant.INSERT_SUCCESS_CODE;
	 			actAttModelBean.setMode(JLOWebConstant.MODE_UPDATE);
	 			result.setResultCode(MESSAGE_CODE);
	 			result.setResultMessage(MESSAGE);
	 			result.setModel(actAttModelBean);
	 			 
	 		 }else{
	 			 setSUCCESS("False");
	 			 MESSAGE = messageSource.getMessage("lbl.save.fail", null, local);	
	 			 MESSAGE_CODE = JLOWebConstant.INSERT_FAIL_CODE;
	 			 actAttModelBean.setMode(JLOWebConstant.MODE_UPDATE);
	 			 result.setModel(actAttModelBean);
	 			 result.setResultCode(MESSAGE_CODE);
	 			 result.setResultMessage(MESSAGE);
	 		 }
	 	 return JsonUtil.toJSON(result, Boolean.TRUE);
	}
	
	@RequestMapping(value = "/getActivityDetailFromOtherModule", method = RequestMethod.POST, headers = { "Accept=application/json" })
	public @ResponseBody String getActivityDetailFromOtherModule(HttpServletRequest request, HttpServletResponse response, Locale local) throws Exception {
	
		logger.info("*************getActivityDetailFromOtherModule*************");
		String actNumber = request.getParameter("activityNumber");
		logger.info("actNumber == "+actNumber);
		String langCd = getLanguageCode(local);
		
		String sEchoTxt = request.getParameter("sEcho");
		Integer sEcho = (!StringUtils.isNullOrEmpty(sEchoTxt)) ? Integer.valueOf(sEchoTxt) : 0;
		
		Pageable pageable = getPagableFromRequest(request);
		DatatableModelBean datatableModelBean = new DatatableModelBean();
		List<ActivityModelBean> activityModelBeanList = new ArrayList<ActivityModelBean>();
		
		ServiceResult<Page<ActivityDTO>> serviceResult = activityManagementService.findById(pageable, actNumber, langCd);
		
		if (serviceResult.isSuccess()) {
			
			Page<ActivityDTO> actResult = serviceResult.getResult();
			Integer totalRecords = (int) (long)actResult.getTotalElements();
			
			ActivityModelBean activityModelBean = new ActivityModelBean();
			
			for (ActivityDTO activityDTO : actResult) {
				
				activityModelBean = new ActivityModelBean();
				
				activityModelBean.setOpenedDt(DateTimeUtils.formatDateTime(new Date()));
				
				activityModelBean.setActivityStatusName(activityDTO.getActivityStatusName());
				activityModelBean.setActivityTypeName(activityDTO.getActivityTypeName());
				
				activityModelBean.setActNumber(activityDTO.getActNumber());
				activityModelBean.setAttendTo(activityDTO.getAttendTo());
				activityModelBean.setTitle(activityDTO.getTitle());
				activityModelBean.setTypeCd(activityDTO.getTypeCd());
				activityModelBean.setDescription(activityDTO.getDescription());
				activityModelBean.setPhoneNo(activityDTO.getPhoneNo());
				activityModelBean.setSmsNo(activityDTO.getSmsNo());
				activityModelBean.setFaxNo(activityDTO.getFaxNo());
				activityModelBean.setEmail(activityDTO.getEmail());
				activityModelBean.setAddress(activityDTO.getAddress());
				activityModelBean.setStatusCd(activityDTO.getStatusCd());
				activityModelBean.setAssignToId(activityDTO.getAssignToId());
				activityModelBean.setAssignToName(activityDTO.getOwnerName());
				activityModelBean.setOwnerDeptCode(activityDTO.getOwnerDeptCode());

				activityModelBean.setOwnerName(activityDTO.getOwnerName());
				activityModelBean.setDeptName(activityDTO.getDeptName());

				activityModelBean.setDueDt(DateTimeUtils.formatDate(activityDTO.getDueDt()));
	   			activityModelBean.setDueTime(DateTimeUtils.parseFormatDateTime(activityDTO.getDueDt()));
	   			
	   			activityModelBean.setOperDt(DateTimeUtils.formatDateTime(activityDTO.getOperDt()));
	   			activityModelBean.setOperTime(DateTimeUtils.parseFormatDateTime(activityDTO.getOperDt()));

	   			activityModelBean.setClosedDt(DateTimeUtils.formatDateTime(activityDTO.getClosedDt()));
	   			activityModelBean.setClosedTime(DateTimeUtils.parseFormatDateTime(activityDTO.getClosedDt()));
	   			
	   			activityModelBean.setMode(JLOWebConstant.MODE_UPDATE);
	   			
	   			// For Panel activity Bottom
	   			activityModelBean.setRegName(!StringUtils.isNullOrEmpty(activityDTO.getRegName()) ? activityDTO.getRegName() : "");
	   			activityModelBean.setRegDatetime(!StringUtils.isNullOrEmpty(activityDTO.getRegDatetime()) ? activityDTO.getRegDatetime() : "");
	   			
	   			activityModelBean.setChgName(!StringUtils.isNullOrEmpty(activityDTO.getChgName()) ? activityDTO.getChgName() : "");
	   			activityModelBean.setChgDatetime(!StringUtils.isNullOrEmpty(activityDTO.getChgDatetime()) ? activityDTO.getChgDatetime() : "");
	   			
				activityModelBeanList.add(activityModelBean);
			}
			
			// Set server prop return result
			datatableModelBean.setsEcho(sEcho);
			datatableModelBean.setiTotalDisplayRecords(totalRecords);
			datatableModelBean.setiTotalRecords(totalRecords);
			datatableModelBean.setAaData(activityModelBeanList);
		
		} else {
			showWebMessage(request, serviceResult.getResponseCode(), serviceResult.getResponseDescription());
		}
		return JsonUtil.toJSON( datatableModelBean, Boolean.TRUE );
	}
	
	@RequestMapping(value = "/insertActAttRetrieveFileKb",method = RequestMethod.POST, headers = { "Accept=application/json; charset=UTF-8" })
	public @ResponseBody String insertActAttRetrieveFileKb(HttpServletRequest request, HttpServletResponse response,Locale local,
			 @RequestParam(value = "attId", required = true) String keyAttId
			,@RequestParam(value = "referenceDocNo", required = true) String referenceDocNo
		    ,@RequestParam(value = "fileName", required = true) String fileName
		    ,@RequestParam(value = "docName", required = false) String docName
		    ,@RequestParam(value = "descp", required = false) String descp
	    
			 ) throws Exception {
		
			logger.info("+++++ insertActAttRetrieveFileKb ++++++"); 
		
//			UserLoginInfoModelBean userInfo = getUserLoginInfo(request);
			UserInfoDTO userInfo = getUserInfo(request);
			ActivityAttFileDTO tpRelAttDTO = new ActivityAttFileDTO();
			JsonResultBean result = new JsonResultBean();
			
			 tpRelAttDTO.setRefDocNo(referenceDocNo);
			 tpRelAttDTO.setAttId(Integer.parseInt(String.valueOf(keyAttId)));
			 tpRelAttDTO.setRefDocType(ACT_ATT_REL_TYPE);
			 tpRelAttDTO.setAttName(docName);
			 tpRelAttDTO.setFileName(fileName);
			 tpRelAttDTO.setDescp(descp);
			 tpRelAttDTO.setSendDocFlg("N");
			 tpRelAttDTO.setRegId(userInfo.getUserId());
			 tpRelAttDTO.setRegTime(new Date());
			 
			 ServiceResult<Long> serviceResultTPRelAtt = activityManagementService.insertTpRelAttAttachment(tpRelAttDTO);
			 Long insertTpRelAttId =  serviceResultTPRelAtt.getResult();
			 
			 logger.info("InsertTpRelAttId : "+insertTpRelAttId);
			 
			 if(serviceResultTPRelAtt.isSuccess()){
				 
				SUCCESS = "True";
				MESSAGE = messageSource.getMessage("lbl.action.save.success", null, local);	
				MESSAGE_CODE = JLOWebConstant.INSERT_SUCCESS_CODE;
				result.setResultCode(MESSAGE_CODE);
				result.setResultMessage(MESSAGE);
				
			 }else{
				 
				 SUCCESS = "False";
				 MESSAGE = messageSource.getMessage("lbl.action.save.fail", null, local);	
			     MESSAGE_CODE = JLOWebConstant.INSERT_FAIL_CODE;
				 result.setResultCode(MESSAGE_CODE);
				 result.setResultMessage(MESSAGE+" Can not insert table TP_REL_ATT");
			 }

		return JsonUtil.toJSON(result, Boolean.TRUE);
	}
	
	protected Boolean checkVisibility(HttpServletRequest request, String action, String ownerId) {
		List<String> ownerGroup = getOwnerGroup(request, action);
		
		if (ownerGroup != null) {
			if (ownerGroup.size() > 0) {
				return ownerGroup.contains(String.valueOf(ownerId));
			} else {
				return true;
			}
			
		} else {
			return false;
		}
		
	}
	
	public String getSUCCESS() {
		return SUCCESS;
	}

	public void setSUCCESS(String sUCCESS) {
		SUCCESS = sUCCESS;
	}
}
