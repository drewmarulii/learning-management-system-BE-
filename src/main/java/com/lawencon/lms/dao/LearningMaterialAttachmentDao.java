package com.lawencon.lms.dao;

import java.util.List;

import com.lawencon.lms.model.AttachmentMaterial;

public interface LearningMaterialAttachmentDao {
	AttachmentMaterial insert(AttachmentMaterial attachmentMaterial);
	AttachmentMaterial getByIdAttachmentMaterial(Long id);
	List<AttachmentMaterial> getByMaterial(Long materialid);
}
