package com.lawencon.lms.service;

import java.util.List;

import com.lawencon.lms.dto.InsertResDto;
import com.lawencon.lms.dto.comments.CommentInsertReqDto;
import com.lawencon.lms.dto.comments.CommentResDto;

public interface CommentService {
	InsertResDto insertComment(CommentInsertReqDto data);
	List<CommentResDto> getByForum(Long id);
}
