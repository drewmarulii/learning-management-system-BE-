package com.lawencon.lms.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.lms.dto.InsertResDto;
import com.lawencon.lms.dto.comments.CommentInsertReqDto;
import com.lawencon.lms.dto.comments.CommentResDto;
import com.lawencon.lms.service.CommentService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("comment")
@SecurityRequirement(name = "bearerAuth")
public class CommentController {

	private final CommentService commentService;

	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}
	
	@PostMapping
	public ResponseEntity<InsertResDto> insertComment(@org.springframework.web.bind.annotation.RequestBody CommentInsertReqDto data) {
		final InsertResDto response = commentService.insertComment(data);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<CommentResDto>> getComments(@RequestParam("id") Long id) {
		final List<CommentResDto> comments = commentService.getByForum(id);
		return new ResponseEntity<>(comments, HttpStatus.OK);
	}
}
