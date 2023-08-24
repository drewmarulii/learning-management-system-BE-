package com.lawencon.lms.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lms.dao.QuestionAnswerDao;
import com.lawencon.lms.model.Question;
import com.lawencon.lms.model.QuestionAnswer;
import com.lawencon.lms.model.QuestionOption;

@Repository
@Profile("native-query")
public class QuestionAnswerDaoImpl implements QuestionAnswerDao {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public QuestionAnswer getById(Long id) {
		final QuestionAnswer answer = this.em.find(QuestionAnswer.class, id);
		return answer;
	}

	@Override
	public QuestionAnswer insert(QuestionAnswer answer) {
		em.persist(answer);
		return answer;
	}

	@Override
	public List<QuestionAnswer> getByTaskId(Long taskid) {
		final List<QuestionAnswer> answers = new ArrayList<>();
		final String sql = "SELECT "
				+ "	qa.id, "
				+ "	qa.essay_answer, "
				+ "	qa.option_id, "
				+ "	q.question_detail"
				+ "FROM "
				+ "	question_answer qa "
				+ "INNER JOIN "
				+ "	question q ON qa.question_id = q.id "
				+ "WHERE "
				+ "	task_id = :taskId";
		final List<?> answerObjs = this.em.createNativeQuery(sql)
				.setParameter("taskId", sql)
				.getResultList();
		
		if (answerObjs.size() > 0) {
			for (Object answerObj : answerObjs) {
				final Object[] answerArr = (Object[]) answerObj;
				
				final QuestionAnswer answer = new QuestionAnswer();
				answer.setId(Long.valueOf(answerArr[0].toString()));
				answer.setEssayAnswer(answerArr[1].toString());
				
				final QuestionOption option = new QuestionOption();
				option.setId(Long.valueOf(answerArr[2].toString()));
				answer.setQuestionOption(option);
				
				final Question question = new Question();
				question.setQuestionDetail(answerArr[3].toString());
				answer.setQuestion(question);
				
				answers.add(answer);
			}
		}
		
		return answers;
	}

}
