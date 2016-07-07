package com.locus.jlo.web.view;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.locus.common.utils.CollectionUtil;
import com.locus.common.utils.DateTimeUtils;
import com.locus.jlo.web.bean.dto.ContentDetailDTO;

public class KnowledgeBaseContentCsvView extends BaseCsvAbstractView {

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String formatDate = DateTimeUtils.formatDate(Calendar.getInstance().getTime(), "yyyyMMdd_HHmmss");
		
		@SuppressWarnings("unchecked")
		List<ContentDetailDTO> contentDetailDTOList = (List<ContentDetailDTO>) model.get("contentDetailDTOList");
	   
	    response.setHeader("Content-Disposition","attachment; filename=\"exportContent_" + formatDate + ".csv\"");
	    response.setContentType("text/csv; charset=UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(response.getOutputStream(), "TIS620"));
	    
	    try {
	    	StringBuffer text = null;
	    	setHeader(writer);
	    	
	    	if (CollectionUtil.isNotEmpty(contentDetailDTOList)) {
				for (ContentDetailDTO contentDetailDTO : contentDetailDTOList) {
					text = new StringBuffer();
					text.append(setData( contentDetailDTO.getContentId() ));
					text.append(setData( contentDetailDTO.getContentNumber() ));
					text.append(setData( contentDetailDTO.getContentCat1Id() ));
					text.append(setData( contentDetailDTO.getContentCat1Name() ));
					text.append(setData( contentDetailDTO.getContentCat2Id() ));
					text.append(setData( contentDetailDTO.getContentCat2Name() ));
					text.append(setData( contentDetailDTO.getContentCat3Id() ));
					text.append(setData( contentDetailDTO.getContentCat3Name() ));
					text.append(setData( contentDetailDTO.getContentCat4Id() ));
					text.append(setData( contentDetailDTO.getContentCat4Name() ));
					text.append(setData( contentDetailDTO.getContentCat5Id() ));
					text.append(setData( contentDetailDTO.getContentCat5Name() ));
					text.append(setData( contentDetailDTO.getTitle() ));
					text.append(setData( contentDetailDTO.getQuestion() ));
					text.append(setData( contentDetailDTO.getType(), true ));
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
		text.append("Content ID" + ",");
		text.append("Content Number" + ",");
		text.append("Content Category1 ID" + ",");
		text.append("Content Category1 Name" + ",");
		text.append("Content Category2 ID" + ",");
		text.append("Content Category2 Name" + ",");
		text.append("Content Category3 ID" + ",");
		text.append("Content Category3 Name" + ",");
		text.append("Content Category4 ID" + ",");
		text.append("Content Category4 Name" + ",");
		text.append("Content Category5 ID" + ",");
		text.append("Content Category5 Name" + ",");
		text.append("Question" + ",");
		text.append("Answer" + ",");
		text.append("Type");
		writer.write(text.toString());
		writer.newLine();
	}
}
