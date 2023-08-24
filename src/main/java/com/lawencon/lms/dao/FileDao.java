package com.lawencon.lms.dao;

import com.lawencon.lms.model.File;

public interface FileDao {
	File getByID(Long id); 
	File insert(File file);
	boolean deleteByID(Long id);
}
