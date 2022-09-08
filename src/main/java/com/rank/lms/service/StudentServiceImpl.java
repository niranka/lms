package com.rank.lms.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.rank.lms.DTO.StudentDTO;
import com.rank.lms.Repository.StudentRepository;
import com.rank.lms.entity.Student;
import com.rank.lms.exception.ErrorCodeHelper;
import com.rank.lms.exception.ErrorConstant;
import com.rank.lms.exception.ErrorInfo;
import com.rank.lms.exception.ServiceException;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ErrorCodeHelper errorCodeHelper;

	// save student record
	@Override
	public StudentDTO addStudent(StudentDTO studentDTO) {
		Student student = dtoToEntity(studentDTO);
		Student savedStudent = studentRepository.save(student);
		return entityToDTO(savedStudent);
	}

	// get student list
	@Override
	public List<StudentDTO> getStudentList() {
		List<StudentDTO> studentDTOs = studentRepository.findAll().stream().map(std -> entityToDTO(std))
				.collect(Collectors.toList());
		return studentDTOs;
	}

	// get student by Id
	@Override
	public StudentDTO getStudentById(Long studentId) {
		Optional<Student> studentById = studentRepository.findById(studentId);
		if (studentById.isEmpty()) {
			ErrorInfo errorInfo = errorCodeHelper.getErrorInfo(ErrorConstant.E1011_ERROR_CODE, 
					ErrorConstant.E1011_ERROR_DESCRIPTION);
			throw new ServiceException(errorInfo, HttpStatus.NOT_FOUND);
		} else {
			return entityToDTO(studentById.get());
		}

	}

	// update student
	@Override
	public StudentDTO updateStudent(StudentDTO studentDTO, Long studentId) {
		Optional<Student> studentById = studentRepository.findById(studentId);
		if (studentById.isEmpty()) {
			ErrorInfo errorInfo = errorCodeHelper.getErrorInfo(ErrorConstant.E1008_ERROR_CODE,
					ErrorConstant.E1008_ERROR_DESCRIPTION);
			throw new ServiceException(errorInfo, HttpStatus.NOT_FOUND);
		}

		Student student = studentById.get();
		student.setStudentName(studentDTO.getStudentName());
		student.setEmail(studentDTO.getEmail());
		student.setCourse(studentDTO.getCourse());
		student.setBatch(studentDTO.getBatch());
		Student updatedStudent = studentRepository.save(student);
		return entityToDTO(updatedStudent);
	}

	// delete student by id
	public void deleteStudentById(Long studentId) {
		Optional<Student> studentById = studentRepository.findById(studentId);
		if (studentById.isPresent()) {
			studentRepository.deleteById(studentId);
		} else {
			ErrorInfo errorInfo = errorCodeHelper.getErrorInfo(ErrorConstant.E1011_ERROR_CODE,
					ErrorConstant.E1011_ERROR_DESCRIPTION);
			throw new ServiceException(errorInfo, HttpStatus.NOT_FOUND);
		}

	}

	// convert studentDTO to entity
	private Student dtoToEntity(StudentDTO studentDTO) {
		Student student = modelMapper.map(studentDTO, Student.class);
		return student;
	}

	// convert entity to studentDTO
	private StudentDTO entityToDTO(Student student) {
		StudentDTO studentDTO = modelMapper.map(student, StudentDTO.class);
		return studentDTO;
	}

}
