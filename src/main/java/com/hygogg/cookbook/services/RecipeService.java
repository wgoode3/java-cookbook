package com.hygogg.cookbook.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hygogg.cookbook.models.Ingredient;
import com.hygogg.cookbook.models.Recipe;
import com.hygogg.cookbook.respositories.IngredientRepository;
import com.hygogg.cookbook.respositories.RecipeRepository;


@Service
public class RecipeService {

	private final RecipeRepository recipeRepo;
	private final IngredientRepository ingredientRepo;
	
	public RecipeService(RecipeRepository recipeRepo, IngredientRepository ingredientRepo) {
		this.recipeRepo = recipeRepo;
		this.ingredientRepo = ingredientRepo;
	}
	
	// creates an ingredient if it doesn't exist
	// otherwise return the matching ingredient
	public Ingredient findOrCreate(String name) {
		Optional<Ingredient> ing = ingredientRepo.findByName(name);
		return ing.isPresent() ? ing.get() : ingredientRepo.save(new Ingredient(name));
	}
	
	// extract the comma separated ingredient names from the recipe
	// create the ingredients
	// create the recipe with ingredient relationship attached
	public Recipe create(Recipe r) {
		String[] tempIngredients = r.getTempIngredients().split(",");
		List<Ingredient> ingredients = new ArrayList<Ingredient>();	
		for(String ingName : tempIngredients) {
			ingredients.add(findOrCreate(ingName));
		}
		r.setIngredients(ingredients);
		return recipeRepo.save(r);
	}
	
	public List<Recipe> getAll() {
		return (List<Recipe>) recipeRepo.findAll();
	}
	
}
