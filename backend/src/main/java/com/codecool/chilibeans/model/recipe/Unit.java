package com.codecool.chilibeans.model.recipe;

import java.util.UUID;

public record Unit (long databaseId, UUID unitId, String unitName) {
}