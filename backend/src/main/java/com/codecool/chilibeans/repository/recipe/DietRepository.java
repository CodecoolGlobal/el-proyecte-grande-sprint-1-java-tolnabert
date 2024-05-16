package com.codecool.chilibeans.repository.recipe;

import com.codecool.chilibeans.model.recipe.Diet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DietRepository extends JpaRepository<Diet, Long> {

    Optional<Diet> findByPublicId(UUID publicId);

    Optional<Diet> findByNameIgnoreCase(String name);

    int deleteByPublicId(UUID publicId);

}
