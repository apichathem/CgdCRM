package com.locus.jlo.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.locus.common.domain.ServiceResult;
import com.locus.common.utils.CollectionUtil;
import com.locus.common.utils.DateTimeUtils;
import com.locus.common.utils.JsonUtil;
import com.locus.common.utils.StringUtils;
import com.locus.common.utils.ThaiUtil;
import com.locus.crm.ws.client.thaiquest.DocumentProperty;
import com.locus.crm.ws.client.thaiquest.SearchResult;
import com.locus.jlo.web.bean.criteria.ServiceRequestCriteria;
import com.locus.jlo.web.bean.dto.ConsultingDTO;
import com.locus.jlo.web.bean.dto.ConsultingRelDTO;
import com.locus.jlo.web.bean.dto.ContentAttDTO;
import com.locus.jlo.web.bean.dto.ServiceRequestActDTO;
import com.locus.jlo.web.bean.dto.ServiceRequestAttachmentDTO;
import com.locus.jlo.web.bean.dto.ServiceRequestContentDTO;
import com.locus.jlo.web.bean.dto.ServiceRequestDTO;
import com.locus.jlo.web.bean.dto.ServiceRequestDetailDTO;
import com.locus.jlo.web.bean.dto.ServiceRequestSolutionDTO;
import com.locus.jlo.web.bean.dto.SlaDTO;
import com.locus.jlo.web.bean.dto.UserInfoDTO;
import com.locus.jlo.web.bean.modelbean.ServiceRequestActAttachmentModelBean;
import com.locus.jlo.web.bean.modelbean.ServiceRequestActivityModelBean;
import com.locus.jlo.web.bean.modelbean.ServiceRequestAttachmentModelBean;
import com.locus.jlo.web.bean.modelbean.ServiceRequestContentModelBean;
import com.locus.jlo.web.bean.modelbean.ServiceRequestDetailModelBean;
import com.locus.jlo.web.bean.modelbean.ServiceRequestModelBean;
import com.locus.jlo.web.bean.modelbean.ServiceRequestSolutionAttachmentModelBean;
import com.locus.jlo.web.bean.modelbean.ServiceRequestSolutionModelBean;
import com.locus.jlo.web.bean.modeljson.DatatableModelBean;
import com.locus.jlo.web.bean.modeljson.JsonResultBean;
import com.locus.jlo.web.constant.ActionType;
import com.locus.jlo.web.constant.JLOWebConstant;
import com.locus.jlo.web.constant.SequenceType;
import com.locus.jlo.web.service.ConsultingService;
import com.locus.jlo.web.service.KnowledgeBaseService;
import com.locus.jlo.web.service.SequenceGeneratorService;
import com.locus.jlo.web.service.ServiceRequestService;
import com.locus.jlo.web.service.SlaService;
import com.locus.jlo.web.service.ThaiQuestWsService;

/**
 * @author Mr.BoonOom
 * 
 */

@Controller
public class ServiceRequestController extends BaseController {

	@Autowired
	private ServiceRequestService serviceRequestService;

	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;

	@Autowired
	private ConsultingService consultingService;

	@Autowired
	ServletContext context;

	@Autowired
	private KnowledgeBaseService knowledgeBaseService;

	@Autowired
	private ThaiQuestWsService thaiQuestWsService;

	@Autowired
	private SlaService slaService;

	@Autowired
	private MessageSource messageSource;
	private Logger logger = Logger.getLogger(getClass());

	private String SUCCESS = "True";
	private String MESSAGE = "";
	private String MESSAGE_CODE = "";

	private static final String SR_MODEL = "srModelBean";
	private static final String SR_ACT_MODEL = "srActModelBean";
	private static final String SR_SOLUTIONS_MODEL = "srSolutionModelBean";

	private static final String SR_CLOSED_STATUS = "03";
	private static final String SR_ACT_CLOSED_STATUS = "03";

	private static final String SR_URL_DETAIL = "serviceRequestDetail.htm";
	private String CONSULTING_OBJECT_SESSION = "CONSULTING_OBJECT";

	@RequestMapping(value = "/serviceRequestList")
	public ModelAndView serviceRequestList(Model model, HttpServletRequest request, Locale local) {
		ServiceRequestModelBean srModelBean = new ServiceRequestModelBean();
		model.addAttribute("pageTitle", messageSource.getMessage("serviceRequest.title", null, local));
		logger.info("+++ ServiceRequestList ServiceRequstController +++ ");

		setMenuId(request);

		UserInfoDTO userInfo = getUserInfo(request);

		srModelBean.setSrOwnerName(userInfo.getFirstName() + " " + userInfo.getLastName());
		srModelBean.setSrOwnerId(userInfo.getUserId().toString());

		return new ModelAndView("serviceRequestList", SR_MODEL, srModelBean);

	}

