package com.lawencon.lms.service.impl;

import static com.lawencon.lms.util.GeneratorID.generateID;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lawencon.lms.dto.userrole.UserRoleResDto;
import com.lawencon.lms.constant.Role;
import com.lawencon.lms.dao.FileDao;
import com.lawencon.lms.dao.UserDao;
import com.lawencon.lms.dao.UserProfileDao;
import com.lawencon.lms.dao.UserRoleDao;
import com.lawencon.lms.dto.InsertResDto;
import com.lawencon.lms.dto.login.LoginReqDto;
import com.lawencon.lms.dto.login.LoginResDto;
import com.lawencon.lms.dto.registration.StudentInsertReqDto;
import com.lawencon.lms.dto.user.UserInsertReqDto;
import com.lawencon.lms.dto.user.UserListResDto;
import com.lawencon.lms.dto.user.UserResDto;
import com.lawencon.lms.model.File;
import com.lawencon.lms.model.User;
import com.lawencon.lms.model.UserProfile;
import com.lawencon.lms.model.UserRole;
import com.lawencon.lms.service.MailService;
import com.lawencon.lms.service.PrincipalService;
import com.lawencon.lms.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private final UserDao userDao;
	private final UserProfileDao userProfileDao;
	private final UserRoleDao userRoleDao;
	private final FileDao fileDao;
	private final MailService mailService;
	private final PasswordEncoder passwordEncoder;
	private final PrincipalService principalService;
	
	@PersistenceContext
	private EntityManager em;
	
	public UserServiceImpl(UserDao userDao, UserProfileDao userProfileDao, 
			UserRoleDao userRoleDao, FileDao fileDao, MailService mailService, 
			PasswordEncoder passwordEncoder, PrincipalService principalService) {
		this.userDao = userDao;
		this.userProfileDao = userProfileDao;
		this.userRoleDao = userRoleDao;
		this.fileDao = fileDao;
		this.mailService = mailService;
		this.passwordEncoder = passwordEncoder;
		this.principalService = principalService;
	}
	
	@Override
	public List<UserResDto> getAll() {
		final List<UserResDto> users = new ArrayList<>();

		userDao.getAll().forEach(u -> {
			final UserResDto user = new UserResDto();
			user.setUserFullName(u.getProfile().getUserFullname());
			user.setRoleName(u.getRole().getRoleName());
			user.setRoleCode(u.getRole().getRoleCode());
			users.add(user);
		});

		return users;
	}
	
	@Override
	public UserListResDto getByID(long id) {
		final UserListResDto profile = new UserListResDto();
		final User u = userDao.getUserById(id);
		
		profile.setId(u.getId());
		profile.setFileId(u.getProfile().getFile().getId());
		profile.setFile(u.getProfile().getFile().getFile());
		profile.setFileExtension(u.getProfile().getFile().getFileExtension());
		profile.setUserIdNumber(u.getProfile().getUserIdNumber());
		profile.setUserAddress(u.getProfile().getUserAddress());
		profile.setUserFullname(u.getProfile().getUserFullname());
		profile.setUserGender(u.getProfile().getUserGender());
		profile.setUserEmail(u.getUserEmail());
		profile.setRoleCode(u.getRole().getRoleCode());
		profile.setRoleName(u.getRole().getRoleName());
		profile.setIsActive(u.getIsActive());
		
		return profile;
	}
	
	@Transactional
	@Override
	public InsertResDto insert(UserInsertReqDto userInsertReqDto) {

		InsertResDto result = new InsertResDto();
		
		final File file = new File();
		file.setFile(userInsertReqDto.getFile());
		file.setFileExtension(userInsertReqDto.getFileExtension());
		file.setCreatedBy(principalService.getPrincipal());
		final File photo = fileDao.insert(file);
		
		final UserProfile userProfile = new UserProfile();
		final String randomCode = generateID(5);
		userProfile.setUserIdNumber(randomCode);
		userProfile.setUserFullname(userInsertReqDto.getUserFullName());
		userProfile.setUserGender(userInsertReqDto.getUserGender());
		userProfile.setUserAddress(userInsertReqDto.getUserAddress());
		userProfile.setCreatedBy(principalService.getPrincipal());
		userProfile.setFile(photo);
		final UserProfile profile = userProfileDao.insert(userProfile);
		
		final User user = new User();
		final String randomPassword = generateID(5);
		user.setUserEmail(userInsertReqDto.getUserEmail());
		final String passwordEncoded = passwordEncoder.encode(randomPassword);
		user.setUserPassword(passwordEncoded);
		
		final UserRole role = userRoleDao.getRoleById(userInsertReqDto.getRoleId());
		user.setRole(role);
		user.setProfile(profile);
		user.setCreatedBy(principalService.getPrincipal());
		userDao.insert(user);

		final String subject = "New Account Registered on LMS";
		final String body = "Here is your Credentials\n"
				+ "Email: " + userInsertReqDto.getUserEmail()
				+ "\nPassword: " + randomPassword;
		mailService.sendMail(userInsertReqDto.getUserEmail(), subject , body);
		result.setId(user.getId());
		result.setMessage("Insert User Success!");
		return result;
	}
	
	@Transactional
	@Override
	public InsertResDto studentRegistration(StudentInsertReqDto student) {
		InsertResDto result = new InsertResDto();
		
		final File file = new File();
		file.setFile(student.getFile());
		file.setFileExtension(student.getFileExtension());
		file.setCreatedBy((long) 4);
		final File photo = fileDao.insert(file);
		
		final UserProfile userProfile = new UserProfile();
		final String randomCode = generateID(5);
		userProfile.setUserIdNumber(randomCode);
		userProfile.setUserFullname(student.getUserFullName());
		userProfile.setUserGender(student.getUserGender());
		userProfile.setUserAddress(student.getUserAddress());
		userProfile.setCreatedBy((long)Role.valueOf("SYSTEMUSER").ordinal());
		userProfile.setFile(photo);
		final UserProfile profile = userProfileDao.insert(userProfile);
		
		final User user = new User();
		final String randomPassword = generateID(5);
		user.setUserEmail(student.getUserEmail());
		final String passwordEncoded = passwordEncoder.encode(randomPassword);
		user.setUserPassword(passwordEncoded);
		
		final UserRole role = userRoleDao.getRoleById((long) 2);
		user.setRole(role);
		user.setProfile(profile);
		user.setCreatedBy((long)Role.valueOf("SYSTEMUSER").ordinal());
		userDao.insert(user);

		final String subject = "New Account Registered on LMS";
		final String body = "Here is your Credentials\n"
				+ "Email: " + student.getUserEmail()
				+ "\nPassword: " + randomPassword;
		mailService.sendMail(student.getUserEmail(), subject , body);
		result.setId(user.getId());
		result.setMessage("Registration Succes! Please Check your email!");
		return result;
	}

	@Override
	public LoginResDto login(LoginReqDto credentials) {
		final User userlog = userDao.getByUsername(credentials.getUserEmail());
		
		final LoginResDto result = new LoginResDto();
		result.setId(userlog.getId());
		result.setUserFullname(userlog.getProfile().getUserFullname());
		result.setRoleCode(userlog.getRole().getRoleCode());
		result.setRoleName(userlog.getRole().getRoleName());
		result.setPhotoId(userlog.getProfile().getFile().getId());
		
		return result;
	}


	@Override
	public List<UserListResDto> getByRoleCode(String roleCode) {
		final List<UserListResDto> users = new ArrayList<>();
		
		userDao.getByRoleCode(roleCode).forEach(u -> {
			final UserListResDto user = new UserListResDto();
			user.setId(u.getId());
			user.setUserFullname(u.getProfile().getUserFullname());
			user.setRoleCode(u.getRole().getRoleCode());
			user.setRoleName(u.getRole().getRoleName());
			users.add(user);
		});
		
		return users;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		final User user = userDao.getByUsername(username);
		
		if(user != null) {
			return new org.springframework.security.core.userdetails.User(username, user.getUserPassword(), new ArrayList<>());
		}
		
		throw new UsernameNotFoundException(username);
	}

	@Override
	public List<UserListResDto> getAllUser() {
		final List<UserListResDto> users = new ArrayList<>();
		
		userDao.getAll().forEach(u -> {
			final UserListResDto user = new UserListResDto();
			user.setId(u.getId());
			user.setFileId(u.getProfile().getFile().getId());
			user.setFile(u.getProfile().getFile().getFile());
			user.setFileExtension(u.getProfile().getFile().getFileExtension());
			user.setUserIdNumber(u.getProfile().getUserIdNumber());
			user.setUserFullname(u.getProfile().getUserFullname());
			user.setUserGender(u.getProfile().getUserGender());
			user.setUserEmail(u.getUserEmail());
			user.setRoleCode(u.getRole().getRoleCode());
			user.setRoleName(u.getRole().getRoleName());
			user.setIsActive(u.getIsActive());
			users.add(user);
		});
		
		return users;
	}
	
	@Override
	public List<UserRoleResDto> getAllRoles() {
		final List<UserRoleResDto> roles = new ArrayList<>();

		userRoleDao.getAll().forEach(r -> {
			final UserRoleResDto role = new UserRoleResDto();
			role.setId(r.getId());
			role.setRoleCode(r.getRoleCode());
			role.setRoleName(r.getRoleName());
			roles.add(role);
		});

		return roles;
	}

}
