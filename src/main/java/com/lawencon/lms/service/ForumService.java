package com.lawencon.lms.service;

import com.lawencon.lms.dto.InsertResDto;
import com.lawencon.lms.dto.learningforum.LearningForumInsertReqDto;
import com.lawencon.lms.dto.learningforum.LearningForumResDto;

public interface ForumService {
	InsertResDto insertForum(LearningForumInsertReqDto forum);
	LearningForumResDto getForum(Long id);
}
