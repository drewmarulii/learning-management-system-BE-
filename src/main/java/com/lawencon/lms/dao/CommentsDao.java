package com.lawencon.lms.dao;

import java.util.List;

import com.lawencon.lms.model.Comments;

public interface CommentsDao {

	Comments getById(Long id);
	Comments insert(Comments comments);
	List<Comments> getCommentByForum(Long forumid);
}
