package com.locus.jlo.web.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.locus.common.domain.ServiceResult;
import com.locus.common.utils.DateTimeUtils;
import com.locus.common.utils.JsonUtil;
import com.locus.common.utils.StringUtils;
import com.locus.jlo.web.bean.dto.SlaDTO;
import com.locus.jlo.web.bean.modelbean.SLAModelBean;
import com.locus.jlo.web.bean.modeljson.DatatableModelBean;
import com.locus.jlo.web.bean.modeljson.JsonResultBean;
import com.locus.jlo.web.constant.JLOWebConstant;
import com.locus.jlo.web.service.SlaService;

@Controller
public class SLAManagementController extends BaseController {
	private static final Logger logger = Logger.getLogger(SLAManagementController.class);
	private static final String MODEL_BEAN = "SLAModelBean";
	
	@Autowired
	private SlaService slaService;
	
	@RequestMapping(value = "/slaManagement")
	public ModelAndView slaManagement(Model model, HttpServletRequest request, Locale locale){
		model.addAttribute("pageTitle", messageSource.getMessage("sla.title", null, locale));
		setMenuId(request);
		
		logger.info("SLAManagement - slaManagement");
		logger.info("*******************************************");
		
		return new ModelAndView("slaManagement", MODEL_BEAN, new SLAModelBean());
	}
	
	@RequestMapping(value = "/getSlaList", headers = { "Accept=application/json" })
	public @ResponseBody String getSlaList(HttpServletRequest request, Pageable pageable, Locale locale){
		logger.info("SLAManagement - getSlaList");
		logger.info("*******************************************");
		
		String slaName = request.getParameter("slaname");
		logger.info("Criteria :" + slaName);
		
		DatatableModelBean result =  new DatatableModelBean();
		ServiceResult<Page<SlaDTO>> serviceResult = slaService.searchByCriteria(slaName, pageable, getLanguageCode(locale));
		if(serviceResult.isSuccess()){
			Page<SlaDTO> page = serviceResult.getResult();
			Long totalElement = page.getTotalElements();
			result.setsEcho(getSecho(request));
			result.setiTotalDisplayRecords(totalElement.intValue());
			result.setiTotalRecords(totalElement.intValue());
			result.setAaData(page.getContent());
		}
		return JsonUtil.toJSON(result, true);
	}
	
	@RequestMapping(value = "/getSlaById", headers = { "Accept=application/json" })
	public @ResponseBody String getSlaById(HttpServletRequest request, Pageable pageable, Locale locale){
		logger.info("SLAManagement - getSlaById");
		logger.info("*******************************************");
		
		String slaId = request.getParameter("slaId");
		logger.info("Criteria :" + slaId);
		
		JsonResultBean result =  new JsonResultBean();
		ServiceResult<SlaDTO> serviceResult = slaService.seachById(Integer.parseInt(slaId));
		if(serviceResult.isSuccess()) {
			result.setResultCode(JLOWebConstant.SUCCESS_CODE);
			result.setResultMessage(JLOWebConstant.SUCCESS_DESC);
			result.setModel(serviceResult.getResult());
		} else {
			result.setResultCode(serviceResult.getResponseCode());
			result.setResultMessage(serviceResult.getResponseDescription());
		}
		
		return JsonUtil.toJSON(result, true);
	}
	
	@RequestMapping(value = "/initSla")
	public ModelAndView initSla(Model model, HttpServletRequest request, Locale locale){
		model.addAttribute("pageTitle", messageSource.getMessage("sla.title", null, locale));
		SLAModelBean slaModelBean = new SLAModelBean();
		
		String slaId = request.getParameter("slaId");
		if (StringUtils.isNotEmpty(slaId)) {
			// Modify SLA
			ServiceResult<SlaDTO> serviceResult = slaService.seachById(Integer.valueOf(slaId));
			if (serviceResult.isSuccess()) {
				SlaDTO slaDTO = serviceResult.getResult();
				setDto2Bean(slaDTO, slaModelBean);
			}
			slaModelBean.setMode(JLOWebConstant.MODE_UPDATE);
		} else {
			// New SLA
			slaModelBean.setMode(JLOWebConstant.MODE_INSERT);
		}
		
		return new ModelAndView("slaDeatil", MODEL_BEAN, slaModelBean);
	}
	
	@RequestMapping(value = "/saveSla", headers = { "Accept=application/json" })
	public @ResponseBody String saveRole(@ModelAttribute(MODEL_BEAN) SLAModelBean slaModelBean, HttpServletRequest request){
		SlaDTO dto = new SlaDTO();
		
		setBean2Dto(slaModelBean, dto);
		if(slaModelBean.isInsertMode()){
			dto.setRegId(getUserId(request));
			ServiceResult<Long> serviceResult = slaService.insert(dto);
			slaModelBean.setSlaId(serviceResult.getResult().intValue());
		}else{
			dto.setChgId(getUserId(request));
			slaService.update(dto);
		}
		String url="/initSla.htm?slaId="+slaModelBean.getSlaId();
		return JsonUtil.toJSON(url, true);
	}
	
	private void setDto2Bean(SlaDTO dto, SLAModelBean bean) {
		bean.setSlaId(dto.getSlaId());
		bean.setSlaName(dto.getSlaName());
		bean.setSlaUnit(dto.getSlaUnit());
		bean.setSlaUomCd(dto.getSlaUomCd());
		bean.setDescp(dto.getDescp());
		bean.setStatusCd(dto.getStatusCd());
		
		bean.setCreateBy(dto.getCreateBy());
		bean.setCreateDate(DateTimeUtils.formatDateTime(dto.getRegDt()));
		bean.setUpdateBy(dto.getUpdateBy());
		bean.setUpdateDate(DateTimeUtils.formatDateTime(dto.getChgDt()));
	}
	
	private void setBean2Dto(SLAModelBean bean, SlaDTO dto) {
		dto.setSlaId(bean.getSlaId());
		dto.setSlaName(bean.getSlaName());
		dto.setSlaUnit(bean.getSlaUnit());
		dto.setSlaUomCd(bean.getSlaUomCd());
		dto.setDescp(bean.getDescp());
		dto.setStatusCd(bean.getStatusCd());
	}
}
