package com.codecool.chilibeans.service;

import com.codecool.chilibeans.controller.dto.recipe.NewRecipeDTO;
import com.codecool.chilibeans.controller.dto.recipe.RecipeDTO;
import com.codecool.chilibeans.controller.dto.unit.NewUnitDTO;
import com.codecool.chilibeans.controller.dto.unit.UnitDTO;
import com.codecool.chilibeans.controller.dto.user.NewUserDTO;
import com.codecool.chilibeans.controller.dto.user.UserDTO;

import java.util.Set;
import java.util.UUID;

public interface ChiliServiceInterface {

    Set<UserDTO> getAllUsers();

    UserDTO getUserById(UUID id);

    UserDTO createNewUser(NewUserDTO newUserDTO);

    UserDTO updateUser(UUID id, UserDTO userDTO);

    boolean deleteUserById(UUID id);


    Set<RecipeDTO> getAllRecipes();

    RecipeDTO getRecipeById(UUID id);

    RecipeDTO createNewRecipe(NewRecipeDTO newRecipeDTO);

    RecipeDTO updateRecipe(UUID id, RecipeDTO recipeDTO);

    boolean deleteRecipeById(UUID id);

    Set<UnitDTO> getAllUnits();

    UnitDTO createNewUnit(NewUnitDTO newUnitDTO);

    UnitDTO getUnitById(UUID uuid);

    UnitDTO deleteUnitById(UUID uuid);

    UnitDTO updateUnitById(UUID uuid, UnitDTO unitDTO);
}
