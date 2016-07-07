package com.locus.jlo.web.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ExceptionController {
	@Autowired
	private MessageSource messageSource;

	private Logger logger = Logger.getLogger(getClass());

	@RequestMapping(value = "/exception")
	public ModelAndView exception(Model model, HttpServletRequest request, Locale local) {
		//model.addAttribute("pageTitle", messageSource.getMessage("home.title", null, local));
		logger.info("ExceptionController - exception");
		logger.info("*******************************************");
		return new ModelAndView("exception");
	}
	
	@RequestMapping(value = "/pagenotfound")
	public ModelAndView pagenotfound(Model model, HttpServletRequest request, Locale local) {
		//model.addAttribute("pageTitle", messageSource.getMessage("home.title", null, local));
		logger.info("ExceptionController - pagenotfound");
		logger.info("*******************************************");
		return new ModelAndView("pagenotfound");
	}
	
	@RequestMapping(value = "/notauthorized")
	public ModelAndView notauthorized(Model model, HttpServletRequest request, Locale local) {
		//model.addAttribute("pageTitle", messageSource.getMessage("home.title", null, local));
		logger.info("ExceptionController - notauthorized");
		logger.info("*******************************************");
		return new ModelAndView("notauthorized");
	}
	
	@RequestMapping(value = "/notsupport")
	public ModelAndView notsupport(Model model, HttpServletRequest request, Locale local) {
		//model.addAttribute("pageTitle", messageSource.getMessage("home.title", null, local));
		logger.info("ExceptionController - notsupport");
		logger.info("*******************************************");
		return new ModelAndView("notsupport");
	}
	
	@RequestMapping(value = "/invalidparam")
	public ModelAndView invalidParam(Model model, HttpServletRequest request, Locale local) {
		//model.addAttribute("pageTitle", messageSource.getMessage("home.title", null, local));
		logger.info("ExceptionController - invalidparam");
		logger.info("*******************************************");
		return new ModelAndView("invalidparam");
	}
	
}
