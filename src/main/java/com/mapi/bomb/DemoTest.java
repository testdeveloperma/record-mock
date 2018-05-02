package com.mapi.bomb;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import scala.collection.generic.BitOperations.Int;

public class DemoTest {

	public static void main(String[] args) {
	/*
		long start = System.currentTimeMillis();
		WeightHandler wh = new WeightHandler();
		for(int i = 0; i <10 ; i++){
			
			wh.weightChoice();
		}
		long end = System.currentTimeMillis();
		
		System.out.println(end - start);
		
		
		Date date = new Date();
		System.out.println(date);*/
		Map<Character,Integer> charM = new HashMap<>();
		String a = "99988 ";
		char[] charArray = a.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			if(charM.containsKey(charArray[i])){
				int c = charM.get(charArray[i])+1;
				charM.put(charArray[i], c);
			}	
			else
				charM.put(charArray[i], 1);		
		}
		
		for(Map.Entry<Character, Integer> m:charM.entrySet()){
			System.out.println(m.getKey() + ":" + m.getValue());
		}
	}
	
	
	public int feibo(int n){
		 int a = 0;
		 for(int i = 3; i < n; i++){
  		 }
		
		
		
		return 0;
	}
}
