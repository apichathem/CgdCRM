package com.locus.jlo.web.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.locus.common.domain.ServiceResult;
import com.locus.common.utils.CollectionUtil;
import com.locus.common.utils.JsonUtil;
import com.locus.jlo.web.bean.dto.ContentDetailDTO;
import com.locus.jlo.web.bean.dto.MyTaskActivityDTO;
import com.locus.jlo.web.bean.dto.MyTaskServiceRequestDTO;
import com.locus.jlo.web.bean.modeljson.DatatableModelBean;
import com.locus.jlo.web.constant.ActionType;
import com.locus.jlo.web.service.TaskSummaryService;

@Controller
public class MyTaskController extends BaseController {
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private TaskSummaryService taskSummaryService;
	
	private Logger logger = Logger.getLogger(getClass());
	
	@RequestMapping(value = "/myTask")
	public ModelAndView myTask(Model model, HttpServletRequest request, Locale local){
		model.addAttribute("pageTitle", messageSource.getMessage("myTask.title", null, local));
		setMenuId(request);
		
		return new ModelAndView("myTask");
	}
	
	@RequestMapping(value = "/getActivityTask", headers = { "Accept=application/json" })
	public @ResponseBody String getActivityTask(HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception {
		logger.info("MyTaskController - getActivityTask");
		logger.info("*****************************************************");

		Pageable pageable = getPagableFromRequest(request);
		DatatableModelBean datatableModelBean = new DatatableModelBean();

		// Get owner filter
		List<String> ownerFilter = getOwnerGroup(request, ActionType.READ.getActionCode());
		
		if (ownerFilter != null) {
			
			ServiceResult<Page<MyTaskActivityDTO>> serviceResult = taskSummaryService.searchActivityList(pageable, getUserId(request), getLanguageCode(locale), (CollectionUtil.isNotEmpty(ownerFilter)) ? ownerFilter : null);
			
			if (serviceResult.isSuccess()) {
				Page<MyTaskActivityDTO> myTaskActivityResult = serviceResult.getResult();
				Integer totalRecords = (int) (long) myTaskActivityResult.getTotalElements();

				// Visibility
				List<MyTaskActivityDTO> myTaskActivityDTOList = myTaskActivityResult.getContent();
				if (CollectionUtil.isNotEmpty(myTaskActivityDTOList)) {
					for (MyTaskActivityDTO myTaskActivityDTO : myTaskActivityDTOList) {
						
						if (checkVisibility(request, ActionType.EDIT.getActionCode(), myTaskActivityDTO.getOwnerId())) {
							StringBuffer url = new StringBuffer();
							url.append("<a href='#' onclick='postAction(\"/activity.htm?module=mytask&mode=update&act_number=" + myTaskActivityDTO.getActNumber() + "\")'>");
							url.append("<i class='fa fa-pencil'></i>");
							url.append("</a>");
							myTaskActivityDTO.setEditUrl(url.toString());
						} else {
							myTaskActivityDTO.setEditUrl("");
						}
					}
				}
				
				// Set server prop return result
				datatableModelBean.setsEcho(getSecho(request));
				datatableModelBean.setiTotalDisplayRecords(totalRecords);
				datatableModelBean.setiTotalRecords(totalRecords);
				datatableModelBean.setAaData(myTaskActivityResult.getContent());
			} else {
				showWebMessage(request, serviceResult.getResponseCode(), serviceResult.getResponseDescription());
			}
		}
		
		return JsonUtil.toJSON(datatableModelBean, Boolean.TRUE);
	}
	
	@RequestMapping(value = "/getServiceRequestTask", headers = { "Accept=application/json" })
	public @ResponseBody String getServiceRequestTask(HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception {
		logger.info("MyTaskController - getServiceRequestTask");
		logger.info("*****************************************************");

		Pageable pageable = getPagableFromRequest(request);
		DatatableModelBean datatableModelBean = new DatatableModelBean();
		
		// Get owner filter
		List<String> ownerFilter = getOwnerGroup(request, ActionType.READ.getActionCode());
		
		if (ownerFilter != null) {
			ServiceResult<Page<MyTaskServiceRequestDTO>> serviceResult = taskSummaryService.searchServiceRequestList(pageable, getUserId(request), getLanguageCode(locale), (CollectionUtil.isNotEmpty(ownerFilter)) ? ownerFilter : null);

			if (serviceResult.isSuccess()) {
				Page<MyTaskServiceRequestDTO> myTaskServiceRequestResult = serviceResult.getResult();
				Integer totalRecords = (int) (long) myTaskServiceRequestResult.getTotalElements();

				// Visibility
				List<MyTaskServiceRequestDTO> myTaskServiceRequestDTOList = myTaskServiceRequestResult.getContent();
				if (CollectionUtil.isNotEmpty(myTaskServiceRequestDTOList)) {
					for (MyTaskServiceRequestDTO myTaskServiceRequestDTO : myTaskServiceRequestDTOList) {
						//var url = 'serviceRequestDetail.htm?module=mytask&mode=update&sr_number=' + srNumber;
						if(myTaskServiceRequestDTO.getSrSubject().length() > 40 ) {
							myTaskServiceRequestDTO.setSrSubject(myTaskServiceRequestDTO.getSrSubject().substring(0, 40));
						}
						
						
						if (checkVisibility(request, ActionType.EDIT.getActionCode(), myTaskServiceRequestDTO.getSrOwnerId())) {
							StringBuffer url = new StringBuffer();
							url.append("<a href='#' onclick='postAction(\"/serviceRequestDetail.htm?module=mytask&mode=update&sr_number=" + myTaskServiceRequestDTO.getSrNumber() + "\")'>");
							url.append("<i class='fa fa-pencil'></i>");
							url.append("</a>");
							myTaskServiceRequestDTO.setEditUrl(url.toString());
						} else {
							myTaskServiceRequestDTO.setEditUrl("");
						}
					}
				}
				
				// Set server prop return result
				datatableModelBean.setsEcho(getSecho(request));
				datatableModelBean.setiTotalDisplayRecords(totalRecords);
				datatableModelBean.setiTotalRecords(totalRecords);
				datatableModelBean.setAaData(myTaskServiceRequestResult.getContent());
			} else {
				showWebMessage(request, serviceResult.getResponseCode(), serviceResult.getResponseDescription());
			}
		}

		return JsonUtil.toJSON(datatableModelBean, Boolean.TRUE);
	}
	

	@RequestMapping(value = "/getKbTask", headers = { "Accept=application/json" })
	public @ResponseBody String getKbTask(HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception {
		logger.info("MyTaskController - getKbTask");
		logger.info("*****************************************************");
		
		Pageable pageable = getPagableFromRequest(request);
		DatatableModelBean datatableModelBean = new DatatableModelBean();
		
		// Get owner filter
		List<String> ownerFilter = getOwnerGroup(request, ActionType.READ.getActionCode());
		logger.info("OwnerGroup >> " + ownerFilter);
		for (String owner : ownerFilter) {
			logger.info("owner >> " + owner);
		}
		
		if (ownerFilter != null) {
			ServiceResult<Page<ContentDetailDTO>> serviceResult = taskSummaryService.searchKbList(pageable, getLanguageCode(locale), (CollectionUtil.isNotEmpty(ownerFilter)) ? ownerFilter : null);

			if (serviceResult.isSuccess()) {
				Page<ContentDetailDTO> result = serviceResult.getResult();
				Integer totalRecords = (int) (long) result.getTotalElements();

				// Visibility
				List<ContentDetailDTO> contentDTOList = result.getContent();
				if (CollectionUtil.isNotEmpty(contentDTOList)) {
					for (ContentDetailDTO contentDetailDTO : contentDTOList) {
						if(contentDetailDTO.getTitle().length() > 40 ) {
							contentDetailDTO.setTitle(contentDetailDTO.getTitle().substring(0, 40));
						}
						
						if (checkVisibility(request, ActionType.EDIT.getActionCode(), contentDetailDTO.getRegId())) {
							StringBuffer url = new StringBuffer();
							url.append("<a href='#' onclick='postAction(\"/knowledgeBaseDetail.htm?contentNumber=" + contentDetailDTO.getContentNumber() + "\")'>");
							url.append("<i class='fa fa-pencil'></i>");
							url.append("</a>");
							contentDetailDTO.setEditUrl(url.toString());
						} else {
							contentDetailDTO.setEditUrl("");
						}
					}
				}
				
				// Set server prop return result
				datatableModelBean.setsEcho(getSecho(request));
				datatableModelBean.setiTotalDisplayRecords(totalRecords);
				datatableModelBean.setiTotalRecords(totalRecords);
				datatableModelBean.setAaData(result.getContent());
			} else {
				showWebMessage(request, serviceResult.getResponseCode(), serviceResult.getResponseDescription());
			}
		}
			
		return JsonUtil.toJSON(datatableModelBean, Boolean.TRUE);
	}
	
	@RequestMapping(value = "/countServiceRequestTask", headers = { "Accept=application/json" })
	public @ResponseBody String countServiceRequestTask(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		logger.info("MyTaskController - countServiceRequestTask");
//		logger.info("*****************************************************");
		
		// Get owner filter
		List<String> ownerFilter = null;//getOwnerGroup(request, ActionType.READ.getActionCode());
		
		//if (ownerFilter != null) {
			int count = taskSummaryService.countPendingServiceRequest(getUserId(request), (CollectionUtil.isNotEmpty(ownerFilter)) ? ownerFilter : null);
			return JsonUtil.toJSON(count, Boolean.TRUE);
		//} else {
		//	return JsonUtil.toJSON(0, Boolean.TRUE);
		//}
		
		
	}
	
	@RequestMapping(value = "/countActivityTask", headers = { "Accept=application/json" })
	public @ResponseBody String countActivityTask(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		logger.info("MyTaskController - countActivityTask");
//		logger.info("*****************************************************");
		
		// Get owner filter
		List<String> ownerFilter = null;//getOwnerGroup(request, ActionType.READ.getActionCode());
		
		//if (ownerFilter != null) {
			int count = taskSummaryService.countPendingActivity(getUserId(request), (CollectionUtil.isNotEmpty(ownerFilter)) ? ownerFilter : null);
			return JsonUtil.toJSON(count, Boolean.TRUE);
		//} else {
		//	return JsonUtil.toJSON(0, Boolean.TRUE);
		//}
		
	}
	
	/*@RequestMapping(value = "/countKbTask", headers = { "Accept=application/json" })
	public @ResponseBody String countKbTask(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// Get owner filter
		List<String> ownerFilter = getOwnerGroup(request, ActionType.READ.getActionCode());
		
		//if (ownerFilter != null) {
			int count = taskSummaryService.countRejectedKb(getUserId(request), (CollectionUtil.isNotEmpty(ownerFilter)) ? ownerFilter : null);
			return JsonUtil.toJSON(count, Boolean.TRUE);
		//} else {
		//	return JsonUtil.toJSON(0, Boolean.TRUE);
		//}
		
	}*/
}
