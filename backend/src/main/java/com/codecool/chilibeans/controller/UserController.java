package com.codecool.chilibeans.controller;

import com.codecool.chilibeans.controller.dto.user.NewUserDTO;
import com.codecool.chilibeans.controller.dto.user.UserDTO;
import com.codecool.chilibeans.service.ChiliServiceInterface;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
public class UserController {

    private final ChiliServiceInterface service;

    public UserController(ChiliServiceInterface service) {
        this.service = service;
    }

    @GetMapping("/users")
    public Set<UserDTO> getAllUsers() {
        return service.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public UserDTO getAllUserById(@PathVariable("id")UUID uuid) {
        return service.getUserById(uuid);
    }

    @PostMapping("/users")
    public UserDTO createNewUser(@RequestBody NewUserDTO newUserDTO) {
        return service.createNewUser(newUserDTO);
    }

    @PatchMapping("/users/{id}")
    public UserDTO updateUser(@PathVariable("id") UUID uuid, @RequestBody UserDTO userDTO){
        return service.updateUser(uuid, userDTO);
    }

    @DeleteMapping("/users/{id}")
    public boolean deleteUser(@PathVariable("id") UUID uuid){
        return service.deleteUserById(uuid);
    }
}
