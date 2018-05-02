package com.mapi.record.bean;


public class RequestAndResponseData {

	private String url; // 请求path,包括get请求参数，不包含目标host

	private String method; // 请求方式，GET or POST

	private String requestHeader; // 请求header

	private String requestParam; // 请求参数
	
	private String responseResult;  // 返回结果
	
	private String responseHeader;  
	
	private int responseCode;  // 响应状态码

	private Integer sresultId;  //健壮性测试报告id
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


	public Integer getSresultId() {
		return sresultId;
	}

	public void setSresultId(Integer sresultId) {
		this.sresultId = sresultId;
	}

	@Override
	public String toString() {
		return "RequestAndResponseData [url=" + url + ", method=" + method + ", requestHeader=" + requestHeader
				+ ", requestParam=" + requestParam + ", responseResult=" + responseResult + ", responseHeader="
				+ responseHeader + ", responseCode=" + responseCode + ", sresultId=" + sresultId + "]";
	}
	
	
	
}
