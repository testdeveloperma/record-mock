package com.mapi.record.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.mapi.util.HttpUtil;

@Service
public class DispatcherService {

	
	public String sendRequest(String url,String method,String data,Map<String,String> headers){
		String resResult = null;
		url = "http://flight-productservice.vip.elong.com" + url;
		if("get".equals(method)){
			try {
				resResult = HttpUtil.sendGet(url, headers);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if("post".equals(method)){
			try {
				resResult = HttpUtil.sendPostRequest("http://flight-productservice.vip.elong.com/order/detail", data, headers, 5000);
				System.out.println(resResult);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return resResult;
	}
}
