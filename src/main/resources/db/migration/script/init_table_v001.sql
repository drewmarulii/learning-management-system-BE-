--DROP TABLE IF EXISTS attachment_answer;
--DROP TABLE IF EXISTS question_answer;
--DROP TABLE IF EXISTS review;
--DROP TABLE IF EXISTS question_option;
--DROP TABLE IF EXISTS attachment_question;
--DROP TABLE IF EXISTS question;
--DROP TABLE IF EXISTS question_type;
--DROP TABLE IF EXISTS learning_task;
--DROP TABLE IF EXISTS attachment_material;
--DROP TABLE IF EXISTS learning_material;
--DROP TABLE IF EXISTS enrollment;
--DROP TABLE IF EXISTS comments;
--DROP TABLE IF EXISTS learning_forum;
--DROP TABLE IF EXISTS attendance;
--DROP TABLE IF EXISTS learning;
--DROP TABLE IF EXISTS class;
--DROP TABLE IF EXISTS user_account;
--DROP TABLE IF EXISTS user_profile;
--DROP TABLE IF EXISTS user_role;
--DROP TABLE IF EXISTS file;
--DROP TABLE IF EXISTS databasechangelog;
--DROP TABLE IF EXISTS databasechangeloglock;

CREATE TABLE file (
	id serial,
	file TEXT NOT NULL,
	file_extension VARCHAR(5) NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	version int NOT NULL
);

ALTER TABLE file ADD CONSTRAINT file_pk
	PRIMARY KEY(id);

CREATE TABLE user_role (
	id serial,
	role_code VARCHAR(5) NOT NULL,
	role_name VARCHAR(20) NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	version int NOT NULL
);

ALTER TABLE user_role ADD CONSTRAINT user_role_pk
	PRIMARY KEY(id);
ALTER TABLE user_role ADD CONSTRAINT user_role_bk
	UNIQUE(role_code);
	
CREATE TABLE user_profile (
	id serial,
	user_id_number VARCHAR(20) NOT NULL,
	user_fullname VARCHAR(50) NOT NULL,
	user_gender VARCHAR(6) NOT NULL,
	user_address VARCHAR(80) NOT NULL,
	file_id int NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	version int NOT NULL
);

ALTER TABLE user_profile ADD CONSTRAINT user_profile_pk
	PRIMARY KEY(id);
ALTER TABLE user_profile ADD CONSTRAINT user_picture_fk
	FOREIGN KEY(file_id)
	REFERENCES file(id);
ALTER TABLE user_profile ADD CONSTRAINT user_profile_bk
	UNIQUE(user_id_number);
	
CREATE TABLE user_account (
	id serial,
	user_email VARCHAR(50) NOT NULL,
	user_password TEXT NOT NULL,
	profile_id int NOT NULL,
	role_id int NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	version int NOT NULL
);

ALTER TABLE user_account ADD CONSTRAINT user_account_pk
	PRIMARY KEY(id);
ALTER TABLE user_account ADD CONSTRAINT user_account_email_bk
	UNIQUE(user_email);
ALTER TABLE user_account ADD CONSTRAINT user_account_profile_fk
	FOREIGN KEY(profile_id)
	REFERENCES user_profile(id);
ALTER TABLE user_account ADD CONSTRAINT user_account_role_fk
	FOREIGN KEY(role_id)
	REFERENCES user_role(id);
	
CREATE TABLE class ( 
	id serial,
	class_code VARCHAR(10) NOT NULL,
	class_name VARCHAR(50) NOT NULL,
	file_id int NOT NULL,
	teacher_id int NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	version int NOT NULL
);

ALTER TABLE class ADD CONSTRAINT class_pk
	PRIMARY KEY(id);
ALTER TABLE class ADD CONSTRAINT class_code_bk
	UNIQUE(class_code);
ALTER TABLE class ADD CONSTRAINT class_teacher_fk
	FOREIGN KEY(teacher_id)
	REFERENCES user_account(id);
ALTER TABLE class ADD CONSTRAINT class_picture_fk
	FOREIGN KEY(file_id)
	REFERENCES file(id);

CREATE TABLE enrollment ( 
	id serial,
	enrollment_code VARCHAR(10) NOT NULL,
	enrollment_date timestamp NOT NULL,
	student_id int NOT NULL,
	class_id int NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	version int NOT NULL
);

