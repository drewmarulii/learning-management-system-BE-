package com.lawencon.lms.dto.question;

import java.util.List;

import com.lawencon.lms.dto.attachmentquestion.AttachmentQuestionInsertReqDto;

public class QuestionInsertReqDto {

	private String questionDetail;
	private Long typeId;
	private Long taskId;
	private List<AttachmentQuestionInsertReqDto> files;

	public String getQuestionDetail() {
		return questionDetail;
	}

	public void setQuestionDetail(String questionDetail) {
		this.questionDetail = questionDetail;
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public List<AttachmentQuestionInsertReqDto> getFiles() {
		return files;
	}

	public void setFiles(List<AttachmentQuestionInsertReqDto> files) {
		this.files = files;
	}
	
}
