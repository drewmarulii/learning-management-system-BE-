package com.lawencon.lms.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lms.dao.QuestionTypeDao;
import com.lawencon.lms.model.QuestionType;

@Repository
@Profile("native-query")
public class QuestionTypeDaoImpl implements QuestionTypeDao {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<QuestionType> getAll() {
		final String sql = "SELECT "
				+ "	* "
				+ "FROM "
				+ "	question_type";
		final List<QuestionType> types = this.em.createNativeQuery(sql, QuestionType.class)
				.getResultList();
		return types;
	}

	@Override
	public QuestionType getById(Long id) {
		final QuestionType type = this.em.find(QuestionType.class, id);
		return type;
	}

}
