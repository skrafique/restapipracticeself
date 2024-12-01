package com.practice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import com.practice.dto.ErrorDetails;

@ControllerAdvice
public class ExceptionHandling 
{
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> handleException(Exception e,WebRequest request)
	{
		ErrorDetails error = new ErrorDetails(new Date(),e.getMessage(),request.getDescription(false));
		return new ResponseEntity<ErrorDetails>(error,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
