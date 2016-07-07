package com.locus.jlo.web.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.locus.common.domain.ServiceResult;
import com.locus.common.utils.CollectionUtil;
import com.locus.common.utils.DateTimeUtils;
import com.locus.common.utils.JsonUtil;
import com.locus.jlo.web.bean.dto.JsonResultDTO;
import com.locus.jlo.web.bean.dto.PreferenceDTO;
import com.locus.jlo.web.bean.modelbean.PreferenceModelBean;
import com.locus.jlo.web.bean.modeljson.DatatableModelBean;
import com.locus.jlo.web.constant.JLOWebConstant;
import com.locus.jlo.web.service.PreferenceService;

@Controller
public class PreferenceManagementController extends BaseController {
	private Logger logger = Logger.getLogger(getClass());
	public static final String PREF_MODEL_BEAN = "prefModelBean";

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private PreferenceService preferenceService;
	
	private String MESSAGE = "";
	private String MESSAGE_CODE = "";
	
	@RequestMapping(value = "/preferenceManagement",  method = RequestMethod.POST)
	public ModelAndView preferenceManagement(Model model, HttpServletRequest request, Locale local) {
		
		model.addAttribute("pageTitle", messageSource.getMessage("preference.title", null, local));
		logger.info("PreferencManagementController - preferenceManagement");
		logger.info("*******************************************");
		
		setMenuId(request);

		return new ModelAndView("preferenceManagement", PREF_MODEL_BEAN, new PreferenceModelBean());
	}

	@RequestMapping(value = "/getPreferenceList", headers = { "Accept=application/json" }, method = RequestMethod.POST)
	public @ResponseBody String getPreferenceList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		logger.info("PreferencManagementController - getPreferenceList");
		logger.info("**********************************************************");
		String prefName = ("".equals(request.getParameter("prefNameCriteria"))) ? null : request.getParameter("prefNameCriteria");
		String prefValue = ("".equals(request.getParameter("prefValueCriteria"))) ? null : request.getParameter("prefValueCriteria");
		logger.info("prefName : " + prefName + ", prefValue : " + prefValue);

		Pageable pageable = getPagableFromRequest(request);

		DatatableModelBean datatableModelBean = new DatatableModelBean();

		ServiceResult<Page<PreferenceDTO>> serviceResult = preferenceService.searchByCriteria(prefName, prefValue, pageable);
		
		if (serviceResult.isSuccess()) {
			Page<PreferenceDTO> prefResult = serviceResult.getResult();
			Integer totalRecords = (int) (long) prefResult.getTotalElements();
			
			List<PreferenceDTO> preferList = prefResult.getContent();
			List<PreferenceModelBean> preferBeanList = new ArrayList<PreferenceModelBean>();
			PreferenceModelBean preferenceModelBean = null;
			if (CollectionUtil.isNotEmpty(preferList)) {
				for (PreferenceDTO preferenceDTO : preferList) {
					preferenceModelBean = new PreferenceModelBean();
					setDto2Bean(preferenceDTO, preferenceModelBean);
					preferBeanList.add(preferenceModelBean);
				}
			}
			
			// Set server prop return result
			datatableModelBean.setsEcho(getSecho(request));
			datatableModelBean.setiTotalDisplayRecords(totalRecords);
			datatableModelBean.setiTotalRecords(totalRecords);
			datatableModelBean.setAaData(preferBeanList);
			
		} else {
			showWebMessage(request, serviceResult.getResponseCode(), serviceResult.getResponseDescription());
		}
		
