package com.cg.homeloan.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.cg.homeloan.entities.LandVerificationOfficer;
import com.cg.homeloan.entities.Roles;
import com.cg.homeloan.entities.User;
import com.cg.homeloan.services.IUserService;

@Controller
@RequestMapping("/user")
public class UserServiceController {

	@Autowired
	IUserService userService;
	
	@RequestMapping("/auth")
	public String authUser(Model model) {
		model.addAttribute("user", new User());
		 return "login";
		
	}
	  
	
	@RequestMapping("/login")
	public RedirectView loginUser(  @Valid @ModelAttribute("user") User user) {
		
		 User userFound = userService.signIn(user);
		 if(userFound != null) {
			 String role = userFound.getRole();
			 if(role.equalsIgnoreCase("ADMIN")) {
				 return new RedirectView("/admin/");		 
			 }else if(role.equalsIgnoreCase("CUSTOMER")) {
				 return new RedirectView("/customer/"+userFound.getUserId());		
			 }else if(role.equalsIgnoreCase("LAND_VERIFICATION_OFFICER")) {
				 return new RedirectView("/land-verification/home");		
			 }else if(role.equalsIgnoreCase("FINANCE_VERIFICATION_OFFICER")) {
				 return new RedirectView("/finance-verification/home");		
			 }
				 
		 }
		 return new RedirectView("/user/auth");
		
	}

	
	
}
