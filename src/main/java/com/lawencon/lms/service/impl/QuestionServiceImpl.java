package com.lawencon.lms.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.lawencon.lms.dao.FileDao;
import com.lawencon.lms.dao.LearningTaskDao;
import com.lawencon.lms.dao.QuestionAttachmentDao;
import com.lawencon.lms.dao.QuestionDao;
import com.lawencon.lms.dao.QuestionTypeDao;
import com.lawencon.lms.dto.InsertResDto;
import com.lawencon.lms.dto.question.QuestionInsertReqDto;
import com.lawencon.lms.dto.question.QuestionResDto;
import com.lawencon.lms.dto.question.QuestionTypeResDto;
import com.lawencon.lms.model.AttachmentQuestion;
import com.lawencon.lms.model.File;
import com.lawencon.lms.model.LearningTask;
import com.lawencon.lms.model.Question;
import com.lawencon.lms.model.QuestionType;
import com.lawencon.lms.service.PrincipalService;
import com.lawencon.lms.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {

	private final QuestionDao questionDao;
	private final QuestionTypeDao questionTypeDao;
	private final LearningTaskDao learningTaskDao;
	private final FileDao fileDao;
	private final QuestionAttachmentDao questionAttachmentDao;
	private final PrincipalService principalService;
	
	public QuestionServiceImpl(QuestionDao questionDao, QuestionTypeDao questionTypeDao, 
			LearningTaskDao learningTaskDao, QuestionAttachmentDao questionAttachmentDao,
			FileDao fileDao, PrincipalService principalService) {
		this.questionDao = questionDao;
		this.questionTypeDao = questionTypeDao;
		this.principalService = principalService;
		this.learningTaskDao = learningTaskDao;
		this.fileDao = fileDao;
		this.questionAttachmentDao = questionAttachmentDao;
	}

	@PersistenceContext
	private EntityManager em;

	@Transactional
	@Override
	public InsertResDto insert(QuestionInsertReqDto question) {
		InsertResDto result = new InsertResDto();
		
		final Question newQuestion = new Question();
		newQuestion.setQuestionDetail(question.getQuestionDetail());
		
		final QuestionType type = questionTypeDao.getById(question.getTypeId());
		newQuestion.setQuestionType(type);
		
		final LearningTask task = learningTaskDao.getById(question.getTaskId());
		newQuestion.setLearningTask(task);
		newQuestion.setCreatedBy(principalService.getPrincipal());
		
		questionDao.insert(newQuestion);
		
		if (question.getFiles() != null) {
			for (int i=0; i<question.getFiles().size(); i++) {
				File file = new File();
				file.setFile(question.getFiles().get(i).getFile());
				file.setFileExtension(question.getFiles().get(i).getFileExtension());
				file.setCreatedBy(principalService.getPrincipal());
				file = fileDao.insert(file);
				
				final AttachmentQuestion attachment = new AttachmentQuestion();
				attachment.setFile(file);
				attachment.setQuestion(newQuestion);
				attachment.setCreatedBy(principalService.getPrincipal());
				questionAttachmentDao.insert(attachment);
			}	
		}
		
		result.setId(newQuestion.getId());
		result.setMessage("Question Has Been Added");
		return result;
	}

	@Override
	public List<QuestionResDto> getByTask(Long id) {
		final List<QuestionResDto> questions = new ArrayList<>();
		
		questionDao.getQuestionByTaskId(id).forEach(q -> {
			final QuestionResDto question = new QuestionResDto();
			question.setId(q.getId());
			question.setQuestionDetail(q.getQuestionDetail());
			question.setTaskCode(q.getLearningTask().getAssignmentCode());
			question.setTypeName(q.getQuestionType().getTypeName());
			
			questions.add(question);
		});
		
		return questions;
	}

	@Override
	public List<QuestionTypeResDto> getType() {
		final List<QuestionTypeResDto> types = new ArrayList<>();
		
		questionTypeDao.getAll().forEach(t -> {
			final QuestionTypeResDto type = new QuestionTypeResDto();
			type.setId(t.getId());
			type.setTypeCode(t.getTypeCode());
			type.setTypeName(t.getTypeName());
			types.add(type);
		});
		
		return types;
	}
	
}