		return JsonUtil.toJSON(datatableModelBean, Boolean.TRUE);
	}
	
	private void setDto2Bean(PreferenceDTO dto, PreferenceModelBean bean) {
		bean.setDescp(dto.getDescp());
		bean.setEtc1(dto.getEtc1());
		bean.setEtc2(dto.getEtc2());
		bean.setEtc3(dto.getEtc3());
		bean.setIdx(dto.getPrefId());
		bean.setPrefId(dto.getPrefId());
		bean.setPrefName(dto.getPrefName());
		bean.setPrefValue(dto.getPrefValue());
		bean.setEnabledFlag(dto.getEnabledFlag());
		bean.setCreateBy(dto.getCreateBy());
		bean.setCreateDate(DateTimeUtils.formatDateTime(dto.getRegDt()));
		bean.setUpdateBy(dto.getUpdateBy());
		bean.setUpdateDate(DateTimeUtils.formatDateTime(dto.getChgDt()));
	}
	
	@RequestMapping(value = "/getPreferenceDetail", headers = { "Accept=application/json" }, method = RequestMethod.GET)
	public @ResponseBody String getPreferenceDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("PreferencManagementController - getPreferenceDetail");
		logger.info("**********************************************************");
		PreferenceModelBean preferenceModelBean = new PreferenceModelBean();
		String prefId = request.getParameter("prefId");
		logger.info("prefId : " + prefId);
		
		ServiceResult<PreferenceDTO> serviceResult = preferenceService.searchById(Integer.valueOf(prefId));

		if(serviceResult.isSuccess()) {
			PreferenceDTO tpPref = serviceResult.getResult();
			
			// Set entity to bean
			if(tpPref != null) {
				preferenceModelBean.setPrefId(tpPref.getPrefId());
				preferenceModelBean.setPrefName(tpPref.getPrefName());
				preferenceModelBean.setPrefValue(tpPref.getPrefValue());
				preferenceModelBean.setEtc1(tpPref.getEtc1());
				preferenceModelBean.setEtc2(tpPref.getEtc2());
				preferenceModelBean.setEtc3(tpPref.getEtc3());
				preferenceModelBean.setDescp(tpPref.getDescp());
				preferenceModelBean.setEnabledFlag(tpPref.getEnabledFlag());
				preferenceModelBean.setCreateBy(null);
				preferenceModelBean.setCreateDate(DateTimeUtils.formatDateTime(tpPref.getRegDt()));
				preferenceModelBean.setUpdateBy(null);
				preferenceModelBean.setUpdateDate(DateTimeUtils.formatDateTime(tpPref.getChgDt()));
			}
		}
		return JsonUtil.toJSON( preferenceModelBean, Boolean.TRUE );
	}
	
	private void clearData(PreferenceModelBean preferenceModelBean) {
		
		preferenceModelBean.setPrefId(null);
		preferenceModelBean.setPrefName("");
		preferenceModelBean.setPrefValue("");
		preferenceModelBean.setEtc1("");
		preferenceModelBean.setEtc2("");
		preferenceModelBean.setEtc3("");
		preferenceModelBean.setDescp("");
		preferenceModelBean.setEnabledFlag("Y");
		preferenceModelBean.setCreateBy("");
		preferenceModelBean.setCreateDate("");
		preferenceModelBean.setUpdateBy("");
		preferenceModelBean.setUpdateDate("");
	}

	@RequestMapping(value = {"/insertPreference","/updatePreference"},method = RequestMethod.POST, headers = { "Accept=application/json; charset=UTF-8" })
	public @ResponseBody String updatePreference(HttpServletRequest request, HttpServletResponse response,Locale local,
			@ModelAttribute("PREF_MODEL_BEAN") PreferenceModelBean preferenceModelBean)throws Exception {
		
		PreferenceModelBean prefBean = new PreferenceModelBean();
		JsonResultDTO result = new JsonResultDTO();
		PreferenceDTO tpPref = null;
		
		Integer prefId = preferenceModelBean.getPrefId();
		
		if(prefId == null){
			
			logger.info("*************insertPreference*************");
			
			tpPref = new PreferenceDTO();
			tpPref.setRegId(getUserId(request));
			
		} else {
			logger.info("*************updatePreference*************");
			
			ServiceResult<PreferenceDTO> serviceResult = preferenceService.searchById(prefId);
			
			if (serviceResult.isSuccess()) {
				
				tpPref = serviceResult.getResult();
				tpPref.setChgId(getUserId(request));
			}
		}
		
		//set data
		tpPref.setPrefName(preferenceModelBean.getPrefName());
		tpPref.setPrefValue(preferenceModelBean.getPrefValue());
		tpPref.setEtc1(preferenceModelBean.getEtc1());
		tpPref.setEtc2(preferenceModelBean.getEtc2());
		tpPref.setEtc3(preferenceModelBean.getEtc3());
		tpPref.setDescp(preferenceModelBean.getDescp());
		tpPref.setEnabledFlag(preferenceModelBean.getEnabledFlag());
		
		// Call menu service for save
		ServiceResult<PreferenceDTO> saveResult = preferenceService.save(tpPref);
		if (saveResult.isSuccess()) {
			MESSAGE_CODE = JLOWebConstant.SUCCESS_CODE;
			MESSAGE = messageSource.getMessage("lbl.action.save.success", null, local);
			showWebMessage(request, JLOWebConstant.SUCCESS_CODE, messageSource.getMessage("lbl.action.save.success", null, local), messageSource.getMessage("preferenceManagement.detail.topic", null, local));
		
		} else {
			MESSAGE_CODE = JLOWebConstant.FAIL_CODE;
			MESSAGE = messageSource.getMessage("lbl.action.save.fail", null, local);
			showWebMessage(request, JLOWebConstant.FAIL_CODE, messageSource.getMessage("lbl.action.save.fail", null, local), messageSource.getMessage("preferenceManagement.detail.topic", null, local));
		
		}
			
		PreferenceDTO tpPrefResult = new PreferenceDTO();
		ServiceResult<PreferenceDTO> resultPref = preferenceService.searchById(tpPref.getPrefId());
		
		if (resultPref.isSuccess()) {
			tpPrefResult = resultPref.getResult();
		}
				
		clearData(preferenceModelBean);
		prefBean.setPrefName(tpPrefResult.getPrefName());
		prefBean.setPrefValue(tpPrefResult.getPrefValue());
		prefBean.setEtc1(tpPrefResult.getEtc1());
		prefBean.setEtc2(tpPrefResult.getEtc2());
		prefBean.setEtc3(tpPrefResult.getEtc3());
		prefBean.setDescp(tpPrefResult.getDescp());
		prefBean.setEnabledFlag(tpPrefResult.getEnabledFlag());
		prefBean.setPrefId(tpPrefResult.getPrefId());
		prefBean.setCreateBy(tpPrefResult.getCreateBy());
		prefBean.setCreateDate(DateTimeUtils.formatDateTime(tpPrefResult.getRegDt()));
		prefBean.setUpdateBy(tpPrefResult.getUpdateBy());
		prefBean.setUpdateDate(DateTimeUtils.formatDateTime(tpPrefResult.getChgDt()));
		prefBean.setMode(JLOWebConstant.MODE_UPDATE);
		
		result.setResultCode(MESSAGE_CODE);
		result.setResultMessage(MESSAGE);
		result.setModel(prefBean);
				
		return JsonUtil.toJSON(result, Boolean.TRUE);
	}
}
