package com.codecool.chilibeans.model;

import com.codecool.chilibeans.model.recipe.Recipe;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

public record User(long id, String username, String password, String firstName, String lastName, LocalDate dateOfBirth,
                   String email, Set<Recipe> ownRecipes, Set<Recipe> favoredRecipes, LocalDateTime creationDate) {

}
