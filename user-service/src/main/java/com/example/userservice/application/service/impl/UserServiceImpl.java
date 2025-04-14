package com.example.userservice.application.service.impl;

import com.example.userservice.application.service.UserService;
import com.example.userservice.persistence.domain.User;
import com.example.userservice.persistence.repository.JpaUserRepository;
import com.example.userservice.presentation.dto.PrincipalDetails;
import com.example.userservice.presentation.dto.request.CreateUserRequest;
import com.example.userservice.presentation.dto.response.ReadOrderResponse;
import com.example.userservice.presentation.dto.response.ReadUserDetailResponse;
import com.example.userservice.presentation.dto.response.ReadUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<ReadUserResponse> getAllUsers() {
        return userRepository.findAll().stream().map(ReadUserResponse::fromEntity).toList();
    }

    @Override
    public ReadUserDetailResponse getUserByUserId(String userId) {

        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 유저가 없습니다."));

        List<ReadOrderResponse> orderList = new ArrayList<>();

        return ReadUserDetailResponse.from(user, orderList);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("해당하는 유저가 없습니다."));

        return new PrincipalDetails(user, null, null);
    }
}
