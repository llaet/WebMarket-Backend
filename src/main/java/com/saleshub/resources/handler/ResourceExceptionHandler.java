package com.saleshub.resources.handler;

import java.time.LocalDateTime;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.saleshub.domain.enums.RequestEnum;
import com.saleshub.services.exceptions.AuthorizationException;
import com.saleshub.services.exceptions.FileException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.saleshub.services.exceptions.DataIntegrityException;
import com.saleshub.services.exceptions.ObjectNotFoundException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<ErrorMessageConstructor> handleObjectNotFoundException(
			ObjectNotFoundException ex, HttpServletRequest request){
		
		ErrorMessageConstructor error = new ErrorMessageConstructor(
				HttpStatus.NOT_FOUND.value(), ex.getMessage(), 
				LocalDateTime.now(), RequestEnum.NOT_FOUND.getErrorDescription(), request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<ErrorMessageConstructor> handleDataIntegrityException(
			DataIntegrityException ex,  HttpServletRequest request){
		
		ErrorMessageConstructor error = new ErrorMessageConstructor(
				HttpStatus.BAD_REQUEST.value(), ex.getMessage(), 
				LocalDateTime.now(), RequestEnum.BAD_REQUEST.getErrorDescription(), request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	@ExceptionHandler(FileException.class)
	public ResponseEntity<ErrorMessageConstructor> handleFileException(
			FileException ex, HttpServletRequest request){

		ErrorMessageConstructor error = new ErrorMessageConstructor(
				HttpStatus.BAD_REQUEST.value(), ex.getMessage(),
				LocalDateTime.now(), "Erro de Arquivo", request.getRequestURI());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	@ExceptionHandler(AmazonClientException.class)
	public ResponseEntity<ErrorMessageConstructor> handleAmazonClientException(
			AmazonClientException ex, HttpServletRequest request){

		ErrorMessageConstructor error = new ErrorMessageConstructor(
				HttpStatus.BAD_REQUEST.value(), ex.getMessage(),
				LocalDateTime.now(), RequestEnum.BAD_REQUEST_AMAZON.getErrorDescription() + " Client", request.getRequestURI());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	@ExceptionHandler(AmazonServiceException.class)
	public ResponseEntity<ErrorMessageConstructor> handleAmazonServiceException(
			AmazonServiceException ex, HttpServletRequest request){

		Integer status = HttpStatus.valueOf(ex.getErrorCode()).value();

		ErrorMessageConstructor error = new ErrorMessageConstructor(
				status, ex.getMessage(),
				LocalDateTime.now(), RequestEnum.BAD_REQUEST_AMAZON.getErrorDescription() + " Service", request.getRequestURI());

		return ResponseEntity.status(status).body(error);
	}

	@ExceptionHandler(AmazonS3Exception.class)
	public ResponseEntity<ErrorMessageConstructor> handleAmazonS3Exception(
			AmazonS3Exception ex, HttpServletRequest request){

		ErrorMessageConstructor error = new ErrorMessageConstructor(
				HttpStatus.BAD_REQUEST.value(), ex.getMessage(),
				LocalDateTime.now(), RequestEnum.BAD_REQUEST_AMAZON.getErrorDescription() + " S3", request.getRequestURI());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorMessageConstructor> handleMethodArgumentNotValidException(
			MethodArgumentNotValidException ex, HttpServletRequest request){
		
		ValidationError error = new ValidationError(
				HttpStatus.UNPROCESSABLE_ENTITY.value(), ex.getMessage(),
				LocalDateTime.now(), RequestEnum.UNPROCESSABLE_ENTITY.getErrorDescription(), request.getRequestURI());
		
		for(FieldError errorField : ex.getBindingResult().getFieldErrors()) {
			error.setError(errorField.getField(), errorField.getDefaultMessage());
		}
		
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error);
	}

	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<ErrorMessageConstructor> handleAuthorizationException(
			AuthorizationException ex, HttpServletRequest request){

		ErrorMessageConstructor error = new ErrorMessageConstructor(
				HttpStatus.FORBIDDEN.value(), ex.getMessage(),
				LocalDateTime.now(), RequestEnum.FORBIDDEN.getErrorDescription(), request.getRequestURI());

		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
	}
}
