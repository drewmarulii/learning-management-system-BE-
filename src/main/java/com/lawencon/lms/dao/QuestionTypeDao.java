package com.lawencon.lms.dao;

import java.util.List;

import com.lawencon.lms.model.QuestionType;

public interface QuestionTypeDao {
	List<QuestionType> getAll();
	QuestionType getById(Long id);
}
