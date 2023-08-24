package com.lawencon.lms.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "attachment_material")
public class AttachmentMaterial extends BaseModel {

	@ManyToOne
	@JoinColumn(name = "file_id")
	private File file;
	
	@ManyToOne
	@JoinColumn(name = "material_id")
	private LearningMaterial learningMaterial;
	
	public File getFile() {
		return file;
	}
	
	public void setFile(File file) {
		this.file = file;
	}
	
	public LearningMaterial getLearningMaterial() {
		return learningMaterial;
	}
	
	public void setLearningMaterial(LearningMaterial learningMaterial) {
		this.learningMaterial = learningMaterial;
	}
	
}
