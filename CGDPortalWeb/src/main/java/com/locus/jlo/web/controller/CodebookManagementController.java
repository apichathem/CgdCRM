package com.locus.jlo.web.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.locus.common.utils.CustomPropertyPlaceholderConfigurer;
import com.locus.common.utils.JsonUtil;
import com.locus.jlo.web.bean.dto.JsonResultDTO;
import com.locus.jlo.web.bean.modelbean.CodebookModelBean;
import com.locus.jlo.web.bean.modelbean.Select2DataModelBean;
import com.locus.jlo.web.bean.modeljson.DatatableModelBean;
import com.locus.jlo.web.constant.JLOWebConstant;
import com.locus.jlo.web.service.CodebookService;
import com.locus.jlo.web.util.CodeBookHelper;

@Controller
public class CodebookManagementController extends BaseController {

	@Autowired
	private CodebookService codebookService;

	
	private Logger logger = Logger.getLogger(getClass());
	private static final String CODEBOOK_MODEL = "codebookModelBean";
	public static final String SESS_RESULT_LIST1 = "parentList";
	public static final String SESS_CODEBOOK_LIST = "codebookAllList";
	public static final String SESS_LANG_CD = "languageCode";
	public static final String SESS_LANG_NM = "languageName";
	
	private void clearSession(HttpServletRequest request) {
		setSessionAttr(request, SESS_RESULT_LIST1, null);
		setSessionAttr(request, SESS_CODEBOOK_LIST, null);
		setSessionAttr(request, SESS_LANG_CD, null);
		setSessionAttr(request, SESS_LANG_NM, null);
	}
	
	
	
