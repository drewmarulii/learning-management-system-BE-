package com.lawencon.lms.dto.learning;

public class LearningResDto {

	private Long id;
	private String learningCode;
	private String learningTopic;
	private String learningDate;

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

	public String getLearningDate() {
		return learningDate;
	}

	public void setLearningDate(String learningDate) {
		this.learningDate = learningDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
