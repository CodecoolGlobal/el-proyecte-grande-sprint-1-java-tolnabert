package com.codecool.chilibeans.controller;

import com.codecool.chilibeans.controller.dto.unit.NewUnitDTO;
import com.codecool.chilibeans.controller.dto.unit.UnitDTO;
import com.codecool.chilibeans.service.UnitService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/units")
public class UnitController {

    private final UnitService unitService;

    public UnitController(UnitService unitService) {
        this.unitService = unitService;
    }

    @GetMapping("")
    public Set<UnitDTO> getAll() {
        return unitService.getAll();
    }

    @GetMapping("/{id}")
    public UnitDTO getById(@PathVariable("id") UUID uuid) {
        return unitService.getById(uuid);
    }

    @PostMapping("")
    public UnitDTO create(@RequestBody NewUnitDTO newUnitDTO) {
        return unitService.create(newUnitDTO);
    }

    @PatchMapping("/{id}")
    public UnitDTO updateById(@PathVariable("id") UUID uuid, @RequestBody UnitDTO unitDTO) {
        return unitService.updateById(uuid, unitDTO);
    }

    @DeleteMapping("/{id}")
    public UnitDTO deleteById(@PathVariable("id") UUID uuid) {
        return unitService.deleteById(uuid);
    }
}
