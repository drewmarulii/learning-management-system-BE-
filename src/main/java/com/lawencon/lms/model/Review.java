package com.lawencon.lms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "review")
public class Review extends BaseModel {

	@Column(name = "notes")
	private String notes;
	
	@Column(name = "score")
	private Integer score;
	
	@ManyToOne
	@JoinColumn(name = "student_id")
	private User student;

	@ManyToOne
	@JoinColumn(name = "teacher_id")
	private User teacher;
	
	@ManyToOne
	@JoinColumn(name = "task_id")
	private LearningTask learningTask;
	
	public String getNotes() {
		return notes;
	}
	
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	public Integer getScore() {
		return score;
	}
	
	public void setScore(Integer score) {
		this.score = score;
	}
	
	public User getStudent() {
		return student;
	}
	
	public void setStudent(User student) {
		this.student = student;
	}
	
	public User getTeacher() {
		return teacher;
	}
	
	public void setTeacher(User teacher) {
		this.teacher = teacher;
	}
	
	public LearningTask getLearningTask() {
		return learningTask;
	}
	
	public void setLearningTask(LearningTask learningTask) {
		this.learningTask = learningTask;
	}
	
}
