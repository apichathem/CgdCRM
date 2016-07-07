package com.locus.jlo.web.util;

import java.io.File;
import java.io.IOException;
import net.coobird.thumbnailator.*;

public class PicturesUtil {
	
	//Thumbnails
	/*public static void main(String args[]){
		try {
			Thumbnails.of(new File("C:\\Ocean\\original.jpg"))
			.size(160, 160)
			.toFile(new File("C:\\Ocean\\thumbnail.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	//Watermark
	/*public static void main(String args[]){
		try {
			Thumbnails.of(new File("C:\\Ocean\\original.jpg"))
	        .size(160, 160)
	        .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File("C:\\Ocean\\watermark.png")), 0.5f)
	        .outputQuality(0.8)
	        .toFile(new File("C:\\Ocean\\original-with-watermark.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	//Resize with Full Quality
	public static void main(String args[]){
		try {
			Thumbnails.of(new File("C:\\Ocean\\original.jpg"))
	        .size(500, 500)
	        .outputQuality(1.0)
	        .toFile(new File("C:\\Ocean\\original-500x500"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
