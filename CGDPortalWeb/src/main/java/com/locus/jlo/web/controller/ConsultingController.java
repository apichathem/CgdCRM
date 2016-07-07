package com.locus.jlo.web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.locus.common.domain.ServiceResult;
import com.locus.common.utils.DateTimeUtils;
import com.locus.common.utils.JsonUtil;
import com.locus.common.utils.StringUtils;
import com.locus.jlo.web.service.ConsultingService;
import com.locus.jlo.web.service.SequenceGeneratorService;
import com.locus.jlo.web.service.ServiceRequestService;
import com.locus.jlo.web.constant.ActionType;
import com.locus.jlo.web.constant.JLOWebConstant;
import com.locus.jlo.web.constant.SequenceType;
import com.locus.jlo.web.bean.criteria.ContactCriteria;
import com.locus.jlo.web.bean.criteria.ServiceRequestCriteria;
import com.locus.jlo.web.bean.dto.CallListDTO;
import com.locus.jlo.web.bean.dto.ConsultingDTO;
import com.locus.jlo.web.bean.dto.ContactDTO;
import com.locus.jlo.web.bean.dto.ServiceRequestDTO;
import com.locus.jlo.web.bean.dto.UserInfoDTO;
import com.locus.jlo.web.constant.SessionUserConstant;
import com.locus.jlo.web.bean.modelbean.CodebookModelBean;
import com.locus.jlo.web.bean.modelbean.ServiceRequestModelBean;
import com.locus.jlo.web.bean.modeljson.DatatableModelBean;
import com.locus.jlo.web.util.CodeBookHelper;
import com.locus.jlo.web.bean.modelbean.ConsultingModelBean;

@Controller
public class ConsultingController extends BaseController {

	private Logger logger = Logger.getLogger(getClass());
	private String INPROGRESS = "01";
	private String FINISHED = "02";

	@Autowired
	private ServiceRequestService serviceRequestService;

	@Autowired
	private ConsultingService consultingService;

	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;

	@RequestMapping(value = "/consulting")
	public ModelAndView consulting(Model model, HttpServletRequest request, Locale local) {
		logger.info("ConsultingController : consulting");
		model.addAttribute("pageTitle", messageSource.getMessage("consulting.page.title", null, local));

		setMenuId(request);

		UserInfoDTO userInfo = getUserInfo(request);

		ConsultingModelBean consultingModelBean = new ConsultingModelBean();
		consultingModelBean.setOwnerName(userInfo.getFirstName() + " " + userInfo.getLastName());
		consultingModelBean.setOwnerId(userInfo.getUserId().toString());

		return new ModelAndView("consulting", "consultingModelBean", consultingModelBean);
	}

	@RequestMapping(value = "/insertConsulting", method = RequestMethod.POST, headers = { "Accept=application/json; charset=UTF-8" })
	public @ResponseBody
	String insertConsulting(HttpServletRequest request, @RequestParam(value = "custId", required = false) String custId) {
		Integer userId = getUserId(request);
		// UserLoginInfoModelBean userLoginInfoModelBean
		// =getUserLoginInfo(request);
		UserInfoDTO userLoginInfoModelBean = getUserInfo(request);
		ConsultingDTO inital = new ConsultingDTO();

		String consoltingNumber = sequenceGeneratorService.generateByType(SequenceType.CONSULTING);
		inital.setConsultingNumber(consoltingNumber);
		inital.setStatusCd("01");
		inital.setChannelCd("03");
		inital.setStartDt(new java.util.Date());
		inital.setOwnerId(String.valueOf(userId));
		inital.setRegId(String.valueOf(userId));
		inital.setRegDt(new java.util.Date());

		if (!StringUtils.isEmpty(custId)) {
			inital.setCustId(custId);
		}

		CallListDTO callListDTO = (CallListDTO) getSessionAttr(request, SessionUserConstant.SESSION_USER_CALL_LIST_OBJ);
		logger.info("callListDTO -----> " + callListDTO);
		if (callListDTO != null) {
			inital.setCallObjectId(String.valueOf(callListDTO.getCallListId()));
			inital.setCallingNumber(callListDTO.getCallingNumber());
		}

		ServiceResult<ConsultingDTO> serviceResult = consultingService.insertInital(inital);
		if (serviceResult.isSuccess()) {

			ConsultingDTO consultingDTO = serviceResult.getResult();
			consultingDTO.setRegName(userLoginInfoModelBean.getFirstName() + " " + userLoginInfoModelBean.getLastName());
			consultingDTO.setOwnerName(userLoginInfoModelBean.getFirstName() + " " + userLoginInfoModelBean.getLastName());

			if (inital.getChannelCd() != null) {
				CodebookModelBean codebookLangch = CodeBookHelper.getByCodeTypeAndCodeId(request, "CONSULT_CHANNEL", inital.getChannelCd());
				consultingDTO.setChannelName(codebookLangch.getCodeName());
			}
			CodebookModelBean codebookLangst = CodeBookHelper.getByCodeTypeAndCodeId(request, "CONSULT_STATUS", inital.getStatusCd());
			consultingDTO.setStatusName(codebookLangst.getCodeName());

			if (!StringUtils.isEmpty(consultingDTO.getCustId())) {

				ServiceResult<ConsultingDTO> serviceResultCust = consultingService.searchConsultingCustomerById(consultingDTO.getCustId());

				if (serviceResultCust.isSuccess()) {
					ConsultingDTO ctDto = new ConsultingDTO();
					ctDto = serviceResultCust.getResult();
					consultingDTO.setCustId(ctDto.getCustId());
					consultingDTO.setCustName(!StringUtils.isEmpty(ctDto.getCustName()) ? ctDto.getCustName() : "");
					consultingDTO.setCustType(ctDto.getCustType());
				}

			}

			logger.info("CustId   : " + consultingDTO.getCustId());
			logger.info("CustName : " + consultingDTO.getCustName());
			logger.info("CustType : " + consultingDTO.getCustType());

			setSessionAttr(request, SessionUserConstant.SESSION_USER_CONSULTING_OBJ, consultingDTO);
			setSessionAttr(request, "CONSULTING_ID", consultingDTO.getConsultingNumber());
			return JsonUtil.toJSON(consultingDTO, Boolean.TRUE);
		} else {
			return JsonUtil.toJSON(false, Boolean.TRUE);
		}
	}

