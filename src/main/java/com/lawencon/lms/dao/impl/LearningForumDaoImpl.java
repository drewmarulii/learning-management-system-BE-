package com.lawencon.lms.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lms.dao.LearningForumDao;
import com.lawencon.lms.model.Learning;
import com.lawencon.lms.model.LearningForum;

@Repository
@Profile("native-query")
public class LearningForumDaoImpl implements LearningForumDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public LearningForum getForumById(Long learningid) {
		final LearningForum forum = this.em.find(LearningForum.class, learningid);
		return forum;
	}

	@Override
	public LearningForum insert(LearningForum learningForum) {
		em.persist(learningForum);
		return learningForum;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LearningForum> getAll() {
		final String sql = "SELECT "
				+ "	* "
				+ "FROM "
				+ "	learning_forum";
		final List<LearningForum> forums = this.em.createNativeQuery(sql, LearningForum.class)
				.getResultList();
		return forums;
	}

	@Override
	public LearningForum getForumByLearning(Long id) {
		final String sql = "SELECT "
				+ " lf.id AS forum_id, "
				+ " forum_code, "
				+ " forum_title, "
				+ " forum_description, "
				+ " learning_id "
				+ "FROM "
				+ " learning_forum lf "
				+ "WHERE "
				+ "	lf.learning_id = :learningId";
		final Object forumObj = this.em.createNativeQuery(sql)
				.setParameter("learningId", id)
				.getSingleResult();
		
		final Object[] forumArr = (Object[]) forumObj;
		
		LearningForum forum = null;
		
		if (forumArr.length > 0) {
			forum = new LearningForum();
			forum.setId(Long.valueOf(forumArr[0].toString()));
			forum.setForumCode(forumArr[1].toString());
			forum.setForumTitle(forumArr[2].toString());
			forum.setForumDescription(forumArr[3].toString());
			
			final Learning learning = new Learning();
			learning.setId(Long.valueOf(forumArr[4].toString()));
			forum.setLearning(learning);
		}
		
		return forum;
	}

}
