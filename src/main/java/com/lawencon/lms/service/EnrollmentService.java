package com.lawencon.lms.service;

import java.util.List;

import com.lawencon.lms.dto.InsertResDto;
import com.lawencon.lms.dto.enrollment.EnrollmentInsertReqDto;
import com.lawencon.lms.dto.enrollment.EnrollmentResDto;

public interface EnrollmentService {
	InsertResDto insert(EnrollmentInsertReqDto enroll);
	List<EnrollmentResDto> getEnrollmentByStudent(Long studentid);
}
