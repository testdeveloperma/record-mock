package com.mapi.record.controller;

import static org.mockito.Matchers.booleanThat;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.elong.mobile.commons.utils.compress.CompressUtil;
import com.elong.mobile.commons.utils.compress.CompressUtil.CompressType;
import com.mapi.bomb.JSONCrawler;
import com.mapi.record.bean.Msg;
import com.mapi.record.bean.RequestAndResponseData;
import com.mapi.record.bean.RequestData;
import com.mapi.record.bean.ResponseData;
import com.mapi.record.bean.TestResult;
import com.mapi.record.service.DispatcherService;
import com.mapi.record.service.RequestAndResponseService;
import com.mapi.record.service.TestResultService;
import com.mapi.record.service.WhiteListService;
import com.mapi.util.HttpServletUtil;
import com.mapi.util.JsonUtil;
import com.test.mobile.redis.SingleValueRedis;

@Controller
public class Dispatcher {

	static boolean record = true;

	SingleValueRedis singleValueRedis = SingleValueRedis.getInstance();
	
	@Autowired
	DispatcherService dispatcherService;

	@Autowired
	RequestAndResponseService requestAndResponseService;

	@Autowired
	TestResultService testResultService;

	@Autowired
	WhiteListService whiteListService;

	@RequestMapping(value = "setMode")
	@ResponseBody
	public Msg setMode(@RequestParam(name = "mode") boolean mode) {
		record = mode;
		System.out.println("鏀跺埌" + mode);
		return Msg.success();
	}

	@RequestMapping(value = "**")
	public void dispathcher(HttpServletRequest request, HttpServletResponse response) {

		ResponseData responseData = new ResponseData();
		RequestData requestData = HttpServletUtil.getRequestData(request);
		boolean isplayback = singleValueRedis.getPlaybackSwitch();

		if (isplayback) {
			Integer resultIdToPlayback = singleValueRedis.getResultIdToPlayback();
			if(resultIdToPlayback != null){
				RequestAndResponseData requestAndResponseData = requestAndResponseService
						.getRequestAndResponseData(requestData.getUrl(),resultIdToPlayback);
				if(requestAndResponseData != null)
					responseData = requestAndResponseService.getResponseData(requestAndResponseData);
				else
					responseData = dispatcherService.sendRequest(requestData);
			}
			
		} else {
			
			responseData = dispatcherService.sendRequest(requestData);
		}
		String responseResult = response(response, request, responseData, requestData,isplayback);

		if (isplayback) {
			
		} else {
/*			testResultService.add(getTestResult(requestData, expect));
*/		
			RequestAndResponseData rarData = new RequestAndResponseData();

			rarData.setResponseResult(responseResult);
			rarData.setResponseHeader(JSON.toJSONString(responseData.getHeaders()));
			rarData.setResposeCode(responseData.getStatusCode());
			requestAndResponseService.add(requestData, rarData);	
		}
	}

	public TestResult getTestResult(RequestData requestData, String expect) {
		TestResult testResult = new TestResult();

		testResult.setUrl(requestData.getUrl());
		String actual = requestData.getReqParam();
		testResult.setActual(actual);
		testResult.setExpect(expect);
		String result = getResult(actual, expect);
		testResult.setResult(result);
		return testResult;
	}

	public String getResult(String actual, String expect) {

		if (expect.equals(actual)) {
			return "PASS";
		}
		return "FAIL";
	}

	private String response(HttpServletResponse response, HttpServletRequest request, ResponseData responseData,
			RequestData requestData,boolean isplayback) {
		int resposeCode = responseData.getStatusCode();
		response.setStatus(resposeCode);

		Map<String, String> responseHeadersMap = responseData.getHeaders();

		String responseResult = null;
		try {

			for (Map.Entry<String, String> header : responseHeadersMap.entrySet()) {
				if (header.getKey().equalsIgnoreCase("Content-Length"))
					continue;
				response.setHeader(header.getKey(), header.getValue());

			}

			responseResult = new String(responseData.getResponseStream(), "UTF-8");

			List<String> pathList = whiteListService.getPath();
			// 非回放模式下，并且 url 不再白名单中，则破坏返回数据
			if (!isplayback && pathList.size() > 0 && JsonUtil.isGoodJson(responseResult)
					&& !pathList.contains(requestData.getUrl())) {

				JSONObject parseJson = JSONCrawler.getInstance().parseJson(responseResult);
				responseResult = JSON.toJSONString(parseJson);
			}
			String compress = request.getHeader("compress");
			if (compress != null && !compress.equalsIgnoreCase("none") && !compress.equals("")) {
				CompressType ct = CompressType.GZP;;
				if(compress.equalsIgnoreCase("gzip")){
					 ct = CompressType.GZP;
				}else if(compress.equalsIgnoreCase("lzss")){
					ct = CompressType.LZSS;
				}				
				try {
					byte[] after = CompressUtil.compress(responseResult.getBytes("UTF-8"), ct);
					response.getOutputStream().write(after);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			
				
			} else {
				response.getOutputStream().write(responseResult.getBytes("UTF-8"));			
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return responseResult;

	}

}
