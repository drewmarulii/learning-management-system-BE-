package com.lawencon.lms.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "learning_task")
public class LearningTask extends BaseModel {

	@Column(name = "assignment_code", length = 10, unique = true, nullable = false)
	private String assignmentCode;
	
	@ManyToOne
	@JoinColumn(name = "learning_id")
	private Learning learning;
	
	@Column(name = "start_datetime", nullable = false)
	private LocalDateTime startDateTime;
	
	@Column(name = "end_datetime", nullable = false)
	private LocalDateTime endDateTime;
	
	public String getAssignmentCode() {
		return assignmentCode;
	}
	
	public void setAssignmentCode(String assignmentCode) {
		this.assignmentCode = assignmentCode;
	}
	
	public Learning getLearning() {
		return learning;
	}
	
	public void setLearning(Learning learning) {
		this.learning = learning;
	}
	
	public LocalDateTime getStartDateTime() {
		return startDateTime;
	}
	
	public void setStartDateTime(LocalDateTime startDateTime) {
		this.startDateTime = startDateTime;
	}
	
	public LocalDateTime getEndDateTime() {
		return endDateTime;
	}
	
	public void setEndDateTime(LocalDateTime endDateTime) {
		this.endDateTime = endDateTime;
	}
	
}
