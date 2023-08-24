package com.lawencon.lms.dao;

import java.util.List;

import com.lawencon.lms.model.AttachmentQuestion;

public interface QuestionAttachmentDao {
	AttachmentQuestion insert(AttachmentQuestion attachment);
	AttachmentQuestion getByIdAttachmentQuestion(Long id);
	List<AttachmentQuestion> getByQuestion(Long questionid);
}
