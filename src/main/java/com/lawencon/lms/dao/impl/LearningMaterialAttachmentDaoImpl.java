package com.lawencon.lms.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lms.dao.LearningMaterialAttachmentDao;
import com.lawencon.lms.model.AttachmentMaterial;
import com.lawencon.lms.model.File;
import com.lawencon.lms.model.LearningMaterial;

@Repository
@Profile("native-query")
public class LearningMaterialAttachmentDaoImpl implements LearningMaterialAttachmentDao {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	@Override
	public AttachmentMaterial insert(AttachmentMaterial attachmentMaterial) {
		em.persist(attachmentMaterial);
		return attachmentMaterial;
	}

	@Override
	public AttachmentMaterial getByIdAttachmentMaterial(Long id) {
		final AttachmentMaterial attachment = this.em.find(AttachmentMaterial.class, id);
		return attachment;
	}

	@Override
	public List<AttachmentMaterial> getByMaterial(Long materialid) {
		final List<AttachmentMaterial> attachments = new ArrayList<>();
 		final String sql = "SELECT "
				+ "	f.id AS fileid, "
				+ "	file, "
				+ "	file_extension, "
				+ "	material_name "
				+ "FROM "
				+ "	file f "
				+ "INNER JOIN "
				+ "	attachment_material am ON f.id = am.file_id "
				+ "INNER JOIN "
				+ "	learning_material lm ON lm.id = am.material_id "
				+ "WHERE "
				+ "	lm.id = :materialId";
		
		final List<?> attachmentObjs = this.em.createNativeQuery(sql)
				.setParameter("materialId", materialid)
				.getResultList();
		
		if (attachmentObjs.size() > 0) {
			for (Object attachmentObj : attachmentObjs) {
				final Object[] attachmentArr = (Object[]) attachmentObj;
				final File file = new File();
				file.setId(Long.valueOf(attachmentArr[0].toString()));
				file.setFile(attachmentArr[1].toString());
				file.setFileExtension(attachmentArr[2].toString());
				
				final LearningMaterial material = new LearningMaterial();
				material.setMaterialName(attachmentArr[3].toString());
				final AttachmentMaterial attachment = new AttachmentMaterial();
				attachment.setLearningMaterial(material);
				attachment.setFile(file);
				attachments.add(attachment);
			}
		}
		
		return attachments;
	}

}
