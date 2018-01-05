package com.mapi.bomb;

import java.text.DateFormat;
import java.util.Date;

public class DemoTest {

	public static void main(String[] args) {
	
		long start = System.currentTimeMillis();
		WeightHandler wh = new WeightHandler();
		for(int i = 0; i <10 ; i++){
			
			wh.weightChoice();
		}
		long end = System.currentTimeMillis();
		
		System.out.println(end - start);
		
		
		Date date = new Date();
		System.out.println(date);
	}
}
