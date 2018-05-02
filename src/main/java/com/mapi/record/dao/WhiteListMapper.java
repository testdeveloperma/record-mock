package com.mapi.record.dao;

import java.util.List;

import com.mapi.record.bean.WhiteList;

public interface WhiteListMapper {

	
	
	public List<WhiteList> getAll();
	
	public List<String> getPath();
}
