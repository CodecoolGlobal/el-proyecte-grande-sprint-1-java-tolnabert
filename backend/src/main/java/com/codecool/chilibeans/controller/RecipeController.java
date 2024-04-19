package com.codecool.chilibeans.controller;

import com.codecool.chilibeans.controller.dto.recipe.NewRecipeDTO;
import com.codecool.chilibeans.controller.dto.recipe.RecipeDTO;
import com.codecool.chilibeans.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    private final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("")
    public List<RecipeDTO> getAll(@RequestParam(required = false, defaultValue = "name", name = "sortBy") String sortBy,
                                  @RequestParam(required = false, defaultValue = "asc", name = "sortOrder") String sorOrder) {
        return recipeService.getAll(sortBy, sorOrder);
    }

    @GetMapping("/{id}")
    public RecipeDTO getById(@PathVariable UUID id) {
        return recipeService.getById(id);
    }

    @PostMapping("")
    public RecipeDTO create(@RequestBody NewRecipeDTO newRecipeDTO) {
        return recipeService.create(newRecipeDTO);
    }

    @PatchMapping("/{id}")
    public RecipeDTO updateById(@PathVariable UUID id, @RequestBody RecipeDTO recipeDTO) {
        return recipeService.updateById(id, recipeDTO);
    }

    @DeleteMapping("/{id}")
    public boolean deleteById(@PathVariable UUID id) {
        return recipeService.deleteById(id);
    }

}
