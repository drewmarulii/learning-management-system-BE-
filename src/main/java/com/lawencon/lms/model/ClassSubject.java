package com.lawencon.lms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "class")
public class ClassSubject extends BaseModel {

	@Column(name = "class_code", length = 10, unique = true, nullable = false)
	private String classCode;
	
	@Column(name = "class_name", length = 50, nullable = false)
	private String className;
	
	@OneToOne
	@JoinColumn(name = "file_id")
	private File file;
	
	@ManyToOne
	@JoinColumn(name = "teacher_id")
	private User user;
	
	public String getClassCode() {
		return classCode;
	}
	
	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}
	
	public String getClassName() {
		return className;
	}
	
	public void setClassName(String className) {
		this.className = className;
	}
	
	public File getFile() {
		return file;
	}
	
	public void setFile(File file) {
		this.file = file;
	}
	
	public User getTeacher() {
		return user;
	}
	
	public void setTeacher(User user) {
		this.user = user;
	}
	
}
