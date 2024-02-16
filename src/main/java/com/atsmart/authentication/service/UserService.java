package com.atsmart.authentication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.atsmart.authentication.dto.UserResponseDto;
import com.atsmart.authentication.exception.ThrowUserException;
import com.atsmart.authentication.exception.UserException;
import com.atsmart.authentication.pojo.User;
import com.atsmart.authentication.repo.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserException exception;
	
	
	// Insert Data
    // You can use userRepository.save() to insert data
	public User insertUser(User user) {
		return userRepository.save(user);
	}
	
	//Login User
	public User loginUser(String email, String password) {
		
		try {
			User user = userRepository.loginUser(email, password);
			
			if(user == null) {
				throw new ThrowUserException("Wrong"); 
			}else {
				return user;
			}
			
			
		}catch(Exception ex) {
			throw new ThrowUserException("fail", ex); 
		}
		
	}

    // Get all data
    // You can use userRepository.findAll() to retrieve all data
	public List<User> getAll() {
		return userRepository.findAll();
	}

    // Get data by id
    // You can use userRepository.findById() to retrieve data by id
	public Optional<User> getUserById(int id) {
		return userRepository.findById(id);
	}

    // Update data
    // You can use userRepository.save() to update data
	public Boolean updateAllUser(int id,User user) {
		Optional<User> userObj = userRepository.findById(id);
		if(userObj.isPresent()) {
			userRepository.save(user);
			return true;
		}
		else {
			return false;
		}
		
	}
	
	public Boolean updatePartialUser(int id,User user) {
		Optional<User> userObj = userRepository.findById(id);
		if(userObj.isPresent()) {
			
			User existingUser = userObj.get();
			
			// Update only the non-null fields from the request
	        if (user.getUser_name() != null) {
	            existingUser.setUser_name(user.getUser_name());
	        }
	        if (user.getUser_mobile() != null) {
	            existingUser.setUser_identity(user.getUser_identity());
	        }
	        if (user.getUser_mobile() != null) {
	            existingUser.setUser_mobile(user.getUser_mobile());
	        }
	        if (user.getUser_address() != null) {
	            existingUser.setUser_address(user.getUser_address());
	        }
	        if (user.getUser_state() != null) {
	            existingUser.setUser_state(user.getUser_state());
	        }
	        if (user.getUser_city() != null) {
	            existingUser.setUser_city(user.getUser_city());
	        }
	        if (user.getUser_gender() != null) {
	            existingUser.setUser_gender(user.getUser_gender());
	        }
	        
			
			userRepository.save(existingUser);
			return true;
		}
		else {
			return false;
		}
	}

    // Delete data
    // You can use userRepository.deleteById() to delete data by id
	public void deleteUser(int id) {
		userRepository.deleteById(id);
	}
}
