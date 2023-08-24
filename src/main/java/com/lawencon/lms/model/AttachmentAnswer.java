package com.lawencon.lms.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "attachment_answer")
public class AttachmentAnswer extends BaseModel {

	@ManyToOne
	@JoinColumn(name = "file_id")
	private File file;
	
	@ManyToOne
	@JoinColumn(name = "answer_id")
	private QuestionAnswer questionAnswer;
	
	public File getFile() {
		return file;
	}
	
	public void setFile(File file) {
		this.file = file;
	}
	
	public QuestionAnswer getQuestionAnswer() {
		return questionAnswer;
	}
	
	public void setQuestionAnswer(QuestionAnswer questionAnswer) {
		this.questionAnswer = questionAnswer;
	}
	
}
