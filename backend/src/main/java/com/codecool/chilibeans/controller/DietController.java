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

    private final DietService dietService;

    @Autowired
    public DietController(DietService chiliService) {
        this.dietService = chiliService;
    }

    @GetMapping("")
    public Set<DietDTO> getAll() {
        return dietService.getAll();
    }

    @GetMapping("/{id}")
    public DietDTO getById(@PathVariable UUID id) {
        return dietService.getById(id);
    }

    @PostMapping("")
    public DietDTO create(@RequestBody NewDietDTO newDietDTO) {
        return dietService.create(newDietDTO);
    }

    @PatchMapping("/{id}")
    public DietDTO updateById(@PathVariable UUID id, @RequestBody DietDTO dietDTO) {
        return dietService.updateById(id, dietDTO);
    }

    @DeleteMapping("/{id}")
    public boolean deleteById(@PathVariable UUID id) {
        return dietService.deleteById(id);
    }
}
