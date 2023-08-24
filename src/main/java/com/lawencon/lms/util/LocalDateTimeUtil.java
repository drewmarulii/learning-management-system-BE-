package com.lawencon.lms.util;

import static com.lawencon.lms.util.ScannerUtil.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeUtil {
	
	public static LocalDateTime setDueDate() {
	    String str = scannerString("Enter Due Date [yyyy-MM-dd HH:mm:ss]: ", 1);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
		 
		return dateTime;
	}
	
	public static LocalDateTime formatDate(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
		LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
		
		
		return dateTime;
	}
}
