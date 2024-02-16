package com.atsmart.authentication.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class UserException {

	public ResponseEntity<Object> errorResponse(String message, String errorMessage, HttpStatus httpStatus){
		
		Map<String,Object> response = new HashMap<>();
		
		response.put("message", message);
		response.put("error", errorMessage);
		response.put("status", httpStatus.value());
		
		return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
}
