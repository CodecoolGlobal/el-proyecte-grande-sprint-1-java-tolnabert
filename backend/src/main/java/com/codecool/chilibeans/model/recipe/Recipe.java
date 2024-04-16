package com.codecool.chilibeans.model.recipe;

import java.util.Set;


public record Recipe(long id, String name, String description, Set<Diets> diets) {
}
