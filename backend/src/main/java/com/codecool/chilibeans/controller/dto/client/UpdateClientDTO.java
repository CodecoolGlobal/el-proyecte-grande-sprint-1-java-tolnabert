package com.codecool.chilibeans.controller.dto.client;

import java.time.LocalDate;
import java.util.UUID;

public record UpdateClientDTO(String firstName, String lastName, LocalDate dateOfBirth,
                              String email) {
}
