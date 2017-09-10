package com.mapi.record.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.mapi.record.bean.RequestAndResponseData;
import com.mapi.record.bean.RequestData;
import com.mapi.record.bean.ResponseData;
import com.mapi.record.service.DispatcherService;
import com.mapi.record.service.RequestAndResponseService;
import com.mapi.util.HttpServletUtil;

@Controller
public class Dispatcher {

	@Autowired
	DispatcherService dispatcherService;
	
	@Autowired
	RequestAndResponseService requestAndResponseService;
	
	
	
	@RequestMapping(value="**")
	public void dispathcher(HttpServletRequest request,HttpServletResponse response){
		boolean record = false;
		Integer case_id = 0;
		ResponseData responseData = new ResponseData();
		RequestData requestData = HttpServletUtil.getRequestData(request);
		if(record){
			 responseData = dispatcherService.sendRequest(requestData);

		}else{
			 responseData = requestAndResponseService.getResponse(case_id,requestData.getUrl());
		}
		
		 
		
		int  resposeCode = responseData.getStatusCode();
		response.setStatus(resposeCode);
		
		Map<String, String> responseHeadersMap = responseData.getHeaders();
		
		String responseResult = null;
		try {
			
			for (Map.Entry<String, String> header : responseHeadersMap.entrySet()) {
				response.setHeader(header.getKey(), header.getValue());

			}

			responseResult = new String(responseData.getResponseStream(), "UTF-8");
			
			response.getWriter().write(responseResult);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestAndResponseData rarData = new RequestAndResponseData();
		
		rarData.setResponseResult(responseResult);
		rarData.setResponseHeader(JSON.toJSONString(responseHeadersMap));
		rarData.setResposeCode(resposeCode);
		requestAndResponseService.add(requestData, rarData);
		
		
	}

	
}
