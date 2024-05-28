package com.codecool.chilibeans.service;


import com.codecool.chilibeans.controller.dto.client.*;
import com.codecool.chilibeans.controller.dto.client.JwtResponse;
import com.codecool.chilibeans.controller.dto.client.LoginRequest;
import com.codecool.chilibeans.controller.dto.client.NewClientDTO;
import com.codecool.chilibeans.controller.dto.client.ClientDTO;
import com.codecool.chilibeans.model.Client;
import com.codecool.chilibeans.model.Role;
import com.codecool.chilibeans.repository.ClientRepository;
import com.codecool.chilibeans.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @Autowired
    public ClientService(ClientRepository clientRepository, PasswordEncoder encoder, AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.clientRepository = clientRepository;
        this.encoder = encoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
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

    @Transactional
    public int deleteByPublicId(UUID publicId) {
        return clientRepository.deleteByPublicId(publicId);
    }

    public ClientDTO updateClientByUsername(UpdateClientDTO updateClientDTO, Principal principal) {

        Optional<Client> optionalUser = clientRepository.findByUsernameIgnoreCase(principal.getName());
        if (optionalUser.isEmpty()) {
            throw new NoSuchElementException();
        }
        Client client = optionalUser.get();

        client.setFirstName(updateClientDTO.firstName());
        client.setLastName(updateClientDTO.lastName());
        client.setDateOfBirth(updateClientDTO.dateOfBirth());
        client.setEmail(updateClientDTO.email());

        clientRepository.save(client);

        return convertToClientDTO(client);
    }

    public void updatePasswordByUsername(UpdatePasswordDTO updatePasswordDTO, Principal principal) {
        Optional<Client> optionalUser = clientRepository.findByUsernameIgnoreCase(principal.getName());
        if (optionalUser.isEmpty()) {
            throw new NoSuchElementException();
        }
        Client client = optionalUser.get();

        client.setPassword(encoder.encode(updatePasswordDTO.newPassword()));

        clientRepository.save(client);
    }

    private static ClientDTO convertToClientDTO(Client client) {
        return new ClientDTO(
                client.getPublicId(),
                client.getUsername(),
                client.getFirstName(),
                client.getLastName(),
                client.getDateOfBirth(),
                client.getEmail(),
                client.getOwnRecipes(),
                client.getFavoredRecipes(),
                client.getCreationDate());
    }

    public void registerClient(NewClientDTO registrationRequest) {
        if (clientRepository.existsByUsernameOrEmail(registrationRequest.username(), registrationRequest.email())) {
            throw new IllegalArgumentException("Username or Email already exists");
        }

        Client client = new Client();
        client.setPublicId(UUID.randomUUID());//hibernat eor Spring data
        client.setUsername(registrationRequest.username());
        client.setPassword(encoder.encode(registrationRequest.password()));
        client.setFirstName(registrationRequest.firstName());
        client.setLastName(registrationRequest.lastName());
        client.setDateOfBirth(registrationRequest.dateOfBirth());
        client.setEmail(registrationRequest.email());
        client.setCreationDate(LocalDate.now());//consider current_date in SQL
        client.setRoles(Set.of(Role.ROLE_USER));

        clientRepository.save(client);
    }

    public JwtResponse authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));

        String jwt = jwtUtils.generateJwtToken(authentication);

        User userDetails = (User) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .toList();
        return new JwtResponse(jwt, userDetails.getUsername(), roles);
    }
}

