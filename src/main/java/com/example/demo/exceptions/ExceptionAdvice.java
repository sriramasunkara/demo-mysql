package com.example.demo.exceptions;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.vo.MessageResponse;

@ControllerAdvice
public class ExceptionAdvice {
	
	
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<MessageResponse> toAccess(AccessDeniedException exp){
	    String message =	exp.getMessage();
	    return new  ResponseEntity<MessageResponse>(new MessageResponse(message,null),HttpStatus.FORBIDDEN);
		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MessageResponse> toAccess(MethodArgumentNotValidException exp){
		 List<String> fieldErrors = exp.getFieldErrors().stream()
		            .map(f ->  f.getDefaultMessage())
		            .collect(Collectors.toList());
	    return new  ResponseEntity<MessageResponse>(new MessageResponse(null,fieldErrors),HttpStatus.FORBIDDEN);
		
	}

}
