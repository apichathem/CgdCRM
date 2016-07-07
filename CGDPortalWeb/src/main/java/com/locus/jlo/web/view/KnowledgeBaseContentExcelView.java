package com.locus.jlo.web.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.locus.common.utils.CollectionUtil;
import com.locus.jlo.web.bean.dto.ContentDetailDTO;

@Component
public class KnowledgeBaseContentExcelView extends AbstractExcelView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HSSFSheet excelSheet = workbook.createSheet("KnowledgeBase Category List");
		
		setExcelHeader(excelSheet, request);
		
		@SuppressWarnings("unchecked")
		List<ContentDetailDTO> contentDetailDTOList = (List<ContentDetailDTO>) model.get("contentDetailDTOList");
		setExcelRows(excelSheet,contentDetailDTOList);

	}

	@Override
	protected void prepareResponse(HttpServletRequest request, HttpServletResponse response) {
		String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",  "KnowledgeBaseContent.csv");
        response.setContentType("text/csv");
        response.setHeader(headerKey, headerValue);
		super.prepareResponse(request, response);
	}
	
	public void setExcelHeader(HSSFSheet excelSheet, HttpServletRequest request) {
		//Locale locale = (Locale) request.getSession().getAttribute("CURRENT_LANG");
		 
		HSSFRow excelHeader = excelSheet.createRow(0);
		excelHeader.createCell(0).setCellValue("Content ID");
		excelHeader.createCell(1).setCellValue("Content Number");
		excelHeader.createCell(2).setCellValue("Content Category1 ID");
		excelHeader.createCell(3).setCellValue("Content Category1 Name");
		excelHeader.createCell(4).setCellValue("Content Category2 ID");
		excelHeader.createCell(5).setCellValue("Content Category2 Name");
		excelHeader.createCell(6).setCellValue("Content Category3 ID");
		excelHeader.createCell(7).setCellValue("Content Category3 Name");
		excelHeader.createCell(8).setCellValue("Content Category4 ID");
		excelHeader.createCell(9).setCellValue("Content Category4 Name");
		excelHeader.createCell(10).setCellValue("Content Category5 ID");
		excelHeader.createCell(11).setCellValue("Content Category5 Name");
		excelHeader.createCell(12).setCellValue("Title");
		excelHeader.createCell(13).setCellValue("Question");
		excelHeader.createCell(14).setCellValue("Summary");
	}
	
	public void setExcelRows(HSSFSheet excelSheet, final List<ContentDetailDTO> contentDetailDTOList){
		int record = 1;
		if (CollectionUtil.isNotEmpty(contentDetailDTOList)) {
			for (ContentDetailDTO contentDetailDTO : contentDetailDTOList) {
				HSSFRow excelRow = excelSheet.createRow(record++);
				excelRow.createCell(0).setCellValue(contentDetailDTO.getContentId());
				excelRow.createCell(1).setCellValue(contentDetailDTO.getContentNumber());
				excelRow.createCell(2).setCellValue(contentDetailDTO.getContentCat1Id());
				excelRow.createCell(3).setCellValue(contentDetailDTO.getContentCat1Name());
				excelRow.createCell(4).setCellValue(contentDetailDTO.getContentCat2Id());
				excelRow.createCell(5).setCellValue(contentDetailDTO.getContentCat2Name());
				excelRow.createCell(6).setCellValue(contentDetailDTO.getContentCat3Id());
				excelRow.createCell(7).setCellValue(contentDetailDTO.getContentCat3Name());
				excelRow.createCell(8).setCellValue(contentDetailDTO.getContentCat4Id());
				excelRow.createCell(9).setCellValue(contentDetailDTO.getContentCat4Name());
				excelRow.createCell(10).setCellValue(contentDetailDTO.getContentCat5Id());
				excelRow.createCell(11).setCellValue(contentDetailDTO.getContentCat5Name());
				excelRow.createCell(12).setCellValue(contentDetailDTO.getTitle());
				excelRow.createCell(13).setCellValue(contentDetailDTO.getQuestion());
				excelRow.createCell(14).setCellValue(contentDetailDTO.getSummary());
			}
		}
	}
}
