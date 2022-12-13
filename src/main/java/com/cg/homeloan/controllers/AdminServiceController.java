package com.cg.homeloan.controllers;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.cg.homeloan.entities.Customer;
import com.cg.homeloan.entities.EMI;
import com.cg.homeloan.entities.LoanAgreement;
import com.cg.homeloan.entities.LoanApplication;
import com.cg.homeloan.entities.Status;
import com.cg.homeloan.exception.CustomerNotFoundException;
import com.cg.homeloan.services.ICustomerService;
import com.cg.homeloan.services.IEmiService;
import com.cg.homeloan.services.ILoanApplicationService;
import com.cg.homeloan.services.IloanAgreementService;
import com.cg.homeloan.util.EMICalculator;

@Controller
@RequestMapping("/admin")
public class AdminServiceController {

	@Autowired
	ILoanApplicationService loanApplicationService;
	@Autowired
	ICustomerService customerService;
	@Autowired
	IloanAgreementService loanAgService;
	@Autowired
	IEmiService emiService;

	@RequestMapping("/")
	public String viewAdmin(Model model) {
		List<LoanApplication> loanApplications = loanApplicationService.getAllApplications();
		List<Customer> customers = customerService.viewAllCustomers();
		model.addAttribute("loanApplications", loanApplications);
		model.addAttribute("customers", customers);
		return "admin";
	}

	@RequestMapping("/update-application-status")
	public RedirectView updateStatus(@Valid @ModelAttribute("loan") LoanApplication loan)
			throws CustomerNotFoundException, ParseException {
		LoanApplication loanApplication = loanApplicationService.getApplicationById(loan.getApplicationId());
		if (!((!loanApplication.isAdminApproval()
				&& loanApplication.getStatus().equalsIgnoreCase(Status.REJECTED.label))
				|| (loanApplication.isAdminApproval()
						&& loanApplication.getStatus().equalsIgnoreCase(Status.APPROVED.label)))) {
			loanApplication.setAdminApproval(loan.isAdminApproval());
			if (loan.isAdminApproval()) {
				loanApplication.setStatus(Status.APPROVED.label);
			} else {
				loanApplication.setStatus(Status.REJECTED.label);
			}
			loanApplication.setLoanApprovedAmount(loanApplication.getLoanAppliedAmount());
			LoanApplication updatedApplication = loanApplicationService.updateLoanApplication(loanApplication);

//			EMI and Agreement

			if (loan.isAdminApproval()) {
				// Entry in agreement
				LoanAgreement loanAggrement = new LoanAgreement();
				loanAggrement.setLoanApplication(loanApplication);
				LoanAgreement newLoanAgrement = loanAgService.addNewLoanAgreement(loanAggrement);
				EMI emi = new EMI();
				emi.setLoanAgreement(newLoanAgrement);
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.MONTH, 1);
//				DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
//				System.out.println(cal.toString());
				Date date =  new Date(cal.getTimeInMillis());
				System.out.println(date);
				emi.setDueDate(date);
				emi.setInterestAmount(10);
				EMICalculator emiCalc = new EMICalculator(loanApplication.getLoanApprovedAmount(), 10, 120);
				Double emiAmount = emiCalc.getEMIAmount();
				emi.setEmiAmount(emiAmount);
				emi.setLoanAmount(loanApplication.getLoanApprovedAmount());
				emi.setLoanAgreement(newLoanAgrement);
				emiService.addNewEmi(emi);

				System.out.println(emi.toString());
			}

		}
		return new RedirectView("/admin/");
	}

}
