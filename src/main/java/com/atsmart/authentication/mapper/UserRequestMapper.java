package com.atsmart.authentication.mapper;

import java.util.List;
import java.util.Optional;

import org.mapstruct.Mapper;

import org.mapstruct.factory.Mappers;

import com.atsmart.authentication.dto.UserLoginDto;
import com.atsmart.authentication.dto.UserRequestDto;
import com.atsmart.authentication.dto.UserResponseDto;
import com.atsmart.authentication.pojo.User;

@Mapper(componentModel = "spring")
public interface UserRequestMapper {

	UserRequestMapper INSTANCE = Mappers.getMapper(UserRequestMapper.class);
	
	UserRequestDto modelToDTO(User user);
	
	User DtoToModel(UserRequestDto userRequestDto);
	
	List<UserResponseDto> modelsToResponseDtos(List<User> user);
	
	UserResponseDto modelToResponseDto(User user);
	
	User ResponseDtoToModel(UserResponseDto userResponseDto);
	
	User loginDtoToModel(UserLoginDto userLoginDto);
	
	// Mapping method for Optional<User> to Optional<UserResponseDto>
	default Optional<UserResponseDto> optionalModelToResponse(Optional<User> user) {
			
			// the map function provided by Optional.
			// . If user is present (not empty), it applies the modelToResponseDto method to the contained value, 
			// resulting in an Optional<UserResponseDto>.
	        return user.map(this::modelToResponseDto);
	}
}
