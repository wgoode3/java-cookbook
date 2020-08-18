package com.hygogg.cookbook.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;


@Entity
@Table(name="recipes")
public class Recipe {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(min=3, message="Recipe name must be 3 characters or longer!")
	@Size(max=100, message="Recipe name must be 100 characters or shorter!")
	private String name;
	
	@Min(value=1, message="Recipe must have at least one serving!")
	private Integer servings;
	
	@Size(min=5, message="Instructions must be 5 characters or longer")
	@Size(max=1000, message="Instructions must be shorter than 1000 characters!")
	private String instructions;
	
	@Transient
	private String tempIngredients;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
	private User chef;
	
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "recipe_ingredients", 
        joinColumns = @JoinColumn(name = "recipe_id"), 
        inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    private List<Ingredient> ingredients;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "favorites", 
        joinColumns = @JoinColumn(name = "recipe_id"), 
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> fans;

	@Column(updatable=false)
    private Date createdAt;
	
    private Date updatedAt;

	public Recipe( String name, Integer servings, String instructions) {
		this.name = name;
		this.servings = servings;
		this.instructions = instructions;
	}
	
	public Recipe() {}

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

	public Integer getServings() {
		return servings;
	}

	public void setServings(Integer servings) {
		this.servings = servings;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}
	
	public String getTempIngredients() {
		return tempIngredients;
	}

	public void setTempIngredients(String tempIngredients) {
		this.tempIngredients = tempIngredients;
	}

	public User getChef() {
		return chef;
	}

	public void setChef(User chef) {
		this.chef = chef;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	
	public List<User> getFans() {
		return fans;
	}

	public void setFans(List<User> fans) {
		this.fans = fans;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	@PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    
    @PreUpdate
    protected void onUpdate(){
    	this.updatedAt = new Date();
    }
    
    public String getShortInstrunctions(int num) {
    	return instructions.substring(0, Math.min(instructions.length(), num)) + "...";
    }
    
    public List<Ingredient> getFirstIngredients(int num) {
    	List<Ingredient> firstFew = new ArrayList<Ingredient>();
    	if(num > ingredients.size()) {
    		num = ingredients.size();
    	}
    	for(int i=0; i<num; i++) {
    		firstFew.add(ingredients.get(i));
    	}
    	return firstFew;
    }

}
