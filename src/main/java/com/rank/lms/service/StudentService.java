package com.rank.lms.service;

import java.util.List;

import com.rank.lms.DTO.StudentDTO;

public interface StudentService {

	/**
	 * 
	 * @param studentDTO
	 * @return
	 */
	public StudentDTO addStudent(StudentDTO studentDTO);
	/**
	 * 
	 * @param studentId
	 * @return
	 */
	public StudentDTO getStudentById(Long studentId);
	
	/**
	 * 
	 * @return
	 */
	public List<StudentDTO> getStudentList();
	/**
	 * 
	 * @param studentDTO
	 * @param studentId
	 * @return
	 */
	public StudentDTO updateStudent(StudentDTO studentDTO, Long studentId);
	/**
	 * 
	 * @param studentId
	 */
	public void deleteStudentById(Long studentId);
}
