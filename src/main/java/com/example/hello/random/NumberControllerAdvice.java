package com.example.hello.random;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class NumberControllerAdvice {
	
	@ExceptionHandler(MyRandomException.class)
	public ResponseEntity<ErrorMessage> randomError(
			MyRandomException e) {
		ErrorMessage error = new ErrorMessage("404", e.getMessage());
		
		return new ResponseEntity<>(error, HttpStatus.OK); 
	}

}
