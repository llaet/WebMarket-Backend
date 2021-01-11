package com.saleshub.resources.handler;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ErrorMessageConstructor implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer status;
	private String error;
	private String message;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime timeStamp;
	private String path;

	public ErrorMessageConstructor(Integer status, String message, LocalDateTime timeStamp,  String error, String path) {
		this.status = status;
		this.error = error;
		this.message = message;
		this.timeStamp = timeStamp;
		this.path = path;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
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
