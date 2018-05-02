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
		Map<String, String> headers = requestData.getHeaders();
		//url = "http://flight-productservice.vip.elong.com" + url;
		String url;
		String host = headers.get("host");
		host = "mobile-api2011.elong.com";
		System.out.println("host:--------->" + host);
		if(host.equals("localhost"))
			url = "http://flight-productservice.vip.elong.com" + requestData.getUrl();
		else
			url = "http://" + host + requestData.getUrl();
		
		if(requestData.getQueryString() != null){
			url = url + "?" + requestData.getQueryString();
		}
		
		System.out.println("url: " + url);
		
		headers.put("host", host);
		headers.remove("content-length");
		headers.remove("compress");
		try {
			responseData = HttpUtil.sendAndGetData(url, requestData.getData(), headers, requestData.getMethod(), 5000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return responseData;
	}
}
