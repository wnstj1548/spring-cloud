package com.example.userservice.presentation.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record LoginRequest(
        @NotNull(message = "이메일은 필수 값 입니다.")
        @Size(min = 2, message = "이메일은 2글자 이상이어야 합니다.")
        @Email
        String email,

        @NotNull(message = "비밀번호는 필수 값 입니다.")
        @Size(min = 8, message = "비밀번호는 8글자 이상이어야 합니다.")
        String password
) {
}
