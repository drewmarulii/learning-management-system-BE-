package com.lawencon.lms.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lms.dao.ClassSubjectDao;
import com.lawencon.lms.model.ClassSubject;

@Repository
@Profile("native-query")
public class ClassSubjectDaoImpl implements ClassSubjectDao {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public ClassSubject getSubjectById(Long id) {
		final ClassSubject subject = this.em.find(ClassSubject.class, id);
		return subject;
	}

	@Override
	public ClassSubject insert(ClassSubject classSubject) {
		em.persist(classSubject);
		return classSubject;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClassSubject> getAll() {
		final String sql = "SELECT "
				+ "	*"
				+ "FROM "
				+ "	class";
		final List<ClassSubject> subjects = this.em.createNativeQuery(sql, ClassSubject.class)
				.getResultList();
		return subjects;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClassSubject> getClassByTeacher(Long id) {
		final String sql = "SELECT "
				+ "	* "
				+ "FROM "
				+ "	class "
				+ "WHERE "
				+ "	teacher_id = :teacherId";
		final List<ClassSubject> subjects = this.em.createNativeQuery(sql, ClassSubject.class)
				.setParameter("teacherId", id)
				.getResultList();
		
		return subjects;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClassSubject> getUnenrolledClass(Long id) {
		final String sql = "SELECT "
				+ "	*"
				+ "FROM "
				+ "	class c "
				+ "WHERE"
				+ "	id NOT IN ("
				+ "		SELECT "
				+ "			class_id "
				+ "		FROM "
				+ "			enrollment e "
				+ "		WHERE "
				+ "			student_id = :studentId"
				+ "	)";
		final List<ClassSubject> subjects = this.em.createNativeQuery(sql, ClassSubject.class)
				.setParameter("studentId", id)
				.getResultList();
		
		return subjects;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClassSubject> getMyClassesStudent(Long id) {
		final String sql = "SELECT "
				+ "	*"
				+ "FROM "
				+ "	\"class\" c "
				+ "WHERE"
				+ "	id IN ( "
				+ "		SELECT "
				+ "			class_id "
				+ "		FROM "
				+ "			enrollment e "
				+ "		WHERE "
				+ "			student_id = :studentId "
				+ "	)";
		final List<ClassSubject> subjects = this.em.createNativeQuery(sql, ClassSubject.class)
				.setParameter("studentId", id)
				.getResultList();
		
		return subjects;
	}

}
