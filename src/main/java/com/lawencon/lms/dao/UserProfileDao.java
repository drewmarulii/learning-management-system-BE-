package com.lawencon.lms.dao;

import com.lawencon.lms.model.UserProfile;

public interface UserProfileDao {
	UserProfile getByID(Long id);
	UserProfile insert(UserProfile userProfile);
	boolean deleteByID(Long id);
	UserProfile getProfileById(Long profileid);
}
