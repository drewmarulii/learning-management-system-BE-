package com.lawencon.lms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "question_type")
public class QuestionType extends BaseModel {

	@Column(name = "type_code", length = 5, unique = true, nullable = false )
	private String typeCode;
	
	@Column(name = "type_name", length = 15, nullable = false)
	private String typeName;
	
	public String getTypeCode() {
		return typeCode;
	}
	
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	
	public String getTypeName() {
		return typeName;
	}
	
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}	
	
}
