package com.lawencon.lms.dao;

import java.util.List;

import com.lawencon.lms.model.AttachmentMaterial;
import com.lawencon.lms.model.LearningMaterial;

public interface LearningMaterialDao {
	LearningMaterial getById(Long id);
	LearningMaterial insert(LearningMaterial material);
	List<LearningMaterial> getByLearningId(Long learningid);
	List<AttachmentMaterial> getLearningMaterialFile(Long materialid);
}
