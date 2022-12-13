package com.cg.homeloan.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cg.homeloan.entities.User;
import com.cg.homeloan.repository.IUserRepository;

import java.util.Optional;
import java.util.logging.*;

/* User Service 
 * IUserServiceImpl implements IUserService 
 * User addNewUser(User user) to add new user as admin,customer,landOfficer,financeOfficer.
 * User signIn(User user) to sign in
 * User signOut(User user) to sign out
 * 
 * Author : 
 */

@Service
public class IUserServiceImpl implements IUserService {
	Logger logger = Logger.getLogger(IUserServiceImpl.class.getName());
	
	@Autowired
	IUserRepository repository;
	
	

	@Override
	public User addNewUser(User user) {
		
		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
		String encryptedPwd = bcrypt.encode(user.getPassword());
		user.setPassword(encryptedPwd);
		
		try {
			User newUser = repository.save(user);
			return newUser;
		} catch (Exception e) {
			logger.info(e.getMessage());
			return null;
		}
	}

	@Override
	public User signIn(User user) {
//		User newUser = new User();
//		newUser.setUserId(1);
//		newUser.setUserName("admin");
//		newUser.setRole("ADMIN");
//		newUser.setPassword("admin");
//		repository.save(newUser);
		BCryptPasswordEncoder bcrypt=new  BCryptPasswordEncoder();
		
		User userFound = repository.findByUserName(user.getUserName());
		if(userFound != null) {
			
			if(bcrypt.matches(user.getPassword(),userFound.getPassword())){
				return userFound;
			}
		}
			
//			if(userFound.getPassword().equalsIgnoreCase(user.getPassword())) {
//				
//				return userFound;
//			}
		
		return null;
	}

	@Override
	public User signOut(User user) {
		
		return null;
	}

	@Override
	public User getUser(int id) {
		Optional<User> user = repository.findById(id);
		if(user.isPresent()) {
			return user.get();
		}
		return null;
	}

}
