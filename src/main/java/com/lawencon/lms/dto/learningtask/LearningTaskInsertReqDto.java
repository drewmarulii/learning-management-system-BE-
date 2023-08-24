package com.lawencon.lms.dto.learningtask;

import java.util.List;

import com.lawencon.lms.dto.question.QuestionInsertReqDto;

public class LearningTaskInsertReqDto {

	private Long learningId;
	private String startDateTime;
	private String endDateTime;
	private List<QuestionInsertReqDto> questions;

	public Long getLearningId() {
		return learningId;
	}

	public void setLearningId(Long learningId) {
		this.learningId = learningId;
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

	public List<QuestionInsertReqDto> getQuestions() {
		return questions;
	}

	public void setQuestions(List<QuestionInsertReqDto> questions) {
		this.questions = questions;
	}
}
