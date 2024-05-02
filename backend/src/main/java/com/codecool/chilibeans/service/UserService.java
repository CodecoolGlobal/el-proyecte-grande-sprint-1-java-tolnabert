package com.codecool.chilibeans.service;

import com.codecool.chilibeans.controller.dto.user.NewUserDTO;
import com.codecool.chilibeans.controller.dto.user.UserDTO;
import com.codecool.chilibeans.model.Client;
import com.codecool.chilibeans.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final ClientRepository clientRepository;

    @Autowired
    public UserService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Set<UserDTO> getAll() {
        List<Client> clients = clientRepository.findAll();

        return clients.stream().map(user -> new UserDTO(user.getPublicId(), user.getClientName(), user.getFirstName(), user.getLastName(), user.getDateOfBirth(), user.getEmail(), user.getOwnRecipes(), user.getFavoredRecipes(), user.getCreationDate())).collect(Collectors.toSet());
    }

    public UserDTO getByPublicId(UUID publicId) {
        Client client = clientRepository.findByPublicId(publicId)
                .orElseThrow(() -> new NoSuchElementException("User not found with ID: " + publicId));

        return new UserDTO(client.getPublicId(), client.getClientName(), client.getFirstName(), client.getLastName(), client.getDateOfBirth(),
                client.getEmail(), client.getOwnRecipes(), client.getFavoredRecipes(), client.getCreationDate());
    }

    public UserDTO save(NewUserDTO newUserDTO) {
        Optional<Client> optionalUser = clientRepository.findByClientNameIgnoreCase(newUserDTO.username());
        if(optionalUser.isEmpty()){
            //TODO lehet nem nev alapjan kene itt keresni
            Client newClient = new Client();
            newClient.setPublicId(UUID.randomUUID());
            newClient.setClientName(newUserDTO.username());
            newClient.setPassword(newUserDTO.password());
            newClient.setFirstName(newUserDTO.firstName());
            newClient.setLastName(newUserDTO.lastName());
            newClient.setDateOfBirth(newUserDTO.dateOfBirth());
            newClient.setEmail(newUserDTO.email());
            newClient.setOwnRecipes(newUserDTO.ownRecipes());
            newClient.setFavoredRecipes(newUserDTO.favoredRecipes());
            newClient.setCreationDate(LocalDate.now());
            clientRepository.save(newClient);
            return new UserDTO(newClient.getPublicId(), newClient.getClientName(), newClient.getFirstName(), newClient.getLastName(), newClient.getDateOfBirth(),
                    newClient.getEmail(), newClient.getOwnRecipes(), newClient.getFavoredRecipes(), newClient.getCreationDate());
        }
        Client client = optionalUser.get();
        return new UserDTO(client.getPublicId(), client.getClientName(), client.getFirstName(), client.getLastName(), client.getDateOfBirth(),
                client.getEmail(), client.getOwnRecipes(), client.getFavoredRecipes(), client.getCreationDate());
    }

    public UserDTO updateByPublicId(UserDTO userDTO) {
        Optional<Client> optionalUser = clientRepository.findByPublicId(userDTO.publicId());
        if(optionalUser.isEmpty()){
            throw new NoSuchElementException();
        }
        Client client = optionalUser.get();

        client.setPublicId(UUID.randomUUID());
        client.setClientName(userDTO.username());
        //TODO password?
        client.setFirstName(userDTO.firstName());
        client.setLastName(userDTO.lastName());
        client.setDateOfBirth(userDTO.dateOfBirth());
        client.setEmail(userDTO.email());
        client.setOwnRecipes(userDTO.ownRecipes());
        client.setFavoredRecipes(userDTO.favoredRecipes());
        client.setCreationDate(LocalDate.now());
        clientRepository.save(client);

        return new UserDTO(client.getPublicId(), client.getClientName(), client.getFirstName(), client.getLastName(), client.getDateOfBirth(),
                client.getEmail(), client.getOwnRecipes(), client.getFavoredRecipes(), client.getCreationDate());
    }

    public boolean deleteByPublicId(UUID publicId) {
        return clientRepository.deleteByPublicId(publicId);
    }
}
