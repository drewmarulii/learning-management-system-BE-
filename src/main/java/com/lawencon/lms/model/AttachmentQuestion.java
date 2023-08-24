package com.lawencon.lms.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "attachment_question")
public class AttachmentQuestion extends BaseModel {

	@ManyToOne
	@JoinColumn(name = "file_id")
	private File file;
	
	@ManyToOne
	@JoinColumn(name = "question_id")
	private Question question;
	
	public File getFile() {
		return file;
	}
	
	public void setFile(File file) {
		this.file = file;
	}
	
	public Question getQuestion() {
		return question;
	}
	
	public void setQuestion(Question question) {
		this.question = question;
	}
	
}
