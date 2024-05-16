package com.codecool.chilibeans.repository;

import com.codecool.chilibeans.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByPublicId(UUID publicId);
    Optional<Client> findByUsernameIgnoreCase(String username);
    boolean existsByUsernameOrEmail(String username, String email);
    boolean deleteByPublicId(UUID publicId);
}
