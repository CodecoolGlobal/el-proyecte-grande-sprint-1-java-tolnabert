package com.codecool.chilibeans.controller;

import com.codecool.chilibeans.controller.dto.DietDTO.DietDTO;
import com.codecool.chilibeans.controller.dto.DietDTO.NewDietDTO;
import com.codecool.chilibeans.service.DietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/diets")
public class DietController {

    private final DietService service;

    @Autowired
    public DietController(DietService chiliService) {
        this.service = chiliService;
    }

    @GetMapping("")
    public Set<DietDTO> getDiets() {
        return service.getAllDiets();
    }

    @GetMapping("/{id}")
    public DietDTO getDiet(@PathVariable UUID id) {
        return service.getDietById(id);
    }

    @PostMapping("")
    public DietDTO createDiet(@RequestBody NewDietDTO newDietDTO) {
        return service.createNewDiet(newDietDTO);
    }

    @PatchMapping("/{id}")
    public DietDTO updateDiet(@PathVariable UUID id, @RequestBody DietDTO dietDTO) {
        return service.updateDiet(id, dietDTO);
    }

    @DeleteMapping("/{id}")
    public boolean deleteDiet(@PathVariable UUID id) {
        return service.deleteDietById(id);
    }
}
