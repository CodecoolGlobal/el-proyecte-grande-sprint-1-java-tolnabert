package com.codecool.chilibeans.controller.dto.client;

import java.util.List;

public record JwtResponse(String jwt, String username, List<String> roles) {
}
