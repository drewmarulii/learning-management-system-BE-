package com.lawencon.lms.service;

import java.util.List;

import com.lawencon.lms.dto.InsertResDto;
import com.lawencon.lms.dto.classubject.ClassSubjectInsertReqDto;
import com.lawencon.lms.dto.classubject.ClassSubjectResDto;

public interface ClassSubjectService {
	InsertResDto insert(ClassSubjectInsertReqDto subject);
	List<ClassSubjectResDto> getAll();
	List<ClassSubjectResDto> getByTeacher(Long id);
	List<ClassSubjectResDto> getUnenrolledClass(Long id);
	List<ClassSubjectResDto> getMyClasses(Long id);
	ClassSubjectResDto getById(Long id);
}
