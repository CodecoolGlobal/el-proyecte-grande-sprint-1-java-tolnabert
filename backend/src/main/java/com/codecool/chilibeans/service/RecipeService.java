package com.codecool.chilibeans.service;

import com.codecool.chilibeans.controller.dto.recipe.NewRecipeDTO;
import com.codecool.chilibeans.controller.dto.recipe.RecipeDTO;

import java.util.List;
import java.util.UUID;

public interface RecipeService {

    List<RecipeDTO> getAllRecipes(String sortBy, String sorOrder);

    RecipeDTO getRecipeById(UUID id);

    RecipeDTO createNewRecipe(NewRecipeDTO newRecipeDTO);

    RecipeDTO updateRecipe(UUID id, RecipeDTO recipeDTO);

    boolean deleteRecipeById(UUID id);

}
