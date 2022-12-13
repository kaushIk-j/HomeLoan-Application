package com.cg.homeloan.controllers;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.cg.homeloan.entities.Customer;
import com.cg.homeloan.entities.LoanApplication;
import com.cg.homeloan.entities.Status;
import com.cg.homeloan.entities.User;
import com.cg.homeloan.exception.CustomerNotFoundException;
import com.cg.homeloan.services.ICustomerService;
import com.cg.homeloan.services.ILoanApplicationService;
import com.cg.homeloan.services.IUserService;
import com.cg.homeloan.util.EMICalculator;
import com.cg.homeloan.util.HomeLoanBorrowingAmountCalculator;

@Controller
@RequestMapping("/loan-application")
public class LoanApplicationServiceController {
	
	@Autowired
	IUserService userService;
	
	@Autowired
	ICustomerService customerService;
	
	@Autowired
	ILoanApplicationService loanApplicationService;
	


	@RequestMapping("/loan-eligiblity-view")
	public String viewLoanEligiblity(Model model, @RequestParam int userId) {
		model.addAttribute("loanEl", new HomeLoanBorrowingAmountCalculator());
		model.addAttribute("user", userService.getUser(userId));
		model.addAttribute("check",5);
		return "loanEligibilityView";
	}
	
	@RequestMapping("/loan-eligiblity")
	public String checkLoanEligiblity(Model model, @RequestParam int userId, @Valid @ModelAttribute("loanEl") HomeLoanBorrowingAmountCalculator homeLoanBorrowingAmountCalculator ) {
		//model.addAttribute("check",null);
		model.addAttribute("loanEl",null);
		LoanApplication loan = new LoanApplication();
		int check = homeLoanBorrowingAmountCalculator.getHomeLoanBorrowingAmount();
		model.addAttribute("isEligible", check == 1 ? "Y":"F");
		loan.setLoanAppliedAmount(homeLoanBorrowingAmountCalculator.getLoanAppliedAmount());
		loan.setInterestRate(homeLoanBorrowingAmountCalculator.getRateOfInterest());
		loan.setTenure(homeLoanBorrowingAmountCalculator.getTenure());
		model.addAttribute("loan", loan);
		model.addAttribute("user", userService.getUser(userId));
		
		//System.out.println(loan.toString());
		EMICalculator emical = new EMICalculator(homeLoanBorrowingAmountCalculator.getLoanAppliedAmount(),homeLoanBorrowingAmountCalculator.getRateOfInterest(),homeLoanBorrowingAmountCalculator.getTenure());
		model.addAttribute("emical", Math.round(emical.getEMIAmount()));
		System.out.println( Math.round(emical.getEMIAmount()));
		System.out.println(emical.toString());
		//System.out.println(homeLoanBorrowingAmountCalculator.getHomeLoanBorrowingAmount());
		return "loanEligibilityView";
	}
	
	
	
	@RequestMapping("/loan-apply")
	public RedirectView loanApply( @RequestParam int userId, @Valid @ModelAttribute("loan") LoanApplication loan ) throws CustomerNotFoundException {

		User user = userService.getUser(userId);
		Customer customer = customerService.getCustomerByUser(user);
		loan.setApplicationId((long)Math.random() * 1000);
		loan.setCustomer(customer);
		loan.setApplicationDate(new Date());
		loan.setStatus(Status.WAITING_FOR_LAND_VERIFICATION_OFFICE_APPROVAL.name());
		loanApplicationService.addNewLoanApplication(loan);
		return new RedirectView("/customer/"+userId);
	}
	
	
	
}
