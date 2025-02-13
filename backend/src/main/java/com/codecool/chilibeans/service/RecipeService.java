package com.codecool.chilibeans.service;

import com.codecool.chilibeans.controller.dto.diet.DietDTO;
import com.codecool.chilibeans.controller.dto.recipe.NewRecipeDTO;
import com.codecool.chilibeans.controller.dto.recipe.RecipeDTO;
import com.codecool.chilibeans.exception.ElementMeantToSaveExists;
import com.codecool.chilibeans.model.Client;
import com.codecool.chilibeans.model.recipe.Diet;
import com.codecool.chilibeans.model.recipe.Recipe;
import com.codecool.chilibeans.repository.ClientRepository;
import com.codecool.chilibeans.repository.recipe.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final ClientRepository clientRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository, ClientRepository clientRepository) {
        this.recipeRepository = recipeRepository;
        this.clientRepository = clientRepository;
    }

    public Set<RecipeDTO> getAll(String sortBy, String sortOrder) {
        Sort.Direction sortDirection;

        if (sortOrder.equals("asc")) {
            sortDirection = Sort.Direction.ASC;

        } else if (sortOrder.equals("desc")) {
            sortDirection = Sort.Direction.DESC;

        } else {
            throw new IllegalArgumentException("Sort Order \"" + sortOrder + "\" is not a valid option.");
        }

        Sort sort = Sort.by(sortDirection, sortBy);

        return recipeRepository.findAll(sort).stream().map(RecipeService::convertToRecipeDTO).collect(Collectors.toSet());
    }

    public RecipeDTO getByPublicId(UUID publicId) {

        Recipe recipe = recipeRepository.findByPublicId(publicId).orElseThrow(NoSuchElementException::new);
        return convertToRecipeDTO(recipe);

    }

    private static RecipeDTO convertToRecipeDTO(Recipe recipe) {
        return new RecipeDTO(
                recipe.getPublicId(),
                recipe.getName(),
                recipe.getDescription(),
                convertToDietDTO(recipe.getDiets()),
                recipe.getIngredients(),
                recipe.getSteps(),
                recipe.getPortions(),
                recipe.getImage(),
                recipe.getCreatedBy().getPublicId(),
                recipe.getCreatedAt());
    }

    private static Set<DietDTO> convertToDietDTO(Set<Diet> diets) {
        return diets.stream().map(diet -> new DietDTO(diet.getPublicId(), diet.getName())).collect(Collectors.toSet());
    }

    public RecipeDTO save(NewRecipeDTO newRecipeDTO) {
        Optional<Recipe> optionalRecipe = recipeRepository.findByNameIgnoreCaseAndDescriptionIgnoreCase(newRecipeDTO.name(), newRecipeDTO.description()); //TODO: Is the name really unique? Decide what makes a recipe unique
        if (optionalRecipe.isEmpty()) {
            Client creator = clientRepository.findByPublicId(newRecipeDTO.createdBy()).orElseThrow( () -> new NoSuchElementException("User not found.")
            );

            Recipe recipe = new Recipe();
            setNewRecipeBasedDTO(newRecipeDTO, recipe, creator);
            return convertToRecipeDTO(recipe);
        }
        throw new ElementMeantToSaveExists(newRecipeDTO);
    }

    private void setNewRecipeBasedDTO(NewRecipeDTO newRecipeDTO, Recipe recipe, Client creator) {
        recipe.setPublicId(UUID.randomUUID());
        recipe.setName(newRecipeDTO.name());
        recipe.setDescription(newRecipeDTO.description());
        recipe.setDiets(newRecipeDTO.diets());
        recipe.setIngredients(newRecipeDTO.ingredients());
        recipe.setSteps(newRecipeDTO.steps());
        recipe.setPortions(newRecipeDTO.portions());
        recipe.setImage(newRecipeDTO.image());
        recipe.setCreatedBy(creator);
        recipe.setCreatedAt(LocalDate.now());
        recipeRepository.save(recipe);
    }

    private void setRecipeBasedDTO(RecipeDTO recipeDTO, Recipe recipe) {
        recipe.setName(recipeDTO.name());
        recipe.setDescription(recipeDTO.description());
        recipe.setDiets(recipeDTO.diets().stream().map(dietDTO -> new Diet(dietDTO.publicId(), dietDTO.name())).collect(Collectors.toSet()));
        recipe.setIngredients(recipeDTO.ingredients());
        recipe.setSteps(recipeDTO.steps());
        recipe.setPortions(recipeDTO.portions());
        recipe.setImage(recipeDTO.image());
        recipeRepository.save(recipe);
    }


    public RecipeDTO updateByPublicId(RecipeDTO recipeDTO) {
        Recipe recipe = recipeRepository.findByPublicId(recipeDTO.publicId()).orElseThrow(NoSuchElementException::new);
        setRecipeBasedDTO(recipeDTO, recipe);
        return convertToRecipeDTO(recipe);


    }

    @Transactional
    public int deleteByPublicId(UUID publicId) {
        return recipeRepository.deleteByPublicId(publicId); //TODO: unsuccessful deletion should throw exception
    }
}