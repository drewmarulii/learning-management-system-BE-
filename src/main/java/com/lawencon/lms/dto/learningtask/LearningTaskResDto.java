package com.lawencon.lms.dto.learningtask;

public class LearningTaskResDto {

	private Long id;
	private String taskCode;
	private String learningTopic;
	private String startDateTime;
	private String endDateTime;

	public String getTaskCode() {
		return taskCode;
	}

	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}

	public String getLearningTopic() {
		return learningTopic;
	}

	public void setLearningTopic(String learningTopic) {
		this.learningTopic = learningTopic;
	}

	public String getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(String startDateTime) {
		this.startDateTime = startDateTime;
	}

	public String getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(String endDateTime) {
		this.endDateTime = endDateTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
