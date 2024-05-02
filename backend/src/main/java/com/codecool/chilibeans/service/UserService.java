package com.codecool.chilibeans.service;

import com.codecool.chilibeans.controller.dto.user.NewUserDTO;
import com.codecool.chilibeans.controller.dto.user.UserDTO;
import com.codecool.chilibeans.model.User;
import com.codecool.chilibeans.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Set<UserDTO> getAll() {
        List<User> users = userRepository.findAll();

        return users.stream().map(user -> new UserDTO(user.getPublicId(), user.getUsername(), user.getFirstName(), user.getLastName(), user.getDateOfBirth(), user.getEmail(), user.getOwnRecipes(), user.getFavoredRecipes(), user.getCreationDate())).collect(Collectors.toSet());
    }

    public UserDTO getByPublicId(UUID publicId) {
        User user = userRepository.findByPublicId(publicId)
                .orElseThrow(() -> new NoSuchElementException("User not found with ID: " + publicId));

        return new UserDTO(user.getPublicId(), user.getUsername(), user.getFirstName(), user.getLastName(), user.getDateOfBirth(),
                user.getEmail(), user.getOwnRecipes(), user.getFavoredRecipes(), user.getCreationDate());
    }

    public UserDTO save(NewUserDTO newUserDTO) {
        Optional<User> optionalUser = userRepository.findByUsernameIgnoreCase(newUserDTO.username());
        if(optionalUser.isEmpty()){
            //TODO lehet nem nev alapjan kene itt keresni
            User newUser = new User();
            newUser.setPublicId(UUID.randomUUID());
            newUser.setUsername(newUserDTO.username());
            newUser.setPassword(newUserDTO.password());
            newUser.setFirstName(newUserDTO.firstName());
            newUser.setLastName(newUserDTO.lastName());
            newUser.setDateOfBirth(newUserDTO.dateOfBirth());
            newUser.setEmail(newUserDTO.email());
            newUser.setOwnRecipes(newUserDTO.ownRecipes());
            newUser.setFavoredRecipes(newUserDTO.favoredRecipes());
            newUser.setCreationDate(LocalDate.now());
            userRepository.save(newUser);
            return new UserDTO(newUser.getPublicId(), newUser.getUsername(), newUser.getFirstName(), newUser.getLastName(), newUser.getDateOfBirth(),
                    newUser.getEmail(), newUser.getOwnRecipes(), newUser.getFavoredRecipes(), newUser.getCreationDate());
        }
        User user = optionalUser.get();
        return new UserDTO(user.getPublicId(), user.getUsername(), user.getFirstName(), user.getLastName(), user.getDateOfBirth(),
                user.getEmail(), user.getOwnRecipes(), user.getFavoredRecipes(), user.getCreationDate());
    }

    public UserDTO updateByPublicId(UserDTO userDTO) {
        Optional<User> optionalUser = userRepository.findByPublicId(userDTO.publicId());
        if(optionalUser.isEmpty()){
            throw new NoSuchElementException();
        }
        User user = optionalUser.get();

        user.setPublicId(UUID.randomUUID());
        user.setUsername(userDTO.username());
        //TODO password?
        user.setFirstName(userDTO.firstName());
        user.setLastName(userDTO.lastName());
        user.setDateOfBirth(userDTO.dateOfBirth());
        user.setEmail(userDTO.email());
        user.setOwnRecipes(userDTO.ownRecipes());
        user.setFavoredRecipes(userDTO.favoredRecipes());
        user.setCreationDate(LocalDate.now());
        userRepository.save(user);

        return new UserDTO(user.getPublicId(), user.getUsername(), user.getFirstName(), user.getLastName(), user.getDateOfBirth(),
                user.getEmail(), user.getOwnRecipes(), user.getFavoredRecipes(), user.getCreationDate());
    }

    public boolean deleteByPublicId(UUID publicId) {
        return userRepository.deleteByPublicId(publicId);
    }
}
