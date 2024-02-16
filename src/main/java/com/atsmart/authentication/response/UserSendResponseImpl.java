package com.atsmart.authentication.response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.atsmart.authentication.dto.UserResponseDto;

@Component  // Add this annotation
public class UserSendResponseImpl implements UserSendResponseInterface {

	@Override
	public ResponseEntity<Object> ResponseNoData(String message, HttpStatus httpStatus) {
		
		Map<String,Object> response = new HashMap();
		response.put("message", message);
		response.put("status", httpStatus.value());
		
		return new ResponseEntity<>(response,httpStatus);
	}


	@Override
	public ResponseEntity<Object> ResponseUserSingleData(UserResponseDto userResponseDto, String message,
			HttpStatus httpStatus) {
		
		Map<String,Object> response = new HashMap();
		
		response.put("data", userResponseDto);
		response.put("message", message);
		response.put("status", httpStatus.value());
		
		return new ResponseEntity<>(response, httpStatus);
	}

	@Override
	public ResponseEntity<Object> ResponseUser(List<UserResponseDto> userResponseDto, String message,
			HttpStatus httpStatus) {
		
		Map<String,Object> response = new HashMap();
		response.put("data", userResponseDto);
		response.put("message", "success");
		response.put("status", httpStatus.value());
		
		return new ResponseEntity<>(response,httpStatus);
	}


	@Override
	public ResponseEntity<Object> ResponseUserSingleDataOptional(Optional<UserResponseDto> userResponseDto,
			String message, HttpStatus httpStatus) {
		
		Map<String,Object> response = new HashMap();
		
		response.put("data", userResponseDto);
		response.put("message", message);
		response.put("message", httpStatus.value());
		
		return new ResponseEntity<>(response, httpStatus);
	}

}
