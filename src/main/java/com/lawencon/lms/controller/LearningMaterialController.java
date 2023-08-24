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
import com.lawencon.lms.dto.learningmaterial.LearningMaterialInsertReqDto;
import com.lawencon.lms.dto.learningmaterial.LearningMaterialResDto;
import com.lawencon.lms.service.LearningMaterialService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("materials")
@SecurityRequirement(name = "bearerAuth")
public class LearningMaterialController {

	private final LearningMaterialService learningMaterialService;

	public LearningMaterialController(LearningMaterialService learningMaterialService) {
		this.learningMaterialService = learningMaterialService;
	}
	
	@GetMapping("/learning")
	public ResponseEntity<List<LearningMaterialResDto>> getByLearning(@RequestParam("id") Long id) {
		final List<LearningMaterialResDto> materials = learningMaterialService.getByLearningId(id);
		return new ResponseEntity<>(materials, HttpStatus.OK);
	}
	
	@GetMapping("/detail")
	public ResponseEntity<LearningMaterialResDto> getById(@RequestParam("id") Long id) {
		final LearningMaterialResDto material = learningMaterialService.getById(id);
		return new ResponseEntity<>(material, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<InsertResDto> insertLearning(@RequestBody LearningMaterialInsertReqDto data) {
		final InsertResDto response = learningMaterialService.insert(data);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
}
