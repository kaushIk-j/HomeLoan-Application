package com.cg.homeloan.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.cg.homeloan.entities.Customer;
import com.cg.homeloan.entities.FinanceVerificationOfficer;
import com.cg.homeloan.entities.LandVerificationOfficer;
import com.cg.homeloan.entities.LoanApplication;
import com.cg.homeloan.entities.Roles;
import com.cg.homeloan.entities.Status;
import com.cg.homeloan.entities.User;
import com.cg.homeloan.exception.CustomerNotFoundException;
import com.cg.homeloan.services.IFinanceVerificationService;
import com.cg.homeloan.services.ILandVerificationService;
import com.cg.homeloan.services.ILoanApplicationService;
import com.cg.homeloan.services.IUserService;


@Validated
@Controller
@RequestMapping("/finance-verification")
public class FinanceVerificationServiceController {
	
	@Autowired
	IUserService userService;
	
	@Autowired
	IFinanceVerificationService financeVerificationService;
	
	@Autowired
	ILoanApplicationService loanApplicationService;
	
	@RequestMapping("/home")
	public String viewHome(Model model) {
		List<LoanApplication> loanApplications = loanApplicationService.getAllApplicationByStatus(Status.WAITING_FOR_FINANCE_APPROVAL);
		model.addAttribute("loanApplications", loanApplications);
		model.addAttribute("loanApplication", new LoanApplication());
		return "financeOfficer";
	}
	
	
	@RequestMapping("/create")
	public String creageRequest(Model model) {
		
		model.addAttribute("u", new User());
		model.addAttribute("fof", new FinanceVerificationOfficer());
		return "createfinanceOfficer";
	}
	
	
	@RequestMapping("/addfinanceofficer")
	public RedirectView homeRequest(Model model, @Valid @ModelAttribute("fof") FinanceVerificationOfficer fvo, @Valid @ModelAttribute("u") User user, BindingResult result) {
		/* mode.addAttribute(); */
		try {

			if (result.hasErrors()) {
				return new RedirectView("/finance-verification/create");
			} 
			user.setRole(Roles.FINANCE_VERIFICATION_OFFICER);
			User newUser = userService.addNewUser(user);
			fvo.setUserId(newUser);
			FinanceVerificationOfficer newfinanceVerificationOfficer = financeVerificationService.addFinanceVerificationOfficer(fvo);
			model.addAttribute("lof", newfinanceVerificationOfficer);
			return new RedirectView("/admin/");
		} catch (Exception ex) {
			System.out.println(ex);
			return new RedirectView("/finance-verification/create");
		}
	}


	@RequestMapping("/update-status")
	public RedirectView updateStatus( @Valid @ModelAttribute("loan") LoanApplication loan ) throws CustomerNotFoundException {
		LoanApplication loanApplication = loanApplicationService.getApplicationById(loan.getApplicationId());
		loanApplication.setFinanceVerificationApproval(loan.isFinanceVerificationApproval());
		if(loan.isFinanceVerificationApproval()) {
			loanApplication.setStatus(Status.PENDING.label);			
		}else {
			loanApplication.setStatus(Status.REJECTED.label);	
		}
		loanApplicationService.updateLoanApplication(loanApplication);
		return new RedirectView("/finance-verification/home");
	}
	

}
