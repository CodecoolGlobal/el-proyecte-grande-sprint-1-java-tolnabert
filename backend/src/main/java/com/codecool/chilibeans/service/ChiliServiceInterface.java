package com.codecool.chilibeans.service;

import com.codecool.chilibeans.controller.dto.recipe.NewRecipeDTO;
import com.codecool.chilibeans.controller.dto.recipe.RecipeDTO;
import com.codecool.chilibeans.controller.dto.user.NewUserDTO;
import com.codecool.chilibeans.controller.dto.user.UserDTO;
import com.codecool.chilibeans.model.User;

import java.util.Set;
import java.util.UUID;

public interface ChiliServiceInterface {

    Set<UserDTO> getAllUsers();

    UserDTO getUserById(UUID id);

    UserDTO createNewUser(NewUserDTO newUserDTO);

    UserDTO updateUser(UUID id, UserDTO userDTO);

    boolean DeleteUserById(UUID id);


    Set<RecipeDTO> getAllRecipes();

    RecipeDTO getRecipeById(UUID id);

    RecipeDTO createNewRecipe(NewRecipeDTO newRecipeDTO);

    UserDTO updateRecipe(UUID id, RecipeDTO recipeDTO);

    boolean DeleteRecipeById(UUID id);
}
