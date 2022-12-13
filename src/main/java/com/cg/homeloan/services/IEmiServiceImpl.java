package com.cg.homeloan.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.homeloan.entities.EMI;
import com.cg.homeloan.repository.IEmiRepository;

@Service
public class IEmiServiceImpl implements IEmiService {
	
	@Autowired
	IEmiRepository repository;
	
	@Override
	public EMI addNewEmi(EMI emi) {
		EMI newEmi = repository.save(emi); 
		if(newEmi != null) {
			return newEmi;
		}
		return null;
	}

}
