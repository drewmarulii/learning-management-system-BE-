package com.lawencon.lms.dao;

import java.util.List;

import com.lawencon.lms.model.LearningTask;

public interface LearningTaskDao {
	LearningTask getById(Long taskid);
	LearningTask insert(LearningTask learningTask);
	List<LearningTask> getByLearningId(Long learningid);
}
