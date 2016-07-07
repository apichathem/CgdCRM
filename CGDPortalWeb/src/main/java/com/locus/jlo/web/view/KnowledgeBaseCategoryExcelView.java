package com.locus.jlo.web.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.locus.common.utils.CollectionUtil;
import com.locus.jlo.web.bean.dto.ContentCategoryDTO;

public class KnowledgeBaseCategoryExcelView extends AbstractExcelView {
    
	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HSSFSheet excelSheet = workbook.createSheet("KnowledgeBase Category List");
		setExcelHeader(excelSheet);
		
		List<ContentCategoryDTO> contentCategoryDTOList = (List<ContentCategoryDTO>) model.get("contentCategoryList");
		setExcelRows(excelSheet,contentCategoryDTOList);
	}
	
	@Override
	protected void prepareResponse(HttpServletRequest request, HttpServletResponse response) {
		String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",  "KnowledgeBaseCategory.csv");
        response.setContentType("text/csv");
        response.setHeader(headerKey, headerValue);
		super.prepareResponse(request, response);
	}
	
	public void setExcelHeader(HSSFSheet excelSheet) {
		HSSFRow excelHeader = excelSheet.createRow(0);
		excelHeader.createCell(0).setCellValue("Level");
		excelHeader.createCell(1).setCellValue("Category ID");
		excelHeader.createCell(2).setCellValue("Mapping ID");
		excelHeader.createCell(3).setCellValue("Category Name");
		excelHeader.createCell(4).setCellValue("Parent Category ID");
		excelHeader.createCell(5).setCellValue("Parent Mapping ID");
		excelHeader.createCell(6).setCellValue("Content Type Code");
		excelHeader.createCell(7).setCellValue("Description");
		excelHeader.createCell(8).setCellValue("SLA ID");
	}
	
	public void setExcelRows(HSSFSheet excelSheet, final List<ContentCategoryDTO> contentCategoryDTOList){
		int record = 1;
		if (CollectionUtil.isNotEmpty(contentCategoryDTOList)) {
			for (ContentCategoryDTO contentCategoryDTO : contentCategoryDTOList) {
				HSSFRow excelRow = excelSheet.createRow(record++);
				
				excelRow.createCell(0).setCellValue(contentCategoryDTO.getLevel());
				excelRow.createCell(1).setCellValue(contentCategoryDTO.getContentCatId());
				excelRow.createCell(2).setCellValue("");
				excelRow.createCell(3).setCellValue(contentCategoryDTO.getCatName());
				excelRow.createCell(4).setCellValue(contentCategoryDTO.getParentCatId());
				excelRow.createCell(5).setCellValue("");
				excelRow.createCell(6).setCellValue(contentCategoryDTO.getContentTypeCd());
				excelRow.createCell(7).setCellValue(contentCategoryDTO.getDescp());
				excelRow.createCell(8).setCellValue(contentCategoryDTO.getSlaId());
			}
		}
	}

}
