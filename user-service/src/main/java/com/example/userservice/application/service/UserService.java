package com.example.userservice.application.service;

import com.example.userservice.presentation.dto.request.CreateUserRequest;
import com.example.userservice.presentation.dto.response.ReadUserDetailResponse;
import com.example.userservice.presentation.dto.response.ReadUserResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    long createUser(CreateUserRequest request);
    List<ReadUserResponse> getAllUsers();
    ReadUserDetailResponse getUserByUserId(String userId);
}
