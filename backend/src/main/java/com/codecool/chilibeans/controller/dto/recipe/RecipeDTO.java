package com.codecool.chilibeans.controller.dto.recipe;

import com.codecool.chilibeans.model.recipe.Diet;

import java.util.Set;
import java.util.UUID;

public record RecipeDTO(UUID id, String name, String description, Set<Diet> diets) {
}
