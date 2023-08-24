package com.lawencon.lms.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lms.dao.QuestionDao;
import com.lawencon.lms.model.LearningTask;
import com.lawencon.lms.model.Question;
import com.lawencon.lms.model.QuestionType;

@Repository
@Profile("native-query")
public class QuestionDaoImpl implements QuestionDao {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Question getById(Long id) {
		final Question question = this.em.find(Question.class, id);
		return question;
	}

	@Override
	public Question insert(Question question) {
		em.persist(question);
		return question;
	}

	@Override
	public List<Question> getQuestionByTaskId(Long taskid) {
		final List<Question> questions = new ArrayList<>();
		final String sql = "SELECT  "
				+ "	q.id, "
				+ " q.question_detail, "
				+ " qt.type_name, "
				+ " lt.assignment_code "
				+ "FROM "
				+ "	question q  "
				+ "INNER JOIN "
				+ "	learning_task lt  ON lt.id = q.task_id "
				+ "INNER JOIN"
				+ "	question_type qt ON qt.id = q.type_id "
				+ "WHERE  "
				+ "	q.task_id = :taskId";
		final List<?> questionObjs = this.em.createNativeQuery(sql)
				.setParameter("taskId", taskid)
				.getResultList();
		
		if (questionObjs.size() > 0) {
			for (Object questionObj : questionObjs) {
				final Object[] questionArr = (Object[]) questionObj;
				
				final Question question = new Question();
				question.setId(Long.valueOf(questionArr[0].toString()));
				question.setQuestionDetail(questionArr[1].toString());
				
				final QuestionType type = new QuestionType();
				type.setTypeName(questionArr[2].toString());
				question.setQuestionType(type);
				
				final LearningTask task = new LearningTask();
				task.setAssignmentCode(questionArr[3].toString());
				question.setLearningTask(task);
				
				questions.add(question);
			}
		}
		return questions;
	}

}
