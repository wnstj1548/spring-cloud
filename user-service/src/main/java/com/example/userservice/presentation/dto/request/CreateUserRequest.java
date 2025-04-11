package com.example.userservice.presentation.dto.request;

import com.example.userservice.persistence.domain.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

public record CreateUserRequest(

        @NotNull(message = "이메일이 없습니다.")
        @Size(min = 2, message = "2글자 이상이어야 합니다.")
        @Email
        String email,

        @NotNull(message = "이름이 없습니다.")
        @Size(min = 2, message = "2글자 이상이어야 합니다.")
        String name,

        @NotNull(message = "비밀번호가 없습니다.")
        @Size(min = 8, message = "8글자 이상이어야 합니다.")
        String pwd
) {
        public User toEntity(PasswordEncoder passwordEncoder) {
                return User.builder()
                        .userId(UUID.randomUUID().toString())
                        .email(email)
                        .name(name)
                        .pwd(passwordEncoder.encode(pwd))
                        .build();
        }
}
