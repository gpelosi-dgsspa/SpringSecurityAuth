package com.cex.application.vo.authentication;

import java.util.Date;

public class UserInfoVo 
{
	private String sessionId;
	private String username;
	private Date accessDate;
	
	public UserInfoVo() {
		super();
	}

	public UserInfoVo(String sessionId, String username, Date accessDate) {
		this();
		this.sessionId = sessionId;
		this.username = username;
		this.accessDate = accessDate;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getAccessDate() {
		return accessDate;
	}

	public void setAccessDate(Date accessDate) {
		this.accessDate = accessDate;
	}
	
}
