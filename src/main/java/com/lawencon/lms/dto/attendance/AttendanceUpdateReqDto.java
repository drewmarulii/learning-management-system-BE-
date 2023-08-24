package com.lawencon.lms.dto.attendance;

public class AttendanceUpdateReqDto {

	private Long attendanceId;
	private boolean isApprove;

	public Long getAttendanceId() {
		return attendanceId;
	}

	public void setAttendanceId(Long attendanceId) {
		this.attendanceId = attendanceId;
	}

	public boolean isApprove() {
		return isApprove;
	}

	public void setApprove(boolean isApprove) {
		this.isApprove = isApprove;
	}	
}
