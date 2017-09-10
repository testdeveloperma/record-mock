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
		 * getPathInfo������������URL�еĶ���·����Ϣ������·����Ϣ������URL�е�λ��Servlet��·��֮��Ͳ�ѯ����֮ǰ�����ݣ�
		 * ���ԡ�/����ͷ�� getRemoteAddr�������ط�������Ŀͻ�����IP��ַ��
		 * getRemoteHost�������ط�������Ŀͻ����������������� getRemotePort�������ؿͻ�����ʹ�õ�����˿ںš�
		 * getLocalAddr��������WEB��������IP��ַ�� getLocalName��������WEB����������������
		 */
		String url = request.getRequestURL().toString();// �������ؿͻ��˷�������ʱ������URL��
		String queryString = request.getQueryString();// get�������
		System.out.println("URL: " + url + "; queryString: " + queryString);
		String uri = request.getRequestURI(); // �����������е���Դ�����֡�
		System.out.println("uri:" + uri);

		String method = request.getMethod();
		requestData.setMethod(method);
		System.out.println("method: " + method);

		/*
		 * getHeader(string name)����:String getHeaders(String name)����:Enumeration
		 * getHeaderNames()����
		 */
		System.out.println("============header��ʼ=====================");
		Enumeration<String> headerNames = request.getHeaderNames();
		Map<String, String> headers = new HashMap<String, String>();
		while (headerNames.hasMoreElements()) {
			String headname = headerNames.nextElement();
			String headervalue = request.getHeader(headname);
			System.out.println(headname + " : " + headervalue);

			headers.put(headname, headervalue);
		}
		requestData.setHeaders(headers);

		System.out.println("============header����=====================");

		/*Map<String, String[]> parameterMap = request.getParameterMap();

		System.out.println("parameterMap: " + parameterMap);
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
			String paramName = entry.getKey();
			String paramValue = request.getParameter(paramName);

			System.out.println(paramName + "==>" + paramValue);
		}*/
		byte[] dataByte = null;
		// ��ȡpost����body������
		if ("POST".equals(method)) {
			try {
				dataByte = HttpUtil.getPostData(request.getInputStream());
				requestData.setData(dataByte);
				requestData.setReqParam(new String(dataByte, "UTF-8"));
				System.out.println("������ " + requestData.getReqParam());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if ("GET".equals(method)) {
			uri = uri + "?" + queryString;
		}

		// System.out.println("uriǰ�� :" + uri);
		requestData.setUrl(uri);
		System.out.println("uriǰ�� :" + requestData.getUrl());
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
