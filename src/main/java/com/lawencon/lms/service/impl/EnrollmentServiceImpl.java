package com.lawencon.lms.service.impl;

import static com.lawencon.lms.util.GeneratorID.generateID;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import com.lawencon.lms.dao.ClassSubjectDao;
import com.lawencon.lms.dao.EnrollmentDao;
import com.lawencon.lms.dao.UserDao;
import com.lawencon.lms.dto.InsertResDto;
import com.lawencon.lms.dto.enrollment.EnrollmentInsertReqDto;
import com.lawencon.lms.dto.enrollment.EnrollmentResDto;
import com.lawencon.lms.model.ClassSubject;
import com.lawencon.lms.model.Enrollment;
import com.lawencon.lms.model.User;
import com.lawencon.lms.service.EnrollmentService;
import com.lawencon.lms.service.PrincipalService;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

	private final EnrollmentDao enrollmentDao;
	private final UserDao userDao;
	private final ClassSubjectDao classSubjectDao;
	private final PrincipalService principalService;

	@PersistenceContext
	private EntityManager em;
	
	public EnrollmentServiceImpl(EnrollmentDao enrollmentDao, UserDao userDao, 
			ClassSubjectDao classSubjectDao, PrincipalService principalService) {
		this.enrollmentDao = enrollmentDao;
		this.userDao = userDao;
		this.classSubjectDao = classSubjectDao;
		this.principalService = principalService;
	}

	@Override
	public InsertResDto insert(EnrollmentInsertReqDto enroll) {
		InsertResDto result = new InsertResDto();
		final LocalDateTime now = LocalDateTime.now();
		
		final Enrollment newEnroll = new Enrollment();
		final String randomCode = generateID(5);
		newEnroll.setEnrollmentCode(randomCode);
		newEnroll.setEnrollmentDate(now);
		
		final User student = userDao.getUserById(enroll.getStudentId());
		newEnroll.setStudent(student);
		
		final ClassSubject subject = classSubjectDao.getSubjectById(enroll.getClassId());
		newEnroll.setClassSubject(subject);
		newEnroll.setCreatedBy(principalService.getPrincipal());
		enrollmentDao.insert(newEnroll);
		
		result.setId(newEnroll.getId());
		result.setMessage("ENROLLMENT CODE: " + newEnroll.getEnrollmentCode() + " Has Been Created! ");
		return result;
	}

	@Override
	public List<EnrollmentResDto> getEnrollmentByStudent(Long studentid) {
		final List<EnrollmentResDto> enrollments = new ArrayList<>();
		
		enrollmentDao.getAllMyEnrollment(studentid).forEach(e -> {
			final EnrollmentResDto enrollment = new EnrollmentResDto();
			enrollment.setEnrollmentCode(e.getEnrollmentCode());
			enrollment.setEnrollmentDate(e.getEnrollmentDate().toString());
			enrollment.setStudentName(e.getStudent().getProfile().getUserFullname());
			enrollment.setClassName(e.getClassSubject().getClassName());
		
			enrollments.add(enrollment);
		});
		
		return enrollments;
	}
}
