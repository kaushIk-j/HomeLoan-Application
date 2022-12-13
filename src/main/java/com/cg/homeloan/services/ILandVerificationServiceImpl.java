package com.cg.homeloan.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.homeloan.entities.LandVerificationOfficer;
import com.cg.homeloan.repository.ILandVerificationRepository;

@Service
public class ILandVerificationServiceImpl implements ILandVerificationService {
	
	@Autowired
	ILandVerificationRepository lVRepository;

	@Override
	public LandVerificationOfficer addLandVerificationOfficer(LandVerificationOfficer lvo) {
		// TODO Auto-generated method stub
		try {
			LandVerificationOfficer newLVO=	lVRepository.save(lvo);
			return newLVO;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
}
