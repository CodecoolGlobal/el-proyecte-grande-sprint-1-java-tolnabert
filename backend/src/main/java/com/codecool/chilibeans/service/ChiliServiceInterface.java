package com.codecool.chilibeans.service;

import com.codecool.chilibeans.controller.dto.DietDTO.DietDTO;
import com.codecool.chilibeans.controller.dto.DietDTO.NewDietDTO;
import com.codecool.chilibeans.controller.dto.recipe.NewRecipeDTO;
import com.codecool.chilibeans.controller.dto.recipe.RecipeDTO;
import com.codecool.chilibeans.controller.dto.user.NewUserDTO;
import com.codecool.chilibeans.controller.dto.user.UserDTO;
import com.codecool.chilibeans.model.recipe.Diet;

import java.util.Set;
import java.util.UUID;

public interface ChiliServiceInterface {

    // Users

    Set<UserDTO> getAllUsers();

    UserDTO getUserById(UUID id);

    UserDTO createNewUser(NewUserDTO newUserDTO);

    UserDTO updateUser(UUID id, UserDTO userDTO);

    boolean deleteUserById(UUID id);

    // Recipes

    Set<RecipeDTO> getAllRecipes();

    RecipeDTO getRecipeById(UUID id);

    RecipeDTO createNewRecipe(NewRecipeDTO newRecipeDTO);

    RecipeDTO updateRecipe(UUID id, RecipeDTO recipeDTO);

    boolean deleteRecipeById(UUID id);

    // Diet

    Set<DietDTO> getAllDiets();

    DietDTO getDietById(UUID id);

    DietDTO createNewDiet(NewDietDTO newDietDTO);

    DietDTO updateDiet(UUID id, DietDTO dietDTO);

    boolean deleteDietById(UUID id);
}