	@RequestMapping(value = "/inquiryServiceRequestList", method = RequestMethod.POST, headers = { "Accept=application/json" })
	public @ResponseBody
	String inquiryServiceRequestList(HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception {
		logger.info("ServiceRequstController - inquiryServiceRequestList");
		logger.info("****************Begin Print Parameter************************");
		String sr_number = request.getParameter("txt_sr_number");
		String owner_id = request.getParameter("srOwnerId");
		String current_to_dept_cd = request.getParameter("current_to_dept_cd");

		String srTypeProblem1 = request.getParameter("cmbSrTypeProblem1");
		String srTypeProblem2 = request.getParameter("cmbSrTypeProblem2");
		String srTypeProblem3 = request.getParameter("cmbSrTypeProblem3");
		String srTypeProblem4 = request.getParameter("cmbSrTypeProblem4");
		String srTypeProblem5 = request.getParameter("cmbSrTypeProblem5");

		String srStatus = request.getParameter("srStatusCd");
		String srPriority = request.getParameter("srPriorityCd");
		String srChannel = request.getParameter("srChannelCd");
		String srCustId = request.getParameter("txt_sr_customer_id");

		String srCreated_date_start = request.getParameter("create_date_start");
		String srCreated_date_end = request.getParameter("create_date_end");
		String srDue_date_start = request.getParameter("due_date_start");
		String srDue_date_end = request.getParameter("due_date_end");

		logger.info("sr_number " + sr_number);
		logger.info("owner_id " + owner_id);
		logger.info("current_to_dept_cd " + current_to_dept_cd);
		logger.info("srStatus " + srStatus);
		logger.info("srChannel " + srChannel);
		logger.info("srCreated_date_start " + srCreated_date_start);
		logger.info("srCreated_date_end " + srCreated_date_end);
		logger.info("srDue_date_start " + srDue_date_start);
		logger.info("srDue_date_end " + srDue_date_end);
		logger.info("****************End Print Parameter************************");
		logger.info("ActionType.READ.getActionCode :" + ActionType.READ.getActionCode());

		List<String> ownerFilter = getOwnerGroup(request, ActionType.READ.getActionCode());

		ServiceRequestCriteria criteria = new ServiceRequestCriteria();
		criteria.setLangCd(getLanguageCode(locale));
		String msg_title = messageSource.getMessage("menu.serviceRequest.detail", null, locale);
		String titleEdit = messageSource.getMessage("sr.srTooltipEdit", null, locale);

		String sEchoTxt = request.getParameter("sEcho");
		Integer sEcho = (!StringUtils.isNullOrEmpty(sEchoTxt)) ? Integer.valueOf(sEchoTxt) : 0;

		Pageable pageable = getPagableFromRequest(request);
		DatatableModelBean datatableModelBean = new DatatableModelBean();
		List<ServiceRequestModelBean> srListModelBean = new ArrayList<ServiceRequestModelBean>();

		if (ownerFilter != null) {
			criteria.setOwnerGroup(ownerFilter);
			if (StringUtils.isNotEmpty(sr_number)) {
				criteria.setSr_number(sr_number);
			}

			if (StringUtils.isNotEmpty(owner_id)) {
				criteria.setOwner_id(owner_id);
			}

			if (StringUtils.isNotEmpty(srTypeProblem1)) {
				criteria.setSrTypeProblem1(srTypeProblem1);
			}

			if (StringUtils.isNotEmpty(srTypeProblem2)) {
				criteria.setSrTypeProblem2(srTypeProblem2);
			}
			if (StringUtils.isNotEmpty(srTypeProblem3)) {
				criteria.setSrTypeProblem3(srTypeProblem3);
			}
			if (StringUtils.isNotEmpty(srTypeProblem4)) {
				criteria.setSrTypeProblem4(srTypeProblem4);
			}
			if (StringUtils.isNotEmpty(srTypeProblem5)) {
				criteria.setSrTypeProblem5(srTypeProblem5);
			}

			if (StringUtils.isNotEmpty(srStatus)) {
				criteria.setStatus_cd(srStatus);
			}

			if (StringUtils.isNotEmpty(srChannel)) {
				criteria.setChannel_cd(srChannel);
			}

			if (StringUtils.isNotEmpty(srPriority)) {
				criteria.setPriority_cd(srPriority);
			}

			if (StringUtils.isNotEmpty(srCustId)) {
				criteria.setCust_id(srCustId);
			}

			if (StringUtils.isNotEmpty(current_to_dept_cd)) {
				criteria.setCurrent_to_dept_cd(current_to_dept_cd);
			}

			if (StringUtils.isNotEmpty(srCreated_date_start)) {
				String str_srCreated_date_start = DateTimeUtils.convertDateStringToDateStringFormat(srCreated_date_start, JLOWebConstant.DATE_PATTERN_SEARCH);
				criteria.setReg_dt_start(str_srCreated_date_start);

				// TODAY
				if (StringUtils.isEmpty(srCreated_date_end)) {
					criteria.setReg_dt_end(DateTimeUtils.convertDateStringToDateStringFormat(DateTimeUtils.getDate(), DateTimeUtils.DATE_FORMAT_YYYYMMDD));
				}
			}

			if (StringUtils.isNotEmpty(srCreated_date_end)) {

				String str_srCreated_date_end = DateTimeUtils.convertDateStringToDateStringFormat(srCreated_date_end, JLOWebConstant.DATE_PATTERN_SEARCH);
				criteria.setReg_dt_end(str_srCreated_date_end);

				if (StringUtils.isEmpty(srCreated_date_start)) {
					criteria.setReg_dt_start(criteria.getReg_dt_end());
				}

			}

			if (StringUtils.isNotEmpty(srDue_date_start)) {
				String str_srDue_date_start = DateTimeUtils.convertDateStringToDateStringFormat(srDue_date_start, JLOWebConstant.DATE_PATTERN_SEARCH);
				criteria.setDue_dt_start(str_srDue_date_start);

				// TODAY
				if (StringUtils.isEmpty(srDue_date_end)) {
					criteria.setDue_dt_end(DateTimeUtils.convertDateStringToDateStringFormat(DateTimeUtils.getDate(), DateTimeUtils.DATE_FORMAT_YYYYMMDD));
				}

			}

			if (StringUtils.isNotEmpty(srDue_date_end)) {
				String str_srDue_date_end = DateTimeUtils.convertDateStringToDateStringFormat(srDue_date_end, JLOWebConstant.DATE_PATTERN_SEARCH);
				criteria.setDue_dt_end(str_srDue_date_end);

				if (StringUtils.isEmpty(srDue_date_start)) {
					criteria.setDue_dt_start(criteria.getDue_dt_end());
				}
			}

			ServiceResult<Page<ServiceRequestDTO>> serviceResult = serviceRequestService.searchServiceRequestByCriteria(pageable, criteria);

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
					
					String questionStr = srContent.getSrSubject();
					
					if(!StringUtils.isNullOrEmpty(questionStr)){
					  
						if(questionStr.length() > 40){						      
					      String subQuestionStr = questionStr.substring(0, 40);
					      requestModelBean.setSrSubject(subQuestionStr);						      	
					  }else{							  
						  requestModelBean.setSrSubject(questionStr);
					  }
					  
					 }
					
					

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
							url.append("<a href='" + srContent.getSrNumber() + "' onclick='javascript:editServiceRequest(this); return false;' title='" + titleEdit + "' >");
							url.append("<center><i class='fa fa-pencil'></i></center>");
							url.append("</a>");
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

		} else {

			// Set server prop return result No permission
			datatableModelBean.setsEcho(sEcho);
			datatableModelBean.setiTotalDisplayRecords(0);
			datatableModelBean.setiTotalRecords(0);
			datatableModelBean.setAaData(srListModelBean);
		}

		return JsonUtil.toJSON(datatableModelBean, Boolean.TRUE);

	}

	@RequestMapping(value = "/serviceRequestDetail", method = RequestMethod.POST)
	public ModelAndView serviceRequestDetail(Model model, HttpServletRequest request, Locale locale, @RequestParam(value = "sr_number", required = false) String sr_number,
			@RequestParam(value = "mode", required = false) String mode, @RequestParam(value = "module", required = false) String module,
			@RequestParam(value = "custId", required = false) String custId, @RequestParam(value = "custType", required = false) String custType,
			@RequestParam(value = "custName", required = false) String custName) throws Exception {

		setMenuId(request);
		model.addAttribute("pageTitle", messageSource.getMessage("serviceRequest.title", null, locale));
		logger.info("+++ ServiceRequestDetail ServiceRequstController +++ ");
		ServiceRequestModelBean srModelBean = new ServiceRequestModelBean();
		logger.info("## sr_number :" + sr_number);
		logger.info("## mode :" + mode);
		logger.info("## module :" + module);
		logger.info("## custId :" + custId);
		logger.info("## custType :" + custType);
		logger.info("## custName :" + custName);

		String langCd = getLanguageCode(locale);

		if (StringUtils.isNotEmpty(mode) && mode.equals("update")) {

			if (StringUtils.isNotEmpty(sr_number)) {
				srModelBean = getServiceRequestDetailAfterInsertOrUpdate(sr_number, mode, langCd);
			}
		} else {
			logger.info("### insert ###");

			String regDateStr = DateTimeUtils.formatDateTime(new Date());
			srModelBean.setSrRegDate(regDateStr);
			srModelBean.setMode(JLOWebConstant.MODE_INSERT);
		}

		if (!StringUtils.isEmpty(module)) {

			if (module.equalsIgnoreCase("customer") && mode.equals("insert")) {

				srModelBean.setSrCustomerName(custName);
				srModelBean.setSrCustId(custId);
				srModelBean.setSrCustType(custType);
			}

			srModelBean.setModule(module.toLowerCase());

		}

		return new ModelAndView("serviceRequestDetailMain", SR_MODEL, srModelBean);

	}

	@RequestMapping(value = { "/insertServiceRequest", "/updateServiceRequest" }, headers = { "Accept=application/json" }, method = RequestMethod.POST)
	public @ResponseBody
	String serviceRequestInsertOrUpdate(Model model, HttpServletRequest request, Locale local, @ModelAttribute("srModelBean") ServiceRequestModelBean srBean, Pageable pageable,
			RedirectAttributes redirectAttributes) throws Exception {

		logger.info("+++++ serviceRequestInsertOrUpdate ++++ ");

		ServiceRequestModelBean srModelBean = new ServiceRequestModelBean();

		Boolean checkCodeTran = false;
		String msg_desc = "";
		String msg_code = JLOWebConstant.UPDATE_SUCCESS_CODE;
		String msg_title = messageSource.getMessage("menu.serviceRequest.detail", null, local);
		String srNumberSeqKey = "";
		try {

			UserInfoDTO userInfo = getUserInfo(request);

			if (srBean.isInsertMode()) {
				logger.info("### Insert Mode ###");
				String srNumberGen = sequenceGeneratorService.generateByType(SequenceType.SERVICE_REQUEST);
				srNumberSeqKey = srNumberGen;
				logger.info("srNumberSeq :" + srNumberSeqKey);
				srBean.setSrNumber(srNumberGen);

				checkCodeTran = serviceRequestInsert(srBean, srNumberGen, userInfo, request);
				msg_desc = messageSource.getMessage("lbl.action.save.success", null, local);
				msg_code = JLOWebConstant.INSERT_SUCCESS_CODE;

			} else if (srBean.isUpdateMode()) {
				logger.info("### Update Mode ###");
				srNumberSeqKey = srBean.getSrNumber();
				logger.info("srNumberSeq :" + srNumberSeqKey);

				checkCodeTran = serviceRequestUpdate(srBean, userInfo);
				msg_desc = messageSource.getMessage("lbl.action.save.success", null, local);
				msg_code = JLOWebConstant.UPDATE_SUCCESS_CODE;
			}

			logger.info("### checkCodeTran :" + checkCodeTran);

			if (checkCodeTran) {

				SUCCESS = "True";
				MESSAGE = msg_desc;

				srModelBean = new ServiceRequestModelBean();
				srModelBean.setSrNumber(srNumberSeqKey);
				srModelBean.setMode(JLOWebConstant.MODE_UPDATE);

				showWebMessage(request, msg_code, MESSAGE, msg_title);

			} else {

				SUCCESS = "False";
				MESSAGE = messageSource.getMessage("lbl.action.save.fail", null, local);
				showWebMessage(request, JLOWebConstant.INSERT_FAIL_CODE, MESSAGE, msg_title);

			}

		} catch (Exception ex) {

			showWebMessage(request, JLOWebConstant.INSERT_FAIL_CODE, ex.getMessage(), msg_title);

		}

		String returnUrl = "/" + SR_URL_DETAIL + "?mode=update&sr_number=" + srNumberSeqKey;

		return JsonUtil.toJSON(returnUrl, Boolean.TRUE);
	}

	@RequestMapping(value = { "/updateServiceRequestNoteDescription" }, headers = { "Accept=application/json" }, method = RequestMethod.POST)
	public @ResponseBody
	String updateServiceRequestNoteDescription(Model model, HttpServletRequest request, Locale local, @ModelAttribute("srModelBean") ServiceRequestModelBean srBean, Pageable pageable,
			RedirectAttributes redirectAttributes) throws Exception {

		logger.info("+++++ updateServiceRequestNoteDescription ++++ ");

		ServiceRequestModelBean srModelBean = new ServiceRequestModelBean();

		Boolean checkCodeTran = false;
		String msg_desc = "";
		String msg_code = JLOWebConstant.UPDATE_SUCCESS_CODE;
		String msg_title = messageSource.getMessage("menu.serviceRequest.detail", null, local);
		String srNumberSeqKey = "";

		try {
			UserInfoDTO userInfo = getUserInfo(request);

			srNumberSeqKey = srBean.getSrNumber();
			logger.info("srNumberSeq :" + srNumberSeqKey);

			checkCodeTran = serviceRequestUpdateNoteDescription(srBean, userInfo);
			msg_desc = messageSource.getMessage("lbl.action.save.success", null, local);
			msg_code = JLOWebConstant.UPDATE_SUCCESS_CODE;

		} catch (Exception ex) {

			showWebMessage(request, JLOWebConstant.INSERT_FAIL_CODE, ex.getMessage(), msg_title);

		}
		String returnUrl = "/" + SR_URL_DETAIL + "?mode=update&sr_number=" + srNumberSeqKey;

		return JsonUtil.toJSON(returnUrl, Boolean.TRUE);
	}

	public Boolean serviceRequestInsert(ServiceRequestModelBean srBean, String srNumber, UserInfoDTO userInfo, HttpServletRequest request) throws Exception {

		ServiceRequestDTO srDTO = new ServiceRequestDTO();

		logger.info("srNumber Generate :" + srNumber);

		srDTO.setSrNumber(srNumber);
		srDTO.setSrSubject(srBean.getSrSubject());

		srDTO.setSrTypeProblem1Cd(srBean.getSrTypeProblem1Cd());
		srDTO.setSrTypeProblem2Cd(srBean.getSrTypeProblem2Cd());
		srDTO.setSrTypeProblem3Cd(srBean.getSrTypeProblem3Cd());
		srDTO.setSrTypeProblem4Cd(srBean.getSrTypeProblem4Cd());
		srDTO.setSrTypeProblem5Cd(srBean.getSrTypeProblem5Cd());

		srDTO.setSrpriorityCd(srBean.getSrPriorityCd());
		srDTO.setSrChannelCd(srBean.getSrChannelCd());
		srDTO.setSrStatusCd(srBean.getSrStatusCd());

		srDTO.setSrCustId(!StringUtils.isNullOrEmpty(srBean.getSrCustId()) ? Integer.valueOf(srBean.getSrCustId()) : null);
		srDTO.setSrCustType(!StringUtils.isNullOrEmpty(srBean.getSrCustType()) ? srBean.getSrCustType() : null);
		srDTO.setSrConsultingNumber(srBean.getSrConsultingNumber());
		srDTO.setSrContactId(!StringUtils.isNullOrEmpty(srBean.getSrContactId()) ? Integer.valueOf(srBean.getSrContactId()) : null); 
		// JOptionPane.showMessageDialog(null, srBean.getSrConsultingNumber());

		srDTO.setSrDescription(srBean.getSrDescription());
		srDTO.setSrNoteDescription(srBean.getSrNoteDescription());
		srDTO.setSrOwnerId(srBean.getSrOwnerId());
		srDTO.setSrOpenedDate(new Date());

		StringBuilder strDueDate = new StringBuilder("");

		if (StringUtils.isNotEmpty(srBean.getSrStatusCd())) {

			if (srBean.getSrStatusCd().equals(SR_CLOSED_STATUS)) {
				srDTO.setSrCloseDate(new Date());
			}

		}

		if (StringUtils.isNotEmpty(srBean.getSrDueDate())) {

			strDueDate.append(srBean.getSrDueDate());
			strDueDate.append(" ");

			if (StringUtils.isNotEmpty(srBean.getSrDueTime())) {
				strDueDate.append(srBean.getSrDueTime());

			} else {

				strDueDate.append("00:00");
			}

			srDTO.setSrDueDate(DateTimeUtils.parseDate(strDueDate.toString(), JLOWebConstant.DATE_PATTERN_INSERT_UPDATE));
		} else {

			srDTO.setSrDueDate(null);
		}

		srDTO.setRegId(userInfo.getUserId());
		srDTO.setRegDt(new Date());

		ServiceResult<Map<String, Object>> serviceResult = serviceRequestService.insertServiceRequestProcedure(srDTO);

		Boolean tranResult = false;
		if (serviceResult.isSuccess()) {

			tranResult = true;

			ConsultingDTO consultObj = (ConsultingDTO) request.getSession().getAttribute(CONSULTING_OBJECT_SESSION);
			if (consultObj != null) {
				logger.info("++++++  Start insert consulting ++++++ ");
				ConsultingRelDTO conRel = new ConsultingRelDTO();
				String consultingId = consultObj.getConsultingNumber();

				conRel.setConsultingId(consultingId);
				conRel.setRefId(srNumber);

				logger.info(" ConsultingId :" + consultingId);
				logger.info(" RefId :" + srNumber);

				ServiceResult<Long> srRelConsult = consultingService.insertTpRelConsulting(conRel);
				Long ConsultRelInsert = srRelConsult.getResult();
				logger.info("ResultCode Consulting Relation  :" + ConsultRelInsert);

				if (srRelConsult.isSuccess() && ConsultRelInsert > 0) {
					tranResult = true;
				} else {
					tranResult = false;
				}

				logger.info("Transection Result Insert  " + tranResult);
				logger.info("++++++  End insert consulting ++++++ ");
			}

		} else {

			tranResult = false;
		}

		return tranResult;

	}

	public Boolean serviceRequestUpdate(ServiceRequestModelBean srBean, UserInfoDTO userInfo) throws Exception {

		ServiceRequestDTO srDTO = new ServiceRequestDTO();

		srDTO.setSrNumber(!StringUtils.isNullOrEmpty(srBean.getSrNumber()) ? srBean.getSrNumber() : "-1");
		srDTO.setSrSubject(srBean.getSrSubject());
		srDTO.setSrTypeProblem1Cd(srBean.getSrTypeProblem1Cd());
		srDTO.setSrTypeProblem2Cd(srBean.getSrTypeProblem2Cd());
		srDTO.setSrTypeProblem3Cd(srBean.getSrTypeProblem3Cd());
		srDTO.setSrTypeProblem4Cd(srBean.getSrTypeProblem4Cd());
		srDTO.setSrTypeProblem5Cd(srBean.getSrTypeProblem5Cd());
		srDTO.setSrpriorityCd(srBean.getSrPriorityCd());
		srDTO.setSrChannelCd(srBean.getSrChannelCd());
		srDTO.setSrStatusCd(srBean.getSrStatusCd());

		srDTO.setSrCustId(!StringUtils.isNullOrEmpty(srBean.getSrCustId()) ? Integer.valueOf(srBean.getSrCustId()) : null);
		srDTO.setSrCustType(!StringUtils.isNullOrEmpty(srBean.getSrCustType()) ? srBean.getSrCustType() : null);
		srDTO.setSrContactId(!StringUtils.isNullOrEmpty(srBean.getSrContactId()) ? Integer.valueOf(srBean.getSrContactId()) : null);
		
		
		srDTO.setSrDescription(srBean.getSrDescription());
		srDTO.setSrNoteDescription(srBean.getSrNoteDescription());
		srDTO.setSrOwnerId(srBean.getSrOwnerId());

		StringBuilder strOpenDate = new StringBuilder("");
		if (StringUtils.isNotEmpty(srBean.getSrOpenedDate())) {

			strOpenDate.append(srBean.getSrOpenedDate());
			strOpenDate.append(" ");

			if (StringUtils.isNotEmpty(srBean.getSrOpenedTime())) {
				strOpenDate.append(srBean.getSrOpenedTime());
			} else {
				strOpenDate.append("00:00");
			}

			srDTO.setSrOpenedDate(DateTimeUtils.parseDate(strOpenDate.toString(), JLOWebConstant.DATE_PATTERN_INSERT_UPDATE));
		} else {
			srDTO.setSrOpenedDate(null);
		}

		if (StringUtils.isNotEmpty(srBean.getSrStatusCd())) {
			if (srBean.getSrStatusCd().equals(SR_CLOSED_STATUS)) {
				srDTO.setSrCloseDate(new Date());
			}
		} else {

			srDTO.setSrCloseDate(null);
		}

		srDTO.setChgId(userInfo.getUserId());
		srDTO.setChgDt(new Date());

		ServiceResult<Map<String, Object>> serviceResult = serviceRequestService.updateServiceRequestDetailProcedure(srDTO);

		// Long updateSR = serviceResult.getResult();
		// logger.info("ResultCode Update :" + updateSR);

		Boolean tranResult = false;

		if (serviceResult.isSuccess()) {
			tranResult = true;

		} else {

			tranResult = false;
		}

		return tranResult;
	}

	public Boolean serviceRequestUpdateNoteDescription(ServiceRequestModelBean srBean, UserInfoDTO userInfo) throws Exception {

		ServiceRequestDTO srDTO = new ServiceRequestDTO();
		
		srDTO.setSrNumber(!StringUtils.isNullOrEmpty(srBean.getSrNumber()) ? srBean.getSrNumber() : "-1");
		srDTO.setSrNoteDescription(srBean.getSrNoteDescription());

		ServiceResult<Long> serviceResult = serviceRequestService.updateServiceRequestNoteDescription(srDTO);
				
		Boolean tranResult = false;

		if (serviceResult.isSuccess()) {
			tranResult = true;

		} else {

			tranResult = false;
		}

		return tranResult;
	}

	public ServiceRequestModelBean getServiceRequestDetailAfterInsertOrUpdate(String sr_number, String mode, String langCd) throws Exception {

		ServiceRequestModelBean srModelBean = new ServiceRequestModelBean();
		if (!StringUtils.isEmpty(sr_number)) {

			ServiceRequestDTO resultRequestDTO = new ServiceRequestDTO();
			ServiceResult<ServiceRequestDTO> serviceResult = serviceRequestService.searchServiceRequestDetailBySrID(sr_number, langCd);

			if (serviceResult.isSuccess()) {

				resultRequestDTO = serviceResult.getResult();

				if (resultRequestDTO != null) {

					// Begin put value into Model Bean
					srModelBean.setSrNumber(resultRequestDTO.getSrNumber());
					srModelBean.setSrSubject(resultRequestDTO.getSrSubject());
					srModelBean.setSrTypeProblem1(resultRequestDTO.getSrTypeProblem1());
					srModelBean.setSrTypeProblem2(resultRequestDTO.getSrTypeProblem2());
					srModelBean.setSrTypeProblem3(resultRequestDTO.getSrTypeProblem3());
					srModelBean.setSrTypeProblem4(resultRequestDTO.getSrTypeProblem4());
					srModelBean.setSrTypeProblem5(resultRequestDTO.getSrTypeProblem5());

					srModelBean.setSrTypeProblem1Cd(resultRequestDTO.getSrTypeProblem1Cd());
					srModelBean.setSrTypeProblem2Cd(resultRequestDTO.getSrTypeProblem2Cd());
					srModelBean.setSrTypeProblem3Cd(resultRequestDTO.getSrTypeProblem3Cd());
					srModelBean.setSrTypeProblem4Cd(resultRequestDTO.getSrTypeProblem4Cd());
					srModelBean.setSrTypeProblem5Cd(resultRequestDTO.getSrTypeProblem5Cd());
					srModelBean.setSrConsultingNumber(resultRequestDTO.getSrConsultingNumber());

					srModelBean.setSrPriorityCd(resultRequestDTO.getSrpriorityCd());
					srModelBean.setSrChannelCd(resultRequestDTO.getSrChannelCd());
					srModelBean.setSrStatusCd(resultRequestDTO.getSrStatusCd());
					srModelBean.setSrContactId(null != resultRequestDTO.getSrContactId() ? String.valueOf(resultRequestDTO.getSrContactId()) : "");
					
					srModelBean.setSrCustomerName(resultRequestDTO.getCustomerName());
					srModelBean.setSrCustId(null != resultRequestDTO.getSrCustId() ? String.valueOf(resultRequestDTO.getSrCustId()) : "");
					srModelBean.setSrCustType(resultRequestDTO.getSrCustType());
					srModelBean.setSrDescription(resultRequestDTO.getSrDescription());
					srModelBean.setSrNoteDescription(resultRequestDTO.getSrNoteDescription());
					srModelBean.setSrOwnerName(resultRequestDTO.getSrOwnerName());
					srModelBean.setSrOwnerId(resultRequestDTO.getSrOwnerId());

					String openedDateStr = DateTimeUtils.formatDate(resultRequestDTO.getSrOpenedDate());
					srModelBean.setSrOpenedDate(openedDateStr);
					srModelBean.setSrOpenedTime(DateTimeUtils.parseFormatDateTime(resultRequestDTO.getSrOpenedDate()));

					String dueDateStr = DateTimeUtils.formatDate(resultRequestDTO.getSrDueDate());
					srModelBean.setSrDueDate(dueDateStr);
					srModelBean.setSrDueTime(DateTimeUtils.parseFormatDateTime(resultRequestDTO.getSrDueDate()));

					String closedDateStr = DateTimeUtils.formatDate(resultRequestDTO.getSrCloseDate());
					srModelBean.setSrCloseDate(closedDateStr);
					srModelBean.setSrCloseTime(DateTimeUtils.parseFormatDateTime(resultRequestDTO.getSrCloseDate()));

					srModelBean.setSrChgId(null != resultRequestDTO.getChgId() ? String.valueOf(resultRequestDTO.getChgId()) : "");
					srModelBean.setCreateByName(resultRequestDTO.getCreateByName());
					srModelBean.setCreateDateTime(resultRequestDTO.getCreateDateTime());
					srModelBean.setUpdateByName(resultRequestDTO.getUpdateByName());
					srModelBean.setUpdateDateTime(resultRequestDTO.getUpdateDateTime());
				}

				srModelBean.setMode(JLOWebConstant.MODE_UPDATE);

			}

		}

		return srModelBean;

	}

	/**
	 * for change tab it will load file url
	 * 
	 * @param model
	 * @param request
	 * @param local
	 * @param tabAction
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/serviceRequestMoreInfo", method = RequestMethod.POST)
	public ModelAndView serviceRequestMoreInfo(Model model, HttpServletRequest request, Locale local, @RequestParam(value = "tabAction", required = true) String tabAction,
			@RequestParam(value = "sr_number", required = true) String srNumber, @RequestParam(value = "refStatusCd", required = false) String refStatusCd) throws Exception {

		logger.info("+++ ServiceRequestList serviceRequestMoreInfo +++ ");
		logger.info(this.getClass().toString());
		ServiceRequestActivityModelBean srActModelBean = new ServiceRequestActivityModelBean();

		ServiceRequestSolutionModelBean srSolutionModelBean = new ServiceRequestSolutionModelBean();

		refStatusCd = (!StringUtils.isEmpty(refStatusCd) ? refStatusCd : "");

		String retrunPage = "";

		if (tabAction != null) {

			srActModelBean.setRefDocNo(srNumber);

			if (tabAction.equals("activity")) {

				srActModelBean.setMode(JLOWebConstant.MODE_UPDATE);
				srActModelBean.setRefStatusCd(refStatusCd);

				retrunPage = "serviceRequestActivity";
				return new ModelAndView(retrunPage, SR_ACT_MODEL, srActModelBean);

			} else if (tabAction.equals("solutions")) {

				srSolutionModelBean.setSrRefDocNo(srNumber);
				srSolutionModelBean.setRefStatusCd(refStatusCd);
				retrunPage = "serviceRequestSolutions";
				return new ModelAndView(retrunPage, SR_SOLUTIONS_MODEL, srSolutionModelBean);
			}

		} else {

			// varible tab is null
			retrunPage = "serviceRequestActivity";
			srActModelBean.setMode(JLOWebConstant.MODE_UPDATE);
			srActModelBean.setRefStatusCd(refStatusCd);

			return new ModelAndView(retrunPage, SR_ACT_MODEL, srActModelBean);

		}

		logger.info("REF_DOC_NO:" + srNumber);
		return new ModelAndView(retrunPage, SR_ACT_MODEL, srActModelBean);

	}

	@RequestMapping(value = "/inquirySrActivityList", method = RequestMethod.POST, headers = { "Accept=application/json" })
	public @ResponseBody
	String inquirySrActivityList(HttpServletRequest request, HttpServletResponse response, Locale locale, @RequestParam(value = "referenceNo", required = true) String referenceNo) throws Exception {

		logger.info("ServiceRequstController - inquirySrActivityList");
		logger.info("*****************************************************");
		String langCd = getLanguageCode(locale);
		String sEchoTxt = request.getParameter("sEcho");
		Integer sEcho = (!StringUtils.isNullOrEmpty(sEchoTxt)) ? Integer.valueOf(sEchoTxt) : 0;
		String referenceNoParam = "";
		if (StringUtils.isEmpty(referenceNo)) {
			referenceNoParam = "-1";
		} else {
			referenceNoParam = referenceNo;
		}

		Pageable pageable = getPagableFromRequest(request);
		DatatableModelBean datatableModelBean = new DatatableModelBean();
		List<ServiceRequestActivityModelBean> actListModelBean = new ArrayList<ServiceRequestActivityModelBean>();
		logger.info("referenceNo : " + referenceNo);

		ServiceResult<Page<ServiceRequestActDTO>> serviceResult = serviceRequestService.searchSRActivityListDetailByRefDocNo(pageable, referenceNoParam, langCd);

		logger.info("isSuccess : " + serviceResult.isSuccess());

		if (serviceResult.isSuccess()) {

			Page<ServiceRequestActDTO> result = serviceResult.getResult();
			Integer totalRecords = (int) (long) result.getTotalElements();

			ServiceRequestActivityModelBean srActModelBean = new ServiceRequestActivityModelBean();

			for (ServiceRequestActDTO actContent : result) {

				srActModelBean = new ServiceRequestActivityModelBean();

				srActModelBean.setRefDocNo(actContent.getRefDocNo());
				srActModelBean.setActNumber(actContent.getActNumber());
				srActModelBean.setTitle(actContent.getTitle());
				srActModelBean.setActivityTypeName(actContent.getActivityTypeName());
				srActModelBean.setActivityStatusName(actContent.getActivityStatusName());

				srActModelBean.setOpenedDt(DateTimeUtils.formatDateTime(actContent.getOpenedDt()));
				srActModelBean.setOperDt(DateTimeUtils.formatDateTime(actContent.getOperDt()));
				srActModelBean.setClosedDt(DateTimeUtils.formatDateTime(actContent.getClosedDt()));

				srActModelBean.setOwnerName(actContent.getOwnerName());

				srActModelBean.setAttendTo(actContent.getAttendTo());
				srActModelBean.setTypeCd(actContent.getTypeCd());
				srActModelBean.setDescription(actContent.getDescription());
				srActModelBean.setPhoneNo(actContent.getPhoneNo());
				srActModelBean.setSmsNo(actContent.getSmsNo());
				srActModelBean.setFaxNo(actContent.getFaxNo());
				srActModelBean.setEmail(actContent.getEmail());
				srActModelBean.setAddress(actContent.getAddress());
				srActModelBean.setStatusCd(actContent.getStatusCd());
				srActModelBean.setOwnerId(actContent.getOwnerId()); // assignTo
																	// hidden
																	// field
				srActModelBean.setDeptName(actContent.getDeptName());
				srActModelBean.setOwnerDeptCode(actContent.getOwnerDeptCode());

				srActModelBean.setDueDate(DateTimeUtils.formatDate(actContent.getDueDt()));
				srActModelBean.setDueDateTime(DateTimeUtils.parseFormatDateTime(actContent.getDueDt()));

				srActModelBean.setOperDate(DateTimeUtils.formatDate(actContent.getOperDt()));
				srActModelBean.setOperDateTime(DateTimeUtils.parseFormatDateTime(actContent.getOperDt()));

				srActModelBean.setClosedDate(DateTimeUtils.formatDate(actContent.getClosedDt()));
				srActModelBean.setClosedDateTime(DateTimeUtils.parseFormatDateTime(actContent.getClosedDt()));

				srActModelBean.setMode(JLOWebConstant.MODE_UPDATE);

				// For Panel activity Bottom
				srActModelBean.setRegName(!StringUtils.isNullOrEmpty(actContent.getRegName()) ? actContent.getRegName() : "");
				srActModelBean.setChgName(!StringUtils.isNullOrEmpty(actContent.getChgName()) ? actContent.getChgName() : "");

				srActModelBean.setRegDatetime(!StringUtils.isNullOrEmpty(actContent.getRegDatetime()) ? actContent.getRegDatetime() : "");
				srActModelBean.setChgDatetime(!StringUtils.isNullOrEmpty(actContent.getChgDatetime()) ? actContent.getChgDatetime() : "");

				actListModelBean.add(srActModelBean);

			}

			// Set server prop return result
			datatableModelBean.setsEcho(sEcho);
			datatableModelBean.setiTotalDisplayRecords(totalRecords);
			datatableModelBean.setiTotalRecords(totalRecords);
			datatableModelBean.setAaData(actListModelBean);

		} else {

			showWebMessage(request, serviceResult.getResponseCode(), serviceResult.getResponseDescription());
		}

		return JsonUtil.toJSON(datatableModelBean, Boolean.TRUE);

	}

	@RequestMapping(value = { "/insertSrActivity", "/updateSrActivity" }, method = RequestMethod.POST)
	public @ResponseBody
	String actInsertOrUpdate(HttpServletRequest request, HttpServletResponse response, Locale locale, @ModelAttribute("srActModelBean") ServiceRequestActivityModelBean actBean) throws Exception {

		logger.info("*****************************************************");
		logger.info("ServiceRequstController - actInsertOrUpdate");
		logger.info("actBean.Mode " + actBean.getMode());
		Boolean checkCodeTran = false;
		String msg_desc = "";
		String msg_code = "";
		String actNumberSeqKey = "";
		String langCd = getLanguageCode(locale);

		UserInfoDTO userInfo = getUserInfo(request);

		if (actBean.isInsertMode()) {

			logger.info("### Insert Mode ###");
			String actNumberGen = sequenceGeneratorService.generateByType(SequenceType.ACTIVITY_SR);
			actNumberSeqKey = actNumberGen;
			logger.info("actNumberSeqKey :" + actNumberSeqKey);

			if (!StringUtils.isEmpty(actNumberSeqKey)) {

				checkCodeTran = activityInsert(actBean, actNumberSeqKey, userInfo);
				msg_desc = messageSource.getMessage("lbl.action.save.success", null, locale);
				msg_code = JLOWebConstant.INSERT_SUCCESS_CODE;

			} else {

				checkCodeTran = false;
				msg_desc = messageSource.getMessage("lbl.action.save.fail", null, locale);
				msg_code = JLOWebConstant.INSERT_FAIL_CODE;
			}

		} else if (actBean.isUpdateMode()) {

			actNumberSeqKey = actBean.getActNumber();

			logger.info("### Upate Mode ###");
			logger.info("update by actNumberSeqKey  :" + actNumberSeqKey);
			if (!StringUtils.isEmpty(actNumberSeqKey)) {

				checkCodeTran = activityUpdate(actBean, userInfo);
				msg_desc = messageSource.getMessage("lbl.action.save.success", null, locale);
				msg_code = JLOWebConstant.UPDATE_SUCCESS_CODE;
			} else {
				checkCodeTran = false;
				msg_desc = messageSource.getMessage("lbl.action.save.fail", null, locale);
				msg_code = JLOWebConstant.UPDATE_SUCCESS_CODE;
			}

		}

		logger.info("### checkCodeTran :" + checkCodeTran);
		JsonResultBean result = new JsonResultBean();

		if (checkCodeTran) {

			SUCCESS = "True";
			MESSAGE = msg_desc;
			MESSAGE_CODE = msg_code;
			actBean = searchSRActivityListDetailByActNumber(actNumberSeqKey, langCd);

			result.setResultCode(MESSAGE_CODE);
			result.setResultMessage(MESSAGE);
			result.setModel(actBean);

		} else {

			SUCCESS = "False";
			MESSAGE = msg_desc;
			MESSAGE_CODE = msg_code;

			result.setResultCode(MESSAGE_CODE);
			result.setResultMessage(MESSAGE);

		}

		return JsonUtil.toJSON(result, Boolean.TRUE);
	}

	public Boolean activityInsert(ServiceRequestActivityModelBean actBean, String actNumberSeq, UserInfoDTO userInfo) throws Exception {

		ServiceRequestActDTO actDto = new ServiceRequestActDTO();

		logger.info("srNumber Generate :" + actNumberSeq);

		actDto.setRefDocNo(actBean.getRefDocNo());
		actDto.setActNumber(actNumberSeq);
		actDto.setAttendTo(actBean.getAttendTo());
		actDto.setTitle(actBean.getTitle());
		actDto.setTypeCd(actBean.getTypeCd());
		actDto.setDescription(actBean.getDescription());
		actDto.setPhoneNo(actBean.getPhoneNo());
		actDto.setSmsNo(actBean.getSmsNo());
		actDto.setFaxNo(actBean.getFaxNo());
		actDto.setEmail(actBean.getEmail());
		actDto.setAddress(actBean.getAddress());
		actDto.setStatusCd(actBean.getStatusCd());
		actDto.setOwnerId(actBean.getOwnerId());
		actDto.setOwnerDeptCode(actBean.getOwnerDeptCode());
		actDto.setOpenedDt(new Date());

		if (!StringUtils.isEmpty(actBean.getStatusCd())) {
			if (actBean.getStatusCd().equals(SR_ACT_CLOSED_STATUS)) {
				actDto.setClosedDt(new Date());
			}
		} else {
			actDto.setClosedDt(null);
		}

		StringBuilder strDueDate = new StringBuilder("");
		StringBuilder strOperDate = new StringBuilder("");

		if (StringUtils.isNotEmpty(actBean.getDueDate())) {
			strDueDate.append(actBean.getDueDate());
			strDueDate.append(" ");

			if (StringUtils.isNotEmpty(actBean.getDueDateTime())) {
				strDueDate.append(actBean.getDueDateTime());
			} else {
				strDueDate.append("00:00");
			}

			actDto.setDueDt(DateTimeUtils.parseDate(strDueDate.toString(), JLOWebConstant.DATE_PATTERN_INSERT_UPDATE));

		} else {

			actDto.setDueDt(null);
		}

		if (StringUtils.isNotEmpty(actBean.getOperDate())) {

			strOperDate.append(actBean.getOperDate());
			strOperDate.append(" ");

			if (StringUtils.isNotEmpty(actBean.getOperDateTime())) {
				strOperDate.append(actBean.getOperDateTime());
			} else {
				strOperDate.append("00:00");
			}

			actDto.setOperDt(DateTimeUtils.parseDate(strOperDate.toString(), JLOWebConstant.DATE_PATTERN_INSERT_UPDATE));

		} else {

			actDto.setOperDt(new Date());
		}
		actDto.setRegId(String.valueOf(userInfo.getUserId()));
		actDto.setRegDt(new Date());

		ServiceResult<Long> serviceResult = serviceRequestService.insertActivity(actDto);

		Long updateSR = serviceResult.getResult();
		logger.info("ResultCode Insert :" + updateSR);

		Boolean tranResult = false;

		if (serviceResult.isSuccess() && updateSR > 0) {
			tranResult = true;
		} else {
			tranResult = false;
		}

		return tranResult;

	}

	public Boolean activityUpdate(ServiceRequestActivityModelBean actBean, UserInfoDTO userInfo) throws Exception {

		ServiceRequestActDTO actDto = new ServiceRequestActDTO();

		actDto.setActNumber(actBean.getActNumber());
		actDto.setAttendTo(actBean.getAttendTo());
		actDto.setTitle(actBean.getTitle());
		actDto.setTypeCd(actBean.getTypeCd());
		actDto.setDescription(actBean.getDescription());
		actDto.setPhoneNo(actBean.getPhoneNo());
		actDto.setSmsNo(actBean.getSmsNo());
		actDto.setFaxNo(actBean.getFaxNo());
		actDto.setEmail(actBean.getEmail());
		actDto.setAddress(actBean.getAddress());
		actDto.setStatusCd(actBean.getStatusCd());
		actDto.setOwnerId(actBean.getOwnerId());
		actDto.setOwnerDeptCode(actBean.getOwnerDeptCode());

		StringBuilder strDueDate = new StringBuilder("");
		StringBuilder strOperDate = new StringBuilder("");

		if (!StringUtils.isEmpty(actBean.getStatusCd())) {

			if (actBean.getStatusCd().equals(SR_ACT_CLOSED_STATUS)) {
				actDto.setClosedDt(new Date());
			}

		} else {
			actDto.setClosedDt(null);
		}

		// check Due Date and set value
		if (StringUtils.isNotEmpty(actBean.getDueDate())) {
			strDueDate.append(actBean.getDueDate());
			strDueDate.append(" ");

			if (StringUtils.isNotEmpty(actBean.getDueDateTime())) {
				strDueDate.append(actBean.getDueDateTime());
			} else {
				strDueDate.append("00:00");
			}

			actDto.setDueDt(DateTimeUtils.parseDate(strDueDate.toString(), JLOWebConstant.DATE_PATTERN_INSERT_UPDATE));

		} else {

			actDto.setDueDt(null);
		}

		if (StringUtils.isNotEmpty(actBean.getOperDate())) {
			strOperDate.append(actBean.getOperDate());
			strOperDate.append(" ");

			if (StringUtils.isNotEmpty(actBean.getOperDateTime())) {
				strOperDate.append(actBean.getOperDateTime());
			} else {
				strOperDate.append("00:00");
			}

			actDto.setOperDt(DateTimeUtils.parseDate(strOperDate.toString(), JLOWebConstant.DATE_PATTERN_INSERT_UPDATE));

		} else {
			actDto.setOperDt(null);
		}

		actDto.setChgId(String.valueOf(userInfo.getUserId()));
		actDto.setChgDt(new Date());

		ServiceResult<Long> serviceResult = serviceRequestService.updateActivity(actDto);

		Long updateSR = serviceResult.getResult();
		logger.info("ResultCode Update :" + updateSR);

		Boolean tranResult = false;

		if (serviceResult.isSuccess() && updateSR > 0) {
			tranResult = true;
		} else {
			tranResult = false;
		}

		return tranResult;

	}

	public ServiceRequestActivityModelBean searchSRActivityListDetailByActNumber(String actNumber, String langCd) throws Exception {

		ServiceRequestActivityModelBean actModelBean = new ServiceRequestActivityModelBean();
		ServiceResult<ServiceRequestActDTO> serviceResult = serviceRequestService.searchSRActivityListDetailByActNumber(actNumber, langCd);
		ServiceRequestActDTO actContent = new ServiceRequestActDTO();

		if (serviceResult.isSuccess()) {

			actContent = serviceResult.getResult();
			actModelBean = new ServiceRequestActivityModelBean();

			if (actContent != null) {

				actModelBean.setRefDocNo(actContent.getRefDocNo());
				actModelBean.setActNumber(actContent.getActNumber());
				actModelBean.setTitle(actContent.getTitle());
				actModelBean.setActivityTypeName(actContent.getActivityTypeName());
				actModelBean.setActivityStatusName(actContent.getActivityStatusName());

				actModelBean.setOpenedDt(DateTimeUtils.formatDateTime(actContent.getOpenedDt()));
				actModelBean.setOperDt(DateTimeUtils.formatDateTime(actContent.getOperDt()));
				actModelBean.setClosedDt(DateTimeUtils.formatDateTime(actContent.getClosedDt()));

				actModelBean.setOwnerName(actContent.getOwnerName());

				actModelBean.setAttendTo(actContent.getAttendTo());
				actModelBean.setTypeCd(actContent.getTypeCd());
				actModelBean.setDescription(actContent.getDescription());
				actModelBean.setPhoneNo(actContent.getPhoneNo());
				actModelBean.setSmsNo(actContent.getSmsNo());
				actModelBean.setFaxNo(actContent.getFaxNo());
				actModelBean.setEmail(actContent.getEmail());
				actModelBean.setAddress(actContent.getAddress());
				actModelBean.setStatusCd(actContent.getStatusCd());
				actModelBean.setOwnerId(actContent.getOwnerId()); // assignTo
																	// hidden
																	// field
				actModelBean.setDeptName(actContent.getDeptName());
				actModelBean.setOwnerDeptCode(actContent.getOwnerDeptCode());

				actModelBean.setDueDate(DateTimeUtils.formatDate(actContent.getDueDt()));
				actModelBean.setDueDateTime(DateTimeUtils.parseFormatDateTime(actContent.getDueDt()));

				actModelBean.setOperDate(DateTimeUtils.formatDate(actContent.getOperDt()));
				actModelBean.setOperDateTime(DateTimeUtils.parseFormatDateTime(actContent.getOperDt()));

				actModelBean.setClosedDate(DateTimeUtils.formatDate(actContent.getClosedDt()));
				actModelBean.setClosedDateTime(DateTimeUtils.parseFormatDateTime(actContent.getClosedDt()));

				// For Panel activity Bottom
				actModelBean.setRegName(!StringUtils.isNullOrEmpty(actContent.getRegName()) ? actContent.getRegName() : "");
				actModelBean.setChgName(!StringUtils.isNullOrEmpty(actContent.getChgName()) ? actContent.getChgName() : "");

				actModelBean.setRegDatetime(!StringUtils.isNullOrEmpty(actContent.getRegDatetime()) ? actContent.getRegDatetime() : "");
				actModelBean.setChgDatetime(!StringUtils.isNullOrEmpty(actContent.getChgDatetime()) ? actContent.getChgDatetime() : "");

			}

			actModelBean.setMode(JLOWebConstant.MODE_UPDATE);
		}

		return actModelBean;
	}

	@RequestMapping(value = "/deleteActivity", method = RequestMethod.POST)
	public @ResponseBody
	String deleteActivity(HttpServletRequest request, HttpServletResponse response, Locale local, @RequestParam(value = "actNoRefNo", required = false) String actNoRefNo,
			@RequestParam(value = "actNumber", required = false) String actNumber) throws Exception {

		logger.info(" +++++++++++ Delete Activity ++++++++++++ ");

		ServiceResult<Long> serviceResult = serviceRequestService.deleteActivity(actNoRefNo, actNumber);
		Long updateSR = serviceResult.getResult();
		logger.info("ResultCode Deleted :" + updateSR);
		JsonResultBean result = new JsonResultBean();

		if (serviceResult.isSuccess() && updateSR > 0) {

			result.setResultCode(JLOWebConstant.DELETE_SUCCESS_CODE);
			MESSAGE = JLOWebConstant.DELETE_SUCCESS_DESC;
			result.setResultCode(JLOWebConstant.DELETE_SUCCESS_CODE);
			result.setResultMessage(MESSAGE);
		} else {

			result.setResultCode(JLOWebConstant.DELETE_FAIL_CODE);
			MESSAGE = JLOWebConstant.DELETE_FAIL_DESC;
			result.setResultCode(JLOWebConstant.DELETE_FAIL_CODE);
			result.setResultMessage(MESSAGE);
		}

		return JsonUtil.toJSON(result, Boolean.TRUE);

	}

	@RequestMapping(value = "/searchThaiQuestDetail", method = RequestMethod.POST, headers = { "Accept=application/json" })
	public @ResponseBody
	String searchDataFromTqKb(HttpServletRequest request, HttpServletResponse response, Locale locale, @RequestParam(value = "contentNumber", required = true) String contentNumber) throws Exception {
		String langCd = getLanguageCode(locale);

		ServiceResult<ServiceRequestSolutionDTO> serviceResult = serviceRequestService.searchKnowledgeBaseContentDetailByKbNumber(contentNumber, langCd);
		ServiceRequestSolutionDTO kbContent = new ServiceRequestSolutionDTO();
		ServiceRequestSolutionModelBean srSolModelBean = new ServiceRequestSolutionModelBean();
		JsonResultBean result = new JsonResultBean();

		logger.info("serviceResult.isSuccess : " + serviceResult.isSuccess());

		if (serviceResult.isSuccess()) {

			srSolModelBean = new ServiceRequestSolutionModelBean();

			kbContent = serviceResult.getResult();

			if (kbContent != null) {

				srSolModelBean.setSolContentId(kbContent.getContentId());
				srSolModelBean.setSolContentNumber(kbContent.getContentNumber());
				srSolModelBean.setSolContentTitle(kbContent.getContentTitle());
				srSolModelBean.setSolContentQuestion(kbContent.getContentQuestion());
				srSolModelBean.setSolContentSummary(kbContent.getContentSummary());
				srSolModelBean.setSolContentStatusCd(kbContent.getContentStatusCd());
				srSolModelBean.setSolContentStatusName(kbContent.getContentStatusName());
				srSolModelBean.setSolContentFaqKbType(kbContent.getContentType());	
				
				srSolModelBean.setSolContentCat1Id(kbContent.getContentCat1Id());
				srSolModelBean.setSolContentCat2Id(kbContent.getContentCat2Id());
				srSolModelBean.setSolContentCat3Id(kbContent.getContentCat3Id());
				srSolModelBean.setSolContentCat4Id(kbContent.getContentCat4Id());
				srSolModelBean.setSolContentCat5Id(kbContent.getContentCat5Id());

				srSolModelBean.setSolContentCat1Name(kbContent.getContentCat1Name());
				srSolModelBean.setSolContentCat2Name(kbContent.getContentCat2Name());
				srSolModelBean.setSolContentCat3Name(kbContent.getContentCat3Name());
				srSolModelBean.setSolContentCat4Name(kbContent.getContentCat4Name());
				srSolModelBean.setSolContentCat5Name(kbContent.getContentCat5Name());

			}

			MESSAGE_CODE = serviceResult.getResponseCode();
			MESSAGE = serviceResult.getResponseDescription();
			result.setResultCode(MESSAGE_CODE);
			result.setResultMessage(MESSAGE);

			result.setModel(srSolModelBean);
		} else {

			MESSAGE_CODE = serviceResult.getResponseCode();
			MESSAGE = serviceResult.getResponseDescription();
			result.setResultCode(MESSAGE_CODE);
			result.setResultMessage(MESSAGE);
			result.setModel(srSolModelBean);
		}

		return JsonUtil.toJSON(result, Boolean.TRUE);
	}

	@RequestMapping(value = "/inquirySearchDataFromTqKbByCriteria", method = RequestMethod.POST, headers = { "Accept=application/json" })
	public @ResponseBody
	String inquirySearchDataFromTqKbByCriteria(HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception {
		logger.info("ServiceRequstController - inquirySearchDataFromTqKbByCriteria");
		logger.info("****************Begin Print Parameter************************");

		String sEchoTxt = request.getParameter("sEcho");
		Integer sEcho = (!StringUtils.isNullOrEmpty(sEchoTxt)) ? Integer.valueOf(sEchoTxt) : 0;

		String iDisplayLengthTxt = request.getParameter("iDisplayLength");
		Integer limit = (!StringUtils.isNullOrEmpty(iDisplayLengthTxt)) ? Integer.valueOf(iDisplayLengthTxt) : 10;

		logger.info("sEcho :" + sEcho);
		logger.info("limit :" + limit);

		Pageable pageable = getPagableFromRequest(request);
		Integer currentPage = pageable.getPageNumber();
		String langCd = getLanguageCode(locale);

		String solutionSearchType = request.getParameter("rdoSolSearchType");
		String contentNumber = request.getParameter("txtSolutionkbNo");
		String soltionKeyWord = request.getParameter("soltionKeyWord");
		String soltionTypeProblem1 = request.getParameter("soltionTypeProblem1");
		String soltionTypeProblem2 = request.getParameter("soltionTypeProblem2");
		String soltionTypeProblem3 = request.getParameter("soltionTypeProblem3");
		String soltionTypeProblem4 = request.getParameter("soltionTypeProblem4");
		String soltionTypeProblem5 = request.getParameter("soltionTypeProblem5");

		if (!StringUtils.isEmpty(soltionKeyWord)) {
			if (soltionKeyWord.indexOf("+") > -1) {
				soltionKeyWord = soltionKeyWord.replaceAll("\\+", " ");
			}
		}

		logger.info("solutionSearchType : " + solutionSearchType);
		logger.info("contentNumber  	: " + contentNumber);
		logger.info("Solution Word 		: " + soltionKeyWord);
		logger.info("soltionTypeProblem1 " + soltionTypeProblem1);
		logger.info("soltionTypeProblem2 " + soltionTypeProblem2);
		logger.info("soltionTypeProblem3 " + soltionTypeProblem3);
		logger.info("soltionTypeProblem4 " + soltionTypeProblem4);
		logger.info("soltionTypeProblem5 " + soltionTypeProblem5);

		logger.info("****************End Print Parameter************************");

		List<ServiceRequestSolutionModelBean> srSolModelBeanList = new ArrayList<ServiceRequestSolutionModelBean>();
		ServiceRequestSolutionModelBean srSolModelBean = null;
		DatatableModelBean datatableModelBean = new DatatableModelBean();

		if (StringUtils.isNotEmpty(solutionSearchType)) {

			if (solutionSearchType.equals("TQ")) {
				logger.info(">>>>> Search ThaiQuest ");

				StringBuilder strCate = new StringBuilder("");

				if (StringUtils.isNotEmpty(soltionTypeProblem1)) {
					strCate.append(soltionTypeProblem1);
				}

				if (StringUtils.isNotEmpty(soltionTypeProblem2)) {
					strCate.append(",");
					strCate.append(soltionTypeProblem2);
				}

				if (StringUtils.isNotEmpty(soltionTypeProblem3)) {
					strCate.append(",");
					strCate.append(soltionTypeProblem3);
				}

				if (StringUtils.isNotEmpty(soltionTypeProblem4)) {
					strCate.append(",");
					strCate.append(soltionTypeProblem4);

				}

				if (StringUtils.isNotEmpty(soltionTypeProblem5)) {
					strCate.append(",");
					strCate.append(soltionTypeProblem5);
				}

				String strCateCri = strCate.toString();
				String strCateSearchCri = "";

				if (!StringUtils.isEmpty(strCateCri)) {

					String[] arrStrCat = strCateCri.split(",");
					strCateSearchCri = arrStrCat[arrStrCat.length - 1];

				}

				logger.info("strCateSearchCri :" + strCateSearchCri);

				SearchResult searchResult = new SearchResult();

				// check first search
				if (sEcho == 1) {

					if (request.getSession().getAttribute("searchResult") != null) {
						request.getSession().removeAttribute("searchResult");
					}

					searchResult = thaiQuestWsService.doSearch7(soltionKeyWord, strCateSearchCri);

					request.getSession().setAttribute("searchResult", searchResult);

				} else {
					searchResult = (SearchResult) request.getSession().getAttribute("searchResult");
				}

				// searchResult.getErrorCode().getValue()
				Integer totalRecords = (int) (long) searchResult.getCount();

				logger.info("## Count : " + searchResult.getCount());
				logger.info("## EstimateTotal : " + searchResult.getEstimateTotal());
				logger.info("## SearchTime : " + searchResult.getSearchTime());

				int startRec = 0;

				if (searchResult.getDocuments() != null) {

					logger.info("## Documents length : " + searchResult.getDocuments().length);
					DocumentProperty[] documentProperties = searchResult.getDocuments();

					currentPage = currentPage + 1;
					startRec = (limit * currentPage) - limit;

					for (int index = startRec; index < startRec + limit && index < totalRecords; index++) {

						srSolModelBean = new ServiceRequestSolutionModelBean();

						srSolModelBean.setSolTqId(documentProperties[index].getID());
						srSolModelBean.setSolContentNumber(documentProperties[index].getReference());
						srSolModelBean.setSolutionResult(documentProperties[index].getStory());
						
						//srSolModelBean.setSolutionResult(StringUtils.isNotEmpty(documentProperties[index].getStory()) ? documentProperties[index].getStory() : documentProperties[index].get_abstract());
						
						srSolModelBean.setSolContentFaqKbType("KB-TQ");
						srSolModelBean.setSearchFlag("TQ");
					

						// logger.info("index : "+index+" = "+documentProperties[index].getStory());

						srSolModelBeanList.add(srSolModelBean);

					}
				}

				// Set server prop return result
				datatableModelBean.setsEcho(sEcho);
				datatableModelBean.setiTotalDisplayRecords(totalRecords);
				datatableModelBean.setiTotalRecords(totalRecords);

				datatableModelBean.setAaData(srSolModelBeanList);

			} else if (solutionSearchType.equals("KB")) {

				logger.info(">>>>> Search Knowledge Base");
				ServiceResult<Page<ServiceRequestSolutionDTO>> serviceResult = serviceRequestService.searchKnowledgeBaseContentByKbNumber(pageable, contentNumber, langCd);

				logger.info("isSuccess : " + serviceResult.isSuccess());

				if (serviceResult.isSuccess()) {

					Page<ServiceRequestSolutionDTO> result = serviceResult.getResult();
					Integer totalRecords = (int) (long) result.getTotalElements();

					for (ServiceRequestSolutionDTO kbContent : result) {

						srSolModelBean = new ServiceRequestSolutionModelBean();
						srSolModelBean.setSolContentNumber(kbContent.getContentNumber());
						srSolModelBean.setSolutionResult(kbContent.getContentTitle());
						srSolModelBean.setSolContentId(kbContent.getContentId());
						srSolModelBean.setSolContentFaqKbType(kbContent.getContentType());
						
						srSolModelBean.setSolContentTitle(kbContent.getContentTitle());
						srSolModelBean.setSolContentQuestion(kbContent.getContentQuestion());
						srSolModelBean.setSolContentSummary(kbContent.getContentSummary());
						srSolModelBean.setSolContentStatusCd(kbContent.getContentStatusCd());
						srSolModelBean.setSolContentStatusName(kbContent.getContentStatusName());

						srSolModelBean.setSolContentCat1Id(kbContent.getContentCat1Id());
						srSolModelBean.setSolContentCat2Id(kbContent.getContentCat2Id());
						srSolModelBean.setSolContentCat3Id(kbContent.getContentCat3Id());
						srSolModelBean.setSolContentCat4Id(kbContent.getContentCat4Id());
						srSolModelBean.setSolContentCat5Id(kbContent.getContentCat5Id());

						srSolModelBean.setSolContentCat1Name(kbContent.getContentCat1Name());
						srSolModelBean.setSolContentCat2Name(kbContent.getContentCat2Name());
						srSolModelBean.setSolContentCat3Name(kbContent.getContentCat3Name());
						srSolModelBean.setSolContentCat4Name(kbContent.getContentCat4Name());
						srSolModelBean.setSolContentCat5Name(kbContent.getContentCat5Name());
						srSolModelBean.setSearchFlag("KB");

						srSolModelBeanList.add(srSolModelBean);

					}

					// Set server prop return result
					datatableModelBean.setsEcho(sEcho);
					datatableModelBean.setiTotalDisplayRecords(totalRecords);
					datatableModelBean.setiTotalRecords(totalRecords);
					datatableModelBean.setAaData(srSolModelBeanList);
				}

			}
		}

		return JsonUtil.toJSON(datatableModelBean, Boolean.TRUE);
	}

	@RequestMapping(value = "/inquirySrSolContentKBAtt", headers = { "Accept=application/json; charset=UTF-8" })
	public @ResponseBody
	String inquirySrSolContentKBAtt(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("ServiceRequstController - inquirySrSolContentKBAtt");
		logger.info("*****************************************************");

		String contentId = request.getParameter("contentId");
		logger.debug("content_id = " + contentId);
		List<ServiceRequestSolutionAttachmentModelBean> srSolAttListModelBean = new ArrayList<ServiceRequestSolutionAttachmentModelBean>();
		ServiceResult<List<ContentAttDTO>> serviceResult = knowledgeBaseService.getContentAttByContentId(contentId);
		ServiceRequestSolutionAttachmentModelBean srSolAttMedelBean = new ServiceRequestSolutionAttachmentModelBean();

		Integer no = 1;

		if (serviceResult.isSuccess()) {
			List<ContentAttDTO> result = serviceResult.getResult();
			for (ContentAttDTO kbAttContent : result) {

				srSolAttMedelBean = new ServiceRequestSolutionAttachmentModelBean();

				srSolAttMedelBean.setNo(no);
				srSolAttMedelBean.setTitle(kbAttContent.getTitle());
				srSolAttMedelBean.setRegBy(kbAttContent.getRegBy());
				srSolAttMedelBean.setRegDtText(kbAttContent.getRegDtText());

				srSolAttMedelBean.setAttId(String.valueOf(kbAttContent.getAttId()));
				srSolAttMedelBean.setContentAttId(String.valueOf(kbAttContent.getContentAttId()));
				srSolAttMedelBean.setContentId(kbAttContent.getContentId());
				srSolAttMedelBean.setContentTitle(kbAttContent.getContentTitle());

				srSolAttMedelBean.setFileName(kbAttContent.getFileName());
				srSolAttMedelBean.setFileType(kbAttContent.getFileType());
				srSolAttMedelBean.setFilePath(kbAttContent.getFilePath());

				no = no + 1;
				srSolAttListModelBean.add(srSolAttMedelBean);

			}
		} else {
			showWebMessage(request, serviceResult.getResponseCode(), serviceResult.getResponseDescription());
		}

		String jsonResultAttKb = JsonUtil.jsonSerialize(srSolAttListModelBean);
		logger.info("Result Json Format ::  " + jsonResultAttKb);

		return jsonResultAttKb;
	}

	@RequestMapping(value = "/insertSeriviceRequestContent", method = RequestMethod.POST, headers = { "Accept=application/json; charset=UTF-8" })
	public @ResponseBody
	String insertSeriviceRequestContent(HttpServletRequest request, HttpServletResponse response, Locale local, @RequestParam(value = "srNumber", required = true) String srNumber,
			@RequestParam(value = "srContentId", required = true) String srContentId) throws Exception {

		UserInfoDTO userInfo = getUserInfo(request);
		Pageable pageable = getPagableFromRequest(request);
		String langCd = getLanguageCode(local);

		ServiceRequestContentDTO srContentDTO = new ServiceRequestContentDTO();
		JsonResultBean result = new JsonResultBean();

		srContentDTO.setSrNumber(srNumber);
		srContentDTO.setSrContentId(srContentId);
		srContentDTO.setRegId(userInfo.getUserId());
		srContentDTO.setRegDt(new Date());

		try {

			ServiceResult<Long> serviceResultContent = serviceRequestService.insertServiceRequestContent(srContentDTO);

			ServiceRequestContentModelBean srContentModelBean = new ServiceRequestContentModelBean();

			if (serviceResultContent.isSuccess()) {

				SUCCESS = "True";
				MESSAGE = messageSource.getMessage("lbl.action.save.success", null, local);
				MESSAGE_CODE = JLOWebConstant.INSERT_SUCCESS_CODE;
				srContentModelBean.setSrNumber(srNumber);

				result.setResultCode(MESSAGE_CODE);
				result.setResultMessage(MESSAGE);
				result.setModel(srContentModelBean);

			} else {
				String resDetail = serviceResultContent.getResponseDescription();

				// Can not insert TP_SR_CONTENT
				SUCCESS = "False";
				MESSAGE = messageSource.getMessage("lbl.action.save.fail", null, local);
				MESSAGE_CODE = JLOWebConstant.INSERT_FAIL_CODE;

				result.setResultCode(MESSAGE_CODE);
				result.setResultMessage(MESSAGE + " Can not insert table TP_SR_CONTENT \n " + resDetail);
				result.setModel(srContentModelBean);
			}

		} catch (Exception ex) {

			logger.error(ex.getMessage());

		}

		return JsonUtil.toJSON(result, Boolean.TRUE);

	}

	@RequestMapping(value = "/inquirySRContentList", method = RequestMethod.POST, headers = { "Accept=application/json" })
	public @ResponseBody
	String inquirySRContentList(HttpServletRequest request, HttpServletResponse response, Locale locale, @RequestParam(value = "srNumber", required = true) String srNumber) throws Exception {

		String sEchoTxt = request.getParameter("sEcho");
		Integer sEcho = (!StringUtils.isNullOrEmpty(sEchoTxt)) ? Integer.valueOf(sEchoTxt) : 0;

		Pageable pageable = getPagableFromRequest(request);
		String langCd = getLanguageCode(locale);

		DatatableModelBean datatableModelBean = new DatatableModelBean();
		List<ServiceRequestContentModelBean> srContentList = new ArrayList<ServiceRequestContentModelBean>();
		ServiceResult<Page<ServiceRequestContentDTO>> serviceResult = serviceRequestService.searchContentServiceRequestByRefDocNo(pageable, srNumber, langCd);

		List<String> ownerFilter = getOwnerGroup(request, ActionType.DELETE.getActionCode());
		System.out.println("ownerFilter : " + ownerFilter);

		if (serviceResult.isSuccess()) {

			Page<ServiceRequestContentDTO> result = serviceResult.getResult();
			Integer totalRecords = (int) (long) result.getTotalElements();
			ServiceRequestContentModelBean srContentModelBean = new ServiceRequestContentModelBean();

			for (ServiceRequestContentDTO srContent : result) {
				srContentModelBean = new ServiceRequestContentModelBean();

				srContentModelBean.setSrContentNumber(srContent.getSrContentNumber());
				srContentModelBean.setContentCat1Name(srContent.getContentCat1Name());
				srContentModelBean.setContentTitle(srContent.getContentTitle());
				srContentModelBean.setContentFaqKbType(srContent.getContentFaqKbType());
				
				srContentModelBean.setSrNumber(srContent.getSrNumber());
				srContentModelBean.setSrContentId(srContent.getSrContentId());
				srContentModelBean.setContentCat1Id(srContent.getContentCat1Id());

				srContentModelBean.setCreateByName(srContent.getCreateByName());
				srContentModelBean.setUpdateByName(srContent.getUpdateByName());
				srContentModelBean.setRegId(srContent.getRegId());
				srContentModelBean.setRegDt(srContent.getRegDt());
				srContentModelBean.setChgId(srContent.getChgId());
				srContentModelBean.setChgDt(srContent.getChgDt());

				if (ownerFilter != null) {

					if (!StringUtils.isEmpty(String.valueOf(srContent.getRegId()))) {
						if (checkVisibility(request, ActionType.DELETE.getActionCode(), Integer.valueOf(srContent.getRegId()))) {
							srContentModelBean.setDeleteUrl("DELETE");
						} else {
							srContentModelBean.setDeleteUrl("");
						}

					} else {
						srContentModelBean.setDeleteUrl("");
					}
				}

				srContentList.add(srContentModelBean);
			}

			// Set server prop return result
			datatableModelBean.setsEcho(sEcho);
			datatableModelBean.setiTotalDisplayRecords(totalRecords);
			datatableModelBean.setiTotalRecords(totalRecords);
			datatableModelBean.setAaData(srContentList);

		} else {

			showWebMessage(request, serviceResult.getResponseCode(), serviceResult.getResponseDescription());
		}

		return JsonUtil.toJSON(datatableModelBean, Boolean.TRUE);

	}

	@RequestMapping(value = "/deleteSrContentByKeyRef", method = RequestMethod.POST, headers = { "Accept=application/json; charset=UTF-8" })
	public @ResponseBody
	String deleteSrContentByKeyRef(HttpServletRequest request, HttpServletResponse response, Locale locale, @RequestParam(value = "srNumber", required = true) String srNumber,
			@RequestParam(value = "srContentId", required = true) String srContentId) throws Exception {

		ServiceRequestContentModelBean resultServiceReqContentModel = new ServiceRequestContentModelBean();
		JsonResultBean result = new JsonResultBean();

		ServiceResult<Long> serviceResult = serviceRequestService.deleteSrContentByKeyRef(srNumber, srContentId);
		Long deleteSR = serviceResult.getResult();
		logger.info("ResultCode DeleteSContentByKeyRef : " + deleteSR);

		if (serviceResult.isSuccess() && deleteSR > 0) {

			SUCCESS = "True";
			MESSAGE = messageSource.getMessage("lbl.delete.success", null, locale);
			MESSAGE_CODE = JLOWebConstant.INSERT_SUCCESS_CODE;
			result.setResultCode(MESSAGE_CODE);
			result.setResultMessage(MESSAGE);
			result.setModel(resultServiceReqContentModel);

		} else {

			SUCCESS = "False";
			MESSAGE = messageSource.getMessage("lbl.delete.fail", null, locale);
			MESSAGE_CODE = JLOWebConstant.INSERT_FAIL_CODE;
			result.setModel(resultServiceReqContentModel);
			result.setResultCode(MESSAGE_CODE);
			result.setResultMessage(MESSAGE);

		}

		return JsonUtil.toJSON(result, Boolean.TRUE);

	}

	@RequestMapping(value = "/getSlaByContentCatId", headers = { "Accept=application/json" })
	public @ResponseBody
	String getSlaByContentCatId(HttpServletRequest request, Pageable pageable, Locale locale) {
		logger.info("SLAManagement - getSlaById");
		logger.info("*******************************************");

		String contentCatId = request.getParameter("contentCatId");
		logger.info("Criteria :" + contentCatId);

		JsonResultBean result = new JsonResultBean();
		ServiceResult<SlaDTO> serviceResult = slaService.seachByContentCatId(contentCatId);
		if (serviceResult.isSuccess()) {
			result.setResultCode(JLOWebConstant.SUCCESS_CODE);
			result.setResultMessage(JLOWebConstant.SUCCESS_DESC);
			result.setModel(serviceResult.getResult());
		} else {
			result.setResultCode(serviceResult.getResponseCode());
			result.setResultMessage(serviceResult.getResponseDescription());
		}

		return JsonUtil.toJSON(result, true);
	}
}
