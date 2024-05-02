package com.codecool.chilibeans.controller.dto.user;

import com.codecool.chilibeans.model.recipe.Recipe;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

public record UserDTO(UUID publicId, String username, String firstName, String lastName, LocalDate dateOfBirth,
                      String email, Set<Recipe> ownRecipes, Set<Recipe> favoredRecipes, LocalDate creationDate) {
}
