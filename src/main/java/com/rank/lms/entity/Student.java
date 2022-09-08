package com.rank.lms.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long studentId;

	@Column(name = "studentName", nullable = false)
	private String studentName;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "course", nullable = false)
	private String course;

	@Column(name = "batch", nullable = false)
	private String batch;

	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
	private List<Book> book;

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public List<Book> getBook() {
		return book;
	}

	public void setBook(List<Book> book) {
		this.book = book;
	}

	public Student(Long studentId, String studentName, String email, String course, String batch, List<Book> book) {
		super();
		this.studentId = studentId;
		this.studentName = studentName;
		this.email = email;
		this.course = course;
		this.batch = batch;
		this.book = book;
	}

	public Student() {
		super();
	}

}
