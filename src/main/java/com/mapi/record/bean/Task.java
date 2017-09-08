package com.mapi.record.bean;

public class Task {

	private Integer id;
	
	private String taskName;
	
	private String serverIp;
	
	private String domainName;

	private String url;
	
	private String email;
	
	private String status;
	
	private Integer userId;



	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getServerIp() {
		return serverIp;
	}

	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", taskName=" + taskName + ", serverIp=" + serverIp + ", domainName=" + domainName
				+ ", url=" + url + ", email=" + email + ", status=" + status + ", userId=" + userId + "]";
	}

	
	public Task() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Task(Integer id, String taskName, String serverIp, String domainName, String url, String email,
			String status, Integer userId) {
		super();
		this.id = id;
		this.taskName = taskName;
		this.serverIp = serverIp;
		this.domainName = domainName;
		this.url = url;
		this.email = email;
		this.status = status;
		this.userId = userId;
	}
	
	
	
}
