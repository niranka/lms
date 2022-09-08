package com.rank.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rank.lms.DTO.LoginRequest;
import com.rank.lms.DTO.ResponseJson;
import com.rank.lms.DTO.SignupRequest;
import com.rank.lms.Utils.CommonConstant;
import com.rank.lms.Utils.MappingConstant;
import com.rank.lms.service.AuthService;

import io.swagger.annotations.ApiOperation;

//import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(MappingConstant.LMS_API)
public class AuthController {

	@Autowired
	private AuthService authService;

	@Autowired
	private Environment enviornmnet;

	@ApiOperation(value = "user signup", tags = "User Authorization")
	@PostMapping(MappingConstant.SIGNUP)
	public ResponseEntity<ResponseJson> registerUser(@RequestBody SignupRequest signUpRequest) {
		ResponseJson responseJson = new ResponseJson(enviornmnet);
		responseJson.setResponse(authService.registerUser(signUpRequest));
		responseJson.setResponseDescription(CommonConstant.S0002_SUCCESS_DESCRIPTION);
		return ResponseEntity.ok(responseJson);
	}

	@ApiOperation(value = "user signin", tags = "User Authorization")
	@PostMapping(MappingConstant.SIGNIN)
	public ResponseEntity<ResponseJson> authenticateuser(@RequestBody LoginRequest loginRequest) {
		ResponseJson responseJson = new ResponseJson(enviornmnet);
		responseJson.setResponse(authService.authenticateuser(loginRequest));
		responseJson.setResponseDescription(CommonConstant.S0001_SUCCESS_DESCRIPTION);
		return ResponseEntity.ok(responseJson);
	}
}
