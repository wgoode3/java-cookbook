package com.hygogg.cookbook.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;


@Entity
@Table(name="ingredients")
public class Ingredient {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(min=3, message="Ingredient name must be 3 characters or longer!")
	@Size(max=100, message="Ingredient name must be 100 characters or shorter!")
	private String name;
	
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "recipe_ingredients", 
        joinColumns = @JoinColumn(name = "ingredient_id"), 
        inverseJoinColumns = @JoinColumn(name = "recipe_id")
    )
    private List<Recipe> recipes;
    
    public Ingredient(String name) {
    	this.name = name;
    }
	
    public Ingredient() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Recipe> getRecipes() {
		return recipes;
	}

	public void setRecipes(List<Recipe> recipes) {
		this.recipes = recipes;
	}
	
}
