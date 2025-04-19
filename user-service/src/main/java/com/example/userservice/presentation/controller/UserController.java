package com.example.userservice.presentation.controller;

import com.example.userservice.application.service.UserService;
import com.example.userservice.presentation.dto.request.CreateUserRequest;
import com.example.userservice.presentation.dto.response.ReadUserDetailResponse;
import com.example.userservice.presentation.dto.response.ReadUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final Environment env;

    @GetMapping
    public ResponseEntity<List<ReadUserResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ReadUserDetailResponse> getByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(userService.getUserByUserId(userId));
    }

    @PostMapping
    public ResponseEntity<Long> createUser(@RequestBody CreateUserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(request));
    }

    @GetMapping("/health_check")
    public String healthCheck() {
        return String.format("It's working in user service"
                + ", port(local.server.port)=" + env.getProperty("local.server.port")
                + ", port(server.port)=" + env.getProperty("server.port")
                + ", token=" + env.getProperty("jwt.key"));

    }
}