ALTER TABLE enrollment ADD CONSTRAINT enrollment_pk
	PRIMARY KEY(id);
ALTER TABLE enrollment ADD CONSTRAINT enrollment_code_bk
	UNIQUE(enrollment_code);
ALTER TABLE enrollment ADD CONSTRAINT enrollment_student_fk
	FOREIGN KEY(student_id)
	REFERENCES user_account(id);
ALTER TABLE enrollment ADD CONSTRAINT enrollment_class_fk
	FOREIGN KEY(class_id)
	REFERENCES class(id);
	
CREATE TABLE learning ( 
	id serial,
	learning_code VARCHAR(10) NOT NULL,
	learning_topic VARCHAR(50) NOT NULL,
	learning_date timestamp NOT NULL,
	class_id int NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	version int NOT NULL
);

ALTER TABLE learning ADD CONSTRAINT learning_pk
	PRIMARY KEY(id);
ALTER TABLE learning ADD CONSTRAINT learning_code_bk
	UNIQUE(learning_code);
ALTER TABLE learning ADD CONSTRAINT learning_class_fk
	FOREIGN KEY(class_id)
	REFERENCES class(id);
ALTER TABLE learning ADD CONSTRAINT learning_section_ck
	UNIQUE(learning_date, learning_code);

CREATE TABLE learning_material (
	id serial,
	material_code VARCHAR(10) NOT NULL,
	material_name VARCHAR(50) NOT NULL,
	material_text TEXT,
	learning_id int NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	version int NOT NULL
);

ALTER TABLE learning_material ADD CONSTRAINT l_material_pk
	PRIMARY KEY(id);
ALTER TABLE learning_material ADD CONSTRAINT l_material_bk
	UNIQUE(material_code);
ALTER TABLE learning_material ADD CONSTRAINT l_material_learning_fk
	FOREIGN KEY(learning_id)
	REFERENCES learning(id);

CREATE TABLE attachment_material ( 
	id serial,
	file_id int NOT NULL,
	material_id int NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	version int NOT NULL
);

ALTER TABLE attachment_material ADD CONSTRAINT attachment_m_pk
	PRIMARY KEY(id);
ALTER TABLE attachment_material ADD CONSTRAINT attachment_m_file_fk
	FOREIGN KEY (file_id)
	REFERENCES file(id);
ALTER TABLE attachment_material ADD CONSTRAINT attachment_m_material_fk
	FOREIGN KEY (material_id)
	REFERENCES learning_material(id);

CREATE TABLE learning_task ( 
	id serial,
	assignment_code VARCHAR(10) NOT NULL,
	learning_id int NOT NULL,
	start_datetime timestamp NOT NULL,
	end_datetime timestamp NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	version int NOT NULL
); 

ALTER TABLE learning_task ADD CONSTRAINT l_task_pk
	PRIMARY KEY(id);
ALTER TABLE learning_task ADD CONSTRAINT l_task_bk
	UNIQUE(assignment_code);
ALTER TABLE learning_task ADD CONSTRAINT l_task_learning_fk
	FOREIGN KEY(learning_id)
	REFERENCES learning(id);

CREATE TABLE question_type ( 
	id serial,
	type_code VARCHAR(5) NOT NULL,
	type_name VARCHAR(15) NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	version int NOT NULL
);

ALTER TABLE question_type ADD CONSTRAINT q_type_pk
	PRIMARY KEY (id);
ALTER TABLE question_type ADD CONSTRAINT q_type_bk
	UNIQUE (type_code);

CREATE TABLE question ( 
	id serial,
	question_detail TEXT NOT NULL,
	type_id int NOT NULL,
	task_id int NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	version int NOT NULL
);

ALTER TABLE question ADD CONSTRAINT question_pk
	PRIMARY KEY (id);
ALTER TABLE question ADD CONSTRAINT question_type_fk
	FOREIGN KEY(type_id)
	REFERENCES question_type(id);
ALTER TABLE question ADD CONSTRAINT question_task_fk
	FOREIGN KEY (task_id)
	REFERENCES learning_task(id);

CREATE TABLE attachment_question ( 
	id serial,
	file_id int NOT NULL,
	question_id int NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	version int NOT NULL
);

ALTER TABLE attachment_question ADD CONSTRAINT attachment_t_pk
	PRIMARY KEY(id);
