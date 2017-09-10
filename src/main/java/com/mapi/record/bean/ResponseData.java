package com.mapi.record.bean;

import java.util.Arrays;
import java.util.Map;

import org.apache.http.Header;

public class ResponseData {

	private int statusCode;
	
	private Map<String,String> headers;
	
	private byte[] responseStream;

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

	public byte[] getResponseStream() {
		return responseStream;
	}

	public void setResponseStream(byte[] responseStream) {
		this.responseStream = responseStream;
	}

	@Override
	public String toString() {
		return "ResponseData [statusCode=" + statusCode + ", headers=" + headers + ", responseStream="
				+ Arrays.toString(responseStream) + "]";
	}

	

	
	
	
	
}