	@RequestMapping(value = "/updateConsulting", method = RequestMethod.POST, headers = { "Accept=application/json; charset=UTF-8" })
	public @ResponseBody
	String updateConsulting(HttpServletRequest request, @ModelAttribute("consultingModelBean") ConsultingModelBean bean) {
		logger.info("ConsultingController : updateConsulting");
		logger.info("********************");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm", java.util.Locale.US);
		ConsultingDTO dto = new ConsultingDTO();
		dto.setConsultingNumber(bean.getConsultingNumber());
		dto.setChannelCd(bean.getChannelCd());

		CodebookModelBean codebookLangch = CodeBookHelper.getByCodeTypeAndCodeId(request, "CONSULT_CHANNEL", bean.getChannelCd());
		if (codebookLangch != null) {
			dto.setChannelName(codebookLangch.getCodeName());
		}
		dto.setStatusCd(bean.getStatusCd());
		CodebookModelBean codebookLang = CodeBookHelper.getByCodeTypeAndCodeId(request, "CONSULT_STATUS", bean.getStatusCd());
		dto.setStatusName(codebookLang.getCodeName());
		/*
		try {			
			dto.setStartDt(sdf.parse(bean.getStartDt() + " " + bean.getStartDtTime()));
			dto.setEndDt(sdf.parse(bean.getEndDt() + " " + bean.getEndDtTime()));			
		} catch (ParseException e) {
		
		}*/
		
		
		dto.setTitle(bean.getTitle());
		dto.setCallingNumber(bean.getCallingNumber());
		dto.setOwnerId(bean.getOwnerId());
		dto.setOwnerName(bean.getOwnerName());
		dto.setNote(bean.getNote());
		dto.setCustId(bean.getCustId());
		dto.setCustName(bean.getCustName());
		dto.setCustType(bean.getCustType());
		dto.setConsultingTypeCd(bean.getConsultingTypeCd());
		dto.setContactId(bean.getContactId());
		dto.setContactName(bean.getContactName());
		dto.setChgId(String.valueOf(getUserId(request)));
		dto.setChgDt(new java.util.Date());

		if (dto.getStatusCd().equalsIgnoreCase("02") && StringUtils.isEmpty(bean.getEndDt())) {
			dto.setEndDt(new Date());
		}

		// java.util.List<String> ownerGroup = getOwnerGroup(request,
		// ActionType.EDIT.getActionCode());
		// logger.debug("ownerGroup:"+ownerGroup);
		// if(ownerGroup!=null){
		ServiceResult<Long> serviceResult = consultingService.update(dto);
		if (serviceResult.isSuccess()) {
			logger.debug("serviceResult.getResult():" + serviceResult.getResult());
			if (serviceResult.getResult() > 0) {
				String consultingSessionId = (String) getSessionAttr(request, "CONSULTING_ID");
				if (dto.getConsultingNumber().equalsIgnoreCase(consultingSessionId)) {
					if (dto.getStatusCd().equalsIgnoreCase("02")) {
						removeSessionAttr(request, SessionUserConstant.SESSION_USER_CONSULTING_OBJ);
						removeSessionAttr(request, SessionUserConstant.SESSION_USER_CALL_LIST_OBJ);
					} else {
						setSessionAttr(request, SessionUserConstant.SESSION_USER_CONSULTING_OBJ, dto);
					}
				}
				return JsonUtil.toJSON(dto, Boolean.TRUE);
			}
		}
		// }
		return JsonUtil.toJSON(false, Boolean.TRUE);

	}

