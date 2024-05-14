package com.codecool.chilibeans.security.service;

import com.codecool.chilibeans.model.Client;
import com.codecool.chilibeans.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final ClientRepository clientRepository;

    @Autowired
    public UserDetailsServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Client client = clientRepository.findByUsernameIgnoreCase(username).orElseThrow(() -> new UsernameNotFoundException(username));

        List<SimpleGrantedAuthority> roles =
                client.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.name())).toList();

        return new User(client.getUsername(), client.getPassword(), roles);
    }
}
