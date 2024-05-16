package com.codecool.chilibeans.repository.recipe;

import com.codecool.chilibeans.model.recipe.Unit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UnitRepository extends JpaRepository<Unit, Long> {
    Optional<Unit> findByPublicId(UUID publicId);
    Optional<Unit> findByUnitNameIgnoreCase(String name);
    int deleteByPublicId(UUID publicId);
}
