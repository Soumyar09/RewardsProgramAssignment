package com.assignment.rewards.program.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvice {

	@ExceptionHandler(value = InvalidDataException.class)
	   public ResponseEntity<Object> exception(InvalidDataException exception) {
	      return new ResponseEntity<>("Invalid Data", HttpStatus.BAD_REQUEST);
	   }
	
}
