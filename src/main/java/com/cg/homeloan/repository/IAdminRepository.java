package com.cg.homeloan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.homeloan.entities.Admin;

public interface IAdminRepository extends JpaRepository<Admin,Integer > {

}
