package com.codecool.chilibeans.repository.recipe;

import com.codecool.chilibeans.model.recipe.Step;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StepRepository extends JpaRepository<Step, Long> {
}
