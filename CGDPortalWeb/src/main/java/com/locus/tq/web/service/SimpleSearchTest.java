package com.locus.tq.web.service;

import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Date;

import org.apache.axis.AxisFault;

import com.locus.common.utils.DateTimeUtils;
import com.locus.crm.ws.client.thaiquest.AttachmentType;
import com.locus.crm.ws.client.thaiquest.DocumentLanguage;
import com.locus.crm.ws.client.thaiquest.DocumentProperty;
import com.locus.crm.ws.client.thaiquest.SearchField;
import com.locus.crm.ws.client.thaiquest.SearchHighlightProperty;
import com.locus.crm.ws.client.thaiquest.SearchParameter;
import com.locus.crm.ws.client.thaiquest.SearchResult;
import com.locus.crm.ws.client.thaiquest.SearchServiceSoapProxy;

public class SimpleSearchTest {

	final static String PERMISSION_KEY = "tqestest"; // ThaiQuest
	final static String WS_URL = "http://demo.thaiquest.com/tqestest/ThaiQuestWebservice/SearchService.asmx";

	public static void main(String[] args) {
		 String txtSearch = "อะไร";
		SimpleSearchTest a = new SimpleSearchTest();
		a.doSearch7(txtSearch); 
		
		// 25/05/2016 
//		System.out.println(DateTimeUtils.getDate());
//		System.out.println(DateTimeUtils.formatDate(new Date()));
//		System.out.println(DateTimeUtils.convertDateStringToDateStringFormat(DateTimeUtils.getDate(), DateTimeUtils.DATE_FORMAT_YYYYMMDD));
	    
	}

	private void doSearch7(String txtSearch) {
		SearchServiceSoapProxy _proxy = new SearchServiceSoapProxy(WS_URL);
		try {
			SearchParameter sp = new SearchParameter();
			SearchField field = new SearchField();

			field.setAttachType(AttachmentType.None);
			field.setCategories("");
			field.setLanguages(DocumentLanguage.Thai);
			field.setQuery(txtSearch);
			field.setSource("00000001");
			sp.setField(field);

			sp.setCount(10); // 'จานวนชิ้นข้อมูลที่ต้องการค้นหา

			Calendar cl = Calendar.getInstance();
			cl.setTime(new Date(1));
			sp.setDateFrom(cl);
			sp.setDateTo(Calendar.getInstance());

			sp.setIsEnableCache(false);
			sp.setShuffleCount(0); // 'กาหนดจานวนชิ้นข้อมูลที่ต้องการ
									// แสดงการเรียงลาดับผลลัพท์ แบบสลับ source
			sp.setIsRelevance(false); // 'เปิด ปิด การเรียงลาดับของผลลัพท์
										// แบบให้ข้อมูลที่มีความสัมพันธ์กับ
										// คาค้นมากที่สุดขึ้นก่อน
			sp.setIsStemming(false);// 'เปิด ปิด
									// การค้นหาข้อมูลโดยใช้คาค้นที่ใกล้เคียงช่วยในการค้นหา
			sp.setOldestFirst(false); // 'กาหนดลาดับของผลลัพท์
										// แบบข้อมูลเก่าขึ้นมาก่อน (True)
										// หรือเอาใหม่ขึ้นก่อน (False)

			SearchHighlightProperty shp = new SearchHighlightProperty();
			String[] highlightTagStart = new String[] { "Nothing" };
			String[] highlightTagStop = new String[] { "Nothing" };

			shp.setHighlightTagStart(highlightTagStart);
			shp.setHighlightTagStop(highlightTagStop);

			// SearchOperatorProperty sop = new SearchOperatorProperty();

			sp.setSearchHighlight(shp); // Nothing 'กาหนดรูปแบบของการทาไฮไลท์
										// ของข้อมูล
			// sp.setSearchOperator(sop); // Nothing
			// 'กาหนดรูปแบบของเครื่องหมายหรือข้อความพิเศษ
			// ที่ใช้เป็นเงื่อนไขในการค้นหา เช่น and, or
			sp.setStoryLength(255); // 'ความยาวของ Story ที่ต้องการ
			sp.setCount(10);
			sp.setResultFieldParameter("All");

			SearchResult searchResult = _proxy.doSearch_7(PERMISSION_KEY, sp);

			System.out.println("## Count : " + searchResult.getCount());
			System.out.println("## EstimateTotal : " + searchResult.getEstimateTotal());
			System.out.println("## SearchTime : " + searchResult.getSearchTime());

			if (searchResult.getDocuments() != null) {

				System.out.println("## Documents length : " + searchResult.getDocuments().length);
				DocumentProperty[] documentProperties = searchResult.getDocuments();

				for (DocumentProperty documentProperty : documentProperties) {

					System.out.println("#################################");
					System.out.println("### Headline : " + documentProperty.getHeadline());
					System.out.println("### Categories : " + documentProperty.getCategories());
					System.out.println("### Story : " + documentProperty.getStory());
					System.out.println("### Abstract : " + documentProperty.get_abstract());
				}
			}
		} /*catch (RemoteException ex) {
		    if(ex instanceof AxisFault){
		       // System.out.println("Axis Fault : " );//+ ((AxisFault)ex).getFaultString()+ ex);
		    }
		}*/ catch (Exception e) {
			e.printStackTrace();
		}
	}

}
