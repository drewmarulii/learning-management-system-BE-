package com.lawencon.lms.dao;

import java.util.List;

import com.lawencon.lms.model.Enrollment;

public interface EnrollmentDao {
	Enrollment insert(Enrollment enrollment);
	List<Enrollment> getAllMyEnrollment(Long userid);
	Enrollment getEnrollmentById(Long enrollmentid);
}
