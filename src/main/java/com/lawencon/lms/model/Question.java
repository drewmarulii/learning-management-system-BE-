package com.lawencon.lms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "question")
public class Question extends BaseModel {

	@Column(name = "question_detail", nullable = false)
	private String questionDetail;
	
	@ManyToOne
	@JoinColumn(name = "type_id")
	private QuestionType questionType;
	
	@ManyToOne
	@JoinColumn(name = "task_id")
	private LearningTask learningTask;
	
	public String getQuestionDetail() {
		return questionDetail;
	}
	
	public void setQuestionDetail(String questionDetail) {
		this.questionDetail = questionDetail;
	}
	
	public QuestionType getQuestionType() {
		return questionType;
	} 
	
	public void setQuestionType(QuestionType questionType) {
		this.questionType = questionType;
	}
	
	public LearningTask getLearningTask() {
		return learningTask;
	}
	
	public void setLearningTask(LearningTask learningTask) {
		this.learningTask = learningTask;
	}
	
}
