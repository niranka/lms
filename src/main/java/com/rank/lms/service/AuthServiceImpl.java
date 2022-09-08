package com.rank.lms.service;

import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rank.lms.DTO.JwtResponse;
import com.rank.lms.DTO.LoginRequest;
import com.rank.lms.DTO.SignupRequest;
import com.rank.lms.DTO.UserDetailsImpl;
import com.rank.lms.Repository.UserRepository;
import com.rank.lms.config.JwtUtils;
import com.rank.lms.entity.User;
import com.rank.lms.exception.ErrorCodeHelper;
import com.rank.lms.exception.ErrorConstant;
import com.rank.lms.exception.ErrorInfo;
import com.rank.lms.exception.ServiceException;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private ErrorCodeHelper errorCodeHelper;

	@Override
	public JwtResponse authenticateuser(LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		return new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail());
	}

	@Override
	public String registerUser(SignupRequest signUpRequest) {
		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			ErrorInfo errorInfo = errorCodeHelper.getErrorInfo(ErrorConstant.E1007_ERROR_CODE,
					ErrorConstant.E1007_ERROR_DESCRIPTION);
			throw new ServiceException(errorInfo, HttpStatus.ALREADY_REPORTED);
		}
		User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()));
		userRepository.save(user);
		return "User registered successfully!";
	}
}
