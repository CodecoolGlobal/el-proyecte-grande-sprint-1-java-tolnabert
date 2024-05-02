package com.codecool.chilibeans.service;

import com.codecool.chilibeans.controller.dto.recipe.NewRecipeDTO;
import com.codecool.chilibeans.controller.dto.recipe.RecipeDTO;
import com.codecool.chilibeans.model.recipe.Recipe;
import com.codecool.chilibeans.repository.recipe.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import java.util.NoSuchElementException;
import java.util.Set;
import java.util.UUID;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Set<RecipeDTO> getAll(String sortBy, String sortOrder) {

        if (sortOrder.equals("asc")) {
            Sort.Direction sortDirection = Sort.Direction.ASC;

        } else if (sortOrder.equals("desc")) {
            Sort.Direction sortDirection = Sort.Direction.DESC;

        }

        Sort sort = Sort.by(sortBy).;

        return recipeRepository.findAll(Sort.by(sortBy).ascending()).stream().map(recipe -> new RecipeDTO(
               recipe.getPublicId(),
               recipe.getName(),
               recipe.getDescription(),
               recipe.getDiets(),
               recipe.getIngredients(),
               recipe.getSteps(),
               recipe.getPortions(),
               recipe.getImage(),
               recipe.getCreatedBy(),
               recipe.getCreatedAt()
       )).collect(Collectors.toSet());
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

    public RecipeDTO getById(UUID publicId) {
        Recipe recipe = recipeRepository.findByPublicId(publicId).orElseThrow(NoSuchElementException::new);

        return new RecipeDTO(recipe.getPublicId(), recipe.getName(),
                recipe.getDescription(), recipe.getDiets(), recipe.getIngredients(), recipe.getSteps(), recipe.getPortions(), recipe.getImage(), recipe.getCreatedBy(), recipe.getCreatedAt());
    }

    public RecipeDTO save(NewRecipeDTO newRecipeDTO) {
        Optional<Recipe> optionalRecipe = recipeRepository.findByNameIgnoreCase(newRecipeDTO.name());
        if(optionalRecipe.isEmpty()){
            Recipe newRecipe = new Recipe();
            newRecipe.setPublicId(UUID.randomUUID());
            newRecipe.setName(newRecipeDTO.name());
            newRecipe.setDescription(newRecipeDTO.description());
            newRecipe.setDiets(newRecipeDTO.diets());
            newRecipe.setIngredients(newRecipeDTO.ingredients());
            newRecipe.setSteps(newRecipeDTO.steps());
            newRecipe.setPortions(newRecipeDTO.portions());
            newRecipe.setImage(newRecipeDTO.image());
            newRecipe.setCreatedBy(newRecipeDTO.createdBy());
            newRecipe.setCreatedAt(LocalDate.now());
            recipeRepository.save(newRecipe);
            return new RecipeDTO(newRecipe.getPublicId(), newRecipe.getName(), newRecipe.getDescription(), newRecipe.getDiets(), newRecipe.getIngredients(), newRecipe.getSteps(), newRecipe.getPortions(), newRecipe.getImage(), newRecipe.getCreatedBy(), newRecipe.getCreatedAt());
        }
        Recipe recipe = optionalRecipe.get();

        return new RecipeDTO(recipe.getPublicId(), recipe.getName(), recipe.getDescription(), recipe.getDiets(), recipe.getIngredients(), recipe.getSteps(), recipe.getPortions(), recipe.getImage(), recipe.getCreatedBy(), recipe.getCreatedAt());
    }

    public RecipeDTO updateByPublicId(RecipeDTO recipeDTO) {
        Optional<Recipe> optionalRecipe = recipeRepository.findByPublicId(recipeDTO.publicId());
        if(optionalRecipe.isEmpty()){
            throw new NoSuchElementException();
        }
        Recipe recipe = optionalRecipe.get();
        recipe.setName(recipeDTO.name());
        recipe.setDescription(recipeDTO.description());
        recipe.setDiets(recipeDTO.diets());
        recipe.setIngredients(recipeDTO.ingredients());
        recipe.setSteps(recipeDTO.steps());
        recipe.setPortions(recipeDTO.portions());
        recipe.setImage(recipeDTO.image());
        recipe.setCreatedBy(recipeDTO.createdBy());
        recipe.setCreatedAt(LocalDate.now());
        recipeRepository.save(recipe);

        return new RecipeDTO(recipe.getPublicId(), recipe.getName(), recipe.getDescription(), recipe.getDiets(), recipe.getIngredients(), recipe.getSteps(), recipe.getPortions(), recipe.getImage(), recipe.getCreatedBy(), recipe.getCreatedAt());
    }

    public boolean deleteByPublicId(UUID publicId) {
        return recipeRepository.deleteByPublicId(publicId);
    }
}
