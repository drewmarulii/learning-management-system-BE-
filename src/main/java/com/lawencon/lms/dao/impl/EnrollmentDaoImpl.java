package com.lawencon.lms.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lms.dao.EnrollmentDao;
import com.lawencon.lms.model.Enrollment;

@Repository
@Profile("native-query")
public class EnrollmentDaoImpl implements EnrollmentDao {
	
	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	@Override
	public Enrollment insert(Enrollment enrollment) {
		em.persist(enrollment);
		return enrollment;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Enrollment> getAllMyEnrollment(Long userid) {
		final String sql = "SELECT "
				+ "	*"
				+ "FROM "
				+ "	enrollment e "
				+ "INNER JOIN "
				+ "	class c ON e.class_id = c.id "
				+ "WHERE "
				+ "	student_id = :studentId";
		final List<Enrollment> enrollments = this.em.createNativeQuery(sql, Enrollment.class)
				.setParameter("studentId", userid)
				.getResultList();
		
		return enrollments;
	}

	@Override
	public Enrollment getEnrollmentById(Long enrollmentid) {
		final Enrollment enrollment = this.em.find(Enrollment.class, enrollmentid);
		return enrollment;
	}		
}
