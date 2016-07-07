package com.locus.jlo.web.controller;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.mail.internet.MimeUtility;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.locus.common.domain.ServiceResult;
import com.locus.jlo.web.bean.dto.AttDTO;
import com.locus.jlo.web.constant.JLOWebConstant;
import com.locus.jlo.web.service.AttService;

@Controller
public class FileController {
	@Autowired
	ServletContext context;
	
	private Logger logger = Logger.getLogger(getClass());
	
	@Value(value = "${home.path}")
	private String homePath;
	
	@Autowired
	private AttService attService;
	
	@RequestMapping(value = "/downloadFile")
	public void downloadFile(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="attId", required = true) String attId)
					throws Exception {
		logger.info("FileController - downloadFile");
		logger.info("*****************************************************");
        BufferedInputStream input = null;
        BufferedOutputStream output = null;
        
        ServiceResult<AttDTO> serviceResult = attService.selectById(attId);
        if(serviceResult.isSuccess()){
	        try {        	
	        	
	        	String full_path = homePath + JLOWebConstant.ROOT_FOLDER+JLOWebConstant.ATTACHMENT+JLOWebConstant.CONTENT_ATT_PATH+serviceResult.getResult().getFileName();
	        	
	        	logger.info("  full_path : "+full_path);
	            // Open streams.
	        	File f = new File(full_path);
	        	
	        	
	        	if(f.exists() && f.isFile()){
	        		// SET CONTEXT TYPE
		        	String mimeType = serviceResult.getResult().getFileType();// new MimetypesFileTypeMap().getContentType(serviceResult.getResult().getFileType());
		        	String fileName = serviceResult.getResult().getFileName();
		        	logger.info("Mime 	  :"+mimeType);
		        	logger.info("FileName :"+fileName);	
		        	
		        	//response.setContentType(mimeType);
		        	response.setHeader("Content-disposition", "attachment; filename=\"" + MimeUtility.encodeWord(fileName) + "\"");

		            String range = request.getHeader("range");
		            response.setHeader("Content-Range", range + Integer.valueOf(new Long(f.length()).intValue()-1));
		            response.setHeader("Accept-Ranges", "bytes");
		            input = new BufferedInputStream(new FileInputStream(f));
		            output = new BufferedOutputStream(response.getOutputStream());
		
		            // Write file contents to response.
		            byte[] buffer = new byte[input.available()];
		            int length;
		            while ((length = input.read(buffer)) > 0) {
		                output.write(buffer, 0, length);
		            }
	        	}else{
	        		PrintWriter out = response.getWriter();
	        		out.write("File Unavailable.");
	        		out.flush();
	        		out.close();
	        	}
		            
	        } finally {
	        	try{
		        	if(output!=null){output.flush(); output.close();}
		            if(input!=null)input.close();
	        	}catch(Exception e){
	        		
	        	}
	        }
        }else{
        	logger.error("request not path file.");
        	PrintWriter out = response.getWriter();
    		out.write("File Unavailable.");
    		out.flush();
    		out.close();
        }
	}
	
	@RequestMapping(value = "/readFile")
	public void readFile(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="attId", required = true) String attId)
					throws Exception {
		logger.info("FileController - readFile");
		logger.info("*****************************************************");
        BufferedInputStream input = null;
        BufferedOutputStream output = null;
        
//        String attId = request.getParameter("attId");
        ServiceResult<AttDTO> serviceResult = attService.selectById(attId);
        if(serviceResult.isSuccess()){
	        try {        	
	        	
	            // Open streams.
	        	File f = new File(JLOWebConstant.ATTACHMENT_FOLDER+serviceResult.getResult().getFilePath()+serviceResult.getResult().getFileName());
	        	
	            
	        	if(f.exists() && f.isFile()){
	        		// SET CONTEXT TYPE
		        	String mimeType = serviceResult.getResult().getFileType();// new MimetypesFileTypeMap().getContentType(serviceResult.getResult().getFileType());
		        	String fileName = serviceResult.getResult().getFileName();
		        	logger.info("Mime 	  :"+mimeType);
		        	logger.info("FileName :"+fileName);	
		        	
//		        	response.setContentType(mimeType);
		        	response.setHeader("Content-disposition", "filename=\"" + MimeUtility.encodeWord(fileName) + "\"");
		        	
		            input = new BufferedInputStream(new FileInputStream(f));
		            output = new BufferedOutputStream(response.getOutputStream());
		            logger.info("size file:"+new Long(f.length()).intValue()+" bytes");
		            String range = request.getHeader("range");
		            response.setContentLength(new Long(f.length()).intValue());
		            logger.info("set header");
		            response.setHeader("Content-Range", range + Integer.valueOf(new Long(f.length()).intValue()-1));
		            response.setHeader("Accept-Ranges", "bytes");
		            response.setHeader("Etag", "W/\"9767057-1323779115364\"");
		            logger.info("start loop read file.");
		            // Write file contents to response.
		            byte[] buffer = new byte[input.available()];
		            int length;
		            while ((length = input.read(buffer)) > 0) {
		                output.write(buffer, 0, length);
		            }
		            logger.info("exit loop read file.");
	        	}else{
	        		logger.info("no file : "+JLOWebConstant.ATTACHMENT_FOLDER+serviceResult.getResult().getFilePath()+serviceResult.getResult().getFileName());
	        		PrintWriter out = response.getWriter();
	        		out.write("File Unavailable.");
	        		out.flush();
	        		out.close();
	        	}
	        	
		            
	        } finally {
	        	try{
		        	if(output!=null){output.flush(); output.close();}		            
	        	}catch(Exception e){
//	        		e.printStackTrace();
	        	}
	        	try{
	        		if(input!=null)input.close();
	        	}catch(Exception e){
//	        		e.printStackTrace();
	        	}	        	
	        }
        }else{
        	logger.error("request not path file.");
        	PrintWriter out = response.getWriter();
    		out.write("File Unavailable.");
    		out.flush();
    		out.close();
        }
	}
	
	@RequestMapping(value = "/imageFile")
	public void imageFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("FileController - imageFile");
		logger.info("*****************************************************");
        BufferedInputStream input = null;
        BufferedOutputStream output = null;
        
        String src = request.getParameter("src");
        logger.debug("src : "+src);
        if(src==null || src.length()==0){
        	logger.error("request not path file.");
	        src=context.getRealPath(File.separator)+"/assets/img/404.png";
        }
        try {
            // Open streams.
        	File f = new File(JLOWebConstant.ATTACHMENT_FOLDER+src);
        	if(!(f.exists() && f.isFile())){
        		f = new File(context.getRealPath(File.separator)+"/assets/img/404.png");
        	}
        	if(f.exists() && f.isFile()){
	            input = new BufferedInputStream(new FileInputStream(f));
	            output = new BufferedOutputStream(response.getOutputStream());
	
	            // Write file contents to response.
	            byte[] buffer = new byte[input.available()];
	            int length;
	            while ((length = input.read(buffer)) > 0) {
	                output.write(buffer, 0, length);
	            }
        	}
	            
        } finally {	        	
        	try{
	        	if(output!=null){output.flush(); output.close();}
	            if(input!=null)input.close();
        	}catch(Exception e){
        		
        	}
        	logger.debug("output close");
        }
	}
	
	@RequestMapping(value = "/getImg")
	public void getImg(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("image/jpeg");
		String src = request.getParameter("src");
        logger.info("src : "+src);
        OutputStream out = null;
        File f = null;
        
		try {
			/**
			 * Original
			 */
			if(src!=null && src.length()>0){
				logger.info("Image path " + homePath + JLOWebConstant.ROOT_FOLDER  + src);
				f = new File(homePath + JLOWebConstant.ROOT_FOLDER  + src);
	        	if(!(f.exists() && f.isFile())){
	        		logger.info("Image not exist !!");
	        		f = new File(context.getRealPath(File.separator)+"/assets/img/noimage.png");
	        	}
				
			} else {
				logger.info("src not exist !!");
        		f = new File(context.getRealPath(File.separator)+"/assets/img/noimage.png");
			}
			
			BufferedImage bi = ImageIO.read(f);
			out = response.getOutputStream();
			ImageIO.write(bi, "png", out);
			
		} catch(Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			try {
				out.flush();
				out.close();
			} catch (Exception e) {
				logger.error("Cannot close output stream");
			}
		}
		
	}
}
