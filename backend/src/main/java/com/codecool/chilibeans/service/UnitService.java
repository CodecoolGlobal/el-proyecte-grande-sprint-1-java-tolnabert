package com.codecool.chilibeans.service;

import com.codecool.chilibeans.controller.dto.unit.NewUnitDTO;
import com.codecool.chilibeans.controller.dto.unit.UnitDTO;
import com.codecool.chilibeans.exception.ElementMeantToSaveExists;
import com.codecool.chilibeans.model.recipe.Unit;
import com.codecool.chilibeans.repository.recipe.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UnitService {

    private final UnitRepository unitRepository;

    @Autowired
    public UnitService(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }

    public Set<UnitDTO> getAll() {
        List<Unit> units = unitRepository.findAll();
        return units.stream()
                .map(unit -> new UnitDTO(unit.getPublicId(), unit.getUnitName()))
                .collect(Collectors.toSet());
    }

    public UnitDTO getByPublicId(UUID publicId) {
        Unit unit = unitRepository.findByPublicId(publicId)
                .orElseThrow(() -> new NoSuchElementException("Unit not found with ID: " + publicId));

        return new UnitDTO(unit.getPublicId(), unit.getUnitName());
    }

    public UnitDTO save(NewUnitDTO newUnitDTO) {
        Optional<Unit> optionalUnit = unitRepository.findByUnitNameIgnoreCase(newUnitDTO.unitName());
        if(optionalUnit.isEmpty()){
            Unit newUnit = new Unit();
            newUnit.setPublicId(UUID.randomUUID());
            newUnit.setUnitName(newUnitDTO.unitName());
            unitRepository.save(newUnit);
            return new UnitDTO(newUnit.getPublicId(), newUnit.getUnitName());
        }
        throw new ElementMeantToSaveExists(newUnitDTO);
    }

    public UnitDTO updateById(UnitDTO unitDTO) {
        Optional<Unit> optionalUnit = unitRepository.findByPublicId(unitDTO.publicId());
        if(optionalUnit.isEmpty()){
            throw new NoSuchElementException();
        }
        Unit unit = optionalUnit.get();
        unit.setUnitName(unitDTO.unitName());

        return new UnitDTO(unit.getPublicId(), unit.getUnitName());
    }

    public boolean deleteByPublicId(UUID publicId) {
        return unitRepository.deleteByPublicId(publicId);
    }
}
