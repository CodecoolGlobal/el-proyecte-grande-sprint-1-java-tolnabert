package com.codecool.chilibeans.service;

import com.codecool.chilibeans.controller.dto.DietDTO.DietDTO;
import com.codecool.chilibeans.controller.dto.DietDTO.NewDietDTO;

import java.util.Set;
import java.util.UUID;

public interface DietService {

    Set<DietDTO> getAllDiets();

    DietDTO getDietById(UUID id);

    DietDTO createNewDiet(NewDietDTO newDietDTO);

    DietDTO updateDiet(UUID id, DietDTO dietDTO);

    boolean deleteDietById(UUID id);

}
