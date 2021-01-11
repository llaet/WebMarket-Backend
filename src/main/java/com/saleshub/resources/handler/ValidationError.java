package com.saleshub.resources.handler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends ErrorMessageConstructor {
	
	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> errors = new ArrayList<>();

	public ValidationError(Integer status, String message, LocalDateTime timeStamp,  String error, String path) {
		super(status, message, timeStamp,  error, path);
	}

	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void setError(String fieldName, String message) {
		this.errors.add(new FieldMessage(fieldName,message));
	}
	
	

}