	@RequestMapping(value = "/inquiryConsultingList", method = RequestMethod.POST, headers = { "Accept=application/json; charset=UTF-8" })
	public @ResponseBody
	String inquiryConsultingList(HttpServletRequest request, @ModelAttribute("consultingModelBean") ConsultingModelBean bean, Locale locale) {
		logger.info("ConsultingController : inquiryConsultingList");
		logger.info("********************");
		DatatableModelBean datatableModelBean = new DatatableModelBean();
		datatableModelBean.setsEcho(1);
		datatableModelBean.setiTotalDisplayRecords(0);
		datatableModelBean.setiTotalRecords(0);
		datatableModelBean.setAaData(new java.util.ArrayList<ConsultingDTO>());

		Pageable pageable = getPagableFromRequest(request);
		try {
			ConsultingDTO obj = new ConsultingDTO();
			obj.setChannelCd(setNull(bean.getChannelCd()));
			obj.setOwnerId(setNull(bean.getOwnerId()));
			obj.setCustId(setNull(bean.getCustId()));
			obj.setConsultingNumber(setNull(bean.getConsultingNumber()));
			obj.setConsultingTypeCd(setNull(bean.getConsultingTypeCd()));
			obj.setContactId(setNull(bean.getContactId()));
			obj.setCallingNumber(setNull(bean.getCallingNumber()));
			obj.setStatusCd(setNull(bean.getStatusCd()));
			obj.setTitle(setNull(bean.getTitle()));
			obj.setNote(setNull(bean.getNote()));
			obj.setLangCd(getLanguageCode(locale));
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
			if (bean.getStartDt() != null && bean.getStartDt().trim().length() > 0) {
				obj.setStartDt(sdf.parse(bean.getStartDt()));
			}
			if (bean.getEndDt() != null && bean.getEndDt().trim().length() > 0) {
				obj.setEndDt(sdf.parse(bean.getEndDt()));
			}
			java.util.List<String> ownerGroup = getOwnerGroup(request, ActionType.READ.getActionCode());
			logger.debug("ownerGroup:" + ownerGroup);
			if (ownerGroup != null) {
				if (ownerGroup.size() > 0) {
					obj.setOwnerGroup(ownerGroup);
				}
				ServiceResult<Page<ConsultingDTO>> serviceResult = consultingService.find(obj, pageable);
				if (serviceResult.isSuccess()) {
					Page<ConsultingDTO> page = serviceResult.getResult();
					datatableModelBean.setsEcho(new Integer(request.getParameter("sEcho")));
					datatableModelBean.setiTotalDisplayRecords((int) page.getTotalElements());
					datatableModelBean.setiTotalRecords((int) page.getTotalElements());
					datatableModelBean.setAaData(page.getContent());

				}
			}
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
			datatableModelBean.setsEcho(1);
			datatableModelBean.setiTotalDisplayRecords(0);
			datatableModelBean.setiTotalRecords(0);
			datatableModelBean.setAaData(new java.util.ArrayList<ConsultingDTO>());
		}
		return JsonUtil.toJSON(datatableModelBean, Boolean.TRUE);
	}

	private String setNull(String text) {
		if (text == null || text.isEmpty()) {
			return null;
		} else {
			return text;
		}
	}