ALTER TABLE attachment_question ADD CONSTRAINT attachment_t_file_fk
	FOREIGN KEY (file_id)
	REFERENCES file(id);
ALTER TABLE attachment_question ADD CONSTRAINT attachment_t_task_fk
	FOREIGN KEY (question_id)
	REFERENCES question(id);

CREATE TABLE review ( 
	id serial,
	notes TEXT,
	score numeric,
	student_id int NOT NULL,
	teacher_id int NOT NULL,
	task_id int NOT NULL,
	created_by int,
	created_at timestamp,
	updated_by int,
	updated_at timestamp,
	is_active boolean,
	version int
);

ALTER TABLE review ADD CONSTRAINT review_pk
	PRIMARY KEY (id);
ALTER TABLE review ADD CONSTRAINT review_student_fk
	FOREIGN KEY(student_id)
	REFERENCES user_account(id);
ALTER TABLE review ADD CONSTRAINT review_teacher_fk
	FOREIGN KEY(teacher_id)
	REFERENCES user_account(id);
ALTER TABLE review ADD CONSTRAINT review_task_fk
	FOREIGN KEY(task_id)
	REFERENCES learning_task(id);

CREATE TABLE question_answer ( 
	id serial,
	question_id int NOT NULL,
	essay_answer TEXT,
	is_file boolean NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	version int NOT NULL
);

ALTER TABLE question_answer ADD CONSTRAINT q_answer_pk
	PRIMARY KEY (id);
ALTER TABLE question_answer ADD CONSTRAINT q_answer_question_fk
	FOREIGN KEY(question_id) 
	REFERENCES question(id);

CREATE TABLE attachment_answer ( 
	id serial,
	file_id int NOT NULL,
	answer_id int NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	version int NOT NULL
);

ALTER TABLE attachment_answer ADD CONSTRAINT attachment_a_pk
	PRIMARY KEY(id);
ALTER TABLE attachment_answer ADD CONSTRAINT attachment_a_file_fk
	FOREIGN KEY (file_id)
	REFERENCES file(id);
ALTER TABLE attachment_answer ADD CONSTRAINT attachment_a_answer_fk
	FOREIGN KEY (answer_id)
	REFERENCES question_answer(id);

CREATE TABLE learning_forum ( 
	id serial,
	forum_code VARCHAR(10) NOT NULL,
	forum_title VARCHAR(50) NOT NULL,
	forum_description TEXT NOT NULL,
	learning_id int NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	version int NOT NULL
);

ALTER TABLE learning_forum ADD CONSTRAINT l_forum_pk
	PRIMARY KEY(id);
ALTER TABLE learning_forum ADD CONSTRAINT l_forum_bk
	UNIQUE(forum_code);
ALTER TABLE learning_forum ADD CONSTRAINT l_forum_learning_fk
	FOREIGN KEY (learning_id)
	REFERENCES learning(id);
ALTER TABLE learning_forum ADD CONSTRAINT l_forum_learnig_bk
	UNIQUE(learning_id);

CREATE TABLE comments (
	id serial,
	comment_description TEXT NOT NULL,
	forum_id int NOT NULL,
	user_id int NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	version int NOT NULL
);

ALTER TABLE comments ADD CONSTRAINT comments_pk
	PRIMARY KEY(id);
ALTER TABLE comments ADD CONSTRAINT comments_forum_fk
	FOREIGN KEY(forum_id)
	REFERENCES learning_forum(id);
ALTER TABLE comments ADD CONSTRAINT comments_user_fk
	FOREIGN KEY(user_id)
	REFERENCES user_account(id);

CREATE TABLE attendance ( 
	id serial,
	learning_id int NOT NULL,
	attendance_date timestamp NOT NULL,
	student_id int NOT NULL,
	is_approve boolean NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	version int NOT NULL
);

ALTER TABLE attendance ADD CONSTRAINT attendance_pk
	PRIMARY KEY (id);
ALTER TABLE attendance ADD CONSTRAINT attendance_learning_fk
	FOREIGN KEY(learning_id)
	REFERENCES learning(id);
ALTER TABLE attendance ADD CONSTRAINT attendance_student_fk
	FOREIGN KEY(student_id)
	REFERENCES user_account(id);
ALTER TABLE attendance ADD CONSTRAINT attendance_ck
	UNIQUE(learning_id, student_id);