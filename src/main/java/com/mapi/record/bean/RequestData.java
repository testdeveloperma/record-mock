package com.mapi.record.bean;

import java.util.Arrays;
import java.util.Map;

public class RequestData {

	private String url;	//请求path,包括get请求参数，不包含目标host
	
	private String method; //请求方式，GET or POST
	
	private byte[] data; //post请求数据(二进制输入流)
	
	private Map<String, String> headers;	//请求header

	private String reqParam;  //请求参数
	
	private String queryString;	//get请求参数
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

	public String getReqParam() {
		return reqParam;
	}

	public void setReqParam(String reqParam) {
		this.reqParam = reqParam;
	}


	public String getQueryString() {
		return queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

	@Override
	public String toString() {
		return "RequestData [url=" + url + ", method=" + method + ", data=" + Arrays.toString(data) + ", headers="
				+ headers + ", reqParam=" + reqParam + ", queryString=" + queryString + "]";
	}


	
	

	
	
	
}
