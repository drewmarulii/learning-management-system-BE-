package com.lawencon.lms.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.lawencon.lms.dao.AttendanceDao;
import com.lawencon.lms.dao.LearningDao;
import com.lawencon.lms.dao.UserDao;
import com.lawencon.lms.dto.InsertResDto;
import com.lawencon.lms.dto.UpdateResDto;
import com.lawencon.lms.dto.attendance.AttendanceInsertReqDto;
import com.lawencon.lms.dto.attendance.AttendanceResDto;
import com.lawencon.lms.dto.attendance.AttendanceUpdateReqDto;
import com.lawencon.lms.model.Attendance;
import com.lawencon.lms.model.Learning;
import com.lawencon.lms.model.User;
import com.lawencon.lms.service.AttendanceService;
import com.lawencon.lms.service.PrincipalService;

@Service
public class AttendanceServiceImpl implements AttendanceService {

	private final AttendanceDao attendanceDao;
	private final UserDao userDao;
	private final LearningDao learningDao;
	private final PrincipalService principalService;
	
	@PersistenceContext
	private EntityManager em;
	
	public AttendanceServiceImpl(AttendanceDao attendanceDao, UserDao userDao, LearningDao learningDao, PrincipalService principalService) {
		this.attendanceDao = attendanceDao;
		this.userDao = userDao;
		this.learningDao = learningDao;
		this.principalService = principalService;
	}

	@Transactional
	@Override
	public InsertResDto insert(AttendanceInsertReqDto attendance) {
		InsertResDto result = new InsertResDto();
		final LocalDateTime now = LocalDateTime.now();
		
		final Attendance attend = new Attendance();
		final Learning learning = learningDao.getById(attendance.getLearningId());
		attend.setLearning(learning);
		
		attend.setAttendanceDate(now);
		
		final User student = userDao.getUserById(attendance.getStudentId());
		attend.setUser(student);
		
		attend.setIsApprove(false);
		attend.setCreatedBy(principalService.getPrincipal());
		attendanceDao.setMyAttendance(attend);
		
		result.setId(attend.getId());
		result.setMessage("Attendance Has Been Added. Waiting for Approval!");
		return result;
	}

	@Override
	public List<AttendanceResDto> getByLearning(Long learningid) {
		final List<AttendanceResDto> attendances = new ArrayList<>();
		
		attendanceDao.getAttendanceByLearning(learningid).forEach(a -> {
			final AttendanceResDto attend = new AttendanceResDto();
			attend.setAttendanceId(a.getId());
			attend.setAttendanceDate(a.getAttendanceDate().toString());
			attend.setStudentName(a.getUser().getProfile().getUserFullname());
			attend.setIsApprove(a.getIsApprove());
			
			attendances.add(attend);
		});
		
		return attendances;
	}

	@Transactional
	@Override
	public UpdateResDto updateApprove(AttendanceUpdateReqDto asset) {
	
		final Attendance attendance = attendanceDao.getAttendanceById(asset.getAttendanceId());
		attendance.setIsApprove(true);
		attendance.setUpdatedBy(principalService.getPrincipal());
		em.flush();
		
		UpdateResDto result = new UpdateResDto();
		
		result.setVersion(attendance.getVersion());
		result.setMessage("Attendance Has Been Approved");
		return result;
	}

}
