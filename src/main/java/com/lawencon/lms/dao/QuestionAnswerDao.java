package com.lawencon.lms.dao;

import java.util.List;

import com.lawencon.lms.model.QuestionAnswer;

public interface QuestionAnswerDao {

	QuestionAnswer getById(Long id);
	QuestionAnswer insert(QuestionAnswer answer);
	List<QuestionAnswer> getByTaskId(Long taskid);
}
