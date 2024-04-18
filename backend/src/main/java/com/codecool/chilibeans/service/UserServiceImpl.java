package com.codecool.chilibeans.service;

import com.codecool.chilibeans.controller.dto.user.NewUserDTO;
import com.codecool.chilibeans.controller.dto.user.UserDTO;
import com.codecool.chilibeans.model.User;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final Set<User> users = new HashSet<>();

    @Override
    public Set<UserDTO> getAllUsers() {
        Set<UserDTO> userDTOs = new HashSet<>();
        for (User user : users) {
            userDTOs.add(new UserDTO(user.id(), user.username(), user.firstName(), user.lastName(), user.dateOfBirth(),
                    user.email(), user.ownRecipes(), user.favoredRecipes(), user.creationDate()));
        }
        return userDTOs;
    }

    @Override
    public UserDTO getUserById(UUID id) {
        User user = users.stream().filter(user1 -> user1.id().equals(id))
                .findFirst()
                .orElse(null);
        return new UserDTO(user.id(), user.username(), user.firstName(), user.lastName(), user.dateOfBirth(),
                user.email(), user.ownRecipes(), user.favoredRecipes(), user.creationDate());
    }

    @Override
    public UserDTO createNewUser(NewUserDTO newUserDTO) {
        User newUser = new User(0, UUID.randomUUID(), newUserDTO.username(), newUserDTO.password(), newUserDTO.firstName(),
                newUserDTO.lastName(), newUserDTO.dateOfBirth(), newUserDTO.email(), newUserDTO.ownRecipes(), newUserDTO.favoredRecipes(),
                null);
        users.add(newUser);
        return new UserDTO(newUser.id(), newUser.username(), newUser.firstName(), newUser.lastName(),
                newUser.dateOfBirth(), newUser.email(), newUser.ownRecipes(), newUser.favoredRecipes(), null);
    }

    @Override
    public UserDTO updateUser(UUID id, UserDTO userDTO) {
        User userToUpdate = users.stream().filter(user1 -> user1.id().equals(id))
                .findFirst()
                .orElse(null);
        User updatedUser = new User(userToUpdate.databaseId(), userToUpdate.id(), userDTO.username(), userToUpdate.password(),
                userDTO.firstName(), userDTO.lastName(), userDTO.dateOfBirth(),
                userDTO.email(), userDTO.ownRecipes(), userDTO.favoredRecipes(), userToUpdate.creationDate());
        users.remove(userToUpdate);
        users.add(updatedUser);
        return userDTO;
    }

    @Override
    public boolean deleteUserById(UUID id) {
        return users.removeIf(user -> user.id().equals(id));
    }

}
