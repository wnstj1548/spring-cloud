package com.example.userservice.application.service;

import com.example.userservice.presentation.dto.request.CreateUserRequest;

public interface UserService {
    long createUser(CreateUserRequest request);
}
