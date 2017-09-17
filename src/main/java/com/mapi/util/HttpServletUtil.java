package com.mapi.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.Header;

import com.alibaba.fastjson.JSON;
import com.mapi.record.bean.RequestAndResponseData;
import com.mapi.record.bean.RequestData;

public class HttpServletUtil {

	public static RequestData getRequestData(HttpServletRequest request) {
		RequestData requestData = new RequestData();
		/*
		 * getPathInfo方法返回请求URL中的额外路径信息。额外路径信息是请求URL中的位于Servlet的路径之后和查询参数之前的内容，
		 * 它以“/”开头。 getRemoteAddr方法返回发出请求的客户机的IP地址。
		 * getRemoteHost方法返回发出请求的客户机的完整主机名。 getRemotePort方法返回客户机所使用的网络端口号。
		 * getLocalAddr方法返回WEB服务器的IP地址。 getLocalName方法返回WEB服务器的主机名。
		 */
		String url = request.getRequestURL().toString();// 方法返回客户端发出请求时的完整URL。
		String queryString = request.getQueryString();// get请求参数
		requestData.setQueryString(queryString);
		System.out.println("URL: " + url + "; queryString: " + queryString);
		String uri = request.getRequestURI(); // 返回请求行中的资源名部分。

		String method = request.getMethod();
		requestData.setMethod(method);
		System.out.println(method);
		/*
		 * getHeader(string name)方法:String getHeaders(String name)方法:Enumeration
		 * getHeaderNames()方法
		 */
		System.out.println("============header开始=====================");
		Enumeration<String> headerNames = request.getHeaderNames();
		Map<String, String> headers = new HashMap<String, String>();
		while (headerNames.hasMoreElements()) {
			String headname = headerNames.nextElement();
			String headervalue = request.getHeader(headname);
			System.out.println(headname + " : " + headervalue);

			headers.put(headname, headervalue);
		}
		requestData.setHeaders(headers);

		System.out.println("============header结束=====================");
		byte[] dataByte = null;
		// 获取post请求，body输入流
		if ("POST".equals(method)) {
			try {
				dataByte = HttpUtil.getPostData(request.getInputStream());
				requestData.setData(dataByte);
				requestData.setReqParam(new String(dataByte, "UTF-8"));
				System.out.println("参数： " + requestData.getReqParam());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if ("GET".equals(method)) {
			requestData.setReqParam(queryString);
		}

		requestData.setUrl(uri);
		System.out.println("uri前奏 :" + requestData.getUrl());
		return requestData;
	}

/*	public static RequestAndResponseData getRequestAndResponseData(HttpServletResponse response,
			RequestAndResponseData requestAndResponseData) {

		Header[] responseHeaders = HttpUtil.responseHeaders;
		Map<String, String> resHeaderMap = new HashMap<String, String>();
		for (int i = 0; i < responseHeaders.length; i++) {
			Header header = responseHeaders[i];
			resHeaderMap.put(header.getName(), header.getValue());
		}
		System.out.println(resHeaderMap.toString());

		String responseHeaderjsonString = JSON.toJSONString(resHeaderMap);
		System.out.println("jsonString : " + responseHeaderjsonString);
		requestAndResponseData.setResponseHeader(responseHeaderjsonString);

		return requestAndResponseData;
	}*/
}
