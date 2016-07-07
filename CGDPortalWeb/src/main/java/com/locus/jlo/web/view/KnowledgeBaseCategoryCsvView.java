package com.locus.jlo.web.view;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.locus.common.utils.CollectionUtil;
import com.locus.common.utils.DateTimeUtils;
import com.locus.jlo.web.bean.dto.ContentCategoryDTO;

public class KnowledgeBaseCategoryCsvView extends BaseCsvAbstractView {
	Logger logger = Logger.getLogger(getClass());
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String formatDate = DateTimeUtils.formatDate(Calendar.getInstance().getTime(), "yyyyMMdd_HHmmss");
		
		@SuppressWarnings("unchecked")
		List<ContentCategoryDTO> contentCategoryDTOList = (List<ContentCategoryDTO>) model.get("contentCategoryList");
	   
	    response.setHeader("Content-Disposition","attachment; filename=\"exportCategory_" + formatDate + ".csv\"");
	    response.setContentType("text/csv; charset=UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(response.getOutputStream(), "TIS620"));
	    
	    try {
	    	StringBuffer text = null;
	    	setHeader(writer);
	    	
	    	if (CollectionUtil.isNotEmpty(contentCategoryDTOList)) {
				for (ContentCategoryDTO contentCategoryDTO : contentCategoryDTOList) {
					text = new StringBuffer();
					text.append(setData( contentCategoryDTO.getLevel() ));
					text.append(setData( contentCategoryDTO.getContentCatId() ));
					text.append(setData( "" ));
					text.append(setData( contentCategoryDTO.getCatName() ));
					text.append(setData( contentCategoryDTO.getParentCatId() ));
					text.append(setData( "" ));
					text.append(setData( contentCategoryDTO.getContentTypeCd() ));
					text.append(setData( contentCategoryDTO.getDescp() ));
					text.append(setData( contentCategoryDTO.getSlaId(), true ));
					writer.write(text.toString());
					writer.newLine();
				}
	    	}
	    } finally {
	    	writer.flush(); 
	    	writer.close();
	    }

	}
	
	private void setHeader(BufferedWriter writer) throws IOException {
		StringBuffer text = new StringBuffer();
		text.append("Level" + ",");
		text.append("Category ID" + ",");
		text.append("Mapping ID" + ",");
		text.append("Category Name" + ",");
		text.append("Parent Category ID" + ",");
		text.append("Parent Mapping ID" + ",");
		text.append("Content Type Code" + ",");
		text.append("Description" + ",");
		text.append("SLA ID");
		writer.write(text.toString());
		writer.newLine();
	}
	
}
