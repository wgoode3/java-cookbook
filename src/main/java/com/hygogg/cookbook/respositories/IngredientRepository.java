package com.hygogg.cookbook.respositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hygogg.cookbook.models.Ingredient;


@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
	
	Optional<Ingredient> findByName(String name);
	
}
