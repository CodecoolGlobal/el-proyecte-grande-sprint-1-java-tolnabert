package com.codecool.chilibeans.service;

import com.codecool.chilibeans.controller.dto.recipe.NewRecipeDTO;
import com.codecool.chilibeans.controller.dto.recipe.RecipeDTO;
import com.codecool.chilibeans.controller.dto.unit.NewUnitDTO;
import com.codecool.chilibeans.controller.dto.unit.UnitDTO;
import com.codecool.chilibeans.controller.dto.user.NewUserDTO;
import com.codecool.chilibeans.controller.dto.user.UserDTO;
import com.codecool.chilibeans.model.User;
import com.codecool.chilibeans.model.recipe.Recipe;
import com.codecool.chilibeans.model.recipe.Unit;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ChiliService implements ChiliServiceInterface {

    private final Set<User> users = new HashSet<>();
    private final Set<Recipe> recipes = new HashSet<>();
    private final Set<Unit> units = new HashSet<>();

    @Override
    public Set<UserDTO> getAllUsers() {
        Set<UserDTO> userDTOs = new HashSet<>();
        for (User user : users) {
            userDTOs.add(new UserDTO(user.id(), user.username(), user.firstName(), user.lastName(), user.dateOfBirth(),
                    user.email(), user.ownRecipes(), user.favoredRecipes(), user.creationDate()));
        }
        return userDTOs;
    }

    @Override
    public UserDTO getUserById(UUID id) {
        User user = users.stream().filter(user1 -> user1.id().equals(id))
                .findFirst()
                .orElse(null);
        return new UserDTO(user.id(), user.username(), user.firstName(), user.lastName(), user.dateOfBirth(),
                user.email(), user.ownRecipes(), user.favoredRecipes(), user.creationDate());
    }

    @Override
    public UserDTO createNewUser(NewUserDTO newUserDTO) {
        User newUser = new User(0, UUID.randomUUID(), newUserDTO.username(), newUserDTO.password(), newUserDTO.firstName(),
                newUserDTO.lastName(), newUserDTO.dateOfBirth(), newUserDTO.email(), newUserDTO.ownRecipes(), newUserDTO.favoredRecipes(),
                null);
        users.add(newUser);
        return new UserDTO(newUser.id(), newUser.username(), newUser.firstName(), newUser.lastName(),
                newUser.dateOfBirth(), newUser.email(), newUser.ownRecipes(), newUser.favoredRecipes(), null);
    }

    @Override
    public UserDTO updateUser(UUID id, UserDTO userDTO) {
        User userToUpdate = users.stream().filter(user1 -> user1.id().equals(id))
                .findFirst()
                .orElse(null);
        User updatedUser = new User(userToUpdate.databaseId(), userToUpdate.id(), userDTO.username(), userToUpdate.password(),
                userDTO.firstName(), userDTO.lastName(), userDTO.dateOfBirth(),
                userDTO.email(), userDTO.ownRecipes(), userDTO.favoredRecipes(), userToUpdate.creationDate());
        users.remove(userToUpdate);
        users.add(updatedUser);
        return userDTO;
    }


    @Override
    public boolean deleteUserById(UUID id) {
        return users.removeIf(user -> user.id().equals(id));
    }

    @Override
    public Set<RecipeDTO> getAllRecipes() {
        Set<RecipeDTO> recipeDTOs = new HashSet<>();
        for (Recipe recipe : recipes) {
            recipeDTOs.add(new RecipeDTO(recipe.id(), recipe.name(),
                    recipe.description(), recipe.diets(), recipe.ingredients(), recipe.steps(), recipe.portions(), recipe.image(), recipe.createdBy(), recipe.createdAt()));
        }
        return recipeDTOs;
    }

    @Override
    public RecipeDTO getRecipeById(UUID id) {
        Recipe recipe = recipes.stream().filter(r -> r.id().equals(id)).findFirst().orElse(null);
        return new RecipeDTO(recipe.id(), recipe.name(),
                recipe.description(), recipe.diets(), recipe.ingredients(), recipe.steps(), recipe.portions(), recipe.image(), recipe.createdBy(), recipe.createdAt());
    }

    @Override
    public RecipeDTO createNewRecipe(NewRecipeDTO newRecipeDTO) {
        Recipe newRecipe = new Recipe(0, UUID.randomUUID(), newRecipeDTO.name(), newRecipeDTO.description(), newRecipeDTO.diets(), newRecipeDTO.ingredients(), newRecipeDTO.steps(), newRecipeDTO.portions(), newRecipeDTO.image(), newRecipeDTO.createdBy(), LocalDate.now());
        recipes.add(newRecipe);
        return new RecipeDTO(newRecipe.id(), newRecipe.name(), newRecipe.description(), newRecipe.diets(), newRecipe.ingredients(), newRecipe.steps(), newRecipe.portions(), newRecipe.image(), newRecipe.createdBy(), newRecipe.createdAt());
    }

    @Override
    public RecipeDTO updateRecipe(UUID id, RecipeDTO recipeDTO) {
        Recipe recipeToUpdate = recipes.stream()
                .filter(recipe -> recipe.id().equals(id))
                .findFirst()
                .orElse(null);
        Recipe updatedRecipe = new Recipe(recipeToUpdate.dataBaseId(), recipeDTO.id(), recipeDTO.name(), recipeDTO.description(), recipeDTO.diets(), recipeDTO.ingredients(), recipeDTO.steps(), recipeDTO.portions(), recipeDTO.image(), recipeDTO.createdBy(), recipeDTO.createdAt());
        recipes.remove(recipeToUpdate);
        recipes.add(updatedRecipe);
        return recipeDTO;
    }

    @Override
    public boolean deleteRecipeById(UUID id) {
        Recipe recipeToDelete = recipes.stream()
                .filter(recipe -> recipe.id().equals(id))
                .findFirst()
                .orElse(null);
        return recipes.remove(recipeToDelete);
    }

    @Override
    public Set<UnitDTO> getAllUnits() {
        return units.stream().map(unit -> new UnitDTO(unit.unitId(), unit.unitName())).collect(Collectors.toSet());
    }

    @Override
    public UnitDTO createNewUnit(NewUnitDTO newUnitDTO) {
        Unit newUnit = new Unit(0, UUID.randomUUID(), newUnitDTO.unitName());
        units.add(newUnit);
        return new UnitDTO(newUnit.unitId(), newUnit.unitName());
    }

    @Override
    public UnitDTO getUnitById(UUID uuid) {
        Unit unit = units.stream().filter(unit1 -> unit1.unitId().equals(uuid)).findFirst().orElse(null);
        return new UnitDTO(unit.unitId(), unit.unitName());
    }

    @Override
    public UnitDTO deleteUnitById(UUID uuid) {
        Unit unitToRemove = units.stream().filter(unit -> unit.unitId().equals(uuid)).findFirst().orElse(null);
        units.remove(unitToRemove);
        return new UnitDTO(unitToRemove.unitId(), unitToRemove.unitName());
    }

    @Override
    public UnitDTO updateUnitById(UUID uuid, UnitDTO unitDTO) {
        Unit unitToUpdate = units.stream().filter(unit -> unit.unitId().equals(uuid)).findFirst().orElse(null);
        units.remove(unitToUpdate);
        Unit updatedUnit = new Unit(unitToUpdate.databaseId(), unitToUpdate.unitId(), unitDTO.unitName());
        units.add(updatedUnit);
        return new UnitDTO(updatedUnit.unitId(), updatedUnit.unitName());
    }
}