package com.codecool.chilibeans.controller;

import com.codecool.chilibeans.controller.dto.diet.DietDTO;
import com.codecool.chilibeans.controller.dto.diet.NewDietDTO;
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
    public DietController(DietService dietService) {
        this.dietService = dietService;
    }

    @GetMapping
    public Set<DietDTO> getAll() {
        return dietService.getAll();
    }

    @GetMapping("/{id}")
    public DietDTO getById(@PathVariable UUID id) {
        return dietService.getByPublicId(id);
    }

    @PostMapping("/user")
    public DietDTO create(@RequestBody NewDietDTO newDietDTO) {
        return dietService.save(newDietDTO);
    }

    @PatchMapping("/user")
    public DietDTO updateById(@RequestBody DietDTO dietDTO) {
        return dietService.updateByPublicId(dietDTO);
    }

    @DeleteMapping("/admin/{id}")
    public int deleteById(@PathVariable UUID id) {
        return dietService.deleteByPublicId(id);
    }
}
