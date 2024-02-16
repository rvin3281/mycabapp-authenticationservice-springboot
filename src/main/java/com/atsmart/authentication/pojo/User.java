package com.atsmart.authentication.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
