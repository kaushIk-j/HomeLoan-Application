package com.cg.homeloan.controllers;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.cg.homeloan.entities.Customer;
import com.cg.homeloan.entities.LoanApplication;
import com.cg.homeloan.entities.Roles;
import com.cg.homeloan.entities.User;
import com.cg.homeloan.exception.CustomerNotFoundException;
import com.cg.homeloan.services.ICustomerService;
import com.cg.homeloan.services.ILoanApplicationService;
import com.cg.homeloan.services.IUserService;

/*Customer Service Controller
 * Customer service controller contains various methods to interact with the loan user table
 * "/home" is to check the home page 
 * "/add" is to add new customer to the database
 * "/view/{cust_id}" to view the customer details by using the customer ID
 * "/update" to update the customer details or make any specific changes to details
 * "/delete" to delete the customer from the database
 * "/viewall" to viewall the customers available in the database
 * "/viewbydate/{date_of_applicaton}"
 * 
 * Author :  
 * */

@Validated
@Controller
@RequestMapping("/customer")
public class CustomerServiceController {
	Logger logger = Logger.getLogger(CustomerServiceController.class.getName());
	@Autowired
	ICustomerService customerService;
	@Autowired 
	IUserService userService;
	@Autowired
	ILoanApplicationService loanApplicationService;

	public CustomerServiceController() {
		logger.log(Level.INFO, "-----> Customer Controller Working!");

	}
	
	
	
	@RequestMapping("/{userId}")
	public String viewCustomer(Model model,@PathVariable("userId") int userId) throws CustomerNotFoundException {
		Customer customer = customerService.getCustomerByUser(userService.getUser(userId));
		List<LoanApplication> loanApplications = loanApplicationService.getAllByCustomer(customer);
		model.addAttribute("userId",userId);
		model.addAttribute("loanApplications", loanApplications);
		return "customer";
	}
	
	@RequestMapping("/edit/{userId}")
	public String editCustomer(Model model,@PathVariable("userId") int userId) throws CustomerNotFoundException {

		User user = userService.getUser(userId);
		Customer cust = customerService.getCustomerByUser(user);
		model.addAttribute("c",cust);
		model.addAttribute("u",user);
		return "updateCustomer";

	}
	
	
	@RequestMapping("/register")
	public String showform(Model model) {

		model.addAttribute("c", new Customer());
		model.addAttribute("u",new User());
		return "register";
	}

	@RequestMapping("/entry") 
	public RedirectView homeRequest(Model model, @Valid @ModelAttribute("c") Customer customer, @Valid @ModelAttribute("u") User user, BindingResult result) {
		/* mode.addAttribute(); */
		try {

			if (result.hasErrors()) {
				return new RedirectView("/customer/register");
			}
			user.setRole(Roles.CUSTOMER);
			User newUser = userService.addNewUser(user);
			customer.setUserId(newUser);
			Customer cust = customerService.addCustomer(customer);
			model.addAttribute("c", cust);
			return new RedirectView("/customer/"+user.getUserId());
		} catch (Exception ex) {
			System.out.println(ex);
			return new RedirectView("/customer/register	");
		}
	}

	@RequestMapping( "/add") 
	public Customer addCustomer( @Valid @ModelAttribute Customer customer) {
	     this.customerService.addCustomer(customer); 
	  return customer; 
	  }

	
	@RequestMapping("/view/{custid}")
	public Customer viewCustomer(@PathVariable("custid") int custid) throws CustomerNotFoundException {
		return this.customerService.viewCustomer(custid);

	}

	

}
