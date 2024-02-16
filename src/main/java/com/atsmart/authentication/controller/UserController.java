package com.atsmart.authentication.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atsmart.authentication.dto.UserLoginDto;
import com.atsmart.authentication.dto.UserRequestDto;
import com.atsmart.authentication.dto.UserResponseDto;
import com.atsmart.authentication.exception.ThrowUserException;
import com.atsmart.authentication.exception.UserException;
import com.atsmart.authentication.mapper.UserRequestMapper;
import com.atsmart.authentication.pojo.User;
import com.atsmart.authentication.response.UserSendResponseImpl;
import com.atsmart.authentication.service.UserService;

@RestController
@RequestMapping(path="/api/v1/user")
@CrossOrigin // Allow requests from all origins
public class UserController {
	
	Logger log = Logger.getAnonymousLogger();

	@Autowired
	UserRequestMapper userRequestMapper;
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserSendResponseImpl response;
	
	@Autowired
	UserException exception;
	
	// INSERT USER ==> NEED TO HAVE REQUEST BODY FROM CLIENT
	@PostMapping("")
	public ResponseEntity<Object> insertUser(@RequestBody UserRequestDto userRequestDto){
		
		try {
			userService.insertUser(userRequestMapper.DtoToModel(userRequestDto));
			return response.ResponseNoData("success", HttpStatus.CREATED);
		}catch(Exception ex)
		{
			return exception.errorResponse("fail", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	// Login User
	@PostMapping("/login")
	public ResponseEntity<Object> loginUser(@RequestBody UserLoginDto userLoginDto)
	{
		try {
			User user = userService.loginUser(userLoginDto.getUser_email(), userLoginDto.getUser_password());

			
			UserResponseDto userResponseDto = userRequestMapper.modelToResponseDto(user);
			return response.ResponseUserSingleData(userResponseDto, "success", HttpStatus.ACCEPTED);
		}catch(ThrowUserException ex) {
			return exception.errorResponse("fail", ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	// GET ALL USER
	@GetMapping("")
	public ResponseEntity<Object> displayAllUser(){
		
		try {
			List<User> user = userService.getAll();
			List<UserResponseDto> usersResponseDto = userRequestMapper.modelsToResponseDtos(user);	
			return response.ResponseUser(usersResponseDto, "success", HttpStatus.OK);
		}catch(Exception ex){
			return exception.errorResponse("fail", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
//	// GET USER BY ID
	@GetMapping("/{id}")
	public ResponseEntity<Object> displayUserById(@PathVariable int id){
		
		try {
			Optional<User> user = userService.getUserById(id);
			Optional<UserResponseDto> responseOptionalDto = userRequestMapper.optionalModelToResponse(user);
			
			if (responseOptionalDto.isPresent()) {
				return response.ResponseUserSingleDataOptional(responseOptionalDto, "success", HttpStatus.OK);
			} else {
				return response.ResponseNoData("success", HttpStatus.NOT_FOUND); 
			}
		}catch(Exception ex) {
			return exception.errorResponse("fail", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

//	// UPDATE USER ==> Put ( (Update Entire Resource) )
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateAllUser(@PathVariable int id, @RequestBody UserRequestDto userRequestDto){
		
		try {
				User user = userRequestMapper.DtoToModel(userRequestDto);
				
				if(userService.updateAllUser(id,user )) {
					return response.ResponseNoData("success", HttpStatus.OK);
				}else {
					return response.ResponseNoData("success", HttpStatus.NO_CONTENT);
				}
				
			}catch(Exception ex) {
			return exception.errorResponse("fail", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	// Update USER ==> Patch ( (Partial Update) )
	@PatchMapping("/{id}")
	public ResponseEntity<Object> updatePartialUser(@PathVariable int id, @RequestBody UserRequestDto userRequestDto){
		log.info(userRequestDto.toString());

		try {
			User user = userRequestMapper.DtoToModel(userRequestDto);
			
			if(userService.updatePartialUser(id,user )) {
				return response.ResponseNoData("success", HttpStatus.OK);
			}else {
				return response.ResponseNoData("success", HttpStatus.NO_CONTENT);
			}
			
		}catch(Exception ex) {
			return exception.errorResponse("fail", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	// DELETE USER BY ID
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable int id){
		try {
			userService.deleteUser(id);
			return response.ResponseNoData("success", HttpStatus.NO_CONTENT);
		}catch(Exception ex) {
			return exception.errorResponse("fail", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
