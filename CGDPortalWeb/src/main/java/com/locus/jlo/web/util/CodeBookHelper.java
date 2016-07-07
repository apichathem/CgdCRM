package com.locus.jlo.web.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.context.i18n.LocaleContextHolder;

import com.locus.jlo.web.bean.modelbean.CodebookModelBean;

public class CodeBookHelper {
	
	private static Logger logger = Logger.getLogger(CodeBookHelper.class);
	
	public static Map<String,Map<String, List<CodebookModelBean>>> CODEBOOK_LIST = null;
	
	public static Map<String, List<CodebookModelBean>> getCodeBookList(){
		synchronized (CodeBookHelper.class) {
			String locale = LocaleContextHolder.getLocale().getLanguage().toUpperCase();
			return CODEBOOK_LIST.get(locale);
		}				
	}
	public static void resetCodeBook() {
		synchronized (CodeBookHelper.class) {
			SessionHelper.resetCodeBook();
		}		
	}
	
	public static CodebookModelBean getByCodeTypeAndCodeId(HttpServletRequest request, String codeType, String codeId){
		CodebookModelBean tpCodebook = null;
		try{
			java.util.Map<String,java.util.List<CodebookModelBean>> map = getCodeBookList();
			if(map!=null){
				List<CodebookModelBean> list = map.get(codeType);
				if(list!=null){
					for (CodebookModelBean obj : list) {
						if(codeId.equalsIgnoreCase(obj.getCodeId())){
							tpCodebook = obj;
						}
					}
				}
			}
		}catch(Exception e){
			logger.error(e);
		}
		
		return tpCodebook;		
	}
	
	public static List<CodebookModelBean> getByCodeTypeAndParentId(HttpServletRequest request, String codeType, String parentId){
		List<CodebookModelBean> listout = new ArrayList<CodebookModelBean>();
		try{
			java.util.Map<String,java.util.List<CodebookModelBean>> map = getCodeBookList();
			if(map!=null){
				List<CodebookModelBean> list = map.get(codeType);	
				if(list!=null){
					for (CodebookModelBean obj : list) {
						if(parentId.equalsIgnoreCase(obj.getParentId())){
							listout.add(obj);
						}
					}
				}
			}
		}catch(Exception e){
			logger.error(e);
		}
		
		return listout;		
	}
}
