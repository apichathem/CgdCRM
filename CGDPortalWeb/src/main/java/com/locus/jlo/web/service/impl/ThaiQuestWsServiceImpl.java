package com.locus.jlo.web.service.impl;

import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Date;

import org.apache.axis.AxisFault;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.locus.common.domain.ServiceResult;
import com.locus.common.utils.StringUtils;
import com.locus.crm.ws.client.thaiquest.AttachmentType;
import com.locus.crm.ws.client.thaiquest.DocumentLanguage;
import com.locus.crm.ws.client.thaiquest.SearchField;
import com.locus.crm.ws.client.thaiquest.SearchParameter;
import com.locus.crm.ws.client.thaiquest.SearchResult;
import com.locus.crm.ws.client.thaiquest.SearchServiceSoapProxy;
import com.locus.jlo.web.bean.dto.PreferenceDTO;
import com.locus.jlo.web.constant.JLOWebConstant;
import com.locus.jlo.web.service.PreferenceService;
import com.locus.jlo.web.service.ThaiQuestWsService;
import com.thaiquest.ws.inject.DocumentProperty;
import com.thaiquest.ws.inject.InjectResult;
import com.thaiquest.ws.inject.InjectServiceSoapProxy;



@Service
public class ThaiQuestWsServiceImpl extends BaseService implements ThaiQuestWsService {
	
	String PERMISSION_KEY = null; // ThaiQuest
	String SEARCH_END_POINT = null;
	String INJECT_END_POINT = null;
	/*
   	static String PERMISSION_KEY = "tqestest"; // ThaiQuest
	static String SEARCH_END_POINT = "http://demo.thaiquest.com/tqestest/ThaiQuestWebservice/SearchService.asmx";
	static String INJECT_END_POINT = "http://demo.thaiquest.com/tqestest/ThaiQuestWebservice/InjectService.asmx";
	*/
	 
	final private static String PREF_NAME_WS_END_POINT = "THAI_QUEST_WS_END_POINT";
	
	String[] THAIQUEST_END_POINT = null;
	
	private Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private PreferenceService preferenceService;

	private String[] getThaiQuestWebserviceEndPoint(){
		
		String[] prefResult;
		String permissionKey = null;
		String searchEndPoint = null;
		String injectEndPoint = null;
	 
	 	ServiceResult<PreferenceDTO> serviceResult = preferenceService.searchByName(PREF_NAME_WS_END_POINT);
			
		if(serviceResult.isSuccess()) {
			PreferenceDTO tpPref = serviceResult.getResult();
			if(tpPref != null) {
				permissionKey = StringUtils.trim(tpPref.getEtc1());
				searchEndPoint = StringUtils.trim(tpPref.getEtc2());
				injectEndPoint = StringUtils.trim(tpPref.getEtc3());
			}
			
			logger.info("1.PERMISSION_KEY	:	"+permissionKey);
			logger.info("2.SEARCH_END_POINT	:	"+searchEndPoint);
			logger.info("3.INJECT_END_POINT	:	"+injectEndPoint);
			
		}	
	 	
		prefResult = new String[]{permissionKey,searchEndPoint,injectEndPoint};
		
		return prefResult;
		
	}
	
	@Override
	public SearchResult doSearch7(String strQuery,String strCategories) throws Exception {
		
		THAIQUEST_END_POINT = getThaiQuestWebserviceEndPoint();
		
		PERMISSION_KEY = THAIQUEST_END_POINT[0];
		SEARCH_END_POINT = THAIQUEST_END_POINT[1];
		INJECT_END_POINT = THAIQUEST_END_POINT[2];
		
		SearchResult searchResult = new SearchResult(); 
		
		try {
			
			SearchServiceSoapProxy _proxy = new SearchServiceSoapProxy(SEARCH_END_POINT);
			SearchParameter sp = new SearchParameter();
			SearchField field = new SearchField();

			field.setAttachType(AttachmentType.All);	
			field.setCategories(strCategories);
			field.setLanguages(DocumentLanguage.Thai);
			
			field.setQuery(strQuery);	            // คำที่ต้องการค้นหา		
			field.setSource(JLOWebConstant.SOURCE_CALL_CENTER);					// การจัดกลุ่ม เอกสาร
			sp.setField(field);
			
			Calendar cl = Calendar.getInstance();
			cl.setTime(new Date(1));
			sp.setDateFrom(cl);   					//Thu Jan 01 07:00:00 ICT 1970 (กาหนดเวลาเริ่มของข้อมูลที่ต้องการค้นหาข้อมูล)
			sp.setDateTo(Calendar.getInstance());   //Mon May 16 11:32:41 ICT 2016 (กาหนดเวลาสิ้นสุดของข้อมูลที่ต้องการค้นหาข้อมูล)
			
			sp.setIsEnableCache(false);		//เปิด ปิด การทาการของ Cache			
			sp.setShuffleCount(0);  		//กาหนดจานวนชิ้นข้อมูลที่ต้องการ แสดงการเรียงลาดับผลลัพท์ แบบสลับ source
			sp.setIsRelevance(false); 		//เปิด ปิด การเรียงลาดับของผลลัพท์ แบบให้ข้อมูลที่มีความสัมพันธ์กับ คาค้นมากที่สุดขึ้นก่อน
			sp.setIsStemming(false);		//เปิด ปิด การค้นหาข้อมูลโดยใช้คาค้นที่ใกล้เคียงช่วยในการค้นหา
			sp.setOldestFirst(false); 		//กาหนดลาดับของผลลัพท์ แบบข้อมูลเก่าขึ้นมาก่อน (True) หรือเอาใหม่ขึ้นก่อน (False)
			
			sp.setStoryLength(255); 	   //ความยาวของ Story ที่ต้องการ
			sp.setCount(1000); //จานวนชิ้นข้อมูลที่ต้องการค้นหา
			sp.setResultFieldParameter("All");
			sp.setAbstractLength(255);
			searchResult = _proxy.doSearch_7(PERMISSION_KEY, sp);

		} catch (RemoteException ex) {
		    if(ex instanceof AxisFault){
		        logger.error("Axis Fault DoSearch 7 : " + ((AxisFault)ex).getFaultString(), ex);
		    }
		 } catch (Exception e) {
			logger.error("Exception :  "+e.getMessage(), e);
		}

		return searchResult;
	}

