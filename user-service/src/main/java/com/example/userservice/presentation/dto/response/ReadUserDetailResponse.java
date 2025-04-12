package com.example.userservice.presentation.dto.response;

import com.example.userservice.persistence.domain.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.util.List;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ReadUserDetailResponse(
        String email,
        String name,
        String userId,
        List<ReadOrderResponse> orderList
) {
    public static ReadUserDetailResponse from(User user, List<ReadOrderResponse> orderList) {
        return ReadUserDetailResponse.builder()
                .email(user.getEmail())
                .name(user.getName())
                .userId(user.getUserId())
                .orderList(orderList)
                .build();
    }
}
