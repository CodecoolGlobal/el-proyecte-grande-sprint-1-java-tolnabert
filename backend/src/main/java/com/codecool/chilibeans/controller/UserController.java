package com.codecool.chilibeans.controller;

import com.codecool.chilibeans.controller.dto.user.NewUserDTO;
import com.codecool.chilibeans.controller.dto.user.UserDTO;
import com.codecool.chilibeans.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public Set<UserDTO> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public UserDTO getById(@PathVariable("id") UUID uuid) {
        return userService.getByPublicId(uuid);
    }

    @PostMapping("")
    public UserDTO create(@RequestBody NewUserDTO newUserDTO) {
        return userService.save(newUserDTO);
    }

    @PatchMapping("/{id}")
    public UserDTO updateById(@PathVariable("id") UUID uuid, @RequestBody UserDTO userDTO) {
        return userService.updateByPublicId(userDTO);
    }

    @DeleteMapping("/{id}")
    public boolean deleteById(@PathVariable("id") UUID uuid) {
        return userService.deleteByPublicId(uuid);
    }
}
