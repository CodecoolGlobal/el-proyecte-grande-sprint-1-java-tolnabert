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

    private final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    public Set<RecipeDTO> getAll(@RequestParam(required = false, defaultValue = "name", name = "sortBy") String sortBy,
                                 @RequestParam(required = false, defaultValue = "asc", name = "sortOrder") String sorOrder) {
        return recipeService.getAll(sortBy, sorOrder);
    }

    @GetMapping("/{id}")
    public RecipeDTO getById(@PathVariable UUID id) {
        return recipeService.getByPublicId(id);
    }

    @PostMapping("/user")
    public RecipeDTO create(@RequestBody NewRecipeDTO newRecipeDTO) {
        System.out.println(newRecipeDTO);
        return recipeService.save(newRecipeDTO);
    }

    @PatchMapping("/user")
    public RecipeDTO updateById(@RequestBody RecipeDTO recipeDTO) {
        return recipeService.updateByPublicId(recipeDTO);
    }

    @DeleteMapping("/user/{id}")
    public int deleteById(@PathVariable UUID id) {
        return recipeService.deleteByPublicId(id);
    }

}
