package com.lawencon.lms.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import com.lawencon.lms.dao.FileDao;
import com.lawencon.lms.model.File;
import com.lawencon.lms.service.FileService;

@Service
public class FileServiceImpl implements FileService {
	
	private final FileDao fileDao;
	
	@PersistenceContext
	private EntityManager em;
	
	public FileServiceImpl(FileDao fileDao) {
		this.fileDao = fileDao;
	}

	@Override
	public File getById(Long id) {
		final File file = fileDao.getByID(id);
		
		return file;
	}
	
	@Override
	public File insert(File file) {
		final File newfile = fileDao.insert(file);
		
		return newfile;
	}

	@Override
	public boolean delete(Long id) {
		final boolean file = fileDao.deleteByID(id);
	
		return file;
	}
	
}
