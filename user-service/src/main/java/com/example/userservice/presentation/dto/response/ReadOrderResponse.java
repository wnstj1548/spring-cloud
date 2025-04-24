package com.example.userservice.presentation.dto.response;

import java.time.LocalDate;

public record ReadOrderResponse(
        String catalogId,
        Integer quantity,
        Integer unitPrice,
        Integer totalPrice,
        LocalDate createdAt,
        String orderId
) {
}
