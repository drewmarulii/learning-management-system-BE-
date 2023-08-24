package com.lawencon.lms.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "enrollment")
public class Enrollment extends BaseModel {
	
	@Column(name = "enrollment_code", length = 10, unique = true, nullable = false)
	private String enrollmentCode;
	
	@Column(name = "enrollment_date", nullable = false)
	private LocalDateTime enrollmentDate;
	
	@ManyToOne
	@JoinColumn(name = "student_id")
	private User student;
	
	@ManyToOne
	@JoinColumn(name = "class_id")
	private ClassSubject classSubject;
	
	public String getEnrollmentCode() {
		return enrollmentCode;
	}
	
	public void setEnrollmentCode(String enrollmentCode) {
		this.enrollmentCode = enrollmentCode;
	}
	
	public LocalDateTime getEnrollmentDate() {
		return enrollmentDate;
	}
	
	public void setEnrollmentDate(LocalDateTime enrollmentDate) {
		this.enrollmentDate = enrollmentDate;
	}
	
	public User getStudent() {
		return student;
	}
	
	public void setStudent(User student) {
		this.student = student;
	}
	
	public ClassSubject getClassSubject() {
		return classSubject;
	}
	
	public void setClassSubject(ClassSubject classSubject) {
		this.classSubject = classSubject;
	}
	
}
