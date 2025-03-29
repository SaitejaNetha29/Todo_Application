package com.project.spring_boot_h2_crud.exception;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler{

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Map<String, Object>> handleResourceNotFoundException(ResourceNotFoundException ex){
		Map<String,Object> errorDetails = new HashMap<>();
		errorDetails.put("timeStamp", LocalDateTime.now());
		errorDetails.put("message", ex.getMessage());
		errorDetails.put("status", HttpStatus.NOT_FOUND.value());
		
		return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, Object>> handleGlobalException(Exception ex){
		Map<String,Object> errorDetails = new HashMap<>();
		errorDetails.put("timeStamp", LocalDateTime.now());
		errorDetails.put("message", "An error occurred during call");
		errorDetails.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
		
		return new ResponseEntity<>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
