package com.locus.jlo.web.controller;

import java.util.Locale;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.locus.common.utils.JsonUtil;
import com.locus.common.utils.ThaiUtil;

import com.locus.jlo.web.service.AttService;
import com.locus.jlo.web.service.ServiceRequestService;
import com.locus.jlo.web.constant.JLOWebConstant;
import com.locus.jlo.web.bean.modeljson.JsonResultBean;

@Controller
public class ActivityCommonController extends BaseController {

	@Autowired
	private ServiceRequestService serviceRequestService;


	@Autowired
	private AttService attService;

	@Autowired
	ServletContext context;

	private Logger logger = Logger.getLogger(getClass());

	@RequestMapping(value = "/checkStatusActivityByRefNo", method = RequestMethod.POST, headers = { "Accept=application/json; charset=UTF-8" })
	public @ResponseBody
	String checkStatusActivityByRefNo(HttpServletRequest request, Locale locale, @RequestParam(value = "referenceNo", required = true) String referenceNo) throws Exception {

		JsonResultBean result = new JsonResultBean();

		logger.info("ReferenceNo :: " + referenceNo);

		int checkCountRow = 0;
		StringBuilder strJsonBuid = new StringBuilder("");

		try {
			checkCountRow = serviceRequestService.countActivityUnderRefDocNo(referenceNo);
			logger.info("CheckCountRow  " + checkCountRow);
			String count = String.valueOf(checkCountRow);
			strJsonBuid.append("{\"count\":" + count + "}");
		} catch (Exception e) {
			logger.error(e);
			result.setResultCode(JLOWebConstant.FAIL_CODE);
			result.setResultMessage(e.getMessage());
			return JsonUtil.toJSON(result, Boolean.TRUE);
		}
		logger.info("strJsonBuid : " + strJsonBuid.toString());
		return ThaiUtil.encodeUnicode(strJsonBuid.toString());
	}

}