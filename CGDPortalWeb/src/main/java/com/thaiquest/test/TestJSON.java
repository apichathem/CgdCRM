package com.thaiquest.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonParser;
import com.locus.common.utils.StringUtils;

public class TestJSON {

	public static void main(String[] args) {
		
//		JSONParser parser = new JSONParser();
		/* String soltionKeyWord = "อะไร+เวรมณี";
		 if(!StringUtils.isEmpty(soltionKeyWord)){
				
				if(soltionKeyWord.indexOf("+") > -1){
					System.out.println("soltionKeyWord "+soltionKeyWord);
					soltionKeyWord = soltionKeyWord.replaceAll("\\+", " ");
				}
		}
		 
		 System.out.println("soltionKeyWord "+soltionKeyWord);*/
		
		
		String strCat = "CAT1-00000002,CAT2-00000009,CAT3-00000024,CAT4-00000037,CAT5-00000037";

        String[] arrB = strCat.split(",");
        System.out.println(arrB.length);
        
       System.out.println("arrB[arrB.length-1] "+arrB[arrB.length-1]); 
        /*
        for(String c : arrB)
        {
        	System.out.println(c);
        }
        
        System.out.println("======================");
        
        for(int i = 0; i<arrB.length; i++)
        {
        	System.out.println(arrB[i]);
        }
		 */
		 
		/*	String pathname = 	"D:\\json1.json";//"D:\\input.json";
			File file = new File(pathname);
			System.out.println("file "+file);
			
			try {
				FileReader searchResult = new FileReader(file);
				 JSONObject jo = new JSONObject(searchResult);
				    JSONArray ja = jo.getJSONArray("items");
				    String s = CDL.toString(ja);
				    System.out.println(s);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			//System.out.println(searchResult.getClass().getName());
			
			
//			JSONObject jsonObject = new JSONObject(file);
//			System.out.println(jsonObject);
			
		
		 
			
		
	//	JSONObject object = parser.parser
		
	/*	 // searchResult refers to the current element in the array "search_result"
	    JSONObject questionMark = searchResult.getJSONObject("question_mark");
	    Iterator keys = questionMark.keys();

	    while(keys.hasNext()) {
	        // loop to get the dynamic key
	        String currentDynamicKey = (String)keys.next();

	        // get the value of the dynamic key
	        JSONObject currentDynamicValue = questionMark.getJSONObject(currentDynamicKey);

	        // do something here with the value...
	    }*/
	    
	}

}
