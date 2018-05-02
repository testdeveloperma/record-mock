package com.mapi.record.service;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.mapi.record.bean.RequestAndResponseData;
import com.mapi.record.bean.RequestData;
import com.mapi.record.bean.ResponseData;
import com.mapi.record.dao.RequestAndResponseMapper;
import com.test.common.util.RedisUtil;
import com.test.mobile.redis.SingleValueRedis;

@Service
public class RequestAndResponseService {

	@Autowired
	RequestAndResponseMapper requestAndResponseMapper;

	public void add(RequestData requestData, RequestAndResponseData requestAndResponseData) {

		requestAndResponseData.setMethod(requestData.getMethod());
		requestAndResponseData.setReqParam(requestData.getReqParam());
		Map<String, String> requestHeaders = requestData.getHeaders();

		requestAndResponseData.setRequestHeader(JSON.toJSONString(requestHeaders));

		requestAndResponseData.setUrl(requestData.getUrl());
		
		requestAndResponseData.setSresultId(SingleValueRedis.getInstance().getResultId());
		requestAndResponseMapper.addData(requestAndResponseData);

	}

	public RequestAndResponseData getRequestAndResponseData(String url,Integer resultId) {
		ResponseData responseData = new ResponseData();
		HashMap<String, Object> map = new HashMap<>();
		map.put("url", url);
		map.put("resultId", resultId);
		RequestAndResponseData requestAndResponseData = requestAndResponseMapper.getData(map);
		if(requestAndResponseData != null){
			String reqParam = requestAndResponseData.getReqParam();

			Map<String, String> headers = (Map<String, String>) JSON.parse(requestAndResponseData.getResponseHeader());

			responseData.setHeaders(headers);
			responseData.setStatusCode(requestAndResponseData.getResposeCode());
			responseData.setResponseStream(requestAndResponseData.getResponseResult().getBytes());
		}
		

		return requestAndResponseData;
	}

	public ResponseData getResponseData(RequestAndResponseData requestAndResponseData) {
		
		ResponseData responseData = new ResponseData();
		Map<String, String> headers = (Map<String, String>) JSON.parse(requestAndResponseData.getResponseHeader());

		responseData.setHeaders(headers);
		responseData.setStatusCode(requestAndResponseData.getResposeCode());
		try {
			responseData.setResponseStream(requestAndResponseData.getResponseResult().getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return responseData;
	}

}
