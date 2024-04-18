package com.codecool.chilibeans.service;

import com.codecool.chilibeans.controller.dto.recipe.NewRecipeDTO;
import com.codecool.chilibeans.controller.dto.recipe.RecipeDTO;

import java.util.Set;
import java.util.UUID;

public interface RecipeService {

    Set<RecipeDTO> getAllRecipes();

    RecipeDTO getRecipeById(UUID id);

    RecipeDTO createNewRecipe(NewRecipeDTO newRecipeDTO);

    RecipeDTO updateRecipe(UUID id, RecipeDTO recipeDTO);

    boolean deleteRecipeById(UUID id);

}
