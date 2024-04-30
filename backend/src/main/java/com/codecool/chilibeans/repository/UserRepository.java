package com.codecool.chilibeans.repository;

import com.codecool.chilibeans.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
