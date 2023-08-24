package com.lawencon.lms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "question_answer")
public class QuestionAnswer extends BaseModel {

	@ManyToOne
	@JoinColumn(name = "question_id")
	private Question question;
	
	@Column(name = "essay_answer", nullable = false)
	private String essayAnswer;
	
	@ManyToOne
	@JoinColumn(name = "option_id")
	private QuestionOption questionOption;
	
	@Column(name = "is_file", nullable = false)
	private Boolean isFile;
	
	public Question getQuestion() {
		return question;
	}
	
	public void setQuestion(Question question) {
		this.question = question;
	}
	
	public String getEssayAnswer() {
		return essayAnswer;
	}
	
	public void setEssayAnswer(String essayAnswer) {
		this.essayAnswer = essayAnswer;
	}
	
	public QuestionOption getQuestionOption() {
		return questionOption;
	}
	
	public void setQuestionOption(QuestionOption questionOption) {
		this.questionOption = questionOption;
	}
	
	public Boolean getIsFile() {
		return isFile;
	}
	
	public void setIsFile(Boolean isFile) {
		this.isFile = isFile;
	}

}
