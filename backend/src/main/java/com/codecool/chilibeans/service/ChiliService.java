package com.codecool.chilibeans.service;

import com.codecool.chilibeans.controller.dto.recipe.NewRecipeDTO;
import com.codecool.chilibeans.controller.dto.recipe.RecipeDTO;
import com.codecool.chilibeans.controller.dto.user.NewUserDTO;
import com.codecool.chilibeans.controller.dto.user.UserDTO;
import com.codecool.chilibeans.model.User;
import com.codecool.chilibeans.model.recipe.Recipe;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class ChiliService implements ChiliServiceInterface {

    private final Set<User> users = new HashSet<>();
    private final Set<Recipe> recipes = new HashSet<>();

    @Override
    public Set<UserDTO> getAllUsers() {
        Set<UserDTO> userDTOs = new HashSet<>();
        for (User user : users) {
            userDTOs.add(new UserDTO(user.id(), user.username(), user.password(), user.firstName(), user.lastName(), user.dateOfBirth(),
                    user.email(), user.ownRecipes(), user.favoredRecipes(), user.creationDate()));
        }
        return userDTOs;
    }

    @Override
    public UserDTO getUserById(UUID id) {
        User user = users.stream().filter(user1 -> user1.id().equals(id))
                .findFirst()
                .orElse(null);
        return new UserDTO(user.id(), user.username(), user.password(), user.firstName(), user.lastName(), user.dateOfBirth(),
                user.email(), user.ownRecipes(), user.favoredRecipes(), user.creationDate());
    }

    @Override
    public UserDTO createNewUser(NewUserDTO newUserDTO) {
        User newUser = new User(0, UUID.randomUUID(), newUserDTO.username(), newUserDTO.password(), newUserDTO.firstName(),
                newUserDTO.lastName(), newUserDTO.dateOfBirth(), newUserDTO.email(), newUserDTO.ownRecipes(), newUserDTO.favoredRecipes(),
                null);
        users.add(newUser);
        return new UserDTO(newUser.id(), newUser.username(), newUser.password(), newUser.firstName(), newUser.lastName(),
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
    public boolean DeleteUserById(UUID id) {
        return users.removeIf(user -> user.id().equals(id));
    }

    @Override
    public Set<RecipeDTO> getAllRecipes() {
        Set<RecipeDTO> recipeDTOs = new HashSet<>();
        for (Recipe recipe : recipes) {
            recipeDTOs.add(new RecipeDTO(recipe.id(), recipe.name(),
                    recipe.description(), recipe.diets()));
        }
        return recipeDTOs;
    }

    @Override
    public RecipeDTO getRecipeById(UUID id) {
        Recipe recipe = recipes.stream().filter(r -> r.id().equals(id)).findFirst().orElse(null);
        return new RecipeDTO(recipe.id(), recipe.name(),
                recipe.description(), recipe.diets());
    }

    @Override
    public RecipeDTO createNewRecipe(NewRecipeDTO newRecipeDTO) {
        Recipe newRecipe = new Recipe(0, UUID.randomUUID(),
                newRecipeDTO.name(), newRecipeDTO.description(), newRecipeDTO.diets());
        recipes.add(newRecipe);
        return new RecipeDTO(newRecipe.id(), newRecipe.name(), newRecipe.description(), newRecipe.diets());
    }

    @Override
    public RecipeDTO updateRecipe(UUID id, RecipeDTO recipeDTO) {
        Recipe recipeToUpdate = recipes.stream()
                .filter(recipe -> recipe.id().equals(id))
                .findFirst()
                .orElse(null);
        Recipe updatedRecipe = new Recipe(recipeToUpdate.dataBaseId(), recipeDTO.id(),
                recipeDTO.name(), recipeDTO.description(), recipeDTO.diets());
        recipes.remove(recipeToUpdate);
        recipes.add(updatedRecipe);
        return recipeDTO;
    }

    @Override
    public boolean DeleteRecipeById(UUID id) {
        Recipe recipeToDelete = recipes.stream()
                .filter(recipe -> recipe.id().equals(id))
                .findFirst()
                .orElse(null);
        return recipes.remove(recipeToDelete);
    }
}
