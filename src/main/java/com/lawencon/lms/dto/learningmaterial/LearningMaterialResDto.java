package com.lawencon.lms.dto.learningmaterial;

public class LearningMaterialResDto {

	private Long id;
	private String materialCode;
	private String materialName;
	private String materialText;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
