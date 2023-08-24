package com.lawencon.lms.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lms.dao.LearningMaterialDao;
import com.lawencon.lms.model.AttachmentMaterial;
import com.lawencon.lms.model.LearningMaterial;

@Repository
@Profile("native-query")
public class LearningMaterialDaoImpl implements LearningMaterialDao {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public LearningMaterial getById(Long id) {
		final LearningMaterial material = this.em.find(LearningMaterial.class, id);
		return material;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<LearningMaterial> getByLearningId(Long learningid) {
		final String sql = "SELECT "
				+ "	* "
				+ "FROM "
				+ "	learning_material lm "
				+ "WHERE "
				+ "	learning_id = :learningId";
		final List<LearningMaterial> materials = this.em.createNativeQuery(sql, LearningMaterial.class)
				.setParameter("learningId", learningid)
				.getResultList();
		
		return materials;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AttachmentMaterial> getLearningMaterialFile(Long materialid) {
		final String sql = "SELECT "
				+ "	* "
				+ "FROM "
				+ "	attachment_material am "
				+ "INNER JOIN "
				+ "	learning_material lm ON lm.id = am.material_id "
				+ "INNER JOIN "
				+ "	file f ON f.id = am.file_id "
				+ "WHERE "
				+ "	lm.id = :materialId";
		final List<AttachmentMaterial> attachments = this.em.createNativeQuery(sql, AttachmentMaterial.class)
				.setParameter("materialId", materialid)
				.getResultList();
	
		return attachments;
	}

	@Override
	public LearningMaterial insert(LearningMaterial material) {
		em.persist(material);
		return material;
	}

}
