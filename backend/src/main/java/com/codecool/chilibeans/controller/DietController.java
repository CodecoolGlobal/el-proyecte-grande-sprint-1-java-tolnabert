package com.codecool.chilibeans.controller;

import com.codecool.chilibeans.controller.dto.DietDTO.DietDTO;
import com.codecool.chilibeans.controller.dto.DietDTO.NewDietDTO;
import com.codecool.chilibeans.service.ChiliServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/diets")
public class DietController {

    private final ChiliServiceInterface chiliService;

    @Autowired
    public DietController(ChiliServiceInterface chiliService) {
        this.chiliService = chiliService;
    }

    @GetMapping("")
    public Set<DietDTO> getDiets() {
        return chiliService.getAllDiets();
    }

    @GetMapping("/{id}")
    public DietDTO getDiet(@PathVariable UUID id) {
        return chiliService.getDietById(id);
    }

    @PostMapping("")
    public DietDTO createDiet(@RequestBody NewDietDTO newDietDTO) {
        return chiliService.createNewDiet(newDietDTO);
    }

    @PatchMapping("/{id}")
    public DietDTO updateDiet(@PathVariable UUID id, @RequestBody DietDTO dietDTO) {
        return chiliService.updateDiet(id, dietDTO);
    }

    @DeleteMapping("/{id}")
    public boolean deleteDiet(@PathVariable UUID id) {
        return chiliService.deleteDietById(id);
    }
}
