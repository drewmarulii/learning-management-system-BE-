package com.lawencon.lms.dto.learningforum;

public class LearningForumResDto {

	private Long id;
	private String forumCode;
	private String forumTitle;
	private String forumDescription;
	private Long learningId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Long getLearningId() {
		return learningId;
	}

	public void setLearningId(Long learningId) {
		this.learningId = learningId;
	}

}
