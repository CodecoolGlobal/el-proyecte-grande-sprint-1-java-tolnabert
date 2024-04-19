package com.codecool.chilibeans.service;

import com.codecool.chilibeans.controller.dto.recipe.NewRecipeDTO;
import com.codecool.chilibeans.controller.dto.recipe.RecipeDTO;
import com.codecool.chilibeans.model.recipe.Recipe;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.UUID;
import java.util.*;

@Service
public class RecipeService {

    private final Set<Recipe> recipes = new HashSet<>();

    public List<RecipeDTO> getAll(String sortBy, String sorOrder) {
        List<RecipeDTO> recipeDTOs = new LinkedList<>();
        for (Recipe recipe : recipes) {
            recipeDTOs.add(new RecipeDTO(recipe.id(), recipe.name(),
                    recipe.description(), recipe.diets(), recipe.ingredients(), recipe.steps(), recipe.portions(), recipe.image(), recipe.createdBy(), recipe.createdAt()));
        }


        return sort(recipeDTOs, sortBy, sorOrder);
    }

    private List<RecipeDTO> sort(List<RecipeDTO> recipes, String sortBy, String sortOrder) {
        Comparator<RecipeDTO> comparator = Comparator.comparing(RecipeDTO::name);

        if ("createdAt".equalsIgnoreCase(sortBy)) {
            comparator = Comparator.comparing(RecipeDTO::createdAt);
        }

        if ("desc".equalsIgnoreCase(sortOrder)) {
            comparator = comparator.reversed();
        }

        return recipes.stream().sorted(comparator).toList();
    }

    public RecipeDTO getById(UUID id) {
        Recipe recipe = recipes.stream().filter(r -> r.id().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Recipe not found with ID: " + id));

        return new RecipeDTO(recipe.id(), recipe.name(),
                recipe.description(), recipe.diets(), recipe.ingredients(), recipe.steps(), recipe.portions(), recipe.image(), recipe.createdBy(), recipe.createdAt());
    }

    public RecipeDTO create(NewRecipeDTO newRecipeDTO) {
        Recipe newRecipe = new Recipe(0, UUID.randomUUID(), newRecipeDTO.name(), newRecipeDTO.description(), newRecipeDTO.diets(), newRecipeDTO.ingredients(), newRecipeDTO.steps(), newRecipeDTO.portions(), newRecipeDTO.image(), newRecipeDTO.createdBy(), LocalDate.now());
        recipes.add(newRecipe);

        return new RecipeDTO(newRecipe.id(), newRecipe.name(), newRecipe.description(), newRecipe.diets(), newRecipe.ingredients(), newRecipe.steps(), newRecipe.portions(), newRecipe.image(), newRecipe.createdBy(), newRecipe.createdAt());
    }

    public RecipeDTO updateById(UUID id, RecipeDTO recipeDTO) {
        Recipe recipeToUpdate = recipes.stream()
                .filter(recipe -> recipe.id().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Recipe not found with ID: " + id));

        Recipe updatedRecipe = new Recipe(recipeToUpdate.dataBaseId(), recipeDTO.id(), recipeDTO.name(), recipeDTO.description(), recipeDTO.diets(), recipeDTO.ingredients(), recipeDTO.steps(), recipeDTO.portions(), recipeDTO.image(), recipeDTO.createdBy(), recipeDTO.createdAt());
        recipes.remove(recipeToUpdate);
        recipes.add(updatedRecipe);

        return recipeDTO;
    }

    public boolean deleteById(UUID id) {
        Recipe recipeToDelete = recipes.stream()
                .filter(recipe -> recipe.id().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Recipe not found with ID: " + id));

        return recipes.remove(recipeToDelete);
    }
}
