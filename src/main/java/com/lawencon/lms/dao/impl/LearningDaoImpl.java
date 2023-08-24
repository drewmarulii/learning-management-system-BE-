package com.lawencon.lms.dao.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lms.dao.LearningDao;
import com.lawencon.lms.model.Learning;

@Repository
@Profile("native-query")
public class LearningDaoImpl implements LearningDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Learning> getClassLearning(Long id) {
		final List<Learning> learnings = new ArrayList<>();
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

		final String sql = "SELECT "
				+ " learning_code, "
				+ "	learning_topic, "
				+ "	TO_CHAR(learning_date, 'YYYY-MM-DD HH24:MI') AS learning_date,"
				+ " l.id "
				+ "FROM "
				+ " learning l "
				+ "INNER JOIN "
				+ " class c ON c.id = l.class_id "
				+ "WHERE "
				+ " class_id = :classId "
				+ "ORDER BY "
				+ " learning_date "
				+ "ASC";
		final List<?> learningObjs = this.em.createNativeQuery(sql)
				.setParameter("classId", id)
				.getResultList();
		
		if (learningObjs.size() > 0) {
			for (Object learningObj : learningObjs) {
				final Object[] assetArr = (Object[]) learningObj;
				final Learning learning = new Learning();
				learning.setLearningCode(assetArr[0].toString());
				learning.setLearningTopic(assetArr[1].toString());
				learning.setLearningDate(LocalDateTime.parse(assetArr[2].toString(), formatter));
				learning.setId(Long.valueOf(assetArr[3].toString()));
				learnings.add(learning);
			}
		}
		return learnings;
	}

	@Override
	public Learning getById(Long learningid) {
		final Learning learning = this.em.find(Learning.class, learningid);
		return learning;
	}

	@Override
	public Learning insert(Learning newLearning) {
		em.persist(newLearning);
		return newLearning;
	}

	
}
