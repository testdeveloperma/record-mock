package com.mapi.record.bean;


public class RequestAndResponseData {

	private String url; // ����path,����get���������������Ŀ��host

	private String method; // ����ʽ��GET or POST

	private String requestHeader; // ����header

	private String requestParam; // �������
	
	private String responseResult;  // ���ؽ��
	
	private String responseHeader;  
	
	private int responseCode;  // ��Ӧ״̬��

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

	public String getRequestHeader() {
		return requestHeader;
	}

	public void setRequestHeader(String requestHeader) {
		this.requestHeader = requestHeader;
	}

	public String getReqParam() {
		return requestParam;
	}

	public void setReqParam(String reqParam) {
		this.requestParam = reqParam;
	}

	public String getResponseResult() {
		return responseResult;
	}

	public void setResponseResult(String responseResult) {
		this.responseResult = responseResult;
	}

	public String getResponseHeader() {
		return responseHeader;
	}

	public void setResponseHeader(String responseHeader) {
		this.responseHeader = responseHeader;
	}

	public int getResposeCode() {
		return responseCode;
	}

	public void setResposeCode(int resposeCode) {
		this.responseCode = resposeCode;
	}

	@Override
	public String toString() {
		return "RequestAndResponseData [url=" + url + ", method=" + method + ", requestHeader=" + requestHeader
				+ ", reqParam=" + requestParam + ", responseResult=" + responseResult + ", responseHeader=" + responseHeader
				+ ", resposeCode=" + responseCode + "]";
	}
	
	
	
}
