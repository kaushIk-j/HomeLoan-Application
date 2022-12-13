package com.cg.homeloan.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.homeloan.entities.FinanceVerificationOfficer;
import com.cg.homeloan.repository.IFinanceVerificationRepository;

@Service
public class IFinanceVerificationServiceImpl implements IFinanceVerificationService {

	@Autowired
	IFinanceVerificationRepository fVRepository;
	
	@Override
	public FinanceVerificationOfficer addFinanceVerificationOfficer(FinanceVerificationOfficer fvo) {
		// TODO Auto-generated method stub
		try {
			return fVRepository.save(fvo); 
		} catch (Exception e) {
			System.err.println(e);
			return null;
		}
	}

}
