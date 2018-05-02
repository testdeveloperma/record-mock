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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.mapi.record.bean.Msg;
import com.mapi.record.bean.RequestAndResponseData;
import com.mapi.record.bean.RequestData;
import com.mapi.record.bean.ResponseData;
import com.mapi.record.bean.TestResult;
import com.mapi.record.service.DispatcherService;
import com.mapi.record.service.RequestAndResponseService;
import com.mapi.record.service.TestResultService;
import com.mapi.util.HttpServletUtil;

@Controller
public class Dispatcher2 {

	static boolean record = true;
	
	@Autowired
	DispatcherService dispatcherService;

	@Autowired
	RequestAndResponseService requestAndResponseService;

	@Autowired
	TestResultService testResultService;
	
	@RequestMapping(value="setModeppp")
	@ResponseBody
	public Msg setMode(@RequestParam(name="mode") boolean mode){
		record = mode;
		System.out.println("收到" + mode);
		return Msg.success();
	}
	
	@RequestMapping(value = "beifen")
	public void dispathcher(HttpServletRequest request, HttpServletResponse response) {
		
		
		ResponseData responseData = new ResponseData();
		RequestData requestData = HttpServletUtil.getRequestData(request);
		String expect = null;
		
		if (record) {
			responseData = dispatcherService.sendRequest(requestData);

		} else {
//			RequestAndResponseData requestAndResponseData = requestAndResponseService
//					.getRequestAndResponseData(requestData.getUrl());
//			responseData = requestAndResponseService.getResponseData(requestAndResponseData);
//			expect = requestAndResponseData.getReqParam();
		}
		String responseResult = response(response, responseData, requestData);

		if(record){
			RequestAndResponseData rarData = new RequestAndResponseData();

			rarData.setResponseResult(responseResult);
			rarData.setResponseHeader(JSON.toJSONString(responseData.getHeaders()));
			rarData.setResposeCode(responseData.getStatusCode());
			requestAndResponseService.add(requestData, rarData);
		}else{
			testResultService.add(getTestResult(requestData, expect));
		}
	}
	
	public TestResult getTestResult(RequestData requestData,String expect){
		TestResult testResult = new TestResult();

		testResult.setUrl(requestData.getUrl());
		String actual = requestData.getReqParam();
		testResult.setActual(actual);
		testResult.setExpect(expect);
		String result = getResult(actual, expect);
		testResult.setResult(result);
		return testResult;
	}

	public String getResult(String actual,String expect) {		
		
		if(expect.equals(actual)){
			return "PASS";
		}
		return "FAIL";
	}

	private String response(HttpServletResponse response, ResponseData responseData, RequestData requestData) {
		int resposeCode = responseData.getStatusCode();
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
		return responseResult;
		
	}

}
