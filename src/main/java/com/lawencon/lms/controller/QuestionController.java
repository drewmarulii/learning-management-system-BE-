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
import com.lawencon.lms.dto.question.QuestionInsertReqDto;
import com.lawencon.lms.dto.question.QuestionResDto;
import com.lawencon.lms.dto.question.QuestionTypeResDto;
import com.lawencon.lms.service.QuestionService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("questions")
@SecurityRequirement(name = "bearerAuth")
public class QuestionController {

	private final QuestionService questionService;

	public QuestionController(QuestionService questionService) {
		this.questionService = questionService;
	}	
	
	@GetMapping("/task")
	public ResponseEntity<List<QuestionResDto>> getByTask(@RequestParam("id") Long id) {
		final List<QuestionResDto> tasks = questionService.getByTask(id);
		return new ResponseEntity<>(tasks, HttpStatus.OK);
	}
	
	@GetMapping("/type")
	public ResponseEntity<List<QuestionTypeResDto>> getTypes() {
		final List<QuestionTypeResDto> types = questionService.getType();
		return new ResponseEntity<>(types, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<InsertResDto> insertQuestion(@RequestBody QuestionInsertReqDto data) {
		final InsertResDto response = questionService.insert(data);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
}
