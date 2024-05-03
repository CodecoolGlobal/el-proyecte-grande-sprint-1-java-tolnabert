package com.codecool.chilibeans.controller;

import com.codecool.chilibeans.controller.dto.client.NewClientDTO;
import com.codecool.chilibeans.controller.dto.client.ClientDTO;
import com.codecool.chilibeans.exception.ElementMeantToSaveExists;
import com.codecool.chilibeans.service.ClientService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public Set<ClientDTO> getAll() {
        return clientService.getAll();
    }

    @GetMapping("/{id}")
    public ClientDTO getById(@PathVariable("id") UUID uuid) {
        return clientService.getByPublicId(uuid);
    }

    @PostMapping
    public ClientDTO create(@RequestBody NewClientDTO newClientDTO) {
        boolean exists = clientService.existsByClientNameOrEmail(newClientDTO);
        if (exists){
            throw new ElementMeantToSaveExists(newClientDTO);
        }
        return clientService.save(newClientDTO);
    }

    @PatchMapping
    public ClientDTO updateById(@RequestBody ClientDTO clientDTO) {
        return clientService.updateByPublicId(clientDTO);
    }

    @DeleteMapping("/{id}")
    public boolean deleteById(@PathVariable("id") UUID uuid) {
        return clientService.deleteByPublicId(uuid);
    }
}
