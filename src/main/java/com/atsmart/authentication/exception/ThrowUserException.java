package com.atsmart.authentication.exception;

public class ThrowUserException extends RuntimeException {

	public ThrowUserException(String message) {
		super(message);
	}
	
	public ThrowUserException(String message, String error) {
		super(message);
	}
	
	public ThrowUserException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
