package com.lawencon.lms.service.impl;

import static com.lawencon.lms.util.GeneratorID.generateID;
import static com.lawencon.lms.util.LocalDateTimeUtil.formatDate;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.lawencon.lms.dao.ClassSubjectDao;
import com.lawencon.lms.dao.LearningDao;
import com.lawencon.lms.dto.InsertResDto;
import com.lawencon.lms.dto.learning.LearningInsertReqDto;
import com.lawencon.lms.dto.learning.LearningResDto;
import com.lawencon.lms.model.ClassSubject;
import com.lawencon.lms.model.Learning;
import com.lawencon.lms.service.LearningService;
import com.lawencon.lms.service.PrincipalService;

@Service
public class LearningServiceImpl implements LearningService {

	private final LearningDao learningDao;
	private final ClassSubjectDao classSubjectDao;
	private final PrincipalService principalService;

	@PersistenceContext
	private EntityManager em;
	
	public LearningServiceImpl(LearningDao learningDao, ClassSubjectDao classSubjectDao, PrincipalService principalService) {
		this.learningDao = learningDao;
		this.classSubjectDao = classSubjectDao;
		this.principalService = principalService;
	}

	@Transactional
	@Override
	public InsertResDto insert(LearningInsertReqDto learning) {
		InsertResDto result = new InsertResDto();
		
		final Learning newLearning = new Learning();
		final String randomCode = generateID(5);
		newLearning.setLearningCode(randomCode);
		newLearning.setLearningTopic(learning.getLearningTopic());
		newLearning.setLearningDate(formatDate(learning.getLearningDate()));
		final ClassSubject subject = classSubjectDao.getSubjectById(learning.getClassId());
		newLearning.setClassSubject(subject);
		newLearning.setCreatedBy(principalService.getPrincipal());
		learningDao.insert(newLearning);
		
		result.setId(newLearning.getId());
		result.setMessage("Learning Code " + newLearning.getLearningCode() + " Has Been Added" );
		return result;
	}

	@Override
	public List<LearningResDto> getByClass(Long classId) {
		final List<LearningResDto> learnings = new ArrayList<>();
		
		learningDao.getClassLearning(classId).forEach(l -> {
			final LearningResDto learning = new LearningResDto();
			learning.setId(l.getId());
			learning.setLearningCode(l.getLearningCode());
			learning.setLearningTopic(l.getLearningTopic());
			learning.setLearningDate(l.getLearningDate().toString());
	
			learnings.add(learning);
		});
		
		return learnings;
	}

	@Override
	public LearningResDto getById(Long id) {
		final Learning learningDetail = learningDao.getById(id);
		
		final LearningResDto learning = new LearningResDto();
		learning.setId(learningDetail.getId());
		learning.setLearningCode(learningDetail.getLearningCode());
		learning.setLearningTopic(learningDetail.getLearningTopic());
		learning.setLearningDate(learningDetail.getLearningDate().toString());
		
		return learning;
	}

	
}
