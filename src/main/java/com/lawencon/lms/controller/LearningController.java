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
import com.lawencon.lms.dto.learning.LearningInsertReqDto;
import com.lawencon.lms.dto.learning.LearningResDto;
import com.lawencon.lms.service.LearningService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("learnings")
@SecurityRequirement(name = "bearerAuth")
public class LearningController {

	private final LearningService learningService;

	public LearningController(LearningService learningService) {
		this.learningService = learningService;
	}
	
	@GetMapping("/class")
	public ResponseEntity<List<LearningResDto>> getByClassSubject(@RequestParam("code") Long code) {
		final List<LearningResDto> learnings = learningService.getByClass(code);	
		return new ResponseEntity<>(learnings, HttpStatus.OK);
	}
	
	@GetMapping("/id")
	public ResponseEntity <LearningResDto> getById(@RequestParam("id") Long id) {
		final LearningResDto learnings = learningService.getById(id);
		return new ResponseEntity<>(learnings, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<InsertResDto> insertLearning(@RequestBody LearningInsertReqDto data) {
		final InsertResDto response = learningService.insert(data);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
}
