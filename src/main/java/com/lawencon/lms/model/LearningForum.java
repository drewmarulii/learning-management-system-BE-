package com.lawencon.lms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "learning_forum")
public class LearningForum extends BaseModel {

	@Column(name = "forum_code", length = 10, unique = true, nullable = false)
	private String forumCode;
	
	@Column(name = "forum_title", length = 50, nullable = false)
	private String forumTitle;
	
	@Column(name = "forum_description", nullable = false)
	private String forumDescription;
	
	@OneToOne
	@JoinColumn(name = "learning_id")
	private Learning learning;
	
	public String getForumCode() {
		return forumCode;
	}
	
	public void setForumCode(String forumCode) {
		this.forumCode = forumCode;
	}
	
	public String getForumTitle() {
		return forumTitle;
	}
	
	public void setForumTitle(String forumTitle) {
		this.forumTitle = forumTitle;
	}
	
	public String getForumDescription() {
		return forumDescription;
	}
	
	public void setForumDescription(String forumDescription) {
		this.forumDescription = forumDescription;
	}
	
	public Learning getLearning() {
		return learning;
	}
	
	public void setLearning(Learning learning) {
		this.learning = learning;
	}
	
}
