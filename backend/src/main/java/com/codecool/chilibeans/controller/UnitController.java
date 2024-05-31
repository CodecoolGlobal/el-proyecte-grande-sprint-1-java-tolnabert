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

    @GetMapping
    public Set<UnitDTO> getAll() {
        return unitService.getAll();
    }

    @GetMapping("/{id}")
    public UnitDTO getById(@PathVariable("id") UUID uuid) {
        return unitService.getByPublicId(uuid);
    }

    @PostMapping("/admin")
    public UnitDTO create(@RequestBody NewUnitDTO newUnitDTO) {
        return unitService.save(newUnitDTO);
    }

    @PatchMapping("/admin")
    public UnitDTO updateById(@RequestBody UnitDTO unitDTO) {
        return unitService.updateById(unitDTO);
    }

    @DeleteMapping("/admin/{id}")
    public int deleteById(@PathVariable("id") UUID uuid) {
        return unitService.deleteByPublicId(uuid);
    }
}
