package com.mapi.record.dao;

import java.util.List;

import com.mapi.record.bean.Task;


public interface TaskMapper {

	
	
	public int add(Task task);
	
	public List<Task> getTaskByUserId(Integer userId);
	
	public List<Task> getAll();
}
