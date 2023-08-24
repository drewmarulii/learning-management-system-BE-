package com.lawencon.lms.service.impl;

import static com.lawencon.lms.util.GeneratorID.generateID;
import static com.lawencon.lms.util.LocalDateTimeUtil.formatDate;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.lawencon.lms.dao.FileDao;
import com.lawencon.lms.dao.LearningDao;
import com.lawencon.lms.dao.LearningTaskDao;
import com.lawencon.lms.dao.QuestionAttachmentDao;
import com.lawencon.lms.dao.QuestionDao;
import com.lawencon.lms.dao.QuestionTypeDao;
import com.lawencon.lms.dto.InsertResDto;
import com.lawencon.lms.dto.learningtask.LearningTaskInsertReqDto;
import com.lawencon.lms.dto.learningtask.LearningTaskResDto;
import com.lawencon.lms.model.AttachmentQuestion;
import com.lawencon.lms.model.File;
import com.lawencon.lms.model.Learning;
import com.lawencon.lms.model.LearningTask;
import com.lawencon.lms.model.Question;
import com.lawencon.lms.model.QuestionType;
import com.lawencon.lms.service.LearningTaskService;
import com.lawencon.lms.service.PrincipalService;

@Service
public class LearningTaskServiceImpl implements LearningTaskService {

	private final LearningDao learningDao;
	private final LearningTaskDao learningTaskDao;
	private final QuestionDao questionDao;
	private final QuestionAttachmentDao questionAttachmentDao;
	private final QuestionTypeDao questionTypeDao;
	private final FileDao fileDao;
	private final PrincipalService principalService;

	public LearningTaskServiceImpl(LearningDao learningDao, QuestionDao questionDao, QuestionTypeDao questionTypeDao,
			LearningTaskDao learningTaskDao, FileDao fileDao, QuestionAttachmentDao questionAttachmentDao, PrincipalService principalService) {
		this.learningDao = learningDao;
		this.questionDao = questionDao;
		this.questionTypeDao = questionTypeDao;
		this.learningTaskDao = learningTaskDao;
		this.questionAttachmentDao = questionAttachmentDao;
		this.fileDao = fileDao;
		this.principalService = principalService;
	}

	@Transactional
	@Override
	public InsertResDto insert(LearningTaskInsertReqDto task) {
		InsertResDto result = new InsertResDto();

		final LearningTask learningTask = new LearningTask();
		final Learning learning = learningDao.getById(task.getLearningId());
		final String randomCode = generateID(5);
		learningTask.setAssignmentCode(randomCode);
		learningTask.setLearning(learning);
		learningTask.setStartDateTime(formatDate(task.getStartDateTime()));
		learningTask.setEndDateTime(formatDate(task.getEndDateTime()));
		learningTask.setCreatedBy(principalService.getPrincipal());
		learningTaskDao.insert(learningTask);

		for (int i = 0; i < task.getQuestions().size(); i++) {
			final Question newQuestion = new Question();
			newQuestion.setQuestionDetail(task.getQuestions().get(i).getQuestionDetail());

			final QuestionType type = questionTypeDao.getById(task.getQuestions().get(i).getTypeId());
			newQuestion.setQuestionType(type);
			newQuestion.setLearningTask(learningTask);
			newQuestion.setCreatedBy(principalService.getPrincipal());

			questionDao.insert(newQuestion);

			if (task.getQuestions().get(i).getFiles() != null) {
				for (int j = 0; j < task.getQuestions().get(i).getFiles().size(); j++) {
					File file = new File();
					file.setFile(task.getQuestions().get(i).getFiles().get(i).getFile());
					file.setFileExtension(task.getQuestions().get(i).getFiles().get(i).getFileExtension());
					file.setCreatedBy(principalService.getPrincipal());
					file = fileDao.insert(file);

					final AttachmentQuestion attachment = new AttachmentQuestion();
					attachment.setFile(file);
					attachment.setQuestion(newQuestion);
					attachment.setCreatedBy(principalService.getPrincipal());
					questionAttachmentDao.insert(attachment);
				}
			}
		}

		result.setId(learningTask.getId());
		result.setMessage("Learning Task Has Been Added " + learningTask.getAssignmentCode());
		return result;
	}

	@Override
	public List<LearningTaskResDto> getByLearningId(Long learningid) {
		final List<LearningTaskResDto> tasks = new ArrayList<>();

		learningTaskDao.getByLearningId(learningid).forEach(t -> {
			final LearningTaskResDto task = new LearningTaskResDto();
			task.setId(t.getId());
			task.setTaskCode(t.getAssignmentCode());
			task.setLearningTopic(t.getLearning().getLearningTopic());
			task.setStartDateTime(t.getStartDateTime().toString());
			task.setEndDateTime(t.getEndDateTime().toString());

			tasks.add(task);
		});

		return tasks;
	}

	@Override
	public LearningTaskResDto getById(Long id) {
		final LearningTask task = learningTaskDao.getById(id);
		
		final LearningTaskResDto detail = new LearningTaskResDto();
		detail.setId(task.getId());
		detail.setTaskCode(task.getAssignmentCode());
		detail.setLearningTopic(task.getLearning().getLearningTopic());
		detail.setStartDateTime(task.getStartDateTime().toString());
		detail.setEndDateTime(task.getEndDateTime().toString());
		
		return detail;
	}

}
