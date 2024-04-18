package com.codecool.chilibeans.service;

import com.codecool.chilibeans.controller.dto.user.NewUserDTO;
import com.codecool.chilibeans.controller.dto.user.UserDTO;

import java.util.Set;
import java.util.UUID;

public interface UserService {

    Set<UserDTO> getAllUsers();

    UserDTO getUserById(UUID id);

    UserDTO createNewUser(NewUserDTO newUserDTO);

    UserDTO updateUser(UUID id, UserDTO userDTO);

    boolean deleteUserById(UUID id);

}
