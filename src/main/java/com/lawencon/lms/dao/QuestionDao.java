package com.lawencon.lms.dao;

import java.util.List;

import com.lawencon.lms.model.Question;

public interface QuestionDao {

	Question getById(Long id);
	Question insert(Question question);
	List<Question> getQuestionByTaskId(Long taskid);
}
