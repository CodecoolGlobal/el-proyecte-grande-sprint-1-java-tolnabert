package com.codecool.chilibeans.controller;

import com.codecool.chilibeans.controller.dto.recipe.NewRecipeDTO;
import com.codecool.chilibeans.controller.dto.recipe.RecipeDTO;
import com.codecool.chilibeans.service.ChiliService;
import com.codecool.chilibeans.service.ChiliServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    private ChiliServiceInterface chiliService;

    @Autowired
    public RecipeController(ChiliServiceInterface chiliService) {
        this.chiliService = chiliService;
    }

    @GetMapping("")
    public ResponseEntity<Set<RecipeDTO>> getAllRecipes(){
        return ResponseEntity.ok().body(chiliService.getAllRecipes());
    }

    @GetMapping("/{id}")
    public RecipeDTO getRecipeById(@PathVariable UUID id){
        return chiliService.getRecipeById(id);
    }

    @PostMapping("")
    public RecipeDTO createNewRecipe(@RequestBody NewRecipeDTO newRecipeDTO){
        return chiliService.createNewRecipe(newRecipeDTO);
    }

    @PatchMapping("/{id}")
    public RecipeDTO updateRecipe (@PathVariable UUID id, @RequestBody RecipeDTO recipeDTO){
        return chiliService.updateRecipe(id, recipeDTO);
    }

    @DeleteMapping("{id}")
    public boolean deleteRecipe(@PathVariable UUID id){
        return chiliService.deleteRecipeById(id);
    }

}