	@Override
	public InjectResult doAdd(DocumentProperty document) {
		
		THAIQUEST_END_POINT = getThaiQuestWebserviceEndPoint();
		
		PERMISSION_KEY = THAIQUEST_END_POINT[0];
		SEARCH_END_POINT = THAIQUEST_END_POINT[1];
		INJECT_END_POINT = THAIQUEST_END_POINT[2];
		
		InjectResult result = null;
		try {
			InjectServiceSoapProxy proxy = new InjectServiceSoapProxy(INJECT_END_POINT);
			result = proxy.doAdd(PERMISSION_KEY, document);
		} catch (RemoteException ex) {
		    if(ex instanceof AxisFault){
		        logger.error("Axis Fault : " + ((AxisFault)ex).getFaultString(), ex);
		    }
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
		return result;
	}

	@Override
	public InjectResult doUpdate(DocumentProperty document) {
		THAIQUEST_END_POINT = getThaiQuestWebserviceEndPoint();
		
		PERMISSION_KEY = THAIQUEST_END_POINT[0];
		SEARCH_END_POINT = THAIQUEST_END_POINT[1];
		INJECT_END_POINT = THAIQUEST_END_POINT[2];	
		
		InjectResult result = null;
		try {
			InjectServiceSoapProxy proxy = new InjectServiceSoapProxy(INJECT_END_POINT);
			result = proxy.doEdit(PERMISSION_KEY, document.getID(), document);
		} catch (RemoteException ex) {
		    if(ex instanceof AxisFault){
		        logger.error("Axis Fault : " + ((AxisFault)ex).getFaultString(), ex);
		    }
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
		return result;
	}

	@Override
	public InjectResult doDelete(String id) {
		THAIQUEST_END_POINT = getThaiQuestWebserviceEndPoint();
		
		PERMISSION_KEY = THAIQUEST_END_POINT[0];
		SEARCH_END_POINT = THAIQUEST_END_POINT[1];
		INJECT_END_POINT = THAIQUEST_END_POINT[2];
		InjectResult result = null;
		try {
			InjectServiceSoapProxy proxy = new InjectServiceSoapProxy(INJECT_END_POINT);
			result = proxy.doDelete(PERMISSION_KEY, id);
		} catch (RemoteException ex) {
		    if(ex instanceof AxisFault){
		        logger.error("Axis Fault : " + ((AxisFault)ex).getFaultString(), ex);
		    }
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
		return result;
	}

	@Override
	public SearchResult doSearch2(String strQuery, String strCategories) throws Exception {
		THAIQUEST_END_POINT = getThaiQuestWebserviceEndPoint();
		
		PERMISSION_KEY = THAIQUEST_END_POINT[0];
		SEARCH_END_POINT = THAIQUEST_END_POINT[1];
		INJECT_END_POINT = THAIQUEST_END_POINT[2];
		
		SearchResult searchResult = new SearchResult(); 
		try {
			
			SearchServiceSoapProxy _proxy = new SearchServiceSoapProxy(SEARCH_END_POINT);
			SearchParameter sp = new SearchParameter();
			SearchField field = new SearchField();
			
			searchResult = _proxy.doSearch_2(PERMISSION_KEY, strQuery);
			
		}catch(Exception e){
			
		}
		return searchResult;
	}

}
