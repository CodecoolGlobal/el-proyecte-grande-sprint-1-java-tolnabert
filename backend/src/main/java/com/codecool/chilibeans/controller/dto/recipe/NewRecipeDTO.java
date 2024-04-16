package com.codecool.chilibeans.controller.dto.recipe;

import com.codecool.chilibeans.model.recipe.Diet;

import java.util.Set;

public record NewRecipeDTO(String name, String description, Set<Diet> diets) {
}
