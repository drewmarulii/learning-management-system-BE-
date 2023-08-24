package com.lawencon.lms.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.lawencon.lms.dao.CommentsDao;
import com.lawencon.lms.dao.LearningForumDao;
import com.lawencon.lms.dao.UserDao;
import com.lawencon.lms.dto.InsertResDto;
import com.lawencon.lms.dto.comments.CommentInsertReqDto;
import com.lawencon.lms.dto.comments.CommentResDto;
import com.lawencon.lms.model.Comments;
import com.lawencon.lms.model.LearningForum;
import com.lawencon.lms.model.User;
import com.lawencon.lms.service.CommentService;
import com.lawencon.lms.service.PrincipalService;

@Service
public class CommentServiceImpl implements CommentService {

	private final CommentsDao commentsDao;
	private final LearningForumDao learningForumDao;
	private final UserDao userDao;
	private final PrincipalService principalService;

	@PersistenceContext
	private EntityManager em;

	public CommentServiceImpl(CommentsDao commentsDao, LearningForumDao learningForumDao, UserDao userDao,
			PrincipalService principalService, EntityManager em) {
		this.commentsDao = commentsDao;
		this.learningForumDao = learningForumDao;
		this.userDao = userDao;
		this.principalService = principalService;
	}

	@Transactional
	@Override
	public InsertResDto insertComment(CommentInsertReqDto data) {
		InsertResDto result = new InsertResDto();

		final Comments comment = new Comments();
		comment.setCommentDescription(data.getCommentDesc());

		final LearningForum forum = learningForumDao.getForumById(data.getForumId());
		comment.setLearningForum(forum);
		
		final User user = userDao.getUserById(data.getUserId());
		comment.setUser(user);
		comment.setCreatedBy(principalService.getPrincipal());
		
		commentsDao.insert(comment);
		
		result.setId(comment.getId());
		result.setMessage("Comment has been added!");

		return result;
	}

	@Override
	public List<CommentResDto> getByForum(Long id) {
		final List<CommentResDto> comments = new ArrayList<>();
		
		commentsDao.getCommentByForum(id).forEach(c -> {
			final CommentResDto comment = new CommentResDto();
			comment.setId(c.getId());
			comment.setCommentDesc(c.getCommentDescription());
			comment.setUserId(c.getUser().getId());
			comment.setUserFullname(c.getUser().getProfile().getUserFullname());
			comment.setCreatedAt(c.getCreatedAt().toString());
			
			comments.add(comment);
		});
		
		return comments;
	}

}
