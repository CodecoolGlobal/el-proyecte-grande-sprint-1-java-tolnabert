package com.codecool.chilibeans.service;

import com.codecool.chilibeans.controller.dto.DietDTO.DietDTO;
import com.codecool.chilibeans.controller.dto.recipe.NewRecipeDTO;
import com.codecool.chilibeans.controller.dto.recipe.RecipeDTO;
import com.codecool.chilibeans.exception.ElementMeantToSaveExists;
import com.codecool.chilibeans.model.User;
import com.codecool.chilibeans.model.recipe.Diet;
import com.codecool.chilibeans.model.recipe.Recipe;
import com.codecool.chilibeans.repository.UserRepository;
import com.codecool.chilibeans.repository.recipe.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository, UserRepository userRepository) {
        this.recipeRepository = recipeRepository;
        this.userRepository = userRepository;
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
                recipe.getDiets(),
                recipe.getIngredients(),
                recipe.getSteps(),
                recipe.getPortions(),
                recipe.getImage(),
                recipe.getCreatedBy().getPublicId(),
                recipe.getCreatedAt());
    }

    public RecipeDTO save(NewRecipeDTO newRecipeDTO) {

        Optional<Recipe> optionalRecipe = recipeRepository.findByNameIgnoreCase(newRecipeDTO.name());
        if (optionalRecipe.isEmpty()) {

            User creator = userRepository.findByPublicId(newRecipeDTO.createdBy()).orElseThrow(NoSuchElementException::new);

            Recipe recipe = new Recipe();
            setRecipeBasedDTO(newRecipeDTO, recipe, creator);

            return convertToRecipeDTO(recipe);
        }
        throw new ElementMeantToSaveExists(newRecipeDTO);

    }

    private void setRecipeBasedDTO(NewRecipeDTO newRecipeDTO, Recipe recipe, User creator) {
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


    public RecipeDTO updateById(RecipeDTO recipeDTO) {

        Recipe recipe = recipeRepository.findByPublicId(recipeDTO.publicId()).orElseThrow(NoSuchElementException::new);



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
