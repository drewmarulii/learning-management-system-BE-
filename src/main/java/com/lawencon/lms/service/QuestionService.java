package com.lawencon.lms.service;

import java.util.List;

import com.lawencon.lms.dto.InsertResDto;
import com.lawencon.lms.dto.question.QuestionInsertReqDto;
import com.lawencon.lms.dto.question.QuestionResDto;
import com.lawencon.lms.dto.question.QuestionTypeResDto;

public interface QuestionService {
	InsertResDto insert(QuestionInsertReqDto question);
	List<QuestionResDto> getByTask(Long id);
	List<QuestionTypeResDto> getType();
}
