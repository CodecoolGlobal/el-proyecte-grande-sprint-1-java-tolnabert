package com.codecool.chilibeans.model.recipe;

import java.util.Set;
import java.util.UUID;


public record Recipe(long dataBaseId, UUID id, String name, String description, Set<Diet> diets) {
}
