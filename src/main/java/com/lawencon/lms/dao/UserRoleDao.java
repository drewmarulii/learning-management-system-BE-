package com.lawencon.lms.dao;

import java.util.List;

import com.lawencon.lms.model.UserRole;

public interface UserRoleDao {
	List<UserRole> getAll();
	UserRole getRoleById(Long roleId);
	UserRole insert(UserRole role); 
}
