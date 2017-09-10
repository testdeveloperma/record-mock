package com.mapi.record.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.mapi.record.bean.RequestAndResponseData;
import com.mapi.record.bean.RequestData;
import com.mapi.record.bean.ResponseData;
import com.mapi.record.dao.RequestAndResponseMapper;

@Service
public class RequestAndResponseService {

	@Autowired
	RequestAndResponseMapper requestAndResponseMapper;
	
	public void add(RequestData requestData,RequestAndResponseData requestAndResponseData) {

		requestAndResponseData.setMethod(requestData.getMethod());
		requestAndResponseData.setReqParam(requestData.getReqParam());
		Map<String, String> requestHeaders = requestData.getHeaders();

		requestAndResponseData.setRequestHeader(JSON.toJSONString(requestHeaders));

		requestAndResponseData.setUrl(requestData.getUrl());
		
		
		requestAndResponseMapper.addData(requestAndResponseData);
		
	}
	
	
	public ResponseData getResponse(int case_id,String url){
		ResponseData responseData = new ResponseData();
		
		RequestAndResponseData requestAndResponseData = requestAndResponseMapper.getData();
		Map<String,String> headers = (Map<String, String>) JSON.parse(requestAndResponseData.getResponseHeader());
		
		
		responseData.setHeaders(headers);
		responseData.setStatusCode(requestAndResponseData.getResposeCode());		
		responseData.setResponseStream(requestAndResponseData.getResponseResult().getBytes());
		
		return responseData;
	}

}
