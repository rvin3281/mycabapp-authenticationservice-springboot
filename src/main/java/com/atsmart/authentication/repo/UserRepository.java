package com.atsmart.authentication.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.atsmart.authentication.dto.UserResponseDto;
import com.atsmart.authentication.pojo.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
	
//	String sql1 = "SELECT u from user u where u.user_email=?1 AND u.user_password=?2";
	@Query("SELECT u FROM User u WHERE u.user_email = :email AND u.user_password = :password")
	public User loginUser (@Param("email") String email, @Param("password") String password);
}
