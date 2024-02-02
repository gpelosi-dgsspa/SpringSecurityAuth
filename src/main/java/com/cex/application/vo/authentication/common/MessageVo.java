package com.cex.application.vo.authentication.common;

public class MessageVo 
{
	private Integer code;
	private String message;
	private String description;
	
	public MessageVo() {
		super();
	}
	
	public MessageVo(Integer code, String message) {
		this();
		this.code = code;
		this.message = message;
	}

	public MessageVo(Integer code, String message, String description) {
		this(code, message);
		this.description = description;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "MessageVo [code=" + code + ", message=" + message + ", description=" + description + "]";
	}
	
}
