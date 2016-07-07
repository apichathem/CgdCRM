/**
 * 
 */
package com.locus.jlo.web.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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
import com.locus.jlo.web.bean.dto.HolidayDTO;
import com.locus.jlo.web.bean.modelbean.FullCalendarEventSourcesModelBean;
import com.locus.jlo.web.bean.modeljson.DatatableModelBean;
import com.locus.jlo.web.constant.JLOWebConstant;
import com.locus.jlo.web.service.HolidayInfoService;
import com.locus.jlo.web.service.SequenceGeneratorService;

/**
 * @author Administrator
 * 
 */
@Controller
public class HolidayManagementController extends BaseController {
	private Logger logger = Logger.getLogger(getClass());
	private static final String HOLIDAY_MODEL = "holidayModelBean";
	
	@Autowired
	private MessageSource messageSource;

	@Autowired
	private HolidayInfoService holidayInfoService;
	
	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;
	/**
	 * 
	 */
	public HolidayManagementController() {
	}

	@RequestMapping(value = "/holidayManagement")
	public ModelAndView holidayManagement(Model model, HttpServletRequest request, Locale local) {

		model.addAttribute("pageTitle", messageSource.getMessage("holiday.title",null, local));
		logger.info("HolidayManagementController - holidayManagement");
		logger.info("*******************************************");
		HolidayDTO holidayModelBean = new HolidayDTO();
		
		// Set menuId
		setMenuId(request);
		
		return new ModelAndView("holidayManagement", HOLIDAY_MODEL, holidayModelBean);

	}
	
