package com.saleshub.resources.handler;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ErrorMessageConstructor implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer status;
	private String message;
	private LocalDateTime timeStamp;
	
	public ErrorMessageConstructor(Integer status, String message, LocalDateTime timeStamp) {
		super();
		this.status = status;
		this.message = message;
		this.timeStamp = timeStamp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}
		
}
