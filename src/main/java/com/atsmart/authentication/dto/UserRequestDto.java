package com.atsmart.authentication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {

	private Integer user_id;
	private String user_name;
	private String user_identity;
	private String user_gender;
	private String user_mobile;
	private String user_email;
	private String user_address;
	private String user_state;
	private String user_city;
	private String user_password;
	
}
