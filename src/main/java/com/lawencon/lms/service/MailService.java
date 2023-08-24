package com.lawencon.lms.service;

public interface MailService {
	void sendMail(String to, String subject, String body);
}
