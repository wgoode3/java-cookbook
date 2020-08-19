package com.hygogg.cookbook.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.hygogg.cookbook.models.Recipe;
import com.hygogg.cookbook.models.User;
import com.hygogg.cookbook.services.RecipeService;
import com.hygogg.cookbook.services.UserService;


@Controller
public class RecipeController {
	
	private final RecipeService recipeServ;
	private final UserService userServ;
	
	public RecipeController(RecipeService recipeServ, UserService userServ) {
		this.recipeServ = recipeServ;
		this.userServ = userServ;
	}

	@GetMapping("/home")
	public String home(HttpSession session, Model model) {
		User userFromSession = (User) session.getAttribute("user");
		if(userFromSession == null) {
			return "redirect:/";
		}
		List<Recipe> all = recipeServ.getAll();
		User u = userServ.getOne(userFromSession.getId());
		// for(Recipe r : u.getFavorites()) {
		// 	  all.remove(r);
		// }
		model.addAttribute("user", u);
		model.addAttribute("allRecipes", all);
		model.addAttribute("newRecipe", new Recipe());
		model.addAttribute("heart", "♥");
		model.addAttribute("hollow", "♡");
		return "home.jsp";
	}
	
	@GetMapping("/new_recipe")
	public String newRecipe(HttpSession session, Model model) {
		User userFromSession = (User) session.getAttribute("user");
		if(userFromSession == null) {
			return "redirect:/";
		}
		model.addAttribute("newRecipe", new Recipe());
		model.addAttribute("user", userFromSession);
		return "new.jsp";
	}
	
	@PostMapping("/recipe")
	public String create(@Valid @ModelAttribute("newRecipe") Recipe newRecipe, BindingResult result, HttpSession session, Model model) {
		User userFromSession = (User) session.getAttribute("user");
		if(userFromSession == null) {
			return "redirect:/";
		}
		if(result.hasErrors()) {
			model.addAttribute("user", userFromSession);
			model.addAttribute("allRecipes", recipeServ.getAll());
			return "new.jsp";
		} else {
			recipeServ.create(newRecipe);
			return "redirect:/home";
		}
	}
	
	@GetMapping("/recipe/{id}")
	public String showRecipe(@PathVariable("id") Long id, HttpSession session, Model model) {
		User userFromSession = (User) session.getAttribute("user");
		if(userFromSession == null) {
			return "redirect:/";
		}
		model.addAttribute("user", userFromSession);
		model.addAttribute("recipe", recipeServ.getOne(id));
		return "recipe.jsp";
	}
	
	@GetMapping("/fav/{id}")
	public String favRecipe(@PathVariable("id") Long id, HttpSession session, Model model) {
		User userFromSession = (User) session.getAttribute("user");
		if(userFromSession == null) {
			return "redirect:/";
		}
		Recipe toFav = recipeServ.getOne(id);
		List<User> fans = toFav.getFans();
		fans.add(userFromSession);
		toFav.setFans(fans);
		recipeServ.update(toFav);
		return "redirect:/home";
	}
	
	@GetMapping("/unfav/{id}")
	public String unfavRecipe(@PathVariable("id") Long id, HttpSession session, Model model) {
		User userFromSession = (User) session.getAttribute("user");
		if(userFromSession == null) {
			return "redirect:/";
		}
		Recipe toUnfav = recipeServ.getOne(id);
		List<User> fans = toUnfav.getFans();
		System.out.println(fans);
		for(int i=0; i<fans.size(); i++) {
			if(fans.get(i).getId() == userFromSession.getId()) {
				fans.remove(fans.get(i));
			}
		}
		System.out.println(fans);
		toUnfav.setFans(fans);
		recipeServ.update(toUnfav);
		return "redirect:/home";
	}
	
}
