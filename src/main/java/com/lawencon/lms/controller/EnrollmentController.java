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
import com.lawencon.lms.dto.enrollment.EnrollmentInsertReqDto;
import com.lawencon.lms.dto.enrollment.EnrollmentResDto;
import com.lawencon.lms.service.EnrollmentService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("enrollments")
@SecurityRequirement(name = "bearerAuth")
public class EnrollmentController {

	private final EnrollmentService enrollmentService;

	public EnrollmentController(EnrollmentService enrollmentService) {
		this.enrollmentService = enrollmentService;
	}
	
	@GetMapping("student")
	public ResponseEntity<List<EnrollmentResDto>> getByStudent(@RequestParam("id") Long id) {
		final List<EnrollmentResDto> subjects = enrollmentService.getEnrollmentByStudent(id);	
		return new ResponseEntity<>(subjects, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<InsertResDto> insertEnroll(@RequestBody EnrollmentInsertReqDto data) {
		final InsertResDto response = enrollmentService.insert(data);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
}
