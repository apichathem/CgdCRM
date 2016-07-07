package com.thaiquest.test;

import java.text.BreakIterator;
import java.util.Locale;

public class ThaiWordbreak {
	
	public static void printEachForward(BreakIterator boundary, String source) {
		 
		StringBuffer strout = new StringBuffer();
		int start = boundary.first();
		for (int end = boundary.next(); end != BreakIterator.DONE; start = end, end = boundary.next()) {
		
		strout.append(source.substring(start, end) + "<br/>");
		}
		 
		System.out.println(strout.toString());
		 
		}

	public static void main(String[] args) {
		 
		Locale thaiLocale = new Locale("th");
		 
		String input = "ชั่วโมงอินเทอร์เน็ตต้อนรับปีจอ แถมมโหฬารนานสูงสุด 2 เท่า";
		 
		BreakIterator boundary = BreakIterator.getWordInstance(thaiLocale);
		 
		//BreakIterator boundary = DictionaryBasedBreakIterator.getWordInstance(thaiLocale);
		 
		boundary.setText(input);
		 
		printEachForward(boundary, input);
		 
		}
}
