package com.mapi.record.dao;

import com.mapi.record.bean.RequestAndResponseData;

public interface RequestAndResponseMapper {

	public void addData(RequestAndResponseData requestAndResponseData);
	
	public RequestAndResponseData getData();
	
}
