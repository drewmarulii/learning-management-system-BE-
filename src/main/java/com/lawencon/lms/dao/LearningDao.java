package com.lawencon.lms.dao;

import java.util.List;

import com.lawencon.lms.model.Learning;

public interface LearningDao {
	List<Learning> getClassLearning(Long id);
	Learning getById(Long learningid);
	Learning insert(Learning newLearning);
}
