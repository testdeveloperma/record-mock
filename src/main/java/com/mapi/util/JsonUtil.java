package com.mapi.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;

public class JsonUtil {

	public static boolean isGoodJson(String json) {    
	      
		   try {    
		       JSON.parseObject(json);
		       return true;    
		   } catch (JSONException e) {    
		       System.out.println("bad json: " + json);    
		       return false;    
		   }    
		} 
}
