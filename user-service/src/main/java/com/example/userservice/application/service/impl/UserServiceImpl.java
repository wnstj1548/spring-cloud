package com.example.userservice.application.service.impl;

import com.example.userservice.application.service.UserService;
import com.example.userservice.persistence.domain.User;
import com.example.userservice.persistence.repository.JpaUserRepository;
import com.example.userservice.presentation.dto.request.CreateUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final JpaUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public long createUser(CreateUserRequest request) {

        User user = userRepository.save(request.toEntity(passwordEncoder));

        return user.getId();
    }
}
