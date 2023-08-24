package com.lawencon.lms.constant;

public enum Role {
	ADMIN("Super Admin", "R001"), SISWA("Siswa", "R002"), PENGAJAR("Pengajar", "R003"), SYSTEMUSER("System User", "R004");
	
	public final String roleName;
	public final String roleCode;
	
	private Role(String roleName, String roleCode) {
		this.roleName = roleName;
		this.roleCode = roleCode;
	}

	public String getRoleName() {
		return roleName;
	}

	public String getRoleCode() {
		return roleCode;
	}
	
}