package com.lawencon.lms.service.impl;

import static com.lawencon.lms.util.GeneratorID.generateID;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.lawencon.lms.dao.LearningDao;
import com.lawencon.lms.dao.LearningForumDao;
import com.lawencon.lms.dto.InsertResDto;
import com.lawencon.lms.dto.learningforum.LearningForumInsertReqDto;
import com.lawencon.lms.dto.learningforum.LearningForumResDto;
import com.lawencon.lms.model.Learning;
import com.lawencon.lms.model.LearningForum;
import com.lawencon.lms.service.ForumService;
import com.lawencon.lms.service.PrincipalService;

@Service
public class ForumServiceImpl implements ForumService {

	private final LearningForumDao learningForumDao;
	private final LearningDao learningDao;
	private final PrincipalService principalService;

	public ForumServiceImpl(LearningForumDao learningForumDao, LearningDao learningDao, PrincipalService principalService) {
		this.learningForumDao = learningForumDao;
		this.learningDao = learningDao;
		this.principalService = principalService;
	}

	@Transactional
	@Override
	public InsertResDto insertForum(LearningForumInsertReqDto forum) {
		InsertResDto result = new InsertResDto();
		
		final LearningForum newForum = new LearningForum();
		final String randomCode = generateID(5);
		newForum.setForumCode(randomCode);
		newForum.setForumTitle(forum.getForumTitle());
		
		final Learning learning = learningDao.getById(forum.getLearningId());
		newForum.setLearning(learning);
		newForum.setForumDescription(forum.getForumDescription());
		newForum.setCreatedBy(principalService.getPrincipal());
		
		learningForumDao.insert(newForum);
		
		result.setId(newForum.getId());
		result.setMessage("Forum Has Been Created");
			
		return result;
	}

	@Override
	public LearningForumResDto getForum(Long id) {
		final LearningForum forum = learningForumDao.getForumByLearning(id);
		
		final LearningForumResDto myForum = new LearningForumResDto();
		myForum.setId(forum.getId());
		myForum.setForumCode(forum.getForumCode());
		myForum.setForumTitle(forum.getForumTitle());
		myForum.setForumDescription(forum.getForumDescription());
		myForum.setLearningId(forum.getLearning().getId());
		
		return myForum;
	}

}
