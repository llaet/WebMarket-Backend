package com.saleshub.resources.handler;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import com.saleshub.services.exceptions.AuthorizationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.saleshub.services.exceptions.DataIntegrityException;
import com.saleshub.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<ErrorMessageConstructor> handleObjectNotFoundException(
			ObjectNotFoundException ex){
		
		ErrorMessageConstructor error = new ErrorMessageConstructor(
				HttpStatus.NOT_FOUND.value(), ex.getMessage(), 
				LocalDateTime.now());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<ErrorMessageConstructor> handleDataIntegrityException(
			DataIntegrityException ex){
		
		ErrorMessageConstructor error = new ErrorMessageConstructor(
				HttpStatus.BAD_REQUEST.value(), ex.getMessage(), 
				LocalDateTime.now());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorMessageConstructor> handleMethodArgumentNotValidException(
			MethodArgumentNotValidException ex){
		
		ValidationError error = new ValidationError(
				HttpStatus.BAD_REQUEST.value(), "Erro de validação", 
				LocalDateTime.now());
		
		for(FieldError errorField : ex.getBindingResult().getFieldErrors()) {
			error.setError(errorField.getField(), errorField.getDefaultMessage());
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<ErrorMessageConstructor> handleAuthorizationException(
			AuthorizationException ex){

		ErrorMessageConstructor error = new ErrorMessageConstructor(
				HttpStatus.FORBIDDEN.value(), ex.getMessage(),
				LocalDateTime.now());

		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
	}
}
