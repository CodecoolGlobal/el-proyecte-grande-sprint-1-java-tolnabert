package com.codecool.chilibeans.controller;

import com.codecool.chilibeans.controller.dto.client.*;
import com.codecool.chilibeans.service.ClientService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/auth/register")
    public void createUser(@RequestBody NewClientDTO registrationRequest) {
        clientService.registerClient(registrationRequest);
    }

    @PostMapping("/auth/login")
    public JwtResponse authenticateUser(@RequestBody LoginRequest loginRequest) {
        return clientService.authenticateUser(loginRequest);
    }

    @GetMapping("/admin")
    public Set<ClientDTO> getAll() {
        return clientService.getAll();
    }

    @GetMapping("/{id}")
    public ClientDTO getByPublicId(@PathVariable("id") UUID uuid) {
        return clientService.getByPublicId(uuid);
    }

    @PatchMapping("/user/change-profile")
    public ClientDTO updateByUsername(@RequestBody UpdateClientDTO updateClientDTO, Principal principal) {
        return clientService.updateClientByUsername(updateClientDTO, principal);
    }

    @PatchMapping("/user/change-password")
    public void updatePasswordByUsername(@RequestBody UpdatePasswordDTO updatePasswordDTO, Principal principal) {
        clientService.updatePasswordByUsername(updatePasswordDTO, principal);
    }

    @DeleteMapping("/user/{id}")
    public int deleteById(@PathVariable("id") UUID uuid) {
        return clientService.deleteByPublicId(uuid);
    }
}