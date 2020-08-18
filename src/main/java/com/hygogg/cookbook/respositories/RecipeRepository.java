package com.hygogg.cookbook.respositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hygogg.cookbook.models.Recipe;


@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long> {}
