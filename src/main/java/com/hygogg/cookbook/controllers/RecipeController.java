package com.hygogg.cookbook.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.hygogg.cookbook.models.Recipe;
import com.hygogg.cookbook.models.User;
import com.hygogg.cookbook.services.RecipeService;


@Controller
public class RecipeController {
	
	private final RecipeService recipeService;
	
	public RecipeController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}

	@GetMapping("/home")
	public String home(HttpSession session, Model model) {
		User userFromSession = (User) session.getAttribute("user");
		if(userFromSession == null) {
			return "redirect:/";
		}
		model.addAttribute("user", userFromSession);
		model.addAttribute("allRecipes", recipeService.getAll());
		model.addAttribute("newRecipe", new Recipe());
		return "home.jsp";
	}
	
	@PostMapping("/recipe")
	public String create(@Valid @ModelAttribute("newRecipe") Recipe newRecipe, BindingResult result, HttpSession session, Model model) {
		User userFromSession = (User) session.getAttribute("user");
		if(userFromSession == null) {
			return "redirect:/";
		}
		if(result.hasErrors()) {
			model.addAttribute("user", userFromSession);
			model.addAttribute("allRecipes", recipeService.getAll());
			return "home.jsp";
		} else {
			recipeService.create(newRecipe);
			return "redirect:/home";
		}
	}
	
}
