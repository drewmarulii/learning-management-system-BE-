package com.lawencon.lms.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lms.dao.QuestionAttachmentDao;
import com.lawencon.lms.model.AttachmentQuestion;

@Repository
@Profile("native-query")
public class QuestionAttachmentDaoImpl implements QuestionAttachmentDao {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public AttachmentQuestion insert(AttachmentQuestion attachment) {
		em.persist(attachment);
		return attachment;
	}

	@Override
	public AttachmentQuestion getByIdAttachmentQuestion(Long id) {
		final AttachmentQuestion attachment = this.em.find(AttachmentQuestion.class, id);
		return attachment;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AttachmentQuestion> getByQuestion(Long questionid) {
		final String sql = "SELECT"
				+ "	*"
				+ "FROM "
				+ "	attachment_question "
				+ "WHERE "
				+ "	question_id = :questionId";
		final List<AttachmentQuestion> attachments = this.em.createNativeQuery(sql, AttachmentQuestion.class)
				.setParameter("questionId", questionid)
				.getResultList();
		return attachments;
	}

}
