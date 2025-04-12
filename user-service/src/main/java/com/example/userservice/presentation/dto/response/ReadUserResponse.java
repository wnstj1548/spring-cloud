package com.example.userservice.presentation.dto.response;

import com.example.userservice.persistence.domain.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public record ReadUserResponse(
    String email,
    String name,
    String userId
) {
    public static ReadUserResponse fromEntity(User user) {
        return ReadUserResponse.builder()
                .email(user.getEmail())
                .name(user.getName())
                .userId(user.getUserId())
                .build();
    }
}