	@RequestMapping(value = "/getCodebookList", headers = { "Accept=application/json" })
	public @ResponseBody String getCodebookList(HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception {
		logger.info("CodebookManagementController - getCodebookList");
		logger.info("*****************************************************");
		String roleName = request.getParameter("roleName");
		logger.info("roleName : " + roleName);
		
		String codeName = request.getParameter("codeName");
		String codeType = request.getParameter("codeType");
		String parentType = request.getParameter("parentType");
		
		Pageable pageable = getPagableFromRequest(request);
		
		DatatableModelBean datatableModelBean = new DatatableModelBean();
		
		ServiceResult<Page<CodebookModelBean>> serviceResult = codebookService.searchByCriteria(codeType, codeName, getLanguageCode(locale), parentType, pageable);
		
		if (serviceResult.isSuccess()) {
			Page<CodebookModelBean> tpRoleResult = serviceResult.getResult();
			Integer totalRecords = (int) (long)tpRoleResult.getTotalElements();
			
			// Set server prop return result
			datatableModelBean.setsEcho(getSecho(request));
			datatableModelBean.setiTotalDisplayRecords(totalRecords);
			datatableModelBean.setiTotalRecords(totalRecords);
			datatableModelBean.setAaData(tpRoleResult.getContent());
		} else {
			showWebMessage(request, serviceResult.getResponseCode(), serviceResult.getResponseDescription(), messageSource.getMessage("codebookManage.topic", null, locale));
		}
		
		return JsonUtil.toJSON( datatableModelBean , true );
	}
	
	@RequestMapping(value = "/getCodebookDetail", headers = { "Accept=application/json" }, method = RequestMethod.POST)
	public @ResponseBody String getCodebookDetail(HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception {
		logger.info("CodebookManagementController - getCodebookDetail");
		logger.info("*****************************************************");
		CodebookModelBean codebookModelBean = new CodebookModelBean();
		String codeType = request.getParameter("codeType");
		String codeId = request.getParameter("codeId");
		
		logger.info("codeType : [" + codeType + "] codeId [" + codeId + "]");

		ServiceResult<CodebookModelBean> serviceResult = codebookService.searchById(codeType, codeId);
		if (serviceResult.isSuccess()) {
			codebookModelBean = serviceResult.getResult();

			// Get Multiple language codebook
			ServiceResult<List<CodebookModelBean>> langServiceResult = codebookService.searchLangById(codeType, codeId);
			if (langServiceResult.isSuccess()) {
				List<CodebookModelBean> codebookLangsList = langServiceResult.getResult();
				
				if (CollectionUtil.isNotEmpty(codebookLangsList)) {
					int size = codebookLangsList.size();
					String[] codenameByLang = new String[size];
					String[] languageCode = new String[size];
					
					for (int i = 0; i < size; i++) {
						CodebookModelBean tpCodebookLang = codebookLangsList.get(i);
						codenameByLang[i] = tpCodebookLang.getCodeName();
						languageCode[i] = tpCodebookLang.getLangCd();
					}
					
					codebookModelBean.setCodenameByLang(codenameByLang);
					codebookModelBean.setLanguageCode(languageCode);
				}
			}
		}
		
		return JsonUtil.toJSON( codebookModelBean, true );
	}
	
	
	@RequestMapping(value = {"/insertCodebookDetail", "/updateCodebookDetail"}, headers = { "Accept=application/json" }, method = RequestMethod.POST)
	public @ResponseBody String insertOrUpdateCodebookDetail(@ModelAttribute("codebookModelBean") CodebookModelBean codebookModelBean, HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception {
		logger.info("insertOrUpdateCodebookDetail [" + request.getParameter("mode") + "]");
		return saveCodebookDetail(codebookModelBean,request, response, locale);
	}
	
	public @ResponseBody String saveCodebookDetail(CodebookModelBean codebookModelBean, HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception {
		
		JsonResultDTO jsonResultBean = new JsonResultDTO();
		//String detailTopicTitle = messageSource.getMessage("codebookManage.detail.topic", null, locale);
//		CodebookModelBean codebookModelBean = new CodebookModelBean();
		
		// Set codebook model bean
//		codebookModelBean.setParentCodeId(request.getParameter("parentCodeId"));
//		codebookModelBean.setParentCodeType(request.getParameter("parentCodeType"));
//		codebookModelBean.setParentCodeName(request.getParameter("parentCodeName"));
//		codebookModelBean.setChildCodeId(request.getParameter("childCodeId"));
//		codebookModelBean.setChildCodeName(request.getParameter("childCodeName"));
//		codebookModelBean.setChildCodeType(request.getParameter("childCodeType"));
//		
//		codebookModelBean.setEtc1(request.getParameter("etc1"));
//		codebookModelBean.setEtc2(request.getParameter("etc2"));
//		codebookModelBean.setEtc3(request.getParameter("etc3"));
//		codebookModelBean.setEtc4(request.getParameter("etc4"));
//		codebookModelBean.setEtc5(request.getParameter("etc5"));
//		codebookModelBean.setUseYn(request.getParameter("useYn"));
//		codebookModelBean.setChannel(request.getParameter("channel"));
//		codebookModelBean.setMode(request.getParameter("mode"));
		logger.debug("CodeType="+codebookModelBean.getCodeType());
		codebookModelBean.setLanguageCode(request.getParameterValues("languageCode"));
		codebookModelBean.setCodenameByLang(request.getParameterValues("codenameByLang"));
		
		logger.debug("user id:"+getUserInfo(request).getUserId());
		if (codebookModelBean.isInsertMode()) {
			logger.info("insert codebook mode");
			codebookModelBean.setCreateBy(getUserInfo(request).getUserId().toString());
			ServiceResult<Boolean> res = codebookService.insert(codebookModelBean);
			if (res.isSuccess()) { 
				jsonResultBean.setResultCode("0");
			} else {
				jsonResultBean.setResultCode(res.getResponseCode());
				jsonResultBean.setResultMessage(res.getResponseDescription());
			}
			
		} else if (codebookModelBean.isUpdateMode()) {
			logger.info("update codebook mode");
			codebookModelBean.setUpdateBy(getUserInfo(request).getUserId().toString());
			codebookService.update(codebookModelBean);
			jsonResultBean.setResultCode("0");
			
		}
		return JsonUtil.toJSON( jsonResultBean, true );
	}
	
	
	
	
	

	@RequestMapping(value = "/searchCodebookDetail")
	public ModelAndView searchCodebookDetail(Model model, HttpServletRequest request, Locale locale,
			@ModelAttribute("codebookModelBean") CodebookModelBean codebookModelBean, Pageable pageable) {
		logger.info("CodebookManagementController - searchCodebookDetail");
		logger.info("*******************************************");
		logger.info("Param : [" + codebookModelBean.getSelectedCodeType() + "] [" + codebookModelBean.getSelectedCodeId() + "]");

		ServiceResult<CodebookModelBean> serviceResult = codebookService.searchById(codebookModelBean.getSelectedCodeType(),
				codebookModelBean.getSelectedCodeId());
		if (serviceResult.isSuccess()) {
			codebookModelBean = serviceResult.getResult();

		} else {
			showWebMessage(request, serviceResult.getResponseCode(), serviceResult.getResponseDescription(), messageSource.getMessage("codebookManage.detail.topic", null, locale));
		}

		return new ModelAndView("codebookManagement", CODEBOOK_MODEL, codebookModelBean);
	}
	
	
	
	

	@RequestMapping(value = "/getCodebookListDialog", headers = { "Accept=application/json" })
	public @ResponseBody String getCodebookListDialog(HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception {
		
		logger.info("CodebookManagementController - getCodebookListDialog");
		logger.info("*****************************************************");
		
		Pageable pageable = getPagableFromRequest(request);
		String codeType = request.getParameter("codeTypeDialog");
		String codeName = request.getParameter("codeNameDialog");
		String parentCodeType = request.getParameter("parentCodeTypeDialog");
		
		DatatableModelBean datatableModelBean = new DatatableModelBean();
		
		ServiceResult<Page<CodebookModelBean>> result = codebookService.searchByCriteria(codeType, codeName, getLanguageCode(locale), parentCodeType, pageable);
		if (result.isSuccess()) {
			Page<CodebookModelBean> codebookList = result.getResult();
			Integer totalRecords = (int) (long) codebookList.getTotalElements();
			
			// Set server prop return result
			datatableModelBean.setsEcho(getSecho(request));
			datatableModelBean.setiTotalDisplayRecords(totalRecords);
			datatableModelBean.setiTotalRecords(totalRecords);
			datatableModelBean.setAaData(codebookList.getContent());
		}
		
		return JsonUtil.toJSON( datatableModelBean , true );
	}

	@RequestMapping(value = "/codebookManagement")
	public ModelAndView codebookManagement(Model model, HttpServletRequest request, Locale local) {

		model.addAttribute("pageTitle", messageSource.getMessage("codebookManagement.title", null, local));
		logger.info("CodebookManagementController - codebookManagement");
		logger.info("*******************************************");
		
		setMenuId(request);
		
		CodebookModelBean codebookModelBean = new CodebookModelBean();
		codebookModelBean.setUseYn("Y");
		codebookModelBean.setChannel("W");
		codebookModelBean.setMode(JLOWebConstant.MODE_INSERT);

		// Clear session
		clearSession(request);
		
		// Default
		codebookModelBean.setParentId(JLOWebConstant.CODEBOOK_ROOT_ID);
		codebookModelBean.setParentName(JLOWebConstant.CODEBOOK_ROOT_NAME);
		codebookModelBean.setParentType(JLOWebConstant.CODEBOOK_ROOT_TYPE);
		
		// Load language properties
		String languageCode = CustomPropertyPlaceholderConfigurer.getProperty("language.code");
		String languageName = CustomPropertyPlaceholderConfigurer.getProperty("language.name");
		
		String[] languageCodes = languageCode.split(",");
		String[] languageNames = languageName.split(",");
		
		// Debug value
		for (String code :languageCodes) {logger.info("language.code : " + code);}
		
		List<String> languageCodeList = new ArrayList<String>(Arrays.asList(languageCodes));
		List<String> languageNameList = new ArrayList<String>(Arrays.asList(languageNames));
		setSessionAttr(request, SESS_LANG_CD, languageCodeList);
		setSessionAttr(request, SESS_LANG_NM, languageNameList);
		
		return new ModelAndView("codebookManagement", CODEBOOK_MODEL, codebookModelBean);
	}
	
	
	
	
	
	
	
	
	@RequestMapping(value = "/getCodebookComboWithDisable", headers = { "Accept=application/json" }, method = RequestMethod.GET)
	public @ResponseBody String getCodebookComboWithDisable(HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception {
		logger.info("CodebookManagementController - getCodebookComboWithDisable");
		logger.info("*****************************************************");
		
		String codeType = request.getParameter("codeType");
		logger.info("CodebookManagementController codeType : " + codeType);
		List<Select2DataModelBean> selectList = new ArrayList<Select2DataModelBean>();
		Select2DataModelBean select2 = null;
		
		ServiceResult<List<CodebookModelBean>> serviceRes = codebookService.searchByCodeTypeWithDisable(codeType, getLanguageCode(locale));
		if (serviceRes.isSuccess()) {
			List<CodebookModelBean> codebookList = serviceRes.getResult();
			if (codebookList != null && !codebookList.isEmpty()) {
				for (CodebookModelBean tpCodebook : codebookList) {
					select2 = new Select2DataModelBean();
					select2.setId(tpCodebook.getCodeId());
					select2.setText(tpCodebook.getCodeName());
					
					if (!"Y".equals(tpCodebook.getUseYn())) {
						select2.setDisabled(Boolean.TRUE);
					} else {
						select2.setDisabled(Boolean.FALSE);
					}
					
					selectList.add(select2);
				}
			}
		}
		
		return JsonUtil.toJSON( selectList, true );
	}

	@RequestMapping(value = "/getCodebookCombo", headers = { "Accept=application/json" }, method = RequestMethod.GET)
	public @ResponseBody String getCodebookCombo(HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception {
		logger.info("CodebookManagementController - getCodebookCombo");
		logger.info("*****************************************************");
		
		String codeType = request.getParameter("codeType");
		logger.info("CodebookManagementController codeType : " + codeType);
		List<Select2DataModelBean> selectList = new ArrayList<Select2DataModelBean>();
		Select2DataModelBean select2 = null;
		
		ServiceResult<List<CodebookModelBean>> serviceRes = codebookService.searchByCodeType(codeType, getLanguageCode(locale));
		if (serviceRes.isSuccess()) {
			List<CodebookModelBean> codebookList = serviceRes.getResult();
			if (codebookList != null && !codebookList.isEmpty()) {
				for (CodebookModelBean tpCodebook : codebookList) {
					select2 = new Select2DataModelBean();
					select2.setId(tpCodebook.getCodeId());
					select2.setText(tpCodebook.getCodeName());
					
					selectList.add(select2);
				}
			}
		}
		
		return JsonUtil.toJSON( selectList, true );
	}
	
	@RequestMapping(value = "/cleanCacheCodebook", headers = { "Accept=application/json" })
	public @ResponseBody String cleanCacheCodebook(HttpServletRequest request) throws Exception {
		logger.info("CodebookManagementController - cleanCacheCodebook");
		logger.info("*****************************************************");
		CodeBookHelper.resetCodeBook();
		return JsonUtil.toJSON( "OK", true );		
	}
}
