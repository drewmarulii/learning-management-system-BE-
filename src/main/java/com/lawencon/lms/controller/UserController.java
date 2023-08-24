package com.lawencon.lms.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.lms.dto.InsertResDto;
import com.lawencon.lms.dto.user.UserInsertReqDto;
import com.lawencon.lms.dto.user.UserListResDto;
import com.lawencon.lms.dto.userrole.UserRoleResDto;
import com.lawencon.lms.service.UserService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("users")
@SecurityRequirement(name = "bearerAuth")
public class UserController {

	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping
	public ResponseEntity<List<UserListResDto>> getAll() {
		final List<UserListResDto> users = userService.getAllUser();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	@GetMapping("/find")
	public ResponseEntity<UserListResDto> getById(@RequestParam("id") long id) {
		final UserListResDto profile = userService.getByID(id);
		return new ResponseEntity<>(profile, HttpStatus.OK);
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<UserListResDto>> getByRoleCode(@RequestParam("code") String code) {
		final List<UserListResDto> users = userService.getByRoleCode(code);
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<InsertResDto> insertUser(@RequestBody UserInsertReqDto data) {
		final InsertResDto response = userService.insert(data);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/roles")
	public ResponseEntity<List<UserRoleResDto>> getAllRole() {
		final List<UserRoleResDto> roles = userService.getAllRoles();
		return new ResponseEntity<>(roles, HttpStatus.OK);
	}

}
