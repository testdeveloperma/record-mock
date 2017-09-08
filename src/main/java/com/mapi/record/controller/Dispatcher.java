package com.mapi.record.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mapi.record.service.DispatcherService;
import com.mapi.util.HttpUtil;

@Controller
public class Dispatcher {

	@Autowired
	DispatcherService dispatcherService;
	
	@RequestMapping(value="**")
	public void dispathcher(HttpServletRequest request,HttpServletResponse response){
		
		/*		　　
		　　getPathInfo方法返回请求URL中的额外路径信息。额外路径信息是请求URL中的位于Servlet的路径之后和查询参数之前的内容，它以“/”开头。
		　　getRemoteAddr方法返回发出请求的客户机的IP地址。
		　　getRemoteHost方法返回发出请求的客户机的完整主机名。
		　　getRemotePort方法返回客户机所使用的网络端口号。
		　　getLocalAddr方法返回WEB服务器的IP地址。
		　　getLocalName方法返回WEB服务器的主机名。*/
		String url = request.getRequestURL().toString();//方法返回客户端发出请求时的完整URL。
		String queryString = request.getQueryString();//get请求参数
		System.out.println("URL: " + url + "; queryString: " + queryString);
		String uri = request.getRequestURI();  //返回请求行中的资源名部分。
		System.out.println("uri:" + uri);
		
		String method = request.getMethod();
		System.out.println("method: " + method);
		
		/*
		   getHeader(string name)方法:String
		　　getHeaders(String name)方法:Enumeration
		　　getHeaderNames()方法 */
		System.out.println("============header开始=====================");
		Enumeration<String> headerNames = request.getHeaderNames();
		Map<String, String> headers = new HashMap<String, String>();
		while (headerNames.hasMoreElements()) {
			String headname = headerNames.nextElement();
			String headervalue = request.getHeader(headname);
			System.out.println(headname + " : " + headervalue);
			
			headers.put(headname, headervalue);
		}
		System.out.println("============header结束=====================");

		Map<String, String[]> parameterMap = request.getParameterMap();
		
		System.out.println("parameterMap: " + parameterMap);
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, String[]> entry: parameterMap.entrySet()) {
			String paramName = entry.getKey();
			String paramValue = request.getParameter(paramName);
			
			
			System.out.println(paramName + "==>" + paramValue);
		}
		byte[] dataByte = null;
		String data = null;
		try {
			dataByte = HttpUtil.getPostData(request.getInputStream());
			 data = new String(dataByte, "UTF-8");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
		
		String resResult = dispatcherService.sendRequest(uri, method, data, headers);
		try {
			response.getWriter().write(resResult);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}
