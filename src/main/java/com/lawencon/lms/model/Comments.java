package com.lawencon.lms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "comments")
public class Comments extends BaseModel {

	@Column(name = "comment_description", nullable = false)
	private String commentDescription;
	
	@ManyToOne
	@JoinColumn(name = "forum_id")
	private LearningForum learningForum;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	public String getCommentDescription() {
		return commentDescription;
	}
	
	public void setCommentDescription(String commentDescription) {
		this.commentDescription = commentDescription;
	}
	
	public LearningForum getLearningForum() {
		return learningForum;
	}
	
	public void setLearningForum(LearningForum learningForum) {
		this.learningForum = learningForum;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
}
