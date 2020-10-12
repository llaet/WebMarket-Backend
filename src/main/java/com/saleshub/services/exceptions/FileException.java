package com.saleshub.services.exceptions;

public class FileException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public FileException(String message) {
		super(message);
	}

	public FileException(String message, Throwable throwable) {
		super(message);
	}
}
