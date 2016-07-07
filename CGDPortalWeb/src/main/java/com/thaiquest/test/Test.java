package com.thaiquest.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import com.locus.common.utils.StringUtils;

public class Test {

	public Test() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws IOException {
		/*String path = "d:/thaicreate.csv";
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path, true),"tis620"));
		bw.newLine();
		bw.write("C001,คืนที่ดาวเต็มฟ้าฉันจินตนาการเป็นหน้าเธอ ครบปีพอดีพังง 55555﻿,win.weerachai@thaicreate.com,TH,1000000,600000\r\n");
		bw.close();*/
		//phoneNo=sip:0851449867@10.10.69.12:5060
		//&UID=UID
		/*String strUrlCIC = "sip:0851449867@10.10.69.12:5060";
		String[] strArrCic1 = strUrlCIC.split("@");
		String[] strArrCic2 = strArrCic1[0].split(":");
		
		
		System.out.println("strArrCic  "+strArrCic2[1]);*/
		
		
		  String Str = new String("There");
		  
		  if(!StringUtils.isNullOrEmpty(Str)){
			  	System.out.println(Str.length());
			  if(Str.length() > 40){
			      
			      String subStrNew = Str.substring(0, 40);
			      System.out.println("Return Value :"+subStrNew );
			      System.out.println(" subnew "+subStrNew.length());
  
			  }else{
				  
				 System.out.println("Str "+Str);
			  }
			  
		      			  
		  }
		
		
		
		
	}

}
