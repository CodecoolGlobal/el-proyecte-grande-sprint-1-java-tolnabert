package com.codecool.chilibeans.controller;

import com.codecool.chilibeans.controller.dto.recipe.NewRecipeDTO;
import com.codecool.chilibeans.controller.dto.recipe.RecipeDTO;
import com.codecool.chilibeans.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    private final RecipeService service;

    @Autowired
    public RecipeController(RecipeService chiliService) {
        this.service = chiliService;
    }

    @GetMapping("")
    public Set<RecipeDTO> getAllRecipes() {
        return service.getAllRecipes();
    }

    @GetMapping("/{id}")
    public RecipeDTO getRecipeById(@PathVariable UUID id) {
        return service.getRecipeById(id);
    }

    @PostMapping("")
    public RecipeDTO createNewRecipe(@RequestBody NewRecipeDTO newRecipeDTO) {
        return service.createNewRecipe(newRecipeDTO);
    }

    @PatchMapping("/{id}")
    public RecipeDTO updateRecipe(@PathVariable UUID id, @RequestBody RecipeDTO recipeDTO) {
        return service.updateRecipe(id, recipeDTO);
    }

    @DeleteMapping("/{id}")
    public boolean deleteRecipe(@PathVariable UUID id) {
        return service.deleteRecipeById(id);
    }

}
