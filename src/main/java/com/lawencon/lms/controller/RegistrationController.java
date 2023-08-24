package com.lawencon.lms.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.lms.dto.InsertResDto;
import com.lawencon.lms.dto.registration.StudentInsertReqDto;
import com.lawencon.lms.service.UserService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("registration")
@SecurityRequirement(name = "bearerAuth")
public class RegistrationController {

	private final UserService userService;

	public RegistrationController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping
	public ResponseEntity<InsertResDto> insertStudent(@RequestBody StudentInsertReqDto student) {
		final InsertResDto response = userService.studentRegistration(student);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
}
