package com.mapi.record.bean;

import java.util.Arrays;
import java.util.Map;

public class RequestData {

	private String url;	//����path,����get���������������Ŀ��host
	
	private String method; //����ʽ��GET or POST
	
	private byte[] data; //post��������(������������)
	
	private Map<String, String> headers;	//����header

	private String reqParam;  //�������
	
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

	@Override
	public String toString() {
		return "RequestData [url=" + url + ", method=" + method + ", data=" + Arrays.toString(data) + ", headers="
				+ headers + ", reqParam=" + reqParam + "]";
	}

	
	

	
	
	
}
