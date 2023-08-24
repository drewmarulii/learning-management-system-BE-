INSERT INTO file (file, file_extension, created_by, created_at, is_active, version) VALUES 
	('Profil_Picture', '.png', 1, now(), true, 1);

INSERT INTO user_role (role_code, role_name, created_by, created_at, is_active, version) VALUES 
	('R001', 'Super Admin', 1, now(), true, 1),
	('R002', 'Siswa', 1, now(), true, 1),
	('R003', 'Pengajar', 1, now(), true, 1),
	('R004', 'Sistem User', 1, now(), true, 1);

INSERT INTO user_profile (user_id_number, user_fullname, user_gender, user_address, file_id, created_by, created_at, is_active, version) VALUES 
	('UID-0100', 'Andrew Maruli', 'Male', 'Jl. Kokas', 1, 1, now(), true, 1);

INSERT INTO user_account (user_email, user_password, profile_id, role_id, created_by, created_at, is_active, version) VALUES 
	('superadmin@email.com', '$2a$12$KEx0d/b0NEQh5Ey5ONN8z.RuwnmVowmPelzP33aXJ3/01U0xLUJdK', 1, 1, 1, now(), true, 1);

INSERT INTO question_type (type_code, type_name, created_by, created_at, is_active, version) VALUES 
	('TY001', 'Essay', 1, now(), true, 1),
	('TY002', 'File', 1, now(), true, 1);