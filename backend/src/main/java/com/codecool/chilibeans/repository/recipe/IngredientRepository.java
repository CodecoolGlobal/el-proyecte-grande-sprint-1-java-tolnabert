package com.codecool.chilibeans.repository.recipe;

import com.codecool.chilibeans.model.recipe.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
}
