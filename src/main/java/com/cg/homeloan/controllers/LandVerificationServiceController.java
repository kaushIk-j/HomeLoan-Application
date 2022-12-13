package com.cg.homeloan.controllers;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.cg.homeloan.entities.Customer;
import com.cg.homeloan.entities.LandVerificationOfficer;
import com.cg.homeloan.entities.LoanApplication;
import com.cg.homeloan.entities.Roles;
import com.cg.homeloan.entities.Status;
import com.cg.homeloan.entities.User;
import com.cg.homeloan.exception.CustomerNotFoundException;
import com.cg.homeloan.services.ILandVerificationService;
import com.cg.homeloan.services.ILoanApplicationService;
import com.cg.homeloan.services.IUserService;

@Controller
@RequestMapping("/land-verification")
public class LandVerificationServiceController {

	@Autowired
	IUserService userService;
	
	@Autowired
	ILandVerificationService landVerificationService;
	
	@Autowired
	ILoanApplicationService loanApplicationService;
	
	
	
	@RequestMapping("/home")
	public String viewAdmin(Model model) {
		List<LoanApplication> loanApplications = loanApplicationService.getAllApplicationByStatus(Status.WAITING_FOR_LAND_VERIFICATION_OFFICE_APPROVAL);
		model.addAttribute("loanApplications", loanApplications);
		model.addAttribute("loanApplication", new LoanApplication());
		return "landOfficer";
	}
	
	
	
	@RequestMapping("/create")
	public String creageRequest(Model model) {
		
		model.addAttribute("u", new User());
		model.addAttribute("lof", new LandVerificationOfficer());
		return "createLandOfficer";
	}
	
	
	@RequestMapping("/addlandofficer")
	public RedirectView homeRequest(Model model, @Valid @ModelAttribute("lof") LandVerificationOfficer lvo, @Valid @ModelAttribute("u") User user, BindingResult result) {
		/* mode.addAttribute(); */
		try {

			if (result.hasErrors()) {
				return new RedirectView("/land-verification/create");
			} 
			user.setRole(Roles.LAND_VERIFICATION_OFFICER);
			User newUser = userService.addNewUser(user);
			lvo.setUserId(newUser);
			LandVerificationOfficer newlandVerificationOfficer = landVerificationService.addLandVerificationOfficer(lvo);
			model.addAttribute("lof", newlandVerificationOfficer);
			return new RedirectView("/admin/");
		} catch (Exception ex) {
			System.out.println(ex);
			return new RedirectView("/land-verification/create");
		}
	}
	
	

	@RequestMapping("/update-status")
	public RedirectView updateStatus( @Valid @ModelAttribute("loan") LoanApplication loan ) throws CustomerNotFoundException {
		LoanApplication loanApplication = loanApplicationService.getApplicationById(loan.getApplicationId());
		loanApplication.setLandVerificationApproval(loan.isLandVerificationApproval());
		if(loan.isLandVerificationApproval()) {
			loanApplication.setStatus(Status.WAITING_FOR_FINANCE_APPROVAL.label);
		}else {
			loanApplication.setStatus(Status.REJECTED.label);
			loanApplication.setLoanApprovedAmount(0);
		}
		loanApplicationService.updateLoanApplication(loanApplication);
		return new RedirectView("/land-verification/home");
	}
	
	
	
}
