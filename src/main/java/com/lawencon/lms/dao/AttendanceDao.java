package com.lawencon.lms.dao;

import java.util.List;

import com.lawencon.lms.model.Attendance;

public interface AttendanceDao {
	Attendance setMyAttendance(Attendance attendance);
	Attendance getAttendanceById(Long id);
	List<Attendance> getAttendanceByLearning(Long learning);
	Attendance getByStudent(Long id);
}
