package com.saleshub.resources.handler;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.saleshub.services.exceptions.CategoryNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(CategoryNotFoundException.class)
	public ResponseEntity<ErrorMessageConstructor> CategoryNotFound(
			CategoryNotFoundException ex,
			HttpServletRequest request){
		
		ErrorMessageConstructor error = new ErrorMessageConstructor(
				HttpStatus.NOT_FOUND.value(), ex.getMessage(), LocalDateTime.now());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
		
	}
}
