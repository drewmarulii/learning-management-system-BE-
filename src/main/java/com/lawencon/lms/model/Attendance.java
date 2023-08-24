package com.lawencon.lms.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "attendance")
public class Attendance extends BaseModel {

	@ManyToOne
	@JoinColumn(name = "learning_id")
	private Learning learning;
	
	@Column(name = "attendance_date", nullable = false)
	private LocalDateTime attendanceDate;
	
	@ManyToOne
	@JoinColumn(name = "student_id")
	private User user;
	
	@Column(name = "is_approve", nullable = false)
	private Boolean isApprove;
	
	public Learning getLearning() {
		return learning;
	}
	public void setLearning(Learning learning) {
		this.learning = learning;
	}
	public LocalDateTime getAttendanceDate() {
		return attendanceDate;
	}
	public void setAttendanceDate(LocalDateTime attendanceDate) {
		this.attendanceDate = attendanceDate;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Boolean getIsApprove() {
		return isApprove;
	}
	public void setIsApprove(Boolean isApprove) {
		this.isApprove = isApprove;
	}
	
}
