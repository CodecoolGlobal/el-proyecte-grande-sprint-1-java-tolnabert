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
public class DietServiceImpl implements DietService {

    private final Set<Diet> diets = new HashSet<>();

    @Override
    public Set<DietDTO> getAllDiets() {
        Set<DietDTO> dietDTOs = new HashSet<>();
        for (Diet diet : diets) {
            dietDTOs.add(new DietDTO(diet.id(), diet.name()));
        }
        return dietDTOs;
    }

    @Override
    public DietDTO getDietById(UUID id) {
        Diet diet = diets.stream()
                .filter(diet1 -> diet1.id().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Diet not found with ID: " + id));

        return new DietDTO(diet.id(), diet.name());
    }

    @Override
    public DietDTO createNewDiet(NewDietDTO newDietDTO) {
        Diet newDiet = new Diet(0, UUID.randomUUID(), newDietDTO.name());
        diets.add(newDiet);
        return new DietDTO(newDiet.id(), newDiet.name());
    }

    @Override
    public DietDTO updateDiet(UUID id, DietDTO dietDTO) {
        Diet dietToUpdate = diets.stream().filter(diet -> diet.id().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Diet not found with ID: " + id));

        Diet updatedDiet = new Diet(0, dietToUpdate.id(), dietDTO.name());
        diets.add(updatedDiet);
        diets.remove(dietToUpdate);

        return dietDTO;
    }

    @Override
    public boolean deleteDietById(UUID id) {
        return diets.removeIf(diet -> diet.id().equals(id));
    }


}
