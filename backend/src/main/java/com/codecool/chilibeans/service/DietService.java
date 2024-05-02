package com.codecool.chilibeans.service;

import com.codecool.chilibeans.controller.dto.DietDTO.DietDTO;
import com.codecool.chilibeans.controller.dto.DietDTO.NewDietDTO;
import com.codecool.chilibeans.model.recipe.Diet;
import com.codecool.chilibeans.repository.recipe.DietRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DietService {

    private final DietRepository dietRepository;

    @Autowired
    public DietService(DietRepository dietRepository) {
        this.dietRepository = dietRepository;
    }

    public Set<DietDTO> getAll() {
        List<Diet> diets = dietRepository.findAll();
        return diets.stream().map(diet -> new DietDTO(diet.getPublicId(), diet.getName())).collect(Collectors.toSet());
    }

    public DietDTO getByPublicId(UUID publicId) {
        Diet diet = dietRepository.findByPublicId(publicId).orElseThrow(NoSuchElementException::new);
        return new DietDTO(diet.getPublicId(), diet.getName());
    }

    public DietDTO save(NewDietDTO newDietDTO) {
        Optional<Diet> optionalDiet = dietRepository.findByNameIgnoreCase(newDietDTO.name());
        if (optionalDiet.isEmpty()) {
            Diet newDiet = new Diet();
            newDiet.setName(newDietDTO.name());
            newDiet.setPublicId(UUID.randomUUID());
            dietRepository.save(newDiet);
            return new DietDTO(newDiet.getPublicId(), newDiet.getName());
        }
        throw new ElementMeantToSaveExists(newDietDTO);
    }

    public DietDTO updateByPublicId(DietDTO dietDTO) {
        Optional<Diet> optionalDiet = dietRepository.findByPublicId(dietDTO.publicId());
        if (optionalDiet.isEmpty()) {
            throw new NoSuchElementException();
        }
        Diet diet = optionalDiet.get();
        diet.setName(dietDTO.name());

        return dietDTO;
    }

    public boolean deleteByPublicId(UUID publicId) {
        return dietRepository.deleteByPublicId(publicId);
    }
}
