package com.lawencon.lms.dto.learning;

public class LearningInsertReqDto {

	private String learningTopic;
	private String learningDate;
	private Long classId;

	public String getLearningTopic() {
		return learningTopic;
	}

	public void setLearningTopic(String learningTopic) {
		this.learningTopic = learningTopic;
	}

	public String getLearningDate() {
		return learningDate;
	}

	public void setLearningDate(String learningDate) {
		this.learningDate = learningDate;
	}

	public Long getClassId() {
		return classId;
	}

	public void setClassId(Long classId) {
		this.classId = classId;
	}
}
