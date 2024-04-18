package com.codecool.chilibeans.service;

import com.codecool.chilibeans.controller.dto.unit.NewUnitDTO;
import com.codecool.chilibeans.controller.dto.unit.UnitDTO;
import com.codecool.chilibeans.model.recipe.Unit;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UnitServiceImpl implements UnitService {

    private final Set<Unit> units = new HashSet<>();

    @Override
    public Set<UnitDTO> getAllUnits() {
        return units.stream()
                .map(unit -> new UnitDTO(unit.unitId(), unit.unitName()))
                .collect(Collectors.toSet());
    }

    @Override
    public UnitDTO createNewUnit(NewUnitDTO newUnitDTO) {
        Unit newUnit = new Unit(0, UUID.randomUUID(), newUnitDTO.unitName());
        units.add(newUnit);

        return new UnitDTO(newUnit.unitId(), newUnit.unitName());
    }

    @Override
    public UnitDTO getUnitById(UUID id) {
        Unit unit = units.stream()
                .filter(unit1 -> unit1.unitId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Unit not found with ID: " + id));

        return new UnitDTO(unit.unitId(), unit.unitName());
    }

    @Override
    public UnitDTO deleteUnitById(UUID id) {
        Unit unitToRemove = units.stream()
                .filter(unit -> unit.unitId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Unit not found with ID: " + id));

        units.remove(unitToRemove);

        return new UnitDTO(unitToRemove.unitId(), unitToRemove.unitName());
    }

    @Override
    public UnitDTO updateUnitById(UUID id, UnitDTO unitDTO) {
        Unit unitToUpdate = units.stream()
                .filter(unit -> unit.unitId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Unit not found with ID: " + id));

        units.remove(unitToUpdate);
        Unit updatedUnit = new Unit(unitToUpdate.databaseId(), unitToUpdate.unitId(), unitDTO.unitName());
        units.add(updatedUnit);

        return new UnitDTO(updatedUnit.unitId(), updatedUnit.unitName());
    }

}
