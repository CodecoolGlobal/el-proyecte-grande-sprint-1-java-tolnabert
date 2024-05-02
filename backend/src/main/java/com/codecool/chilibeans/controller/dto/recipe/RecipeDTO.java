package com.codecool.chilibeans.controller.dto.recipe;

import com.codecool.chilibeans.model.recipe.Diet;
import com.codecool.chilibeans.model.recipe.Ingredient;
import com.codecool.chilibeans.model.recipe.Step;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public record RecipeDTO(UUID publicId, String name, String description, Set<Diet> diets, Set<Ingredient> ingredients, List<Step> steps, int portions, String image, UUID createdBy, LocalDate createdAt) {
}
