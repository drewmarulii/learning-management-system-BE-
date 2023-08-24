package com.lawencon.lms.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lms.dao.LearningTaskDao;
import com.lawencon.lms.model.LearningTask;

@Repository
@Profile("native-query")
public class LearningTaskDaoImpl implements LearningTaskDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public LearningTask getById(Long taskid) {
		final LearningTask task = this.em.find(LearningTask.class, taskid);
		return task;
	}

	@Override
	public LearningTask insert(LearningTask learningTask) {
		em.persist(learningTask);
		return learningTask;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LearningTask> getByLearningId(Long learningid) {
		final String sql = "SELECT  "
				+ "	* "
				+ "FROM  "
				+ "	learning_task lt  "
				+ "INNER JOIN  "
				+ "	learning l ON lt.learning_id = l.id "
				+ "WHERE  "
				+ "	l.id = :learningId";
		final List<LearningTask> tasks = this.em.createNativeQuery(sql, LearningTask.class)
				.setParameter("learningId", learningid)
				.getResultList();
		return tasks;
	}

}
