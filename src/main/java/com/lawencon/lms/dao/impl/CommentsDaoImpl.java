package com.lawencon.lms.dao.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lms.dao.CommentsDao;
import com.lawencon.lms.model.Comments;
import com.lawencon.lms.model.User;
import com.lawencon.lms.model.UserProfile;

@Repository
@Profile("native-query")
public class CommentsDaoImpl implements CommentsDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Comments getById(Long id) {
		final Comments comments = this.em.find(Comments.class, id);
		return comments;
	}

	@Override
	public Comments insert(Comments comments) {
		em.persist(comments);
		return comments;
	}

	@Override
	public List<Comments> getCommentByForum(Long id) {
		final List<Comments> comments = new ArrayList<>();
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"); 
		
		final String sql = "SELECT "
				+ "	c.id, c.comment_description, c.user_id, TO_CHAR(c.created_at, 'YYYY-MM-DD HH24:MI') AS date, up.user_fullname "
				+ "FROM  "
				+ "	comments c "
				+ "INNER JOIN "
				+ "	learning_forum lf ON c.forum_id = lf.id "
				+ "INNER JOIN  "
				+ "	user_account ua ON c.user_id  = ua.id "
				+ "INNER JOIN  "
				+ "	user_profile up ON ua.profile_id = up.id "
				+ "WHERE  "
				+ "	c.forum_id = :forum "
				+ "ORDER BY "
				+ "	c.created_at DESC";
		final List<?> commentsObjs = this.em.createNativeQuery(sql)
				.setParameter("forum", id)
				.getResultList();
		
		if (commentsObjs.size() > 0) {
			for (Object commentsObj : commentsObjs) {
				final Object[] commentsArr = (Object[]) commentsObj;
				final Comments comment = new Comments();
				comment.setId(Long.valueOf(commentsArr[0].toString()));
				comment.setCommentDescription(commentsArr[1].toString());
				
				final User user = new User();
				user.setId(Long.valueOf(commentsArr[2].toString()));
				comment.setCreatedAt(LocalDateTime.parse(commentsArr[3].toString(), formatter));
				
				final UserProfile profile = new UserProfile();
				profile.setUserFullname(commentsArr[4].toString());
				user.setProfile(profile);
				comment.setUser(user);				
				
				comments.add(comment);
			}
		}
		
		return comments;
	}

}
