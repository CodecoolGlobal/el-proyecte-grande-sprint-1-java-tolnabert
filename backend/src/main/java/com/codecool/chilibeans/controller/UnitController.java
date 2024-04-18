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

    UnitService unitService;

    public UnitController(UnitService unitService) {
        this.unitService = unitService;
    }

    @GetMapping("")
    public Set<UnitDTO> getAllUnits() {
        return unitService.getAllUnits();
    }

    @GetMapping("/{id}")
    public UnitDTO getUnitById(@PathVariable("id") UUID uuid) {
        return unitService.getUnitById(uuid);
    }

    @PostMapping("")
    public UnitDTO createUnit(@RequestBody NewUnitDTO newUnitDTO) {
        return unitService.createNewUnit(newUnitDTO);
    }

    @DeleteMapping("/{id}")
    public UnitDTO deleteUnitById(@PathVariable("id") UUID uuid) {
        return unitService.deleteUnitById(uuid);
    }

    @PatchMapping("/{id}")
    public UnitDTO updateUnitById(@PathVariable("id") UUID uuid, @RequestBody UnitDTO unitDTO) {
        return unitService.updateUnitById(uuid, unitDTO);
    }

}
