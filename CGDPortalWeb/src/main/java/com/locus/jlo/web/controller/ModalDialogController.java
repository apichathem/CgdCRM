package com.locus.jlo.web.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.locus.jlo.web.controller.BaseController;

/**
 * 
 * @author Mr.BoonOom
 *
 */
@Controller
public class ModalDialogController extends BaseController {
	
	@Autowired
	private MessageSource messageSource;

	private Logger logger = Logger.getLogger(getClass());
	
	public ModalDialogController() {
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping(value = "/openModalDialog")
	public ModelAndView openModalDialog(Model model, Locale local, HttpServletRequest request, HttpServletResponse response
			,@RequestParam(value = "pageModalName", required = true) String pageModalName
			,@RequestParam(value = "modalHeaderCode", required = true) String modalHeaderCaption
			,@RequestParam(value="callbackfn",required = false) String callbackfn){
		
		model.addAttribute("headerTitle", messageSource.getMessage(modalHeaderCaption, null, local));
		model.addAttribute("callbackfn", callbackfn);
		
		logger.info("+++ ModalDialogController +++ ");
		logger.info("PageModalName : "+pageModalName);
		logger.info("ModalHeaderCaption : "+modalHeaderCaption);
		logger.info("CallBackfn : "+callbackfn);
		
		
		
		String pageDialogReturn = "";
		
		if(null != pageModalName &&  !pageModalName.equals("")){
			pageDialogReturn = pageModalName;
		}
		
		return new ModelAndView(pageDialogReturn);
	}
	
	

}
