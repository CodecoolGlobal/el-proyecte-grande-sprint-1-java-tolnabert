package com.codecool.chilibeans.service;

import com.codecool.chilibeans.controller.dto.DietDTO.DietDTO;
import com.codecool.chilibeans.controller.dto.DietDTO.NewDietDTO;
import com.codecool.chilibeans.model.recipe.Diet;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.UUID;

@Service
public class DietService {

    private final Set<Diet> diets = new HashSet<>();

    public Set<DietDTO> getAll() {
        Set<DietDTO> dietDTOs = new HashSet<>();
        for (Diet diet : diets) {
            dietDTOs.add(new DietDTO(diet.id(), diet.name()));
        }
        return dietDTOs;
    }

    public DietDTO getById(UUID id) {
        Diet diet = diets.stream()
                .filter(diet1 -> diet1.id().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Diet not found with ID: " + id));

        return new DietDTO(diet.id(), diet.name());
    }

    public DietDTO create(NewDietDTO newDietDTO) {
        Diet newDiet = new Diet(0, UUID.randomUUID(), newDietDTO.name());
        diets.add(newDiet);

        return new DietDTO(newDiet.id(), newDiet.name());
    }

    public DietDTO updateById(UUID id, DietDTO dietDTO) {
        Diet dietToUpdate = diets.stream().filter(diet -> diet.id().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Diet not found with ID: " + id));

        Diet updatedDiet = new Diet(0, dietToUpdate.id(), dietDTO.name());
        diets.add(updatedDiet);
        diets.remove(dietToUpdate);

        return dietDTO;
    }

    public boolean deleteById(UUID id) {
        return diets.removeIf(diet -> diet.id().equals(id));
    }
}
