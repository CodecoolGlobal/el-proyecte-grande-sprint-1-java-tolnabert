package com.codecool.chilibeans.service;

import com.codecool.chilibeans.controller.dto.DietDTO.DietDTO;
import com.codecool.chilibeans.controller.dto.DietDTO.NewDietDTO;
import com.codecool.chilibeans.controller.dto.recipe.NewRecipeDTO;
import com.codecool.chilibeans.controller.dto.recipe.RecipeDTO;
import com.codecool.chilibeans.controller.dto.user.NewUserDTO;
import com.codecool.chilibeans.controller.dto.user.UserDTO;
import com.codecool.chilibeans.model.User;
import com.codecool.chilibeans.model.recipe.Diet;
import com.codecool.chilibeans.model.recipe.Recipe;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class ChiliService implements ChiliServiceInterface {

    private final Set<User> users = new HashSet<>();
    private final Set<Recipe> recipes = new HashSet<>();
    private final Set<Diet> diets = new HashSet<>();

    // User service

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


    // Recipe service

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
    public boolean deleteRecipeById(UUID id) {
        Recipe recipeToDelete = recipes.stream()
                .filter(recipe -> recipe.id().equals(id))
                .findFirst()
                .orElse(null);
        return recipes.remove(recipeToDelete);
    }

    // Diet service


    @Override
    public Set<DietDTO> getAllDiets() {
        Set<DietDTO> dietDTOs = new HashSet<>();
        for (Diet diet : diets){
            dietDTOs.add(new DietDTO(diet.id(), diet.name()));
        }
        return dietDTOs;
    }

    @Override
    public DietDTO getDietById(UUID id) {
        Diet diet = diets.stream()
                .filter(diet1 -> diet1.id().equals(id))
                .findFirst()
                .orElse(null);

        return new DietDTO(diet.id(), diet.name());
    }

    @Override
    public DietDTO createNewDiet(NewDietDTO newDietDTO) {
        Diet newDiet = new Diet(0, UUID.randomUUID(), newDietDTO.name());
        diets.add(newDiet);
        return new DietDTO(newDiet.id(), newDiet.name());
    }

    @Override
    public DietDTO updateDiet(UUID id, DietDTO dietDTO) {
        Diet dietToUpdate = diets.stream().filter(diet -> diet.id().equals(id))
                .findFirst().orElse(null);
        Diet updatedDiet = new Diet(0, dietToUpdate.id(), dietDTO.name());
        diets.add(updatedDiet);
        diets.remove(dietToUpdate);

        return dietDTO;
    }

    @Override
    public boolean deleteDietById(UUID id) {
        return diets.removeIf(diet -> diet.id().equals(id));
    }
}
