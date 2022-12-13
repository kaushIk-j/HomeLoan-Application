package com.cg.homeloan.services;

import com.cg.homeloan.entities.User;

/* User Service 
 * IUserServiceImpl implements IUserService 
 * User addNewUser(User user) to add new user as admin,customer,landOfficer,financeOfficer.
 * User signIn(User user) to sign in
 * User signOut(User user) to sign out
 * 
 * Author : 
 */

public interface IUserService  {
	public  User addNewUser(User user);
	public  User getUser(int id);
	public User signIn(User user);
	public User signOut(User user);
}
