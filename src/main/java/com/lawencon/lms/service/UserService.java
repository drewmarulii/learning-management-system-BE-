package com.lawencon.lms.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.lawencon.lms.dto.user.UserListResDto;
import com.lawencon.lms.dto.InsertResDto;
import com.lawencon.lms.dto.login.LoginReqDto;
import com.lawencon.lms.dto.login.LoginResDto;
import com.lawencon.lms.dto.registration.StudentInsertReqDto;
import com.lawencon.lms.dto.user.UserInsertReqDto;
import com.lawencon.lms.dto.user.UserResDto;
import com.lawencon.lms.dto.userrole.UserRoleResDto;

public interface UserService extends UserDetailsService {
	List<UserResDto> getAll(); 
	InsertResDto insert(UserInsertReqDto user);
	LoginResDto login(LoginReqDto user);
	List<UserListResDto> getByRoleCode(String roleCode);
	InsertResDto studentRegistration(StudentInsertReqDto student);
	List<UserListResDto> getAllUser();
	List<UserRoleResDto> getAllRoles();
	UserListResDto getByID(long id);
}
