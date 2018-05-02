package com.mapi.record.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapi.record.bean.WhiteList;
import com.mapi.record.dao.WhiteListMapper;
import com.test.mobile.redis.WhiteListRedis;

@Service
public class WhiteListService {

	@Autowired
	WhiteListMapper whiteListMapper;

	WhiteListRedis instance = WhiteListRedis.getInstance();

	public List<String> getPath(){
		
		
		List<String> whiteList = instance.getWhiteList();
		
		if(whiteList.size() != 0)
			return whiteList;
		else{
			whiteList = whiteListMapper.getPath();
			if(whiteList.size() > 0)
				instance.addWhiteList(whiteList);
		}		
		return whiteList;
		
	}
	
}
