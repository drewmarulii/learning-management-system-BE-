package com.lawencon.lms.service;

import com.lawencon.lms.model.File;

public interface FileService {
	 File getById(Long id); 
	 File insert(File file);
	 boolean delete(Long id);
}