	@RequestMapping(value = "/getHolidayList", headers = { "Accept=application/json" })
	public @ResponseBody String getHolidayList(HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception {
		logger.info("HolidayManagementController - getHolidayList");
		logger.info("*****************************************************");
		
		Integer year = Calendar.getInstance().get(Calendar.YEAR);
		logger.info("Current year : " + year);
		
		DatatableModelBean datatableModelBean = new DatatableModelBean();
		
		ServiceResult<List<HolidayDTO>> serviceResult =  holidayInfoService.searchHolidayByYear(year, getLanguageCode(locale));
		
		if (serviceResult.isSuccess()) {
			List<HolidayDTO> tpHolidayResult = serviceResult.getResult();
			Integer totalRecords = tpHolidayResult.size();
			
			// Set server prop return result
			datatableModelBean.setsEcho(getSecho(request));
			datatableModelBean.setiTotalDisplayRecords(totalRecords);
			datatableModelBean.setiTotalRecords(totalRecords);
			datatableModelBean.setAaData(tpHolidayResult);
			
		} else {
			showWebMessage(request, serviceResult.getResponseCode(), serviceResult.getResponseDescription());
		}
		
		return JsonUtil.toJSON( datatableModelBean , Boolean.TRUE);
	}
	
	@RequestMapping(value = "/getHoliday", headers = { "Accept=application/json" })
	public @ResponseBody String getHoliday(HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception {
		logger.info("HolidayManagementController - getHoliday");
		logger.info("*****************************************************");
		
		List<FullCalendarEventSourcesModelBean> fullCalendarEventSourcesModelList = new ArrayList<FullCalendarEventSourcesModelBean>();
		FullCalendarEventSourcesModelBean fullCalendarEventSourcesModel = null;
		
		Integer year = Calendar.getInstance().get(Calendar.YEAR);
		logger.info("Current year : " + year);
		
		ServiceResult<List<HolidayDTO>> res = holidayInfoService.searchHolidayByYear(year, getLanguageCode(locale));
		
		if (res.isSuccess()) {
			List<HolidayDTO> tpHolidayInfoList = res.getResult();
			if (CollectionUtil.isNotEmpty(tpHolidayInfoList)) {
				for (HolidayDTO tpHolidayDTO : tpHolidayInfoList) {
					logger.info(tpHolidayDTO.toString());
					fullCalendarEventSourcesModel = new FullCalendarEventSourcesModelBean();
					fullCalendarEventSourcesModel.setAllDay(true);
					fullCalendarEventSourcesModel.setId(tpHolidayDTO.getHolidayId().toString());
					fullCalendarEventSourcesModel.setStart(tpHolidayDTO.getHolidayDate());
					fullCalendarEventSourcesModel.setTitle(tpHolidayDTO.getHolidayName());
					
					fullCalendarEventSourcesModelList.add(fullCalendarEventSourcesModel);
				}
			}
		} else {
			showWebMessage(request, res.getResponseCode(), res.getResponseDescription());
		}
		
		return JsonUtil.toJSON( fullCalendarEventSourcesModelList , Boolean.TRUE);
	}
	
	
	@RequestMapping(value = {"/insertHoliday", "/updateHoliday"}, method = RequestMethod.GET)
	public ModelAndView saveHolidayGet(@ModelAttribute(HOLIDAY_MODEL) HolidayDTO holidayModelBean, Model model, HttpServletRequest request, Locale local) {
		//Prevent Post-Redirect
		return new ModelAndView("holidayManagement", HOLIDAY_MODEL, new HolidayDTO());
	}
	
	@RequestMapping(value = "/insertHoliday", method = RequestMethod.POST, headers = { "Accept=application/json" })
	public @ResponseBody String insertHoliday(@ModelAttribute(HOLIDAY_MODEL) HolidayDTO holidayModelBean, Model model, HttpServletRequest request, Locale locale) {
		logger.info("HolidayManagementController - insertHoliday");
		logger.info("*******************************************");
		return saveHoliday(holidayModelBean, model, request, locale);
	}
	
	@RequestMapping(value = "/updateHoliday", method = RequestMethod.POST, headers = { "Accept=application/json" })
	public @ResponseBody String updateHoliday(@ModelAttribute(HOLIDAY_MODEL) HolidayDTO holidayModelBean, Model model, HttpServletRequest request, Locale locale) {
		logger.info("HolidayManagementController - updateHoliday");
		logger.info("*******************************************");
		return saveHoliday(holidayModelBean, model, request, locale);
	}
	
	private String saveHoliday(HolidayDTO holidayModelBean, Model model, HttpServletRequest request, Locale locale) {
		logger.info("saveHoliday Mode :" + ((holidayModelBean.getHolidayId() != null) ? "UPDATE" : "INSERT"));
		logger.info(holidayModelBean.toString());
		model.addAttribute("pageTitle", messageSource.getMessage("holiday.title",null, locale));
		
		HolidayDTO tpHolidayInfo = null;
		
		Calendar holidayCalendar = Calendar.getInstance();
		holidayCalendar.setTime(DateTimeUtils.parseDate(holidayModelBean.getHolidayDate(), "dd/MM/yyyy"));
		
		if (holidayModelBean.getHolidayId() != null) {
			ServiceResult<HolidayDTO> res = holidayInfoService.searchHolidayById(holidayModelBean.getHolidayId());
			if (res.isSuccess()) {
				tpHolidayInfo = res.getResult();
			} else {
				tpHolidayInfo = new HolidayDTO();
				tpHolidayInfo.setHolidayId(holidayModelBean.getHolidayId());
			}
			
			tpHolidayInfo.setChgId(getUserId(request));
		} else {
			tpHolidayInfo = new HolidayDTO();
			tpHolidayInfo.setRegId(getUserId(request));
		}
		
		tpHolidayInfo.setHolidayDate(holidayModelBean.getHolidayDate());
		tpHolidayInfo.setHolidayName(holidayModelBean.getHolidayName());
		tpHolidayInfo.setTypeCd(holidayModelBean.getTypeCd());
		tpHolidayInfo.setYear(holidayCalendar.get(Calendar.YEAR));
		
		try {
			if (holidayModelBean.getHolidayId() != null) {
				tpHolidayInfo.setUpdateBy(getUserId(request).toString());
				tpHolidayInfo = holidayInfoService.updateHoliday(tpHolidayInfo);
			} else {
				tpHolidayInfo.setCreateBy(getUserId(request).toString());
				tpHolidayInfo = holidayInfoService.insertHoliday(tpHolidayInfo);
			}
			
			showWebMessage(request, JLOWebConstant.SUCCESS_CODE, messageSource.getMessage("lbl.save.success", null, locale), messageSource.getMessage("holidayManagement.topic", null, locale));
		} catch (Exception e) {
			showWebMessage(request, JLOWebConstant.FAIL_CODE,  messageSource.getMessage("lbl.save.fail", null, locale), messageSource.getMessage("holidayManagement.topic", null, locale));
		}
		
		String url = "/holidayManagement.htm";
		return JsonUtil.toJSON(url, Boolean.TRUE);
	}
	
	@RequestMapping(value = "/getHolidayDetail", headers = { "Accept=application/json" })
	public @ResponseBody String getHolidayDetail(HttpServletRequest request, HttpServletResponse response, Locale locale) {
		String holidayId = request.getParameter("holidayId");
		HolidayDTO tpHolidayInfo = null;
		
		ServiceResult<HolidayDTO> res = holidayInfoService.searchHolidayById(Integer.parseInt(holidayId));
		if (res.isSuccess()) {
			tpHolidayInfo = res.getResult();
		} else {
			showWebMessage(request, res.getResponseCode(), res.getResponseDescription());
		}
		
		return JsonUtil.toJSON( tpHolidayInfo , Boolean.TRUE);
	}
	
	@RequestMapping(value = "/deleteHoliday", method = RequestMethod.GET)
	public ModelAndView deleteHoliday(@ModelAttribute(HOLIDAY_MODEL) HolidayDTO holidayModelBean, Model model, HttpServletRequest request, Locale local) {

		model.addAttribute("pageTitle", messageSource.getMessage("holiday.title",null, local));
		logger.info("HolidayManagementController - deleteHoliday");
		logger.info("*******************************************");
		
		String holidayId = request.getParameter("holidayId");
		
		try {
			holidayInfoService.deleteHoliday(Integer.parseInt(holidayId));
			showWebMessage(request, JLOWebConstant.SUCCESS_CODE, messageSource.getMessage("lbl.delete.success", null, local), messageSource.getMessage("holidayManagement.topic", null, local));
		} catch (Exception e) {
			showWebMessage(request, JLOWebConstant.FAIL_CODE,  messageSource.getMessage("lbl.delete.fail", null, local), messageSource.getMessage("holidayManagement.topic", null, local));
		}
		
		return new ModelAndView("holidayManagement", HOLIDAY_MODEL, new HolidayDTO());
	}

}
