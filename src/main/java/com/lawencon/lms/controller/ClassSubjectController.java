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
import com.lawencon.lms.dto.classubject.ClassSubjectInsertReqDto;
import com.lawencon.lms.dto.classubject.ClassSubjectResDto;
import com.lawencon.lms.service.ClassSubjectService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("classes")
@SecurityRequirement(name = "bearerAuth")
public class ClassSubjectController {

	private final ClassSubjectService classSubjectService;

	public ClassSubjectController(ClassSubjectService classSubjectService) {
		this.classSubjectService = classSubjectService;
	}
	
	@GetMapping
	public ResponseEntity<List<ClassSubjectResDto>> getAll() {
		final List<ClassSubjectResDto> subjects = classSubjectService.getAll();
		return new ResponseEntity<>(subjects, HttpStatus.OK);
	}
	
	@GetMapping("/teacher")
	public ResponseEntity<List<ClassSubjectResDto>> getByTeacher(@RequestParam("id") Long id) {
		final List<ClassSubjectResDto> subjects = classSubjectService.getByTeacher(id);
		return new ResponseEntity<>(subjects, HttpStatus.OK);
	}
	
	@GetMapping("/student")
	public ResponseEntity<List<ClassSubjectResDto>> getUnenrolledClass(@RequestParam("id") Long id) {
		final List<ClassSubjectResDto> subjects = classSubjectService.getUnenrolledClass(id);
		return new ResponseEntity<>(subjects, HttpStatus.OK);
	}
	
	@GetMapping("/myclass")
	public ResponseEntity<List<ClassSubjectResDto>> getMyClass(@RequestParam("id") Long id) {
		final List<ClassSubjectResDto> subjects = classSubjectService.getMyClasses(id);
		return new ResponseEntity<>(subjects, HttpStatus.OK);
	}
	
	@GetMapping("/id")
	public ResponseEntity<ClassSubjectResDto> getByCode(@RequestParam("code") Long id) {
		final ClassSubjectResDto subject = classSubjectService.getById(id);
		return new ResponseEntity<>(subject, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<InsertResDto> insertUser(@RequestBody ClassSubjectInsertReqDto data) {
		final InsertResDto response = classSubjectService.insert(data);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
}
