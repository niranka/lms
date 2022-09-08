package com.rank.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rank.lms.DTO.ResponseJson;
import com.rank.lms.Utils.CommonConstant;
import com.rank.lms.Utils.MappingConstant;

import io.swagger.annotations.ApiOperation;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", maxAge = 4800)
@RestController
@RequestMapping(MappingConstant.LMS_API)
public class UserController {

	@Autowired
	private Environment enviornmnet;

	@ApiOperation(value = "test Authorization", tags = "User Authorization Test")
	@GetMapping(MappingConstant.TEST)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<ResponseJson> userAccess() {
		ResponseJson responseJson = new ResponseJson(enviornmnet);
		responseJson.setResponseDescription(CommonConstant.S0001_SUCCESS_DESCRIPTION);
		return ResponseEntity.ok(responseJson);
	}
}
