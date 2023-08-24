package com.lawencon.lms.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.lms.dto.InsertResDto;
import com.lawencon.lms.dto.learningforum.LearningForumInsertReqDto;
import com.lawencon.lms.dto.learningforum.LearningForumResDto;
import com.lawencon.lms.service.ForumService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("forum")
@SecurityRequirement(name = "bearerAuth")
public class LearningForumController {

	private final ForumService forumService;

	public LearningForumController(ForumService forumService) {
		this.forumService = forumService;
	}

	@GetMapping("/search")
	public ResponseEntity<LearningForumResDto> getById(@RequestParam("id") Long id) {
		final LearningForumResDto forum = forumService.getForum(id);
		return new ResponseEntity<>(forum, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<InsertResDto> insertForum(@RequestBody LearningForumInsertReqDto data) {
		final InsertResDto response = forumService.insertForum(data);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
}
