package com.codecool.chilibeans.controller;

import com.codecool.chilibeans.controller.dto.recipe.RecipeDTO;
import com.codecool.chilibeans.service.ChiliService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class RecipeController {

    private ChiliService chiliService;

    @Autowired
    public RecipeController(ChiliService chiliService) {
        this.chiliService = chiliService;
    }

    @GetMapping("/recipe")
    public ResponseEntity<Set<RecipeDTO>> getAllRecipe(){
        return ResponseEntity.ok().body(chiliService.getAllRecipe());
    }

    @GetMapping("/recipe/{id}")
    public RecipeDTO getRecipeById(@PathVariable long id){
        return chiliService.getRecipeById(id);
    }
}
