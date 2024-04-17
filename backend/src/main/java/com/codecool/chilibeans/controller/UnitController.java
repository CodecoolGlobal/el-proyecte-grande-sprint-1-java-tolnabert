package com.codecool.chilibeans.controller;

import com.codecool.chilibeans.controller.dto.unit.NewUnitDTO;
import com.codecool.chilibeans.controller.dto.unit.UnitDTO;
import com.codecool.chilibeans.service.ChiliServiceInterface;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/units")
public class UnitController {

    ChiliServiceInterface chiliService;

    public UnitController(ChiliServiceInterface chiliService) {
        this.chiliService = chiliService;
    }

    @GetMapping("")
    public Set<UnitDTO> getAllUnits() {
        return chiliService.getAllUnits();
    }

    @GetMapping("/{id}")
    public UnitDTO getUnitById(@PathVariable("id") UUID uuid) {
        return chiliService.getUnitById(uuid);
    }

    @PostMapping("")
    public UnitDTO createUnit(@RequestBody NewUnitDTO newUnitDTO) {
        return chiliService.createNewUnit(newUnitDTO);
    }

    @DeleteMapping("/{id}")
    public UnitDTO deleteUnitById(@PathVariable("id") UUID uuid) {
        return chiliService.deleteUnitById(uuid);
    }

    @PatchMapping("/{id}")
    public UnitDTO updateUnitById(@PathVariable("id") UUID uuid, @RequestBody UnitDTO unitDTO) {
        return chiliService.updateUnitById(uuid, unitDTO);
    }

}
