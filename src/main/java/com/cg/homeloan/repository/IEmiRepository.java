package com.cg.homeloan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.homeloan.entities.EMI;

public interface IEmiRepository extends JpaRepository<EMI,Integer >{

}
