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
		
		/*		����
		����getPathInfo������������URL�еĶ���·����Ϣ������·����Ϣ������URL�е�λ��Servlet��·��֮��Ͳ�ѯ����֮ǰ�����ݣ����ԡ�/����ͷ��
		����getRemoteAddr�������ط�������Ŀͻ�����IP��ַ��
		����getRemoteHost�������ط�������Ŀͻ�����������������
		����getRemotePort�������ؿͻ�����ʹ�õ�����˿ںš�
		����getLocalAddr��������WEB��������IP��ַ��
		����getLocalName��������WEB����������������*/
		String url = request.getRequestURL().toString();//�������ؿͻ��˷�������ʱ������URL��
		String queryString = request.getQueryString();//get�������
		System.out.println("URL: " + url + "; queryString: " + queryString);
		String uri = request.getRequestURI();  //�����������е���Դ�����֡�
		System.out.println("uri:" + uri);
		
		String method = request.getMethod();
		System.out.println("method: " + method);
		
		/*
		   getHeader(string name)����:String
		����getHeaders(String name)����:Enumeration
		����getHeaderNames()���� */
		System.out.println("============header��ʼ=====================");
		Enumeration<String> headerNames = request.getHeaderNames();
		Map<String, String> headers = new HashMap<String, String>();
		while (headerNames.hasMoreElements()) {
			String headname = headerNames.nextElement();
			String headervalue = request.getHeader(headname);
			System.out.println(headname + " : " + headervalue);
			
			headers.put(headname, headervalue);
		}
		System.out.println("============header����=====================");

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
