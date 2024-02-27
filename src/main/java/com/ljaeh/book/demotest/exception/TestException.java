package com.ljaeh.book.demotest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TestException extends RuntimeException {
 
	private static final long serialVersionUID = 6034167892600448190L;
 
	public TestException(String message) {
		super(message);
	}
 
	public TestException(String message, Throwable cause) {
		super(message, cause);
	}
}