	@RequestMapping(value = "/openDialogConsulting")
	public ModelAndView openDialogConsulting(Model model, Locale local, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "pageModalName", required = true) String pageModalName, @RequestParam(value = "modalHeaderCode", required = true) String modalHeaderCaption,
			@RequestParam(value = "callbackfn", required = false) String callbackfn, @RequestParam(value = "strParam", required = false) String strParam,
			@RequestParam(value = "dialogName", required = false) String dialogName) throws Exception {

		model.addAttribute("headerTitle", messageSource.getMessage(modalHeaderCaption, null, local));
		model.addAttribute("callbackfn", callbackfn);
		model.addAttribute("dialogName", dialogName);

		logger.info("+++ openDialogConsulting +++ ");
		logger.info("PageModalName : " + pageModalName);
		logger.info("ModalHeaderCaption : " + modalHeaderCaption);
		logger.info("CallBackfn : " + callbackfn);
		logger.info("strParam : " + strParam);
		logger.info("dialogName :" + dialogName);

		if (!StringUtils.isEmpty(strParam)) {
			if (strParam.equals("CONSULTING_WRAPUP")) {
				model.addAttribute("conWrapUpStatusCd", FINISHED);
			} else {
				model.addAttribute("conWrapUpStatusCd", INPROGRESS);
			}
		}

		String pageDialogReturn = "";
		if (!StringUtils.isEmpty(pageModalName)) {
			pageDialogReturn = pageModalName;
		}

		return new ModelAndView(pageDialogReturn, SessionUserConstant.SESSION_USER_CONSULTING_OBJ, request.getSession().getAttribute(SessionUserConstant.SESSION_USER_CONSULTING_OBJ));
	}

	@RequestMapping(value = "/getContactConsultingList", method = RequestMethod.POST, headers = { "Accept=application/json; charset=UTF-8" })
	public @ResponseBody
	String getContactConsultingList(Model model, Locale locale, HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "custId", required = true) String custId,
			@RequestParam(value = "firstName", required = false) String firstName, @RequestParam(value = "lastName", required = false) String lastName,
			@RequestParam(value = "mobile", required = false) String mobile) throws Exception {

		logger.info("*****************************************************");
		logger.info("+++ CustomerController.getCustList +++");

		Pageable pageable = getPagableFromRequest(request);
		DatatableModelBean datatableModelBean = new DatatableModelBean();

		ContactCriteria contactCriteria = new ContactCriteria();
		contactCriteria.setLangCd(getLanguageCode(locale));

		if (StringUtils.isNotEmpty(firstName)) {
			contactCriteria.setFirstName(firstName);
		}

		if (StringUtils.isNotEmpty(lastName)) {
			contactCriteria.setLastName(lastName);
		}

		if (StringUtils.isNotEmpty(mobile)) {
			contactCriteria.setMobileNo(mobile);
		}

		ServiceResult<Page<ContactDTO>> serviceResult = consultingService.searchContactList(pageable, contactCriteria, custId);
		if (serviceResult.isSuccess()) {
			Page<ContactDTO> result = serviceResult.getResult();
			Integer totalRecords = (int) (long) result.getTotalElements();

			// Set server prop return result
			datatableModelBean.setsEcho(getSecho(request));
			datatableModelBean.setiTotalDisplayRecords(totalRecords);
			datatableModelBean.setiTotalRecords(totalRecords);
			datatableModelBean.setAaData(result.getContent());
		} else {
			showWebMessage(request, serviceResult.getResponseCode(), serviceResult.getResponseDescription(), messageSource.getMessage("customer.title", null, locale));
		}

		return JsonUtil.toJSON(datatableModelBean, Boolean.TRUE);
	}

	@RequestMapping(value = "/inquiryServiceRequestListByConsultingNumber", method = RequestMethod.POST, headers = { "Accept=application/json" })
	public @ResponseBody
	String inquiryServiceRequestList(HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception {
		logger.info("ServiceRequstController - inquiryServiceRequestList");
		logger.info("****************Begin Print Parameter************************");
		String consultingNumber = request.getParameter("consultingNumber");

		List<String> ownerFilter = getOwnerGroup(request, ActionType.READ.getActionCode());

		ServiceRequestCriteria criteria = new ServiceRequestCriteria();
		criteria.setLangCd(getLanguageCode(locale));

		if (StringUtils.isNotEmpty(consultingNumber)) {
			criteria.setConsultingNumber(consultingNumber);
		}

		String msg_title = messageSource.getMessage("menu.serviceRequest.detail", null, locale);
		//String titleEdit = messageSource.getMessage("sr.srAttTooltipEdit", null, locale);

		String sEchoTxt = request.getParameter("sEcho");
		Integer sEcho = (!StringUtils.isNullOrEmpty(sEchoTxt)) ? Integer.valueOf(sEchoTxt) : 0;

		Pageable pageable = getPagableFromRequest(request);
		DatatableModelBean datatableModelBean = new DatatableModelBean();
		List<ServiceRequestModelBean> srListModelBean = new ArrayList<ServiceRequestModelBean>();

		// JOptionPane.showMessageDialog(null,
		// "criteria LocalCD : "+criteria.getLangCd());

		ServiceResult<Page<ServiceRequestDTO>> serviceResult = serviceRequestService.searchServiceRequestByConsultingNumber(pageable, criteria);

		if (serviceResult.isSuccess()) {

			Page<ServiceRequestDTO> result = serviceResult.getResult();
			Integer totalRecords = (int) (long) result.getTotalElements();

			ServiceRequestModelBean requestModelBean = new ServiceRequestModelBean();
			for (ServiceRequestDTO srContent : result) {

				requestModelBean = new ServiceRequestModelBean();

				requestModelBean.setSrNumber(srContent.getSrNumber());
				
				requestModelBean.setSrTypeProblem1(srContent.getSrTypeProblem1());
				requestModelBean.setSrTypeProblem2(srContent.getSrTypeProblem2());
				requestModelBean.setSrTypeProblem3(srContent.getSrTypeProblem3());
				requestModelBean.setSrTypeProblem4(srContent.getSrTypeProblem4());
				requestModelBean.setSrTypeProblem5(srContent.getSrTypeProblem5());
				requestModelBean.setSrSubject(srContent.getSrSubject());
				
				requestModelBean.setSrPriorityDesc(srContent.getSrPriorityDesc());
				requestModelBean.setSrStatusDesc(srContent.getSrStatusDesc());
				requestModelBean.setSrOpenedDate(DateTimeUtils.formatDateTime(srContent.getSrOpenedDate()));
				requestModelBean.setSrDueDate(DateTimeUtils.formatDateTime(srContent.getSrDueDate()));
				requestModelBean.setSrCloseDate(DateTimeUtils.formatDateTime(srContent.getSrCloseDate()));

				requestModelBean.setSrOwnerName(srContent.getSrOwnerName());

				// for display code
				requestModelBean.setSrTypeProblem1Cd(srContent.getSrTypeProblem1Cd());
				requestModelBean.setSrTypeProblem2Cd(srContent.getSrTypeProblem2Cd());
				requestModelBean.setSrTypeProblem3Cd(srContent.getSrTypeProblem3Cd());
				requestModelBean.setSrTypeProblem4Cd(srContent.getSrTypeProblem4Cd());
				requestModelBean.setSrTypeProblem5Cd(srContent.getSrTypeProblem5Cd());
				
				
				requestModelBean.setSrPriorityCd(srContent.getSrpriorityCd());
				requestModelBean.setSrStatusCd(srContent.getSrStatusCd());
				requestModelBean.setSrOwnerId(srContent.getSrOwnerId());
				requestModelBean.setSrCustId(String.valueOf(srContent.getSrCustId()));
				requestModelBean.setSrCustType(srContent.getSrCustType());
				requestModelBean.setSrChannelCd(srContent.getSrChannelCd());
				requestModelBean.setSrChannelName(srContent.getSrChannelName());
				requestModelBean.setSrCustomerName(srContent.getCustomerName());

				requestModelBean.setSrRegId(String.valueOf(srContent.getRegId()));
				requestModelBean.setSrRegDate(DateTimeUtils.formatDateTime(srContent.getRegDt()));

				if (!StringUtils.isEmpty(srContent.getSrOwnerId())) {
					if (checkVisibility(request, ActionType.EDIT.getActionCode(), Integer.valueOf(srContent.getSrOwnerId()))) {
						StringBuffer url = new StringBuffer();
						url.append("<a href='" + srContent.getSrNumber() + "' onclick='javascript:editServiceRequest(this); return false;'>");
						url.append("<center><i class='fa fa-pencil'></i></center>");
						url.append("</a>");
						logger.info("edit url : " + url);
						requestModelBean.setEditUrl(url.toString());
					} else {
						requestModelBean.setEditUrl("");
					}

				} else {
					requestModelBean.setEditUrl("");
				}

				srListModelBean.add(requestModelBean);
			}

			// Set server prop return result
			datatableModelBean.setsEcho(sEcho);
			datatableModelBean.setiTotalDisplayRecords(totalRecords);
			datatableModelBean.setiTotalRecords(totalRecords);
			datatableModelBean.setAaData(srListModelBean);

		} else {
			showWebMessage(request, serviceResult.getResponseCode(), serviceResult.getResponseDescription(), msg_title);
		}

		return JsonUtil.toJSON(datatableModelBean, Boolean.TRUE);

	}
}
