package com.codecool.chilibeans.repository.recipe;

import com.codecool.chilibeans.model.recipe.Recipe;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findAll(Sort sort);

    Optional<Recipe> findByPublicId(UUID publicId);

    Optional<Recipe> findByNameIgnoreCaseAndDescriptionIgnoreCase(String name, String Description);

    int deleteByPublicId(UUID publicId);

}
