package com.mapi.record.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.mapi.record.bean.RequestData;
import com.mapi.record.bean.ResponseData;
import com.mapi.util.HttpUtil;

@Service
public class DispatcherService {

	
	public ResponseData sendRequest(RequestData requestData){
		String resResult = null;
		ResponseData responseData = null;
		//url = "http://flight-productservice.vip.elong.com" + url;
		String url = "http://flight.elong.com" + requestData.getUrl();
		
		if(requestData.getQueryString() != null){
			url = url + "?" + requestData.getQueryString();
		}
		
		System.out.println("url: " + url);
		System.out.println("requestdata:" + requestData.toString());
		Map<String, String> headers = requestData.getHeaders();
		headers.put("host", "flight.elong.com");
		headers.remove("content-length");
		try {
			responseData = HttpUtil.sendAndGetData(url, requestData.getData(), headers, requestData.getMethod(), 5000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return responseData;
	}
}
