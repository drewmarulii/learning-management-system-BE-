package com.lawencon.lms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "learning_material")
public class LearningMaterial extends BaseModel {

	@Column(name = "material_code", length = 10, unique = true, nullable = false)
	private String materialCode;
	
	@Column(name = "material_name", length = 50, nullable = false)
	private String materialName;
	
	@Column(name = "material_text", nullable = false)
	private String materialText;
	
	@ManyToOne
	@JoinColumn(name = "learning_id")
	private Learning learning;
	
	public String getMaterialCode() {
		return materialCode;
	}
	
	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}
	
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
	
	public Learning getLearning() {
		return learning;
	}
	
	public void setLearning(Learning learning) {
		this.learning = learning;
	}
	
}
