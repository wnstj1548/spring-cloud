package com.example.userservice.persistence.repository;

import com.example.userservice.persistence.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<User, Long> {
}
