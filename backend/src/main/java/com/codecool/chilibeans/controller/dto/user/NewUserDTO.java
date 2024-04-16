package com.codecool.chilibeans.controller.dto.user;

import com.codecool.chilibeans.model.recipe.Recipe;

import java.time.LocalDate;
import java.util.Set;

public record NewUserDTO(String username, String password, String firstName, String lastName, LocalDate dateOfBirth,
                         String email, Set<Recipe> ownRecipes, Set<Recipe> favoredRecipes) {
}

