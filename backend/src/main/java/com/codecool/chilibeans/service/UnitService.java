package com.codecool.chilibeans.service;

import com.codecool.chilibeans.controller.dto.unit.NewUnitDTO;
import com.codecool.chilibeans.controller.dto.unit.UnitDTO;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public interface UnitService {

    Set<UnitDTO> getAllUnits();

    UnitDTO createNewUnit(NewUnitDTO newUnitDTO);

    UnitDTO getUnitById(UUID uuid);

    UnitDTO deleteUnitById(UUID uuid);

    UnitDTO updateUnitById(UUID uuid, UnitDTO unitDTO);

}
