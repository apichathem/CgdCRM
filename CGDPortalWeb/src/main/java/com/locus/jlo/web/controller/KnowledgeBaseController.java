package com.locus.jlo.web.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import com.locus.common.utils.CollectionUtil;
import com.locus.common.utils.DateTimeUtils;
import com.locus.common.utils.FileUtil;
import com.locus.common.utils.JsonUtil;
import com.locus.common.utils.StringUtils;
import com.locus.jlo.web.bean.criteria.ContentDetailCriteria;
import com.locus.jlo.web.bean.criteria.ExportKnowledgeBaseCriteria;
import com.locus.jlo.web.bean.dto.AttDTO;
import com.locus.jlo.web.bean.dto.ContentAttDTO;
import com.locus.jlo.web.bean.dto.ContentCategoryDTO;
import com.locus.jlo.web.bean.dto.ContentDetailDTO;
import com.locus.jlo.web.bean.dto.SlaDTO;
import com.locus.jlo.web.bean.modelbean.ContentDetailModelBean;
import com.locus.jlo.web.bean.modelbean.ImportKnowledgeBaseModelBean;
import com.locus.jlo.web.bean.modelbean.KnowledgeBaseAttModelBean;
import com.locus.jlo.web.bean.modelbean.Select2DataModelBean;
import com.locus.jlo.web.bean.modelbean.ValidateResultBean;
import com.locus.jlo.web.bean.modeljson.DatatableModelBean;
import com.locus.jlo.web.bean.modeljson.JsonResultBean;
import com.locus.jlo.web.bean.modeljson.TreeModelBean;
import com.locus.jlo.web.constant.ActionType;
import com.locus.jlo.web.constant.FileSizeType;
import com.locus.jlo.web.constant.JLOWebConstant;
import com.locus.jlo.web.constant.SequenceType;
import com.locus.jlo.web.service.AttService;
import com.locus.jlo.web.service.ContentService;
import com.locus.jlo.web.service.DropdownService;
import com.locus.jlo.web.service.KnowledgeBaseService;
import com.locus.jlo.web.service.SequenceGeneratorService;
import com.locus.jlo.web.service.SlaService;
import com.locus.jlo.web.service.ThaiQuestWsService;
import com.opencsv.CSVReader;
import com.thaiquest.ws.inject.AttachmentEncoding;
import com.thaiquest.ws.inject.AttachmentProperty;
import com.thaiquest.ws.inject.DocumentProperty;
import com.thaiquest.ws.inject.InjectResult;

@Controller
public class KnowledgeBaseController extends BaseController {
	@Value(value = "${home.path}")
	private String homePath;

	@Autowired
	ServletContext context;
	
	@Autowired
	private KnowledgeBaseService knowledgeBaseService;
	
	@Autowired
	private DropdownService dropdownService;
	
	@Autowired
	private SlaService slaService;
	
	@Autowired
	private ThaiQuestWsService thaiQuestWsService;
	
	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;
	
	@Autowired
	private ContentService contentService;
	
	@Autowired
	private AttService attService;
	
	private Logger logger = Logger.getLogger(getClass());
	
	private static final String CATEGORY_MODEL = "categoryModel";
	private static final String CATEGORY_CRITERIA_MODEL = "categoryCriteriaModel";
	private static final String CATEGORY_INFO_MODEL = "categoryInfoModel";
	private static final String CATEGORY_ATTACH_MODEL = "knowledgeBaseAttModelBean";
	private static final String CONTENT_STATUS_WAIT_FOR_APPROVE = "02";
	private static final String CONTENT_STATUS_APPROVED = "03";
	private static final String CONTENT_STATUS_REJECTED = "04";
	private static final String CONTENT_STATUS_INACTIVE = "05";
	private static final String CONTENT_TYPE_KB = "KB";
	private static final String CONTENT_TYPE_FAQ = "FAQ";
	private static final String CONTENT_TYPE_GEN = "GEN";
	
	
	@RequestMapping(value = "/knowledgeBase")
	public ModelAndView knowledgeBase(Model model, HttpServletRequest request, Locale local,
			HttpServletResponse response) {

		logger.info("KnowledgeBaseController - knowledgeBase : ");
		logger.info("*****************************************************");
		
		setMenuId(request);
		model.addAttribute("pageTitle", messageSource.getMessage("knowledgeBase.title", null, local));
		
		// Get SLA combo
		Map<Integer, String> slaSelect = new HashMap<Integer, String>();
		ServiceResult<Map<Integer, String>> slaServiceResult = dropdownService.getSlaSelect();
		if (slaServiceResult.isSuccess()) {
			slaSelect = slaServiceResult.getResult();
		}
		
		model.addAttribute("slaSelect", slaSelect);
		return new ModelAndView("knowledgeBase", CATEGORY_MODEL, new ContentCategoryDTO());
	}
	
