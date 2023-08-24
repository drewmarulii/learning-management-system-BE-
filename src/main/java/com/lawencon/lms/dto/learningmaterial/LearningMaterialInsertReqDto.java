package com.lawencon.lms.dto.learningmaterial;

import java.util.List;

import com.lawencon.lms.dto.attachmentmaterial.AttachmentMaterialInsertReqDto;

public class LearningMaterialInsertReqDto {

	private String materialName;
	private String materialText;
	private Long learningId;
	private List<AttachmentMaterialInsertReqDto> files;

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public String getMaterialText() {
		return materialText;
	}

	public void setMaterialText(String materialText) {
		this.materialText = materialText;
	}

	public Long getLearningId() {
		return learningId;
	}

	public void setLearningId(Long learningId) {
		this.learningId = learningId;
	}

	public List<AttachmentMaterialInsertReqDto> getFiles() {
		return files;
	}

	public void setFiles(List<AttachmentMaterialInsertReqDto> files) {
		this.files = files;
	}

}
