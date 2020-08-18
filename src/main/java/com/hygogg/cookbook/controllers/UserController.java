package com.hygogg.cookbook.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.mindrot.jbcrypt.BCrypt;
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
	public String index(Model model, HttpServletRequest request) {
		model.addAttribute("user", new User());
		model.addAttribute("loginError", request.getParameter("loginError"));
		return "index.jsp";
	}
	
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("user") User u, BindingResult result, HttpSession session) {
		if( userServ.getOne(u.getEmail()) != null) {
			result.rejectValue("email", "Unique", "Email already in use! Try logging in?");
		}
		if( !u.getPassword().equals( u.getConfirm()) ) {
			result.rejectValue("confirm", "Match", "Confirm Password must match Password!");
		}
		if(result.hasErrors()) {
			return "index.jsp";
		} else {
			User userFromDb = userServ.create(u);
			session.setAttribute("user", userFromDb);
			return "redirect:/home";
		}		
	}
	
	@PostMapping("/login")
	public String login(@RequestParam(value="email") String email, @RequestParam(value="password") String password, HttpSession session) {
		User userInDb = userServ.getOne(email);
		if(userInDb != null) {
			if(BCrypt.checkpw(password, userInDb.getPassword())) {
				session.setAttribute("user", userInDb);
				return "redirect:/home";
			}
		}
		return "redirect:/?loginError=Invalid login attempt";
	}	
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		return "redirect:/";
	}
	
}
