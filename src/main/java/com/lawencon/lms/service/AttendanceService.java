package com.lawencon.lms.service;

import java.util.List;

import com.lawencon.lms.dto.InsertResDto;
import com.lawencon.lms.dto.UpdateResDto;
import com.lawencon.lms.dto.attendance.AttendanceInsertReqDto;
import com.lawencon.lms.dto.attendance.AttendanceResDto;
import com.lawencon.lms.dto.attendance.AttendanceUpdateReqDto;

public interface AttendanceService {
	InsertResDto insert(AttendanceInsertReqDto attendance);
	List<AttendanceResDto> getByLearning(Long learningid);
	UpdateResDto updateApprove(AttendanceUpdateReqDto asset);
}
