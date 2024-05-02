package com.codecool.chilibeans.service;

import com.codecool.chilibeans.controller.dto.client.NewClientDTO;
import com.codecool.chilibeans.controller.dto.client.ClientDTO;
import com.codecool.chilibeans.model.Client;
import com.codecool.chilibeans.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Set<ClientDTO> getAll() {
        List<Client> clients = clientRepository.findAll();

        return clients.stream().map(user -> new ClientDTO(user.getPublicId(), user.getClientName(), user.getFirstName(), user.getLastName(), user.getDateOfBirth(), user.getEmail(), user.getOwnRecipes(), user.getFavoredRecipes(), user.getCreationDate())).collect(Collectors.toSet());
    }

    public ClientDTO getByPublicId(UUID publicId) {
        Client client = clientRepository.findByPublicId(publicId)
                .orElseThrow(() -> new NoSuchElementException("User not found with ID: " + publicId));

        return new ClientDTO(client.getPublicId(), client.getClientName(), client.getFirstName(), client.getLastName(), client.getDateOfBirth(),
                client.getEmail(), client.getOwnRecipes(), client.getFavoredRecipes(), client.getCreationDate());
    }

    public ClientDTO save(NewClientDTO newClientDTO) {
        Optional<Client> optionalUser = clientRepository.findByClientNameIgnoreCase(newClientDTO.clientName());
        if(optionalUser.isEmpty()){
            //TODO lehet nem nev alapjan kene itt keresni
            Client newClient = new Client();
            newClient.setPublicId(UUID.randomUUID());
            newClient.setClientName(newClientDTO.clientName());
            newClient.setPassword(newClientDTO.password());
            newClient.setFirstName(newClientDTO.firstName());
            newClient.setLastName(newClientDTO.lastName());
            newClient.setDateOfBirth(newClientDTO.dateOfBirth());
            newClient.setEmail(newClientDTO.email());
            newClient.setOwnRecipes(new HashSet<>());
            newClient.setFavoredRecipes(new HashSet<>());
            newClient.setCreationDate(LocalDate.now());
            clientRepository.save(newClient);
            return new ClientDTO(newClient.getPublicId(), newClient.getClientName(), newClient.getFirstName(), newClient.getLastName(), newClient.getDateOfBirth(),
                    newClient.getEmail(), newClient.getOwnRecipes(), newClient.getFavoredRecipes(), newClient.getCreationDate());
        }
        Client client = optionalUser.get();
        return new ClientDTO(client.getPublicId(), client.getClientName(), client.getFirstName(), client.getLastName(), client.getDateOfBirth(),
                client.getEmail(), client.getOwnRecipes(), client.getFavoredRecipes(), client.getCreationDate());
    }

    public ClientDTO updateByPublicId(ClientDTO clientDTO) {
        Optional<Client> optionalUser = clientRepository.findByPublicId(clientDTO.publicId());
        if(optionalUser.isEmpty()){
            throw new NoSuchElementException();
        }
        Client client = optionalUser.get();

        client.setPublicId(UUID.randomUUID());
        client.setClientName(clientDTO.username());
        //TODO password?
        client.setFirstName(clientDTO.firstName());
        client.setLastName(clientDTO.lastName());
        client.setDateOfBirth(clientDTO.dateOfBirth());
        client.setEmail(clientDTO.email());
        client.setOwnRecipes(clientDTO.ownRecipes());
        client.setFavoredRecipes(clientDTO.favoredRecipes());
        client.setCreationDate(LocalDate.now());
        clientRepository.save(client);

        return new ClientDTO(client.getPublicId(), client.getClientName(), client.getFirstName(), client.getLastName(), client.getDateOfBirth(),
                client.getEmail(), client.getOwnRecipes(), client.getFavoredRecipes(), client.getCreationDate());
    }

    public boolean deleteByPublicId(UUID publicId) {
        return clientRepository.deleteByPublicId(publicId);
    }
}
