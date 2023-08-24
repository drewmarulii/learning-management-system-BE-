package com.lawencon.lms.dto.attendance;

public class AttendanceInsertReqDto {

	private Long learningId;
	private Long studentId;

	public Long getLearningId() {
		return learningId;
	}

	public void setLearningId(Long learningId) {
		this.learningId = learningId;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
}
