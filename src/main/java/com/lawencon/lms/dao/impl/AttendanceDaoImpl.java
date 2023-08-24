package com.lawencon.lms.dao.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lms.dao.AttendanceDao;
import com.lawencon.lms.model.Attendance;
import com.lawencon.lms.model.User;
import com.lawencon.lms.model.UserProfile;

@Repository
@Profile("native-query")
public class AttendanceDaoImpl implements AttendanceDao {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Attendance setMyAttendance(Attendance attendance) {
		em.persist(attendance);
		return attendance;
	}
	
	public Attendance getAttendanceById(Long id) {
		final Attendance attendance = this.em.find(Attendance.class, id);
		return attendance;
	}
	
	@Override
	public List<Attendance> getAttendanceByLearning(Long learning) {
		final List<Attendance> attendances = new ArrayList<>();
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

		final String sql = "SELECT "
				+ "	atd.id, "
				+ "	up.user_fullname, "
				+ "	atd.is_approve, "
				+ " TO_CHAR(attendance_date, 'YYYY-MM-DD HH24:MI') AS attend_date "
				+ "FROM "
				+ "	attendance atd "
				+ "INNER JOIN "
				+ "	user_account ua ON ua.id = atd.student_id "
				+ "INNER JOIN "
				+ "	user_profile up ON up.id = ua.profile_id "
				+ "WHERE "
				+ "	learning_id = :learningId";
		final List<?> attendanceObjs = this.em.createNativeQuery(sql)
				.setParameter("learningId", learning)
				.getResultList();
		
		if (attendanceObjs.size() > 0) {
			for (Object attendanceObj : attendanceObjs) {
				final Object[] attendanceArr = (Object[]) attendanceObj;
				
				final Attendance attendance = new Attendance();
				attendance.setId(Long.valueOf(attendanceArr[0].toString()));
				attendance.setIsApprove(Boolean.valueOf(attendanceArr[2].toString()));
				attendance.setAttendanceDate(LocalDateTime.parse(attendanceArr[3].toString(), formatter));
				
				final UserProfile profile = new UserProfile();
				profile.setUserFullname(attendanceArr[1].toString());
				final User user = new User();
				user.setProfile(profile);
				attendance.setUser(user);
				
				attendances.add(attendance);				
			}
		}
		
		return attendances;
	}

	@Override
	public Attendance getByStudent(Long id) {
		final String sql = "SELECT "
				+ "	is_approve "
				+ "FROM "
				+ "	attendance a "
				+ "INNER JOIN "
				+ "	user_account ua ON a.student_id = ua.id "
				+ "WHERE "
				+ "	student_id = :studentId";
		final Object attendObj = this.em.createNativeQuery(sql)
				.setParameter("studentId", id)
				.getSingleResult();
		
		final Object[] attendArr = (Object[]) attendObj;
		
		Attendance attend = null;
		
		if (attendArr.length > 0) {
			attend = new Attendance();
			attend.setIsApprove(Boolean.valueOf(attendArr[0].toString()));
		}
		
		return attend;
	}
	
}
