package com.lawencon.lms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "question_option")
public class QuestionOption extends BaseModel {

	@Column(name = "option_label", length = 1, nullable = true)
	private String optionLabel;
	
	@Column(name = "option_desc", nullable = true)
	private String optionDesc;
	
	@Column(name = "is_true", nullable = true)
	private Boolean isTrue;
	
	public String getOptionLabel() {
		return optionLabel;
	}
	
	public void setOptionLabel(String optionLabel) {
		this.optionLabel = optionLabel;
	}
	
	public String getOptionDesc() {
		return optionDesc;
	}
	
	public void setOptionDesc(String optionDesc) {
		this.optionDesc = optionDesc;
	}
	
	public Boolean getIsTrue() {
		return isTrue;
	}
	
	public void setIsTrue(Boolean isTrue) {
		this.isTrue = isTrue;
	}
	
}
