package com.codecool.chilibeans.repository.recipe;

import com.codecool.chilibeans.model.recipe.Recipe;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findAll(Sort sort);
    Set<Recipe> findByOrderByNameDesc();
    Set<Recipe> findByOrderByCreatedAtAsc();
    Set<Recipe> findByOrderByCreatedAtDesc();
    Optional<Recipe> findByPublicId(UUID publicId);
    Optional<Recipe> findByNameIgnoreCase(String name);
    boolean deleteByPublicId(UUID publicId);
}
