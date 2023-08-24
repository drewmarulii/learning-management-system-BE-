package com.lawencon.lms.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "learning")
public class Learning extends BaseModel {
	
	@Column(name = "learning_code", length = 10, unique = true, nullable = false)
	private String learningCode;
	
	@Column(name = "learning_topic", length = 50, nullable = false)
	private String learningTopic;
	
	@Column(name = "learning_date", nullable = false)
	private LocalDateTime learningDate;
	
	@ManyToOne
	@JoinColumn(name = "class_id")
	private ClassSubject classSubject;
	
	public String getLearningCode() {
		return learningCode;
	}
	
	public void setLearningCode(String learningCode) {
		this.learningCode = learningCode;
	}
	
	public String getLearningTopic() {
		return learningTopic;
	}
	
	public void setLearningTopic(String learningTopic) {
		this.learningTopic = learningTopic;
	}
	
	public LocalDateTime getLearningDate() {
		return learningDate;
	}
	
	public void setLearningDate(LocalDateTime learningDate) {
		this.learningDate = learningDate;
	}
	
	public ClassSubject getClassSubject() {
		return classSubject;
	}
	
	public void setClassSubject(ClassSubject classSubject) {
		this.classSubject = classSubject;
	}
	
}
