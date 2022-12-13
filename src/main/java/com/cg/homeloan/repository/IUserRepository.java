package com.cg.homeloan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.homeloan.entities.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {
	User findByUserName(String userName);
}