package com.codecool.chilibeans.repository.recipe;

import com.codecool.chilibeans.model.recipe.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
