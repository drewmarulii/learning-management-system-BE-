package com.lawencon.lms.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.lms.dto.InsertResDto;
import com.lawencon.lms.dto.UpdateResDto;
import com.lawencon.lms.dto.attendance.AttendanceInsertReqDto;
import com.lawencon.lms.dto.attendance.AttendanceResDto;
import com.lawencon.lms.dto.attendance.AttendanceUpdateReqDto;
import com.lawencon.lms.service.AttendanceService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("attendances")
@SecurityRequirement(name = "bearerAuth")
public class AttendanceController {

	private final AttendanceService attendanceService;

	public AttendanceController(AttendanceService attendanceService) {
		this.attendanceService = attendanceService;
	}
	
	@GetMapping("/learning")
	public ResponseEntity<List<AttendanceResDto>> getByLearning(@RequestParam("id") Long id) {
		final List<AttendanceResDto> attends = attendanceService.getByLearning(id);
		return new ResponseEntity<>(attends, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<InsertResDto> insertUser(@RequestBody AttendanceInsertReqDto data) {
		final InsertResDto response = attendanceService.insert(data);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@PatchMapping
	public ResponseEntity<UpdateResDto> updateAsset(@RequestBody AttendanceUpdateReqDto asset) {
		final UpdateResDto result = attendanceService.updateApprove(asset);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
