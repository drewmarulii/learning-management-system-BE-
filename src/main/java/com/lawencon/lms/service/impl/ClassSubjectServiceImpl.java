package com.lawencon.lms.service.impl;

import static com.lawencon.lms.util.GeneratorID.generateID;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.lawencon.lms.dao.ClassSubjectDao;
import com.lawencon.lms.dao.FileDao;
import com.lawencon.lms.dao.UserDao;
import com.lawencon.lms.dao.UserProfileDao;
import com.lawencon.lms.dto.InsertResDto;
import com.lawencon.lms.dto.classubject.ClassSubjectInsertReqDto;
import com.lawencon.lms.dto.classubject.ClassSubjectResDto;
import com.lawencon.lms.model.ClassSubject;
import com.lawencon.lms.model.File;
import com.lawencon.lms.model.User;
import com.lawencon.lms.model.UserProfile;
import com.lawencon.lms.service.ClassSubjectService;
import com.lawencon.lms.service.PrincipalService;

@Service
public class ClassSubjectServiceImpl implements ClassSubjectService {

	private final ClassSubjectDao classSubjectDao;
	private final FileDao fileDao;
	private final UserDao userDao;
	private final UserProfileDao userProfileDao;
	private final PrincipalService principalService;
	
	@PersistenceContext
	private EntityManager em;
	
	public ClassSubjectServiceImpl(ClassSubjectDao classSubjectDao, FileDao fileDao, UserDao userDao, UserProfileDao userProfileDao, PrincipalService principalService) {
		this.classSubjectDao = classSubjectDao;
		this.fileDao = fileDao;
		this.userDao = userDao;
		this.userProfileDao = userProfileDao;
		this.principalService = principalService;
	}

	@Transactional
	@Override
	public InsertResDto insert(ClassSubjectInsertReqDto subject) {
		InsertResDto result = new InsertResDto();
		
		File file = new File();
		file.setFile(subject.getFile());
		file.setFileExtension(subject.getFileExtension());
		file.setCreatedBy(principalService.getPrincipal());
		file = fileDao.insert(file);
		
		final ClassSubject newClass = new ClassSubject();
		final String randomCode = generateID(5);
		newClass.setClassCode(randomCode);
		newClass.setClassName(subject.getClassName());
		newClass.setFile(file);
		final User teacher = userDao.getUserById(subject.getTeacherId());
		newClass.setTeacher(teacher);
		newClass.setCreatedBy(principalService.getPrincipal());
		
		classSubjectDao.insert(newClass);
		
		result.setId(newClass.getId());
		result.setMessage("Class Subject: " + newClass.getClassName() + " Has Been Added");
		return result;
	}

	@Override
	public List<ClassSubjectResDto> getAll() {
		final List<ClassSubjectResDto> classes = new ArrayList<>();
		
		classSubjectDao.getAll().forEach(c -> {
			final ClassSubjectResDto classe = new ClassSubjectResDto();
			classe.setId(c.getId());
			final File file = fileDao.getByID(c.getFile().getId());
			classe.setFileId(file.getId());
			classe.setClassCode(c.getClassCode());
			classe.setClassName(c.getClassName());
			
			final User teacher = userDao.getUserById(c.getTeacher().getId());
			final UserProfile profile = userProfileDao.getProfileById(teacher.getProfile().getId());
			teacher.setProfile(profile);
			classe.setTeacherName(teacher.getProfile().getUserFullname());
			classes.add(classe);
		});
		
		return classes;
	}

	@Override
	public List<ClassSubjectResDto> getByTeacher(Long id) {
		final List<ClassSubjectResDto> classes = new ArrayList<>();
		
		classSubjectDao.getClassByTeacher(id).forEach(c -> {
			final ClassSubjectResDto myClass = new ClassSubjectResDto();
			myClass.setId(c.getId());
			final File file = fileDao.getByID(c.getFile().getId());
			myClass.setFileId(file.getId());
			myClass.setClassCode(c.getClassCode());
			myClass.setClassName(c.getClassName());
			
			final User teacher = userDao.getUserById(c.getTeacher().getId());
			final UserProfile profile = userProfileDao.getProfileById(teacher.getProfile().getId());
			teacher.setProfile(profile);
			myClass.setTeacherName(teacher.getProfile().getUserFullname());
			classes.add(myClass);
		});
		
		return classes;
	}

	@Override
	public List<ClassSubjectResDto> getUnenrolledClass(Long id) {
		final List<ClassSubjectResDto> classes = new ArrayList<>();
		
		classSubjectDao.getUnenrolledClass(id).forEach(c -> {
			final ClassSubjectResDto myClass = new ClassSubjectResDto();
			myClass.setId(c.getId());
			final File file = fileDao.getByID(c.getFile().getId());
			myClass.setFileId(file.getId());
			myClass.setClassCode(c.getClassCode());
			myClass.setClassName(c.getClassName());
			
			final User teacher = userDao.getUserById(c.getTeacher().getId());
			final UserProfile profile = userProfileDao.getProfileById(teacher.getProfile().getId());
			teacher.setProfile(profile);
			myClass.setTeacherName(teacher.getProfile().getUserFullname());
			classes.add(myClass);
		});
		
		return classes;
	}

	@Override
	public List<ClassSubjectResDto> getMyClasses(Long id) {
		final List<ClassSubjectResDto> classes = new ArrayList<>();
		
		classSubjectDao.getMyClassesStudent(id).forEach(c -> {
			final ClassSubjectResDto myClass = new ClassSubjectResDto();
			myClass.setId(c.getId());
			final File file = fileDao.getByID(c.getFile().getId());
			myClass.setFileId(file.getId());
			myClass.setClassCode(c.getClassCode());
			myClass.setClassName(c.getClassName());
			
			final User teacher = userDao.getUserById(c.getTeacher().getId());
			final UserProfile profile = userProfileDao.getProfileById(teacher.getProfile().getId());
			teacher.setProfile(profile);
			myClass.setTeacherName(teacher.getProfile().getUserFullname());
			classes.add(myClass);
		});
		
		return classes;
	}

	@Override
	public ClassSubjectResDto getById(Long id) {
		
		final ClassSubject classSubject = classSubjectDao.getSubjectById(id);
		
		final ClassSubjectResDto subject = new ClassSubjectResDto();
		subject.setId(classSubject.getId());
		final File file = fileDao.getByID(classSubject.getFile().getId());
		subject.setFileId(file.getId());
		subject.setClassCode(classSubject.getClassCode());
		subject.setClassName(classSubject.getClassName());
		
		final User teacher = userDao.getUserById(classSubject.getTeacher().getId());
		final UserProfile profile = userProfileDao.getProfileById(teacher.getProfile().getId());
		teacher.setProfile(profile);
		subject.setTeacherName(teacher.getProfile().getUserFullname());
		
		return subject;
	}

}
