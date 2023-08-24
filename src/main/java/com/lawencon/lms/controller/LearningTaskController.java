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
import com.lawencon.lms.dto.learningtask.LearningTaskInsertReqDto;
import com.lawencon.lms.dto.learningtask.LearningTaskResDto;
import com.lawencon.lms.service.LearningTaskService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("tasks")
@SecurityRequirement(name = "bearerAuth")
public class LearningTaskController {

	private final LearningTaskService learningTaskService;

	public LearningTaskController(LearningTaskService learningTaskService) {
		this.learningTaskService = learningTaskService;
	}
	
	@GetMapping("/learning")
	public ResponseEntity<List<LearningTaskResDto>> getByLearning(@RequestParam("id") Long id) {
		final List<LearningTaskResDto> tasks = learningTaskService.getByLearningId(id);
		return new ResponseEntity<>(tasks, HttpStatus.OK);
	}
	
	@GetMapping("/search")
	public ResponseEntity<LearningTaskResDto> getById(@RequestParam("id") Long id) {
		final LearningTaskResDto tasks = learningTaskService.getById(id);
		return new ResponseEntity<>(tasks, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<InsertResDto> insertLearning(@RequestBody LearningTaskInsertReqDto data) {
		final InsertResDto response = learningTaskService.insert(data);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
}
