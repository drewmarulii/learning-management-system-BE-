package com.lawencon.lms.service;

import java.util.List;

import com.lawencon.lms.dto.InsertResDto;
import com.lawencon.lms.dto.attachmentmaterial.AttachmentMaterialResDto;
import com.lawencon.lms.dto.learningmaterial.LearningMaterialInsertReqDto;
import com.lawencon.lms.dto.learningmaterial.LearningMaterialResDto;

public interface LearningMaterialService {
	InsertResDto insert(LearningMaterialInsertReqDto material);
	List<LearningMaterialResDto> getByLearningId(Long learningid);
	List<AttachmentMaterialResDto> getByMaterial(Long materialid);
	LearningMaterialResDto getById(Long id);
}
