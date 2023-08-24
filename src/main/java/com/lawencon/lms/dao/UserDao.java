package com.lawencon.lms.dao;

import java.util.List;

import com.lawencon.lms.model.User;

public interface UserDao {
	List<User> getAll(); 
	List<User> getByRoleCode(String roleCode);
	User insert(User user);
	User getByUsername(String email);
	User getUserById(Long userId);
}
