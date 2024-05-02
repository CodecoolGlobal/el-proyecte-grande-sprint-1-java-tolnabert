package com.codecool.chilibeans.controller.dto.client;


import java.time.LocalDate;

public record NewClientDTO(String clientName,
                           String password,
                           String firstName,
                           String lastName,
                           LocalDate dateOfBirth,
                           String email) {
}

