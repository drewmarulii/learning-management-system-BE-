package com.lawencon.lms.service.impl;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.lawencon.lms.service.PrincipalService;

@Service
public class PrincipalServiceImpl implements PrincipalService {

	public Long getPrincipal() {
		final int principal = (int) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		final Long  id = (long) principal;
		
		return id;
	}
	
}
