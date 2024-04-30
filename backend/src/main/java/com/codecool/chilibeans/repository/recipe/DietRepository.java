package com.codecool.chilibeans.repository.recipe;

import com.codecool.chilibeans.model.recipe.Diet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DietRepository extends JpaRepository<Diet, Long> {
}
