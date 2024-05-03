package com.codecool.chilibeans.service;

import com.codecool.chilibeans.controller.dto.client.NewClientDTO;
import com.codecool.chilibeans.controller.dto.client.ClientDTO;
import com.codecool.chilibeans.exception.ElementMeantToSaveExists;
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

        return clients.stream().map(ClientService::convertToClientDTO).collect(Collectors.toSet());
    }

    public ClientDTO getByPublicId(UUID publicId) {
        Client client = clientRepository.findByPublicId(publicId)
                .orElseThrow(() -> new NoSuchElementException("User not found with ID: " + publicId));

        return convertToClientDTO(client);
    }

    private boolean existsByClientNameOrEmail(NewClientDTO newClientDTO){
        return clientRepository.existsByClientNameOrEmail(newClientDTO.clientName(), newClientDTO.email());
    }

    public ClientDTO save(NewClientDTO newClientDTO) {
        boolean exists = existsByClientNameOrEmail(newClientDTO);
        if (exists){
            throw new ElementMeantToSaveExists(newClientDTO);
        }
        //TODO consider registration form

        Client newClient = new Client();
        setAndSaveClientByDTO(newClientDTO, newClient);
        return convertToClientDTO(newClient);
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

        return convertToClientDTO(client);
    }

    public boolean deleteByPublicId(UUID publicId) {
        return clientRepository.deleteByPublicId(publicId);
    }

    private void setAndSaveClientByDTO(NewClientDTO newClientDTO, Client newClient){
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
    }

    private static ClientDTO convertToClientDTO(Client client){
        return new ClientDTO(
                client.getPublicId(),
                client.getClientName(),
                client.getFirstName(),
                client.getLastName(),
                client.getDateOfBirth(),
                client.getEmail(),
                client.getOwnRecipes(),
                client.getFavoredRecipes(),
                client.getCreationDate());
    }
}

