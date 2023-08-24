package com.lawencon.lms.dao;

import java.util.List;

import com.lawencon.lms.model.LearningForum;

public interface LearningForumDao {
	LearningForum insert(LearningForum learningForum);
	LearningForum getForumById(Long learningid);
	List<LearningForum> getAll();
	LearningForum getForumByLearning(Long id);
}