	@RequestMapping(value = "/searchCategoryTree", headers = { "Accept=application/json" })
	public @ResponseBody String searchCategoryTree(HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception {
		logger.info("KnowledgeBaseController - searchCategoryTree : ");
		logger.info("*****************************************************");
		
		String cateTypeCd = request.getParameter("cateTypeCd");
//		String cateStatusCd = request.getParameter("cateStatusCd");
		String parentId = request.getParameter("parent");
		Integer level = Integer.valueOf(request.getParameter("level"));
		
		logger.info("cateTypeCd : " + cateTypeCd + " ,parent :" + parentId + " ,level :" + level);
		
		List<TreeModelBean> categoryTree = new ArrayList<TreeModelBean>();
		TreeModelBean treeModelBean = null;
		
		String contentCateId = null;
		if (StringUtils.isNotEmpty(parentId) && !"#".equals(parentId)) {
			contentCateId = parentId;
		} 
		
		// Get owner filter
		List<String> ownerFilter = getOwnerGroup(request, ActionType.READ.getActionCode());
		logger.info("ownerFilter : " + ownerFilter);
		if (ownerFilter != null) {
			ServiceResult<List<ContentCategoryDTO>> serviceResult = knowledgeBaseService.getContentCategoryList(contentCateId, level, cateTypeCd, "01", ownerFilter);
			if (serviceResult.isSuccess()) {
				List<ContentCategoryDTO> contentCategoryDTOList = serviceResult.getResult();
				if (CollectionUtil.isNotEmpty(contentCategoryDTOList)) {
					for (ContentCategoryDTO contentCategoryDTO : contentCategoryDTOList) {
						treeModelBean = new TreeModelBean();
						
						Boolean isParent = (contentCategoryDTO.getParentCatId() == null) ? Boolean.TRUE : Boolean.FALSE;
						treeModelBean.setId(contentCategoryDTO.getContentCatId().toString());
						treeModelBean.setParent((isParent) ? "#" : contentCategoryDTO.getParentCatId().toString());
						treeModelBean.setText(contentCategoryDTO.getCatName());
						treeModelBean.setChildren(contentCategoryDTO.getHasChild());
						
						categoryTree.add(treeModelBean);
					}
				}
			}
		}
		
		
		/* Demo */
		/*
		treeModelBean = new TreeModelBean();
		if ("#".equals(parentId)) {
			treeModelBean.setId("node_1");
			treeModelBean.setText("Node #1");
			treeModelBean.setIcon("fa fa-folder icon-lg icon-success");
			treeModelBean.setChildren(true);
			treeModelBean.setType("root");
			treeModelBean.setParent("#");
			
			categoryTree.add(treeModelBean);
		} else {
			treeModelBean.setId("node_" + 2);
			treeModelBean.setText("Node #2");
			treeModelBean.setIcon("fa fa-folder icon-lg icon-info");
			treeModelBean.setChildren(true);
			treeModelBean.setParent("node_1");
			
			categoryTree.add(treeModelBean);
		}
		 * 
		 */
		logger.info("Result [" + JsonUtil.toJSON( categoryTree, Boolean.TRUE ) + "]");
		return JsonUtil.toJSON( categoryTree, Boolean.TRUE );
	}
	
	@RequestMapping(value = "/searchCategoryList", headers = { "Accept=application/json" })
	public @ResponseBody String searchCategoryList(HttpServletRequest request, HttpServletResponse response, Locale locale,
			@RequestParam("keyword") String keyword,
			@RequestParam("level") Integer level) throws Exception {
		logger.info("KnowledgeBaseController - searchCategoryList : ");
		logger.info("*****************************************************");
		
		DatatableModelBean result =  new DatatableModelBean();
		Pageable pageable = getPagableFromRequest(request);
		

		// Get owner filter
		List<String> ownerFilter = getOwnerGroup(request, ActionType.READ.getActionCode());
		logger.info("ownerFilter : " + ownerFilter);
		if (ownerFilter != null) {
			ServiceResult<Page<ContentCategoryDTO>> serviceResult = knowledgeBaseService.getContentCategoryList(level, keyword, pageable, ownerFilter);
			if(serviceResult.isSuccess()){
				Page<ContentCategoryDTO> page = serviceResult.getResult();
				Long totalElement = page.getTotalElements();			
				result.setsEcho(getSecho(request));
				result.setiTotalDisplayRecords(totalElement.intValue());
				result.setiTotalRecords(totalElement.intValue());
				result.setAaData(page.getContent());
			}
		}
		
		
		return JsonUtil.toJSON(result, true);
	}
	
	
	@RequestMapping(value = "/searchCategoryDetail", headers = { "Accept=application/json" })
	public @ResponseBody String searchCategoryDetail(HttpServletRequest request, 
												HttpServletResponse response, 
												Locale locale,
												@RequestParam("contentCatId") String contentCatId,
												@RequestParam("level") String level) throws Exception {
		
		logger.info("KnowledgeBaseController - searchCategoryDetail : ");
		logger.info("*****************************************************");
		logger.info("contentCatId: " + contentCatId + ", level: " + level);
		
		JsonResultBean jsonResultBean = new JsonResultBean();
		
		ServiceResult<ContentCategoryDTO> serviceResult = knowledgeBaseService.getContentCategory(contentCatId, Integer.parseInt(level));
		
		if (serviceResult.isSuccess()) {
			ContentCategoryDTO contentCategoryDTO = serviceResult.getResult();
			logger.info("hasChild " + contentCategoryDTO.getHasChild());
			jsonResultBean.setResultCode(JLOWebConstant.SUCCESS_CODE);
			jsonResultBean.setResultMessage(JLOWebConstant.SUCCESS_DESC);
			jsonResultBean.setModel(contentCategoryDTO);
		} else {
			jsonResultBean.setResultCode(serviceResult.getResponseCode());
			jsonResultBean.setResultMessage(serviceResult.getResponseDescription());
		}
		
		return JsonUtil.toJSON( jsonResultBean, Boolean.TRUE ); 
	}
	
	@RequestMapping(value = "/getSlaCombo", headers = { "Accept=application/json" }, method = RequestMethod.GET)
	public @ResponseBody String getSlaCombo(HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception {
		logger.info("KnowledgeBaseController - getSlaCombo");
		logger.info("*****************************************************");
		
		List<Select2DataModelBean> selectList = new ArrayList<Select2DataModelBean>();
		Select2DataModelBean select2 = null;
		
		ServiceResult<List<SlaDTO>> serviceRes = slaService.getSlaList();
		if (serviceRes.isSuccess()) {
			List<SlaDTO> slaDTOList = serviceRes.getResult();
			if (CollectionUtil.isNotEmpty(slaDTOList)) {
				for (SlaDTO slaDTO : slaDTOList) {
					select2 = new Select2DataModelBean();
					select2.setId(slaDTO.getSlaId().toString());
					select2.setText(slaDTO.getSlaName());
					
					selectList.add(select2);
				}
			}
		}
		
		return JsonUtil.toJSON( selectList, true );
	}
	
	@RequestMapping(value = "/saveCategoryDetail", headers = { "Accept=application/json" }, method = RequestMethod.POST)
	public @ResponseBody String saveKbCategory(HttpServletRequest request, HttpServletResponse response, Locale locale, @ModelAttribute(CATEGORY_MODEL) ContentCategoryDTO contentCategoryDTO) throws Exception {
		logger.info("KnowledgeBaseController - saveCategoryDetail");
		logger.info("*****************************************************");
		JsonResultBean jsonResult = new JsonResultBean();
		
		logger.info("getContentCatId " + contentCategoryDTO.getContentCatId());
		logger.info("getContentTypeCd " + contentCategoryDTO.getContentTypeCd());
		logger.info("getParentCatId " + contentCategoryDTO.getParentCatId());
		logger.info("getParentCatName " + contentCategoryDTO.getParentCatName());
		logger.info("getCatName " + contentCategoryDTO.getCatName());
		logger.info("getStatusCd " + contentCategoryDTO.getStatusCd());
		logger.info("getDescp " + contentCategoryDTO.getDescp());
		logger.info("getHasChild " + contentCategoryDTO.getHasChild());
		logger.info("getSlaId " + contentCategoryDTO.getSlaId());
		logger.info("getRegId " + contentCategoryDTO.getRegId());
		logger.info("getSlaUnit " + contentCategoryDTO.getSlaUnit());
		logger.info("getLevel " + contentCategoryDTO.getLevel());
		
		if (StringUtils.isNotEmpty(contentCategoryDTO.getContentCatId())) {
			// Update mode
			logger.info("Update categoey");
			
			List<String> ownerFilter = getOwnerGroup(request, ActionType.EDIT.getActionCode());
			logger.info("ownerFilter : " + ownerFilter);
			if (ownerFilter != null) {
				ServiceResult<ContentCategoryDTO> res = knowledgeBaseService.getContentCategory(contentCategoryDTO.getContentCatId(), contentCategoryDTO.getLevel());
				if (res.isSuccess()) {
					ContentCategoryDTO categoryDTO = res.getResult();
					
					// Check permission for save
					if (!checkVisibility(request, ActionType.EDIT.getActionCode(), categoryDTO.getRegId())) {
						jsonResult.setResultCode(JLOWebConstant.FAIL_CODE);
						jsonResult.setResultMessage(messageSource.getMessage("lbl.nopermission.edit", null, locale));
						
						return JsonUtil.toJSON( jsonResult, true );
					}
				}
			} else {
				jsonResult.setResultCode(JLOWebConstant.FAIL_CODE);
				jsonResult.setResultMessage(messageSource.getMessage("lbl.nopermission.edit", null, locale));
				
				return JsonUtil.toJSON( jsonResult, true );
			}
			
			
			contentCategoryDTO.setChgId(getUserId(request));
			ServiceResult<ContentCategoryDTO> serviceRes = knowledgeBaseService.updateCategory(contentCategoryDTO);
			if (serviceRes.isSuccess()) {
				jsonResult.setResultCode(JLOWebConstant.SUCCESS_CODE);
				jsonResult.setResultMessage(JLOWebConstant.SUCCESS_DESC);
			} else {
				jsonResult.setResultCode(JLOWebConstant.FAIL_CODE);
				jsonResult.setResultMessage(JLOWebConstant.FAIL_DESC);
			}
			
			jsonResult.setModel(contentCategoryDTO);
			
			return JsonUtil.toJSON( jsonResult, true );
		} else {
			// Insert mode
		
			contentCategoryDTO.setRegId(getUserId(request));
			contentCategoryDTO.setChgId(getUserId(request));
			ServiceResult<ContentCategoryDTO> serviceRes = knowledgeBaseService.insertCategory(contentCategoryDTO);
			if (serviceRes.isSuccess()) {
				jsonResult.setResultCode(JLOWebConstant.SUCCESS_CODE);
				jsonResult.setResultMessage(JLOWebConstant.SUCCESS_DESC);
			} else {
				jsonResult.setResultCode(JLOWebConstant.FAIL_CODE);
				jsonResult.setResultMessage(JLOWebConstant.FAIL_DESC);
			}
			
			jsonResult.setModel(contentCategoryDTO);
			
			return JsonUtil.toJSON( jsonResult, true );
		}
	}
	
	@RequestMapping(value = "/deleteCategory", headers = { "Accept=application/json" }, method = RequestMethod.POST)
	public @ResponseBody String deleteCategory(HttpServletRequest request, HttpServletResponse response, Locale locale, 
		   @RequestParam String contentCatId,
		   @RequestParam Integer level) throws Exception {
		logger.info("KnowledgeBaseController - deleteCategory");
		logger.info("*****************************************************");
		JsonResultBean jsonResult = new JsonResultBean();
		
		logger.info("contentCatId " + contentCatId + " level " + level);
		
		List<String> ownerFilter = getOwnerGroup(request, ActionType.DELETE.getActionCode());
		logger.info("ownerFilter : " + ownerFilter);
		if (ownerFilter != null) {
			ServiceResult<ContentCategoryDTO> res = knowledgeBaseService.getContentCategory(contentCatId, level);
			if (res.isSuccess()) {
				ContentCategoryDTO contentCategoryDTO = res.getResult();
				
				// Check permission for delete
				if (!checkVisibility(request, ActionType.DELETE.getActionCode(), contentCategoryDTO.getRegId())) {
					jsonResult.setResultCode(JLOWebConstant.FAIL_CODE);
					jsonResult.setResultMessage(messageSource.getMessage("lbl.nopermission.delete", null, locale));
					
					return JsonUtil.toJSON( jsonResult, true );
				}
				
				ServiceResult<Boolean> serviceRes = knowledgeBaseService.deleteCategory(contentCatId, level);
				if (serviceRes.isSuccess()) {
					jsonResult.setResultCode(JLOWebConstant.SUCCESS_CODE);
					jsonResult.setResultMessage(JLOWebConstant.SUCCESS_DESC);
				} else {
					jsonResult.setResultCode(serviceRes.getResponseCode());
					jsonResult.setResultMessage(messageSource.getMessage("lbl.delete.fail", null, locale));
				}
			}
			
		} else {
			jsonResult.setResultCode(JLOWebConstant.FAIL_CODE);
			jsonResult.setResultMessage(messageSource.getMessage("lbl.nopermission.delete", null, locale));
			
			return JsonUtil.toJSON( jsonResult, true );
		}
		
		return JsonUtil.toJSON( jsonResult, true );
	}
	
	@RequestMapping(value = "/knowledgeBaseDetail")
	public ModelAndView knowledgeBaseDetail(Model model, HttpServletRequest request, Locale local, HttpServletResponse response) {

		logger.info("KnowledgeBaseController - knowledgeBaseDetail : ");
		logger.info("*****************************************************");
		
		setMenuId(request);
		model.addAttribute("pageTitle", messageSource.getMessage("knowledgeBase.title", null, local));
		
		String contentNumber = request.getParameter("contentNumber");
		if (StringUtils.isNotEmpty(contentNumber)) {
			model.addAttribute("contentNumber", contentNumber);
		}
		
		return new ModelAndView("knowledgeBaseDetail", CATEGORY_CRITERIA_MODEL, new ContentDetailCriteria());
	}
	
	@RequestMapping(value = "/searchContentList", headers = { "Accept=application/json" })
	public @ResponseBody String searchContentList(HttpServletRequest request, HttpServletResponse response, Locale locale,
			@ModelAttribute(CATEGORY_CRITERIA_MODEL) ContentDetailCriteria contentDetailCriteria) throws Exception {
		logger.info("KnowledgeBaseController - searchContentList : ");
		logger.info("*****************************************************");
		
		Pageable pageable = getPagableFromRequest(request);
		DatatableModelBean result =  new DatatableModelBean();
		contentDetailCriteria.setLangCd(getLanguageCode(locale));
		
		// Get owner filter
		List<String> ownerFilter = getOwnerGroup(request, ActionType.READ.getActionCode());
		logger.info("ownerFilter : " + ownerFilter);
		if (ownerFilter != null) {
			contentDetailCriteria.setOwnerGroup(ownerFilter);
			ServiceResult<Page<ContentDetailDTO>> serviceResult = knowledgeBaseService.getContentDetailList(contentDetailCriteria, pageable);
			if(serviceResult.isSuccess()){
				Page<ContentDetailDTO> page = serviceResult.getResult();
				List<ContentDetailDTO> contentDetailDTOList = page.getContent();
				/*if (CollectionUtil.isNotEmpty(contentDetailDTOList)) {
					for (ContentDetailDTO contentDetailDTO : contentDetailDTOList) {
						if(contentDetailDTO.getTitle().length() > 40 ) {
							contentDetailDTO.setTitle(contentDetailDTO.getTitle().substring(0, 40));
						}
						if(contentDetailDTO.getQuestion().length() > 40 ) {
							contentDetailDTO.setQuestion(contentDetailDTO.getQuestion().substring(0, 40));
						}
					}
				}*/
				Long totalElement = page.getTotalElements();			
				result.setsEcho(getSecho(request));
				result.setiTotalDisplayRecords(totalElement.intValue());
				result.setiTotalRecords(totalElement.intValue());
				result.setAaData(contentDetailDTOList);
			}
		}
		
		return JsonUtil.toJSON(result, true);
	}
	
	@RequestMapping(value = "/saveContentInfo", headers = { "Accept=application/json" })
	public @ResponseBody String saveContentInfo(HttpServletRequest request, HttpServletResponse response, Locale locale,
			@ModelAttribute(CATEGORY_INFO_MODEL) ContentDetailModelBean contentDetailModelBean) throws Exception {
		logger.info("KnowledgeBaseController - saveContentInfo : ");
		logger.info("*****************************************************");
		
		logger.info("getContentIdTxt " + contentDetailModelBean.getContentIdTxt());
		logger.info("getDescriptionTxt " + contentDetailModelBean.getDescriptionTxt());
		logger.info("getQuestionTxt " + contentDetailModelBean.getQuestionTxt());
		logger.info("getTitleTxt " + contentDetailModelBean.getTitleTxt());
		logger.info("getKbNoTxt " + contentDetailModelBean.getKbNoTxt());
		logger.info("getContentCat1Cmb " + contentDetailModelBean.getContentCat1Cmb());
		logger.info("getContentCat2Cmb " + contentDetailModelBean.getContentCat2Cmb());
		logger.info("getContentCat3Cmb " + contentDetailModelBean.getContentCat3Cmb());
		logger.info("getContentCat4Cmb " + contentDetailModelBean.getContentCat4Cmb());
		logger.info("getContentCat5Cmb " + contentDetailModelBean.getContentCat5Cmb());
		logger.info("getContentStatusCd " + contentDetailModelBean.getContentStatusCd());
		logger.info("getExternalRefId " + contentDetailModelBean.getExternalRefId());
		logger.info("getType " + contentDetailModelBean.getType());
		
		JsonResultBean jsonResult = new JsonResultBean();
		ContentDetailDTO contentDetailDTO = new ContentDetailDTO();
		
		// Set Bean 2 DTO
		setBean2Dto(contentDetailModelBean, contentDetailDTO);
		
		
		if (StringUtils.isNotEmpty(contentDetailModelBean.getContentIdTxt())) {
			logger.info("Update knowledge base content");
			
			// Permission control (EDIT, APPROVE)
			String actionCode = "";
			if ("05".equals(contentDetailModelBean.getContentStatusCd())) {
				actionCode = ActionType.EDIT.getActionCode();
			} else {
				actionCode = ActionType.APPROVE.getActionCode();
			}
			List<String> ownerFilter = getOwnerGroup(request, actionCode);
			logger.info("ownerFilter : " + ownerFilter);
			
			if (ownerFilter != null) {
				ServiceResult<ContentDetailDTO> res = knowledgeBaseService.getContentDetail(contentDetailDTO.getContentId());
				if (res.isSuccess()) {
					ContentDetailDTO contentDTO = res.getResult();
					
					// Check permission for save
					if (!checkVisibility(request, ActionType.EDIT.getActionCode(), contentDTO.getRegId())) {
						jsonResult.setResultCode(JLOWebConstant.FAIL_CODE);
						jsonResult.setResultMessage(messageSource.getMessage("lbl.nopermission.edit", null, locale));
						
						return JsonUtil.toJSON( jsonResult, true );
					}
				}
			} else {
				jsonResult.setResultCode(JLOWebConstant.FAIL_CODE);
				jsonResult.setResultMessage(messageSource.getMessage("lbl.nopermission.edit", null, locale));
				
				return JsonUtil.toJSON( jsonResult, true );
			}
			
			
			contentDetailDTO.setChgId(getUserId(request));
			ServiceResult<Boolean> serviceRes = knowledgeBaseService.updateContent(contentDetailDTO);
			
			if (serviceRes.isSuccess()) {
				jsonResult.setResultCode(JLOWebConstant.SUCCESS_CODE);
				jsonResult.setResultMessage(JLOWebConstant.SUCCESS_DESC);
				
				// Inactive("05") should be delete from ThaiQuest
				if (CONTENT_STATUS_INACTIVE.equals(contentDetailDTO.getStatusCd())) {
					
					String extRefId = contentDetailDTO.getExternalRefId();
					logger.info("extRefId : " + extRefId);
					InjectResult injectResult = thaiQuestWsService.doDelete(extRefId);
					if (injectResult != null && "OK".equals(injectResult.getErrorCode().getValue())) {
						// Success delete from ThaiQuest
						
					} else {
						// Fail delete from ThaiQuest
						
					}
				}
				
			} else {
				jsonResult.setResultCode(JLOWebConstant.FAIL_CODE);
				jsonResult.setResultMessage(messageSource.getMessage("lbl.save.fail", null, locale));
			}
			
			jsonResult.setModel(contentDetailDTO);
		} else {
			logger.info("Insert knowledge base content");
			
			// Generate content number from sequence
			String contentNumber = null;
			if ("KB".equalsIgnoreCase(contentDetailDTO.getType())) {
				contentNumber = sequenceGeneratorService.generateByType(SequenceType.KB);
			} else if ("FAQ".equalsIgnoreCase(contentDetailDTO.getType())) {
				contentNumber = sequenceGeneratorService.generateByType(SequenceType.FAQ);
			} else {
				contentNumber = sequenceGeneratorService.generateByType(SequenceType.GEN);
			}
			
			// Insert mode
			contentDetailDTO.setRegId(getUserId(request));
			contentDetailDTO.setContentNumber(contentNumber);
			ServiceResult<Integer> serviceRes = knowledgeBaseService.insertContent(contentDetailDTO);
			
			if (serviceRes.isSuccess()) {
				jsonResult.setResultCode(JLOWebConstant.SUCCESS_CODE);
				jsonResult.setResultMessage(JLOWebConstant.SUCCESS_DESC);
			} else {
				jsonResult.setResultCode(JLOWebConstant.FAIL_CODE);
				jsonResult.setResultMessage(JLOWebConstant.FAIL_DESC);
			}
			
			jsonResult.setModel(contentDetailDTO);
		}
		
		return JsonUtil.toJSON(jsonResult, true);
	}
	
	@RequestMapping(value = "/approveKnowledgeBase")
	public ModelAndView approveKnowledgeBase(Model model, HttpServletRequest request, Locale local,
			HttpServletResponse response) {

		logger.info("KnowledgeBaseController - approveKnowledgeBase : ");
		logger.info("*****************************************************");
		
		setMenuId(request);
		model.addAttribute("pageTitle", messageSource.getMessage("knowledgeBase.title", null, local));
		
		return new ModelAndView("knowledgeBaseApproval");
	}
	
	@RequestMapping(value = "/searchKBWaitingList", headers = { "Accept=application/json" })
	public @ResponseBody String searchKBWaitingList(HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception {
		logger.info("KnowledgeBaseController - searchKBWaitingList : ");
		logger.info("*****************************************************");
		
		DatatableModelBean result =  new DatatableModelBean();
		Pageable pageable = getPagableFromRequest(request);
		// Get owner filter
		List<String> ownerFilter = getOwnerGroup(request, ActionType.APPROVE.getActionCode());
		logger.info("ownerFilter : " + ownerFilter);
		if (ownerFilter != null) {
			ServiceResult<Page<ContentDetailDTO>> serviceRes = knowledgeBaseService.getKBWaitingList(pageable, ownerFilter, getLanguageCode(locale));
			if (serviceRes.isSuccess()) {
				Page<ContentDetailDTO> page = serviceRes.getResult();
				Long totalElement = page.getTotalElements();	
				
				for (ContentDetailDTO contentDetailDTO : page) {
					if (checkVisibility(request, ActionType.EDIT.getActionCode(), contentDetailDTO.getRegId())) {
						StringBuffer url = new StringBuffer();
						url.append("<a href='#' onclick='postAction(\"/knowledgeBaseDetail.htm?contentNumber=" + contentDetailDTO.getContentNumber() + "\")'>");
						url.append("<i class='fa fa-pencil'></i>");
						url.append("</a>");
						logger.info("edit url : " + url);
						contentDetailDTO.setEditUrl(url.toString());
					} else {
						contentDetailDTO.setEditUrl("");
					}
				}
				
				result.setsEcho(getSecho(request));
				result.setiTotalDisplayRecords(totalElement.intValue());
				result.setiTotalRecords(totalElement.intValue());
				result.setAaData(page.getContent());
			}
		}
		
		return JsonUtil.toJSON(result, true);
	}
	
	@RequestMapping(value = "/kbApproveOrReject", method = RequestMethod.POST)
	public @ResponseBody String kbApproveOrReject(HttpServletRequest request, HttpServletResponse response, Locale locale, String[] dataReqObj, String modeAction) throws Exception {
		logger.info("KnowledgeBaseController - kbApproveOrReject : " + dataReqObj);
		logger.info("*****************************************************");
		
		JsonResultBean result = new JsonResultBean();
		
		try {
			List<String> contentIds = Arrays.asList(dataReqObj);
			ServiceResult<Boolean> serviceRes = null;
			Integer userId = getUserId(request);
			List<String> failList = new ArrayList<String>();
			
			if ("APPROVE".equals(modeAction)) {
				
				for (String contentId : contentIds) {
					ServiceResult<ContentDetailDTO> serviceResult = knowledgeBaseService.getContentDetail(Integer.parseInt(contentId));
					if (serviceResult.isSuccess()) {
						ContentDetailDTO contentDetailDTO = serviceResult.getResult();
						
						// Prepare ThaiQuest Document Object
						DocumentProperty document = prepareThaiquestDocument(contentDetailDTO);
						
						InjectResult injectResult = thaiQuestWsService.doAdd(document);
						if (injectResult != null && "OK".equals(injectResult.getErrorCode().getValue())) {
							// ThaiQuest Success
							
							String thaiQuestId = injectResult.getID();
							contentDetailDTO.setExternalRefId(thaiQuestId);
							knowledgeBaseService.updateContent(contentDetailDTO);
							knowledgeBaseService.updateContentStatus(Integer.parseInt(contentId), userId, CONTENT_STATUS_APPROVED);
						} else {
							// ThaiQuest Fail
							logger.info("Fail to send data to ThaiQuest " + injectResult.getErrorCode().getValue() + " " + contentDetailDTO.getContentNumber());
							failList.add(contentDetailDTO.getContentNumber());
						}
					}
				}
				
				if (CollectionUtil.isNotEmpty(failList)) {
					
					String errerParam = "";
					for (String failObj : failList) {
						errerParam += failObj + ",";
					}
					if (errerParam.length() > 0) {
						errerParam = errerParam.substring(0, (errerParam.length() -1) );
					}
					logger.info("errerParam : " + errerParam);
					String[] errerParams = {errerParam};
					result.setResultCode(JLOWebConstant.FAIL_CODE);
					result.setResultMessage(messageSource.getMessage("lbl.kb.approve.fail", errerParams, locale));
				} else {
					result.setResultCode(JLOWebConstant.SUCCESS_CODE);
					result.setResultMessage(JLOWebConstant.SUCCESS_DESC);
				}
				
			} else {
				serviceRes = knowledgeBaseService.updateContentStatus(contentIds, userId, CONTENT_STATUS_REJECTED);
				if (serviceRes.isSuccess()) {
					result.setResultCode(JLOWebConstant.SUCCESS_CODE);
					result.setResultMessage(JLOWebConstant.SUCCESS_DESC);
				} else {
					result.setResultCode(JLOWebConstant.FAIL_CODE);
					result.setResultMessage(JLOWebConstant.FAIL_DESC);
				}
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.setResultCode(JLOWebConstant.FAIL_CODE);
			result.setResultMessage(e.getMessage());
		}
		
		
		return JsonUtil.toJSON(result, Boolean.TRUE);
	}
	
	//searchContentAttByContentId
	
	@RequestMapping(value = "/searchContentAttList", headers = { "Accept=application/json" })
	public @ResponseBody String searchContentAttList(HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception {
		logger.info("KnowledgeBaseController - searchContentAttList : ");
		logger.info("*****************************************************");
		String contentId = request.getParameter("contentId");
		logger.info("ContentId : " + contentId);
		
		DatatableModelBean result =  new DatatableModelBean();
		Pageable pageable = getPagableFromRequest(request);
		ServiceResult<Page<ContentAttDTO>> serviceRes = contentService.searchContentAttByContentId(contentId, pageable);
		if (serviceRes.isSuccess()) {
			Page<ContentAttDTO> page = serviceRes.getResult();
			Long totalElement = page.getTotalElements();
			
			// Visibility
			List<ContentAttDTO> contentAttDTOList = page.getContent();
			if (CollectionUtil.isNotEmpty(contentAttDTOList)) {
				for (ContentAttDTO contentAttDTO : contentAttDTOList) {
					if (checkVisibility(request, ActionType.DELETE.getActionCode(), Integer.parseInt(contentAttDTO.getRegId()))) {
						StringBuffer url = new StringBuffer();
						url.append("<a href='#' onclick='deleteFileConfirm(" + contentAttDTO.getContentAttId() + "," + contentAttDTO.getAttId() + ")'>");
						url.append("<i class='fa fa-trash-o'></i>");
						url.append("</a>");
						
						String downloadUrl = request.getContextPath() + "/downloadFile.htm?attId=" + contentAttDTO.getAttId();
						url.append(" <a onclick=\"window.open('" + downloadUrl + "', '_blank', 'location=yes');\" title='Download Document'>");
						url.append("<i class='fa fa-download'></i>");
						url.append("</a>");
						
						contentAttDTO.setDeleteUrl(url.toString());
					} else {
						StringBuffer url = new StringBuffer();
						String downloadUrl = request.getContextPath() + "/downloadFile.htm?attId=" + contentAttDTO.getAttId();
						url.append("<a onclick=\"window.open('" + downloadUrl + "', '_blank', 'location=yes');\" title='Download Document'>");
						url.append("<i class='fa fa-download'></i>");
						url.append("</a>");
						
						contentAttDTO.setDeleteUrl(url.toString());
					}
				}
			}
			
			result.setsEcho(getSecho(request));
			result.setiTotalDisplayRecords(totalElement.intValue());
			result.setiTotalRecords(totalElement.intValue());
			result.setAaData(page.getContent());
		}
		return JsonUtil.toJSON(result, true);
	}
	
	@RequestMapping(value = "/searchContentAttListForApprove", headers = { "Accept=application/json" })
	public @ResponseBody String searchContentAttListForApprove(HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception {
		logger.info("KnowledgeBaseController - searchContentAttList : ");
		logger.info("*****************************************************");
		String contentId = request.getParameter("contentId");
		logger.info("ContentId : " + contentId);
		
		DatatableModelBean result =  new DatatableModelBean();
		Pageable pageable = getPagableFromRequest(request);
		ServiceResult<Page<ContentAttDTO>> serviceRes = contentService.searchContentAttByContentId(contentId, pageable);
		if (serviceRes.isSuccess()) {
			Page<ContentAttDTO> page = serviceRes.getResult();
			Long totalElement = page.getTotalElements();
			
			// Visibility
			List<ContentAttDTO> contentAttDTOList = page.getContent();
			if (CollectionUtil.isNotEmpty(contentAttDTOList)) {
				for (ContentAttDTO contentAttDTO : contentAttDTOList) {
					StringBuffer url = new StringBuffer();
					String downloadUrl = request.getContextPath() + "/downloadFile.htm?attId=" + contentAttDTO.getAttId();
					url.append("<a onclick=\"window.open('" + downloadUrl + "', '_blank', 'location=yes');\" title='Download Document'>");
					url.append("<i class='fa fa-download'></i>");
					url.append("</a>");
					
					contentAttDTO.setDeleteUrl(url.toString());
				}
			}
			
			result.setsEcho(getSecho(request));
			result.setiTotalDisplayRecords(totalElement.intValue());
			result.setiTotalRecords(totalElement.intValue());
			result.setAaData(page.getContent());
		}
		return JsonUtil.toJSON(result, true);
	}
	
	@RequestMapping(value = "/saveContentAtt", headers = { "Accept=application/json" })
	public @ResponseBody String saveContentAtt(MultipartHttpServletRequest request, HttpServletResponse response, Locale locale,
			@ModelAttribute(CATEGORY_ATTACH_MODEL) KnowledgeBaseAttModelBean knowledgeBaseAttModelBean) throws Exception {
		logger.info("KnowledgeBaseController - saveContentAtt : " + knowledgeBaseAttModelBean.getAttMode());
		logger.info("*****************************************************");
		logger.info("Content ID : " + knowledgeBaseAttModelBean.getContentId());
		logger.info("Att File name : " + knowledgeBaseAttModelBean.getAttFileName());
		logger.info("Att Title : " + knowledgeBaseAttModelBean.getAttTitle());
		logger.info("Att Descp : " + knowledgeBaseAttModelBean.getAttDescp());
		logger.info("Att ID : " + knowledgeBaseAttModelBean.getAttId());
		
		JsonResultBean jsonResult = new JsonResultBean();
	
		// Prepare bean before save
		ContentAttDTO contentAttDTO = new ContentAttDTO();
		contentAttDTO.setContentId(knowledgeBaseAttModelBean.getContentId());
		contentAttDTO.setTitle(knowledgeBaseAttModelBean.getAttTitle());
		contentAttDTO.setDescp(knowledgeBaseAttModelBean.getAttDescp());
		contentAttDTO.setMainFlag(knowledgeBaseAttModelBean.getMainFlag());
		contentAttDTO.setRegId(String.valueOf(getUserId(request)));
		
		if ("add".equals(knowledgeBaseAttModelBean.getAttMode())) {
			// Parameter value from request
			MultipartFile imgFile = request.getFile("kbAttFile");
			
			// 20 MB = 20971520 B
			if (imgFile.getSize() >= JLOWebConstant.FILE_SIZE.TWENTY_MB) {
				// Over limit size 
				jsonResult.setResultCode(JLOWebConstant.FAIL_CODE);
				String [] param = {"20"};
				jsonResult.setResultMessage(messageSource.getMessage("lbl.validate.limitfilesize", param, locale));
				return JsonUtil.toJSON(jsonResult, Boolean.TRUE);
			}
			
			//String originalFileName = imgFile.getOriginalFilename();
			String[] ext = imgFile.getOriginalFilename().split("\\.");
			String fileExtension = "";
			if(ext.length > 1){
				fileExtension = (ext[ext.length-1]);
			}	
			String filename = FileUtil.genFilename("CONTENT_ATT") + "." + fileExtension;
			String path = homePath + JLOWebConstant.ROOT_FOLDER;
			String imagePath = JLOWebConstant.ATTACHMENT+JLOWebConstant.CONTENT_ATT_PATH + filename;
			saveAttachFile(imgFile, filename, path + imagePath);
			
			AttDTO attDTO = new AttDTO();
			attDTO.setActiveFlg("1");
			attDTO.setFileName(filename);
			attDTO.setFilePath(JLOWebConstant.ATTACHMENT+JLOWebConstant.CONTENT_ATT_PATH);
			attDTO.setFileExtension(fileExtension);
			attDTO.setFileType(imgFile.getContentType());
			attDTO.setFileSize(String.valueOf(imgFile.getSize()));
			attDTO.setRegId(String.valueOf(getUserId(request)));
			
			// Save image to ATT
			ServiceResult<Long> serviceResult = contentService.insertContentAtt(contentAttDTO, attDTO);
			logger.info("InsertContentAtt Result" + serviceResult.getResponseCode() + " " + serviceResult.getResponseDescription());
			if (serviceResult.isSuccess()) {
				jsonResult.setResultCode(JLOWebConstant.SUCCESS_CODE);
				jsonResult.setResultMessage(JLOWebConstant.SUCCESS_DESC);
			} else {
				jsonResult.setResultCode(JLOWebConstant.FAIL_CODE);
				jsonResult.setResultMessage(messageSource.getMessage("lbl.save.fail", null, locale));
			}
		} else {
			
			List<String> ownerFilter = getOwnerGroup(request, ActionType.EDIT.getActionCode());
			logger.info("ownerFilter : " + ownerFilter);
			if (ownerFilter != null) {
					// Check permission for save
					if (!checkVisibility(request, ActionType.EDIT.getActionCode(), knowledgeBaseAttModelBean.getRegId())) {
						jsonResult.setResultCode(JLOWebConstant.FAIL_CODE);
						jsonResult.setResultMessage(messageSource.getMessage("lbl.nopermission.edit", null, locale));
						
						return JsonUtil.toJSON( jsonResult, true );
					}
				
			} else {
				jsonResult.setResultCode(JLOWebConstant.FAIL_CODE);
				jsonResult.setResultMessage(messageSource.getMessage("lbl.nopermission.edit", null, locale));
				
				return JsonUtil.toJSON( jsonResult, true );
			}
			
			contentAttDTO.setAttId(Long.parseLong(knowledgeBaseAttModelBean.getAttId()));
			contentAttDTO.setContentAttId(Long.parseLong(knowledgeBaseAttModelBean.getContentAttId()));
			contentAttDTO.setChgId(String.valueOf(getUserId(request)));
			Long res = contentService.updateContentAtt(contentAttDTO);
			if (res > 0) {
				jsonResult.setResultCode(JLOWebConstant.SUCCESS_CODE);
				jsonResult.setResultMessage(JLOWebConstant.SUCCESS_DESC);
			} else {
				jsonResult.setResultCode(JLOWebConstant.FAIL_CODE);
				jsonResult.setResultMessage(messageSource.getMessage("lbl.save.fail", null, locale));
			}
			
		}
		
		return JsonUtil.toJSON(jsonResult, Boolean.TRUE);
	}
	
	@RequestMapping(value = "/deleteContentAtt", headers = { "Accept=application/json" })
	public @ResponseBody String deleteContentAtt(HttpServletRequest request, HttpServletResponse response, Locale locale)  {
		logger.info("KnowledgeBaseController - deleteContentAtt : ");
		logger.info("*****************************************************");
		JsonResultBean jsonResult = new JsonResultBean();
		
		String contentAttId = request.getParameter("contentAttId");
		String attId = request.getParameter("attId");
		
		logger.info("Content ID : " + contentAttId);
		logger.info("Att ID : " + attId);
		
		ContentAttDTO contentAttDTO = new ContentAttDTO();
		contentAttDTO.setContentAttId(new Long(contentAttId));
		
		AttDTO attDTO = new AttDTO();
		attDTO.setAttId(new Long(attId));
		
		ServiceResult<AttDTO> attServiceRes = attService.selectById(attId);
		if (attServiceRes.isSuccess()) {
			AttDTO attDTOTemp = attServiceRes.getResult();
			
			String rootPath = homePath + JLOWebConstant.ROOT_FOLDER;
			String imagePath = attDTOTemp.getFilePath() + attDTOTemp.getFileName();
			
			Path path = Paths.get(rootPath + imagePath);
			try {
				Files.deleteIfExists(path);
			} catch (IOException e) {
				logger.error("Cannot delete file name " + path);
			}
			
		}
		
		Long deleteContentAtt = contentService.deleteContentAtt(contentAttDTO,attDTO);
		if (deleteContentAtt > 0) {
			jsonResult.setResultCode(JLOWebConstant.SUCCESS_CODE);
			jsonResult.setResultMessage(messageSource.getMessage("lbl.delete.success", null, locale));
		} else {
			jsonResult.setResultCode(JLOWebConstant.FAIL_CODE);
			jsonResult.setResultMessage(messageSource.getMessage("lbl.delete.fail", null, locale));
		}
				
		return JsonUtil.toJSON(jsonResult, Boolean.TRUE);
	}
	
	@RequestMapping(value = "/importKb")
	public ModelAndView importKb(Model model, HttpServletRequest request, Locale local,
			HttpServletResponse response) {

		logger.info("KnowledgeBaseController - importKb : ");
		logger.info("*****************************************************");
		
		setMenuId(request);
		model.addAttribute("pageTitle", messageSource.getMessage("knowledgeBase.import.title", null, local));
		
		return new ModelAndView("importKnowledgeBase");
	}
	
	@RequestMapping(value = "/exportKb")
	public ModelAndView exportKb(Model model, HttpServletRequest request, Locale local,
			HttpServletResponse response) {

		logger.info("KnowledgeBaseController - exportKb : ");
		logger.info("*****************************************************");
		
		setMenuId(request);
		model.addAttribute("pageTitle", messageSource.getMessage("knowledgeBase.export.title", null, local));
		
		return new ModelAndView("exportKnowledgeBase");
	}
	
	@SuppressWarnings("unused")
	@RequestMapping(value = "/validateImportFile", headers = { "Accept=application/json" })
	public @ResponseBody String validateImportFile(MultipartHttpServletRequest request, HttpServletResponse response, Model model, Locale locale) {
		logger.info("KnowledgeBaseController - validateImportFile : ");
		logger.info("*****************************************************");
		MultipartFile importFile = request.getFile("kbImportFile");
		JsonResultBean result = new JsonResultBean();
		CSVReader reader = null;
		
		try {
			if (importFile != null) {

				// Validate
				result = validateFile(importFile, FileSizeType.TEN, locale);
				if ( !JLOWebConstant.SUCCESS_CODE.equals(result.getResultCode()) ) {
					return JsonUtil.toJSON(result, Boolean.TRUE);
				}
				
				reader = new CSVReader(new InputStreamReader(importFile.getInputStream()), ',');
				String[] record = null;
				
			    // Validate import file
			    StringBuffer errorMsg = null;
				String eximType = request.getParameter("eximType");
				List<ValidateResultBean> validateResultBeanList = new ArrayList<ValidateResultBean>();
				ValidateResultBean validateResultBean = null;
				Boolean valid = null;
				ImportKnowledgeBaseModelBean importKnowledgeBaseModelBean = new ImportKnowledgeBaseModelBean();
				importKnowledgeBaseModelBean.setFileName(importFile.getOriginalFilename());
				importKnowledgeBaseModelBean.setImportDateTime(DateTimeUtils.formatDateTime(Calendar.getInstance().getTime()));
				
				if ("01".equals(eximType)) {
					// skip header row
					reader.readNext();
					int i = 1;
					while ((record = reader.readNext()) != null) {
						valid = true;
						validateResultBean = new ValidateResultBean();
						validateResultBean.setRow(i++);
						errorMsg = new StringBuffer();
						logger.info("Length :: " + record.length);
						
						if (record.length != 9) {
							// Invalid number of column
							valid = false;
							validateResultBean.setMessage("Invalid number of column");
							validateResultBeanList.add(validateResultBean);
							
							continue;
						}
						/*
						 * ==== Validate rule ====
						 * Column 1 : required
						 * Column 2 : (1) 
						 * Column 3 : (1)
						 * Column 4 : required
						 * Column 5 : (2)
						 * Column 6 : (2)
						 * Column 7 : required
						 * Column 8 : -
						 * Column 9 : required
						 * */
						int idx = 0;
						String column1 = String.valueOf(record[idx++]);
						String column2 = String.valueOf(record[idx++]);
						String column3 = String.valueOf(record[idx++]);
						String column4 = String.valueOf(record[idx++]);
						String column5 = String.valueOf(record[idx++]);
						String column6 = String.valueOf(record[idx++]);
						String column7 = String.valueOf(record[idx++]);
						String column8 = String.valueOf(record[idx++]);
						String column9 = String.valueOf(record[idx++]);
						
						// Column 1
						if (StringUtils.isEmpty(column1)) {
							valid = false;
							errorMsg.append("Column 1 is required, ");
						}
						
						// Column 2,3
						if (StringUtils.isEmpty(column2) && StringUtils.isEmpty(column3)) {
							valid = false;
							errorMsg.append("Must be have only one value of Column 2 or Column 3, ");
						} else if (StringUtils.isNotEmpty(column2) && StringUtils.isNotEmpty(column3)) {
							valid = false;
							errorMsg.append("Must be have only one value of Column 2 or Column 3, ");
						}
						
						// Column 4
						if (StringUtils.isEmpty(column4)) {
							valid = false;
							errorMsg.append("Column 4 is required, ");
						}
						
						// Column 5, 6
//						if (StringUtils.isEmpty(column5) && StringUtils.isEmpty(column6)) {
//							valid = false;
//							errorMsg.append("Must be have only one value of Column 5 or Column 6 ");
//						} else if (StringUtils.isNotEmpty(column5) && StringUtils.isNotEmpty(column6)) {
//							valid = false;
//							errorMsg.append("Must be have only one value of Column 5 or Column 6 ");
//						}
						
						// Column 7
						if (StringUtils.isEmpty(column7)) {
							valid = false;
							errorMsg.append("Column 7 is required, ");
						}
						
						// Column 9
						if (StringUtils.isEmpty(column9)) {
							valid = false;
							errorMsg.append("Column 9 is required, ");
						}
						
						if (!valid) {
							validateResultBean.setMessage(errorMsg.toString().trim());
							validateResultBeanList.add(validateResultBean);
						}
					}
				} else if ("02".equals(eximType)) {
					// skip header row
					reader.readNext();
					int i = 1;
					while ((record = reader.readNext()) != null) {
						valid = true;
						validateResultBean = new ValidateResultBean();
						validateResultBean.setRow(i++);
						errorMsg = new StringBuffer();
						logger.info("Length :: " + record.length);
						
						if (record.length != 15) {
							// Invalid number of column
							valid = false;
							validateResultBean.setMessage("Invalid number of column");
							validateResultBeanList.add(validateResultBean);
							
							continue;
						}
						/*
						 * ==== Validate rule ====
						 * Column 1 : empty
						 * Column 2 : empty 
						 * Column 3 : required
						 * Column 4 : -
						 * Column 5 : required
						 * Column 6 : -
						 * Column 7 : required
						 * Column 8 : -
						 * Column 9 : required
						 * Column 10 : -
						 * Column 11 : required
						 * Column 12 : -
						 * Column 13 : required
						 * Column 14 : required
						 * Column 15 : -
						 * */
						int idx = 0;
						String column1 = String.valueOf(record[idx++]);
						String column2 = String.valueOf(record[idx++]);
						String column3 = String.valueOf(record[idx++]);
						String column4 = String.valueOf(record[idx++]);
						String column5 = String.valueOf(record[idx++]);
						String column6 = String.valueOf(record[idx++]);
						String column7 = String.valueOf(record[idx++]);
						String column8 = String.valueOf(record[idx++]);
						String column9 = String.valueOf(record[idx++]);
						String column10 = String.valueOf(record[idx++]);
						String column11 = String.valueOf(record[idx++]);
						String column12 = String.valueOf(record[idx++]);
						String column13 = String.valueOf(record[idx++]);
						String column14 = String.valueOf(record[idx++]);
						String column15 = String.valueOf(record[idx]);
						
						// Column 1
						if (StringUtils.isNotEmpty(column1)) {
							valid = false;
							errorMsg.append("Column 1 must be empty, ");
						}
						
						// Column 2
						if (StringUtils.isNotEmpty(column2)) {
							valid = false;
							errorMsg.append("Column 2 must be empty, ");
						}
						
						// Column 3
						if (StringUtils.isEmpty(column3)) {
							valid = false;
							errorMsg.append("Column 3 is required, ");
						}
						
						// Column 5
						if (StringUtils.isEmpty(column5)) {
							valid = false;
							errorMsg.append("Column 5 is required, ");
						}
						
						// Column 7
						if (StringUtils.isEmpty(column7)) {
							valid = false;
							errorMsg.append("Column 7 is required, ");
						}
						
						// Column 9
						if (StringUtils.isEmpty(column9)) {
							valid = false;
							errorMsg.append("Column 9 is required, ");
						}
						
						// Column 11
						if (StringUtils.isEmpty(column11)) {
							valid = false;
							errorMsg.append("Column 11 is required, ");
						}
						
						// Column 13
						if (StringUtils.isEmpty(column13)) {
							valid = false;
							errorMsg.append("Column 13 is required, ");
						}
						
						// Column 14
						if (StringUtils.isEmpty(column14)) {
							valid = false;
							errorMsg.append("Column 14 is required, ");
						}
						
						if (!valid) {
							validateResultBean.setMessage(errorMsg.toString().trim());
							validateResultBeanList.add(validateResultBean);
						}
					}
				} else {
					
				}
				
				importKnowledgeBaseModelBean.setValidateResultBeanList(validateResultBeanList);
				result.setModel(importKnowledgeBaseModelBean);
				
				if (CollectionUtil.isEmpty(validateResultBeanList)) {
					result.setResultCode(JLOWebConstant.SUCCESS_CODE);
					result.setResultMessage(JLOWebConstant.SUCCESS_DESC);
				} else {
					result.setResultCode(JLOWebConstant.FAIL_CODE);
					result.setResultMessage("Validate import file fail");
				}
				
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			
			result.setResultCode(JLOWebConstant.FAIL_CODE);
			result.setResultMessage("Cannot read csv file");
			
			return JsonUtil.toJSON(result, Boolean.TRUE);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					logger.error("Cannot close reader :: " + e.getMessage());
				}
			}
		}
		
		
		return JsonUtil.toJSON(result, Boolean.TRUE);
	}
	
	@SuppressWarnings("unused")
	@RequestMapping(value = "/importKbFromCsv", headers = { "Accept=application/json" })
	public @ResponseBody String importKbFromCsv(MultipartHttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception {
		logger.info("KnowledgeBaseController - importKbFromCsv : ");
		logger.info("*****************************************************");
		JsonResultBean result = new JsonResultBean();
		MultipartFile importFile = request.getFile("kbImportFile");
		String eximType = request.getParameter("eximType");
		CSVReader reader = null;
		
		try {
			if (importFile != null) {
				String fileExtension = "";
				String[] ext = importFile.getOriginalFilename().split("\\.");
				if(ext.length > 1){
					fileExtension = (ext[ext.length-1]);
				}
				
				// Validate
				result = validateFile(importFile, FileSizeType.TEN, locale);
				if ( !JLOWebConstant.SUCCESS_CODE.equals(result.getResultCode()) ) {
					return JsonUtil.toJSON(result, Boolean.TRUE);
				}
				
				List<ValidateResultBean> validateResultBeanList = new ArrayList<ValidateResultBean>();
				ValidateResultBean validateResultBean = null;
				ImportKnowledgeBaseModelBean importKnowledgeBaseModelBean = new ImportKnowledgeBaseModelBean();
				importKnowledgeBaseModelBean.setFileName(importFile.getOriginalFilename());
				importKnowledgeBaseModelBean.setImportDateTime(DateTimeUtils.formatDateTime(Calendar.getInstance().getTime()));
				
				reader = new CSVReader(new InputStreamReader(importFile.getInputStream()), ',');
				String[] record = null;
				HashMap< String, String> keyIdMapping = new HashMap<>();
				int successfulRecords = 0;
				
				if ("01".equals(eximType)) {
					// Insert Category
					// skip header row
					reader.readNext();
					int i = 1;
					while ((record = reader.readNext()) != null) {
						
						validateResultBean = new ValidateResultBean();
						int idx = 0;
						String level = String.valueOf(record[idx++]);
						String categoryId = String.valueOf(record[idx++]);
						String mappingId = String.valueOf(record[idx++]);
						String categoryName = String.valueOf(record[idx++]);
						String parentCategoryId = String.valueOf(record[idx++]);
						String parentMappingId = String.valueOf(record[idx++]);
						String contentTypeCode = String.valueOf(record[idx++]);
						String description = String.valueOf(record[idx++]);
						String slaId = String.valueOf(record[idx++]);
						
						ContentCategoryDTO contentCategoryDTO = null;
						
						if (StringUtils.isNotEmpty(categoryId)) {
							// Update Category
							ServiceResult<ContentCategoryDTO> getServiceResult = knowledgeBaseService.getContentCategory(categoryId, Integer.parseInt(level));
							if (getServiceResult.isSuccess()) {
								contentCategoryDTO = getServiceResult.getResult();
								
								// Only 2 fields allow to update.
								contentCategoryDTO.setDescp(description);
								contentCategoryDTO.setSlaId(Integer.parseInt(slaId));
								
								ServiceResult<ContentCategoryDTO> serviceResult = knowledgeBaseService.updateCategory(contentCategoryDTO);
								if (serviceResult.isSuccess()) {
									if (StringUtils.isNotEmpty(mappingId)) {
										keyIdMapping.put(mappingId, contentCategoryDTO.getContentCatId());
									}
								} else {
									validateResultBean.setRow(i);
									validateResultBean.setMessage("" + serviceResult.getResponseDescription());
									
									validateResultBeanList.add(validateResultBean);
								}
							}
							
							
						} else {
							// Insert Category
							contentCategoryDTO = new ContentCategoryDTO();
							contentCategoryDTO.setLevel(Integer.valueOf(level));
							contentCategoryDTO.setCatName(categoryName);
							contentCategoryDTO.setContentTypeCd(contentTypeCode);
							contentCategoryDTO.setDescp(description);
							contentCategoryDTO.setSlaId(Integer.parseInt(slaId));
							contentCategoryDTO.setStatusCd("01");
							contentCategoryDTO.setRegId(getUserId(request));
							contentCategoryDTO.setParentCatId(parentCategoryId);
							
							if (StringUtils.isNotEmpty(parentMappingId)) {
								String contentCatId = keyIdMapping.get(parentMappingId);
								if (StringUtils.isEmpty(contentCatId)) {
									validateResultBean.setRow(i);
									validateResultBean.setMessage("Invalid mapping key");
									
									validateResultBeanList.add(validateResultBean);
									
									continue;
								}
								contentCategoryDTO.setParentCatId(contentCatId);
							}
							
							ServiceResult<ContentCategoryDTO> serviceResult = knowledgeBaseService.insertCategory(contentCategoryDTO);
							if (serviceResult.isSuccess()) {
								if (StringUtils.isNotEmpty(mappingId)) {
									keyIdMapping.put(mappingId, contentCategoryDTO.getContentCatId());
								}
								successfulRecords++;
							} else {
								validateResultBean.setRow(i);
								validateResultBean.setMessage("Cannot insert [" + serviceResult.getResponseDescription() + "]");
								
								validateResultBeanList.add(validateResultBean);
							}
						}
						
						i++;
					}
					
				} else if ("02".equals(eximType)) {
					reader.readNext();
					int i = 1;
					ContentDetailDTO contentDetailDTO = null;
					
					while ((record = reader.readNext()) != null) {
						validateResultBean = new ValidateResultBean();
						validateResultBean.setRow(i++);
						logger.info("Length :: " + record.length);
						
						if (record.length != 15) {
							// Invalid number of column
							validateResultBean.setMessage("Invalid number of column");
							validateResultBeanList.add(validateResultBean);
							
							continue;
						}
						
						int idx = 0;
						String contentId = String.valueOf(record[idx++]);
						String contentNumber = String.valueOf(record[idx++]);
						String contentCat1Id = String.valueOf(record[idx++]);
						String contentCat1Name = String.valueOf(record[idx++]);
						String contentCat2Id = String.valueOf(record[idx++]);
						String contentCat2Name = String.valueOf(record[idx++]);
						String contentCat3Id = String.valueOf(record[idx++]);
						String contentCat3Name = String.valueOf(record[idx++]);
						String contentCat4Id = String.valueOf(record[idx++]);
						String contentCat4Name = String.valueOf(record[idx++]);
						String contentCat5Id = String.valueOf(record[idx++]);
						String contentCat5Name = String.valueOf(record[idx++]);
						String title = String.valueOf(record[idx++]);
						String question = String.valueOf(record[idx++]);
						String type = String.valueOf(record[idx]);
						
						/* ------------------------- Validate Category ---------------------- */
						ContentDetailCriteria contentDetailCriteria = null;
						ServiceResult<List<ContentDetailDTO>> res = null;
						StringBuffer message = new StringBuffer();
						
						contentDetailCriteria = new ContentDetailCriteria();
						contentDetailCriteria.setContentCat1Id(contentCat1Id);
						contentDetailCriteria.setLangCd(getLanguageCode(locale));
						res = knowledgeBaseService.getContentDetailList(contentDetailCriteria);
						if (res == null || CollectionUtil.isEmpty(res.getResult())) {
							message.append("Content Category Id 1 is invalid, ");
						}
						
						contentDetailCriteria = new ContentDetailCriteria();
						contentDetailCriteria.setContentCat2Id(contentCat2Id);
						contentDetailCriteria.setLangCd(getLanguageCode(locale));
						res = knowledgeBaseService.getContentDetailList(contentDetailCriteria);
						if (res == null || CollectionUtil.isEmpty(res.getResult())) {
							message.append("Content Category Id 2 is invalid, ");
						}
						
						contentDetailCriteria = new ContentDetailCriteria();
						contentDetailCriteria.setContentCat3Id(contentCat3Id);
						contentDetailCriteria.setLangCd(getLanguageCode(locale));
						res = knowledgeBaseService.getContentDetailList(contentDetailCriteria);
						if (res == null || CollectionUtil.isEmpty(res.getResult())) {
							message.append("Content Category Id 3 is invalid, ");
						}
						
						contentDetailCriteria = new ContentDetailCriteria();
						contentDetailCriteria.setContentCat4Id(contentCat4Id);
						contentDetailCriteria.setLangCd(getLanguageCode(locale));
						res = knowledgeBaseService.getContentDetailList(contentDetailCriteria);
						if (res == null || CollectionUtil.isEmpty(res.getResult())) {
							message.append("Content Category Id 4 is invalid, ");
						}
						
						contentDetailCriteria = new ContentDetailCriteria();
						contentDetailCriteria.setContentCat5Id(contentCat5Id);
						contentDetailCriteria.setLangCd(getLanguageCode(locale));
						res = knowledgeBaseService.getContentDetailList(contentDetailCriteria);
						if (res == null || CollectionUtil.isEmpty(res.getResult())) {
							message.append("Content Category Id 5 is invalid, ");
						}
						
						if (StringUtils.isNotEmpty(message.toString())) {
							validateResultBean.setMessage(message.toString());
							validateResultBeanList.add(validateResultBean);
							
							continue;
						}
						
						/* ---------------------------------------------------------------------- */
						contentDetailDTO = new ContentDetailDTO();
						
						contentDetailDTO.setContentCat1Id(contentCat1Id);
						contentDetailDTO.setContentCat2Id(contentCat2Id);
						contentDetailDTO.setContentCat3Id(contentCat3Id);
						contentDetailDTO.setContentCat4Id(contentCat4Id);
						contentDetailDTO.setContentCat5Id(contentCat5Id);
						contentDetailDTO.setTitle(title);
						contentDetailDTO.setQuestion(question);
//						contentDetailDTO.setSummary(summary);
						contentDetailDTO.setType(type);
						contentDetailDTO.setRegId(getUserId(request));
						
						String contentNumberGenerated = "";
						if (CONTENT_TYPE_KB.equals(type)) {
							contentNumberGenerated = sequenceGeneratorService.generateByType(SequenceType.KB);
						} else if (CONTENT_TYPE_FAQ.equals(type)) {
							contentNumberGenerated = sequenceGeneratorService.generateByType(SequenceType.FAQ);
						} else if (CONTENT_TYPE_GEN.equals(type)) {
							contentNumberGenerated = sequenceGeneratorService.generateByType(SequenceType.GEN);
						} else {
							validateResultBean.setMessage("Invalid type value");
							validateResultBeanList.add(validateResultBean);

							continue;
						}
						contentDetailDTO.setContentNumber(contentNumberGenerated);
						contentDetailDTO.setStatusCd(CONTENT_STATUS_WAIT_FOR_APPROVE);
						
						ServiceResult<Integer> serviceResult = knowledgeBaseService.insertContent(contentDetailDTO);
						if (serviceResult.isSuccess()) {
							Integer contentIdAfterSave = serviceResult.getResult();
							ServiceResult<ContentDetailDTO> serviceResult2 = knowledgeBaseService.getContentDetail(contentIdAfterSave);
							
							if (serviceResult2.isSuccess()) {
								contentDetailDTO = serviceResult2.getResult();
								
								// Call ThaiQuest
								DocumentProperty documentProperty = prepareThaiquestDocument(contentDetailDTO);
								InjectResult injectResult = thaiQuestWsService.doAdd(documentProperty);
								if (injectResult != null && "OK".equals(injectResult.getErrorCode().getValue())) {
									// ThaiQuest Success
									
									// Update reference ID from ThaiQuest to DB
									String thaiQuestId = injectResult.getID();
									contentDetailDTO.setExternalRefId(thaiQuestId);
									knowledgeBaseService.updateContent(contentDetailDTO);
									
									// Update status to Approve
									knowledgeBaseService.updateContentStatus(contentIdAfterSave, getUserId(request), CONTENT_STATUS_APPROVED);
									
									successfulRecords++;
								} else {
									// ThaiQuest Fail
									logger.info("Fail to send data to ThaiQuest" + contentDetailDTO.getContentNumber());
								}
								
								
							} else {
								//ERROR cannot find content in database 
								validateResultBean.setMessage("Cannon find content from database");
								validateResultBeanList.add(validateResultBean);
							}
							
						} else {
							// ERROR cannot insert
							validateResultBean.setMessage("Cannot insert content to database");
							validateResultBeanList.add(validateResultBean);
						}
						
						i++;
					}
				} else {
					
				}
				
				importKnowledgeBaseModelBean.setValidateResultBeanList(validateResultBeanList);
				result.setModel(importKnowledgeBaseModelBean);
				
				if (CollectionUtil.isEmpty(validateResultBeanList)) {
					result.setResultCode(JLOWebConstant.SUCCESS_CODE);
					String[] successRecords = {String.valueOf(successfulRecords)};
					result.setResultMessage( messageSource.getMessage("lbl.kb.import.success", successRecords, locale));
				} else {
					result.setResultCode(JLOWebConstant.FAIL_CODE);
					result.setResultMessage(messageSource.getMessage("lbl.kb.import.fail", null, locale));
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			
			result.setResultCode(JLOWebConstant.FAIL_CODE);
			result.setResultMessage("Cannot read csv file");
			
			return JsonUtil.toJSON(result, Boolean.TRUE);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					logger.error("Cannot close reader :: " + e.getMessage());
				}
			}
		}
		
		return JsonUtil.toJSON(result, Boolean.TRUE);
	}
	
	@RequestMapping(value = "/exportKb2Excel")
	public ModelAndView exportKb2Excel(HttpServletRequest request, HttpServletResponse response, Locale locale, final ExportKnowledgeBaseCriteria criteria)  {
		logger.info("KnowledgeBaseController - exportKb2Excel : ");
		logger.info("*****************************************************");
		
		logger.info("Exim type : " + criteria.getEximType());
		logger.info("Content cate 1 : " + criteria.getContentCat1Id());
		
		
		if ("01".equals(criteria.getEximType())) {
			List<ContentCategoryDTO> contentCategoryDTOList = new ArrayList<ContentCategoryDTO>();
			
			Integer level = null;
			String contentCateId = "";
			if (StringUtils.isNotEmpty(criteria.getContentCat1Id())) {
				level = 0;
				contentCateId = criteria.getContentCat1Id();
				
				if (StringUtils.isNotEmpty(criteria.getContentCat2Id())) {
					level = 1;
					contentCateId = criteria.getContentCat2Id();
					
					if (StringUtils.isNotEmpty(criteria.getContentCat3Id())) {
						level = 2;
						contentCateId = criteria.getContentCat3Id();
						
						if (StringUtils.isNotEmpty(criteria.getContentCat4Id())) {
							level = 3;
							contentCateId = criteria.getContentCat4Id();
							
							if (StringUtils.isNotEmpty(criteria.getContentCat5Id())) {
								level = 4;
								contentCateId = criteria.getContentCat5Id();
							}
						}
					}
				}
			}
			logger.info("Level : " + level + " Content Cate Id : " + contentCateId);
			// Get owner filter
			List<String> ownerFilter = getOwnerGroup(request, ActionType.READ.getActionCode());
			logger.info("ownerFilter : " + ownerFilter);
			if (ownerFilter != null) {
				ServiceResult<List<ContentCategoryDTO>> serviceResult = knowledgeBaseService.getContentCategoryListForExport(contentCateId, level, "01", "01", ownerFilter);
				if (serviceResult.isSuccess()) {
					contentCategoryDTOList = serviceResult.getResult();
					logger.info("Export size :: " + contentCategoryDTOList.size());
				}
			}
			
			return new ModelAndView("ExcelKnowledgeBaseCategory", "contentCategoryList", contentCategoryDTOList);
		} else if ("02".equals(criteria.getEximType())) {
			List<ContentDetailDTO> contentDetailDTOList = new ArrayList<ContentDetailDTO>();
			
			ContentDetailCriteria contentDetailCriteria = new ContentDetailCriteria();
			contentDetailCriteria.setLangCd(getLanguageCode(locale));
			if (StringUtils.isNotEmpty(criteria.getContentCat1Id())) {
				contentDetailCriteria.setContentCat1Id(criteria.getContentCat1Id());
			} else {
				contentDetailCriteria.setContentCat1Id("xx");
			}
			
			if (StringUtils.isNotEmpty(criteria.getContentCat2Id())) {
				contentDetailCriteria.setContentCat2Id(criteria.getContentCat2Id());
			} else {
				contentDetailCriteria.setContentCat2Id("xx");
			}
			
			if (StringUtils.isNotEmpty(criteria.getContentCat3Id())) {
				contentDetailCriteria.setContentCat3Id(criteria.getContentCat3Id());
			} else {
				contentDetailCriteria.setContentCat3Id("xx");
			}
			
			if (StringUtils.isNotEmpty(criteria.getContentCat4Id())) {
				contentDetailCriteria.setContentCat4Id(criteria.getContentCat4Id());
			} else {
				contentDetailCriteria.setContentCat4Id("xx");
			}
			
			if (StringUtils.isNotEmpty(criteria.getContentCat5Id())) {
				contentDetailCriteria.setContentCat5Id(criteria.getContentCat5Id());
			} else {
				contentDetailCriteria.setContentCat5Id("xx");
			}
			
			ServiceResult<List<ContentDetailDTO>> serviceResult = knowledgeBaseService.getContentDetailListForExport(contentDetailCriteria);
			if (serviceResult.isSuccess()) {
				contentDetailDTOList = serviceResult.getResult();
			}
			
			return new ModelAndView("ExcelKnowledgeBaseContent", "contentDetailDTOList", contentDetailDTOList);
		} else {
			// Error
			return null;
		}
		
	}
	
	@RequestMapping(value = "/exportKb2Csv")
	public ModelAndView exportKb2Csv(HttpServletRequest request, HttpServletResponse response, Locale locale, final ExportKnowledgeBaseCriteria criteria)  {
		logger.info("KnowledgeBaseController - exportKb2Csv : ");
		logger.info("*****************************************************");
		
		logger.info("Exim type : " + criteria.getEximType());
		logger.info("Content cate 1 : " + criteria.getContentCat1Id());
		
		
		if ("01".equals(criteria.getEximType())) {
			List<ContentCategoryDTO> contentCategoryDTOList = new ArrayList<ContentCategoryDTO>();
			
			Integer level = null;
			String contentCateId = "";
			if (StringUtils.isNotEmpty(criteria.getContentCat1Id())) {
				level = 0;
				contentCateId = criteria.getContentCat1Id();
				
				if (StringUtils.isNotEmpty(criteria.getContentCat2Id())) {
					level = 1;
					contentCateId = criteria.getContentCat2Id();
					
					if (StringUtils.isNotEmpty(criteria.getContentCat3Id())) {
						level = 2;
						contentCateId = criteria.getContentCat3Id();
						
						if (StringUtils.isNotEmpty(criteria.getContentCat4Id())) {
							level = 3;
							contentCateId = criteria.getContentCat4Id();
							
							if (StringUtils.isNotEmpty(criteria.getContentCat5Id())) {
								level = 4;
								contentCateId = criteria.getContentCat5Id();
							}
						}
					}
				}
			}
			logger.info("Level : " + level + " Content Cate Id : " + contentCateId);
			// Get owner filter
			List<String> ownerFilter = getOwnerGroup(request, ActionType.READ.getActionCode());
			logger.info("ownerFilter : " + ownerFilter);
			if (ownerFilter != null) {
				ServiceResult<List<ContentCategoryDTO>> serviceResult = knowledgeBaseService.getContentCategoryListForExport(contentCateId, level, "01", "01", ownerFilter);
				if (serviceResult.isSuccess()) {
					contentCategoryDTOList = serviceResult.getResult();
					logger.info("Export size :: " + contentCategoryDTOList.size());
				}
			}
			
			return new ModelAndView("CSVKnowledgeBaseCategory", "contentCategoryList", contentCategoryDTOList);
		} else if ("02".equals(criteria.getEximType())) {
			List<ContentDetailDTO> contentDetailDTOList = new ArrayList<ContentDetailDTO>();
			
			ContentDetailCriteria contentDetailCriteria = new ContentDetailCriteria();
			contentDetailCriteria.setLangCd(getLanguageCode(locale));
			if (StringUtils.isNotEmpty(criteria.getContentCat1Id())) {
				contentDetailCriteria.setContentCat1Id(criteria.getContentCat1Id());
			} else {
				contentDetailCriteria.setContentCat1Id(null);
			}
			
			if (StringUtils.isNotEmpty(criteria.getContentCat2Id())) {
				contentDetailCriteria.setContentCat2Id(criteria.getContentCat2Id());
			} else {
				contentDetailCriteria.setContentCat2Id(null);
			}
			
			if (StringUtils.isNotEmpty(criteria.getContentCat3Id())) {
				contentDetailCriteria.setContentCat3Id(criteria.getContentCat3Id());
			} else {
				contentDetailCriteria.setContentCat3Id(null);
			}
			
			if (StringUtils.isNotEmpty(criteria.getContentCat4Id())) {
				contentDetailCriteria.setContentCat4Id(criteria.getContentCat4Id());
			} else {
				contentDetailCriteria.setContentCat4Id(null);
			}
			
			if (StringUtils.isNotEmpty(criteria.getContentCat5Id())) {
				contentDetailCriteria.setContentCat5Id(criteria.getContentCat5Id());
			} else {
				contentDetailCriteria.setContentCat5Id(null);
			}
			
			ServiceResult<List<ContentDetailDTO>> serviceResult = knowledgeBaseService.getContentDetailListForExport(contentDetailCriteria);
			if (serviceResult.isSuccess()) {
				contentDetailDTOList = serviceResult.getResult();
			}
			
			return new ModelAndView("CSVKnowledgeBaseContent", "contentDetailDTOList", contentDetailDTOList);
		} else {
			// Error
			return null;
		}
		
	}
	
	@RequestMapping(value = "/exportAllKb2Csv")
	public ModelAndView exportAllKb2Csv(HttpServletRequest request, HttpServletResponse response, Locale locale, final ExportKnowledgeBaseCriteria criteria)  {
		logger.info("KnowledgeBaseController - exportAllKb2Csv : ");
		logger.info("*****************************************************");
		
		List<ContentDetailDTO> res = new ArrayList<ContentDetailDTO>();
		
		ServiceResult<List<ContentDetailDTO>> serviceResult = knowledgeBaseService.getAllCategoryForExport();
		if (serviceResult.isSuccess()) {
			res = serviceResult.getResult();
		}
		
		return new ModelAndView("CSVKnowledgeBaseAllCategory", "contentDetailDTOList", res);
	}
	
	private void saveAttachFile(MultipartFile imgFile, String filename, String imagePath) {
		// Save attachment file
		logger.info("[ " + imgFile.getOriginalFilename() + " ][ " + imgFile.getContentType() + " ][ " + imgFile.getSize() + " ]");
		if (!StringUtils.isNullOrEmpty(imgFile.getOriginalFilename())) {
			
			// Save file
			File myFile = new File(imagePath);
			logger.info("path : " + (imagePath) + " length : " + (imagePath).length());
			try {
				File parentDir = myFile.getParentFile();
				if(! parentDir.exists()) {
					parentDir.mkdirs(); 
				}
				imgFile.transferTo(myFile);
//				imageUpScale(myFile,128,128);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				logger.info("Cannot write message to file.");
			}
		}
		
	}
	
	
	private String getCategory(ContentDetailDTO contentDetailDTO) {
		
		String category = contentDetailDTO.getContentCat1Id();
		
		if (StringUtils.isNotEmpty(contentDetailDTO.getContentCat2Id())) {
			category +=	" " + contentDetailDTO.getContentCat2Id();
		}
		
		if (StringUtils.isNotEmpty(contentDetailDTO.getContentCat3Id())) {
			category +=	" " + contentDetailDTO.getContentCat3Id() + "_";	
		}
		
		if (StringUtils.isNotEmpty(contentDetailDTO.getContentCat4Id())) {
			category +=	" " + contentDetailDTO.getContentCat4Id() + "_";
		}
		
		if (StringUtils.isNotEmpty(contentDetailDTO.getContentCat5Id())) {
			category +=	" " + contentDetailDTO.getContentCat5Id();
		}
		
		return category;
	}
	
	private void setBean2Dto(ContentDetailModelBean bean, ContentDetailDTO dto) {
		dto.setContentId((StringUtils.isNotEmpty(bean.getContentIdTxt()) ? Integer.parseInt(bean.getContentIdTxt()): null));
		dto.setContentNumber(bean.getKbNoTxt());
		dto.setContentCat1Id((StringUtils.isEmpty(bean.getContentCat1Cmb())) ? null : bean.getContentCat1Cmb());
		dto.setContentCat2Id((StringUtils.isEmpty(bean.getContentCat2Cmb())) ? null : bean.getContentCat2Cmb());
		dto.setContentCat3Id((StringUtils.isEmpty(bean.getContentCat3Cmb())) ? null : bean.getContentCat3Cmb());
		dto.setContentCat4Id((StringUtils.isEmpty(bean.getContentCat4Cmb())) ? null : bean.getContentCat4Cmb());
		dto.setContentCat5Id((StringUtils.isEmpty(bean.getContentCat5Cmb())) ? null : bean.getContentCat5Cmb());
		dto.setStatusCd(bean.getContentStatusCd());
		dto.setQuestion(bean.getQuestionTxt());
		dto.setTitle(bean.getTitleTxt());
		dto.setSummary(bean.getDescriptionTxt());
		dto.setExternalRefId(bean.getExternalRefId());
		dto.setType(bean.getType());
	}
	
	private AttachmentProperty createAttachmentProperty(ContentAttDTO contentAttDTO) {
		AttachmentProperty attachmentProperty = new AttachmentProperty();
		String rootPath = homePath + JLOWebConstant.ROOT_FOLDER;
		String imagePath = contentAttDTO.getFilePath() + contentAttDTO.getFileName();
		
		Path path = Paths.get(rootPath + imagePath);
		byte[] data;
		try {
			data = Files.readAllBytes(path);
			logger.info("Data from file : " + data);
			attachmentProperty.setAttachData(data);
		} catch (IOException e) {
			logger.error("Cannot load file from " + path + " cause :" + e.getMessage());
		}
		
		Integer fileSize = (contentAttDTO.getFileSize() != null) ? Integer.parseInt(contentAttDTO.getFileSize()) : 0;
		attachmentProperty.setAttachLength(fileSize);
		attachmentProperty.setAttachFilename(contentAttDTO.getFileName());
		attachmentProperty.setAttachEncoding(AttachmentEncoding.None);
		attachmentProperty.setIsAttachConvert(Boolean.TRUE);
		String[] ext = contentAttDTO.getFileName().split("\\.");
		String fileExtension = "";
		if(ext.length > 1){
			fileExtension = (ext[ext.length-1]);
		}	
		String [] attachType = {mapAttachmentType(fileExtension)};
		attachmentProperty.setAttachType(attachType);
		
		return attachmentProperty;
	}
	
	private DocumentProperty prepareThaiquestDocument(ContentDetailDTO contentDetailDTO) {
		// ThaiQuest
		DocumentProperty document = new DocumentProperty();
		document.setID(null);
		document.setDisplayTime(Calendar.getInstance());
		document.setStoryTime(Calendar.getInstance());
		document.setHeadline(contentDetailDTO.getTitle());		
		document.setDescription(null);
		document.setLanguages(new String[]{"Thai"});	
		document.setStory(contentDetailDTO.getQuestion());
		document.setCategories(getCategory(contentDetailDTO));
		document.setReference(contentDetailDTO.getContentNumber());
		document.setSourceCode(JLOWebConstant.SOURCE_CALL_CENTER);
		
		// Attachments
		ServiceResult<List<ContentAttDTO>> contentServiceRes = contentService.searchContentAttByContentId(contentDetailDTO.getContentId().toString());
		if(contentServiceRes.isSuccess()) {
			List<AttachmentProperty> attachmentPropertyList = new ArrayList<AttachmentProperty>();
			List<ContentAttDTO> contentAttDTOList = contentServiceRes.getResult();
			AttachmentProperty attachmentProperty = null;
			int i = 0;
			if (CollectionUtil.isNotEmpty(contentAttDTOList)) {
				for (ContentAttDTO contentAttDTO : contentAttDTOList) {
					attachmentProperty = createAttachmentProperty(contentAttDTO);
					attachmentProperty.setAttachOrder(i++);
					attachmentPropertyList.add(attachmentProperty);
					
				}
				
				if (attachmentPropertyList.size() > 0) {
					AttachmentProperty[] stockArr = new AttachmentProperty[attachmentPropertyList.size()];
					stockArr = attachmentPropertyList.toArray(stockArr);
					document.setAttachs(stockArr);	
				}
			}
		}
		
		return document;
	}
	
	private String mapAttachmentType(String fileExtension) {
		/*
		MSPowerPoint
		Ai
		MsPowerPoint2007
		All*/
		
		if ("png".equalsIgnoreCase(fileExtension)) {
			return "Png";
		} else if ("jpg".equalsIgnoreCase(fileExtension)) {
			return "Jpg";
		} else if ("tif".equalsIgnoreCase(fileExtension)) {
			return "Tif";
		} else if ("bmp".equalsIgnoreCase(fileExtension)) {
			return "Bmp";
		} else if ("gif".equalsIgnoreCase(fileExtension)) {
			return "Gif";
		} else if ("doc".equalsIgnoreCase(fileExtension)) {
			return "MSWord";
		} else if ("xls".equalsIgnoreCase(fileExtension)) {
			return "MSExcel";
		} else if ("docx".equalsIgnoreCase(fileExtension)) {
			return "MsWord2007";
		} else if ("xlsx".equalsIgnoreCase(fileExtension)) {
			return "MsWord2007";
		} else if ("txt".equalsIgnoreCase(fileExtension)) {
			return "Text";
		} else if ("xml".equalsIgnoreCase(fileExtension)) {
			return "Xml";
		} else if ("html".equalsIgnoreCase(fileExtension)) {
			return "Html";
		} else if ("pdf".equalsIgnoreCase(fileExtension)) {
			return "Pdf";
		} else if ("zip".equalsIgnoreCase(fileExtension)) {
			return "Zip";
		} else if ("pcx".equalsIgnoreCase(fileExtension)) {
			return "Pcx";
		} else if ("mht".equalsIgnoreCase(fileExtension)) {
			return "Mht";
		} else if ("vsd".equalsIgnoreCase(fileExtension)) {
			return "Vsd";
		} else {
			return "None";
		}
	}
	
	private JsonResultBean validateFile(MultipartFile importFile, FileSizeType fileSizeType, Locale locale) {
		JsonResultBean result = new JsonResultBean();
		
		String fileExtension = "";
		String[] ext = importFile.getOriginalFilename().split("\\.");
		if(ext.length > 1){
			fileExtension = (ext[ext.length-1]);
		}
		
		
		// Validate file size 10MB = 10485760
		/*
		 * 5242880	= 5MB
		 * 10485760	= 10MB
		 * 20971520	= 20MB
		 * */
		if (importFile.getSize() >= fileSizeType.getSizing()) {
			// Over limit size 
			result.setResultCode(JLOWebConstant.FAIL_CODE);
			String [] param = {String.valueOf(fileSizeType.getValue())};
			result.setResultMessage(messageSource.getMessage("lbl.validate.limitfilesize", param, locale));
			
		} else if (!"csv".equalsIgnoreCase(fileExtension)) {
			// Validate csv extension
			result.setResultMessage(messageSource.getMessage("lbl.validate.file.format.csv", null, locale));
			
		} else {
			result.setResultCode(JLOWebConstant.SUCCESS_CODE);
		}
		
		return result;
	}
	
}
