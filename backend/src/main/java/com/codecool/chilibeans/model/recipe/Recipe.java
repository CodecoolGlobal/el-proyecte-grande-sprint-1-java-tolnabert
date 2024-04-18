package com.codecool.chilibeans.model.recipe;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;


public record Recipe(long dataBaseId,
                     UUID id,
                     String name,
                     String description,
                     Set<Diet> diets,
                     Set<Ingredient> ingredients,
                     List<Step> steps,
                     int portions,
                     String image,
                     UUID createdBy,
                     LocalDate createdAt) {
}
