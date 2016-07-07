package com.locus.jlo.web.service;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

	@Autowired
	private MessageSource messageSource;
	
	public String getMessage(String code){
		return getMessage(code, code);
	}
	
	public String getMessage(String code, String defaultMessage){
		return getMessage(code, defaultMessage, null);
	}
	
	public String getMessage(String code, Object[] args){
		Locale locale = LocaleContextHolder.getLocale();
		try{
			return messageSource.getMessage(code, args, locale);
		}catch(Exception e){
			return code;
		}
	}
	
	public String getMessage(String code, String defaultMessage, Object[] args){
		Locale locale = LocaleContextHolder.getLocale();
		try{
			return messageSource.getMessage(code, args, locale);
		}catch(Exception e){
			return defaultMessage;
		}
	}
}
