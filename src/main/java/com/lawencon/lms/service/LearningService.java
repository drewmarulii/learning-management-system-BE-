package com.lawencon.lms.service;

import java.util.List;

import com.lawencon.lms.dto.InsertResDto;
import com.lawencon.lms.dto.learning.LearningInsertReqDto;
import com.lawencon.lms.dto.learning.LearningResDto;

public interface LearningService {
	InsertResDto insert(LearningInsertReqDto learning);
	List<LearningResDto> getByClass(Long classId);
	LearningResDto getById(Long classId);
}
