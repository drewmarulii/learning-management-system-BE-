package com.lawencon.lms.service;

import java.util.List;

import com.lawencon.lms.dto.InsertResDto;
import com.lawencon.lms.dto.learningtask.LearningTaskInsertReqDto;
import com.lawencon.lms.dto.learningtask.LearningTaskResDto;

public interface LearningTaskService {
	InsertResDto insert(LearningTaskInsertReqDto task);
	List<LearningTaskResDto> getByLearningId(Long learningid);
	LearningTaskResDto getById(Long id);
}
