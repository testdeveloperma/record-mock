package com.mapi.util;

public class WeightConfigUtil {

	public static Config DEF_WEIGHT = new Config("weight.properties");
	
	public static String getConfigVal(String key){
		return DEF_WEIGHT.getString(key);
	}
	
}
