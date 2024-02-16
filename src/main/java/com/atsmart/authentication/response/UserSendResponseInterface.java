package com.atsmart.authentication.response;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.atsmart.authentication.dto.UserResponseDto;

public interface UserSendResponseInterface {

	public ResponseEntity<Object> ResponseNoData(String message, HttpStatus httpStatus);
	
	public ResponseEntity<Object> ResponseUserSingleData(UserResponseDto userResponseDto, String message, HttpStatus httpStatus);
	
	public ResponseEntity<Object> ResponseUserSingleDataOptional(Optional<UserResponseDto> userResponseDto, String message, HttpStatus httpStatus);
	
	public ResponseEntity<Object> ResponseUser(List<UserResponseDto> userResponseDto, String message, HttpStatus httpStatus);
	
}
