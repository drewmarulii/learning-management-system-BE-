package com.lawencon.lms.dao;

import java.util.List;

import com.lawencon.lms.model.ClassSubject;

public interface ClassSubjectDao {
	ClassSubject getSubjectById(Long id);
	ClassSubject insert(ClassSubject classSubject);
	List<ClassSubject> getAll();
	List<ClassSubject> getClassByTeacher(Long id);
	List<ClassSubject> getUnenrolledClass(Long id);
	List<ClassSubject> getMyClassesStudent(Long id);
}
