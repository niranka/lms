package com.rank.lms.service;

import com.rank.lms.DTO.JwtResponse;
import com.rank.lms.DTO.LoginRequest;
import com.rank.lms.DTO.SignupRequest;

public interface AuthService {
	/**
	 * authenticateuser
	 * @param loginRequest
	 * @return
	 */
	JwtResponse authenticateuser(LoginRequest loginRequest);
	/**
	 * registerUser
	 * @param signUpRequest
	 * @return
	 */
	String registerUser(SignupRequest signUpRequest);
}

