package com.rank.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rank.lms.DTO.ResponseJson;
import com.rank.lms.DTO.StudentDTO;
import com.rank.lms.Utils.CommonConstant;
import com.rank.lms.Utils.MappingConstant;
import com.rank.lms.service.StudentService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(MappingConstant.LMS_API)
public class StudentController {

	@Autowired
	private StudentService studentService;

	@Autowired
	private Environment enviornmnet;

	// add student record
	@ApiOperation(value = "save student", tags = "Student Information")
	@PostMapping(MappingConstant.ADD_STUDENT)
	public ResponseEntity<ResponseJson> saveStudent(@RequestBody StudentDTO studentDTO) {
		ResponseJson responseJson = new ResponseJson(enviornmnet);
		responseJson.setResponse(studentService.addStudent(studentDTO));
		responseJson.setResponseDescription(CommonConstant.S0002_SUCCESS_DESCRIPTION);
		return ResponseEntity.ok(responseJson);

	}

	// get student list
	@ApiOperation(value = "get all students list", tags = "Student Information")
	@GetMapping(MappingConstant.STUDENT_LIST)
	public ResponseEntity<ResponseJson> getStudentList() {
		ResponseJson responseJson = new ResponseJson(enviornmnet);
		responseJson.setResponse(studentService.getStudentList());
		responseJson.setResponseDescription(CommonConstant.S0001_SUCCESS_DESCRIPTION);
		return ResponseEntity.ok(responseJson);
	}

	// get studentt by id
	@ApiOperation(value = "get student by id", tags = "Student Information")
	@GetMapping(MappingConstant.GET_STUDENT)
	public ResponseEntity<ResponseJson> getStudentById(@PathVariable("sId") Long stdId) {
		ResponseJson responseJson = new ResponseJson(enviornmnet);
		responseJson.setResponse(studentService.getStudentById(stdId));
		responseJson.setResponseDescription(CommonConstant.S0001_SUCCESS_DESCRIPTION);
		return ResponseEntity.ok(responseJson);
	}

	// update student information
	@ApiOperation(value = "update student information by id", tags = "Student Information")
	@PutMapping(MappingConstant.UPDATE_STUDENT)
	public ResponseEntity<ResponseJson> updateStudent(@RequestBody StudentDTO studentDTO,
			@PathVariable("sId") Long sId) {
		ResponseJson responseJson = new ResponseJson(enviornmnet);
		responseJson.setResponse(studentService.updateStudent(studentDTO, sId));
		responseJson.setResponseDescription(CommonConstant.S0003_SUCCESS_DESCRIPTION);
		return ResponseEntity.ok(responseJson);
	}
	
	// delete student by id
	@ApiOperation(value = "delete student by id", tags = "Student Information")
	@DeleteMapping(MappingConstant.DELETE_STUDENT)
	public ResponseEntity<ResponseJson> deleteStudent(@PathVariable("sId") Long studentId){
		ResponseJson responseJson = new ResponseJson(enviornmnet);
		studentService.deleteStudentById(studentId);
		responseJson.setResponseDescription(CommonConstant.S0004_SUCCESS_DESCRIPTION);
		return ResponseEntity.ok(responseJson);
	}
}
