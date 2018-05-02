package com.mapi.record.dao;

import java.util.Map;

import com.mapi.record.bean.RequestAndResponseData;

public interface RequestAndResponseMapper {

	public void addData(RequestAndResponseData requestAndResponseData);
	
	public RequestAndResponseData getData(Map<String, Object> map);
	
}
