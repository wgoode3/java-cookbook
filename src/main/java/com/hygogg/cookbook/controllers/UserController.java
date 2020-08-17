package com.hygogg.cookbook.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hygogg.cookbook.models.User;
import com.hygogg.cookbook.services.UserService;


@Controller
public class UserController {

	private final UserService userServ;
	
	public UserController(UserService userServ) {
		this.userServ = userServ;
	}
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("user", new User());
		return "index.jsp";
	}
	
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("user") User u, BindingResult result) {
		if( userServ.getOne(u.getEmail()) != null) {
			result.rejectValue("email", "Unique", "Email already in use! Try logging in?");
		}
		if( !u.getPassword().equals( u.getConfirm()) ) {
			result.rejectValue("confirm", "Match", "Confirm Password must match Password!");
		}
		if(result.hasErrors()) {
			return "index.jsp";
		} else {
			userServ.create(u);
			return "redirect:/";
		}		
	}
	
	@PostMapping("/login")
	public String login(@RequestParam(value="email") String email, @RequestParam(value="password") String password) {
		return "redirect:/";
	}	
	
}
