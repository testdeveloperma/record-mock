package com.mapi.record.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapi.record.bean.TestResult;
import com.mapi.record.dao.TestResultMapper;

@Service
public class TestResultService {

	@Autowired
	TestResultMapper testResultDao;
	
	public void add(TestResult testResult){
		testResultDao.addTestResult(testResult);
	}
	
}
